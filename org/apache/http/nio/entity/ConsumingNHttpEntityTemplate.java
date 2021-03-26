/*    */ package org.apache.http.nio.entity;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.entity.HttpEntityWrapper;
/*    */ import org.apache.http.nio.ContentDecoder;
/*    */ import org.apache.http.nio.IOControl;
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
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class ConsumingNHttpEntityTemplate
/*    */   extends HttpEntityWrapper
/*    */   implements ConsumingNHttpEntity
/*    */ {
/*    */   private final ContentListener contentListener;
/*    */   
/*    */   public ConsumingNHttpEntityTemplate(HttpEntity httpEntity, ContentListener contentListener) {
/* 58 */     super(httpEntity);
/* 59 */     this.contentListener = contentListener;
/*    */   }
/*    */   
/*    */   public ContentListener getContentListener() {
/* 63 */     return this.contentListener;
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getContent() throws IOException, UnsupportedOperationException {
/* 68 */     throw new UnsupportedOperationException("Does not support blocking methods");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStreaming() {
/* 73 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeTo(OutputStream out) throws IOException, UnsupportedOperationException {
/* 78 */     throw new UnsupportedOperationException("Does not support blocking methods");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 85 */     this.contentListener.contentAvailable(decoder, ioControl);
/*    */   }
/*    */ 
/*    */   
/*    */   public void finish() {
/* 90 */     this.contentListener.finished();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/ConsumingNHttpEntityTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */