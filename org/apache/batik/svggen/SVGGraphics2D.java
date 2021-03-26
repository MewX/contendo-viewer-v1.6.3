/*      */ package org.apache.batik.svggen;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.GlyphVector;
/*      */ import java.awt.font.TextAttribute;
/*      */ import java.awt.font.TextLayout;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.BufferedImageOp;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Writer;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.batik.ext.awt.g2d.AbstractGraphics2D;
/*      */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentFragment;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SVGGraphics2D
/*      */   extends AbstractGraphics2D
/*      */   implements Cloneable, ErrorConstants, SVGSyntax
/*      */ {
/*      */   public static final String DEFAULT_XML_ENCODING = "ISO-8859-1";
/*      */   public static final int DEFAULT_MAX_GC_OVERRIDES = 3;
/*      */   protected DOMTreeManager domTreeManager;
/*      */   protected DOMGroupManager domGroupManager;
/*      */   protected SVGGeneratorContext generatorCtx;
/*      */   protected SVGShape shapeConverter;
/*      */   protected Dimension svgCanvasSize;
/*      */   protected Graphics2D fmg;
/*      */   protected Set unsupportedAttributes;
/*      */   
/*      */   public final Dimension getSVGCanvasSize() {
/*  160 */     return this.svgCanvasSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setSVGCanvasSize(Dimension svgCanvasSize) {
/*  170 */     this.svgCanvasSize = new Dimension(svgCanvasSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final SVGGeneratorContext getGeneratorContext() {
/*  177 */     return this.generatorCtx;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final SVGShape getShapeConverter() {
/*  185 */     return this.shapeConverter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DOMTreeManager getDOMTreeManager() {
/*  192 */     return this.domTreeManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setDOMTreeManager(DOMTreeManager treeMgr) {
/*  200 */     this.domTreeManager = treeMgr;
/*  201 */     this.generatorCtx.genericImageHandler.setDOMTreeManager(this.domTreeManager);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final DOMGroupManager getDOMGroupManager() {
/*  208 */     return this.domGroupManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setDOMGroupManager(DOMGroupManager groupMgr) {
/*  216 */     this.domGroupManager = groupMgr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Document getDOMFactory() {
/*  224 */     return this.generatorCtx.domFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final ImageHandler getImageHandler() {
/*  231 */     return this.generatorCtx.imageHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final GenericImageHandler getGenericImageHandler() {
/*  238 */     return this.generatorCtx.genericImageHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final ExtensionHandler getExtensionHandler() {
/*  245 */     return this.generatorCtx.extensionHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setExtensionHandler(ExtensionHandler extensionHandler) {
/*  253 */     this.generatorCtx.setExtensionHandler(extensionHandler);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGGraphics2D(Document domFactory) {
/*  262 */     this(SVGGeneratorContext.createDefault(domFactory), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGGraphics2D(Document domFactory, ImageHandler imageHandler, ExtensionHandler extensionHandler, boolean textAsShapes) {
/*  281 */     this(buildSVGGeneratorContext(domFactory, imageHandler, extensionHandler), textAsShapes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SVGGeneratorContext buildSVGGeneratorContext(Document domFactory, ImageHandler imageHandler, ExtensionHandler extensionHandler) {
/*  296 */     SVGGeneratorContext generatorCtx = new SVGGeneratorContext(domFactory);
/*  297 */     generatorCtx.setIDGenerator(new SVGIDGenerator());
/*  298 */     generatorCtx.setExtensionHandler(extensionHandler);
/*  299 */     generatorCtx.setImageHandler(imageHandler);
/*  300 */     generatorCtx.setStyleHandler(new DefaultStyleHandler());
/*  301 */     generatorCtx.setComment("Generated by the Batik Graphics2D SVG Generator");
/*  302 */     generatorCtx.setErrorHandler(new DefaultErrorHandler());
/*      */     
/*  304 */     return generatorCtx;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGGraphics2D(SVGGeneratorContext generatorCtx, boolean textAsShapes)
/*      */   {
/*  318 */     super(textAsShapes);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     BufferedImage bi = new BufferedImage(1, 1, 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     this.fmg = bi.createGraphics();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1496 */     this.unsupportedAttributes = new HashSet();
/* 1497 */     this.unsupportedAttributes.add(TextAttribute.BACKGROUND);
/* 1498 */     this.unsupportedAttributes.add(TextAttribute.BIDI_EMBEDDING);
/* 1499 */     this.unsupportedAttributes.add(TextAttribute.CHAR_REPLACEMENT);
/* 1500 */     this.unsupportedAttributes.add(TextAttribute.JUSTIFICATION);
/* 1501 */     this.unsupportedAttributes.add(TextAttribute.RUN_DIRECTION);
/* 1502 */     this.unsupportedAttributes.add(TextAttribute.SUPERSCRIPT);
/* 1503 */     this.unsupportedAttributes.add(TextAttribute.SWAP_COLORS);
/* 1504 */     this.unsupportedAttributes.add(TextAttribute.TRANSFORM);
/* 1505 */     this.unsupportedAttributes.add(TextAttribute.WIDTH); if (generatorCtx == null) throw new SVGGraphics2DRuntimeException("generatorContext should not be null");  setGeneratorContext(generatorCtx); } protected void setGeneratorContext(SVGGeneratorContext generatorCtx) { this.generatorCtx = generatorCtx; this.gc = new GraphicContext(new AffineTransform()); SVGGeneratorContext.GraphicContextDefaults gcDefaults = generatorCtx.getGraphicContextDefaults(); if (gcDefaults != null) { if (gcDefaults.getPaint() != null) this.gc.setPaint(gcDefaults.getPaint());  if (gcDefaults.getStroke() != null) this.gc.setStroke(gcDefaults.getStroke());  if (gcDefaults.getComposite() != null) this.gc.setComposite(gcDefaults.getComposite());  if (gcDefaults.getClip() != null) this.gc.setClip(gcDefaults.getClip());  if (gcDefaults.getRenderingHints() != null) this.gc.setRenderingHints(gcDefaults.getRenderingHints());  if (gcDefaults.getFont() != null) this.gc.setFont(gcDefaults.getFont());  if (gcDefaults.getBackground() != null) this.gc.setBackground(gcDefaults.getBackground());  }  this.shapeConverter = new SVGShape(generatorCtx); this.domTreeManager = new DOMTreeManager(this.gc, generatorCtx, 3); this.domGroupManager = new DOMGroupManager(this.gc, this.domTreeManager); this.domTreeManager.addGroupManager(this.domGroupManager); generatorCtx.genericImageHandler.setDOMTreeManager(this.domTreeManager); } public SVGGraphics2D(SVGGraphics2D g) { super(g); BufferedImage bi = new BufferedImage(1, 1, 2); this.fmg = bi.createGraphics(); this.unsupportedAttributes = new HashSet(); this.unsupportedAttributes.add(TextAttribute.BACKGROUND); this.unsupportedAttributes.add(TextAttribute.BIDI_EMBEDDING); this.unsupportedAttributes.add(TextAttribute.CHAR_REPLACEMENT); this.unsupportedAttributes.add(TextAttribute.JUSTIFICATION); this.unsupportedAttributes.add(TextAttribute.RUN_DIRECTION); this.unsupportedAttributes.add(TextAttribute.SUPERSCRIPT); this.unsupportedAttributes.add(TextAttribute.SWAP_COLORS); this.unsupportedAttributes.add(TextAttribute.TRANSFORM); this.unsupportedAttributes.add(TextAttribute.WIDTH); this.generatorCtx = g.generatorCtx; this.gc.validateTransformStack(); this.shapeConverter = g.shapeConverter; this.domTreeManager = g.domTreeManager; this.domGroupManager = new DOMGroupManager(this.gc, this.domTreeManager); this.domTreeManager.addGroupManager(this.domGroupManager); }
/*      */   public void stream(String svgFileName) throws SVGGraphics2DIOException { stream(svgFileName, false); }
/*      */   public void stream(String svgFileName, boolean useCss) throws SVGGraphics2DIOException { try { OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(svgFileName), "ISO-8859-1"); stream(writer, useCss); writer.flush(); writer.close(); } catch (SVGGraphics2DIOException io) { throw io; } catch (IOException e) { this.generatorCtx.errorHandler.handleError(new SVGGraphics2DIOException(e)); }  }
/*      */   public void stream(Writer writer) throws SVGGraphics2DIOException { stream(writer, false); }
/*      */   public void stream(Writer writer, boolean useCss, boolean escaped) throws SVGGraphics2DIOException { Element svgRoot = getRoot(); stream(svgRoot, writer, useCss, escaped); }
/*      */   public void stream(Writer writer, boolean useCss) throws SVGGraphics2DIOException { Element svgRoot = getRoot(); stream(svgRoot, writer, useCss, false); }
/*      */   public void stream(Element svgRoot, Writer writer) throws SVGGraphics2DIOException { stream(svgRoot, writer, false, false); }
/*      */   public void stream(Element svgRoot, Writer writer, boolean useCss, boolean escaped) throws SVGGraphics2DIOException { Node rootParent = svgRoot.getParentNode(); Node nextSibling = svgRoot.getNextSibling(); try { svgRoot.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "http://www.w3.org/2000/svg"); svgRoot.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink"); DocumentFragment svgDocument = svgRoot.getOwnerDocument().createDocumentFragment(); svgDocument.appendChild(svgRoot); if (useCss) SVGCSSStyler.style(svgDocument);  XmlWriter.writeXml(svgDocument, writer, escaped); writer.flush(); } catch (SVGGraphics2DIOException e) { this.generatorCtx.errorHandler.handleError(e); } catch (IOException io) { this.generatorCtx.errorHandler.handleError(new SVGGraphics2DIOException(io)); } finally { if (rootParent != null) if (nextSibling == null) { rootParent.appendChild(svgRoot); } else { rootParent.insertBefore(svgRoot, nextSibling); }   }  }
/*      */   public List getDefinitionSet() { return this.domTreeManager.getDefinitionSet(); }
/*      */   public Element getTopLevelGroup() { return getTopLevelGroup(true); }
/*      */   public Element getTopLevelGroup(boolean includeDefinitionSet) { return this.domTreeManager.getTopLevelGroup(includeDefinitionSet); }
/*      */   public void setTopLevelGroup(Element topLevelGroup) { this.domTreeManager.setTopLevelGroup(topLevelGroup); }
/*      */   public Element getRoot() { return getRoot((Element)null); }
/*      */   public Element getRoot(Element svgRoot) { svgRoot = this.domTreeManager.getRoot(svgRoot); if (this.svgCanvasSize != null) { svgRoot.setAttributeNS((String)null, "width", String.valueOf(this.svgCanvasSize.width)); svgRoot.setAttributeNS((String)null, "height", String.valueOf(this.svgCanvasSize.height)); }  return svgRoot; }
/*      */   public Graphics create() { return (Graphics)new SVGGraphics2D(this); }
/* 1520 */   public void setXORMode(Color c1) { this.generatorCtx.errorHandler.handleError(new SVGGraphics2DRuntimeException("XOR Mode is not supported by Graphics2D SVG Generator")); } public FontMetrics getFontMetrics(Font f) { return this.fmg.getFontMetrics(f); } public void copyArea(int x, int y, int width, int height, int dx, int dy) {} public boolean drawImage(Image img, int x, int y, ImageObserver observer) { Element imageElement = getGenericImageHandler().createElement(getGeneratorContext()); AffineTransform xform = getGenericImageHandler().handleImage(img, imageElement, x, y, img.getWidth(null), img.getHeight(null), getGeneratorContext()); if (xform == null) { this.domGroupManager.addElement(imageElement); } else { AffineTransform inverseTransform = null; try { inverseTransform = xform.createInverse(); } catch (NoninvertibleTransformException e) { throw new SVGGraphics2DRuntimeException("unexpected exception"); }  this.gc.transform(xform); this.domGroupManager.addElement(imageElement); this.gc.transform(inverseTransform); }  return true; } public void setUnsupportedAttributes(Set<?> attrs) { if (attrs == null) { this.unsupportedAttributes = null; }
/* 1521 */     else { this.unsupportedAttributes = new HashSet(attrs); }  }
/*      */   public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) { Element imageElement = getGenericImageHandler().createElement(getGeneratorContext()); AffineTransform xform = getGenericImageHandler().handleImage(img, imageElement, x, y, width, height, getGeneratorContext()); if (xform == null) { this.domGroupManager.addElement(imageElement); } else { AffineTransform inverseTransform = null; try { inverseTransform = xform.createInverse(); } catch (NoninvertibleTransformException e) { throw new SVGGraphics2DRuntimeException("unexpected exception"); }  this.gc.transform(xform); this.domGroupManager.addElement(imageElement); this.gc.transform(inverseTransform); }  return true; }
/*      */   public void dispose() { this.domTreeManager.removeGroupManager(this.domGroupManager); }
/*      */   public void draw(Shape s) { Stroke stroke = this.gc.getStroke(); if (stroke instanceof java.awt.BasicStroke) { Element svgShape = this.shapeConverter.toSVG(s); if (svgShape != null) this.domGroupManager.addElement(svgShape, (short)1);  } else { Shape strokedShape = stroke.createStrokedShape(s); fill(strokedShape); }  }
/* 1525 */   public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) { boolean retVal = true; if (xform == null) { retVal = drawImage(img, 0, 0, (ImageObserver)null); } else if (xform.getDeterminant() != 0.0D) { AffineTransform inverseTransform = null; try { inverseTransform = xform.createInverse(); } catch (NoninvertibleTransformException e) { throw new SVGGraphics2DRuntimeException("unexpected exception"); }  this.gc.transform(xform); retVal = drawImage(img, 0, 0, (ImageObserver)null); this.gc.transform(inverseTransform); } else { AffineTransform savTransform = new AffineTransform(this.gc.getTransform()); this.gc.transform(xform); retVal = drawImage(img, 0, 0, (ImageObserver)null); this.gc.setTransform(savTransform); }  return retVal; } public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) { img = op.filter(img, null); drawImage(img, x, y, (ImageObserver)null); } public void drawRenderedImage(RenderedImage img, AffineTransform trans2) { AffineTransform xform; Element image = getGenericImageHandler().createElement(getGeneratorContext()); AffineTransform trans1 = getGenericImageHandler().handleImage(img, image, img.getMinX(), img.getMinY(), img.getWidth(), img.getHeight(), getGeneratorContext()); if (trans2 == null) { xform = trans1; } else if (trans1 == null) { xform = trans2; } else { xform = new AffineTransform(trans2); xform.concatenate(trans1); }  if (xform == null) { this.domGroupManager.addElement(image); } else if (xform.getDeterminant() != 0.0D) { AffineTransform inverseTransform = null; try { inverseTransform = xform.createInverse(); } catch (NoninvertibleTransformException e) { throw new SVGGraphics2DRuntimeException("unexpected exception"); }  this.gc.transform(xform); this.domGroupManager.addElement(image); this.gc.transform(inverseTransform); } else { AffineTransform savTransform = new AffineTransform(this.gc.getTransform()); this.gc.transform(xform); this.domGroupManager.addElement(image); this.gc.setTransform(savTransform); }  } public void drawRenderableImage(RenderableImage img, AffineTransform trans2) { AffineTransform xform; Element image = getGenericImageHandler().createElement(getGeneratorContext()); AffineTransform trans1 = getGenericImageHandler().handleImage(img, image, img.getMinX(), img.getMinY(), img.getWidth(), img.getHeight(), getGeneratorContext()); if (trans2 == null) { xform = trans1; } else if (trans1 == null) { xform = trans2; } else { xform = new AffineTransform(trans2); xform.concatenate(trans1); }  if (xform == null) { this.domGroupManager.addElement(image); } else if (xform.getDeterminant() != 0.0D) { AffineTransform inverseTransform = null; try { inverseTransform = xform.createInverse(); } catch (NoninvertibleTransformException e) { throw new SVGGraphics2DRuntimeException("unexpected exception"); }  this.gc.transform(xform); this.domGroupManager.addElement(image); this.gc.transform(inverseTransform); } else { AffineTransform savTransform = new AffineTransform(this.gc.getTransform()); this.gc.transform(xform); this.domGroupManager.addElement(image); this.gc.setTransform(savTransform); }  } public void drawString(String s, float x, float y) { if (this.textAsShapes) { GlyphVector gv = getFont().createGlyphVector(getFontRenderContext(), s); drawGlyphVector(gv, x, y); return; }  if (this.generatorCtx.svgFont) this.domTreeManager.gcConverter.getFontConverter().recordFontUsage(s, getFont());  AffineTransform savTxf = getTransform(); AffineTransform txtTxf = transformText(x, y); Element text = getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "text"); text.setAttributeNS((String)null, "x", this.generatorCtx.doubleString(x)); text.setAttributeNS((String)null, "y", this.generatorCtx.doubleString(y)); text.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", "preserve"); text.appendChild(getDOMFactory().createTextNode(s)); this.domGroupManager.addElement(text, (short)16); if (txtTxf != null) setTransform(savTxf);  } private AffineTransform transformText(float x, float y) { AffineTransform txtTxf = null; Font font = getFont(); if (font != null) { txtTxf = font.getTransform(); if (txtTxf != null && !txtTxf.isIdentity()) { AffineTransform t = new AffineTransform(); t.translate(x, y); t.concatenate(txtTxf); t.translate(-x, -y); transform(t); } else { txtTxf = null; }  }  return txtTxf; } public void drawString(AttributedCharacterIterator ati, float x, float y) { if (this.textAsShapes || usesUnsupportedAttributes(ati)) { TextLayout layout = new TextLayout(ati, getFontRenderContext()); layout.draw((Graphics2D)this, x, y); return; }  boolean multiSpans = false; if (ati.getRunLimit() < ati.getEndIndex()) multiSpans = true;  Element text = getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "text"); text.setAttributeNS((String)null, "x", this.generatorCtx.doubleString(x)); text.setAttributeNS((String)null, "y", this.generatorCtx.doubleString(y)); text.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", "preserve"); Font baseFont = getFont(); Paint basePaint = getPaint(); char ch = ati.first(); setTextElementFill(ati); setTextFontAttributes(ati, baseFont); SVGGraphicContext textGC = this.domTreeManager.getGraphicContextConverter().toSVG(this.gc); this.domGroupManager.addElement(text, (short)16); textGC.getContext().put("stroke", "none"); textGC.getGroupContext().put("stroke", "none"); boolean firstSpan = true; AffineTransform savTxf = getTransform(); AffineTransform txtTxf = null; while (ch != Character.MAX_VALUE) { Element tspan = text; if (multiSpans) { tspan = getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "tspan"); text.appendChild(tspan); }  setTextElementFill(ati); boolean resetTransform = setTextFontAttributes(ati, baseFont); if (resetTransform || firstSpan) { txtTxf = transformText(x, y); firstSpan = false; }  int start = ati.getIndex(); int end = ati.getRunLimit() - 1; StringBuffer buf = new StringBuffer(end - start); buf.append(ch); for (int i = start; i < end; i++) { ch = ati.next(); buf.append(ch); }  String s = buf.toString(); if (this.generatorCtx.isEmbeddedFontsOn()) getDOMTreeManager().getGraphicContextConverter().getFontConverter().recordFontUsage(s, getFont());  SVGGraphicContext elementGC = this.domTreeManager.gcConverter.toSVG(this.gc); elementGC.getGroupContext().put("stroke", "none"); SVGGraphicContext deltaGC = DOMGroupManager.processDeltaGC(elementGC, textGC); setTextElementAttributes(deltaGC, ati); this.domTreeManager.getStyleHandler().setStyle(tspan, deltaGC.getContext(), this.domTreeManager.getGeneratorContext()); tspan.appendChild(getDOMFactory().createTextNode(s)); if ((resetTransform || firstSpan) && txtTxf != null) setTransform(savTxf);  ch = ati.next(); }  setFont(baseFont); setPaint(basePaint); } public void fill(Shape s) { Element svgShape = this.shapeConverter.toSVG(s); if (svgShape != null) this.domGroupManager.addElement(svgShape, (short)16);  } private boolean setTextFontAttributes(AttributedCharacterIterator ati, Font baseFont) { boolean resetTransform = false; if (ati.getAttribute(TextAttribute.FONT) != null || ati.getAttribute(TextAttribute.FAMILY) != null || ati.getAttribute(TextAttribute.WEIGHT) != null || ati.getAttribute(TextAttribute.POSTURE) != null || ati.getAttribute(TextAttribute.SIZE) != null) { Map<AttributedCharacterIterator.Attribute, Object> m = ati.getAttributes(); Font f = baseFont.deriveFont(m); setFont(f); resetTransform = true; }  return resetTransform; } private void setTextElementFill(AttributedCharacterIterator ati) { if (ati.getAttribute(TextAttribute.FOREGROUND) != null) { Color color = (Color)ati.getAttribute(TextAttribute.FOREGROUND); setPaint(color); }  } private void setTextElementAttributes(SVGGraphicContext tspanGC, AttributedCharacterIterator ati) { String decoration = ""; if (isUnderline(ati)) decoration = decoration + "underline ";  if (isStrikeThrough(ati)) decoration = decoration + "line-through ";  int len = decoration.length(); if (len != 0) tspanGC.getContext().put("text-decoration", decoration.substring(0, len - 1));  } private boolean isBold(AttributedCharacterIterator ati) { Object weight = ati.getAttribute(TextAttribute.WEIGHT); if (weight == null) return false;  if (weight.equals(TextAttribute.WEIGHT_REGULAR)) return false;  if (weight.equals(TextAttribute.WEIGHT_DEMILIGHT)) return false;  if (weight.equals(TextAttribute.WEIGHT_EXTRA_LIGHT)) return false;  if (weight.equals(TextAttribute.WEIGHT_LIGHT)) return false;  return true; } private boolean isItalic(AttributedCharacterIterator ati) { Object attr = ati.getAttribute(TextAttribute.POSTURE); if (TextAttribute.POSTURE_OBLIQUE.equals(attr)) return true;  return false; } private boolean isUnderline(AttributedCharacterIterator ati) { Object attr = ati.getAttribute(TextAttribute.UNDERLINE); return TextAttribute.UNDERLINE_ON.equals(attr); } private boolean isStrikeThrough(AttributedCharacterIterator ati) { Object attr = ati.getAttribute(TextAttribute.STRIKETHROUGH); if (TextAttribute.STRIKETHROUGH_ON.equals(attr)) return true;  return false; } public GraphicsConfiguration getDeviceConfiguration() { return null; } public boolean usesUnsupportedAttributes(AttributedCharacterIterator aci) { if (this.unsupportedAttributes == null) return false;
/*      */     
/* 1527 */     Set<AttributedCharacterIterator.Attribute> allAttrs = aci.getAllAttributeKeys();
/* 1528 */     for (AttributedCharacterIterator.Attribute allAttr : allAttrs) {
/* 1529 */       if (this.unsupportedAttributes.contains(allAttr)) {
/* 1530 */         return true;
/*      */       }
/*      */     } 
/* 1533 */     return false; }
/*      */ 
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGraphics2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */