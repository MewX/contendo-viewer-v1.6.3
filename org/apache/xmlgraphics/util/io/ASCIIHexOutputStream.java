/*    */ package org.apache.xmlgraphics.util.io;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ public class ASCIIHexOutputStream
/*    */   extends FilterOutputStream
/*    */   implements Finalizable
/*    */ {
/*    */   private static final int EOL = 10;
/*    */   private static final int EOD = 62;
/*    */   private static final int ZERO = 48;
/*    */   private static final int NINE = 57;
/*    */   private static final int A = 65;
/*    */   private static final int ADIFF = 7;
/*    */   private int posinline;
/*    */   
/*    */   public ASCIIHexOutputStream(OutputStream out) {
/* 46 */     super(out);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(int b) throws IOException {
/* 52 */     b &= 0xFF;
/*    */     
/* 54 */     int digit1 = ((b & 0xF0) >> 4) + 48;
/* 55 */     if (digit1 > 57) {
/* 56 */       digit1 += 7;
/*    */     }
/* 58 */     this.out.write(digit1);
/*    */     
/* 60 */     int digit2 = (b & 0xF) + 48;
/* 61 */     if (digit2 > 57) {
/* 62 */       digit2 += 7;
/*    */     }
/* 64 */     this.out.write(digit2);
/*    */     
/* 66 */     this.posinline++;
/* 67 */     checkLineWrap();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void checkLineWrap() throws IOException {
/* 73 */     if (this.posinline >= 40) {
/* 74 */       this.out.write(10);
/* 75 */       this.posinline = 0;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void finalizeStream() throws IOException {
/* 82 */     checkLineWrap();
/*    */     
/* 84 */     super.write(62);
/*    */     
/* 86 */     flush();
/* 87 */     if (this.out instanceof Finalizable) {
/* 88 */       ((Finalizable)this.out).finalizeStream();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 95 */     finalizeStream();
/* 96 */     super.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/ASCIIHexOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */