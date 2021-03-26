/*    */ package org.apache.pdfbox.pdmodel.graphics.color;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PDCIEBasedColorSpace
/*    */   extends PDColorSpace
/*    */ {
/*    */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 42 */     int width = raster.getWidth();
/* 43 */     int height = raster.getHeight();
/*    */     
/* 45 */     BufferedImage rgbImage = new BufferedImage(width, height, 1);
/* 46 */     WritableRaster rgbRaster = rgbImage.getRaster();
/*    */ 
/*    */     
/* 49 */     float[] abc = new float[3];
/* 50 */     for (int y = 0; y < height; y++) {
/*    */       
/* 52 */       for (int x = 0; x < width; x++) {
/*    */         
/* 54 */         raster.getPixel(x, y, abc);
/*    */ 
/*    */         
/* 57 */         abc[0] = abc[0] / 255.0F;
/* 58 */         abc[1] = abc[1] / 255.0F;
/* 59 */         abc[2] = abc[2] / 255.0F;
/*    */         
/* 61 */         float[] rgb = toRGB(abc);
/*    */ 
/*    */         
/* 64 */         rgb[0] = rgb[0] * 255.0F;
/* 65 */         rgb[1] = rgb[1] * 255.0F;
/* 66 */         rgb[2] = rgb[2] * 255.0F;
/*    */         
/* 68 */         rgbRaster.setPixel(x, y, rgb);
/*    */       } 
/*    */     } 
/*    */     
/* 72 */     return rgbImage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return getName();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDCIEBasedColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */