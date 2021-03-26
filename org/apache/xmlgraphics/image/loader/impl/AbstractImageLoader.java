/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageProcessingHints;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
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
/*    */ public abstract class AbstractImageLoader
/*    */   implements ImageLoader
/*    */ {
/*    */   public Image loadImage(ImageInfo info, ImageSessionContext session) throws ImageException, IOException {
/* 40 */     return loadImage(info, null, session);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUsagePenalty() {
/* 45 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean ignoreColorProfile(Map hints) {
/* 55 */     if (hints == null) {
/* 56 */       return false;
/*    */     }
/* 58 */     Boolean b = (Boolean)hints.get(ImageProcessingHints.IGNORE_COLOR_PROFILE);
/* 59 */     return (b != null && b.booleanValue());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/AbstractImageLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */