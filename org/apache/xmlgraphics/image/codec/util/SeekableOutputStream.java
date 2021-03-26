/*    */ package org.apache.xmlgraphics.image.codec.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.RandomAccessFile;
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
/*    */ public class SeekableOutputStream
/*    */   extends OutputStream
/*    */ {
/*    */   private RandomAccessFile file;
/*    */   
/*    */   public SeekableOutputStream(RandomAccessFile file) {
/* 47 */     if (file == null) {
/* 48 */       throw new IllegalArgumentException("SeekableOutputStream0");
/*    */     }
/* 50 */     this.file = file;
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 54 */     this.file.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 58 */     this.file.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 62 */     this.file.write(b, off, len);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {
/* 70 */     this.file.getFD().sync();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 74 */     this.file.close();
/*    */   }
/*    */   
/*    */   public long getFilePointer() throws IOException {
/* 78 */     return this.file.getFilePointer();
/*    */   }
/*    */   
/*    */   public void seek(long pos) throws IOException {
/* 82 */     this.file.seek(pos);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/SeekableOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */