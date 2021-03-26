/*      */ package jp.cssj.homare.b.b.a;
/*      */ 
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import jp.cssj.homare.b.a.a;
/*      */ import jp.cssj.homare.b.a.a.d;
/*      */ import jp.cssj.homare.b.a.a.g;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.r;
/*      */ import jp.cssj.homare.b.a.b.v;
/*      */ import jp.cssj.homare.b.a.b.w;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.I;
/*      */ import jp.cssj.homare.b.a.c.J;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.o;
/*      */ import jp.cssj.homare.b.a.c.p;
/*      */ import jp.cssj.homare.b.a.d;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.a.k;
/*      */ import jp.cssj.homare.b.b.c;
/*      */ import jp.cssj.homare.b.b.d;
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
/*      */ public abstract class b
/*      */   extends a
/*      */ {
/*      */   static {
/*   38 */     C = Logger.getLogger(b.class.getName());
/*      */   }
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
/*   51 */   protected int t = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   56 */   protected int u = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   61 */   protected byte v = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean w = true;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean x = true;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean y = false;
/*      */ 
/*      */ 
/*      */   
/*   78 */   protected byte z = 0; private static final Logger C; public static final byte o = 0; public static final byte p = 1; public static final byte q = 2; protected byte r;
/*      */   protected byte s;
/*      */   protected r A;
/*      */   
/*      */   public b(d layoutStack, c contextBox, byte mode) {
/*   83 */     super(layoutStack, contextBox);
/*   84 */     this.r = mode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private d.c a(r tableBox) {
/*      */     w w;
/*   95 */     if (tableBox.m() <= 0) {
/*   96 */       return null;
/*      */     }
/*   98 */     double pageLimit = z();
/*   99 */     double last = this.k;
/*  100 */     if (e.a((tableBox.g()).D)) {
/*  101 */       last -= tableBox.s() + tableBox.i().b();
/*      */     } else {
/*  103 */       last -= tableBox.t() + tableBox.i().c();
/*      */     } 
/*  105 */     if (tableBox.k() != null) {
/*  106 */       last += tableBox.k().f();
/*      */     }
/*  108 */     if (tableBox.l() != null) {
/*  109 */       last += tableBox.l().f();
/*      */     }
/*  111 */     byte breakMode = 0;
/*  112 */     d box = null;
/*  113 */     int rowGroup = 0, row = -1;
/*  114 */     label67: for (; rowGroup < tableBox.m(); rowGroup++) {
/*  115 */       w rowGroupBox = tableBox.a(rowGroup);
/*  116 */       if (rowGroup > 0) {
/*  117 */         I pos = rowGroupBox.g();
/*  118 */         breakMode = pos.a;
/*  119 */         if (breakMode == 4 || breakMode == 7) {
/*      */           
/*  121 */           rowGroup--;
/*  122 */           w = tableBox.a(rowGroup);
/*  123 */           row = -1;
/*      */           break;
/*      */         } 
/*      */       } 
/*  127 */       for (row = 0; row < rowGroupBox.h(); row++) {
/*  128 */         v rowBox = rowGroupBox.a(row);
/*  129 */         last += rowBox.f();
/*  130 */         if (e.a(last, pageLimit) > 0) {
/*      */           break label67;
/*      */         }
/*  133 */         J pos = rowBox.g();
/*  134 */         if (rowGroup > 0 || row > 0) {
/*  135 */           breakMode = pos.a;
/*  136 */           if (breakMode == 4 || breakMode == 7) {
/*      */             
/*  138 */             row--;
/*  139 */             if (row >= 0) {
/*  140 */               v v = rowGroupBox.a(row); break label67;
/*      */             } 
/*  142 */             rowGroup--;
/*  143 */             w = tableBox.a(rowGroup);
/*      */             
/*      */             break label67;
/*      */           } 
/*      */         } 
/*  148 */         if (rowGroup == tableBox.m() - 1 && row == rowGroupBox.h() - 1) {
/*      */           break;
/*      */         }
/*      */         
/*  152 */         breakMode = pos.b;
/*  153 */         if (breakMode == 4 || breakMode == 7) {
/*      */           
/*  155 */           if (row < rowGroupBox.h() - 1) {
/*  156 */             v v = rowBox; break label67;
/*      */           } 
/*  158 */           w = rowGroupBox;
/*  159 */           row = -1;
/*      */           
/*      */           break label67;
/*      */         } 
/*      */       } 
/*  164 */       if (rowGroup < tableBox.m() - 1) {
/*  165 */         I pos = rowGroupBox.g();
/*  166 */         breakMode = pos.b;
/*  167 */         if (breakMode == 4 || breakMode == 7) {
/*      */           
/*  169 */           w = rowGroupBox;
/*  170 */           row = -1;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  175 */     if (w == null) {
/*  176 */       return null;
/*      */     }
/*  178 */     return new d.c((d)w, breakMode, rowGroup, row);
/*      */   }
/*      */   public void a(f flowBox) {
/*      */     f f1;
/*  182 */     if (!B && this.d != null) throw new AssertionError((flowBox.b()).al);
/*      */     
/*  184 */     boolean canBreakAfter = false;
/*  185 */     switch (flowBox.a()) {
/*      */       case 5:
/*  187 */         f1 = flowBox;
/*      */         
/*  189 */         if (e.a((j().c_()).D)) {
/*  190 */           canBreakAfter = !(f1.m()).a.c.b().b(); break;
/*      */         } 
/*  192 */         canBreakAfter = !(f1.m()).a.c.a().b();
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  197 */     if (this.r != 0 && this.t == -1) {
/*      */       
/*  199 */       p pos = (p)flowBox.b_();
/*  200 */       while (a(pos));
/*      */ 
/*      */ 
/*      */       
/*  204 */       if (this.r == 2) {
/*  205 */         if (this.v != -1 && canBreakAfter)
/*      */         {
/*  207 */           b(this.v);
/*      */         }
/*  209 */         switch (pos.a) {
/*      */           case 4:
/*      */           case 7:
/*  212 */             if (this.w) {
/*  213 */               b(pos.a);
/*      */             }
/*      */             break;
/*      */           case 5:
/*      */           case 6:
/*  218 */             if (!this.y && (this.w || pos.a != this.s)) {
/*  219 */               b(pos.a);
/*      */             }
/*      */             break;
/*      */           case 8:
/*  223 */             if (!this.y && this.s == 5) {
/*  224 */               b((byte)6);
/*      */             }
/*      */             break;
/*      */           case 9:
/*  228 */             if (!this.y && this.s == 6) {
/*  229 */               b((byte)5);
/*      */             }
/*      */             break;
/*      */           case 1:
/*  233 */             this.x = false;
/*      */             break;
/*      */           case 0:
/*      */             break;
/*      */           default:
/*  238 */             throw new IllegalStateException();
/*      */         } 
/*      */       
/*      */       } 
/*      */     } 
/*  243 */     if (this.t == -1) {
/*  244 */       if (e.a(((v()).a.c_()).D) != 
/*  245 */         e.a((flowBox.c_()).D)) {
/*  246 */         this.t = 0;
/*      */       }
/*      */     } else {
/*  249 */       this.t++;
/*      */     } 
/*  251 */     super.a(flowBox);
/*  252 */     if (canBreakAfter) {
/*  253 */       this.w = true;
/*  254 */       this.x = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean a(p pos) {
/*  260 */     if (!B && this.d != null) throw new AssertionError(); 
/*  261 */     boolean breakFloats = false;
/*  262 */     switch (pos.c) {
/*      */       case 0:
/*  264 */         return false;
/*      */       
/*      */       case 1:
/*  267 */         if ((this.z & 0x1) != 0) {
/*  268 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 2:
/*  273 */         if ((this.z & 0x2) != 0) {
/*  274 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 3:
/*  279 */         if (this.z != 0) {
/*  280 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       default:
/*  285 */         throw new IllegalStateException();
/*      */     } 
/*  287 */     if (!breakFloats) {
/*  288 */       return false;
/*      */     }
/*      */     
/*  291 */     if (C.isLoggable(Level.FINE)) {
/*  292 */       C.fine("page break [block clear]");
/*      */     }
/*      */ 
/*      */     
/*  296 */     double savePageAxis = this.k;
/*  297 */     this.k = z() + 1.0D;
/*  298 */     boolean breaked = A();
/*  299 */     if (!breaked) {
/*  300 */       this.k = savePageAxis;
/*      */     }
/*  302 */     if (this.d != null) {
/*  303 */       f();
/*      */     }
/*  305 */     return true; } public final void a(j box) { r r1;
/*      */     byte pageBreakBefore, pageBreakAfter;
/*      */     p p;
/*      */     o pos;
/*  309 */     if (this.r == 0 || this.t != -1) {
/*  310 */       super.a(box);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  315 */     switch (box.b_().a()) {
/*      */       
/*      */       case 4:
/*  318 */         if (!B && this.d != null) throw new AssertionError(); 
/*  319 */         p = (p)box.b_();
/*  320 */         pageBreakBefore = p.a;
/*  321 */         pageBreakAfter = p.b;
/*      */         
/*  323 */         while (a(p));
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*  329 */         pos = (o)box.b_();
/*  330 */         pageBreakBefore = pos.a;
/*  331 */         pageBreakAfter = pos.b;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 7:
/*  336 */         super.a(box);
/*      */         return;
/*      */       
/*      */       case 8:
/*  340 */         pageBreakAfter = pageBreakBefore = 0;
/*      */         break;
/*      */       
/*      */       default:
/*  344 */         throw new IllegalStateException();
/*      */     } 
/*      */ 
/*      */     
/*  348 */     if (this.r == 2) {
/*  349 */       if (this.v != -1)
/*      */       {
/*  351 */         b(this.v);
/*      */       }
/*  353 */       switch (pageBreakBefore) {
/*      */         case 4:
/*      */         case 7:
/*  356 */           if (this.w) {
/*  357 */             b(pageBreakBefore);
/*      */           }
/*      */           break;
/*      */         case 5:
/*      */         case 6:
/*  362 */           if (!this.y && (this.w || pageBreakBefore != this.s)) {
/*  363 */             b(pageBreakBefore);
/*      */           }
/*      */           break;
/*      */         case 8:
/*  367 */           if (!this.y && this.s == 5) {
/*  368 */             b((byte)6);
/*      */           }
/*      */           break;
/*      */         case 9:
/*  372 */           if (!this.y && this.s == 6) {
/*  373 */             b((byte)5);
/*      */           }
/*      */           break;
/*      */         case 0:
/*      */         case 1:
/*      */           break;
/*      */         default:
/*  380 */           throw new IllegalStateException(String.valueOf(pageBreakBefore));
/*      */       } 
/*      */     
/*      */     } 
/*  384 */     super.a(box);
/*  385 */     if (!this.y) {
/*  386 */       r tableBox; switch (box.a()) {
/*      */         case 7:
/*  388 */           tableBox = (r)box;
/*  389 */           if (!e.a(tableBox)) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           while (true) {
/*  396 */             if (this.r == 2) {
/*  397 */               d.c mode = a(tableBox);
/*  398 */               if (mode != null) {
/*  399 */                 a((d.b)mode);
/*  400 */                 r r2 = tableBox = this.A;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */             } 
/*  405 */             if (e.a(this.k, z()) <= 0) {
/*      */               break;
/*      */             }
/*      */ 
/*      */             
/*  410 */             if (C.isLoggable(Level.FINE)) {
/*  411 */               C.fine("page break [in table]");
/*      */             }
/*  413 */             this.A = null;
/*  414 */             if (!A()) {
/*      */               break;
/*      */             }
/*      */             
/*  418 */             if (this.A == null) {
/*      */               break;
/*      */             }
/*  421 */             r1 = tableBox = this.A;
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 5:
/*      */           break;
/*      */         case 6:
/*  428 */           if (r1.b_().a() != 4) {
/*      */             break;
/*      */           }
/*      */           
/*  432 */           while (e.a(this.k, z()) > 0) {
/*      */ 
/*      */ 
/*      */             
/*  436 */             if (C.isLoggable(Level.FINE)) {
/*  437 */               C.fine("page break [interflow image]");
/*      */             }
/*  439 */             if (!A()) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*  448 */           throw new IllegalStateException();
/*      */       } 
/*      */     
/*  451 */     } else if (r1.a() == 7) {
/*  452 */       this.A = r1;
/*      */     } 
/*      */ 
/*      */     
/*  456 */     this.w = true;
/*  457 */     this.x = true;
/*      */ 
/*      */     
/*  460 */     if (this.r == 2) {
/*  461 */       switch (pageBreakAfter) {
/*      */         case 4:
/*      */         case 7:
/*  464 */           this.v = pageBreakAfter;
/*      */         
/*      */         case 5:
/*      */         case 6:
/*  468 */           if (pageBreakAfter != this.s) {
/*  469 */             b(pageBreakAfter);
/*      */           } else {
/*      */             
/*  472 */             this.v = pageBreakAfter;
/*      */           } 
/*      */         case 1:
/*  475 */           this.x = false;
/*      */         
/*      */         case 0:
/*      */           return;
/*      */       } 
/*  480 */       throw new IllegalStateException();
/*      */     }  }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void y() {
/*  486 */     if (this.r != 0 && this.t == -1 && this.v != -1)
/*      */     {
/*      */       
/*  489 */       b(this.v);
/*      */     }
/*  491 */     super.y();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void b() {
/*  499 */     while (this.d.e()) {
/*      */       
/*  501 */       if (this.r == 0 || this.t != -1) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */       
/*  506 */       k tbb = this.d;
/*  507 */       double pageAxis = this.k;
/*  508 */       pageAxis += this.d.b();
/*  509 */       if (pageAxis > 0.0D) {
/*  510 */         this.w = true;
/*  511 */         this.x = true;
/*      */       } 
/*      */ 
/*      */       
/*  515 */       if (e.a(pageAxis, z()) <= 0) {
/*      */         continue;
/*      */       }
/*      */       
/*  519 */       this.u++;
/*      */       
/*  521 */       i params = this.d.a.g();
/*  522 */       if (this.u < Math.max(2, params.W)) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */       
/*  527 */       super.f();
/*      */       
/*  529 */       if (C.isLoggable(Level.FINE)) {
/*  530 */         C.fine("page break [interline]/" + pageAxis + "/" + this.u);
/*      */       }
/*  532 */       A();
/*  533 */       if (!B && this.d == null) throw new AssertionError(String.valueOf(this));
/*      */ 
/*      */       
/*  536 */       this.d.a(tbb.b, tbb.c);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void f() {
/*  541 */     super.f();
/*  542 */     if (this.k > 0.0D) {
/*  543 */       this.w = true;
/*  544 */       this.x = true;
/*      */     } 
/*      */     
/*  547 */     if (this.r != 0 && this.t == -1) {
/*  548 */       double pageLimit = z();
/*  549 */       if (!this.x || e.a(pageLimit, this.k) >= 0) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  554 */       if (C.isLoggable(Level.FINE)) {
/*  555 */         C.fine("page break [after text]" + pageLimit + "/" + this.k);
/*      */       }
/*  557 */       if (A() && 
/*  558 */         this.d != null)
/*      */       {
/*  560 */         f();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void e() {
/*      */     a blockBox;
/*  567 */     if (!B && this.d != null) throw new AssertionError(); 
/*  568 */     if (!B && this.c.isEmpty()) throw new AssertionError(); 
/*  569 */     c.b flow = this.c.get(this.c.size() - 1);
/*      */     
/*  571 */     if (this.t == -1 && flow.a.j()) {
/*      */       
/*  573 */       double columnLimit = flow.c + flow.a.t();
/*      */       
/*  575 */       double lastFrame = a(flow, 1);
/*      */ 
/*      */       
/*  578 */       if (e.a(columnLimit, z() - lastFrame) > 0) {
/*  579 */         d.a a1 = new d.a((j)flow.a);
/*  580 */         byte flags = 3;
/*  581 */         a(flow, (d)a1, (byte)3, lastFrame, 1);
/*      */       } 
/*      */     } 
/*      */     
/*  585 */     boolean canBreakAfter = false;
/*  586 */     switch (flow.a.a()) {
/*      */       case 5:
/*  588 */         blockBox = (a)flow.a;
/*      */         
/*  590 */         if (e.a((j().c_()).D)) {
/*  591 */           if (!(blockBox.m()).a.c.d().b()) {
/*  592 */             this.w = true;
/*  593 */             this.x = true;
/*  594 */             canBreakAfter = true;
/*      */           }  break;
/*      */         } 
/*  597 */         if (!(blockBox.m()).a.c.c().b()) {
/*  598 */           this.w = true;
/*  599 */           this.x = true;
/*  600 */           canBreakAfter = true;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     
/*  605 */     if (this.r != 0 && this.t == -1) {
/*      */       
/*  607 */       if (this.v != -1 && canBreakAfter) {
/*  608 */         b(this.v);
/*      */       }
/*      */       
/*  611 */       if (this.c.size() == 1) {
/*  612 */         while (this.z != 0) {
/*  613 */           if (C.isLoggable(Level.FINE)) {
/*  614 */             C.fine("page break [floats]");
/*      */           }
/*      */           
/*  617 */           this.k = z() + 1.0D;
/*  618 */           A();
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  623 */       flow = this.c.get(this.c.size() - 1);
/*  624 */       if (this.d != null)
/*      */       {
/*  626 */         f();
/*      */       }
/*      */     } 
/*      */     
/*  630 */     super.e();
/*  631 */     if (this.t != -1) {
/*  632 */       this.t--;
/*      */     }
/*      */ 
/*      */     
/*  636 */     if (this.r != 0 && this.t == -1) {
/*  637 */       double pageLimit = z();
/*  638 */       f flowBox = (f)flow.a;
/*      */       
/*  640 */       p pos = (p)flowBox.b_();
/*  641 */       if (pos.b == 1) {
/*  642 */         this.x = false;
/*      */       }
/*      */       
/*  645 */       if (this.x) {
/*      */ 
/*      */         
/*  648 */         double pageAxis = this.k - this.h + this.i;
/*      */         
/*  650 */         if (e.a(pageAxis, pageLimit) > 0) {
/*  651 */           if (C.isLoggable(Level.FINE)) {
/*  652 */             C.fine("page break [interflow]/" + (flowBox.b()).al);
/*      */           }
/*  654 */           A();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  659 */       if (this.r == 2) {
/*  660 */         switch (pos.b) {
/*      */           case 8:
/*  662 */             if (this.s == 5) {
/*  663 */               b((byte)6);
/*      */             }
/*      */             break;
/*      */           case 9:
/*  667 */             if (this.s == 6) {
/*  668 */               b((byte)5);
/*      */             }
/*      */             break;
/*      */           case 5:
/*      */           case 6:
/*  673 */             if (pos.b != this.s) {
/*  674 */               b(pos.b);
/*      */               break;
/*      */             } 
/*      */           case 4:
/*      */           case 7:
/*  679 */             this.v = pos.b;
/*      */             break;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(k box) {
/*  688 */     boolean breakFloats = false;
/*  689 */     switch ((box.c()).c) {
/*      */       case 0:
/*      */         break;
/*      */       
/*      */       case 1:
/*  694 */         if ((this.z & 0x1) != 0) {
/*  695 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 2:
/*  700 */         if ((this.z & 0x2) != 0) {
/*  701 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 3:
/*  706 */         if (this.z != 0) {
/*  707 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       default:
/*  712 */         throw new IllegalStateException();
/*      */     } 
/*  714 */     if (breakFloats) {
/*  715 */       int i; boolean vertical = e.a((j().c_()).D);
/*  716 */       this.z = (byte)(this.z | (box.c()).e);
/*  717 */       double pageStart = z();
/*  718 */       c.b flow = v();
/*  719 */       flow.a.a(box, 0.0D, pageStart - flow.c);
/*      */       
/*  721 */       double pageAxis = pageStart;
/*  722 */       if (vertical) {
/*  723 */         pageAxis += box.p();
/*      */       } else {
/*  725 */         pageAxis += box.q();
/*      */       } 
/*      */       
/*  728 */       if (this.c != null) {
/*  729 */         i = this.c.size() - 1;
/*      */       } else {
/*  731 */         i = -1;
/*      */       } 
/*  733 */       if (i == -1) {
/*  734 */         c rootBox = j();
/*  735 */         rootBox.a(pageAxis);
/*      */       } 
/*      */     } else {
/*  738 */       super.a(box);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(k box) {
/*  744 */     boolean breakFloats = false;
/*  745 */     switch ((box.c()).c) {
/*      */       case 0:
/*      */         break;
/*      */       
/*      */       case 1:
/*  750 */         if ((this.z & 0x1) != 0) {
/*  751 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 2:
/*  756 */         if ((this.z & 0x2) != 0) {
/*  757 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 3:
/*  762 */         if (this.z != 0) {
/*  763 */           breakFloats = true;
/*      */         }
/*      */         break;
/*      */       
/*      */       default:
/*  768 */         throw new IllegalStateException();
/*      */     } 
/*  770 */     if (breakFloats) {
/*  771 */       int i; boolean vertical = e.a((j().c_()).D);
/*  772 */       this.z = (byte)(this.z | (box.c()).e);
/*  773 */       double pageStart = z();
/*  774 */       c.b flow = v();
/*  775 */       flow.a.a(box, 0.0D, pageStart - flow.c);
/*      */       
/*  777 */       double pageAxis = pageStart;
/*  778 */       if (vertical) {
/*  779 */         pageAxis += box.p();
/*      */       } else {
/*  781 */         pageAxis += box.q();
/*      */       } 
/*      */       
/*  784 */       if (this.c != null) {
/*  785 */         i = this.c.size() - 1;
/*      */       } else {
/*  787 */         i = -1;
/*      */       } 
/*  789 */       if (i == -1) {
/*  790 */         c rootBox = j();
/*  791 */         rootBox.a(pageAxis);
/*      */       } 
/*      */     } else {
/*  794 */       super.b(box);
/*      */     } 
/*      */   }
/*      */   boolean a(k box, double pageStart) { boolean transfer;
/*      */     c containerBox;
/*  799 */     if (this.r == 0 || this.t != -1) {
/*  800 */       return false;
/*      */     }
/*      */     
/*  803 */     double pageAxis = pageStart;
/*  804 */     if (e.a((j().c_()).D)) {
/*  805 */       pageAxis += box.p();
/*      */     } else {
/*  807 */       pageAxis += box.q();
/*      */     } 
/*  809 */     if (e.a(pageAxis, z()) <= 0)
/*      */     {
/*  811 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  815 */     if (e.a((j().c_()).D)) {
/*  816 */       pageStart -= (v()).a.m().d();
/*      */     } else {
/*      */       
/*  819 */       pageStart -= (v()).a.m().a();
/*      */     } 
/*      */     
/*  822 */     switch (box.a()) {
/*      */       case 5:
/*  824 */         this.z = (byte)(this.z | (box.c()).e);
/*  825 */         containerBox = (c)box;
/*  826 */         if ((containerBox.c_()).U == 1) {
/*  827 */           if (e.a(pageStart, 0.0D) <= 0) {
/*      */             
/*  829 */             transfer = false;
/*      */           } else {
/*      */             
/*  832 */             transfer = true;
/*      */           } 
/*      */         } else {
/*      */           
/*  836 */           transfer = false;
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
/*  863 */         return transfer;case 6: if (e.a(pageStart, 0.0D) <= 0) { transfer = false; } else { if (C.isLoggable(Level.FINE)) C.fine("transfer float replaced: " + (box.b()).al);  this.z = (byte)(this.z | (box.c()).e); transfer = true; }  return transfer;
/*      */     } 
/*      */     throw new IllegalStateException(); } public double z() {
/*      */     double pageLimit;
/*  867 */     c rootBox = j();
/*  868 */     i params = rootBox.c_();
/*      */     
/*  870 */     if (e.a(params.D)) {
/*  871 */       pageLimit = rootBox.s();
/*      */     } else {
/*  873 */       pageLimit = rootBox.t();
/*      */     } 
/*  875 */     if (pageLimit < 20.0D)
/*      */     {
/*  877 */       pageLimit = 20.0D;
/*      */     }
/*  879 */     return pageLimit;
/*      */   }
/*      */   
/*      */   public void b(byte breakType) {
/*  883 */     a(new d.b((j)k(), breakType));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(d.b breakMode) {
/*  892 */     if (breakMode.e == 7) {
/*      */       
/*  894 */       c.b columnBreak = null;
/*  895 */       int depth = 0;
/*  896 */       if (this.c != null) {
/*  897 */         for (int i = this.c.size() - 1; i >= 0; i--) {
/*  898 */           c.b flow = this.c.get(i);
/*  899 */           depth++;
/*  900 */           if (flow.a.j()) {
/*  901 */             columnBreak = flow;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  906 */       if (columnBreak != null) {
/*  907 */         double lastFrame = a(columnBreak, depth);
/*  908 */         a(columnBreak, (d)breakMode, (byte)1, lastFrame, depth);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  913 */     boolean breaked = a((d)breakMode, (byte)1);
/*  914 */     if (!B && !breaked) throw new AssertionError(); 
/*  915 */     if (!B && this.d != null) throw new AssertionError();
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean A() {
/*      */     d.a a1;
/*  924 */     byte flags = 1;
/*  925 */     c.b columnBreak = null;
/*  926 */     int depth = 0;
/*  927 */     if (this.c != null) {
/*  928 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/*  929 */         c.b flow = this.c.get(i);
/*  930 */         depth++;
/*      */         
/*  932 */         if (flow.a.j()) {
/*  933 */           columnBreak = flow;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  940 */     if (this.c == null || this.c.size() <= 1) {
/*  941 */       a1 = d.c;
/*      */     } else {
/*  943 */       a1 = new d.a((j)k());
/*  944 */       flags = (byte)(flags | 0x2);
/*      */     } 
/*  946 */     if (columnBreak != null) {
/*  947 */       double lastFrame = a(columnBreak, depth);
/*  948 */       if (a(columnBreak, (d)a1, flags, lastFrame, depth)) {
/*  949 */         return true;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  959 */     return a((d)a1, flags);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected double a(c.b breakFlow, int depth) {
/*  966 */     double lastFrame = 0.0D;
/*  967 */     if (this.c == null) {
/*  968 */       return lastFrame;
/*      */     }
/*  970 */     if (e.a((breakFlow.a.c_()).D)) {
/*  971 */       for (int i = this.c.size() - depth; i >= 0; i--) {
/*  972 */         c.b flow = this.c.get(i);
/*  973 */         lastFrame += flow.a.m().b();
/*      */       } 
/*      */     } else {
/*  976 */       for (int i = this.c.size() - depth; i >= 0; i--) {
/*  977 */         c.b flow = this.c.get(i);
/*  978 */         lastFrame += flow.a.m().c();
/*      */       } 
/*      */     } 
/*  981 */     return lastFrame;
/*      */   }
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
/*      */   protected boolean a(c.b breakFlow, d mode, byte flags, double lastFrame, int depth) {
/*  995 */     if (!B && this.d != null) throw new AssertionError(); 
/*  996 */     this.z = 0;
/*  997 */     this.v = -1;
/*  998 */     this.w = false;
/*  999 */     this.x = false;
/*      */     
/* 1001 */     double pageAxis = z() - breakFlow.c - lastFrame;
/*      */ 
/*      */     
/* 1004 */     if (e.a((breakFlow.a.c_()).D)) {
/* 1005 */       if (e.a(breakFlow.c - breakFlow.a.m().d(), 0.0D) > 0) {
/* 1006 */         flags = (byte)(flags ^ 0x1);
/*      */       }
/*      */     }
/* 1009 */     else if (e.a(breakFlow.c - breakFlow.a.m().a(), 0.0D) > 0) {
/* 1010 */       flags = (byte)(flags ^ 0x1);
/*      */     } 
/*      */ 
/*      */     
/* 1014 */     g container = breakFlow.a.a(pageAxis, mode, flags);
/*      */     
/* 1016 */     if (container == null) {
/* 1017 */       return false;
/*      */     }
/*      */     
/* 1020 */     if (this.c != null) {
/* 1021 */       for (int i = this.c.size() - 1; i >= 0; i--) {
/* 1022 */         c.b flow = this.c.get(i);
/* 1023 */         if (flow == breakFlow) {
/*      */           break;
/*      */         }
/* 1026 */         this.c.remove(i);
/*      */       } 
/*      */     }
/*      */     
/* 1030 */     this.k = breakFlow.c;
/* 1031 */     this.j = breakFlow.b;
/* 1032 */     this.h = 0.0D;
/* 1033 */     this.i = 0.0D;
/* 1034 */     this.u = 0;
/* 1035 */     this.m = null;
/* 1036 */     this.y = true;
/* 1037 */     container.a(this, depth, false);
/* 1038 */     this.y = false;
/* 1039 */     return true;
/*      */   }
/*      */   
/*      */   protected abstract boolean a(d paramd, byte paramByte);
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */