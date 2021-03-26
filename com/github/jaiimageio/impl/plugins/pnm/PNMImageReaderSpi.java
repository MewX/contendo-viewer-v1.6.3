/*     */ package com.github.jaiimageio.impl.plugins.pnm;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.PackageUtil;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.imageio.spi.ServiceRegistry;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNMImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*  63 */   private static String[] writerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.pnm.PNMImageWriterSpi" };
/*     */   
/*  65 */   private static String[] formatNames = new String[] { "pnm", "PNM" };
/*  66 */   private static String[] entensions = new String[] { "pbm", "pgm", "ppm" };
/*  67 */   private static String[] mimeType = new String[] { "image/x-portable-anymap", "image/x-portable-bitmap", "image/x-portable-graymap", "image/x-portable-pixmap" };
/*     */ 
/*     */   
/*     */   private boolean registered = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public PNMImageReaderSpi() {
/*  75 */     super(PackageUtil.getVendor(), 
/*  76 */         PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.pnm.PNMImageReader", STANDARD_INPUT_TYPE, writerSpiNames, true, null, null, null, null, true, null, null, null, null);
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
/*  91 */     if (this.registered) {
/*     */       return;
/*     */     }
/*  94 */     this.registered = true;
/*     */   }
/*     */   
/*     */   public String getDescription(Locale locale) {
/*  98 */     String desc = PackageUtil.getSpecificationTitle() + " PNM Image Reader";
/*     */     
/* 100 */     return desc;
/*     */   }
/*     */   
/*     */   public boolean canDecodeInput(Object source) throws IOException {
/* 104 */     if (!(source instanceof ImageInputStream)) {
/* 105 */       return false;
/*     */     }
/*     */     
/* 108 */     ImageInputStream stream = (ImageInputStream)source;
/* 109 */     byte[] b = new byte[2];
/*     */     
/* 111 */     stream.mark();
/* 112 */     stream.readFully(b);
/* 113 */     stream.reset();
/*     */     
/* 115 */     return (b[0] == 80 && b[1] >= 49 && b[1] <= 54);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object extension) throws IIOException {
/* 120 */     return new PNMImageReader(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pnm/PNMImageReaderSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */