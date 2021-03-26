/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import org.apache.batik.util.XMLConstants;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultImageHandler
/*     */   implements ErrorConstants, ImageHandler, XMLConstants
/*     */ {
/*     */   public void handleImage(Image image, Element imageElement, SVGGeneratorContext generatorContext) {
/*  56 */     imageElement.setAttributeNS(null, "width", String.valueOf(image.getWidth(null)));
/*  57 */     imageElement.setAttributeNS(null, "height", String.valueOf(image.getHeight(null)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  63 */       handleHREF(image, imageElement, generatorContext);
/*  64 */     } catch (SVGGraphics2DIOException e) {
/*     */       try {
/*  66 */         generatorContext.errorHandler.handleError(e);
/*  67 */       } catch (SVGGraphics2DIOException io) {
/*     */ 
/*     */         
/*  70 */         throw new SVGGraphics2DRuntimeException(io);
/*     */       } 
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
/*     */   public void handleImage(RenderedImage image, Element imageElement, SVGGeneratorContext generatorContext) {
/*  84 */     imageElement.setAttributeNS(null, "width", String.valueOf(image.getWidth()));
/*  85 */     imageElement.setAttributeNS(null, "height", String.valueOf(image.getHeight()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  91 */       handleHREF(image, imageElement, generatorContext);
/*  92 */     } catch (SVGGraphics2DIOException e) {
/*     */       try {
/*  94 */         generatorContext.errorHandler.handleError(e);
/*  95 */       } catch (SVGGraphics2DIOException io) {
/*     */ 
/*     */         
/*  98 */         throw new SVGGraphics2DRuntimeException(io);
/*     */       } 
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
/*     */   public void handleImage(RenderableImage image, Element imageElement, SVGGeneratorContext generatorContext) {
/* 112 */     imageElement.setAttributeNS(null, "width", String.valueOf(image.getWidth()));
/* 113 */     imageElement.setAttributeNS(null, "height", String.valueOf(image.getHeight()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 119 */       handleHREF(image, imageElement, generatorContext);
/* 120 */     } catch (SVGGraphics2DIOException e) {
/*     */       try {
/* 122 */         generatorContext.errorHandler.handleError(e);
/* 123 */       } catch (SVGGraphics2DIOException io) {
/*     */ 
/*     */         
/* 126 */         throw new SVGGraphics2DRuntimeException(io);
/*     */       } 
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
/*     */   protected void handleHREF(Image image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 139 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", image.toString());
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
/*     */   protected void handleHREF(RenderedImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 151 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", image.toString());
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
/*     */   protected void handleHREF(RenderableImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 163 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", image.toString());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/DefaultImageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */