/*    */ package jp.cssj.homare.impl.a.b;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */   implements b
/*    */ {
/*    */   protected final double a;
/*    */   protected final b b;
/*    */   
/*    */   public h(jp.cssj.sakae.c.a.h fontStyle, b color) {
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
/* 34 */     return "■";
/*    */   }
/*    */   
/*    */   public void a(b gc) throws c {
/* 38 */     gc.d();
/*    */     
/* 40 */     gc.b(this.b);
/*    */     
/* 42 */     double d = this.a * 0.35D;
/* 43 */     gc.b(new Rectangle2D.Double(this.a / 2.0D - d / 2.0D, this.a * 0.2D + this.a / 2.0D - d / 2.0D, d, d));
/* 44 */     gc.e();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/b/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */