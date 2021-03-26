/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.GraphicsDevice;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.VolatileImage;
/*    */ 
/*    */ class e
/*    */   extends GraphicsConfiguration {
/* 13 */   private static BufferedImage b = new BufferedImage(1, 1, 2);
/*    */   
/* 15 */   private static BufferedImage c = new BufferedImage(1, 1, 1);
/*    */   
/* 17 */   public static final e a = new e();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage createCompatibleImage(int width, int height, int transparency) {
/* 24 */     if (transparency == 1) {
/* 25 */       return new BufferedImage(width, height, 1);
/*    */     }
/* 27 */     return new BufferedImage(width, height, 2);
/*    */   }
/*    */   
/*    */   public BufferedImage createCompatibleImage(int width, int height) {
/* 31 */     return new BufferedImage(width, height, 2);
/*    */   }
/*    */   
/*    */   public Rectangle getBounds() {
/* 35 */     return null;
/*    */   }
/*    */   
/*    */   public ColorModel getColorModel() {
/* 39 */     return b.getColorModel();
/*    */   }
/*    */   
/*    */   public ColorModel getColorModel(int transparency) {
/* 43 */     if (transparency == 1) {
/* 44 */       return c.getColorModel();
/*    */     }
/* 46 */     return b.getColorModel();
/*    */   }
/*    */   
/*    */   public AffineTransform getDefaultTransform() {
/* 50 */     return new AffineTransform();
/*    */   }
/*    */   
/*    */   public AffineTransform getNormalizingTransform() {
/* 54 */     return new AffineTransform(2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   public GraphicsDevice getDevice() {
/* 58 */     return new f(this);
/*    */   }
/*    */   
/*    */   public VolatileImage createCompatibleVolatileImage(int width, int height) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public VolatileImage createCompatibleVolatileImage(int width, int height, int transparency) {
/* 66 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */