/*     */ package jp.cssj.sakae.c.c;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements Serializable, b
/*     */ {
/*     */   private static final long q = 1L;
/*     */   public static final int a = 0;
/*     */   public static final int b = 1;
/*     */   public static final int c = 2;
/*     */   public static final int d = 3;
/*  16 */   public static byte e = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   public static byte f = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   public static byte g = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public byte h = e; private final float r; private final float s;
/*     */   
/*     */   public static a a(float cyan, float magenta, float yellow, float black) {
/*  34 */     return new a(cyan, magenta, yellow, black);
/*     */   }
/*     */   private final float t; private final float u;
/*     */   public static a a(float cyan, float magenta, float yellow, float black, byte overprint) {
/*  38 */     return new a(cyan, magenta, yellow, black, overprint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected a(float cyan, float magenta, float yellow, float black) {
/*  44 */     this(cyan, magenta, yellow, black, e);
/*     */   }
/*     */   
/*     */   protected a(float cyan, float magenta, float yellow, float black, byte overprint) {
/*  48 */     this.r = Math.min(1.0F, Math.max(0.0F, cyan));
/*  49 */     this.s = Math.min(1.0F, Math.max(0.0F, magenta));
/*  50 */     this.t = Math.min(1.0F, Math.max(0.0F, yellow));
/*  51 */     this.u = Math.min(1.0F, Math.max(0.0F, black));
/*  52 */     this.h = overprint;
/*     */   }
/*     */   
/*     */   public short b() {
/*  56 */     return 1;
/*     */   }
/*     */   
/*     */   public short c() {
/*  60 */     return 2;
/*     */   }
/*     */   
/*     */   public float a(int i) {
/*  64 */     switch (i) {
/*     */       case 0:
/*  66 */         return this.r;
/*     */       case 1:
/*  68 */         return this.s;
/*     */       case 2:
/*  70 */         return this.t;
/*     */       case 3:
/*  72 */         return this.u;
/*     */     } 
/*  74 */     throw new IllegalArgumentException();
/*     */   }
/*     */   
/*     */   public float d() {
/*  78 */     return Math.max(0.0F, 1.0F - this.r + this.u);
/*     */   }
/*     */   
/*     */   public float e() {
/*  82 */     return Math.max(0.0F, 1.0F - this.s + this.u);
/*     */   }
/*     */   
/*     */   public float f() {
/*  86 */     return Math.max(0.0F, 1.0F - this.t + this.u);
/*     */   }
/*     */   
/*     */   public float g() {
/*  90 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public byte h() {
/*  94 */     return this.h;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  98 */     if (o instanceof a) {
/*  99 */       a color = (a)o;
/* 100 */       return (this.r == color.r && this.s == color.s && this.t == color.t && this.u == color.u && this.h == color.h);
/*     */     } 
/*     */     
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 107 */     return "-cssj-cmyk(" + this.r + "," + this.s + "," + this.t + "," + this.u + "," + this.h + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */