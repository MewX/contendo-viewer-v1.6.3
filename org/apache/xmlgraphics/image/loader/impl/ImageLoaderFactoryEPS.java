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
/*    */ public class ImageLoaderFactoryEPS
/*    */   extends AbstractImageLoaderFactory
/*    */ {
/* 31 */   private static final String[] MIMES = new String[] { "application/postscript" };
/*    */ 
/*    */   
/* 34 */   private static final ImageFlavor[] FLAVORS = new ImageFlavor[] { ImageFlavor.RAW_EPS };
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
/* 45 */     if ("application/postscript".equals(mime)) {
/* 46 */       return FLAVORS;
/*    */     }
/* 48 */     throw new IllegalArgumentException("Unsupported MIME type: " + mime);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageLoader newImageLoader(ImageFlavor targetFlavor) {
/* 53 */     return new ImageLoaderEPS();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAvailable() {
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderFactoryEPS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */