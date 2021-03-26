/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ShadingContext
/*     */ {
/*     */   private float[] background;
/*     */   private int rgbBackground;
/*     */   private final PDShading shading;
/*     */   private ColorModel outputColorModel;
/*     */   private PDColorSpace shadingColorSpace;
/*     */   
/*     */   public ShadingContext(PDShading shading, ColorModel cm, AffineTransform xform, Matrix matrix) throws IOException {
/*  56 */     this.shading = shading;
/*  57 */     this.shadingColorSpace = shading.getColorSpace();
/*     */ 
/*     */     
/*  60 */     ColorSpace outputCS = ColorSpace.getInstance(1000);
/*  61 */     this.outputColorModel = new ComponentColorModel(outputCS, true, false, 3, 0);
/*     */ 
/*     */ 
/*     */     
/*  65 */     COSArray bg = shading.getBackground();
/*  66 */     if (bg != null) {
/*     */       
/*  68 */       this.background = bg.toFloatArray();
/*  69 */       this.rgbBackground = convertToRGB(this.background);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   PDColorSpace getShadingColorSpace() {
/*  75 */     return this.shadingColorSpace;
/*     */   }
/*     */ 
/*     */   
/*     */   PDShading getShading() {
/*  80 */     return this.shading;
/*     */   }
/*     */ 
/*     */   
/*     */   float[] getBackground() {
/*  85 */     return this.background;
/*     */   }
/*     */ 
/*     */   
/*     */   int getRgbBackground() {
/*  90 */     return this.rgbBackground;
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
/*     */   final int convertToRGB(float[] values) throws IOException {
/* 105 */     float[] rgbValues = this.shadingColorSpace.toRGB(values);
/* 106 */     int normRGBValues = (int)(rgbValues[0] * 255.0F);
/* 107 */     normRGBValues |= (int)(rgbValues[1] * 255.0F) << 8;
/* 108 */     normRGBValues |= (int)(rgbValues[2] * 255.0F) << 16;
/*     */     
/* 110 */     return normRGBValues;
/*     */   }
/*     */ 
/*     */   
/*     */   ColorModel getColorModel() {
/* 115 */     return this.outputColorModel;
/*     */   }
/*     */ 
/*     */   
/*     */   void dispose() {
/* 120 */     this.outputColorModel = null;
/* 121 */     this.shadingColorSpace = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/ShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */