/*    */ package org.apache.pdfbox.pdmodel.graphics.color;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PDJPXColorSpace
/*    */   extends PDColorSpace
/*    */ {
/*    */   private final ColorSpace awtColorSpace;
/*    */   
/*    */   public PDJPXColorSpace(ColorSpace colorSpace) {
/* 42 */     this.awtColorSpace = colorSpace;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return "JPX";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumberOfComponents() {
/* 54 */     return this.awtColorSpace.getNumComponents();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 60 */     int n = getNumberOfComponents();
/* 61 */     float[] decode = new float[n * 2];
/* 62 */     for (int i = 0; i < n; i++) {
/*    */       
/* 64 */       decode[i * 2] = this.awtColorSpace.getMinValue(i);
/* 65 */       decode[i * 2 + 1] = this.awtColorSpace.getMaxValue(i);
/*    */     } 
/* 67 */     return decode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PDColor getInitialColor() {
/* 73 */     throw new UnsupportedOperationException("JPX color spaces don't support drawing");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] toRGB(float[] value) {
/* 79 */     throw new UnsupportedOperationException("JPX color spaces don't support drawing");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 85 */     return toRGBImageAWT(raster, this.awtColorSpace);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 91 */     throw new UnsupportedOperationException("JPX color spaces don't have COS objects");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDJPXColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */