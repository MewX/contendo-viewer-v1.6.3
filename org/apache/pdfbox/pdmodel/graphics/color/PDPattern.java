/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDPattern
/*     */   extends PDSpecialColorSpace
/*     */ {
/*  36 */   private static PDColor EMPTY_PATTERN = new PDColor(new float[0], null);
/*     */ 
/*     */ 
/*     */   
/*     */   private final PDResources resources;
/*     */ 
/*     */   
/*     */   private PDColorSpace underlyingColorSpace;
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPattern(PDResources resources) {
/*  48 */     this.resources = resources;
/*  49 */     this.array = new COSArray();
/*  50 */     this.array.add((COSBase)COSName.PATTERN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPattern(PDResources resources, PDColorSpace colorSpace) {
/*  61 */     this.resources = resources;
/*  62 */     this.underlyingColorSpace = colorSpace;
/*  63 */     this.array = new COSArray();
/*  64 */     this.array.add((COSBase)COSName.PATTERN);
/*  65 */     this.array.add(colorSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  71 */     return COSName.PATTERN.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/*  89 */     return EMPTY_PATTERN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) {
/*  95 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 101 */     throw new UnsupportedOperationException();
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
/*     */   public PDAbstractPattern getPattern(PDColor color) throws IOException {
/* 113 */     PDAbstractPattern pattern = this.resources.getPattern(color.getPatternName());
/* 114 */     if (pattern == null)
/*     */     {
/* 116 */       throw new IOException("pattern " + color.getPatternName() + " was not found");
/*     */     }
/*     */ 
/*     */     
/* 120 */     return pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getUnderlyingColorSpace() {
/* 129 */     return this.underlyingColorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 135 */     return "Pattern";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */