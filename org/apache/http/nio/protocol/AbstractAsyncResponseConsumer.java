/*     */ package org.apache.http.nio.protocol;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpException;
/*     */ import org.apache.http.HttpResponse;
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
/*     */ public abstract class AbstractAsyncResponseConsumer<T>
/*     */   implements HttpAsyncResponseConsumer<T>
/*     */ {
/*  56 */   private final AtomicBoolean completed = new AtomicBoolean(false);
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
/*     */   protected abstract void onResponseReceived(HttpResponse paramHttpResponse) throws HttpException, IOException;
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
/*     */   protected abstract void onEntityEnclosed(HttpEntity paramHttpEntity, ContentType paramContentType) throws IOException;
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
/*     */   protected void onClose() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ContentType getContentType(HttpEntity entity) {
/* 125 */     return (entity != null) ? ContentType.getOrDefault(entity) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void responseReceived(HttpResponse response) throws IOException, HttpException {
/* 134 */     onResponseReceived(response);
/* 135 */     HttpEntity entity = response.getEntity();
/* 136 */     if (entity != null) {
/* 137 */       onEntityEnclosed(entity, getContentType(entity));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 147 */     onContentReceived(decoder, ioControl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void responseCompleted(HttpContext context) {
/* 155 */     if (this.completed.compareAndSet(false, true)) {
/*     */       try {
/* 157 */         this.result = buildResult(context);
/* 158 */       } catch (Exception ex) {
/* 159 */         this.ex = ex;
/*     */       } finally {
/* 161 */         releaseResources();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean cancel() {
/* 168 */     if (this.completed.compareAndSet(false, true)) {
/* 169 */       releaseResources();
/* 170 */       return true;
/*     */     } 
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void failed(Exception ex) {
/* 177 */     if (this.completed.compareAndSet(false, true)) {
/* 178 */       this.ex = ex;
/* 179 */       releaseResources();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void close() throws IOException {
/* 185 */     if (this.completed.compareAndSet(false, true)) {
/* 186 */       releaseResources();
/* 187 */       onClose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 193 */     return this.ex;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getResult() {
/* 198 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDone() {
/* 203 */     return this.completed.get();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/AbstractAsyncResponseConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */