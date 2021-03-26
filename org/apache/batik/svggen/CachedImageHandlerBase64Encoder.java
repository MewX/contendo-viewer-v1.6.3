/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
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
/*     */ public class CachedImageHandlerBase64Encoder
/*     */   extends DefaultCachedImageHandler
/*     */ {
/*     */   public CachedImageHandlerBase64Encoder() {
/*  46 */     setImageCacher(new ImageCacher.Embedded());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElement(SVGGeneratorContext generatorContext) {
/*  57 */     Element imageElement = generatorContext.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "use");
/*     */ 
/*     */ 
/*     */     
/*  61 */     return imageElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRefPrefix() {
/*  66 */     return "";
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected AffineTransform handleTransform(Element imageElement, double x, double y, double srcWidth, double srcHeight, double dstWidth, double dstHeight, SVGGeneratorContext generatorContext) {
/*  85 */     AffineTransform af = new AffineTransform();
/*  86 */     double hRatio = dstWidth / srcWidth;
/*  87 */     double vRatio = dstHeight / srcHeight;
/*     */     
/*  89 */     af.translate(x, y);
/*     */     
/*  91 */     if (hRatio != 1.0D || vRatio != 1.0D) {
/*  92 */       af.scale(hRatio, vRatio);
/*     */     }
/*     */     
/*  95 */     if (!af.isIdentity()) {
/*  96 */       return af;
/*     */     }
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encodeImage(BufferedImage buf, OutputStream os) throws IOException {
/* 107 */     Base64EncoderStream b64Encoder = new Base64EncoderStream(os);
/* 108 */     ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/png");
/*     */     
/* 110 */     writer.writeImage(buf, (OutputStream)b64Encoder);
/* 111 */     b64Encoder.close();
/*     */   }
/*     */   
/*     */   public int getBufferedImageType() {
/* 115 */     return 2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/CachedImageHandlerBase64Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */