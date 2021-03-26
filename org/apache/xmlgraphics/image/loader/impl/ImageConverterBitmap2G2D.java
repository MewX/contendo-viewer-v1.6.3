/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.util.Map;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
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
/*    */ public class ImageConverterBitmap2G2D
/*    */   extends AbstractImageConverter
/*    */ {
/*    */   public Image convert(Image src, Map hints) {
/* 40 */     checkSourceFlavor(src);
/* 41 */     assert src instanceof ImageRendered;
/* 42 */     ImageRendered rendImage = (ImageRendered)src;
/*    */     
/* 44 */     Graphics2DImagePainterImpl painter = new Graphics2DImagePainterImpl(rendImage);
/* 45 */     ImageGraphics2D g2dImage = new ImageGraphics2D(src.getInfo(), painter);
/* 46 */     return g2dImage;
/*    */   }
/*    */   
/*    */   static class Graphics2DImagePainterImpl
/*    */     implements Graphics2DImagePainter {
/*    */     public Graphics2DImagePainterImpl(ImageRendered rendImage) {
/* 52 */       this.rendImage = rendImage;
/*    */     } ImageRendered rendImage;
/*    */     public Dimension getImageSize() {
/* 55 */       return this.rendImage.getSize().getDimensionMpt();
/*    */     }
/*    */     public void paint(Graphics2D g2d, Rectangle2D area) {
/* 58 */       RenderedImage ri = this.rendImage.getRenderedImage();
/* 59 */       double w = area.getWidth();
/* 60 */       double h = area.getHeight();
/*    */       
/* 62 */       AffineTransform at = new AffineTransform();
/* 63 */       at.translate(area.getX(), area.getY());
/*    */       
/* 65 */       double sx = w / ri.getWidth();
/* 66 */       double sy = h / ri.getHeight();
/* 67 */       if (sx != 1.0D || sy != 1.0D) {
/* 68 */         at.scale(sx, sy);
/*    */       }
/* 70 */       g2d.drawRenderedImage(ri, at);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getSourceFlavor() {
/* 76 */     return ImageFlavor.RENDERED_IMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 81 */     return ImageFlavor.GRAPHICS2D;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageConverterBitmap2G2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */