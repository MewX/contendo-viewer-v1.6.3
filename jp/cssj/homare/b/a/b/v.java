/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.c.J;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.s;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.d;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.p;
/*     */ import jp.cssj.homare.b.c.f;
/*     */ import jp.cssj.homare.b.c.g;
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
/*     */ public class v
/*     */   extends d
/*     */   implements p
/*     */ {
/*     */   public static interface b
/*     */   {
/*     */     boolean c();
/*     */     
/*     */     s d();
/*     */     
/*     */     b e();
/*     */     
/*     */     void a(v.c param1c);
/*     */     
/*     */     v.c a();
/*     */     
/*     */     v b();
/*     */   }
/*     */   
/*     */   public static interface c
/*     */     extends b
/*     */   {
/*     */     void a(v.b param1b);
/*     */   }
/*     */   
/*     */   protected static abstract class a
/*     */     implements b
/*     */   {
/*     */     protected v.c a;
/*     */     protected final v b;
/*     */     
/*     */     protected a(v row) {
/*  64 */       this.b = row;
/*     */     }
/*     */     
/*     */     public v.c a() {
/*  68 */       return this.a;
/*     */     }
/*     */     
/*     */     public void a(v.c extended) {
/*  72 */       this.a = extended;
/*     */     }
/*     */     
/*     */     public v b() {
/*  76 */       return this.b;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class e extends a {
/*     */     protected final s c;
/*     */     
/*     */     public e(s cell, v row) {
/*  84 */       super(row);
/*  85 */       this.c = cell;
/*     */     }
/*     */     
/*     */     public boolean c() {
/*  89 */       return true;
/*     */     }
/*     */     
/*     */     public v.b e() {
/*  93 */       return this;
/*     */     }
/*     */     
/*     */     public s d() {
/*  97 */       return this.c;
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class d extends a implements c {
/*     */     protected v.b c;
/*     */     
/*     */     public d(v row) {
/* 105 */       super(row);
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 109 */       return false;
/*     */     }
/*     */     
/*     */     public v.b e() {
/* 113 */       return this.c;
/*     */     }
/*     */     
/*     */     public s d() {
/* 117 */       return this.c.d();
/*     */     }
/*     */     
/*     */     public void a(v.b source) {
/* 121 */       this.c = source;
/*     */     }
/*     */   }
/*     */   
/* 125 */   protected final List<b> f = new ArrayList<>(); protected final J e;
/*     */   
/*     */   public v(s params, J pos) {
/* 128 */     super(params);
/* 129 */     this.e = pos;
/*     */   }
/*     */   private static final boolean h = false;
/*     */   public final byte a() {
/* 133 */     return 11;
/*     */   }
/*     */   
/*     */   public final z b_() {
/* 137 */     return (z)this.e;
/*     */   }
/*     */   
/*     */   public final J g() {
/* 141 */     return this.e;
/*     */   }
/*     */   
/*     */   public final void a(double lineSize) {
/* 145 */     this.c = lineSize;
/*     */   }
/*     */   
/*     */   public final void b(double pageSize) {
/* 149 */     this.d = pageSize;
/*     */   }
/*     */   
/*     */   public final b a(s cellBox) {
/* 153 */     b source = new e(cellBox, this);
/* 154 */     this.f.add(source);
/* 155 */     return source;
/*     */   }
/*     */   
/*     */   public final c a(b cell) {
/* 159 */     c extended = new d(this);
/* 160 */     extended.a(cell.e());
/* 161 */     cell.a(extended);
/* 162 */     this.f.add(extended);
/* 163 */     return extended;
/*     */   }
/*     */   
/*     */   public final b a(int i) {
/* 167 */     return this.f.get(i);
/*     */   }
/*     */   
/*     */   public final int h() {
/* 171 */     return this.f.size();
/*     */   }
/*     */   
/*     */   public final void a(m containerBox) {
/* 175 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 178 */     for (int i = 0; i < this.f.size(); i++) {
/* 179 */       b cell = this.f.get(i);
/* 180 */       if (cell.c()) {
/* 181 */         cell.d().a(containerBox);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/* 188 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/* 191 */     if (this.a.a.c()) {
/*     */       
/* 193 */       jp.cssj.homare.b.c.c c = new jp.cssj.homare.b.c.c(pageBox, clip, this.a.ao, transform, this.a.a, this.a.b, p(), q());
/* 194 */       drawer.a((f)c, x, y);
/*     */     } 
/* 196 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 199 */     if (jp.cssj.homare.b.f.e.a(this.b.D)) {
/*     */       
/* 201 */       for (int i = 0; i < this.f.size(); i++) {
/* 202 */         b cell = this.f.get(i);
/* 203 */         s cellBox = cell.d();
/* 204 */         if (cell.c() && (cellBox.u()).d == null) {
/* 205 */           cellBox.a(pageBox, drawer, clip, transform, x - cellBox.p() + this.d, y);
/*     */         }
/* 207 */         y += cellBox.q();
/*     */       } 
/*     */     } else {
/*     */       
/* 211 */       for (int i = 0; i < this.f.size(); i++) {
/* 212 */         b cell = this.f.get(i);
/* 213 */         s cellBox = cell.d();
/* 214 */         if (cell.c() && (cellBox.u()).d == null) {
/* 215 */           cellBox.a(pageBox, drawer, clip, transform, x, y);
/*     */         }
/*     */         
/* 218 */         x += cellBox.p();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void b(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 225 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/* 228 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 231 */     if (jp.cssj.homare.b.f.e.a(this.b.D)) {
/* 232 */       for (int i = 0; i < this.f.size(); i++) {
/*     */         
/* 234 */         b cell = this.f.get(i);
/* 235 */         s cellBox = cell.d();
/* 236 */         if (cell.c() && (cellBox.u()).d == null) {
/* 237 */           cellBox.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x - cellBox
/* 238 */               .p() + this.d, y);
/*     */         }
/*     */         
/* 241 */         y += cellBox.q();
/*     */       } 
/*     */     } else {
/*     */       
/* 245 */       for (int i = 0; i < this.f.size(); i++) {
/* 246 */         b cell = this.f.get(i);
/* 247 */         s cellBox = cell.d();
/* 248 */         if (cell.c() && (cellBox.u()).d == null) {
/* 249 */           cellBox.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */         }
/*     */         
/* 252 */         x += cellBox.p();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 259 */     visitor.a(transform, (j)this, x, y);
/*     */     
/* 261 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
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
/* 279 */     if (this.a.an == 1) {
/* 280 */       g newDrawer = new g(this.a.am);
/* 281 */       drawer.a(newDrawer);
/* 282 */       drawer = newDrawer;
/*     */     } 
/* 284 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 287 */     if (jp.cssj.homare.b.f.e.a(this.b.D)) {
/*     */       
/* 289 */       for (int i = 0; i < this.f.size(); i++) {
/* 290 */         b cell = this.f.get(i);
/* 291 */         s cellBox = cell.d();
/* 292 */         if (cell.c()) {
/* 293 */           cellBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x - cellBox
/* 294 */               .p() + this.d, y);
/*     */         }
/*     */         
/* 297 */         y += cellBox.q();
/*     */       } 
/*     */     } else {
/*     */       
/* 301 */       for (int i = 0; i < this.f.size(); i++) {
/* 302 */         b cell = this.f.get(i);
/* 303 */         s cellBox = cell.d();
/* 304 */         if (cell.c()) {
/* 305 */           cellBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */         }
/*     */         
/* 308 */         x += cellBox.p();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 314 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 317 */     for (int i = 0; i < this.f.size(); i++) {
/* 318 */       b cell = this.f.get(i);
/* 319 */       cell.d().a(textBuff);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final p b(double pageLimit, jp.cssj.homare.b.a.a.d mode, byte flags) {
/* 324 */     if (!g && (flags & 0x2) != 0) throw new AssertionError();
/*     */ 
/*     */ 
/*     */     
/* 328 */     boolean vertical = jp.cssj.homare.b.f.e.a(this.b.D);
/* 329 */     if ((flags & 0x4) == 0) {
/* 330 */       if ((flags & 0x1) == 0) {
/*     */         
/* 332 */         if (jp.cssj.homare.b.f.e.a(pageLimit, 0.0D) < 0)
/*     */         {
/* 334 */           return this;
/*     */         }
/* 336 */         if (jp.cssj.homare.b.f.e.a(pageLimit, f()) >= 0) {
/*     */ 
/*     */           
/* 339 */           boolean leave = true;
/* 340 */           if (vertical) {
/* 341 */             for (int j = 0; j < this.f.size(); j++) {
/* 342 */               b cell = this.f.get(j);
/* 343 */               if (jp.cssj.homare.b.f.e.a(pageLimit, cell.d().p()) < 0) {
/* 344 */                 leave = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } else {
/* 349 */             for (int j = 0; j < this.f.size(); j++) {
/* 350 */               b cell = this.f.get(j);
/* 351 */               if (jp.cssj.homare.b.f.e.a(pageLimit, cell.d().q()) < 0) {
/* 352 */                 leave = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 357 */           if (leave)
/*     */           {
/* 359 */             return null;
/*     */           }
/*     */         } 
/* 362 */         boolean breakAvoid = false;
/*     */         
/* 364 */         if (this.a.f == 1) {
/*     */           
/* 366 */           breakAvoid = true;
/*     */         } else {
/* 368 */           for (int j = 0; j < this.f.size(); j++) {
/* 369 */             b cell = this.f.get(j);
/* 370 */             i cellParams = cell.d().c_();
/*     */             
/* 372 */             if (jp.cssj.homare.b.f.e.a(cellParams.D) != jp.cssj.homare.b.f.e.a(this.b.D)) {
/* 373 */               return this;
/*     */             }
/* 375 */             if (cellParams.U == 1)
/*     */             {
/* 377 */               breakAvoid = true;
/*     */             }
/*     */           } 
/*     */         } 
/* 381 */         if (breakAvoid && (flags & 0x8) == 0) {
/* 382 */           return this;
/*     */         }
/*     */       } else {
/*     */         
/* 386 */         if (jp.cssj.homare.b.f.e.a(pageLimit, f()) >= 0)
/*     */         {
/* 388 */           return null;
/*     */         }
/*     */         int j;
/* 391 */         for (j = 0; j < this.f.size(); j++) {
/* 392 */           b cell = this.f.get(j);
/* 393 */           i cellParams = cell.d().c_();
/* 394 */           if (jp.cssj.homare.b.f.e.a(cellParams.D) != jp.cssj.homare.b.f.e.a(this.b.D)) {
/* 395 */             return null;
/*     */           }
/*     */         } 
/*     */         
/* 399 */         if (vertical) {
/* 400 */           for (j = 0; j < this.f.size(); j++) {
/* 401 */             b cell = this.f.get(j);
/* 402 */             s cellBox = cell.d();
/* 403 */             if (cellBox.m().d() <= 0.0D && 
/* 404 */               jp.cssj.homare.b.f.e.a(cellBox.s(), 0.0D) <= 0) {
/* 405 */               return null;
/*     */             }
/*     */           } 
/*     */         } else {
/* 409 */           for (j = 0; j < this.f.size(); j++) {
/* 410 */             b cell = this.f.get(j);
/* 411 */             s cellBox = cell.d();
/* 412 */             if (cellBox.m().a() <= 0.0D && 
/* 413 */               jp.cssj.homare.b.f.e.a(cellBox.t(), 0.0D) <= 0) {
/* 414 */               return null;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 421 */     byte xflags = (byte)(flags & 0x5);
/* 422 */     double pageWindow = this.d - pageLimit;
/* 423 */     v nextRowBox = null;
/* 424 */     if ((flags & 0x4) != 0) {
/*     */       
/* 426 */       nextRowBox = new v(this.a, g());
/* 427 */       nextRowBox.a(this.b);
/* 428 */       this.d = pageLimit;
/* 429 */       nextRowBox.d = pageWindow;
/*     */     }  int i;
/* 431 */     for (i = 0; i < this.f.size(); i++) {
/* 432 */       b cell = this.f.get(i);
/* 433 */       s prevCellBox = cell.d();
/*     */       
/* 435 */       double cutPageAxis = pageLimit;
/* 436 */       if (!cell.c()) {
/* 437 */         b sCell = cell.e();
/* 438 */         cutPageAxis += sCell.b().f();
/* 439 */         for (c c = sCell.a(); c != null; 
/* 440 */           c = c.a()) {
/* 441 */           if (c == cell) {
/*     */             break;
/*     */           }
/* 444 */           cutPageAxis += c.b().f();
/*     */         } 
/*     */       } 
/*     */       
/* 448 */       s nextCellBox = (s)prevCellBox.b(cutPageAxis, mode, xflags);
/*     */ 
/*     */ 
/*     */       
/* 452 */       if (nextCellBox == null || nextCellBox == prevCellBox) {
/* 453 */         if (nextRowBox == null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 458 */         byte xxflags = (byte)(xflags | 0x4);
/* 459 */         nextCellBox = (s)prevCellBox.b(cutPageAxis, mode, xxflags);
/* 460 */         if (!g && nextCellBox == null) throw new AssertionError(); 
/* 461 */         if (!g && nextCellBox == prevCellBox) throw new AssertionError();
/*     */       
/*     */       } 
/*     */       
/* 465 */       if (nextRowBox == null) {
/* 466 */         nextRowBox = new v(this.a, g());
/* 467 */         nextRowBox.a(this.b);
/* 468 */         this.d = pageLimit;
/* 469 */         nextRowBox.d = pageWindow;
/*     */         
/* 471 */         for (int j = 0; j < i; j++) {
/* 472 */           b cell2 = this.f.get(j);
/* 473 */           s prevCell2 = cell2.d();
/* 474 */           double cutPageAxis2 = pageLimit;
/* 475 */           if (!cell2.c()) {
/* 476 */             b sCell = cell2.e();
/* 477 */             cutPageAxis2 += sCell.b().f();
/* 478 */             for (c c1 = sCell.a(); c1 != null; 
/* 479 */               c1 = c1.a()) {
/* 480 */               if (c1 == cell2) {
/*     */                 break;
/*     */               }
/* 483 */               cutPageAxis2 += c1.b().f();
/*     */             } 
/*     */           } 
/* 486 */           byte xxflags = (byte)(xflags | 0x4);
/* 487 */           s nextCell2 = (s)prevCell2.b(cutPageAxis2, mode, xxflags);
/* 488 */           if (!g && nextCell2 == null) throw new AssertionError(); 
/* 489 */           if (!g && nextCell2 == prevCell2) throw new AssertionError();
/*     */ 
/*     */ 
/*     */           
/* 493 */           if (vertical) {
/* 494 */             prevCell2.b(cutPageAxis2);
/*     */           } else {
/* 496 */             prevCell2.c(cutPageAxis2);
/*     */           } 
/* 498 */           b(nextCell2);
/* 499 */           b b1 = nextRowBox.a(nextCell2);
/* 500 */           c c = cell2.a();
/* 501 */           double d1 = 1.0D;
/* 502 */           if (c != null) {
/* 503 */             b1.a(c);
/*     */             do {
/* 505 */               d1++;
/* 506 */               c.a(b1);
/* 507 */               c = c.a();
/* 508 */             } while (c != null);
/*     */           } 
/* 510 */           if (vertical) {
/* 511 */             nextRowBox.d = Math.max(nextRowBox.d, nextCell2.p() / d1);
/*     */           } else {
/* 513 */             nextRowBox.d = Math.max(nextRowBox.d, nextCell2.q() / d1);
/*     */           } 
/*     */         } 
/*     */       } 
/* 517 */       if (vertical) {
/* 518 */         prevCellBox.b(cutPageAxis);
/*     */       } else {
/* 520 */         prevCellBox.c(cutPageAxis);
/*     */       } 
/* 522 */       b(nextCellBox);
/* 523 */       b source = nextRowBox.a(nextCellBox);
/* 524 */       c xcell = cell.a();
/* 525 */       double span = 1.0D;
/* 526 */       if (xcell != null) {
/* 527 */         source.a(xcell);
/*     */         do {
/* 529 */           span++;
/* 530 */           xcell.a(source);
/* 531 */           xcell = xcell.a();
/* 532 */         } while (xcell != null);
/*     */       } 
/* 534 */       if (vertical) {
/* 535 */         nextRowBox.d = Math.max(nextRowBox.d, nextCellBox.p() / span);
/*     */       } else {
/* 537 */         nextRowBox.d = Math.max(nextRowBox.d, nextCellBox.q() / span);
/*     */       } 
/*     */       
/*     */       continue;
/*     */     } 
/*     */     
/* 543 */     if (nextRowBox == null) {
/* 544 */       if ((flags & 0x1) != 0) {
/* 545 */         return null;
/*     */       }
/*     */       
/* 548 */       return this;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 554 */     for (i = 0; i < nextRowBox.f.size(); i++) {
/* 555 */       b cell = nextRowBox.f.get(i);
/* 556 */       double rowSize = nextRowBox.d;
/* 557 */       for (c xcell = cell.a(); xcell != null; xcell = xcell.a()) {
/* 558 */         rowSize += xcell.b().f();
/*     */       }
/* 560 */       s nextCell = cell.d();
/*     */       
/* 562 */       if (vertical) {
/* 563 */         nextCell.b(rowSize);
/*     */       } else {
/* 565 */         nextCell.c(rowSize);
/*     */       } 
/*     */     } 
/* 568 */     return nextRowBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void i() {
/* 573 */     boolean vertical = jp.cssj.homare.b.f.e.a(this.b.D); int i;
/* 574 */     for (i = 0; i < this.f.size(); i++) {
/* 575 */       b cell = this.f.get(i);
/* 576 */       if (!cell.c()) {
/*     */ 
/*     */         
/* 579 */         s prevCell = cell.d();
/*     */         
/* 581 */         b sCell = cell.e();
/* 582 */         double cutPageAxis = sCell.b().f();
/* 583 */         for (c xcell = sCell.a(); xcell != null && 
/* 584 */           xcell != cell; xcell = xcell.a())
/*     */         {
/*     */           
/* 587 */           cutPageAxis += xcell.b().f();
/*     */         }
/*     */         
/* 590 */         s nextCell = (s)prevCell.b(cutPageAxis, (jp.cssj.homare.b.a.a.d)jp.cssj.homare.b.a.a.d.c, (byte)4);
/*     */         
/* 592 */         if (!g && nextCell == null) throw new AssertionError(); 
/* 593 */         if (!g && nextCell == prevCell) throw new AssertionError(); 
/* 594 */         if (vertical) {
/* 595 */           prevCell.b(cutPageAxis);
/*     */         } else {
/* 597 */           prevCell.c(cutPageAxis);
/*     */         } 
/* 599 */         b(nextCell);
/*     */         
/* 601 */         b source = new e(nextCell, this);
/* 602 */         this.f.set(i, source);
/* 603 */         c c1 = cell.a();
/* 604 */         int span = 1;
/* 605 */         if (c1 != null) {
/* 606 */           source.a(c1);
/*     */           do {
/* 608 */             span++;
/* 609 */             c1.a(source);
/* 610 */             c1 = c1.a();
/* 611 */           } while (c1 != null);
/*     */         } 
/*     */         
/* 614 */         if (vertical) {
/* 615 */           this.d = Math.max(this.d, nextCell.p() / span);
/*     */         } else {
/* 617 */           this.d = Math.max(this.d, nextCell.q() / span);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 622 */     for (i = 0; i < this.f.size(); i++) {
/* 623 */       b cell = this.f.get(i);
/* 624 */       double rowSize = this.d;
/* 625 */       for (c xcell = cell.a(); xcell != null; xcell = xcell.a()) {
/* 626 */         rowSize += xcell.b().f();
/*     */       }
/* 628 */       s nextCell = cell.d();
/* 629 */       if (vertical) {
/* 630 */         nextCell.b(rowSize);
/*     */       } else {
/* 632 */         nextCell.c(rowSize);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final void b(s nextCell) {
/* 639 */     jp.cssj.homare.b.b.a.a cellBindBuilder = new jp.cssj.homare.b.b.a.a(null, nextCell);
/* 640 */     nextCell.a(cellBindBuilder, 0);
/* 641 */     cellBindBuilder.x();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/v.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */