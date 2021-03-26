/*      */ package org.apache.http.nio.protocol;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.net.SocketTimeoutException;
/*      */ import java.util.Queue;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import org.apache.http.ConnectionReuseStrategy;
/*      */ import org.apache.http.ExceptionLogger;
/*      */ import org.apache.http.HttpEntity;
/*      */ import org.apache.http.HttpEntityEnclosingRequest;
/*      */ import org.apache.http.HttpException;
/*      */ import org.apache.http.HttpRequest;
/*      */ import org.apache.http.HttpResponse;
/*      */ import org.apache.http.HttpResponseFactory;
/*      */ import org.apache.http.HttpVersion;
/*      */ import org.apache.http.ProtocolVersion;
/*      */ import org.apache.http.annotation.Contract;
/*      */ import org.apache.http.annotation.ThreadingBehavior;
/*      */ import org.apache.http.concurrent.Cancellable;
/*      */ import org.apache.http.entity.ContentType;
/*      */ import org.apache.http.impl.DefaultConnectionReuseStrategy;
/*      */ import org.apache.http.impl.DefaultHttpResponseFactory;
/*      */ import org.apache.http.nio.ContentDecoder;
/*      */ import org.apache.http.nio.ContentEncoder;
/*      */ import org.apache.http.nio.IOControl;
/*      */ import org.apache.http.nio.NHttpConnection;
/*      */ import org.apache.http.nio.NHttpServerConnection;
/*      */ import org.apache.http.nio.NHttpServerEventHandler;
/*      */ import org.apache.http.nio.entity.NStringEntity;
/*      */ import org.apache.http.nio.reactor.SessionBufferStatus;
/*      */ import org.apache.http.params.HttpParams;
/*      */ import org.apache.http.protocol.BasicHttpContext;
/*      */ import org.apache.http.protocol.HttpContext;
/*      */ import org.apache.http.protocol.HttpProcessor;
/*      */ import org.apache.http.util.Args;
/*      */ import org.apache.http.util.Asserts;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*      */ public class HttpAsyncService
/*      */   implements NHttpServerEventHandler
/*      */ {
/*      */   static final String HTTP_EXCHANGE_STATE = "http.nio.http-exchange-state";
/*      */   private final HttpProcessor httpProcessor;
/*      */   private final ConnectionReuseStrategy connectionStrategy;
/*      */   private final HttpResponseFactory responseFactory;
/*      */   private final HttpAsyncRequestHandlerMapper handlerMapper;
/*      */   private final HttpAsyncExpectationVerifier expectationVerifier;
/*      */   private final ExceptionLogger exceptionLogger;
/*      */   
/*      */   @Deprecated
/*      */   public HttpAsyncService(HttpProcessor httpProcessor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpAsyncRequestHandlerResolver handlerResolver, HttpAsyncExpectationVerifier expectationVerifier, HttpParams params) {
/*  136 */     this(httpProcessor, connStrategy, responseFactory, new HttpAsyncRequestHandlerResolverAdapter(handlerResolver), expectationVerifier);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public HttpAsyncService(HttpProcessor httpProcessor, ConnectionReuseStrategy connStrategy, HttpAsyncRequestHandlerResolver handlerResolver, HttpParams params) {
/*  161 */     this(httpProcessor, connStrategy, (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE, new HttpAsyncRequestHandlerResolverAdapter(handlerResolver), null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpAsyncService(HttpProcessor httpProcessor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpAsyncRequestHandlerMapper handlerMapper, HttpAsyncExpectationVerifier expectationVerifier) {
/*  187 */     this(httpProcessor, connStrategy, responseFactory, handlerMapper, expectationVerifier, (ExceptionLogger)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpAsyncService(HttpProcessor httpProcessor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpAsyncRequestHandlerMapper handlerMapper, HttpAsyncExpectationVerifier expectationVerifier, ExceptionLogger exceptionLogger) {
/*  215 */     this.httpProcessor = (HttpProcessor)Args.notNull(httpProcessor, "HTTP processor");
/*  216 */     this.connectionStrategy = (connStrategy != null) ? connStrategy : (ConnectionReuseStrategy)DefaultConnectionReuseStrategy.INSTANCE;
/*      */     
/*  218 */     this.responseFactory = (responseFactory != null) ? responseFactory : (HttpResponseFactory)DefaultHttpResponseFactory.INSTANCE;
/*      */     
/*  220 */     this.handlerMapper = handlerMapper;
/*  221 */     this.expectationVerifier = expectationVerifier;
/*  222 */     this.exceptionLogger = (exceptionLogger != null) ? exceptionLogger : ExceptionLogger.NO_OP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpAsyncService(HttpProcessor httpProcessor, HttpAsyncRequestHandlerMapper handlerMapper) {
/*  236 */     this(httpProcessor, null, null, handlerMapper, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpAsyncService(HttpProcessor httpProcessor, HttpAsyncRequestHandlerMapper handlerMapper, ExceptionLogger exceptionLogger) {
/*  255 */     this(httpProcessor, (ConnectionReuseStrategy)null, (HttpResponseFactory)null, handlerMapper, (HttpAsyncExpectationVerifier)null, exceptionLogger);
/*      */   }
/*      */ 
/*      */   
/*      */   public void connected(NHttpServerConnection conn) {
/*  260 */     State state = new State();
/*  261 */     conn.getContext().setAttribute("http.nio.http-exchange-state", state);
/*      */   }
/*      */ 
/*      */   
/*      */   public void closed(NHttpServerConnection conn) {
/*  266 */     State state = (State)conn.getContext().removeAttribute("http.nio.http-exchange-state");
/*  267 */     if (state != null) {
/*  268 */       state.setTerminated();
/*  269 */       closeHandlers(state);
/*  270 */       Cancellable cancellable = state.getCancellable();
/*  271 */       if (cancellable != null) {
/*  272 */         cancellable.cancel();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void exception(NHttpServerConnection conn, Exception cause) {
/*  280 */     log(cause);
/*  281 */     State state = getState((NHttpConnection)conn);
/*  282 */     if (state == null) {
/*  283 */       shutdownConnection((NHttpConnection)conn);
/*      */       return;
/*      */     } 
/*  286 */     state.setTerminated();
/*  287 */     closeHandlers(state, cause);
/*  288 */     Cancellable cancellable = state.getCancellable();
/*  289 */     if (cancellable != null) {
/*  290 */       cancellable.cancel();
/*      */     }
/*  292 */     Queue<PipelineEntry> pipeline = state.getPipeline();
/*  293 */     if (!pipeline.isEmpty() || conn.isResponseSubmitted() || state.getResponseState().compareTo(MessageState.INIT) > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  298 */       shutdownConnection((NHttpConnection)conn);
/*      */     } else {
/*      */       try {
/*  301 */         Incoming incoming = state.getIncoming();
/*  302 */         HttpRequest request = (incoming != null) ? incoming.getRequest() : null;
/*  303 */         HttpContext context = (incoming != null) ? incoming.getContext() : (HttpContext)new BasicHttpContext();
/*  304 */         HttpAsyncResponseProducer responseProducer = handleException(cause, context);
/*  305 */         HttpResponse response = responseProducer.generateResponse();
/*  306 */         Outgoing outgoing = new Outgoing(request, response, responseProducer, context);
/*  307 */         state.setResponseState(MessageState.INIT);
/*  308 */         state.setOutgoing(outgoing);
/*  309 */         commitFinalResponse(conn, state);
/*  310 */       } catch (Exception ex) {
/*  311 */         shutdownConnection((NHttpConnection)conn);
/*  312 */         closeHandlers(state);
/*  313 */         if (ex instanceof RuntimeException) {
/*  314 */           throw (RuntimeException)ex;
/*      */         }
/*  316 */         log(ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestReceived(NHttpServerConnection conn) throws IOException, HttpException {
/*  324 */     State state = getState((NHttpConnection)conn);
/*  325 */     Asserts.notNull(state, "Connection state");
/*  326 */     Asserts.check((state.getRequestState() == MessageState.READY), "Unexpected request state %s", state.getRequestState());
/*      */ 
/*      */     
/*  329 */     HttpRequest request = conn.getHttpRequest();
/*  330 */     BasicHttpContext basicHttpContext = new BasicHttpContext();
/*      */     
/*  332 */     basicHttpContext.setAttribute("http.request", request);
/*  333 */     basicHttpContext.setAttribute("http.connection", conn);
/*  334 */     this.httpProcessor.process(request, (HttpContext)basicHttpContext);
/*      */     
/*  336 */     HttpAsyncRequestHandler<Object> requestHandler = getRequestHandler(request);
/*  337 */     HttpAsyncRequestConsumer<Object> consumer = requestHandler.processRequest(request, (HttpContext)basicHttpContext);
/*  338 */     consumer.requestReceived(request);
/*      */     
/*  340 */     Incoming incoming = new Incoming(request, requestHandler, consumer, (HttpContext)basicHttpContext);
/*  341 */     state.setIncoming(incoming);
/*      */     
/*  343 */     if (request instanceof HttpEntityEnclosingRequest) {
/*      */ 
/*      */ 
/*      */       
/*  347 */       if (((HttpEntityEnclosingRequest)request).expectContinue() && state.getResponseState() == MessageState.READY && state.getPipeline().isEmpty() && (!(conn instanceof SessionBufferStatus) || !((SessionBufferStatus)conn).hasBufferedInput())) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  352 */         state.setRequestState(MessageState.ACK_EXPECTED);
/*  353 */         HttpResponse ack = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, 100, (HttpContext)basicHttpContext);
/*      */         
/*  355 */         if (this.expectationVerifier != null) {
/*  356 */           conn.suspendInput();
/*  357 */           conn.suspendOutput();
/*  358 */           HttpAsyncExchange httpAsyncExchange = new HttpAsyncExchangeImpl(request, ack, state, conn, (HttpContext)basicHttpContext);
/*      */           
/*  360 */           this.expectationVerifier.verify(httpAsyncExchange, (HttpContext)basicHttpContext);
/*      */         } else {
/*  362 */           conn.submitResponse(ack);
/*  363 */           state.setRequestState(MessageState.BODY_STREAM);
/*      */         } 
/*      */       } else {
/*  366 */         state.setRequestState(MessageState.BODY_STREAM);
/*      */       } 
/*      */     } else {
/*      */       
/*  370 */       completeRequest(incoming, conn, state);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void inputReady(NHttpServerConnection conn, ContentDecoder decoder) throws IOException, HttpException {
/*  378 */     State state = getState((NHttpConnection)conn);
/*  379 */     Asserts.notNull(state, "Connection state");
/*  380 */     Asserts.check((state.getRequestState() == MessageState.BODY_STREAM), "Unexpected request state %s", state.getRequestState());
/*      */ 
/*      */     
/*  383 */     Incoming incoming = state.getIncoming();
/*  384 */     Asserts.notNull(incoming, "Incoming request");
/*  385 */     HttpAsyncRequestConsumer<?> consumer = incoming.getConsumer();
/*  386 */     consumer.consumeContent(decoder, (IOControl)conn);
/*  387 */     if (decoder.isCompleted()) {
/*  388 */       completeRequest(incoming, conn, state);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void responseReady(NHttpServerConnection conn) throws IOException, HttpException {
/*  395 */     State state = getState((NHttpConnection)conn);
/*  396 */     Asserts.notNull(state, "Connection state");
/*  397 */     Asserts.check((state.getResponseState() == MessageState.READY || state.getResponseState() == MessageState.INIT), "Unexpected response state %s", state.getResponseState());
/*      */ 
/*      */ 
/*      */     
/*  401 */     if (state.getRequestState() == MessageState.ACK_EXPECTED) {
/*      */       Outgoing outgoing;
/*  403 */       synchronized (state) {
/*  404 */         outgoing = state.getOutgoing();
/*  405 */         if (outgoing == null) {
/*  406 */           conn.suspendOutput();
/*      */           return;
/*      */         } 
/*      */       } 
/*  410 */       HttpResponse response = outgoing.getResponse();
/*  411 */       int status = response.getStatusLine().getStatusCode();
/*  412 */       if (status == 100) {
/*  413 */         HttpContext context = outgoing.getContext();
/*  414 */         HttpAsyncResponseProducer responseProducer = outgoing.getProducer();
/*      */         
/*      */         try {
/*  417 */           response.setEntity(null);
/*  418 */           conn.requestInput();
/*  419 */           state.setRequestState(MessageState.BODY_STREAM);
/*  420 */           state.setOutgoing(null);
/*  421 */           conn.submitResponse(response);
/*  422 */           responseProducer.responseCompleted(context);
/*      */         } finally {
/*  424 */           responseProducer.close();
/*      */         } 
/*  426 */       } else if (status >= 400) {
/*  427 */         conn.resetInput();
/*  428 */         state.setRequestState(MessageState.READY);
/*  429 */         commitFinalResponse(conn, state);
/*      */       } else {
/*  431 */         throw new HttpException("Invalid response: " + response.getStatusLine());
/*      */       } 
/*      */     } else {
/*  434 */       if (state.getResponseState() == MessageState.READY) {
/*  435 */         Queue<PipelineEntry> pipeline = state.getPipeline();
/*  436 */         PipelineEntry pipelineEntry = pipeline.poll();
/*  437 */         if (pipelineEntry == null) {
/*  438 */           conn.suspendOutput();
/*      */           return;
/*      */         } 
/*  441 */         state.setResponseState(MessageState.INIT);
/*  442 */         Object result = pipelineEntry.getResult();
/*  443 */         HttpRequest request = pipelineEntry.getRequest();
/*  444 */         HttpContext context = pipelineEntry.getContext();
/*  445 */         if (result != null) {
/*  446 */           HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, 200, context);
/*      */           
/*  448 */           HttpAsyncExchangeImpl httpExchange = new HttpAsyncExchangeImpl(request, response, state, conn, context);
/*      */           
/*  450 */           HttpAsyncRequestHandler<Object> handler = pipelineEntry.getHandler();
/*  451 */           conn.suspendOutput();
/*      */           try {
/*  453 */             handler.handle(result, httpExchange, context);
/*  454 */           } catch (RuntimeException ex) {
/*  455 */             throw ex;
/*  456 */           } catch (Exception ex) {
/*  457 */             pipeline.add(new PipelineEntry(request, null, ex, handler, context));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  463 */             state.setResponseState(MessageState.READY);
/*  464 */             responseReady(conn);
/*      */             return;
/*      */           } 
/*      */         } else {
/*  468 */           Exception exception = pipelineEntry.getException();
/*  469 */           HttpAsyncResponseProducer responseProducer = handleException((exception != null) ? exception : (Exception)new HttpException("Internal error processing request"), context);
/*      */ 
/*      */           
/*  472 */           HttpResponse error = responseProducer.generateResponse();
/*  473 */           state.setOutgoing(new Outgoing(request, error, responseProducer, context));
/*      */         } 
/*      */       } 
/*  476 */       if (state.getResponseState() == MessageState.INIT) {
/*      */         Outgoing outgoing;
/*  478 */         synchronized (state) {
/*  479 */           outgoing = state.getOutgoing();
/*  480 */           if (outgoing == null) {
/*  481 */             conn.suspendOutput();
/*      */             return;
/*      */           } 
/*      */         } 
/*  485 */         HttpResponse response = outgoing.getResponse();
/*  486 */         int status = response.getStatusLine().getStatusCode();
/*  487 */         if (status >= 200) {
/*  488 */           commitFinalResponse(conn, state);
/*      */         } else {
/*  490 */           throw new HttpException("Invalid response: " + response.getStatusLine());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void outputReady(NHttpServerConnection conn, ContentEncoder encoder) throws HttpException, IOException {
/*  500 */     State state = getState((NHttpConnection)conn);
/*  501 */     Asserts.notNull(state, "Connection state");
/*  502 */     Asserts.check((state.getResponseState() == MessageState.BODY_STREAM), "Unexpected response state %s", state.getResponseState());
/*      */ 
/*      */     
/*  505 */     Outgoing outgoing = state.getOutgoing();
/*  506 */     Asserts.notNull(outgoing, "Outgoing response");
/*  507 */     HttpAsyncResponseProducer responseProducer = outgoing.getProducer();
/*      */     
/*  509 */     responseProducer.produceContent(encoder, (IOControl)conn);
/*      */     
/*  511 */     if (encoder.isCompleted()) {
/*  512 */       completeResponse(outgoing, conn, state);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endOfInput(NHttpServerConnection conn) throws IOException {
/*  522 */     if (conn.getSocketTimeout() <= 0) {
/*  523 */       conn.setSocketTimeout(1000);
/*      */     }
/*  525 */     conn.close();
/*      */   }
/*      */ 
/*      */   
/*      */   public void timeout(NHttpServerConnection conn) throws IOException {
/*  530 */     State state = getState((NHttpConnection)conn);
/*  531 */     if (state != null) {
/*  532 */       exception(conn, new SocketTimeoutException(String.format("%,d milliseconds timeout on connection %s", new Object[] { Integer.valueOf(conn.getSocketTimeout()), conn })));
/*      */     }
/*      */     
/*  535 */     if (conn.getStatus() == 0) {
/*  536 */       conn.close();
/*  537 */       if (conn.getStatus() == 1)
/*      */       {
/*      */         
/*  540 */         conn.setSocketTimeout(250);
/*      */       }
/*      */     } else {
/*  543 */       conn.shutdown();
/*      */     } 
/*      */   }
/*      */   
/*      */   private State getState(NHttpConnection conn) {
/*  548 */     return (State)conn.getContext().getAttribute("http.nio.http-exchange-state");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void log(Exception ex) {
/*  559 */     this.exceptionLogger.log(ex);
/*      */   }
/*      */   
/*      */   private void shutdownConnection(NHttpConnection conn) {
/*      */     try {
/*  564 */       conn.shutdown();
/*  565 */     } catch (IOException ex) {
/*  566 */       log(ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void closeHandlers(State state, Exception ex) {
/*  571 */     HttpAsyncRequestConsumer<Object> consumer = (state.getIncoming() != null) ? state.getIncoming().getConsumer() : null;
/*      */     
/*  573 */     if (consumer != null) {
/*      */       try {
/*  575 */         consumer.failed(ex);
/*      */       } finally {
/*      */         try {
/*  578 */           consumer.close();
/*  579 */         } catch (IOException ioex) {
/*  580 */           log(ioex);
/*      */         } 
/*      */       } 
/*      */     }
/*  584 */     HttpAsyncResponseProducer producer = (state.getOutgoing() != null) ? state.getOutgoing().getProducer() : null;
/*      */     
/*  586 */     if (producer != null) {
/*      */       try {
/*  588 */         producer.failed(ex);
/*      */       } finally {
/*      */         try {
/*  591 */           producer.close();
/*  592 */         } catch (IOException ioex) {
/*  593 */           log(ioex);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void closeHandlers(State state) {
/*  600 */     HttpAsyncRequestConsumer<Object> consumer = (state.getIncoming() != null) ? state.getIncoming().getConsumer() : null;
/*      */     
/*  602 */     if (consumer != null) {
/*      */       try {
/*  604 */         consumer.close();
/*  605 */       } catch (IOException ioex) {
/*  606 */         log(ioex);
/*      */       } 
/*      */     }
/*  609 */     HttpAsyncResponseProducer producer = (state.getOutgoing() != null) ? state.getOutgoing().getProducer() : null;
/*      */     
/*  611 */     if (producer != null) {
/*      */       try {
/*  613 */         producer.close();
/*  614 */       } catch (IOException ioex) {
/*  615 */         log(ioex);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected HttpAsyncResponseProducer handleException(Exception ex, HttpContext context) {
/*  622 */     String message = ex.getMessage();
/*  623 */     if (message == null) {
/*  624 */       message = ex.toString();
/*      */     }
/*  626 */     HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, toStatusCode(ex, context), context);
/*      */     
/*  628 */     return new ErrorResponseProducer(response, (HttpEntity)new NStringEntity(message, ContentType.DEFAULT_TEXT), false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected int toStatusCode(Exception ex, HttpContext context) {
/*      */     int code;
/*  634 */     if (ex instanceof org.apache.http.MethodNotSupportedException) {
/*  635 */       code = 501;
/*  636 */     } else if (ex instanceof org.apache.http.UnsupportedHttpVersionException) {
/*  637 */       code = 505;
/*  638 */     } else if (ex instanceof org.apache.http.ProtocolException) {
/*  639 */       code = 400;
/*  640 */     } else if (ex instanceof SocketTimeoutException) {
/*  641 */       code = 504;
/*      */     } else {
/*  643 */       code = 500;
/*      */     } 
/*  645 */     return code;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleAlreadySubmittedResponse(Cancellable cancellable, HttpContext context) {
/*  659 */     throw new IllegalStateException("Response already submitted");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleAlreadySubmittedResponse(HttpAsyncResponseProducer responseProducer, HttpContext context) {
/*  673 */     throw new IllegalStateException("Response already submitted");
/*      */   }
/*      */   
/*      */   private boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
/*  677 */     if (request != null && "HEAD".equalsIgnoreCase(request.getRequestLine().getMethod())) {
/*  678 */       return false;
/*      */     }
/*  680 */     int status = response.getStatusLine().getStatusCode();
/*  681 */     return (status >= 200 && status != 204 && status != 304 && status != 205);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void completeRequest(Incoming incoming, NHttpServerConnection conn, State state) throws IOException {
/*      */     PipelineEntry pipelineEntry;
/*  691 */     state.setRequestState(MessageState.READY);
/*  692 */     state.setIncoming(null);
/*      */ 
/*      */     
/*  695 */     HttpAsyncRequestConsumer<?> consumer = incoming.getConsumer();
/*      */     try {
/*  697 */       HttpContext context = incoming.getContext();
/*  698 */       consumer.requestCompleted(context);
/*  699 */       pipelineEntry = new PipelineEntry(incoming.getRequest(), consumer.getResult(), consumer.getException(), incoming.getHandler(), context);
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */       
/*  706 */       consumer.close();
/*      */     } 
/*  708 */     Queue<PipelineEntry> pipeline = state.getPipeline();
/*  709 */     pipeline.add(pipelineEntry);
/*  710 */     if (state.getResponseState() == MessageState.READY) {
/*  711 */       conn.requestOutput();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void commitFinalResponse(NHttpServerConnection conn, State state) throws IOException, HttpException {
/*  718 */     Outgoing outgoing = state.getOutgoing();
/*  719 */     Asserts.notNull(outgoing, "Outgoing response");
/*  720 */     HttpRequest request = outgoing.getRequest();
/*  721 */     HttpResponse response = outgoing.getResponse();
/*  722 */     HttpContext context = outgoing.getContext();
/*      */     
/*  724 */     context.setAttribute("http.response", response);
/*  725 */     this.httpProcessor.process(response, context);
/*      */     
/*  727 */     HttpEntity entity = response.getEntity();
/*  728 */     if (entity != null && !canResponseHaveBody(request, response)) {
/*  729 */       response.setEntity(null);
/*  730 */       entity = null;
/*      */     } 
/*      */     
/*  733 */     conn.submitResponse(response);
/*      */     
/*  735 */     if (entity == null) {
/*  736 */       completeResponse(outgoing, conn, state);
/*      */     } else {
/*  738 */       state.setResponseState(MessageState.BODY_STREAM);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void completeResponse(Outgoing outgoing, NHttpServerConnection conn, State state) throws IOException {
/*  746 */     HttpContext context = outgoing.getContext();
/*  747 */     HttpResponse response = outgoing.getResponse();
/*  748 */     HttpAsyncResponseProducer responseProducer = outgoing.getProducer();
/*      */     try {
/*  750 */       responseProducer.responseCompleted(context);
/*  751 */       state.setOutgoing(null);
/*  752 */       state.setCancellable(null);
/*  753 */       state.setResponseState(MessageState.READY);
/*      */     } finally {
/*  755 */       responseProducer.close();
/*      */     } 
/*  757 */     if (!this.connectionStrategy.keepAlive(response, context)) {
/*  758 */       conn.close();
/*      */     } else {
/*  760 */       conn.requestInput();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private HttpAsyncRequestHandler<Object> getRequestHandler(HttpRequest request) {
/*  766 */     HttpAsyncRequestHandler<Object> handler = null;
/*  767 */     if (this.handlerMapper != null) {
/*  768 */       handler = (HttpAsyncRequestHandler)this.handlerMapper.lookup(request);
/*      */     }
/*  770 */     if (handler == null) {
/*  771 */       handler = NullRequestHandler.INSTANCE;
/*      */     }
/*  773 */     return handler;
/*      */   }
/*      */ 
/*      */   
/*      */   static class Incoming
/*      */   {
/*      */     private final HttpRequest request;
/*      */     
/*      */     private final HttpAsyncRequestHandler<Object> handler;
/*      */     
/*      */     private final HttpAsyncRequestConsumer<Object> consumer;
/*      */     
/*      */     private final HttpContext context;
/*      */     
/*      */     Incoming(HttpRequest request, HttpAsyncRequestHandler<Object> handler, HttpAsyncRequestConsumer<Object> consumer, HttpContext context) {
/*  788 */       this.request = request;
/*  789 */       this.handler = handler;
/*  790 */       this.consumer = consumer;
/*  791 */       this.context = context;
/*      */     }
/*      */     
/*      */     public HttpRequest getRequest() {
/*  795 */       return this.request;
/*      */     }
/*      */     
/*      */     public HttpAsyncRequestHandler<Object> getHandler() {
/*  799 */       return this.handler;
/*      */     }
/*      */     
/*      */     public HttpAsyncRequestConsumer<Object> getConsumer() {
/*  803 */       return this.consumer;
/*      */     }
/*      */     
/*      */     public HttpContext getContext() {
/*  807 */       return this.context;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class Outgoing
/*      */   {
/*      */     private final HttpRequest request;
/*      */     
/*      */     private final HttpResponse response;
/*      */     
/*      */     private final HttpAsyncResponseProducer producer;
/*      */     
/*      */     private final HttpContext context;
/*      */     
/*      */     Outgoing(HttpRequest request, HttpResponse response, HttpAsyncResponseProducer producer, HttpContext context) {
/*  823 */       this.request = request;
/*  824 */       this.response = response;
/*  825 */       this.producer = producer;
/*  826 */       this.context = context;
/*      */     }
/*      */     
/*      */     public HttpRequest getRequest() {
/*  830 */       return this.request;
/*      */     }
/*      */     
/*      */     public HttpResponse getResponse() {
/*  834 */       return this.response;
/*      */     }
/*      */     
/*      */     public HttpAsyncResponseProducer getProducer() {
/*  838 */       return this.producer;
/*      */     }
/*      */     
/*      */     public HttpContext getContext() {
/*  842 */       return this.context;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class PipelineEntry
/*      */   {
/*      */     private final HttpRequest request;
/*      */     
/*      */     private final Object result;
/*      */     
/*      */     private final Exception exception;
/*      */     
/*      */     private final HttpAsyncRequestHandler<Object> handler;
/*      */     
/*      */     private final HttpContext context;
/*      */     
/*      */     PipelineEntry(HttpRequest request, Object result, Exception exception, HttpAsyncRequestHandler<Object> handler, HttpContext context) {
/*  860 */       this.request = request;
/*  861 */       this.result = result;
/*  862 */       this.exception = exception;
/*  863 */       this.handler = handler;
/*  864 */       this.context = context;
/*      */     }
/*      */     
/*      */     public HttpRequest getRequest() {
/*  868 */       return this.request;
/*      */     }
/*      */     
/*      */     public Object getResult() {
/*  872 */       return this.result;
/*      */     }
/*      */     
/*      */     public Exception getException() {
/*  876 */       return this.exception;
/*      */     }
/*      */     
/*      */     public HttpAsyncRequestHandler<Object> getHandler() {
/*  880 */       return this.handler;
/*      */     }
/*      */     
/*      */     public HttpContext getContext() {
/*  884 */       return this.context;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class State
/*      */   {
/*  901 */     private final Queue<HttpAsyncService.PipelineEntry> pipeline = new ConcurrentLinkedQueue<HttpAsyncService.PipelineEntry>(); private volatile boolean terminated;
/*  902 */     private volatile MessageState requestState = MessageState.READY;
/*  903 */     private volatile MessageState responseState = MessageState.READY;
/*      */     private volatile HttpAsyncService.Incoming incoming;
/*      */     
/*      */     public boolean isTerminated() {
/*  907 */       return this.terminated;
/*      */     }
/*      */     private volatile HttpAsyncService.Outgoing outgoing; private volatile Cancellable cancellable;
/*      */     public void setTerminated() {
/*  911 */       this.terminated = true;
/*      */     }
/*      */     
/*      */     public MessageState getRequestState() {
/*  915 */       return this.requestState;
/*      */     }
/*      */     
/*      */     public void setRequestState(MessageState state) {
/*  919 */       this.requestState = state;
/*      */     }
/*      */     
/*      */     public MessageState getResponseState() {
/*  923 */       return this.responseState;
/*      */     }
/*      */     
/*      */     public void setResponseState(MessageState state) {
/*  927 */       this.responseState = state;
/*      */     }
/*      */     
/*      */     public HttpAsyncService.Incoming getIncoming() {
/*  931 */       return this.incoming;
/*      */     }
/*      */     
/*      */     public void setIncoming(HttpAsyncService.Incoming incoming) {
/*  935 */       this.incoming = incoming;
/*      */     }
/*      */     
/*      */     public HttpAsyncService.Outgoing getOutgoing() {
/*  939 */       return this.outgoing;
/*      */     }
/*      */     
/*      */     public void setOutgoing(HttpAsyncService.Outgoing outgoing) {
/*  943 */       this.outgoing = outgoing;
/*      */     }
/*      */     
/*      */     public Cancellable getCancellable() {
/*  947 */       return this.cancellable;
/*      */     }
/*      */     
/*      */     public void setCancellable(Cancellable cancellable) {
/*  951 */       this.cancellable = cancellable;
/*      */     }
/*      */     
/*      */     public Queue<HttpAsyncService.PipelineEntry> getPipeline() {
/*  955 */       return this.pipeline;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  960 */       StringBuilder buf = new StringBuilder();
/*  961 */       buf.append("[incoming ");
/*  962 */       buf.append(this.requestState);
/*  963 */       if (this.incoming != null) {
/*  964 */         buf.append(" ");
/*  965 */         buf.append(this.incoming.getRequest().getRequestLine());
/*      */       } 
/*  967 */       buf.append("; outgoing ");
/*  968 */       buf.append(this.responseState);
/*  969 */       if (this.outgoing != null) {
/*  970 */         buf.append(" ");
/*  971 */         buf.append(this.outgoing.getResponse().getStatusLine());
/*      */       } 
/*  973 */       buf.append("]");
/*  974 */       return buf.toString();
/*      */     }
/*      */   }
/*      */   
/*      */   class HttpAsyncExchangeImpl
/*      */     implements HttpAsyncExchange
/*      */   {
/*  981 */     private final AtomicBoolean completed = new AtomicBoolean();
/*      */     
/*      */     private final HttpRequest request;
/*      */     
/*      */     private final HttpResponse response;
/*      */     
/*      */     private final HttpAsyncService.State state;
/*      */     
/*      */     private final NHttpServerConnection conn;
/*      */     
/*      */     private final HttpContext context;
/*      */ 
/*      */     
/*      */     public HttpAsyncExchangeImpl(HttpRequest request, HttpResponse response, HttpAsyncService.State state, NHttpServerConnection conn, HttpContext context) {
/*  995 */       this.request = request;
/*  996 */       this.response = response;
/*  997 */       this.state = state;
/*  998 */       this.conn = conn;
/*  999 */       this.context = context;
/*      */     }
/*      */ 
/*      */     
/*      */     public HttpRequest getRequest() {
/* 1004 */       return this.request;
/*      */     }
/*      */ 
/*      */     
/*      */     public HttpResponse getResponse() {
/* 1009 */       return this.response;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setCallback(Cancellable cancellable) {
/* 1014 */       if (this.completed.get()) {
/* 1015 */         HttpAsyncService.this.handleAlreadySubmittedResponse(cancellable, this.context);
/* 1016 */       } else if (this.state.isTerminated() && cancellable != null) {
/* 1017 */         cancellable.cancel();
/*      */       } else {
/* 1019 */         this.state.setCancellable(cancellable);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void submitResponse(HttpAsyncResponseProducer responseProducer) {
/* 1025 */       Args.notNull(responseProducer, "Response producer");
/* 1026 */       if (this.completed.getAndSet(true)) {
/* 1027 */         HttpAsyncService.this.handleAlreadySubmittedResponse(responseProducer, this.context);
/* 1028 */       } else if (!this.state.isTerminated()) {
/* 1029 */         HttpResponse response = responseProducer.generateResponse();
/* 1030 */         HttpAsyncService.Outgoing outgoing = new HttpAsyncService.Outgoing(this.request, response, responseProducer, this.context);
/*      */ 
/*      */         
/* 1033 */         synchronized (this.state) {
/* 1034 */           this.state.setOutgoing(outgoing);
/* 1035 */           this.state.setCancellable(null);
/* 1036 */           this.conn.requestOutput();
/*      */         } 
/*      */       } else {
/*      */         
/*      */         try {
/* 1041 */           responseProducer.close();
/* 1042 */         } catch (IOException ex) {
/* 1043 */           HttpAsyncService.this.log(ex);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void submitResponse() {
/* 1050 */       submitResponse(new BasicAsyncResponseProducer(this.response));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isCompleted() {
/* 1055 */       return this.completed.get();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setTimeout(int timeout) {
/* 1060 */       this.conn.setSocketTimeout(timeout);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getTimeout() {
/* 1065 */       return this.conn.getSocketTimeout();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   private static class HttpAsyncRequestHandlerResolverAdapter
/*      */     implements HttpAsyncRequestHandlerMapper
/*      */   {
/*      */     private final HttpAsyncRequestHandlerResolver resolver;
/*      */ 
/*      */ 
/*      */     
/*      */     public HttpAsyncRequestHandlerResolverAdapter(HttpAsyncRequestHandlerResolver resolver) {
/* 1081 */       this.resolver = resolver;
/*      */     }
/*      */ 
/*      */     
/*      */     public HttpAsyncRequestHandler<?> lookup(HttpRequest request) {
/* 1086 */       return this.resolver.lookup(request.getRequestLine().getUri());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpResponseFactory getResponseFactory() {
/* 1098 */     return this.responseFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpProcessor getHttpProcessor() {
/* 1108 */     return this.httpProcessor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConnectionReuseStrategy getConnectionStrategy() {
/* 1118 */     return this.connectionStrategy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpAsyncRequestHandlerMapper getHandlerMapper() {
/* 1128 */     return this.handlerMapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HttpAsyncExpectationVerifier getExpectationVerifier() {
/* 1138 */     return this.expectationVerifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExceptionLogger getExceptionLogger() {
/* 1148 */     return this.exceptionLogger;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */