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
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.nio.NHttpClientHandler;
/*     */ import org.apache.http.nio.NHttpConnection;
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
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ import org.apache.http.util.Args;
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
/*     */ public class ThrottlingHttpClientHandler
/*     */   extends NHttpHandlerBase
/*     */   implements NHttpClientHandler
/*     */ {
/*     */   protected HttpRequestExecutionHandler execHandler;
/*     */   protected final Executor executor;
/*     */   private final int bufsize;
/*     */   
/*     */   public ThrottlingHttpClientHandler(HttpProcessor httpProcessor, HttpRequestExecutionHandler execHandler, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, Executor executor, HttpParams params) {
/* 118 */     super(httpProcessor, connStrategy, allocator, params);
/* 119 */     Args.notNull(execHandler, "HTTP request execution handler");
/* 120 */     Args.notNull(executor, "Executor");
/* 121 */     this.execHandler = execHandler;
/* 122 */     this.executor = executor;
/* 123 */     this.bufsize = this.params.getIntParameter("http.nio.content-buffer-size", 20480);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ThrottlingHttpClientHandler(HttpProcessor httpProcessor, HttpRequestExecutionHandler execHandler, ConnectionReuseStrategy connStrategy, Executor executor, HttpParams params) {
/* 132 */     this(httpProcessor, execHandler, connStrategy, (ByteBufferAllocator)DirectByteBufferAllocator.INSTANCE, executor, params);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void connected(NHttpClientConnection conn, Object attachment) {
/* 138 */     HttpContext context = conn.getContext();
/*     */     
/* 140 */     initialize(conn, attachment);
/*     */     
/* 142 */     ClientConnState connState = new ClientConnState(this.bufsize, (IOControl)conn, this.allocator);
/* 143 */     context.setAttribute("http.nio.conn-state", connState);
/*     */     
/* 145 */     if (this.eventListener != null) {
/* 146 */       this.eventListener.connectionOpen((NHttpConnection)conn);
/*     */     }
/*     */     
/* 149 */     requestReady(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpClientConnection conn) {
/* 154 */     HttpContext context = conn.getContext();
/* 155 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 157 */     if (connState != null) {
/* 158 */       synchronized (connState) {
/* 159 */         connState.close();
/* 160 */         connState.notifyAll();
/*     */       } 
/*     */     }
/* 163 */     this.execHandler.finalizeContext(context);
/*     */     
/* 165 */     if (this.eventListener != null) {
/* 166 */       this.eventListener.connectionClosed((NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, HttpException ex) {
/* 172 */     closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 173 */     if (this.eventListener != null) {
/* 174 */       this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, IOException ex) {
/* 180 */     shutdownConnection((NHttpConnection)conn, ex);
/* 181 */     if (this.eventListener != null) {
/* 182 */       this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestReady(NHttpClientConnection conn) {
/* 189 */     HttpContext context = conn.getContext();
/*     */     
/* 191 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 195 */       synchronized (connState) {
/* 196 */         if (connState.getOutputState() != 0) {
/*     */           return;
/*     */         }
/*     */         
/* 200 */         HttpRequest request = this.execHandler.submitRequest(context);
/* 201 */         if (request == null) {
/*     */           return;
/*     */         }
/*     */         
/* 205 */         request.setParams((HttpParams)new DefaultedHttpParams(request.getParams(), this.params));
/*     */ 
/*     */         
/* 208 */         context.setAttribute("http.request", request);
/* 209 */         this.httpProcessor.process(request, context);
/* 210 */         connState.setRequest(request);
/* 211 */         conn.submitRequest(request);
/* 212 */         connState.setOutputState(1);
/*     */         
/* 214 */         conn.requestInput();
/*     */         
/* 216 */         if (request instanceof HttpEntityEnclosingRequest) {
/* 217 */           if (((HttpEntityEnclosingRequest)request).expectContinue()) {
/* 218 */             int timeout = conn.getSocketTimeout();
/* 219 */             connState.setTimeout(timeout);
/* 220 */             timeout = this.params.getIntParameter("http.protocol.wait-for-continue", 3000);
/*     */             
/* 222 */             conn.setSocketTimeout(timeout);
/* 223 */             connState.setOutputState(2);
/*     */           } else {
/* 225 */             sendRequestBody((HttpEntityEnclosingRequest)request, connState, conn);
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 232 */         connState.notifyAll();
/*     */       }
/*     */     
/* 235 */     } catch (IOException ex) {
/* 236 */       shutdownConnection((NHttpConnection)conn, ex);
/* 237 */       if (this.eventListener != null) {
/* 238 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 240 */     } catch (HttpException ex) {
/* 241 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 242 */       if (this.eventListener != null) {
/* 243 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpClientConnection conn, ContentEncoder encoder) {
/* 250 */     HttpContext context = conn.getContext();
/*     */     
/* 252 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 256 */       synchronized (connState) {
/* 257 */         if (connState.getOutputState() == 2) {
/* 258 */           conn.suspendOutput();
/*     */           return;
/*     */         } 
/* 261 */         ContentOutputBuffer buffer = connState.getOutbuffer();
/* 262 */         buffer.produceContent(encoder);
/* 263 */         if (encoder.isCompleted()) {
/* 264 */           connState.setInputState(8);
/*     */         } else {
/* 266 */           connState.setInputState(4);
/*     */         } 
/*     */         
/* 269 */         connState.notifyAll();
/*     */       }
/*     */     
/* 272 */     } catch (IOException ex) {
/* 273 */       shutdownConnection((NHttpConnection)conn, ex);
/* 274 */       if (this.eventListener != null) {
/* 275 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReceived(NHttpClientConnection conn) {
/* 282 */     HttpContext context = conn.getContext();
/* 283 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 287 */       synchronized (connState) {
/* 288 */         HttpResponse response = conn.getHttpResponse();
/* 289 */         response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */ 
/*     */         
/* 292 */         HttpRequest request = connState.getRequest();
/*     */         
/* 294 */         int statusCode = response.getStatusLine().getStatusCode();
/* 295 */         if (statusCode < 200) {
/*     */           
/* 297 */           if (statusCode == 100 && connState.getOutputState() == 2) {
/*     */             
/* 299 */             connState.setOutputState(1);
/* 300 */             continueRequest(conn, connState);
/*     */           } 
/*     */           return;
/*     */         } 
/* 304 */         connState.setResponse(response);
/* 305 */         connState.setInputState(16);
/*     */         
/* 307 */         if (connState.getOutputState() == 2) {
/* 308 */           int timeout = connState.getTimeout();
/* 309 */           conn.setSocketTimeout(timeout);
/* 310 */           conn.resetOutput();
/*     */         } 
/*     */ 
/*     */         
/* 314 */         if (!canResponseHaveBody(request, response)) {
/* 315 */           conn.resetInput();
/* 316 */           response.setEntity(null);
/* 317 */           connState.setInputState(64);
/*     */           
/* 319 */           if (!this.connStrategy.keepAlive(response, context)) {
/* 320 */             conn.close();
/*     */           }
/*     */         } 
/*     */         
/* 324 */         if (response.getEntity() != null) {
/* 325 */           response.setEntity((HttpEntity)new ContentBufferEntity(response.getEntity(), connState.getInbuffer()));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 330 */         context.setAttribute("http.response", response);
/*     */         
/* 332 */         this.httpProcessor.process(response, context);
/*     */         
/* 334 */         handleResponse(response, connState, conn);
/*     */         
/* 336 */         connState.notifyAll();
/*     */       }
/*     */     
/* 339 */     } catch (IOException ex) {
/* 340 */       shutdownConnection((NHttpConnection)conn, ex);
/* 341 */       if (this.eventListener != null) {
/* 342 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 344 */     } catch (HttpException ex) {
/* 345 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 346 */       if (this.eventListener != null) {
/* 347 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpClientConnection conn, ContentDecoder decoder) {
/* 354 */     HttpContext context = conn.getContext();
/*     */     
/* 356 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/*     */     try {
/* 359 */       synchronized (connState) {
/* 360 */         HttpResponse response = connState.getResponse();
/* 361 */         ContentInputBuffer buffer = connState.getInbuffer();
/*     */         
/* 363 */         buffer.consumeContent(decoder);
/* 364 */         if (decoder.isCompleted()) {
/* 365 */           connState.setInputState(64);
/*     */           
/* 367 */           if (!this.connStrategy.keepAlive(response, context)) {
/* 368 */             conn.close();
/*     */           }
/*     */         } else {
/* 371 */           connState.setInputState(32);
/*     */         } 
/*     */         
/* 374 */         connState.notifyAll();
/*     */       }
/*     */     
/* 377 */     } catch (IOException ex) {
/* 378 */       shutdownConnection((NHttpConnection)conn, ex);
/* 379 */       if (this.eventListener != null) {
/* 380 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpClientConnection conn) {
/* 387 */     HttpContext context = conn.getContext();
/* 388 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 392 */       synchronized (connState) {
/* 393 */         if (connState.getOutputState() == 2) {
/* 394 */           connState.setOutputState(1);
/* 395 */           continueRequest(conn, connState);
/*     */           
/* 397 */           connState.notifyAll();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 402 */     } catch (IOException ex) {
/* 403 */       shutdownConnection((NHttpConnection)conn, ex);
/* 404 */       if (this.eventListener != null) {
/* 405 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */     
/* 409 */     handleTimeout((NHttpConnection)conn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize(NHttpClientConnection conn, Object attachment) {
/* 415 */     HttpContext context = conn.getContext();
/*     */     
/* 417 */     context.setAttribute("http.connection", conn);
/* 418 */     this.execHandler.initalizeContext(context, attachment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void continueRequest(NHttpClientConnection conn, ClientConnState connState) throws IOException {
/* 425 */     HttpRequest request = connState.getRequest();
/*     */     
/* 427 */     int timeout = connState.getTimeout();
/* 428 */     conn.setSocketTimeout(timeout);
/*     */     
/* 430 */     sendRequestBody((HttpEntityEnclosingRequest)request, connState, conn);
/*     */   }
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
/*     */   private void sendRequestBody(final HttpEntityEnclosingRequest request, final ClientConnState connState, final NHttpClientConnection conn) throws IOException {
/* 443 */     HttpEntity entity = request.getEntity();
/* 444 */     if (entity != null)
/*     */     {
/* 446 */       this.executor.execute(new Runnable()
/*     */           {
/*     */ 
/*     */ 
/*     */             
/*     */             public void run()
/*     */             {
/*     */               try {
/* 454 */                 synchronized (connState) {
/*     */                   while (true) {
/*     */                     try {
/* 457 */                       int currentState = connState.getOutputState();
/* 458 */                       if (!connState.isWorkerRunning()) {
/*     */                         break;
/*     */                       }
/* 461 */                       if (currentState == -1) {
/*     */                         return;
/*     */                       }
/* 464 */                       connState.wait();
/*     */                     }
/* 466 */                     catch (InterruptedException ex) {
/* 467 */                       connState.shutdown(); return;
/*     */                     } 
/*     */                   } 
/* 470 */                   connState.setWorkerRunning(true);
/*     */                 } 
/*     */                 
/* 473 */                 ContentOutputStream contentOutputStream = new ContentOutputStream(connState.getOutbuffer());
/*     */                 
/* 475 */                 request.getEntity().writeTo((OutputStream)contentOutputStream);
/* 476 */                 contentOutputStream.flush();
/* 477 */                 contentOutputStream.close();
/*     */                 
/* 479 */                 synchronized (connState) {
/* 480 */                   connState.setWorkerRunning(false);
/* 481 */                   connState.notifyAll();
/*     */                 }
/*     */               
/* 484 */               } catch (IOException ex) {
/* 485 */                 ThrottlingHttpClientHandler.this.shutdownConnection((NHttpConnection)conn, ex);
/* 486 */                 if (ThrottlingHttpClientHandler.this.eventListener != null) {
/* 487 */                   ThrottlingHttpClientHandler.this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleResponse(final HttpResponse response, final ClientConnState connState, final NHttpClientConnection conn) {
/* 501 */     final HttpContext context = conn.getContext();
/*     */     
/* 503 */     this.executor.execute(new Runnable()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/* 511 */               synchronized (connState) {
/*     */                 while (true) {
/*     */                   try {
/* 514 */                     int currentState = connState.getOutputState();
/* 515 */                     if (!connState.isWorkerRunning()) {
/*     */                       break;
/*     */                     }
/* 518 */                     if (currentState == -1) {
/*     */                       return;
/*     */                     }
/* 521 */                     connState.wait();
/*     */                   }
/* 523 */                   catch (InterruptedException ex) {
/* 524 */                     connState.shutdown(); return;
/*     */                   } 
/*     */                 } 
/* 527 */                 connState.setWorkerRunning(true);
/*     */               } 
/*     */               
/* 530 */               ThrottlingHttpClientHandler.this.execHandler.handleResponse(response, context);
/*     */               
/* 532 */               synchronized (connState)
/*     */               {
/*     */                 while (true) {
/*     */                   try {
/* 536 */                     int currentState = connState.getInputState();
/* 537 */                     if (currentState == 64) {
/*     */                       break;
/*     */                     }
/* 540 */                     if (currentState == -1) {
/*     */                       return;
/*     */                     }
/* 543 */                     connState.wait();
/*     */                   }
/* 545 */                   catch (InterruptedException ex) {
/* 546 */                     connState.shutdown(); break;
/*     */                   } 
/*     */                 } 
/* 549 */                 connState.resetInput();
/* 550 */                 connState.resetOutput();
/* 551 */                 if (conn.isOpen()) {
/* 552 */                   conn.requestOutput();
/*     */                 }
/* 554 */                 connState.setWorkerRunning(false);
/* 555 */                 connState.notifyAll();
/*     */               }
/*     */             
/* 558 */             } catch (IOException ex) {
/* 559 */               ThrottlingHttpClientHandler.this.shutdownConnection((NHttpConnection)conn, ex);
/* 560 */               if (ThrottlingHttpClientHandler.this.eventListener != null) {
/* 561 */                 ThrottlingHttpClientHandler.this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */               }
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static class ClientConnState
/*     */   {
/*     */     public static final int SHUTDOWN = -1;
/*     */     
/*     */     public static final int READY = 0;
/*     */     
/*     */     public static final int REQUEST_SENT = 1;
/*     */     
/*     */     public static final int EXPECT_CONTINUE = 2;
/*     */     
/*     */     public static final int REQUEST_BODY_STREAM = 4;
/*     */     
/*     */     public static final int REQUEST_BODY_DONE = 8;
/*     */     
/*     */     public static final int RESPONSE_RECEIVED = 16;
/*     */     
/*     */     public static final int RESPONSE_BODY_STREAM = 32;
/*     */     
/*     */     public static final int RESPONSE_BODY_DONE = 64;
/*     */     
/*     */     public static final int RESPONSE_DONE = 64;
/*     */     
/*     */     private final SharedInputBuffer inBuffer;
/*     */     private final SharedOutputBuffer outbuffer;
/*     */     private volatile int inputState;
/*     */     private volatile int outputState;
/*     */     private volatile HttpRequest request;
/*     */     private volatile HttpResponse response;
/*     */     private volatile int timeout;
/*     */     private volatile boolean workerRunning;
/*     */     
/*     */     public ClientConnState(int bufsize, IOControl ioControl, ByteBufferAllocator allocator) {
/* 601 */       this.inBuffer = new SharedInputBuffer(bufsize, ioControl, allocator);
/* 602 */       this.outbuffer = new SharedOutputBuffer(bufsize, ioControl, allocator);
/* 603 */       this.inputState = 0;
/* 604 */       this.outputState = 0;
/*     */     }
/*     */     
/*     */     public ContentInputBuffer getInbuffer() {
/* 608 */       return (ContentInputBuffer)this.inBuffer;
/*     */     }
/*     */     
/*     */     public ContentOutputBuffer getOutbuffer() {
/* 612 */       return (ContentOutputBuffer)this.outbuffer;
/*     */     }
/*     */     
/*     */     public int getInputState() {
/* 616 */       return this.inputState;
/*     */     }
/*     */     
/*     */     public void setInputState(int inputState) {
/* 620 */       this.inputState = inputState;
/*     */     }
/*     */     
/*     */     public int getOutputState() {
/* 624 */       return this.outputState;
/*     */     }
/*     */     
/*     */     public void setOutputState(int outputState) {
/* 628 */       this.outputState = outputState;
/*     */     }
/*     */     
/*     */     public HttpRequest getRequest() {
/* 632 */       return this.request;
/*     */     }
/*     */     
/*     */     public void setRequest(HttpRequest request) {
/* 636 */       this.request = request;
/*     */     }
/*     */     
/*     */     public HttpResponse getResponse() {
/* 640 */       return this.response;
/*     */     }
/*     */     
/*     */     public void setResponse(HttpResponse response) {
/* 644 */       this.response = response;
/*     */     }
/*     */     
/*     */     public int getTimeout() {
/* 648 */       return this.timeout;
/*     */     }
/*     */     
/*     */     public void setTimeout(int timeout) {
/* 652 */       this.timeout = timeout;
/*     */     }
/*     */     
/*     */     public boolean isWorkerRunning() {
/* 656 */       return this.workerRunning;
/*     */     }
/*     */     
/*     */     public void setWorkerRunning(boolean b) {
/* 660 */       this.workerRunning = b;
/*     */     }
/*     */     
/*     */     public void close() {
/* 664 */       this.inBuffer.close();
/* 665 */       this.outbuffer.close();
/* 666 */       this.inputState = -1;
/* 667 */       this.outputState = -1;
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 671 */       this.inBuffer.shutdown();
/* 672 */       this.outbuffer.shutdown();
/* 673 */       this.inputState = -1;
/* 674 */       this.outputState = -1;
/*     */     }
/*     */     
/*     */     public void resetInput() {
/* 678 */       this.inBuffer.reset();
/* 679 */       this.request = null;
/* 680 */       this.inputState = 0;
/*     */     }
/*     */     
/*     */     public void resetOutput() {
/* 684 */       this.outbuffer.reset();
/* 685 */       this.response = null;
/* 686 */       this.outputState = 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/ThrottlingHttpClientHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */