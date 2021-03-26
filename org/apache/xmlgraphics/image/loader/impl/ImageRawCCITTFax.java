/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
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
/*    */ public class ImageRawCCITTFax
/*    */   extends ImageRawStream
/*    */ {
/*    */   private int compression;
/*    */   
/*    */   public ImageRawCCITTFax(ImageInfo info, InputStream in, int compression) {
/* 42 */     super(info, ImageFlavor.RAW_CCITTFAX, in);
/* 43 */     this.compression = compression;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ColorSpace getColorSpace() {
/* 51 */     return ColorSpace.getInstance(1003);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCompression() {
/* 59 */     return this.compression;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageRawCCITTFax.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */