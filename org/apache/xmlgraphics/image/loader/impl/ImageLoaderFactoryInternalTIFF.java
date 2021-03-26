/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageLoaderFactoryInternalTIFF
/*    */   extends AbstractImageLoaderFactory
/*    */ {
/* 31 */   private static final String[] MIMES = new String[] { "image/tiff" };
/*    */ 
/*    */   
/* 34 */   private static final ImageFlavor[] FLAVORS = new ImageFlavor[] { ImageFlavor.RENDERED_IMAGE };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getSupportedMIMETypes() {
/* 40 */     return MIMES;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor[] getSupportedFlavors(String mime) {
/* 45 */     if ("image/tiff".equals(mime)) {
/* 46 */       return FLAVORS;
/*    */     }
/* 48 */     throw new IllegalArgumentException("Unsupported MIME type: " + mime);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageLoader newImageLoader(ImageFlavor targetFlavor) {
/* 53 */     return new ImageLoaderInternalTIFF();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAvailable() {
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderFactoryInternalTIFF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */