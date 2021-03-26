/*    */ package jp.cssj.sakae.c.c;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ 
/*    */ 
/*    */ public class i
/*    */   implements e
/*    */ {
/*    */   protected final double a;
/*    */   protected final double b;
/*    */   protected final double c;
/*    */   protected final double d;
/*    */   
/*    */   public i(double cx, double cy, double radius, double fx, double fy, double[] fractions, b[] colors, AffineTransform transform) {
/* 15 */     this.a = cx;
/* 16 */     this.b = cy;
/* 17 */     this.c = radius;
/* 18 */     this.d = fx;
/* 19 */     this.e = fy;
/* 20 */     this.f = colors;
/* 21 */     this.g = fractions;
/* 22 */     this.h = transform;
/*    */   }
/*    */   protected final double e; protected final b[] f; protected final double[] g; protected final AffineTransform h;
/*    */   public short b() {
/* 26 */     return 4;
/*    */   }
/*    */   
/*    */   public double a() {
/* 30 */     return this.a;
/*    */   }
/*    */   
/*    */   public double c() {
/* 34 */     return this.b;
/*    */   }
/*    */   
/*    */   public double d() {
/* 38 */     return this.c;
/*    */   }
/*    */   
/*    */   public double e() {
/* 42 */     return this.d;
/*    */   }
/*    */   
/*    */   public double f() {
/* 46 */     return this.e;
/*    */   }
/*    */   
/*    */   public b[] g() {
/* 50 */     return this.f;
/*    */   }
/*    */   
/*    */   public double[] h() {
/* 54 */     return this.g;
/*    */   }
/*    */   
/*    */   public AffineTransform i() {
/* 58 */     return this.h;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */