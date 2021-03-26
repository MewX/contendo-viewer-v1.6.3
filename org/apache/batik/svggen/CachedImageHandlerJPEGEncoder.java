/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*    */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*    */ import org.apache.batik.ext.awt.image.spi.ImageWriterRegistry;
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
/*    */ public class CachedImageHandlerJPEGEncoder
/*    */   extends DefaultCachedImageHandler
/*    */ {
/*    */   public static final String CACHED_JPEG_PREFIX = "jpegImage";
/*    */   public static final String CACHED_JPEG_SUFFIX = ".jpg";
/* 39 */   protected String refPrefix = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CachedImageHandlerJPEGEncoder(String imageDir, String urlRoot) throws SVGGraphics2DIOException {
/* 50 */     this.refPrefix = urlRoot + "/";
/* 51 */     setImageCacher(new ImageCacher.External(imageDir, "jpegImage", ".jpg"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeImage(BufferedImage buf, OutputStream os) throws IOException {
/* 61 */     ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/jpeg");
/*    */     
/* 63 */     ImageWriterParams params = new ImageWriterParams();
/* 64 */     params.setJPEGQuality(1.0F, false);
/* 65 */     writer.writeImage(buf, os, params);
/*    */   }
/*    */   
/*    */   public int getBufferedImageType() {
/* 69 */     return 1;
/*    */   }
/*    */   
/*    */   public String getRefPrefix() {
/* 73 */     return this.refPrefix;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/CachedImageHandlerJPEGEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */