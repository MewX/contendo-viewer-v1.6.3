/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Locale;
/*     */ import org.w3c.dom.Document;
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
/*     */ public class SVGGeneratorContext
/*     */   implements ErrorConstants
/*     */ {
/*     */   Document domFactory;
/*     */   ImageHandler imageHandler;
/*     */   GenericImageHandler genericImageHandler;
/*     */   ExtensionHandler extensionHandler;
/*     */   SVGIDGenerator idGenerator;
/*     */   StyleHandler styleHandler;
/*     */   String generatorComment;
/*     */   ErrorHandler errorHandler;
/*     */   boolean svgFont = false;
/*     */   GraphicContextDefaults gcDefaults;
/* 112 */   int precision = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected DecimalFormat decimalFormat = decimalFormats[this.precision];
/*     */ 
/*     */   
/*     */   public static class GraphicContextDefaults
/*     */   {
/*     */     protected Paint paint;
/*     */     
/*     */     protected Stroke stroke;
/*     */     
/*     */     protected Composite composite;
/*     */     
/*     */     protected Shape clip;
/*     */     
/*     */     protected RenderingHints hints;
/*     */     
/*     */     protected Font font;
/*     */     
/*     */     protected Color background;
/*     */     
/*     */     public void setStroke(Stroke stroke) {
/* 137 */       this.stroke = stroke;
/*     */     }
/*     */     
/*     */     public Stroke getStroke() {
/* 141 */       return this.stroke;
/*     */     }
/*     */     
/*     */     public void setComposite(Composite composite) {
/* 145 */       this.composite = composite;
/*     */     }
/*     */     
/*     */     public Composite getComposite() {
/* 149 */       return this.composite;
/*     */     }
/*     */     
/*     */     public void setClip(Shape clip) {
/* 153 */       this.clip = clip;
/*     */     }
/*     */     
/*     */     public Shape getClip() {
/* 157 */       return this.clip;
/*     */     }
/*     */     
/*     */     public void setRenderingHints(RenderingHints hints) {
/* 161 */       this.hints = hints;
/*     */     }
/*     */     
/*     */     public RenderingHints getRenderingHints() {
/* 165 */       return this.hints;
/*     */     }
/*     */     
/*     */     public void setFont(Font font) {
/* 169 */       this.font = font;
/*     */     }
/*     */     
/*     */     public Font getFont() {
/* 173 */       return this.font;
/*     */     }
/*     */     
/*     */     public void setBackground(Color background) {
/* 177 */       this.background = background;
/*     */     }
/*     */     
/*     */     public Color getBackground() {
/* 181 */       return this.background;
/*     */     }
/*     */     
/*     */     public void setPaint(Paint paint) {
/* 185 */       this.paint = paint;
/*     */     }
/*     */     
/*     */     public Paint getPaint() {
/* 189 */       return this.paint;
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
/*     */ 
/*     */   
/*     */   protected SVGGeneratorContext(Document domFactory) {
/* 205 */     setDOMFactory(domFactory);
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
/*     */   public static SVGGeneratorContext createDefault(Document domFactory) {
/* 219 */     SVGGeneratorContext ctx = new SVGGeneratorContext(domFactory);
/* 220 */     ctx.setIDGenerator(new SVGIDGenerator());
/* 221 */     ctx.setExtensionHandler(new DefaultExtensionHandler());
/* 222 */     ctx.setImageHandler(new ImageHandlerBase64Encoder());
/* 223 */     ctx.setStyleHandler(new DefaultStyleHandler());
/* 224 */     ctx.setComment("Generated by the Batik Graphics2D SVG Generator");
/* 225 */     ctx.setErrorHandler(new DefaultErrorHandler());
/* 226 */     return ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final GraphicContextDefaults getGraphicContextDefaults() {
/* 234 */     return this.gcDefaults;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setGraphicContextDefaults(GraphicContextDefaults gcDefaults) {
/* 243 */     this.gcDefaults = gcDefaults;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final SVGIDGenerator getIDGenerator() {
/* 251 */     return this.idGenerator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIDGenerator(SVGIDGenerator idGenerator) {
/* 259 */     if (idGenerator == null)
/* 260 */       throw new SVGGraphics2DRuntimeException("idGenerator should not be null"); 
/* 261 */     this.idGenerator = idGenerator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Document getDOMFactory() {
/* 269 */     return this.domFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setDOMFactory(Document domFactory) {
/* 277 */     if (domFactory == null)
/* 278 */       throw new SVGGraphics2DRuntimeException("domFactory should not be null"); 
/* 279 */     this.domFactory = domFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ExtensionHandler getExtensionHandler() {
/* 287 */     return this.extensionHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setExtensionHandler(ExtensionHandler extensionHandler) {
/* 295 */     if (extensionHandler == null)
/* 296 */       throw new SVGGraphics2DRuntimeException("extensionHandler should not be null"); 
/* 297 */     this.extensionHandler = extensionHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ImageHandler getImageHandler() {
/* 305 */     return this.imageHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setImageHandler(ImageHandler imageHandler) {
/* 313 */     if (imageHandler == null)
/* 314 */       throw new SVGGraphics2DRuntimeException("imageHandler should not be null"); 
/* 315 */     this.imageHandler = imageHandler;
/* 316 */     this.genericImageHandler = new SimpleImageHandler(imageHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setGenericImageHandler(GenericImageHandler genericImageHandler) {
/* 324 */     if (genericImageHandler == null) {
/* 325 */       throw new SVGGraphics2DRuntimeException("imageHandler should not be null");
/*     */     }
/* 327 */     this.imageHandler = null;
/* 328 */     this.genericImageHandler = genericImageHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final StyleHandler getStyleHandler() {
/* 336 */     return this.styleHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setStyleHandler(StyleHandler styleHandler) {
/* 344 */     if (styleHandler == null)
/* 345 */       throw new SVGGraphics2DRuntimeException("styleHandler should not be null"); 
/* 346 */     this.styleHandler = styleHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getComment() {
/* 353 */     return this.generatorComment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setComment(String generatorComment) {
/* 361 */     this.generatorComment = generatorComment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ErrorHandler getErrorHandler() {
/* 369 */     return this.errorHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setErrorHandler(ErrorHandler errorHandler) {
/* 377 */     if (errorHandler == null)
/* 378 */       throw new SVGGraphics2DRuntimeException("errorHandler should not be null"); 
/* 379 */     this.errorHandler = errorHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isEmbeddedFontsOn() {
/* 387 */     return this.svgFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setEmbeddedFontsOn(boolean svgFont) {
/* 395 */     this.svgFont = svgFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getPrecision() {
/* 402 */     return this.precision;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setPrecision(int precision) {
/* 412 */     if (precision < 0) {
/* 413 */       this.precision = 0;
/* 414 */     } else if (precision > 12) {
/* 415 */       this.precision = 12;
/*     */     } else {
/* 417 */       this.precision = precision;
/*     */     } 
/* 419 */     this.decimalFormat = decimalFormats[this.precision];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String doubleString(double value) {
/* 427 */     double absvalue = Math.abs(value);
/*     */ 
/*     */     
/* 430 */     if (absvalue >= 1.0E8D || (int)value == value) {
/* 431 */       return Integer.toString((int)value);
/*     */     }
/*     */ 
/*     */     
/* 435 */     return this.decimalFormat.format(value);
/*     */   }
/*     */ 
/*     */   
/* 439 */   protected static DecimalFormatSymbols dsf = new DecimalFormatSymbols(Locale.US);
/*     */ 
/*     */   
/* 442 */   protected static DecimalFormat[] decimalFormats = new DecimalFormat[13];
/*     */   
/*     */   static {
/* 445 */     decimalFormats[0] = new DecimalFormat("#", dsf);
/*     */     
/* 447 */     String format = "#.";
/* 448 */     for (int i = 1; i < decimalFormats.length; i++) {
/* 449 */       format = format + "#";
/* 450 */       decimalFormats[i] = new DecimalFormat(format, dsf);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGeneratorContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */