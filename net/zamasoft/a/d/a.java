/*     */ package net.zamasoft.a.d;
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
/*     */ public class a
/*     */   extends RandomAccessFile
/*     */ {
/*     */   private final byte[] a;
/*  40 */   private int b = 0;
/*  41 */   private int c = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private long d = 0L;
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
/*     */   private final int e;
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
/*     */   public a(String filename, String mode, int bufsize) throws FileNotFoundException {
/*  71 */     super(filename, mode);
/*  72 */     this.e = bufsize;
/*  73 */     this.a = new byte[this.e];
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
/*     */   
/*     */   public a(File file, String mode) throws FileNotFoundException {
/*  92 */     super(file, mode);
/*  93 */     this.e = 512;
/*  94 */     this.a = new byte[this.e];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int read() throws IOException {
/* 102 */     if (this.c >= this.b && a() < 0) {
/* 103 */       return -1;
/*     */     }
/* 105 */     if (this.b == 0) {
/* 106 */       return -1;
/*     */     }
/*     */     
/* 109 */     return this.a[this.c++] + 256 & 0xFF;
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
/*     */   private int a() throws IOException {
/* 124 */     int n = super.read(this.a, 0, this.e);
/*     */     
/* 126 */     if (n >= 0) {
/* 127 */       this.d += n;
/* 128 */       this.b = n;
/* 129 */       this.c = 0;
/*     */     } 
/* 131 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b() throws IOException {
/* 141 */     this.b = 0;
/* 142 */     this.c = 0;
/* 143 */     this.d = super.getFilePointer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 151 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 159 */     int leftover = this.b - this.c;
/* 160 */     if (len <= leftover) {
/* 161 */       System.arraycopy(this.a, this.c, b, off, len);
/* 162 */       this.c += len;
/* 163 */       return len;
/*     */     } 
/* 165 */     System.arraycopy(this.a, this.c, b, off, leftover);
/* 166 */     this.c += leftover;
/* 167 */     if (a() > 0) {
/* 168 */       int bytesRead = read(b, off + leftover, len - leftover);
/* 169 */       if (bytesRead > 0) {
/* 170 */         leftover += bytesRead;
/*     */       }
/*     */     } 
/* 173 */     return (leftover > 0) ? leftover : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFilePointer() throws IOException {
/* 181 */     return this.d - this.b + this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long pos) throws IOException {
/* 189 */     int n = (int)(this.d - pos);
/* 190 */     if (n >= 0 && n <= this.b) {
/* 191 */       this.c = this.b - n;
/*     */     } else {
/* 193 */       super.seek(pos);
/* 194 */       b();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */