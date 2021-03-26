/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicAsyncClientExchangeHandler<T>
/*     */   implements HttpAsyncClientExchangeHandler
/*     */ {
/*     */   private final HttpAsyncRequestProducer requestProducer;
/*     */   private final HttpAsyncResponseConsumer<T> responseConsumer;
/*     */   private final BasicFuture<T> future;
/*     */   private final HttpContext localContext;
/*     */   private final NHttpClientConnection conn;
/*     */   private final HttpProcessor httpPocessor;
/*     */   private final ConnectionReuseStrategy connReuseStrategy;
/*     */   private final AtomicBoolean requestSent;
/*     */   private final AtomicBoolean keepAlive;
/*     */   private final AtomicBoolean closed;
/*     */   
/*     */   public BasicAsyncClientExchangeHandler(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, FutureCallback<T> callback, HttpContext localContext, NHttpClientConnection conn, HttpProcessor httpPocessor, ConnectionReuseStrategy connReuseStrategy) {
/*  91 */     this.requestProducer = (HttpAsyncRequestProducer)Args.notNull(requestProducer, "Request producer");
/*  92 */     this.responseConsumer = (HttpAsyncResponseConsumer<T>)Args.notNull(responseConsumer, "Response consumer");
/*  93 */     this.future = new BasicFuture(callback);
/*  94 */     this.localContext = (HttpContext)Args.notNull(localContext, "HTTP context");
/*  95 */     this.conn = (NHttpClientConnection)Args.notNull(conn, "HTTP connection");
/*  96 */     this.httpPocessor = (HttpProcessor)Args.notNull(httpPocessor, "HTTP processor");
/*  97 */     this.connReuseStrategy = (connReuseStrategy != null) ? connReuseStrategy : (ConnectionReuseStrategy)DefaultConnectionReuseStrategy.INSTANCE;
/*     */     
/*  99 */     this.requestSent = new AtomicBoolean(false);
/* 100 */     this.keepAlive = new AtomicBoolean(false);
/* 101 */     this.closed = new AtomicBoolean(false);
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
/*     */   public BasicAsyncClientExchangeHandler(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, HttpContext localContext, NHttpClientConnection conn, HttpProcessor httpPocessor) {
/* 119 */     this(requestProducer, responseConsumer, null, localContext, conn, httpPocessor, null);
/*     */   }
/*     */   
/*     */   public Future<T> getFuture() {
/* 123 */     return (Future<T>)this.future;
/*     */   }
/*     */   
/*     */   private void releaseResources() {
/*     */     try {
/* 128 */       this.responseConsumer.close();
/* 129 */     } catch (IOException iOException) {}
/*     */     
/*     */     try {
/* 132 */       this.requestProducer.close();
/* 133 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 139 */     if (this.closed.compareAndSet(false, true)) {
/* 140 */       releaseResources();
/* 141 */       if (!this.future.isDone()) {
/* 142 */         this.future.cancel();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpRequest generateRequest() throws IOException, HttpException {
/* 149 */     if (isDone()) {
/* 150 */       return null;
/*     */     }
/* 152 */     HttpRequest request = this.requestProducer.generateRequest();
/* 153 */     this.localContext.setAttribute("http.request", request);
/* 154 */     this.localContext.setAttribute("http.connection", this.conn);
/* 155 */     this.httpPocessor.process(request, this.localContext);
/* 156 */     return request;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 162 */     this.requestProducer.produceContent(encoder, ioControl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestCompleted() {
/* 167 */     this.requestProducer.requestCompleted(this.localContext);
/* 168 */     this.requestSent.set(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseReceived(HttpResponse response) throws IOException, HttpException {
/* 173 */     this.localContext.setAttribute("http.response", response);
/* 174 */     this.httpPocessor.process(response, this.localContext);
/* 175 */     this.responseConsumer.responseReceived(response);
/* 176 */     this.keepAlive.set(this.connReuseStrategy.keepAlive(response, this.localContext));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 182 */     this.responseConsumer.consumeContent(decoder, ioControl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseCompleted() throws IOException {
/*     */     try {
/* 188 */       if (!this.keepAlive.get()) {
/* 189 */         this.conn.close();
/*     */       }
/* 191 */       this.responseConsumer.responseCompleted(this.localContext);
/* 192 */       T result = this.responseConsumer.getResult();
/* 193 */       Exception ex = this.responseConsumer.getException();
/* 194 */       if (result != null) {
/* 195 */         this.future.completed(result);
/*     */       } else {
/* 197 */         this.future.failed(ex);
/*     */       } 
/* 199 */       if (this.closed.compareAndSet(false, true)) {
/* 200 */         releaseResources();
/*     */       }
/* 202 */     } catch (RuntimeException ex) {
/* 203 */       failed(ex);
/* 204 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void inputTerminated() {
/* 210 */     failed((Exception)new ConnectionClosedException());
/*     */   }
/*     */ 
/*     */   
/*     */   public void failed(Exception ex) {
/* 215 */     if (this.closed.compareAndSet(false, true)) {
/*     */       try {
/* 217 */         if (!this.requestSent.get()) {
/* 218 */           this.requestProducer.failed(ex);
/*     */         }
/* 220 */         this.responseConsumer.failed(ex);
/*     */       } finally {
/*     */         try {
/* 223 */           this.future.failed(ex);
/*     */         } finally {
/* 225 */           releaseResources();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cancel() {
/* 233 */     if (this.closed.compareAndSet(false, true)) {
/*     */       try {
/*     */         try {
/* 236 */           return this.responseConsumer.cancel();
/*     */         } finally {
/* 238 */           this.future.cancel();
/*     */         } 
/*     */       } finally {
/* 241 */         releaseResources();
/*     */       } 
/*     */     }
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 249 */     return this.responseConsumer.isDone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BasicAsyncClientExchangeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */