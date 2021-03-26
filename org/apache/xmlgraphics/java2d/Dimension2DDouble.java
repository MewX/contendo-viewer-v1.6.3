/*    */ package org.apache.xmlgraphics.java2d;
/*    */ 
/*    */ import java.awt.geom.Dimension2D;
/*    */ import java.io.Serializable;
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
/*    */ public class Dimension2DDouble
/*    */   extends Dimension2D
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7909028357685520189L;
/*    */   private double width;
/*    */   private double height;
/*    */   
/*    */   public Dimension2DDouble() {
/* 39 */     this.width = 0.0D;
/* 40 */     this.height = 0.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension2DDouble(double width, double height) {
/* 49 */     this.width = width;
/* 50 */     this.height = height;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getWidth() {
/* 55 */     return this.width;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getHeight() {
/* 60 */     return this.height;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSize(double w, double h) {
/* 65 */     this.width = w;
/* 66 */     this.height = h;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 71 */     if (this == obj) {
/* 72 */       return true;
/*    */     }
/* 74 */     if (obj instanceof Dimension2DDouble) {
/* 75 */       Dimension2DDouble other = (Dimension2DDouble)obj;
/* 76 */       if (Double.doubleToLongBits(this.height) != Double.doubleToLongBits(other.height)) {
/* 77 */         return false;
/*    */       }
/* 79 */       if (Double.doubleToLongBits(this.width) != Double.doubleToLongBits(other.width)) {
/* 80 */         return false;
/*    */       }
/* 82 */       return true;
/*    */     } 
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 90 */     double sum = this.width + this.height;
/* 91 */     return (int)(sum * (sum + 1.0D) / 2.0D + this.width);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     return getClass().getName() + "[width=" + this.width + ",height=" + this.height + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/Dimension2DDouble.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */