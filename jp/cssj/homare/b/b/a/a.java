/*      */ package jp.cssj.homare.b.b.a;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.r;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.o;
/*      */ import jp.cssj.homare.b.a.c.p;
/*      */ import jp.cssj.homare.b.a.c.t;
/*      */ import jp.cssj.homare.b.a.f;
/*      */ import jp.cssj.homare.b.a.g;
/*      */ import jp.cssj.homare.b.a.i;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.a.k;
/*      */ import jp.cssj.homare.b.a.l;
/*      */ import jp.cssj.homare.b.a.m;
/*      */ import jp.cssj.homare.b.b.a;
/*      */ import jp.cssj.homare.b.b.b;
/*      */ import jp.cssj.homare.b.b.c;
/*      */ import jp.cssj.homare.b.b.d;
/*      */ import jp.cssj.homare.b.b.f;
/*      */ import jp.cssj.homare.b.e.b;
/*      */ import jp.cssj.homare.b.f.e;
/*      */ import jp.cssj.sakae.c.a.f;
/*      */ import jp.cssj.sakae.c.a.h;
/*      */ import jp.cssj.sakae.c.d.g;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class a
/*      */   implements a, c
/*      */ {
/*      */   static {
/*   47 */     o = Logger.getLogger(a.class.getName());
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
/*   96 */     q = new Comparator() { public int compare(Object o1, Object o2) {
/*      */           double a;
/*      */           double b;
/*   99 */           if (o1 instanceof c.a) {
/*  100 */             c.a c1 = (c.a)o1;
/*  101 */             a = c1.e;
/*      */           } else {
/*  103 */             Double c1 = (Double)o1;
/*  104 */             a = c1.doubleValue();
/*      */           } 
/*  106 */           if (o2 instanceof c.a) {
/*  107 */             c.a c2 = (c.a)o2;
/*  108 */             b = c2.e;
/*      */           } else {
/*  110 */             Double c2 = (Double)o2;
/*  111 */             b = c2.doubleValue();
/*      */           } 
/*  113 */           return (a > b) ? 1 : ((a == b) ? 0 : -1);
/*      */         } }
/*      */       ;
/*      */   } protected List<c.b> c = null; protected k d = null; protected byte g = 0; protected double h = 0.0D, i = 0.0D; protected double j = 0.0D; protected double k = 0.0D; protected List<k> l = null; protected List<c.a> m = null; private List<List<c.a>> p = null; private static final Logger o; protected d a; protected c.b b; public static final byte e = 1; public static final byte f = 2; private static Comparator<Object> q;
/*      */   public a(d layoutStack, c contextBox) {
/*  118 */     this.a = layoutStack;
/*  119 */     if (contextBox != null) {
/*  120 */       this.b = new c.b(contextBox, 0.0D, 0.0D);
/*      */     }
/*      */   }
/*      */   
/*      */   public a i() {
/*  125 */     return (a)this.a;
/*      */   }
/*      */   
/*      */   public c r() {
/*  129 */     c box = j();
/*  130 */     if ((box.c_()).X.a() != 3 && (
/*  131 */       (box.c_()).X.a() != 2 || box
/*  132 */       .a() <= 7 || box.a() > 12)) {
/*  133 */       return box;
/*      */     }
/*      */     
/*  136 */     if (this.a == null) {
/*  137 */       return null;
/*      */     }
/*  139 */     switch (box.b_().a()) {
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 13:
/*  144 */         return this.a.p();
/*      */       
/*      */       case 7:
/*  147 */         return this.a.r();
/*      */     } 
/*  149 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   public c s() {
/*  154 */     c box = j();
/*  155 */     if ((box.c_()).X.b() != 3 && (
/*  156 */       (box.c_()).X.b() != 2 || box
/*  157 */       .a() <= 7 || box.a() > 12)) {
/*  158 */       return box;
/*      */     }
/*      */     
/*  161 */     if (this.a == null) {
/*  162 */       return null;
/*      */     }
/*  164 */     switch (box.b_().a()) {
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 13:
/*  169 */         return this.a.q();
/*      */       
/*      */       case 7:
/*  172 */         return this.a.s();
/*      */     } 
/*  174 */     throw new IllegalStateException(String.valueOf(box.getClass()));
/*      */   }
/*      */ 
/*      */   
/*      */   public double n() {
/*  179 */     double frameWidth = 0.0D;
/*  180 */     if (this.c != null) {
/*  181 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  182 */         c.b flow = this.c.get(i);
/*  183 */         i params = flow.a.c_();
/*  184 */         frameWidth += flow.a.m().f();
/*  185 */         if (!e.a(params.D))
/*      */         {
/*  187 */           return flow.a.p() - frameWidth;
/*      */         }
/*  189 */         if (flow.a.e_())
/*      */         {
/*  191 */           return flow.a.p() - frameWidth;
/*      */         }
/*      */       } 
/*      */     }
/*  195 */     c box = r();
/*  196 */     return (box == null) ? 0.0D : (box.p() - frameWidth);
/*      */   }
/*      */   
/*      */   public c p() {
/*  200 */     if (this.c != null) {
/*  201 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  202 */         c.b flow = this.c.get(i);
/*  203 */         i params = flow.a.c_();
/*  204 */         if (!e.a(params.D))
/*      */         {
/*  206 */           return flow.a;
/*      */         }
/*  208 */         if (flow.a.e_())
/*      */         {
/*  210 */           return flow.a;
/*      */         }
/*      */       } 
/*      */     }
/*  214 */     return r();
/*      */   }
/*      */   
/*      */   public c q() {
/*  218 */     if (this.c != null) {
/*  219 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  220 */         c.b flow = this.c.get(i);
/*  221 */         i params = flow.a.c_();
/*  222 */         if (e.a(params.D))
/*      */         {
/*  224 */           return flow.a;
/*      */         }
/*  226 */         if (flow.a.e_())
/*      */         {
/*  228 */           return flow.a;
/*      */         }
/*      */       } 
/*      */     }
/*  232 */     return s();
/*      */   }
/*      */   
/*      */   public double o() {
/*  236 */     double frameHeight = 0.0D;
/*  237 */     if (this.c != null) {
/*  238 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  239 */         c.b flow = this.c.get(i);
/*  240 */         i params = flow.a.c_();
/*  241 */         frameHeight += flow.a.m().e();
/*  242 */         if (e.a(params.D))
/*      */         {
/*  244 */           return flow.a.q() - frameHeight;
/*      */         }
/*  246 */         if (flow.a.e_())
/*      */         {
/*  248 */           return flow.a.q() - frameHeight;
/*      */         }
/*      */       } 
/*      */     }
/*  252 */     c box = s();
/*  253 */     return (box == null) ? 0.0D : (box.t() - frameHeight);
/*      */   }
/*      */   
/*      */   public h h() {
/*  257 */     return this.a.h();
/*      */   }
/*      */   
/*      */   private int z() {
/*  261 */     return (this.m == null) ? 0 : this.m.size();
/*      */   }
/*      */   
/*      */   private c.a b(int index) {
/*  265 */     return this.m.get(index);
/*      */   }
/*      */   
/*      */   public void a(double pageAxis) {
/*  269 */     this.k = pageAxis;
/*      */   }
/*      */   
/*      */   public double t() {
/*  273 */     return this.k;
/*      */   }
/*      */   
/*      */   public boolean d() {
/*  277 */     return false;
/*      */   }
/*      */   
/*      */   public boolean c() {
/*  281 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public c l() {
/*  288 */     if (this.c != null) {
/*  289 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  290 */         c.b flow = this.c.get(i);
/*  291 */         c c1 = flow.a;
/*  292 */         if (c1.g()) {
/*  293 */           return flow.a;
/*      */         }
/*      */       } 
/*      */     }
/*  297 */     c box = this.b.a;
/*  298 */     if (this.a == null) {
/*  299 */       return box;
/*      */     }
/*  301 */     if (!box.g()) {
/*  302 */       return this.a.l();
/*      */     }
/*  304 */     return box;
/*      */   }
/*      */   
/*      */   public c m() {
/*  308 */     if (this.c != null) {
/*  309 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  310 */         c.b flow = this.c.get(i);
/*  311 */         if (flow.a.i() > 1) {
/*  312 */           return flow.a;
/*      */         }
/*      */       } 
/*      */     }
/*  316 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   c.b u() {
/*  325 */     if (this.c != null) {
/*  326 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  327 */         c.b flow = this.c.get(i);
/*  328 */         c box = flow.a;
/*  329 */         if (box.g()) {
/*  330 */           return flow;
/*      */         }
/*      */       } 
/*      */     }
/*  334 */     return this.b;
/*      */   }
/*      */   
/*      */   public c j() {
/*  338 */     return this.b.a;
/*      */   }
/*      */   
/*      */   public c k() {
/*  342 */     return (v()).a;
/*      */   }
/*      */   
/*      */   public c.b v() {
/*  346 */     if (this.c == null || this.c.isEmpty()) {
/*  347 */       return this.b;
/*      */     }
/*  349 */     return this.c.get(this.c.size() - 1);
/*      */   }
/*      */   
/*      */   public int g() {
/*  353 */     return (this.c == null) ? 1 : (this.c.size() + 1);
/*      */   }
/*      */   
/*      */   public c.b a(int index) {
/*  357 */     if (index == 0) {
/*  358 */       return this.b;
/*      */     }
/*  360 */     return this.c.get(index - 1);
/*      */   } public void a(f flowBox) {
/*      */     double marginStart, frameStart, frameHead;
/*      */     boolean bordered;
/*  364 */     if (!n && this.d != null) throw new AssertionError(); 
/*  365 */     c containerBox = k();
/*  366 */     double xmargin = 0.0D;
/*  367 */     double lineSize = containerBox.h();
/*  368 */     if (flowBox.i() > 1) {
/*      */       
/*  370 */       double lineStart = this.j, lineEnd = this.j + lineSize;
/*  371 */       for (int i = z() - 1; i >= 0; i--) {
/*  372 */         c.a floating = b(i);
/*  373 */         double pageEnd = floating.e;
/*  374 */         if (pageEnd <= this.k) {
/*      */           break;
/*      */         }
/*  377 */         o floatingPos = floating.a.c();
/*  378 */         switch (floatingPos.e) {
/*      */           case 1:
/*  380 */             lineStart = Math.max(lineStart, floating.d);
/*      */             break;
/*      */           case 2:
/*  383 */             lineEnd = Math.min(lineEnd, floating.b);
/*      */             break;
/*      */         } 
/*  386 */         xmargin = lineStart - this.j;
/*  387 */         lineSize = lineEnd - lineStart;
/*      */       } 
/*      */     } 
/*  390 */     flowBox.a((d)this, xmargin, lineSize);
/*  391 */     p pos = flowBox.v();
/*      */     
/*  393 */     if ((flowBox.c_()).ab == 1) {
/*      */       
/*  395 */       if (this.p == null) {
/*  396 */         this.p = new ArrayList<>();
/*      */       }
/*  398 */       this.p.add(new ArrayList<>());
/*      */     } 
/*      */     
/*  401 */     i cParams = k().c_();
/*  402 */     b frame = flowBox.m();
/*      */     
/*  404 */     if (pos.c != 0 && z() > 0) {
/*      */       double d1;
/*  406 */       c.a floating = null;
/*  407 */       double pageEnd = 0.0D;
/*  408 */       if (e.a(cParams.D)) {
/*  409 */         d1 = frame.b.b;
/*      */       } else {
/*  411 */         d1 = frame.b.a;
/*      */       } 
/*  413 */       double pageStart = this.k - d1;
/*  414 */       for (int i = z() - 1; i >= 0; i--) {
/*  415 */         floating = b(i);
/*  416 */         pageEnd = floating.e - d1;
/*  417 */         if (pageEnd <= pageStart) {
/*  418 */           floating = null;
/*      */           break;
/*      */         } 
/*  421 */         o floatingPos = floating.a.c();
/*  422 */         switch (pos.c) {
/*      */           
/*      */           case 1:
/*  425 */             if (floatingPos.e == 1) {
/*      */               break;
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2:
/*  432 */             if (floatingPos.e == 2) {
/*      */               break;
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/*      */             break;
/*      */           
/*      */           default:
/*  442 */             throw new IllegalStateException();
/*      */         } 
/*  444 */         floating = null;
/*      */       } 
/*  446 */       if (floating != null) {
/*      */         
/*  448 */         this.h = this.i = 0.0D;
/*  449 */         this.k = pageEnd;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  458 */     c.b parentFlow = a(g() - 1);
/*      */ 
/*      */     
/*  461 */     if (e.a(cParams.D)) {
/*      */       
/*  463 */       marginStart = frame.b.b;
/*  464 */       frameHead = frame.a();
/*  465 */       frameStart = frame.d();
/*  466 */       bordered = (frame.c.b > 0.0D || !frame.a.c.b().b());
/*      */     } else {
/*      */       
/*  469 */       marginStart = frame.b.a;
/*  470 */       frameHead = frame.b();
/*  471 */       frameStart = frame.a();
/*  472 */       bordered = (frame.c.a > 0.0D || !frame.a.c.a().b());
/*      */     } 
/*  474 */     if (marginStart >= 0.0D) {
/*  475 */       if (marginStart > this.h) {
/*  476 */         this.k -= this.h;
/*  477 */         this.h = marginStart;
/*      */       } else {
/*  479 */         this.k -= marginStart;
/*      */       }
/*      */     
/*  482 */     } else if (marginStart < this.i) {
/*  483 */       this.k -= this.i;
/*  484 */       this.i = marginStart;
/*      */     } else {
/*  486 */       this.k -= marginStart;
/*      */     } 
/*      */     
/*  489 */     if (bordered) {
/*  490 */       this.h = this.i = 0.0D;
/*      */     }
/*      */     
/*  493 */     this.j += frameHead;
/*  494 */     parentFlow.a.a((l)flowBox, this.k - parentFlow.c);
/*  495 */     this.k += frameStart;
/*      */     
/*  497 */     if (this.c == null) {
/*  498 */       this.c = new ArrayList<>();
/*      */     }
/*  500 */     c.b flow = new c.b((c)flowBox, this.j, this.k);
/*  501 */     this.c.add(flow);
/*  502 */     this.g = 0;
/*      */   }
/*      */   public void e() { double marginEnd, frameEnd, frameHead;
/*      */     boolean bordered;
/*  506 */     if (!n && this.d != null) throw new AssertionError(); 
/*  507 */     c.b flow = this.c.remove(this.c.size() - 1);
/*  508 */     f flowBox = (f)flow.a;
/*  509 */     i params = flowBox.c_();
/*      */     
/*  511 */     if (flowBox.i() > 1 && params.ac.g == 2) {
/*      */       
/*  513 */       this.k = flow.c;
/*  514 */       flowBox.a(this);
/*      */     } 
/*  516 */     if ((flowBox.c_()).ab == 1) {
/*      */       
/*  518 */       List<?> floatings = this.p.remove(this.p.size() - 1);
/*  519 */       if (this.m != null) {
/*  520 */         this.m.removeAll(floatings);
/*      */       }
/*      */     } 
/*      */     
/*  524 */     c.b parentFlow = v();
/*  525 */     i parentParams = parentFlow.a.c_();
/*  526 */     b frame = flowBox.m();
/*      */ 
/*      */     
/*  529 */     if (e.a(parentParams.D)) {
/*      */       
/*  531 */       marginEnd = frame.b.d;
/*      */       
/*  533 */       bordered = (frame.c.d > 0.0D || !frame.a.c.d().b() || params.ab != 0 || flowBox.i() > 1);
/*  534 */       double width = flowBox.s();
/*  535 */       if (flowBox.w() != width || bordered) {
/*  536 */         this.k = flow.c + width;
/*  537 */         if (params.X.a() == 1 && width > 0.0D) {
/*  538 */           bordered = true;
/*      */         }
/*      */       } 
/*  541 */       frameEnd = frame.b();
/*  542 */       frameHead = frame.a();
/*      */     } else {
/*      */       
/*  545 */       marginEnd = frame.b.c;
/*      */       
/*  547 */       bordered = (frame.c.c > 0.0D || !frame.a.c.c().b() || params.ab != 0 || flowBox.i() > 1);
/*  548 */       double height = flowBox.t();
/*  549 */       if (flowBox.w() != height || bordered) {
/*  550 */         this.k = flow.c + height;
/*  551 */         if (params.X.b() == 1 && height > 0.0D) {
/*  552 */           bordered = true;
/*      */         }
/*      */       } 
/*  555 */       frameEnd = frame.c();
/*  556 */       frameHead = frame.b();
/*      */     } 
/*  558 */     if (bordered) {
/*  559 */       if (marginEnd >= 0.0D) {
/*  560 */         this.h = marginEnd;
/*  561 */         this.i = 0.0D;
/*      */       } else {
/*  563 */         this.h = 0.0D;
/*  564 */         this.i = marginEnd;
/*      */       }
/*      */     
/*  567 */     } else if (marginEnd >= 0.0D) {
/*  568 */       if (marginEnd > this.h) {
/*  569 */         this.k -= this.h;
/*  570 */         this.h = marginEnd;
/*      */       } else {
/*  572 */         this.k -= marginEnd;
/*      */       }
/*      */     
/*  575 */     } else if (marginEnd < this.i) {
/*  576 */       this.k -= this.i;
/*  577 */       this.i = marginEnd;
/*      */     } else {
/*  579 */       this.k -= marginEnd;
/*      */     } 
/*      */ 
/*      */     
/*  583 */     this.k += frameEnd;
/*      */     
/*  585 */     parentFlow.a.a(this.k - parentFlow.c);
/*  586 */     this.j -= frameHead; } public void a(j box) { l flowBox; k floatBox; i absoluteBox; c.b flow; jp.cssj.homare.b.a.c.a pos; i params; c contextBox; boolean vertical; c.b b1; jp.cssj.homare.b.e.a amargin; double staticX; b frame; byte clear; double d1; byte align; f replacedBox; jp.cssj.homare.b.a.a blockBox; r tableBox; t margin; p p; double lineSize; double cLineSize; double lineStop;
/*      */     double xMarginStart;
/*      */     double lineEnd;
/*      */     double xMarginEnd;
/*  590 */     switch (box.b_().a()) {
/*      */       
/*      */       case 4:
/*      */       case 8:
/*  594 */         if (!n && this.d != null) throw new AssertionError(); 
/*  595 */         flowBox = (l)box;
/*      */         
/*  597 */         flow = v();
/*  598 */         params = flow.a.c_();
/*  599 */         vertical = e.a(params.D);
/*      */ 
/*      */ 
/*      */         
/*  603 */         switch (box.a()) {
/*      */           case 6:
/*  605 */             replacedBox = (f)flowBox;
/*  606 */             e.a(this, replacedBox);
/*  607 */             frame = replacedBox.m();
/*  608 */             p = (p)flowBox.b_();
/*  609 */             clear = p.c;
/*  610 */             align = p.g;
/*      */             break;
/*      */           
/*      */           case 5:
/*  614 */             blockBox = (jp.cssj.homare.b.a.a)flowBox;
/*  615 */             frame = blockBox.m();
/*  616 */             p = (p)flowBox.b_();
/*  617 */             clear = p.c;
/*  618 */             align = p.g;
/*      */             break;
/*      */           
/*      */           case 7:
/*  622 */             tableBox = (r)flowBox;
/*  623 */             frame = tableBox.i();
/*  624 */             clear = 0;
/*  625 */             align = -1;
/*      */             break;
/*      */           
/*      */           default:
/*  629 */             throw new IllegalStateException();
/*      */         } 
/*  631 */         margin = frame.a.b;
/*  632 */         amargin = frame.b;
/*      */         
/*  634 */         if (vertical) {
/*  635 */           lineSize = box.q();
/*      */         } else {
/*  637 */           lineSize = box.p();
/*      */         } 
/*  639 */         cLineSize = flow.a.h();
/*  640 */         lineStop = this.j + cLineSize;
/*  641 */         xMarginStart = 0.0D; lineEnd = lineStop; xMarginEnd = 0.0D;
/*  642 */         if (z() > 0) {
/*      */           double pageStart;
/*      */           
/*  645 */           c.a floating = null;
/*  646 */           double pageEnd = 0.0D;
/*      */           
/*  648 */           if (vertical) {
/*  649 */             pageStart = this.k - amargin.b;
/*      */           } else {
/*  651 */             pageStart = this.k - amargin.a;
/*      */           } 
/*  653 */           for (int i = z() - 1; i >= 0; i--) {
/*  654 */             floating = b(i);
/*  655 */             pageEnd = floating.e;
/*  656 */             if (vertical) {
/*  657 */               pageEnd -= amargin.b;
/*      */             } else {
/*  659 */               pageEnd -= amargin.a;
/*      */             } 
/*  661 */             if (pageStart >= pageEnd) {
/*  662 */               floating = null;
/*      */               break;
/*      */             } 
/*  665 */             o floatingPos = floating.a.c();
/*  666 */             switch (clear) {
/*      */               case 0:
/*      */                 break;
/*      */               
/*      */               case 1:
/*  671 */                 if (floatingPos.e == 1) {
/*      */                   break;
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 2:
/*  677 */                 if (floatingPos.e == 2) {
/*      */                   break;
/*      */                 }
/*      */                 break;
/*      */               
/*      */               case 3:
/*      */                 break;
/*      */               
/*      */               default:
/*  686 */                 throw new IllegalStateException();
/*      */             } 
/*      */             
/*  689 */             switch (floatingPos.e) {
/*      */               case 1:
/*  691 */                 xMarginStart = 0.0D;
/*      */                 break;
/*      */               case 2:
/*  694 */                 if (e.a(floating.b - xMarginStart, lineSize) < 0) {
/*  695 */                   lineEnd = lineStop;
/*      */                   break;
/*      */                 } 
/*  698 */                 lineEnd = Math.min(lineEnd, floating.b);
/*      */                 break;
/*      */               default:
/*  701 */                 throw new IllegalStateException();
/*      */             } 
/*  703 */             floating = null;
/*      */           } 
/*  705 */           if (floating != null) {
/*  706 */             this.h = this.i = 0.0D;
/*  707 */             this.k = pageEnd;
/*      */           } 
/*      */         } 
/*  710 */         xMarginEnd = lineStop - lineEnd;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  715 */         if (align != -1) {
/*      */           double frameSize;
/*  717 */           if (vertical) {
/*  718 */             frameSize = frame.e();
/*  719 */             marginStart = (margin.a() == 3) ? 1.722773839210782E308D : amargin.a;
/*  720 */             marginEnd = (margin.c() == 3) ? 1.722773839210782E308D : amargin.c;
/*      */           } else {
/*  722 */             frameSize = frame.f();
/*  723 */             marginStart = (margin.d() == 3) ? 1.722773839210782E308D : amargin.d;
/*  724 */             marginEnd = (margin.b() == 3) ? 1.722773839210782E308D : amargin.b;
/*      */           } 
/*  726 */           lineSize -= frameSize;
/*      */ 
/*      */           
/*  729 */           double marginEnd = (cLineSize - lineSize - frameSize - xMarginStart - xMarginEnd) / 2.0D, marginStart = marginEnd;
/*  730 */           if (e.a(marginStart)) {
/*      */             
/*  732 */             marginStart = cLineSize - lineSize - frameSize - xMarginStart - xMarginEnd;
/*  733 */           } else if (e.a(marginEnd)) {
/*      */             
/*  735 */             marginEnd = cLineSize - lineSize - frameSize - xMarginStart - xMarginEnd;
/*      */           } else {
/*      */             double remainder;
/*  738 */             switch (align) {
/*      */               
/*      */               case 1:
/*  741 */                 marginEnd = 0.0D;
/*      */                 break;
/*      */               
/*      */               case 2:
/*  745 */                 marginStart += cLineSize - lineSize - frameSize - xMarginStart - xMarginEnd;
/*      */                 break;
/*      */               
/*      */               case 3:
/*  749 */                 remainder = cLineSize - lineSize - frameSize - xMarginStart - xMarginEnd;
/*  750 */                 remainder /= 2.0D;
/*  751 */                 marginStart += remainder;
/*  752 */                 marginEnd += remainder;
/*      */                 break;
/*      */               default:
/*  755 */                 throw new IllegalStateException();
/*      */             } 
/*      */           } 
/*  758 */           if (vertical) {
/*  759 */             amargin.a = marginStart + xMarginStart;
/*  760 */             amargin.c = marginEnd + xMarginEnd;
/*      */           } else {
/*  762 */             amargin.d = marginStart + xMarginStart;
/*  763 */             amargin.b = marginEnd + xMarginEnd;
/*      */           } 
/*      */         } 
/*  766 */         if (amargin.a >= 0.0D) {
/*  767 */           if (amargin.a > this.h) {
/*  768 */             this.k -= this.h;
/*  769 */             this.h = amargin.a;
/*      */           } else {
/*  771 */             this.k -= amargin.a;
/*      */           }
/*      */         
/*  774 */         } else if (amargin.a < this.i) {
/*  775 */           this.k -= this.i;
/*  776 */           this.i = amargin.a;
/*      */         } else {
/*  778 */           this.k -= amargin.a;
/*      */         } 
/*      */ 
/*      */         
/*  782 */         this.h = this.i = amargin.d;
/*      */         
/*  784 */         this.h = this.i = amargin.c;
/*      */         
/*  786 */         flow.a.a(flowBox, this.k - flow.c);
/*      */         
/*  788 */         if (vertical) {
/*      */           
/*  790 */           this.k += flowBox.p();
/*      */         } else {
/*      */           
/*  793 */           this.k += flowBox.q();
/*      */         } 
/*  795 */         flow.a.a(this.k - flow.c);
/*      */         return;
/*      */ 
/*      */       
/*      */       case 6:
/*  800 */         if (box.a() == 6) {
/*  801 */           f f = (f)box;
/*  802 */           e.a(this, f);
/*      */         } 
/*      */ 
/*      */         
/*  806 */         floatBox = (k)box;
/*  807 */         if (this.d != null && this.d.c() > 0.0D) {
/*  808 */           c(floatBox);
/*      */         } else {
/*  810 */           d(floatBox);
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/*  817 */         absoluteBox = (i)box;
/*  818 */         pos = absoluteBox.c();
/*      */         
/*  820 */         switch (pos.c) {
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 2:
/*  825 */             b1 = v();
/*  826 */             contextBox = b1.a;
/*  827 */             staticX = this.j - b1.b;
/*  828 */             d1 = this.k - b1.c;
/*  829 */             if (this.d != null) {
/*  830 */               if (!n && pos.b != 1) throw new AssertionError(box.b()); 
/*  831 */               d1 = d1 + this.d.a();
/*      */             } 
/*  833 */             contextBox.a(absoluteBox, staticX, d1);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/*  838 */             h().a(absoluteBox);
/*  839 */             contextBox = h().j();
/*      */             break;
/*      */           
/*      */           default:
/*  843 */             throw new IllegalStateException();
/*      */         } 
/*  845 */         if (box.a() == 6) {
/*  846 */           f f = (f)box;
/*  847 */           f.a(contextBox.h());
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/*  853 */     throw new IllegalStateException(); }
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
/*      */   protected void a(k box) {
/*      */     double lineWidth, pageWidth;
/*  872 */     byte progression = (j().c_()).D;
/*      */     
/*  874 */     if (e.a(progression)) {
/*      */       
/*  876 */       lineWidth = box.q();
/*  877 */       pageWidth = box.p();
/*      */     } else {
/*      */       
/*  880 */       lineWidth = box.p();
/*  881 */       pageWidth = box.q();
/*      */     } 
/*  883 */     double pageStart = this.k;
/*  884 */     if (this.d != null) {
/*  885 */       pageStart += this.d.a();
/*      */     }
/*  887 */     double lineStart = this.j;
/*  888 */     if (z() > 0) {
/*      */       
/*  890 */       c.a lastFloating = this.m.get(this.m.size() - 1);
/*  891 */       pageStart = Math.max(pageStart, lastFloating.c);
/*      */       
/*  893 */       o pos = box.c();
/*      */       while (true) {
/*  895 */         c.a startFloating = null, endFloating = null;
/*  896 */         double lineEnd = this.j + k().h();
/*  897 */         lineStart = this.j;
/*  898 */         for (int i = this.m.size() - 1; i >= 0; i--) {
/*  899 */           double tempStart, tempEnd; c.a floating = this.m.get(i);
/*  900 */           double pageEnd = floating.e;
/*  901 */           if (e.a(pageStart, pageEnd) >= 0) {
/*      */             break;
/*      */           }
/*  904 */           o floatingPos = floating.a.c();
/*  905 */           switch (pos.c) {
/*      */             case 0:
/*      */               break;
/*      */             case 1:
/*  909 */               if (floatingPos.e == 1) {
/*  910 */                 pageStart = pageEnd;
/*      */                 break;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 2:
/*  916 */               if (floatingPos.e == 2) {
/*  917 */                 pageStart = pageEnd;
/*      */                 break;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 3:
/*  923 */               pageStart = pageEnd;
/*      */               break;
/*      */             
/*      */             default:
/*  927 */               throw new IllegalStateException();
/*      */           } 
/*  929 */           switch (floatingPos.e) {
/*      */             case 1:
/*  931 */               tempStart = floating.d;
/*  932 */               if (e.a(tempStart, lineStart) >= 0) {
/*  933 */                 startFloating = floating;
/*  934 */                 lineStart = tempStart;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 2:
/*  939 */               tempEnd = floating.b;
/*  940 */               if (e.a(tempEnd, lineEnd) <= 0) {
/*  941 */                 endFloating = floating;
/*  942 */                 lineEnd = tempEnd;
/*      */               } 
/*      */               break;
/*      */             
/*      */             default:
/*  947 */               throw new IllegalStateException();
/*      */           } 
/*      */         } 
/*  950 */         double width = lineEnd - lineStart;
/*  951 */         if (e.a(width, lineWidth) >= 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  957 */         if (startFloating == null && endFloating == null) {
/*      */           break;
/*      */         }
/*  960 */         if (endFloating == null) {
/*  961 */           pageStart = startFloating.e; continue;
/*  962 */         }  if (startFloating == null) {
/*  963 */           pageStart = endFloating.e; continue;
/*      */         } 
/*  965 */         double lineStartPageEnd = startFloating.e;
/*  966 */         double lineEndPageEnd = endFloating.e;
/*  967 */         if (lineStartPageEnd > lineEndPageEnd) {
/*  968 */           pageStart = lineEndPageEnd; continue;
/*      */         } 
/*  970 */         pageStart = lineStartPageEnd;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  975 */     boolean transfer = a(box, pageStart);
/*      */     
/*  977 */     c.b flow = v();
/*  978 */     flow.a.a(box, lineStart - flow.b, pageStart - flow.c);
/*  979 */     if (!transfer) {
/*  980 */       c.a floating = new c.a(box, lineStart, pageStart, progression);
/*  981 */       a(floating);
/*      */     } 
/*      */     
/*  984 */     a(pageStart, pageWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void b(k box) {
/*      */     double lineWidth, pageWidth;
/*  994 */     byte progression = (j().c_()).D;
/*      */     
/*  996 */     if (e.a(progression)) {
/*      */       
/*  998 */       lineWidth = box.q();
/*  999 */       pageWidth = box.p();
/*      */     } else {
/*      */       
/* 1002 */       lineWidth = box.p();
/* 1003 */       pageWidth = box.q();
/*      */     } 
/* 1005 */     double pageStart = this.k;
/* 1006 */     if (this.d != null) {
/* 1007 */       pageStart += this.d.a();
/*      */     }
/* 1009 */     double lineEnd = this.j + k().h();
/* 1010 */     if (z() > 0) {
/*      */       
/* 1012 */       c.a lastFloating = this.m.get(this.m.size() - 1);
/* 1013 */       pageStart = Math.max(pageStart, lastFloating.c);
/*      */       
/* 1015 */       o pos = box.c();
/*      */       while (true) {
/* 1017 */         c.a startFloating = null, endFloating = null;
/* 1018 */         double lineStart = this.j;
/* 1019 */         lineEnd = this.j + k().h();
/* 1020 */         for (int i = this.m.size() - 1; i >= 0; i--) {
/* 1021 */           double tempStart, tempEnd; c.a floating = this.m.get(i);
/* 1022 */           double pageEnd = floating.e;
/* 1023 */           if (e.a(pageStart, pageEnd) >= 0) {
/*      */             break;
/*      */           }
/* 1026 */           o floatingPos = floating.a.c();
/* 1027 */           switch (pos.c) {
/*      */             case 0:
/*      */               break;
/*      */             case 1:
/* 1031 */               if (floatingPos.e == 1) {
/* 1032 */                 pageStart = pageEnd;
/*      */                 break;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 2:
/* 1038 */               if (floatingPos.e == 2) {
/* 1039 */                 pageStart = pageEnd;
/*      */                 break;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 3:
/* 1045 */               pageStart = pageEnd;
/*      */               break;
/*      */             
/*      */             default:
/* 1049 */               throw new IllegalStateException();
/*      */           } 
/* 1051 */           switch (floatingPos.e) {
/*      */             case 1:
/* 1053 */               tempStart = floating.d;
/* 1054 */               if (e.a(tempStart, lineStart) >= 0) {
/* 1055 */                 startFloating = floating;
/* 1056 */                 lineStart = tempStart;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 2:
/* 1061 */               tempEnd = floating.b;
/* 1062 */               if (e.a(tempEnd, lineEnd) <= 0) {
/* 1063 */                 endFloating = floating;
/* 1064 */                 lineEnd = tempEnd;
/*      */               } 
/*      */               break;
/*      */             
/*      */             default:
/* 1069 */               throw new IllegalStateException();
/*      */           } 
/*      */         } 
/* 1072 */         double width = lineEnd - lineStart;
/*      */         
/* 1074 */         if (e.a(width, lineWidth) >= 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/* 1079 */         if (startFloating == null && endFloating == null) {
/*      */           break;
/*      */         }
/* 1082 */         if (endFloating == null) {
/* 1083 */           pageStart = startFloating.e; continue;
/* 1084 */         }  if (startFloating == null) {
/* 1085 */           pageStart = endFloating.e; continue;
/*      */         } 
/* 1087 */         double leftBottom = startFloating.e;
/* 1088 */         double rightBottom = endFloating.e;
/* 1089 */         if (leftBottom > rightBottom) {
/* 1090 */           pageStart = rightBottom; continue;
/*      */         } 
/* 1092 */         pageStart = leftBottom;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1097 */     lineEnd -= lineWidth;
/*      */     
/* 1099 */     boolean transfer = a(box, pageStart);
/*      */     
/* 1101 */     c.b flow = v();
/* 1102 */     flow.a.a(box, lineEnd - flow.b, pageStart - flow.c);
/* 1103 */     if (!transfer) {
/* 1104 */       c.a floating = new c.a(box, lineEnd, pageStart, progression);
/* 1105 */       a(floating);
/*      */     } 
/*      */     
/* 1108 */     a(pageStart, pageWidth);
/*      */   }
/*      */   
/*      */   private void a(double pageStart, double pageWidth) {
/*      */     int i;
/* 1113 */     c.b contextFlow = u();
/* 1114 */     double pageAxis = pageStart + pageWidth;
/*      */     
/* 1116 */     if (this.c != null) {
/* 1117 */       i = this.c.size() - 1;
/* 1118 */       for (; i >= 0; i--) {
/* 1119 */         contextFlow = this.c.get(i);
/* 1120 */         i params = contextFlow.a.c_();
/* 1121 */         if (params.ab == 1) {
/* 1122 */           contextFlow.a.a(pageAxis - contextFlow.c);
/* 1123 */           if (params.ab == 1)
/*      */           {
/* 1125 */             if (e.a((contextFlow.a.c_()).D)) {
/* 1126 */               pageAxis = contextFlow.a.s() + contextFlow.c;
/*      */             } else {
/* 1128 */               pageAxis = contextFlow.a.t() + contextFlow.c;
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1134 */       i = -1;
/*      */     } 
/* 1136 */     if (i == -1) {
/* 1137 */       c rootBox = j();
/* 1138 */       rootBox.a(pageAxis);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void c(k box) {
/* 1148 */     if (this.l == null) {
/* 1149 */       this.l = new ArrayList<>();
/*      */     }
/* 1151 */     this.l.add(box);
/* 1152 */     if (this.d == null) {
/* 1153 */       w();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void w() {
/* 1161 */     if (this.l == null || this.l.isEmpty()) {
/*      */       return;
/*      */     }
/* 1164 */     for (Iterator<k> i = this.l.iterator(); i.hasNext(); ) {
/* 1165 */       k box = i.next();
/* 1166 */       i.remove();
/* 1167 */       d(box);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void d(k box) {
/* 1172 */     if (o.isLoggable(Level.FINE)) {
/* 1173 */       o.fine("Add float: " + (box.b()).al + "/" + ((v()).a.b()).al);
/*      */     }
/* 1175 */     o pos = box.c();
/* 1176 */     switch (pos.e) {
/*      */       case 1:
/* 1178 */         a(box);
/*      */         break;
/*      */       case 2:
/* 1181 */         b(box);
/*      */         break;
/*      */       default:
/* 1184 */         throw new IllegalStateException();
/*      */     } 
/* 1186 */     if (this.m != null)
/*      */     {
/*      */       
/* 1189 */       Collections.sort(this.m, q);
/*      */     }
/*      */   }
/*      */   
/*      */   private void a(c.a floating) {
/* 1194 */     if (this.m == null) {
/* 1195 */       this.m = new ArrayList<>();
/*      */     }
/* 1197 */     this.m.add(floating);
/* 1198 */     if (this.p != null && !this.p.isEmpty()) {
/*      */       
/* 1200 */       List<c.a> floatings = this.p.get(this.p.size() - 1);
/* 1201 */       floatings.add(floating);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void a(f tableBuilder) {
/* 1206 */     m autoTableBuilder = (m)tableBuilder;
/* 1207 */     autoTableBuilder.d();
/* 1208 */     autoTableBuilder.a(this);
/*      */   } public a a(jp.cssj.homare.b.a.a blockBox) {
/*      */     a builder;
/*      */     c containerBox;
/*      */     g staticBlockBox;
/*      */     jp.cssj.homare.b.a.b.a absoluteBox;
/* 1214 */     switch (blockBox.b_().a()) {
/*      */       case 4:
/*      */       case 9:
/* 1217 */         if (blockBox.l()) {
/* 1218 */           f flowBox = (f)blockBox;
/* 1219 */           c c1 = k();
/* 1220 */           flowBox.a((d)this, 0.0D, c1.h());
/* 1221 */           return new e((d)this, (c)blockBox);
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/*      */       case 6:
/* 1228 */         staticBlockBox = (g)blockBox;
/* 1229 */         containerBox = k();
/* 1230 */         if (e.a(containerBox, (c)blockBox)) {
/*      */           
/* 1232 */           staticBlockBox.a((d)this, 0.0D, 0.0D, false);
/* 1233 */           if (blockBox.l()) {
/*      */             
/* 1235 */             builder = new e((d)this, (c)blockBox);
/*      */           } else {
/* 1237 */             builder = new a((d)this, (c)blockBox);
/*      */           } 
/*      */         } else {
/*      */           
/* 1241 */           blockBox.a(containerBox);
/* 1242 */           builder = new l((d)this, (c)blockBox);
/*      */         } 
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
/* 1273 */         return builder;case 7: absoluteBox = (jp.cssj.homare.b.a.b.a)blockBox; if ((absoluteBox.c()).c != 1) { containerBox = h().j(); } else { containerBox = l(); }  if (e.a(containerBox, (c)blockBox)) { absoluteBox.a((m)containerBox, 0.0D, 0.0D); builder = new a((d)this, (c)blockBox); } else { absoluteBox.a(containerBox); builder = new l((d)this, (c)blockBox); }  return builder;
/*      */     } 
/*      */     throw new IllegalStateException();
/*      */   } public void x() {
/* 1277 */     if (!n && this.c != null && !this.c.isEmpty()) throw new AssertionError(); 
/* 1278 */     if (!n && this.d != null) throw new AssertionError();
/*      */     
/* 1280 */     c flowBox = this.b.a;
/* 1281 */     i params = flowBox.c_();
/* 1282 */     if (flowBox.i() > 1 && params.ac.g == 2) {
/*      */       
/* 1284 */       this.k = this.b.c;
/* 1285 */       flowBox.a(this);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void a(byte textState) {
/* 1290 */     this.g = (byte)(this.g | textState);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void y() {
/* 1296 */     if (!n && this.d != null) throw new AssertionError(); 
/* 1297 */     this.d = new k(this, this.g);
/* 1298 */     this.g = 1;
/*      */     
/* 1300 */     c.b flow = v();
/* 1301 */     double localPageAxis = this.k - flow.c;
/* 1302 */     flow.a.a((l)this.d.a, localPageAxis);
/*      */   }
/*      */   
/*      */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/* 1306 */     if (this.d == null)
/*      */     {
/* 1308 */       y();
/*      */     }
/* 1310 */     this.d.a(fontStyle, fontMetrics);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/* 1315 */     this.d.a(charOffset, ch, coff, clen, gid);
/*      */   }
/*      */   
/*      */   public void a() {
/* 1319 */     this.d.d();
/*      */   }
/*      */   
/*      */   public void a(g quad) {
/* 1323 */     if (quad instanceof b) {
/*      */       b.e inlineStartQuad; b.a inlineAbsoluteQuad; b.d inlineReplacedQuad;
/* 1325 */       b inlineQuad = (b)quad;
/* 1326 */       switch (inlineQuad.b()) {
/*      */         
/*      */         case 1:
/* 1329 */           inlineStartQuad = (b.e)inlineQuad;
/* 1330 */           inlineStartQuad.g.b(k());
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/*      */         case 4:
/*      */           break;
/*      */         
/*      */         case 5:
/* 1339 */           inlineAbsoluteQuad = (b.a)inlineQuad;
/* 1340 */           if (inlineAbsoluteQuad.g.a() == 6) {
/* 1341 */             e.a(this, (f)inlineAbsoluteQuad.g);
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/* 1347 */           inlineReplacedQuad = (b.d)inlineQuad;
/* 1348 */           e.a(this, inlineReplacedQuad.g);
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/* 1353 */           throw new IllegalStateException();
/*      */       } 
/*      */     } 
/* 1356 */     if (this.d == null)
/*      */     {
/* 1358 */       y();
/*      */     }
/*      */     
/* 1361 */     this.d.a(quad);
/*      */   }
/*      */   
/*      */   public void b() {
/* 1365 */     while (this.d.e());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void f() {
/* 1371 */     this.d.f();
/* 1372 */     this.k += this.d.b();
/* 1373 */     this.d = null;
/* 1374 */     c.b flow = v();
/* 1375 */     flow.a.a(this.k - flow.c);
/*      */   }
/*      */ 
/*      */   
/*      */   boolean a(k prevBox, double floatPageAxis) {
/* 1380 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */