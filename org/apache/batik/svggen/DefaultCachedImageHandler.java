/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
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
/*     */ public abstract class DefaultCachedImageHandler
/*     */   implements CachedImageHandler, ErrorConstants, SVGSyntax
/*     */ {
/*     */   static final String XLINK_NAMESPACE_URI = "http://www.w3.org/1999/xlink";
/*  53 */   static final AffineTransform IDENTITY = new AffineTransform();
/*     */ 
/*     */   
/*  56 */   private static Method createGraphics = null;
/*     */   private static boolean initDone = false;
/*  58 */   private static final Class[] paramc = new Class[] { BufferedImage.class };
/*  59 */   private static Object[] paramo = null;
/*     */ 
/*     */   
/*     */   protected ImageCacher imageCacher;
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageCacher getImageCacher() {
/*  67 */     return this.imageCacher;
/*     */   }
/*     */   
/*     */   void setImageCacher(ImageCacher imageCacher) {
/*  71 */     if (imageCacher == null) {
/*  72 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/*  76 */     DOMTreeManager dtm = null;
/*  77 */     if (this.imageCacher != null) {
/*  78 */       dtm = this.imageCacher.getDOMTreeManager();
/*     */     }
/*     */     
/*  81 */     this.imageCacher = imageCacher;
/*  82 */     if (dtm != null) {
/*  83 */       this.imageCacher.setDOMTreeManager(dtm);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDOMTreeManager(DOMTreeManager domTreeManager) {
/*  92 */     this.imageCacher.setDOMTreeManager(domTreeManager);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Graphics2D createGraphics(BufferedImage buf) {
/* 102 */     if (!initDone) {
/*     */       try {
/* 104 */         Class<?> clazz = Class.forName("org.apache.batik.ext.awt.image.GraphicsUtil");
/* 105 */         createGraphics = clazz.getMethod("createGraphics", paramc);
/* 106 */         paramo = new Object[1];
/* 107 */       } catch (Throwable throwable) {
/*     */       
/*     */       } finally {
/* 110 */         initDone = true;
/*     */       } 
/*     */     }
/* 113 */     if (createGraphics == null) {
/* 114 */       return buf.createGraphics();
/*     */     }
/* 116 */     paramo[0] = buf;
/* 117 */     Graphics2D g2d = null;
/*     */     try {
/* 119 */       g2d = (Graphics2D)createGraphics.invoke(null, paramo);
/* 120 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 123 */     return g2d;
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
/*     */   public Element createElement(SVGGeneratorContext generatorContext) {
/* 136 */     Element imageElement = generatorContext.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "image");
/*     */ 
/*     */ 
/*     */     
/* 140 */     return imageElement;
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
/* 152 */     int imageWidth = image.getWidth(null);
/* 153 */     int imageHeight = image.getHeight(null);
/* 154 */     AffineTransform af = null;
/*     */     
/* 156 */     if (imageWidth == 0 || imageHeight == 0 || width == 0 || height == 0) {
/*     */ 
/*     */ 
/*     */       
/* 160 */       handleEmptyImage(imageElement);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/* 165 */         handleHREF(image, imageElement, generatorContext);
/* 166 */       } catch (SVGGraphics2DIOException e) {
/*     */         try {
/* 168 */           generatorContext.errorHandler.handleError(e);
/* 169 */         } catch (SVGGraphics2DIOException io) {
/*     */ 
/*     */           
/* 172 */           throw new SVGGraphics2DRuntimeException(io);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       af = handleTransform(imageElement, x, y, imageWidth, imageHeight, width, height, generatorContext);
/*     */     } 
/*     */     
/* 182 */     return af;
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
/* 194 */     int imageWidth = image.getWidth();
/* 195 */     int imageHeight = image.getHeight();
/* 196 */     AffineTransform af = null;
/*     */     
/* 198 */     if (imageWidth == 0 || imageHeight == 0 || width == 0 || height == 0) {
/*     */ 
/*     */ 
/*     */       
/* 202 */       handleEmptyImage(imageElement);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/* 207 */         handleHREF(image, imageElement, generatorContext);
/* 208 */       } catch (SVGGraphics2DIOException e) {
/*     */         try {
/* 210 */           generatorContext.errorHandler.handleError(e);
/* 211 */         } catch (SVGGraphics2DIOException io) {
/*     */ 
/*     */           
/* 214 */           throw new SVGGraphics2DRuntimeException(io);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       af = handleTransform(imageElement, x, y, imageWidth, imageHeight, width, height, generatorContext);
/*     */     } 
/*     */     
/* 224 */     return af;
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
/* 236 */     double imageWidth = image.getWidth();
/* 237 */     double imageHeight = image.getHeight();
/* 238 */     AffineTransform af = null;
/*     */     
/* 240 */     if (imageWidth == 0.0D || imageHeight == 0.0D || width == 0.0D || height == 0.0D) {
/*     */ 
/*     */ 
/*     */       
/* 244 */       handleEmptyImage(imageElement);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/* 249 */         handleHREF(image, imageElement, generatorContext);
/* 250 */       } catch (SVGGraphics2DIOException e) {
/*     */         try {
/* 252 */           generatorContext.errorHandler.handleError(e);
/* 253 */         } catch (SVGGraphics2DIOException io) {
/*     */ 
/*     */           
/* 256 */           throw new SVGGraphics2DRuntimeException(io);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 263 */       af = handleTransform(imageElement, x, y, imageWidth, imageHeight, width, height, generatorContext);
/*     */     } 
/*     */ 
/*     */     
/* 267 */     return af;
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
/*     */   protected AffineTransform handleTransform(Element imageElement, double x, double y, double srcWidth, double srcHeight, double dstWidth, double dstHeight, SVGGeneratorContext generatorContext) {
/* 285 */     imageElement.setAttributeNS(null, "x", generatorContext.doubleString(x));
/*     */ 
/*     */     
/* 288 */     imageElement.setAttributeNS(null, "y", generatorContext.doubleString(y));
/*     */ 
/*     */     
/* 291 */     imageElement.setAttributeNS(null, "width", generatorContext.doubleString(dstWidth));
/*     */ 
/*     */     
/* 294 */     imageElement.setAttributeNS(null, "height", generatorContext.doubleString(dstHeight));
/*     */ 
/*     */     
/* 297 */     return null;
/*     */   }
/*     */   
/*     */   protected void handleEmptyImage(Element imageElement) {
/* 301 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "");
/*     */     
/* 303 */     imageElement.setAttributeNS(null, "width", "0");
/* 304 */     imageElement.setAttributeNS(null, "height", "0");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHREF(Image image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 314 */     if (image == null) {
/* 315 */       throw new SVGGraphics2DRuntimeException("image should not be null");
/*     */     }
/* 317 */     int width = image.getWidth(null);
/* 318 */     int height = image.getHeight(null);
/*     */     
/* 320 */     if (width == 0 || height == 0) {
/* 321 */       handleEmptyImage(imageElement);
/*     */     }
/* 323 */     else if (image instanceof RenderedImage) {
/* 324 */       handleHREF((RenderedImage)image, imageElement, generatorContext);
/*     */     } else {
/*     */       
/* 327 */       BufferedImage buf = buildBufferedImage(new Dimension(width, height));
/* 328 */       Graphics2D g = createGraphics(buf);
/* 329 */       g.drawImage(image, 0, 0, (ImageObserver)null);
/* 330 */       g.dispose();
/* 331 */       handleHREF(buf, imageElement, generatorContext);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage buildBufferedImage(Dimension size) {
/* 342 */     return new BufferedImage(size.width, size.height, getBufferedImageType());
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
/*     */   protected void handleHREF(RenderedImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 355 */     BufferedImage buf = null;
/* 356 */     if (image instanceof BufferedImage && ((BufferedImage)image).getType() == getBufferedImageType()) {
/*     */ 
/*     */       
/* 359 */       buf = (BufferedImage)image;
/*     */     } else {
/* 361 */       Dimension size = new Dimension(image.getWidth(), image.getHeight());
/* 362 */       buf = buildBufferedImage(size);
/*     */       
/* 364 */       Graphics2D g = createGraphics(buf);
/*     */       
/* 366 */       g.drawRenderedImage(image, IDENTITY);
/* 367 */       g.dispose();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 373 */     cacheBufferedImage(imageElement, buf, generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleHREF(RenderableImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 384 */     Dimension size = new Dimension((int)Math.ceil(image.getWidth()), (int)Math.ceil(image.getHeight()));
/*     */     
/* 386 */     BufferedImage buf = buildBufferedImage(size);
/*     */     
/* 388 */     Graphics2D g = createGraphics(buf);
/*     */     
/* 390 */     g.drawRenderableImage(image, IDENTITY);
/* 391 */     g.dispose();
/*     */     
/* 393 */     handleHREF(buf, imageElement, generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void cacheBufferedImage(Element imageElement, BufferedImage buf, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/*     */     ByteArrayOutputStream os;
/* 403 */     if (generatorContext == null) {
/* 404 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null");
/*     */     }
/*     */     try {
/* 407 */       os = new ByteArrayOutputStream();
/*     */       
/* 409 */       encodeImage(buf, os);
/* 410 */       os.flush();
/* 411 */       os.close();
/* 412 */     } catch (IOException e) {
/*     */       
/* 414 */       throw new SVGGraphics2DIOException("unexpected exception", e);
/*     */     } 
/*     */ 
/*     */     
/* 418 */     String ref = this.imageCacher.lookup(os, buf.getWidth(), buf.getHeight(), generatorContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 424 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", getRefPrefix() + ref);
/*     */   }
/*     */   
/*     */   public abstract String getRefPrefix();
/*     */   
/*     */   public abstract void encodeImage(BufferedImage paramBufferedImage, OutputStream paramOutputStream) throws IOException;
/*     */   
/*     */   public abstract int getBufferedImageType();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/DefaultCachedImageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */