/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
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
/*    */ public class CachedImageHandlerPNGEncoder
/*    */   extends DefaultCachedImageHandler
/*    */ {
/*    */   public static final String CACHED_PNG_PREFIX = "pngImage";
/*    */   public static final String CACHED_PNG_SUFFIX = ".png";
/* 38 */   protected String refPrefix = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CachedImageHandlerPNGEncoder(String imageDir, String urlRoot) throws SVGGraphics2DIOException {
/* 49 */     this.refPrefix = urlRoot + "/";
/* 50 */     setImageCacher(new ImageCacher.External(imageDir, "pngImage", ".png"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeImage(BufferedImage buf, OutputStream os) throws IOException {
/* 61 */     ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/png");
/*    */     
/* 63 */     writer.writeImage(buf, os);
/*    */   }
/*    */   
/*    */   public int getBufferedImageType() {
/* 67 */     return 2;
/*    */   }
/*    */   
/*    */   public String getRefPrefix() {
/* 71 */     return this.refPrefix;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/CachedImageHandlerPNGEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */