/*     */ package org.apache.xmlgraphics.java2d;
/*     */ 
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsConfigurationWithTransparency
/*     */   extends AbstractGraphicsConfiguration
/*     */ {
/*  35 */   private static final BufferedImage BI_WITH_ALPHA = new BufferedImage(1, 1, 2);
/*     */ 
/*     */   
/*  38 */   private static final BufferedImage BI_WITHOUT_ALPHA = new BufferedImage(1, 1, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage createCompatibleImage(int width, int height, int transparency) {
/*  51 */     if (transparency == 1) {
/*  52 */       return new BufferedImage(width, height, 1);
/*     */     }
/*  54 */     return new BufferedImage(width, height, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage createCompatibleImage(int width, int height) {
/*  66 */     return new BufferedImage(width, height, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/*  84 */     return BI_WITH_ALPHA.getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel(int transparency) {
/*  94 */     if (transparency == 1) {
/*  95 */       return BI_WITHOUT_ALPHA.getColorModel();
/*     */     }
/*  97 */     return BI_WITH_ALPHA.getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getDefaultTransform() {
/* 107 */     return new AffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getNormalizingTransform() {
/* 118 */     return new AffineTransform(2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsDevice getDevice() {
/* 127 */     return new GenericGraphicsDevice(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/GraphicsConfigurationWithTransparency.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */