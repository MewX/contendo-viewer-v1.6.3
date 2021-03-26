/*     */ package jp.cssj.homare.css.f;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class A
/*     */   implements Serializable, ad
/*     */ {
/*     */   private static final long s = 0L;
/*     */   public static final short a = 400;
/*     */   public static final short b = 700;
/*     */   public static final short c = 1;
/*     */   public static final short d = 2;
/*     */   public static final A e;
/*     */   public static final A f;
/*     */   public static final A g;
/*     */   public static final A h;
/*     */   public static final A i;
/*     */   
/*     */   static {
/*  22 */     e = new A((short)100);
/*     */     
/*  24 */     f = new A((short)200);
/*     */     
/*  26 */     g = new A((short)300);
/*     */     
/*  28 */     h = new A((short)400);
/*     */     
/*  30 */     i = new A((short)500);
/*     */     
/*  32 */     j = new A((short)600);
/*     */     
/*  34 */     k = new A((short)700);
/*     */     
/*  36 */     l = new A((short)800);
/*     */     
/*  38 */     m = new A((short)900);
/*     */     
/*  40 */     n = new A((short)400);
/*     */     
/*  42 */     o = new A((short)700);
/*     */     
/*  44 */     p = new A((short)1);
/*     */     
/*  46 */     q = new A((short)2);
/*     */     
/*  48 */     t = new A[] { e, f, g, h, i, j, k, l, m };
/*     */   }
/*     */   public static final A j; public static final A k; public static final A l; public static final A m; public static final A n; public static final A o; public static final A p; public static final A q; private static final A[] t; private final short u;
/*     */   public static A a(int fontWeight) throws IllegalArgumentException {
/*  52 */     switch (fontWeight) {
/*     */       case 100:
/*  54 */         return e;
/*     */       case 200:
/*  56 */         return f;
/*     */       case 300:
/*  58 */         return g;
/*     */       case 400:
/*  60 */         return h;
/*     */       case 500:
/*  62 */         return i;
/*     */       case 600:
/*  64 */         return j;
/*     */       case 700:
/*  66 */         return k;
/*     */       case 800:
/*  68 */         return l;
/*     */       case 900:
/*  70 */         return m;
/*     */     } 
/*  72 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private A(short fontWeight) {
/*  79 */     this.u = fontWeight;
/*     */   }
/*     */   
/*     */   public short a() {
/*  83 */     return 1032;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short b() {
/*  92 */     if (!r && (this.u == 1 || this.u == 2)) throw new AssertionError(); 
/*  93 */     return this.u;
/*     */   }
/*     */   
/*     */   public A c() {
/*  97 */     if (!r && (this.u == 1 || this.u == 2)) throw new AssertionError(); 
/*  98 */     return t[Math.min(8, this.u / 100)];
/*     */   }
/*     */   
/*     */   public A d() {
/* 102 */     if (!r && (this.u == 1 || this.u == 2)) throw new AssertionError(); 
/* 103 */     return t[Math.max(0, this.u / 100 - 2)];
/*     */   }
/*     */   
/*     */   public String toString() {
/* 107 */     return String.valueOf(this.u);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */