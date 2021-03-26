/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterRegistry;
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
/*     */ public class ImageHandlerJPEGEncoder
/*     */   extends AbstractImageHandlerEncoder
/*     */ {
/*     */   public ImageHandlerJPEGEncoder(String imageDir, String urlRoot) throws SVGGraphics2DIOException {
/*  54 */     super(imageDir, urlRoot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getSuffix() {
/*  62 */     return ".jpg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getPrefix() {
/*  70 */     return "jpegImage";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encodeImage(BufferedImage buf, File imageFile) throws SVGGraphics2DIOException {
/*     */     try {
/*  80 */       OutputStream os = new FileOutputStream(imageFile);
/*     */       try {
/*  82 */         ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/jpeg");
/*     */         
/*  84 */         ImageWriterParams params = new ImageWriterParams();
/*  85 */         params.setJPEGQuality(1.0F, false);
/*  86 */         writer.writeImage(buf, os, params);
/*     */       } finally {
/*     */         
/*  89 */         os.close();
/*     */       } 
/*  91 */     } catch (IOException e) {
/*  92 */       throw new SVGGraphics2DIOException("could not write image File " + imageFile.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage buildBufferedImage(Dimension size) {
/* 101 */     return new BufferedImage(size.width, size.height, 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ImageHandlerJPEGEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */