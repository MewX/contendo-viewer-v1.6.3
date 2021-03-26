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
/*     */ public class SpotLight
/*     */   extends AbstractLight
/*     */ {
/*     */   private double lightX;
/*     */   private double lightY;
/*     */   private double lightZ;
/*     */   private double pointAtX;
/*     */   private double pointAtY;
/*     */   private double pointAtZ;
/*     */   private double specularExponent;
/*     */   private double limitingConeAngle;
/*     */   private double limitingCos;
/*  53 */   private final double[] S = new double[3];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLightX() {
/*  59 */     return this.lightX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLightY() {
/*  66 */     return this.lightY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLightZ() {
/*  73 */     return this.lightZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getPointAtX() {
/*  80 */     return this.pointAtX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getPointAtY() {
/*  87 */     return this.pointAtY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getPointAtZ() {
/*  94 */     return this.pointAtZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSpecularExponent() {
/* 101 */     return this.specularExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getLimitingConeAngle() {
/* 108 */     return this.limitingConeAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpotLight(double lightX, double lightY, double lightZ, double pointAtX, double pointAtY, double pointAtZ, double specularExponent, double limitingConeAngle, Color lightColor) {
/* 115 */     super(lightColor);
/*     */     
/* 117 */     this.lightX = lightX;
/* 118 */     this.lightY = lightY;
/* 119 */     this.lightZ = lightZ;
/* 120 */     this.pointAtX = pointAtX;
/* 121 */     this.pointAtY = pointAtY;
/* 122 */     this.pointAtZ = pointAtZ;
/* 123 */     this.specularExponent = specularExponent;
/* 124 */     this.limitingConeAngle = limitingConeAngle;
/* 125 */     this.limitingCos = Math.cos(Math.toRadians(limitingConeAngle));
/*     */     
/* 127 */     this.S[0] = pointAtX - lightX;
/* 128 */     this.S[1] = pointAtY - lightY;
/* 129 */     this.S[2] = pointAtZ - lightZ;
/*     */     
/* 131 */     double invNorm = 1.0D / Math.sqrt(this.S[0] * this.S[0] + this.S[1] * this.S[1] + this.S[2] * this.S[2]);
/*     */     
/* 133 */     this.S[0] = this.S[0] * invNorm;
/* 134 */     this.S[1] = this.S[1] * invNorm;
/* 135 */     this.S[2] = this.S[2] * invNorm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConstant() {
/* 142 */     return false;
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
/*     */   public final double getLightBase(double x, double y, double z, double[] L) {
/* 158 */     double L0 = this.lightX - x;
/* 159 */     double L1 = this.lightY - y;
/* 160 */     double L2 = this.lightZ - z;
/*     */     
/* 162 */     double invNorm = 1.0D / Math.sqrt(L0 * L0 + L1 * L1 + L2 * L2);
/*     */     
/* 164 */     L0 *= invNorm;
/* 165 */     L1 *= invNorm;
/* 166 */     L2 *= invNorm;
/*     */     
/* 168 */     double LS = -(L0 * this.S[0] + L1 * this.S[1] + L2 * this.S[2]);
/*     */ 
/*     */     
/* 171 */     L[0] = L0;
/* 172 */     L[1] = L1;
/* 173 */     L[2] = L2;
/*     */     
/* 175 */     if (LS <= this.limitingCos) {
/* 176 */       return 0.0D;
/*     */     }
/* 178 */     double Iatt = this.limitingCos / LS;
/* 179 */     Iatt *= Iatt;
/* 180 */     Iatt *= Iatt;
/* 181 */     Iatt *= Iatt;
/* 182 */     Iatt *= Iatt;
/* 183 */     Iatt *= Iatt;
/* 184 */     Iatt *= Iatt;
/*     */     
/* 186 */     Iatt = 1.0D - Iatt;
/* 187 */     return Iatt * Math.pow(LS, this.specularExponent);
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
/*     */   public final void getLight(double x, double y, double z, double[] L) {
/* 203 */     double s = getLightBase(x, y, z, L);
/* 204 */     L[0] = L[0] * s;
/* 205 */     L[1] = L[1] * s;
/* 206 */     L[2] = L[2] * s;
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
/*     */   public final void getLight4(double x, double y, double z, double[] L) {
/* 221 */     L[3] = getLightBase(x, y, z, L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[][] getLightRow4(double x, double y, double dx, int width, double[][] z, double[][] lightRow) {
/* 228 */     double[][] ret = lightRow;
/* 229 */     if (ret == null) {
/* 230 */       ret = new double[width][4];
/*     */     }
/* 232 */     for (int i = 0; i < width; i++) {
/* 233 */       getLight4(x, y, z[i][3], ret[i]);
/* 234 */       x += dx;
/*     */     } 
/*     */     
/* 237 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/SpotLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */