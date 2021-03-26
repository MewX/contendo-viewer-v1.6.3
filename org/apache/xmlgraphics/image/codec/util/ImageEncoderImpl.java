/*    */ package org.apache.xmlgraphics.image.codec.util;
/*    */ 
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.Raster;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ImageEncoderImpl
/*    */   implements ImageEncoder
/*    */ {
/*    */   protected OutputStream output;
/*    */   protected ImageEncodeParam param;
/*    */   
/*    */   public ImageEncoderImpl(OutputStream output, ImageEncodeParam param) {
/* 49 */     this.output = output;
/* 50 */     this.param = param;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageEncodeParam getParam() {
/* 61 */     return this.param;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParam(ImageEncodeParam param) {
/* 73 */     this.param = param;
/*    */   }
/*    */ 
/*    */   
/*    */   public OutputStream getOutputStream() {
/* 78 */     return this.output;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(Raster ras, ColorModel cm) throws IOException {
/* 86 */     RenderedImage im = new SingleTileRenderedImage(ras, cm);
/* 87 */     encode(im);
/*    */   }
/*    */   
/*    */   public abstract void encode(RenderedImage paramRenderedImage) throws IOException;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/ImageEncoderImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */