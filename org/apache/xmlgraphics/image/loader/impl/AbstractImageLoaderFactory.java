/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImageLoader;
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImageLoaderFactory;
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
/*    */ public abstract class AbstractImageLoaderFactory
/*    */   implements ImageLoaderFactory
/*    */ {
/*    */   public boolean isSupported(ImageInfo imageInfo) {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getUsagePenalty(String mime, ImageFlavor flavor) {
/* 45 */     ImageLoader loader = newImageLoader(flavor);
/* 46 */     return loader.getUsagePenalty();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/AbstractImageLoaderFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */