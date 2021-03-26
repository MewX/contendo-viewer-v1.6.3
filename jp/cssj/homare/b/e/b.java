/*    */ package jp.cssj.homare.b.e;
/*    */ 
/*    */ import jp.cssj.homare.b.a.c.B;
/*    */ import jp.cssj.homare.b.f.e;
/*    */ import jp.cssj.sakae.c.c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */ {
/*    */   public B a;
/*    */   public a b;
/*    */   public a c;
/*    */   
/*    */   public b(B frame) {
/* 20 */     this(frame, new a(), new a());
/*    */   }
/*    */   
/*    */   protected b(B frame, a margin, a padding) {
/* 24 */     this.a = frame;
/* 25 */     this.b = margin;
/* 26 */     this.c = padding;
/*    */   }
/*    */   
/*    */   public final double a() {
/* 30 */     return this.b.a + (this.a.c.a()).m + this.c.a;
/*    */   }
/*    */   
/*    */   public final double b() {
/* 34 */     return this.b.d + (this.a.c.d()).m + this.c.d;
/*    */   }
/*    */   
/*    */   public final double c() {
/* 38 */     return this.c.c + (this.a.c.c()).m + this.b.c;
/*    */   }
/*    */   
/*    */   public final double d() {
/* 42 */     return this.c.b + (this.a.c.b()).m + this.b.b;
/*    */   }
/*    */   
/*    */   public final double e() {
/* 46 */     return a() + c();
/*    */   }
/*    */   
/*    */   public final double f() {
/* 50 */     return b() + d();
/*    */   }
/*    */   
/*    */   public final double g() {
/* 54 */     return (this.a.c.a()).m + this.c.a + (this.a.c.c()).m + this.c.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public final double h() {
/* 59 */     return (this.a.c.d()).m + this.c.d + (this.a.c.b()).m + this.c.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean i() {
/* 64 */     return this.a.a();
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.sakae.c.b gc, double x, double y, double width, double height) throws c {
/* 68 */     if (!d && e.a(x)) throw new AssertionError("Undefined x"); 
/* 69 */     if (!d && e.a(y)) throw new AssertionError("Undefined y"); 
/* 70 */     if (!d && e.a(width)) throw new AssertionError("Undefined width"); 
/* 71 */     if (!d && e.a(height)) throw new AssertionError("Undefined height"); 
/* 72 */     x += this.b.d;
/* 73 */     y += this.b.a;
/* 74 */     width -= this.b.a();
/* 75 */     height -= this.b.b();
/* 76 */     double pbLeft = (this.a.c.d()).m;
/* 77 */     double pbTop = (this.a.c.a()).m;
/* 78 */     double pbRight = (this.a.c.b()).m;
/* 79 */     double pbBottom = (this.a.c.c()).m;
/* 80 */     this.a.d.a(gc, x, y, width, height, pbLeft, pbTop, pbRight, pbBottom, this.a.c);
/* 81 */     this.a.c.a(gc, x, y, width, height);
/*    */   }
/*    */   
/*    */   public b a(boolean top, boolean right, boolean bottom, boolean left) {
/* 85 */     return new b(this.a.a(top, right, bottom, left), this.b
/* 86 */         .a(top, right, bottom, left), this.c.a(top, right, bottom, left));
/*    */   }
/*    */   
/*    */   public String toString() {
/* 90 */     return super.toString() + "[frame=" + this.a + ",margin=" + this.b + ",padding=" + this.c + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/e/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */