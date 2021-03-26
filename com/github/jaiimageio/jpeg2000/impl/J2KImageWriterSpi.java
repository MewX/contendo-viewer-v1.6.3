/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
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
/*     */ public class J2KImageWriterSpi
/*     */   extends ImageWriterSpi
/*     */ {
/*  60 */   private static String[] readerSpiNames = new String[] { "com.github.jaiimageio.jpeg2000.impl.J2KImageReaderSpi" };
/*     */   
/*  62 */   private static String[] formatNames = new String[] { "jpeg 2000", "JPEG 2000", "jpeg2000", "JPEG2000" };
/*     */   
/*  64 */   private static String[] extensions = new String[] { "jp2" };
/*     */   
/*  66 */   private static String[] mimeTypes = new String[] { "image/jp2", "image/jpeg2000" };
/*     */   private boolean registered = false;
/*     */   
/*     */   public J2KImageWriterSpi() {
/*  70 */     super(PackageUtil.getVendor(), 
/*  71 */         PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.jpeg2000.impl.J2KImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, false, null, null, null, null, true, "com_sun_media_imageio_plugins_jpeg2000_image_1.0", "com.github.jaiimageio.jpeg2000.impl.J2KMetadataFormat", null, null);
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
/*     */   public String getDescription(Locale locale) {
/*  88 */     String desc = PackageUtil.getSpecificationTitle() + " JPEG 2000 Image Writer";
/*     */     
/*  90 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  95 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/*  99 */     this.registered = true;
/*     */ 
/*     */     
/* 102 */     Class<?> codecLibWriterSPIClass = null;
/*     */     
/*     */     try {
/* 105 */       codecLibWriterSPIClass = Class.forName("com.github.jaiimageio.jpeg2000.impl.J2KImageWriterCodecLibSpi");
/* 106 */     } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (codecLibWriterSPIClass != null) {
/*     */       
/* 112 */       Object codecLibWriterSPI = registry.getServiceProviderByClass(codecLibWriterSPIClass);
/* 113 */       if (codecLibWriterSPI != null) {
/* 114 */         registry.setOrdering(category, codecLibWriterSPI, this);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canEncodeImage(ImageTypeSpecifier type) {
/* 120 */     SampleModel sm = type.getSampleModel();
/* 121 */     if (sm.getNumBands() > 16384)
/* 122 */       return false; 
/* 123 */     if (sm.getDataType() < 0 || sm
/* 124 */       .getDataType() > 3)
/* 125 */       return false; 
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageWriter createWriterInstance(Object extension) throws IIOException {
/* 131 */     return new J2KImageWriter(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KImageWriterSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */