/*      */ package org.apache.pdfbox.rendering;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.TexturePaint;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.Stack;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.pdmodel.PDResources;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*      */ import org.apache.pdfbox.pdmodel.common.function.PDFunction;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*      */ import org.apache.pdfbox.pdmodel.font.PDCIDFontType0;
/*      */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*      */ import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
/*      */ import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
/*      */ import org.apache.pdfbox.pdmodel.font.PDType0Font;
/*      */ import org.apache.pdfbox.pdmodel.font.PDType1CFont;
/*      */ import org.apache.pdfbox.pdmodel.font.PDType1Font;
/*      */ import org.apache.pdfbox.pdmodel.font.PDType3Font;
/*      */ import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDICCBased;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
/*      */ import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
/*      */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
/*      */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentMembershipDictionary;
/*      */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDShadingPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDSoftMask;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.AnnotationFilter;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*      */ import org.apache.pdfbox.util.Matrix;
/*      */ import org.apache.pdfbox.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PageDrawer
/*      */   extends PDFGraphicsStreamEngine
/*      */ {
/*  118 */   private static final Log LOG = LogFactory.getLog(PageDrawer.class);
/*      */ 
/*      */   
/*      */   private final PDFRenderer renderer;
/*      */ 
/*      */   
/*      */   private final boolean subsamplingAllowed;
/*      */ 
/*      */   
/*      */   private Graphics2D graphics;
/*      */ 
/*      */   
/*      */   private AffineTransform xform;
/*      */ 
/*      */   
/*      */   private PDRectangle pageSize;
/*      */   
/*      */   private boolean flipTG = false;
/*      */   
/*  137 */   private int clipWindingRule = -1;
/*  138 */   private GeneralPath linePath = new GeneralPath();
/*      */ 
/*      */   
/*      */   private Area lastClip;
/*      */ 
/*      */   
/*      */   private List<Shape> textClippings;
/*      */ 
/*      */   
/*  147 */   private final Map<PDFont, Glyph2D> fontGlyph2D = new HashMap<PDFont, Glyph2D>();
/*      */   
/*  149 */   private final TilingPaintFactory tilingPaintFactory = new TilingPaintFactory(this);
/*      */   
/*  151 */   private final Stack<TransparencyGroup> transparencyGroupStack = new Stack<TransparencyGroup>();
/*      */   
/*      */   private int nestedHiddenOCGCount;
/*      */   
/*      */   private final RenderDestination destination;
/*      */   
/*      */   private final RenderingHints renderingHints;
/*      */   
/*  159 */   static final int JAVA_VERSION = getJavaVersion();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   private AnnotationFilter annotationFilter = new AnnotationFilter()
/*      */     {
/*      */       
/*      */       public boolean accept(PDAnnotation annotation)
/*      */       {
/*  169 */         return true;
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PageDrawer(PageDrawerParameters parameters) throws IOException {
/*  181 */     super(parameters.getPage());
/*  182 */     this.renderer = parameters.getRenderer();
/*  183 */     this.subsamplingAllowed = parameters.isSubsamplingAllowed();
/*  184 */     this.destination = parameters.getDestination();
/*  185 */     this.renderingHints = parameters.getRenderingHints();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationFilter getAnnotationFilter() {
/*  195 */     return this.annotationFilter;
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
/*      */   public void setAnnotationFilter(AnnotationFilter annotationFilter) {
/*  207 */     this.annotationFilter = annotationFilter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final PDFRenderer getRenderer() {
/*  215 */     return this.renderer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final Graphics2D getGraphics() {
/*  223 */     return this.graphics;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final GeneralPath getLinePath() {
/*  231 */     return this.linePath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setRenderingHints() {
/*  239 */     this.graphics.addRenderingHints(this.renderingHints);
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
/*      */   public void drawPage(Graphics g, PDRectangle pageSize) throws IOException {
/*  251 */     this.graphics = (Graphics2D)g;
/*  252 */     this.xform = this.graphics.getTransform();
/*  253 */     this.pageSize = pageSize;
/*      */     
/*  255 */     setRenderingHints();
/*      */     
/*  257 */     this.graphics.translate(0.0D, pageSize.getHeight());
/*  258 */     this.graphics.scale(1.0D, -1.0D);
/*      */ 
/*      */     
/*  261 */     this.graphics.translate(-pageSize.getLowerLeftX(), -pageSize.getLowerLeftY());
/*      */     
/*  263 */     processPage(getPage());
/*      */     
/*  265 */     for (PDAnnotation annotation : getPage().getAnnotations(this.annotationFilter))
/*      */     {
/*  267 */       showAnnotation(annotation);
/*      */     }
/*      */     
/*  270 */     this.graphics = null;
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
/*      */   void drawTilingPattern(Graphics2D g, PDTilingPattern pattern, PDColorSpace colorSpace, PDColor color, Matrix patternMatrix) throws IOException {
/*  286 */     Graphics2D oldGraphics = this.graphics;
/*  287 */     this.graphics = g;
/*      */     
/*  289 */     GeneralPath oldLinePath = this.linePath;
/*  290 */     this.linePath = new GeneralPath();
/*  291 */     int oldClipWindingRule = this.clipWindingRule;
/*  292 */     this.clipWindingRule = -1;
/*      */     
/*  294 */     Area oldLastClip = this.lastClip;
/*  295 */     this.lastClip = null;
/*      */     
/*  297 */     boolean oldFlipTG = this.flipTG;
/*  298 */     this.flipTG = true;
/*      */     
/*  300 */     setRenderingHints();
/*  301 */     processTilingPattern(pattern, color, colorSpace, patternMatrix);
/*      */     
/*  303 */     this.flipTG = oldFlipTG;
/*  304 */     this.graphics = oldGraphics;
/*  305 */     this.linePath = oldLinePath;
/*  306 */     this.lastClip = oldLastClip;
/*  307 */     this.clipWindingRule = oldClipWindingRule;
/*      */   }
/*      */ 
/*      */   
/*      */   private float clampColor(float color) {
/*  312 */     return (color < 0.0F) ? 0.0F : ((color > 1.0F) ? 1.0F : color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Paint getPaint(PDColor color) throws IOException {
/*  323 */     PDColorSpace colorSpace = color.getColorSpace();
/*  324 */     if (!(colorSpace instanceof PDPattern)) {
/*      */       
/*  326 */       float[] rgb = colorSpace.toRGB(color.getComponents());
/*  327 */       return new Color(clampColor(rgb[0]), clampColor(rgb[1]), clampColor(rgb[2]));
/*      */     } 
/*      */ 
/*      */     
/*  331 */     PDPattern patternSpace = (PDPattern)colorSpace;
/*  332 */     PDAbstractPattern pattern = patternSpace.getPattern(color);
/*  333 */     if (pattern instanceof PDTilingPattern) {
/*      */       
/*  335 */       PDTilingPattern tilingPattern = (PDTilingPattern)pattern;
/*      */       
/*  337 */       if (tilingPattern.getPaintType() == 1)
/*      */       {
/*      */         
/*  340 */         return this.tilingPaintFactory.create(tilingPattern, null, null, this.xform);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  345 */       return this.tilingPaintFactory.create(tilingPattern, patternSpace
/*  346 */           .getUnderlyingColorSpace(), color, this.xform);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  351 */     PDShadingPattern shadingPattern = (PDShadingPattern)pattern;
/*  352 */     PDShading shading = shadingPattern.getShading();
/*  353 */     if (shading == null) {
/*      */       
/*  355 */       LOG.error("shadingPattern is null, will be filled with transparency");
/*  356 */       return new Color(0, 0, 0, 0);
/*      */     } 
/*  358 */     return shading.toPaint(Matrix.concatenate(getInitialMatrix(), shadingPattern
/*  359 */           .getMatrix()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setClip() {
/*  369 */     Area clippingPath = getGraphicsState().getCurrentClippingPath();
/*  370 */     if (clippingPath != this.lastClip) {
/*      */       
/*  372 */       this.graphics.setClip(clippingPath);
/*  373 */       this.lastClip = clippingPath;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginText() throws IOException {
/*  380 */     setClip();
/*  381 */     beginTextClip();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endText() throws IOException {
/*  387 */     endTextClip();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void beginTextClip() {
/*  396 */     this.textClippings = new ArrayList<Shape>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void endTextClip() {
/*  404 */     PDGraphicsState state = getGraphicsState();
/*  405 */     RenderingMode renderingMode = state.getTextState().getRenderingMode();
/*      */ 
/*      */     
/*  408 */     if (renderingMode.isClip() && !this.textClippings.isEmpty()) {
/*      */ 
/*      */ 
/*      */       
/*  412 */       GeneralPath path = new GeneralPath();
/*  413 */       for (Shape shape : this.textClippings)
/*      */       {
/*  415 */         path.append(shape, false);
/*      */       }
/*  417 */       state.intersectClippingPath(path);
/*  418 */       this.textClippings = new ArrayList<Shape>();
/*      */ 
/*      */ 
/*      */       
/*  422 */       this.lastClip = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void showFontGlyph(Matrix textRenderingMatrix, PDFont font, int code, String unicode, Vector displacement) throws IOException {
/*  430 */     AffineTransform at = textRenderingMatrix.createAffineTransform();
/*  431 */     at.concatenate(font.getFontMatrix().createAffineTransform());
/*      */     
/*  433 */     Glyph2D glyph2D = createGlyph2D(font);
/*  434 */     drawGlyph2D(glyph2D, font, code, displacement, at);
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
/*      */   private void drawGlyph2D(Glyph2D glyph2D, PDFont font, int code, Vector displacement, AffineTransform at) throws IOException {
/*  450 */     PDGraphicsState state = getGraphicsState();
/*  451 */     RenderingMode renderingMode = state.getTextState().getRenderingMode();
/*      */     
/*  453 */     GeneralPath path = glyph2D.getPathForCharacterCode(code);
/*  454 */     if (path != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  459 */       if (!font.isEmbedded() && !font.isVertical() && !font.isStandard14() && font.hasExplicitWidth(code)) {
/*      */         
/*  461 */         float fontWidth = font.getWidthFromFont(code);
/*  462 */         if (fontWidth > 0.0F && 
/*  463 */           Math.abs(fontWidth - displacement.getX() * 1000.0F) > 1.0E-4D) {
/*      */           
/*  465 */           float pdfWidth = displacement.getX() * 1000.0F;
/*  466 */           at.scale((pdfWidth / fontWidth), 1.0D);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  471 */       Shape glyph = at.createTransformedShape(path);
/*      */       
/*  473 */       if (renderingMode.isFill()) {
/*      */         
/*  475 */         this.graphics.setComposite(state.getNonStrokingJavaComposite());
/*  476 */         this.graphics.setPaint(getNonStrokingPaint());
/*  477 */         setClip();
/*  478 */         if (isContentRendered())
/*      */         {
/*  480 */           this.graphics.fill(glyph);
/*      */         }
/*      */       } 
/*      */       
/*  484 */       if (renderingMode.isStroke()) {
/*      */         
/*  486 */         this.graphics.setComposite(state.getStrokingJavaComposite());
/*  487 */         this.graphics.setPaint(getStrokingPaint());
/*  488 */         this.graphics.setStroke(getStroke());
/*  489 */         setClip();
/*  490 */         if (isContentRendered())
/*      */         {
/*  492 */           this.graphics.draw(glyph);
/*      */         }
/*      */       } 
/*      */       
/*  496 */       if (renderingMode.isClip())
/*      */       {
/*  498 */         this.textClippings.add(glyph);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void showType3Glyph(Matrix textRenderingMatrix, PDType3Font font, int code, String unicode, Vector displacement) throws IOException {
/*  507 */     PDGraphicsState state = getGraphicsState();
/*  508 */     RenderingMode renderingMode = state.getTextState().getRenderingMode();
/*  509 */     if (!RenderingMode.NEITHER.equals(renderingMode))
/*      */     {
/*  511 */       super.showType3Glyph(textRenderingMatrix, font, code, unicode, displacement);
/*      */     }
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
/*      */   private Glyph2D createGlyph2D(PDFont font) throws IOException {
/*  524 */     Glyph2D glyph2D = this.fontGlyph2D.get(font);
/*      */     
/*  526 */     if (glyph2D != null)
/*      */     {
/*  528 */       return glyph2D;
/*      */     }
/*      */     
/*  531 */     if (font instanceof PDTrueTypeFont) {
/*      */       
/*  533 */       PDTrueTypeFont ttfFont = (PDTrueTypeFont)font;
/*  534 */       glyph2D = new TTFGlyph2D(ttfFont);
/*      */     }
/*  536 */     else if (font instanceof PDType1Font) {
/*      */       
/*  538 */       PDType1Font pdType1Font = (PDType1Font)font;
/*  539 */       glyph2D = new Type1Glyph2D((PDSimpleFont)pdType1Font);
/*      */     }
/*  541 */     else if (font instanceof PDType1CFont) {
/*      */       
/*  543 */       PDType1CFont type1CFont = (PDType1CFont)font;
/*  544 */       glyph2D = new Type1Glyph2D((PDSimpleFont)type1CFont);
/*      */     }
/*  546 */     else if (font instanceof PDType0Font) {
/*      */       
/*  548 */       PDType0Font type0Font = (PDType0Font)font;
/*  549 */       if (type0Font.getDescendantFont() instanceof org.apache.pdfbox.pdmodel.font.PDCIDFontType2)
/*      */       {
/*  551 */         glyph2D = new TTFGlyph2D(type0Font);
/*      */       }
/*  553 */       else if (type0Font.getDescendantFont() instanceof PDCIDFontType0)
/*      */       {
/*      */         
/*  556 */         PDCIDFontType0 cidType0Font = (PDCIDFontType0)type0Font.getDescendantFont();
/*  557 */         glyph2D = new CIDType0Glyph2D(cidType0Font);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  562 */       throw new IllegalStateException("Bad font type: " + font.getClass().getSimpleName());
/*      */     } 
/*      */ 
/*      */     
/*  566 */     if (glyph2D != null)
/*      */     {
/*  568 */       this.fontGlyph2D.put(font, glyph2D);
/*      */     }
/*      */     
/*  571 */     if (glyph2D == null)
/*      */     {
/*      */       
/*  574 */       throw new UnsupportedOperationException("No font for " + font.getName());
/*      */     }
/*      */     
/*  577 */     return glyph2D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendRectangle(Point2D p0, Point2D p1, Point2D p2, Point2D p3) {
/*  585 */     this.linePath.moveTo((float)p0.getX(), (float)p0.getY());
/*  586 */     this.linePath.lineTo((float)p1.getX(), (float)p1.getY());
/*  587 */     this.linePath.lineTo((float)p2.getX(), (float)p2.getY());
/*  588 */     this.linePath.lineTo((float)p3.getX(), (float)p3.getY());
/*      */ 
/*      */ 
/*      */     
/*  592 */     this.linePath.closePath();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Paint applySoftMaskToPaint(Paint parentPaint, PDSoftMask softMask) throws IOException {
/*  598 */     if (softMask == null || softMask.getGroup() == null)
/*      */     {
/*  600 */       return parentPaint;
/*      */     }
/*  602 */     PDColor backdropColor = null;
/*  603 */     if (COSName.LUMINOSITY.equals(softMask.getSubType())) {
/*      */       
/*  605 */       COSArray backdropColorArray = softMask.getBackdropColor();
/*  606 */       PDTransparencyGroup form = softMask.getGroup();
/*  607 */       PDColorSpace colorSpace = form.getGroup().getColorSpace(form.getResources());
/*  608 */       if (colorSpace != null && backdropColorArray != null)
/*      */       {
/*  610 */         backdropColor = new PDColor(backdropColorArray, colorSpace);
/*      */       }
/*      */     } 
/*      */     
/*  614 */     TransparencyGroup transparencyGroup = new TransparencyGroup(softMask.getGroup(), true, softMask.getInitialTransformationMatrix(), backdropColor);
/*  615 */     BufferedImage image = transparencyGroup.getImage();
/*  616 */     if (image == null)
/*      */     {
/*      */ 
/*      */       
/*  620 */       return parentPaint;
/*      */     }
/*  622 */     BufferedImage gray = new BufferedImage(image.getWidth(), image.getHeight(), 10);
/*  623 */     if (COSName.ALPHA.equals(softMask.getSubType())) {
/*      */       
/*  625 */       gray.setData(image.getAlphaRaster());
/*      */     }
/*  627 */     else if (COSName.LUMINOSITY.equals(softMask.getSubType())) {
/*      */       
/*  629 */       Graphics g = gray.getGraphics();
/*  630 */       g.drawImage(image, 0, 0, null);
/*  631 */       g.dispose();
/*      */     }
/*      */     else {
/*      */       
/*  635 */       throw new IOException("Invalid soft mask subtype.");
/*      */     } 
/*  637 */     gray = adjustImage(gray);
/*  638 */     Rectangle2D tpgBounds = transparencyGroup.getBounds();
/*  639 */     adjustRectangle(tpgBounds);
/*  640 */     return new SoftMask(parentPaint, gray, tpgBounds, backdropColor, softMask.getTransferFunction());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void adjustRectangle(Rectangle2D r) {
/*  651 */     Matrix m = new Matrix(this.xform);
/*  652 */     float scaleX = Math.abs(m.getScalingFactorX());
/*  653 */     float scaleY = Math.abs(m.getScalingFactorY());
/*      */     
/*  655 */     AffineTransform adjustedTransform = new AffineTransform(this.xform);
/*  656 */     adjustedTransform.scale(1.0D / scaleX, 1.0D / scaleY);
/*  657 */     r.setRect(adjustedTransform.createTransformedShape(r).getBounds2D());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private BufferedImage adjustImage(BufferedImage gray) throws IOException {
/*  663 */     AffineTransform at = new AffineTransform(this.xform);
/*  664 */     Matrix m = new Matrix(at);
/*  665 */     at.scale(1.0D / Math.abs(m.getScalingFactorX()), 1.0D / Math.abs(m.getScalingFactorY()));
/*      */     
/*  667 */     Rectangle originalBounds = new Rectangle(gray.getWidth(), gray.getHeight());
/*  668 */     Rectangle2D transformedBounds = at.createTransformedShape(originalBounds).getBounds2D();
/*  669 */     at.preConcatenate(AffineTransform.getTranslateInstance(-transformedBounds.getMinX(), 
/*  670 */           -transformedBounds.getMinY()));
/*      */     
/*  672 */     int width = (int)Math.ceil(transformedBounds.getWidth());
/*  673 */     int height = (int)Math.ceil(transformedBounds.getHeight());
/*  674 */     BufferedImage transformedGray = new BufferedImage(width, height, 10);
/*      */     
/*  676 */     Graphics2D g2 = (Graphics2D)transformedGray.getGraphics();
/*  677 */     g2.drawImage(gray, at, (ImageObserver)null);
/*  678 */     g2.dispose();
/*  679 */     return transformedGray;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Paint getStrokingPaint() throws IOException {
/*  685 */     return applySoftMaskToPaint(
/*  686 */         getPaint(getGraphicsState().getStrokingColor()), 
/*  687 */         getGraphicsState().getSoftMask());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Paint getNonStrokingPaint() throws IOException {
/*  693 */     return applySoftMaskToPaint(
/*  694 */         getPaint(getGraphicsState().getNonStrokingColor()), 
/*  695 */         getGraphicsState().getSoftMask());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private BasicStroke getStroke() {
/*  701 */     PDGraphicsState state = getGraphicsState();
/*      */ 
/*      */     
/*  704 */     float lineWidth = transformWidth(state.getLineWidth());
/*      */ 
/*      */     
/*  707 */     if (lineWidth < 0.25D)
/*      */     {
/*  709 */       lineWidth = 0.25F;
/*      */     }
/*      */     
/*  712 */     PDLineDashPattern dashPattern = state.getLineDashPattern();
/*  713 */     float phaseStart = dashPattern.getPhase();
/*  714 */     float[] dashArray = getDashArray(dashPattern);
/*  715 */     phaseStart = transformWidth(phaseStart);
/*      */ 
/*      */ 
/*      */     
/*  719 */     if (dashArray.length == 0 || Float.isInfinite(phaseStart) || Float.isNaN(phaseStart)) {
/*      */       
/*  721 */       dashArray = null;
/*      */     }
/*      */     else {
/*      */       
/*  725 */       for (int i = 0; i < dashArray.length; i++) {
/*      */         
/*  727 */         if (Float.isInfinite(dashArray[i]) || Float.isNaN(dashArray[i])) {
/*      */           
/*  729 */           dashArray = null;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  734 */     int lineCap = Math.min(2, Math.max(0, state.getLineCap()));
/*  735 */     int lineJoin = Math.min(2, Math.max(0, state.getLineJoin()));
/*  736 */     return new BasicStroke(lineWidth, lineCap, lineJoin, state
/*  737 */         .getMiterLimit(), dashArray, phaseStart);
/*      */   }
/*      */ 
/*      */   
/*      */   private float[] getDashArray(PDLineDashPattern dashPattern) {
/*  742 */     float[] dashArray = dashPattern.getDashArray();
/*  743 */     if (JAVA_VERSION < 10) {
/*      */       
/*  745 */       float scalingFactorX = (new Matrix(this.xform)).getScalingFactorX();
/*  746 */       for (int i = 0; i < dashArray.length; i++) {
/*      */ 
/*      */         
/*  749 */         float w = transformWidth(dashArray[i]);
/*      */ 
/*      */ 
/*      */         
/*  753 */         if (scalingFactorX < 0.5F)
/*      */         {
/*      */           
/*  756 */           dashArray[i] = Math.max(w, 0.2F);
/*      */         }
/*      */         else
/*      */         {
/*  760 */           dashArray[i] = Math.max(w, 0.062F);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  766 */       for (int i = 0; i < dashArray.length; i++)
/*      */       {
/*      */         
/*  769 */         dashArray[i] = transformWidth(dashArray[i]);
/*      */       }
/*      */     } 
/*  772 */     return dashArray;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void strokePath() throws IOException {
/*  778 */     this.graphics.setComposite(getGraphicsState().getStrokingJavaComposite());
/*  779 */     this.graphics.setPaint(getStrokingPaint());
/*  780 */     this.graphics.setStroke(getStroke());
/*  781 */     setClip();
/*      */     
/*  783 */     if (isContentRendered())
/*      */     {
/*  785 */       this.graphics.draw(this.linePath);
/*      */     }
/*  787 */     this.linePath.reset();
/*      */   }
/*      */ 
/*      */   
/*      */   public void fillPath(int windingRule) throws IOException {
/*      */     Shape shape;
/*  793 */     this.graphics.setComposite(getGraphicsState().getNonStrokingJavaComposite());
/*  794 */     this.graphics.setPaint(getNonStrokingPaint());
/*  795 */     setClip();
/*  796 */     this.linePath.setWindingRule(windingRule);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  802 */     Rectangle2D bounds = this.linePath.getBounds2D();
/*      */     
/*  804 */     boolean noAntiAlias = (isRectangular(this.linePath) && bounds.getWidth() > 1.0D && bounds.getHeight() > 1.0D);
/*  805 */     if (noAntiAlias)
/*      */     {
/*  807 */       this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  812 */     if (!(this.graphics.getPaint() instanceof Color)) {
/*      */ 
/*      */       
/*  815 */       Area area = new Area(this.linePath);
/*  816 */       area.intersect(new Area(this.graphics.getClip()));
/*  817 */       intersectShadingBBox(getGraphicsState().getNonStrokingColor(), area);
/*  818 */       shape = area;
/*      */     }
/*      */     else {
/*      */       
/*  822 */       shape = this.linePath;
/*      */     } 
/*  824 */     if (isContentRendered())
/*      */     {
/*  826 */       this.graphics.fill(shape);
/*      */     }
/*      */     
/*  829 */     this.linePath.reset();
/*      */     
/*  831 */     if (noAntiAlias)
/*      */     {
/*      */ 
/*      */       
/*  835 */       setRenderingHints();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void intersectShadingBBox(PDColor color, Area area) throws IOException {
/*  844 */     if (color.getColorSpace() instanceof PDPattern) {
/*      */       
/*  846 */       PDColorSpace colorSpace = color.getColorSpace();
/*  847 */       PDAbstractPattern pat = ((PDPattern)colorSpace).getPattern(color);
/*  848 */       if (pat instanceof PDShadingPattern) {
/*      */         
/*  850 */         PDShading shading = ((PDShadingPattern)pat).getShading();
/*  851 */         PDRectangle bbox = shading.getBBox();
/*  852 */         if (bbox != null) {
/*      */           
/*  854 */           Matrix m = Matrix.concatenate(getInitialMatrix(), pat.getMatrix());
/*  855 */           Area bboxArea = new Area(bbox.transform(m));
/*  856 */           area.intersect(bboxArea);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isRectangular(GeneralPath path) {
/*  867 */     PathIterator iter = path.getPathIterator((AffineTransform)null);
/*  868 */     double[] coords = new double[6];
/*  869 */     int count = 0;
/*  870 */     int[] xs = new int[4];
/*  871 */     int[] ys = new int[4];
/*  872 */     while (!iter.isDone()) {
/*      */       
/*  874 */       switch (iter.currentSegment(coords)) {
/*      */         
/*      */         case 0:
/*  877 */           if (count == 0) {
/*      */             
/*  879 */             xs[count] = (int)Math.floor(coords[0]);
/*  880 */             ys[count] = (int)Math.floor(coords[1]);
/*      */           }
/*      */           else {
/*      */             
/*  884 */             return false;
/*      */           } 
/*  886 */           count++;
/*      */           break;
/*      */         
/*      */         case 1:
/*  890 */           if (count < 4) {
/*      */             
/*  892 */             xs[count] = (int)Math.floor(coords[0]);
/*  893 */             ys[count] = (int)Math.floor(coords[1]);
/*      */           }
/*      */           else {
/*      */             
/*  897 */             return false;
/*      */           } 
/*  899 */           count++;
/*      */           break;
/*      */         
/*      */         case 3:
/*  903 */           return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  908 */       iter.next();
/*      */     } 
/*      */     
/*  911 */     if (count == 4)
/*      */     {
/*  913 */       return (xs[0] == xs[1] || xs[0] == xs[2] || ys[0] == ys[1] || ys[0] == ys[3]);
/*      */     }
/*      */     
/*  916 */     return false;
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
/*      */   public void fillAndStrokePath(int windingRule) throws IOException {
/*  929 */     GeneralPath path = (GeneralPath)this.linePath.clone();
/*  930 */     fillPath(windingRule);
/*  931 */     this.linePath = path;
/*  932 */     strokePath();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clip(int windingRule) {
/*  939 */     this.clipWindingRule = windingRule;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveTo(float x, float y) {
/*  945 */     this.linePath.moveTo(x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void lineTo(float x, float y) {
/*  951 */     this.linePath.lineTo(x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) {
/*  957 */     this.linePath.curveTo(x1, y1, x2, y2, x3, y3);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Point2D getCurrentPoint() {
/*  963 */     return this.linePath.getCurrentPoint();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void closePath() {
/*  969 */     this.linePath.closePath();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endPath() {
/*  975 */     if (this.clipWindingRule != -1) {
/*      */       
/*  977 */       this.linePath.setWindingRule(this.clipWindingRule);
/*  978 */       getGraphicsState().intersectClippingPath(this.linePath);
/*      */ 
/*      */ 
/*      */       
/*  982 */       this.lastClip = null;
/*      */       
/*  984 */       this.clipWindingRule = -1;
/*      */     } 
/*  986 */     this.linePath.reset();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawImage(PDImage pdImage) throws IOException {
/*  992 */     Matrix ctm = getGraphicsState().getCurrentTransformationMatrix();
/*  993 */     AffineTransform at = ctm.createAffineTransform();
/*      */     
/*  995 */     if (!pdImage.getInterpolate()) {
/*      */ 
/*      */       
/*  998 */       boolean isScaledUp = (pdImage.getWidth() < Math.round(at.getScaleX()) || pdImage.getHeight() < Math.round(at.getScaleY()));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1003 */       if (isScaledUp || pdImage.isStencil())
/*      */       {
/* 1005 */         this.graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1010 */     if (pdImage.isStencil()) {
/*      */       
/* 1012 */       if (getGraphicsState().getNonStrokingColor().getColorSpace() instanceof PDPattern)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1025 */         Paint paint = getNonStrokingPaint();
/* 1026 */         Rectangle2D unitRect = new Rectangle2D.Float(0.0F, 0.0F, 1.0F, 1.0F);
/* 1027 */         Rectangle2D bounds = at.createTransformedShape(unitRect).getBounds2D();
/*      */ 
/*      */         
/* 1030 */         BufferedImage renderedPaint = new BufferedImage((int)Math.ceil(bounds.getWidth()), (int)Math.ceil(bounds.getHeight()), 2);
/*      */         
/* 1032 */         Graphics2D g = (Graphics2D)renderedPaint.getGraphics();
/* 1033 */         g.translate(-bounds.getMinX(), -bounds.getMinY());
/* 1034 */         g.setPaint(paint);
/* 1035 */         g.fill(bounds);
/* 1036 */         g.dispose();
/*      */ 
/*      */         
/* 1039 */         BufferedImage mask = pdImage.getImage();
/*      */         
/* 1041 */         BufferedImage renderedMask = new BufferedImage((int)Math.ceil(bounds.getWidth()), (int)Math.ceil(bounds.getHeight()), 1);
/*      */         
/* 1043 */         g = (Graphics2D)renderedMask.getGraphics();
/* 1044 */         g.translate(-bounds.getMinX(), -bounds.getMinY());
/* 1045 */         AffineTransform imageTransform = new AffineTransform(at);
/* 1046 */         imageTransform.scale(1.0D / mask.getWidth(), -1.0D / mask.getHeight());
/* 1047 */         imageTransform.translate(0.0D, -mask.getHeight());
/* 1048 */         g.drawImage(mask, imageTransform, (ImageObserver)null);
/* 1049 */         g.dispose();
/*      */ 
/*      */         
/* 1052 */         int[] transparent = new int[4];
/* 1053 */         int[] alphaPixel = null;
/* 1054 */         WritableRaster raster = renderedPaint.getRaster();
/* 1055 */         WritableRaster alpha = renderedMask.getRaster();
/* 1056 */         int h = renderedMask.getRaster().getHeight();
/* 1057 */         int w = renderedMask.getRaster().getWidth();
/* 1058 */         for (int y = 0; y < h; y++) {
/*      */           
/* 1060 */           for (int x = 0; x < w; x++) {
/*      */             
/* 1062 */             alphaPixel = alpha.getPixel(x, y, alphaPixel);
/* 1063 */             if (alphaPixel[0] == 255)
/*      */             {
/* 1065 */               raster.setPixel(x, y, transparent);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1071 */         setClip();
/* 1072 */         this.graphics.setComposite(getGraphicsState().getNonStrokingJavaComposite());
/* 1073 */         if (isContentRendered())
/*      */         {
/* 1075 */           this.graphics.drawImage(renderedPaint, 
/* 1076 */               AffineTransform.getTranslateInstance(bounds.getMinX(), bounds.getMinY()), (ImageObserver)null);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else
/*      */       {
/* 1083 */         BufferedImage image = pdImage.getStencilImage(getNonStrokingPaint());
/*      */ 
/*      */         
/* 1086 */         drawBufferedImage(image, at);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1091 */     else if (this.subsamplingAllowed) {
/*      */       
/* 1093 */       int subsampling = getSubsampling(pdImage, at);
/*      */       
/* 1095 */       drawBufferedImage(pdImage.getImage(null, subsampling), at);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1100 */       drawBufferedImage(pdImage.getImage(), at);
/*      */     } 
/*      */ 
/*      */     
/* 1104 */     if (!pdImage.getInterpolate())
/*      */     {
/*      */ 
/*      */       
/* 1108 */       setRenderingHints();
/*      */     }
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
/*      */   private int getSubsampling(PDImage pdImage, AffineTransform at) {
/* 1124 */     double scale = Math.abs(at.getDeterminant() * this.xform.getDeterminant());
/*      */     
/* 1126 */     int subsampling = (int)Math.floor(Math.sqrt((pdImage.getWidth() * pdImage.getHeight()) / scale));
/* 1127 */     if (subsampling > 8)
/*      */     {
/* 1129 */       subsampling = 8;
/*      */     }
/* 1131 */     if (subsampling < 1)
/*      */     {
/* 1133 */       subsampling = 1;
/*      */     }
/* 1135 */     if (subsampling > pdImage.getWidth() || subsampling > pdImage.getHeight())
/*      */     {
/*      */ 
/*      */       
/* 1139 */       subsampling = Math.min(pdImage.getWidth(), pdImage.getHeight());
/*      */     }
/* 1141 */     return subsampling;
/*      */   }
/*      */ 
/*      */   
/*      */   private void drawBufferedImage(BufferedImage image, AffineTransform at) throws IOException {
/* 1146 */     this.graphics.setComposite(getGraphicsState().getNonStrokingJavaComposite());
/* 1147 */     setClip();
/* 1148 */     AffineTransform imageTransform = new AffineTransform(at);
/* 1149 */     PDSoftMask softMask = getGraphicsState().getSoftMask();
/* 1150 */     if (softMask != null) {
/*      */       
/* 1152 */       imageTransform.scale(1.0D, -1.0D);
/* 1153 */       imageTransform.translate(0.0D, -1.0D);
/*      */ 
/*      */       
/* 1156 */       Paint awtPaint = new TexturePaint(image, new Rectangle2D.Double(imageTransform.getTranslateX(), imageTransform.getTranslateY(), imageTransform.getScaleX(), imageTransform.getScaleY()));
/* 1157 */       awtPaint = applySoftMaskToPaint(awtPaint, softMask);
/* 1158 */       this.graphics.setPaint(awtPaint);
/* 1159 */       Rectangle2D unitRect = new Rectangle2D.Float(0.0F, 0.0F, 1.0F, 1.0F);
/* 1160 */       if (isContentRendered())
/*      */       {
/* 1162 */         this.graphics.fill(at.createTransformedShape(unitRect));
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1167 */       COSBase transfer = getGraphicsState().getTransfer();
/* 1168 */       if (transfer instanceof COSArray || transfer instanceof COSDictionary)
/*      */       {
/* 1170 */         image = applyTransferFunction(image, transfer);
/*      */       }
/*      */       
/* 1173 */       int width = image.getWidth();
/* 1174 */       int height = image.getHeight();
/* 1175 */       imageTransform.scale(1.0D / width, -1.0D / height);
/* 1176 */       imageTransform.translate(0.0D, -height);
/* 1177 */       if (isContentRendered())
/*      */       {
/* 1179 */         this.graphics.drawImage(image, imageTransform, (ImageObserver)null); } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private BufferedImage applyTransferFunction(BufferedImage image, COSBase transfer) throws IOException {
/*      */     BufferedImage bim;
/*      */     Integer[] rMap, gMap, bMap;
/*      */     PDFunction rf, gf, bf;
/* 1187 */     if (image.getColorModel().hasAlpha()) {
/*      */       
/* 1189 */       bim = new BufferedImage(image.getWidth(), image.getHeight(), 2);
/*      */     }
/*      */     else {
/*      */       
/* 1193 */       bim = new BufferedImage(image.getWidth(), image.getHeight(), 1);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1200 */     if (transfer instanceof COSArray) {
/*      */       
/* 1202 */       COSArray ar = (COSArray)transfer;
/* 1203 */       rf = PDFunction.create(ar.getObject(0));
/* 1204 */       gf = PDFunction.create(ar.getObject(1));
/* 1205 */       bf = PDFunction.create(ar.getObject(2));
/* 1206 */       rMap = new Integer[256];
/* 1207 */       gMap = new Integer[256];
/* 1208 */       bMap = new Integer[256];
/*      */     }
/*      */     else {
/*      */       
/* 1212 */       rf = PDFunction.create(transfer);
/* 1213 */       gf = rf;
/* 1214 */       bf = rf;
/* 1215 */       rMap = new Integer[256];
/* 1216 */       gMap = rMap;
/* 1217 */       bMap = rMap;
/*      */     } 
/*      */ 
/*      */     
/* 1221 */     float[] input = new float[1];
/* 1222 */     for (int x = 0; x < image.getWidth(); x++) {
/*      */       
/* 1224 */       for (int y = 0; y < image.getHeight(); y++) {
/*      */         
/* 1226 */         int ro, go, bo, rgb = image.getRGB(x, y);
/* 1227 */         int ri = rgb >> 16 & 0xFF;
/* 1228 */         int gi = rgb >> 8 & 0xFF;
/* 1229 */         int bi = rgb & 0xFF;
/*      */         
/* 1231 */         if (rMap[ri] != null) {
/*      */           
/* 1233 */           ro = rMap[ri].intValue();
/*      */         }
/*      */         else {
/*      */           
/* 1237 */           input[0] = (ri & 0xFF) / 255.0F;
/* 1238 */           ro = (int)(rf.eval(input)[0] * 255.0F);
/* 1239 */           rMap[ri] = Integer.valueOf(ro);
/*      */         } 
/* 1241 */         if (gMap[gi] != null) {
/*      */           
/* 1243 */           go = gMap[gi].intValue();
/*      */         }
/*      */         else {
/*      */           
/* 1247 */           input[0] = (gi & 0xFF) / 255.0F;
/* 1248 */           go = (int)(gf.eval(input)[0] * 255.0F);
/* 1249 */           gMap[gi] = Integer.valueOf(go);
/*      */         } 
/* 1251 */         if (bMap[bi] != null) {
/*      */           
/* 1253 */           bo = bMap[bi].intValue();
/*      */         }
/*      */         else {
/*      */           
/* 1257 */           input[0] = (bi & 0xFF) / 255.0F;
/* 1258 */           bo = (int)(bf.eval(input)[0] * 255.0F);
/* 1259 */           bMap[bi] = Integer.valueOf(bo);
/*      */         } 
/* 1261 */         bim.setRGB(x, y, rgb & 0xFF000000 | ro << 16 | go << 8 | bo);
/*      */       } 
/*      */     } 
/* 1264 */     return bim;
/*      */   }
/*      */ 
/*      */   
/*      */   public void shadingFill(COSName shadingName) throws IOException {
/*      */     Area area;
/* 1270 */     PDShading shading = getResources().getShading(shadingName);
/* 1271 */     if (shading == null) {
/*      */       
/* 1273 */       LOG.error("shading " + shadingName + " does not exist in resources dictionary");
/*      */       return;
/*      */     } 
/* 1276 */     Matrix ctm = getGraphicsState().getCurrentTransformationMatrix();
/* 1277 */     Paint paint = shading.toPaint(ctm);
/* 1278 */     paint = applySoftMaskToPaint(paint, getGraphicsState().getSoftMask());
/*      */     
/* 1280 */     this.graphics.setComposite(getGraphicsState().getNonStrokingJavaComposite());
/* 1281 */     this.graphics.setPaint(paint);
/* 1282 */     this.graphics.setClip(null);
/* 1283 */     this.lastClip = null;
/*      */ 
/*      */ 
/*      */     
/* 1287 */     PDRectangle bbox = shading.getBBox();
/*      */     
/* 1289 */     if (bbox != null) {
/*      */       
/* 1291 */       area = new Area(bbox.transform(ctm));
/* 1292 */       area.intersect(getGraphicsState().getCurrentClippingPath());
/*      */     }
/*      */     else {
/*      */       
/* 1296 */       area = getGraphicsState().getCurrentClippingPath();
/*      */     } 
/* 1298 */     if (isContentRendered())
/*      */     {
/* 1300 */       this.graphics.fill(area);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void showAnnotation(PDAnnotation annotation) throws IOException {
/* 1307 */     this.lastClip = null;
/* 1308 */     int deviceType = -1;
/* 1309 */     if (this.graphics.getDeviceConfiguration() != null && this.graphics
/* 1310 */       .getDeviceConfiguration().getDevice() != null)
/*      */     {
/* 1312 */       deviceType = this.graphics.getDeviceConfiguration().getDevice().getType();
/*      */     }
/* 1314 */     if (deviceType == 1 && !annotation.isPrinted()) {
/*      */       return;
/*      */     }
/*      */     
/* 1318 */     if (deviceType == 0 && annotation.isNoView()) {
/*      */       return;
/*      */     }
/*      */     
/* 1322 */     if (annotation.isHidden()) {
/*      */       return;
/*      */     }
/*      */     
/* 1326 */     if (annotation.isInvisible() && annotation instanceof org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationUnknown) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1334 */     if (isHiddenOCG(annotation.getOptionalContent())) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1339 */     PDAppearanceDictionary appearance = annotation.getAppearance();
/* 1340 */     if (appearance == null || appearance.getNormalAppearance() == null)
/*      */     {
/*      */       
/* 1343 */       annotation.constructAppearances();
/*      */     }
/*      */     
/* 1346 */     if (annotation.isNoRotate() && getCurrentPage().getRotation() != 0) {
/*      */       
/* 1348 */       PDRectangle rect = annotation.getRectangle();
/* 1349 */       AffineTransform savedTransform = this.graphics.getTransform();
/*      */ 
/*      */       
/* 1352 */       this.graphics.rotate(Math.toRadians(getCurrentPage().getRotation()), rect
/* 1353 */           .getLowerLeftX(), rect.getUpperRightY());
/* 1354 */       super.showAnnotation(annotation);
/* 1355 */       this.graphics.setTransform(savedTransform);
/*      */     }
/*      */     else {
/*      */       
/* 1359 */       super.showAnnotation(annotation);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showForm(PDFormXObject form) throws IOException {
/* 1369 */     if (isContentRendered())
/*      */     {
/* 1371 */       super.showForm(form);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void showTransparencyGroup(PDTransparencyGroup form) throws IOException {
/* 1378 */     if (!isContentRendered()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1383 */     TransparencyGroup group = new TransparencyGroup(form, false, getGraphicsState().getCurrentTransformationMatrix(), null);
/* 1384 */     BufferedImage image = group.getImage();
/* 1385 */     if (image == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1391 */     this.graphics.setComposite(getGraphicsState().getNonStrokingJavaComposite());
/* 1392 */     setClip();
/*      */ 
/*      */ 
/*      */     
/* 1396 */     PDRectangle bbox = group.getBBox();
/* 1397 */     AffineTransform prev = this.graphics.getTransform();
/*      */     
/* 1399 */     Matrix m = new Matrix(this.xform);
/* 1400 */     float xScale = Math.abs(m.getScalingFactorX());
/* 1401 */     float yScale = Math.abs(m.getScalingFactorY());
/*      */     
/* 1403 */     AffineTransform transform = new AffineTransform(this.xform);
/* 1404 */     transform.scale(1.0D / xScale, 1.0D / yScale);
/* 1405 */     this.graphics.setTransform(transform);
/*      */ 
/*      */     
/* 1408 */     float x = bbox.getLowerLeftX() - this.pageSize.getLowerLeftX();
/* 1409 */     float y = this.pageSize.getUpperRightY() - bbox.getUpperRightY();
/*      */     
/* 1411 */     if (this.flipTG) {
/*      */       
/* 1413 */       this.graphics.translate(0, image.getHeight());
/* 1414 */       this.graphics.scale(1.0D, -1.0D);
/*      */     }
/*      */     else {
/*      */       
/* 1418 */       this.graphics.translate((x * xScale), (y * yScale));
/*      */     } 
/*      */     
/* 1421 */     PDSoftMask softMask = getGraphicsState().getSoftMask();
/* 1422 */     if (softMask != null) {
/*      */ 
/*      */       
/* 1425 */       Paint awtPaint = new TexturePaint(image, new Rectangle2D.Float(0.0F, 0.0F, image.getWidth(), image.getHeight()));
/* 1426 */       awtPaint = applySoftMaskToPaint(awtPaint, softMask);
/* 1427 */       this.graphics.setPaint(awtPaint);
/* 1428 */       if (isContentRendered())
/*      */       {
/* 1430 */         this.graphics.fill(new Rectangle2D.Float(0.0F, 0.0F, bbox
/* 1431 */               .getWidth() * xScale, bbox.getHeight() * yScale));
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1436 */     else if (isContentRendered()) {
/*      */       
/* 1438 */       this.graphics.drawImage(image, (AffineTransform)null, (ImageObserver)null);
/*      */     } 
/*      */ 
/*      */     
/* 1442 */     this.graphics.setTransform(prev);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final class TransparencyGroup
/*      */   {
/*      */     private final BufferedImage image;
/*      */ 
/*      */     
/*      */     private final PDRectangle bbox;
/*      */ 
/*      */     
/*      */     private final int minX;
/*      */ 
/*      */     
/*      */     private final int minY;
/*      */ 
/*      */     
/*      */     private final int maxX;
/*      */ 
/*      */     
/*      */     private final int maxY;
/*      */ 
/*      */     
/*      */     private final int width;
/*      */     
/*      */     private final int height;
/*      */     
/*      */     private final float scaleX;
/*      */     
/*      */     private final float scaleY;
/*      */ 
/*      */     
/*      */     private TransparencyGroup(PDTransparencyGroup form, boolean isSoftMask, Matrix ctm, PDColor backdropColor) throws IOException {
/* 1477 */       Graphics2D g2dOriginal = PageDrawer.this.graphics;
/* 1478 */       Area lastClipOriginal = PageDrawer.this.lastClip;
/*      */ 
/*      */       
/* 1481 */       Matrix transform = Matrix.concatenate(ctm, form.getMatrix());
/*      */ 
/*      */       
/* 1484 */       GeneralPath transformedBox = form.getBBox().transform(transform);
/*      */ 
/*      */       
/* 1487 */       Area clip = (Area)PageDrawer.this.getGraphicsState().getCurrentClippingPath().clone();
/* 1488 */       clip.intersect(new Area(transformedBox));
/* 1489 */       Rectangle2D clipRect = clip.getBounds2D();
/* 1490 */       Matrix m = new Matrix(PageDrawer.this.xform);
/* 1491 */       this.scaleX = Math.abs(m.getScalingFactorX());
/* 1492 */       this.scaleY = Math.abs(m.getScalingFactorY());
/* 1493 */       if (clipRect.isEmpty()) {
/*      */         
/* 1495 */         this.image = null;
/* 1496 */         this.bbox = null;
/* 1497 */         this.minX = 0;
/* 1498 */         this.minY = 0;
/* 1499 */         this.maxX = 0;
/* 1500 */         this.maxY = 0;
/* 1501 */         this.width = 0;
/* 1502 */         this.height = 0;
/*      */         return;
/*      */       } 
/* 1505 */       this
/* 1506 */         .bbox = new PDRectangle((float)clipRect.getX(), (float)clipRect.getY(), (float)clipRect.getWidth(), (float)clipRect.getHeight());
/*      */ 
/*      */       
/* 1509 */       AffineTransform dpiTransform = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/* 1510 */       Rectangle2D bounds = dpiTransform.createTransformedShape(clip.getBounds2D()).getBounds2D();
/*      */       
/* 1512 */       this.minX = (int)Math.floor(bounds.getMinX());
/* 1513 */       this.minY = (int)Math.floor(bounds.getMinY());
/* 1514 */       this.maxX = (int)Math.floor(bounds.getMaxX()) + 1;
/* 1515 */       this.maxY = (int)Math.floor(bounds.getMaxY()) + 1;
/*      */       
/* 1517 */       this.width = this.maxX - this.minX;
/* 1518 */       this.height = this.maxY - this.minY;
/*      */ 
/*      */       
/* 1521 */       if (isGray(form.getGroup().getColorSpace(form.getResources()))) {
/*      */         
/* 1523 */         this.image = create2ByteGrayAlphaImage(this.width, this.height);
/*      */       }
/*      */       else {
/*      */         
/* 1527 */         this.image = new BufferedImage(this.width, this.height, 2);
/*      */       } 
/*      */ 
/*      */       
/* 1531 */       boolean needsBackdrop = (!isSoftMask && !form.getGroup().isIsolated() && PageDrawer.this.hasBlendMode(form, new HashSet()));
/* 1532 */       BufferedImage backdropImage = null;
/*      */       
/* 1534 */       int backdropX = 0;
/* 1535 */       int backdropY = 0;
/* 1536 */       if (needsBackdrop)
/*      */       {
/* 1538 */         if (PageDrawer.this.transparencyGroupStack.isEmpty()) {
/*      */ 
/*      */           
/* 1541 */           backdropImage = PageDrawer.this.renderer.getPageImage();
/* 1542 */           needsBackdrop = (backdropImage != null);
/* 1543 */           backdropX = this.minX;
/* 1544 */           backdropY = (backdropImage != null) ? (backdropImage.getHeight() - this.maxY) : 0;
/*      */         }
/*      */         else {
/*      */           
/* 1548 */           TransparencyGroup parentGroup = PageDrawer.this.transparencyGroupStack.peek();
/* 1549 */           backdropImage = parentGroup.image;
/* 1550 */           backdropX = this.minX - parentGroup.minX;
/* 1551 */           backdropY = parentGroup.maxY - this.maxY;
/*      */         } 
/*      */       }
/*      */       
/* 1555 */       Graphics2D g = this.image.createGraphics();
/* 1556 */       if (needsBackdrop) {
/*      */ 
/*      */         
/* 1559 */         g.drawImage(backdropImage, 0, 0, this.width, this.height, backdropX, backdropY, backdropX + this.width, backdropY + this.height, null);
/*      */         
/* 1561 */         g = new GroupGraphics(this.image, g);
/*      */       } 
/* 1563 */       if (isSoftMask && backdropColor != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1568 */         g.setBackground(new Color(backdropColor.toRGB()));
/* 1569 */         g.clearRect(0, 0, this.width, this.height);
/*      */       } 
/*      */ 
/*      */       
/* 1573 */       g.translate(0, this.image.getHeight());
/* 1574 */       g.scale(1.0D, -1.0D);
/*      */       
/* 1576 */       boolean oldFlipTG = PageDrawer.this.flipTG;
/* 1577 */       PageDrawer.this.flipTG = false;
/*      */ 
/*      */ 
/*      */       
/* 1581 */       g.transform(dpiTransform);
/*      */       
/* 1583 */       AffineTransform xformOriginal = PageDrawer.this.xform;
/* 1584 */       PageDrawer.this.xform = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/* 1585 */       PDRectangle pageSizeOriginal = PageDrawer.this.pageSize;
/* 1586 */       PageDrawer.this.pageSize = new PDRectangle(this.minX / this.scaleX, this.minY / this.scaleY, 
/*      */           
/* 1588 */           (float)bounds.getWidth() / this.scaleX, 
/* 1589 */           (float)bounds.getHeight() / this.scaleY);
/* 1590 */       int clipWindingRuleOriginal = PageDrawer.this.clipWindingRule;
/* 1591 */       PageDrawer.this.clipWindingRule = -1;
/* 1592 */       GeneralPath linePathOriginal = PageDrawer.this.linePath;
/* 1593 */       PageDrawer.this.linePath = new GeneralPath();
/*      */ 
/*      */       
/* 1596 */       g.translate(-clipRect.getX(), -clipRect.getY());
/*      */       
/* 1598 */       PageDrawer.this.graphics = g;
/* 1599 */       PageDrawer.this.setRenderingHints();
/*      */       
/*      */       try {
/* 1602 */         if (isSoftMask)
/*      */         {
/* 1604 */           PageDrawer.this.processSoftMask(form);
/*      */         }
/*      */         else
/*      */         {
/* 1608 */           PageDrawer.this.transparencyGroupStack.push(this);
/* 1609 */           PageDrawer.this.processTransparencyGroup(form);
/* 1610 */           if (!PageDrawer.this.transparencyGroupStack.isEmpty())
/*      */           {
/* 1612 */             PageDrawer.this.transparencyGroupStack.pop();
/*      */           }
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 1618 */         PageDrawer.this.flipTG = oldFlipTG;
/* 1619 */         PageDrawer.this.lastClip = lastClipOriginal;
/* 1620 */         PageDrawer.this.graphics.dispose();
/* 1621 */         PageDrawer.this.graphics = g2dOriginal;
/* 1622 */         PageDrawer.this.clipWindingRule = clipWindingRuleOriginal;
/* 1623 */         PageDrawer.this.linePath = linePathOriginal;
/* 1624 */         PageDrawer.this.pageSize = pageSizeOriginal;
/* 1625 */         PageDrawer.this.xform = xformOriginal;
/*      */         
/* 1627 */         if (needsBackdrop)
/*      */         {
/* 1629 */           ((GroupGraphics)g).removeBackdrop(backdropImage, backdropX, backdropY);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private BufferedImage create2ByteGrayAlphaImage(int width, int height) {
/* 1640 */       int[] bandOffsets = { 1, 0 };
/* 1641 */       int bands = bandOffsets.length;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1648 */       ColorModel CM_GRAY_ALPHA = new ComponentColorModel(ColorSpace.getInstance(1003), true, false, 3, 0);
/*      */ 
/*      */ 
/*      */       
/* 1652 */       DataBuffer buffer = new DataBufferByte(width * height * bands);
/*      */ 
/*      */ 
/*      */       
/* 1656 */       WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, width * bands, bands, bandOffsets, new Point(0, 0));
/*      */ 
/*      */ 
/*      */       
/* 1660 */       return new BufferedImage(CM_GRAY_ALPHA, raster, false, null);
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean isGray(PDColorSpace colorSpace) {
/* 1665 */       if (colorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray)
/*      */       {
/* 1667 */         return true;
/*      */       }
/* 1669 */       if (colorSpace instanceof PDICCBased) {
/*      */         
/*      */         try {
/*      */           
/* 1673 */           return ((PDICCBased)colorSpace).getAlternateColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*      */         }
/* 1675 */         catch (IOException ex) {
/*      */           
/* 1677 */           return false;
/*      */         } 
/*      */       }
/* 1680 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public BufferedImage getImage() {
/* 1685 */       return this.image;
/*      */     }
/*      */ 
/*      */     
/*      */     public PDRectangle getBBox() {
/* 1690 */       return this.bbox;
/*      */     }
/*      */ 
/*      */     
/*      */     public Rectangle2D getBounds() {
/* 1695 */       Point2D size = new Point2D.Double(PageDrawer.this.pageSize.getWidth(), PageDrawer.this.pageSize.getHeight());
/*      */       
/* 1697 */       AffineTransform dpiTransform = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/* 1698 */       size = dpiTransform.transform(size, size);
/*      */       
/* 1700 */       return new Rectangle2D.Double((this.minX - PageDrawer.this.pageSize.getLowerLeftX() * this.scaleX), size
/* 1701 */           .getY() - this.minY - this.height + (PageDrawer.this.pageSize.getLowerLeftY() * this.scaleY), this.width, this.height);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean hasBlendMode(PDTransparencyGroup group, Set<COSBase> groupsDone) {
/* 1708 */     if (groupsDone.contains(group.getCOSObject()))
/*      */     {
/*      */       
/* 1711 */       return false;
/*      */     }
/* 1713 */     groupsDone.add(group.getCOSObject());
/*      */     
/* 1715 */     PDResources resources = group.getResources();
/* 1716 */     if (resources == null)
/*      */     {
/* 1718 */       return false;
/*      */     }
/* 1720 */     for (COSName name : resources.getExtGStateNames()) {
/*      */       
/* 1722 */       PDExtendedGraphicsState extGState = resources.getExtGState(name);
/* 1723 */       if (extGState == null) {
/*      */         continue;
/*      */       }
/*      */       
/* 1727 */       BlendMode blendMode = extGState.getBlendMode();
/* 1728 */       if (blendMode != BlendMode.NORMAL)
/*      */       {
/* 1730 */         return true;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1735 */     for (COSName name : resources.getXObjectNames()) {
/*      */       PDXObject xObject;
/*      */ 
/*      */       
/*      */       try {
/* 1740 */         xObject = resources.getXObject(name);
/*      */       }
/* 1742 */       catch (IOException ex) {
/*      */         continue;
/*      */       } 
/*      */       
/* 1746 */       if (xObject instanceof PDTransparencyGroup && 
/* 1747 */         hasBlendMode((PDTransparencyGroup)xObject, groupsDone))
/*      */       {
/* 1749 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1753 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginMarkedContentSequence(COSName tag, COSDictionary properties) {
/* 1762 */     if (this.nestedHiddenOCGCount > 0) {
/*      */       
/* 1764 */       this.nestedHiddenOCGCount++;
/*      */       return;
/*      */     } 
/* 1767 */     if (tag == null || getPage().getResources() == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1771 */     if (isHiddenOCG(getPage().getResources().getProperties(tag)))
/*      */     {
/* 1773 */       this.nestedHiddenOCGCount = 1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endMarkedContentSequence() {
/* 1783 */     if (this.nestedHiddenOCGCount > 0)
/*      */     {
/* 1785 */       this.nestedHiddenOCGCount--;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isContentRendered() {
/* 1791 */     return (this.nestedHiddenOCGCount <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isHiddenOCG(PDPropertyList propertyList) {
/* 1796 */     if (propertyList instanceof PDOptionalContentGroup) {
/*      */       
/* 1798 */       PDOptionalContentGroup group = (PDOptionalContentGroup)propertyList;
/* 1799 */       PDOptionalContentGroup.RenderState printState = group.getRenderState(this.destination);
/* 1800 */       if (printState == null)
/*      */       {
/* 1802 */         if (!getRenderer().isGroupEnabled(group))
/*      */         {
/* 1804 */           return true;
/*      */         }
/*      */       }
/* 1807 */       else if (PDOptionalContentGroup.RenderState.OFF.equals(printState))
/*      */       {
/* 1809 */         return true;
/*      */       }
/*      */     
/* 1812 */     } else if (propertyList instanceof PDOptionalContentMembershipDictionary) {
/*      */       
/* 1814 */       return isHiddenOCMD((PDOptionalContentMembershipDictionary)propertyList);
/*      */     } 
/* 1816 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isHiddenOCMD(PDOptionalContentMembershipDictionary ocmd) {
/* 1821 */     if (ocmd.getCOSObject().getCOSArray(COSName.VE) != null)
/*      */     {
/*      */       
/* 1824 */       LOG.info("/VE entry ignored in Optional Content Membership Dictionary");
/*      */     }
/* 1826 */     List<Boolean> visibles = new ArrayList<Boolean>();
/* 1827 */     for (PDPropertyList prop : ocmd.getOCGs())
/*      */     {
/* 1829 */       visibles.add(Boolean.valueOf(!isHiddenOCG(prop)));
/*      */     }
/* 1831 */     COSName visibilityPolicy = ocmd.getVisibilityPolicy();
/* 1832 */     if (COSName.ANY_OFF.equals(visibilityPolicy)) {
/*      */       
/* 1834 */       for (Iterator<Boolean> iterator1 = visibles.iterator(); iterator1.hasNext(); ) { boolean visible = ((Boolean)iterator1.next()).booleanValue();
/*      */         
/* 1836 */         if (!visible)
/*      */         {
/* 1838 */           return true;
/*      */         } }
/*      */       
/* 1841 */       return false;
/*      */     } 
/* 1843 */     if (COSName.ALL_ON.equals(visibilityPolicy)) {
/*      */       
/* 1845 */       for (Iterator<Boolean> iterator1 = visibles.iterator(); iterator1.hasNext(); ) { boolean visible = ((Boolean)iterator1.next()).booleanValue();
/*      */         
/* 1847 */         if (!visible)
/*      */         {
/* 1849 */           return true;
/*      */         } }
/*      */       
/* 1852 */       return false;
/*      */     } 
/* 1854 */     if (COSName.ALL_OFF.equals(visibilityPolicy)) {
/*      */       
/* 1856 */       for (Iterator<Boolean> iterator1 = visibles.iterator(); iterator1.hasNext(); ) { boolean visible = ((Boolean)iterator1.next()).booleanValue();
/*      */         
/* 1858 */         if (visible)
/*      */         {
/* 1860 */           return false;
/*      */         } }
/*      */       
/* 1863 */       return true;
/*      */     } 
/*      */     
/* 1866 */     for (Iterator<Boolean> iterator = visibles.iterator(); iterator.hasNext(); ) { boolean visible = ((Boolean)iterator.next()).booleanValue();
/*      */       
/* 1868 */       if (visible)
/*      */       {
/* 1870 */         return false;
/*      */       } }
/*      */     
/* 1873 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getJavaVersion() {
/* 1879 */     String version = System.getProperty("java.specification.version");
/* 1880 */     StringTokenizer st = new StringTokenizer(version, ".");
/*      */     
/*      */     try {
/* 1883 */       int major = Integer.parseInt(st.nextToken());
/* 1884 */       int minor = 0;
/* 1885 */       if (st.hasMoreTokens())
/*      */       {
/* 1887 */         minor = Integer.parseInt(st.nextToken());
/*      */       }
/* 1889 */       return (major == 1) ? minor : major;
/*      */     }
/* 1891 */     catch (NumberFormatException nfe) {
/*      */ 
/*      */       
/* 1894 */       return 0;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/PageDrawer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */