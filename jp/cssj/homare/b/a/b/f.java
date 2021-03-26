/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.a;
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.e;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.p;
/*     */ import jp.cssj.homare.b.a.c.t;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.g;
/*     */ import jp.cssj.homare.b.a.l;
/*     */ import jp.cssj.homare.b.b.a.a;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.a;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.b.g.a;
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
/*     */ public class f
/*     */   extends g
/*     */   implements l
/*     */ {
/*     */   private static final boolean L = false;
/*     */   protected final p I;
/*     */   protected double J;
/*     */   
/*     */   public f(i params, p pos) {
/*  44 */     super(params);
/*  45 */     this.I = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   protected f(i params, p pos, m size, m minSize, b frame, g container) {
/*  50 */     super(params, size, minSize, frame, container);
/*  51 */     this.I = pos;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  55 */     return (z)this.I;
/*     */   }
/*     */   
/*     */   public final e u() {
/*  59 */     return (e)this.I;
/*     */   }
/*     */   
/*     */   public final p v() {
/*  63 */     return this.I;
/*     */   }
/*     */   
/*     */   public final void a(d layoutStack, double minLineAxis, double maxLineAxis, boolean table) {
/*  67 */     super.a(layoutStack, minLineAxis, maxLineAxis, table);
/*     */     
/*  69 */     if (!table) {
/*     */       return;
/*     */     }
/*     */     
/*  73 */     a builder = (a)layoutStack;
/*  74 */     c containerBox = (builder.a(builder.g() - 2)).a;
/*     */     
/*  76 */     byte align = this.I.g;
/*  77 */     if (e.a((containerBox.c_()).D)) {
/*     */       
/*  79 */       if (align == 1) {
/*  80 */         t margin = (c_()).S.b;
/*  81 */         if (margin.a() == 3) {
/*  82 */           if (margin.c() == 3) {
/*  83 */             align = 3;
/*     */           } else {
/*  85 */             align = 2;
/*     */           } 
/*     */         }
/*     */       } 
/*  89 */       double remainder = containerBox.h() - this.g;
/*  90 */       switch (align) {
/*     */         case 1:
/*  92 */           this.e.b.c = remainder;
/*     */           break;
/*     */         case 2:
/*  95 */           this.e.b.a = remainder;
/*     */           break;
/*     */         case 3:
/*  98 */           this.e.b.a = this.e.b.c = remainder / 2.0D;
/*     */           break;
/*     */       } 
/*     */       
/* 102 */       this.c = m.a(0.0D, this.g, (byte)3, (byte)1);
/*     */     } else {
/*     */       
/* 105 */       if (align == 1) {
/* 106 */         t margin = (c_()).S.b;
/* 107 */         if (margin.d() == 3) {
/* 108 */           if (margin.b() == 3) {
/* 109 */             align = 3;
/*     */           } else {
/* 111 */             align = 2;
/*     */           } 
/*     */         }
/*     */       } 
/* 115 */       double remainder = containerBox.h() - this.f;
/* 116 */       switch (align) {
/*     */         case 1:
/* 118 */           this.e.b.b = remainder;
/*     */           break;
/*     */         case 2:
/* 121 */           this.e.b.d = remainder;
/*     */           break;
/*     */         case 3:
/* 124 */           this.e.b.d = this.e.b.b = remainder / 2.0D;
/*     */           break;
/*     */       } 
/*     */       
/* 128 */       this.c = m.a(this.f, 0.0D, (byte)1, (byte)3);
/*     */     } 
/* 130 */     this.I.g = align;
/*     */   }
/*     */   
/*     */   public final void a(double newSize) {
/* 134 */     this.J = Math.max(this.J, newSize);
/*     */     
/* 136 */     if (e.a(this.a.D)) {
/*     */       
/* 138 */       if (newSize <= this.f) {
/*     */         return;
/*     */       }
/* 141 */       if ((e_() || i() > 1) && newSize < this.f) {
/*     */         return;
/*     */       }
/* 144 */       this.f = Math.max(this.h, newSize);
/* 145 */       this.f = Math.min(this.i, this.f);
/*     */     } else {
/*     */       
/* 148 */       if (newSize == this.g) {
/*     */         return;
/*     */       }
/* 151 */       if ((e_() || i() > 1) && newSize < this.g) {
/*     */         return;
/*     */       }
/* 154 */       this.g = Math.max(this.h, newSize);
/* 155 */       this.g = Math.min(this.i, this.g);
/*     */     } 
/*     */   }
/*     */   public void a(d layoutStack, double xmargin, double lineSize) {
/*     */     double marginLeft, marginRight, marginTop, marginBottom;
/* 160 */     c containerBox = layoutStack.k();
/* 161 */     i cParams = containerBox.c_();
/* 162 */     if (e.a(this.a.D)) {
/*     */       
/* 164 */       this
/* 165 */         .n = (this.c.a() == 1 || (this.c.a() == 2 && containerBox.e_()));
/*     */     } else {
/*     */       
/* 168 */       this
/* 169 */         .n = (this.c.b() == 1 || (this.c.b() == 2 && containerBox.e_()));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     e.b(this.e.c, this.e.a.e, lineSize);
/*     */ 
/*     */ 
/*     */     
/* 179 */     e.a(this.e.b, this.e.a.b, lineSize);
/*     */     
/* 181 */     t margin = this.e.a.b;
/* 182 */     a amargin = this.e.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     double minWidth = 1.722773839210782E308D, maxWidth = 1.722773839210782E308D, minHeight = 1.722773839210782E308D;
/* 191 */     double maxHeight = 1.722773839210782E308D;
/* 192 */     if (e.a(cParams.D)) {
/*     */       
/* 194 */       marginLeft = amargin.d;
/* 195 */       marginRight = amargin.b;
/* 196 */       this.g = e.b(this.c, lineSize);
/* 197 */       if (this.a.aa == 2 && !e.a(this.g)) {
/* 198 */         this.g -= this.e.g();
/*     */       }
/* 200 */       marginTop = marginBottom = 0.0D;
/* 201 */       for (int state = 0; state < 2; state++) {
/*     */ 
/*     */         
/* 204 */         marginTop = (margin.a() == 3) ? 1.722773839210782E308D : amargin.a;
/* 205 */         marginBottom = (margin.c() == 3) ? 1.722773839210782E308D : amargin.c;
/*     */ 
/*     */         
/* 208 */         marginTop = marginBottom = (lineSize - this.g - this.e.e()) / 2.0D;
/* 209 */         if (e.a(marginTop)) {
/*     */           
/* 211 */           marginTop = lineSize - this.g - this.e.e();
/* 212 */         } else if (e.a(marginBottom)) {
/*     */           
/* 214 */           marginBottom = lineSize - marginBottom - this.e.e();
/*     */         } else {
/*     */           double remainder;
/* 217 */           p pos = v();
/* 218 */           switch (pos.g) {
/*     */             
/*     */             case 1:
/* 221 */               marginBottom = 0.0D;
/*     */               break;
/*     */             
/*     */             case 2:
/* 225 */               marginTop += lineSize - this.g - this.e.e();
/*     */               break;
/*     */             
/*     */             case 3:
/* 229 */               remainder = lineSize - this.g - this.e.e();
/* 230 */               remainder /= 2.0D;
/* 231 */               marginTop += remainder;
/* 232 */               marginBottom += remainder;
/*     */               break;
/*     */             default:
/* 235 */               throw new IllegalStateException();
/*     */           } 
/*     */ 
/*     */         
/*     */         } 
/* 240 */         marginTop = amargin.a;
/* 241 */         marginBottom = amargin.c;
/* 242 */         this.g = lineSize - this.e.e();
/*     */         
/* 244 */         switch (state) {
/*     */           case 0:
/* 246 */             maxHeight = e.b(this.a.Z, lineSize);
/* 247 */             if (e.a(maxHeight)) {
/* 248 */               maxHeight = Double.MAX_VALUE;
/* 249 */             } else if (this.g > maxHeight) {
/* 250 */               this.g = maxHeight;
/*     */               break;
/*     */             } 
/* 253 */             state = 1;
/*     */           case 1:
/* 255 */             minHeight = e.b(this.d, lineSize);
/* 256 */             if (this.g < minHeight) {
/* 257 */               this.g = minHeight;
/*     */               break;
/*     */             } 
/* 260 */             state = 2;
/*     */             break;
/*     */         } 
/*     */       } 
/* 264 */       if (!K && e.a(minHeight)) throw new AssertionError(); 
/* 265 */       if (!K && e.a(maxHeight)) throw new AssertionError(); 
/* 266 */       switch (this.d.a()) {
/*     */         case 2:
/* 268 */           if (e_()) {
/* 269 */             minWidth = this.d.c() * containerBox.s();
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 273 */           minWidth = 0.0D;
/*     */           break;
/*     */         case 1:
/* 276 */           minWidth = this.d.c();
/*     */           break;
/*     */         default:
/* 279 */           throw new IllegalStateException();
/*     */       } 
/* 281 */       switch (this.a.Z.a()) {
/*     */         case 2:
/* 283 */           if (e_()) {
/* 284 */             maxWidth = this.a.Z.c() * containerBox.s();
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 288 */           maxWidth = Double.MAX_VALUE;
/*     */           break;
/*     */         case 1:
/* 291 */           maxWidth = this.a.Z.c();
/*     */           break;
/*     */         default:
/* 294 */           throw new IllegalStateException();
/*     */       } 
/* 296 */       switch (this.c.a()) {
/*     */         case 2:
/* 298 */           if (e_()) {
/* 299 */             this.f = this.c.c() * containerBox.s();
/* 300 */             this.f = Math.max(this.f, minWidth);
/* 301 */             this.f = Math.min(this.f, maxWidth);
/* 302 */             if (this.a.aa == 2) {
/* 303 */               this.f -= m().h();
/*     */             }
/* 305 */             minWidth = maxWidth = this.f;
/*     */           } 
/*     */         
/*     */         case 3:
/* 309 */           if (!e.a(this.a.D)) {
/*     */             
/* 311 */             this.f = layoutStack.n() - this.e.f();
/*     */           } else {
/* 313 */             this.f = 0.0D;
/*     */           } 
/* 315 */           this.f = Math.max(this.f, minWidth);
/*     */           break;
/*     */         case 1:
/* 318 */           this.f = this.c.c();
/* 319 */           this.f = Math.max(this.f, minWidth);
/* 320 */           this.f = Math.min(this.f, maxWidth);
/* 321 */           if (this.a.aa == 2) {
/* 322 */             this.f -= m().h();
/*     */           }
/* 324 */           minWidth = maxWidth = this.f;
/*     */         
/*     */         default:
/* 327 */           throw new IllegalStateException();
/*     */       } 
/* 329 */       marginTop += xmargin;
/*     */     } else {
/*     */       
/* 332 */       marginTop = amargin.a;
/* 333 */       marginBottom = amargin.c;
/* 334 */       this.f = e.a(this.c, lineSize);
/* 335 */       if (this.a.aa == 2 && !e.a(this.f)) {
/* 336 */         this.f -= this.e.h();
/*     */       }
/* 338 */       marginLeft = marginRight = 0.0D;
/* 339 */       for (int state = 0; state < 2; state++) {
/*     */ 
/*     */         
/* 342 */         marginLeft = (margin.d() == 3) ? 1.722773839210782E308D : amargin.d;
/* 343 */         marginRight = (margin.b() == 3) ? 1.722773839210782E308D : amargin.b;
/*     */ 
/*     */         
/* 346 */         marginLeft = marginRight = (lineSize - this.f - this.e.f()) / 2.0D;
/*     */         
/* 348 */         if (e.a(marginLeft) && !e.a(marginRight)) {
/*     */           
/* 350 */           marginLeft = lineSize - this.f - this.e.f();
/* 351 */         } else if (e.a(marginRight)) {
/*     */           
/* 353 */           marginRight = lineSize - this.f - this.e.f();
/*     */         } else {
/*     */           double remainder;
/* 356 */           p pos = v();
/* 357 */           switch (pos.g) {
/*     */             
/*     */             case 1:
/* 360 */               marginRight = 0.0D;
/*     */               break;
/*     */             
/*     */             case 2:
/* 364 */               marginLeft += lineSize - this.f - this.e.f();
/*     */               break;
/*     */             
/*     */             case 3:
/* 368 */               remainder = lineSize - this.f - this.e.f();
/* 369 */               remainder /= 2.0D;
/* 370 */               marginLeft += remainder;
/* 371 */               marginRight += remainder;
/*     */               break;
/*     */             default:
/* 374 */               throw new IllegalStateException();
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         } 
/* 380 */         marginLeft = amargin.d;
/* 381 */         marginRight = amargin.b;
/* 382 */         this.f = lineSize - this.e.f();
/*     */         
/* 384 */         switch (state) {
/*     */           case 0:
/* 386 */             maxWidth = e.a(this.a.Z, lineSize);
/* 387 */             if (e.a(maxWidth)) {
/* 388 */               maxWidth = Double.MAX_VALUE;
/* 389 */             } else if (this.f > maxWidth) {
/* 390 */               this.f = maxWidth;
/*     */               break;
/*     */             } 
/* 393 */             state = 1;
/*     */           case 1:
/* 395 */             minWidth = e.a(this.d, lineSize);
/* 396 */             if (this.f < minWidth) {
/* 397 */               this.f = minWidth;
/*     */               break;
/*     */             } 
/* 400 */             state = 2;
/*     */             break;
/*     */         } 
/*     */       } 
/* 404 */       if (!K && e.a(minWidth)) throw new AssertionError(); 
/* 405 */       if (!K && e.a(maxWidth)) throw new AssertionError(); 
/* 406 */       switch (this.d.b()) {
/*     */         case 2:
/* 408 */           if (e_()) {
/* 409 */             minHeight = this.d.d() * containerBox.t();
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 413 */           minHeight = 0.0D;
/*     */           break;
/*     */         case 1:
/* 416 */           minHeight = this.d.d();
/*     */           break;
/*     */         default:
/* 419 */           throw new IllegalStateException();
/*     */       } 
/* 421 */       switch (this.a.Z.b()) {
/*     */         case 2:
/* 423 */           if (e_()) {
/* 424 */             maxHeight = this.a.Z.d() * containerBox.t();
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 428 */           maxHeight = Double.MAX_VALUE;
/*     */           break;
/*     */         case 1:
/* 431 */           maxHeight = this.a.Z.d();
/*     */           break;
/*     */         default:
/* 434 */           throw new IllegalStateException();
/*     */       } 
/* 436 */       switch (this.c.b()) {
/*     */         case 2:
/* 438 */           if (e_()) {
/* 439 */             this.g = this.c.d() * containerBox.t();
/* 440 */             this.g = Math.max(this.g, minHeight);
/* 441 */             this.g = Math.min(this.g, maxHeight);
/* 442 */             if (this.a.aa == 2) {
/* 443 */               this.g -= m().g();
/*     */             }
/* 445 */             minHeight = this.g;
/* 446 */             maxHeight = this.g;
/*     */             break;
/*     */           } 
/*     */         case 3:
/* 450 */           if (e.a(this.a.D)) {
/*     */             
/* 452 */             this.g = layoutStack.o() - this.e.e();
/*     */           } else {
/* 454 */             this.g = 0.0D;
/*     */           } 
/* 456 */           this.g = Math.max(this.g, minHeight);
/*     */           break;
/*     */         case 1:
/* 459 */           this.g = this.c.d();
/* 460 */           this.g = Math.max(this.g, minHeight);
/* 461 */           this.g = Math.min(this.g, maxHeight);
/* 462 */           if (this.a.aa == 2) {
/* 463 */             this.g -= m().g();
/*     */           }
/* 465 */           minHeight = this.g;
/*     */           
/* 467 */           maxHeight = this.g;
/*     */           break;
/*     */         default:
/* 470 */           throw new IllegalStateException();
/*     */       } 
/* 472 */       marginLeft += xmargin;
/*     */     } 
/* 474 */     if (e.a(this.a.D)) {
/* 475 */       this.h = minWidth;
/* 476 */       this.i = maxWidth;
/*     */     } else {
/* 478 */       this.h = minHeight;
/* 479 */       this.i = maxHeight;
/*     */     } 
/* 481 */     if (!K && e.a(marginTop)) throw new AssertionError(); 
/* 482 */     if (!K && e.a(marginRight)) throw new AssertionError(); 
/* 483 */     if (!K && e.a(marginBottom)) throw new AssertionError(); 
/* 484 */     if (!K && e.a(marginLeft)) throw new AssertionError(); 
/* 485 */     this.e.b.a = marginTop;
/* 486 */     this.e.b.b = marginRight;
/* 487 */     this.e.b.c = marginBottom;
/* 488 */     this.e.b.d = marginLeft;
/* 489 */     if (!K && e.a(this.f)) throw new AssertionError(); 
/* 490 */     if (!K && e.a(this.g)) throw new AssertionError(); 
/*     */   }
/*     */   
/*     */   public final double w() {
/* 494 */     return this.J;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 504 */     if (this.a.an == 1) {
/* 505 */       g newDrawer = new g(this.a.am);
/* 506 */       drawer.a(newDrawer);
/* 507 */       drawer = newDrawer;
/*     */     } 
/*     */     
/* 510 */     if ((v()).d != null) {
/* 511 */       a(pageBox, drawer, clip, transform, x, y);
/*     */     }
/* 513 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   protected a a(m nextSize, m nextMinSize, b nextFrame, g container) {
/* 518 */     i params = c_();
/*     */     
/* 520 */     return (a)new f(params, v(), nextSize, nextMinSize, nextFrame, container);
/*     */   }
/*     */   
/*     */   public final void a(a builder, int depth) {
/* 524 */     builder.a(this);
/* 525 */     super.a(builder, depth);
/* 526 */     if (depth > 0) {
/*     */       return;
/*     */     }
/* 529 */     builder.e();
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 533 */     if ((v()).a == 1) {
/* 534 */       return true;
/*     */     }
/* 536 */     return this.l.e();
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 540 */     if ((v()).b == 1) {
/* 541 */       return true;
/*     */     }
/* 543 */     return this.l.d();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */