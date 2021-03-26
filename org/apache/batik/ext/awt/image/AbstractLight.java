/*     */ package org.apache.batik.ext.awt.image;
/*     */ 
/*     */ import java.awt.Color;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractLight
/*     */   implements Light
/*     */ {
/*     */   private double[] color;
/*     */   
/*     */   public static final double sRGBToLsRGB(double value) {
/*  34 */     if (value <= 0.003928D)
/*  35 */       return value / 12.92D; 
/*  36 */     return Math.pow((value + 0.055D) / 1.055D, 2.4D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getColor(boolean linear) {
/*  51 */     double[] ret = new double[3];
/*  52 */     if (linear) {
/*  53 */       ret[0] = sRGBToLsRGB(this.color[0]);
/*  54 */       ret[1] = sRGBToLsRGB(this.color[1]);
/*  55 */       ret[2] = sRGBToLsRGB(this.color[2]);
/*     */     } else {
/*  57 */       ret[0] = this.color[0];
/*  58 */       ret[1] = this.color[1];
/*  59 */       ret[2] = this.color[2];
/*     */     } 
/*  61 */     return ret;
/*     */   }
/*     */   
/*     */   public AbstractLight(Color color) {
/*  65 */     setColor(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color newColor) {
/*  72 */     this.color = new double[3];
/*  73 */     this.color[0] = newColor.getRed() / 255.0D;
/*  74 */     this.color[1] = newColor.getGreen() / 255.0D;
/*  75 */     this.color[2] = newColor.getBlue() / 255.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConstant() {
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][][] getLightMap(double x, double y, double dx, double dy, int width, int height, double[][][] z) {
/* 103 */     double[][][] L = new double[height][][];
/*     */     
/* 105 */     for (int i = 0; i < height; i++) {
/* 106 */       L[i] = getLightRow(x, y, dx, width, z[i], (double[][])null);
/* 107 */       y += dy;
/*     */     } 
/*     */     
/* 110 */     return L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][] getLightRow(double x, double y, double dx, int width, double[][] z, double[][] lightRow) {
/* 133 */     double[][] ret = lightRow;
/* 134 */     if (ret == null) {
/* 135 */       ret = new double[width][3];
/*     */     }
/* 137 */     for (int i = 0; i < width; i++) {
/* 138 */       getLight(x, y, z[i][3], ret[i]);
/* 139 */       x += dx;
/*     */     } 
/*     */     
/* 142 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/AbstractLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */