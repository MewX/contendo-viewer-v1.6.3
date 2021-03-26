/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Future;
/*     */ import org.apache.http.ConnectionClosedException;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.ExceptionLogger;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.concurrent.BasicFuture;
/*     */ import org.apache.http.concurrent.FutureCallback;
/*     */ import org.apache.http.impl.DefaultConnectionReuseStrategy;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.pool.ConnPool;
/*     */ import org.apache.http.pool.PoolEntry;
/*     */ import org.apache.http.protocol.BasicHttpContext;
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
/*     */ @Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/*     */ public class HttpAsyncRequester
/*     */ {
/*     */   private final HttpProcessor httpprocessor;
/*     */   private final ConnectionReuseStrategy connReuseStrategy;
/*     */   private final ExceptionLogger exceptionLogger;
/*     */   
/*     */   @Deprecated
/*     */   public HttpAsyncRequester(HttpProcessor httpprocessor, ConnectionReuseStrategy reuseStrategy, HttpParams params) {
/*  78 */     this(httpprocessor, reuseStrategy);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpAsyncRequester(HttpProcessor httpprocessor, ConnectionReuseStrategy connReuseStrategy, ExceptionLogger exceptionLogger) {
/*  98 */     this.httpprocessor = (HttpProcessor)Args.notNull(httpprocessor, "HTTP processor");
/*  99 */     this.connReuseStrategy = (connReuseStrategy != null) ? connReuseStrategy : (ConnectionReuseStrategy)DefaultConnectionReuseStrategy.INSTANCE;
/*     */     
/* 101 */     this.exceptionLogger = (exceptionLogger != null) ? exceptionLogger : ExceptionLogger.NO_OP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpAsyncRequester(HttpProcessor httpprocessor, ConnectionReuseStrategy connReuseStrategy) {
/* 112 */     this(httpprocessor, connReuseStrategy, (ExceptionLogger)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpAsyncRequester(HttpProcessor httpprocessor) {
/* 121 */     this(httpprocessor, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, NHttpClientConnection conn, HttpContext context, FutureCallback<T> callback) {
/* 141 */     Args.notNull(requestProducer, "HTTP request producer");
/* 142 */     Args.notNull(responseConsumer, "HTTP response consumer");
/* 143 */     Args.notNull(conn, "HTTP connection");
/* 144 */     Args.notNull(context, "HTTP context");
/* 145 */     BasicAsyncClientExchangeHandler<T> handler = new BasicAsyncClientExchangeHandler<T>(requestProducer, responseConsumer, callback, context, conn, this.httpprocessor, this.connReuseStrategy);
/*     */ 
/*     */     
/* 148 */     initExecution(handler, conn);
/* 149 */     return handler.getFuture();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initExecution(HttpAsyncClientExchangeHandler handler, NHttpClientConnection conn) {
/* 155 */     HttpContext context = conn.getContext();
/* 156 */     synchronized (context) {
/* 157 */       context.setAttribute("http.nio.exchange-handler", handler);
/* 158 */       if (!conn.isOpen()) {
/* 159 */         handler.failed((Exception)new ConnectionClosedException());
/*     */       } else {
/* 161 */         conn.requestOutput();
/*     */       } 
/*     */     } 
/* 164 */     if (handler.isDone()) {
/*     */       try {
/* 166 */         handler.close();
/* 167 */       } catch (IOException ex) {
/* 168 */         log(ex);
/*     */       } 
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, NHttpClientConnection conn, HttpContext context) {
/* 188 */     return execute(requestProducer, responseConsumer, conn, context, (FutureCallback<T>)null);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, NHttpClientConnection conn) {
/* 204 */     return execute(requestProducer, responseConsumer, conn, (HttpContext)new BasicHttpContext());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, E extends PoolEntry<HttpHost, NHttpClientConnection>> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, ConnPool<HttpHost, E> connPool, HttpContext context, FutureCallback<T> callback) {
/* 225 */     Args.notNull(requestProducer, "HTTP request producer");
/* 226 */     Args.notNull(responseConsumer, "HTTP response consumer");
/* 227 */     Args.notNull(connPool, "HTTP connection pool");
/* 228 */     Args.notNull(context, "HTTP context");
/* 229 */     BasicFuture<T> future = new BasicFuture(callback);
/* 230 */     HttpHost target = requestProducer.getTarget();
/* 231 */     connPool.lease(target, null, new ConnRequestCallback<T, E>(future, requestProducer, responseConsumer, connPool, context));
/*     */     
/* 233 */     return (Future<T>)future;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, E extends PoolEntry<HttpHost, NHttpClientConnection>> Future<List<T>> executePipelined(HttpHost target, List<? extends HttpAsyncRequestProducer> requestProducers, List<? extends HttpAsyncResponseConsumer<T>> responseConsumers, ConnPool<HttpHost, E> connPool, HttpContext context, FutureCallback<List<T>> callback) {
/* 258 */     Args.notNull(target, "HTTP target");
/* 259 */     Args.notEmpty(requestProducers, "Request producer list");
/* 260 */     Args.notEmpty(responseConsumers, "Response consumer list");
/* 261 */     Args.notNull(connPool, "HTTP connection pool");
/* 262 */     Args.notNull(context, "HTTP context");
/* 263 */     BasicFuture<List<T>> future = new BasicFuture(callback);
/* 264 */     connPool.lease(target, null, new ConnPipelinedRequestCallback<T, E>(future, requestProducers, responseConsumers, connPool, context));
/*     */     
/* 266 */     return (Future<List<T>>)future;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, E extends PoolEntry<HttpHost, NHttpClientConnection>> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, E poolEntry, ConnPool<HttpHost, E> connPool, HttpContext context, FutureCallback<T> callback) {
/* 293 */     Args.notNull(requestProducer, "HTTP request producer");
/* 294 */     Args.notNull(responseConsumer, "HTTP response consumer");
/* 295 */     Args.notNull(connPool, "HTTP connection pool");
/* 296 */     Args.notNull(poolEntry, "Pool entry");
/* 297 */     Args.notNull(context, "HTTP context");
/* 298 */     BasicFuture<T> future = new BasicFuture(callback);
/* 299 */     NHttpClientConnection conn = (NHttpClientConnection)poolEntry.getConnection();
/* 300 */     BasicAsyncClientExchangeHandler<T> handler = new BasicAsyncClientExchangeHandler<T>(requestProducer, responseConsumer, new RequestExecutionCallback<T, E>(future, poolEntry, connPool), context, conn, this.httpprocessor, this.connReuseStrategy);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     initExecution(handler, conn);
/* 306 */     return (Future<T>)future;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, E extends PoolEntry<HttpHost, NHttpClientConnection>> Future<List<T>> executePipelined(List<HttpAsyncRequestProducer> requestProducers, List<HttpAsyncResponseConsumer<T>> responseConsumers, E poolEntry, ConnPool<HttpHost, E> connPool, HttpContext context, FutureCallback<List<T>> callback) {
/* 333 */     Args.notEmpty(requestProducers, "Request producer list");
/* 334 */     Args.notEmpty(responseConsumers, "Response consumer list");
/* 335 */     Args.notNull(connPool, "HTTP connection pool");
/* 336 */     Args.notNull(poolEntry, "Pool entry");
/* 337 */     Args.notNull(context, "HTTP context");
/* 338 */     BasicFuture<List<T>> future = new BasicFuture(callback);
/* 339 */     NHttpClientConnection conn = (NHttpClientConnection)poolEntry.getConnection();
/* 340 */     PipeliningClientExchangeHandler<T> handler = new PipeliningClientExchangeHandler<T>(requestProducers, responseConsumers, new RequestExecutionCallback<List<T>, E>(future, poolEntry, connPool), context, conn, this.httpprocessor, this.connReuseStrategy);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     initExecution(handler, conn);
/* 346 */     return (Future<List<T>>)future;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, E extends PoolEntry<HttpHost, NHttpClientConnection>> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, ConnPool<HttpHost, E> connPool, HttpContext context) {
/* 365 */     return execute(requestProducer, responseConsumer, connPool, context, (FutureCallback<T>)null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T, E extends PoolEntry<HttpHost, NHttpClientConnection>> Future<T> execute(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, ConnPool<HttpHost, E> connPool) {
/* 382 */     return execute(requestProducer, responseConsumer, connPool, (HttpContext)new BasicHttpContext());
/*     */   }
/*     */ 
/*     */   
/*     */   class ConnRequestCallback<T, E extends PoolEntry<HttpHost, NHttpClientConnection>>
/*     */     implements FutureCallback<E>
/*     */   {
/*     */     private final BasicFuture<T> requestFuture;
/*     */     
/*     */     private final HttpAsyncRequestProducer requestProducer;
/*     */     
/*     */     private final HttpAsyncResponseConsumer<T> responseConsumer;
/*     */     
/*     */     private final ConnPool<HttpHost, E> connPool;
/*     */     
/*     */     private final HttpContext context;
/*     */     
/*     */     ConnRequestCallback(BasicFuture<T> requestFuture, HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, ConnPool<HttpHost, E> connPool, HttpContext context) {
/* 400 */       this.requestFuture = requestFuture;
/* 401 */       this.requestProducer = requestProducer;
/* 402 */       this.responseConsumer = responseConsumer;
/* 403 */       this.connPool = connPool;
/* 404 */       this.context = context;
/*     */     }
/*     */ 
/*     */     
/*     */     public void completed(E result) {
/* 409 */       if (this.requestFuture.isDone()) {
/* 410 */         this.connPool.release(result, true);
/*     */         return;
/*     */       } 
/* 413 */       NHttpClientConnection conn = (NHttpClientConnection)result.getConnection();
/* 414 */       BasicAsyncClientExchangeHandler<T> handler = new BasicAsyncClientExchangeHandler<T>(this.requestProducer, this.responseConsumer, new HttpAsyncRequester.RequestExecutionCallback<T, E>(this.requestFuture, result, this.connPool), this.context, conn, HttpAsyncRequester.this.httpprocessor, HttpAsyncRequester.this.connReuseStrategy);
/*     */ 
/*     */ 
/*     */       
/* 418 */       HttpAsyncRequester.this.initExecution(handler, conn);
/*     */     }
/*     */ 
/*     */     
/*     */     public void failed(Exception ex) {
/*     */       try {
/*     */         try {
/* 425 */           this.responseConsumer.failed(ex);
/*     */         } finally {
/* 427 */           releaseResources();
/*     */         } 
/*     */       } finally {
/* 430 */         this.requestFuture.failed(ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void cancelled() {
/*     */       try {
/*     */         try {
/* 438 */           this.responseConsumer.cancel();
/*     */         } finally {
/* 440 */           releaseResources();
/*     */         } 
/*     */       } finally {
/* 443 */         this.requestFuture.cancel(true);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void releaseResources() {
/* 448 */       HttpAsyncRequester.this.close(this.requestProducer);
/* 449 */       HttpAsyncRequester.this.close(this.responseConsumer);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class ConnPipelinedRequestCallback<T, E extends PoolEntry<HttpHost, NHttpClientConnection>>
/*     */     implements FutureCallback<E>
/*     */   {
/*     */     private final BasicFuture<List<T>> requestFuture;
/*     */     
/*     */     private final List<? extends HttpAsyncRequestProducer> requestProducers;
/*     */     
/*     */     private final List<? extends HttpAsyncResponseConsumer<T>> responseConsumers;
/*     */     
/*     */     private final ConnPool<HttpHost, E> connPool;
/*     */     
/*     */     private final HttpContext context;
/*     */ 
/*     */     
/*     */     ConnPipelinedRequestCallback(BasicFuture<List<T>> requestFuture, List<? extends HttpAsyncRequestProducer> requestProducers, List<? extends HttpAsyncResponseConsumer<T>> responseConsumers, ConnPool<HttpHost, E> connPool, HttpContext context) {
/* 469 */       this.requestFuture = requestFuture;
/* 470 */       this.requestProducers = requestProducers;
/* 471 */       this.responseConsumers = responseConsumers;
/* 472 */       this.connPool = connPool;
/* 473 */       this.context = context;
/*     */     }
/*     */ 
/*     */     
/*     */     public void completed(E result) {
/* 478 */       if (this.requestFuture.isDone()) {
/* 479 */         this.connPool.release(result, true);
/*     */         return;
/*     */       } 
/* 482 */       NHttpClientConnection conn = (NHttpClientConnection)result.getConnection();
/* 483 */       PipeliningClientExchangeHandler<T> handler = new PipeliningClientExchangeHandler<T>(this.requestProducers, this.responseConsumers, new HttpAsyncRequester.RequestExecutionCallback<List<T>, E>(this.requestFuture, result, this.connPool), this.context, conn, HttpAsyncRequester.this.httpprocessor, HttpAsyncRequester.this.connReuseStrategy);
/*     */ 
/*     */ 
/*     */       
/* 487 */       HttpAsyncRequester.this.initExecution(handler, conn);
/*     */     }
/*     */ 
/*     */     
/*     */     public void failed(Exception ex) {
/*     */       try {
/*     */         try {
/* 494 */           for (HttpAsyncResponseConsumer<T> responseConsumer : this.responseConsumers) {
/* 495 */             responseConsumer.failed(ex);
/*     */           }
/*     */         } finally {
/* 498 */           releaseResources();
/*     */         } 
/*     */       } finally {
/* 501 */         this.requestFuture.failed(ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void cancelled() {
/*     */       try {
/*     */         try {
/* 509 */           for (HttpAsyncResponseConsumer<T> responseConsumer : this.responseConsumers) {
/* 510 */             responseConsumer.cancel();
/*     */           }
/*     */         } finally {
/* 513 */           releaseResources();
/*     */         } 
/*     */       } finally {
/* 516 */         this.requestFuture.cancel(true);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void releaseResources() {
/* 521 */       for (HttpAsyncRequestProducer requestProducer : this.requestProducers) {
/* 522 */         HttpAsyncRequester.this.close(requestProducer);
/*     */       }
/* 524 */       for (HttpAsyncResponseConsumer<T> responseConsumer : this.responseConsumers) {
/* 525 */         HttpAsyncRequester.this.close(responseConsumer);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class RequestExecutionCallback<T, E extends PoolEntry<HttpHost, NHttpClientConnection>>
/*     */     implements FutureCallback<T>
/*     */   {
/*     */     private final BasicFuture<T> future;
/*     */     
/*     */     private final E poolEntry;
/*     */     
/*     */     private final ConnPool<HttpHost, E> connPool;
/*     */ 
/*     */     
/*     */     RequestExecutionCallback(BasicFuture<T> future, E poolEntry, ConnPool<HttpHost, E> connPool) {
/* 543 */       this.future = future;
/* 544 */       this.poolEntry = poolEntry;
/* 545 */       this.connPool = connPool;
/*     */     }
/*     */ 
/*     */     
/*     */     public void completed(T result) {
/*     */       try {
/* 551 */         this.connPool.release(this.poolEntry, true);
/*     */       } finally {
/* 553 */         this.future.completed(result);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void failed(Exception ex) {
/*     */       try {
/* 560 */         this.connPool.release(this.poolEntry, false);
/*     */       } finally {
/* 562 */         this.future.failed(ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void cancelled() {
/*     */       try {
/* 569 */         this.connPool.release(this.poolEntry, false);
/*     */       } finally {
/* 571 */         this.future.cancel(true);
/*     */       } 
/*     */     }
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
/*     */   protected void log(Exception ex) {
/* 585 */     this.exceptionLogger.log(ex);
/*     */   }
/*     */   
/*     */   private void close(Closeable closeable) {
/*     */     try {
/* 590 */       closeable.close();
/* 591 */     } catch (IOException ex) {
/* 592 */       log(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequester.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */