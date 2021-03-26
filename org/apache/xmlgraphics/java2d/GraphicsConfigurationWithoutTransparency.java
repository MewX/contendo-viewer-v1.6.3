/*    */ package org.apache.xmlgraphics.java2d;
/*    */ 
/*    */ import java.awt.GraphicsDevice;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.ColorModel;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
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
/*    */ public class GraphicsConfigurationWithoutTransparency
/*    */   extends AbstractGraphicsConfiguration
/*    */ {
/* 36 */   private static final Log LOG = LogFactory.getLog(GraphicsConfigurationWithoutTransparency.class);
/*    */ 
/*    */   
/* 39 */   private static final BufferedImage BI_WITHOUT_ALPHA = new BufferedImage(1, 1, 1);
/*    */ 
/*    */   
/* 42 */   private final GraphicsConfigurationWithTransparency defaultDelegate = new GraphicsConfigurationWithTransparency();
/*    */ 
/*    */   
/*    */   public GraphicsDevice getDevice() {
/* 46 */     return new GenericGraphicsDevice(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public BufferedImage createCompatibleImage(int width, int height) {
/* 51 */     return this.defaultDelegate.createCompatibleImage(width, height, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public BufferedImage createCompatibleImage(int width, int height, int transparency) {
/* 56 */     if (transparency != 1) {
/* 57 */       LOG.warn("Does not support transparencies (alpha channels) in images");
/*    */     }
/* 59 */     return this.defaultDelegate.createCompatibleImage(width, height, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ColorModel getColorModel() {
/* 64 */     return BI_WITHOUT_ALPHA.getColorModel();
/*    */   }
/*    */ 
/*    */   
/*    */   public ColorModel getColorModel(int transparency) {
/* 69 */     if (transparency == 1) {
/* 70 */       LOG.warn("Does not support transparencies (alpha channels) in images");
/*    */     }
/* 72 */     return getColorModel();
/*    */   }
/*    */ 
/*    */   
/*    */   public AffineTransform getDefaultTransform() {
/* 77 */     return this.defaultDelegate.getDefaultTransform();
/*    */   }
/*    */ 
/*    */   
/*    */   public AffineTransform getNormalizingTransform() {
/* 82 */     return this.defaultDelegate.getNormalizingTransform();
/*    */   }
/*    */ 
/*    */   
/*    */   public Rectangle getBounds() {
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/GraphicsConfigurationWithoutTransparency.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */