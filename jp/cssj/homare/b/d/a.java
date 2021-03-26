/*     */ package jp.cssj.homare.b.d;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class a
/*     */   implements b
/*     */ {
/*  15 */   protected int e = 0;
/*     */   
/*  17 */   protected byte f = 1;
/*     */ 
/*     */ 
/*     */   
/*  21 */   protected byte h = 1;
/*     */   
/*  23 */   protected byte i = 1;
/*     */ 
/*     */   
/*     */   protected boolean j = false;
/*     */ 
/*     */   
/*     */   protected boolean k = false;
/*     */ 
/*     */   
/*  32 */   protected double l = 28.346456692913385D;
/*  33 */   protected double m = 28.346456692913385D;
/*  34 */   protected double n = 28.346456692913385D;
/*  35 */   protected double o = 28.346456692913385D;
/*     */ 
/*     */   
/*  38 */   protected double p = 8.503937007874017D;
/*     */ 
/*     */   
/*  41 */   protected double q = 0.0D;
/*     */ 
/*     */   
/*  44 */   protected double r = 595.2755905511812D;
/*     */ 
/*     */   
/*  47 */   protected double s = 841.8897637795277D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   protected MessageFormat v = null; protected boolean w = true; protected final m d;
/*     */   protected byte g;
/*     */   protected double t;
/*     */   protected double u;
/*     */   
/*     */   public a(m ua) {
/*  62 */     if (!x && ua == null) throw new AssertionError(); 
/*  63 */     this.d = ua;
/*  64 */     this.t = this.s;
/*  65 */     this.u = this.s;
/*  66 */     this.g = (byte)B.x.a(ua);
/*     */   }
/*     */   
/*     */   public jp.cssj.homare.css.a f() {
/*  70 */     jp.cssj.homare.css.a pageElement = this.d.b().b();
/*  71 */     switch (this.g) {
/*     */       
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*  76 */         if (g() == 1) {
/*     */           
/*  78 */           if (pageElement == null) {
/*  79 */             pageElement = jp.cssj.homare.css.a.i;
/*  80 */           } else if (pageElement == jp.cssj.homare.css.a.i) {
/*  81 */             pageElement = jp.cssj.homare.css.a.j;
/*  82 */           } else if (pageElement == jp.cssj.homare.css.a.j) {
/*  83 */             pageElement = jp.cssj.homare.css.a.k;
/*  84 */           } else if (pageElement == jp.cssj.homare.css.a.k) {
/*  85 */             pageElement = jp.cssj.homare.css.a.j;
/*     */           }
/*     */         
/*     */         }
/*  89 */         else if (pageElement == null) {
/*  90 */           pageElement = jp.cssj.homare.css.a.l;
/*  91 */         } else if (pageElement == jp.cssj.homare.css.a.l) {
/*  92 */           pageElement = jp.cssj.homare.css.a.n;
/*  93 */         } else if (pageElement == jp.cssj.homare.css.a.n) {
/*  94 */           pageElement = jp.cssj.homare.css.a.m;
/*  95 */         } else if (pageElement == jp.cssj.homare.css.a.m) {
/*  96 */           pageElement = jp.cssj.homare.css.a.n;
/*     */         } 
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
/* 113 */         this.d.b().a(pageElement);
/* 114 */         return pageElement;case 1: if (pageElement == null) { pageElement = jp.cssj.homare.css.a.o; } else { pageElement = jp.cssj.homare.css.a.p; }  this.d.b().a(pageElement); return pageElement;
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   } public final byte g() {
/* 118 */     return this.f;
/*     */   }
/*     */   
/*     */   public final void a(byte boundSide) {
/* 122 */     this.f = boundSide;
/* 123 */     switch (this.g) {
/*     */       
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/* 128 */         if (g() == 1) {
/*     */           
/* 130 */           this.d.e((byte)1);
/*     */         } else {
/*     */           
/* 133 */           this.d.e((byte)2);
/*     */         } 
/*     */ 
/*     */       
/*     */       case 1:
/*     */         return;
/*     */     } 
/*     */     
/* 141 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public final byte h() {
/* 146 */     return this.h;
/*     */   }
/*     */   
/*     */   public final void b(byte align) {
/* 150 */     this.h = align;
/*     */   }
/*     */   
/*     */   public final byte i() {
/* 154 */     return this.i;
/*     */   }
/*     */   
/*     */   public final void c(byte autoRotate) {
/* 158 */     this.i = autoRotate;
/*     */   }
/*     */   
/*     */   public final double j() {
/* 162 */     return this.l;
/*     */   }
/*     */   
/*     */   public final double k() {
/* 166 */     return this.m;
/*     */   }
/*     */   
/*     */   public final double l() {
/* 170 */     return this.o;
/*     */   }
/*     */   
/*     */   public final double m() {
/* 174 */     return this.n;
/*     */   }
/*     */   
/*     */   public final void a(double trimTop, double trimRight, double trimBottom, double trimLeft) {
/* 178 */     this.l = trimTop;
/* 179 */     this.m = trimRight;
/* 180 */     this.o = trimBottom;
/* 181 */     this.n = trimLeft;
/*     */   }
/*     */   
/*     */   public final double n() {
/* 185 */     return this.p;
/*     */   }
/*     */   
/*     */   public final void a(double cuttingMargin) {
/* 189 */     this.p = cuttingMargin;
/*     */   }
/*     */   
/*     */   public final double o() {
/* 193 */     return this.q;
/*     */   }
/*     */   
/*     */   public final void b(double spineWidth) {
/* 197 */     this.q = spineWidth;
/*     */   }
/*     */   
/*     */   public final double p() {
/* 201 */     return this.r;
/*     */   }
/*     */   
/*     */   public final void c(double pageWidth) {
/* 205 */     this.r = pageWidth;
/*     */   }
/*     */   
/*     */   public void q() {
/* 209 */     this.t = this.r + this.n + this.m;
/*     */   }
/*     */   
/*     */   public final double r() {
/* 213 */     return this.s;
/*     */   }
/*     */   
/*     */   public final void d(double pageHeight) {
/* 217 */     this.s = pageHeight;
/*     */   }
/*     */   
/*     */   public void s() {
/* 221 */     this.u = this.s + this.l + this.o;
/*     */   }
/*     */   
/*     */   public final double t() {
/* 225 */     return this.t;
/*     */   }
/*     */   
/*     */   public final void e(double paperWidth) {
/* 229 */     this.t = paperWidth;
/*     */   }
/*     */   
/*     */   public final double u() {
/* 233 */     return this.u;
/*     */   }
/*     */   
/*     */   public final void f(double paperHeight) {
/* 237 */     this.u = paperHeight;
/*     */   }
/*     */   
/*     */   public final String v() {
/* 241 */     if (this.v == null) {
/* 242 */       return null;
/*     */     }
/* 244 */     return this.v.toPattern();
/*     */   }
/*     */   
/*     */   public final void a(String note) {
/* 248 */     if (note == null) {
/* 249 */       this.v = null;
/*     */       return;
/*     */     } 
/* 252 */     this.v = new MessageFormat(note);
/*     */   }
/*     */   
/*     */   public final boolean w() {
/* 256 */     return this.j;
/*     */   }
/*     */   
/*     */   public final void a(boolean crop) {
/* 260 */     this.j = crop;
/*     */   }
/*     */   
/*     */   public final boolean x() {
/* 264 */     return this.k;
/*     */   }
/*     */   
/*     */   public final void b(boolean cross) {
/* 268 */     this.k = cross;
/*     */   }
/*     */   
/*     */   public final boolean y() {
/* 272 */     return this.w;
/*     */   }
/*     */   
/*     */   public final void c(boolean clip) {
/* 276 */     this.w = clip;
/*     */   }
/*     */   
/*     */   public void z() throws c {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */