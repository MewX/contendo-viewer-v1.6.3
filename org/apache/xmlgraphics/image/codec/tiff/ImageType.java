/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
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
/*     */ enum ImageType
/*     */ {
/*  25 */   UNSUPPORTED(-1),
/*  26 */   BILEVEL_WHITE_IS_ZERO(0),
/*  27 */   BILEVEL_BLACK_IS_ZERO(1),
/*  28 */   GRAY(1),
/*  29 */   PALETTE(3),
/*  30 */   RGB(2),
/*  31 */   CMYK(5),
/*  32 */   YCBCR(6),
/*  33 */   CIELAB(8),
/*  34 */   GENERIC(1);
/*     */   
/*     */   private final int photometricInterpretation;
/*     */   
/*     */   ImageType(int photometricInterpretation) {
/*  39 */     this.photometricInterpretation = photometricInterpretation;
/*     */   }
/*     */   
/*     */   int getPhotometricInterpretation() {
/*  43 */     return this.photometricInterpretation;
/*     */   }
/*     */ 
/*     */   
/*     */   static ImageType getTypeFromRGB(int mapSize, byte[] r, byte[] g, byte[] b, int dataTypeSize, int numBands) {
/*  48 */     if (numBands == 1) {
/*  49 */       if (dataTypeSize == 1) {
/*  50 */         if (mapSize != 2) {
/*  51 */           throw new IllegalArgumentException(PropertyUtil.getString("TIFFImageEncoder7"));
/*     */         }
/*     */         
/*  54 */         if (isBlackZero(r, g, b))
/*  55 */           return BILEVEL_BLACK_IS_ZERO; 
/*  56 */         if (isWhiteZero(r, g, b)) {
/*  57 */           return BILEVEL_WHITE_IS_ZERO;
/*     */         }
/*     */       } 
/*  60 */       return PALETTE;
/*     */     } 
/*  62 */     return UNSUPPORTED;
/*     */   }
/*     */   
/*     */   private static boolean rgbIsValueAt(byte[] r, byte[] g, byte[] b, byte value, int i) {
/*  66 */     return (r[i] == value && g[i] == value && b[i] == value);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean bilevelColorValue(byte[] r, byte[] g, byte[] b, int blackValue, int whiteValue) {
/*  71 */     return (rgbIsValueAt(r, g, b, (byte)blackValue, 0) && rgbIsValueAt(r, g, b, (byte)whiteValue, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isBlackZero(byte[] r, byte[] g, byte[] b) {
/*  76 */     return bilevelColorValue(r, g, b, 0, 255);
/*     */   }
/*     */   
/*     */   private static boolean isWhiteZero(byte[] r, byte[] g, byte[] b) {
/*  80 */     return bilevelColorValue(r, g, b, 255, 0);
/*     */   }
/*     */   
/*     */   static ImageType getTypeFromColorSpace(ColorSpace colorSpace, TIFFEncodeParam params) {
/*  84 */     switch (colorSpace.getType()) {
/*     */       case 9:
/*  86 */         return CMYK;
/*     */       case 6:
/*  88 */         return GRAY;
/*     */       case 1:
/*  90 */         return CIELAB;
/*     */       case 5:
/*  92 */         if (params.getJPEGCompressRGBToYCbCr()) {
/*  93 */           return YCBCR;
/*     */         }
/*  95 */         return RGB;
/*     */       
/*     */       case 3:
/*  98 */         return YCBCR;
/*     */     } 
/* 100 */     return GENERIC;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/ImageType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */