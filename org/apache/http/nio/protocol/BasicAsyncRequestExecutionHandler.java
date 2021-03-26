/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.Future;
/*     */ import org.apache.http.ConnectionReuseStrategy;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpHost;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.concurrent.BasicFuture;
/*     */ import org.apache.http.concurrent.FutureCallback;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
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
/*     */ @Deprecated
/*     */ public class BasicAsyncRequestExecutionHandler<T>
/*     */   implements HttpAsyncRequestExecutionHandler<T>
/*     */ {
/*     */   private final HttpAsyncRequestProducer requestProducer;
/*     */   private final HttpAsyncResponseConsumer<T> responseConsumer;
/*     */   private final BasicFuture<T> future;
/*     */   private final HttpContext localContext;
/*     */   private final HttpProcessor httpPocessor;
/*     */   private final ConnectionReuseStrategy reuseStrategy;
/*     */   private volatile boolean requestSent;
/*     */   
/*     */   public BasicAsyncRequestExecutionHandler(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, FutureCallback<T> callback, HttpContext localContext, HttpProcessor httpPocessor, ConnectionReuseStrategy reuseStrategy, HttpParams params) {
/*  78 */     Args.notNull(requestProducer, "Request producer");
/*  79 */     Args.notNull(responseConsumer, "Response consumer");
/*  80 */     Args.notNull(localContext, "HTTP context");
/*  81 */     Args.notNull(httpPocessor, "HTTP processor");
/*  82 */     Args.notNull(reuseStrategy, "Connection reuse strategy");
/*  83 */     Args.notNull(params, "HTTP parameters");
/*  84 */     this.requestProducer = requestProducer;
/*  85 */     this.responseConsumer = responseConsumer;
/*  86 */     this.future = new BasicFuture(callback);
/*  87 */     this.localContext = localContext;
/*  88 */     this.httpPocessor = httpPocessor;
/*  89 */     this.reuseStrategy = reuseStrategy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BasicAsyncRequestExecutionHandler(HttpAsyncRequestProducer requestProducer, HttpAsyncResponseConsumer<T> responseConsumer, HttpContext localContext, HttpProcessor httpPocessor, ConnectionReuseStrategy reuseStrategy, HttpParams params) {
/*  99 */     this(requestProducer, responseConsumer, null, localContext, httpPocessor, reuseStrategy, params);
/*     */   }
/*     */   
/*     */   public Future<T> getFuture() {
/* 103 */     return (Future<T>)this.future;
/*     */   }
/*     */   
/*     */   private void releaseResources() {
/*     */     try {
/* 108 */       this.responseConsumer.close();
/* 109 */     } catch (IOException iOException) {}
/*     */     
/*     */     try {
/* 112 */       this.requestProducer.close();
/* 113 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 119 */     releaseResources();
/* 120 */     if (!this.future.isDone()) {
/* 121 */       this.future.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpHost getTarget() {
/* 127 */     return this.requestProducer.getTarget();
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpRequest generateRequest() throws IOException, HttpException {
/* 132 */     return this.requestProducer.generateRequest();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 138 */     this.requestProducer.produceContent(encoder, ioControl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void requestCompleted(HttpContext context) {
/* 143 */     this.requestProducer.requestCompleted(context);
/* 144 */     this.requestSent = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetRequest() {}
/*     */ 
/*     */   
/*     */   public void responseReceived(HttpResponse response) throws IOException, HttpException {
/* 158 */     this.responseConsumer.responseReceived(response);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 164 */     this.responseConsumer.consumeContent(decoder, ioControl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void failed(Exception ex) {
/*     */     try {
/* 170 */       if (!this.requestSent) {
/* 171 */         this.requestProducer.failed(ex);
/*     */       }
/* 173 */       this.responseConsumer.failed(ex);
/*     */     } finally {
/*     */       try {
/* 176 */         this.future.failed(ex);
/*     */       } finally {
/* 178 */         releaseResources();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cancel() {
/*     */     try {
/* 186 */       boolean cancelled = this.responseConsumer.cancel();
/* 187 */       this.future.cancel();
/* 188 */       releaseResources();
/* 189 */       return cancelled;
/* 190 */     } catch (RuntimeException ex) {
/* 191 */       failed(ex);
/* 192 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void responseCompleted(HttpContext context) {
/*     */     try {
/* 199 */       this.responseConsumer.responseCompleted(context);
/* 200 */       T result = this.responseConsumer.getResult();
/* 201 */       Exception ex = this.responseConsumer.getException();
/* 202 */       if (ex == null) {
/* 203 */         this.future.completed(result);
/*     */       } else {
/* 205 */         this.future.failed(ex);
/*     */       } 
/* 207 */       releaseResources();
/* 208 */     } catch (RuntimeException ex) {
/* 209 */       failed(ex);
/* 210 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public T getResult() {
/* 216 */     return this.responseConsumer.getResult();
/*     */   }
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 221 */     return this.responseConsumer.getException();
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpContext getContext() {
/* 226 */     return this.localContext;
/*     */   }
/*     */ 
/*     */   
/*     */   public HttpProcessor getHttpProcessor() {
/* 231 */     return this.httpPocessor;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConnectionReuseStrategy getConnectionReuseStrategy() {
/* 236 */     return this.reuseStrategy;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 241 */     return this.responseConsumer.isDone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/BasicAsyncRequestExecutionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */