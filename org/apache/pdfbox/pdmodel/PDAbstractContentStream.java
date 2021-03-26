/*      */ package org.apache.pdfbox.pdmodel;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.io.Closeable;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayDeque;
/*      */ import java.util.Deque;
/*      */ import java.util.Locale;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.pdfwriter.COSWriter;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*      */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*      */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
/*      */ import org.apache.pdfbox.pdmodel.graphics.image.PDInlineImage;
/*      */ import org.apache.pdfbox.pdmodel.graphics.shading.PDShading;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*      */ import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
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
/*      */ abstract class PDAbstractContentStream
/*      */   implements Closeable
/*      */ {
/*   64 */   private static final Log LOG = LogFactory.getLog(PDAbstractContentStream.class);
/*      */   
/*      */   protected final PDDocument document;
/*      */   
/*      */   protected final OutputStream outputStream;
/*      */   
/*      */   protected final PDResources resources;
/*      */   protected boolean inTextMode = false;
/*   72 */   protected final Deque<PDFont> fontStack = new ArrayDeque<PDFont>();
/*      */   
/*   74 */   protected final Deque<PDColorSpace> nonStrokingColorSpaceStack = new ArrayDeque<PDColorSpace>();
/*   75 */   protected final Deque<PDColorSpace> strokingColorSpaceStack = new ArrayDeque<PDColorSpace>();
/*      */ 
/*      */   
/*   78 */   private final NumberFormat formatDecimal = NumberFormat.getNumberInstance(Locale.US);
/*   79 */   private final byte[] formatBuffer = new byte[32];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   PDAbstractContentStream(PDDocument document, OutputStream outputStream, PDResources resources) {
/*   90 */     this.document = document;
/*   91 */     this.outputStream = outputStream;
/*   92 */     this.resources = resources;
/*      */     
/*   94 */     this.formatDecimal.setMaximumFractionDigits(4);
/*   95 */     this.formatDecimal.setGroupingUsed(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setMaximumFractionDigits(int fractionDigitsNumber) {
/*  106 */     this.formatDecimal.setMaximumFractionDigits(fractionDigitsNumber);
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
/*  118 */     if (this.inTextMode)
/*      */     {
/*  120 */       throw new IllegalStateException("Error: Nested beginText() calls are not allowed.");
/*      */     }
/*  122 */     writeOperator("BT");
/*  123 */     this.inTextMode = true;
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
/*  135 */     if (!this.inTextMode)
/*      */     {
/*  137 */       throw new IllegalStateException("Error: You must call beginText() before calling endText.");
/*      */     }
/*  139 */     writeOperator("ET");
/*  140 */     this.inTextMode = false;
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
/*  152 */     if (this.fontStack.isEmpty()) {
/*      */       
/*  154 */       this.fontStack.add(font);
/*      */     }
/*      */     else {
/*      */       
/*  158 */       this.fontStack.pop();
/*  159 */       this.fontStack.push(font);
/*      */     } 
/*      */ 
/*      */     
/*  163 */     if (font.willBeSubset())
/*      */     {
/*  165 */       if (this.document != null) {
/*      */         
/*  167 */         this.document.getFontsToSubset().add(font);
/*      */       }
/*      */       else {
/*      */         
/*  171 */         LOG.warn("attempting to use subset font " + font.getName() + " without proper context");
/*      */       } 
/*      */     }
/*      */     
/*  175 */     writeOperand(this.resources.add(font));
/*  176 */     writeOperand(fontSize);
/*  177 */     writeOperator("Tf");
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
/*  195 */     write("[");
/*  196 */     for (Object obj : textWithPositioningArray) {
/*      */       
/*  198 */       if (obj instanceof String) {
/*      */         
/*  200 */         showTextInternal((String)obj);
/*      */       }
/*  202 */       else if (obj instanceof Float) {
/*      */         
/*  204 */         writeOperand(((Float)obj).floatValue());
/*      */       }
/*      */       else {
/*      */         
/*  208 */         throw new IllegalArgumentException("Argument must consist of array of Float and String types");
/*      */       } 
/*      */     } 
/*  211 */     write("] ");
/*  212 */     writeOperator("TJ");
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
/*  224 */     showTextInternal(text);
/*  225 */     write(" ");
/*  226 */     writeOperator("Tj");
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
/*  238 */     if (!this.inTextMode)
/*      */     {
/*  240 */       throw new IllegalStateException("Must call beginText() before showText()");
/*      */     }
/*      */     
/*  243 */     if (this.fontStack.isEmpty())
/*      */     {
/*  245 */       throw new IllegalStateException("Must call setFont() before showText()");
/*      */     }
/*      */     
/*  248 */     PDFont font = this.fontStack.peek();
/*      */ 
/*      */     
/*  251 */     byte[] encodedText = null;
/*      */     
/*  253 */     if (encodedText == null)
/*      */     {
/*  255 */       encodedText = font.encode(text);
/*      */     }
/*      */ 
/*      */     
/*  259 */     if (font.willBeSubset()) {
/*      */       
/*  261 */       int offset = 0;
/*  262 */       while (offset < text.length()) {
/*      */         
/*  264 */         int codePoint = text.codePointAt(offset);
/*  265 */         font.addToSubset(codePoint);
/*  266 */         offset += Character.charCount(codePoint);
/*      */       } 
/*      */     } 
/*      */     
/*  270 */     COSWriter.writeString(encodedText, this.outputStream);
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
/*  281 */     writeOperand(leading);
/*  282 */     writeOperator("TL");
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
/*  293 */     if (!this.inTextMode)
/*      */     {
/*  295 */       throw new IllegalStateException("Must call beginText() before newLine()");
/*      */     }
/*  297 */     writeOperator("T*");
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
/*  311 */     if (!this.inTextMode)
/*      */     {
/*  313 */       throw new IllegalStateException("Error: must call beginText() before newLineAtOffset()");
/*      */     }
/*  315 */     writeOperand(tx);
/*  316 */     writeOperand(ty);
/*  317 */     writeOperator("Td");
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
/*  330 */     if (!this.inTextMode)
/*      */     {
/*  332 */       throw new IllegalStateException("Error: must call beginText() before setTextMatrix");
/*      */     }
/*  334 */     writeAffineTransform(matrix.createAffineTransform());
/*  335 */     writeOperator("Tm");
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
/*  349 */     drawImage(image, x, y, image.getWidth(), image.getHeight());
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
/*  366 */     if (this.inTextMode)
/*      */     {
/*  368 */       throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
/*      */     }
/*      */     
/*  371 */     saveGraphicsState();
/*      */     
/*  373 */     AffineTransform transform = new AffineTransform(width, 0.0F, 0.0F, height, x, y);
/*  374 */     transform(new Matrix(transform));
/*      */     
/*  376 */     writeOperand(this.resources.add(image));
/*  377 */     writeOperator("Do");
/*      */     
/*  379 */     restoreGraphicsState();
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
/*  393 */     if (this.inTextMode)
/*      */     {
/*  395 */       throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
/*      */     }
/*      */     
/*  398 */     saveGraphicsState();
/*      */     
/*  400 */     AffineTransform transform = matrix.createAffineTransform();
/*  401 */     transform(new Matrix(transform));
/*      */     
/*  403 */     writeOperand(this.resources.add(image));
/*  404 */     writeOperator("Do");
/*      */     
/*  406 */     restoreGraphicsState();
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
/*  420 */     drawImage(inlineImage, x, y, inlineImage.getWidth(), inlineImage.getHeight());
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
/*  437 */     if (this.inTextMode)
/*      */     {
/*  439 */       throw new IllegalStateException("Error: drawImage is not allowed within a text block.");
/*      */     }
/*      */     
/*  442 */     saveGraphicsState();
/*  443 */     transform(new Matrix(width, 0.0F, 0.0F, height, x, y));
/*      */ 
/*      */     
/*  446 */     StringBuilder sb = new StringBuilder();
/*  447 */     sb.append("BI");
/*      */     
/*  449 */     sb.append("\n /W ");
/*  450 */     sb.append(inlineImage.getWidth());
/*      */     
/*  452 */     sb.append("\n /H ");
/*  453 */     sb.append(inlineImage.getHeight());
/*      */     
/*  455 */     sb.append("\n /CS ");
/*  456 */     sb.append("/");
/*  457 */     sb.append(inlineImage.getColorSpace().getName());
/*      */     
/*  459 */     if (inlineImage.getDecode() != null && inlineImage.getDecode().size() > 0) {
/*      */       
/*  461 */       sb.append("\n /D ");
/*  462 */       sb.append("[");
/*  463 */       for (COSBase base : inlineImage.getDecode()) {
/*      */         
/*  465 */         sb.append(((COSNumber)base).intValue());
/*  466 */         sb.append(" ");
/*      */       } 
/*  468 */       sb.append("]");
/*      */     } 
/*      */     
/*  471 */     if (inlineImage.isStencil())
/*      */     {
/*  473 */       sb.append("\n /IM true");
/*      */     }
/*      */     
/*  476 */     sb.append("\n /BPC ");
/*  477 */     sb.append(inlineImage.getBitsPerComponent());
/*      */ 
/*      */     
/*  480 */     write(sb.toString());
/*  481 */     writeLine();
/*      */ 
/*      */     
/*  484 */     writeOperator("ID");
/*  485 */     writeBytes(inlineImage.getData());
/*  486 */     writeLine();
/*  487 */     writeOperator("EI");
/*      */     
/*  489 */     restoreGraphicsState();
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
/*  501 */     if (this.inTextMode)
/*      */     {
/*  503 */       throw new IllegalStateException("Error: drawForm is not allowed within a text block.");
/*      */     }
/*      */     
/*  506 */     writeOperand(this.resources.add(form));
/*  507 */     writeOperator("Do");
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
/*  518 */     if (this.inTextMode)
/*      */     {
/*  520 */       throw new IllegalStateException("Error: Modifying the current transformation matrix is not allowed within text objects.");
/*      */     }
/*      */     
/*  523 */     writeAffineTransform(matrix.createAffineTransform());
/*  524 */     writeOperator("cm");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveGraphicsState() throws IOException {
/*  533 */     if (this.inTextMode)
/*      */     {
/*  535 */       throw new IllegalStateException("Error: Saving the graphics state is not allowed within text objects.");
/*      */     }
/*      */     
/*  538 */     if (!this.fontStack.isEmpty())
/*      */     {
/*  540 */       this.fontStack.push(this.fontStack.peek());
/*      */     }
/*  542 */     if (!this.strokingColorSpaceStack.isEmpty())
/*      */     {
/*  544 */       this.strokingColorSpaceStack.push(this.strokingColorSpaceStack.peek());
/*      */     }
/*  546 */     if (!this.nonStrokingColorSpaceStack.isEmpty())
/*      */     {
/*  548 */       this.nonStrokingColorSpaceStack.push(this.nonStrokingColorSpaceStack.peek());
/*      */     }
/*  550 */     writeOperator("q");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void restoreGraphicsState() throws IOException {
/*  559 */     if (this.inTextMode)
/*      */     {
/*  561 */       throw new IllegalStateException("Error: Restoring the graphics state is not allowed within text objects.");
/*      */     }
/*      */     
/*  564 */     if (!this.fontStack.isEmpty())
/*      */     {
/*  566 */       this.fontStack.pop();
/*      */     }
/*  568 */     if (!this.strokingColorSpaceStack.isEmpty())
/*      */     {
/*  570 */       this.strokingColorSpaceStack.pop();
/*      */     }
/*  572 */     if (!this.nonStrokingColorSpaceStack.isEmpty())
/*      */     {
/*  574 */       this.nonStrokingColorSpaceStack.pop();
/*      */     }
/*  576 */     writeOperator("Q");
/*      */   }
/*      */ 
/*      */   
/*      */   protected COSName getName(PDColorSpace colorSpace) {
/*  581 */     if (colorSpace instanceof PDDeviceGray || colorSpace instanceof PDDeviceRGB || colorSpace instanceof PDDeviceCMYK)
/*      */     {
/*      */ 
/*      */       
/*  585 */       return COSName.getPDFName(colorSpace.getName());
/*      */     }
/*      */ 
/*      */     
/*  589 */     return this.resources.add(colorSpace);
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
/*  601 */     if (this.strokingColorSpaceStack.isEmpty() || this.strokingColorSpaceStack
/*  602 */       .peek() != color.getColorSpace()) {
/*      */       
/*  604 */       writeOperand(getName(color.getColorSpace()));
/*  605 */       writeOperator("CS");
/*  606 */       setStrokingColorSpaceStack(color.getColorSpace());
/*      */     } 
/*      */     
/*  609 */     for (float value : color.getComponents())
/*      */     {
/*  611 */       writeOperand(value);
/*      */     }
/*      */     
/*  614 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern)
/*      */     {
/*  616 */       writeOperand(color.getPatternName());
/*      */     }
/*      */     
/*  619 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern || color
/*  620 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDSeparation || color
/*  621 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceN || color
/*  622 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDICCBased) {
/*      */       
/*  624 */       writeOperator("SCN");
/*      */     }
/*      */     else {
/*      */       
/*  628 */       writeOperator("SC");
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
/*  641 */     float[] components = { color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F };
/*  642 */     PDColor pdColor = new PDColor(components, (PDColorSpace)PDDeviceRGB.INSTANCE);
/*  643 */     setStrokingColor(pdColor);
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
/*  657 */     if (isOutside255Interval(r) || isOutside255Interval(g) || isOutside255Interval(b))
/*      */     {
/*  659 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/*  660 */           String.format("(%d,%d,%d)", new Object[] { Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b) }));
/*      */     }
/*  662 */     writeOperand(r / 255.0F);
/*  663 */     writeOperand(g / 255.0F);
/*  664 */     writeOperand(b / 255.0F);
/*  665 */     writeOperator("RG");
/*  666 */     setStrokingColorSpaceStack((PDColorSpace)PDDeviceRGB.INSTANCE);
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
/*  681 */     if (isOutsideOneInterval(c) || isOutsideOneInterval(m) || isOutsideOneInterval(y) || isOutsideOneInterval(k))
/*      */     {
/*  683 */       throw new IllegalArgumentException("Parameters must be within 0..1, but are " + 
/*  684 */           String.format("(%.2f,%.2f,%.2f,%.2f)", new Object[] { Float.valueOf(c), Float.valueOf(m), Float.valueOf(y), Float.valueOf(k) }));
/*      */     }
/*  686 */     writeOperand(c);
/*  687 */     writeOperand(m);
/*  688 */     writeOperand(y);
/*  689 */     writeOperand(k);
/*  690 */     writeOperator("K");
/*  691 */     setStrokingColorSpaceStack((PDColorSpace)PDDeviceCMYK.INSTANCE);
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
/*  703 */     if (isOutsideOneInterval(g))
/*      */     {
/*  705 */       throw new IllegalArgumentException("Parameter must be within 0..1, but is " + g);
/*      */     }
/*  707 */     writeOperand(g);
/*  708 */     writeOperator("G");
/*  709 */     setStrokingColorSpaceStack((PDColorSpace)PDDeviceGray.INSTANCE);
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
/*  720 */     if (this.nonStrokingColorSpaceStack.isEmpty() || this.nonStrokingColorSpaceStack
/*  721 */       .peek() != color.getColorSpace()) {
/*      */       
/*  723 */       writeOperand(getName(color.getColorSpace()));
/*  724 */       writeOperator("cs");
/*  725 */       setNonStrokingColorSpaceStack(color.getColorSpace());
/*      */     } 
/*      */     
/*  728 */     for (float value : color.getComponents())
/*      */     {
/*  730 */       writeOperand(value);
/*      */     }
/*      */     
/*  733 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern)
/*      */     {
/*  735 */       writeOperand(color.getPatternName());
/*      */     }
/*      */     
/*  738 */     if (color.getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern || color
/*  739 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDSeparation || color
/*  740 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDDeviceN || color
/*  741 */       .getColorSpace() instanceof org.apache.pdfbox.pdmodel.graphics.color.PDICCBased) {
/*      */       
/*  743 */       writeOperator("scn");
/*      */     }
/*      */     else {
/*      */       
/*  747 */       writeOperator("sc");
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
/*  760 */     float[] components = { color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F };
/*  761 */     PDColor pdColor = new PDColor(components, (PDColorSpace)PDDeviceRGB.INSTANCE);
/*  762 */     setNonStrokingColor(pdColor);
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
/*  776 */     if (isOutside255Interval(r) || isOutside255Interval(g) || isOutside255Interval(b))
/*      */     {
/*  778 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/*  779 */           String.format("(%d,%d,%d)", new Object[] { Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b) }));
/*      */     }
/*  781 */     writeOperand(r / 255.0F);
/*  782 */     writeOperand(g / 255.0F);
/*  783 */     writeOperand(b / 255.0F);
/*  784 */     writeOperator("rg");
/*  785 */     setNonStrokingColorSpaceStack((PDColorSpace)PDDeviceRGB.INSTANCE);
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
/*  800 */     if (isOutside255Interval(c) || isOutside255Interval(m) || isOutside255Interval(y) || isOutside255Interval(k))
/*      */     {
/*  802 */       throw new IllegalArgumentException("Parameters must be within 0..255, but are " + 
/*  803 */           String.format("(%d,%d,%d,%d)", new Object[] { Integer.valueOf(c), Integer.valueOf(m), Integer.valueOf(y), Integer.valueOf(k) }));
/*      */     }
/*  805 */     setNonStrokingColor(c / 255.0F, m / 255.0F, y / 255.0F, k / 255.0F);
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
/*  819 */     if (isOutsideOneInterval(c) || isOutsideOneInterval(m) || isOutsideOneInterval(y) || isOutsideOneInterval(k))
/*      */     {
/*  821 */       throw new IllegalArgumentException("Parameters must be within 0..1, but are " + 
/*  822 */           String.format("(%.2f,%.2f,%.2f,%.2f)", new Object[] { Float.valueOf(c), Float.valueOf(m), Float.valueOf(y), Float.valueOf(k) }));
/*      */     }
/*  824 */     writeOperand(c);
/*  825 */     writeOperand(m);
/*  826 */     writeOperand(y);
/*  827 */     writeOperand(k);
/*  828 */     writeOperator("k");
/*  829 */     setNonStrokingColorSpaceStack((PDColorSpace)PDDeviceCMYK.INSTANCE);
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
/*  841 */     if (isOutside255Interval(g))
/*      */     {
/*  843 */       throw new IllegalArgumentException("Parameter must be within 0..255, but is " + g);
/*      */     }
/*  845 */     setNonStrokingColor(g / 255.0F);
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
/*  857 */     if (isOutsideOneInterval(g))
/*      */     {
/*  859 */       throw new IllegalArgumentException("Parameter must be within 0..1, but is " + g);
/*      */     }
/*  861 */     writeOperand(g);
/*  862 */     writeOperator("g");
/*  863 */     setNonStrokingColorSpaceStack((PDColorSpace)PDDeviceGray.INSTANCE);
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
/*  878 */     if (this.inTextMode)
/*      */     {
/*  880 */       throw new IllegalStateException("Error: addRect is not allowed within a text block.");
/*      */     }
/*  882 */     writeOperand(x);
/*  883 */     writeOperand(y);
/*  884 */     writeOperand(width);
/*  885 */     writeOperand(height);
/*  886 */     writeOperator("re");
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
/*  904 */     if (this.inTextMode)
/*      */     {
/*  906 */       throw new IllegalStateException("Error: curveTo is not allowed within a text block.");
/*      */     }
/*  908 */     writeOperand(x1);
/*  909 */     writeOperand(y1);
/*  910 */     writeOperand(x2);
/*  911 */     writeOperand(y2);
/*  912 */     writeOperand(x3);
/*  913 */     writeOperand(y3);
/*  914 */     writeOperator("c");
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
/*  930 */     if (this.inTextMode)
/*      */     {
/*  932 */       throw new IllegalStateException("Error: curveTo2 is not allowed within a text block.");
/*      */     }
/*  934 */     writeOperand(x2);
/*  935 */     writeOperand(y2);
/*  936 */     writeOperand(x3);
/*  937 */     writeOperand(y3);
/*  938 */     writeOperator("v");
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
/*  954 */     if (this.inTextMode)
/*      */     {
/*  956 */       throw new IllegalStateException("Error: curveTo1 is not allowed within a text block.");
/*      */     }
/*  958 */     writeOperand(x1);
/*  959 */     writeOperand(y1);
/*  960 */     writeOperand(x3);
/*  961 */     writeOperand(y3);
/*  962 */     writeOperator("y");
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
/*  975 */     if (this.inTextMode)
/*      */     {
/*  977 */       throw new IllegalStateException("Error: moveTo is not allowed within a text block.");
/*      */     }
/*  979 */     writeOperand(x);
/*  980 */     writeOperand(y);
/*  981 */     writeOperator("m");
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
/*  994 */     if (this.inTextMode)
/*      */     {
/*  996 */       throw new IllegalStateException("Error: lineTo is not allowed within a text block.");
/*      */     }
/*  998 */     writeOperand(x);
/*  999 */     writeOperand(y);
/* 1000 */     writeOperator("l");
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
/* 1011 */     if (this.inTextMode)
/*      */     {
/* 1013 */       throw new IllegalStateException("Error: stroke is not allowed within a text block.");
/*      */     }
/* 1015 */     writeOperator("S");
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
/* 1026 */     if (this.inTextMode)
/*      */     {
/* 1028 */       throw new IllegalStateException("Error: closeAndStroke is not allowed within a text block.");
/*      */     }
/* 1030 */     writeOperator("s");
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
/* 1041 */     if (this.inTextMode)
/*      */     {
/* 1043 */       throw new IllegalStateException("Error: fill is not allowed within a text block.");
/*      */     }
/* 1045 */     writeOperator("f");
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
/* 1056 */     if (this.inTextMode)
/*      */     {
/* 1058 */       throw new IllegalStateException("Error: fillEvenOdd is not allowed within a text block.");
/*      */     }
/* 1060 */     writeOperator("f*");
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
/* 1073 */     if (this.inTextMode)
/*      */     {
/* 1075 */       throw new IllegalStateException("Error: fillAndStroke is not allowed within a text block.");
/*      */     }
/* 1077 */     writeOperator("B");
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
/* 1090 */     if (this.inTextMode)
/*      */     {
/* 1092 */       throw new IllegalStateException("Error: fillAndStrokeEvenOdd is not allowed within a text block.");
/*      */     }
/* 1094 */     writeOperator("B*");
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
/* 1107 */     if (this.inTextMode)
/*      */     {
/* 1109 */       throw new IllegalStateException("Error: closeAndFillAndStroke is not allowed within a text block.");
/*      */     }
/* 1111 */     writeOperator("b");
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
/* 1124 */     if (this.inTextMode)
/*      */     {
/* 1126 */       throw new IllegalStateException("Error: closeAndFillAndStrokeEvenOdd is not allowed within a text block.");
/*      */     }
/* 1128 */     writeOperator("b*");
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
/* 1140 */     if (this.inTextMode)
/*      */     {
/* 1142 */       throw new IllegalStateException("Error: shadingFill is not allowed within a text block.");
/*      */     }
/*      */     
/* 1145 */     writeOperand(this.resources.add(shading));
/* 1146 */     writeOperator("sh");
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
/* 1157 */     if (this.inTextMode)
/*      */     {
/* 1159 */       throw new IllegalStateException("Error: closePath is not allowed within a text block.");
/*      */     }
/* 1161 */     writeOperator("h");
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
/* 1172 */     if (this.inTextMode)
/*      */     {
/* 1174 */       throw new IllegalStateException("Error: clip is not allowed within a text block.");
/*      */     }
/* 1176 */     writeOperator("W");
/*      */ 
/*      */     
/* 1179 */     writeOperator("n");
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
/* 1190 */     if (this.inTextMode)
/*      */     {
/* 1192 */       throw new IllegalStateException("Error: clipEvenOdd is not allowed within a text block.");
/*      */     }
/* 1194 */     writeOperator("W*");
/*      */ 
/*      */     
/* 1197 */     writeOperator("n");
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
/* 1209 */     if (this.inTextMode)
/*      */     {
/* 1211 */       throw new IllegalStateException("Error: setLineWidth is not allowed within a text block.");
/*      */     }
/* 1213 */     writeOperand(lineWidth);
/* 1214 */     writeOperator("w");
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
/* 1227 */     if (this.inTextMode)
/*      */     {
/* 1229 */       throw new IllegalStateException("Error: setLineJoinStyle is not allowed within a text block.");
/*      */     }
/* 1231 */     if (lineJoinStyle >= 0 && lineJoinStyle <= 2) {
/*      */       
/* 1233 */       writeOperand(lineJoinStyle);
/* 1234 */       writeOperator("j");
/*      */     }
/*      */     else {
/*      */       
/* 1238 */       throw new IllegalArgumentException("Error: unknown value for line join style");
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
/* 1252 */     if (this.inTextMode)
/*      */     {
/* 1254 */       throw new IllegalStateException("Error: setLineCapStyle is not allowed within a text block.");
/*      */     }
/* 1256 */     if (lineCapStyle >= 0 && lineCapStyle <= 2) {
/*      */       
/* 1258 */       writeOperand(lineCapStyle);
/* 1259 */       writeOperator("J");
/*      */     }
/*      */     else {
/*      */       
/* 1263 */       throw new IllegalArgumentException("Error: unknown value for line cap style");
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
/* 1277 */     if (this.inTextMode)
/*      */     {
/* 1279 */       throw new IllegalStateException("Error: setLineDashPattern is not allowed within a text block.");
/*      */     }
/* 1281 */     write("[");
/* 1282 */     for (float value : pattern)
/*      */     {
/* 1284 */       writeOperand(value);
/*      */     }
/* 1286 */     write("] ");
/* 1287 */     writeOperand(phase);
/* 1288 */     writeOperator("d");
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
/* 1299 */     if (this.inTextMode)
/*      */     {
/* 1301 */       throw new IllegalStateException("Error: setMiterLimit is not allowed within a text block.");
/*      */     }
/* 1303 */     if (miterLimit <= 0.0D)
/*      */     {
/* 1305 */       throw new IllegalArgumentException("A miter limit <= 0 is invalid and will not render in Acrobat Reader");
/*      */     }
/* 1307 */     writeOperand(miterLimit);
/* 1308 */     writeOperator("M");
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
/* 1319 */     writeOperand(tag);
/* 1320 */     writeOperator("BMC");
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
/* 1333 */     writeOperand(tag);
/* 1334 */     writeOperand(this.resources.add(propertyList));
/* 1335 */     writeOperator("BDC");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endMarkedContent() throws IOException {
/* 1345 */     writeOperator("EMC");
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
/* 1356 */     writeOperand(this.resources.add(state));
/* 1357 */     writeOperator("gs");
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
/*      */   public void addComment(String comment) throws IOException {
/* 1370 */     if (comment.indexOf('\n') >= 0 || comment.indexOf('\r') >= 0)
/*      */     {
/* 1372 */       throw new IllegalArgumentException("comment should not include a newline");
/*      */     }
/* 1374 */     this.outputStream.write(37);
/* 1375 */     this.outputStream.write(comment.getBytes(Charsets.US_ASCII));
/* 1376 */     this.outputStream.write(10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeOperand(float real) throws IOException {
/* 1386 */     int byteCount = NumberFormatUtil.formatFloatFast(real, this.formatDecimal.getMaximumFractionDigits(), this.formatBuffer);
/*      */     
/* 1388 */     if (byteCount == -1) {
/*      */ 
/*      */       
/* 1391 */       write(this.formatDecimal.format(real));
/*      */     }
/*      */     else {
/*      */       
/* 1395 */       this.outputStream.write(this.formatBuffer, 0, byteCount);
/*      */     } 
/* 1397 */     this.outputStream.write(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeOperand(int integer) throws IOException {
/* 1407 */     write(this.formatDecimal.format(integer));
/* 1408 */     this.outputStream.write(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeOperand(COSName name) throws IOException {
/* 1418 */     name.writePDF(this.outputStream);
/* 1419 */     this.outputStream.write(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeOperator(String text) throws IOException {
/* 1429 */     this.outputStream.write(text.getBytes(Charsets.US_ASCII));
/* 1430 */     this.outputStream.write(10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void write(String text) throws IOException {
/* 1440 */     this.outputStream.write(text.getBytes(Charsets.US_ASCII));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void write(byte[] data) throws IOException {
/* 1450 */     this.outputStream.write(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeLine() throws IOException {
/* 1459 */     this.outputStream.write(10);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeBytes(byte[] data) throws IOException {
/* 1469 */     this.outputStream.write(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeAffineTransform(AffineTransform transform) throws IOException {
/* 1477 */     double[] values = new double[6];
/* 1478 */     transform.getMatrix(values);
/* 1479 */     for (double v : values)
/*      */     {
/* 1481 */       writeOperand((float)v);
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
/* 1493 */     if (this.inTextMode)
/*      */     {
/* 1495 */       LOG.warn("You did not call endText(), some viewers won't display your text");
/*      */     }
/* 1497 */     this.outputStream.close();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isOutside255Interval(int val) {
/* 1502 */     return (val < 0 || val > 255);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isOutsideOneInterval(double val) {
/* 1507 */     return (val < 0.0D || val > 1.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setStrokingColorSpaceStack(PDColorSpace colorSpace) {
/* 1512 */     if (this.strokingColorSpaceStack.isEmpty()) {
/*      */       
/* 1514 */       this.strokingColorSpaceStack.add(colorSpace);
/*      */     }
/*      */     else {
/*      */       
/* 1518 */       this.strokingColorSpaceStack.pop();
/* 1519 */       this.strokingColorSpaceStack.push(colorSpace);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setNonStrokingColorSpaceStack(PDColorSpace colorSpace) {
/* 1525 */     if (this.nonStrokingColorSpaceStack.isEmpty()) {
/*      */       
/* 1527 */       this.nonStrokingColorSpaceStack.add(colorSpace);
/*      */     }
/*      */     else {
/*      */       
/* 1531 */       this.nonStrokingColorSpaceStack.pop();
/* 1532 */       this.nonStrokingColorSpaceStack.push(colorSpace);
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
/*      */   public void setCharacterSpacing(float spacing) throws IOException {
/* 1545 */     writeOperand(spacing);
/* 1546 */     writeOperator("Tc");
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
/* 1564 */     writeOperand(spacing);
/* 1565 */     writeOperator("Tw");
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
/* 1577 */     writeOperand(scale);
/* 1578 */     writeOperator("Tz");
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
/* 1590 */     writeOperand(rm.intValue());
/* 1591 */     writeOperator("Tr");
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
/* 1604 */     writeOperand(rise);
/* 1605 */     writeOperator("Ts");
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDAbstractContentStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */