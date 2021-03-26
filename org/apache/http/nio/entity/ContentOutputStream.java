/*    */ package org.apache.http.nio.entity;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.http.nio.util.ContentOutputBuffer;
/*    */ import org.apache.http.util.Args;
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
/*    */ public class ContentOutputStream
/*    */   extends OutputStream
/*    */ {
/*    */   private final ContentOutputBuffer buffer;
/*    */   
/*    */   public ContentOutputStream(ContentOutputBuffer buffer) {
/* 47 */     Args.notNull(buffer, "Output buffer");
/* 48 */     this.buffer = buffer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 53 */     this.buffer.writeCompleted();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {}
/*    */ 
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 62 */     this.buffer.write(b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 67 */     if (b == null) {
/*    */       return;
/*    */     }
/* 70 */     this.buffer.write(b, 0, b.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(int b) throws IOException {
/* 75 */     this.buffer.write(b);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/ContentOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */