/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.io.InputStream;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageRawEPS
/*    */   extends ImageRawStream
/*    */ {
/*    */   public ImageRawEPS(ImageInfo info, ImageRawStream.InputStreamFactory streamFactory) {
/* 40 */     super(info, ImageFlavor.RAW_EPS, streamFactory);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageRawEPS(ImageInfo info, InputStream in) {
/* 49 */     super(info, ImageFlavor.RAW_EPS, in);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Rectangle2D getBoundingBox() {
/* 57 */     Rectangle2D bbox = (Rectangle2D)getInfo().getCustomObjects().get(PreloaderEPS.EPS_BOUNDING_BOX);
/*    */     
/* 59 */     return bbox;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageRawEPS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */