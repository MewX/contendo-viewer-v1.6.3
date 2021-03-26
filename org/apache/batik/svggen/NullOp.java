/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.BufferedImageOp;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.ImageObserver;
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
/*    */ class NullOp
/*    */   implements BufferedImageOp
/*    */ {
/*    */   public BufferedImage filter(BufferedImage src, BufferedImage dest) {
/* 35 */     Graphics2D g = dest.createGraphics();
/* 36 */     g.drawImage(src, 0, 0, (ImageObserver)null);
/* 37 */     g.dispose();
/* 38 */     return dest;
/*    */   }
/*    */   
/*    */   public Rectangle2D getBounds2D(BufferedImage src) {
/* 42 */     return new Rectangle(0, 0, src.getWidth(), src.getHeight());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
/* 51 */     BufferedImage dest = null;
/* 52 */     if (destCM == null) {
/* 53 */       destCM = src.getColorModel();
/*    */     }
/* 55 */     dest = new BufferedImage(destCM, destCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()), destCM.isAlphaPremultiplied(), null);
/*    */ 
/*    */     
/* 58 */     return dest;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Point2D getPoint2D(Point2D srcPt, Point2D destPt) {
/* 68 */     if (destPt == null)
/* 69 */       destPt = new Point2D.Double(); 
/* 70 */     destPt.setLocation(srcPt.getX(), srcPt.getY());
/* 71 */     return destPt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderingHints getRenderingHints() {
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/NullOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */