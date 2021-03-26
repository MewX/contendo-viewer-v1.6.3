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
/*    */ public class ASCIIDecoder
/*    */   extends AbstractCharDecoder
/*    */ {
/*    */   public ASCIIDecoder(InputStream is) {
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
/* 51 */     int result = this.buffer[this.position++];
/* 52 */     if (result < 0) {
/* 53 */       charError("ASCII");
/*    */     }
/* 55 */     return result;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/ASCIIDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */