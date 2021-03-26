/*    */ package org.apache.http.nio.protocol;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.apache.http.HttpRequest;
/*    */ import org.apache.http.nio.ContentDecoder;
/*    */ import org.apache.http.nio.IOControl;
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
/*    */ class NullRequestConsumer
/*    */   implements HttpAsyncRequestConsumer<Object>
/*    */ {
/* 45 */   private final ByteBuffer buffer = ByteBuffer.allocate(2048);
/*    */ 
/*    */   
/*    */   private volatile boolean completed;
/*    */ 
/*    */   
/*    */   public void requestReceived(HttpRequest request) {}
/*    */ 
/*    */   
/*    */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/*    */     int lastRead;
/*    */     do {
/* 57 */       this.buffer.clear();
/* 58 */       lastRead = decoder.read(this.buffer);
/* 59 */     } while (lastRead > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void requestCompleted(HttpContext context) {
/* 64 */     this.completed = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void failed(Exception ex) {
/* 69 */     this.completed = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getResult() {
/* 74 */     return Boolean.valueOf(this.completed);
/*    */   }
/*    */ 
/*    */   
/*    */   public Exception getException() {
/* 79 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 84 */     this.completed = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDone() {
/* 89 */     return this.completed;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/NullRequestConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */