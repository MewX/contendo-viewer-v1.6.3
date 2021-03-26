/*    */ package org.apache.http.nio;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.ReadableByteChannel;
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
/*    */ public class ContentDecoderChannel
/*    */   implements ReadableByteChannel
/*    */ {
/*    */   private final ContentDecoder decoder;
/*    */   
/*    */   public ContentDecoderChannel(ContentDecoder decoder) {
/* 46 */     this.decoder = decoder;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(ByteBuffer dst) throws IOException {
/* 51 */     return this.decoder.read(dst);
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/ContentDecoderChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */