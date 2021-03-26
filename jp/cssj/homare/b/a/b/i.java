/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.B;
/*     */ import jp.cssj.homare.b.a.c.f;
/*     */ import jp.cssj.homare.b.a.c.q;
/*     */ import jp.cssj.homare.b.a.c.r;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.h;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.n;
/*     */ import jp.cssj.homare.b.a.o;
/*     */ import jp.cssj.homare.b.b.b;
/*     */ import jp.cssj.homare.b.c.a;
/*     */ import jp.cssj.homare.b.c.f;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.a;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.b.g.a;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.g;
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
/*     */ public class i
/*     */   extends h
/*     */   implements n, o
/*     */ {
/*     */   protected double F;
/*     */   protected double E;
/*     */   protected boolean D = true;
/*     */   protected final boolean o;
/*     */   protected final b c;
/*     */   protected final r b;
/*     */   protected final q a;
/*     */   private static final boolean H = false;
/*     */   
/*     */   public i(q params, r pos) {
/*  51 */     this(params, pos, params.a, false);
/*     */   }
/*     */   
/*     */   private i(q params, r pos, B frame, boolean cut) {
/*  55 */     this.a = params;
/*  56 */     this.b = pos;
/*  57 */     this.c = new b(frame);
/*  58 */     this.o = cut;
/*  59 */     if (!G && params.C == null) throw new AssertionError(); 
/*  60 */     if (!G && params.G == null) throw new AssertionError(); 
/*  61 */     if (!G && params.F == null) throw new AssertionError();
/*     */   
/*     */   }
/*     */   
/*     */   public final byte a() {
/*  66 */     return 4;
/*     */   }
/*     */   
/*     */   public final y b() {
/*  70 */     return (y)this.a;
/*     */   }
/*     */   
/*     */   public final f g() {
/*  74 */     return (f)this.a;
/*     */   }
/*     */   
/*     */   public final q f() {
/*  78 */     return this.a;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  82 */     return (z)this.b;
/*     */   }
/*     */   
/*     */   public final r c() {
/*  86 */     return this.b;
/*     */   }
/*     */   
/*     */   public final b m() {
/*  90 */     return this.c;
/*     */   }
/*     */   
/*     */   public double s() {
/*  94 */     return p() - m().f();
/*     */   }
/*     */   
/*     */   public double t() {
/*  98 */     return q() - m().e();
/*     */   }
/*     */   
/*     */   public final boolean j() {
/* 102 */     return ((c()).d != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(double ascent, double descent) {
/* 107 */     if (e.a(this.a.D)) {
/*     */       
/* 109 */       ascent += this.c.d();
/* 110 */       descent += this.c.b();
/*     */     } else {
/*     */       
/* 113 */       ascent += this.c.a();
/* 114 */       descent += this.c.c();
/*     */     } 
/* 116 */     if (ascent > this.k) {
/* 117 */       this.k = ascent;
/*     */     }
/* 119 */     if (descent > this.l) {
/* 120 */       this.l = descent;
/*     */     }
/* 122 */     if (!G && e.a(this.k + this.l)) throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   public final void a(c cb) {
/* 126 */     B rframe = this.c.a;
/*     */ 
/*     */ 
/*     */     
/* 130 */     e.b(this.c.c, rframe.e, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     e.a(this.c.b, rframe.b, 0.0D);
/*     */   }
/*     */   
/*     */   public final void b(c containerBox) {
/* 139 */     jp.cssj.homare.b.a.c.i params = containerBox.c_();
/* 140 */     double lineSize = containerBox.h();
/* 141 */     B rframe = this.c.a;
/*     */ 
/*     */ 
/*     */     
/* 145 */     e.b(this.c.c, rframe.e, lineSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     if (e.a(params.D)) {
/*     */       double top, bottom;
/*     */       
/* 154 */       switch (rframe.b.a()) {
/*     */         case 1:
/* 156 */           top = rframe.b.f();
/*     */           break;
/*     */         case 2:
/* 159 */           top = rframe.b.f() * lineSize;
/*     */           break;
/*     */         case 3:
/* 162 */           top = 0.0D;
/*     */           break;
/*     */         default:
/* 165 */           throw new IllegalStateException();
/*     */       } 
/*     */       
/* 168 */       switch (rframe.b.c()) {
/*     */         case 1:
/* 170 */           bottom = rframe.b.h();
/*     */           break;
/*     */         case 2:
/* 173 */           bottom = rframe.b.h() * lineSize;
/*     */           break;
/*     */         case 3:
/* 176 */           bottom = 0.0D;
/*     */           break;
/*     */         default:
/* 179 */           throw new IllegalStateException();
/*     */       } 
/* 181 */       this.c.b.a = top;
/* 182 */       this.c.b.b = 0.0D;
/* 183 */       this.c.b.c = bottom;
/* 184 */       this.c.b.d = 0.0D;
/*     */     } else {
/*     */       double left, right;
/*     */       
/* 188 */       switch (rframe.b.d()) {
/*     */         case 1:
/* 190 */           left = rframe.b.i();
/*     */           break;
/*     */         case 2:
/* 193 */           left = rframe.b.i() * lineSize;
/*     */           break;
/*     */         case 3:
/* 196 */           left = 0.0D;
/*     */           break;
/*     */         default:
/* 199 */           throw new IllegalStateException();
/*     */       } 
/* 201 */       switch (rframe.b.b()) {
/*     */         case 1:
/* 203 */           right = rframe.b.g();
/*     */           break;
/*     */         case 2:
/* 206 */           right = rframe.b.g() * lineSize;
/*     */           break;
/*     */         case 3:
/* 209 */           right = 0.0D;
/*     */           break;
/*     */         default:
/* 212 */           throw new IllegalStateException();
/*     */       } 
/* 214 */       this.c.b.a = 0.0D;
/* 215 */       this.c.b.b = right;
/* 216 */       this.c.b.c = 0.0D;
/* 217 */       this.c.b.d = left;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void a(m containerBox) {
/* 222 */     r pos = c();
/* 223 */     if (pos.d != null) {
/*     */ 
/*     */ 
/*     */       
/* 227 */       this.E = e.a(pos.d, (j)containerBox);
/* 228 */       this.F = e.b(pos.d, (j)containerBox);
/*     */     } 
/* 230 */     super.a(containerBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 239 */     x += this.E;
/* 240 */     y += this.F;
/*     */     
/* 242 */     visitor.a(transform, (j)this, x, y);
/*     */     
/* 244 */     if (this.a.ao != 0.0F) {
/* 245 */       if (this.a.an == 1) {
/* 246 */         g newDrawer = new g(this.a.am);
/* 247 */         drawer.a(newDrawer);
/* 248 */         drawer = newDrawer;
/*     */       } 
/*     */       
/* 251 */       if (this.c.i()) {
/*     */         
/* 253 */         a a1 = new a(pageBox, clip, this.a.ao, transform, this.c, p(), q());
/* 254 */         drawer.a((f)a1, x, y);
/*     */       } 
/* 256 */       if (e.a((g()).D)) {
/*     */ 
/*     */         
/* 259 */         y += this.c.a();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 264 */         x += this.c.b();
/*     */       } 
/*     */ 
/*     */       
/* 268 */       if ((c()).d != null) {
/* 269 */         contextX = x;
/* 270 */         contextY = y;
/*     */       } 
/*     */ 
/*     */       
/* 274 */       super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final i a(boolean cut) {
/*     */     i newInline;
/* 280 */     q params = f();
/* 281 */     if (cut) {
/*     */       B previousFrame, nextFrame;
/*     */       
/* 284 */       if (e.a(params.D)) {
/*     */         
/* 286 */         previousFrame = this.c.a.a(true, true, false, true);
/* 287 */         nextFrame = this.c.a.a(false, true, true, true);
/*     */       } else {
/*     */         
/* 290 */         previousFrame = this.c.a.a(true, false, true, true);
/* 291 */         nextFrame = this.c.a.a(true, true, true, false);
/* 292 */         this.c.b.b = 0.0D;
/*     */       } 
/* 294 */       this.c.a = previousFrame;
/* 295 */       newInline = new i(params, c(), nextFrame, cut);
/*     */     } else {
/* 297 */       newInline = new i(params, c());
/*     */     } 
/* 299 */     return newInline;
/*     */   }
/*     */   
/*     */   public final void o() {
/* 303 */     this.D = false;
/*     */   }
/*     */   
/*     */   public final void a(e gh, boolean widow) {
/* 307 */     q params = f();
/* 308 */     if (!this.o) {
/* 309 */       i inlineBox = new i(params, c());
/* 310 */       inlineBox.c.b = this.c.b;
/* 311 */       inlineBox.c.c = this.c.c;
/* 312 */       b quad = b.a(inlineBox);
/*     */       
/* 314 */       gh.a((g)quad);
/* 315 */     } else if (widow) {
/*     */       B nextFrame;
/*     */       
/*     */       a nextMargin, nextPadding;
/* 319 */       if (e.a(params.D)) {
/*     */         
/* 321 */         nextFrame = this.c.a.a(false, true, true, true);
/* 322 */         nextMargin = this.c.b.a(false, true, true, true);
/* 323 */         nextPadding = this.c.c.a(false, true, true, true);
/*     */       } else {
/*     */         
/* 326 */         nextFrame = params.a.a(true, true, true, false);
/* 327 */         nextMargin = this.c.b.a(true, true, true, false);
/* 328 */         nextPadding = this.c.c.a(true, true, true, false);
/*     */       } 
/* 330 */       i inlineBox = new i(params, c(), nextFrame, true);
/* 331 */       inlineBox.c.b = nextMargin;
/* 332 */       inlineBox.c.c = nextPadding;
/* 333 */       b quad = b.a(inlineBox);
/*     */       
/* 335 */       gh.a((g)quad);
/*     */     } 
/* 337 */     super.a(gh, widow);
/* 338 */     if (!this.D) {
/* 339 */       b quad = b.b(this);
/*     */       
/* 341 */       gh.a((g)quad);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 346 */     return "[InlineBox]" + super.toString() + "[/InlineBox]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */