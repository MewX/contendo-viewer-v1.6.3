/*     */ package com.github.jaiimageio.impl.plugins.raw;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.PackageUtil;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.spi.ImageReaderSpi;
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
/*     */ 
/*     */ public class RawImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*  64 */   private static String[] writerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.raw.RawImageWriterSpi" };
/*     */   
/*  66 */   private static String[] formatNames = new String[] { "raw", "RAW" };
/*  67 */   private static String[] entensions = new String[] { "" };
/*  68 */   private static String[] mimeType = new String[] { "" };
/*     */   
/*     */   private boolean registered = false;
/*     */   
/*     */   public RawImageReaderSpi() {
/*  73 */     super(PackageUtil.getVendor(), 
/*  74 */         PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.raw.RawImageReader", STANDARD_INPUT_TYPE, writerSpiNames, true, null, null, null, null, true, null, null, null, null);
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
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  89 */     if (this.registered) {
/*     */       return;
/*     */     }
/*  92 */     this.registered = true;
/*     */   }
/*     */   
/*     */   public String getDescription(Locale locale) {
/*  96 */     String desc = PackageUtil.getSpecificationTitle() + " Raw Image Reader";
/*     */     
/*  98 */     return desc;
/*     */   }
/*     */   
/*     */   public boolean canDecodeInput(Object source) throws IOException {
/* 102 */     if (!(source instanceof com.github.jaiimageio.stream.RawImageInputStream)) {
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object extension) throws IIOException {
/* 111 */     return new RawImageReader(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/raw/RawImageReaderSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */