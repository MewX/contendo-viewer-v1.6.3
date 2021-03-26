/*    */ package org.apache.pdfbox.io;
/*    */ 
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
/*    */ 
/*    */ public class RandomAccessOutputStream
/*    */   extends OutputStream
/*    */ {
/*    */   private final RandomAccessWrite writer;
/*    */   
/*    */   public RandomAccessOutputStream(RandomAccessWrite writer) {
/* 39 */     this.writer = writer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] b, int offset, int length) throws IOException {
/* 46 */     this.writer.write(b, offset, length);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 52 */     this.writer.write(b);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(int b) throws IOException {
/* 58 */     this.writer.write(b);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */