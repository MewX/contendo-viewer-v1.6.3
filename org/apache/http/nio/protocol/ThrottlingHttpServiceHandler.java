/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.concurrent.Executor;
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
/*     */ import org.apache.http.entity.ByteArrayEntity;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.NHttpConnection;
/*     */ import org.apache.http.nio.NHttpServerConnection;
/*     */ import org.apache.http.nio.NHttpServiceHandler;
/*     */ import org.apache.http.nio.entity.ContentBufferEntity;
/*     */ import org.apache.http.nio.entity.ContentOutputStream;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.ContentInputBuffer;
/*     */ import org.apache.http.nio.util.ContentOutputBuffer;
/*     */ import org.apache.http.nio.util.DirectByteBufferAllocator;
/*     */ import org.apache.http.nio.util.SharedInputBuffer;
/*     */ import org.apache.http.nio.util.SharedOutputBuffer;
/*     */ import org.apache.http.params.DefaultedHttpParams;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.protocol.HttpExpectationVerifier;
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ import org.apache.http.protocol.HttpRequestHandler;
/*     */ import org.apache.http.protocol.HttpRequestHandlerResolver;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.EncodingUtils;
/*     */ import org.apache.http.util.EntityUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class ThrottlingHttpServiceHandler
/*     */   extends NHttpHandlerBase
/*     */   implements NHttpServiceHandler
/*     */ {
/*     */   protected final HttpResponseFactory responseFactory;
/*     */   protected final Executor executor;
/*     */   protected HttpRequestHandlerResolver handlerResolver;
/*     */   protected HttpExpectationVerifier expectationVerifier;
/*     */   private final int bufsize;
/*     */   
/*     */   public ThrottlingHttpServiceHandler(HttpProcessor httpProcessor, HttpResponseFactory responseFactory, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, Executor executor, HttpParams params) {
/* 132 */     super(httpProcessor, connStrategy, allocator, params);
/* 133 */     Args.notNull(responseFactory, "Response factory");
/* 134 */     Args.notNull(executor, "Executor");
/* 135 */     this.responseFactory = responseFactory;
/* 136 */     this.executor = executor;
/* 137 */     this.bufsize = this.params.getIntParameter("http.nio.content-buffer-size", 20480);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ThrottlingHttpServiceHandler(HttpProcessor httpProcessor, HttpResponseFactory responseFactory, ConnectionReuseStrategy connStrategy, Executor executor, HttpParams params) {
/* 146 */     this(httpProcessor, responseFactory, connStrategy, (ByteBufferAllocator)DirectByteBufferAllocator.INSTANCE, executor, params);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHandlerResolver(HttpRequestHandlerResolver handlerResolver) {
/* 151 */     this.handlerResolver = handlerResolver;
/*     */   }
/*     */   
/*     */   public void setExpectationVerifier(HttpExpectationVerifier expectationVerifier) {
/* 155 */     this.expectationVerifier = expectationVerifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpServerConnection conn) {
/* 160 */     HttpContext context = conn.getContext();
/*     */     
/* 162 */     ServerConnState connState = new ServerConnState(this.bufsize, (IOControl)conn, this.allocator);
/* 163 */     context.setAttribute("http.nio.conn-state", connState);
/*     */     
/* 165 */     if (this.eventListener != null) {
/* 166 */       this.eventListener.connectionOpen((NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpServerConnection conn) {
/* 172 */     HttpContext context = conn.getContext();
/* 173 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 175 */     if (connState != null) {
/* 176 */       synchronized (connState) {
/* 177 */         connState.close();
/* 178 */         connState.notifyAll();
/*     */       } 
/*     */     }
/*     */     
/* 182 */     if (this.eventListener != null) {
/* 183 */       this.eventListener.connectionClosed((NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, HttpException httpex) {
/* 189 */     if (conn.isResponseSubmitted()) {
/* 190 */       if (this.eventListener != null) {
/* 191 */         this.eventListener.fatalProtocolException(httpex, (NHttpConnection)conn);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 196 */     HttpContext context = conn.getContext();
/*     */     
/* 198 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 202 */       HttpResponse response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
/*     */ 
/*     */ 
/*     */       
/* 206 */       response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */       
/* 208 */       handleException(httpex, response);
/* 209 */       response.setEntity(null);
/*     */       
/* 211 */       this.httpProcessor.process(response, context);
/*     */       
/* 213 */       synchronized (connState) {
/* 214 */         connState.setResponse(response);
/*     */         
/* 216 */         conn.requestOutput();
/*     */       }
/*     */     
/* 219 */     } catch (IOException ex) {
/* 220 */       shutdownConnection((NHttpConnection)conn, ex);
/* 221 */       if (this.eventListener != null) {
/* 222 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 224 */     } catch (HttpException ex) {
/* 225 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 226 */       if (this.eventListener != null) {
/* 227 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpServerConnection conn, IOException ex) {
/* 234 */     shutdownConnection((NHttpConnection)conn, ex);
/*     */     
/* 236 */     if (this.eventListener != null) {
/* 237 */       this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpServerConnection conn) {
/* 243 */     handleTimeout((NHttpConnection)conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestReceived(final NHttpServerConnection conn) {
/* 248 */     HttpContext context = conn.getContext();
/*     */     
/* 250 */     final HttpRequest request = conn.getHttpRequest();
/* 251 */     final ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 253 */     synchronized (connState) {
/* 254 */       boolean contentExpected = false;
/* 255 */       if (request instanceof HttpEntityEnclosingRequest) {
/* 256 */         HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
/* 257 */         if (entity != null) {
/* 258 */           contentExpected = true;
/*     */         }
/*     */       } 
/*     */       
/* 262 */       if (!contentExpected) {
/* 263 */         conn.suspendInput();
/*     */       }
/*     */       
/* 266 */       this.executor.execute(new Runnable()
/*     */           {
/*     */             
/*     */             public void run()
/*     */             {
/*     */               try {
/* 272 */                 ThrottlingHttpServiceHandler.this.handleRequest(request, connState, conn);
/*     */               }
/* 274 */               catch (IOException ex) {
/* 275 */                 ThrottlingHttpServiceHandler.this.shutdownConnection((NHttpConnection)conn, ex);
/* 276 */                 if (ThrottlingHttpServiceHandler.this.eventListener != null) {
/* 277 */                   ThrottlingHttpServiceHandler.this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */                 }
/* 279 */               } catch (HttpException ex) {
/* 280 */                 ThrottlingHttpServiceHandler.this.shutdownConnection((NHttpConnection)conn, (Throwable)ex);
/* 281 */                 if (ThrottlingHttpServiceHandler.this.eventListener != null) {
/* 282 */                   ThrottlingHttpServiceHandler.this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 289 */       connState.notifyAll();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpServerConnection conn, ContentDecoder decoder) {
/* 296 */     HttpContext context = conn.getContext();
/*     */     
/* 298 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 302 */       synchronized (connState) {
/* 303 */         ContentInputBuffer buffer = connState.getInbuffer();
/*     */         
/* 305 */         buffer.consumeContent(decoder);
/* 306 */         if (decoder.isCompleted()) {
/* 307 */           connState.setInputState(4);
/*     */         } else {
/* 309 */           connState.setInputState(2);
/*     */         } 
/*     */         
/* 312 */         connState.notifyAll();
/*     */       }
/*     */     
/* 315 */     } catch (IOException ex) {
/* 316 */       shutdownConnection((NHttpConnection)conn, ex);
/* 317 */       if (this.eventListener != null) {
/* 318 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void responseReady(NHttpServerConnection conn) {
/* 326 */     HttpContext context = conn.getContext();
/*     */     
/* 328 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 332 */       synchronized (connState) {
/* 333 */         if (connState.isExpectationFailed()) {
/*     */ 
/*     */ 
/*     */           
/* 337 */           conn.resetInput();
/* 338 */           connState.setExpectationFailed(false);
/*     */         } 
/*     */         
/* 341 */         HttpResponse response = connState.getResponse();
/* 342 */         if (connState.getOutputState() == 0 && response != null && !conn.isResponseSubmitted()) {
/*     */ 
/*     */ 
/*     */           
/* 346 */           conn.submitResponse(response);
/* 347 */           int statusCode = response.getStatusLine().getStatusCode();
/* 348 */           HttpEntity entity = response.getEntity();
/*     */           
/* 350 */           if (statusCode >= 200 && entity == null) {
/* 351 */             connState.setOutputState(32);
/*     */             
/* 353 */             if (!this.connStrategy.keepAlive(response, context)) {
/* 354 */               conn.close();
/*     */             }
/*     */           } else {
/* 357 */             connState.setOutputState(8);
/*     */           } 
/*     */         } 
/*     */         
/* 361 */         connState.notifyAll();
/*     */       }
/*     */     
/* 364 */     } catch (IOException ex) {
/* 365 */       shutdownConnection((NHttpConnection)conn, ex);
/* 366 */       if (this.eventListener != null) {
/* 367 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 369 */     } catch (HttpException ex) {
/* 370 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 371 */       if (this.eventListener != null) {
/* 372 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpServerConnection conn, ContentEncoder encoder) {
/* 379 */     HttpContext context = conn.getContext();
/*     */     
/* 381 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 385 */       synchronized (connState) {
/* 386 */         HttpResponse response = connState.getResponse();
/* 387 */         ContentOutputBuffer buffer = connState.getOutbuffer();
/*     */         
/* 389 */         buffer.produceContent(encoder);
/* 390 */         if (encoder.isCompleted()) {
/* 391 */           connState.setOutputState(32);
/*     */           
/* 393 */           if (!this.connStrategy.keepAlive(response, context)) {
/* 394 */             conn.close();
/*     */           }
/*     */         } else {
/* 397 */           connState.setOutputState(16);
/*     */         } 
/*     */         
/* 400 */         connState.notifyAll();
/*     */       }
/*     */     
/* 403 */     } catch (IOException ex) {
/* 404 */       shutdownConnection((NHttpConnection)conn, ex);
/* 405 */       if (this.eventListener != null) {
/* 406 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void handleException(HttpException ex, HttpResponse response) {
/* 412 */     if (ex instanceof org.apache.http.MethodNotSupportedException) {
/* 413 */       response.setStatusCode(501);
/* 414 */     } else if (ex instanceof org.apache.http.UnsupportedHttpVersionException) {
/* 415 */       response.setStatusCode(505);
/* 416 */     } else if (ex instanceof org.apache.http.ProtocolException) {
/* 417 */       response.setStatusCode(400);
/*     */     } else {
/* 419 */       response.setStatusCode(500);
/*     */     } 
/* 421 */     byte[] msg = EncodingUtils.getAsciiBytes(ex.getMessage());
/* 422 */     ByteArrayEntity entity = new ByteArrayEntity(msg);
/* 423 */     entity.setContentType("text/plain; charset=US-ASCII");
/* 424 */     response.setEntity((HttpEntity)entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleRequest(HttpRequest request, ServerConnState connState, NHttpServerConnection conn) throws HttpException, IOException {
/*     */     HttpVersion httpVersion;
/* 432 */     HttpContext context = conn.getContext();
/*     */ 
/*     */ 
/*     */     
/* 436 */     synchronized (connState) {
/*     */       while (true) {
/*     */         try {
/* 439 */           int currentState = connState.getOutputState();
/* 440 */           if (currentState == 0) {
/*     */             break;
/*     */           }
/* 443 */           if (currentState == -1) {
/*     */             return;
/*     */           }
/* 446 */           connState.wait();
/*     */         }
/* 448 */         catch (InterruptedException ex) {
/* 449 */           connState.shutdown(); return;
/*     */         } 
/*     */       } 
/* 452 */       connState.setInputState(1);
/* 453 */       connState.setRequest(request);
/*     */     } 
/*     */     
/* 456 */     request.setParams((HttpParams)new DefaultedHttpParams(request.getParams(), this.params));
/*     */     
/* 458 */     context.setAttribute("http.connection", conn);
/* 459 */     context.setAttribute("http.request", request);
/*     */     
/* 461 */     ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
/*     */     
/* 463 */     if (!ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_1))
/*     */     {
/* 465 */       httpVersion = HttpVersion.HTTP_1_1;
/*     */     }
/*     */     
/* 468 */     HttpResponse response = null;
/*     */     
/* 470 */     if (request instanceof HttpEntityEnclosingRequest) {
/* 471 */       HttpEntityEnclosingRequest eeRequest = (HttpEntityEnclosingRequest)request;
/*     */       
/* 473 */       if (eeRequest.expectContinue()) {
/* 474 */         response = this.responseFactory.newHttpResponse((ProtocolVersion)httpVersion, 100, context);
/*     */ 
/*     */ 
/*     */         
/* 478 */         response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */         
/* 480 */         if (this.expectationVerifier != null) {
/*     */           try {
/* 482 */             this.expectationVerifier.verify(request, response, context);
/* 483 */           } catch (HttpException ex) {
/* 484 */             response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
/*     */             
/* 486 */             response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */             
/* 488 */             handleException(ex, response);
/*     */           } 
/*     */         }
/*     */         
/* 492 */         synchronized (connState) {
/* 493 */           if (response.getStatusLine().getStatusCode() < 200) {
/*     */ 
/*     */             
/* 496 */             connState.setResponse(response);
/* 497 */             conn.requestOutput();
/*     */ 
/*     */             
/*     */             try {
/*     */               while (true) {
/* 502 */                 int currentState = connState.getOutputState();
/* 503 */                 if (currentState == 8) {
/*     */                   break;
/*     */                 }
/* 506 */                 if (currentState == -1) {
/*     */                   return;
/*     */                 }
/* 509 */                 connState.wait();
/*     */               } 
/* 511 */             } catch (InterruptedException ex) {
/* 512 */               connState.shutdown();
/*     */               return;
/*     */             } 
/* 515 */             connState.resetOutput();
/* 516 */             response = null;
/*     */           } else {
/*     */             
/* 519 */             eeRequest.setEntity(null);
/* 520 */             conn.suspendInput();
/* 521 */             connState.setExpectationFailed(true);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 527 */       if (eeRequest.getEntity() != null) {
/* 528 */         eeRequest.setEntity((HttpEntity)new ContentBufferEntity(eeRequest.getEntity(), connState.getInbuffer()));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 535 */     if (response == null) {
/* 536 */       response = this.responseFactory.newHttpResponse((ProtocolVersion)httpVersion, 200, context);
/*     */ 
/*     */ 
/*     */       
/* 540 */       response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */ 
/*     */       
/* 543 */       context.setAttribute("http.response", response);
/*     */ 
/*     */       
/*     */       try {
/* 547 */         this.httpProcessor.process(request, context);
/*     */         
/* 549 */         HttpRequestHandler handler = null;
/* 550 */         if (this.handlerResolver != null) {
/* 551 */           String requestURI = request.getRequestLine().getUri();
/* 552 */           handler = this.handlerResolver.lookup(requestURI);
/*     */         } 
/* 554 */         if (handler != null) {
/* 555 */           handler.handle(request, response, context);
/*     */         } else {
/* 557 */           response.setStatusCode(501);
/*     */         }
/*     */       
/* 560 */       } catch (HttpException ex) {
/* 561 */         response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
/*     */         
/* 563 */         response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */         
/* 565 */         handleException(ex, response);
/*     */       } 
/*     */     } 
/*     */     
/* 569 */     if (request instanceof HttpEntityEnclosingRequest) {
/* 570 */       HttpEntityEnclosingRequest eeRequest = (HttpEntityEnclosingRequest)request;
/* 571 */       HttpEntity entity = eeRequest.getEntity();
/* 572 */       EntityUtils.consume(entity);
/*     */     } 
/*     */ 
/*     */     
/* 576 */     connState.resetInput();
/*     */     
/* 578 */     this.httpProcessor.process(response, context);
/*     */     
/* 580 */     if (!canResponseHaveBody(request, response)) {
/* 581 */       response.setEntity(null);
/*     */     }
/*     */     
/* 584 */     connState.setResponse(response);
/*     */     
/* 586 */     conn.requestOutput();
/*     */     
/* 588 */     if (response.getEntity() != null) {
/* 589 */       ContentOutputBuffer buffer = connState.getOutbuffer();
/* 590 */       ContentOutputStream contentOutputStream = new ContentOutputStream(buffer);
/*     */       
/* 592 */       HttpEntity entity = response.getEntity();
/* 593 */       entity.writeTo((OutputStream)contentOutputStream);
/* 594 */       contentOutputStream.flush();
/* 595 */       contentOutputStream.close();
/*     */     } 
/*     */     
/* 598 */     synchronized (connState) {
/*     */       while (true) {
/*     */         try {
/* 601 */           int currentState = connState.getOutputState();
/* 602 */           if (currentState == 32) {
/*     */             break;
/*     */           }
/* 605 */           if (currentState == -1) {
/*     */             return;
/*     */           }
/* 608 */           connState.wait();
/*     */         }
/* 610 */         catch (InterruptedException ex) {
/* 611 */           connState.shutdown(); return;
/*     */         } 
/*     */       } 
/* 614 */       connState.resetOutput();
/* 615 */       conn.requestInput();
/* 616 */       connState.notifyAll();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void shutdownConnection(NHttpConnection conn, Throwable cause) {
/* 622 */     HttpContext context = conn.getContext();
/*     */     
/* 624 */     ServerConnState connState = (ServerConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 626 */     super.shutdownConnection(conn, cause);
/*     */     
/* 628 */     if (connState != null) {
/* 629 */       connState.shutdown();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class ServerConnState
/*     */   {
/*     */     public static final int SHUTDOWN = -1;
/*     */     
/*     */     public static final int READY = 0;
/*     */     
/*     */     public static final int REQUEST_RECEIVED = 1;
/*     */     
/*     */     public static final int REQUEST_BODY_STREAM = 2;
/*     */     
/*     */     public static final int REQUEST_BODY_DONE = 4;
/*     */     
/*     */     public static final int RESPONSE_SENT = 8;
/*     */     
/*     */     public static final int RESPONSE_BODY_STREAM = 16;
/*     */     
/*     */     public static final int RESPONSE_BODY_DONE = 32;
/*     */     public static final int RESPONSE_DONE = 32;
/*     */     private final SharedInputBuffer inBuffer;
/*     */     private final SharedOutputBuffer outbuffer;
/*     */     private volatile int inputState;
/*     */     private volatile int outputState;
/*     */     private volatile HttpRequest request;
/*     */     private volatile HttpResponse response;
/*     */     private volatile boolean expectationFailure;
/*     */     
/*     */     public ServerConnState(int bufsize, IOControl ioControl, ByteBufferAllocator allocator) {
/* 661 */       this.inBuffer = new SharedInputBuffer(bufsize, ioControl, allocator);
/* 662 */       this.outbuffer = new SharedOutputBuffer(bufsize, ioControl, allocator);
/* 663 */       this.inputState = 0;
/* 664 */       this.outputState = 0;
/*     */     }
/*     */     
/*     */     public ContentInputBuffer getInbuffer() {
/* 668 */       return (ContentInputBuffer)this.inBuffer;
/*     */     }
/*     */     
/*     */     public ContentOutputBuffer getOutbuffer() {
/* 672 */       return (ContentOutputBuffer)this.outbuffer;
/*     */     }
/*     */     
/*     */     public int getInputState() {
/* 676 */       return this.inputState;
/*     */     }
/*     */     
/*     */     public void setInputState(int inputState) {
/* 680 */       this.inputState = inputState;
/*     */     }
/*     */     
/*     */     public int getOutputState() {
/* 684 */       return this.outputState;
/*     */     }
/*     */     
/*     */     public void setOutputState(int outputState) {
/* 688 */       this.outputState = outputState;
/*     */     }
/*     */     
/*     */     public HttpRequest getRequest() {
/* 692 */       return this.request;
/*     */     }
/*     */     
/*     */     public void setRequest(HttpRequest request) {
/* 696 */       this.request = request;
/*     */     }
/*     */     
/*     */     public HttpResponse getResponse() {
/* 700 */       return this.response;
/*     */     }
/*     */     
/*     */     public void setResponse(HttpResponse response) {
/* 704 */       this.response = response;
/*     */     }
/*     */     
/*     */     public boolean isExpectationFailed() {
/* 708 */       return this.expectationFailure;
/*     */     }
/*     */     
/*     */     public void setExpectationFailed(boolean b) {
/* 712 */       this.expectationFailure = b;
/*     */     }
/*     */     
/*     */     public void close() {
/* 716 */       this.inBuffer.close();
/* 717 */       this.outbuffer.close();
/* 718 */       this.inputState = -1;
/* 719 */       this.outputState = -1;
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 723 */       this.inBuffer.shutdown();
/* 724 */       this.outbuffer.shutdown();
/* 725 */       this.inputState = -1;
/* 726 */       this.outputState = -1;
/*     */     }
/*     */     
/*     */     public void resetInput() {
/* 730 */       this.inBuffer.reset();
/* 731 */       this.request = null;
/* 732 */       this.inputState = 0;
/*     */     }
/*     */     
/*     */     public void resetOutput() {
/* 736 */       this.outbuffer.reset();
/* 737 */       this.response = null;
/* 738 */       this.outputState = 0;
/* 739 */       this.expectationFailure = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/ThrottlingHttpServiceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */