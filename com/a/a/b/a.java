/*     */ package com.a.a.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends InputStream
/*     */ {
/*     */   private static final int b = -1;
/*     */   
/*     */   private static class a
/*     */   {
/*     */     byte[] a;
/*     */     int b;
/*     */     int c;
/*     */     
/*     */     a(byte[] b, int off, int len) {
/*  24 */       this.a = b;
/*  25 */       this.b = off;
/*  26 */       this.c = len;
/*     */     }
/*     */     
/*     */     int a(int n) {
/*  30 */       return this.a[this.b + n] & 0xFF;
/*     */     }
/*     */   }
/*  33 */   LinkedList<a> a = new LinkedList<a>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private b c = null;
/*     */   
/*  41 */   private long d = 0L;
/*  42 */   private long e = 0L;
/*  43 */   private long f = 0L;
/*  44 */   private int g = 0;
/*  45 */   private int h = 0;
/*  46 */   private long i = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(byte[] arrayOfByte) {
/*  59 */     a(arrayOfByte);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(byte[] arrayOfByte, int off, int len) {
/*  69 */     a(arrayOfByte, off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(List<byte[]> list) {
/*  77 */     for (byte[] arrayOfByte : list) {
/*  78 */       a(arrayOfByte);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(b recycler) {
/*  86 */     this.c = recycler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte[] arrayOfByte) {
/*  95 */     if (arrayOfByte != null && arrayOfByte.length > 0) {
/*  96 */       a(arrayOfByte, 0, arrayOfByte.length);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte[] arrayOfByte, int off, int len) {
/* 108 */     if (arrayOfByte != null && arrayOfByte.length > 0 && len > 0) {
/* 109 */       this.a.addLast(new a(arrayOfByte, off, len));
/* 110 */       this.d += len;
/* 111 */       this.g = ((a)this.a.getFirst()).c;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long a() {
/* 120 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void c() {
/* 128 */     if (this.h > 0) {
/* 129 */       d();
/*     */     }
/* 131 */     while (!this.a.isEmpty() && 
/* 132 */       this.f + this.g <= this.e && (
/* 133 */       this.h <= 0 || this.f + this.g <= this.i)) {
/*     */ 
/*     */ 
/*     */       
/* 137 */       a info = this.a.removeFirst();
/* 138 */       if (this.c != null) {
/* 139 */         this.c.a(this, info.a, info.b, info.c);
/*     */       }
/*     */       
/* 142 */       this.d -= this.g;
/* 143 */       this.f += this.g;
/*     */ 
/*     */       
/* 146 */       this.g = this.a.isEmpty() ? 0 : ((a)this.a.getFirst()).c;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void d() {
/* 154 */     long size = this.e - this.i;
/* 155 */     if (size > this.h) {
/* 156 */       this.h = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 165 */     int n = (int)(this.d + this.f - this.e);
/* 166 */     if (n < 0) n = 0; 
/* 167 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mark(int readlimit) {
/* 175 */     this.i = this.e;
/* 176 */     this.h = readlimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] buffer, int offset, int length) throws IOException {
/* 192 */     int nRead = 0;
/*     */ 
/*     */     
/* 195 */     int off = (int)(this.e - this.f);
/*     */ 
/*     */     
/* 198 */     Iterator<a> it = this.a.iterator();
/* 199 */     while (it.hasNext()) {
/* 200 */       a info = it.next();
/* 201 */       int size = info.c;
/*     */ 
/*     */       
/* 204 */       if (off < size) {
/* 205 */         int len = size - off;
/* 206 */         if (length <= len) len = length;
/*     */         
/* 208 */         System.arraycopy(info.a, off, buffer, offset, len);
/* 209 */         this.e += len;
/* 210 */         nRead += len;
/*     */ 
/*     */         
/* 213 */         if (length <= len) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 218 */         off += len;
/* 219 */         offset += len;
/* 220 */         length -= len;
/*     */       } 
/* 222 */       off -= size;
/*     */     } 
/* 224 */     c();
/*     */     
/* 226 */     return nRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] buffer) throws IOException {
/* 234 */     return read(buffer, 0, buffer.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 242 */     int offset = (int)(this.e - this.f);
/* 243 */     int n = -1;
/*     */ 
/*     */     
/*     */     try {
/* 247 */       if (offset < this.g) {
/*     */         
/* 249 */         this.e++;
/* 250 */         n = ((a)this.a.getFirst()).a(offset);
/*     */       } else {
/*     */         
/* 253 */         Iterator<a> it = this.a.iterator();
/* 254 */         int size = this.g;
/* 255 */         while (it.hasNext()) {
/* 256 */           a info = it.next();
/* 257 */           size = info.c;
/* 258 */           if (offset < size) {
/* 259 */             n = info.a(offset);
/* 260 */             this.e++;
/* 261 */             c();
/*     */             break;
/*     */           } 
/* 264 */           offset -= size;
/*     */         } 
/*     */       } 
/* 267 */     } catch (NullPointerException e) {
/* 268 */       throw new IOException("InputStream already closed.");
/*     */     } 
/*     */     
/* 271 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 279 */     d();
/* 280 */     if (this.h <= 0) {
/* 281 */       throw new IOException("Mark is not available.");
/*     */     }
/* 283 */     this.e = this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long byteCount) throws IOException {
/* 291 */     return super.skip(byteCount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 300 */       super.close();
/*     */     } finally {
/* 302 */       this.a = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void b() {
/* 310 */     this.e = 0L;
/* 311 */     this.d = 0L;
/* 312 */     this.h = 0;
/* 313 */     this.i = 0L;
/* 314 */     this.f = 0L;
/* 315 */     this.g = 0;
/* 316 */     c();
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     void a(Object param1Object, byte[] param1ArrayOfbyte, int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */