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
/*     */ public class DistantLight
/*     */   extends AbstractLight
/*     */ {
/*     */   private double azimuth;
/*     */   private double elevation;
/*     */   private double Lx;
/*     */   private double Ly;
/*     */   private double Lz;
/*     */   
/*     */   public double getAzimuth() {
/*  52 */     return this.azimuth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getElevation() {
/*  59 */     return this.elevation;
/*     */   }
/*     */   
/*     */   public DistantLight(double azimuth, double elevation, Color color) {
/*  63 */     super(color);
/*     */     
/*  65 */     this.azimuth = azimuth;
/*  66 */     this.elevation = elevation;
/*     */     
/*  68 */     this.Lx = Math.cos(Math.toRadians(azimuth)) * Math.cos(Math.toRadians(elevation));
/*  69 */     this.Ly = Math.sin(Math.toRadians(azimuth)) * Math.cos(Math.toRadians(elevation));
/*  70 */     this.Lz = Math.sin(Math.toRadians(elevation));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConstant() {
/*  77 */     return true;
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
/*     */   public void getLight(double x, double y, double z, double[] L) {
/*  89 */     L[0] = this.Lx;
/*  90 */     L[1] = this.Ly;
/*  91 */     L[2] = this.Lz;
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
/* 114 */     double[][] ret = lightRow;
/*     */     
/* 116 */     if (ret == null) {
/*     */ 
/*     */       
/* 119 */       ret = new double[width][];
/*     */       
/* 121 */       double[] CL = new double[3];
/* 122 */       CL[0] = this.Lx;
/* 123 */       CL[1] = this.Ly;
/* 124 */       CL[2] = this.Lz;
/*     */       
/* 126 */       for (int i = 0; i < width; i++) {
/* 127 */         ret[i] = CL;
/*     */       }
/*     */     } else {
/* 130 */       double lx = this.Lx;
/* 131 */       double ly = this.Ly;
/* 132 */       double lz = this.Lz;
/*     */       
/* 134 */       for (int i = 0; i < width; i++) {
/* 135 */         ret[i][0] = lx;
/* 136 */         ret[i][1] = ly;
/* 137 */         ret[i][2] = lz;
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/DistantLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */