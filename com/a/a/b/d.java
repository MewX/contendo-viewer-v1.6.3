/*     */ package com.a.a.b;
/*     */ 
/*     */ import com.a.a.b.a.c;
/*     */ import com.a.a.b.a.e;
/*     */ import com.a.a.b.a.g;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends FilterInputStream
/*     */ {
/*     */   private e a;
/*  15 */   private long b = 0L;
/*     */ 
/*     */   
/*  18 */   private long c = 0L;
/*     */ 
/*     */   
/*  21 */   private byte[] d = null;
/*     */   
/*     */   private long e;
/*     */   
/*     */   private long f;
/*     */   private int g;
/*  27 */   private byte[] h = null;
/*     */   
/*     */   private long i;
/*     */   private int j;
/*     */   private int k;
/*  32 */   private byte[] l = new byte[512];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(InputStream in, c encryptor) throws IOException {
/*  43 */     this(in, (e)new g(encryptor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(InputStream in, e encryptor) {
/*  52 */     super(in);
/*  53 */     this.a = encryptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long a() {
/*  60 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  68 */     int nRead = 0;
/*  69 */     int offSave = off;
/*     */     
/*  71 */     while (len > 0) {
/*  72 */       int n = 0;
/*  73 */       if (this.d != null) {
/*  74 */         if (this.b >= this.e && this.b <= this.f) {
/*  75 */           int copy_off = (int)(this.e - this.b);
/*  76 */           n = this.g - copy_off;
/*  77 */           if (len < n) {
/*  78 */             n = len;
/*     */           }
/*  80 */           System.arraycopy(this.d, copy_off, b, off, n);
/*     */         } else {
/*  82 */           this.d = null;
/*     */         } 
/*     */       } else {
/*  85 */         int read = 0;
/*  86 */         if (this.a == null) {
/*  87 */           read = this.in.read(b, off, len);
/*  88 */           if (read < 0) {
/*  89 */             this.m = true;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*  94 */         } else if (this.a.b() > 0 && this.a.a() == 0) {
/*  95 */           n = this.a.b(b, off, len);
/*     */         } else {
/*  97 */           if (this.m) return -1; 
/*  98 */           read = this.in.read(this.l, 0, this.l.length);
/*  99 */           if (read < 0) {
/* 100 */             this.m = true;
/*     */             break;
/*     */           } 
/* 103 */           this.a.a(this.l, 0, read);
/*     */         } 
/*     */         
/* 106 */         this.c += read;
/*     */       } 
/* 108 */       len -= n;
/* 109 */       off += n;
/* 110 */       nRead += n;
/* 111 */       this.b += n;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     if (this.h != null) {
/* 116 */       int size = this.k + nRead;
/* 117 */       if (size > this.j) {
/*     */         
/* 119 */         this.h = null;
/*     */       } else {
/* 121 */         System.arraycopy(b, offSave, this.h, this.k, nRead);
/* 122 */         this.k = size;
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     return nRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long b() {
/* 135 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 143 */     if (this.h != null && 
/* 144 */       this.k + n > this.j) {
/* 145 */       this.h = null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 150 */     long skip = 0L;
/* 151 */     for (; skip < n; skip++) {
/* 152 */       int b = read();
/* 153 */       if (b < 0)
/*     */         break; 
/* 155 */     }  return skip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 163 */     this.j = readlimit;
/* 164 */     this.k = 0;
/* 165 */     this.h = new byte[readlimit];
/* 166 */     this.i = this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 175 */     if (this.h == null) {
/* 176 */       throw new IOException("Mark is not available.");
/*     */     }
/*     */ 
/*     */     
/* 180 */     if (this.d == null) {
/*     */ 
/*     */ 
/*     */       
/* 184 */       this.d = this.h;
/* 185 */       this.e = this.i;
/* 186 */       this.g = this.k;
/* 187 */       this.f = this.e + this.g - 1L;
/*     */     } 
/*     */     
/* 190 */     this.k = 0;
/*     */ 
/*     */     
/* 193 */     this.b = this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 201 */     byte[] b = new byte[1];
/* 202 */     int n = read(b, 0, 1);
/* 203 */     return (n == 1) ? b[0] : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 211 */     super.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */