/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
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
/*     */ public class SimpleImageHandler
/*     */   implements ErrorConstants, GenericImageHandler, SVGSyntax
/*     */ {
/*     */   static final String XLINK_NAMESPACE_URI = "http://www.w3.org/1999/xlink";
/*     */   protected ImageHandler imageHandler;
/*     */   
/*     */   public SimpleImageHandler(ImageHandler imageHandler) {
/*  54 */     if (imageHandler == null) {
/*  55 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  58 */     this.imageHandler = imageHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDOMTreeManager(DOMTreeManager domTreeManager) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElement(SVGGeneratorContext generatorContext) {
/*  75 */     Element imageElement = generatorContext.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "image");
/*     */ 
/*     */ 
/*     */     
/*  79 */     return imageElement;
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
/*     */   public AffineTransform handleImage(Image image, Element imageElement, int x, int y, int width, int height, SVGGeneratorContext generatorContext) {
/*  91 */     int imageWidth = image.getWidth(null);
/*  92 */     int imageHeight = image.getHeight(null);
/*  93 */     if (imageWidth == 0 || imageHeight == 0 || width == 0 || height == 0) {
/*     */ 
/*     */ 
/*     */       
/*  97 */       handleEmptyImage(imageElement);
/*     */     } else {
/*     */       
/* 100 */       this.imageHandler.handleImage(image, imageElement, generatorContext);
/* 101 */       setImageAttributes(imageElement, x, y, width, height, generatorContext);
/*     */     } 
/*     */     
/* 104 */     return null;
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
/*     */   public AffineTransform handleImage(RenderedImage image, Element imageElement, int x, int y, int width, int height, SVGGeneratorContext generatorContext) {
/* 116 */     int imageWidth = image.getWidth();
/* 117 */     int imageHeight = image.getHeight();
/*     */     
/* 119 */     if (imageWidth == 0 || imageHeight == 0 || width == 0 || height == 0) {
/*     */ 
/*     */ 
/*     */       
/* 123 */       handleEmptyImage(imageElement);
/*     */     } else {
/*     */       
/* 126 */       this.imageHandler.handleImage(image, imageElement, generatorContext);
/* 127 */       setImageAttributes(imageElement, x, y, width, height, generatorContext);
/*     */     } 
/*     */     
/* 130 */     return null;
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
/*     */   public AffineTransform handleImage(RenderableImage image, Element imageElement, double x, double y, double width, double height, SVGGeneratorContext generatorContext) {
/* 142 */     double imageWidth = image.getWidth();
/* 143 */     double imageHeight = image.getHeight();
/*     */     
/* 145 */     if (imageWidth == 0.0D || imageHeight == 0.0D || width == 0.0D || height == 0.0D) {
/*     */ 
/*     */ 
/*     */       
/* 149 */       handleEmptyImage(imageElement);
/*     */     } else {
/*     */       
/* 152 */       this.imageHandler.handleImage(image, imageElement, generatorContext);
/* 153 */       setImageAttributes(imageElement, x, y, width, height, generatorContext);
/*     */     } 
/* 155 */     return null;
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
/*     */   protected void setImageAttributes(Element imageElement, double x, double y, double width, double height, SVGGeneratorContext generatorContext) {
/* 168 */     imageElement.setAttributeNS(null, "x", generatorContext.doubleString(x));
/*     */ 
/*     */     
/* 171 */     imageElement.setAttributeNS(null, "y", generatorContext.doubleString(y));
/*     */ 
/*     */     
/* 174 */     imageElement.setAttributeNS(null, "width", generatorContext.doubleString(width));
/*     */ 
/*     */     
/* 177 */     imageElement.setAttributeNS(null, "height", generatorContext.doubleString(height));
/*     */ 
/*     */     
/* 180 */     imageElement.setAttributeNS(null, "preserveAspectRatio", "none");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleEmptyImage(Element imageElement) {
/* 186 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "");
/*     */     
/* 188 */     imageElement.setAttributeNS(null, "width", "0");
/* 189 */     imageElement.setAttributeNS(null, "height", "0");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SimpleImageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */