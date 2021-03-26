/*    */ package org.apache.batik.ext.awt.image;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PointLight
/*    */   extends AbstractLight
/*    */ {
/*    */   private double lightX;
/*    */   private double lightY;
/*    */   private double lightZ;
/*    */   
/*    */   public double getLightX() {
/* 39 */     return this.lightX;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getLightY() {
/* 46 */     return this.lightY;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getLightZ() {
/* 53 */     return this.lightZ;
/*    */   }
/*    */ 
/*    */   
/*    */   public PointLight(double lightX, double lightY, double lightZ, Color lightColor) {
/* 58 */     super(lightColor);
/* 59 */     this.lightX = lightX;
/* 60 */     this.lightY = lightY;
/* 61 */     this.lightZ = lightZ;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isConstant() {
/* 68 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void getLight(double x, double y, double z, double[] L) {
/* 82 */     double L0 = this.lightX - x;
/* 83 */     double L1 = this.lightY - y;
/* 84 */     double L2 = this.lightZ - z;
/*    */     
/* 86 */     double norm = Math.sqrt(L0 * L0 + L1 * L1 + L2 * L2);
/*    */     
/* 88 */     if (norm > 0.0D) {
/* 89 */       double invNorm = 1.0D / norm;
/* 90 */       L0 *= invNorm;
/* 91 */       L1 *= invNorm;
/* 92 */       L2 *= invNorm;
/*    */     } 
/*    */ 
/*    */     
/* 96 */     L[0] = L0;
/* 97 */     L[1] = L1;
/* 98 */     L[2] = L2;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/PointLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */