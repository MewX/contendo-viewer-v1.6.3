/*    */ package org.apache.batik.util.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ public class ISO_8859_1Decoder
/*    */   extends AbstractCharDecoder
/*    */ {
/*    */   public ISO_8859_1Decoder(InputStream is) {
/* 37 */     super(is);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readChar() throws IOException {
/* 45 */     if (this.position == this.count) {
/* 46 */       fillBuffer();
/*    */     }
/* 48 */     if (this.count == -1) {
/* 49 */       return -1;
/*    */     }
/* 51 */     return this.buffer[this.position++] & 0xFF;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/ISO_8859_1Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */