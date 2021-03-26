/*    */ package org.apache.xmlgraphics.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.Writer;
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
/*    */ public class WriterOutputStream
/*    */   extends OutputStream
/*    */ {
/*    */   private Writer writer;
/*    */   private String encoding;
/*    */   
/*    */   public WriterOutputStream(Writer writer) {
/* 39 */     this(writer, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WriterOutputStream(Writer writer, String encoding) {
/* 48 */     this.writer = writer;
/* 49 */     this.encoding = encoding;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 56 */     this.writer.close();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {
/* 63 */     this.writer.flush();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] buf, int offset, int length) throws IOException {
/* 70 */     if (this.encoding != null) {
/* 71 */       this.writer.write(new String(buf, offset, length, this.encoding));
/*    */     } else {
/* 73 */       this.writer.write(new String(buf, offset, length, "UTF-8"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] buf) throws IOException {
/* 81 */     write(buf, 0, buf.length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(int b) throws IOException {
/* 88 */     write(new byte[] { (byte)b });
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/WriterOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */