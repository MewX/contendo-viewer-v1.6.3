/*     */ package jp.cssj.sakae.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ 
/*     */ public class f implements f {
/*     */   private static final long h = 1L;
/*     */   protected final j a;
/*     */   protected final g b;
/*     */   protected final double c;
/*     */   protected final double d;
/*     */   protected final double e;
/*     */   protected final double f;
/*     */   protected e g;
/*     */   
/*     */   public f(j fontStore, g fontSource, h fontStyle) {
/*     */     double ascent, descent;
/*  19 */     this.g = null;
/*     */ 
/*     */     
/*  22 */     this.a = fontStore;
/*  23 */     this.b = fontSource;
/*  24 */     this.c = fontStyle.e();
/*  25 */     this.d = this.c * this.b.l() / 1000.0D;
/*     */ 
/*     */     
/*  28 */     byte direction = fontStyle.a();
/*  29 */     switch (direction) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/*  33 */         ascent = this.c * this.b.g() / 1000.0D;
/*  34 */         descent = this.c * this.b.i() / 1000.0D;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  39 */         ascent = descent = this.c / 2.0D;
/*     */ 
/*     */       
/*     */       default:
/*  43 */         throw new IllegalStateException();
/*     */     } 
/*  45 */     double remainder = this.c - ascent - descent;
/*  46 */     if (remainder != 0.0D) {
/*  47 */       double afrac = ascent / (ascent + descent);
/*  48 */       double dfrac = descent / (ascent + descent);
/*  49 */       ascent = this.c * afrac;
/*  50 */       descent = this.c * dfrac;
/*     */     } 
/*  52 */     this.e = ascent;
/*  53 */     this.f = descent;
/*     */   }
/*     */   
/*     */   public e a() {
/*     */     try {
/*  58 */       this.g = this.a.a(this.b);
/*  59 */     } catch (IOException iOException) {
/*  60 */       throw new RuntimeException(iOException);
/*     */     } 
/*  62 */     return this.g;
/*     */   }
/*     */   
/*     */   public g b() {
/*  66 */     return this.b;
/*     */   }
/*     */   
/*     */   public double c() {
/*  70 */     return this.e;
/*     */   }
/*     */   
/*     */   public double d() {
/*  74 */     return this.f;
/*     */   }
/*     */   
/*     */   public double e() {
/*  78 */     return this.c;
/*     */   }
/*     */   
/*     */   public double f() {
/*  82 */     return this.d;
/*     */   }
/*     */   
/*     */   public double g() {
/*  86 */     return this.c * this.b.m() / 1000.0D;
/*     */   }
/*     */   
/*     */   public double a(int gid) {
/*  90 */     return this.c * a().b(gid) / 1000.0D;
/*     */   }
/*     */   
/*     */   public double b(int gid) {
/*  94 */     return this.c * a().c(gid) / 1000.0D;
/*     */   }
/*     */   
/*     */   public double a(int gid, int sgid) {
/*  98 */     return this.c * a().a(gid, sgid) / 1000.0D;
/*     */   }
/*     */   
/*     */   public int b(int gid, int sgid) {
/* 102 */     return a().b(gid, sgid);
/*     */   }
/*     */   
/*     */   public boolean c(int c) {
/* 106 */     return b().a(c);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 110 */     return super.toString() + ":[fontSource=" + this.b + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */