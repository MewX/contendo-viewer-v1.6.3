/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public final class PDDeviceRGB
/*     */   extends PDDeviceColorSpace
/*     */ {
/*  36 */   public static final PDDeviceRGB INSTANCE = new PDDeviceRGB();
/*     */   
/*  38 */   private final PDColor initialColor = new PDColor(new float[] { 0.0F, 0.0F, 0.0F }, this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile ColorSpace awtColorSpace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init() {
/*  51 */     if (this.awtColorSpace != null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  56 */     synchronized (this) {
/*     */ 
/*     */       
/*  59 */       if (this.awtColorSpace != null) {
/*     */         return;
/*     */       }
/*     */       
/*  63 */       this.awtColorSpace = ColorSpace.getInstance(1000);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       this.awtColorSpace.toRGB(new float[] { 0.0F, 0.0F, 0.0F, 0.0F });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  75 */     return COSName.DEVICERGB.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/*  84 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/*  90 */     return new float[] { 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/*  96 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) {
/* 102 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 108 */     init();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     BufferedImage image = new BufferedImage(raster.getWidth(), raster.getHeight(), 1);
/* 117 */     image.setData(raster);
/* 118 */     return image;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceRGB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */