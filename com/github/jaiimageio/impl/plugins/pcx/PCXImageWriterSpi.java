/*     */ package com.github.jaiimageio.impl.plugins.pcx;
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
/*     */ public class PCXImageWriterSpi
/*     */   extends ImageWriterSpi
/*     */ {
/*  56 */   private static String[] readerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.pcx.PCXImageReaderSpi" };
/*     */   
/*  58 */   private static String[] formatNames = new String[] { "pcx", "PCX" };
/*  59 */   private static String[] extensions = new String[] { "pcx" };
/*  60 */   private static String[] mimeTypes = new String[] { "image/pcx", "image/x-pcx", "image/x-windows-pcx", "image/x-pc-paintbrush" };
/*     */   
/*     */   private boolean registered = false;
/*     */ 
/*     */   
/*     */   public PCXImageWriterSpi() {
/*  66 */     super(PackageUtil.getVendor(), 
/*  67 */         PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.pcx.PCXImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, false, null, null, null, null, true, null, null, null, null);
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
/*  83 */     String desc = PackageUtil.getSpecificationTitle() + " PCX Image Writer";
/*     */     
/*  85 */     return desc;
/*     */   }
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  89 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     this.registered = true;
/*     */   }
/*     */   
/*     */   public boolean canEncodeImage(ImageTypeSpecifier type) {
/*  97 */     int dataType = type.getSampleModel().getDataType();
/*  98 */     if (dataType < 0 || dataType > 3) {
/*  99 */       return false;
/*     */     }
/* 101 */     SampleModel sm = type.getSampleModel();
/* 102 */     int numBands = sm.getNumBands();
/* 103 */     if (numBands != 1 && numBands != 3) {
/* 104 */       return false;
/*     */     }
/* 106 */     if (numBands == 1 && dataType != 0) {
/* 107 */       return false;
/*     */     }
/* 109 */     if (dataType > 0 && !(sm instanceof java.awt.image.SinglePixelPackedSampleModel)) {
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageWriter createWriterInstance(Object extension) throws IIOException {
/* 117 */     return new PCXImageWriter(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pcx/PCXImageWriterSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */