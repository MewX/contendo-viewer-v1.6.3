/*     */ package com.github.jaiimageio.impl.plugins.raw;
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
/*     */ public class RawImageWriterSpi
/*     */   extends ImageWriterSpi
/*     */ {
/*  61 */   private static String[] readerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.raw.RawImageReaderSpi" };
/*     */   
/*  63 */   private static String[] formatNames = new String[] { "raw", "RAW" };
/*  64 */   private static String[] entensions = new String[] { "" };
/*  65 */   private static String[] mimeType = new String[] { "" };
/*     */   
/*     */   private boolean registered = false;
/*     */   
/*     */   public RawImageWriterSpi() {
/*  70 */     super(PackageUtil.getVendor(), 
/*  71 */         PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.raw.RawImageWriter", STANDARD_OUTPUT_TYPE, readerSpiNames, true, null, null, null, null, true, null, null, null, null);
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
/*  85 */     String desc = PackageUtil.getSpecificationTitle() + " Raw Image Writer";
/*     */     
/*  87 */     return desc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  92 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/*  96 */     this.registered = true;
/*     */   }
/*     */   
/*     */   public boolean canEncodeImage(ImageTypeSpecifier type) {
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageWriter createWriterInstance(Object extension) throws IIOException {
/* 105 */     return new RawImageWriter(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/raw/RawImageWriterSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */