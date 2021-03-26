/*      */ package jp.cssj.homare.b.b.a;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import jp.cssj.homare.b.a.b.c;
/*      */ import jp.cssj.homare.b.a.b.i;
/*      */ import jp.cssj.homare.b.a.b.x;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.c;
/*      */ import jp.cssj.homare.b.a.c.f;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.o;
/*      */ import jp.cssj.homare.b.a.c.q;
/*      */ import jp.cssj.homare.b.a.c.r;
/*      */ import jp.cssj.homare.b.a.e;
/*      */ import jp.cssj.homare.b.a.h;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.a.n;
/*      */ import jp.cssj.homare.b.b.b;
/*      */ import jp.cssj.homare.b.b.c;
/*      */ import jp.cssj.homare.b.f.e;
/*      */ import jp.cssj.sakae.c.a.d;
/*      */ import jp.cssj.sakae.c.a.f;
/*      */ import jp.cssj.sakae.c.a.h;
/*      */ import jp.cssj.sakae.c.d.a.a.b;
/*      */ import jp.cssj.sakae.c.d.b.a.c;
/*      */ import jp.cssj.sakae.c.d.b.a.d;
/*      */ import jp.cssj.sakae.c.d.c;
/*      */ import jp.cssj.sakae.c.d.g;
/*      */ import jp.cssj.sakae.c.d.h;
/*      */ import jp.cssj.sakae.c.d.i;
/*      */ 
/*      */ public class k {
/*      */   private static final b e;
/*      */   private static final double f = 24.0D;
/*      */   private final a g;
/*      */   x a;
/*      */   private List<a> h;
/*      */   private List<i> i;
/*      */   private boolean j;
/*      */   private boolean k;
/*      */   private boolean l;
/*      */   private boolean m;
/*      */   private boolean n;
/*      */   private boolean o;
/*      */   private byte p;
/*      */   private double q;
/*      */   
/*      */   static {
/*   50 */     e = (b)new jp.cssj.sakae.c.d.a.a.a("‘“（〔［｛〈《「『【⦅〖«〝");
/*      */   }
/*      */   private double r; private double s; private double t; private double u; private double v;
/*      */   private double w;
/*      */   private double x;
/*      */   private e y;
/*      */   private i z;
/*      */   private List<c> A;
/*      */   private double B;
/*      */   private int C;
/*      */   private int D;
/*      */   h b;
/*      */   f c;
/*      */   
/*      */   protected static class a { public final i a;
/*      */     public double b;
/*      */     
/*      */     public a(i inline) {
/*   68 */       this.a = inline;
/*      */     } }
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
/*      */   public k(a builder, byte textState) {
/*      */     jp.cssj.homare.b.a.b.k k1;
/*   82 */     this.h = null;
/*      */     
/*   84 */     this.i = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   94 */     this.m = false;
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
/*  108 */     this.w = 0.0D;
/*      */     
/*  110 */     this.x = 0.0D;
/*      */ 
/*      */ 
/*      */     
/*  114 */     this.z = null;
/*      */     
/*  116 */     this.A = new ArrayList<>();
/*      */     
/*  118 */     this.B = 0.0D;
/*      */     
/*  120 */     this.C = 0;
/*      */     
/*  122 */     this.D = 0;
/*      */ 
/*      */     
/*  125 */     this.g = builder;
/*  126 */     c.b flow = builder.v();
/*  127 */     i params = flow.a.c_();
/*  128 */     this.a = new x(params, textState);
/*      */     
/*  130 */     if ((textState & 0x1) == 0) {
/*  131 */       this.q = flow.a.r();
/*      */     } else {
/*  133 */       this.q = 0.0D;
/*      */     } 
/*      */     
/*  136 */     if ((textState & 0x1) == 0 && params.T != null) {
/*  137 */       c c = new c(params.T);
/*      */     } else {
/*  139 */       k1 = new jp.cssj.homare.b.a.b.k(params);
/*      */     } 
/*      */     
/*  142 */     this.l = ((textState & 0x2) == 0);
/*  143 */     this.y = (e)k1;
/*  144 */     this.j = this.k = true;
/*  145 */     this.v = 0.0D;
/*  146 */     a((f)params);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(f params) {
/*  155 */     switch (params.K) {
/*      */       case 2:
/*  157 */         this.n = false;
/*  158 */         this.o = false;
/*      */         break;
/*      */       
/*      */       case 3:
/*  162 */         this.n = true;
/*  163 */         this.o = false;
/*      */         break;
/*      */       
/*      */       case 1:
/*  167 */         this.n = true;
/*  168 */         this.o = true;
/*      */         break;
/*      */       
/*      */       case 5:
/*  172 */         this.n = true;
/*  173 */         this.o = true;
/*      */         break;
/*      */       
/*      */       case 4:
/*  177 */         this.n = false;
/*  178 */         this.o = true;
/*      */         break;
/*      */       default:
/*  181 */         throw new IllegalStateException();
/*      */     } 
/*  183 */     if (this.o) {
/*  184 */       this.p = params.L;
/*      */     } else {
/*  186 */       this.p = 1;
/*      */     } 
/*  188 */     this.r = e.a(params.H, this.g.k().h());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(e lineBox) {
/*  196 */     this.a.a(lineBox, this.w);
/*  197 */     double pageAdvance = lineBox.k() + lineBox.l();
/*  198 */     this.w += pageAdvance;
/*  199 */     if (!d && e.a(this.w)) throw new AssertionError(); 
/*  200 */     if (pageAdvance > 0.0D) {
/*  201 */       this.g.h = this.g.i = 0.0D;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void g() {
/*  209 */     double pageStart = this.g.k + this.w;
/*  210 */     double lineStart = this.g.j;
/*  211 */     this.u = Double.MAX_VALUE;
/*      */     
/*  213 */     this.t = this.g.k().h();
/*  214 */     if (this.g.m != null) {
/*  215 */       double lineHeight = (this.y.c()).i;
/*      */ 
/*      */       
/*  218 */       double lineEnd0 = this.g.j + this.t;
/*      */       
/*      */       while (true) {
/*  221 */         c.a startContent = null, endContent = null;
/*  222 */         double lineEnd = lineEnd0;
/*  223 */         lineStart = this.g.j;
/*      */ 
/*      */         
/*  226 */         for (int j = 0; j < this.g.m.size(); j++) {
/*  227 */           c.a floating = this.g.m.get(j);
/*  228 */           double pageEnd = floating.e;
/*      */ 
/*      */           
/*  231 */           if (e.a(pageStart, pageEnd) < 0) {
/*      */             double tempStart, tempEnd;
/*      */ 
/*      */             
/*  235 */             o floatingPos = floating.a.c();
/*  236 */             if (e.a(floating.c, pageStart + lineHeight) >= 0) {
/*      */ 
/*      */               
/*  239 */               this.u = floating.c - pageStart;
/*      */               break;
/*      */             } 
/*  242 */             switch (floatingPos.e) {
/*      */               case 1:
/*  244 */                 tempStart = floating.d;
/*  245 */                 if (e.a(tempStart, lineStart) >= 0) {
/*  246 */                   startContent = floating;
/*  247 */                   lineStart = tempStart;
/*      */                 } 
/*      */                 break;
/*      */               
/*      */               case 2:
/*  252 */                 tempEnd = floating.b;
/*  253 */                 if (e.a(tempEnd, lineEnd) <= 0) {
/*  254 */                   endContent = floating;
/*  255 */                   lineEnd = tempEnd;
/*      */                 } 
/*      */                 break;
/*      */               
/*      */               default:
/*  260 */                 throw new IllegalStateException();
/*      */             } 
/*      */           } 
/*  263 */         }  this.t = lineEnd - lineStart;
/*  264 */         if (e.a(this.t, this.x) >= 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  269 */         if (startContent == null && endContent == null) {
/*      */           break;
/*      */         }
/*  272 */         if (endContent == null) {
/*  273 */           pageStart = startContent.e; continue;
/*  274 */         }  if (startContent == null) {
/*  275 */           pageStart = endContent.e; continue;
/*      */         } 
/*  277 */         double startEnd = startContent.e;
/*  278 */         double endEnd = endContent.e;
/*  279 */         if (startEnd > endEnd) {
/*  280 */           pageStart = endEnd; continue;
/*      */         } 
/*  282 */         pageStart = startEnd;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  288 */     if (!d && e.a(pageStart - this.g.k, this.w) < 0) throw new AssertionError(); 
/*  289 */     this.w = pageStart - this.g.k;
/*  290 */     if (!d && e.a(this.w)) throw new AssertionError(); 
/*  291 */     this.s = lineStart - this.g.j;
/*      */ 
/*      */ 
/*      */     
/*  295 */     if (!this.l && e.a((this.y.c()).D)) {
/*  296 */       for (int j = 0; j < this.A.size(); ) {
/*  297 */         c c = this.A.get(j);
/*  298 */         if (c.c() == 0.0D) {
/*      */           j++; continue;
/*      */         } 
/*  301 */         if (c.f_() == 1) {
/*  302 */           h text = (h)c;
/*  303 */           char c1 = text.h()[0];
/*  304 */           if (k.e.a(c1)) {
/*  305 */             this.q = -text.b().e() * 0.5D;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private h h()
/*      */   {
/*  319 */     if (this.h == null || this.h.isEmpty()) {
/*  320 */       return (h)this.y;
/*      */     }
/*  322 */     a inline = this.h.get(this.h.size() - 1);
/*  323 */     return (h)inline.a; } private void a(n box) { double baseline; i i1; n inlineBox; q params; double advance; r pos; d flm; c lineParams; double ascent; r r1;
/*      */     double descent, d1, d2, start, verticalAlign;
/*      */     f textParams;
/*      */     a inline;
/*      */     double d3;
/*  328 */     h textBox = h();
/*  329 */     textBox.a(box);
/*      */ 
/*      */     
/*  332 */     if (this.h == null) {
/*  333 */       this.h = new ArrayList<>();
/*  334 */       baseline = 0.0D;
/*  335 */     } else if (this.h.isEmpty()) {
/*  336 */       baseline = 0.0D;
/*      */     }
/*      */     else {
/*      */       
/*  340 */       a parentInline = this.h.get(this.h.size() - 1);
/*  341 */       baseline = parentInline.b;
/*      */     } 
/*      */     
/*  344 */     switch (box.a()) {
/*      */       case 4:
/*  346 */         i1 = (i)box;
/*  347 */         params = i1.f();
/*  348 */         pos = box.c();
/*  349 */         flm = params.a();
/*  350 */         ascent = flm.c();
/*  351 */         d1 = flm.d();
/*  352 */         i1.a(ascent, d1);
/*      */ 
/*      */         
/*  355 */         textParams = textBox.g();
/*  356 */         if (e.a(textParams.D)) {
/*      */           
/*  358 */           start = i1.m().a();
/*      */         } else {
/*      */           
/*  361 */           start = i1.m().b();
/*      */         } 
/*  363 */         this.y.a(start);
/*  364 */         i1.a(start);
/*  365 */         inline = new a(i1);
/*  366 */         this.h.add(inline);
/*      */ 
/*      */         
/*  369 */         d3 = pos.a.a(textBox, this.y, ascent, d1, pos.b, baseline);
/*      */         
/*  371 */         inline.b = baseline + d3;
/*      */         
/*  373 */         if (i1.m().f() > 0.0D) {
/*      */           
/*  375 */           double lineHeight = pos.b;
/*  376 */           lineHeight = Math.max((this.y.c()).i, lineHeight);
/*  377 */           double textHeight = ascent + d1;
/*  378 */           if (lineHeight != textHeight) {
/*  379 */             lineHeight = (lineHeight - textHeight) / 2.0D;
/*  380 */             ascent += lineHeight;
/*  381 */             d1 += lineHeight;
/*      */           } 
/*  383 */           ascent = ascent + d3 + baseline;
/*  384 */           d1 = d1 - d3 - baseline;
/*  385 */           this.y.a(ascent, d1);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 5:
/*      */       case 6:
/*  392 */         inlineBox = box;
/*      */         
/*  394 */         lineParams = this.y.c();
/*  395 */         if (e.a(lineParams.D)) {
/*      */           
/*  397 */           advance = inlineBox.q();
/*      */         } else {
/*      */           
/*  400 */           advance = inlineBox.p();
/*      */         } 
/*  402 */         textBox.a(advance);
/*  403 */         if (this.y != textBox) {
/*  404 */           this.y.a(advance);
/*      */         }
/*  406 */         this.h.add(null);
/*      */         
/*  408 */         r1 = box.c();
/*      */         
/*  410 */         if (box.a() == 5) {
/*      */           
/*  412 */           c inlineBlockBox = (c)box;
/*  413 */           i i2 = inlineBlockBox.c_();
/*  414 */           switch (lineParams.D) {
/*      */             
/*      */             case 1:
/*  417 */               if (i2.D == 1) {
/*  418 */                 descent = inlineBlockBox.o();
/*  419 */                 if (e.a(descent)) {
/*  420 */                   descent = 0.0D;
/*      */                 }
/*      */               } else {
/*      */                 
/*  424 */                 descent = 0.0D;
/*      */               } 
/*  426 */               d2 = inlineBox.q() - descent;
/*      */               break;
/*      */             
/*      */             case 2:
/*      */             case 3:
/*  431 */               if (i2.D == 2 || i2.D == 3) {
/*  432 */                 descent = inlineBlockBox.o();
/*  433 */                 if (e.a(descent)) {
/*  434 */                   descent = inlineBox.p() / 2.0D;
/*      */                 }
/*      */               } else {
/*      */                 
/*  438 */                 descent = inlineBox.p() / 2.0D;
/*      */               } 
/*  440 */               d2 = inlineBox.p() - descent;
/*      */               break;
/*      */             default:
/*  443 */               throw new IllegalStateException();
/*      */           } 
/*      */         
/*      */         } else {
/*  447 */           switch (lineParams.D) {
/*      */             
/*      */             case 1:
/*  450 */               d2 = box.q();
/*  451 */               descent = 0.0D;
/*      */               break;
/*      */             
/*      */             case 2:
/*      */             case 3:
/*  456 */               d2 = box.p();
/*  457 */               descent = d2 /= 2.0D;
/*      */             
/*      */             default:
/*  460 */               throw new IllegalStateException();
/*      */           } 
/*      */         
/*      */         } 
/*  464 */         verticalAlign = r1.a.a(textBox, this.y, d2, descent, r1.b, baseline);
/*      */ 
/*      */         
/*  467 */         if (box.a() == 5) {
/*      */           
/*  469 */           double lineHeight = r1.b;
/*  470 */           lineHeight = Math.max((this.y.c()).i, lineHeight);
/*  471 */           double textHeight = d2 + descent;
/*  472 */           if (lineHeight > textHeight) {
/*  473 */             lineHeight = (lineHeight - textHeight) / 2.0D;
/*  474 */             d2 += lineHeight;
/*  475 */             descent += lineHeight;
/*      */           } 
/*      */         } 
/*      */         
/*  479 */         d2 = d2 + verticalAlign + baseline;
/*  480 */         descent = descent - verticalAlign - baseline;
/*      */         
/*  482 */         this.y.a(d2, descent);
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/*  487 */     throw new IllegalStateException(); }
/*      */ 
/*      */ 
/*      */   
/*      */   private void i() {
/*  492 */     a inline = this.h.remove(this.h.size() - 1);
/*  493 */     if (inline != null) {
/*  494 */       double advance, end; i inlineBox = inline.a;
/*      */       
/*  496 */       inlineBox.o();
/*      */ 
/*      */       
/*  499 */       c params = this.y.c();
/*  500 */       switch (params.D) {
/*      */         
/*      */         case 1:
/*  503 */           end = inlineBox.m().d();
/*  504 */           advance = inlineBox.p() + end;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/*      */         case 3:
/*  510 */           end = inlineBox.m().c();
/*  511 */           advance = inlineBox.q() + end;
/*      */           break;
/*      */         
/*      */         default:
/*  515 */           throw new IllegalStateException();
/*      */       } 
/*  517 */       inlineBox.a(end);
/*  518 */       this.y.a(end);
/*  519 */       h textBox = h();
/*  520 */       if (this.y != textBox)
/*  521 */         textBox.a(advance); 
/*      */     } 
/*      */   } private void a(c c1) {
/*      */     double ascent, descent;
/*      */     h text;
/*      */     jp.cssj.sakae.c.d.b.a.a control;
/*  527 */     h textBox = h();
/*      */     
/*  529 */     double advance = c1.c();
/*      */ 
/*      */     
/*  532 */     switch (c1.f_()) {
/*      */       case 1:
/*  534 */         text = (h)c1;
/*  535 */         textBox.a(text);
/*  536 */         ascent = text.f();
/*  537 */         descent = text.g();
/*  538 */         if (!d && e.a(ascent + descent)) throw new AssertionError(); 
/*      */         break;
/*      */       case 2:
/*  541 */         control = (jp.cssj.sakae.c.d.b.a.a)c1;
/*  542 */         textBox.a(control);
/*  543 */         if (control.e() == ' ' && control.c() == 0.0D) {
/*      */           return;
/*      */         }
/*  546 */         ascent = control.f();
/*  547 */         descent = control.g();
/*  548 */         if (!d && e.a(ascent + descent)) throw new AssertionError(); 
/*      */         break;
/*      */       default:
/*  551 */         throw new IllegalStateException();
/*      */     } 
/*  553 */     textBox.a(advance);
/*  554 */     if (this.y != textBox) {
/*  555 */       e e1; double baseline; this.y.a(advance);
/*      */ 
/*      */ 
/*      */       
/*  559 */       if (this.h.size() >= 2) {
/*  560 */         a parentInline = this.h.get(this.h.size() - 2);
/*  561 */         baseline = parentInline.b;
/*  562 */         i i1 = parentInline.a;
/*      */       } else {
/*  564 */         baseline = 0.0D;
/*  565 */         e1 = this.y;
/*      */       } 
/*  567 */       i inlineBox = (i)textBox;
/*  568 */       r pos = inlineBox.c();
/*      */       
/*  570 */       double verticalAlign = pos.a.a((h)e1, this.y, ascent, descent, pos.b, baseline);
/*      */       
/*  572 */       double lineHeight = pos.b;
/*      */ 
/*      */       
/*  575 */       double textHeight = (this.y.c()).i;
/*  576 */       if (!e.a(textHeight)) {
/*  577 */         lineHeight = Math.max(textHeight, lineHeight);
/*      */       }
/*      */       
/*  580 */       if (!d && e.a(lineHeight)) throw new AssertionError(); 
/*  581 */       textHeight = ascent + descent;
/*      */       
/*  583 */       if (lineHeight != textHeight) {
/*  584 */         lineHeight = (lineHeight - textHeight) / 2.0D;
/*  585 */         ascent += lineHeight;
/*  586 */         descent += lineHeight;
/*  587 */         ascent = ascent + verticalAlign + baseline;
/*  588 */         descent = descent - verticalAlign - baseline;
/*      */       } 
/*  590 */       if (!d && e.a(ascent + descent)) throw new AssertionError(); 
/*      */     } else {
/*  592 */       double lineHeight = (this.y.c()).i;
/*      */       
/*  594 */       double textHeight = ascent + descent;
/*  595 */       if (lineHeight != textHeight) {
/*  596 */         lineHeight = (lineHeight - textHeight) / 2.0D;
/*  597 */         ascent += lineHeight;
/*  598 */         descent += lineHeight;
/*      */       } 
/*  600 */       if (!d && e.a(ascent + descent)) throw new AssertionError(); 
/*      */     } 
/*  602 */     this.y.a(ascent, descent);
/*      */   }
/*      */   
/*      */   double a() {
/*  606 */     return this.w + this.y.i();
/*      */   }
/*      */   
/*      */   double b() {
/*  610 */     return this.w;
/*      */   }
/*      */   
/*      */   double c() {
/*  614 */     return this.x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean a(boolean last) {
/*  624 */     boolean lineAdded = false;
/*  625 */     if (b(last)) {
/*  626 */       e lineBox = this.y;
/*  627 */       jp.cssj.homare.b.a.b.k newLineBox = lineBox.a(this.a.g());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  633 */       lineBox.a(this.q, this.s, this.t, last);
/*  634 */       if (this.h != null && !this.h.isEmpty()) {
/*  635 */         f lineParams = this.y.g();
/*  636 */         this.y = (e)newLineBox;
/*      */         
/*  638 */         List<a> inlineStack = this.h;
/*  639 */         this.h = null;
/*      */         int m;
/*  641 */         for (m = 0; m < inlineStack.size(); m++) {
/*  642 */           a inline = inlineStack.get(m);
/*  643 */           i oldInline = inline.a;
/*  644 */           i newInline = oldInline.a(true);
/*  645 */           newInline.b(this.g.k());
/*  646 */           a((n)newInline);
/*      */         } 
/*  648 */         for (m = inlineStack.size() - 1; m >= 1; m--) {
/*  649 */           a inline = inlineStack.get(m);
/*  650 */           a parent = inlineStack.get(m - 1);
/*  651 */           if (e.a(lineParams.D)) {
/*      */             
/*  653 */             parent.a.a(inline.a.q());
/*      */           } else {
/*      */             
/*  656 */             parent.a.a(inline.a.p());
/*      */           } 
/*      */         } 
/*      */       } else {
/*  660 */         this.y = (e)newLineBox;
/*      */       } 
/*  662 */       a(lineBox);
/*  663 */       lineAdded = true;
/*      */     } 
/*      */     
/*  666 */     this.l = last;
/*  667 */     this.q = 0.0D;
/*  668 */     this.j = this.k = true;
/*  669 */     this.v = 0.0D;
/*  670 */     if (last) {
/*  671 */       return lineAdded;
/*      */     }
/*      */     
/*  674 */     if (!this.n) {
/*  675 */       return lineAdded;
/*      */     }
/*      */     
/*  678 */     for (int j = 0; j < this.A.size(); j++) {
/*  679 */       c c = this.A.get(j);
/*  680 */       if (c instanceof d) {
/*  681 */         d whiteSpace = (d)c;
/*  682 */         this.x -= whiteSpace.c();
/*  683 */         whiteSpace.h();
/*      */       } else {
/*      */         
/*  686 */         this.j = false;
/*      */         break;
/*      */       } 
/*      */     } 
/*  690 */     return lineAdded;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean b(boolean last) {
/*      */     int count;
/*      */     boolean content;
/*  700 */     if (this.k) {
/*  701 */       g();
/*  702 */       this.k = false;
/*      */     } 
/*      */     
/*  705 */     if (last) {
/*  706 */       count = this.A.size();
/*      */     } else {
/*  708 */       count = this.C;
/*      */     } 
/*      */     
/*  711 */     if (!d && count <= 0 && !last) throw new AssertionError();
/*      */ 
/*      */     
/*  714 */     if (count > 0) {
/*  715 */       for (int j = 0; j < count; j++) {
/*  716 */         h h1; i text; g quad; jp.cssj.sakae.c.d.b.a.a control; c c = this.A.get(j);
/*  717 */         switch (c.f_()) {
/*      */           case 1:
/*  719 */             text = (i)c;
/*  720 */             if (j == count - 1) {
/*  721 */               if (last || this.D == 0 || this.D == text.l()) {
/*      */ 
/*      */ 
/*      */                 
/*  725 */                 text.n();
/*  726 */                 if (this.z == text) {
/*  727 */                   this.z = null;
/*      */                 }
/*      */               } else {
/*      */                 
/*  731 */                 h1 = text.a(this.D);
/*  732 */                 i prevText = (i)h1;
/*      */                 
/*  734 */                 this.x += this.c.a(prevText.e[prevText.g - 1], text.e[0]);
/*      */                 
/*  736 */                 this.A.add(j, h1);
/*      */               } 
/*      */             }
/*  739 */             a((c)h1);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2:
/*  744 */             quad = (g)h1;
/*  745 */             if (quad instanceof b) {
/*      */               b.e inlineStartQuad; b.d inlineReplacedQuad; b.a inlineAbsoluteQuad;
/*  747 */               b inlineQuad = (b)quad;
/*  748 */               switch (inlineQuad.b()) {
/*      */                 
/*      */                 case 1:
/*  751 */                   inlineStartQuad = (b.e)inlineQuad;
/*  752 */                   a((n)inlineStartQuad.g);
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 2:
/*  758 */                   i();
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 3:
/*  764 */                   inlineReplacedQuad = (b.d)inlineQuad;
/*  765 */                   a((n)inlineReplacedQuad.g);
/*  766 */                   i();
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 4:
/*  772 */                   a((n)inlineQuad.a());
/*  773 */                   i();
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 5:
/*  779 */                   inlineAbsoluteQuad = (b.a)inlineQuad;
/*  780 */                   h().a(inlineAbsoluteQuad.g);
/*      */                   break;
/*      */               } 
/*      */ 
/*      */               
/*  785 */               throw new IllegalStateException();
/*      */             } 
/*      */             
/*  788 */             control = (jp.cssj.sakae.c.d.b.a.a)quad;
/*  789 */             a((c)control);
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/*  794 */             throw new IllegalStateException();
/*      */         } 
/*  796 */         this.x -= h1.c();
/*      */       } 
/*      */       
/*  799 */       double lastSpaceAdvance = 0.0D;
/*  800 */       for (int m = count - 1; m >= 0; m--) {
/*  801 */         c c = this.A.get(m);
/*  802 */         if (c.f_() == 2 && 
/*  803 */           c instanceof jp.cssj.sakae.c.d.b.a.a) {
/*  804 */           jp.cssj.sakae.c.d.b.a.a a1 = (jp.cssj.sakae.c.d.b.a.a)c;
/*  805 */           lastSpaceAdvance += a1.c();
/*      */           
/*      */           continue;
/*      */         } 
/*  809 */         if (c.c() <= 0.0D) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */       
/*  814 */       this.y.a(-lastSpaceAdvance);
/*  815 */       int remainder = this.A.size() - count; int n;
/*  816 */       for (n = 0; n < remainder; n++) {
/*  817 */         this.A.set(n, this.A.get(count + n));
/*      */       }
/*  819 */       for (n = 0; n < count; n++) {
/*  820 */         this.A.remove(this.A.size() - 1);
/*      */       }
/*  822 */       content = true;
/*      */     } else {
/*  824 */       content = false;
/*      */     } 
/*      */     
/*  827 */     this.C = this.A.size();
/*  828 */     if (this.z != null) {
/*  829 */       this.D = this.z.l();
/*      */     } else {
/*  831 */       this.D = 0;
/*      */     } 
/*  833 */     this.g.w();
/*  834 */     return content;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(h fontStyle, f fontMetrics) {
/*  842 */     if (!d && this.z != null) throw new AssertionError();
/*      */     
/*  844 */     this.b = fontStyle;
/*  845 */     this.c = fontMetrics;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/*  856 */     if (this.p == 2 && this.B > 0.0D) {
/*  857 */       if (this.k) {
/*  858 */         g();
/*  859 */         this.k = false;
/*      */       } 
/*  861 */       double lineAxis = this.B + this.r;
/*  862 */       if (this.z == null) {
/*  863 */         lineAxis += this.c.a(gid);
/*      */       } else {
/*  865 */         lineAxis += this.z.b(gid);
/*      */       } 
/*  867 */       double maxLineAxis = this.t - this.q;
/*  868 */       if (e.a(lineAxis, maxLineAxis) > 0) {
/*  869 */         e();
/*      */       }
/*      */     } 
/*      */     
/*  873 */     if (this.z == null) {
/*  874 */       if (!d && this.b == null) throw new AssertionError(); 
/*  875 */       if (!d && this.c == null) throw new AssertionError(); 
/*  876 */       this.z = new i(charOffset, this.b, this.c);
/*  877 */       this.z.a(this.r);
/*  878 */       this.A.add(this.z);
/*      */     } 
/*      */     
/*  881 */     double advance = this.z.a(ch, coff, clen, gid) + this.r;
/*  882 */     this.B += advance;
/*  883 */     this.x += advance;
/*  884 */     this.v = 0.0D;
/*  885 */     this.j = false;
/*      */     
/*  887 */     if (e.a(this.z.f() + this.z.g(), this.u) > 0)
/*      */     {
/*  889 */       this.t = 0.0D;
/*      */     }
/*      */     
/*  892 */     if (this.z.l() > 10000) {
/*      */       
/*  894 */       d();
/*  895 */       a(this.b, this.c);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void d() {
/*  902 */     if (!d && this.z.l() <= 0) throw new AssertionError(); 
/*  903 */     this.z.n();
/*  904 */     this.z = null;
/*      */   }
/*      */   
/*      */   public void a(g quad) {
/*  908 */     if (!d && this.z != null) throw new AssertionError(); 
/*  909 */     if (quad instanceof jp.cssj.sakae.c.d.b.a.a) {
/*      */       c tab; d whiteSpace;
/*  911 */       jp.cssj.sakae.c.d.b.a.a control = (jp.cssj.sakae.c.d.b.a.a)quad;
/*  912 */       switch (control.e()) {
/*      */         
/*      */         case '\n':
/*  915 */           this.m = true;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '\t':
/*  920 */           tab = (c)control;
/*  921 */           tab.a = 24.0D - this.x % 24.0D;
/*      */           break;
/*      */ 
/*      */         
/*      */         case ' ':
/*  926 */           if (!this.n) {
/*      */             break;
/*      */           }
/*  929 */           whiteSpace = (d)control;
/*  930 */           if (this.j) {
/*      */             
/*  932 */             whiteSpace.h();
/*      */             break;
/*      */           } 
/*  935 */           this.v = whiteSpace.c();
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  940 */           throw new IllegalStateException();
/*      */       } 
/*      */     } else {
/*      */       f f1; b.e inlineStartQuad; b.c inlineEndQuad; double lineHeight;
/*  944 */       if (this.i == null || this.i.isEmpty()) {
/*  945 */         f params = this.y.g();
/*      */       } else {
/*  947 */         i box = this.i.get(this.i.size() - 1);
/*  948 */         f params = box.g();
/*      */       } 
/*      */       
/*  951 */       b inlineQuad = (b)quad;
/*  952 */       switch (inlineQuad.b()) {
/*      */         case 1:
/*  954 */           inlineStartQuad = (b.e)inlineQuad;
/*  955 */           f1 = inlineStartQuad.g.g();
/*  956 */           a(f1);
/*  957 */           if (this.i == null) {
/*  958 */             this.i = new ArrayList<>();
/*      */           }
/*  960 */           if (!d && !this.i.isEmpty() && 
/*  961 */             (((j)this.i.get(this.i.size() - 1)).b()).al == f1.al)
/*  962 */             throw new AssertionError(f1.al);  this.i.add(inlineStartQuad.g);
/*  963 */           if (inlineStartQuad.c() != 0.0D) {
/*  964 */             this.v = 0.0D;
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  973 */           inlineEndQuad = (b.c)inlineQuad;
/*      */ 
/*      */ 
/*      */           
/*  977 */           this.i.remove(this.i.size() - 1);
/*  978 */           if (this.i.isEmpty()) {
/*  979 */             f1 = this.y.g();
/*      */           } else {
/*  981 */             i box = this.i.get(this.i.size() - 1);
/*  982 */             f1 = box.g();
/*      */           } 
/*      */           
/*  985 */           a(f1);
/*  986 */           if (inlineEndQuad.c() != 0.0D) {
/*  987 */             this.v = 0.0D;
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 3:
/*      */         case 4:
/*  995 */           if (e.a(f1.D)) {
/*  996 */             lineHeight = inlineQuad.a().p();
/*      */           } else {
/*  998 */             lineHeight = inlineQuad.a().q();
/*      */           } 
/* 1000 */           if (e.a(lineHeight, this.u) > 0)
/*      */           {
/* 1002 */             this.t = 0.0D;
/*      */           }
/* 1004 */           this.j = false;
/*      */           break;
/*      */         
/*      */         case 5:
/*      */           break;
/*      */         
/*      */         default:
/* 1011 */           throw new IllegalStateException();
/*      */       } 
/*      */     } 
/* 1014 */     this.B += quad.c();
/* 1015 */     this.x += quad.c();
/* 1016 */     this.A.add(quad);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean e() {
/* 1025 */     this.B = 0.0D;
/* 1026 */     if (this.A.isEmpty()) {
/* 1027 */       return false;
/*      */     }
/* 1029 */     if (this.o && this.x > 0.0D) {
/* 1030 */       if (this.k) {
/* 1031 */         g();
/* 1032 */         this.k = false;
/*      */       } 
/* 1034 */       if (this.C > 0) {
/* 1035 */         double lineAxis = this.x - this.v;
/* 1036 */         double maxLineAxis = this.t - this.q;
/*      */         
/* 1038 */         if (e.a(lineAxis, maxLineAxis) > 0) {
/*      */           
/* 1040 */           boolean ret = a(false);
/* 1041 */           return ret;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1045 */     if (this.m) {
/*      */       
/* 1047 */       boolean ret = a(true);
/* 1048 */       this.m = false;
/* 1049 */       return ret;
/*      */     } 
/* 1051 */     this.C = this.A.size();
/* 1052 */     if (this.z != null) {
/* 1053 */       this.D = this.z.l();
/*      */     } else {
/* 1055 */       this.D = 0;
/*      */     } 
/* 1057 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void f() {
/* 1063 */     if (!b(true)) {
/* 1064 */       if (!d && this.a.l() <= 0) throw new AssertionError(this.z); 
/*      */       return;
/*      */     } 
/* 1067 */     this.y.a(this.q, this.s, this.t, true);
/* 1068 */     a(this.y);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/b/a/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */