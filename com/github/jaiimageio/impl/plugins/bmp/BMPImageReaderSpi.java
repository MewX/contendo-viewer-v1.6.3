/*     */ package com.github.jaiimageio.impl.plugins.bmp;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
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
/*     */ public class BMPImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*  64 */   private static String[] writerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.bmp.BMPImageWriterSpi" };
/*     */   
/*  66 */   private static String[] formatNames = new String[] { "bmp", "BMP" };
/*  67 */   private static String[] extensions = new String[] { "bmp" };
/*  68 */   private static String[] mimeTypes = new String[] { "image/bmp", "image/x-bmp", "image/x-windows-bmp" };
/*     */   
/*     */   private boolean registered = false;
/*     */ 
/*     */   
/*     */   public BMPImageReaderSpi() {
/*  74 */     super(PackageUtil.getVendor(), 
/*  75 */         PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.bmp.BMPImageReader", STANDARD_INPUT_TYPE, writerSpiNames, false, null, null, null, null, true, "com_sun_media_imageio_plugins_bmp_image_1.0", "com.github.jaiimageio.impl.plugins.bmp.BMPMetadataFormat", null, null);
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
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  92 */     if (this.registered) {
/*     */       return;
/*     */     }
/*  95 */     this.registered = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     ImageUtil.processOnRegistration(registry, category, "BMP", this, 8, 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription(Locale locale) {
/* 105 */     String desc = PackageUtil.getSpecificationTitle() + " BMP Image Reader";
/*     */     
/* 107 */     return desc;
/*     */   }
/*     */   
/*     */   public boolean canDecodeInput(Object source) throws IOException {
/* 111 */     if (!(source instanceof ImageInputStream)) {
/* 112 */       return false;
/*     */     }
/*     */     
/* 115 */     ImageInputStream stream = (ImageInputStream)source;
/* 116 */     byte[] b = new byte[2];
/* 117 */     stream.mark();
/* 118 */     stream.readFully(b);
/* 119 */     stream.reset();
/*     */     
/* 121 */     return (b[0] == 66 && b[1] == 77);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object extension) throws IIOException {
/* 126 */     return new BMPImageReader(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/bmp/BMPImageReaderSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */