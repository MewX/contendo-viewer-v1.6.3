/*     */ package org.apache.batik.ext.awt.image.codec.png;
/*     */ 
/*     */ import org.apache.batik.ext.awt.image.codec.util.ImageDecodeParam;
/*     */ import org.apache.batik.ext.awt.image.codec.util.PropertyUtil;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNGDecodeParam
/*     */   implements ImageDecodeParam
/*     */ {
/*     */   private boolean suppressAlpha = false;
/*     */   
/*     */   public boolean getSuppressAlpha() {
/*  92 */     return this.suppressAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuppressAlpha(boolean suppressAlpha) {
/* 103 */     this.suppressAlpha = suppressAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean expandPalette = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandPalette() {
/* 113 */     return this.expandPalette;
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
/*     */   public void setExpandPalette(boolean expandPalette) {
/* 127 */     this.expandPalette = expandPalette;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean output8BitGray = false;
/*     */ 
/*     */   
/*     */   public boolean getOutput8BitGray() {
/* 136 */     return this.output8BitGray;
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
/*     */   public void setOutput8BitGray(boolean output8BitGray) {
/* 155 */     this.output8BitGray = output8BitGray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean performGammaCorrection = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPerformGammaCorrection() {
/* 175 */     return this.performGammaCorrection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPerformGammaCorrection(boolean performGammaCorrection) {
/* 182 */     this.performGammaCorrection = performGammaCorrection;
/*     */   }
/*     */   
/* 185 */   private float userExponent = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUserExponent() {
/* 192 */     return this.userExponent;
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
/*     */   public void setUserExponent(float userExponent) {
/* 232 */     if (userExponent <= 0.0F) {
/* 233 */       throw new IllegalArgumentException(PropertyUtil.getString("PNGDecodeParam0"));
/*     */     }
/* 235 */     this.userExponent = userExponent;
/*     */   }
/*     */   
/* 238 */   private float displayExponent = 2.2F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDisplayExponent() {
/* 245 */     return this.displayExponent;
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
/*     */   public void setDisplayExponent(float displayExponent) {
/* 281 */     if (displayExponent <= 0.0F) {
/* 282 */       throw new IllegalArgumentException(PropertyUtil.getString("PNGDecodeParam1"));
/*     */     }
/* 284 */     this.displayExponent = displayExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean expandGrayAlpha = false;
/*     */ 
/*     */   
/*     */   public boolean getExpandGrayAlpha() {
/* 293 */     return this.expandGrayAlpha;
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
/*     */   public void setExpandGrayAlpha(boolean expandGrayAlpha) {
/* 308 */     this.expandGrayAlpha = expandGrayAlpha;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean generateEncodeParam = false;
/* 313 */   private PNGEncodeParam encodeParam = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGenerateEncodeParam() {
/* 321 */     return this.generateEncodeParam;
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
/*     */   public void setGenerateEncodeParam(boolean generateEncodeParam) {
/* 333 */     this.generateEncodeParam = generateEncodeParam;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNGEncodeParam getEncodeParam() {
/* 343 */     return this.encodeParam;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncodeParam(PNGEncodeParam encodeParam) {
/* 352 */     this.encodeParam = encodeParam;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGDecodeParam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */