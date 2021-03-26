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
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
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
/*     */ public abstract class AbstractImageHandlerEncoder
/*     */   extends DefaultImageHandler
/*     */ {
/*  49 */   private static final AffineTransform IDENTITY = new AffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private String imageDir = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private String urlRoot = "";
/*     */ 
/*     */   
/*  62 */   private static Method createGraphics = null;
/*     */   private static boolean initDone = false;
/*  64 */   private static final Class[] paramc = new Class[] { BufferedImage.class };
/*  65 */   private static Object[] paramo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Graphics2D createGraphics(BufferedImage buf) {
/*  74 */     if (!initDone) {
/*     */       try {
/*  76 */         Class<?> clazz = Class.forName("org.apache.batik.ext.awt.image.GraphicsUtil");
/*  77 */         createGraphics = clazz.getMethod("createGraphics", paramc);
/*  78 */         paramo = new Object[1];
/*  79 */       } catch (ThreadDeath td) {
/*  80 */         throw td;
/*  81 */       } catch (Throwable throwable) {
/*     */       
/*     */       } finally {
/*  84 */         initDone = true;
/*     */       } 
/*     */     }
/*  87 */     if (createGraphics == null) {
/*  88 */       return buf.createGraphics();
/*     */     }
/*  90 */     paramo[0] = buf;
/*  91 */     Graphics2D g2d = null;
/*     */     try {
/*  93 */       g2d = (Graphics2D)createGraphics.invoke(null, paramo);
/*  94 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  97 */     return g2d;
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
/*     */   public AbstractImageHandlerEncoder(String imageDir, String urlRoot) throws SVGGraphics2DIOException {
/* 110 */     if (imageDir == null) {
/* 111 */       throw new SVGGraphics2DRuntimeException("imageDir should not be null");
/*     */     }
/* 113 */     File imageDirFile = new File(imageDir);
/* 114 */     if (!imageDirFile.exists()) {
/* 115 */       throw new SVGGraphics2DRuntimeException("imageDir does not exist");
/*     */     }
/* 117 */     this.imageDir = imageDir;
/* 118 */     if (urlRoot != null) {
/* 119 */       this.urlRoot = urlRoot;
/*     */     } else {
/*     */       try {
/* 122 */         this.urlRoot = imageDirFile.toURI().toURL().toString();
/* 123 */       } catch (MalformedURLException e) {
/* 124 */         throw new SVGGraphics2DIOException("cannot convert imageDir to a URL value : " + e.getMessage(), e);
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
/*     */   
/*     */   protected void handleHREF(Image image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 139 */     Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
/*     */     
/* 141 */     BufferedImage buf = buildBufferedImage(size);
/*     */     
/* 143 */     Graphics2D g = createGraphics(buf);
/*     */     
/* 145 */     g.drawImage(image, 0, 0, (ImageObserver)null);
/* 146 */     g.dispose();
/*     */ 
/*     */     
/* 149 */     saveBufferedImageToFile(imageElement, buf, generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleHREF(RenderedImage image, Element imageElement, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 160 */     Dimension size = new Dimension(image.getWidth(), image.getHeight());
/* 161 */     BufferedImage buf = buildBufferedImage(size);
/*     */     
/* 163 */     Graphics2D g = createGraphics(buf);
/*     */     
/* 165 */     g.drawRenderedImage(image, IDENTITY);
/* 166 */     g.dispose();
/*     */ 
/*     */     
/* 169 */     saveBufferedImageToFile(imageElement, buf, generatorContext);
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
/* 180 */     Dimension size = new Dimension((int)Math.ceil(image.getWidth()), (int)Math.ceil(image.getHeight()));
/*     */     
/* 182 */     BufferedImage buf = buildBufferedImage(size);
/*     */     
/* 184 */     Graphics2D g = createGraphics(buf);
/*     */     
/* 186 */     g.drawRenderableImage(image, IDENTITY);
/* 187 */     g.dispose();
/*     */ 
/*     */     
/* 190 */     saveBufferedImageToFile(imageElement, buf, generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveBufferedImageToFile(Element imageElement, BufferedImage buf, SVGGeneratorContext generatorContext) throws SVGGraphics2DIOException {
/* 197 */     if (generatorContext == null) {
/* 198 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null");
/*     */     }
/*     */     
/* 201 */     File imageFile = null;
/*     */ 
/*     */ 
/*     */     
/* 205 */     while (imageFile == null) {
/* 206 */       String fileId = generatorContext.idGenerator.generateID(getPrefix());
/* 207 */       imageFile = new File(this.imageDir, fileId + getSuffix());
/* 208 */       if (imageFile.exists()) {
/* 209 */         imageFile = null;
/*     */       }
/*     */     } 
/*     */     
/* 213 */     encodeImage(buf, imageFile);
/*     */ 
/*     */     
/* 216 */     imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", this.urlRoot + "/" + imageFile.getName());
/*     */   }
/*     */   
/*     */   public abstract String getSuffix();
/*     */   
/*     */   public abstract String getPrefix();
/*     */   
/*     */   public abstract void encodeImage(BufferedImage paramBufferedImage, File paramFile) throws SVGGraphics2DIOException;
/*     */   
/*     */   public abstract BufferedImage buildBufferedImage(Dimension paramDimension);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/AbstractImageHandlerEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */