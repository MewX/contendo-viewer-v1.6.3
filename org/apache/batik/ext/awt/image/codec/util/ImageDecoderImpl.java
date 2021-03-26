/*     */ package org.apache.batik.ext.awt.image.codec.util;
/*     */ 
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public abstract class ImageDecoderImpl
/*     */   implements ImageDecoder
/*     */ {
/*     */   protected SeekableStream input;
/*     */   protected ImageDecodeParam param;
/*     */   
/*     */   public ImageDecoderImpl(SeekableStream input, ImageDecodeParam param) {
/*  53 */     this.input = input;
/*  54 */     this.param = param;
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
/*     */ 
/*     */   
/*     */   public ImageDecoderImpl(InputStream input, ImageDecodeParam param) {
/*  68 */     this.input = new ForwardSeekableStream(input);
/*  69 */     this.param = param;
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
/*     */   public ImageDecodeParam getParam() {
/*  81 */     return this.param;
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
/*     */ 
/*     */   
/*     */   public void setParam(ImageDecodeParam param) {
/*  95 */     this.param = param;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SeekableStream getInputStream() {
/* 103 */     return this.input;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumPages() throws IOException {
/* 112 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster decodeAsRaster() throws IOException {
/* 122 */     return decodeAsRaster(0);
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
/*     */ 
/*     */   
/*     */   public Raster decodeAsRaster(int page) throws IOException {
/* 136 */     RenderedImage im = decodeAsRenderedImage(page);
/* 137 */     return im.getData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage decodeAsRenderedImage() throws IOException {
/* 147 */     return decodeAsRenderedImage(0);
/*     */   }
/*     */   
/*     */   public abstract RenderedImage decodeAsRenderedImage(int paramInt) throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/ImageDecoderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */