/*      */ package jp.cssj.sakae.pdf.d;
/*      */ 
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import jp.cssj.sakae.a.e;
/*      */ import jp.cssj.sakae.a.f;
/*      */ import jp.cssj.sakae.c.a.e;
/*      */ import jp.cssj.sakae.c.a.g;
/*      */ import jp.cssj.sakae.c.a.h;
/*      */ import jp.cssj.sakae.c.b;
/*      */ import jp.cssj.sakae.c.c.e;
/*      */ import jp.cssj.sakae.c.c.f;
/*      */ import jp.cssj.sakae.c.c.i;
/*      */ import jp.cssj.sakae.c.d.h;
/*      */ import jp.cssj.sakae.pdf.c.e;
/*      */ import jp.cssj.sakae.pdf.c.f;
/*      */ import jp.cssj.sakae.pdf.f;
/*      */ import jp.cssj.sakae.pdf.g;
/*      */ import jp.cssj.sakae.pdf.h;
/*      */ import jp.cssj.sakae.pdf.j;
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
/*      */ public class a
/*      */   implements b
/*      */ {
/*      */   static {
/*  149 */     k = Logger.getLogger(a.class.getName());
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
/*      */   static class a
/*      */   {
/*  162 */     public a.d a = null;
/*      */     
/*      */     public final double b;
/*      */     
/*      */     public final short c;
/*      */     
/*      */     public final short d;
/*      */     
/*      */     public final double[] e;
/*      */     
/*      */     public final e f;
/*      */     
/*      */     public final e g;
/*      */     
/*      */     public final short h;
/*      */     
/*      */     public final float i;
/*      */     
/*      */     public final float j;
/*      */     
/*      */     public final byte k;
/*      */     
/*      */     public final byte l;
/*      */     
/*      */     public final AffineTransform m;
/*      */     
/*      */     public a(a gc) {
/*  189 */       this.b = a.a(gc);
/*  190 */       this.c = a.b(gc);
/*  191 */       this.d = a.c(gc);
/*  192 */       this.e = a.d(gc);
/*  193 */       this.f = a.e(gc);
/*  194 */       this.g = a.f(gc);
/*  195 */       this.h = a.g(gc);
/*  196 */       this.i = gc.b;
/*  197 */       this.j = gc.d;
/*  198 */       this.k = gc.f;
/*  199 */       this.l = gc.h;
/*  200 */       this.m = a.h(gc);
/*  201 */       if (this.m != null) {
/*  202 */         a.a(gc, new AffineTransform(this.m));
/*      */       }
/*      */     }
/*      */     
/*      */     public void a(a gc) {
/*  207 */       a.a(gc, this.b);
/*  208 */       a.a(gc, this.c);
/*  209 */       a.b(gc, this.d);
/*  210 */       a.a(gc, this.e);
/*  211 */       a.a(gc, this.f);
/*  212 */       a.b(gc, this.g);
/*  213 */       a.c(gc, this.h);
/*  214 */       gc.b = this.i;
/*  215 */       gc.d = this.j;
/*  216 */       gc.f = this.k;
/*  217 */       gc.h = this.l;
/*  218 */       a.a(gc, this.m);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class d
/*      */   {
/*      */     public final double a;
/*      */ 
/*      */     
/*      */     public final short b;
/*      */ 
/*      */     
/*      */     public final short c;
/*      */     
/*      */     public final double[] d;
/*      */     
/*      */     public final e e;
/*      */     
/*      */     public final e f;
/*      */     
/*      */     public final float g;
/*      */     
/*      */     public final float h;
/*      */     
/*      */     public final byte i;
/*      */     
/*      */     public final byte j;
/*      */     
/*      */     public final double k;
/*      */     
/*      */     public final short l;
/*      */ 
/*      */     
/*      */     public d(a gc) {
/*  254 */       this.a = a.i(gc);
/*  255 */       this.b = a.j(gc);
/*  256 */       this.c = a.k(gc);
/*  257 */       this.d = a.l(gc);
/*  258 */       this.e = a.m(gc);
/*  259 */       this.f = a.n(gc);
/*  260 */       this.k = a.o(gc);
/*  261 */       this.l = a.p(gc);
/*  262 */       this.g = gc.e;
/*  263 */       this.h = gc.c;
/*  264 */       this.i = gc.i;
/*  265 */       this.j = gc.g;
/*      */     }
/*      */     
/*      */     public void a(a gc) {
/*  269 */       a.b(gc, this.a);
/*  270 */       a.d(gc, this.b);
/*  271 */       a.e(gc, this.c);
/*  272 */       a.b(gc, this.d);
/*  273 */       a.c(gc, this.e);
/*  274 */       a.d(gc, this.f);
/*  275 */       a.c(gc, this.k);
/*  276 */       a.f(gc, this.l);
/*  277 */       gc.e = this.g;
/*  278 */       gc.c = this.h;
/*  279 */       gc.i = this.i;
/*  280 */       gc.g = this.j;
/*      */     }
/*      */   }
/*      */   
/*  284 */   private List<a> m = new ArrayList<>();
/*      */   
/*  286 */   private AffineTransform x = null;
/*      */   
/*  288 */   private AffineTransform y = null;
/*      */   
/*  290 */   private Shape z = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  295 */   private short A = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  300 */   private short B = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  305 */   private short C = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   private short D = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  315 */   private double E = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  320 */   private double F = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  325 */   private double[] G = t;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  330 */   private double[] H = t;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  335 */   private e I = (e)jp.cssj.sakae.c.c.c.b;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  340 */   private e J = (e)jp.cssj.sakae.c.c.c.b;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  345 */   private e K = (e)jp.cssj.sakae.c.c.c.b;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  350 */   private e L = (e)jp.cssj.sakae.c.c.c.b;
/*      */   
/*  352 */   private double M = 0.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  357 */   private short N = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  362 */   private short O = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  367 */   public float b = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  372 */   public float c = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  377 */   public float d = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  382 */   public float e = 1.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  387 */   public byte f = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   public byte g = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  397 */   public byte h = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  402 */   public byte i = 0;
/*      */ 
/*      */   
/*      */   private static class b
/*      */   {
/*      */     final double a;
/*      */     
/*      */     final double b;
/*      */     final jp.cssj.sakae.c.b.b c;
/*      */     final AffineTransform d;
/*      */     
/*      */     b(double pageWidth, double pageHeight, jp.cssj.sakae.c.b.b image, AffineTransform at) {
/*  414 */       this.a = pageWidth;
/*  415 */       this.b = pageHeight;
/*  416 */       this.c = image;
/*  417 */       this.d = at;
/*      */     }
/*      */     
/*      */     public boolean equals(Object o) {
/*  421 */       if (o instanceof b) {
/*  422 */         b key = (b)o;
/*  423 */         return (key.a == this.a && key.b == this.b && key.c
/*  424 */           .equals(this.c) && key.d.equals(this.d));
/*      */       } 
/*  426 */       return false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  430 */       int hash = 1;
/*  431 */       long a = Double.doubleToLongBits(this.a);
/*  432 */       long l1 = Double.doubleToLongBits(this.b);
/*  433 */       hash = hash * 31 + (int)(a ^ a >>> 32L);
/*  434 */       hash = hash * 31 + (int)(l1 ^ l1 >>> 32L);
/*  435 */       hash = hash * 31 + this.c.hashCode();
/*  436 */       if (this.d != null) {
/*  437 */         hash = hash * 31 + this.d.hashCode();
/*      */       }
/*  439 */       return hash;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  445 */   private int Q = 0; private static final Logger k; private static final boolean l = false; protected final jp.cssj.sakae.pdf.d a;
/*      */   private final Map<b, Object> P;
/*      */   private final int R;
/*      */   
/*      */   private a(jp.cssj.sakae.pdf.d out, Map<b, Object> patterns) {
/*  450 */     this.a = out;
/*  451 */     this.P = patterns;
/*  452 */     this.R = this.a.d().a().h();
/*      */   }
/*      */   
/*      */   public a(jp.cssj.sakae.pdf.d out) {
/*  456 */     this(out, new HashMap<>());
/*      */   }
/*      */   
/*      */   public e a() {
/*  460 */     return c().b();
/*      */   }
/*      */   
/*      */   public jp.cssj.sakae.pdf.d b() {
/*  464 */     return this.a;
/*      */   }
/*      */   
/*      */   public j c() {
/*  468 */     return this.a.d();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void d() throws jp.cssj.sakae.c.c {
/*      */     try {
/*  476 */       r();
/*  477 */       s();
/*  478 */     } catch (IOException iOException) {
/*  479 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*  481 */     this.m.add(new a(this));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void e() throws jp.cssj.sakae.c.c {
/*      */     try {
/*  489 */       u();
/*  490 */     } catch (IOException iOException) {
/*  491 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*  493 */     a state = this.m.remove(this.m.size() - 1);
/*  494 */     state.a(this);
/*  495 */     if (this.m.isEmpty()) {
/*  496 */       this.x = null;
/*      */     }
/*  498 */     this.z = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void p() throws jp.cssj.sakae.c.c {
/*      */     try {
/*  506 */       u();
/*  507 */     } catch (IOException iOException) {
/*  508 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*  510 */     a state = this.m.get(this.m.size() - 1);
/*  511 */     state.a(this);
/*  512 */     this.x = null;
/*  513 */     this.z = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(double lineWidth) {
/*  520 */     this.E = lineWidth;
/*      */   }
/*      */   
/*      */   public double f() {
/*  524 */     return this.E;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(double[] linePattern) {
/*  531 */     if (linePattern != null && linePattern.length > 0) {
/*  532 */       this.G = linePattern;
/*      */     } else {
/*  534 */       this.G = t;
/*      */     } 
/*      */   }
/*      */   
/*      */   public double[] g() {
/*  539 */     return this.G;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(short lineJoin) {
/*  546 */     this.C = lineJoin;
/*      */   }
/*      */   
/*      */   public short h() {
/*  550 */     return this.C;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(short lineCap) {
/*  557 */     this.A = lineCap;
/*      */   }
/*      */   
/*      */   public short i() {
/*  561 */     return this.A;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(Object paint) throws jp.cssj.sakae.c.c {
/*  568 */     a(paint, false);
/*      */   }
/*      */   
/*      */   public Object j() {
/*  572 */     return this.I;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(Object paint) throws jp.cssj.sakae.c.c {
/*  579 */     a(paint, true);
/*      */   }
/*      */   
/*      */   public Object k() {
/*  583 */     return this.K;
/*      */   }
/*      */   
/*      */   protected void a(Object paint, boolean fill) throws jp.cssj.sakae.c.c {
/*  587 */     e p = (e)paint;
/*  588 */     if (fill) {
/*  589 */       this.K = p;
/*  590 */       this.d = 1.0F;
/*  591 */       this.h = jp.cssj.sakae.c.c.a.e;
/*      */     } else {
/*  593 */       this.I = p;
/*  594 */       this.b = 1.0F;
/*  595 */       this.f = jp.cssj.sakae.c.c.a.e;
/*      */     } 
/*  597 */     if (p.b() == 1) {
/*  598 */       jp.cssj.sakae.c.c.b color = (jp.cssj.sakae.c.c.b)p;
/*  599 */       switch (color.c()) {
/*      */         case 4:
/*  601 */           if (fill) {
/*  602 */             this.d = color.g(); break;
/*      */           } 
/*  604 */           this.b = color.g();
/*      */           break;
/*      */         
/*      */         case 2:
/*  608 */           if (fill) {
/*  609 */             this.h = ((jp.cssj.sakae.c.c.a)color).h(); break;
/*      */           } 
/*  611 */           this.f = ((jp.cssj.sakae.c.c.a)color).h();
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float l() {
/*  619 */     return this.b;
/*      */   }
/*      */   
/*      */   public void a(float strokeAlpha) {
/*  623 */     this.b = strokeAlpha;
/*      */   }
/*      */   
/*      */   public float m() {
/*  627 */     return this.d;
/*      */   }
/*      */   
/*      */   public void b(float fillAlpha) {
/*  631 */     this.d = fillAlpha;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void c(short textMode) {
/*  638 */     this.N = textMode;
/*      */   }
/*      */   
/*      */   public short n() {
/*  642 */     return this.N;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(AffineTransform at) throws jp.cssj.sakae.c.c {
/*  649 */     if (at == null || at.isIdentity()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  654 */     if (!j && Double.isNaN(at.getTranslateX())) throw new AssertionError(); 
/*  655 */     if (!j && Double.isNaN(at.getTranslateY())) throw new AssertionError(); 
/*  656 */     if (!j && Double.isNaN(at.getScaleX())) throw new AssertionError(); 
/*  657 */     if (!j && Double.isNaN(at.getScaleY())) throw new AssertionError(); 
/*  658 */     if (!j && Double.isNaN(at.getShearX())) throw new AssertionError(); 
/*  659 */     if (!j && Double.isNaN(at.getShearY())) throw new AssertionError(); 
/*      */     try {
/*  661 */       s();
/*  662 */     } catch (IOException iOException) {
/*  663 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*  665 */     if (this.x == null) {
/*  666 */       this.x = new AffineTransform(at);
/*      */     } else {
/*  668 */       this.x.concatenate(at);
/*      */     } 
/*  670 */     if (this.y == null) {
/*  671 */       this.y = new AffineTransform(at);
/*      */     } else {
/*  673 */       this.y.concatenate(at);
/*      */     } 
/*      */   }
/*      */   
/*      */   public AffineTransform o() {
/*  678 */     return (this.y == null) ? null : new AffineTransform(this.y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(Shape clip) throws jp.cssj.sakae.c.c {
/*      */     try {
/*  686 */       r();
/*  687 */       s();
/*  688 */     } catch (IOException iOException) {
/*  689 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*  691 */     this.z = clip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(Shape shape) throws jp.cssj.sakae.c.c {
/*      */     try {
/*      */       int winding;
/*  699 */       t();
/*      */       
/*  701 */       if (shape instanceof Rectangle2D) {
/*  702 */         Rectangle2D r = (Rectangle2D)shape;
/*  703 */         if (this.a.b(r.getWidth(), 0.0D) || this.a.b(r.getHeight(), 0.0D)) {
/*      */           return;
/*      */         }
/*  706 */         winding = 1;
/*  707 */         a(r);
/*      */       } else {
/*  709 */         PathIterator i = shape.getPathIterator(null);
/*  710 */         winding = i.getWindingRule();
/*  711 */         a(i);
/*      */       } 
/*      */       
/*  714 */       switch (winding) {
/*      */         case 1:
/*  716 */           this.a.b("f");
/*      */           return;
/*      */         case 0:
/*  719 */           this.a.b("f*");
/*      */           return;
/*      */       } 
/*  722 */       throw new IllegalStateException();
/*      */     }
/*  724 */     catch (IOException iOException) {
/*  725 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void c(Shape shape) throws jp.cssj.sakae.c.c {
/*      */     try {
/*      */       boolean close;
/*  734 */       t();
/*      */       
/*  736 */       if (shape instanceof Rectangle2D) {
/*  737 */         Rectangle2D r = (Rectangle2D)shape;
/*  738 */         close = false;
/*  739 */         a(r);
/*      */       } else {
/*  741 */         PathIterator i = shape.getPathIterator(null);
/*  742 */         close = a(i);
/*      */       } 
/*      */       
/*  745 */       this.a.b(close ? "s" : "S");
/*  746 */     } catch (IOException iOException) {
/*  747 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void d(Shape shape) throws jp.cssj.sakae.c.c {
/*      */     try {
/*      */       int winding;
/*      */       boolean close;
/*  756 */       t();
/*      */ 
/*      */       
/*  759 */       if (shape instanceof Rectangle2D) {
/*  760 */         Rectangle2D r = (Rectangle2D)shape;
/*  761 */         winding = 1;
/*  762 */         close = false;
/*  763 */         a(r);
/*      */       } else {
/*  765 */         PathIterator i = shape.getPathIterator(null);
/*  766 */         winding = i.getWindingRule();
/*  767 */         close = a(i);
/*      */       } 
/*      */       
/*  770 */       switch (winding) {
/*      */         case 1:
/*  772 */           this.a.b(close ? "b" : "B");
/*      */           return;
/*      */         case 0:
/*  775 */           this.a.b(close ? "b*" : "B*");
/*      */           return;
/*      */       } 
/*  778 */       throw new IllegalStateException();
/*      */     }
/*  780 */     catch (IOException iOException) {
/*  781 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(jp.cssj.sakae.c.b.b image) throws jp.cssj.sakae.c.c {
/*      */     try {
/*  790 */       t();
/*  791 */     } catch (IOException iOException) {
/*  792 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*  794 */     image.a(this);
/*      */   }
/*      */   
/*      */   public void a(String name, double width, double height) throws jp.cssj.sakae.c.c {
/*      */     try {
/*  799 */       t();
/*  800 */       d();
/*      */       
/*  802 */       q();
/*  803 */       this.a.a(width);
/*  804 */       this.a.a(0.0D);
/*  805 */       this.a.a(0.0D);
/*  806 */       this.a.a(height);
/*  807 */       this.a.a(0.0D, height);
/*  808 */       this.a.b("cm");
/*      */       
/*  810 */       this.a.a("XObject", name);
/*  811 */       this.a.a(name);
/*  812 */       this.a.b("Do");
/*      */       
/*  814 */       e();
/*  815 */     } catch (IOException iOException) {
/*  816 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(h text, double x, double y) throws jp.cssj.sakae.c.c {
/*  824 */     if (text.l() <= 0) {
/*      */       return;
/*      */     }
/*      */     
/*  828 */     e font = ((f)text.d()).a();
/*  829 */     g fpl = text.b().f();
/*  830 */     boolean outline = false;
/*  831 */     for (int i = 0; i < fpl.b(); i++) {
/*  832 */       switch (fpl.a(i)) {
/*      */         case 2:
/*      */         case 3:
/*      */           break;
/*      */         case 4:
/*  837 */           outline = true;
/*      */           break;
/*      */       } 
/*      */     } 
/*  841 */     if ((outline || font instanceof jp.cssj.sakae.a.k) && 
/*  842 */       font instanceof jp.cssj.sakae.a.d) {
/*  843 */       d();
/*  844 */       a(AffineTransform.getTranslateInstance(x, y));
/*  845 */       jp.cssj.sakae.c.a.a.a.a(this, (jp.cssj.sakae.a.d)font, text);
/*  846 */       e();
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  851 */     if (!j && text.i() <= 0) throw new AssertionError();  try {
/*      */       double enlargement; jp.cssj.sakae.a.b bbox;
/*  853 */       t();
/*  854 */       if (this.N != this.O) {
/*  855 */         this.O = this.N;
/*  856 */         this.a.a(this.N);
/*  857 */         this.a.b("Tr");
/*      */       } 
/*      */       
/*  860 */       f fm = (f)text.d();
/*  861 */       f source = (f)fm.b();
/*  862 */       if (this.R == 1412 || this.R == 1421) {
/*  863 */         byte type = source.h_();
/*  864 */         if (type != 2 && type != 0) {
/*  865 */           throw new IllegalStateException("PDF/A-1またはPDF/X-1aで埋め込みフォント以外は使用できません。");
/*      */         }
/*      */       } 
/*  868 */       h fontStyle = text.b();
/*      */       
/*  870 */       if (k.isLoggable(Level.FINE)) {
/*  871 */         k.fine("drawText: fontSource=" + source + " text=" + text);
/*      */       }
/*      */       
/*  874 */       boolean localContext = false;
/*  875 */       double size = fontStyle.e();
/*      */       
/*  877 */       short weight = fontStyle.b();
/*  878 */       if (this.N == 0 && weight >= 500 && source.c() < 500) {
/*      */         
/*  880 */         switch (weight) {
/*      */           case 500:
/*  882 */             enlargement = size / 28.0D;
/*      */             break;
/*      */           case 600:
/*  885 */             enlargement = size / 24.0D;
/*      */             break;
/*      */           case 700:
/*  888 */             enlargement = size / 20.0D;
/*      */             break;
/*      */           case 800:
/*  891 */             enlargement = size / 16.0D;
/*      */             break;
/*      */           case 900:
/*  894 */             enlargement = size / 12.0D;
/*      */             break;
/*      */           default:
/*  897 */             throw new IllegalStateException();
/*      */         } 
/*  899 */         if (enlargement > 0.0D) {
/*  900 */           v();
/*  901 */           localContext = true;
/*  902 */           this.a.a(enlargement);
/*  903 */           this.a.b("w");
/*  904 */           this.a.a(2);
/*  905 */           this.a.b("Tr");
/*  906 */           if (!this.K.equals(this.I)) {
/*  907 */             if (this.J != null && this.J.b() != 1) {
/*  908 */               this.a.a("DeviceRGB");
/*  909 */               this.a.b("CS");
/*      */             } 
/*  911 */             this.a.b((jp.cssj.sakae.c.c.b)this.K);
/*      */           } 
/*      */         } 
/*      */       } else {
/*  915 */         enlargement = 0.0D;
/*      */       } 
/*      */ 
/*      */       
/*  919 */       byte direction = fontStyle.a();
/*  920 */       AffineTransform rotate = null;
/*  921 */       double center = 0.0D;
/*  922 */       boolean verticalFont = false;
/*  923 */       switch (direction) {
/*      */         case 1:
/*      */         case 2:
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  930 */           if (source.e() == direction) {
/*      */             
/*  932 */             verticalFont = true;
/*      */             break;
/*      */           } 
/*  935 */           if (!localContext) {
/*  936 */             v();
/*  937 */             localContext = true;
/*      */           } 
/*  939 */           rotate = AffineTransform.getRotateInstance(1.5707963267948966D, x, y);
/*  940 */           this.a.a(rotate);
/*  941 */           this.a.b("cm");
/*  942 */           bbox = source.f();
/*  943 */           center = (bbox.b + bbox.d) * size / 1000.0D / 2.0D;
/*  944 */           y += center;
/*      */           break;
/*      */         
/*      */         default:
/*  948 */           throw new IllegalStateException();
/*      */       } 
/*      */ 
/*      */       
/*  952 */       this.a.b("BT");
/*      */ 
/*      */       
/*  955 */       short style = (short)fontStyle.c();
/*  956 */       if (style != 1 && !source.b()) {
/*      */         
/*  958 */         if (verticalFont) {
/*      */           
/*  960 */           this.a.a(1.0D);
/*  961 */           this.a.a(-0.25D);
/*  962 */           this.a.a(0.0D);
/*  963 */           this.a.a(1.0D);
/*  964 */           this.a.a(x, y);
/*  965 */           this.a.b("Tm");
/*      */         } else {
/*      */           
/*  968 */           this.a.a(1.0D);
/*  969 */           this.a.a(0.0D);
/*  970 */           this.a.a(0.25D);
/*  971 */           this.a.a(1.0D);
/*  972 */           this.a.a(x, y);
/*  973 */           this.a.b("Tm");
/*      */         } 
/*      */       } else {
/*  976 */         this.a.a(x, y);
/*  977 */         this.a.b("Td");
/*      */       } 
/*      */ 
/*      */       
/*  981 */       String name = ((e)font).i();
/*  982 */       this.a.a("Font", name);
/*  983 */       this.a.a(name);
/*  984 */       this.a.a(size);
/*  985 */       this.a.b("Tf");
/*      */ 
/*      */       
/*  988 */       double letterSpacing = text.m();
/*      */       
/*  990 */       if (verticalFont) {
/*  991 */         letterSpacing = -letterSpacing;
/*      */       }
/*  993 */       if (!this.a.b(letterSpacing, this.M)) {
/*  994 */         this.a.a(letterSpacing);
/*  995 */         this.a.b("Tc");
/*  996 */         if (!localContext) {
/*  997 */           this.M = letterSpacing;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1002 */       font.a(this, text);
/*      */ 
/*      */       
/* 1005 */       this.a.b("ET");
/*      */       
/* 1007 */       if (enlargement > 0.0D) {
/*      */         
/* 1009 */         this.a.a(0);
/* 1010 */         this.a.b("Tr");
/* 1011 */         if (!this.K.equals(this.I)) {
/* 1012 */           if (this.L != null && this.L.b() != 1) {
/* 1013 */             this.a.a("DeviceRGB");
/* 1014 */             this.a.b("CS");
/*      */           } 
/* 1016 */           this.a.b((jp.cssj.sakae.c.c.b)this.I);
/*      */         } 
/*      */       } 
/*      */       
/* 1020 */       if (localContext) {
/* 1021 */         w();
/*      */       }
/* 1023 */     } catch (IOException iOException) {
/* 1024 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static class c extends a implements jp.cssj.sakae.c.b.a {
/*      */     c(b image) {
/* 1030 */       super((jp.cssj.sakae.pdf.d)image);
/*      */     }
/*      */     
/*      */     public jp.cssj.sakae.c.b.b q() throws jp.cssj.sakae.c.c {
/* 1034 */       b image = (b)this.a;
/*      */       try {
/* 1036 */         image.close();
/* 1037 */       } catch (IOException e) {
/* 1038 */         throw new jp.cssj.sakae.c.c(e);
/*      */       } 
/* 1040 */       return image;
/*      */     }
/*      */   }
/*      */   
/*      */   public jp.cssj.sakae.c.b.a a(double width, double height) {
/*      */     try {
/* 1046 */       b image = c().a(width, height);
/* 1047 */       jp.cssj.sakae.c.b.a gc = new c(image);
/* 1048 */       return gc;
/* 1049 */     } catch (IOException iOException) {
/* 1050 */       throw new jp.cssj.sakae.c.c(iOException);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void r() throws IOException {
/* 1055 */     if (this.x != null) {
/* 1056 */       q();
/* 1057 */       this.a.a(this.x);
/* 1058 */       this.a.b("cm");
/* 1059 */       this.x = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void s() throws IOException {
/* 1064 */     if (this.z != null) {
/* 1065 */       int winding; q();
/*      */       
/* 1067 */       if (this.z instanceof Rectangle2D) {
/* 1068 */         Rectangle2D r = (Rectangle2D)this.z;
/* 1069 */         winding = 1;
/* 1070 */         this.a.b(r.getX(), r.getY(), r.getWidth(), r.getHeight());
/* 1071 */         this.a.b("re");
/*      */       } else {
/* 1073 */         PathIterator i = this.z.getPathIterator(null);
/* 1074 */         winding = i.getWindingRule();
/* 1075 */         a(i);
/*      */       } 
/*      */       
/* 1078 */       switch (winding) {
/*      */         case 1:
/* 1080 */           this.a.b("W");
/*      */           break;
/*      */         case 0:
/* 1083 */           this.a.b("W*");
/*      */           break;
/*      */         default:
/* 1086 */           throw new IllegalStateException();
/*      */       } 
/* 1088 */       this.a.b("n");
/* 1089 */       this.z = null;
/*      */     }  } private String a(e paint) throws jp.cssj.sakae.c.c { String name; jp.cssj.sakae.c.c.d gradient; i gp; f pattern; jp.cssj.sakae.c.c.b[] colors; jp.cssj.sakae.c.b.b image; double[] fractions; AffineTransform at; double radius; jp.cssj.sakae.pdf.d pout; b key; AffineTransform affineTransform1; double dx;
/*      */     double dy;
/*      */     double d1;
/*      */     jp.cssj.sakae.pdf.d d2;
/* 1094 */     switch (paint.b()) {
/*      */       
/*      */       case 2:
/* 1097 */         pattern = (f)paint;
/* 1098 */         image = pattern.c();
/* 1099 */         at = o();
/* 1100 */         if (at == null) {
/* 1101 */           at = pattern.a();
/* 1102 */         } else if (pattern.a() != null) {
/* 1103 */           at.concatenate(pattern.a());
/*      */         } 
/*      */         
/* 1106 */         pout = this.a;
/* 1107 */         key = new b(pout.a(), pout.b(), image, at);
/*      */         
/* 1109 */         name = (String)this.P.get(key);
/* 1110 */         if (name == null) {
/* 1111 */           double width = image.a();
/* 1112 */           double height = image.b();
/* 1113 */           try (f tout = pout.d().a(width, height, pout
/* 1114 */                 .b(), at)) {
/* 1115 */             a pgc = new a((jp.cssj.sakae.pdf.d)tout, this.P);
/* 1116 */             image.a(pgc);
/* 1117 */             name = tout.e();
/* 1118 */           } catch (IOException iOException) {
/* 1119 */             new jp.cssj.sakae.c.c(iOException);
/*      */           } 
/* 1121 */           this.P.put(key, name);
/*      */         } 
/* 1123 */         return name;
/*      */ 
/*      */       
/*      */       case 3:
/* 1127 */         if (this.a.d().a().h() < 1300) {
/* 1128 */           return null;
/*      */         }
/* 1130 */         gradient = (jp.cssj.sakae.c.c.d)paint;
/*      */         
/* 1132 */         colors = gradient.f();
/* 1133 */         fractions = gradient.g();
/*      */         
/* 1135 */         at = o();
/* 1136 */         if (at == null) {
/* 1137 */           at = gradient.h();
/* 1138 */         } else if (gradient.h() != null) {
/* 1139 */           at.concatenate(gradient.h());
/*      */         } 
/*      */         
/* 1142 */         pout = this.a;
/* 1143 */         try (g sout = pout.d().a(pout.b(), at)) {
/* 1144 */           sout.a("ShadingType");
/* 1145 */           sout.a(2);
/* 1146 */           sout.k();
/*      */           
/* 1148 */           sout.a("Coords");
/* 1149 */           sout.i();
/* 1150 */           sout.a(gradient.a());
/* 1151 */           sout.a(gradient.c());
/* 1152 */           sout.a(gradient.d());
/* 1153 */           sout.a(gradient.e());
/* 1154 */           sout.j();
/* 1155 */           sout.k();
/* 1156 */           a((h)sout, colors, fractions);
/*      */           
/* 1158 */           return sout.a();
/* 1159 */         } catch (IOException iOException) {
/* 1160 */           throw new jp.cssj.sakae.c.c(iOException);
/*      */         } 
/*      */ 
/*      */       
/*      */       case 4:
/* 1165 */         if (this.a.d().a().h() < 1300) {
/* 1166 */           return null;
/*      */         }
/* 1168 */         gp = (i)paint;
/*      */         
/* 1170 */         colors = gp.g();
/* 1171 */         fractions = gp.h();
/* 1172 */         radius = gp.d();
/*      */         
/* 1174 */         affineTransform1 = o();
/* 1175 */         if (affineTransform1 == null) {
/* 1176 */           affineTransform1 = gp.i();
/* 1177 */         } else if (gp.i() != null) {
/* 1178 */           affineTransform1.concatenate(gp.i());
/*      */         } 
/*      */         
/* 1181 */         dx = gp.e() - gp.a();
/* 1182 */         dy = gp.f() - gp.c();
/* 1183 */         d1 = Math.sqrt(dx * dx + dy * dy);
/* 1184 */         if (d1 > radius) {
/* 1185 */           double scale = radius * 0.9999D / d1;
/* 1186 */           dx *= scale;
/* 1187 */           dy *= scale;
/*      */         } 
/*      */         
/* 1190 */         d2 = this.a;
/* 1191 */         try (g sout = d2.d().a(d2.b(), affineTransform1)) {
/* 1192 */           sout.a("ShadingType");
/* 1193 */           sout.a(3);
/* 1194 */           sout.k();
/*      */           
/* 1196 */           sout.a("Coords");
/* 1197 */           sout.i();
/* 1198 */           sout.a(gp.a() + dx);
/* 1199 */           sout.a(gp.c() + dy);
/* 1200 */           sout.a(0.0D);
/* 1201 */           sout.a(gp.a());
/* 1202 */           sout.a(gp.c());
/* 1203 */           sout.a(radius);
/* 1204 */           sout.j();
/* 1205 */           sout.k();
/*      */           
/* 1207 */           a((h)sout, colors, fractions);
/* 1208 */           return sout.a();
/* 1209 */         } catch (IOException iOException) {
/* 1210 */           throw new jp.cssj.sakae.c.c(iOException);
/*      */         } 
/*      */     } 
/*      */ 
/*      */     
/* 1215 */     throw new IllegalStateException(); }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(h sout, jp.cssj.sakae.c.c.b[] colors, double[] fractions) throws IOException {
/*      */     short colorType;
/* 1221 */     sout.a("ColorSpace");
/*      */     
/* 1223 */     if (c().a().l() == 1) {
/* 1224 */       colorType = 3;
/* 1225 */     } else if (c().a().l() == 2) {
/* 1226 */       colorType = 2;
/*      */     } else {
/* 1228 */       colorType = colors[0].c();
/* 1229 */       for (int i = 1; i < colors.length; i++) {
/* 1230 */         if (colorType != colors[i].c()) {
/* 1231 */           colorType = 1;
/*      */         }
/*      */       } 
/* 1234 */       if (colorType == 4) {
/* 1235 */         colorType = 1;
/*      */       }
/*      */     } 
/* 1238 */     switch (colorType) {
/*      */       case 3:
/* 1240 */         sout.a("DeviceGray");
/*      */         break;
/*      */       case 1:
/* 1243 */         sout.a("DeviceRGB");
/*      */         break;
/*      */       case 2:
/* 1246 */         sout.a("DeviceCMYK");
/*      */         break;
/*      */       default:
/* 1249 */         throw new IllegalStateException();
/*      */     } 
/* 1251 */     sout.k();
/*      */     
/* 1253 */     sout.a("Extend");
/* 1254 */     sout.i();
/* 1255 */     sout.a(true);
/* 1256 */     sout.a(true);
/* 1257 */     sout.j();
/* 1258 */     sout.k();
/*      */     
/* 1260 */     sout.a("Function");
/* 1261 */     sout.g();
/* 1262 */     if (colors.length <= 2 && (fractions == null || fractions.length == 0 || (fractions.length == 1 && fractions[0] == 0.0D) || (fractions.length == 2 && fractions[0] == 0.0D && fractions[1] == 1.0D))) {
/*      */ 
/*      */ 
/*      */       
/* 1266 */       jp.cssj.sakae.c.c.b c0 = colors[0];
/* 1267 */       jp.cssj.sakae.c.c.b c1 = colors[1];
/*      */       
/* 1269 */       sout.a("FunctionType");
/* 1270 */       sout.a(2);
/* 1271 */       sout.k();
/*      */       
/* 1273 */       sout.a("Domain");
/* 1274 */       sout.i();
/* 1275 */       sout.a(0.0D);
/* 1276 */       sout.a(1.0D);
/* 1277 */       sout.j();
/* 1278 */       sout.k();
/*      */       
/* 1280 */       sout.a("N");
/* 1281 */       sout.a(1.0D);
/* 1282 */       sout.k();
/*      */       
/* 1284 */       sout.a("C0");
/* 1285 */       sout.i();
/* 1286 */       a(sout, colorType, c0);
/* 1287 */       sout.j();
/* 1288 */       sout.k();
/*      */       
/* 1290 */       sout.a("C1");
/* 1291 */       sout.i();
/* 1292 */       a(sout, colorType, c1);
/* 1293 */       sout.j();
/* 1294 */       sout.k();
/*      */     } else {
/*      */       
/* 1297 */       int segments = fractions.length - 1;
/* 1298 */       if (fractions[0] != 0.0D) {
/* 1299 */         segments++;
/*      */       }
/* 1301 */       if (fractions[fractions.length - 1] != 1.0D) {
/* 1302 */         segments++;
/*      */       }
/*      */       
/* 1305 */       sout.a("FunctionType");
/* 1306 */       sout.a(3);
/* 1307 */       sout.k();
/*      */       
/* 1309 */       sout.a("Domain");
/* 1310 */       sout.i();
/* 1311 */       sout.a(0.0D);
/* 1312 */       sout.a(1.0D);
/* 1313 */       sout.j();
/* 1314 */       sout.k();
/*      */       
/* 1316 */       sout.a("Encode");
/* 1317 */       sout.i(); int i;
/* 1318 */       for (i = 0; i < segments; i++) {
/* 1319 */         sout.a(0.0D);
/* 1320 */         sout.a(1.0D);
/*      */       } 
/* 1322 */       sout.j();
/* 1323 */       sout.k();
/*      */       
/* 1325 */       sout.a("Bounds");
/* 1326 */       sout.i();
/* 1327 */       if (fractions[0] != 0.0D) {
/* 1328 */         sout.a(fractions[0]);
/*      */       }
/* 1330 */       for (i = 1; i < fractions.length - 1; i++) {
/* 1331 */         sout.a(fractions[i]);
/*      */       }
/* 1333 */       if (fractions[fractions.length - 1] != 1.0D) {
/* 1334 */         sout.a(fractions[fractions.length - 1]);
/*      */       }
/* 1336 */       sout.j();
/* 1337 */       sout.k();
/*      */       
/* 1339 */       sout.a("Functions");
/* 1340 */       sout.i();
/* 1341 */       for (i = -1; i < fractions.length; i++) {
/*      */         jp.cssj.sakae.c.c.b c0, c1;
/* 1343 */         if (i == -1) {
/* 1344 */           if (fractions[0] != 0.0D) {
/* 1345 */             c0 = colors[0];
/* 1346 */             c1 = colors[0];
/*      */           } else {
/*      */             continue;
/*      */           } 
/* 1350 */         } else if (i == fractions.length - 1) {
/* 1351 */           if (fractions[i] != 1.0D) {
/* 1352 */             c0 = colors[i];
/* 1353 */             c1 = colors[i];
/*      */           } else {
/*      */             break;
/*      */           } 
/*      */         } else {
/* 1358 */           c0 = colors[i];
/* 1359 */           c1 = colors[i + 1];
/*      */         } 
/*      */         
/* 1362 */         sout.g();
/* 1363 */         sout.a("FunctionType");
/* 1364 */         sout.a(2);
/* 1365 */         sout.k();
/*      */         
/* 1367 */         sout.a("Domain");
/* 1368 */         sout.i();
/* 1369 */         sout.a(0.0D);
/* 1370 */         sout.a(1.0D);
/* 1371 */         sout.j();
/* 1372 */         sout.k();
/*      */         
/* 1374 */         sout.a("N");
/* 1375 */         sout.a(1.0D);
/* 1376 */         sout.k();
/*      */         
/* 1378 */         sout.a("C0");
/* 1379 */         sout.i();
/* 1380 */         a(sout, colorType, c0);
/* 1381 */         sout.j();
/* 1382 */         sout.k();
/*      */         
/* 1384 */         sout.a("C1");
/* 1385 */         sout.i();
/* 1386 */         a(sout, colorType, c1);
/* 1387 */         sout.j();
/* 1388 */         sout.k();
/* 1389 */         sout.h(); continue;
/*      */       } 
/* 1391 */       sout.j();
/* 1392 */       sout.k();
/*      */     } 
/* 1394 */     sout.h();
/* 1395 */     sout.k();
/*      */   }
/*      */   private static void a(h sout, short colorType, jp.cssj.sakae.c.c.b color) throws IOException {
/*      */     jp.cssj.sakae.c.c.a cmyk;
/* 1399 */     switch (colorType) {
/*      */       case 3:
/* 1401 */         if (color.c() == 3) {
/* 1402 */           sout.a(((jp.cssj.sakae.c.c.c)color).a(0));
/*      */         } else {
/*      */           
/* 1405 */           sout.a(jp.cssj.sakae.e.b.a(color.d(), color.e(), color.f()));
/*      */         }  return;
/*      */       case 1:
/* 1408 */         sout.a(color.d());
/* 1409 */         sout.a(color.e());
/* 1410 */         sout.a(color.f());
/*      */         return;
/*      */       case 2:
/* 1413 */         cmyk = jp.cssj.sakae.e.b.b(color);
/* 1414 */         sout.a(cmyk.a(0));
/* 1415 */         sout.a(cmyk.a(1));
/* 1416 */         sout.a(cmyk.a(2));
/* 1417 */         sout.a(cmyk.a(3));
/*      */         return;
/*      */     } 
/* 1420 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void t() throws IOException {
/* 1426 */     r();
/* 1427 */     s();
/*      */ 
/*      */     
/* 1430 */     if (this.E != this.F) {
/* 1431 */       this.F = this.E;
/* 1432 */       this.a.a(this.E);
/* 1433 */       this.a.b("w");
/*      */     } 
/* 1435 */     if (this.A != this.B) {
/* 1436 */       this.B = this.A;
/* 1437 */       this.a.a(this.A);
/* 1438 */       this.a.b("J");
/*      */     } 
/* 1440 */     if (this.C != this.D) {
/* 1441 */       this.B = this.C;
/* 1442 */       this.a.a(this.C);
/* 1443 */       this.a.b("j");
/*      */     } 
/* 1445 */     if (!Arrays.equals(this.G, this.H)) {
/* 1446 */       this.H = this.G;
/* 1447 */       this.a.i();
/* 1448 */       if (this.G != null) {
/* 1449 */         for (int i = 0; i < this.G.length; i++) {
/* 1450 */           this.a.a(this.G[i]);
/*      */         }
/*      */       }
/* 1453 */       this.a.j();
/* 1454 */       this.a.a(0);
/* 1455 */       this.a.b("d");
/*      */     } 
/*      */ 
/*      */     
/* 1459 */     if (this.I != null && !this.I.equals(this.J)) {
/* 1460 */       String name; switch (this.I.b()) {
/*      */         case 1:
/* 1462 */           if (this.J != null && this.J.b() != 1) {
/* 1463 */             this.a.a("DeviceRGB");
/* 1464 */             this.a.b("CS");
/*      */           } 
/* 1466 */           this.a.b((jp.cssj.sakae.c.c.b)this.I);
/*      */           break;
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/* 1471 */           name = a(this.I);
/* 1472 */           if (name != null) {
/* 1473 */             this.a.a("Pattern");
/* 1474 */             this.a.b("CS");
/* 1475 */             this.a.a("Pattern", name);
/* 1476 */             this.a.a(name);
/* 1477 */             this.a.b("SCN");
/*      */           } 
/*      */           break;
/*      */         default:
/* 1481 */           throw new IllegalStateException();
/*      */       } 
/* 1483 */       this.J = this.I;
/*      */     } 
/* 1485 */     if (this.K != null && !this.K.equals(this.L)) {
/* 1486 */       String name; switch (this.K.b()) {
/*      */         case 1:
/* 1488 */           if (this.L != null && this.L.b() != 1) {
/* 1489 */             this.a.a("DeviceRGB");
/* 1490 */             this.a.b("cs");
/*      */           } 
/* 1492 */           this.a.a((jp.cssj.sakae.c.c.b)this.K);
/*      */           break;
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/* 1497 */           name = a(this.K);
/* 1498 */           if (name != null) {
/* 1499 */             this.a.a("Pattern");
/* 1500 */             this.a.b("cs");
/* 1501 */             this.a.a("Pattern", name);
/* 1502 */             this.a.a(name);
/* 1503 */             this.a.b("scn");
/*      */           } 
/*      */           break;
/*      */         default:
/* 1507 */           throw new IllegalStateException();
/*      */       } 
/* 1509 */       this.L = this.K;
/*      */     } 
/*      */ 
/*      */     
/* 1513 */     int pdfVersion = this.a.d().a().h();
/* 1514 */     boolean supportAlpha = (pdfVersion >= 1400 && pdfVersion != 1412 && pdfVersion != 1421);
/*      */ 
/*      */     
/* 1517 */     if ((supportAlpha && (this.b != this.c || this.d != this.e)) || this.f != this.g || this.h != this.i) {
/*      */       
/* 1519 */       this.c = this.b;
/* 1520 */       this.e = this.d;
/* 1521 */       this.g = this.f;
/* 1522 */       this.i = this.h;
/*      */       
/* 1524 */       Map<String, String> gs = (Map<String, String>)this.a.d().a("sfGs");
/* 1525 */       if (gs == null) {
/* 1526 */         gs = new HashMap<>();
/* 1527 */         this.a.d().a("sfGs", gs);
/*      */       } 
/* 1529 */       String key = (supportAlpha ? (this.b + "/" + this.d) : "") + "/" + this.h + "/" + this.f;
/*      */       
/* 1531 */       String name = gs.get(key);
/*      */       try {
/* 1533 */         if (name == null) {
/* 1534 */           try (g gsOut = this.a.d().c()) {
/* 1535 */             if (supportAlpha) {
/* 1536 */               gsOut.a("CA");
/* 1537 */               gsOut.a(this.b);
/* 1538 */               gsOut.a("ca");
/* 1539 */               gsOut.a(this.d);
/*      */             } 
/* 1541 */             if (this.f != jp.cssj.sakae.c.c.a.e) {
/* 1542 */               gsOut.a("OP");
/* 1543 */               gsOut.a(true);
/* 1544 */               if (this.f == jp.cssj.sakae.c.c.a.g) {
/* 1545 */                 gsOut.a("OPM");
/* 1546 */                 gsOut.a(1);
/*      */               } 
/*      */             } 
/* 1549 */             if (this.h != jp.cssj.sakae.c.c.a.e) {
/* 1550 */               gsOut.a("op");
/* 1551 */               gsOut.a(true);
/* 1552 */               if (this.h == jp.cssj.sakae.c.c.a.g) {
/* 1553 */                 gsOut.a("opm");
/* 1554 */                 gsOut.a(1);
/*      */               } 
/*      */             } 
/* 1557 */             name = gsOut.a();
/* 1558 */             gs.put(key, name);
/*      */           } 
/*      */         }
/* 1561 */         this.a.a("ExtGState", name);
/* 1562 */         this.a.a(name);
/* 1563 */         this.a.b("gs");
/* 1564 */       } catch (IOException iOException) {
/* 1565 */         throw new jp.cssj.sakae.c.c(iOException);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void q() throws IOException {
/* 1576 */     if (this.m.isEmpty()) {
/*      */       return;
/*      */     }
/* 1579 */     a state = this.m.get(this.m.size() - 1);
/* 1580 */     if (state.a == null) {
/* 1581 */       v();
/* 1582 */       state.a = new d(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void u() throws IOException {
/* 1592 */     a state = this.m.get(this.m.size() - 1);
/* 1593 */     if (state.a != null) {
/* 1594 */       w();
/* 1595 */       state.a.a(this);
/* 1596 */       state.a = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void v() throws IOException {
/* 1601 */     this.Q++;
/* 1602 */     if (this.R == 1412 && 
/* 1603 */       this.Q > 28) {
/* 1604 */       throw new IllegalStateException("PDF/A-1ではグラフィックステートを28以上入れ子にできません。");
/*      */     }
/*      */     
/* 1607 */     this.a.b("q");
/*      */   }
/*      */   
/*      */   private void w() throws IOException {
/* 1611 */     this.Q--;
/* 1612 */     this.a.b("Q");
/*      */   }
/*      */   
/*      */   protected void a(Rectangle2D r) throws IOException {
/* 1616 */     this.a.b(r.getX(), r.getY(), r.getWidth(), r.getHeight());
/* 1617 */     this.a.b("re");
/*      */   }
/*      */   
/*      */   protected boolean a(PathIterator i) throws IOException {
/* 1621 */     double[] cord = new double[6];
/* 1622 */     double sx = 0.0D, sy = 0.0D;
/* 1623 */     while (!i.isDone()) {
/* 1624 */       double cx, cy, ex, ey, x1, y1, x2, y2; int type = i.currentSegment(cord);
/* 1625 */       switch (type) {
/*      */         case 0:
/* 1627 */           this.a.a(sx = cord[0], sy = cord[1]);
/* 1628 */           this.a.b("m");
/*      */         
/*      */         case 1:
/* 1631 */           this.a.a(sx = cord[0], sy = cord[1]);
/* 1632 */           this.a.b("l");
/*      */         
/*      */         case 2:
/* 1635 */           cx = cord[0];
/* 1636 */           cy = cord[1];
/* 1637 */           ex = cord[2];
/* 1638 */           ey = cord[3];
/* 1639 */           x1 = (sx + 2.0D * cx) / 3.0D;
/* 1640 */           y1 = (sy + 2.0D * cy) / 3.0D;
/* 1641 */           x2 = (ex + 2.0D * cx) / 3.0D;
/* 1642 */           y2 = (ey + 2.0D * cy) / 3.0D;
/* 1643 */           this.a.a(x1, y1);
/* 1644 */           this.a.a(x2, y2);
/* 1645 */           this.a.a(sx = ex, sy = ey);
/* 1646 */           this.a.b("c");
/*      */         
/*      */         case 3:
/* 1649 */           this.a.a(cord[0], cord[1]);
/* 1650 */           this.a.a(cord[2], cord[3]);
/* 1651 */           this.a.a(sx = cord[4], sy = cord[5]);
/* 1652 */           this.a.b("c");
/*      */         
/*      */         case 4:
/* 1655 */           i.next();
/* 1656 */           if (i.isDone()) {
/* 1657 */             return true;
/*      */           }
/* 1659 */           this.a.b("h");
/*      */           continue;
/*      */       } 
/* 1662 */       i.next();
/*      */     } 
/* 1664 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */