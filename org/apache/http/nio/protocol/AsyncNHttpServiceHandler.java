/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpEntityEnclosingRequest;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpResponseFactory;
/*     */ import org.apache.http.HttpVersion;
/*     */ import org.apache.http.ProtocolVersion;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.NHttpConnection;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
/*     */ import org.apache.http.nio.entity.ConsumingNHttpEntity;
/*     */ import org.apache.http.nio.entity.NByteArrayEntity;
/*     */ import org.apache.http.nio.entity.NHttpEntityWrapper;
/*     */ import org.apache.http.nio.entity.ProducingNHttpEntity;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
/*     */ import org.apache.http.params.DefaultedHttpParams;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.protocol.HttpExpectationVerifier;
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ import org.apache.http.util.EncodingUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class AsyncNHttpServiceHandler
/*     */   extends NHttpHandlerBase
/*     */   implements NHttpServiceHandler
/*     */ {
/*     */   protected final HttpResponseFactory responseFactory;
/*     */   protected NHttpRequestHandlerResolver handlerResolver;
/*     */   protected HttpExpectationVerifier expectationVerifier;
/*     */   
/*     */   public AsyncNHttpServiceHandler(HttpProcessor httpProcessor, HttpResponseFactory responseFactory, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, HttpParams params) {
/* 119 */     super(httpProcessor, connStrategy, allocator, params);
/* 120 */     Args.notNull(responseFactory, "Response factory");
/* 121 */     this.responseFactory = responseFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AsyncNHttpServiceHandler(HttpProcessor httpProcessor, HttpResponseFactory responseFactory, ConnectionReuseStrategy connStrategy, HttpParams params) {
/* 129 */     this(httpProcessor, responseFactory, connStrategy, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */   
/*     */   public void setExpectationVerifier(HttpExpectationVerifier expectationVerifier) {
/* 133 */     this.expectationVerifier = expectationVerifier;
/*     */   }
/*     */   
/*     */   public void setHandlerResolver(NHttpRequestHandlerResolver handlerResolver) {
/* 137 */     this.handlerResolver = handlerResolver;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpServerConnection conn) {
/* 142 */     HttpContext context = conn.getContext();
/*     */     
/* 144 */     ServerConnState connState = new ServerConnState();
/* 145 */     context.setAttribute("http.nio.conn-state", connState);
/* 146 */     context.setAttribute("http.connection", conn);
/*     */     
/* 148 */     if (this.eventListener != null) {
/* 149 */       this.eventListener.connectionOpen((NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */   
/*     */   public void requestReceived(NHttpServerConnection conn) {
/*     */     HttpVersion httpVersion;
/* 155 */     HttpContext context = conn.getContext();
/*     */     
/* 157 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 159 */     HttpRequest request = conn.getHttpRequest();
/* 160 */     request.setParams((HttpParams)new DefaultedHttpParams(request.getParams(), this.params));
/*     */     
/* 162 */     connState.setRequest(request);
/*     */     
/* 164 */     NHttpRequestHandler requestHandler = getRequestHandler(request);
/* 165 */     connState.setRequestHandler(requestHandler);
/*     */     
/* 167 */     ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
/* 168 */     if (!ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_1))
/*     */     {
/* 170 */       httpVersion = HttpVersion.HTTP_1_1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 177 */       if (request instanceof HttpEntityEnclosingRequest) {
/* 178 */         HttpEntityEnclosingRequest entityRequest = (HttpEntityEnclosingRequest)request;
/* 179 */         if (entityRequest.expectContinue()) {
/* 180 */           HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)httpVersion, 100, context);
/*     */           
/* 182 */           response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */ 
/*     */           
/* 185 */           if (this.expectationVerifier != null) {
/*     */             try {
/* 187 */               this.expectationVerifier.verify(request, response, context);
/* 188 */             } catch (HttpException ex) {
/* 189 */               response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
/*     */ 
/*     */ 
/*     */               
/* 193 */               response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */               
/* 195 */               handleException(ex, response);
/*     */             } 
/*     */           }
/*     */           
/* 199 */           if (response.getStatusLine().getStatusCode() < 200) {
/*     */ 
/*     */             
/* 202 */             conn.submitResponse(response);
/*     */           } else {
/* 204 */             conn.resetInput();
/* 205 */             sendResponse(conn, request, response);
/*     */           } 
/*     */         } 
/*     */         
/* 209 */         ConsumingNHttpEntity consumingEntity = null;
/*     */ 
/*     */         
/* 212 */         if (requestHandler != null) {
/* 213 */           consumingEntity = requestHandler.entityRequest(entityRequest, context);
/*     */         }
/* 215 */         if (consumingEntity == null) {
/* 216 */           consumingEntity = new NullNHttpEntity(entityRequest.getEntity());
/*     */         }
/* 218 */         entityRequest.setEntity((HttpEntity)consumingEntity);
/* 219 */         connState.setConsumingEntity(consumingEntity);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 224 */         conn.suspendInput();
/* 225 */         processRequest(conn, request);
/*     */       }
/*     */     
/* 228 */     } catch (IOException ex) {
/* 229 */       shutdownConnection((NHttpConnection)conn, ex);
/* 230 */       if (this.eventListener != null) {
/* 231 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 233 */     } catch (HttpException ex) {
/* 234 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 235 */       if (this.eventListener != null) {
/* 236 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closed(NHttpServerConnection conn) {
/* 244 */     HttpContext context = conn.getContext();
/*     */     
/* 246 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     try {
/* 248 */       connState.reset();
/* 249 */     } catch (IOException ex) {
/* 250 */       if (this.eventListener != null) {
/* 251 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/* 254 */     if (this.eventListener != null) {
/* 255 */       this.eventListener.connectionClosed((NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, HttpException httpex) {
/* 261 */     if (conn.isResponseSubmitted()) {
/*     */ 
/*     */       
/* 264 */       closeConnection((NHttpConnection)conn, (Throwable)httpex);
/* 265 */       if (this.eventListener != null) {
/* 266 */         this.eventListener.fatalProtocolException(httpex, (NHttpConnection)conn);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 271 */     HttpContext context = conn.getContext();
/*     */     try {
/* 273 */       HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
/*     */       
/* 275 */       response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */       
/* 277 */       handleException(httpex, response);
/* 278 */       response.setEntity(null);
/* 279 */       sendResponse(conn, (HttpRequest)null, response);
/*     */     }
/* 281 */     catch (IOException ex) {
/* 282 */       shutdownConnection((NHttpConnection)conn, ex);
/* 283 */       if (this.eventListener != null) {
/* 284 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 286 */     } catch (HttpException ex) {
/* 287 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 288 */       if (this.eventListener != null) {
/* 289 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, IOException ex) {
/* 296 */     shutdownConnection((NHttpConnection)conn, ex);
/*     */     
/* 298 */     if (this.eventListener != null) {
/* 299 */       this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpServerConnection conn) {
/* 305 */     handleTimeout((NHttpConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpServerConnection conn, ContentDecoder decoder) {
/* 310 */     HttpContext context = conn.getContext();
/* 311 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 313 */     HttpRequest request = connState.getRequest();
/* 314 */     ConsumingNHttpEntity consumingEntity = connState.getConsumingEntity();
/*     */ 
/*     */     
/*     */     try {
/* 318 */       consumingEntity.consumeContent(decoder, (IOControl)conn);
/* 319 */       if (decoder.isCompleted()) {
/* 320 */         conn.suspendInput();
/* 321 */         processRequest(conn, request);
/*     */       }
/*     */     
/* 324 */     } catch (IOException ex) {
/* 325 */       shutdownConnection((NHttpConnection)conn, ex);
/* 326 */       if (this.eventListener != null) {
/* 327 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 329 */     } catch (HttpException ex) {
/* 330 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 331 */       if (this.eventListener != null) {
/* 332 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReady(NHttpServerConnection conn) {
/* 339 */     HttpContext context = conn.getContext();
/* 340 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 342 */     if (connState.isHandled()) {
/*     */       return;
/*     */     }
/*     */     
/* 346 */     HttpRequest request = connState.getRequest();
/*     */ 
/*     */     
/*     */     try {
/* 350 */       IOException ioex = connState.getIOException();
/* 351 */       if (ioex != null) {
/* 352 */         throw ioex;
/*     */       }
/*     */       
/* 355 */       HttpException httpex = connState.getHttpException();
/* 356 */       if (httpex != null) {
/* 357 */         HttpResponse httpResponse = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
/*     */         
/* 359 */         httpResponse.setParams((HttpParams)new DefaultedHttpParams(httpResponse.getParams(), this.params));
/*     */         
/* 361 */         handleException(httpex, httpResponse);
/* 362 */         connState.setResponse(httpResponse);
/*     */       } 
/*     */       
/* 365 */       HttpResponse response = connState.getResponse();
/* 366 */       if (response != null) {
/* 367 */         connState.setHandled(true);
/* 368 */         sendResponse(conn, request, response);
/*     */       }
/*     */     
/* 371 */     } catch (IOException ex) {
/* 372 */       shutdownConnection((NHttpConnection)conn, ex);
/* 373 */       if (this.eventListener != null) {
/* 374 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 376 */     } catch (HttpException ex) {
/* 377 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 378 */       if (this.eventListener != null) {
/* 379 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpServerConnection conn, ContentEncoder encoder) {
/* 386 */     HttpContext context = conn.getContext();
/* 387 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 389 */     HttpResponse response = conn.getHttpResponse();
/*     */     
/*     */     try {
/* 392 */       ProducingNHttpEntity entity = connState.getProducingEntity();
/* 393 */       entity.produceContent(encoder, (IOControl)conn);
/*     */       
/* 395 */       if (encoder.isCompleted()) {
/* 396 */         connState.finishOutput();
/* 397 */         if (!this.connStrategy.keepAlive(response, context)) {
/* 398 */           conn.close();
/*     */         } else {
/*     */           
/* 401 */           connState.reset();
/* 402 */           conn.requestInput();
/*     */         } 
/* 404 */         responseComplete(response, context);
/*     */       }
/*     */     
/* 407 */     } catch (IOException ex) {
/* 408 */       shutdownConnection((NHttpConnection)conn, ex);
/* 409 */       if (this.eventListener != null) {
/* 410 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void handleException(HttpException ex, HttpResponse response) {
/* 416 */     int code = 500;
/* 417 */     if (ex instanceof org.apache.http.MethodNotSupportedException) {
/* 418 */       code = 501;
/* 419 */     } else if (ex instanceof org.apache.http.UnsupportedHttpVersionException) {
/* 420 */       code = 505;
/* 421 */     } else if (ex instanceof org.apache.http.ProtocolException) {
/* 422 */       code = 400;
/*     */     } 
/* 424 */     response.setStatusCode(code);
/*     */     
/* 426 */     byte[] msg = EncodingUtils.getAsciiBytes(ex.getMessage());
/* 427 */     NByteArrayEntity entity = new NByteArrayEntity(msg);
/* 428 */     entity.setContentType("text/plain; charset=US-ASCII");
/* 429 */     response.setEntity((HttpEntity)entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processRequest(NHttpServerConnection conn, HttpRequest request) throws IOException, HttpException {
/*     */     HttpVersion httpVersion;
/* 439 */     HttpContext context = conn.getContext();
/* 440 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 442 */     ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
/*     */     
/* 444 */     if (!ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_1))
/*     */     {
/* 446 */       httpVersion = HttpVersion.HTTP_1_1;
/*     */     }
/*     */     
/* 449 */     NHttpResponseTrigger trigger = new ResponseTriggerImpl(connState, (IOControl)conn);
/*     */     try {
/* 451 */       this.httpProcessor.process(request, context);
/*     */       
/* 453 */       NHttpRequestHandler handler = connState.getRequestHandler();
/* 454 */       if (handler != null) {
/* 455 */         HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)httpVersion, 200, context);
/*     */         
/* 457 */         response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */ 
/*     */         
/* 460 */         handler.handle(request, response, trigger, context);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 466 */         HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)httpVersion, 501, context);
/*     */         
/* 468 */         response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */         
/* 470 */         trigger.submitResponse(response);
/*     */       }
/*     */     
/* 473 */     } catch (HttpException ex) {
/* 474 */       trigger.handleException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendResponse(NHttpServerConnection conn, HttpRequest request, HttpResponse response) throws IOException, HttpException {
/* 482 */     HttpContext context = conn.getContext();
/* 483 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/* 486 */     connState.finishInput();
/*     */ 
/*     */     
/* 489 */     context.setAttribute("http.request", request);
/* 490 */     this.httpProcessor.process(response, context);
/* 491 */     context.setAttribute("http.request", null);
/*     */     
/* 493 */     if (response.getEntity() != null && !canResponseHaveBody(request, response)) {
/* 494 */       response.setEntity(null);
/*     */     }
/*     */     
/* 497 */     HttpEntity entity = response.getEntity();
/* 498 */     if (entity != null) {
/* 499 */       if (entity instanceof ProducingNHttpEntity) {
/* 500 */         connState.setProducingEntity((ProducingNHttpEntity)entity);
/*     */       } else {
/* 502 */         connState.setProducingEntity((ProducingNHttpEntity)new NHttpEntityWrapper(entity));
/*     */       } 
/*     */     }
/*     */     
/* 506 */     conn.submitResponse(response);
/*     */     
/* 508 */     if (entity == null) {
/* 509 */       if (!this.connStrategy.keepAlive(response, context)) {
/* 510 */         conn.close();
/*     */       } else {
/*     */         
/* 513 */         connState.reset();
/* 514 */         conn.requestInput();
/*     */       } 
/* 516 */       responseComplete(response, context);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void responseComplete(HttpResponse response, HttpContext context) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private NHttpRequestHandler getRequestHandler(HttpRequest request) {
/* 530 */     NHttpRequestHandler handler = null;
/* 531 */     if (this.handlerResolver != null) {
/* 532 */       String requestURI = request.getRequestLine().getUri();
/* 533 */       handler = this.handlerResolver.lookup(requestURI);
/*     */     } 
/*     */     
/* 536 */     return handler;
/*     */   }
/*     */   
/*     */   protected static class ServerConnState
/*     */   {
/*     */     private volatile NHttpRequestHandler requestHandler;
/*     */     private volatile HttpRequest request;
/*     */     private volatile ConsumingNHttpEntity consumingEntity;
/*     */     private volatile HttpResponse response;
/*     */     private volatile ProducingNHttpEntity producingEntity;
/*     */     private volatile IOException ioex;
/*     */     private volatile HttpException httpex;
/*     */     private volatile boolean handled;
/*     */     
/*     */     public void finishInput() throws IOException {
/* 551 */       if (this.consumingEntity != null) {
/* 552 */         this.consumingEntity.finish();
/* 553 */         this.consumingEntity = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void finishOutput() throws IOException {
/* 558 */       if (this.producingEntity != null) {
/* 559 */         this.producingEntity.finish();
/* 560 */         this.producingEntity = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void reset() throws IOException {
/* 565 */       finishInput();
/* 566 */       this.request = null;
/* 567 */       finishOutput();
/* 568 */       this.handled = false;
/* 569 */       this.response = null;
/* 570 */       this.ioex = null;
/* 571 */       this.httpex = null;
/* 572 */       this.requestHandler = null;
/*     */     }
/*     */     
/*     */     public NHttpRequestHandler getRequestHandler() {
/* 576 */       return this.requestHandler;
/*     */     }
/*     */     
/*     */     public void setRequestHandler(NHttpRequestHandler requestHandler) {
/* 580 */       this.requestHandler = requestHandler;
/*     */     }
/*     */     
/*     */     public HttpRequest getRequest() {
/* 584 */       return this.request;
/*     */     }
/*     */     
/*     */     public void setRequest(HttpRequest request) {
/* 588 */       this.request = request;
/*     */     }
/*     */     
/*     */     public ConsumingNHttpEntity getConsumingEntity() {
/* 592 */       return this.consumingEntity;
/*     */     }
/*     */     
/*     */     public void setConsumingEntity(ConsumingNHttpEntity consumingEntity) {
/* 596 */       this.consumingEntity = consumingEntity;
/*     */     }
/*     */     
/*     */     public HttpResponse getResponse() {
/* 600 */       return this.response;
/*     */     }
/*     */     
/*     */     public void setResponse(HttpResponse response) {
/* 604 */       this.response = response;
/*     */     }
/*     */     
/*     */     public ProducingNHttpEntity getProducingEntity() {
/* 608 */       return this.producingEntity;
/*     */     }
/*     */     
/*     */     public void setProducingEntity(ProducingNHttpEntity producingEntity) {
/* 612 */       this.producingEntity = producingEntity;
/*     */     }
/*     */     
/*     */     public IOException getIOException() {
/* 616 */       return this.ioex;
/*     */     }
/*     */     
/*     */     public IOException getIOExepction() {
/* 620 */       return this.ioex;
/*     */     }
/*     */     
/*     */     public void setIOException(IOException ex) {
/* 624 */       this.ioex = ex;
/*     */     }
/*     */     
/*     */     public void setIOExepction(IOException ex) {
/* 628 */       this.ioex = ex;
/*     */     }
/*     */     
/*     */     public HttpException getHttpException() {
/* 632 */       return this.httpex;
/*     */     }
/*     */     
/*     */     public HttpException getHttpExepction() {
/* 636 */       return this.httpex;
/*     */     }
/*     */     
/*     */     public void setHttpException(HttpException ex) {
/* 640 */       this.httpex = ex;
/*     */     }
/*     */     
/*     */     public void setHttpExepction(HttpException ex) {
/* 644 */       this.httpex = ex;
/*     */     }
/*     */     
/*     */     public boolean isHandled() {
/* 648 */       return this.handled;
/*     */     }
/*     */     
/*     */     public void setHandled(boolean handled) {
/* 652 */       this.handled = handled;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ResponseTriggerImpl
/*     */     implements NHttpResponseTrigger
/*     */   {
/*     */     private final AsyncNHttpServiceHandler.ServerConnState connState;
/*     */     
/*     */     private final IOControl iocontrol;
/*     */     private volatile boolean triggered;
/*     */     
/*     */     public ResponseTriggerImpl(AsyncNHttpServiceHandler.ServerConnState connState, IOControl iocontrol) {
/* 666 */       this.connState = connState;
/* 667 */       this.iocontrol = iocontrol;
/*     */     }
/*     */ 
/*     */     
/*     */     public void submitResponse(HttpResponse response) {
/* 672 */       Args.notNull(response, "Response");
/* 673 */       Asserts.check(!this.triggered, "Response already triggered");
/* 674 */       this.triggered = true;
/* 675 */       this.connState.setResponse(response);
/* 676 */       this.iocontrol.requestOutput();
/*     */     }
/*     */ 
/*     */     
/*     */     public void handleException(HttpException ex) {
/* 681 */       Asserts.check(!this.triggered, "Response already triggered");
/* 682 */       this.triggered = true;
/* 683 */       this.connState.setHttpException(ex);
/* 684 */       this.iocontrol.requestOutput();
/*     */     }
/*     */ 
/*     */     
/*     */     public void handleException(IOException ex) {
/* 689 */       Asserts.check(!this.triggered, "Response already triggered");
/* 690 */       this.triggered = true;
/* 691 */       this.connState.setIOException(ex);
/* 692 */       this.iocontrol.requestOutput();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/AsyncNHttpServiceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */