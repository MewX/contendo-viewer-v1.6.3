/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
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
/*     */ public class BufferedRandomAccessFile
/*     */   extends RandomAccessFile
/*     */ {
/*     */   private final byte[] buffer;
/*  40 */   private int bufend = 0;
/*  41 */   private int bufpos = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private long realpos = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int BUFSIZE;
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
/*     */   public BufferedRandomAccessFile(String filename, String mode, int bufsize) throws FileNotFoundException {
/*  68 */     super(filename, mode);
/*  69 */     this.BUFSIZE = bufsize;
/*  70 */     this.buffer = new byte[this.BUFSIZE];
/*     */   }
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
/*     */   public BufferedRandomAccessFile(File file, String mode, int bufsize) throws FileNotFoundException {
/*  88 */     super(file, mode);
/*  89 */     this.BUFSIZE = bufsize;
/*  90 */     this.buffer = new byte[this.BUFSIZE];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int read() throws IOException {
/*  99 */     if (this.bufpos >= this.bufend && fillBuffer() < 0)
/*     */     {
/* 101 */       return -1;
/*     */     }
/* 103 */     if (this.bufend == 0)
/*     */     {
/* 105 */       return -1;
/*     */     }
/*     */     
/* 108 */     return this.buffer[this.bufpos++] + 256 & 0xFF;
/*     */   }
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
/*     */   private int fillBuffer() throws IOException {
/* 122 */     int n = super.read(this.buffer, 0, this.BUFSIZE);
/*     */     
/* 124 */     if (n >= 0) {
/*     */       
/* 126 */       this.realpos += n;
/* 127 */       this.bufend = n;
/* 128 */       this.bufpos = 0;
/*     */     } 
/* 130 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void invalidate() throws IOException {
/* 140 */     this.bufend = 0;
/* 141 */     this.bufpos = 0;
/* 142 */     this.realpos = super.getFilePointer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 151 */     int leftover = this.bufend - this.bufpos;
/* 152 */     if (len <= leftover) {
/*     */       
/* 154 */       System.arraycopy(this.buffer, this.bufpos, b, off, len);
/* 155 */       this.bufpos += len;
/* 156 */       return len;
/*     */     } 
/* 158 */     System.arraycopy(this.buffer, this.bufpos, b, off, leftover);
/* 159 */     this.bufpos += leftover;
/* 160 */     if (fillBuffer() > 0) {
/*     */       
/* 162 */       int bytesRead = read(b, off + leftover, len - leftover);
/* 163 */       if (bytesRead > 0)
/*     */       {
/* 165 */         leftover += bytesRead;
/*     */       }
/*     */     } 
/* 168 */     return (leftover > 0) ? leftover : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFilePointer() throws IOException {
/* 177 */     return this.realpos - this.bufend + this.bufpos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long pos) throws IOException {
/* 186 */     int n = (int)(this.realpos - pos);
/* 187 */     if (n >= 0 && n <= this.bufend) {
/*     */       
/* 189 */       this.bufpos = this.bufend - n;
/*     */     }
/*     */     else {
/*     */       
/* 193 */       super.seek(pos);
/* 194 */       invalidate();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/BufferedRandomAccessFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */