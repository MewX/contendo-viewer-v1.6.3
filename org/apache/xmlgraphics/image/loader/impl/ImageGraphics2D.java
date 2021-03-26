/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.java2d.Graphics2DImagePainter;
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
/*    */ public class ImageGraphics2D
/*    */   extends AbstractImage
/*    */ {
/*    */   private Graphics2DImagePainter painter;
/*    */   
/*    */   public ImageGraphics2D(ImageInfo info, Graphics2DImagePainter painter) {
/* 40 */     super(info);
/* 41 */     setGraphics2DImagePainter(painter);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getFlavor() {
/* 46 */     return ImageFlavor.GRAPHICS2D;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCacheable() {
/* 51 */     Image img = getInfo().getOriginalImage();
/* 52 */     if (img == null) {
/* 53 */       return true;
/*    */     }
/* 55 */     return img.isCacheable();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Graphics2DImagePainter getGraphics2DImagePainter() {
/* 63 */     return this.painter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setGraphics2DImagePainter(Graphics2DImagePainter painter) {
/* 71 */     this.painter = painter;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageGraphics2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */