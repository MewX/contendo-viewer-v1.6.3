/*     */ package com.github.jaiimageio.impl.plugins.bmp;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import com.github.jaiimageio.impl.common.PackageUtil;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.util.Locale;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.spi.ImageWriterSpi;
/*     */ import javax.imageio.spi.ServiceRegistry;
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
/*     */ public class BMPImageWriterSpi
/*     */   extends ImageWriterSpi
/*     */ {
/*  65 */   private static String[] readerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.bmp.BMPImageReaderSpi" };
/*     */   
/*  67 */   private static String[] formatNames = new String[] { "bmp", "BMP" };
/*  68 */   private static String[] extensions = new String[] { "bmp" };
/*  69 */   private static String[] mimeTypes = new String[] { "image/bmp", "image/x-bmp", "image/x-windows-bmp" };
/*     */   
/*     */   private boolean registered = false;
/*     */ 
/*     */   
/*     */   public BMPImageWriterSpi() {
/*  75 */     super(PackageUtil.getVendor(), 
/*  76 */         PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.bmp.BMPImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, false, null, null, null, null, true, "com_sun_media_imageio_plugins_bmp_image_1.0", "com.github.jaiimageio.impl.plugins.bmp.BMPMetadataFormat", null, null);
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
/*     */   public String getDescription(Locale locale) {
/*  92 */     String desc = PackageUtil.getSpecificationTitle() + " BMP Image Writer";
/*     */     
/*  94 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  99 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/* 103 */     this.registered = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     ImageUtil.processOnRegistration(registry, category, "BMP", this, 8, 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEncodeImage(ImageTypeSpecifier type) {
/* 113 */     int dataType = type.getSampleModel().getDataType();
/* 114 */     if (dataType < 0 || dataType > 3) {
/* 115 */       return false;
/*     */     }
/* 117 */     SampleModel sm = type.getSampleModel();
/* 118 */     int numBands = sm.getNumBands();
/* 119 */     if (numBands != 1 && numBands != 3) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (numBands == 1 && dataType != 0) {
/* 123 */       return false;
/*     */     }
/* 125 */     if (dataType > 0 && !(sm instanceof java.awt.image.SinglePixelPackedSampleModel))
/*     */     {
/* 127 */       return false;
/*     */     }
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageWriter createWriterInstance(Object extension) throws IIOException {
/* 134 */     return new BMPImageWriter(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/bmp/BMPImageWriterSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */