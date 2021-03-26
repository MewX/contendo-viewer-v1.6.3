/*      */ package jp.cssj.homare.b.a.a;
/*      */ 
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.n;
/*      */ import jp.cssj.homare.b.a.b.r;
/*      */ import jp.cssj.homare.b.a.b.x;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.p;
/*      */ import jp.cssj.homare.b.a.f;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.a.k;
/*      */ import jp.cssj.homare.b.a.l;
/*      */ import jp.cssj.homare.b.a.m;
/*      */ import jp.cssj.homare.b.a.p;
/*      */ import jp.cssj.homare.b.c.g;
/*      */ import jp.cssj.homare.b.f.e;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class i
/*      */   implements g
/*      */ {
/*      */   protected static class a
/*      */     extends c
/*      */   {
/*      */     public final l b;
/*      */     public final double c;
/*      */     
/*      */     public a(int serial, l box, double pageAxis) {
/*   48 */       super(serial);
/*   49 */       this.b = box;
/*   50 */       this.c = pageAxis;
/*      */     }
/*      */     
/*      */     public j a() {
/*   54 */       return (j)this.b;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*   60 */   protected int b = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   protected List<a> e = null;
/*      */   
/*   67 */   protected h f = null;
/*      */   
/*   69 */   protected b g = null;
/*      */ 
/*      */   
/*      */   protected c a;
/*      */ 
/*      */   
/*      */   public final byte a() {
/*   76 */     return 1;
/*      */   }
/*      */   
/*      */   public final void a(c box) {
/*   80 */     this.a = box;
/*      */   }
/*      */   
/*      */   public final void a(l box, double pageAxis) {
/*   84 */     if (!h && box == null) throw new AssertionError(); 
/*   85 */     a(++this.b, box, pageAxis);
/*      */   }
/*      */   
/*      */   private final void a(int serial, l box, double pageAxis) {
/*   89 */     if (!h && box == null) throw new AssertionError(); 
/*   90 */     a flow = new a(serial, box, pageAxis);
/*   91 */     if (this.e == null) {
/*   92 */       this.e = new ArrayList<>();
/*      */     }
/*   94 */     this.e.add(flow);
/*      */   }
/*      */   
/*      */   public final void a(jp.cssj.homare.b.a.i box, double staticX, double staticY) {
/*   98 */     if (this.g == null) {
/*   99 */       this.g = new b();
/*      */     }
/*  101 */     this.g.a(box, staticX, staticY);
/*      */   }
/*      */   
/*      */   public final void a(k box, double lineAxis, double pageAxis) {
/*  105 */     if (this.f == null) {
/*  106 */       this.f = new h();
/*      */     }
/*  108 */     h.a floating = new h.a(++this.b, box, lineAxis, pageAxis);
/*  109 */     this.f.a(floating);
/*      */   }
/*      */   
/*      */   public boolean j() {
/*  113 */     return (this.e != null && !this.e.isEmpty());
/*      */   }
/*      */   
/*      */   public boolean i() {
/*  117 */     return (this.f != null && this.f.a() > 0); } public double f() { double ascent;
/*      */     c containerBox;
/*      */     x textBox;
/*      */     double firstAscent;
/*  121 */     a flow = b();
/*  122 */     if (flow == null) {
/*  123 */       return 1.722773839210782E308D;
/*      */     }
/*      */ 
/*      */     
/*  127 */     switch (flow.b.a()) {
/*      */       case 5:
/*  129 */         containerBox = (c)flow.b;
/*  130 */         firstAscent = containerBox.n();
/*  131 */         if (e.a(firstAscent)) {
/*  132 */           return firstAscent;
/*      */         }
/*  134 */         ascent = firstAscent;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  139 */         textBox = (x)flow.b;
/*  140 */         firstAscent = textBox.h();
/*  141 */         ascent = firstAscent;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/*      */       case 7:
/*  147 */         ascent = flow.b.q();
/*      */         break;
/*      */       default:
/*  150 */         throw new IllegalStateException(String.valueOf(flow.b.a()));
/*      */     } 
/*      */     
/*  153 */     switch ((this.a.c_()).D) {
/*      */       
/*      */       case 1:
/*  156 */         ascent += this.a.m().a();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  169 */         return ascent;case 2: ascent += this.a.m().b(); return ascent;case 3: ascent += this.a.m().d(); return ascent;
/*      */     }  throw new IllegalStateException(); } public double g() { double descent; c containerBox;
/*      */     x textBox;
/*      */     double lastDescent;
/*  173 */     a flow = c();
/*  174 */     if (flow == null) {
/*  175 */       return 1.722773839210782E308D;
/*      */     }
/*      */ 
/*      */     
/*  179 */     switch (flow.b.a()) {
/*      */       case 5:
/*  181 */         containerBox = (c)flow.b;
/*  182 */         lastDescent = containerBox.o();
/*  183 */         if (e.a(lastDescent)) {
/*  184 */           return lastDescent;
/*      */         }
/*  186 */         descent = lastDescent;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  191 */         textBox = (x)flow.b;
/*  192 */         lastDescent = textBox.i();
/*  193 */         descent = lastDescent;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/*      */       case 7:
/*  199 */         descent = 0.0D;
/*      */         break;
/*      */       default:
/*  202 */         throw new IllegalStateException();
/*      */     } 
/*      */     
/*  205 */     switch ((this.a.c_()).D) {
/*      */       
/*      */       case 1:
/*  208 */         descent += this.a.m().c();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  221 */         return descent;case 2: descent += this.a.m().b(); return descent;case 3: descent += this.a.m().d(); return descent;
/*      */     } 
/*      */     throw new IllegalStateException(); }
/*      */    public double h() {
/*  225 */     a flow = c();
/*  226 */     if (flow == null) {
/*  227 */       return 0.0D;
/*      */     }
/*  229 */     if (e.a((this.a.c_()).D))
/*      */     {
/*  231 */       return flow.c + flow.b.p();
/*      */     }
/*  233 */     return flow.c + flow.b.q();
/*      */   }
/*      */   
/*      */   public double a(double pageAxis) {
/*  237 */     if (e.a((this.a.c_()).D)) {
/*      */       
/*  239 */       if (j()) {
/*  240 */         for (int j = 0; j < this.e.size(); j++) {
/*  241 */           a flow = this.e.get(j);
/*  242 */           double bottom = flow.c + flow.b.p();
/*  243 */           if (e.a(bottom, pageAxis) >= 0) {
/*  244 */             if (flow.b.a() == 5) {
/*  245 */               f blockBox = (f)flow.b;
/*  246 */               if ((blockBox.c_()).U == 1) {
/*  247 */                 pageAxis = bottom;
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*  253 */               pageAxis = flow.c + blockBox.e().a(pageAxis - flow.c - blockBox.m().d()) + blockBox.m().f(); break;
/*  254 */             }  if (flow.b.a() == 2) {
/*  255 */               x blockBox = (x)flow.b;
/*  256 */               pageAxis = flow.c + blockBox.a(pageAxis - flow.c); break;
/*      */             } 
/*  258 */             pageAxis = bottom;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  264 */       if (i()) {
/*  265 */         for (int j = 0; j < this.f.a(); j++) {
/*  266 */           h.a floaing = this.f.a(j);
/*  267 */           double bottom = floaing.d + floaing.b.p();
/*  268 */           if (e.a(bottom, pageAxis) >= 0) {
/*  269 */             pageAxis = bottom;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } else {
/*  276 */       if (j()) {
/*  277 */         for (int j = 0; j < this.e.size(); j++) {
/*  278 */           a flow = this.e.get(j);
/*  279 */           double bottom = flow.c + flow.b.q();
/*  280 */           if (e.a(bottom, pageAxis) >= 0) {
/*  281 */             if (flow.b.a() == 5) {
/*  282 */               f blockBox = (f)flow.b;
/*  283 */               if ((blockBox.c_()).U == 1) {
/*  284 */                 pageAxis = bottom;
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*  290 */               pageAxis = flow.c + blockBox.e().a(pageAxis - flow.c - blockBox.m().a()) + blockBox.m().e(); break;
/*  291 */             }  if (flow.b.a() == 2) {
/*  292 */               x blockBox = (x)flow.b;
/*  293 */               pageAxis = flow.c + blockBox.a(pageAxis - flow.c); break;
/*      */             } 
/*  295 */             pageAxis = bottom;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  301 */       if (i()) {
/*  302 */         for (int j = 0; j < this.f.a(); j++) {
/*  303 */           h.a floaing = this.f.a(j);
/*  304 */           double bottom = floaing.d + floaing.b.q();
/*  305 */           if (e.a(bottom, pageAxis) >= 0) {
/*  306 */             pageAxis = bottom;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*  312 */     return pageAxis;
/*      */   }
/*      */   
/*      */   protected a b() {
/*  316 */     if (this.e == null || this.e.isEmpty()) {
/*  317 */       return null;
/*      */     }
/*  319 */     return this.e.get(0);
/*      */   }
/*      */   
/*      */   protected a c() {
/*  323 */     if (this.e == null || this.e.isEmpty()) {
/*  324 */       return null;
/*      */     }
/*  326 */     return this.e.get(this.e.size() - 1);
/*      */   }
/*      */   
/*      */   public boolean e() {
/*  330 */     if (this.e == null || this.e.isEmpty()) {
/*  331 */       return false;
/*      */     }
/*  333 */     for (int j = 0; j < this.e.size(); j++) {
/*  334 */       a flow = this.e.get(j);
/*  335 */       if (flow.b.c()) {
/*  336 */         return true;
/*      */       }
/*  338 */       if (flow.b.q() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/*  343 */     return false;
/*      */   }
/*      */   
/*      */   public boolean d() {
/*  347 */     if (this.e == null || this.e.isEmpty()) {
/*  348 */       return false;
/*      */     }
/*  350 */     for (int j = this.e.size() - 1; j >= 0; j--) {
/*  351 */       a flow = this.e.get(j);
/*  352 */       if (flow.b.f()) {
/*  353 */         return true;
/*      */       }
/*  355 */       if (flow.b.q() > 0.0D) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/*  360 */     return false;
/*      */   }
/*      */   public void a(m containerBox) {
/*      */     c c1;
/*  364 */     if (this.a.g()) {
/*  365 */       c1 = this.a;
/*      */     }
/*  367 */     if (this.e != null) {
/*  368 */       for (int j = 0; j < this.e.size(); j++) {
/*  369 */         a flow = this.e.get(j);
/*  370 */         flow.b.a((m)c1);
/*      */       } 
/*      */     }
/*  373 */     if (this.f != null) {
/*  374 */       for (int j = 0; j < this.f.a(); j++) {
/*  375 */         h.a a = this.f.a(j);
/*  376 */         a.b.a((m)c1);
/*      */       } 
/*      */     }
/*  379 */     if (this.g != null) {
/*  380 */       for (int j = 0; j < this.g.a(); j++) {
/*  381 */         b.a a = this.g.a(j);
/*  382 */         a.a.a((m)c1);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public final void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/*      */     int j;
/*  389 */     if (this.e == null) {
/*      */       return;
/*      */     }
/*  392 */     switch ((this.a.c_()).D) {
/*      */ 
/*      */       
/*      */       case 1:
/*  396 */         for (j = 0; j < this.e.size(); j++) {
/*  397 */           a a = this.e.get(j);
/*  398 */           if (a.b.a() == 5 && ((p)a.b.b_()).d == null) {
/*  399 */             jp.cssj.homare.b.a.a blockBox = (jp.cssj.homare.b.a.a)a.b;
/*  400 */             blockBox.a(pageBox, drawer, clip, transform, x, y + a.c);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 2:
/*      */       case 3:
/*  407 */         x += this.a.s();
/*  408 */         for (j = 0; j < this.e.size(); j++) {
/*      */           
/*  410 */           a a = this.e.get(j);
/*  411 */           if (a.b.a() == 5 && ((p)a.b.b_()).d == null) {
/*  412 */             jp.cssj.homare.b.a.a blockBox = (jp.cssj.homare.b.a.a)a.b;
/*  413 */             blockBox.a(pageBox, drawer, clip, transform, x - a.c + -blockBox.p(), y);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */     } 
/*  418 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/*      */     int j;
/*  424 */     if (this.e == null) {
/*      */       return;
/*      */     }
/*  427 */     switch ((this.a.c_()).D) {
/*      */ 
/*      */       
/*      */       case 1:
/*  431 */         for (j = 0; j < this.e.size(); j++) {
/*  432 */           a a1 = this.e.get(j);
/*  433 */           a1.b.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y + a1.c);
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 2:
/*      */       case 3:
/*  439 */         x += this.a.s();
/*  440 */         for (j = 0; j < this.e.size(); j++) {
/*      */           
/*  442 */           a a1 = this.e.get(j);
/*  443 */           a1.b.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x - a1.c - a1.b
/*  444 */               .p(), y);
/*      */         } 
/*      */         return;
/*      */     } 
/*  448 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void b(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/*  454 */     if (this.f == null) {
/*      */       return;
/*      */     }
/*  457 */     this.f.a(this.a, pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void c(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/*  462 */     if (this.g == null) {
/*      */       return;
/*      */     }
/*  465 */     this.g.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*      */   }
/*      */   public g a(double pageLimit, d mode, byte flags) {
/*      */     double frameStart, pageSize, pageInnerSize;
/*  469 */     boolean vertical = e.a((this.a.c_()).D);
/*      */     
/*  471 */     if (vertical) {
/*  472 */       frameStart = this.a.m().d();
/*  473 */       pageSize = this.a.p();
/*  474 */       pageInnerSize = this.a.s();
/*      */     } else {
/*  476 */       frameStart = this.a.m().a();
/*  477 */       pageSize = this.a.q();
/*  478 */       pageInnerSize = this.a.t();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  486 */     if (mode.a() == 1) {
/*      */       int index;
/*      */       
/*  489 */       d.b force = (d.b)mode;
/*      */       
/*  491 */       i i1 = new i();
/*  492 */       if (this.a != force.d) {
/*  493 */         index = this.e.size() - 1;
/*  494 */         byte lflags = -1;
/*  495 */         if (index != 0) {
/*  496 */           lflags = (byte)(lflags ^ 0x1);
/*      */         }
/*  498 */         if (index != this.e.size() - 1) {
/*  499 */           lflags = (byte)(lflags ^ 0x2);
/*      */         }
/*  501 */         a flow = this.e.get(index);
/*  502 */         p flowBox = (p)flow.b;
/*  503 */         l nextFlowBox = (l)flowBox.b(pageLimit - flow.c, mode, (byte)(lflags & flags));
/*      */         
/*  505 */         if (!h && nextFlowBox == null) throw new AssertionError("force break failed"); 
/*  506 */         if (!h && nextFlowBox == flowBox) throw new AssertionError(); 
/*  507 */         i1.a(flow.a, nextFlowBox, 0.0D);
/*      */       } else {
/*  509 */         index = (this.e == null) ? 0 : this.e.size();
/*      */       } 
/*  511 */       i1.f = a(pageLimit, flags, index);
/*  512 */       if (i1.f == this.f) {
/*  513 */         this.f = null;
/*      */       }
/*  515 */       if (!h && i1 == null) throw new AssertionError(); 
/*  516 */       if (!h && i1 == this) throw new AssertionError(); 
/*  517 */       return i1;
/*      */     } 
/*      */     
/*  520 */     if (e.a(pageLimit, 0.0D) <= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  528 */       if ((flags & 0x4) != 0)
/*      */       {
/*  530 */         return b(pageLimit, flags);
/*      */       }
/*  532 */       if ((flags & 0x1) != 0) {
/*      */         
/*  534 */         if (e.a(frameStart, 0.0D) > 0)
/*      */         {
/*  536 */           return b(pageLimit, flags);
/*      */         }
/*      */         
/*  539 */         return a((g)null, pageLimit, flags);
/*      */       } 
/*      */       
/*  542 */       return this;
/*      */     } 
/*  544 */     if ((flags & 0x6) == 0 && 
/*  545 */       e.a(pageLimit, pageSize) >= 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  553 */       return a((g)null, pageLimit, flags);
/*      */     }
/*  555 */     double prevPageSize = pageLimit;
/*  556 */     if ((flags & 0x6) == 0 && 
/*  557 */       e.a(pageLimit, pageInnerSize) >= 0) {
/*  558 */       pageLimit = pageInnerSize - 1.0D;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  566 */     if (this.e == null || this.e.isEmpty()) {
/*      */       
/*  568 */       if ((flags & 0x4) != 0)
/*      */       {
/*  570 */         return c(prevPageSize, flags);
/*      */       }
/*  572 */       if ((flags & 0x1) != 0) {
/*      */         
/*  574 */         if (e.a(pageInnerSize, 0.0D) > 0) {
/*  575 */           return c(prevPageSize, flags);
/*      */         }
/*  577 */         return a((g)null, prevPageSize, flags);
/*      */       } 
/*      */       
/*  580 */       if ((flags & 0x2) != 0 || e.a(pageSize, 0.0D) > 0) {
/*  581 */         return a(this, prevPageSize, flags);
/*      */       }
/*  583 */       return a((g)null, prevPageSize, flags);
/*      */     } 
/*      */ 
/*      */     
/*  587 */     jp.cssj.homare.b.a.c.i params = this.a.c_();
/*      */     int lastOrphan;
/*  589 */     for (lastOrphan = this.e.size() - 1; lastOrphan >= 0; lastOrphan--) {
/*  590 */       a flow = this.e.get(lastOrphan);
/*  591 */       double lastBottom = flow.c;
/*  592 */       if (flow.b.a() == 5) {
/*  593 */         f flowBlock = (f)flow.b;
/*  594 */         switch (params.D) {
/*      */           
/*      */           case 1:
/*  597 */             lastBottom += Math.max(flowBlock.t(), flowBlock.w()) + flowBlock
/*  598 */               .m().a();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2:
/*  603 */             lastBottom += Math.max(flowBlock.s(), flowBlock.w()) + flowBlock
/*  604 */               .m().d();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/*  609 */             lastBottom += Math.max(flowBlock.s(), flowBlock.w()) + flowBlock
/*  610 */               .m().b();
/*      */             break;
/*      */           
/*      */           default:
/*  614 */             throw new IllegalStateException();
/*      */         } 
/*      */       
/*  617 */       } else if (e.a(params.D)) {
/*  618 */         lastBottom += flow.b.p();
/*      */       } else {
/*  620 */         lastBottom += flow.b.q();
/*      */       } 
/*      */       
/*  623 */       if (e.a(lastBottom, pageLimit) <= 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*  627 */     lastOrphan++;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  632 */     if (lastOrphan == this.e.size()) {
/*      */       
/*  634 */       if ((flags & 0x2) == 0) {
/*  635 */         if ((flags & 0x4) != 0 || (flags & 0x1) == 0) {
/*  636 */           return c(prevPageSize, flags);
/*      */         }
/*  638 */         a flow = this.e.get(this.e.size() - 1);
/*  639 */         double contentHeight = flow.c;
/*  640 */         if (vertical) {
/*  641 */           contentHeight += flow.b.p();
/*      */         } else {
/*  643 */           contentHeight += flow.b.q();
/*      */         } 
/*  645 */         if (e.a(pageInnerSize, contentHeight) > 0)
/*      */         {
/*  647 */           return c(prevPageSize, flags);
/*      */         }
/*      */         
/*  650 */         g next = a((g)null, prevPageSize, flags);
/*  651 */         return next;
/*      */       } 
/*  653 */       lastOrphan = this.e.size() - 1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  660 */     i nextBox = null;
/*  661 */     boolean ignoreAvoid = false;
/*  662 */     double savePageLimit = pageLimit;
/*      */     
/*  664 */     for (int j = lastOrphan; j < this.e.size(); j++) {
/*  665 */       l nextFlowBox; p prevFlowBox; jp.cssj.homare.b.a.c.i cParams; double prevFlowPageSize; a prevFlow = this.e.get(j);
/*  666 */       double splitLine = pageLimit - prevFlow.c;
/*  667 */       byte lflags = -1;
/*  668 */       if (e.a(prevFlow.c, 0.0D) > 0)
/*      */       {
/*  670 */         lflags = (byte)(lflags ^ 0x1);
/*      */       }
/*  672 */       if (((d.a)mode).d == this.a || j != this.e.size() - 1)
/*      */       {
/*  674 */         lflags = (byte)(lflags ^ 0x2);
/*      */       }
/*  676 */       byte xflags = (byte)(lflags & flags);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  685 */       switch (prevFlow.b.a()) {
/*      */         case 2:
/*      */         case 7:
/*  688 */           prevFlowBox = (p)prevFlow.b;
/*  689 */           nextFlowBox = (l)prevFlowBox.b(splitLine, mode, xflags);
/*      */           break;
/*      */         
/*      */         case 5:
/*  693 */           cParams = ((c)prevFlow.b).c_();
/*      */           
/*  695 */           if ((cParams.U != 1 || (xflags & 0x1) != 0) && vertical == 
/*  696 */             e.a(cParams.D)) {
/*  697 */             p p = (p)prevFlow.b;
/*  698 */             nextFlowBox = (l)p.b(splitLine, mode, xflags);
/*      */             break;
/*      */           } 
/*  701 */           if ((xflags & 0x2) != 0) {
/*      */             
/*  703 */             nextFlowBox = prevFlow.b;
/*      */             break;
/*      */           } 
/*      */ 
/*      */         
/*      */         case 6:
/*  709 */           if (vertical) {
/*  710 */             prevFlowPageSize = prevFlow.b.p();
/*      */           } else {
/*  712 */             prevFlowPageSize = prevFlow.b.q();
/*      */           } 
/*  714 */           if ((xflags & 0x1) != 0 || 
/*  715 */             e.a(splitLine, prevFlowPageSize) >= 0) {
/*      */             
/*  717 */             nextFlowBox = null;
/*      */             break;
/*      */           } 
/*  720 */           nextFlowBox = prevFlow.b;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  725 */           throw new IllegalStateException(prevFlow.b.toString());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  732 */       if (nextFlowBox == null) {
/*  733 */         if ((xflags & 0x2) != 0)
/*      */         {
/*  735 */           return null;
/*      */         }
/*  737 */         if (j >= lastOrphan) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */         
/*  742 */         nextFlowBox = prevFlow.b;
/*      */       } 
/*  744 */       if (nextFlowBox == prevFlow.b) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  749 */         if ((lflags & 0x1) != 0) {
/*      */           
/*  751 */           pageLimit = savePageLimit;
/*  752 */           if ((flags & 0x4) != 0)
/*      */           {
/*  754 */             return b(prevPageSize, flags);
/*      */           }
/*  756 */           if ((flags & 0x1) != 0) {
/*      */             
/*  758 */             if (j < lastOrphan) {
/*      */               
/*  760 */               j = lastOrphan - 1;
/*  761 */               ignoreAvoid = true;
/*      */             } else {
/*      */               
/*  764 */               if ((flags & 0x2) != 0)
/*      */               {
/*  766 */                 return c(prevPageSize, flags);
/*      */               }
/*      */               
/*  769 */               return a((g)null, prevPageSize, flags);
/*      */             } 
/*      */           } else {
/*  772 */             return this;
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  778 */         if (!ignoreAvoid && j > 0 && j <= lastOrphan) {
/*      */ 
/*      */           
/*  781 */           int beforeFlows = 1;
/*  782 */           boolean breakAvoid = prevFlow.b.c();
/*  783 */           a beforeFlow = this.e.get(j - 1);
/*      */           
/*  785 */           for (int m = j - 1; m >= 0; m--) {
/*  786 */             a beforeFlow2 = this.e.get(m);
/*  787 */             double beforeBottom = beforeFlow2.c;
/*  788 */             if (vertical) {
/*  789 */               beforeBottom += beforeFlow2.b.p();
/*      */             } else {
/*  791 */               beforeBottom += beforeFlow2.b.q();
/*      */             } 
/*  793 */             if (e.a(beforeBottom, prevFlow.c) < 0) {
/*  794 */               if (m == j - 1) {
/*  795 */                 breakAvoid = false;
/*      */               }
/*      */               break;
/*      */             } 
/*  799 */             beforeFlows++;
/*  800 */             beforeFlow = beforeFlow2;
/*  801 */             if (beforeFlow.b.f()) {
/*  802 */               breakAvoid = true;
/*      */             }
/*      */           } 
/*  805 */           if (breakAvoid)
/*      */           {
/*  807 */             if (this.f != null)
/*  808 */               for (int n = 0; n < this.f.a(); n++) {
/*  809 */                 double floatPageSize; h.a floating = this.f.a(n);
/*  810 */                 if (e.a(floating.d, pageLimit) >= 0) {
/*  811 */                   breakAvoid = false;
/*      */                   
/*      */                   break;
/*      */                 } 
/*  815 */                 if (vertical) {
/*  816 */                   floatPageSize = floating.b.p();
/*      */                 } else {
/*  818 */                   floatPageSize = floating.b.q();
/*      */                 } 
/*  820 */                 if (e.a(floating.d + floatPageSize, pageLimit) > 0)
/*      */                 {
/*      */                   
/*  823 */                   if (floating.b.a() != 6)
/*      */                   {
/*      */ 
/*      */                     
/*  827 */                     if ((((c)floating.b).c_()).U != 1) {
/*      */ 
/*      */                       
/*  830 */                       breakAvoid = false;
/*      */                       break;
/*      */                     } 
/*      */                   }
/*      */                 }
/*      */               }  
/*      */           }
/*  837 */           if (breakAvoid) {
/*      */             
/*  839 */             if (!h && beforeFlows < 2) throw new AssertionError(); 
/*  840 */             j -= beforeFlows;
/*  841 */             pageLimit = beforeFlow.c - 1.0D;
/*  842 */             if (vertical) {
/*  843 */               pageLimit += beforeFlow.b.p();
/*  844 */               if (beforeFlow.b.a() == 5) {
/*  845 */                 pageLimit -= ((c)beforeFlow.b).m().b();
/*      */               }
/*      */             } else {
/*  848 */               pageLimit += beforeFlow.b.q();
/*  849 */               if (beforeFlow.b.a() == 5) {
/*  850 */                 pageLimit -= ((c)beforeFlow.b).m().c();
/*      */               }
/*      */             } 
/*      */             continue;
/*      */           } 
/*      */         } 
/*  856 */         nextBox = new i();
/*  857 */         nextBox.e = new ArrayList<>();
/*      */       } else {
/*  859 */         nextBox = new i();
/*  860 */         nextBox.a(nextFlowBox, 0.0D);
/*  861 */         j++;
/*      */       } 
/*  863 */       int remove = 0; int k;
/*  864 */       for (k = j; k < this.e.size(); k++) {
/*  865 */         a f = this.e.get(k);
/*  866 */         nextBox.e.add(f);
/*  867 */         remove++;
/*      */       } 
/*  869 */       for (k = 0; k < remove; k++) {
/*  870 */         this.e.remove(this.e.size() - 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  877 */     if (nextBox == null) {
/*      */ 
/*      */       
/*  880 */       if (!h && (flags & 0x2) != 0 && ((d.a)mode).d != this.a) throw new AssertionError(); 
/*  881 */       pageLimit = savePageLimit;
/*  882 */       if ((flags & 0x4) != 0)
/*      */       {
/*  884 */         return c(prevPageSize, flags);
/*      */       }
/*  886 */       if ((flags & 0x1) != 0) {
/*      */         
/*  888 */         a flow = this.e.get(this.e.size() - 1);
/*  889 */         double contentHeight = flow.c;
/*  890 */         if (vertical) {
/*  891 */           contentHeight += flow.b.p();
/*      */         } else {
/*  893 */           contentHeight += flow.b.q();
/*      */         } 
/*  895 */         if (e.a(pageInnerSize, contentHeight) > 0)
/*      */         {
/*  897 */           return c(prevPageSize, flags);
/*      */         }
/*  899 */         return a((g)null, prevPageSize, flags);
/*      */       } 
/*  901 */       if (lastOrphan == 0)
/*      */       {
/*  903 */         return a(this, prevPageSize, flags);
/*      */       }
/*      */       
/*  906 */       return c(prevPageSize, flags);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  913 */     return a(nextBox, prevPageSize, flags);
/*      */   }
/*      */   
/*      */   public g a(g nextBox, double pageLimit, byte flags) {
/*  917 */     if (!h && (flags & 0x4) != 0 && nextBox == null) throw new AssertionError(); 
/*  918 */     if (!h && (flags & 0x4) != 0 && nextBox == this) throw new AssertionError(); 
/*  919 */     int flowCount = (this.e == null) ? 0 : this.e.size();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  924 */     h nextFloatings = a(pageLimit, flags, flowCount);
/*  925 */     if (nextFloatings != null) {
/*  926 */       if (nextFloatings == this.f) {
/*  927 */         if (nextBox == this) {
/*  928 */           return this;
/*      */         }
/*  930 */         if (nextBox == null && (flags & 0x1) == 0 && 
/*  931 */           e.a(
/*  932 */             e.a((this.a.c_()).D) ? this.a.s() : this.a
/*  933 */             .t(), 0.0D) <= 0)
/*      */         {
/*  935 */           return this;
/*      */         }
/*      */         
/*  938 */         this.f = null;
/*      */       } 
/*  940 */       if (nextBox == null || nextBox == this) {
/*  941 */         nextBox = new i();
/*      */       }
/*  943 */       ((i)nextBox).f = nextFloatings;
/*      */     } 
/*  945 */     return nextBox;
/*      */   }
/*      */   
/*      */   public final h a(double pageLimit, byte flags) {
/*  949 */     int flowCount = (this.e == null) ? 0 : this.e.size();
/*  950 */     h nextFloatings = a(pageLimit, flags, flowCount);
/*  951 */     if (nextFloatings == this.f) {
/*  952 */       this.f = null;
/*      */     }
/*  954 */     return nextFloatings;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private h a(double pageLimit, byte flags, int index) {
/*      */     h nextFloatings;
/*  961 */     if (this.f != null) {
/*      */       
/*  963 */       nextFloatings = this.f.a(this.a, pageLimit, flags);
/*  964 */       if (this.f.a() == 0) {
/*  965 */         this.f = null;
/*      */       }
/*      */     } else {
/*  968 */       nextFloatings = null;
/*      */     } 
/*  970 */     for (int j = 0; j < index; j++) {
/*  971 */       c blockBox; double pageAxis; h floatings; int k; a flow = this.e.get(j);
/*  972 */       byte lflags = -1;
/*  973 */       if (j != 0) {
/*  974 */         lflags = (byte)(lflags ^ 0x1);
/*      */       }
/*  976 */       if (j != this.e.size() - 1) {
/*  977 */         lflags = (byte)(lflags ^ 0x2);
/*      */       }
/*  979 */       switch (flow.b.a()) {
/*      */         case 5:
/*  981 */           blockBox = (c)flow.b;
/*  982 */           pageAxis = pageLimit - flow.c;
/*  983 */           if (e.a((blockBox.c_()).D)) {
/*  984 */             pageAxis -= blockBox.m().d();
/*      */           } else {
/*  986 */             pageAxis -= blockBox.m().a();
/*      */           } 
/*  988 */           floatings = blockBox.e().a(pageAxis, (byte)(lflags & flags));
/*  989 */           if (floatings == null) {
/*      */             break;
/*      */           }
/*  992 */           if (nextFloatings == this.f) {
/*  993 */             this.f = null;
/*      */           }
/*  995 */           if (nextFloatings == null) {
/*  996 */             nextFloatings = floatings;
/*      */             break;
/*      */           } 
/*  999 */           for (k = 0; k < floatings.a(); k++) {
/* 1000 */             nextFloatings.a(floatings.a(k));
/*      */           }
/*      */           break;
/*      */       } 
/*      */     } 
/* 1005 */     if (!h && nextFloatings != null && nextFloatings.a() == 0) throw new AssertionError(); 
/* 1006 */     return nextFloatings;
/*      */   }
/*      */   
/*      */   private i b(double pageLimit, byte flags) {
/* 1010 */     if (pageLimit < 0.0D) {
/* 1011 */       pageLimit = 0.0D;
/*      */     }
/* 1013 */     i nextBox = new i();
/* 1014 */     if (this.e != null) {
/* 1015 */       nextBox.e = this.e;
/* 1016 */       this.e = null;
/*      */     } 
/* 1018 */     nextBox.f = a(pageLimit, flags, 0);
/* 1019 */     if (nextBox.f == this.f) {
/* 1020 */       this.f = null;
/*      */     }
/* 1022 */     return nextBox;
/*      */   }
/*      */   
/*      */   private i c(double pageLimit, byte flags) {
/* 1026 */     i nextBox = new i();
/* 1027 */     int flowCount = (this.e == null) ? 0 : this.e.size();
/* 1028 */     nextBox.f = a(pageLimit, flags, flowCount);
/* 1029 */     if (nextBox.f == this.f) {
/* 1030 */       this.f = null;
/*      */     }
/* 1032 */     return nextBox;
/*      */   }
/*      */   
/*      */   public final void a(StringBuffer textBuff) {
/* 1036 */     if (this.e == null) {
/*      */       return;
/*      */     }
/* 1039 */     for (int j = 0; j < this.e.size(); j++) {
/*      */       
/* 1041 */       a a = this.e.get(j);
/* 1042 */       a.b.a(textBuff);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void a(jp.cssj.homare.b.b.a.a builder, int depth, boolean restyleAbsolutes) {
/* 1047 */     List<c> items = null;
/* 1048 */     if (this.f != null) {
/* 1049 */       h floatings = this.f;
/* 1050 */       this.f = null;
/* 1051 */       int size = floatings.a();
/* 1052 */       if (size > 0) {
/* 1053 */         if (items == null) {
/* 1054 */           items = new ArrayList<>();
/*      */         }
/* 1056 */         for (int j = 0; j < size; j++) {
/* 1057 */           items.add(floatings.a(j));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1062 */     if (restyleAbsolutes && this.g != null) {
/* 1063 */       b absolutes = this.g;
/* 1064 */       this.g = null;
/* 1065 */       int size = absolutes.a();
/* 1066 */       for (int j = 0; j < size; j++) {
/* 1067 */         builder.a((j)(absolutes.a(j)).a);
/*      */       }
/*      */     } 
/*      */     
/* 1071 */     a lastFlow = null;
/* 1072 */     if (this.e != null) {
/* 1073 */       List<a> flows = this.e;
/* 1074 */       this.e = null;
/* 1075 */       int size = flows.size();
/* 1076 */       if (size > 0) {
/* 1077 */         if (items == null) {
/* 1078 */           items = new ArrayList<>();
/*      */         }
/* 1080 */         for (int j = 0; j < size; j++) {
/* 1081 */           items.add(flows.get(j));
/*      */         }
/* 1083 */         lastFlow = flows.get(size - 1);
/*      */       } 
/*      */     } 
/*      */     
/* 1087 */     if (items != null) {
/* 1088 */       Collections.sort(items);
/* 1089 */       int size = items.size();
/* 1090 */       for (int j = 0; j < size; j++) {
/* 1091 */         x textBlock; r tableBox; f replacedBox; c holder = items.get(j);
/* 1092 */         switch (holder.a().a()) {
/*      */           
/*      */           case 2:
/* 1095 */             textBlock = (x)holder.a();
/* 1096 */             textBlock.a(builder);
/*      */             
/* 1098 */             if (lastFlow != holder || depth != 1) {
/* 1099 */               builder.f();
/*      */             }
/*      */             break;
/*      */           
/*      */           case 5:
/* 1104 */             if (holder.a().b_().a() != 6) {
/* 1105 */               c containerBox = (c)holder.a();
/* 1106 */               if (e.a((containerBox.c_()).D) != 
/* 1107 */                 e.a((builder.j().c_()).D)) {
/*      */                 
/* 1109 */                 builder.a((j)containerBox);
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/* 1114 */               if (lastFlow == holder && depth >= 1) {
/* 1115 */                 containerBox.a(builder, depth - 1); break;
/*      */               } 
/* 1117 */               containerBox.a(builder, 0);
/*      */               
/*      */               break;
/*      */             } 
/* 1121 */             ((h.a)holder).a(builder);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 7:
/* 1128 */             tableBox = (r)holder.a();
/* 1129 */             builder.a((j)tableBox);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 6:
/* 1134 */             replacedBox = (f)holder.a();
/* 1135 */             if (replacedBox.b_().a() != 6) {
/* 1136 */               builder.a((j)replacedBox); break;
/*      */             } 
/* 1138 */             ((h.a)holder).a(builder);
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 1143 */             throw new IllegalStateException(holder.a().toString());
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double k() {
/* 1150 */     if (this.e == null) {
/* 1151 */       return 0.0D;
/*      */     }
/* 1153 */     double width = 0.0D;
/* 1154 */     for (int j = 0; j < this.e.size(); j++) {
/* 1155 */       a flow = this.e.get(j);
/* 1156 */       width = Math.max(width, flow.b.p());
/*      */     } 
/* 1158 */     return width;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1162 */     return super.toString() + "/flowCount=" + ((this.e == null) ? 0 : this.e.size());
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */