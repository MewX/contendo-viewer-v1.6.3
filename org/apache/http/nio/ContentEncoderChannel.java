/*    */ package org.apache.http.nio;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.WritableByteChannel;
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
/*    */ public class ContentEncoderChannel
/*    */   implements WritableByteChannel
/*    */ {
/*    */   private final ContentEncoder contentEncoder;
/*    */   
/*    */   public ContentEncoderChannel(ContentEncoder contentEncoder) {
/* 46 */     this.contentEncoder = contentEncoder;
/*    */   }
/*    */ 
/*    */   
/*    */   public int write(ByteBuffer src) throws IOException {
/* 51 */     return this.contentEncoder.write(src);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {}
/*    */ 
/*    */   
/*    */   public boolean isOpen() {
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/ContentEncoderChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */