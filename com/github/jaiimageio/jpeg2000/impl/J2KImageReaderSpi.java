/*     */ package com.github.jaiimageio.jpeg2000.impl;
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
/*     */ public class J2KImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*  60 */   private static String[] writerSpiNames = new String[] { "com.github.jaiimageio.jpeg2000.impl.J2KImageWriterSpi" };
/*     */   
/*  62 */   private static String[] formatNames = new String[] { "jpeg 2000", "JPEG 2000", "jpeg2000", "JPEG2000" };
/*     */   
/*  64 */   private static String[] extensions = new String[] { "jp2" };
/*     */   
/*  66 */   private static String[] mimeTypes = new String[] { "image/jp2", "image/jpeg2000" };
/*     */   private boolean registered = false;
/*     */   
/*     */   public J2KImageReaderSpi() {
/*  70 */     super(PackageUtil.getVendor(), 
/*  71 */         PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.jpeg2000.impl.J2KImageReader", STANDARD_INPUT_TYPE, writerSpiNames, false, null, null, null, null, true, "com_sun_media_imageio_plugins_jpeg2000_image_1.0", "com.github.jaiimageio.jpeg2000.impl.J2KMetadataFormat", null, null);
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
/*     */   
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  89 */     if (this.registered) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     this.registered = true;
/*     */ 
/*     */     
/*  96 */     Class<?> codecLibReaderSPIClass = null;
/*     */     
/*     */     try {
/*  99 */       codecLibReaderSPIClass = Class.forName("com.github.jaiimageio.jpeg2000.impl.J2KImageReaderCodecLibSpi");
/* 100 */     } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (codecLibReaderSPIClass != null) {
/*     */       
/* 106 */       Object codecLibReaderSPI = registry.getServiceProviderByClass(codecLibReaderSPIClass);
/* 107 */       if (codecLibReaderSPI != null) {
/* 108 */         registry.setOrdering(category, codecLibReaderSPI, this);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getDescription(Locale locale) {
/* 114 */     String desc = PackageUtil.getSpecificationTitle() + " JPEG 2000 Image Reader";
/*     */     
/* 116 */     return desc;
/*     */   }
/*     */   
/*     */   public boolean canDecodeInput(Object source) throws IOException {
/* 120 */     if (!(source instanceof ImageInputStream)) {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     ImageInputStream stream = (ImageInputStream)source;
/*     */ 
/*     */     
/* 127 */     stream.mark();
/* 128 */     int marker = stream.read() << 8 | stream.read();
/*     */     
/* 130 */     if (marker == 65359) {
/* 131 */       stream.reset();
/* 132 */       return true;
/*     */     } 
/*     */     
/* 135 */     stream.reset();
/* 136 */     stream.mark();
/* 137 */     byte[] b = new byte[12];
/* 138 */     stream.readFully(b);
/* 139 */     stream.reset();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (b[0] != 0 || b[1] != 0 || b[2] != 0 || b[3] != 12) {
/* 145 */       return false;
/*     */     }
/*     */     
/* 148 */     if ((b[4] & 0xFF) != 106 || (b[5] & 0xFF) != 80 || (b[6] & 0xFF) != 32 || (b[7] & 0xFF) != 32)
/*     */     {
/* 150 */       return false;
/*     */     }
/*     */     
/* 153 */     if ((b[8] & 0xFF) != 13 || (b[9] & 0xFF) != 10 || (b[10] & 0xFF) != 135 || (b[11] & 0xFF) != 10)
/*     */     {
/* 155 */       return false;
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object extension) throws IIOException {
/* 162 */     return new J2KImageReader(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KImageReaderSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */