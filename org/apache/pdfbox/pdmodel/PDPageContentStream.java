/*      */ package org.apache.pdfbox.pdmodel;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.io.Closeable;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Locale;
/*      */ import java.util.Stack;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.pdfwriter.COSWriter;
/*      */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*      */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*      */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*      */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*      */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.image.PDInlineImage;
/*      */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
/*      */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
/*      */ import org.apache.pdfbox.util.Charsets;
/*      */ import org.apache.pdfbox.util.Matrix;
/*      */ import org.apache.pdfbox.util.NumberFormatUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class PDPageContentStream
/*      */   implements Closeable
/*      */ {
/*      */   public enum AppendMode
/*      */   {
/*   77 */     OVERWRITE,
/*      */ 
/*      */ 
/*      */     
/*   81 */     APPEND,
/*      */ 
/*      */ 
/*      */     
/*   85 */     PREPEND;
/*      */ 
/*      */     
/*      */     public boolean isOverwrite() {
/*   89 */       return (this == OVERWRITE);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isPrepend() {
/*   94 */       return (this == PREPEND);
/*      */     }
/*      */   }
/*      */   
/*   98 */   private static final Log LOG = LogFactory.getLog(PDPageContentStream.class);
/*      */   
/*      */   private final PDDocument document;
/*      */   
/*      */   private OutputStream output;
/*      */   private PDResources resources;
/*      */   private boolean inTextMode = false;
/*  105 */   private final Stack<PDFont> fontStack = new Stack<PDFont>();
/*      */   
/*  107 */   private final Stack<PDColorSpace> nonStrokingColorSpaceStack = new Stack<PDColorSpace>();
/*  108 */   private final Stack<PDColorSpace> strokingColorSpaceStack = new Stack<PDColorSpace>();
/*      */ 
/*      */   
/*  111 */   private final NumberFormat formatDecimal = NumberFormat.getNumberInstance(Locale.US);
/*  112 */   private final byte[] formatBuffer = new byte[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDPageContentStream(PDDocument document, PDPage sourcePage) throws IOException {
/*  124 */     this(document, sourcePage, AppendMode.OVERWRITE, true, false);
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
/*      */   @Deprecated
/*      */   public PDPageContentStream(PDDocument document, PDPage sourcePage, boolean appendContent, boolean compress) throws IOException {
/*  142 */     this(document, sourcePage, appendContent, compress, false);
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
/*      */   public PDPageContentStream(PDDocument document, PDPage sourcePage, AppendMode appendContent, boolean compress) throws IOException {
/*  160 */     this(document, sourcePage, appendContent, compress, false);
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
/*      */   
/*      */   @Deprecated
/*      */   public PDPageContentStream(PDDocument document, PDPage sourcePage, boolean appendContent, boolean compress, boolean resetContext) throws IOException {
/*  181 */     this(document, sourcePage, appendContent ? AppendMode.APPEND : AppendMode.OVERWRITE, compress, resetContext);
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
/*      */   public PDPageContentStream(PDDocument document, PDPage sourcePage, AppendMode appendContent, boolean compress, boolean resetContext) throws IOException {
/*  200 */     this.document = document;
/*  201 */     COSName filter = compress ? COSName.FLATE_DECODE : null;
/*      */ 
/*      */     
/*  204 */     if (!appendContent.isOverwrite() && sourcePage.hasContents()) {
/*      */       COSArray array;
/*      */       
/*  207 */       PDStream contentsToAppend = new PDStream(document);
/*      */ 
/*      */       
/*  210 */       COSBase contents = sourcePage.getCOSObject().getDictionaryObject(COSName.CONTENTS);
/*      */       
/*  212 */       if (contents instanceof COSArray) {
/*      */ 
/*      */         
/*  215 */         array = (COSArray)contents;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  220 */         array = new COSArray();
/*  221 */         array.add(contents);
/*      */       } 
/*  223 */       if (appendContent.isPrepend()) {
/*      */         
/*  225 */         array.add(0, (COSBase)contentsToAppend.getCOSObject());
/*      */       }
/*      */       else {
/*      */         
/*  229 */         array.add((COSObjectable)contentsToAppend);
/*      */       } 
/*      */ 
/*      */       
/*  233 */       if (resetContext) {
/*      */ 
/*      */         
/*  236 */         PDStream saveGraphics = new PDStream(document);
/*  237 */         this.output = saveGraphics.createOutputStream(filter);
/*      */ 
/*      */         
/*  240 */         saveGraphicsState();
/*  241 */         close();
/*      */ 
/*      */         
/*  244 */         array.add(0, (COSBase)saveGraphics.getCOSObject());
/*      */       } 
/*      */ 
/*      */       
/*  248 */       sourcePage.getCOSObject().setItem(COSName.CONTENTS, (COSBase)array);
/*  249 */       this.output = contentsToAppend.createOutputStream(filter);
/*      */ 
/*      */       
/*  252 */       if (resetContext)
/*      */       {
/*  254 */         restoreGraphicsState();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  259 */       if (sourcePage.hasContents())
/*      */       {
/*  261 */         LOG.warn("You are overwriting an existing content, you should use the append mode");
/*      */       }
/*  263 */       PDStream contents = new PDStream(document);
/*  264 */       sourcePage.setContents(contents);
/*  265 */       this.output = contents.createOutputStream(filter);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  270 */     this.resources = sourcePage.getResources();
/*  271 */     if (this.resources == null) {
/*      */       
/*  273 */       this.resources = new PDResources();
/*  274 */       sourcePage.setResources(this.resources);
/*      */     } 
/*      */ 
/*      */     
/*  278 */     this.formatDecimal.setMaximumFractionDigits(5);
/*  279 */     this.formatDecimal.setGroupingUsed(false);
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
/*      */   public PDPageContentStream(PDDocument doc, PDAppearanceStream appearance) throws IOException {
/*  291 */     this(doc, appearance, appearance.getStream().createOutputStream());
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
/*      */   public PDPageContentStream(PDDocument doc, PDAppearanceStream appearance, OutputStream outputStream) throws IOException {
/*  305 */     this.document = doc;
/*      */     
/*  307 */     this.output = outputStream;
/*  308 */     this.resources = appearance.getResources();
/*      */     
/*  310 */     this.formatDecimal.setMaximumFractionDigits(4);
/*  311 */     this.formatDecimal.setGroupingUsed(false);
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
/*      */   public PDPageContentStream(PDDocument doc, PDFormXObject form, OutputStream outputStream) throws IOException {
/*  325 */     this.document = doc;
/*      */     
/*  327 */     this.output = outputStream;
/*  328 */     this.resources = form.getResources();
/*      */     
/*  330 */     this.formatDecimal.setMaximumFractionDigits(4);
/*  331 */     this.formatDecimal.setGroupingUsed(false);
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
/*      */   public PDPageContentStream(PDDocument doc, PDTilingPattern pattern, OutputStream outputStream) throws IOException {
/*  345 */     this.document = doc;
/*      */     
/*  347 */     this.output = outputStream;
/*  348 */     this.resources = pattern.getResources();
/*      */     
/*  350 */     this.formatDecimal.setMaximumFractionDigits(4);
/*  351 */     this.formatDecimal.setGroupingUsed(false);
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
/*      */   public void beginText() throws IOException {
/*  363 */     if (this.inTextMode)
/*      */     {
/*  365 */       throw new IllegalStateException("Error: Nested beginText() calls are not allowed.");
/*      */     }
/*  367 */     writeOperator("BT");
/*  368 */     this.inTextMode = true;
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
/*      */   public void endText() throws IOException {
/*  380 */     if (!this.inTextMode)
/*      */     {
/*  382 */       throw new IllegalStateException("Error: You must call beginText() before calling endText.");
/*      */     }
/*  384 */     writeOperator("ET");
/*  385 */     this.inTextMode = false;
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
/*      */   public void setFont(PDFont font, float fontSize) throws IOException {
/*  397 */     if (this.fontStack.isEmpty()) {
/*      */       
/*  399 */       this.fontStack.add(font);
/*      */     }
/*      */     else {
/*      */       
/*  403 */       this.fontStack.setElementAt(font, this.fontStack.size() - 1);
/*      */     } 
/*      */     
/*  406 */     if (font.willBeSubset())
/*      */     {
/*  408 */       this.document.getFontsToSubset().add(font);
/*      */     }
/*      */     
/*  411 */     writeOperand(this.resources.add(font));
/*  412 */     writeOperand(fontSize);
/*  413 */     writeOperator("Tf");
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
/*      */   @Deprecated
/*      */   public void drawString(String text) throws IOException {
/*  426 */     showText(text);
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
/*      */   public void showTextWithPositioning(Object[] textWithPositioningArray) throws IOException {
/*  444 */     write("[");
/*  445 */     for (Object obj : textWithPositioningArray) {
/*      */       
/*  447 */       if (obj instanceof String) {
/*      */         
/*  449 */         showTextInternal((String)obj);
/*      */       }
/*  451 */       else if (obj instanceof Float) {
/*      */         
/*  453 */         writeOperand(((Float)obj).floatValue());
/*      */       }
/*      */       else {
/*      */         
/*  457 */         throw new IllegalArgumentException("Argument must consist of array of Float and String types");
/*      */       } 
/*      */     } 
/*  460 */     write("] ");
/*  461 */     writeOperator("TJ");
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
/*      */   public void showText(String text) throws IOException {
/*  473 */     showTextInternal(text);
/*  474 */     write(" ");
/*  475 */     writeOperator("Tj");
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
/*      */   protected void showTextInternal(String text) throws IOException {
/*  487 */     if (!this.inTextMode)
/*      */     {
/*  489 */       throw new IllegalStateException("Must call beginText() before showText()");
/*      */     }
/*      */     
/*  492 */     if (this.fontStack.isEmpty())
/*      */     {
/*  494 */       throw new IllegalStateException("Must call setFont() before showText()");
/*      */     }
/*      */     
/*  497 */     PDFont font = this.fontStack.peek();
/*      */ 
/*      */     
/*  500 */     if (font.willBeSubset()) {
/*      */       
/*  502 */       int offset = 0;
/*  503 */       while (offset < text.length()) {
/*      */         
/*  505 */         int codePoint = text.codePointAt(offset);
/*  506 */         font.addToSubset(codePoint);
/*  507 */         offset += Character.charCount(codePoint);
/*      */       } 
/*      */     } 
/*      */     
/*  511 */     COSWriter.writeString(font.encode(text), this.output);
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
/*      */   @Deprecated
/*      */   public void setLeading(double leading) throws IOException {
/*  524 */     setLeading((float)leading);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLeading(float leading) throws IOException {
/*  535 */     writeOperand(leading);
/*  536 */     writeOperator("TL");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void newLine() throws IOException {
/*  547 */     if (!this.inTextMode)
/*      */     {
/*  549 */       throw new IllegalStateException("Must call beginText() before newLine()");
/*      */     }
/*  551 */     writeOperator("T*");
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
/*      */   @Deprecated
/*      */   public void moveTextPositionByAmount(float tx, float ty) throws IOException {
/*  565 */     newLineAtOffset(tx, ty);
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
/*      */   public void newLineAtOffset(float tx, float ty) throws IOException {
/*  579 */     if (!this.inTextMode)
/*      */     {
/*  581 */       throw new IllegalStateException("Error: must call beginText() before newLineAtOffset()");
/*      */     }
/*  583 */     writeOperand(tx);
/*  584 */     writeOperand(ty);
/*  585 */     writeOperator("Td");
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
/*      */   @Deprecated
/*      */   public void setTextMatrix(double a, double b, double c, double d, double e, double f) throws IOException {
/*  603 */     setTextMatrix(new Matrix((float)a, (float)b, (float)c, (float)d, (float)e, (float)f));
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
/*      */   @Deprecated
/*      */   public void setTextMatrix(AffineTransform matrix) throws IOException {
/*  616 */     setTextMatrix(new Matrix(matrix));
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
/*      */   public void setTextMatrix(Matrix matrix) throws IOException {
/*  629 */     if (!this.inTextMode)
/*      */     {
/*  631 */       throw new IllegalStateException("Error: must call beginText() before setTextMatrix");
/*      */     }
/*  633 */     writeAffineTransform(matrix.createAffineTransform());
/*  634 */     writeOperator("Tm");
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
/*      */   @Deprecated
/*      */   public void setTextScaling(double sx, double sy, double tx, double ty) throws IOException {
/*  650 */     setTextMatrix(new Matrix((float)sx, 0.0F, 0.0F, (float)sy, (float)tx, (float)ty));
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
/*      */   @Deprecated
/*      */   public void setTextTranslation(double tx, double ty) throws IOException {
/*  664 */     setTextMatrix(Matrix.getTranslateInstance((float)tx, (float)ty));
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
/*      */   @Deprecated
/*      */   public void setTextRotation(double angle, double tx, double ty) throws IOException {
/*  679 */     setTextMatrix(Matrix.getRotateInstance(angle, (float)tx, (float)ty));
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
/*      */   public void drawImage(PDImageXObject image, float x, float y) throws IOException {
/*  693 */     drawImage(image, x, y, image.getWidth(), image.getHeight());
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
/*      */   public void drawImage(PDImageXObject image, float x, float y, float width, float height) throws IOException {
/*  710 */     if (this.inTextMode)
/*      */     {
/*  712 */       throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
/*      */     }
/*      */     
/*  715 */     saveGraphicsState();
/*      */     
/*  717 */     AffineTransform transform = new AffineTransform(width, 0.0F, 0.0F, height, x, y);
/*  718 */     transform(new Matrix(transform));
/*      */     
/*  720 */     writeOperand(this.resources.add(image));
/*  721 */     writeOperator("Do");
/*      */     
/*  723 */     restoreGraphicsState();
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
/*      */   public void drawImage(PDImageXObject image, Matrix matrix) throws IOException {
/*  737 */     if (this.inTextMode)
/*      */     {
/*  739 */       throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
/*      */     }
/*      */     
/*  742 */     saveGraphicsState();
/*      */     
/*  744 */     AffineTransform transform = matrix.createAffineTransform();
/*  745 */     transform(new Matrix(transform));
/*      */     
/*  747 */     writeOperand(this.resources.add(image));
/*  748 */     writeOperator("Do");
/*      */     
/*  750 */     restoreGraphicsState();
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
/*      */   @Deprecated
/*      */   public void drawInlineImage(PDInlineImage inlineImage, float x, float y) throws IOException {
/*  766 */     drawImage(inlineImage, x, y, inlineImage.getWidth(), inlineImage.getHeight());
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
/*      */   public void drawImage(PDInlineImage inlineImage, float x, float y) throws IOException {
/*  780 */     drawImage(inlineImage, x, y, inlineImage.getWidth(), inlineImage.getHeight());
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
/*      */   @Deprecated
/*      */   public void drawInlineImage(PDInlineImage inlineImage, float x, float y, float width, float height) throws IOException {
/*  798 */     drawImage(inlineImage, x, y, width, height);
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
/*      */   public void drawImage(PDInlineImage inlineImage, float x, float y, float width, float height) throws IOException {
/*  815 */     if (this.inTextMode)
/*      */     {
/*  817 */       throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
/*      */     }
/*      */     
/*  820 */     saveGraphicsState();
/*  821 */     transform(new Matrix(width, 0.0F, 0.0F, height, x, y));
/*      */ 
/*      */     
/*  824 */     StringBuilder sb = new StringBuilder();
/*  825 */     sb.append("BI");
/*      */     
/*  827 */     sb.append("\n /W ");
/*  828 */     sb.append(inlineImage.getWidth());
/*      */     
/*  830 */     sb.append("\n /H ");
/*  831 */     sb.append(inlineImage.getHeight());
/*      */     
/*  833 */     sb.append("\n /CS ");
/*  834 */     sb.append("/");
/*  835 */     sb.append(inlineImage.getColorSpace().getName());
/*      */     
/*  837 */     if (inlineImage.getDecode() != null && inlineImage.getDecode().size() > 0) {
/*      */       
/*  839 */       sb.append("\n /D ");
/*  840 */       sb.append("[");
/*  841 */       for (COSBase base : inlineImage.getDecode()) {
/*      */         
/*  843 */         sb.append(((COSNumber)base).intValue());
/*  844 */         sb.append(" ");
/*      */       } 
/*  846 */       sb.append("]");
/*      */     } 
/*      */     
/*  849 */     if (inlineImage.isStencil())
/*      */     {
/*  851 */       sb.append("\n /IM true");
/*      */     }
/*      */     
/*  854 */     sb.append("\n /BPC ");
/*  855 */     sb.append(inlineImage.getBitsPerComponent());
/*      */ 
/*      */     
/*  858 */     write(sb.toString());
/*  859 */     writeLine();
/*      */ 
/*      */     
/*  862 */     writeOperator("ID");
/*  863 */     writeBytes(inlineImage.getData());
/*  864 */     writeLine();
/*  865 */     writeOperator("EI");
/*      */     
/*  867 */     restoreGraphicsState();
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
/*      */   @Deprecated
/*      */   public void drawXObject(PDXObject xobject, float x, float y, float width, float height) throws IOException {
/*  885 */     AffineTransform transform = new AffineTransform(width, 0.0F, 0.0F, height, x, y);
/*  886 */     drawXObject(xobject, transform);
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
/*      */   @Deprecated
/*      */   public void drawXObject(PDXObject xobject, AffineTransform transform) throws IOException {
/*      */     String xObjectPrefix;
/*  904 */     if (this.inTextMode)
/*      */     {
/*  906 */       throw new IllegalStateException("Error: drawXObject is not allowed within a text block.");
/*      */     }
/*      */ 
/*      */     
/*  910 */     if (xobject instanceof PDImageXObject) {
/*      */       
/*  912 */       xObjectPrefix = "Im";
/*      */     }
/*      */     else {
/*      */       
/*  916 */       xObjectPrefix = "Form";
/*      */     } 
/*  918 */     COSName objMapping = this.resources.add(xobject, xObjectPrefix);
/*      */     
/*  920 */     saveGraphicsState();
/*  921 */     transform(new Matrix(transform));
/*      */     
/*  923 */     writeOperand(objMapping);
/*  924 */     writeOperator("Do");
/*      */     
/*  926 */     restoreGraphicsState();
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
/*      */   public void drawForm(PDFormXObject form) throws IOException {
/*  938 */     if (this.inTextMode)
/*      */     {
/*  940 */       throw new IllegalStateException("Error: drawForm is not allowed within a text block.");
/*      */     }
/*      */     
/*  943 */     writeOperand(this.resources.add(form));
/*  944 */     writeOperator("Do");
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
/*      */   @Deprecated
/*      */   public void concatenate2CTM(double a, double b, double c, double d, double e, double f) throws IOException {
/*  961 */     transform(new Matrix((float)a, (float)b, (float)c, (float)d, (float)e, (float)f));
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
/*      */   @Deprecated
/*      */   public void concatenate2CTM(AffineTransform at) throws IOException {
/*  974 */     transform(new Matrix(at));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transform(Matrix matrix) throws IOException {
/*  985 */     if (this.inTextMode)
/*      */     {
/*  987 */       LOG.warn("Modifying the current transformation matrix is not allowed within text objects.");
/*      */     }
/*      */     
/*  990 */     writeAffineTransform(matrix.createAffineTransform());
/*  991 */     writeOperator("cm");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveGraphicsState() throws IOException {
/* 1000 */     if (this.inTextMode)
/*      */     {
/* 1002 */       LOG.warn("Saving the graphics state is not allowed within text objects.");
/*      */     }
/*      */     
/* 1005 */     if (!this.fontStack.isEmpty())
/*      */     {
/* 1007 */       this.fontStack.push(this.fontStack.peek());
/*      */     }
/* 1009 */     if (!this.strokingColorSpaceStack.isEmpty())
/*      */     {
/* 1011 */       this.strokingColorSpaceStack.push(this.strokingColorSpaceStack.peek());
/*      */     }
/* 1013 */     if (!this.nonStrokingColorSpaceStack.isEmpty())
/*      */     {
/* 1015 */       this.nonStrokingColorSpaceStack.push(this.nonStrokingColorSpaceStack.peek());
/*      */     }
/* 1017 */     writeOperator("q");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void restoreGraphicsState() throws IOException {
/* 1026 */     if (this.inTextMode)
/*      */     {
/* 1028 */       LOG.warn("Restoring the graphics state is not allowed within text objects.");
/*      */     }
/*      */     
/* 1031 */     if (!this.fontStack.isEmpty())
/*      */     {
/* 1033 */       this.fontStack.pop();
/*      */     }
/* 1035 */     if (!this.strokingColorSpaceStack.isEmpty())
/*      */     {
/* 1037 */       this.strokingColorSpaceStack.pop();
/*      */     }
/* 1039 */     if (!this.nonStrokingColorSpaceStack.isEmpty())
/*      */     {
/* 1041 */       this.nonStrokingColorSpaceStack.pop();
/*      */     }
/* 1043 */     writeOperator("Q");
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
/*      */   @Deprecated
/*      */   public void setStrokingColorSpace(PDColorSpace colorSpace) throws IOException {
/* 1057 */     setStrokingColorSpaceStack(colorSpace);
/* 1058 */     writeOperand(getName(colorSpace));
/* 1059 */     writeOperator("CS");
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
/*      */   @Deprecated
/*      */   public void setNonStrokingColorSpace(PDColorSpace colorSpace) throws IOException {
/* 1073 */     setNonStrokingColorSpaceStack(colorSpace);
/* 1074 */     writeOperand(getName(colorSpace));
/* 1075 */     writeOperator("cs");
/*      */   }
/*      */ 
/*      */   
/*      */   private COSName getName(PDColorSpace colorSpace) throws IOException {
/* 1080 */     if (colorSpace instanceof PDDeviceGray || colorSpace instanceof PDDeviceRGB || colorSpace instanceof PDDeviceCMYK)
/*      */     {
/*      */ 
/*      */       
/* 1084 */       return COSName.getPDFName(colorSpace.getName());
/*      */     }
/*      */ 
/*      */     
/* 1088 */     return this.resources.add(colorSpace);
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
/*      */   public void setStrokingColor(PDColor color) throws IOException {
/* 1100 */     if (this.strokingColorSpaceStack.isEmpty() || this.strokingColorSpaceStack
/* 1101 */       .peek() != color.getColorSpace()) {
/*      */       
/* 1103 */       writeOperand(getName(color.getColorSpace()));
/* 1104 */       writeOperator("CS");
/* 1105 */       setStrokingColorSpaceStack(color.getColorSpace());
/*      */     } 
/*      */     
/* 1108 */     for (float value : color.getComponents())
/*      */     {
/* 1110 */       writeOperand(value);
/*      */     }
/*      */     
/* 1113 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern)
/*      */     {
/* 1115 */       writeOperand(color.getPatternName());
/*      */     }
/*      */     
/* 1118 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern || color
/* 1119 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDSeparation || color
/* 1120 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceN || color
/* 1121 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDICCBased) {
/*      */       
/* 1123 */       writeOperator("SCN");
/*      */     }
/*      */     else {
/*      */       
/* 1127 */       writeOperator("SC");
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
/*      */   public void setStrokingColor(Color color) throws IOException {
/* 1140 */     float[] components = { color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F };
/* 1141 */     PDColor pdColor = new PDColor(components, (PDColorSpace)PDDeviceRGB.INSTANCE);
/* 1142 */     setStrokingColor(pdColor);
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
/*      */   @Deprecated
/*      */   public void setStrokingColor(float[] components) throws IOException {
/* 1155 */     if (this.strokingColorSpaceStack.isEmpty())
/*      */     {
/* 1157 */       throw new IllegalStateException("The color space must be set before setting a color");
/*      */     }
/*      */     
/* 1160 */     for (float component : components)
/*      */     {
/* 1162 */       writeOperand(component);
/*      */     }
/*      */     
/* 1165 */     PDColorSpace currentStrokingColorSpace = this.strokingColorSpaceStack.peek();
/*      */     
/* 1167 */     if (currentStrokingColorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDSeparation || currentStrokingColorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern || currentStrokingColorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDICCBased) {
/*      */ 
/*      */ 
/*      */       
/* 1171 */       writeOperator("SCN");
/*      */     }
/*      */     else {
/*      */       
/* 1175 */       writeOperator("SC");
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
/*      */   public void setStrokingColor(int r, int g, int b) throws IOException {
/* 1190 */     if (isOutside255Interval(r) || isOutside255Interval(g) || isOutside255Interval(b))
/*      */     {
/* 1192 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/* 1193 */           String.format("(%d,%d,%d)", new Object[] { Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b) }));
/*      */     }
/* 1195 */     writeOperand(r / 255.0F);
/* 1196 */     writeOperand(g / 255.0F);
/* 1197 */     writeOperand(b / 255.0F);
/* 1198 */     writeOperator("RG");
/* 1199 */     setStrokingColorSpaceStack((PDColorSpace)PDDeviceRGB.INSTANCE);
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
/*      */   @Deprecated
/*      */   public void setStrokingColor(int c, int m, int y, int k) throws IOException {
/* 1216 */     if (isOutside255Interval(c) || isOutside255Interval(m) || isOutside255Interval(y) || isOutside255Interval(k))
/*      */     {
/* 1218 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/* 1219 */           String.format("(%d,%d,%d,%d)", new Object[] { Integer.valueOf(c), Integer.valueOf(m), Integer.valueOf(y), Integer.valueOf(k) }));
/*      */     }
/* 1221 */     setStrokingColor(c / 255.0F, m / 255.0F, y / 255.0F, k / 255.0F);
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
/*      */   public void setStrokingColor(float c, float m, float y, float k) throws IOException {
/* 1236 */     if (isOutsideOneInterval(c) || isOutsideOneInterval(m) || isOutsideOneInterval(y) || isOutsideOneInterval(k))
/*      */     {
/* 1238 */       throw new IllegalArgumentException("Parameters must be within 0..1, but are " + 
/* 1239 */           String.format("(%.2f,%.2f,%.2f,%.2f)", new Object[] { Float.valueOf(c), Float.valueOf(m), Float.valueOf(y), Float.valueOf(k) }));
/*      */     }
/* 1241 */     writeOperand(c);
/* 1242 */     writeOperand(m);
/* 1243 */     writeOperand(y);
/* 1244 */     writeOperand(k);
/* 1245 */     writeOperator("K");
/* 1246 */     setStrokingColorSpaceStack((PDColorSpace)PDDeviceCMYK.INSTANCE);
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
/*      */   @Deprecated
/*      */   public void setStrokingColor(int g) throws IOException {
/* 1260 */     if (isOutside255Interval(g))
/*      */     {
/* 1262 */       throw new IllegalArgumentException("Parameter must be within 0..255, but is " + g);
/*      */     }
/* 1264 */     setStrokingColor(g / 255.0F);
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
/*      */   @Deprecated
/*      */   public void setStrokingColor(double g) throws IOException {
/* 1278 */     setStrokingColor((float)g);
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
/*      */   public void setStrokingColor(float g) throws IOException {
/* 1290 */     if (isOutsideOneInterval(g))
/*      */     {
/* 1292 */       throw new IllegalArgumentException("Parameter must be within 0..1, but is " + g);
/*      */     }
/* 1294 */     writeOperand(g);
/* 1295 */     writeOperator("G");
/* 1296 */     setStrokingColorSpaceStack((PDColorSpace)PDDeviceGray.INSTANCE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNonStrokingColor(PDColor color) throws IOException {
/* 1307 */     if (this.nonStrokingColorSpaceStack.isEmpty() || this.nonStrokingColorSpaceStack
/* 1308 */       .peek() != color.getColorSpace()) {
/*      */       
/* 1310 */       writeOperand(getName(color.getColorSpace()));
/* 1311 */       writeOperator("cs");
/* 1312 */       setNonStrokingColorSpaceStack(color.getColorSpace());
/*      */     } 
/*      */     
/* 1315 */     for (float value : color.getComponents())
/*      */     {
/* 1317 */       writeOperand(value);
/*      */     }
/*      */     
/* 1320 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern)
/*      */     {
/* 1322 */       writeOperand(color.getPatternName());
/*      */     }
/*      */     
/* 1325 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern || color
/* 1326 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDSeparation || color
/* 1327 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceN || color
/* 1328 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDICCBased) {
/*      */       
/* 1330 */       writeOperator("scn");
/*      */     }
/*      */     else {
/*      */       
/* 1334 */       writeOperator("sc");
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
/*      */   public void setNonStrokingColor(Color color) throws IOException {
/* 1347 */     float[] components = { color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F };
/* 1348 */     PDColor pdColor = new PDColor(components, (PDColorSpace)PDDeviceRGB.INSTANCE);
/* 1349 */     setNonStrokingColor(pdColor);
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
/*      */   @Deprecated
/*      */   public void setNonStrokingColor(float[] components) throws IOException {
/* 1362 */     if (this.nonStrokingColorSpaceStack.isEmpty())
/*      */     {
/* 1364 */       throw new IllegalStateException("The color space must be set before setting a color");
/*      */     }
/*      */     
/* 1367 */     for (float component : components)
/*      */     {
/* 1369 */       writeOperand(component);
/*      */     }
/*      */     
/* 1372 */     PDColorSpace currentNonStrokingColorSpace = this.nonStrokingColorSpaceStack.peek();
/*      */     
/* 1374 */     if (currentNonStrokingColorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDSeparation || currentNonStrokingColorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern || currentNonStrokingColorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDICCBased) {
/*      */ 
/*      */ 
/*      */       
/* 1378 */       writeOperator("scn");
/*      */     }
/*      */     else {
/*      */       
/* 1382 */       writeOperator("sc");
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
/*      */   public void setNonStrokingColor(int r, int g, int b) throws IOException {
/* 1397 */     if (isOutside255Interval(r) || isOutside255Interval(g) || isOutside255Interval(b))
/*      */     {
/* 1399 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/* 1400 */           String.format("(%d,%d,%d)", new Object[] { Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b) }));
/*      */     }
/* 1402 */     writeOperand(r / 255.0F);
/* 1403 */     writeOperand(g / 255.0F);
/* 1404 */     writeOperand(b / 255.0F);
/* 1405 */     writeOperator("rg");
/* 1406 */     setNonStrokingColorSpaceStack((PDColorSpace)PDDeviceRGB.INSTANCE);
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
/*      */   public void setNonStrokingColor(int c, int m, int y, int k) throws IOException {
/* 1421 */     if (isOutside255Interval(c) || isOutside255Interval(m) || isOutside255Interval(y) || isOutside255Interval(k))
/*      */     {
/* 1423 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/* 1424 */           String.format("(%d,%d,%d,%d)", new Object[] { Integer.valueOf(c), Integer.valueOf(m), Integer.valueOf(y), Integer.valueOf(k) }));
/*      */     }
/* 1426 */     setNonStrokingColor(c / 255.0F, m / 255.0F, y / 255.0F, k / 255.0F);
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
/*      */   @Deprecated
/*      */   public void setNonStrokingColor(double c, double m, double y, double k) throws IOException {
/* 1443 */     setNonStrokingColor((float)c, (float)m, (float)y, (float)k);
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
/*      */   public void setNonStrokingColor(float c, float m, float y, float k) throws IOException {
/* 1457 */     if (isOutsideOneInterval(c) || isOutsideOneInterval(m) || isOutsideOneInterval(y) || isOutsideOneInterval(k))
/*      */     {
/* 1459 */       throw new IllegalArgumentException("Parameters must be within 0..1, but are " + 
/* 1460 */           String.format("(%.2f,%.2f,%.2f,%.2f)", new Object[] { Float.valueOf(c), Float.valueOf(m), Float.valueOf(y), Float.valueOf(k) }));
/*      */     }
/* 1462 */     writeOperand(c);
/* 1463 */     writeOperand(m);
/* 1464 */     writeOperand(y);
/* 1465 */     writeOperand(k);
/* 1466 */     writeOperator("k");
/* 1467 */     setNonStrokingColorSpaceStack((PDColorSpace)PDDeviceCMYK.INSTANCE);
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
/*      */   public void setNonStrokingColor(int g) throws IOException {
/* 1479 */     if (isOutside255Interval(g))
/*      */     {
/* 1481 */       throw new IllegalArgumentException("Parameter must be within 0..255, but is " + g);
/*      */     }
/* 1483 */     setNonStrokingColor(g / 255.0F);
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
/*      */   @Deprecated
/*      */   public void setNonStrokingColor(double g) throws IOException {
/* 1497 */     setNonStrokingColor((float)g);
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
/*      */   public void setNonStrokingColor(float g) throws IOException {
/* 1509 */     if (isOutsideOneInterval(g))
/*      */     {
/* 1511 */       throw new IllegalArgumentException("Parameter must be within 0..1, but is " + g);
/*      */     }
/* 1513 */     writeOperand(g);
/* 1514 */     writeOperator("g");
/* 1515 */     setNonStrokingColorSpaceStack((PDColorSpace)PDDeviceGray.INSTANCE);
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
/*      */   public void addRect(float x, float y, float width, float height) throws IOException {
/* 1530 */     if (this.inTextMode)
/*      */     {
/* 1532 */       throw new IllegalStateException("Error: addRect is not allowed within a text block.");
/*      */     }
/* 1534 */     writeOperand(x);
/* 1535 */     writeOperand(y);
/* 1536 */     writeOperand(width);
/* 1537 */     writeOperand(height);
/* 1538 */     writeOperator("re");
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
/*      */   @Deprecated
/*      */   public void fillRect(float x, float y, float width, float height) throws IOException {
/* 1555 */     if (this.inTextMode)
/*      */     {
/* 1557 */       throw new IllegalStateException("Error: fillRect is not allowed within a text block.");
/*      */     }
/* 1559 */     addRect(x, y, width, height);
/* 1560 */     fill();
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
/*      */   @Deprecated
/*      */   public void addBezier312(float x1, float y1, float x2, float y2, float x3, float y3) throws IOException {
/* 1578 */     curveTo(x1, y1, x2, y2, x3, y3);
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
/*      */   public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) throws IOException {
/* 1596 */     if (this.inTextMode)
/*      */     {
/* 1598 */       throw new IllegalStateException("Error: curveTo is not allowed within a text block.");
/*      */     }
/* 1600 */     writeOperand(x1);
/* 1601 */     writeOperand(y1);
/* 1602 */     writeOperand(x2);
/* 1603 */     writeOperand(y2);
/* 1604 */     writeOperand(x3);
/* 1605 */     writeOperand(y3);
/* 1606 */     writeOperator("c");
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
/*      */   @Deprecated
/*      */   public void addBezier32(float x2, float y2, float x3, float y3) throws IOException {
/* 1623 */     curveTo2(x2, y2, x3, y3);
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
/*      */   public void curveTo2(float x2, float y2, float x3, float y3) throws IOException {
/* 1639 */     if (this.inTextMode)
/*      */     {
/* 1641 */       throw new IllegalStateException("Error: curveTo2 is not allowed within a text block.");
/*      */     }
/* 1643 */     writeOperand(x2);
/* 1644 */     writeOperand(y2);
/* 1645 */     writeOperand(x3);
/* 1646 */     writeOperand(y3);
/* 1647 */     writeOperator("v");
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
/*      */   @Deprecated
/*      */   public void addBezier31(float x1, float y1, float x3, float y3) throws IOException {
/* 1664 */     curveTo1(x1, y1, x3, y3);
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
/*      */   public void curveTo1(float x1, float y1, float x3, float y3) throws IOException {
/* 1680 */     if (this.inTextMode)
/*      */     {
/* 1682 */       throw new IllegalStateException("Error: curveTo1 is not allowed within a text block.");
/*      */     }
/* 1684 */     writeOperand(x1);
/* 1685 */     writeOperand(y1);
/* 1686 */     writeOperand(x3);
/* 1687 */     writeOperand(y3);
/* 1688 */     writeOperator("y");
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
/*      */   public void moveTo(float x, float y) throws IOException {
/* 1701 */     if (this.inTextMode)
/*      */     {
/* 1703 */       throw new IllegalStateException("Error: moveTo is not allowed within a text block.");
/*      */     }
/* 1705 */     writeOperand(x);
/* 1706 */     writeOperand(y);
/* 1707 */     writeOperator("m");
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
/*      */   public void lineTo(float x, float y) throws IOException {
/* 1720 */     if (this.inTextMode)
/*      */     {
/* 1722 */       throw new IllegalStateException("Error: lineTo is not allowed within a text block.");
/*      */     }
/* 1724 */     writeOperand(x);
/* 1725 */     writeOperand(y);
/* 1726 */     writeOperator("l");
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
/*      */   @Deprecated
/*      */   public void addLine(float xStart, float yStart, float xEnd, float yEnd) throws IOException {
/* 1744 */     if (this.inTextMode)
/*      */     {
/* 1746 */       throw new IllegalStateException("Error: addLine is not allowed within a text block.");
/*      */     }
/* 1748 */     moveTo(xStart, yStart);
/* 1749 */     lineTo(xEnd, yEnd);
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
/*      */   @Deprecated
/*      */   public void drawLine(float xStart, float yStart, float xEnd, float yEnd) throws IOException {
/* 1767 */     if (this.inTextMode)
/*      */     {
/* 1769 */       throw new IllegalStateException("Error: drawLine is not allowed within a text block.");
/*      */     }
/* 1771 */     moveTo(xStart, yStart);
/* 1772 */     lineTo(xEnd, yEnd);
/* 1773 */     stroke();
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
/*      */   @Deprecated
/*      */   public void addPolygon(float[] x, float[] y) throws IOException {
/* 1788 */     if (this.inTextMode)
/*      */     {
/* 1790 */       throw new IllegalStateException("Error: addPolygon is not allowed within a text block.");
/*      */     }
/* 1792 */     if (x.length != y.length)
/*      */     {
/* 1794 */       throw new IllegalArgumentException("Error: some points are missing coordinate");
/*      */     }
/* 1796 */     for (int i = 0; i < x.length; i++) {
/*      */       
/* 1798 */       if (i == 0) {
/*      */         
/* 1800 */         moveTo(x[i], y[i]);
/*      */       }
/*      */       else {
/*      */         
/* 1804 */         lineTo(x[i], y[i]);
/*      */       } 
/*      */     } 
/* 1807 */     closeSubPath();
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
/*      */   @Deprecated
/*      */   public void drawPolygon(float[] x, float[] y) throws IOException {
/* 1821 */     if (this.inTextMode)
/*      */     {
/* 1823 */       throw new IllegalStateException("Error: drawPolygon is not allowed within a text block.");
/*      */     }
/* 1825 */     addPolygon(x, y);
/* 1826 */     stroke();
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
/*      */   @Deprecated
/*      */   public void fillPolygon(float[] x, float[] y) throws IOException {
/* 1840 */     if (this.inTextMode)
/*      */     {
/* 1842 */       throw new IllegalStateException("Error: fillPolygon is not allowed within a text block.");
/*      */     }
/* 1844 */     addPolygon(x, y);
/* 1845 */     fill();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stroke() throws IOException {
/* 1856 */     if (this.inTextMode)
/*      */     {
/* 1858 */       throw new IllegalStateException("Error: stroke is not allowed within a text block.");
/*      */     }
/* 1860 */     writeOperator("S");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeAndStroke() throws IOException {
/* 1871 */     if (this.inTextMode)
/*      */     {
/* 1873 */       throw new IllegalStateException("Error: closeAndStroke is not allowed within a text block.");
/*      */     }
/* 1875 */     writeOperator("s");
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
/*      */   @Deprecated
/*      */   public void fill(int windingRule) throws IOException {
/* 1889 */     if (windingRule == 1) {
/*      */       
/* 1891 */       fill();
/*      */     }
/* 1893 */     else if (windingRule == 0) {
/*      */       
/* 1895 */       fillEvenOdd();
/*      */     }
/*      */     else {
/*      */       
/* 1899 */       throw new IllegalArgumentException("Error: unknown value for winding rule");
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
/*      */   public void fill() throws IOException {
/* 1911 */     if (this.inTextMode)
/*      */     {
/* 1913 */       throw new IllegalStateException("Error: fill is not allowed within a text block.");
/*      */     }
/* 1915 */     writeOperator("f");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fillEvenOdd() throws IOException {
/* 1926 */     if (this.inTextMode)
/*      */     {
/* 1928 */       throw new IllegalStateException("Error: fillEvenOdd is not allowed within a text block.");
/*      */     }
/* 1930 */     writeOperator("f*");
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
/*      */   public void fillAndStroke() throws IOException {
/* 1943 */     if (this.inTextMode)
/*      */     {
/* 1945 */       throw new IllegalStateException("Error: fillAndStroke is not allowed within a text block.");
/*      */     }
/* 1947 */     writeOperator("B");
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
/*      */   public void fillAndStrokeEvenOdd() throws IOException {
/* 1960 */     if (this.inTextMode)
/*      */     {
/* 1962 */       throw new IllegalStateException("Error: fillAndStrokeEvenOdd is not allowed within a text block.");
/*      */     }
/* 1964 */     writeOperator("B*");
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
/*      */   public void closeAndFillAndStroke() throws IOException {
/* 1977 */     if (this.inTextMode)
/*      */     {
/* 1979 */       throw new IllegalStateException("Error: closeAndFillAndStroke is not allowed within a text block.");
/*      */     }
/* 1981 */     writeOperator("b");
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
/*      */   public void closeAndFillAndStrokeEvenOdd() throws IOException {
/* 1994 */     if (this.inTextMode)
/*      */     {
/* 1996 */       throw new IllegalStateException("Error: closeAndFillAndStrokeEvenOdd is not allowed within a text block.");
/*      */     }
/* 1998 */     writeOperator("b*");
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
/*      */   public void shadingFill(PDShading shading) throws IOException {
/* 2010 */     if (this.inTextMode)
/*      */     {
/* 2012 */       throw new IllegalStateException("Error: shadingFill is not allowed within a text block.");
/*      */     }
/*      */     
/* 2015 */     writeOperand(this.resources.add(shading));
/* 2016 */     writeOperator("sh");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void closeSubPath() throws IOException {
/* 2028 */     closePath();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closePath() throws IOException {
/* 2039 */     if (this.inTextMode)
/*      */     {
/* 2041 */       throw new IllegalStateException("Error: closePath is not allowed within a text block.");
/*      */     }
/* 2043 */     writeOperator("h");
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
/*      */   @Deprecated
/*      */   public void clipPath(int windingRule) throws IOException {
/* 2057 */     if (this.inTextMode)
/*      */     {
/* 2059 */       throw new IllegalStateException("Error: clipPath is not allowed within a text block.");
/*      */     }
/* 2061 */     if (windingRule == 1) {
/*      */       
/* 2063 */       writeOperator("W");
/*      */     }
/* 2065 */     else if (windingRule == 0) {
/*      */       
/* 2067 */       writeOperator("W*");
/*      */     }
/*      */     else {
/*      */       
/* 2071 */       throw new IllegalArgumentException("Error: unknown value for winding rule");
/*      */     } 
/* 2073 */     writeOperator("n");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clip() throws IOException {
/* 2084 */     if (this.inTextMode)
/*      */     {
/* 2086 */       throw new IllegalStateException("Error: clip is not allowed within a text block.");
/*      */     }
/* 2088 */     writeOperator("W");
/*      */ 
/*      */     
/* 2091 */     writeOperator("n");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clipEvenOdd() throws IOException {
/* 2102 */     if (this.inTextMode)
/*      */     {
/* 2104 */       throw new IllegalStateException("Error: clipEvenOdd is not allowed within a text block.");
/*      */     }
/* 2106 */     writeOperator("W*");
/*      */ 
/*      */     
/* 2109 */     writeOperator("n");
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
/*      */   public void setLineWidth(float lineWidth) throws IOException {
/* 2121 */     if (this.inTextMode)
/*      */     {
/* 2123 */       throw new IllegalStateException("Error: setLineWidth is not allowed within a text block.");
/*      */     }
/* 2125 */     writeOperand(lineWidth);
/* 2126 */     writeOperator("w");
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
/*      */   public void setLineJoinStyle(int lineJoinStyle) throws IOException {
/* 2139 */     if (this.inTextMode)
/*      */     {
/* 2141 */       throw new IllegalStateException("Error: setLineJoinStyle is not allowed within a text block.");
/*      */     }
/* 2143 */     if (lineJoinStyle >= 0 && lineJoinStyle <= 2) {
/*      */       
/* 2145 */       writeOperand(lineJoinStyle);
/* 2146 */       writeOperator("j");
/*      */     }
/*      */     else {
/*      */       
/* 2150 */       throw new IllegalArgumentException("Error: unknown value for line join style");
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
/*      */   public void setLineCapStyle(int lineCapStyle) throws IOException {
/* 2164 */     if (this.inTextMode)
/*      */     {
/* 2166 */       throw new IllegalStateException("Error: setLineCapStyle is not allowed within a text block.");
/*      */     }
/* 2168 */     if (lineCapStyle >= 0 && lineCapStyle <= 2) {
/*      */       
/* 2170 */       writeOperand(lineCapStyle);
/* 2171 */       writeOperator("J");
/*      */     }
/*      */     else {
/*      */       
/* 2175 */       throw new IllegalArgumentException("Error: unknown value for line cap style");
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
/*      */   public void setLineDashPattern(float[] pattern, float phase) throws IOException {
/* 2189 */     if (this.inTextMode)
/*      */     {
/* 2191 */       throw new IllegalStateException("Error: setLineDashPattern is not allowed within a text block.");
/*      */     }
/* 2193 */     write("[");
/* 2194 */     for (float value : pattern)
/*      */     {
/* 2196 */       writeOperand(value);
/*      */     }
/* 2198 */     write("] ");
/* 2199 */     writeOperand(phase);
/* 2200 */     writeOperator("d");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMiterLimit(float miterLimit) throws IOException {
/* 2211 */     if (this.inTextMode)
/*      */     {
/* 2213 */       throw new IllegalStateException("Error: setMiterLimit is not allowed within a text block.");
/*      */     }
/* 2215 */     if (miterLimit <= 0.0D)
/*      */     {
/* 2217 */       throw new IllegalArgumentException("A miter limit <= 0 is invalid and will not render in Acrobat Reader");
/*      */     }
/* 2219 */     writeOperand(miterLimit);
/* 2220 */     writeOperator("M");
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
/*      */   @Deprecated
/*      */   public void beginMarkedContentSequence(COSName tag) throws IOException {
/* 2233 */     beginMarkedContent(tag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void beginMarkedContent(COSName tag) throws IOException {
/* 2244 */     writeOperand(tag);
/* 2245 */     writeOperator("BMC");
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
/*      */   @Deprecated
/*      */   public void beginMarkedContentSequence(COSName tag, COSName propsName) throws IOException {
/* 2260 */     writeOperand(tag);
/* 2261 */     writeOperand(propsName);
/* 2262 */     writeOperator("BDC");
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
/*      */   public void beginMarkedContent(COSName tag, PDPropertyList propertyList) throws IOException {
/* 2275 */     writeOperand(tag);
/* 2276 */     writeOperand(this.resources.add(propertyList));
/* 2277 */     writeOperator("BDC");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void endMarkedContentSequence() throws IOException {
/* 2289 */     endMarkedContent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endMarkedContent() throws IOException {
/* 2299 */     writeOperator("EMC");
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
/*      */   @Deprecated
/*      */   public void appendRawCommands(String commands) throws IOException {
/* 2312 */     this.output.write(commands.getBytes(Charsets.US_ASCII));
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
/*      */   @Deprecated
/*      */   public void appendRawCommands(byte[] commands) throws IOException {
/* 2325 */     this.output.write(commands);
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
/*      */   @Deprecated
/*      */   public void appendRawCommands(int data) throws IOException {
/* 2338 */     this.output.write(data);
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
/*      */   @Deprecated
/*      */   public void appendRawCommands(double data) throws IOException {
/* 2351 */     this.output.write(this.formatDecimal.format(data).getBytes(Charsets.US_ASCII));
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
/*      */   @Deprecated
/*      */   public void appendRawCommands(float data) throws IOException {
/* 2364 */     this.output.write(this.formatDecimal.format(data).getBytes(Charsets.US_ASCII));
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
/*      */   @Deprecated
/*      */   public void appendCOSName(COSName name) throws IOException {
/* 2377 */     name.writePDF(this.output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGraphicsStateParameters(PDExtendedGraphicsState state) throws IOException {
/* 2388 */     writeOperand(this.resources.add(state));
/* 2389 */     writeOperator("gs");
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
/*      */   public void addComment(String comment) throws IOException {
/* 2403 */     if (comment.indexOf('\n') >= 0 || comment.indexOf('\r') >= 0)
/*      */     {
/* 2405 */       throw new IllegalArgumentException("comment should not include a newline");
/*      */     }
/* 2407 */     this.output.write(37);
/* 2408 */     this.output.write(comment.getBytes(Charsets.US_ASCII));
/* 2409 */     this.output.write(10);
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
/*      */   protected void writeOperand(float real) throws IOException {
/* 2421 */     int byteCount = NumberFormatUtil.formatFloatFast(real, this.formatDecimal.getMaximumFractionDigits(), this.formatBuffer);
/*      */     
/* 2423 */     if (byteCount == -1) {
/*      */ 
/*      */       
/* 2426 */       write(this.formatDecimal.format(real));
/*      */     }
/*      */     else {
/*      */       
/* 2430 */       this.output.write(this.formatBuffer, 0, byteCount);
/*      */     } 
/* 2432 */     this.output.write(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeOperand(int integer) throws IOException {
/* 2440 */     write(this.formatDecimal.format(integer));
/* 2441 */     this.output.write(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeOperand(COSName name) throws IOException {
/* 2449 */     name.writePDF(this.output);
/* 2450 */     this.output.write(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeOperator(String text) throws IOException {
/* 2458 */     this.output.write(text.getBytes(Charsets.US_ASCII));
/* 2459 */     this.output.write(10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void write(String text) throws IOException {
/* 2467 */     this.output.write(text.getBytes(Charsets.US_ASCII));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeLine() throws IOException {
/* 2475 */     this.output.write(10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeBytes(byte[] data) throws IOException {
/* 2483 */     this.output.write(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeAffineTransform(AffineTransform transform) throws IOException {
/* 2491 */     double[] values = new double[6];
/* 2492 */     transform.getMatrix(values);
/* 2493 */     for (double v : values)
/*      */     {
/* 2495 */       writeOperand((float)v);
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
/*      */   public void close() throws IOException {
/* 2507 */     if (this.inTextMode)
/*      */     {
/* 2509 */       LOG.warn("You did not call endText(), some viewers won't display your text");
/*      */     }
/* 2511 */     if (this.output != null) {
/*      */       
/* 2513 */       this.output.close();
/* 2514 */       this.output = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isOutside255Interval(int val) {
/* 2520 */     return (val < 0 || val > 255);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isOutsideOneInterval(double val) {
/* 2525 */     return (val < 0.0D || val > 1.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setStrokingColorSpaceStack(PDColorSpace colorSpace) {
/* 2530 */     if (this.strokingColorSpaceStack.isEmpty()) {
/*      */       
/* 2532 */       this.strokingColorSpaceStack.add(colorSpace);
/*      */     }
/*      */     else {
/*      */       
/* 2536 */       this.strokingColorSpaceStack.setElementAt(colorSpace, this.strokingColorSpaceStack.size() - 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setNonStrokingColorSpaceStack(PDColorSpace colorSpace) {
/* 2542 */     if (this.nonStrokingColorSpaceStack.isEmpty()) {
/*      */       
/* 2544 */       this.nonStrokingColorSpaceStack.add(colorSpace);
/*      */     }
/*      */     else {
/*      */       
/* 2548 */       this.nonStrokingColorSpaceStack.setElementAt(colorSpace, this.nonStrokingColorSpaceStack.size() - 1);
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
/*      */   public void setRenderingMode(RenderingMode rm) throws IOException {
/* 2561 */     writeOperand(rm.intValue());
/* 2562 */     writeOperator("Tr");
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
/*      */   public void setCharacterSpacing(float spacing) throws IOException {
/* 2574 */     writeOperand(spacing);
/* 2575 */     writeOperator("Tc");
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
/*      */   public void setWordSpacing(float spacing) throws IOException {
/* 2593 */     writeOperand(spacing);
/* 2594 */     writeOperator("Tw");
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
/*      */   public void setHorizontalScaling(float scale) throws IOException {
/* 2606 */     writeOperand(scale);
/* 2607 */     writeOperator("Tz");
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
/*      */   public void setTextRise(float rise) throws IOException {
/* 2620 */     writeOperand(rise);
/* 2621 */     writeOperator("Ts");
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDPageContentStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */