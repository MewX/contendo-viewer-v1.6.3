/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.a;
/*     */ import jp.cssj.homare.b.a.a.g;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.A;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.t;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.b.a.l;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.e.b;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */   implements i
/*     */ {
/*     */   protected final jp.cssj.homare.b.a.c.a n;
/*     */   private l I;
/*     */   
/*     */   public a(i params, jp.cssj.homare.b.a.c.a pos) {
/*  37 */     super(params);
/*  38 */     this.n = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   protected a(i params, jp.cssj.homare.b.a.c.a pos, m size, m minSize, b frame, g container) {
/*  43 */     super(params, size, minSize, frame, container);
/*  44 */     this.n = pos;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  48 */     return (z)this.n;
/*     */   }
/*     */   
/*     */   public final jp.cssj.homare.b.a.c.a c() {
/*  52 */     return this.n;
/*     */   }
/*     */   
/*     */   public final boolean e_() {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(l builder) {
/*  62 */     this.I = builder;
/*     */   }
/*     */   public final void a(m containerBox, double minLineAxis, double maxLineAxis) { double lineAxis, marginLeft, marginRight, marginTop, marginBottom, width, top, left, height;
/*     */     int state;
/*  66 */     double cWidth = containerBox.s() + (containerBox.m()).c.a();
/*  67 */     double cHeight = containerBox.t() + (containerBox.m()).c.b();
/*     */ 
/*     */     
/*  70 */     if (e.a(this.a.D)) {
/*     */       
/*  72 */       lineAxis = cHeight;
/*     */     } else {
/*     */       
/*  75 */       lineAxis = cWidth;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     e.b(this.e.c, this.e.a.e, lineAxis);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     e.a(this.e.b, this.e.a.b, lineAxis);
/*     */ 
/*     */     
/*  89 */     t margin = this.e.a.b;
/*  90 */     jp.cssj.homare.b.e.a amargin = this.e.b;
/*     */ 
/*     */     
/*  93 */     jp.cssj.homare.b.a.c.a pos = c();
/*     */ 
/*     */ 
/*     */     
/*  97 */     switch (this.a.D) {
/*     */       
/*     */       case 1:
/* 100 */         width = e.a(this.c, cWidth);
/* 101 */         if (this.a.aa == 2 && !e.a(width)) {
/* 102 */           width -= this.e.h();
/*     */         }
/* 104 */         marginLeft = marginRight = 0.0D;
/* 105 */         left = 0.0D;
/* 106 */         for (state = 0; state < 2; state++) {
/* 107 */           double maxWidth, minWidth; left = e.b(pos.a, cWidth);
/* 108 */           double right = e.c(pos.a, cWidth);
/* 109 */           if (!e.a(left) && !e.a(right) && !e.a(width)) {
/* 110 */             marginLeft = (margin.d() == 3) ? 1.722773839210782E308D : amargin.d;
/* 111 */             marginRight = (margin.b() == 3) ? 1.722773839210782E308D : amargin.b;
/* 112 */             if (e.a(marginLeft) && e.a(marginRight)) {
/* 113 */               marginLeft = marginRight = (cWidth - left - right - width - this.e.f()) / 2.0D;
/*     */             }
/* 115 */             if (e.a(marginLeft) && !e.a(marginRight)) {
/* 116 */               marginLeft = cWidth - left - right - width - this.e.f();
/*     */             }
/* 118 */             if (!e.a(marginLeft) && e.a(marginRight)) {
/* 119 */               marginRight = cWidth - left - right - width - this.e.f();
/*     */             } else {
/*     */               
/* 122 */               right = 0.0D;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 127 */             marginLeft = amargin.d;
/* 128 */             marginRight = amargin.b;
/* 129 */             if (e.a(width)) {
/* 130 */               if (!e.a(left) && !e.a(right)) {
/* 131 */                 width = cWidth - left - right - this.e.f();
/*     */               } else {
/* 133 */                 width = maxLineAxis;
/* 134 */                 double limitWidth = cWidth - this.e.f();
/*     */                 
/* 136 */                 width = Math.max(minLineAxis, Math.min(limitWidth, width));
/* 137 */                 left = right = 0.0D;
/* 138 */                 if (e.a(left)) {
/* 139 */                   width = Math.max(minLineAxis, Math.min(limitWidth - right, width));
/* 140 */                   left = cWidth - right - width - this.e.f();
/*     */                 } else {
/* 142 */                   width = Math.max(minLineAxis, Math.min(limitWidth - left, width));
/* 143 */                   right = cWidth - left - width - this.e.f();
/*     */                 }
/*     */               
/*     */               } 
/* 147 */             } else if (e.a(right)) {
/* 148 */               if (e.a(left)) {
/* 149 */                 left = 0.0D;
/*     */               }
/* 151 */               right = cWidth - left - width - this.e.f();
/*     */             } else {
/* 153 */               left = cWidth - right - width - this.e.f();
/*     */             } 
/*     */           } 
/*     */           
/* 157 */           switch (state) {
/*     */             case 0:
/* 159 */               maxWidth = e.a(this.a.Z, cWidth);
/* 160 */               if (!e.a(maxWidth) && width > maxWidth) {
/* 161 */                 width = maxWidth;
/*     */                 break;
/*     */               } 
/* 164 */               state = 1;
/*     */             case 1:
/* 166 */               minWidth = e.a(this.d, cWidth);
/* 167 */               if (width < minWidth) {
/* 168 */                 width = minWidth;
/*     */                 break;
/*     */               } 
/* 171 */               state = 2;
/*     */               break;
/*     */           } 
/*     */         } 
/* 175 */         marginTop = (margin.a() == 3) ? 1.722773839210782E308D : amargin.a;
/* 176 */         marginBottom = (margin.c() == 3) ? 1.722773839210782E308D : amargin.c;
/* 177 */         if (!o && e.a(left)) throw new AssertionError(); 
/* 178 */         this.j = left;
/* 179 */         this.e.b.a = marginTop;
/* 180 */         this.e.b.b = marginRight;
/* 181 */         this.e.b.c = marginBottom;
/* 182 */         this.e.b.d = marginLeft;
/* 183 */         this.f = width;
/* 184 */         this.g = 0.0D;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 190 */         top = 0.0D;
/* 191 */         height = e.b(this.c, cHeight);
/* 192 */         if (this.a.aa == 2 && !e.a(height)) {
/* 193 */           height -= this.e.g();
/*     */         }
/* 195 */         marginTop = marginBottom = 0.0D;
/* 196 */         for (state = 0; state < 2; state++) {
/* 197 */           double maxHeight, minHeight; top = e.a(pos.a, cHeight);
/* 198 */           double bottom = e.d(pos.a, cHeight);
/* 199 */           if (!e.a(top) && !e.a(bottom) && !e.a(height)) {
/* 200 */             marginTop = (margin.a() == 3) ? 1.722773839210782E308D : amargin.a;
/* 201 */             marginBottom = (margin.c() == 3) ? 1.722773839210782E308D : amargin.c;
/* 202 */             if (e.a(marginTop) && e.a(marginBottom)) {
/* 203 */               marginTop = marginBottom = (cHeight - top - bottom - height - this.e.e()) / 2.0D;
/*     */             }
/*     */             
/* 206 */             if (e.a(marginTop) && !e.a(marginBottom)) {
/* 207 */               marginTop = cHeight - top - bottom - height - this.e.e();
/*     */             }
/* 209 */             if (!e.a(marginTop) && e.a(marginBottom)) {
/* 210 */               marginBottom = cHeight - top - bottom - height - this.e.e();
/*     */             } else {
/*     */               
/* 213 */               bottom = 0.0D;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 218 */             marginTop = amargin.a;
/* 219 */             marginBottom = amargin.c;
/* 220 */             if (e.a(height)) {
/* 221 */               if (!e.a(top) && !e.a(bottom)) {
/* 222 */                 height = cHeight - top - bottom - this.e.e();
/*     */               } else {
/* 224 */                 height = maxLineAxis;
/* 225 */                 double limitHeight = cHeight - this.e.e();
/*     */                 
/* 227 */                 height = Math.max(minLineAxis, Math.min(limitHeight, height));
/* 228 */                 top = bottom = 0.0D;
/* 229 */                 if (e.a(top)) {
/* 230 */                   height = Math.max(minLineAxis - bottom, Math.min(limitHeight, height));
/* 231 */                   top = cHeight - bottom - height - this.e.e();
/*     */                 } else {
/* 233 */                   height = Math.max(minLineAxis - top, Math.min(limitHeight, height));
/* 234 */                   bottom = cHeight - top - height - this.e.e();
/*     */                 }
/*     */               
/*     */               } 
/* 238 */             } else if (e.a(bottom)) {
/* 239 */               if (e.a(top)) {
/* 240 */                 top = 0.0D;
/*     */               }
/* 242 */               bottom = cHeight - top - height - this.e.e();
/*     */             } else {
/* 244 */               top = cHeight - bottom - height - this.e.e();
/*     */             } 
/*     */           } 
/*     */           
/* 248 */           switch (state) {
/*     */             case 0:
/* 250 */               maxHeight = e.b(this.a.Z, cHeight);
/* 251 */               if (!e.a(maxHeight) && height > maxHeight) {
/* 252 */                 height = maxHeight;
/*     */                 break;
/*     */               } 
/* 255 */               state = 1;
/*     */             case 1:
/* 257 */               minHeight = e.b(this.d, cHeight);
/* 258 */               if (height < minHeight) {
/* 259 */                 height = minHeight;
/*     */                 break;
/*     */               } 
/* 262 */               state = 2;
/*     */               break;
/*     */           } 
/*     */         } 
/* 266 */         marginLeft = (margin.d() == 3) ? 1.722773839210782E308D : amargin.d;
/* 267 */         marginRight = (margin.b() == 3) ? 1.722773839210782E308D : amargin.b;
/* 268 */         if (!o && e.a(top)) throw new AssertionError(); 
/* 269 */         this.k = top;
/* 270 */         this.e.b.a = marginTop;
/* 271 */         this.e.b.b = marginRight;
/* 272 */         this.e.b.c = marginBottom;
/* 273 */         this.e.b.d = marginLeft;
/* 274 */         this.g = height;
/* 275 */         this.f = 0.0D;
/*     */         break;
/*     */       
/*     */       default:
/* 279 */         throw new IllegalStateException();
/*     */     } 
/* 281 */     if (!o && e.a(this.f)) throw new AssertionError(); 
/* 282 */     if (!o && e.a(this.g)) throw new AssertionError();  } public final void a(m containerBox) { double height, marginTop, marginBottom, top;
/*     */     int state;
/*     */     double marginLeft, marginRight, left, width;
/*     */     int j;
/* 286 */     if (this.I != null) {
/* 287 */       a(containerBox, this.I.g(), this.I.t());
/* 288 */       jp.cssj.homare.b.b.a.a absoluteBuilder = new jp.cssj.homare.b.b.a.a((d)this.I.h(), (c)this);
/* 289 */       this.I.a(absoluteBuilder);
/* 290 */       absoluteBuilder.x();
/* 291 */       this.I = null;
/*     */     } 
/*     */     
/* 294 */     double cWidth = containerBox.s() + (containerBox.m()).c.a();
/* 295 */     double cHeight = containerBox.t() + (containerBox.m()).c.b();
/*     */ 
/*     */     
/* 298 */     jp.cssj.homare.b.a.c.a pos = c();
/*     */ 
/*     */ 
/*     */     
/* 302 */     jp.cssj.homare.b.e.a margin = this.e.b;
/* 303 */     jp.cssj.homare.b.e.a padding = this.e.c;
/* 304 */     A border = this.e.a.c;
/* 305 */     switch (this.a.D) {
/*     */       
/*     */       case 1:
/* 308 */         height = e.b(this.c, cHeight);
/* 309 */         marginTop = 0.0D;
/* 310 */         marginBottom = 0.0D;
/* 311 */         top = 0.0D;
/* 312 */         for (state = 0; state < 2; state++) {
/* 313 */           double maxHeight, minHeight; marginTop = margin.a;
/* 314 */           marginBottom = margin.c;
/* 315 */           top = e.a(pos.a, cHeight);
/* 316 */           double bottom = e.d(pos.a, cHeight);
/* 317 */           if (!e.a(top) && !e.a(bottom) && !e.a(height)) {
/* 318 */             if (e.a(marginTop) && e.a(marginBottom))
/*     */             {
/* 320 */               marginTop = marginBottom = (cHeight - top - bottom - height - border.j() - padding.b()) / 2.0D;
/*     */             }
/* 322 */             if (e.a(marginTop) && !e.a(marginBottom))
/*     */             {
/* 324 */               marginTop = cHeight - top - bottom - height - marginBottom - border.j() - padding.b();
/*     */             }
/* 326 */             if (!e.a(marginTop) && e.a(marginBottom)) {
/*     */               
/* 328 */               marginBottom = cHeight - top - bottom - height - marginTop - border.j() - padding.b();
/*     */             } else {
/*     */               
/* 331 */               bottom = 0.0D;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 336 */             if (e.a(marginTop)) {
/* 337 */               marginTop = 0.0D;
/*     */             }
/* 339 */             if (e.a(marginBottom)) {
/* 340 */               marginBottom = 0.0D;
/*     */             }
/* 342 */             double contentSize = this.g;
/* 343 */             if (e.a(height)) {
/* 344 */               if (e.a(top) && e.a(bottom)) {
/* 345 */                 top = 0.0D;
/* 346 */                 bottom = 0.0D;
/* 347 */                 height = contentSize;
/* 348 */               } else if (e.a(top) && !e.a(bottom)) {
/* 349 */                 height = contentSize;
/*     */                 
/* 351 */                 top = cHeight - bottom - height - marginTop - marginBottom - border.j() - padding.b();
/* 352 */               } else if (!e.a(top) && e.a(bottom)) {
/* 353 */                 height = contentSize;
/*     */                 
/* 355 */                 bottom = cHeight - top - height - marginTop - marginBottom - border.j() - padding.b();
/*     */               } else {
/*     */                 
/* 358 */                 height = cHeight - top - bottom - marginTop - marginBottom - border.j() - padding.b();
/*     */               }
/*     */             
/* 361 */             } else if (e.a(bottom)) {
/* 362 */               if (e.a(top)) {
/* 363 */                 top = 0.0D;
/*     */               }
/*     */               
/* 366 */               bottom = cHeight - top - height - marginTop - marginBottom - border.j() - padding.b();
/*     */             } else {
/*     */               
/* 369 */               top = cHeight - bottom - height - marginTop - marginBottom - border.j() - padding.b();
/*     */             } 
/*     */           } 
/*     */           
/* 373 */           switch (state) {
/*     */             case 0:
/* 375 */               maxHeight = e.b(this.a.Z, cHeight);
/* 376 */               if (!e.a(maxHeight) && height > maxHeight) {
/* 377 */                 height = maxHeight;
/*     */                 break;
/*     */               } 
/* 380 */               state = 1;
/*     */             case 1:
/* 382 */               minHeight = e.b(this.d, cHeight);
/* 383 */               if (height < minHeight) {
/* 384 */                 height = minHeight;
/*     */                 break;
/*     */               } 
/* 387 */               state = 2;
/*     */               break;
/*     */           } 
/*     */         } 
/* 391 */         if (!o && e.a(top)) throw new AssertionError(); 
/* 392 */         this.k = top;
/* 393 */         if (!o && e.a(margin.b)) throw new AssertionError(); 
/* 394 */         if (!o && e.a(margin.d)) throw new AssertionError(); 
/* 395 */         if (!o && e.a(marginTop)) throw new AssertionError(); 
/* 396 */         if (!o && e.a(marginBottom)) throw new AssertionError(); 
/* 397 */         this.e.b.a = marginTop;
/* 398 */         this.e.b.c = marginBottom;
/* 399 */         if (!o && e.a(height)) throw new AssertionError(); 
/* 400 */         if (this.a.aa == 2) {
/* 401 */           height -= this.e.g();
/*     */         }
/* 403 */         this.g = height;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 409 */         marginLeft = 0.0D;
/* 410 */         marginRight = 0.0D;
/* 411 */         left = 0.0D;
/* 412 */         width = e.a(this.c, cWidth);
/* 413 */         for (j = 0; j < 2; j++) {
/* 414 */           double maxWidth, minWidth; marginLeft = margin.d;
/* 415 */           marginRight = margin.b;
/* 416 */           left = e.b(pos.a, cWidth);
/* 417 */           double right = e.c(pos.a, cWidth);
/* 418 */           if (!e.a(left) && !e.a(right) && !e.a(width)) {
/* 419 */             if (e.a(marginLeft) && e.a(marginRight))
/*     */             {
/* 421 */               marginLeft = marginRight = (cWidth - left - right - width - border.i() - padding.a()) / 2.0D;
/*     */             }
/* 423 */             if (e.a(marginLeft) && !e.a(marginRight))
/*     */             {
/* 425 */               marginLeft = cWidth - left - right - width - marginRight - border.i() - padding.a();
/*     */             }
/* 427 */             if (!e.a(marginLeft) && e.a(marginRight)) {
/*     */               
/* 429 */               marginRight = cWidth - left - right - width - marginLeft - border.i() - padding.a();
/*     */             } else {
/*     */               
/* 432 */               right = 0.0D;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 437 */             if (e.a(marginLeft)) {
/* 438 */               marginLeft = 0.0D;
/*     */             }
/* 440 */             if (e.a(marginRight)) {
/* 441 */               marginRight = 0.0D;
/*     */             }
/* 443 */             double contentSize = p() - this.e.f();
/* 444 */             if (e.a(width)) {
/* 445 */               if (e.a(left) && e.a(right)) {
/* 446 */                 left = 0.0D;
/* 447 */                 right = 0.0D;
/* 448 */                 width = contentSize;
/* 449 */               } else if (e.a(left) && !e.a(right)) {
/* 450 */                 width = contentSize;
/*     */                 
/* 452 */                 left = cWidth - right - width - marginLeft - marginRight - border.i() - padding.a();
/* 453 */               } else if (!e.a(left) && e.a(right)) {
/* 454 */                 width = contentSize;
/*     */                 
/* 456 */                 right = cWidth - left - width - marginLeft - marginRight - border.i() - padding.a();
/*     */               } else {
/*     */                 
/* 459 */                 width = cWidth - left - right - marginLeft - marginRight - border.i() - padding.a();
/*     */               }
/*     */             
/* 462 */             } else if (e.a(left)) {
/* 463 */               if (e.a(right)) {
/* 464 */                 right = 0.0D;
/*     */               }
/*     */               
/* 467 */               left = cWidth - right - width - marginLeft - marginRight - border.i() - padding.a();
/*     */             } else {
/*     */               
/* 470 */               right = cWidth - left - width - marginLeft - marginRight - border.i() - padding.a();
/*     */             } 
/*     */           } 
/*     */           
/* 474 */           switch (j) {
/*     */             case 0:
/* 476 */               maxWidth = e.a(this.a.Z, cWidth);
/* 477 */               if (!e.a(maxWidth) && width > maxWidth) {
/* 478 */                 width = maxWidth;
/*     */                 break;
/*     */               } 
/* 481 */               j = 1;
/*     */             case 1:
/* 483 */               minWidth = e.a(this.d, cWidth);
/* 484 */               if (width < minWidth) {
/* 485 */                 width = minWidth;
/*     */                 break;
/*     */               } 
/* 488 */               j = 2;
/*     */               break;
/*     */           } 
/*     */         } 
/* 492 */         if (!o && e.a(left)) throw new AssertionError(); 
/* 493 */         this.j = left;
/* 494 */         if (!o && e.a(margin.a)) throw new AssertionError(); 
/* 495 */         if (!o && e.a(margin.c)) throw new AssertionError(); 
/* 496 */         if (!o && e.a(marginRight)) throw new AssertionError(); 
/* 497 */         if (!o && e.a(marginLeft)) throw new AssertionError(); 
/* 498 */         this.e.b.b = marginRight;
/* 499 */         this.e.b.d = marginLeft;
/* 500 */         if (this.a.aa == 2) {
/* 501 */           width -= this.e.h();
/*     */         }
/* 503 */         this.f = width;
/*     */         break;
/*     */       default:
/* 506 */         throw new IllegalStateException();
/*     */     } 
/* 508 */     if (!o && e.a(this.f)) throw new AssertionError(); 
/* 509 */     if (!o && e.a(this.g)) throw new AssertionError(); 
/* 510 */     super.a(containerBox); }
/*     */ 
/*     */   
/*     */   public final boolean g() {
/* 514 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 519 */     if (this.a.an == 1) {
/* 520 */       g newDrawer = new g(this.a.am);
/* 521 */       drawer.a(newDrawer);
/* 522 */       drawer = newDrawer;
/*     */     } 
/*     */     
/* 525 */     a(pageBox, drawer, clip, transform, x, y);
/* 526 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final a a(m nextSize, m nextMinSize, b nextFrame, g container) {
/* 531 */     return new a(this.a, c(), nextSize, nextMinSize, nextFrame, container);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */