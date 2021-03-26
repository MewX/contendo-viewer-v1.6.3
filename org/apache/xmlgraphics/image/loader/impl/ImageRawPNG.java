/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.InputStream;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageRawPNG
/*     */   extends ImageRawStream
/*     */ {
/*     */   private ColorModel cm;
/*     */   private ICC_Profile iccProfile;
/*     */   private int bitDepth;
/*     */   private boolean isTransparent;
/*     */   private int grayTransparentAlpha;
/*     */   private int redTransparentAlpha;
/*     */   private int greenTransparentAlpha;
/*     */   private int blueTransparentAlpha;
/*  43 */   private int renderingIntent = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageRawPNG(ImageInfo info, InputStream in, ColorModel colorModel, int bitDepth, ICC_Profile iccProfile) {
/*  54 */     super(info, ImageFlavor.RAW_PNG, in);
/*  55 */     this.iccProfile = iccProfile;
/*  56 */     this.cm = colorModel;
/*  57 */     this.bitDepth = bitDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBitDepth() {
/*  65 */     return this.bitDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ICC_Profile getICCProfile() {
/*  73 */     return this.iccProfile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/*  81 */     return this.cm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorSpace getColorSpace() {
/*  89 */     return this.cm.getColorSpace();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setGrayTransparentAlpha(int gray) {
/*  97 */     this.isTransparent = true;
/*  98 */     this.grayTransparentAlpha = gray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setRGBTransparentAlpha(int red, int green, int blue) {
/* 108 */     this.isTransparent = true;
/* 109 */     this.redTransparentAlpha = red;
/* 110 */     this.greenTransparentAlpha = green;
/* 111 */     this.blueTransparentAlpha = blue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTransparent() {
/* 118 */     this.isTransparent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransparent() {
/* 126 */     return this.isTransparent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getTransparentColor() {
/* 134 */     Color color = null;
/* 135 */     if (!this.isTransparent) {
/* 136 */       return color;
/*     */     }
/* 138 */     if (this.cm.getNumColorComponents() == 3) {
/* 139 */       color = new Color(this.redTransparentAlpha, this.greenTransparentAlpha, this.blueTransparentAlpha);
/*     */     } else {
/* 141 */       color = new Color(this.grayTransparentAlpha, 0, 0);
/*     */     } 
/* 143 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingIntent(int intent) {
/* 151 */     this.renderingIntent = intent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderingIntent() {
/* 159 */     return this.renderingIntent;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageRawPNG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */