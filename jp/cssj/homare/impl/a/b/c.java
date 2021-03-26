/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import jp.cssj.sakae.c.a.h;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   implements b
/*    */ {
/*    */   private final double a;
/*    */   private final b b;
/*    */   
/*    */   public c(h fontStyle, b color) {
/* 21 */     this.a = fontStyle.e();
/* 22 */     this.b = color;
/*    */   }
/*    */   
/*    */   public double a() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public double b() {
/* 30 */     return this.a;
/*    */   }
/*    */   
/*    */   public String c() {
/* 34 */     return "○";
/*    */   }
/*    */   
/*    */   public void a(b gc) throws jp.cssj.sakae.c.c {
/* 38 */     gc.d();
/*    */     
/* 40 */     gc.b(this.b);
/* 41 */     gc.a(this.a / 24.0D);
/* 42 */     gc.a(b.t);
/*    */     
/* 44 */     double d = this.a * 0.35D;
/* 45 */     gc.c(new Ellipse2D.Double(this.a / 2.0D - d / 2.0D, this.a * 0.2D + this.a / 2.0D - d / 2.0D, d, d));
/*    */     
/* 47 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */