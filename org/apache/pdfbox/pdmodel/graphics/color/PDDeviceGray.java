/*    */ package org.apache.pdfbox.pdmodel.graphics.color;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ public final class PDDeviceGray
/*    */   extends PDDeviceColorSpace
/*    */ {
/* 34 */   public static final PDDeviceGray INSTANCE = new PDDeviceGray();
/*    */   
/* 36 */   private final PDColor initialColor = new PDColor(new float[] { 0.0F }, this);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 45 */     return COSName.DEVICEGRAY.getName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumberOfComponents() {
/* 51 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 57 */     return new float[] { 0.0F, 1.0F };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PDColor getInitialColor() {
/* 63 */     return this.initialColor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] toRGB(float[] value) {
/* 69 */     return new float[] { value[0], value[0], value[0] };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 75 */     int width = raster.getWidth();
/* 76 */     int height = raster.getHeight();
/*    */     
/* 78 */     BufferedImage image = new BufferedImage(width, height, 1);
/*    */     
/* 80 */     int[] gray = new int[1];
/* 81 */     int[] rgb = new int[3];
/* 82 */     for (int y = 0; y < height; y++) {
/*    */       
/* 84 */       for (int x = 0; x < width; x++) {
/*    */         
/* 86 */         raster.getPixel(x, y, gray);
/* 87 */         rgb[0] = gray[0];
/* 88 */         rgb[1] = gray[0];
/* 89 */         rgb[2] = gray[0];
/* 90 */         image.getRaster().setPixel(x, y, rgb);
/*    */       } 
/*    */     } 
/*    */     
/* 94 */     return image;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceGray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */