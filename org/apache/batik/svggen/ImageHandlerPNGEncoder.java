/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
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
/*    */ public class ImageHandlerPNGEncoder
/*    */   extends AbstractImageHandlerEncoder
/*    */ {
/*    */   public ImageHandlerPNGEncoder(String imageDir, String urlRoot) throws SVGGraphics2DIOException {
/* 53 */     super(imageDir, urlRoot);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String getSuffix() {
/* 61 */     return ".png";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String getPrefix() {
/* 69 */     return "pngImage";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeImage(BufferedImage buf, File imageFile) throws SVGGraphics2DIOException {
/*    */     try {
/* 79 */       OutputStream os = new FileOutputStream(imageFile);
/*    */       try {
/* 81 */         ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/png");
/*    */         
/* 83 */         writer.writeImage(buf, os);
/*    */       } finally {
/*    */         
/* 86 */         os.close();
/*    */       } 
/* 88 */     } catch (IOException e) {
/* 89 */       throw new SVGGraphics2DIOException("could not write image File " + imageFile.getName());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage buildBufferedImage(Dimension size) {
/* 98 */     return new BufferedImage(size.width, size.height, 2);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ImageHandlerPNGEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */