/*     */ package jp.cssj.sakae.pdf.c.a.c;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.sakae.a.a;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.c.a.j;
/*     */ import jp.cssj.sakae.pdf.c.a.b;
/*     */ import jp.cssj.sakae.pdf.c.a.f;
/*     */ import jp.cssj.sakae.pdf.c.a.l;
/*     */ import jp.cssj.sakae.pdf.c.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends a
/*     */   implements b
/*     */ {
/*  26 */   private static final Logger w = Logger.getLogger(b.class.getName());
/*     */   
/*     */   private static final long x = 1L;
/*     */   protected final f i;
/*     */   protected final f j;
/*     */   protected String k;
/*     */   protected jp.cssj.sakae.a.b l;
/*     */   protected short m;
/*     */   protected short n;
/*     */   protected short o;
/*     */   protected short p;
/*     */   protected short q;
/*     */   protected short r;
/*     */   protected short s;
/*     */   protected l t;
/*     */   protected j u;
/*  42 */   protected transient Font v = null;
/*     */   
/*     */   public b(f hcmap, f vcmap) {
/*  45 */     if (hcmap == null) {
/*  46 */       throw new NullPointerException();
/*     */     }
/*  48 */     this.i = hcmap;
/*  49 */     this.j = vcmap;
/*  50 */     if (w.isLoggable(Level.FINE)) {
/*  51 */       w.fine("new font: " + d());
/*     */     }
/*     */   }
/*     */   
/*     */   public byte e() {
/*  56 */     return (this.j == null) ? 1 : 3;
/*     */   }
/*     */   
/*     */   public String d() {
/*  60 */     return this.k;
/*     */   }
/*     */   
/*     */   public jp.cssj.sakae.a.b f() {
/*  64 */     return this.l;
/*     */   }
/*     */   
/*     */   public short g() {
/*  68 */     return this.m;
/*     */   }
/*     */   
/*     */   public short i() {
/*  72 */     return this.n;
/*     */   }
/*     */   
/*     */   public short h() {
/*  76 */     return this.o;
/*     */   }
/*     */   
/*     */   public short l() {
/*  80 */     return this.p;
/*     */   }
/*     */   
/*     */   public short j() {
/*  84 */     return this.q;
/*     */   }
/*     */   
/*     */   public short k() {
/*  88 */     return this.r;
/*     */   }
/*     */   
/*     */   public l q() {
/*  92 */     return this.t;
/*     */   }
/*     */   
/*     */   public short m() {
/*  96 */     return this.s;
/*     */   }
/*     */   
/*     */   public void a(String fontName) {
/* 100 */     this.k = fontName;
/*     */   }
/*     */   
/*     */   public void a(j panose) {
/* 104 */     this.u = panose;
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.sakae.a.b bbox) {
/* 108 */     this.l = bbox;
/*     */   }
/*     */   
/*     */   public void b(short ascent) {
/* 112 */     this.m = ascent;
/*     */   }
/*     */   
/*     */   public void c(short descent) {
/* 116 */     this.n = descent;
/*     */   }
/*     */   
/*     */   public void d(short capHeight) {
/* 120 */     this.o = capHeight;
/*     */   }
/*     */   
/*     */   public void e(short xHeight) {
/* 124 */     this.p = xHeight;
/*     */   }
/*     */   
/*     */   public void a(l warray) {
/* 128 */     if (warray == null) {
/* 129 */       throw new NullPointerException();
/*     */     }
/* 131 */     this.t = warray;
/* 132 */     this.s = warray.a(this.i.a().a(32));
/*     */   }
/*     */   
/*     */   public void f(short stemH) {
/* 136 */     this.q = stemH;
/*     */   }
/*     */   
/*     */   public void g(short stemV) {
/* 140 */     this.r = stemV;
/*     */   }
/*     */   
/*     */   protected synchronized Font r() {
/* 144 */     if (this.v == null) {
/* 145 */       this.v = jp.cssj.sakae.pdf.c.d.b.a((g)this);
/*     */     }
/* 147 */     return this.v;
/*     */   }
/*     */   
/*     */   public byte h_() {
/* 151 */     return 4;
/*     */   }
/*     */   
/*     */   public boolean a(int c) {
/* 155 */     return this.i.a().b(c);
/*     */   }
/*     */   
/*     */   public j p() {
/* 159 */     return this.u;
/*     */   }
/*     */   
/*     */   public e a(String name, jp.cssj.sakae.pdf.b fontRef) {
/* 163 */     switch (e()) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 167 */         return (e)new a(this, name, fontRef, this.i);
/*     */       
/*     */       case 3:
/* 170 */         return (e)new a(this, name, fontRef, this.j);
/*     */     } 
/* 172 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */   
/*     */   public e n() {
/* 177 */     return (e)a(null, null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */