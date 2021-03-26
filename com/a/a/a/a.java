/*     */ package com.a.a.a;
/*     */ 
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements c
/*     */ {
/*  12 */   private static SoftReference<?>[] g = (SoftReference<?>[])new SoftReference[2];
/*     */ 
/*     */   
/*     */   private c h;
/*     */   
/*     */   private ByteOrder i;
/*     */ 
/*     */   
/*     */   protected static c a(ByteOrder bo) {
/*  21 */     c imp = null;
/*     */     
/*  23 */     int index = 0;
/*  24 */     if (bo != ByteOrder.BIG_ENDIAN) {
/*  25 */       index = 1;
/*     */     }
/*     */     
/*  28 */     synchronized (g) {
/*  29 */       SoftReference<?> ref = g[index];
/*  30 */       if (ref != null) {
/*  31 */         imp = (c)ref.get();
/*     */       }
/*  33 */       if (imp == null) {
/*  34 */         if (index == 0) {
/*  35 */           imp = new e();
/*     */         } else {
/*  37 */           imp = new f();
/*     */         } 
/*  39 */         g[index] = new SoftReference(imp);
/*     */       } 
/*     */     } 
/*     */     
/*  43 */     return imp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a() {
/*  50 */     this(ByteOrder.BIG_ENDIAN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(ByteOrder bo) {
/*  57 */     this.i = (bo == null) ? ByteOrder.BIG_ENDIAN : bo;
/*  58 */     this.h = a(this.i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOrder a() {
/*  65 */     return this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(ByteOrder bo) {
/*  72 */     if (this.i != bo) {
/*  73 */       this.i = bo;
/*  74 */       this.h = a(bo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char a(byte[] b, int pos) {
/*  83 */     return this.h.a(b, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short b(byte[] b, int pos) {
/*  91 */     return this.h.b(b, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int c(byte[] b, int pos) {
/*  99 */     return this.h.c(b, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long d(byte[] b, int pos) {
/* 107 */     return this.h.d(b, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, byte x, int pos) {
/* 115 */     return this.h.a(b, x, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, byte[] x, int pos) {
/* 123 */     return this.h.a(b, x, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, int n, int pos) {
/* 131 */     return this.h.a(b, n, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, short s, int pos) {
/* 139 */     return this.h.a(b, s, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, char c1, int pos) {
/* 147 */     return this.h.a(b, c1, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char a(char n) {
/* 155 */     return this.h.a(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short a(short n) {
/* 163 */     return this.h.a(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(int n) {
/* 171 */     return this.h.a(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, long l, int pos) {
/* 179 */     return this.h.a(b, l, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] a(Collection<Byte> col) {
/* 188 */     byte[] buf = new byte[col.size()];
/* 189 */     int i = 0;
/* 190 */     for (Byte b : col) buf[i++] = b.byteValue(); 
/* 191 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int b(int length) {
/* 200 */     return length << 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int c(int length) {
/* 209 */     return length << 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int d(int length) {
/* 218 */     return length << 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int e(int length) {
/* 227 */     return length << 3;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */