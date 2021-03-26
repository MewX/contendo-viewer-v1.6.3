/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import org.apache.http.ConnectionClosedException;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.concurrent.BasicFuture;
/*     */ import org.apache.http.concurrent.FutureCallback;
/*     */ import org.apache.http.impl.DefaultConnectionReuseStrategy;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.NHttpClientConnection;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.protocol.HttpProcessor;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Pipelined
/*     */ public class PipeliningClientExchangeHandler<T>
/*     */   implements HttpAsyncClientExchangeHandler
/*     */ {
/*     */   private final Queue<HttpAsyncRequestProducer> requestProducerQueue;
/*     */   private final Queue<HttpAsyncResponseConsumer<T>> responseConsumerQueue;
/*     */   private final Queue<HttpRequest> requestQueue;
/*     */   private final Queue<T> resultQueue;
/*     */   private final BasicFuture<List<T>> future;
/*     */   private final HttpContext localContext;
/*     */   private final NHttpClientConnection conn;
/*     */   private final HttpProcessor httpPocessor;
/*     */   private final ConnectionReuseStrategy connReuseStrategy;
/*     */   private final AtomicReference<HttpAsyncRequestProducer> requestProducerRef;
/*     */   private final AtomicReference<HttpAsyncResponseConsumer<T>> responseConsumerRef;
/*     */   private final AtomicBoolean keepAlive;
/*     */   private final AtomicBoolean closed;
/*     */   
/*     */   public PipeliningClientExchangeHandler(List<? extends HttpAsyncRequestProducer> requestProducers, List<? extends HttpAsyncResponseConsumer<T>> responseConsumers, FutureCallback<List<T>> callback, HttpContext localContext, NHttpClientConnection conn, HttpProcessor httpPocessor, ConnectionReuseStrategy connReuseStrategy) {
/* 103 */     Args.notEmpty(requestProducers, "Request producer list");
/* 104 */     Args.notEmpty(responseConsumers, "Response consumer list");
/* 105 */     Args.check((requestProducers.size() == responseConsumers.size()), "Number of request producers does not match that of response consumers");
/*     */     
/* 107 */     this.requestProducerQueue = new ConcurrentLinkedQueue<HttpAsyncRequestProducer>(requestProducers);
/* 108 */     this.responseConsumerQueue = new ConcurrentLinkedQueue<HttpAsyncResponseConsumer<T>>(responseConsumers);
/* 109 */     this.requestQueue = new ConcurrentLinkedQueue<HttpRequest>();
/* 110 */     this.resultQueue = new ConcurrentLinkedQueue<T>();
/* 111 */     this.future = new BasicFuture(callback);
/* 112 */     this.localContext = (HttpContext)Args.notNull(localContext, "HTTP context");
/* 113 */     this.conn = (NHttpClientConnection)Args.notNull(conn, "HTTP connection");
/* 114 */     this.httpPocessor = (HttpProcessor)Args.notNull(httpPocessor, "HTTP processor");
/* 115 */     this.connReuseStrategy = (connReuseStrategy != null) ? connReuseStrategy : (ConnectionReuseStrategy)DefaultConnectionReuseStrategy.INSTANCE;
/*     */     
/* 117 */     this.localContext.setAttribute("http.connection", this.conn);
/* 118 */     this.requestProducerRef = new AtomicReference<HttpAsyncRequestProducer>(null);
/* 119 */     this.responseConsumerRef = new AtomicReference<HttpAsyncResponseConsumer<T>>(null);
/* 120 */     this.keepAlive = new AtomicBoolean(false);
/* 121 */     this.closed = new AtomicBoolean(false);
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
/*     */   public PipeliningClientExchangeHandler(List<? extends HttpAsyncRequestProducer> requestProducers, List<? extends HttpAsyncResponseConsumer<T>> responseConsumers, HttpContext localContext, NHttpClientConnection conn, HttpProcessor httpPocessor) {
/* 139 */     this(requestProducers, responseConsumers, null, localContext, conn, httpPocessor, null);
/*     */   }
/*     */   
/*     */   public Future<List<T>> getFuture() {
/* 143 */     return (Future<List<T>>)this.future;
/*     */   }
/*     */   
/*     */   private static void closeQuietly(Closeable closeable) {
/* 147 */     if (closeable != null) {
/*     */       try {
/* 149 */         closeable.close();
/* 150 */       } catch (IOException iOException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void releaseResources() {
/* 156 */     closeQuietly(this.requestProducerRef.getAndSet(null));
/* 157 */     closeQuietly(this.responseConsumerRef.getAndSet(null));
/* 158 */     while (!this.requestProducerQueue.isEmpty()) {
/* 159 */       closeQuietly(this.requestProducerQueue.remove());
/*     */     }
/* 161 */     while (!this.responseConsumerQueue.isEmpty()) {
/* 162 */       closeQuietly(this.responseConsumerQueue.remove());
/*     */     }
/* 164 */     this.requestQueue.clear();
/* 165 */     this.resultQueue.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 170 */     if (this.closed.compareAndSet(false, true)) {
/* 171 */       releaseResources();
/* 172 */       if (!this.future.isDone()) {
/* 173 */         this.future.cancel();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpRequest generateRequest() throws IOException, HttpException {
/* 180 */     Asserts.check((this.requestProducerRef.get() == null), "Inconsistent state: request producer is not null");
/* 181 */     HttpAsyncRequestProducer requestProducer = this.requestProducerQueue.poll();
/* 182 */     if (requestProducer == null) {
/* 183 */       return null;
/*     */     }
/* 185 */     this.requestProducerRef.set(requestProducer);
/* 186 */     HttpRequest request = requestProducer.generateRequest();
/* 187 */     this.httpPocessor.process(request, this.localContext);
/* 188 */     this.requestQueue.add(request);
/* 189 */     return request;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 195 */     HttpAsyncRequestProducer requestProducer = this.requestProducerRef.get();
/* 196 */     Asserts.check((requestProducer != null), "Inconsistent state: request producer is null");
/* 197 */     requestProducer.produceContent(encoder, ioControl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestCompleted() {
/* 202 */     HttpAsyncRequestProducer requestProducer = this.requestProducerRef.getAndSet(null);
/* 203 */     Asserts.check((requestProducer != null), "Inconsistent state: request producer is null");
/* 204 */     requestProducer.requestCompleted(this.localContext);
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReceived(HttpResponse response) throws IOException, HttpException {
/* 209 */     Asserts.check((this.responseConsumerRef.get() == null), "Inconsistent state: response consumer is not null");
/*     */     
/* 211 */     HttpAsyncResponseConsumer<T> responseConsumer = this.responseConsumerQueue.poll();
/* 212 */     Asserts.check((responseConsumer != null), "Inconsistent state: response consumer queue is empty");
/* 213 */     this.responseConsumerRef.set(responseConsumer);
/*     */     
/* 215 */     HttpRequest request = this.requestQueue.poll();
/* 216 */     Asserts.check((request != null), "Inconsistent state: request queue is empty");
/*     */     
/* 218 */     this.localContext.setAttribute("http.request", request);
/* 219 */     this.localContext.setAttribute("http.response", response);
/* 220 */     this.httpPocessor.process(response, this.localContext);
/*     */     
/* 222 */     responseConsumer.responseReceived(response);
/* 223 */     this.keepAlive.set(this.connReuseStrategy.keepAlive(response, this.localContext));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 229 */     HttpAsyncResponseConsumer<T> responseConsumer = this.responseConsumerRef.get();
/* 230 */     Asserts.check((responseConsumer != null), "Inconsistent state: response consumer is null");
/* 231 */     responseConsumer.consumeContent(decoder, ioControl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseCompleted() throws IOException {
/* 236 */     HttpAsyncResponseConsumer<T> responseConsumer = this.responseConsumerRef.getAndSet(null);
/* 237 */     Asserts.check((responseConsumer != null), "Inconsistent state: response consumer is null");
/*     */     try {
/* 239 */       if (!this.keepAlive.get()) {
/* 240 */         this.conn.close();
/*     */       }
/* 242 */       responseConsumer.responseCompleted(this.localContext);
/* 243 */       T result = responseConsumer.getResult();
/* 244 */       Exception ex = responseConsumer.getException();
/* 245 */       if (result != null) {
/* 246 */         this.resultQueue.add(result);
/*     */       } else {
/* 248 */         this.future.failed(ex);
/* 249 */         this.conn.shutdown();
/*     */       } 
/* 251 */       if (!this.conn.isOpen() && 
/* 252 */         this.closed.compareAndSet(false, true)) {
/* 253 */         releaseResources();
/*     */       }
/*     */       
/* 256 */       if (!this.future.isDone() && this.responseConsumerQueue.isEmpty()) {
/* 257 */         this.future.completed(new ArrayList<T>(this.resultQueue));
/* 258 */         this.resultQueue.clear();
/*     */       } 
/* 260 */     } catch (RuntimeException ex) {
/* 261 */       failed(ex);
/* 262 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputTerminated() {
/* 268 */     failed((Exception)new ConnectionClosedException());
/*     */   }
/*     */ 
/*     */   
/*     */   public void failed(Exception ex) {
/* 273 */     if (this.closed.compareAndSet(false, true)) {
/*     */       try {
/* 275 */         HttpAsyncRequestProducer requestProducer = this.requestProducerRef.get();
/* 276 */         if (requestProducer != null) {
/* 277 */           requestProducer.failed(ex);
/*     */         }
/* 279 */         HttpAsyncResponseConsumer<T> responseConsumer = this.responseConsumerRef.get();
/* 280 */         if (responseConsumer != null) {
/* 281 */           responseConsumer.failed(ex);
/*     */         }
/*     */       } finally {
/*     */         try {
/* 285 */           this.future.failed(ex);
/*     */         } finally {
/* 287 */           releaseResources();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cancel() {
/* 295 */     if (this.closed.compareAndSet(false, true)) {
/*     */       try {
/*     */         try {
/* 298 */           HttpAsyncResponseConsumer<T> responseConsumer = this.responseConsumerRef.get();
/* 299 */           return (responseConsumer != null && responseConsumer.cancel());
/*     */         } finally {
/* 301 */           this.future.cancel();
/*     */         } 
/*     */       } finally {
/* 304 */         releaseResources();
/*     */       } 
/*     */     }
/* 307 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 312 */     return this.future.isDone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/PipeliningClientExchangeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */