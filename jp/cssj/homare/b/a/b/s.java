/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.A;
/*     */ import jp.cssj.homare.b.a.c.B;
/*     */ import jp.cssj.homare.b.a.c.E;
/*     */ import jp.cssj.homare.b.a.c.G;
/*     */ import jp.cssj.homare.b.a.c.g;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.p;
/*     */ import jp.cssj.homare.b.c.c;
/*     */ import jp.cssj.homare.b.c.f;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
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
/*     */ public class s
/*     */   extends c
/*     */ {
/*     */   protected boolean J;
/*     */   protected boolean I;
/*  46 */   protected double o = 0.0D; protected double n = 0.0D;
/*     */   
/*     */   protected final E b;
/*     */   protected final i a;
/*     */   private static final boolean L = false;
/*     */   
/*     */   public s(i params, E pos, g container) {
/*  53 */     this(params, pos, params.X, params.Y, new b(params.S), container);
/*     */   }
/*     */ 
/*     */   
/*     */   public s(i params, E pos, m size, m minSize, b frame, g container) {
/*  58 */     super(size, minSize, container);
/*  59 */     this.a = params;
/*  60 */     this.b = pos;
/*  61 */     this.e = frame;
/*     */   }
/*     */   
/*     */   public final byte a() {
/*  65 */     return 12;
/*     */   }
/*     */   
/*     */   public final y b() {
/*  69 */     return (y)this.a;
/*     */   }
/*     */   
/*     */   public final i c_() {
/*  73 */     return this.a;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  77 */     return (z)this.b;
/*     */   }
/*     */   
/*     */   public final E u() {
/*  81 */     return this.b;
/*     */   }
/*     */   
/*     */   public final boolean e_() {
/*  85 */     return false;
/*     */   }
/*     */   
/*     */   public void a(double newSize) {
/*  89 */     if (newSize > this.o) {
/*  90 */       this.o = newSize;
/*     */     }
/*  92 */     super.a(newSize);
/*     */   }
/*     */   
/*     */   public final void b(double width) {
/*  96 */     if (!K && e.a(width)) throw new AssertionError(); 
/*  97 */     this.f = width - this.e.f();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void c(double height) {
/* 103 */     if (!K && e.a(height)) throw new AssertionError(); 
/* 104 */     this.g = height - this.e.e();
/*     */   }
/*     */   public final void v() {
/*     */     double pageSize;
/* 108 */     switch (this.b.g) {
/*     */       case 1:
/*     */       case 2:
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     if (e.a(this.a.D)) {
/* 116 */       pageSize = this.f;
/*     */     } else {
/* 118 */       pageSize = this.g;
/*     */     } 
/* 120 */     double diff = Math.max(0.0D, pageSize - this.o);
/* 121 */     switch (this.b.g) {
/*     */       
/*     */       case 4:
/* 124 */         this.n = diff;
/*     */         return;
/*     */       
/*     */       case 3:
/* 128 */         this.n = diff / 2.0D;
/*     */         return;
/*     */     } 
/* 131 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(double lineSize, r tableBox, jp.cssj.homare.b.e.a spacing) {
/* 136 */     e.b(this.e.c, this.e.a.e, lineSize);
/* 137 */     this.e.b = spacing;
/*     */     
/* 139 */     G tableParams = tableBox.g();
/* 140 */     this.I = (tableParams.ax == 1);
/* 141 */     B frame = this.e.a;
/* 142 */     if (this.I) {
/* 143 */       this.e.a = B.a(frame.b, A.a, frame.d, frame.e);
/*     */     }
/*     */ 
/*     */     
/* 147 */     if (e.a(this.a.D)) {
/* 148 */       switch (this.d.a()) {
/*     */         case 1:
/* 150 */           this.h = this.d.c();
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/* 154 */           this.h = 0.0D;
/*     */           break;
/*     */         default:
/* 157 */           throw new IllegalStateException();
/*     */       } 
/* 159 */       switch (this.a.Z.a()) {
/*     */         case 1:
/* 161 */           this.i = this.a.Z.c();
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/* 165 */           this.i = Double.MAX_VALUE;
/*     */           break;
/*     */         default:
/* 168 */           throw new IllegalStateException();
/*     */       } 
/* 170 */       this.f = this.h;
/*     */     } else {
/* 172 */       switch (this.d.b()) {
/*     */         case 1:
/* 174 */           this.h = this.d.d();
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/* 178 */           this.h = 0.0D;
/*     */           break;
/*     */         default:
/* 181 */           throw new IllegalStateException();
/*     */       } 
/* 183 */       switch (this.a.Z.b()) {
/*     */         case 1:
/* 185 */           this.i = this.a.Z.d();
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/* 189 */           this.i = Double.MAX_VALUE;
/*     */           break;
/*     */         default:
/* 192 */           throw new IllegalStateException();
/*     */       } 
/* 194 */       this.g = this.h;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void d(double rowAscent) {
/* 200 */     if (this.b.g != 1) {
/*     */       return;
/*     */     }
/* 203 */     double firstAscent = n();
/* 204 */     if (e.a(firstAscent)) {
/*     */       return;
/*     */     }
/* 207 */     double xascent = rowAscent - firstAscent;
/* 208 */     if (xascent > 0.0D) {
/* 209 */       this.n += xascent;
/* 210 */       if (e.a(this.a.D)) {
/* 211 */         this.f += xascent;
/*     */       } else {
/* 213 */         this.g += xascent;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final boolean g() {
/* 219 */     return ((u()).d != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/* 224 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/* 227 */     x += this.j;
/* 228 */     y += this.k;
/*     */     
/* 230 */     transform = a(transform, x, y);
/*     */     
/* 232 */     if (w()) {
/*     */ 
/*     */       
/* 235 */       a a = new a(clip, pageBox, this.a.ao, transform, this.e.a.d, this.e.a.c, this.I, this.e.b, p(), q());
/* 236 */       drawer.a((f)a, x, y);
/*     */     } 
/*     */     
/* 239 */     clip = a(clip, x, y);
/*     */     
/* 241 */     x += this.e.b();
/* 242 */     y += this.e.a();
/* 243 */     if (e.a(this.a.D)) {
/* 244 */       x -= this.n;
/*     */     } else {
/* 246 */       y += this.n;
/*     */     } 
/* 248 */     this.l.a(pageBox, drawer, clip, transform, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void b(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 253 */     if (this.a.ao == 0.0F || g() || !this.l.i()) {
/*     */       return;
/*     */     }
/* 256 */     x += this.j;
/* 257 */     y += this.k;
/*     */     
/* 259 */     transform = a(transform, x, y);
/*     */     
/* 261 */     if (this.a.ab == 1)
/*     */     {
/* 263 */       clip = a(clip, x, y);
/*     */     }
/* 265 */     x += this.e.b();
/* 266 */     y += this.e.a();
/* 267 */     if (e.a(this.a.D)) {
/* 268 */       x -= this.n;
/*     */     } else {
/* 270 */       y += this.n;
/*     */     } 
/* 272 */     this.l.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 281 */     if (g()) {
/* 282 */       a(pageBox, drawer, clip, transform, x, y);
/*     */     }
/* 284 */     x += this.j;
/* 285 */     y += this.k;
/*     */     
/* 287 */     transform = a(transform, x, y);
/*     */     
/* 289 */     visitor.a(transform, (j)this, x, y);
/*     */     
/* 291 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/*     */     
/* 295 */     if (this.a.an == 1) {
/* 296 */       g newDrawer = new g(this.a.am);
/* 297 */       drawer.a(newDrawer);
/* 298 */       drawer = newDrawer;
/*     */     } 
/* 300 */     clip = a(clip, x, y);
/*     */     
/* 302 */     x += this.e.b();
/* 303 */     y += this.e.a();
/* 304 */     if (e.a(this.a.D)) {
/* 305 */       x -= this.n;
/*     */     } else {
/* 307 */       y += this.n;
/*     */     } 
/*     */     
/* 310 */     boolean contextBox = g();
/* 311 */     if (contextBox) {
/* 312 */       contextX = x;
/* 313 */       contextY = y;
/* 314 */       this.l.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */     } 
/* 316 */     this.l.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/* 317 */     if (!contextBox) {
/* 318 */       clip = null;
/*     */     }
/* 320 */     this.l.c(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean w() {
/* 327 */     if (!this.e.i()) {
/* 328 */       return false;
/*     */     }
/* 330 */     if (this.b.f == 2) {
/* 331 */       return true;
/*     */     }
/* 333 */     if (this.I) {
/* 334 */       return true;
/*     */     }
/* 336 */     if (this.l.j()) {
/* 337 */       return true;
/*     */     }
/* 339 */     if (this.l.i()) {
/* 340 */       return true;
/*     */     }
/* 342 */     if (this.J) {
/* 343 */       return true;
/*     */     }
/* 345 */     return false;
/*     */   }
/*     */   
/*     */   protected static class a
/*     */     extends c
/*     */   {
/*     */     protected final boolean a;
/*     */     protected final jp.cssj.homare.b.e.a b;
/*     */     
/*     */     public a(Shape clip, n pageBox, float opacity, AffineTransform transform, g background, A border, boolean collapse, jp.cssj.homare.b.e.a spacing, double width, double height) {
/* 355 */       super(pageBox, clip, opacity, transform, background, border, width, height);
/* 356 */       this.a = collapse;
/* 357 */       this.b = spacing;
/*     */     }
/*     */     
/*     */     public void a(b gc, double x, double y) throws c {
/* 361 */       double pbLeft = (this.d.d()).m;
/* 362 */       double pbTop = (this.d.a()).m;
/* 363 */       double pbRight = (this.d.b()).m;
/* 364 */       double pbBottom = (this.d.c()).m;
/* 365 */       if (this.a) {
/* 366 */         this.c.a(gc, x, y, this.e, this.f, pbLeft, pbTop, pbRight, pbBottom, this.d);
/*     */       } else {
/* 368 */         x += this.b.d;
/* 369 */         y += this.b.a;
/* 370 */         double width = this.e - this.b.a();
/* 371 */         double height = this.f - this.b.b();
/* 372 */         if (width >= 0.0D || height >= 0.0D) {
/* 373 */           this.c.a(gc, x, y, width, height, pbLeft, pbTop, pbRight, pbBottom, this.d);
/* 374 */           this.d.a(gc, x, y, width, height);
/*     */         } 
/*     */       } 
/*     */     } }
/*     */   protected final c a(g container, double pageLimit, byte flags) {
/*     */     m nextSize, nextMinSize;
/*     */     b nextFrame;
/* 381 */     boolean vertical = e.a(this.a.D);
/*     */ 
/*     */     
/* 384 */     if (vertical) {
/* 385 */       if (this.c.a() != 3) {
/*     */         
/* 387 */         double width = Math.max(0.0D, this.f - pageLimit);
/* 388 */         nextSize = m.a(this.c.d(), width, this.c.b(), (byte)1);
/*     */       } else {
/*     */         
/* 391 */         nextSize = this.c;
/*     */       } 
/* 393 */       if (this.d.a() != 3) {
/*     */         
/* 395 */         double width = Math.max(0.0D, this.f - pageLimit);
/* 396 */         nextMinSize = m.a(this.d.d(), width, this.d.b(), (byte)1);
/*     */       } else {
/*     */         
/* 399 */         nextMinSize = this.d;
/*     */       } 
/* 401 */       nextFrame = this.e.a(true, false, true, true);
/*     */     } else {
/* 403 */       if (this.c.b() != 3) {
/*     */         
/* 405 */         double height = Math.max(0.0D, this.g - pageLimit);
/* 406 */         nextSize = m.a(this.c.c(), height, this.c.a(), (byte)1);
/*     */       } else {
/*     */         
/* 409 */         nextSize = this.c;
/*     */       } 
/* 411 */       if (this.d.b() != 3) {
/*     */         
/* 413 */         double height = Math.max(0.0D, this.g - pageLimit);
/* 414 */         nextMinSize = m.a(this.d.c(), height, this.d.a(), (byte)1);
/*     */       } else {
/*     */         
/* 417 */         nextMinSize = this.d;
/*     */       } 
/* 419 */       nextFrame = this.e.a(false, true, true, true);
/*     */     } 
/*     */     
/* 422 */     s cell = new s(this.a, this.b, nextSize, nextMinSize, nextFrame, container);
/* 423 */     cell.I = this.I;
/* 424 */     cell.J = w();
/* 425 */     if (vertical) {
/* 426 */       cell.g = this.g;
/* 427 */       this.e = this.e.a(true, true, true, false);
/* 428 */       this.f = pageLimit;
/*     */     } else {
/* 430 */       cell.f = this.f;
/* 431 */       this.e = this.e.a(true, true, false, true);
/* 432 */       this.g = pageLimit;
/*     */     } 
/* 434 */     this.J = w();
/*     */     
/* 436 */     return cell;
/*     */   }
/*     */   
/*     */   public final p b(double pageLimit, d mode, byte flags) {
/* 440 */     if (!K && (flags & 0x2) != 0) throw new AssertionError(); 
/* 441 */     boolean vertical = e.a(this.a.D);
/* 442 */     pageLimit -= this.n;
/* 443 */     s nextBox = (s)super.b(pageLimit, mode, flags);
/*     */ 
/*     */ 
/*     */     
/* 447 */     if (nextBox == null || nextBox == this) {
/* 448 */       if (!K && (flags & 0x4) != 0) throw new AssertionError((nextBox != null)); 
/* 449 */       return (p)nextBox;
/*     */     } 
/* 451 */     if (this.l.i()) {
/* 452 */       if (vertical) {
/* 453 */         pageLimit -= this.e.d();
/*     */       } else {
/* 455 */         pageLimit -= this.e.a();
/*     */       } 
/* 457 */       this.l.a(nextBox.l, pageLimit, flags);
/*     */     } 
/* 459 */     return (p)nextBox;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/s.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */