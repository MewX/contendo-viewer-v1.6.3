/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFColorConverter;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFYCbCrColorConverter
/*     */   extends TIFFColorConverter
/*     */ {
/*  57 */   private float LumaRed = 0.299F;
/*  58 */   private float LumaGreen = 0.587F;
/*  59 */   private float LumaBlue = 0.114F;
/*     */   
/*  61 */   private float referenceBlackY = 0.0F;
/*  62 */   private float referenceWhiteY = 255.0F;
/*     */   
/*  64 */   private float referenceBlackCb = 128.0F;
/*  65 */   private float referenceWhiteCb = 255.0F;
/*     */   
/*  67 */   private float referenceBlackCr = 128.0F;
/*  68 */   private float referenceWhiteCr = 255.0F;
/*     */   
/*  70 */   private float codingRangeY = 255.0F;
/*  71 */   private float codingRangeCbCr = 127.0F;
/*     */   
/*     */   public TIFFYCbCrColorConverter(TIFFImageMetadata metadata) {
/*  74 */     TIFFImageMetadata tmetadata = metadata;
/*     */ 
/*     */     
/*  77 */     TIFFField f = tmetadata.getTIFFField(529);
/*  78 */     if (f != null && f.getCount() == 3) {
/*  79 */       this.LumaRed = f.getAsFloat(0);
/*  80 */       this.LumaGreen = f.getAsFloat(1);
/*  81 */       this.LumaBlue = f.getAsFloat(2);
/*     */     } 
/*     */ 
/*     */     
/*  85 */     f = tmetadata.getTIFFField(532);
/*  86 */     if (f != null && f.getCount() == 6) {
/*  87 */       this.referenceBlackY = f.getAsFloat(0);
/*  88 */       this.referenceWhiteY = f.getAsFloat(1);
/*  89 */       this.referenceBlackCb = f.getAsFloat(2);
/*  90 */       this.referenceWhiteCb = f.getAsFloat(3);
/*  91 */       this.referenceBlackCr = f.getAsFloat(4);
/*  92 */       this.referenceWhiteCr = f.getAsFloat(5);
/*     */     } 
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
/*     */   public void fromRGB(float r, float g, float b, float[] result) {
/* 110 */     float Y = this.LumaRed * r + this.LumaGreen * g + this.LumaBlue * b;
/* 111 */     float Cb = (b - Y) / (2.0F - 2.0F * this.LumaBlue);
/* 112 */     float Cr = (r - Y) / (2.0F - 2.0F * this.LumaRed);
/*     */ 
/*     */     
/* 115 */     result[0] = Y * (this.referenceWhiteY - this.referenceBlackY) / this.codingRangeY + this.referenceBlackY;
/*     */     
/* 117 */     result[1] = Cb * (this.referenceWhiteCb - this.referenceBlackCb) / this.codingRangeCbCr + this.referenceBlackCb;
/*     */     
/* 119 */     result[2] = Cr * (this.referenceWhiteCr - this.referenceBlackCr) / this.codingRangeCbCr + this.referenceBlackCr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toRGB(float x0, float x1, float x2, float[] rgb) {
/* 125 */     float Y = (x0 - this.referenceBlackY) * this.codingRangeY / (this.referenceWhiteY - this.referenceBlackY);
/*     */     
/* 127 */     float Cb = (x1 - this.referenceBlackCb) * this.codingRangeCbCr / (this.referenceWhiteCb - this.referenceBlackCb);
/*     */     
/* 129 */     float Cr = (x2 - this.referenceBlackCr) * this.codingRangeCbCr / (this.referenceWhiteCr - this.referenceBlackCr);
/*     */ 
/*     */ 
/*     */     
/* 133 */     rgb[0] = Cr * (2.0F - 2.0F * this.LumaRed) + Y;
/* 134 */     rgb[2] = Cb * (2.0F - 2.0F * this.LumaBlue) + Y;
/* 135 */     rgb[1] = (Y - this.LumaBlue * rgb[2] - this.LumaRed * rgb[0]) / this.LumaGreen;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFYCbCrColorConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */