/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.image.BufferedImage;
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
/*    */ public class ImageBuffered
/*    */   extends ImageRendered
/*    */ {
/*    */   public ImageBuffered(ImageInfo info, BufferedImage buffered, Color transparentColor) {
/* 40 */     super(info, buffered, transparentColor);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getFlavor() {
/* 45 */     return ImageFlavor.BUFFERED_IMAGE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage getBufferedImage() {
/* 53 */     return (BufferedImage)getRenderedImage();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageBuffered.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */