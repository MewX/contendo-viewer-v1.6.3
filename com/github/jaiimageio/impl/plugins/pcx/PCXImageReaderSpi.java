/*     */ package com.github.jaiimageio.impl.plugins.pcx;
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
/*     */ public class PCXImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*  58 */   private static String[] writerSpiNames = new String[] { "com.github.jaiimageio.impl.plugins.pcx.PCXImageWriterSpi" };
/*     */   
/*  60 */   private static String[] formatNames = new String[] { "pcx", "PCX" };
/*  61 */   private static String[] extensions = new String[] { "pcx" };
/*  62 */   private static String[] mimeTypes = new String[] { "image/pcx", "image/x-pcx", "image/x-windows-pcx", "image/x-pc-paintbrush" };
/*     */   
/*     */   private boolean registered = false;
/*     */ 
/*     */   
/*     */   public PCXImageReaderSpi() {
/*  68 */     super(PackageUtil.getVendor(), 
/*  69 */         PackageUtil.getVersion(), formatNames, extensions, mimeTypes, "com.github.jaiimageio.impl.plugins.pcx.PCXImageReader", STANDARD_INPUT_TYPE, writerSpiNames, false, null, null, null, null, true, null, null, null, null);
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
/*     */   public void onRegistration(ServiceRegistry registry, Class category) {
/*  85 */     if (this.registered) {
/*     */       return;
/*     */     }
/*  88 */     this.registered = true;
/*     */   }
/*     */   
/*     */   public String getDescription(Locale locale) {
/*  92 */     String desc = PackageUtil.getSpecificationTitle() + " PCX Image Reader";
/*     */     
/*  94 */     return desc;
/*     */   }
/*     */   
/*     */   public boolean canDecodeInput(Object source) throws IOException {
/*  98 */     if (!(source instanceof ImageInputStream)) {
/*  99 */       return false;
/*     */     }
/*     */     
/* 102 */     ImageInputStream stream = (ImageInputStream)source;
/* 103 */     stream.mark();
/* 104 */     byte b = stream.readByte();
/* 105 */     stream.reset();
/*     */     
/* 107 */     return (b == 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object extension) throws IIOException {
/* 112 */     return new PCXImageReader(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pcx/PCXImageReaderSpi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */