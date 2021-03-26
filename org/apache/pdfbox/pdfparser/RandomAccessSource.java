/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class RandomAccessSource
/*     */   implements SequentialSource
/*     */ {
/*     */   private final RandomAccessRead reader;
/*     */   
/*     */   RandomAccessSource(RandomAccessRead reader) {
/*  37 */     this.reader = reader;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  43 */     return this.reader.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  49 */     return this.reader.read(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int offset, int length) throws IOException {
/*  55 */     return this.reader.read(b, offset, length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() throws IOException {
/*  61 */     return this.reader.getPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int peek() throws IOException {
/*  67 */     return this.reader.peek();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unread(int b) throws IOException {
/*  73 */     this.reader.rewind(1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unread(byte[] bytes) throws IOException {
/*  79 */     this.reader.rewind(bytes.length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unread(byte[] bytes, int start, int len) throws IOException {
/*  85 */     this.reader.rewind(len - start);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readFully(int length) throws IOException {
/*  91 */     return this.reader.readFully(length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEOF() throws IOException {
/*  97 */     return this.reader.isEOF();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 103 */     this.reader.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/RandomAccessSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */