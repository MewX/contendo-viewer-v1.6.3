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
/*    */ public class ImageLoaderFactoryPNG
/*    */   extends AbstractImageLoaderFactory
/*    */ {
/* 28 */   private static final String[] MIMES = new String[] { "image/png" };
/*    */   
/* 30 */   private static final ImageFlavor[] FLAVORS = new ImageFlavor[] { ImageFlavor.RENDERED_IMAGE };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getSupportedMIMETypes() {
/* 38 */     return MIMES;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor[] getSupportedFlavors(String mime) {
/* 43 */     if ("image/png".equals(mime)) {
/* 44 */       return FLAVORS;
/*    */     }
/* 46 */     throw new IllegalArgumentException("Unsupported MIME type: " + mime);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageLoader newImageLoader(ImageFlavor targetFlavor) {
/* 51 */     return new ImageLoaderPNG();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAvailable() {
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderFactoryPNG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */