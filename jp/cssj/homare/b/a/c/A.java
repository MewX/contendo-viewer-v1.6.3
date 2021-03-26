/*     */ package jp.cssj.homare.b.a.c;
/*     */ 
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ 
/*     */ public class A
/*     */ {
/*     */   private final j b;
/*     */   private final j c;
/*     */   private final j d;
/*     */   private final j e;
/*  12 */   public static final A a = new A(j.k, j.k, j.k, j.k, a.a, a.a, a.a, a.a);
/*     */   private final a f;
/*     */   private final a g;
/*     */   private final a h;
/*     */   private final a i;
/*     */   
/*     */   public static class a {
/*  19 */     public static final a a = new a(0.0D, 0.0D);
/*     */     public final double b;
/*     */     public final double c;
/*     */     
/*     */     public static a a(double hr, double vr) {
/*  24 */       if (hr == 0.0D && vr == 0.0D) {
/*  25 */         return a;
/*     */       }
/*  27 */       return new a(hr, vr);
/*     */     }
/*     */     
/*     */     public a(double hr, double vr) {
/*  31 */       this.b = hr;
/*  32 */       this.c = vr;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/*  36 */       a r = (a)o;
/*  37 */       return (r.b == this.b && r.c == this.c);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static A a(j top, j right, j bottom, j left, a topLeft, a topRight, a bottomLeft, a bottomRight) {
/*  45 */     if (top.n == 0 && right.n == 0 && bottom.n == 0 && left.n == 0 && topLeft == a.a && topRight == a.a && bottomLeft == a.a && bottomRight == a.a)
/*     */     {
/*     */       
/*  48 */       return a;
/*     */     }
/*  50 */     return new A(top, right, bottom, left, topLeft, topRight, bottomLeft, bottomRight);
/*     */   }
/*     */ 
/*     */   
/*     */   private A(j top, j right, j bottom, j left, a topLeft, a topRight, a bottomLeft, a bottomRight) {
/*  55 */     this.b = top;
/*  56 */     this.c = right;
/*  57 */     this.d = bottom;
/*  58 */     this.e = left;
/*  59 */     this.f = topLeft;
/*  60 */     this.g = topRight;
/*  61 */     this.h = bottomLeft;
/*  62 */     this.i = bottomRight;
/*     */   }
/*     */   
/*     */   public j a() {
/*  66 */     return this.b;
/*     */   }
/*     */   
/*     */   public j b() {
/*  70 */     return this.c;
/*     */   }
/*     */   
/*     */   public j c() {
/*  74 */     return this.d;
/*     */   }
/*     */   
/*     */   public j d() {
/*  78 */     return this.e;
/*     */   }
/*     */   
/*     */   public a e() {
/*  82 */     return this.f;
/*     */   }
/*     */   
/*     */   public a f() {
/*  86 */     return this.g;
/*     */   }
/*     */   
/*     */   public a g() {
/*  90 */     return this.h;
/*     */   }
/*     */   
/*     */   public a h() {
/*  94 */     return this.i;
/*     */   }
/*     */   
/*     */   public void a(b gc, double x, double y, double width, double height) throws c {
/*  98 */     jp.cssj.homare.b.f.a.a.i(gc, this, x, y, width, height);
/*     */   }
/*     */   
/*     */   public double i() {
/* 102 */     return (d()).m + (b()).m;
/*     */   }
/*     */   
/*     */   public double j() {
/* 106 */     return (a()).m + (c()).m;
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 110 */     return (a().a() || b().a() || c().a() || 
/* 111 */       d().a());
/*     */   }
/*     */   
/*     */   public boolean l() {
/* 115 */     return (a().b() && b().b() && c().b() && 
/* 116 */       d().b());
/*     */   }
/*     */   
/*     */   public boolean m() {
/* 120 */     return (this.f != a.a || this.g != a.a || this.h != a.a || this.i != a.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public A a(boolean top, boolean right, boolean bottom, boolean left) {
/* 125 */     A newBorder = a(top ? a() : j.k, right ? 
/* 126 */         b() : j.k, bottom ? c() : j.k, left ? 
/* 127 */         d() : j.k, (top && left) ? e() : a.a, (top && right) ? 
/* 128 */         f() : a.a, (bottom && left) ? 
/* 129 */         g() : a.a, (bottom && right) ? 
/* 130 */         h() : a.a);
/* 131 */     return newBorder;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 135 */     return "[top=" + a() + ",left=" + d() + ",bottom=" + c() + ",right=" + 
/* 136 */       b() + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */