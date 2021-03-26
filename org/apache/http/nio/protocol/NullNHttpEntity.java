/*    */ package org.apache.http.nio.protocol;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.entity.HttpEntityWrapper;
/*    */ import org.apache.http.nio.ContentDecoder;
/*    */ import org.apache.http.nio.IOControl;
/*    */ import org.apache.http.nio.entity.ConsumingNHttpEntity;
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
/*    */ class NullNHttpEntity
/*    */   extends HttpEntityWrapper
/*    */   implements ConsumingNHttpEntity
/*    */ {
/*    */   private final ByteBuffer buffer;
/*    */   
/*    */   public NullNHttpEntity(HttpEntity httpEntity) {
/* 50 */     super(httpEntity);
/* 51 */     this.buffer = ByteBuffer.allocate(2048);
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getContent() throws IOException, UnsupportedOperationException {
/* 56 */     throw new UnsupportedOperationException("Does not support blocking methods");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStreaming() {
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeTo(OutputStream out) throws IOException, UnsupportedOperationException {
/* 66 */     throw new UnsupportedOperationException("Does not support blocking methods");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/*    */     int lastRead;
/*    */     do {
/* 75 */       this.buffer.clear();
/* 76 */       lastRead = decoder.read(this.buffer);
/* 77 */     } while (lastRead > 0);
/*    */   }
/*    */   
/*    */   public void finish() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/NullNHttpEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */