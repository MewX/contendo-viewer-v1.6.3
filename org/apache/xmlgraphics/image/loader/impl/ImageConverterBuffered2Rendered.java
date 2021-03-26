/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
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
/*    */ public class ImageConverterBuffered2Rendered
/*    */   extends AbstractImageConverter
/*    */ {
/*    */   public Image convert(Image src, Map hints) {
/* 35 */     checkSourceFlavor(src);
/* 36 */     assert src instanceof ImageBuffered;
/* 37 */     ImageBuffered buffered = (ImageBuffered)src;
/* 38 */     return new ImageRendered(buffered.getInfo(), buffered.getRenderedImage(), buffered.getTransparentColor());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageFlavor getSourceFlavor() {
/* 44 */     return ImageFlavor.BUFFERED_IMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 49 */     return ImageFlavor.RENDERED_IMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getConversionPenalty() {
/* 54 */     return 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageConverterBuffered2Rendered.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */