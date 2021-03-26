/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.spi.ImageConverter;
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
/*    */ public abstract class AbstractImageConverter
/*    */   implements ImageConverter
/*    */ {
/*    */   protected void checkSourceFlavor(Image img) {
/* 35 */     if (!getSourceFlavor().equals(img.getFlavor())) {
/* 36 */       throw new IllegalArgumentException("Incompatible image: " + img);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getConversionPenalty() {
/* 42 */     return 10;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/AbstractImageConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */