/*     */ package com.github.jaiimageio.impl.plugins.pnm;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.PackageUtil;
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
/*     */ public class PNMImageWriterSpi
/*     */   extends ImageWriterSpi
/*     */ {
/*  62 */   private static String[] readerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.pnm.PNMImageReaderSpi" };
/*     */   
/*  64 */   private static String[] formatNames = new String[] { "pnm", "PNM" };
/*  65 */   private static String[] entensions = new String[] { "pbm", "pgm", "ppm" };
/*  66 */   private static String[] mimeType = new String[] { "image/x-portable-anymap", "image/x-portable-bitmap", "image/x-portable-graymap", "image/x-portable-pixmap" };
/*     */ 
/*     */   
/*     */   private boolean registered = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public PNMImageWriterSpi() {
/*  74 */     super(PackageUtil.getVendor(), 
/*  75 */         PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.pnm.PNMImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, true, null, null, null, null, true, null, null, null, null);
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
/*     */   public String getDescription(Locale locale) {
/*  89 */     String desc = PackageUtil.getSpecificationTitle() + " PNM Image Writer";
/*     */     
/*  91 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  96 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     this.registered = true;
/*     */   }
/*     */   
/*     */   public boolean canEncodeImage(ImageTypeSpecifier type) {
/* 104 */     int dataType = type.getSampleModel().getDataType();
/* 105 */     if (dataType < 0 || dataType > 3)
/*     */     {
/* 107 */       return false;
/*     */     }
/* 109 */     int numBands = type.getSampleModel().getNumBands();
/* 110 */     if (numBands != 1 && numBands != 3) {
/* 111 */       return false;
/*     */     }
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageWriter createWriterInstance(Object extension) throws IIOException {
/* 118 */     return new PNMImageWriter(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pnm/PNMImageWriterSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */