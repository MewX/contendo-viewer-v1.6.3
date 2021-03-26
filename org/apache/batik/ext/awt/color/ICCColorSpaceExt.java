/*     */ package org.apache.batik.ext.awt.color;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
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
/*     */ public class ICCColorSpaceExt
/*     */   extends ICC_ColorSpace
/*     */ {
/*     */   public static final int PERCEPTUAL = 0;
/*     */   public static final int RELATIVE_COLORIMETRIC = 1;
/*     */   public static final int ABSOLUTE_COLORIMETRIC = 2;
/*     */   public static final int SATURATION = 3;
/*     */   public static final int AUTO = 4;
/*  42 */   static final ColorSpace sRGB = ColorSpace.getInstance(1000);
/*     */   int intent;
/*     */   
/*     */   public ICCColorSpaceExt(ICC_Profile p, int intent) {
/*  46 */     super(p);
/*     */     
/*  48 */     this.intent = intent;
/*  49 */     switch (intent) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */         break;
/*     */       default:
/*  57 */         throw new IllegalArgumentException();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     if (intent != 4) {
/*  64 */       byte[] hdr = p.getData(1751474532);
/*  65 */       hdr[64] = (byte)intent;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] intendedToRGB(float[] values) {
/*  75 */     switch (this.intent) {
/*     */       case 2:
/*  77 */         return absoluteColorimetricToRGB(values);
/*     */       case 0:
/*     */       case 4:
/*  80 */         return perceptualToRGB(values);
/*     */       case 1:
/*  82 */         return relativeColorimetricToRGB(values);
/*     */       case 3:
/*  84 */         return saturationToRGB(values);
/*     */     } 
/*  86 */     throw new RuntimeException("invalid intent:" + this.intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] perceptualToRGB(float[] values) {
/*  95 */     return toRGB(values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] relativeColorimetricToRGB(float[] values) {
/* 103 */     float[] ciexyz = toCIEXYZ(values);
/* 104 */     return sRGB.fromCIEXYZ(ciexyz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] absoluteColorimetricToRGB(float[] values) {
/* 112 */     return perceptualToRGB(values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] saturationToRGB(float[] values) {
/* 120 */     return perceptualToRGB(values);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/color/ICCColorSpaceExt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */