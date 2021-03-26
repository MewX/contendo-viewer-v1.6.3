/*     */ package net.a.a.e.c.e;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.text.AttributedString;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.a;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d;
/*     */ import net.a.a.e.d.a.a;
/*     */ import net.a.a.e.d.b.c;
/*     */ import net.a.a.e.d.b.d;
/*     */ import net.a.a.e.d.b.h;
/*     */ import net.a.a.e.d.d;
/*     */ import net.a.a.g.b;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import net.a.a.g.k;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.mathml.MathMLOperatorElement;
/*     */ import org.w3c.dom.mathml.MathMLScriptElement;
/*     */ import org.w3c.dom.mathml.MathMLUnderOverElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class e
/*     */   extends c
/*     */   implements EventListener, MathMLOperatorElement
/*     */ {
/*     */   public static final String r = "form";
/*     */   public static final String s = "separator";
/*     */   public static final String t = "lspace";
/*     */   public static final String u = "rspace";
/*     */   public static final String v = "minsize";
/*     */   public static final String w = "maxsize";
/*     */   public static final String x = "moveablelimits";
/*     */   public static final String y = "movablelimits";
/*     */   public static final String z = "accent";
/*     */   public static final String A = "mo";
/*     */   public static final float B = 1.2F;
/*     */   public static final float C = 1.5F;
/*     */   public static final String D = "stretchy";
/*     */   public static final String E = "horizontal";
/*     */   public static final String F = "vertical";
/*     */   public static final String G = "largeop";
/*     */   public static final String H = "symmetric";
/*     */   public static final String I = "fence";
/*     */   public static final String J = "MOEvent";
/*     */   private static final long L = 1L;
/*     */   private final c M;
/*     */   private boolean N;
/*     */   
/*     */   public e(String paramString, AbstractDocument paramAbstractDocument) {
/* 162 */     super(paramString, paramAbstractDocument);
/*     */     
/* 164 */     a("form", "infix");
/*     */     
/* 166 */     a("fence", "false");
/* 167 */     a("separator", "false");
/* 168 */     a("lspace", "0.277778em");
/*     */     
/* 170 */     a("rspace", "0.277778em");
/*     */     
/* 172 */     a("stretchy", "false");
/* 173 */     a("symmetric", "true");
/*     */     
/* 175 */     a("maxsize", "9999999pt");
/*     */     
/* 177 */     a("minsize", "1");
/* 178 */     a("largeop", "false");
/* 179 */     a("movablelimits", "false");
/* 180 */     a("accent", "false");
/* 181 */     this.M = d.a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 187 */     return (Node)new e(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float d(c paramc) {
/* 196 */     if (((Integer)paramc.a(d.e)).intValue() > 0) {
/* 197 */       return 0.0F;
/*     */     }
/* 199 */     return a.a(getLspace(), paramc, "pt");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float c(c paramc) {
/* 211 */     if (a.a.equals(paramc.a(d.a))) {
/* 212 */       return 1.5F;
/*     */     }
/* 214 */     return 1.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float e(c paramc) {
/* 224 */     if (((Integer)paramc.a(d.e)).intValue() > 0) {
/* 225 */       return 0.0F;
/*     */     }
/* 227 */     return a.a(getRspace(), paramc, "pt");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean i() {
/* 233 */     return Boolean.parseBoolean(getFence());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxsize(String paramString) {
/* 243 */     setAttribute("maxsize", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMaxsize() {
/* 252 */     return a("maxsize");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinsize(String paramString) {
/* 262 */     setAttribute("minsize", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMinsize() {
/* 271 */     return a("minsize");
/*     */   }
/*     */ 
/*     */   
/*     */   private TextLayout c(Graphics2D paramGraphics2D, c paramc) {
/* 276 */     if (!K && paramGraphics2D == null) throw new AssertionError("Graphics2d is null in produceUnstrechtedLayout"); 
/* 277 */     float f = d.a(paramc);
/* 278 */     if (Boolean.parseBoolean(getLargeop())) {
/* 279 */       f *= c(paramc);
/*     */     }
/*     */     
/* 282 */     String str = f();
/*     */     
/* 284 */     AttributedString attributedString = net.a.a.e.d.c.e.a(str, 
/* 285 */         d(), f, paramc);
/*     */     
/* 287 */     return net.a.a.e.d.c.e.a(paramGraphics2D, attributedString, paramc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void h() {
/* 294 */     super.h();
/* 295 */     if (!this.N) {
/* 296 */       this.N = true;
/* 297 */       k();
/* 298 */       b("largeop", "false");
/* 299 */       b("symmetric", "true");
/* 300 */       b("stretchy", "false");
/* 301 */       b("fence", "false");
/* 302 */       b("lspace", "0.277778em");
/*     */       
/* 304 */       b("rspace", "0.277778em");
/*     */       
/* 306 */       b("movablelimits", "false");
/*     */ 
/*     */       
/* 309 */       j();
/* 310 */       if (i()) {
/* 311 */         a("stretchy", "vertical");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 321 */       this.N = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void j() {
/* 326 */     d d = g();
/* 327 */     while (d != null) {
/* 328 */       if (d instanceof EventTarget) {
/* 329 */         ((EventTarget)d).addEventListener("DOMSubtreeModified", this, false);
/*     */       }
/*     */       
/* 332 */       if (d instanceof net.a.a.e.c.b.i && d.e() > 1) {
/* 333 */         d = null; continue;
/*     */       } 
/* 335 */       d = d.g();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(String paramString1, String paramString2) {
/*     */     String str;
/*     */     try {
/* 344 */       str = this.M.a(f(), 
/* 345 */           getForm(), paramString1);
/* 346 */     } catch (h h) {
/* 347 */       str = paramString2;
/*     */     } 
/* 349 */     if (str.equals("NULL")) {
/* 350 */       str = paramString2;
/*     */     }
/* 352 */     a(paramString1, str);
/*     */   }
/*     */ 
/*     */   
/*     */   private void k() {
/*     */     String str;
/* 358 */     d d = g();
/* 359 */     if (d != null && d instanceof net.a.a.e.c.b.i) {
/* 360 */       int i = d.a((d)this);
/* 361 */       if (i == 0 && d.e() > 0) {
/* 362 */         str = "prefix";
/*     */       }
/* 364 */       else if (i == d.e() - 1 && d
/* 365 */         .e() > 0) {
/* 366 */         str = "postfix";
/*     */       } else {
/* 368 */         str = "infix";
/*     */       } 
/*     */     } else {
/*     */       
/* 372 */       str = "infix";
/*     */     } 
/* 374 */     a("form", str);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLargeop() {
/* 380 */     return a("largeop");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLspace() {
/* 385 */     return a("lspace");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMovablelimits() {
/* 390 */     String str = a("moveablelimits", false);
/*     */     
/* 392 */     if (str == null) {
/* 393 */       return a("movablelimits");
/*     */     }
/* 395 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRspace() {
/* 401 */     return a("rspace");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAccent(String paramString) {
/* 406 */     setAttribute("accent", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFence(String paramString) {
/* 411 */     setAttribute("fence", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForm(String paramString) {
/* 416 */     setAttribute("form", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLargeop(String paramString) {
/* 421 */     setAttribute("largeop", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLspace(String paramString) {
/* 426 */     setAttribute("lspace", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMovablelimits(String paramString) {
/* 431 */     setAttribute("movablelimits", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRspace(String paramString) {
/* 436 */     setAttribute("rspace", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeparator(String paramString) {
/* 441 */     setAttribute("separator", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStretchy(String paramString) {
/* 446 */     setAttribute("stretchy", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSymmetric(String paramString) {
/* 451 */     setAttribute("symmetric", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFence() {
/* 456 */     return a("fence");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getForm() {
/* 461 */     return a("form");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSeparator() {
/* 466 */     return a("separator");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String a() {
/*     */     String str;
/* 479 */     Attr attr = getAttributeNodeNS("http://jeuclid.sf.net/ns/ext", "stretchy");
/*     */     
/* 481 */     if (attr == null) {
/* 482 */       str = a("stretchy");
/*     */     } else {
/* 484 */       str = attr.getValue().trim();
/*     */     } 
/* 486 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStretchy() {
/* 491 */     String str = a();
/* 492 */     if ("horizontal".equalsIgnoreCase(str) || "vertical"
/* 493 */       .equalsIgnoreCase(str)) {
/* 494 */       return "true";
/*     */     }
/* 496 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(String paramString) {
/* 501 */     return ("horizontal".equalsIgnoreCase(paramString) || 
/* 502 */       Boolean.parseBoolean(paramString));
/*     */   }
/*     */   
/*     */   private boolean c(String paramString) {
/* 506 */     return ("vertical".equalsIgnoreCase(paramString) || 
/* 507 */       Boolean.parseBoolean(paramString));
/*     */   }
/*     */   
/*     */   private boolean l() {
/* 511 */     String str = a();
/* 512 */     return (b(str) || 
/* 513 */       c(str));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAccent() {
/* 518 */     return a("accent");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSymmetric() {
/* 523 */     return a("symmetric");
/*     */   }
/*     */   
/*     */   private boolean m() {
/* 527 */     return Boolean.parseBoolean(getSymmetric());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(g paramg, d paramd, f paramf, c paramc) {
/* 534 */     c c1 = b(paramc);
/* 535 */     Graphics2D graphics2D = paramg.d();
/* 536 */     TextLayout textLayout = c(graphics2D, c1);
/*     */     
/* 538 */     net.a.a.e.d.c.e.a a = net.a.a.e.d.c.e.a(textLayout, true);
/*     */     
/* 540 */     float f1 = a.a();
/* 541 */     float f2 = a.b();
/* 542 */     float f3 = a.c();
/* 543 */     float f4 = a.d() + f3;
/* 544 */     d d1 = g();
/* 545 */     float f5 = d(c1);
/* 546 */     float f6 = e(c1);
/* 547 */     if (d1 != null && d1.a((d)this, paramc)) {
/* 548 */       f6 = 0.0F;
/*     */     } else {
/* 550 */       f6 = e(c1);
/*     */     } 
/* 552 */     if (d1 != null && d1.c((d)this)) {
/* 553 */       f5 = 0.0F;
/*     */     } else {
/* 555 */       f5 = d(c1);
/*     */     } 
/*     */     
/* 558 */     paramd.c(f1, f.b);
/* 559 */     paramd.d(f2, f.b);
/* 560 */     paramd.a(f5 + f4 / 2.0F, f.b);
/*     */     
/* 562 */     paramd.e(f5 + f4 + f6, f.b);
/* 563 */     if (l()) {
/* 564 */       paramd.a(f.b);
/* 565 */       paramd.c(0.0F);
/* 566 */       paramd.b(0.0F);
/*     */     } else {
/* 568 */       paramd.a((b)new k(textLayout, f5 + a.c(), 0.0F, null, (Color)c1
/* 569 */             .a(d.i)));
/* 570 */       paramd.a(f.c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(g paramg, d paramd, c paramc) {
/* 578 */     c c1 = b(paramc);
/*     */     
/* 580 */     Graphics2D graphics2D = paramg.d();
/* 581 */     TextLayout textLayout = c(graphics2D, c1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 586 */     String str = a();
/* 587 */     boolean bool1 = c(str);
/*     */     
/* 589 */     boolean bool2 = b(str);
/*     */     
/* 591 */     d d1 = null;
/* 592 */     e e1 = this;
/*     */ 
/*     */     
/*     */     while (true) {
/* 596 */       e e2 = e1;
/* 597 */       d d2 = e1.g();
/* 598 */       boolean bool = false;
/* 599 */       if (d2 instanceof net.a.a.e.c.b.i && d2.e() == 1) {
/*     */         
/* 601 */         bool = true;
/* 602 */       } else if (d2 instanceof MathMLUnderOverElement || d2 instanceof MathMLScriptElement) {
/*     */         boolean bool3;
/*     */ 
/*     */ 
/*     */         
/* 607 */         if (d2 instanceof MathMLUnderOverElement) {
/* 608 */           MathMLUnderOverElement mathMLUnderOverElement = (MathMLUnderOverElement)d2;
/* 609 */           bool3 = (mathMLUnderOverElement.getBase() == e2) ? true : false;
/*     */         } else {
/* 611 */           MathMLScriptElement mathMLScriptElement = (MathMLScriptElement)d2;
/* 612 */           bool3 = (mathMLScriptElement.getBase() == e2) ? true : false;
/*     */         } 
/* 614 */         if (!bool3) {
/* 615 */           bool1 = false;
/*     */         }
/* 617 */         d1 = d2;
/* 618 */         bool = true;
/*     */       } 
/* 620 */       if (!bool) {
/* 621 */         float f1, f2, f3; if (d1 == null) {
/* 622 */           d1 = d2;
/*     */         }
/*     */         
/* 625 */         d d3 = paramg.a((i)d2);
/* 626 */         net.a.a.e.d.c.e.a a = net.a.a.e.d.c.e.a(textLayout, true);
/*     */         
/* 628 */         if (d3 == null) {
/* 629 */           f2 = 1.0F;
/* 630 */           f1 = 1.0F;
/* 631 */           f3 = 0.0F;
/*     */         } else {
/* 633 */           if (bool1) {
/* 634 */             float[] arrayOfFloat = a(paramd, d3, a, c1, graphics2D);
/*     */             
/* 636 */             f1 = arrayOfFloat[0];
/* 637 */             f3 = arrayOfFloat[1];
/*     */           } else {
/* 639 */             f1 = 1.0F;
/* 640 */             f3 = 0.0F;
/*     */           } 
/* 642 */           if (bool2) {
/* 643 */             f2 = a(paramd, paramg
/* 644 */                 .a((i)d1), a);
/*     */           } else {
/* 646 */             f2 = 1.0F;
/*     */           } 
/*     */         } 
/* 649 */         paramd.a((b)new k(textLayout, d(c1) + a
/* 650 */               .c() * f2, f3, 
/* 651 */               AffineTransform.getScaleInstance(f2, f1), (Color)c1
/* 652 */               .a(d.i)));
/* 653 */         paramd.a(f.c);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private float a(d paramd1, d paramd2, net.a.a.e.d.c.e.a parama) {
/* 659 */     float f1, f2 = paramd2.b();
/* 660 */     if (f2 > 0.0F) {
/* 661 */       float f = parama.d();
/* 662 */       if (f > 0.0F) {
/* 663 */         float f3 = Math.max(f, f2);
/* 664 */         f1 = f3 / f;
/* 665 */         paramd1.e(f3, f.c);
/* 666 */         paramd1.a(f3 / 2.0F, f.c);
/*     */       } else {
/*     */         
/* 669 */         f1 = 1.0F;
/*     */       } 
/*     */     } else {
/* 672 */       f1 = 1.0F;
/*     */     } 
/* 674 */     return f1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] a(d paramd1, d paramd2, net.a.a.e.d.c.e.a parama, c paramc, Graphics2D paramGraphics2D) {
/* 682 */     float f1, f5, f6, f3 = parama.b();
/* 683 */     float f4 = parama.a();
/*     */ 
/*     */     
/* 686 */     if (i()) {
/* 687 */       f5 = Math.max(paramd2
/* 688 */           .b(f.b), f4);
/* 689 */       f6 = Math.max(paramd2
/* 690 */           .c(f.b), f3);
/*     */     } else {
/* 692 */       f5 = Math.max(paramd2.d(), f4);
/* 693 */       f6 = Math.max(paramd2.c(), f3);
/*     */     } 
/*     */     
/* 696 */     if (m()) {
/* 697 */       float f14 = b(paramGraphics2D, paramc);
/* 698 */       float f15 = f5 - f14;
/* 699 */       float f16 = f6 + f14;
/* 700 */       float f17 = Math.max(f15, f16);
/*     */       
/* 702 */       f5 = f17 + f14;
/* 703 */       f6 = f17 - f14;
/*     */     } 
/* 705 */     float f7 = f5 + f6;
/* 706 */     float f8 = f4 + f3;
/*     */ 
/*     */     
/* 709 */     float f9 = a.a(
/* 710 */         getMaxsize(), paramc, f8);
/* 711 */     float f10 = a.a(
/* 712 */         getMinsize(), paramc, f8);
/* 713 */     float f11 = Math.max(Math.min(f7, f9), f10);
/*     */     
/* 715 */     float f12 = f11 / f7 * f7 / 2.0F - f7 / 2.0F - f6;
/*     */ 
/*     */ 
/*     */     
/* 719 */     if (f8 > 0.0F) {
/* 720 */       f1 = f11 / f8;
/*     */     } else {
/* 722 */       f1 = 1.0F;
/*     */     } 
/* 724 */     float f13 = f3 * f1;
/* 725 */     float f2 = f12 - f13;
/*     */     
/* 727 */     paramd1.d(f12, f.c);
/* 728 */     paramd1.c(f11 - f12, f.c);
/* 729 */     return new float[] { f1, f2 };
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEvent(Event paramEvent) {
/* 734 */     h();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/e/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */