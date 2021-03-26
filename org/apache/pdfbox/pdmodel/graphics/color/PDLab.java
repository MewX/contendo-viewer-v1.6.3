/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDLab
/*     */   extends PDCIEDictionaryBasedColorSpace
/*     */ {
/*     */   private PDColor initialColor;
/*     */   
/*     */   public PDLab() {
/*  42 */     super(COSName.LAB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDLab(COSArray lab) {
/*  51 */     super(lab);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  57 */     return COSName.LAB.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/*  66 */     int width = raster.getWidth();
/*  67 */     int height = raster.getHeight();
/*     */     
/*  69 */     BufferedImage rgbImage = new BufferedImage(width, height, 1);
/*  70 */     WritableRaster rgbRaster = rgbImage.getRaster();
/*     */     
/*  72 */     float minA = getARange().getMin();
/*  73 */     float maxA = getARange().getMax();
/*  74 */     float minB = getBRange().getMin();
/*  75 */     float maxB = getBRange().getMax();
/*     */ 
/*     */     
/*  78 */     float[] abc = new float[3];
/*  79 */     for (int y = 0; y < height; y++) {
/*     */       
/*  81 */       for (int x = 0; x < width; x++) {
/*     */         
/*  83 */         raster.getPixel(x, y, abc);
/*     */ 
/*     */         
/*  86 */         abc[0] = abc[0] / 255.0F;
/*  87 */         abc[1] = abc[1] / 255.0F;
/*  88 */         abc[2] = abc[2] / 255.0F;
/*     */ 
/*     */         
/*  91 */         abc[0] = abc[0] * 100.0F;
/*  92 */         abc[1] = minA + abc[1] * (maxA - minA);
/*  93 */         abc[2] = minB + abc[2] * (maxB - minB);
/*     */         
/*  95 */         float[] rgb = toRGB(abc);
/*     */ 
/*     */         
/*  98 */         rgb[0] = rgb[0] * 255.0F;
/*  99 */         rgb[1] = rgb[1] * 255.0F;
/* 100 */         rgb[2] = rgb[2] * 255.0F;
/*     */         
/* 102 */         rgbRaster.setPixel(x, y, rgb);
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     return rgbImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) {
/* 115 */     float lstar = (value[0] + 16.0F) * 0.00862069F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     float x = this.wpX * inverse(lstar + value[1] * 0.002F);
/* 121 */     float y = this.wpY * inverse(lstar);
/* 122 */     float z = this.wpZ * inverse(lstar - value[2] * 0.005F);
/*     */     
/* 124 */     return convXYZtoRGB(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float inverse(float x) {
/* 130 */     if (x > 0.20689655172413793D)
/*     */     {
/* 132 */       return x * x * x;
/*     */     }
/*     */ 
/*     */     
/* 136 */     return 0.12841855F * (x - 0.13793103F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/* 143 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 149 */     PDRange a = getARange();
/* 150 */     PDRange b = getARange();
/* 151 */     return new float[] { 0.0F, 100.0F, a.getMin(), a.getMax(), b.getMin(), b.getMax() };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/* 157 */     if (this.initialColor == null)
/*     */     {
/* 159 */       this
/*     */ 
/*     */         
/* 162 */         .initialColor = new PDColor(new float[] { 0.0F, Math.max(0.0F, getARange().getMin()), Math.max(0.0F, getBRange().getMin()) }this);
/*     */     }
/*     */     
/* 165 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSArray getDefaultRangeArray() {
/* 174 */     COSArray range = new COSArray();
/* 175 */     range.add((COSBase)new COSFloat(-100.0F));
/* 176 */     range.add((COSBase)new COSFloat(100.0F));
/* 177 */     range.add((COSBase)new COSFloat(-100.0F));
/* 178 */     range.add((COSBase)new COSFloat(100.0F));
/* 179 */     return range;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRange getARange() {
/* 189 */     COSArray rangeArray = (COSArray)this.dictionary.getDictionaryObject(COSName.RANGE);
/* 190 */     if (rangeArray == null)
/*     */     {
/* 192 */       rangeArray = getDefaultRangeArray();
/*     */     }
/* 194 */     return new PDRange(rangeArray, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRange getBRange() {
/* 204 */     COSArray rangeArray = (COSArray)this.dictionary.getDictionaryObject(COSName.RANGE);
/* 205 */     if (rangeArray == null)
/*     */     {
/* 207 */       rangeArray = getDefaultRangeArray();
/*     */     }
/* 209 */     return new PDRange(rangeArray, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setARange(PDRange range) {
/* 219 */     setComponentRangeArray(range, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBRange(PDRange range) {
/* 229 */     setComponentRangeArray(range, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setComponentRangeArray(PDRange range, int index) {
/* 234 */     COSArray rangeArray = (COSArray)this.dictionary.getDictionaryObject(COSName.RANGE);
/* 235 */     if (rangeArray == null)
/*     */     {
/* 237 */       rangeArray = getDefaultRangeArray();
/*     */     }
/* 239 */     if (range == null) {
/*     */ 
/*     */       
/* 242 */       rangeArray.set(index, (COSBase)new COSFloat(-100.0F));
/* 243 */       rangeArray.set(index + 1, (COSBase)new COSFloat(100.0F));
/*     */     }
/*     */     else {
/*     */       
/* 247 */       rangeArray.set(index, (COSBase)new COSFloat(range.getMin()));
/* 248 */       rangeArray.set(index + 1, (COSBase)new COSFloat(range.getMax()));
/*     */     } 
/* 250 */     this.dictionary.setItem(COSName.RANGE, (COSBase)rangeArray);
/* 251 */     this.initialColor = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDLab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */