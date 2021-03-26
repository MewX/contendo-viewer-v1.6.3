/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterRegistry;
/*     */ import org.apache.batik.util.Base64EncoderStream;
/*     */ import org.w3c.dom.Element;
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
/*     */ 
/*     */ 
/*     */ public class ImageHandlerBase64Encoder
/*     */   extends DefaultImageHandler
/*     */ {
/*     */   public void handleHREF(Image image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/*  62 */     if (image == null) {
/*  63 */       throw new SVGGraphics2DRuntimeException("image should not be null");
/*     */     }
/*  65 */     int width = image.getWidth(null);
/*  66 */     int height = image.getHeight(null);
/*     */     
/*  68 */     if (width == 0 || height == 0) {
/*  69 */       handleEmptyImage(imageElement);
/*     */     }
/*  71 */     else if (image instanceof RenderedImage) {
/*  72 */       handleHREF((RenderedImage)image, imageElement, generatorContext);
/*     */     } else {
/*     */       
/*  75 */       BufferedImage buf = new BufferedImage(width, height, 2);
/*     */ 
/*     */ 
/*     */       
/*  79 */       Graphics2D g = buf.createGraphics();
/*  80 */       g.drawImage(image, 0, 0, (ImageObserver)null);
/*  81 */       g.dispose();
/*  82 */       handleHREF(buf, imageElement, generatorContext);
/*     */     } 
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
/*     */   public void handleHREF(RenderableImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/*  95 */     if (image == null) {
/*  96 */       throw new SVGGraphics2DRuntimeException("image should not be null");
/*     */     }
/*     */     
/*  99 */     RenderedImage r = image.createDefaultRendering();
/* 100 */     if (r == null) {
/* 101 */       handleEmptyImage(imageElement);
/*     */     } else {
/* 103 */       handleHREF(r, imageElement, generatorContext);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleEmptyImage(Element imageElement) {
/* 108 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "data:image/png;base64,");
/*     */     
/* 110 */     imageElement.setAttributeNS(null, "width", "0");
/* 111 */     imageElement.setAttributeNS(null, "height", "0");
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
/*     */ 
/*     */   
/*     */   public void handleHREF(RenderedImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 127 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 128 */     Base64EncoderStream b64Encoder = new Base64EncoderStream(os);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 133 */       encodeImage(image, (OutputStream)b64Encoder);
/*     */ 
/*     */       
/* 136 */       b64Encoder.close();
/* 137 */     } catch (IOException e) {
/*     */       
/* 139 */       throw new SVGGraphics2DIOException("unexpected exception", e);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "data:image/png;base64," + os.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encodeImage(RenderedImage buf, OutputStream os) throws SVGGraphics2DIOException {
/*     */     try {
/* 155 */       ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/png");
/*     */       
/* 157 */       writer.writeImage(buf, os);
/* 158 */     } catch (IOException e) {
/*     */       
/* 160 */       throw new SVGGraphics2DIOException("unexpected exception");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage buildBufferedImage(Dimension size) {
/* 169 */     return new BufferedImage(size.width, size.height, 2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ImageHandlerBase64Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */