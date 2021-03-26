/*     */ package org.apache.xmlgraphics.image.codec.png;
/*     */ 
/*     */ import org.apache.xmlgraphics.image.codec.util.ImageDecodeParam;
/*     */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   private static final long serialVersionUID = 3957265194926624623L;
/*     */   private boolean suppressAlpha;
/*     */   private boolean expandPalette;
/*     */   private boolean output8BitGray;
/*     */   
/*     */   public boolean getSuppressAlpha() {
/*  95 */     return this.suppressAlpha;
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
/* 106 */     this.suppressAlpha = suppressAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandPalette() {
/* 116 */     return this.expandPalette;
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
/* 130 */     this.expandPalette = expandPalette;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getOutput8BitGray() {
/* 139 */     return this.output8BitGray;
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
/* 158 */     this.output8BitGray = output8BitGray;
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
/* 178 */     return this.performGammaCorrection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPerformGammaCorrection(boolean performGammaCorrection) {
/* 185 */     this.performGammaCorrection = performGammaCorrection;
/*     */   }
/*     */   
/* 188 */   private float userExponent = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUserExponent() {
/* 195 */     return this.userExponent;
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
/* 235 */     if (userExponent <= 0.0F) {
/* 236 */       throw new IllegalArgumentException(PropertyUtil.getString("PNGDecodeParam0"));
/*     */     }
/* 238 */     this.userExponent = userExponent;
/*     */   }
/*     */   
/* 241 */   private float displayExponent = 2.2F;
/*     */   
/*     */   private boolean expandGrayAlpha;
/*     */   private boolean generateEncodeParam;
/*     */   private PNGEncodeParam encodeParam;
/*     */   
/*     */   public float getDisplayExponent() {
/* 248 */     return this.displayExponent;
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
/* 284 */     if (displayExponent <= 0.0F) {
/* 285 */       throw new IllegalArgumentException(PropertyUtil.getString("PNGDecodeParam1"));
/*     */     }
/* 287 */     this.displayExponent = displayExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandGrayAlpha() {
/* 296 */     return this.expandGrayAlpha;
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
/* 311 */     this.expandGrayAlpha = expandGrayAlpha;
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
/*     */   public boolean getGenerateEncodeParam() {
/* 324 */     return this.generateEncodeParam;
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
/* 336 */     this.generateEncodeParam = generateEncodeParam;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNGEncodeParam getEncodeParam() {
/* 346 */     return this.encodeParam;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncodeParam(PNGEncodeParam encodeParam) {
/* 355 */     this.encodeParam = encodeParam;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGDecodeParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */