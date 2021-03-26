/*     */ package org.apache.xmlgraphics.java2d.color;
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
/*     */ public class ICCColorSpaceWithIntent
/*     */   extends ICC_ColorSpace
/*     */   implements ColorSpaceOrigin
/*     */ {
/*     */   private static final long serialVersionUID = -3338065900662625221L;
/*  37 */   static final ColorSpace SRGB = ColorSpace.getInstance(1000);
/*     */ 
/*     */ 
/*     */   
/*     */   private RenderingIntent intent;
/*     */ 
/*     */ 
/*     */   
/*     */   private String profileName;
/*     */ 
/*     */   
/*     */   private String profileURI;
/*     */ 
/*     */ 
/*     */   
/*     */   public ICCColorSpaceWithIntent(ICC_Profile p, RenderingIntent intent, String profileName, String profileURI) {
/*  53 */     super(p);
/*     */     
/*  55 */     this.intent = intent;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (intent != RenderingIntent.AUTO) {
/*  61 */       byte[] hdr = p.getData(1751474532);
/*  62 */       hdr[64] = (byte)intent.getIntegerValue();
/*     */     } 
/*     */     
/*  65 */     this.profileName = profileName;
/*  66 */     this.profileURI = profileURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] intendedToRGB(float[] values) {
/*  77 */     switch (this.intent) {
/*     */       case ABSOLUTE_COLORIMETRIC:
/*  79 */         return absoluteColorimetricToRGB(values);
/*     */       case PERCEPTUAL:
/*     */       case AUTO:
/*  82 */         return perceptualToRGB(values);
/*     */       case RELATIVE_COLORIMETRIC:
/*  84 */         return relativeColorimetricToRGB(values);
/*     */       case SATURATION:
/*  86 */         return saturationToRGB(values);
/*     */     } 
/*  88 */     throw new RuntimeException("invalid intent:" + this.intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] perceptualToRGB(float[] values) {
/*  99 */     return toRGB(values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] relativeColorimetricToRGB(float[] values) {
/* 109 */     float[] ciexyz = toCIEXYZ(values);
/* 110 */     return SRGB.fromCIEXYZ(ciexyz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] absoluteColorimetricToRGB(float[] values) {
/* 120 */     return perceptualToRGB(values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] saturationToRGB(float[] values) {
/* 130 */     return perceptualToRGB(values);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProfileName() {
/* 135 */     return this.profileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProfileURI() {
/* 140 */     return this.profileURI;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/ICCColorSpaceWithIntent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */