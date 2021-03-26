/*      */ package jp.cssj.homare.b.b.a;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import jp.cssj.homare.b.a.a;
/*      */ import jp.cssj.homare.b.a.b.a;
/*      */ import jp.cssj.homare.b.a.b.b;
/*      */ import jp.cssj.homare.b.a.b.d;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.h;
/*      */ import jp.cssj.homare.b.a.b.i;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.a;
/*      */ import jp.cssj.homare.b.a.c.f;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.p;
/*      */ import jp.cssj.homare.b.a.f;
/*      */ import jp.cssj.homare.b.a.g;
/*      */ import jp.cssj.homare.b.a.i;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.a.k;
/*      */ import jp.cssj.homare.b.a.m;
/*      */ import jp.cssj.homare.b.b.a;
/*      */ import jp.cssj.homare.b.b.b;
/*      */ import jp.cssj.homare.b.b.d;
/*      */ import jp.cssj.homare.b.b.f;
/*      */ import jp.cssj.homare.b.b.g;
/*      */ import jp.cssj.homare.b.f.b;
/*      */ import jp.cssj.homare.b.f.e;
/*      */ import jp.cssj.homare.impl.a.a.c;
/*      */ import jp.cssj.sakae.c.a.f;
/*      */ import jp.cssj.sakae.c.a.h;
/*      */ import jp.cssj.sakae.c.d.b.a.b;
/*      */ import jp.cssj.sakae.c.d.c;
/*      */ import jp.cssj.sakae.c.d.d;
/*      */ import jp.cssj.sakae.c.d.e;
/*      */ import jp.cssj.sakae.c.d.g;
/*      */ import jp.cssj.sakae.c.d.h;
/*      */ import jp.cssj.sakae.c.d.i;
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
/*      */ public class l
/*      */   implements a, d, g
/*      */ {
/*   73 */   private double l = 0.0D, m = 0.0D, n = 0.0D;
/*      */   
/*   75 */   private double o = 0.0D; private double p = 0.0D;
/*      */   
/*   77 */   private int q = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   84 */   private double s = 0.0D;
/*      */   
/*   86 */   private double t = 0.0D;
/*      */   
/*   88 */   private double u = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   97 */   private double x = 0.0D; private double y = 0.0D;
/*      */   
/*   99 */   private b z = null;
/*      */   
/*  101 */   private final List<c> A = new ArrayList<>();
/*      */   
/*  103 */   private final List<j> B = new ArrayList<>();
/*      */   
/*  105 */   private final List<Object> C = new ArrayList();
/*      */   
/*  107 */   private final b D = new b();
/*      */   
/*  109 */   private final List<g> E = new ArrayList<>(); private static final boolean c = false; private static final byte d = 1; private static final byte e = 2; private static final byte f = 3; private static final byte g = 4; private static final byte h = 5; private static final byte i = 6; private static final byte j = 7; private static final byte k = 9; protected final d a; private i r; private double v; private boolean w;
/*      */   
/*      */   public l(d layoutStack, c containerBox) {
/*  112 */     this.a = layoutStack;
/*  113 */     this.A.add(containerBox);
/*  114 */     this.v = containerBox.r();
/*  115 */     this.w = true;
/*  116 */     this.u = e.a((containerBox.c_()).H, 
/*  117 */         k().h());
/*      */   }
/*      */   
/*      */   public c r() {
/*  121 */     c box = l();
/*  122 */     if ((box.c_()).X.a() != 3) {
/*  123 */       return box;
/*      */     }
/*  125 */     switch (box.b_().a()) {
/*      */       case 1:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 9:
/*      */       case 13:
/*  132 */         return this.a.p();
/*      */       
/*      */       case 7:
/*  135 */         return this.a.r();
/*      */     } 
/*  137 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   public c s() {
/*  142 */     c box = l();
/*  143 */     if ((box.c_()).X.b() != 3) {
/*  144 */       return box;
/*      */     }
/*  146 */     switch (box.b_().a()) {
/*      */       case 1:
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 9:
/*      */       case 13:
/*  153 */         return this.a.q();
/*      */       
/*      */       case 7:
/*  156 */         return this.a.s();
/*      */     } 
/*  158 */     throw new IllegalStateException(String.valueOf(box.b_().a()));
/*      */   }
/*      */ 
/*      */   
/*      */   public double n() {
/*  163 */     double frameWidth = 0.0D;
/*  164 */     for (int j = this.A.size() - 1; j >= 1; j--) {
/*  165 */       c flowBox = this.A.get(j);
/*  166 */       frameWidth += flowBox.m().f();
/*  167 */       if ((flowBox.c_()).X.a() != 3) {
/*  168 */         return flowBox.p() - frameWidth;
/*      */       }
/*      */     } 
/*  171 */     c box = r();
/*  172 */     if (box == null) {
/*  173 */       return 0.0D;
/*      */     }
/*  175 */     return box.s() - frameWidth;
/*      */   }
/*      */   
/*      */   public c p() {
/*  179 */     for (int j = this.A.size() - 1; j >= 1; j--) {
/*  180 */       c flowBox = this.A.get(j);
/*  181 */       if ((flowBox.c_()).X.a() != 3) {
/*  182 */         return flowBox;
/*      */       }
/*      */     } 
/*  185 */     return r();
/*      */   }
/*      */   
/*      */   public double o() {
/*  189 */     double flowHeight = 0.0D;
/*  190 */     for (int j = this.A.size() - 1; j >= 1; j--) {
/*  191 */       c flowBox = this.A.get(j);
/*  192 */       flowHeight += flowBox.m().e();
/*  193 */       if ((flowBox.c_()).X.b() != 3) {
/*  194 */         return flowBox.q() - flowHeight;
/*      */       }
/*      */     } 
/*  197 */     c box = s();
/*  198 */     if (box == null) {
/*  199 */       return 0.0D;
/*      */     }
/*  201 */     return box.t() - flowHeight;
/*      */   }
/*      */   
/*      */   public c q() {
/*  205 */     for (int j = this.A.size() - 1; j >= 1; j--) {
/*  206 */       c flowBox = this.A.get(j);
/*  207 */       if ((flowBox.c_()).X.b() != 3) {
/*  208 */         return flowBox;
/*      */       }
/*      */     } 
/*  211 */     return s();
/*      */   }
/*      */   
/*      */   public h h() {
/*  215 */     return this.a.h();
/*      */   }
/*      */   
/*      */   public a i() {
/*  219 */     return (a)this.a;
/*      */   }
/*      */   
/*      */   public double t() {
/*  223 */     return this.m;
/*      */   }
/*      */   
/*      */   public double g() {
/*  227 */     return this.l;
/*      */   }
/*      */   
/*      */   public double u() {
/*  231 */     return this.n;
/*      */   }
/*      */   
/*      */   public boolean c() {
/*  235 */     return false;
/*      */   }
/*      */   
/*      */   public boolean d() {
/*  239 */     return true;
/*      */   }
/*      */   
/*      */   public c l() {
/*  243 */     if (this.A != null) {
/*  244 */       for (int j = this.A.size() - 1; j >= 1; j--) {
/*  245 */         c c = this.A.get(j);
/*  246 */         if (c.g()) {
/*  247 */           return c;
/*      */         }
/*      */       } 
/*      */     }
/*  251 */     c box = this.A.get(0);
/*  252 */     if (this.a == null) {
/*  253 */       return box;
/*      */     }
/*  255 */     if (!box.g()) {
/*  256 */       return this.a.l();
/*      */     }
/*  258 */     return box;
/*      */   }
/*      */   
/*      */   public c m() {
/*  262 */     if (this.A != null) {
/*  263 */       for (int j = this.A.size() - 1; j >= 0; j--) {
/*  264 */         c box = this.A.get(j);
/*  265 */         if (box.i() > 1) {
/*  266 */           return box;
/*      */         }
/*      */       } 
/*      */     }
/*  270 */     return null;
/*      */   }
/*      */   
/*      */   public c j() {
/*  274 */     return this.A.get(0);
/*      */   }
/*      */   
/*      */   public c k() {
/*  278 */     return this.A.get(this.A.size() - 1);
/*      */   }
/*      */   
/*      */   public void a(f flowBox) {
/*      */     double lineSize;
/*  283 */     if (!b && !this.B.isEmpty()) throw new AssertionError(); 
/*  284 */     c containerBox = k();
/*  285 */     i params = containerBox.c_();
/*  286 */     p pos = (p)flowBox.b_();
/*  287 */     a(pos.c);
/*      */     
/*  289 */     flowBox.a(containerBox);
/*      */     
/*  291 */     if (e.a(params.D)) {
/*      */       
/*  293 */       lineSize = this.x + flowBox.q();
/*  294 */       this.x += flowBox.m().e();
/*  295 */       this.y += flowBox.m().f();
/*      */     } else {
/*      */       
/*  298 */       lineSize = this.x + flowBox.p();
/*  299 */       this.x += flowBox.m().f();
/*  300 */       this.y += flowBox.m().e();
/*      */     } 
/*  302 */     if (!b && e.a(this.x)) throw new AssertionError(); 
/*  303 */     if (flowBox.i() > 0) {
/*  304 */       this.x += (flowBox.c_()).ac.e * (flowBox.i() - 1);
/*      */     }
/*  306 */     this.x *= this.q;
/*  307 */     lineSize *= this.q;
/*  308 */     if (this.x > this.l) {
/*  309 */       this.l = this.x;
/*      */     }
/*  311 */     if (this.y > this.n) {
/*  312 */       this.n = this.y;
/*      */     }
/*  314 */     if (lineSize > this.m) {
/*  315 */       this.m = lineSize;
/*      */     }
/*  317 */     this.v = flowBox.r();
/*  318 */     this.w = true;
/*      */     
/*  320 */     this.A.add(flowBox);
/*  321 */     this.D.a((byte)3);
/*  322 */     this.C.add(flowBox);
/*  323 */     this.q *= flowBox.i();
/*  324 */     this.u = e.a((flowBox.c_()).H, 
/*  325 */         k().h());
/*      */   }
/*      */ 
/*      */   
/*      */   public void e() {
/*  330 */     if (!b && !this.B.isEmpty()) throw new AssertionError(); 
/*  331 */     a flowBox = (a)this.A.remove(this.A.size() - 1);
/*  332 */     c containerBox = k();
/*  333 */     i params = containerBox.c_();
/*  334 */     i flowParams = flowBox.c_();
/*  335 */     this.q /= flowBox.i();
/*  336 */     this.x /= this.q;
/*  337 */     if (flowBox.i() > 0) {
/*  338 */       this.x -= (flowBox.c_()).ac.e * (flowBox.i() - 1);
/*      */     }
/*      */     
/*  341 */     switch (params.D) {
/*      */       
/*      */       case 1:
/*  344 */         this.x -= flowBox.m().f();
/*  345 */         this.y -= flowBox.m().e();
/*  346 */         if (flowParams.X.a() == 1)
/*      */         {
/*  348 */           this.m = this.l = flowBox.p();
/*      */         }
/*      */         break;
/*      */       
/*      */       case 2:
/*      */       case 3:
/*  354 */         this.x -= flowBox.m().e();
/*  355 */         this.y -= flowBox.m().f();
/*  356 */         if (flowParams.X.b() == 1)
/*      */         {
/*  358 */           this.m = this.l = flowBox.q();
/*      */         }
/*      */         break;
/*      */       default:
/*  362 */         throw new IllegalStateException();
/*      */     } 
/*      */     
/*  365 */     if (!b && e.a(this.x)) throw new AssertionError(); 
/*  366 */     this.D.a((byte)4);
/*  367 */     this.C.add(flowBox);
/*      */     
/*  369 */     this.v = 0.0D;
/*  370 */     this.w = false;
/*  371 */     this.u = e.a((flowBox.c_()).H, 
/*  372 */         k().h()); } public void a(j box) { c containerBox, contextBox; jp.cssj.homare.b.a.l flowBox; k floatingBox; p pos;
/*      */     double minLineAxis, d1, minPageAxis, maxLineAxis, d2, d3;
/*      */     i params, i1;
/*      */     double xmaxLineAxis;
/*  376 */     f replacedBox = (f)box;
/*  377 */     switch (replacedBox.b_().a()) {
/*      */       
/*      */       case 4:
/*  380 */         containerBox = k();
/*  381 */         flowBox = (jp.cssj.homare.b.a.l)replacedBox;
/*  382 */         pos = (p)flowBox.b_();
/*  383 */         a(pos.c);
/*  384 */         e.a(this, replacedBox);
/*      */         
/*  386 */         maxLineAxis = 0.0D;
/*  387 */         i1 = containerBox.c_();
/*  388 */         if (e.a(i1.D)) {
/*      */           
/*  390 */           d1 = replacedBox.q();
/*  391 */           d3 = replacedBox.p();
/*  392 */           if ((replacedBox.d_()).b.b() == 1) {
/*  393 */             maxLineAxis = (replacedBox.d_()).b.d();
/*      */           }
/*      */         } else {
/*      */           
/*  397 */           d1 = replacedBox.p();
/*  398 */           d3 = replacedBox.q();
/*  399 */           if ((replacedBox.d_()).b.a() == 1) {
/*  400 */             maxLineAxis = (replacedBox.d_()).b.c();
/*      */           }
/*      */         } 
/*  403 */         d3 += this.y;
/*  404 */         d1 *= this.q;
/*  405 */         d1 += this.x;
/*      */         
/*  407 */         maxLineAxis *= this.q;
/*  408 */         maxLineAxis += this.x;
/*      */         
/*  410 */         if (!b && e.a(d1)) throw new AssertionError(); 
/*  411 */         if (d1 > this.l) {
/*  412 */           this.l = d1;
/*      */         }
/*  414 */         if (d3 > this.n) {
/*  415 */           this.n = d3;
/*      */         }
/*  417 */         if (maxLineAxis > this.m) {
/*  418 */           this.m = maxLineAxis;
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/*  424 */         containerBox = k();
/*  425 */         floatingBox = (k)replacedBox;
/*  426 */         a((floatingBox.c()).c);
/*  427 */         e.a(this, replacedBox);
/*      */         
/*  429 */         d2 = 0.0D;
/*  430 */         params = containerBox.c_();
/*  431 */         if (e.a(params.D)) {
/*      */           
/*  433 */           minLineAxis = replacedBox.q();
/*  434 */           minPageAxis = replacedBox.p();
/*  435 */           if ((replacedBox.d_()).b.b() == 1) {
/*  436 */             d2 = (replacedBox.d_()).b.d();
/*      */           }
/*      */         } else {
/*      */           
/*  440 */           minLineAxis = replacedBox.p();
/*  441 */           minPageAxis = replacedBox.q();
/*  442 */           if ((replacedBox.d_()).b.a() == 1) {
/*  443 */             d2 = (replacedBox.d_()).b.c();
/*      */           }
/*      */         } 
/*  446 */         if (!b && e.a(minLineAxis)) throw new AssertionError(); 
/*  447 */         if (minLineAxis > this.l) {
/*  448 */           this.l = minLineAxis;
/*      */         }
/*  450 */         if (minPageAxis > this.n) {
/*  451 */           this.n = minPageAxis;
/*      */         }
/*      */         
/*  454 */         switch ((floatingBox.c()).e) {
/*      */           case 1:
/*  456 */             this.o += minLineAxis;
/*      */             break;
/*      */           
/*      */           case 2:
/*  460 */             this.p += minLineAxis;
/*      */             break;
/*      */           
/*      */           default:
/*  464 */             throw new IllegalStateException();
/*      */         } 
/*  466 */         xmaxLineAxis = this.o + this.p;
/*  467 */         if (xmaxLineAxis > d2) {
/*  468 */           d2 = xmaxLineAxis;
/*      */         }
/*  470 */         d2 *= this.q;
/*  471 */         d2 += this.x;
/*  472 */         if (d2 > this.m) {
/*  473 */           this.m = d2;
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/*  481 */         switch ((((b)replacedBox).c()).c) {
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 2:
/*  486 */             contextBox = k();
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/*  491 */             contextBox = h().j();
/*      */             break;
/*      */           
/*      */           default:
/*  495 */             throw new IllegalStateException();
/*      */         } 
/*  497 */         replacedBox.a(contextBox.h());
/*      */         break;
/*      */       
/*      */       default:
/*  501 */         throw new IllegalStateException();
/*      */     } 
/*  503 */     this.D.a((byte)5);
/*  504 */     this.C.add(replacedBox); }
/*      */ 
/*      */   
/*      */   public void a(f tableBuilder) {
/*  508 */     m autoTableBuilder = (m)tableBuilder;
/*  509 */     autoTableBuilder.d();
/*  510 */     double maxLineAxis = autoTableBuilder.g();
/*  511 */     double minLineAxis = autoTableBuilder.t();
/*  512 */     maxLineAxis *= this.q;
/*  513 */     minLineAxis *= this.q;
/*  514 */     this.l = Math.max(this.l, maxLineAxis);
/*  515 */     this.m = Math.max(this.m, minLineAxis);
/*  516 */     this.D.a((byte)9);
/*  517 */     this.C.add(tableBuilder);
/*  518 */     switch (autoTableBuilder.a().h().b_().a()) {
/*      */       case 5:
/*  520 */         this.E.add(autoTableBuilder);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public a a(a stfBox) {
/*  529 */     l builder = new l(this, (c)stfBox);
/*  530 */     c box = k();
/*  531 */     stfBox.a(box);
/*  532 */     switch (stfBox.b_().a()) {
/*      */ 
/*      */       
/*      */       case 4:
/*      */       case 6:
/*  537 */         this.D.a((byte)6);
/*  538 */         this.C.add(builder);
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
/*  555 */         return builder;case 7: this.D.a((byte)7); this.C.add(builder); return builder;case 5: this.E.add(builder); return builder;
/*      */     } 
/*      */     throw new IllegalStateException();
/*      */   } public void a(l childBuilder) {
/*  559 */     d floatingBox = (d)childBuilder.j();
/*  560 */     a((floatingBox.c()).c);
/*      */     
/*  562 */     i params = floatingBox.c_();
/*  563 */     i flowParams = k().c_();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  568 */     double maxLineAxis = floatingBox.q(), minLineAxis = maxLineAxis;
/*      */     
/*  570 */     minLineAxis = childBuilder.g() + floatingBox.m().f();
/*  571 */     maxLineAxis = childBuilder.t() + floatingBox.m().e();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  576 */     minLineAxis = maxLineAxis = floatingBox.p();
/*      */     
/*  578 */     minLineAxis = childBuilder.g() + floatingBox.m().f();
/*  579 */     maxLineAxis = childBuilder.t() + floatingBox.m().f();
/*      */ 
/*      */     
/*  582 */     if (!b && e.a(maxLineAxis)) throw new AssertionError();
/*      */     
/*  584 */     if (minLineAxis > this.l) {
/*  585 */       this.l = minLineAxis;
/*      */     }
/*      */     
/*  588 */     switch ((floatingBox.c()).e) {
/*      */       case 1:
/*  590 */         this.o += maxLineAxis;
/*      */         break;
/*      */       case 2:
/*  593 */         this.p += maxLineAxis;
/*      */         break;
/*      */       default:
/*  596 */         throw new IllegalStateException();
/*      */     } 
/*  598 */     maxLineAxis = this.o + this.p;
/*  599 */     maxLineAxis *= this.q;
/*  600 */     maxLineAxis += this.x;
/*  601 */     if (maxLineAxis > this.m) {
/*  602 */       this.m = maxLineAxis;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(a builder) {
/*  611 */     d textUnitizer = null;
/*  612 */     Iterator<Object> k = this.C.iterator();
/*      */     
/*  614 */     for (int j = 0; j < this.D.b(); j++) {
/*  615 */       c c; c e; f flow; h text; j replacedBox; l stfBuilder; m tableBuilder; g quad; g blockBox; a absoluteBox; a boundBuilder; c containerBox; a a1; a pos; b b1; switch (this.D.a(j)) {
/*      */         case 1:
/*  617 */           if (textUnitizer == null) {
/*  618 */             c = new c((builder.k().c_()).G);
/*  619 */             c.a(new c(builder));
/*      */           } 
/*  621 */           e = (c)k.next();
/*      */ 
/*      */ 
/*      */           
/*  625 */           switch (e.f_()) {
/*      */             case 1:
/*  627 */               text = (h)e;
/*  628 */               if (!b && text.l() <= 0) throw new AssertionError(); 
/*  629 */               text.a((e)c);
/*      */               break;
/*      */             case 2:
/*  632 */               quad = (g)e;
/*  633 */               if (quad instanceof b.b) {
/*  634 */                 g twoPass = this.E.remove(0);
/*  635 */                 if (twoPass instanceof l) {
/*  636 */                   b.b inlineBlockQuad = (b.b)quad;
/*  637 */                   h inlineBlockBox = inlineBlockQuad.g;
/*  638 */                   l l1 = (l)twoPass;
/*  639 */                   inlineBlockBox.a((d)builder, l1.g(), l1
/*  640 */                       .t(), false);
/*  641 */                   a lnlineBlockBuilder = new a(this, (c)inlineBlockBox);
/*  642 */                   l1.a(lnlineBlockBuilder);
/*  643 */                   lnlineBlockBuilder.x();
/*      */                 } 
/*      */               } 
/*  646 */               c.a(quad);
/*      */               break;
/*      */           } 
/*  649 */           throw new IllegalStateException();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  658 */           c.b();
/*  659 */           c = null;
/*  660 */           builder.f();
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 3:
/*  667 */           flow = (f)k.next();
/*  668 */           if (e.a((flow.c_()).D) == 
/*  669 */             e.a((builder.j().c_()).D)) {
/*  670 */             builder.a(flow);
/*      */             break;
/*      */           } 
/*  673 */           builder = (a)builder.a((a)flow);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 4:
/*  682 */           flow = (f)k.next();
/*  683 */           if (flow == builder.j()) {
/*  684 */             builder = (a)builder.i();
/*  685 */             builder.a((j)flow); break;
/*      */           } 
/*  687 */           builder.e();
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 5:
/*  695 */           replacedBox = (j)k.next();
/*  696 */           switch (replacedBox.b_().a()) {
/*      */             case 4:
/*      */             case 6:
/*  699 */               if (c != null)
/*  700 */                 c.b(); 
/*      */               break;
/*      */           } 
/*  703 */           builder.a(replacedBox);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 6:
/*  711 */           if (c != null) {
/*  712 */             c.b();
/*      */           }
/*  714 */           stfBuilder = (l)k.next();
/*  715 */           blockBox = (g)stfBuilder.j();
/*  716 */           blockBox.a((d)builder, stfBuilder.g(), stfBuilder.t(), false);
/*  717 */           boundBuilder = new a(this, (c)blockBox);
/*  718 */           stfBuilder.a(boundBuilder);
/*  719 */           boundBuilder.x();
/*  720 */           builder.a((j)blockBox);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 7:
/*  729 */           stfBuilder = (l)k.next();
/*  730 */           absoluteBox = (a)stfBuilder.j();
/*      */           
/*  732 */           if ((absoluteBox.c()).c != 1) {
/*  733 */             containerBox = builder.h().j();
/*      */           } else {
/*  735 */             containerBox = builder.l();
/*      */           } 
/*  737 */           absoluteBox.a((m)containerBox, stfBuilder.g(), stfBuilder.t());
/*  738 */           a1 = new a(this, (c)absoluteBox);
/*  739 */           stfBuilder.a(a1);
/*  740 */           a1.x();
/*  741 */           pos = absoluteBox.c();
/*  742 */           switch (pos.b) {
/*      */             case 1:
/*  744 */               builder.a((j)absoluteBox);
/*      */               break;
/*      */             case 2:
/*  747 */               b1 = b.a((i)absoluteBox);
/*  748 */               if (c == null) {
/*  749 */                 c = new c((builder.k().c_()).G);
/*  750 */                 c.a(new c(builder));
/*      */               } 
/*  752 */               c.a((g)b1);
/*      */               break;
/*      */           } 
/*  755 */           throw new IllegalStateException();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 9:
/*  764 */           tableBuilder = (m)k.next();
/*  765 */           switch (tableBuilder.a().h().b_().a()) {
/*      */             case 4:
/*      */             case 6:
/*  768 */               if (c != null)
/*  769 */                 c.b(); 
/*      */               break;
/*      */           } 
/*  772 */           tableBuilder.a(builder);
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  777 */           throw new IllegalStateException();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double w() {
/*  786 */     if (this.B.isEmpty()) {
/*  787 */       return (k().c_()).i;
/*      */     }
/*  789 */     i box = (i)this.B.get(this.B.size() - 1);
/*  790 */     return (box.c()).b;
/*      */   }
/*      */   
/*      */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/*  794 */     this.r = new i(charOffset, fontStyle, fontMetrics);
/*      */   }
/*      */   
/*      */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/*  798 */     double advance = this.r.a(ch, coff, clen, gid);
/*  799 */     advance += this.u;
/*  800 */     this.t += advance;
/*  801 */     this.s += advance;
/*  802 */     double minPageAxis = w() + this.y;
/*  803 */     if (minPageAxis > this.n) {
/*  804 */       this.n = minPageAxis;
/*      */     }
/*      */   }
/*      */   
/*      */   public void a() {
/*  809 */     this.r.n();
/*  810 */     this.D.a((byte)1);
/*  811 */     this.C.add(this.r);
/*  812 */     this.r = null;
/*      */   }
/*      */   public void a(g quad) {
/*      */     double minAdvance, maxAdvance;
/*  816 */     if (quad instanceof b) {
/*  817 */       this.z = (b)quad;
/*      */     }
/*  819 */     this.D.a((byte)1);
/*  820 */     this.C.add(quad);
/*      */ 
/*      */     
/*  823 */     if (quad instanceof b) {
/*  824 */       b inlineQuad = (b)quad;
/*  825 */       i cParams = k().c_();
/*  826 */       if (quad instanceof b.d) {
/*      */         
/*  828 */         f box = (f)inlineQuad.a();
/*  829 */         maxAdvance = quad.c();
/*  830 */         minAdvance = 0.0D;
/*  831 */         if (e.a(cParams.D)) {
/*      */           
/*  833 */           if ((box.d_()).b.b() != 2 && 
/*  834 */             (box.d_()).d.b() != 2) {
/*  835 */             minAdvance = maxAdvance;
/*      */           }
/*  837 */           if ((box.d_()).b.b() == 1 && 
/*  838 */             (box.d_()).b.d() > maxAdvance) {
/*  839 */             maxAdvance = (box.d_()).b.d();
/*      */           }
/*      */           
/*  842 */           pageSize = box.p();
/*      */         } else {
/*      */           
/*  845 */           if ((box.d_()).b.a() != 2 && 
/*  846 */             (box.d_()).d.a() != 2) {
/*  847 */             minAdvance = maxAdvance;
/*      */           }
/*  849 */           if ((box.d_()).b.a() == 1 && 
/*  850 */             (box.d_()).b.c() > maxAdvance) {
/*  851 */             maxAdvance = (box.d_()).b.c();
/*      */           }
/*      */           
/*  854 */           pageSize = box.q();
/*      */         } 
/*  856 */       } else if (quad instanceof b.b) {
/*      */         double lineFrame, pageFrame;
/*  858 */         c box = (c)inlineQuad.a();
/*      */         
/*  860 */         if (e.a(cParams.D)) {
/*      */           
/*  862 */           lineFrame = box.m().e();
/*  863 */           pageFrame = box.m().f();
/*      */         } else {
/*      */           
/*  866 */           lineFrame = box.m().f();
/*  867 */           pageFrame = box.m().e();
/*      */         } 
/*      */         
/*  870 */         i params = (i)box.b();
/*  871 */         g stfBuilder = this.E.get(this.E.size() - 1);
/*  872 */         if (e.a(cParams.D) == e.a(params.D)) {
/*  873 */           minAdvance = stfBuilder.g() + lineFrame;
/*  874 */           maxAdvance = stfBuilder.t() + lineFrame;
/*  875 */           pageSize = stfBuilder.u() + pageFrame;
/*      */         } else {
/*      */           
/*  878 */           minAdvance = maxAdvance = stfBuilder.u() + pageFrame;
/*  879 */           pageSize = stfBuilder.g() + lineFrame;
/*      */         } 
/*  881 */         if (e.a(params.D)) {
/*      */           
/*  883 */           minAdvance = Math.max(minAdvance, box.q());
/*  884 */           maxAdvance = Math.max(maxAdvance, box.q());
/*  885 */           pageSize = Math.max(pageSize, box.p());
/*      */         } else {
/*      */           
/*  888 */           minAdvance = Math.max(minAdvance, box.p());
/*  889 */           maxAdvance = Math.max(maxAdvance, box.p());
/*  890 */           pageSize = Math.max(pageSize, box.q());
/*      */         } 
/*      */       } else {
/*  893 */         if (inlineQuad instanceof b.e) {
/*  894 */           this.B.add(inlineQuad.a());
/*  895 */           b.e inlineStartQuad = (b.e)inlineQuad;
/*  896 */           this.u = e.a((inlineStartQuad.g.g()).H, 
/*  897 */               k().h());
/*  898 */         } else if (inlineQuad instanceof b.c) {
/*  899 */           f params; this.B.remove(this.B.size() - 1);
/*      */           
/*  901 */           if (this.B.isEmpty()) {
/*  902 */             i i1 = k().c_();
/*      */           } else {
/*  904 */             i box = (i)this.B.get(this.B.size() - 1);
/*  905 */             params = box.g();
/*      */           } 
/*  907 */           this.u = e.a(params.H, 
/*  908 */               k().h());
/*      */         } 
/*  910 */         minAdvance = maxAdvance = quad.c();
/*  911 */         if (e.a(cParams.D)) {
/*  912 */           pageSize = inlineQuad.a().p();
/*      */         } else {
/*  914 */           pageSize = inlineQuad.a().q();
/*      */         } 
/*      */       } 
/*      */     } else {
/*  918 */       minAdvance = maxAdvance = quad.c();
/*  919 */       pageSize = 0.0D;
/*      */     } 
/*  921 */     double pageSize = Math.max(pageSize, w());
/*  922 */     pageSize += this.y;
/*  923 */     if (pageSize > this.n) {
/*  924 */       this.n = pageSize;
/*      */     }
/*  926 */     this.t += minAdvance;
/*  927 */     this.s += maxAdvance;
/*      */   }
/*      */   
/*      */   public void b() {
/*  931 */     double minLineSize = this.t;
/*  932 */     if (this.w) {
/*  933 */       minLineSize += this.v;
/*  934 */       this.w = false;
/*      */     } 
/*  936 */     minLineSize *= this.q;
/*  937 */     minLineSize += this.x;
/*  938 */     if (minLineSize > this.l) {
/*  939 */       this.l = minLineSize;
/*  940 */       if (minLineSize > this.m) {
/*  941 */         this.m = minLineSize;
/*      */       }
/*      */     } 
/*  944 */     this.t = 0.0D;
/*  945 */     if (this.z != null) {
/*  946 */       if (!b && e.a(this.s)) throw new AssertionError(); 
/*  947 */       if (!b && e.a(this.x)) throw new AssertionError(); 
/*  948 */       double maxLineSize = this.v + this.o + this.p + this.s;
/*  949 */       maxLineSize *= this.q;
/*  950 */       maxLineSize += this.x;
/*  951 */       if (maxLineSize > this.m) {
/*  952 */         this.m = maxLineSize;
/*      */       }
/*  954 */       this.s = 0.0D;
/*  955 */       this.z = null;
/*  956 */       this.v = 0.0D;
/*  957 */       a((byte)3);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void f() {
/*  962 */     this.D.a((byte)2);
/*  963 */     if (!b && e.a(this.s)) throw new AssertionError(); 
/*  964 */     if (!b && e.a(this.x)) throw new AssertionError(); 
/*  965 */     double minLineSize = this.t;
/*  966 */     if (this.w) {
/*  967 */       minLineSize += this.v;
/*  968 */       this.w = false;
/*      */     } 
/*  970 */     minLineSize *= this.q;
/*  971 */     minLineSize += this.x;
/*  972 */     if (minLineSize > this.l) {
/*  973 */       this.l = minLineSize;
/*      */     }
/*  975 */     double maxLineSize = this.v + this.o + this.p + this.s;
/*  976 */     maxLineSize *= this.q;
/*  977 */     maxLineSize += this.x;
/*  978 */     if (maxLineSize > this.m) {
/*  979 */       this.m = maxLineSize;
/*      */     }
/*  981 */     this.t = 0.0D;
/*  982 */     this.s = 0.0D;
/*      */   }
/*      */   
/*      */   private void a(byte clear) {
/*  986 */     switch (clear) {
/*      */       case 3:
/*  988 */         this.o = 0.0D;
/*  989 */         this.p = 0.0D;
/*      */       
/*      */       case 1:
/*  992 */         this.o = 0.0D;
/*      */       
/*      */       case 2:
/*  995 */         this.p = 0.0D;
/*      */       
/*      */       case 0:
/*      */         return;
/*      */     } 
/* 1000 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean v() {
/* 1005 */     return this.D.d();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */