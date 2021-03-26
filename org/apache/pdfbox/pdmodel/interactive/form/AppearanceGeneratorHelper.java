/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.contentstream.PDContentStream;
/*     */ import org.apache.pdfbox.contentstream.operator.Operator;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdfparser.PDFStreamParser;
/*     */ import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
/*     */ import org.apache.pdfbox.pdmodel.PDPageContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
/*     */ import org.apache.pdfbox.util.Matrix;
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
/*     */ class AppearanceGeneratorHelper
/*     */ {
/*  55 */   private static final Log LOG = LogFactory.getLog(AppearanceGeneratorHelper.class);
/*     */   
/*  57 */   private static final Operator BMC = Operator.getOperator("BMC");
/*  58 */   private static final Operator EMC = Operator.getOperator("EMC");
/*     */ 
/*     */ 
/*     */   
/*     */   private final PDVariableText field;
/*     */ 
/*     */ 
/*     */   
/*     */   private PDDefaultAppearanceString defaultAppearance;
/*     */ 
/*     */   
/*     */   private String value;
/*     */ 
/*     */   
/*  72 */   private static final int[] HIGHLIGHT_COLOR = new int[] { 153, 193, 215 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FONTSCALE = 1000;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final float DEFAULT_FONT_SIZE = 12.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final float DEFAULT_PADDING = 0.5F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AppearanceGeneratorHelper(PDVariableText field) throws IOException {
/*  97 */     this.field = field;
/*  98 */     validateAndEnsureAcroFormResources();
/*     */     
/* 100 */     this.defaultAppearance = field.getDefaultAppearanceString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void validateAndEnsureAcroFormResources() {
/* 111 */     if (this.field.getAcroForm().getDefaultResources() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 116 */     PDResources acroFormResources = this.field.getAcroForm().getDefaultResources();
/*     */     
/* 118 */     for (PDAnnotationWidget widget : this.field.getWidgets()) {
/*     */       
/* 120 */       if (widget.getNormalAppearanceStream() != null && widget.getNormalAppearanceStream().getResources() != null) {
/*     */         
/* 122 */         PDResources widgetResources = widget.getNormalAppearanceStream().getResources();
/* 123 */         for (COSName fontResourceName : widgetResources.getFontNames()) {
/*     */ 
/*     */           
/*     */           try {
/* 127 */             if (acroFormResources.getFont(fontResourceName) == null)
/*     */             {
/* 129 */               LOG.debug("Adding font resource " + fontResourceName + " from widget to AcroForm");
/* 130 */               acroFormResources.put(fontResourceName, widgetResources.getFont(fontResourceName));
/*     */             }
/*     */           
/* 133 */           } catch (IOException e) {
/*     */             
/* 135 */             LOG.warn("Unable to match field level font with AcroForm font");
/*     */           } 
/*     */         } 
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
/*     */   public void setAppearanceValue(String apValue) throws IOException {
/* 150 */     this.value = apValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (this.field instanceof PDTextField && !((PDTextField)this.field).isMultiline())
/*     */     {
/* 160 */       this.value = apValue.replaceAll("\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]", " ");
/*     */     }
/*     */     
/* 163 */     for (PDAnnotationWidget widget : this.field.getWidgets()) {
/*     */ 
/*     */ 
/*     */       
/* 167 */       PDDefaultAppearanceString acroFormAppearance = this.defaultAppearance;
/*     */       
/* 169 */       if (widget.getCOSObject().getDictionaryObject(COSName.DA) != null)
/*     */       {
/* 171 */         this.defaultAppearance = getWidgetDefaultAppearanceString(widget);
/*     */       }
/*     */       
/* 174 */       PDRectangle rect = widget.getRectangle();
/* 175 */       if (rect == null) {
/*     */         
/* 177 */         widget.getCOSObject().removeItem(COSName.AP);
/* 178 */         LOG.warn("widget of field " + this.field.getFullyQualifiedName() + " has no rectangle, no appearance stream created");
/*     */         
/*     */         continue;
/*     */       } 
/* 182 */       PDFormFieldAdditionalActions actions = this.field.getActions();
/*     */ 
/*     */ 
/*     */       
/* 186 */       if (actions == null || actions.getF() == null || widget
/* 187 */         .getCOSObject().getDictionaryObject(COSName.AP) != null) {
/*     */         PDAppearanceStream appearanceStream;
/* 189 */         PDAppearanceDictionary appearanceDict = widget.getAppearance();
/* 190 */         if (appearanceDict == null) {
/*     */           
/* 192 */           appearanceDict = new PDAppearanceDictionary();
/* 193 */           widget.setAppearance(appearanceDict);
/*     */         } 
/*     */         
/* 196 */         PDAppearanceEntry appearance = appearanceDict.getNormalAppearance();
/*     */ 
/*     */ 
/*     */         
/* 200 */         if (appearance != null && appearance.isStream()) {
/*     */           
/* 202 */           appearanceStream = appearance.getAppearanceStream();
/*     */         }
/*     */         else {
/*     */           
/* 206 */           appearanceStream = prepareNormalAppearanceStream(widget);
/*     */           
/* 208 */           appearanceDict.setNormalAppearance(appearanceStream);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 218 */         if (widget.getAppearanceCharacteristics() != null || appearanceStream.getContentStream().getLength() == 0)
/*     */         {
/* 220 */           initializeAppearanceContent(widget, appearanceStream);
/*     */         }
/*     */         
/* 223 */         setAppearanceContent(widget, appearanceStream);
/*     */       } 
/*     */ 
/*     */       
/* 227 */       this.defaultAppearance = acroFormAppearance;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private PDAppearanceStream prepareNormalAppearanceStream(PDAnnotationWidget widget) {
/* 233 */     PDAppearanceStream appearanceStream = new PDAppearanceStream(this.field.getAcroForm().getDocument());
/*     */ 
/*     */ 
/*     */     
/* 237 */     int rotation = resolveRotation(widget);
/* 238 */     PDRectangle rect = widget.getRectangle();
/* 239 */     Matrix matrix = Matrix.getRotateInstance(Math.toRadians(rotation), 0.0F, 0.0F);
/* 240 */     Point2D.Float point2D = matrix.transformPoint(rect.getWidth(), rect.getHeight());
/*     */     
/* 242 */     PDRectangle bbox = new PDRectangle(Math.abs((float)point2D.getX()), Math.abs((float)point2D.getY()));
/* 243 */     appearanceStream.setBBox(bbox);
/*     */     
/* 245 */     AffineTransform at = calculateMatrix(bbox, rotation);
/* 246 */     if (!at.isIdentity())
/*     */     {
/* 248 */       appearanceStream.setMatrix(at);
/*     */     }
/* 250 */     appearanceStream.setFormType(1);
/* 251 */     appearanceStream.setResources(new PDResources());
/* 252 */     return appearanceStream;
/*     */   }
/*     */ 
/*     */   
/*     */   private PDDefaultAppearanceString getWidgetDefaultAppearanceString(PDAnnotationWidget widget) throws IOException {
/* 257 */     COSString da = (COSString)widget.getCOSObject().getDictionaryObject(COSName.DA);
/* 258 */     PDResources dr = this.field.getAcroForm().getDefaultResources();
/* 259 */     return new PDDefaultAppearanceString(da, dr);
/*     */   }
/*     */ 
/*     */   
/*     */   private int resolveRotation(PDAnnotationWidget widget) {
/* 264 */     PDAppearanceCharacteristicsDictionary characteristicsDictionary = widget.getAppearanceCharacteristics();
/* 265 */     if (characteristicsDictionary != null)
/*     */     {
/*     */       
/* 268 */       return characteristicsDictionary.getRotation();
/*     */     }
/* 270 */     return 0;
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
/*     */   private void initializeAppearanceContent(PDAnnotationWidget widget, PDAppearanceStream appearanceStream) throws IOException {
/* 286 */     ByteArrayOutputStream output = new ByteArrayOutputStream();
/* 287 */     PDPageContentStream contents = new PDPageContentStream(this.field.getAcroForm().getDocument(), appearanceStream, output);
/*     */     
/* 289 */     PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();
/*     */ 
/*     */     
/* 292 */     if (appearanceCharacteristics != null) {
/*     */       
/* 294 */       PDColor backgroundColour = appearanceCharacteristics.getBackground();
/* 295 */       if (backgroundColour != null) {
/*     */         
/* 297 */         contents.setNonStrokingColor(backgroundColour);
/* 298 */         PDRectangle bbox = resolveBoundingBox(widget, appearanceStream);
/* 299 */         contents.addRect(bbox.getLowerLeftX(), bbox.getLowerLeftY(), bbox.getWidth(), bbox.getHeight());
/* 300 */         contents.fill();
/*     */       } 
/*     */       
/* 303 */       float lineWidth = 0.0F;
/* 304 */       PDColor borderColour = appearanceCharacteristics.getBorderColour();
/* 305 */       if (borderColour != null) {
/*     */         
/* 307 */         contents.setStrokingColor(borderColour);
/* 308 */         lineWidth = 1.0F;
/*     */       } 
/* 310 */       PDBorderStyleDictionary borderStyle = widget.getBorderStyle();
/* 311 */       if (borderStyle != null && borderStyle.getWidth() > 0.0F)
/*     */       {
/* 313 */         lineWidth = borderStyle.getWidth();
/*     */       }
/*     */       
/* 316 */       if (lineWidth > 0.0F && borderColour != null) {
/*     */         
/* 318 */         if (lineWidth != 1.0F)
/*     */         {
/* 320 */           contents.setLineWidth(lineWidth);
/*     */         }
/* 322 */         PDRectangle bbox = resolveBoundingBox(widget, appearanceStream);
/* 323 */         PDRectangle clipRect = applyPadding(bbox, Math.max(0.5F, lineWidth / 2.0F));
/* 324 */         contents.addRect(clipRect.getLowerLeftX(), clipRect.getLowerLeftY(), clipRect.getWidth(), clipRect.getHeight());
/* 325 */         contents.closeAndStroke();
/*     */       } 
/*     */     } 
/*     */     
/* 329 */     contents.close();
/* 330 */     output.close();
/* 331 */     writeToStream(output.toByteArray(), appearanceStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Object> tokenize(PDAppearanceStream appearanceStream) throws IOException {
/* 339 */     PDFStreamParser parser = new PDFStreamParser((PDContentStream)appearanceStream);
/* 340 */     parser.parse();
/* 341 */     return parser.getTokens();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setAppearanceContent(PDAnnotationWidget widget, PDAppearanceStream appearanceStream) throws IOException {
/* 352 */     this.defaultAppearance.copyNeededResourcesTo(appearanceStream);
/*     */ 
/*     */ 
/*     */     
/* 356 */     ByteArrayOutputStream output = new ByteArrayOutputStream();
/* 357 */     ContentStreamWriter writer = new ContentStreamWriter(output);
/*     */     
/* 359 */     List<Object> tokens = tokenize(appearanceStream);
/* 360 */     int bmcIndex = tokens.indexOf(BMC);
/* 361 */     if (bmcIndex == -1) {
/*     */ 
/*     */       
/* 364 */       writer.writeTokens(tokens);
/* 365 */       writer.writeTokens(new Object[] { COSName.TX, BMC });
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 370 */       writer.writeTokens(tokens.subList(0, bmcIndex + 1));
/*     */     } 
/*     */ 
/*     */     
/* 374 */     insertGeneratedAppearance(widget, appearanceStream, output);
/*     */     
/* 376 */     int emcIndex = tokens.indexOf(EMC);
/* 377 */     if (emcIndex == -1) {
/*     */ 
/*     */       
/* 380 */       writer.writeTokens(new Object[] { EMC });
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 385 */       writer.writeTokens(tokens.subList(emcIndex, tokens.size()));
/*     */     } 
/*     */     
/* 388 */     output.close();
/* 389 */     writeToStream(output.toByteArray(), appearanceStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertGeneratedAppearance(PDAnnotationWidget widget, PDAppearanceStream appearanceStream, OutputStream output) throws IOException {
/*     */     float y;
/* 399 */     PDPageContentStream contents = new PDPageContentStream(this.field.getAcroForm().getDocument(), appearanceStream, output);
/*     */ 
/*     */     
/* 402 */     PDRectangle bbox = resolveBoundingBox(widget, appearanceStream);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 407 */     float borderWidth = 0.0F;
/* 408 */     if (widget.getBorderStyle() != null)
/*     */     {
/* 410 */       borderWidth = widget.getBorderStyle().getWidth();
/*     */     }
/* 412 */     PDRectangle clipRect = applyPadding(bbox, Math.max(1.0F, borderWidth));
/* 413 */     PDRectangle contentRect = applyPadding(clipRect, Math.max(1.0F, borderWidth));
/*     */     
/* 415 */     contents.saveGraphicsState();
/*     */ 
/*     */     
/* 418 */     contents.addRect(clipRect.getLowerLeftX(), clipRect.getLowerLeftY(), clipRect
/* 419 */         .getWidth(), clipRect.getHeight());
/* 420 */     contents.clip();
/*     */ 
/*     */     
/* 423 */     PDFont font = this.defaultAppearance.getFont();
/* 424 */     if (font == null)
/*     */     {
/* 426 */       throw new IllegalArgumentException("font is null, check whether /DA entry is incomplete or incorrect");
/*     */     }
/* 428 */     if (font.getName().contains("+")) {
/*     */       
/* 430 */       LOG.warn("Font '" + this.defaultAppearance.getFontName().getName() + "' of field '" + this.field
/* 431 */           .getFullyQualifiedName() + "' contains subsetted font '" + font
/* 432 */           .getName() + "'");
/* 433 */       LOG.warn("This may bring trouble with PDField.setValue(), PDAcroForm.flatten() or PDAcroForm.refreshAppearances()");
/*     */       
/* 435 */       LOG.warn("You should replace this font with a non-subsetted font:");
/* 436 */       LOG.warn("PDFont font = PDType0Font.load(doc, new FileInputStream(fontfile), false);");
/* 437 */       LOG.warn("acroForm.getDefaultResources().put(COSName.getPDFName(\"" + this.defaultAppearance
/* 438 */           .getFontName().getName() + "\", font);");
/*     */     } 
/*     */ 
/*     */     
/* 442 */     float fontSize = this.defaultAppearance.getFontSize();
/*     */     
/* 444 */     if (fontSize == 0.0F)
/*     */     {
/* 446 */       fontSize = calculateFontSize(font, contentRect);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 451 */     if (this.field instanceof PDListBox)
/*     */     {
/* 453 */       insertGeneratedListboxSelectionHighlight(contents, appearanceStream, font, fontSize);
/*     */     }
/*     */ 
/*     */     
/* 457 */     contents.beginText();
/*     */ 
/*     */     
/* 460 */     this.defaultAppearance.writeTo(contents, fontSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 466 */     float fontScaleY = fontSize / 1000.0F;
/* 467 */     float fontBoundingBoxAtSize = font.getBoundingBox().getHeight() * fontScaleY;
/* 468 */     float fontCapAtSize = font.getFontDescriptor().getCapHeight() * fontScaleY;
/* 469 */     float fontDescentAtSize = font.getFontDescriptor().getDescent() * fontScaleY;
/*     */     
/* 471 */     if (this.field instanceof PDTextField && ((PDTextField)this.field).isMultiline()) {
/*     */       
/* 473 */       y = contentRect.getUpperRightY() - fontBoundingBoxAtSize;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 478 */     else if (fontCapAtSize > clipRect.getHeight()) {
/*     */       
/* 480 */       y = clipRect.getLowerLeftY() + -fontDescentAtSize;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 485 */       y = clipRect.getLowerLeftY() + (clipRect.getHeight() - fontCapAtSize) / 2.0F;
/*     */ 
/*     */       
/* 488 */       if (y - clipRect.getLowerLeftY() < -fontDescentAtSize) {
/*     */         
/* 490 */         float fontDescentBased = -fontDescentAtSize + contentRect.getLowerLeftY();
/* 491 */         float fontCapBased = contentRect.getHeight() - contentRect.getLowerLeftY() - fontCapAtSize;
/*     */         
/* 493 */         y = Math.min(fontDescentBased, Math.max(y, fontCapBased));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 499 */     float x = contentRect.getLowerLeftX();
/*     */ 
/*     */ 
/*     */     
/* 503 */     if (shallComb()) {
/*     */       
/* 505 */       insertGeneratedCombAppearance(contents, appearanceStream, font, fontSize);
/*     */     }
/* 507 */     else if (this.field instanceof PDListBox) {
/*     */       
/* 509 */       insertGeneratedListboxAppearance(contents, appearanceStream, contentRect, font, fontSize);
/*     */     }
/*     */     else {
/*     */       
/* 513 */       PlainText textContent = new PlainText(this.value);
/* 514 */       AppearanceStyle appearanceStyle = new AppearanceStyle();
/* 515 */       appearanceStyle.setFont(font);
/* 516 */       appearanceStyle.setFontSize(fontSize);
/*     */ 
/*     */       
/* 519 */       appearanceStyle.setLeading(font.getBoundingBox().getHeight() * fontScaleY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 529 */       PlainTextFormatter formatter = (new PlainTextFormatter.Builder(contents)).style(appearanceStyle).text(textContent).width(contentRect.getWidth()).wrapLines(isMultiLine()).initialOffset(x, y).textAlign(this.field.getQ()).build();
/* 530 */       formatter.format();
/*     */     } 
/*     */     
/* 533 */     contents.endText();
/* 534 */     contents.restoreGraphicsState();
/* 535 */     contents.close();
/*     */   }
/*     */ 
/*     */   
/*     */   private AffineTransform calculateMatrix(PDRectangle bbox, int rotation) {
/* 540 */     if (rotation == 0)
/*     */     {
/* 542 */       return new AffineTransform();
/*     */     }
/* 544 */     float tx = 0.0F, ty = 0.0F;
/* 545 */     switch (rotation) {
/*     */       
/*     */       case 90:
/* 548 */         tx = bbox.getUpperRightY();
/*     */         break;
/*     */       case 180:
/* 551 */         tx = bbox.getUpperRightY();
/* 552 */         ty = bbox.getUpperRightX();
/*     */         break;
/*     */       case 270:
/* 555 */         ty = bbox.getUpperRightX();
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 560 */     Matrix matrix = Matrix.getRotateInstance(Math.toRadians(rotation), tx, ty);
/* 561 */     return matrix.createAffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMultiLine() {
/* 568 */     return (this.field instanceof PDTextField && ((PDTextField)this.field).isMultiline());
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
/*     */   private boolean shallComb() {
/* 585 */     return (this.field instanceof PDTextField && ((PDTextField)this.field)
/* 586 */       .isComb() && 
/* 587 */       !((PDTextField)this.field).isMultiline() && 
/* 588 */       !((PDTextField)this.field).isPassword() && 
/* 589 */       !((PDTextField)this.field).isFileSelect());
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
/*     */   
/*     */   private void insertGeneratedCombAppearance(PDPageContentStream contents, PDAppearanceStream appearanceStream, PDFont font, float fontSize) throws IOException {
/* 608 */     int maxLen = ((PDTextField)this.field).getMaxLen();
/* 609 */     int numChars = Math.min(this.value.length(), maxLen);
/*     */     
/* 611 */     PDRectangle paddingEdge = applyPadding(appearanceStream.getBBox(), 1.0F);
/*     */     
/* 613 */     float combWidth = appearanceStream.getBBox().getWidth() / maxLen;
/* 614 */     float ascentAtFontSize = font.getFontDescriptor().getAscent() / 1000.0F * fontSize;
/*     */     
/* 616 */     float baselineOffset = paddingEdge.getLowerLeftY() + (appearanceStream.getBBox().getHeight() - ascentAtFontSize) / 2.0F;
/*     */     
/* 618 */     float prevCharWidth = 0.0F;
/*     */     
/* 620 */     float xOffset = combWidth / 2.0F;
/*     */     
/* 622 */     for (int i = 0; i < numChars; i++) {
/*     */       
/* 624 */       String combString = this.value.substring(i, i + 1);
/* 625 */       float currCharWidth = font.getStringWidth(combString) / 1000.0F * fontSize / 2.0F;
/*     */       
/* 627 */       xOffset = xOffset + prevCharWidth / 2.0F - currCharWidth / 2.0F;
/*     */       
/* 629 */       contents.newLineAtOffset(xOffset, baselineOffset);
/* 630 */       contents.showText(combString);
/*     */       
/* 632 */       baselineOffset = 0.0F;
/* 633 */       prevCharWidth = currCharWidth;
/* 634 */       xOffset = combWidth;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertGeneratedListboxSelectionHighlight(PDPageContentStream contents, PDAppearanceStream appearanceStream, PDFont font, float fontSize) throws IOException {
/* 641 */     List<Integer> indexEntries = ((PDListBox)this.field).getSelectedOptionsIndex();
/* 642 */     List<String> values = ((PDListBox)this.field).getValue();
/* 643 */     List<String> options = ((PDListBox)this.field).getOptionsExportValues();
/*     */     
/* 645 */     if (!values.isEmpty() && !options.isEmpty() && indexEntries.isEmpty()) {
/*     */ 
/*     */       
/* 648 */       indexEntries = new ArrayList<Integer>();
/* 649 */       for (String v : values)
/*     */       {
/* 651 */         indexEntries.add(Integer.valueOf(options.indexOf(v)));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 658 */     int topIndex = ((PDListBox)this.field).getTopIndex();
/*     */     
/* 660 */     float highlightBoxHeight = font.getBoundingBox().getHeight() * fontSize / 1000.0F;
/*     */ 
/*     */     
/* 663 */     PDRectangle paddingEdge = applyPadding(appearanceStream.getBBox(), 1.0F);
/*     */     
/* 665 */     for (Iterator<Integer> iterator = indexEntries.iterator(); iterator.hasNext(); ) { int selectedIndex = ((Integer)iterator.next()).intValue();
/*     */       
/* 667 */       contents.setNonStrokingColor(HIGHLIGHT_COLOR[0], HIGHLIGHT_COLOR[1], HIGHLIGHT_COLOR[2]);
/*     */       
/* 669 */       contents.addRect(paddingEdge.getLowerLeftX(), paddingEdge
/* 670 */           .getUpperRightY() - highlightBoxHeight * (selectedIndex - topIndex + 1) + 2.0F, paddingEdge
/* 671 */           .getWidth(), highlightBoxHeight);
/*     */       
/* 673 */       contents.fill(); }
/*     */     
/* 675 */     contents.setNonStrokingColor(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void insertGeneratedListboxAppearance(PDPageContentStream contents, PDAppearanceStream appearanceStream, PDRectangle contentRect, PDFont font, float fontSize) throws IOException {
/* 682 */     contents.setNonStrokingColor(0);
/*     */     
/* 684 */     int q = this.field.getQ();
/*     */     
/* 686 */     if (q == 1 || q == 2) {
/*     */       
/* 688 */       float fieldWidth = appearanceStream.getBBox().getWidth();
/* 689 */       float stringWidth = font.getStringWidth(this.value) / 1000.0F * fontSize;
/* 690 */       float adjustAmount = fieldWidth - stringWidth - 4.0F;
/*     */       
/* 692 */       if (q == 1)
/*     */       {
/* 694 */         adjustAmount /= 2.0F;
/*     */       }
/*     */       
/* 697 */       contents.newLineAtOffset(adjustAmount, 0.0F);
/*     */     }
/* 699 */     else if (q != 0) {
/*     */       
/* 701 */       throw new IOException("Error: Unknown justification value:" + q);
/*     */     } 
/*     */     
/* 704 */     List<String> options = ((PDListBox)this.field).getOptionsDisplayValues();
/* 705 */     int numOptions = options.size();
/*     */     
/* 707 */     float yTextPos = contentRect.getUpperRightY();
/*     */     
/* 709 */     int topIndex = ((PDListBox)this.field).getTopIndex();
/*     */     
/* 711 */     for (int i = topIndex; i < numOptions; i++) {
/*     */ 
/*     */       
/* 714 */       if (i == topIndex) {
/*     */         
/* 716 */         yTextPos -= font.getFontDescriptor().getAscent() / 1000.0F * fontSize;
/*     */       }
/*     */       else {
/*     */         
/* 720 */         yTextPos -= font.getBoundingBox().getHeight() / 1000.0F * fontSize;
/* 721 */         contents.beginText();
/*     */       } 
/*     */       
/* 724 */       contents.newLineAtOffset(contentRect.getLowerLeftX(), yTextPos);
/* 725 */       contents.showText(options.get(i));
/*     */       
/* 727 */       if (i != numOptions - 1)
/*     */       {
/* 729 */         contents.endText();
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
/*     */   private void writeToStream(byte[] data, PDAppearanceStream appearanceStream) throws IOException {
/* 741 */     OutputStream out = appearanceStream.getCOSObject().createOutputStream();
/* 742 */     out.write(data);
/* 743 */     out.close();
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
/*     */   private float calculateFontSize(PDFont font, PDRectangle contentRect) throws IOException {
/* 755 */     float fontSize = this.defaultAppearance.getFontSize();
/*     */ 
/*     */     
/* 758 */     if (fontSize == 0.0F) {
/*     */       
/* 760 */       if (isMultiLine())
/*     */       {
/*     */         
/* 763 */         return 12.0F;
/*     */       }
/*     */ 
/*     */       
/* 767 */       float yScalingFactor = 1000.0F * font.getFontMatrix().getScaleY();
/* 768 */       float xScalingFactor = 1000.0F * font.getFontMatrix().getScaleX();
/*     */ 
/*     */       
/* 771 */       float width = font.getStringWidth(this.value) * font.getFontMatrix().getScaleX();
/* 772 */       float widthBasedFontSize = contentRect.getWidth() / width * xScalingFactor;
/*     */ 
/*     */ 
/*     */       
/* 776 */       float height = (font.getFontDescriptor().getCapHeight() + -font.getFontDescriptor().getDescent()) * font.getFontMatrix().getScaleY();
/* 777 */       if (height <= 0.0F)
/*     */       {
/* 779 */         height = font.getBoundingBox().getHeight() * font.getFontMatrix().getScaleY();
/*     */       }
/*     */       
/* 782 */       float heightBasedFontSize = contentRect.getHeight() / height * yScalingFactor;
/*     */       
/* 784 */       return Math.min(heightBasedFontSize, widthBasedFontSize);
/*     */     } 
/*     */     
/* 787 */     return fontSize;
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
/*     */   private PDRectangle resolveBoundingBox(PDAnnotationWidget fieldWidget, PDAppearanceStream appearanceStream) {
/* 800 */     PDRectangle boundingBox = appearanceStream.getBBox();
/* 801 */     if (boundingBox == null)
/*     */     {
/* 803 */       boundingBox = fieldWidget.getRectangle().createRetranslatedRectangle();
/*     */     }
/* 805 */     return boundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDRectangle applyPadding(PDRectangle box, float padding) {
/* 816 */     return new PDRectangle(box.getLowerLeftX() + padding, box
/* 817 */         .getLowerLeftY() + padding, box
/* 818 */         .getWidth() - 2.0F * padding, box
/* 819 */         .getHeight() - 2.0F * padding);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/AppearanceGeneratorHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */