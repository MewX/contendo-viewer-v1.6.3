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
/*    */ 
/*    */ 
/*    */ public class ImageLoaderFactoryRaw
/*    */   extends AbstractImageLoaderFactory
/*    */ {
/*    */   public static final String MIME_EMF = "image/x-emf";
/* 34 */   private static final String[] MIMES = new String[] { "image/png", "image/jpeg", "image/tiff", "image/x-emf" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   private static final ImageFlavor[][] FLAVORS = new ImageFlavor[][] { { ImageFlavor.RAW_PNG }, { ImageFlavor.RAW_JPEG }, { ImageFlavor.RAW_TIFF }, { ImageFlavor.RAW_EMF } };
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
/*    */   public static String getMimeForRawFlavor(ImageFlavor flavor) {
/* 55 */     for (int i = 0, ci = FLAVORS.length; i < ci; i++) {
/* 56 */       for (int j = 0, cj = (FLAVORS[i]).length; j < cj; j++) {
/* 57 */         if (FLAVORS[i][j].equals(flavor)) {
/* 58 */           return MIMES[i];
/*    */         }
/*    */       } 
/*    */     } 
/* 62 */     throw new IllegalArgumentException("ImageFlavor is not a \"raw\" flavor: " + flavor);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getSupportedMIMETypes() {
/* 67 */     return MIMES;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor[] getSupportedFlavors(String mime) {
/* 72 */     for (int i = 0, c = MIMES.length; i < c; i++) {
/* 73 */       if (MIMES[i].equals(mime)) {
/* 74 */         return FLAVORS[i];
/*    */       }
/*    */     } 
/* 77 */     throw new IllegalArgumentException("Unsupported MIME type: " + mime);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageLoader newImageLoader(ImageFlavor targetFlavor) {
/* 82 */     if (targetFlavor.equals(ImageFlavor.RAW_JPEG))
/* 83 */       return new ImageLoaderRawJPEG(); 
/* 84 */     if (targetFlavor.equals(ImageFlavor.RAW_PNG)) {
/* 85 */       return new ImageLoaderRawPNG();
/*    */     }
/* 87 */     return new ImageLoaderRaw(targetFlavor);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAvailable() {
/* 93 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderFactoryRaw.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */