/*    */ package org.apache.http.nio.protocol;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.HttpResponse;
/*    */ import org.apache.http.nio.ContentEncoder;
/*    */ import org.apache.http.nio.IOControl;
/*    */ import org.apache.http.nio.entity.EntityAsyncContentProducer;
/*    */ import org.apache.http.nio.entity.HttpAsyncContentProducer;
/*    */ import org.apache.http.protocol.HttpContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ErrorResponseProducer
/*    */   implements HttpAsyncResponseProducer
/*    */ {
/*    */   private final HttpResponse response;
/*    */   private final HttpEntity entity;
/*    */   private final HttpAsyncContentProducer contentProducer;
/*    */   private final boolean keepAlive;
/*    */   
/*    */   public ErrorResponseProducer(HttpResponse response, HttpEntity entity, boolean keepAlive) {
/* 58 */     this.response = response;
/* 59 */     this.entity = entity;
/* 60 */     if (entity instanceof HttpAsyncContentProducer) {
/* 61 */       this.contentProducer = (HttpAsyncContentProducer)entity;
/*    */     } else {
/* 63 */       this.contentProducer = (HttpAsyncContentProducer)new EntityAsyncContentProducer(entity);
/*    */     } 
/* 65 */     this.keepAlive = keepAlive;
/*    */   }
/*    */ 
/*    */   
/*    */   public HttpResponse generateResponse() {
/* 70 */     if (this.keepAlive) {
/* 71 */       this.response.addHeader("Connection", "Keep-Alive");
/*    */     } else {
/* 73 */       this.response.addHeader("Connection", "Close");
/*    */     } 
/* 75 */     this.response.setEntity(this.entity);
/* 76 */     return this.response;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 82 */     this.contentProducer.produceContent(encoder, ioControl);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void responseCompleted(HttpContext context) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void failed(Exception ex) {}
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 95 */     this.contentProducer.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/ErrorResponseProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */