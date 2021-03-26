/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.b;
/*     */ import jp.cssj.homare.b.a.c.A;
/*     */ import jp.cssj.homare.b.a.c.B;
/*     */ import jp.cssj.homare.b.a.c.G;
/*     */ import jp.cssj.homare.b.a.c.H;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.l;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.o;
/*     */ import jp.cssj.homare.b.a.p;
/*     */ import jp.cssj.homare.b.c.c;
/*     */ import jp.cssj.homare.b.c.f;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.c;
/*     */ import jp.cssj.homare.b.f.e;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class r
/*     */   extends b
/*     */   implements l, o, p
/*     */ {
/*  53 */   protected u d = null;
/*     */   
/*  55 */   protected w e = null;
/*     */   
/*  57 */   protected List<w> f = null;
/*     */   
/*  59 */   protected w g = null;
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected double i = 0.0D;
/*     */   
/*  65 */   protected double j = 0.0D;
/*     */   
/*  67 */   protected double k = 0.0D;
/*     */   
/*  69 */   protected double l = 0.0D; private static final boolean n = false; protected final G a;
/*     */   
/*     */   public r(G params, jp.cssj.homare.b.a.a block) {
/*  72 */     this(params, new jp.cssj.homare.b.e.b(params.S), block);
/*     */   }
/*     */   protected final jp.cssj.homare.b.a.a b; protected jp.cssj.homare.b.e.b c; protected c h;
/*     */   protected r(G params, jp.cssj.homare.b.e.b frame, jp.cssj.homare.b.a.a block) {
/*  76 */     this.a = params;
/*  77 */     this.b = block;
/*  78 */     this.c = frame;
/*     */   }
/*     */   
/*     */   public final byte a() {
/*  82 */     return 7;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  86 */     return (z)H.a;
/*     */   }
/*     */   
/*     */   public final y b() {
/*  90 */     return (y)this.a;
/*     */   }
/*     */   
/*     */   public final G g() {
/*  94 */     return this.a;
/*     */   }
/*     */   
/*     */   public final jp.cssj.homare.b.a.a h() {
/*  98 */     return this.b;
/*     */   }
/*     */   
/*     */   public final jp.cssj.homare.b.e.b i() {
/* 102 */     return this.c;
/*     */   }
/*     */   
/*     */   public final double s() {
/* 106 */     return this.i;
/*     */   }
/*     */   
/*     */   public final double t() {
/* 110 */     return this.j;
/*     */   }
/*     */   
/*     */   public final double p() {
/* 114 */     return this.i + this.c.f();
/*     */   }
/*     */   
/*     */   public final double q() {
/* 118 */     return this.j + this.c.e();
/*     */   }
/*     */   
/*     */   public final void a(double width, double height) {
/* 122 */     this.i = width;
/* 123 */     this.j = height;
/*     */   }
/*     */   
/*     */   public final void a(double lineSize) {
/* 127 */     e.a(this.c.b, this.a.S.b, lineSize);
/* 128 */     if (this.a.ax == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       e.b(this.c.c, this.a.S.e, lineSize);
/* 134 */       this.c.c.a = this.a.aw / 2.0D;
/* 135 */       this.c.c.b = this.a.av / 2.0D;
/* 136 */       this.c.c.c = this.a.aw / 2.0D;
/* 137 */       this.c.c.d = this.a.av / 2.0D;
/*     */     } else {
/* 139 */       this.c.a = B.a(this.a.S.b, A.a, this.a.S.d, this.a.S.e);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(m containerBox) {
/* 146 */     if (this.e != null) {
/* 147 */       this.e.a(containerBox);
/*     */     }
/* 149 */     for (int i = 0; i < m(); i++) {
/* 150 */       w rowGroupBox = a(i);
/* 151 */       rowGroupBox.a(containerBox);
/*     */     } 
/* 153 */     if (this.g != null) {
/* 154 */       this.g.a(containerBox);
/*     */     }
/*     */   }
/*     */   
/*     */   public final void a(c borders) {
/* 159 */     if (!m && borders == null) throw new AssertionError(); 
/* 160 */     this.h = borders;
/*     */   }
/*     */   
/*     */   public final c j() {
/* 164 */     return this.h;
/*     */   }
/*     */   
/*     */   public final void a(u columnGroup) {
/* 168 */     this.d = columnGroup;
/*     */   }
/*     */   
/*     */   public final void a(w headerGroup) {
/* 172 */     this.e = headerGroup;
/* 173 */     if (e.a(this.a.D)) {
/* 174 */       this.i += headerGroup.p();
/* 175 */       if (headerGroup.q() > this.j) {
/* 176 */         this.j = headerGroup.q();
/*     */       }
/*     */     } else {
/* 179 */       this.j += headerGroup.q();
/* 180 */       if (headerGroup.p() > this.i) {
/* 181 */         this.i = headerGroup.p();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public final w k() {
/* 187 */     return this.e;
/*     */   }
/*     */   
/*     */   public final void b(w footerGroup) {
/* 191 */     this.g = footerGroup;
/* 192 */     if (e.a(this.a.D)) {
/* 193 */       this.i += footerGroup.p();
/* 194 */       if (footerGroup.q() > this.j) {
/* 195 */         this.j = footerGroup.q();
/*     */       }
/*     */     } else {
/* 198 */       this.j += footerGroup.q();
/* 199 */       if (footerGroup.p() > this.i) {
/* 200 */         this.i = footerGroup.p();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public final w l() {
/* 206 */     return this.g;
/*     */   }
/*     */   
/*     */   public final void c(w rowGroupBox) {
/* 210 */     if (this.f == null) {
/* 211 */       this.f = new ArrayList<>();
/*     */     }
/* 213 */     this.f.add(rowGroupBox);
/* 214 */     if (e.a(this.a.D)) {
/* 215 */       this.i += rowGroupBox.p();
/* 216 */       if (rowGroupBox.q() > this.j) {
/* 217 */         this.j = rowGroupBox.q();
/*     */       }
/*     */     } else {
/* 220 */       this.j += rowGroupBox.q();
/* 221 */       if (rowGroupBox.p() > this.i) {
/* 222 */         this.i = rowGroupBox.p();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public final w a(int i) {
/* 228 */     return this.f.get(i);
/*     */   }
/*     */   
/*     */   public final int m() {
/* 232 */     return (this.f == null) ? 0 : this.f.size();
/*     */   }
/*     */   
/*     */   private void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y, double xx, double yy) {
/*     */     b b1;
/* 237 */     switch (this.a.ax) {
/*     */       case 0:
/* 239 */         if (this.c.a.c.k()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 246 */           a a1 = new a(pageBox, clip, this.a.ao, transform, this.c.a.c, this.i + this.c.c.a() + this.c.a.c.i(), this.j + this.c.c.b() + this.c.a.c.j());
/* 247 */           drawer.a((f)a1, x + this.c.b.d, y + this.c.b.a);
/*     */         } 
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 254 */         b1 = new b(pageBox, clip, this.a.ao, transform, this.h, e.a(this.a.D));
/* 255 */         drawer.a((f)b1, xx, yy);
/*     */         return;
/*     */     } 
/*     */     
/* 259 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 265 */     if (!m && e.a(x)) throw new AssertionError("Undefined x"); 
/* 266 */     if (!m && e.a(y)) throw new AssertionError("Undefined y"); 
/* 267 */     x += this.k;
/* 268 */     y += this.l;
/*     */     
/* 270 */     visitor.a(transform, (j)this, x, y);
/*     */     
/* 272 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/* 275 */     double xx = x + this.c.b();
/* 276 */     double yy = y + this.c.a();
/*     */     
/* 278 */     if (this.a.an == 1) {
/* 279 */       g newDrawer = new g(this.a.am);
/* 280 */       drawer.a(newDrawer);
/* 281 */       drawer = newDrawer;
/*     */     } 
/*     */     
/* 284 */     if (this.a.S.d.c()) {
/*     */ 
/*     */       
/* 287 */       c c1 = new c(pageBox, clip, this.a.ao, transform, this.a.S.d, this.a.S.c, p() - this.c.f(), q() - this.c.e());
/* 288 */       drawer.a((f)c1, xx, yy);
/*     */     } 
/*     */     
/* 291 */     if (e.a(this.a.D)) {
/*     */ 
/*     */ 
/*     */       
/* 295 */       double xxx = xx;
/* 296 */       if (this.d != null) {
/* 297 */         this.d.a(pageBox, drawer, clip, transform, xxx, yy);
/*     */       }
/* 299 */       xxx += this.i;
/* 300 */       if (this.e != null) {
/* 301 */         xxx -= this.e.p();
/* 302 */         this.e.a(pageBox, drawer, clip, transform, xxx, yy);
/*     */       } 
/* 304 */       if (this.f != null) {
/* 305 */         for (int i = 0; i < this.f.size(); i++) {
/* 306 */           w rowGroup = this.f.get(i);
/* 307 */           xxx -= rowGroup.p();
/* 308 */           rowGroup.a(pageBox, drawer, clip, transform, xxx, yy);
/*     */         } 
/*     */       }
/* 311 */       if (this.g != null) {
/* 312 */         xxx -= this.g.p();
/* 313 */         this.g.a(pageBox, drawer, clip, transform, xxx, yy);
/*     */       } 
/*     */ 
/*     */       
/* 317 */       a(pageBox, drawer, clip, transform, x, y, xx, yy);
/*     */ 
/*     */ 
/*     */       
/* 321 */       xxx = xx + this.i;
/* 322 */       if (this.e != null) {
/* 323 */         xxx -= this.e.p();
/* 324 */         this.e.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */       } 
/* 326 */       if (this.f != null) {
/* 327 */         for (int i = 0; i < this.f.size(); i++) {
/* 328 */           w rowGroup = this.f.get(i);
/* 329 */           xxx -= rowGroup.p();
/* 330 */           rowGroup.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */         } 
/*     */       }
/* 333 */       if (this.g != null) {
/* 334 */         xxx -= this.g.p();
/* 335 */         this.g.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 341 */       xxx = xx;
/* 342 */       if (this.d != null) {
/* 343 */         this.d.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */       }
/* 345 */       xxx += this.i;
/* 346 */       if (this.e != null) {
/* 347 */         xxx -= this.e.p();
/* 348 */         this.e.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */       } 
/* 350 */       if (this.f != null) {
/* 351 */         for (int i = 0; i < this.f.size(); i++) {
/* 352 */           w rowGroup = this.f.get(i);
/* 353 */           xxx -= rowGroup.p();
/* 354 */           rowGroup.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */         } 
/*     */       }
/* 357 */       if (this.g != null) {
/* 358 */         xxx -= this.g.p();
/* 359 */         this.g.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yy);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 366 */       double yyy = yy;
/* 367 */       if (this.d != null) {
/* 368 */         this.d.a(pageBox, drawer, clip, transform, xx, yyy);
/*     */       }
/* 370 */       if (this.e != null) {
/* 371 */         this.e.a(pageBox, drawer, clip, transform, xx, yyy);
/* 372 */         yyy += this.e.q();
/*     */       } 
/* 374 */       if (this.f != null) {
/* 375 */         for (int i = 0; i < this.f.size(); i++) {
/* 376 */           w rowGroup = this.f.get(i);
/* 377 */           rowGroup.a(pageBox, drawer, clip, transform, xx, yyy);
/* 378 */           yyy += rowGroup.q();
/*     */         } 
/*     */       }
/* 381 */       if (this.g != null) {
/* 382 */         this.g.a(pageBox, drawer, clip, transform, xx, yyy);
/* 383 */         yyy += this.g.q();
/*     */       } 
/*     */ 
/*     */       
/* 387 */       a(pageBox, drawer, clip, transform, x, y, xx, yy);
/*     */ 
/*     */ 
/*     */       
/* 391 */       yyy = yy;
/* 392 */       if (this.e != null) {
/* 393 */         this.e.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/* 394 */         yyy += this.e.q();
/*     */       } 
/* 396 */       if (this.f != null) {
/* 397 */         for (int i = 0; i < this.f.size(); i++) {
/* 398 */           w rowGroup = this.f.get(i);
/* 399 */           rowGroup.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/* 400 */           yyy += rowGroup.q();
/*     */         } 
/*     */       }
/* 403 */       if (this.g != null) {
/* 404 */         this.g.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/* 405 */         yyy += this.g.q();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 411 */       yyy = yy;
/* 412 */       if (this.d != null) {
/* 413 */         this.d.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/*     */       }
/* 415 */       if (this.e != null) {
/* 416 */         this.e.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/* 417 */         yyy += this.e.q();
/*     */       } 
/* 419 */       if (this.f != null) {
/* 420 */         for (int i = 0; i < this.f.size(); i++) {
/* 421 */           w rowGroup = this.f.get(i);
/* 422 */           rowGroup.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/* 423 */           yyy += rowGroup.q();
/*     */         } 
/*     */       }
/* 426 */       if (this.g != null) {
/* 427 */         this.g.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yyy);
/* 428 */         yyy += this.g.q();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 439 */     if (this.e != null) {
/* 440 */       this.e.a(textBuff);
/*     */     }
/* 442 */     if (this.f != null) {
/* 443 */       for (int i = 0; i < this.f.size(); i++) {
/* 444 */         w rowGroup = this.f.get(i);
/* 445 */         rowGroup.a(textBuff);
/*     */       } 
/*     */     }
/* 448 */     if (this.g != null)
/* 449 */       this.g.a(textBuff); 
/*     */   }
/*     */   
/*     */   protected static class a
/*     */     extends jp.cssj.homare.b.c.b {
/*     */     protected final A a;
/*     */     protected final double b;
/*     */     protected final double c;
/*     */     
/*     */     public a(n pageBox, Shape clip, float opacity, AffineTransform transform, A border, double width, double height) {
/* 459 */       super(pageBox, clip, opacity, transform);
/* 460 */       this.a = border;
/* 461 */       this.b = width;
/* 462 */       this.c = height;
/*     */     }
/*     */     
/*     */     public void a(jp.cssj.sakae.c.b gc, double x, double y) throws c {
/* 466 */       this.a.a(gc, x, y, this.b, this.c);
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class b
/*     */     extends jp.cssj.homare.b.c.b {
/*     */     protected final c a;
/*     */     protected final boolean b;
/*     */     
/*     */     public b(n pageBox, Shape clip, float opacity, AffineTransform transform, c borders, boolean vertical) {
/* 476 */       super(pageBox, clip, opacity, transform);
/* 477 */       this.a = borders;
/* 478 */       this.b = vertical;
/*     */     }
/*     */     
/*     */     public void a(jp.cssj.sakae.c.b gc, double x, double y) throws c {
/* 482 */       jp.cssj.homare.b.f.a.a.a(gc, this.a, x, y, this.b);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public final p b(double pageLimit, d mode, byte flags) {
/* 488 */     if (!m && (flags & 0x2) != 0) throw new AssertionError();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 495 */     boolean vertical = e.a(this.a.D);
/* 496 */     int origBodyRowCount = 0;
/* 497 */     if (this.h != null && this.f != null) {
/* 498 */       for (int k = 0; k < this.f.size(); k++) {
/* 499 */         origBodyRowCount += a(k).h();
/*     */       }
/*     */     }
/* 502 */     if (mode.a() == 1) {
/*     */       
/* 504 */       d.c force = (d.c)mode;
/* 505 */       r r1 = n();
/* 506 */       int rowGroup = force.g;
/* 507 */       int row = force.h;
/* 508 */       if (row != -1) {
/* 509 */         if (!m && force.d.a() != 11 && force.d.a() != 10) throw new AssertionError(); 
/* 510 */         w rowGroupBox = this.f.get(rowGroup);
/* 511 */         w newRowGroupBox = (w)rowGroupBox.b(pageLimit, mode, (byte)0);
/*     */         
/* 513 */         r1.c(newRowGroupBox);
/* 514 */         if (vertical) {
/* 515 */           this.i -= newRowGroupBox.f();
/*     */         } else {
/* 517 */           this.j -= newRowGroupBox.f();
/*     */         } 
/*     */       }  int k;
/* 520 */       for (k = rowGroup + 1; k < this.f.size(); k++) {
/* 521 */         r1.c(this.f.get(k));
/*     */       }
/* 523 */       for (k = this.f.size() - 1; k > rowGroup; k--) {
/* 524 */         w rowGroupBox = this.f.remove(k);
/* 525 */         if (vertical) {
/* 526 */           this.i -= rowGroupBox.f();
/*     */         } else {
/* 528 */           this.j -= rowGroupBox.f();
/*     */         } 
/*     */       } 
/* 531 */       if (this.h != null)
/*     */       {
/* 533 */         r1.h = this.h.a(this, r1, origBodyRowCount);
/*     */       }
/* 535 */       return r1;
/*     */     } 
/*     */     
/* 538 */     if (this.f == null || this.f.isEmpty()) {
/* 539 */       if ((flags & 0x1) != 0) {
/* 540 */         return null;
/*     */       }
/*     */       
/* 543 */       return this;
/*     */     } 
/*     */     
/* 546 */     if (vertical) {
/*     */       
/* 548 */       double over = p() - pageLimit;
/* 549 */       pageLimit -= this.c.d();
/* 550 */       if (this.e != null) {
/* 551 */         pageLimit -= this.e.f();
/*     */       }
/* 553 */       if (this.g != null) {
/* 554 */         pageLimit -= this.g.f();
/* 555 */         pageLimit -= this.c.b();
/*     */       
/*     */       }
/* 558 */       else if (over > 0.0D && e.a(over, this.c.b.d) < 0) {
/* 559 */         pageLimit -= this.c.b.d;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 564 */       double over = q() - pageLimit;
/* 565 */       pageLimit -= this.c.a();
/* 566 */       if (this.e != null) {
/* 567 */         pageLimit -= this.e.f();
/*     */       }
/* 569 */       if (this.g != null) {
/* 570 */         pageLimit -= this.g.f();
/* 571 */         pageLimit -= this.c.c();
/*     */       
/*     */       }
/* 574 */       else if (over > 0.0D && e.a(over, this.c.b.c) < 0) {
/* 575 */         pageLimit -= this.c.b.c;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 583 */     if (e.a(pageLimit, 0.0D) <= 0) {
/* 584 */       if ((flags & 0x1) != 0) {
/* 585 */         return null;
/*     */       }
/*     */       
/* 588 */       return this;
/*     */     } 
/*     */     
/* 591 */     r nextTable = null;
/*     */     
/* 593 */     boolean ignoreBreakAvoid = false;
/* 594 */     double savePageLimit = pageLimit;
/*     */     
/*     */     int i;
/* 597 */     for (i = 0; i < this.f.size(); i++) {
/* 598 */       w prevRowGroup = this.f.get(i);
/* 599 */       double prevRowGroupSize = prevRowGroup.f();
/* 600 */       if (i < this.f.size() - 1 && e.a(pageLimit, prevRowGroupSize) > 0) {
/* 601 */         pageLimit -= prevRowGroupSize;
/*     */         continue;
/*     */       } 
/* 604 */       byte lflags = 5;
/* 605 */       if (i > 0) {
/* 606 */         lflags = (byte)(lflags ^ 0x1);
/*     */       }
/* 608 */       w nextRowGroup = (w)prevRowGroup.b(pageLimit, mode, (byte)(lflags & flags));
/*     */       
/* 610 */       if (!m && nextTable != null && nextRowGroup == null) throw new AssertionError();
/*     */ 
/*     */ 
/*     */       
/* 614 */       if (nextRowGroup == null) {
/* 615 */         if (!ignoreBreakAvoid && i == 0 && (flags & 0x1) != 0) {
/*     */           
/* 617 */           ignoreBreakAvoid = true;
/* 618 */           pageLimit = savePageLimit;
/* 619 */           i = -1;
/*     */         } else {
/*     */           
/* 622 */           pageLimit -= prevRowGroupSize;
/*     */         }  continue;
/*     */       } 
/* 625 */       if (nextRowGroup == prevRowGroup) {
/* 626 */         if (i == 0) {
/*     */           
/* 628 */           if (!m && (flags & 0x1) != 0) throw new AssertionError(); 
/* 629 */           return this;
/*     */         } 
/* 631 */         if (!ignoreBreakAvoid) {
/* 632 */           w beforeGroup = this.f.get(i - 1);
/*     */           
/* 634 */           boolean breakAvoid = ((beforeGroup.g()).b == 1 || (prevRowGroup.g()).a == 1);
/* 635 */           if (!breakAvoid && beforeGroup.h() > 0)
/*     */           {
/* 637 */             breakAvoid = ((beforeGroup.a(beforeGroup.h() - 1).g()).b == 1);
/*     */           }
/* 639 */           if (!breakAvoid && prevRowGroup.h() > 0)
/*     */           {
/* 641 */             breakAvoid = ((prevRowGroup.a(0).g()).a == 1);
/*     */           }
/* 643 */           if (breakAvoid) {
/*     */ 
/*     */             
/* 646 */             pageLimit = beforeGroup.f() - 1.0D;
/* 647 */             i -= 2;
/*     */             continue;
/*     */           } 
/*     */         } 
/* 651 */         nextTable = n();
/*     */         break;
/*     */       } 
/* 654 */       nextTable = n();
/* 655 */       prevRowGroupSize -= prevRowGroup.f();
/* 656 */       if (vertical) {
/* 657 */         this.i -= prevRowGroupSize;
/*     */       } else {
/* 659 */         this.j -= prevRowGroupSize;
/*     */       } 
/* 661 */       nextTable.c(nextRowGroup);
/* 662 */       i++;
/*     */     } 
/*     */ 
/*     */     
/* 666 */     if (nextTable == null) {
/* 667 */       if ((flags & 0x1) != 0) {
/* 668 */         return null;
/*     */       }
/* 670 */       return this;
/*     */     } 
/*     */     
/* 673 */     int remove = 0; int j;
/* 674 */     for (j = i; j < this.f.size(); j++) {
/* 675 */       w prevRowGroup = this.f.get(j);
/* 676 */       if (vertical) {
/* 677 */         this.i -= prevRowGroup.f();
/*     */       } else {
/* 679 */         this.j -= prevRowGroup.f();
/*     */       } 
/* 681 */       nextTable.c(prevRowGroup);
/* 682 */       remove++;
/*     */     } 
/* 684 */     for (j = 0; j < remove; j++) {
/* 685 */       this.f.remove(this.f.size() - 1);
/*     */     }
/* 687 */     if (this.d != null)
/*     */     {
/* 689 */       if (vertical) {
/* 690 */         nextTable.d = (u)this.d.a(this.i, nextTable.i);
/*     */       } else {
/*     */         
/* 693 */         nextTable.d = (u)this.d.a(this.j, nextTable.j);
/*     */       } 
/*     */     }
/*     */     
/* 697 */     if (this.h != null)
/*     */     {
/* 699 */       nextTable.h = this.h.a(this, nextTable, origBodyRowCount);
/*     */     }
/* 701 */     return nextTable;
/*     */   }
/*     */   public final r n() {
/*     */     jp.cssj.homare.b.e.b nextFrame;
/* 705 */     boolean vertical = e.a(this.a.D);
/*     */     
/* 707 */     if (this.e != null) {
/* 708 */       nextFrame = this.c;
/*     */     }
/* 710 */     else if (vertical) {
/* 711 */       nextFrame = this.c.a(true, false, true, true);
/*     */     } else {
/* 713 */       nextFrame = this.c.a(false, true, true, true);
/*     */     } 
/*     */     
/* 716 */     r nextTable = new r(this.a, nextFrame, this.b);
/* 717 */     if (vertical) {
/* 718 */       nextTable.j = this.j;
/*     */     } else {
/* 720 */       nextTable.i = this.i;
/*     */     } 
/*     */     
/* 723 */     if (this.e != null) {
/* 724 */       nextTable.a(this.e);
/*     */     }
/* 726 */     if (this.g != null) {
/* 727 */       nextTable.b(this.g);
/*     */     }
/* 729 */     else if (vertical) {
/* 730 */       this.c = this.c.a(true, true, true, false);
/*     */     } else {
/* 732 */       this.c = this.c.a(true, true, false, true);
/*     */     } 
/*     */     
/* 735 */     return nextTable;
/*     */   }
/*     */   
/*     */   public final boolean c() {
/* 739 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean f() {
/* 743 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/r.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */