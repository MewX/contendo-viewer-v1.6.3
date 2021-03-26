/*     */ package jp.cssj.homare.b;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a;
/*     */ import jp.cssj.homare.b.a.b.a;
/*     */ import jp.cssj.homare.b.a.b.d;
/*     */ import jp.cssj.homare.b.a.b.f;
/*     */ import jp.cssj.homare.b.a.b.g;
/*     */ import jp.cssj.homare.b.a.b.h;
/*     */ import jp.cssj.homare.b.a.b.i;
/*     */ import jp.cssj.homare.b.a.b.l;
/*     */ import jp.cssj.homare.b.a.b.p;
/*     */ import jp.cssj.homare.b.a.b.r;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.G;
/*     */ import jp.cssj.homare.b.a.c.a;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.k;
/*     */ import jp.cssj.homare.b.a.c.o;
/*     */ import jp.cssj.homare.b.a.c.p;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.a.d;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.o;
/*     */ import jp.cssj.homare.b.b.a;
/*     */ import jp.cssj.homare.b.b.a.a;
/*     */ import jp.cssj.homare.b.b.a.g;
/*     */ import jp.cssj.homare.b.b.a.h;
/*     */ import jp.cssj.homare.b.b.a.j;
/*     */ import jp.cssj.homare.b.b.a.l;
/*     */ import jp.cssj.homare.b.b.a.m;
/*     */ import jp.cssj.homare.b.b.d;
/*     */ import jp.cssj.homare.b.b.e;
/*     */ import jp.cssj.homare.b.b.f;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.sakae.e.d;
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
/*     */ public class a
/*     */ {
/*     */   protected static class a
/*     */   {
/*     */     public final a a;
/*  59 */     protected j b = null;
/*     */     
/*     */     public a(a builder) {
/*  62 */       this.a = builder;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public j a() {
/*  71 */       if (this.b == null) {
/*  72 */         this.b = new j(this.a);
/*     */       }
/*  74 */       return this.b;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private byte f = 0;
/*     */   
/*  85 */   private final List<o> g = new ArrayList<>();
/*     */   
/*  87 */   private final List<Object> h = new ArrayList();
/*     */   
/*  89 */   private final List<Object> i = new ArrayList();
/*     */   
/*  91 */   private final List<Object> j = new ArrayList(); private static final boolean d = false; public static final byte a = 1; public static final byte b = 2; private final e e;
/*     */   
/*     */   public a(e pageGenerator) {
/*  94 */     this.e = pageGenerator;
/*     */   }
/*     */   
/*     */   public void a(byte pageMode) {
/*  98 */     this.f = pageMode;
/*     */   }
/*     */   
/*     */   public byte a() {
/* 102 */     return this.f;
/*     */   }
/*     */   
/*     */   private void d() {
/* 106 */     if (!this.h.isEmpty()) {
/*     */       return;
/*     */     }
/* 109 */     byte mode = ((this.f & 0x3) != 0) ? 0 : 2;
/*     */     
/* 111 */     h h = new h(this.e, mode);
/* 112 */     a((a)h);
/* 113 */     k();
/*     */   }
/*     */   
/*     */   private void a(a builder) {
/* 117 */     this.h.add(new a(builder));
/*     */   }
/*     */   
/*     */   private a e() {
/* 121 */     int index = this.h.size() - 1;
/* 122 */     Object o = this.h.get(index);
/* 123 */     while (o instanceof f) {
/*     */ 
/*     */       
/* 126 */       index--;
/* 127 */       o = this.h.get(index);
/*     */     } 
/* 129 */     return (a)o;
/*     */   }
/*     */   
/*     */   private a f() {
/* 133 */     for (int i = this.h.size() - 1; i >= 0; i--) {
/* 134 */       Object entry = this.h.get(i);
/* 135 */       if (entry instanceof a) {
/* 136 */         return (a)entry;
/*     */       }
/*     */     } 
/* 139 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   private h g() {
/* 143 */     return (h)((a)this.h.get(0)).a;
/*     */   }
/*     */   
/*     */   private a h() {
/* 147 */     a entry = e();
/* 148 */     if (!entry.a.d()) {
/* 149 */       ((a)entry.a).x();
/*     */     }
/* 151 */     this.h.remove(this.h.size() - 1);
/* 152 */     return entry;
/*     */   }
/*     */   
/*     */   private f i() {
/* 156 */     f tableBuilder = (f)this.h.get(this.h.size() - 1);
/* 157 */     return tableBuilder;
/*     */   }
/*     */   
/*     */   private f j() {
/* 161 */     f builder = (f)this.h.remove(this.h.size() - 1);
/* 162 */     return builder;
/*     */   }
/*     */   
/*     */   private void a(y params) {
/* 166 */     int count = 0;
/*     */     
/* 168 */     for (int i = this.g.size() - 1; i >= 0; i--) {
/* 169 */       j box = (j)this.g.get(i);
/* 170 */       if (box.a() != 4) {
/*     */         break;
/*     */       }
/* 173 */       b();
/* 174 */       this.i.add(box);
/* 175 */       count++;
/*     */     } 
/* 177 */     if (count > 0) {
/* 178 */       this.i.add(d.a(count));
/* 179 */       this.i.add(params);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(y params) {
/* 190 */     if (this.i.isEmpty() || this.i.get(this.i.size() - 1) != params) {
/*     */       return;
/*     */     }
/* 193 */     this.i.remove(this.i.size() - 1);
/* 194 */     Integer count = (Integer)this.i.remove(this.i.size() - 1);
/* 195 */     for (int i = 0; i < count.intValue(); i++) {
/* 196 */       i box = (i)this.i.remove(this.i.size() - 1);
/* 197 */       box = box.a(false);
/* 198 */       a((o)box);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(p pos) {
/* 203 */     a builder = (e()).a;
/* 204 */     if (builder.m() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 208 */     List<a> flows = new ArrayList<>();
/*     */     while (true) {
/* 210 */       a blockBox = (a)builder.k();
/* 211 */       flows.add(blockBox);
/* 212 */       if (blockBox.i() > 1) {
/* 213 */         i colParams = blockBox.c_();
/* 214 */         k oldColumns = colParams.ac;
/* 215 */         colParams.ac = new k(colParams.ac.c, colParams.ac.d, colParams.ac.e, colParams.ac.f, (byte)2);
/*     */         
/* 217 */         l();
/* 218 */         builder.e();
/* 219 */         k();
/* 220 */         b(blockBox.b());
/* 221 */         colParams.ac = oldColumns;
/*     */         break;
/*     */       } 
/* 224 */       l();
/* 225 */       builder.e();
/* 226 */       k();
/* 227 */       b(blockBox.b());
/*     */     } 
/*     */     
/* 230 */     this.j.add(flows);
/* 231 */     this.j.add(pos);
/*     */   }
/*     */   
/*     */   private void b(p pos) {
/* 235 */     if (this.j.isEmpty() || this.j.get(this.j.size() - 1) != pos) {
/*     */       return;
/*     */     }
/* 238 */     a builder = (e()).a;
/* 239 */     this.j.remove(this.j.size() - 1);
/* 240 */     List<?> flows = (List)this.j.remove(this.j.size() - 1);
/* 241 */     for (int i = flows.size() - 1; i >= 0; i--) {
/* 242 */       l l; f f1, flowBox = (f)flows.get(i);
/* 243 */       a((y)flowBox.c_());
/* 244 */       l();
/* 245 */       if (flowBox.i() > 1) {
/* 246 */         l = new l(flowBox.c_(), flowBox.v());
/*     */       } else {
/* 248 */         f1 = new f(l.c_(), l.v());
/*     */       } 
/* 250 */       builder.a(f1);
/* 251 */       k();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void k() {
/* 256 */     a cbe = e();
/* 257 */     cbe.a().b();
/*     */   }
/*     */   
/*     */   private void l() {
/* 261 */     a cbe = e();
/* 262 */     cbe.a().d(); } public void a(o box) { r tableBox; f tableBuiler; h inlineBlockBox; f blockBox; a stfBox; G tableParams; c containerBox; d innerTableBox; a a1;
/*     */     i params;
/*     */     a builder, a3, a2;
/*     */     p pos;
/*     */     a newBuilder;
/*     */     g g;
/*     */     a a4;
/* 269 */     d();
/* 270 */     switch (box.b_().a()) {
/*     */       
/*     */       case 8:
/* 273 */         tableBox = (r)box;
/* 274 */         tableParams = tableBox.g();
/* 275 */         a3 = (e()).a;
/* 276 */         switch (tableBox.h().b_().a()) {
/*     */           case 4:
/* 278 */             a((y)tableParams);
/* 279 */             l();
/* 280 */             k();
/*     */             break;
/*     */         } 
/*     */         
/* 284 */         if (!a3.c() || e.a(tableBox)) {
/*     */           
/* 286 */           m m = new m((d)a3, tableBox);
/*     */         } else {
/*     */           
/* 289 */           g fixedTableBuilder = new g(tableBox);
/* 290 */           fixedTableBuilder.a((h)a3);
/* 291 */           g = fixedTableBuilder;
/*     */         } 
/* 293 */         this.h.add(g);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/*     */       case 13:
/* 301 */         tableBuiler = i();
/* 302 */         if (tableBuiler.c()) {
/* 303 */           a(tableBuiler.a().b());
/* 304 */           l();
/* 305 */           k();
/*     */         } 
/* 307 */         containerBox = (c)box;
/* 308 */         a2 = tableBuiler.a(containerBox);
/* 309 */         a(a2);
/* 310 */         k();
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/* 321 */         tableBuiler = i();
/* 322 */         if (tableBuiler.c()) {
/* 323 */           a(tableBuiler.a().b());
/* 324 */           l();
/*     */         } 
/* 326 */         innerTableBox = (d)box;
/* 327 */         tableBuiler.a(innerTableBox);
/* 328 */         if (tableBuiler.c()) {
/* 329 */           k();
/*     */         }
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 336 */         if (box.a() == 4) {
/* 337 */           i inlineBox = (i)box;
/* 338 */           e().a().a(inlineBox);
/*     */           break;
/*     */         } 
/* 341 */         inlineBlockBox = (h)box;
/* 342 */         a1 = (e()).a;
/* 343 */         a2 = a1.a((a)inlineBlockBox);
/* 344 */         a(a2);
/* 345 */         k();
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 352 */         blockBox = (f)box;
/* 353 */         params = blockBox.c_();
/*     */         
/* 355 */         if (!this.g.isEmpty()) {
/* 356 */           j parentBox = (j)this.g.get(this.g.size() - 1);
/* 357 */           if (parentBox.d() == 1 && blockBox.d() == 2 && 
/* 358 */             (e()).a.d()) {
/* 359 */             l l = (l)(e()).a;
/* 360 */             if (!l.v()) {
/* 361 */               p rubyBox = (p)parentBox;
/* 362 */               b();
/* 363 */               rubyBox = new p(rubyBox.c_(), rubyBox.c());
/* 364 */               a((o)rubyBox);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 370 */         pos = blockBox.v();
/* 371 */         if (pos.h == -1) {
/* 372 */           a(pos);
/*     */         }
/*     */         
/* 375 */         a((y)params);
/* 376 */         l();
/* 377 */         a4 = (e()).a;
/* 378 */         if (e.a(params.D) == e.a((a4.j().c_()).D) && 
/* 379 */           !blockBox.l()) {
/* 380 */           a4.a(blockBox);
/*     */         } else {
/*     */           
/* 383 */           a a5 = a4.a((a)blockBox);
/* 384 */           a(a5);
/*     */         } 
/* 386 */         k();
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 392 */         e().a().c();
/*     */       
/*     */       case 7:
/* 395 */         stfBox = (a)box;
/* 396 */         builder = (f()).a;
/* 397 */         if (box.b_().a() == 7) {
/* 398 */           a a5 = (a)stfBox.b_();
/* 399 */           if (a5.b == 2) {
/* 400 */             e().a().c();
/* 401 */             e().a().a();
/*     */           } 
/*     */         } 
/* 404 */         newBuilder = builder.a(stfBox);
/* 405 */         a(newBuilder);
/* 406 */         k();
/*     */         break;
/*     */       
/*     */       default:
/* 410 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 413 */     this.g.add(box); } public void b() { f tableBuilder; f blockBox; a entry; r tableBox; a a1, parentBuilder, builder; G tableParams;
/*     */     p pos;
/*     */     a a2;
/*     */     a absoluteBox;
/* 417 */     j box = (j)this.g.remove(this.g.size() - 1);
/*     */ 
/*     */ 
/*     */     
/* 421 */     switch (box.b_().a()) {
/*     */       
/*     */       case 8:
/* 424 */         tableBuilder = j();
/* 425 */         tableBox = tableBuilder.a();
/* 426 */         tableParams = tableBox.g();
/* 427 */         switch (tableBox.h().b_().a()) {
/*     */           case 4:
/* 429 */             a(tableBox.h().b());
/* 430 */             l();
/*     */             break;
/*     */           case 6:
/* 433 */             e().a().c();
/*     */             break;
/*     */         } 
/* 436 */         a2 = (e()).a;
/* 437 */         if (!a2.c() || e.a(tableBox)) {
/*     */           
/* 439 */           a2.a(tableBuilder);
/*     */         } else {
/*     */           
/* 442 */           g fixedTableBuilder = (g)tableBuilder;
/* 443 */           fixedTableBuilder.d();
/*     */         } 
/* 445 */         switch (tableBox.h().b_().a()) {
/*     */           case 4:
/* 447 */             k();
/* 448 */             b((y)tableParams);
/*     */             break;
/*     */           case 5:
/* 451 */             e().a().a((h)tableBox.h());
/*     */             break;
/*     */           case 7:
/* 454 */             absoluteBox = (a)tableBox.h();
/* 455 */             if ((absoluteBox.c()).b == 2) {
/* 456 */               e().a().a((i)absoluteBox);
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*     */         return;
/*     */       
/*     */       case 9:
/*     */       case 13:
/* 466 */         l();
/* 467 */         h();
/* 468 */         if (!c && this.h.size() == 1) throw new AssertionError();
/*     */         
/*     */         return;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/* 478 */         i().b();
/*     */         return;
/*     */ 
/*     */       
/*     */       case 5:
/* 483 */         if (box.a() == 4) {
/*     */           
/* 485 */           e().a().e();
/*     */         } else {
/*     */           
/* 488 */           l();
/* 489 */           a a3 = h();
/* 490 */           h inlineBlockBox = (h)a3.a.j();
/* 491 */           a a4 = (e()).a;
/* 492 */           if (!a4.d() && a3.a.d()) {
/*     */             
/* 494 */             l stfBuilder = (l)a3.a;
/* 495 */             inlineBlockBox.a((d)a4, stfBuilder.g(), stfBuilder.t(), false);
/*     */             
/* 497 */             a lnlineBlockBuilder = new a((d)g(), (c)inlineBlockBox);
/* 498 */             stfBuilder.a(lnlineBlockBuilder);
/* 499 */             lnlineBlockBuilder.x();
/*     */           } 
/* 501 */           e().a().a(inlineBlockBox);
/*     */         } 
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 508 */         l();
/* 509 */         blockBox = (f)box;
/* 510 */         a1 = (e()).a;
/* 511 */         if (a1.j() != box) {
/* 512 */           a1.e();
/* 513 */           k();
/* 514 */           b(box.b());
/*     */         } else {
/* 516 */           a a3 = h();
/* 517 */           a a4 = (e()).a;
/* 518 */           if (!a4.d()) {
/* 519 */             if (a3.a.d()) {
/*     */               
/* 521 */               l contentBuilder = (l)a3.a;
/* 522 */               blockBox.a((d)a4, contentBuilder.g(), contentBuilder
/* 523 */                   .t(), false);
/* 524 */               a bindBuilder = new a((d)g(), (c)blockBox);
/* 525 */               contentBuilder.a(bindBuilder);
/* 526 */               bindBuilder.x();
/*     */             } 
/* 528 */             a4.a((j)blockBox);
/*     */           } 
/* 530 */           k();
/* 531 */           b(box.b());
/*     */         } 
/*     */         
/* 534 */         pos = blockBox.v();
/*     */         
/* 536 */         if (pos.h == -1) {
/* 537 */           b(pos);
/*     */         }
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 544 */         l();
/* 545 */         entry = h();
/* 546 */         parentBuilder = (e()).a;
/* 547 */         if (!parentBuilder.d()) {
/* 548 */           a boundBuilder = (a)parentBuilder;
/* 549 */           d floatBox = (d)entry.a.j();
/* 550 */           if (entry.a.d()) {
/*     */             
/* 552 */             l contentBuilder = (l)entry.a;
/* 553 */             floatBox.a((d)parentBuilder, contentBuilder.g(), contentBuilder
/* 554 */                 .t(), false);
/* 555 */             a floatBuilder = new a((d)g(), (c)floatBox);
/* 556 */             contentBuilder.a(floatBuilder);
/* 557 */             floatBuilder.x();
/*     */           } 
/* 559 */           o o = (o)box.b_();
/* 560 */           boolean pageBreak = (this.f == 0 && ((o.a != 0 && o.a != 1) || (o.b != 0 && o.b != 1)));
/*     */ 
/*     */ 
/*     */           
/* 564 */           if (pageBreak) {
/* 565 */             a(box.b());
/* 566 */             l();
/*     */           } 
/* 568 */           boundBuilder.a((j)floatBox);
/* 569 */           if (pageBreak) {
/* 570 */             k();
/* 571 */             b(box.b());
/*     */           } 
/* 573 */         } else if (entry.a.d()) {
/*     */           
/* 575 */           l stfBuilder = (l)parentBuilder;
/* 576 */           l contentBuilder = (l)entry.a;
/* 577 */           stfBuilder.a(contentBuilder);
/*     */         } 
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 7:
/* 584 */         l();
/* 585 */         entry = h();
/* 586 */         builder = (f()).a;
/* 587 */         if (!builder.d()) {
/* 588 */           a boundBuilder = (a)builder;
/* 589 */           a a3 = (a)entry.a.j();
/* 590 */           if (entry.a.d()) {
/*     */             
/* 592 */             l contentBuilder = (l)entry.a;
/* 593 */             if ((a3.c()).c != 1) {
/*     */               
/* 595 */               c c = g().j();
/* 596 */               a3.a((m)c, contentBuilder.g(), contentBuilder
/* 597 */                   .t());
/* 598 */               a absoluteBuilder = new a((d)g(), (c)a3);
/* 599 */               contentBuilder.a(absoluteBuilder);
/* 600 */               absoluteBuilder.x();
/*     */             } else {
/*     */               
/* 603 */               a3.a(contentBuilder);
/*     */             } 
/*     */           } 
/* 606 */           switch ((a3.c()).b) {
/*     */             case 1:
/* 608 */               boundBuilder.a((j)a3);
/*     */               return;
/*     */             case 2:
/* 611 */               e().a().a((i)a3);
/*     */               return;
/*     */           } 
/* 614 */           throw new IllegalStateException();
/*     */         } 
/*     */         return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 621 */     throw new IllegalStateException(); } public void a(f replacedBox) { p pos;
/*     */     a context, builder;
/*     */     o o;
/*     */     i absoluteBox;
/*     */     boolean pageBreak;
/* 626 */     d();
/*     */     
/* 628 */     switch (replacedBox.b_().a()) {
/*     */ 
/*     */       
/*     */       case 4:
/* 632 */         pos = ((g)replacedBox).g();
/* 633 */         if (pos.h == -1) {
/* 634 */           a(pos);
/*     */         }
/*     */         
/* 637 */         builder = (e()).a;
/* 638 */         a(replacedBox.b());
/* 639 */         l();
/* 640 */         builder.a((j)replacedBox);
/* 641 */         k();
/* 642 */         b(replacedBox.b());
/*     */ 
/*     */         
/* 645 */         if (pos.h == -1) {
/* 646 */           b(pos);
/*     */         }
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/* 653 */         context = (e()).a;
/* 654 */         o = (o)replacedBox.b_();
/* 655 */         pageBreak = (this.f == 0 && ((o.a != 0 && o.a != 1) || (o.b != 0 && o.b != 1)));
/*     */ 
/*     */         
/* 658 */         if (pageBreak) {
/* 659 */           a(replacedBox.b());
/* 660 */           l();
/*     */         } else {
/* 662 */           e().a().c();
/*     */         } 
/* 664 */         context.a((j)replacedBox);
/* 665 */         if (pageBreak) {
/* 666 */           k();
/* 667 */           b(replacedBox.b());
/*     */         } 
/*     */         return;
/*     */ 
/*     */       
/*     */       case 7:
/* 673 */         context = (e()).a;
/* 674 */         absoluteBox = (i)replacedBox;
/* 675 */         switch ((absoluteBox.c()).b) {
/*     */           case 1:
/* 677 */             context.a((j)replacedBox);
/*     */             return;
/*     */           case 2:
/* 680 */             e().a().a(absoluteBox);
/*     */             return;
/*     */         } 
/* 683 */         throw new IllegalStateException();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/* 690 */         e().a().a(replacedBox);
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 695 */     throw new IllegalStateException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int charOffset, char[] ch, int off, int len, boolean lineFeed) {
/* 703 */     d();
/* 704 */     e().a().a(charOffset, ch, off, len, lineFeed);
/*     */   }
/*     */   
/*     */   public void c() {
/* 708 */     d();
/* 709 */     l();
/* 710 */     h();
/* 711 */     if (!c && !this.h.isEmpty()) throw new AssertionError(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */