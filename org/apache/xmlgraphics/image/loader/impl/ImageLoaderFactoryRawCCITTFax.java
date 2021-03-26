/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageLoaderFactoryRawCCITTFax
/*     */   extends AbstractImageLoaderFactory
/*     */ {
/*  37 */   private transient Log log = LogFactory.getLog(ImageLoaderFactoryRawCCITTFax.class);
/*     */   
/*  39 */   private static final String[] MIMES = new String[] { "image/tiff" };
/*     */ 
/*     */   
/*  42 */   private static final ImageFlavor[][] FLAVORS = new ImageFlavor[][] { { ImageFlavor.RAW_CCITTFAX } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMimeForRawFlavor(ImageFlavor flavor) {
/*  54 */     for (int i = 0, ci = FLAVORS.length; i < ci; i++) {
/*  55 */       for (int j = 0, cj = (FLAVORS[i]).length; j < cj; j++) {
/*  56 */         if (FLAVORS[i][j].equals(flavor)) {
/*  57 */           return MIMES[i];
/*     */         }
/*     */       } 
/*     */     } 
/*  61 */     throw new IllegalArgumentException("ImageFlavor is not a \"raw\" flavor: " + flavor);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getSupportedMIMETypes() {
/*  66 */     return MIMES;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageFlavor[] getSupportedFlavors(String mime) {
/*  71 */     for (int i = 0, c = MIMES.length; i < c; i++) {
/*  72 */       if (MIMES[i].equals(mime)) {
/*  73 */         return FLAVORS[i];
/*     */       }
/*     */     } 
/*  76 */     throw new IllegalArgumentException("Unsupported MIME type: " + mime);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageLoader newImageLoader(ImageFlavor targetFlavor) {
/*  81 */     if (targetFlavor.equals(ImageFlavor.RAW_CCITTFAX)) {
/*  82 */       return new ImageLoaderRawCCITTFax();
/*     */     }
/*  84 */     throw new IllegalArgumentException("Unsupported image flavor: " + targetFlavor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAvailable() {
/*  90 */     return true;
/*     */   }
/*     */   public boolean isSupported(ImageInfo imageInfo) {
/*     */     Integer stripCount;
/*     */     boolean supported;
/*  95 */     Boolean tiled = (Boolean)imageInfo.getCustomObjects().get("TIFF_TILED");
/*  96 */     if (Boolean.TRUE.equals(tiled)) {
/*     */       
/*  98 */       this.log.trace("Raw CCITT loading not supported for tiled TIFF image");
/*  99 */       return false;
/*     */     } 
/* 101 */     Integer compression = (Integer)imageInfo.getCustomObjects().get("TIFF_COMPRESSION");
/* 102 */     if (compression == null) {
/* 103 */       return false;
/*     */     }
/* 105 */     switch (compression.intValue()) {
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/* 109 */         stripCount = (Integer)imageInfo.getCustomObjects().get("TIFF_STRIP_COUNT");
/* 110 */         supported = (stripCount != null && stripCount.intValue() == 1);
/* 111 */         if (!supported) {
/* 112 */           this.log.trace("Raw CCITT loading not supported for multi-strip TIFF image");
/*     */         }
/* 114 */         return supported;
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderFactoryRawCCITTFax.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */