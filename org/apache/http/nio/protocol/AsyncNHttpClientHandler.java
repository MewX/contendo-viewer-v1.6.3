/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ import org.apache.http.nio.entity.ConsumingNHttpEntity;
/*     */ import org.apache.http.nio.entity.NHttpEntityWrapper;
/*     */ import org.apache.http.nio.entity.ProducingNHttpEntity;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.HeapByteBufferAllocator;
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
/*     */ @Deprecated
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class AsyncNHttpClientHandler
/*     */   extends NHttpHandlerBase
/*     */   implements NHttpClientHandler
/*     */ {
/*     */   protected NHttpRequestExecutionHandler execHandler;
/*     */   
/*     */   public AsyncNHttpClientHandler(HttpProcessor httpProcessor, NHttpRequestExecutionHandler execHandler, ConnectionReuseStrategy connStrategy, ByteBufferAllocator allocator, HttpParams params) {
/* 107 */     super(httpProcessor, connStrategy, allocator, params);
/* 108 */     this.execHandler = (NHttpRequestExecutionHandler)Args.notNull(execHandler, "HTTP request execution handler");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AsyncNHttpClientHandler(HttpProcessor httpProcessor, NHttpRequestExecutionHandler execHandler, ConnectionReuseStrategy connStrategy, HttpParams params) {
/* 116 */     this(httpProcessor, execHandler, connStrategy, (ByteBufferAllocator)HeapByteBufferAllocator.INSTANCE, params);
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(NHttpClientConnection conn, Object attachment) {
/* 121 */     HttpContext context = conn.getContext();
/*     */     
/* 123 */     initialize(conn, attachment);
/*     */     
/* 125 */     ClientConnState connState = new ClientConnState();
/* 126 */     context.setAttribute("http.nio.conn-state", connState);
/*     */     
/* 128 */     if (this.eventListener != null) {
/* 129 */       this.eventListener.connectionOpen((NHttpConnection)conn);
/*     */     }
/*     */     
/* 132 */     requestReady(conn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closed(NHttpClientConnection conn) {
/* 137 */     HttpContext context = conn.getContext();
/*     */     
/* 139 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */     try {
/* 141 */       connState.reset();
/* 142 */     } catch (IOException ex) {
/* 143 */       if (this.eventListener != null) {
/* 144 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */     
/* 148 */     this.execHandler.finalizeContext(context);
/*     */     
/* 150 */     if (this.eventListener != null) {
/* 151 */       this.eventListener.connectionClosed((NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, HttpException ex) {
/* 157 */     closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 158 */     if (this.eventListener != null) {
/* 159 */       this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void exception(NHttpClientConnection conn, IOException ex) {
/* 165 */     shutdownConnection((NHttpConnection)conn, ex);
/* 166 */     if (this.eventListener != null) {
/* 167 */       this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestReady(NHttpClientConnection conn) {
/* 173 */     HttpContext context = conn.getContext();
/*     */     
/* 175 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/* 176 */     if (connState.getOutputState() != 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 182 */       HttpRequest request = this.execHandler.submitRequest(context);
/* 183 */       if (request == null) {
/*     */         return;
/*     */       }
/*     */       
/* 187 */       request.setParams((HttpParams)new DefaultedHttpParams(request.getParams(), this.params));
/*     */ 
/*     */       
/* 190 */       context.setAttribute("http.request", request);
/* 191 */       this.httpProcessor.process(request, context);
/*     */       
/* 193 */       HttpEntityEnclosingRequest entityReq = null;
/* 194 */       HttpEntity entity = null;
/*     */       
/* 196 */       if (request instanceof HttpEntityEnclosingRequest) {
/* 197 */         entityReq = (HttpEntityEnclosingRequest)request;
/* 198 */         entity = entityReq.getEntity();
/*     */       } 
/*     */       
/* 201 */       if (entity instanceof ProducingNHttpEntity) {
/* 202 */         connState.setProducingEntity((ProducingNHttpEntity)entity);
/* 203 */       } else if (entity != null) {
/* 204 */         connState.setProducingEntity((ProducingNHttpEntity)new NHttpEntityWrapper(entity));
/*     */       } 
/*     */       
/* 207 */       connState.setRequest(request);
/* 208 */       conn.submitRequest(request);
/* 209 */       connState.setOutputState(1);
/*     */       
/* 211 */       if (entityReq != null && entityReq.expectContinue()) {
/* 212 */         int timeout = conn.getSocketTimeout();
/* 213 */         connState.setTimeout(timeout);
/* 214 */         timeout = this.params.getIntParameter("http.protocol.wait-for-continue", 3000);
/*     */         
/* 216 */         conn.setSocketTimeout(timeout);
/* 217 */         connState.setOutputState(2);
/* 218 */       } else if (connState.getProducingEntity() != null) {
/* 219 */         connState.setOutputState(4);
/*     */       }
/*     */     
/* 222 */     } catch (IOException ex) {
/* 223 */       shutdownConnection((NHttpConnection)conn, ex);
/* 224 */       if (this.eventListener != null) {
/* 225 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 227 */     } catch (HttpException ex) {
/* 228 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 229 */       if (this.eventListener != null) {
/* 230 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputReady(NHttpClientConnection conn, ContentDecoder decoder) {
/* 237 */     HttpContext context = conn.getContext();
/*     */     
/* 239 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 241 */     ConsumingNHttpEntity consumingEntity = connState.getConsumingEntity();
/*     */     
/*     */     try {
/* 244 */       consumingEntity.consumeContent(decoder, (IOControl)conn);
/* 245 */       if (decoder.isCompleted()) {
/* 246 */         processResponse(conn, connState);
/*     */       }
/*     */     }
/* 249 */     catch (IOException ex) {
/* 250 */       shutdownConnection((NHttpConnection)conn, ex);
/* 251 */       if (this.eventListener != null) {
/* 252 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 254 */     } catch (HttpException ex) {
/* 255 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 256 */       if (this.eventListener != null) {
/* 257 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void outputReady(NHttpClientConnection conn, ContentEncoder encoder) {
/* 264 */     HttpContext context = conn.getContext();
/* 265 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/*     */     try {
/* 268 */       if (connState.getOutputState() == 2) {
/* 269 */         conn.suspendOutput();
/*     */         
/*     */         return;
/*     */       } 
/* 273 */       ProducingNHttpEntity entity = connState.getProducingEntity();
/*     */       
/* 275 */       entity.produceContent(encoder, (IOControl)conn);
/* 276 */       if (encoder.isCompleted()) {
/* 277 */         connState.setOutputState(8);
/*     */       }
/* 279 */     } catch (IOException ex) {
/* 280 */       shutdownConnection((NHttpConnection)conn, ex);
/* 281 */       if (this.eventListener != null) {
/* 282 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReceived(NHttpClientConnection conn) {
/* 289 */     HttpContext context = conn.getContext();
/* 290 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */     
/* 292 */     HttpResponse response = conn.getHttpResponse();
/* 293 */     response.setParams((HttpParams)new DefaultedHttpParams(response.getParams(), this.params));
/*     */ 
/*     */     
/* 296 */     HttpRequest request = connState.getRequest();
/*     */     
/*     */     try {
/* 299 */       int statusCode = response.getStatusLine().getStatusCode();
/* 300 */       if (statusCode < 200) {
/*     */         
/* 302 */         if (statusCode == 100 && connState.getOutputState() == 2)
/*     */         {
/* 304 */           continueRequest(conn, connState);
/*     */         }
/*     */         return;
/*     */       } 
/* 308 */       connState.setResponse(response);
/* 309 */       if (connState.getOutputState() == 2) {
/* 310 */         cancelRequest(conn, connState);
/* 311 */       } else if (connState.getOutputState() == 4) {
/*     */         
/* 313 */         cancelRequest(conn, connState);
/* 314 */         connState.invalidate();
/* 315 */         conn.suspendOutput();
/*     */       } 
/*     */ 
/*     */       
/* 319 */       context.setAttribute("http.response", response);
/*     */       
/* 321 */       if (!canResponseHaveBody(request, response)) {
/* 322 */         conn.resetInput();
/* 323 */         response.setEntity(null);
/* 324 */         this.httpProcessor.process(response, context);
/* 325 */         processResponse(conn, connState);
/*     */       } else {
/* 327 */         HttpEntity entity = response.getEntity();
/* 328 */         if (entity != null) {
/* 329 */           ConsumingNHttpEntity consumingEntity = this.execHandler.responseEntity(response, context);
/*     */           
/* 331 */           if (consumingEntity == null) {
/* 332 */             consumingEntity = new NullNHttpEntity(entity);
/*     */           }
/* 334 */           response.setEntity((HttpEntity)consumingEntity);
/* 335 */           connState.setConsumingEntity(consumingEntity);
/* 336 */           this.httpProcessor.process(response, context);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 341 */     } catch (IOException ex) {
/* 342 */       shutdownConnection((NHttpConnection)conn, ex);
/* 343 */       if (this.eventListener != null) {
/* 344 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/* 346 */     } catch (HttpException ex) {
/* 347 */       closeConnection((NHttpConnection)conn, (Throwable)ex);
/* 348 */       if (this.eventListener != null) {
/* 349 */         this.eventListener.fatalProtocolException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void timeout(NHttpClientConnection conn) {
/* 356 */     HttpContext context = conn.getContext();
/* 357 */     ClientConnState connState = (ClientConnState)context.getAttribute("http.nio.conn-state");
/*     */ 
/*     */     
/*     */     try {
/* 361 */       if (connState.getOutputState() == 2) {
/* 362 */         continueRequest(conn, connState);
/*     */         
/*     */         return;
/*     */       } 
/* 366 */     } catch (IOException ex) {
/* 367 */       shutdownConnection((NHttpConnection)conn, ex);
/* 368 */       if (this.eventListener != null) {
/* 369 */         this.eventListener.fatalIOException(ex, (NHttpConnection)conn);
/*     */       }
/*     */     } 
/*     */     
/* 373 */     handleTimeout((NHttpConnection)conn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize(NHttpClientConnection conn, Object attachment) {
/* 379 */     HttpContext context = conn.getContext();
/*     */     
/* 381 */     context.setAttribute("http.connection", conn);
/* 382 */     this.execHandler.initalizeContext(context, attachment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void continueRequest(NHttpClientConnection conn, ClientConnState connState) throws IOException {
/* 392 */     int timeout = connState.getTimeout();
/* 393 */     conn.setSocketTimeout(timeout);
/*     */     
/* 395 */     conn.requestOutput();
/* 396 */     connState.setOutputState(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void cancelRequest(NHttpClientConnection conn, ClientConnState connState) throws IOException {
/* 403 */     int timeout = connState.getTimeout();
/* 404 */     conn.setSocketTimeout(timeout);
/*     */     
/* 406 */     conn.resetOutput();
/* 407 */     connState.resetOutput();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processResponse(NHttpClientConnection conn, ClientConnState connState) throws IOException, HttpException {
/* 417 */     if (!connState.isValid()) {
/* 418 */       conn.close();
/*     */     }
/*     */     
/* 421 */     HttpContext context = conn.getContext();
/* 422 */     HttpResponse response = connState.getResponse();
/* 423 */     this.execHandler.handleResponse(response, context);
/* 424 */     if (!this.connStrategy.keepAlive(response, context)) {
/* 425 */       conn.close();
/*     */     }
/*     */     
/* 428 */     if (conn.isOpen()) {
/*     */       
/* 430 */       connState.resetInput();
/* 431 */       connState.resetOutput();
/* 432 */       conn.requestOutput();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static class ClientConnState
/*     */   {
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
/*     */     public static final int RESPONSE_BODY_DONE = 64;
/*     */     private int outputState;
/*     */     private HttpRequest request;
/*     */     private HttpResponse response;
/*     */     private ConsumingNHttpEntity consumingEntity;
/*     */     private ProducingNHttpEntity producingEntity;
/*     */     private boolean valid = true;
/*     */     private int timeout;
/*     */     
/*     */     public void setConsumingEntity(ConsumingNHttpEntity consumingEntity) {
/* 462 */       this.consumingEntity = consumingEntity;
/*     */     }
/*     */     
/*     */     public void setProducingEntity(ProducingNHttpEntity producingEntity) {
/* 466 */       this.producingEntity = producingEntity;
/*     */     }
/*     */     
/*     */     public ProducingNHttpEntity getProducingEntity() {
/* 470 */       return this.producingEntity;
/*     */     }
/*     */     
/*     */     public ConsumingNHttpEntity getConsumingEntity() {
/* 474 */       return this.consumingEntity;
/*     */     }
/*     */     
/*     */     public int getOutputState() {
/* 478 */       return this.outputState;
/*     */     }
/*     */     
/*     */     public void setOutputState(int outputState) {
/* 482 */       this.outputState = outputState;
/*     */     }
/*     */     
/*     */     public HttpRequest getRequest() {
/* 486 */       return this.request;
/*     */     }
/*     */     
/*     */     public void setRequest(HttpRequest request) {
/* 490 */       this.request = request;
/*     */     }
/*     */     
/*     */     public HttpResponse getResponse() {
/* 494 */       return this.response;
/*     */     }
/*     */     
/*     */     public void setResponse(HttpResponse response) {
/* 498 */       this.response = response;
/*     */     }
/*     */     
/*     */     public int getTimeout() {
/* 502 */       return this.timeout;
/*     */     }
/*     */     
/*     */     public void setTimeout(int timeout) {
/* 506 */       this.timeout = timeout;
/*     */     }
/*     */     
/*     */     public void resetInput() throws IOException {
/* 510 */       this.response = null;
/* 511 */       if (this.consumingEntity != null) {
/* 512 */         this.consumingEntity.finish();
/* 513 */         this.consumingEntity = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void resetOutput() throws IOException {
/* 518 */       this.request = null;
/* 519 */       if (this.producingEntity != null) {
/* 520 */         this.producingEntity.finish();
/* 521 */         this.producingEntity = null;
/*     */       } 
/* 523 */       this.outputState = 0;
/*     */     }
/*     */     
/*     */     public void reset() throws IOException {
/* 527 */       resetInput();
/* 528 */       resetOutput();
/*     */     }
/*     */     
/*     */     public boolean isValid() {
/* 532 */       return this.valid;
/*     */     }
/*     */     
/*     */     public void invalidate() {
/* 536 */       this.valid = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/AsyncNHttpClientHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */