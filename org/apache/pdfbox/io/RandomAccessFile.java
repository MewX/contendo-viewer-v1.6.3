/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomAccessFile
/*     */   implements RandomAccess
/*     */ {
/*     */   private final java.io.RandomAccessFile ras;
/*     */   private boolean isClosed;
/*     */   
/*     */   public RandomAccessFile(File file, String mode) throws FileNotFoundException {
/*  43 */     this.ras = new java.io.RandomAccessFile(file, mode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  49 */     this.ras.close();
/*  50 */     this.isClosed = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() throws IOException {
/*  56 */     checkClosed();
/*  57 */     this.ras.seek(0L);
/*  58 */     this.ras.setLength(0L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long position) throws IOException {
/*  64 */     checkClosed();
/*  65 */     this.ras.seek(position);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() throws IOException {
/*  71 */     checkClosed();
/*  72 */     return this.ras.getFilePointer();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  78 */     checkClosed();
/*  79 */     return this.ras.read();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  85 */     checkClosed();
/*  86 */     return this.ras.read(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int offset, int length) throws IOException {
/*  92 */     checkClosed();
/*  93 */     return this.ras.read(b, offset, length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long length() throws IOException {
/*  99 */     checkClosed();
/* 100 */     return this.ras.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkClosed() throws IOException {
/* 110 */     if (this.isClosed)
/*     */     {
/* 112 */       throw new IOException("RandomAccessFile already closed");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 120 */     return this.isClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int offset, int length) throws IOException {
/* 126 */     checkClosed();
/* 127 */     this.ras.write(b, offset, length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 133 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 139 */     checkClosed();
/* 140 */     this.ras.write(b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int peek() throws IOException {
/* 146 */     int result = read();
/* 147 */     if (result != -1)
/*     */     {
/* 149 */       rewind(1);
/*     */     }
/* 151 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void rewind(int bytes) throws IOException {
/* 157 */     checkClosed();
/* 158 */     this.ras.seek(this.ras.getFilePointer() - bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readFully(int length) throws IOException {
/* 164 */     checkClosed();
/* 165 */     byte[] b = new byte[length];
/* 166 */     this.ras.readFully(b);
/* 167 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEOF() throws IOException {
/* 173 */     return (peek() == -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 179 */     checkClosed();
/* 180 */     return (int)Math.min(this.ras.length() - getPosition(), 2147483647L);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */