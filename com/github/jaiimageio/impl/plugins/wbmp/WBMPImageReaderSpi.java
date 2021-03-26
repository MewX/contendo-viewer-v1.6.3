/*     */ package com.github.jaiimageio.impl.plugins.wbmp;
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
/*     */ public class WBMPImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*  64 */   private static String[] writerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.wbmp.WBMPImageWriterSpi" };
/*     */   
/*  66 */   private static String[] formatNames = new String[] { "wbmp", "WBMP" };
/*  67 */   private static String[] entensions = new String[] { "wbmp" };
/*  68 */   private static String[] mimeType = new String[] { "image/vnd.wap.wbmp" };
/*     */   
/*     */   private boolean registered = false;
/*     */   
/*     */   public WBMPImageReaderSpi() {
/*  73 */     super(PackageUtil.getVendor(), 
/*  74 */         PackageUtil.getVersion(), formatNames, entensions, mimeType, "com.github.jaiimageio.impl.plugins.wbmp.WBMPImageReader", STANDARD_INPUT_TYPE, writerSpiNames, true, null, null, null, null, true, "com_sun_media_imageio_plugins_wbmp_image_1.0", "com.github.jaiimageio.impl.plugins.wbmp.WBMPMetadataFormat", null, null);
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
/*  91 */     if (this.registered) {
/*     */       return;
/*     */     }
/*  94 */     this.registered = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     ImageUtil.processOnRegistration(registry, category, "WBMP", this, 8, 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription(Locale locale) {
/* 104 */     String desc = PackageUtil.getSpecificationTitle() + " WBMP Image Reader";
/*     */     
/* 106 */     return desc;
/*     */   }
/*     */   
/*     */   public boolean canDecodeInput(Object source) throws IOException {
/* 110 */     if (!(source instanceof ImageInputStream)) {
/* 111 */       return false;
/*     */     }
/*     */     
/* 114 */     ImageInputStream stream = (ImageInputStream)source;
/*     */     
/* 116 */     stream.mark();
/* 117 */     int type = stream.readByte();
/* 118 */     byte fixHeaderField = stream.readByte();
/*     */     
/* 120 */     int width = ImageUtil.readMultiByteInteger(stream);
/* 121 */     int height = ImageUtil.readMultiByteInteger(stream);
/*     */     
/* 123 */     long remainingBytes = stream.length() - stream.getStreamPosition();
/* 124 */     stream.reset();
/*     */ 
/*     */     
/* 127 */     if (type != 0 || fixHeaderField != 0)
/*     */     {
/* 129 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 133 */     if (width <= 0 || height <= 0) {
/* 134 */       return false;
/*     */     }
/*     */     
/* 137 */     long scanSize = (width / 8 + ((width % 8 == 0) ? 0 : 1));
/*     */     
/* 139 */     return (remainingBytes == scanSize * height);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object extension) throws IIOException {
/* 144 */     return new WBMPImageReader(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/wbmp/WBMPImageReaderSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */