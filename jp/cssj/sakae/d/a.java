/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.geom.Dimension2D;
/*    */ 
/*    */ public class a extends Dimension2D {
/*    */   protected double a;
/*    */   
/*    */   public a(double width, double height) {
/*  9 */     setSize(width, height);
/*    */   }
/*    */   protected double b;
/*    */   public double getHeight() {
/* 13 */     return this.b;
/*    */   }
/*    */   
/*    */   public double getWidth() {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setSize(double width, double height) {
/* 21 */     this.a = width;
/* 22 */     this.b = height;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 26 */     return super.toString() + "[" + this.a + "," + this.b + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */