/*     */ package jp.cssj.sakae.c;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b.a;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   implements b
/*     */ {
/*     */   protected static class a
/*     */   {
/*     */     public final AffineTransform a;
/*     */     public final double b;
/*     */     public final double[] c;
/*     */     public final short d;
/*     */     public final short e;
/*     */     public final Object f;
/*     */     public final Object g;
/*     */     public final float h;
/*     */     public final float i;
/*     */     public final short j;
/*     */     
/*     */     public a(d gc) {
/*  33 */       this.a = new AffineTransform(gc.a);
/*  34 */       this.f = gc.f;
/*  35 */       this.g = gc.g;
/*  36 */       this.b = gc.b;
/*  37 */       this.c = gc.c;
/*  38 */       this.d = gc.d;
/*  39 */       this.e = gc.e;
/*  40 */       this.i = gc.i;
/*  41 */       this.h = gc.h;
/*  42 */       this.j = gc.j;
/*     */     }
/*     */     
/*     */     public void a(d gc) {
/*  46 */       gc.a = this.a;
/*  47 */       gc.b = this.b;
/*  48 */       gc.c = this.c;
/*  49 */       gc.d = this.d;
/*  50 */       gc.e = this.e;
/*  51 */       gc.g = this.g;
/*  52 */       gc.f = this.f;
/*  53 */       gc.h = this.h;
/*  54 */       gc.i = this.i;
/*  55 */       gc.j = this.j;
/*     */     }
/*     */   }
/*     */   
/*  59 */   protected AffineTransform a = new AffineTransform();
/*     */   
/*  61 */   protected double b = 1.0D;
/*     */   
/*  63 */   protected double[] c = b.t;
/*     */   
/*  65 */   protected short d = 0;
/*     */   
/*  67 */   protected short e = 0;
/*     */   
/*     */   protected Object f;
/*     */   
/*     */   protected Object g;
/*     */   
/*  73 */   protected float h = 1.0F; protected float i = 1.0F;
/*     */   
/*  75 */   protected short j = 0;
/*     */   
/*  77 */   protected ArrayList<a> k = new ArrayList<>();
/*     */   
/*     */   protected final e l;
/*     */   
/*     */   public d(e fm) {
/*  82 */     this.l = fm;
/*     */   }
/*     */   
/*     */   public e a() {
/*  86 */     return this.l;
/*     */   }
/*     */   
/*     */   public void d() {
/*  90 */     this.k.add(new a(this));
/*     */   }
/*     */   
/*     */   public void e() {
/*  94 */     a state = this.k.remove(this.k.size() - 1);
/*  95 */     state.a(this);
/*     */   }
/*     */   
/*     */   public void a(double lineWidth) {
/*  99 */     this.b = lineWidth;
/*     */   }
/*     */   
/*     */   public double f() {
/* 103 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(double[] linePattern) {
/* 107 */     this.c = linePattern;
/*     */   }
/*     */   
/*     */   public double[] g() {
/* 111 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(short lineJoin) {
/* 115 */     this.d = lineJoin;
/*     */   }
/*     */   
/*     */   public short h() {
/* 119 */     return this.d;
/*     */   }
/*     */   
/*     */   public void b(short lineCap) {
/* 123 */     this.e = lineCap;
/*     */   }
/*     */   
/*     */   public short i() {
/* 127 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(Object paint) throws c {
/* 131 */     this.f = paint;
/*     */   }
/*     */   
/*     */   public Object j() {
/* 135 */     return this.f;
/*     */   }
/*     */   
/*     */   public void b(Object paint) throws c {
/* 139 */     this.g = paint;
/*     */   }
/*     */   
/*     */   public Object k() {
/* 143 */     return this.g;
/*     */   }
/*     */   
/*     */   public void a(float alpha) {
/* 147 */     this.i = alpha;
/*     */   }
/*     */   
/*     */   public float l() {
/* 151 */     return this.i;
/*     */   }
/*     */   
/*     */   public void b(float alpha) {
/* 155 */     this.h = alpha;
/*     */   }
/*     */   
/*     */   public float m() {
/* 159 */     return this.h;
/*     */   }
/*     */   
/*     */   public void c(short textMode) {
/* 163 */     this.j = textMode;
/*     */   }
/*     */   
/*     */   public short n() {
/* 167 */     return this.j;
/*     */   }
/*     */   
/*     */   public void a(AffineTransform at) {
/* 171 */     this.a.concatenate(at);
/*     */   }
/*     */   
/*     */   public AffineTransform o() {
/* 175 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Shape clip) {}
/*     */ 
/*     */   
/*     */   public void p() {
/* 183 */     a state = this.k.get(this.k.size() - 1);
/* 184 */     state.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(b image) throws c {}
/*     */ 
/*     */   
/*     */   public void b(Shape shape) {}
/*     */ 
/*     */   
/*     */   public void c(Shape shape) {}
/*     */ 
/*     */   
/*     */   public void d(Shape shape) {}
/*     */ 
/*     */   
/*     */   public void a(h text, double x, double y) throws c {}
/*     */ 
/*     */   
/*     */   public static class c
/*     */     implements b
/*     */   {
/*     */     protected final double a;
/*     */     
/*     */     protected final double b;
/*     */     
/*     */     public c(double width, double height) {
/* 211 */       this.a = width;
/* 212 */       this.b = height;
/*     */     }
/*     */     
/*     */     public double a() {
/* 216 */       return this.a;
/*     */     }
/*     */     
/*     */     public double b() {
/* 220 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(b gc) throws c {}
/*     */ 
/*     */     
/*     */     public String c() {
/* 228 */       return "";
/*     */     } }
/*     */   
/*     */   public static class b extends d implements a {
/*     */     private final double m;
/*     */     private final double x;
/*     */     
/*     */     public b(e fm, double width, double height) {
/* 236 */       super(fm);
/* 237 */       this.m = width;
/* 238 */       this.x = height;
/*     */     }
/*     */     
/*     */     public b q() throws c {
/* 242 */       return new d.c(this.m, this.x);
/*     */     }
/*     */   }
/*     */   
/*     */   public a a(double width, double height) throws c {
/* 247 */     return new b(a(), width, height);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */