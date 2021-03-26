/*    */ package org.apache.http.nio.entity;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.http.io.BufferInfo;
/*    */ import org.apache.http.nio.util.ContentInputBuffer;
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
/*    */ public class ContentInputStream
/*    */   extends InputStream
/*    */ {
/*    */   private final ContentInputBuffer buffer;
/*    */   
/*    */   public ContentInputStream(ContentInputBuffer buffer) {
/* 48 */     Args.notNull(buffer, "Input buffer");
/* 49 */     this.buffer = buffer;
/*    */   }
/*    */ 
/*    */   
/*    */   public int available() throws IOException {
/* 54 */     return (this.buffer instanceof BufferInfo) ? ((BufferInfo)this.buffer).length() : super.available();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 59 */     return this.buffer.read(b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b) throws IOException {
/* 64 */     if (b == null) {
/* 65 */       return 0;
/*    */     }
/* 67 */     return this.buffer.read(b, 0, b.length);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 72 */     return this.buffer.read();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 78 */     byte[] tmp = new byte[1024];
/* 79 */     while (this.buffer.read(tmp, 0, tmp.length) >= 0);
/*    */     
/* 81 */     super.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/ContentInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */