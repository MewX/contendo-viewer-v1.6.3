/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpEntityEnclosingRequest;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpRequest;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractAsyncRequestConsumer<T>
/*     */   implements HttpAsyncRequestConsumer<T>
/*     */ {
/*  57 */   private final AtomicBoolean completed = new AtomicBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile T result;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile Exception ex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void onRequestReceived(HttpRequest paramHttpRequest) throws HttpException, IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void onEntityEnclosed(HttpEntity paramHttpEntity, ContentType paramContentType) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void onContentReceived(ContentDecoder paramContentDecoder, IOControl paramIOControl) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract T buildResult(HttpContext paramHttpContext) throws Exception;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void releaseResources();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onClose() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void requestReceived(HttpRequest request) throws HttpException, IOException {
/* 129 */     onRequestReceived(request);
/* 130 */     if (request instanceof HttpEntityEnclosingRequest) {
/* 131 */       HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
/* 132 */       if (entity != null) {
/* 133 */         ContentType contentType = ContentType.getOrDefault(entity);
/* 134 */         onEntityEnclosed(entity, contentType);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 145 */     onContentReceived(decoder, ioControl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void requestCompleted(HttpContext context) {
/* 153 */     if (this.completed.compareAndSet(false, true)) {
/*     */       try {
/* 155 */         this.result = buildResult(context);
/* 156 */       } catch (Exception ex) {
/* 157 */         this.ex = ex;
/*     */       } finally {
/* 159 */         releaseResources();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final void failed(Exception ex) {
/* 166 */     if (this.completed.compareAndSet(false, true)) {
/* 167 */       this.ex = ex;
/* 168 */       releaseResources();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void close() throws IOException {
/* 174 */     if (this.completed.compareAndSet(false, true)) {
/* 175 */       releaseResources();
/* 176 */       onClose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 182 */     return this.ex;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getResult() {
/* 187 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 192 */     return this.completed.get();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/AbstractAsyncRequestConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */