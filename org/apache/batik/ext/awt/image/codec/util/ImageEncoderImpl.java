/*    */ package org.apache.batik.ext.awt.image.codec.util;
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
/*    */ 
/*    */ 
/*    */ public abstract class ImageEncoderImpl
/*    */   implements ImageEncoder
/*    */ {
/*    */   protected OutputStream output;
/*    */   protected ImageEncodeParam param;
/*    */   
/*    */   public ImageEncoderImpl(OutputStream output, ImageEncodeParam param) {
/* 51 */     this.output = output;
/* 52 */     this.param = param;
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
/* 63 */     return this.param;
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
/* 75 */     this.param = param;
/*    */   }
/*    */ 
/*    */   
/*    */   public OutputStream getOutputStream() {
/* 80 */     return this.output;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(Raster ras, ColorModel cm) throws IOException {
/* 88 */     RenderedImage im = new SingleTileRenderedImage(ras, cm);
/* 89 */     encode(im);
/*    */   }
/*    */   
/*    */   public abstract void encode(RenderedImage paramRenderedImage) throws IOException;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/ImageEncoderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */