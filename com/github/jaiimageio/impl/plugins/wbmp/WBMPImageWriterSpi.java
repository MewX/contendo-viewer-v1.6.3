/*     */ package com.github.jaiimageio.impl.plugins.wbmp;
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
/*     */ public class WBMPImageWriterSpi
/*     */   extends ImageWriterSpi
/*     */ {
/*  64 */   private static String[] readerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.wbmp.WBMPImageReaderSpi" };
/*     */   
/*  66 */   private static String[] formatNames = new String[] { "wbmp", "WBMP" };
/*  67 */   private static String[] entensions = new String[] { "wbmp" };
/*  68 */   private static String[] mimeType = new String[] { "image/vnd.wap.wbmp" };
/*     */   
/*     */   private boolean registered = false;
/*     */   
/*     */   public WBMPImageWriterSpi() {
/*  73 */     super(PackageUtil.getVendor(), 
/*  74 */         PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.wbmp.WBMPImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, true, null, null, null, null, true, "com_sun_media_imageio_plugins_wbmp_image_1.0", "com.github.jaiimageio.impl.plugins.wbmp.WBMPMetadataFormat", null, null);
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
/*  90 */     String desc = PackageUtil.getSpecificationTitle() + " WBMP Image Writer";
/*     */     
/*  92 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  97 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     this.registered = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     ImageUtil.processOnRegistration(registry, category, "WBMP", this, 8, 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEncodeImage(ImageTypeSpecifier type) {
/* 111 */     SampleModel sm = type.getSampleModel();
/* 112 */     if (!(sm instanceof java.awt.image.MultiPixelPackedSampleModel))
/* 113 */       return false; 
/* 114 */     if (sm.getSampleSize(0) != 1) {
/* 115 */       return false;
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageWriter createWriterInstance(Object extension) throws IIOException {
/* 122 */     return new WBMPImageWriter(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/wbmp/WBMPImageWriterSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */