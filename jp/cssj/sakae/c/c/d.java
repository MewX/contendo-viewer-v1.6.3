/*    */ package jp.cssj.sakae.c.c;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ 
/*    */ 
/*    */ public class d
/*    */   implements e
/*    */ {
/*    */   protected final double a;
/*    */   protected final double b;
/*    */   protected final double c;
/*    */   
/*    */   public d(double x1, double y1, double x2, double y2, double[] fractions, b[] colors, AffineTransform transform) {
/* 14 */     this.a = x1;
/* 15 */     this.b = y1;
/* 16 */     this.c = x2;
/* 17 */     this.d = y2;
/* 18 */     this.e = colors;
/* 19 */     this.f = fractions;
/* 20 */     this.g = transform;
/* 21 */     if (colors == null) {
/* 22 */       throw new NullPointerException("Colors cannnot be null.");
/*    */     }
/* 24 */     if (fractions == null) {
/* 25 */       throw new NullPointerException("Fractions cannnot be null.");
/*    */     }
/* 27 */     if (transform == null)
/* 28 */       throw new NullPointerException("Transform cannnot be null."); 
/*    */   }
/*    */   protected final double d; protected final b[] e; protected final double[] f; protected final AffineTransform g;
/*    */   
/*    */   public short b() {
/* 33 */     return 3;
/*    */   }
/*    */   
/*    */   public double a() {
/* 37 */     return this.a;
/*    */   }
/*    */   
/*    */   public double c() {
/* 41 */     return this.b;
/*    */   }
/*    */   
/*    */   public double d() {
/* 45 */     return this.c;
/*    */   }
/*    */   
/*    */   public double e() {
/* 49 */     return this.d;
/*    */   }
/*    */   
/*    */   public b[] f() {
/* 53 */     return this.e;
/*    */   }
/*    */   
/*    */   public double[] g() {
/* 57 */     return this.f;
/*    */   }
/*    */   
/*    */   public AffineTransform h() {
/* 61 */     return this.g;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */