/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import org.apache.xmlgraphics.image.codec.util.ImageDecodeParam;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFDecodeParam
/*     */   implements ImageDecodeParam
/*     */ {
/*     */   private static final long serialVersionUID = -2371665950056848358L;
/*     */   private boolean decodePaletteAsShorts;
/*     */   private Long ifdOffset;
/*     */   private boolean convertJPEGYCbCrToRGB = true;
/*     */   
/*     */   public void setDecodePaletteAsShorts(boolean decodePaletteAsShorts) {
/*  89 */     this.decodePaletteAsShorts = decodePaletteAsShorts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDecodePaletteAsShorts() {
/*  97 */     return this.decodePaletteAsShorts;
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
/*     */   public byte decode16BitsTo8Bits(int s) {
/* 110 */     return (byte)(s >> 8 & 0xFFFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte decodeSigned16BitsTo8Bits(short s) {
/* 119 */     return (byte)(s + -32768 >> 8);
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
/*     */   public void setIFDOffset(long offset) {
/* 134 */     this.ifdOffset = Long.valueOf(offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getIFDOffset() {
/* 142 */     return this.ifdOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJPEGDecompressYCbCrToRGB(boolean convertJPEGYCbCrToRGB) {
/* 151 */     this.convertJPEGYCbCrToRGB = convertJPEGYCbCrToRGB;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getJPEGDecompressYCbCrToRGB() {
/* 158 */     return this.convertJPEGYCbCrToRGB;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFDecodeParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */