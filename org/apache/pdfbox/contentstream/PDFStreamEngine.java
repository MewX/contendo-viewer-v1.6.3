/*      */ package org.apache.pdfbox.contentstream;
/*      */ 
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Stack;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.contentstream.operator.Operator;
/*      */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.cos.COSObject;
/*      */ import org.apache.pdfbox.cos.COSString;
/*      */ import org.apache.pdfbox.pdfparser.PDFStreamParser;
/*      */ import org.apache.pdfbox.pdmodel.PDPage;
/*      */ import org.apache.pdfbox.pdmodel.PDResources;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*      */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*      */ import org.apache.pdfbox.pdmodel.font.PDFontFactory;
/*      */ import org.apache.pdfbox.pdmodel.font.PDType3CharProc;
/*      */ import org.apache.pdfbox.pdmodel.font.PDType3Font;
/*      */ import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*      */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.form.PDTransparencyGroup;
/*      */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDGraphicsState;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDTextState;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
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
/*      */ public abstract class PDFStreamEngine
/*      */ {
/*   76 */   private static final Log LOG = LogFactory.getLog(PDFStreamEngine.class);
/*      */   
/*   78 */   private final Map<String, OperatorProcessor> operators = new HashMap<String, OperatorProcessor>(80);
/*      */   
/*      */   private Matrix textMatrix;
/*      */   
/*      */   private Matrix textLineMatrix;
/*   83 */   private Stack<PDGraphicsState> graphicsStack = new Stack<PDGraphicsState>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PDResources resources;
/*      */ 
/*      */ 
/*      */   
/*      */   private PDPage currentPage;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isProcessingPage;
/*      */ 
/*      */ 
/*      */   
/*      */   private Matrix initialMatrix;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void registerOperatorProcessor(String operator, OperatorProcessor op) {
/*  107 */     op.setContext(this);
/*  108 */     this.operators.put(operator, op);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void addOperator(OperatorProcessor op) {
/*  118 */     op.setContext(this);
/*  119 */     this.operators.put(op.getName(), op);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initPage(PDPage page) {
/*  127 */     if (page == null)
/*      */     {
/*  129 */       throw new IllegalArgumentException("Page cannot be null");
/*      */     }
/*  131 */     this.currentPage = page;
/*  132 */     this.graphicsStack.clear();
/*  133 */     this.graphicsStack.push(new PDGraphicsState(page.getCropBox()));
/*  134 */     this.textMatrix = null;
/*  135 */     this.textLineMatrix = null;
/*  136 */     this.resources = null;
/*  137 */     this.initialMatrix = page.getMatrix();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processPage(PDPage page) throws IOException {
/*  148 */     initPage(page);
/*  149 */     if (page.hasContents()) {
/*      */       
/*  151 */       this.isProcessingPage = true;
/*  152 */       processStream((PDContentStream)page);
/*  153 */       this.isProcessingPage = false;
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
/*      */   public void showTransparencyGroup(PDTransparencyGroup form) throws IOException {
/*  165 */     processTransparencyGroup(form);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showForm(PDFormXObject form) throws IOException {
/*  176 */     if (this.currentPage == null)
/*      */     {
/*  178 */       throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
/*      */     }
/*      */     
/*  181 */     if (form.getCOSObject().getLength() > 0L)
/*      */     {
/*  183 */       processStream((PDContentStream)form);
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
/*      */   protected void processSoftMask(PDTransparencyGroup group) throws IOException {
/*  196 */     saveGraphicsState();
/*  197 */     Matrix softMaskCTM = getGraphicsState().getSoftMask().getInitialTransformationMatrix();
/*  198 */     getGraphicsState().setCurrentTransformationMatrix(softMaskCTM);
/*  199 */     processTransparencyGroup(group);
/*  200 */     restoreGraphicsState();
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
/*      */   protected void processTransparencyGroup(PDTransparencyGroup group) throws IOException {
/*  212 */     if (this.currentPage == null)
/*      */     {
/*  214 */       throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
/*      */     }
/*      */ 
/*      */     
/*  218 */     PDResources parent = pushResources((PDContentStream)group);
/*  219 */     Stack<PDGraphicsState> savedStack = saveGraphicsStack();
/*      */     
/*  221 */     Matrix parentMatrix = this.initialMatrix;
/*      */ 
/*      */     
/*  224 */     this.initialMatrix = getGraphicsState().getCurrentTransformationMatrix().clone();
/*      */ 
/*      */     
/*  227 */     getGraphicsState().getCurrentTransformationMatrix().concatenate(group.getMatrix());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  232 */     getGraphicsState().setBlendMode((BlendMode)BlendMode.NORMAL);
/*  233 */     getGraphicsState().setAlphaConstant(1.0D);
/*  234 */     getGraphicsState().setNonStrokeAlphaConstant(1.0D);
/*  235 */     getGraphicsState().setSoftMask(null);
/*      */ 
/*      */     
/*  238 */     clipToRect(group.getBBox());
/*      */     
/*  240 */     processStreamOperators((PDContentStream)group);
/*      */     
/*  242 */     this.initialMatrix = parentMatrix;
/*      */     
/*  244 */     restoreGraphicsStack(savedStack);
/*  245 */     popResources(parent);
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
/*      */   protected void processType3Stream(PDType3CharProc charProc, Matrix textRenderingMatrix) throws IOException {
/*  258 */     if (this.currentPage == null)
/*      */     {
/*  260 */       throw new IllegalStateException("No current page, call #processChildStream(PDContentStream, PDPage) instead");
/*      */     }
/*      */ 
/*      */     
/*  264 */     PDResources parent = pushResources((PDContentStream)charProc);
/*  265 */     Stack<PDGraphicsState> savedStack = saveGraphicsStack();
/*      */ 
/*      */     
/*  268 */     getGraphicsState().setCurrentTransformationMatrix(textRenderingMatrix);
/*      */ 
/*      */     
/*  271 */     getGraphicsState().getCurrentTransformationMatrix().concatenate(charProc.getMatrix());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  276 */     Matrix textMatrixOld = this.textMatrix;
/*  277 */     this.textMatrix = new Matrix();
/*  278 */     Matrix textLineMatrixOld = this.textLineMatrix;
/*  279 */     this.textLineMatrix = new Matrix();
/*      */     
/*  281 */     processStreamOperators((PDContentStream)charProc);
/*      */ 
/*      */     
/*  284 */     this.textMatrix = textMatrixOld;
/*  285 */     this.textLineMatrix = textLineMatrixOld;
/*      */     
/*  287 */     restoreGraphicsStack(savedStack);
/*  288 */     popResources(parent);
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
/*      */   protected void processAnnotation(PDAnnotation annotation, PDAppearanceStream appearance) throws IOException {
/*  301 */     PDResources parent = pushResources((PDContentStream)appearance);
/*  302 */     Stack<PDGraphicsState> savedStack = saveGraphicsStack();
/*      */     
/*  304 */     PDRectangle bbox = appearance.getBBox();
/*  305 */     PDRectangle rect = annotation.getRectangle();
/*  306 */     Matrix matrix = appearance.getMatrix();
/*      */ 
/*      */     
/*  309 */     if (rect != null && rect.getWidth() > 0.0F && rect.getHeight() > 0.0F && bbox != null) {
/*      */ 
/*      */       
/*  312 */       Rectangle2D transformedBox = bbox.transform(matrix).getBounds2D();
/*      */ 
/*      */ 
/*      */       
/*  316 */       Matrix a = Matrix.getTranslateInstance(rect.getLowerLeftX(), rect.getLowerLeftY());
/*  317 */       a.concatenate(Matrix.getScaleInstance((float)(rect.getWidth() / transformedBox.getWidth()), 
/*  318 */             (float)(rect.getHeight() / transformedBox.getHeight())));
/*  319 */       a.concatenate(Matrix.getTranslateInstance((float)-transformedBox.getX(), 
/*  320 */             (float)-transformedBox.getY()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  327 */       Matrix aa = Matrix.concatenate(a, matrix);
/*      */ 
/*      */       
/*  330 */       getGraphicsState().setCurrentTransformationMatrix(aa);
/*      */ 
/*      */       
/*  333 */       clipToRect(bbox);
/*      */ 
/*      */       
/*  336 */       this.initialMatrix = aa.clone();
/*      */       
/*  338 */       processStreamOperators((PDContentStream)appearance);
/*      */     } 
/*      */     
/*  341 */     restoreGraphicsStack(savedStack);
/*  342 */     popResources(parent);
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
/*      */   protected final void processTilingPattern(PDTilingPattern tilingPattern, PDColor color, PDColorSpace colorSpace) throws IOException {
/*  356 */     processTilingPattern(tilingPattern, color, colorSpace, tilingPattern.getMatrix());
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
/*      */   protected final void processTilingPattern(PDTilingPattern tilingPattern, PDColor color, PDColorSpace colorSpace, Matrix patternMatrix) throws IOException {
/*  373 */     PDResources parent = pushResources((PDContentStream)tilingPattern);
/*      */     
/*  375 */     Matrix parentMatrix = this.initialMatrix;
/*  376 */     this.initialMatrix = Matrix.concatenate(this.initialMatrix, patternMatrix);
/*      */ 
/*      */     
/*  379 */     Stack<PDGraphicsState> savedStack = saveGraphicsStack();
/*      */ 
/*      */     
/*  382 */     Rectangle2D bbox = tilingPattern.getBBox().transform(patternMatrix).getBounds2D();
/*      */     
/*  384 */     PDRectangle rect = new PDRectangle((float)bbox.getX(), (float)bbox.getY(), (float)bbox.getWidth(), (float)bbox.getHeight());
/*  385 */     this.graphicsStack.push(new PDGraphicsState(rect));
/*      */ 
/*      */     
/*  388 */     if (colorSpace != null) {
/*      */       
/*  390 */       color = new PDColor(color.getComponents(), colorSpace);
/*  391 */       getGraphicsState().setNonStrokingColorSpace(colorSpace);
/*  392 */       getGraphicsState().setNonStrokingColor(color);
/*  393 */       getGraphicsState().setStrokingColorSpace(colorSpace);
/*  394 */       getGraphicsState().setStrokingColor(color);
/*      */     } 
/*      */ 
/*      */     
/*  398 */     getGraphicsState().getCurrentTransformationMatrix().concatenate(patternMatrix);
/*      */ 
/*      */     
/*  401 */     clipToRect(tilingPattern.getBBox());
/*      */     
/*  403 */     processStreamOperators((PDContentStream)tilingPattern);
/*      */     
/*  405 */     this.initialMatrix = parentMatrix;
/*  406 */     restoreGraphicsStack(savedStack);
/*  407 */     popResources(parent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showAnnotation(PDAnnotation annotation) throws IOException {
/*  418 */     PDAppearanceStream appearanceStream = getAppearance(annotation);
/*  419 */     if (appearanceStream != null)
/*      */     {
/*  421 */       processAnnotation(annotation, appearanceStream);
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
/*      */   public PDAppearanceStream getAppearance(PDAnnotation annotation) {
/*  434 */     return annotation.getNormalAppearanceStream();
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
/*      */   protected void processChildStream(PDContentStream contentStream, PDPage page) throws IOException {
/*  447 */     if (this.isProcessingPage)
/*      */     {
/*  449 */       throw new IllegalStateException("Current page has already been set via  #processPage(PDPage) call #processChildStream(PDContentStream) instead");
/*      */     }
/*      */     
/*  452 */     initPage(page);
/*  453 */     processStream(contentStream);
/*  454 */     this.currentPage = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processStream(PDContentStream contentStream) throws IOException {
/*  465 */     PDResources parent = pushResources(contentStream);
/*  466 */     Stack<PDGraphicsState> savedStack = saveGraphicsStack();
/*  467 */     Matrix parentMatrix = this.initialMatrix;
/*      */ 
/*      */     
/*  470 */     getGraphicsState().getCurrentTransformationMatrix().concatenate(contentStream.getMatrix());
/*      */ 
/*      */     
/*  473 */     this.initialMatrix = getGraphicsState().getCurrentTransformationMatrix().clone();
/*      */ 
/*      */     
/*  476 */     PDRectangle bbox = contentStream.getBBox();
/*  477 */     clipToRect(bbox);
/*      */     
/*  479 */     processStreamOperators(contentStream);
/*      */     
/*  481 */     this.initialMatrix = parentMatrix;
/*  482 */     restoreGraphicsStack(savedStack);
/*  483 */     popResources(parent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processStreamOperators(PDContentStream contentStream) throws IOException {
/*  494 */     List<COSBase> arguments = new ArrayList<COSBase>();
/*  495 */     PDFStreamParser parser = new PDFStreamParser(contentStream);
/*  496 */     Object token = parser.parseNextToken();
/*  497 */     while (token != null) {
/*      */       
/*  499 */       if (token instanceof COSObject) {
/*      */         
/*  501 */         arguments.add(((COSObject)token).getObject());
/*      */       }
/*  503 */       else if (token instanceof Operator) {
/*      */         
/*  505 */         processOperator((Operator)token, arguments);
/*  506 */         arguments = new ArrayList<COSBase>();
/*      */       }
/*      */       else {
/*      */         
/*  510 */         arguments.add((COSBase)token);
/*      */       } 
/*  512 */       token = parser.parseNextToken();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PDResources pushResources(PDContentStream contentStream) {
/*  522 */     PDResources parentResources = this.resources;
/*  523 */     PDResources streamResources = contentStream.getResources();
/*  524 */     if (streamResources != null) {
/*      */       
/*  526 */       this.resources = streamResources;
/*      */     }
/*  528 */     else if (this.resources == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  535 */       this.resources = this.currentPage.getResources();
/*      */     } 
/*      */ 
/*      */     
/*  539 */     if (this.resources == null)
/*      */     {
/*  541 */       this.resources = new PDResources();
/*      */     }
/*  543 */     return parentResources;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void popResources(PDResources parentResources) {
/*  551 */     this.resources = parentResources;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clipToRect(PDRectangle rectangle) {
/*  560 */     if (rectangle != null) {
/*      */       
/*  562 */       GeneralPath clip = rectangle.transform(getGraphicsState().getCurrentTransformationMatrix());
/*  563 */       getGraphicsState().intersectClippingPath(clip);
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
/*      */   public void beginText() throws IOException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endText() throws IOException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showTextString(byte[] string) throws IOException {
/*  597 */     showText(string);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showTextStrings(COSArray array) throws IOException {
/*  608 */     PDTextState textState = getGraphicsState().getTextState();
/*  609 */     float fontSize = textState.getFontSize();
/*  610 */     float horizontalScaling = textState.getHorizontalScaling() / 100.0F;
/*  611 */     PDFont font = textState.getFont();
/*  612 */     boolean isVertical = false;
/*  613 */     if (font != null)
/*      */     {
/*  615 */       isVertical = font.isVertical();
/*      */     }
/*      */     
/*  618 */     for (COSBase obj : array) {
/*      */       
/*  620 */       if (obj instanceof COSNumber) {
/*      */         
/*  622 */         float tx, ty, tj = ((COSNumber)obj).floatValue();
/*      */ 
/*      */ 
/*      */         
/*  626 */         if (isVertical) {
/*      */           
/*  628 */           tx = 0.0F;
/*  629 */           ty = -tj / 1000.0F * fontSize;
/*      */         }
/*      */         else {
/*      */           
/*  633 */           tx = -tj / 1000.0F * fontSize * horizontalScaling;
/*  634 */           ty = 0.0F;
/*      */         } 
/*      */         
/*  637 */         applyTextAdjustment(tx, ty); continue;
/*      */       } 
/*  639 */       if (obj instanceof COSString) {
/*      */         
/*  641 */         byte[] string = ((COSString)obj).getBytes();
/*  642 */         showText(string);
/*      */         
/*      */         continue;
/*      */       } 
/*  646 */       throw new IOException("Unknown type in array for TJ operation:" + obj);
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
/*      */   protected void applyTextAdjustment(float tx, float ty) throws IOException {
/*  662 */     this.textMatrix.concatenate(Matrix.getTranslateInstance(tx, ty));
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
/*      */   protected void showText(byte[] string) throws IOException {
/*  674 */     PDGraphicsState state = getGraphicsState();
/*  675 */     PDTextState textState = state.getTextState();
/*      */ 
/*      */     
/*  678 */     PDFont font = textState.getFont();
/*  679 */     if (font == null) {
/*      */       
/*  681 */       LOG.warn("No current font, will use default");
/*  682 */       font = PDFontFactory.createDefaultFont();
/*      */     } 
/*      */     
/*  685 */     float fontSize = textState.getFontSize();
/*  686 */     float horizontalScaling = textState.getHorizontalScaling() / 100.0F;
/*  687 */     float charSpacing = textState.getCharacterSpacing();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  693 */     Matrix parameters = new Matrix(fontSize * horizontalScaling, 0.0F, 0.0F, fontSize, 0.0F, textState.getRise());
/*      */ 
/*      */     
/*  696 */     InputStream in = new ByteArrayInputStream(string);
/*  697 */     while (in.available() > 0) {
/*      */       float tx, ty;
/*      */       
/*  700 */       int before = in.available();
/*  701 */       int code = font.readCode(in);
/*  702 */       int codeLength = before - in.available();
/*  703 */       String unicode = font.toUnicode(code);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  708 */       float wordSpacing = 0.0F;
/*  709 */       if (codeLength == 1 && code == 32)
/*      */       {
/*  711 */         wordSpacing += textState.getWordSpacing();
/*      */       }
/*      */ 
/*      */       
/*  715 */       Matrix ctm = state.getCurrentTransformationMatrix();
/*  716 */       Matrix textRenderingMatrix = parameters.multiply(this.textMatrix).multiply(ctm);
/*      */ 
/*      */ 
/*      */       
/*  720 */       if (font.isVertical()) {
/*      */ 
/*      */         
/*  723 */         Vector v = font.getPositionVector(code);
/*      */ 
/*      */         
/*  726 */         textRenderingMatrix.translate(v);
/*      */       } 
/*      */ 
/*      */       
/*  730 */       Vector w = font.getDisplacement(code);
/*      */ 
/*      */       
/*  733 */       saveGraphicsState();
/*  734 */       Matrix textMatrixOld = this.textMatrix;
/*  735 */       Matrix textLineMatrixOld = this.textLineMatrix;
/*  736 */       showGlyph(textRenderingMatrix, font, code, unicode, w);
/*  737 */       this.textMatrix = textMatrixOld;
/*  738 */       this.textLineMatrix = textLineMatrixOld;
/*  739 */       restoreGraphicsState();
/*      */ 
/*      */ 
/*      */       
/*  743 */       if (font.isVertical()) {
/*      */         
/*  745 */         tx = 0.0F;
/*  746 */         ty = w.getY() * fontSize + charSpacing + wordSpacing;
/*      */       }
/*      */       else {
/*      */         
/*  750 */         tx = (w.getX() * fontSize + charSpacing + wordSpacing) * horizontalScaling;
/*  751 */         ty = 0.0F;
/*      */       } 
/*      */ 
/*      */       
/*  755 */       this.textMatrix.concatenate(Matrix.getTranslateInstance(tx, ty));
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
/*      */ 
/*      */   
/*      */   protected void showGlyph(Matrix textRenderingMatrix, PDFont font, int code, String unicode, Vector displacement) throws IOException {
/*  773 */     if (font instanceof PDType3Font) {
/*      */       
/*  775 */       showType3Glyph(textRenderingMatrix, (PDType3Font)font, code, unicode, displacement);
/*      */     }
/*      */     else {
/*      */       
/*  779 */       showFontGlyph(textRenderingMatrix, font, code, unicode, displacement);
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected void showFontGlyph(Matrix textRenderingMatrix, PDFont font, int code, String unicode, Vector displacement) throws IOException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void showType3Glyph(Matrix textRenderingMatrix, PDType3Font font, int code, String unicode, Vector displacement) throws IOException {
/*  814 */     PDType3CharProc charProc = font.getCharProc(code);
/*  815 */     if (charProc != null)
/*      */     {
/*  817 */       processType3Stream(charProc, textRenderingMatrix);
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
/*      */   public void beginMarkedContentSequence(COSName tag, COSDictionary properties) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endMarkedContentSequence() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processOperator(String operation, List<COSBase> arguments) throws IOException {
/*  849 */     Operator operator = Operator.getOperator(operation);
/*  850 */     processOperator(operator, arguments);
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
/*      */   protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
/*  862 */     String name = operator.getName();
/*  863 */     OperatorProcessor processor = this.operators.get(name);
/*  864 */     if (processor != null) {
/*      */       
/*  866 */       processor.setContext(this);
/*      */       
/*      */       try {
/*  869 */         processor.process(operator, operands);
/*      */       }
/*  871 */       catch (IOException e) {
/*      */         
/*  873 */         operatorException(operator, operands, e);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  878 */       unsupportedOperator(operator, operands);
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
/*      */   protected void unsupportedOperator(Operator operator, List<COSBase> operands) throws IOException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void operatorException(Operator operator, List<COSBase> operands, IOException e) throws IOException {
/*  907 */     if (e instanceof org.apache.pdfbox.contentstream.operator.MissingOperandException || e instanceof org.apache.pdfbox.pdmodel.MissingResourceException || e instanceof org.apache.pdfbox.filter.MissingImageReaderException) {
/*      */ 
/*      */ 
/*      */       
/*  911 */       LOG.error(e.getMessage());
/*      */     }
/*  913 */     else if (e instanceof org.apache.pdfbox.contentstream.operator.state.EmptyGraphicsStackException) {
/*      */       
/*  915 */       LOG.warn(e.getMessage());
/*      */     }
/*  917 */     else if (operator.getName().equals("Do")) {
/*      */ 
/*      */ 
/*      */       
/*  921 */       LOG.warn(e.getMessage());
/*      */     }
/*      */     else {
/*      */       
/*  925 */       throw e;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveGraphicsState() {
/*  934 */     this.graphicsStack.push(((PDGraphicsState)this.graphicsStack.peek()).clone());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void restoreGraphicsState() {
/*  942 */     this.graphicsStack.pop();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final Stack<PDGraphicsState> saveGraphicsStack() {
/*  952 */     Stack<PDGraphicsState> savedStack = this.graphicsStack;
/*  953 */     this.graphicsStack = new Stack<PDGraphicsState>();
/*  954 */     this.graphicsStack.add(((PDGraphicsState)savedStack.peek()).clone());
/*  955 */     return savedStack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void restoreGraphicsStack(Stack<PDGraphicsState> snapshot) {
/*  965 */     this.graphicsStack = snapshot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getGraphicsStackSize() {
/*  973 */     return this.graphicsStack.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDGraphicsState getGraphicsState() {
/*  981 */     return this.graphicsStack.peek();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix getTextLineMatrix() {
/*  989 */     return this.textLineMatrix;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextLineMatrix(Matrix value) {
/*  997 */     this.textLineMatrix = value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix getTextMatrix() {
/* 1005 */     return this.textMatrix;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextMatrix(Matrix value) {
/* 1013 */     this.textMatrix = value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLineDashPattern(COSArray array, int phase) {
/* 1022 */     if (phase < 0) {
/*      */       
/* 1024 */       LOG.warn("Dash phase has negative value " + phase + ", set to 0");
/* 1025 */       phase = 0;
/*      */     } 
/* 1027 */     PDLineDashPattern lineDash = new PDLineDashPattern(array, phase);
/* 1028 */     getGraphicsState().setLineDashPattern(lineDash);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDResources getResources() {
/* 1037 */     return this.resources;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDPage getCurrentPage() {
/* 1045 */     return this.currentPage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Matrix getInitialMatrix() {
/* 1055 */     return this.initialMatrix;
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
/*      */   public Point2D.Float transformedPoint(float x, float y) {
/* 1068 */     float[] position = { x, y };
/* 1069 */     getGraphicsState().getCurrentTransformationMatrix().createAffineTransform()
/* 1070 */       .transform(position, 0, position, 0, 1);
/* 1071 */     return new Point2D.Float(position[0], position[1]);
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
/*      */   protected float transformWidth(float width) {
/* 1083 */     Matrix ctm = getGraphicsState().getCurrentTransformationMatrix();
/* 1084 */     float x = ctm.getScaleX() + ctm.getShearX();
/* 1085 */     float y = ctm.getScaleY() + ctm.getShearY();
/* 1086 */     return width * (float)Math.sqrt((x * x + y * y) * 0.5D);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/PDFStreamEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */