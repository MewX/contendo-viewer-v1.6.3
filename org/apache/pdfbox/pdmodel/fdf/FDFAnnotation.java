/*      */ package org.apache.pdfbox.pdmodel.fdf;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.io.IOException;
/*      */ import java.util.Calendar;
/*      */ import javax.xml.xpath.XPath;
/*      */ import javax.xml.xpath.XPathConstants;
/*      */ import javax.xml.xpath.XPathExpressionException;
/*      */ import javax.xml.xpath.XPathFactory;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.cos.COSStream;
/*      */ import org.apache.pdfbox.cos.COSString;
/*      */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
/*      */ import org.apache.pdfbox.util.DateConverter;
/*      */ import org.w3c.dom.CDATASection;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.Text;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class FDFAnnotation
/*      */   implements COSObjectable
/*      */ {
/*   56 */   private static final Log LOG = LogFactory.getLog(FDFAnnotation.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_INVISIBLE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_HIDDEN = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_PRINTED = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_NO_ZOOM = 8;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_NO_ROTATE = 16;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_NO_VIEW = 32;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_READ_ONLY = 64;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_LOCKED = 128;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int FLAG_TOGGLE_NO_VIEW = 256;
/*      */ 
/*      */ 
/*      */   
/*      */   protected COSDictionary annot;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FDFAnnotation() {
/*  105 */     this.annot = new COSDictionary();
/*  106 */     this.annot.setItem(COSName.TYPE, (COSBase)COSName.ANNOT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FDFAnnotation(COSDictionary a) {
/*  116 */     this.annot = a;
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
/*      */   public FDFAnnotation(Element element) throws IOException {
/*  128 */     this();
/*      */     
/*  130 */     String page = element.getAttribute("page");
/*  131 */     if (page == null || page.isEmpty())
/*      */     {
/*  133 */       throw new IOException("Error: missing required attribute 'page'");
/*      */     }
/*  135 */     setPage(Integer.parseInt(page));
/*      */     
/*  137 */     String color = element.getAttribute("color");
/*  138 */     if (color != null && color.length() == 7 && color.charAt(0) == '#') {
/*      */       
/*  140 */       int colorValue = Integer.parseInt(color.substring(1, 7), 16);
/*  141 */       setColor(new Color(colorValue));
/*      */     } 
/*      */     
/*  144 */     setDate(element.getAttribute("date"));
/*      */     
/*  146 */     String flags = element.getAttribute("flags");
/*  147 */     if (flags != null) {
/*      */       
/*  149 */       String[] flagTokens = flags.split(",");
/*  150 */       for (String flagToken : flagTokens) {
/*      */         
/*  152 */         if (flagToken.equals("invisible")) {
/*      */           
/*  154 */           setInvisible(true);
/*      */         }
/*  156 */         else if (flagToken.equals("hidden")) {
/*      */           
/*  158 */           setHidden(true);
/*      */         }
/*  160 */         else if (flagToken.equals("print")) {
/*      */           
/*  162 */           setPrinted(true);
/*      */         }
/*  164 */         else if (flagToken.equals("nozoom")) {
/*      */           
/*  166 */           setNoZoom(true);
/*      */         }
/*  168 */         else if (flagToken.equals("norotate")) {
/*      */           
/*  170 */           setNoRotate(true);
/*      */         }
/*  172 */         else if (flagToken.equals("noview")) {
/*      */           
/*  174 */           setNoView(true);
/*      */         }
/*  176 */         else if (flagToken.equals("readonly")) {
/*      */           
/*  178 */           setReadOnly(true);
/*      */         }
/*  180 */         else if (flagToken.equals("locked")) {
/*      */           
/*  182 */           setLocked(true);
/*      */         }
/*  184 */         else if (flagToken.equals("togglenoview")) {
/*      */           
/*  186 */           setToggleNoView(true);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  191 */     setName(element.getAttribute("name"));
/*      */     
/*  193 */     String rect = element.getAttribute("rect");
/*  194 */     if (rect == null)
/*      */     {
/*  196 */       throw new IOException("Error: missing attribute 'rect'");
/*      */     }
/*  198 */     String[] rectValues = rect.split(",");
/*  199 */     if (rectValues.length != 4)
/*      */     {
/*  201 */       throw new IOException("Error: wrong amount of numbers in attribute 'rect'");
/*      */     }
/*  203 */     float[] values = new float[4];
/*  204 */     for (int i = 0; i < 4; i++)
/*      */     {
/*  206 */       values[i] = Float.parseFloat(rectValues[i]);
/*      */     }
/*  208 */     COSArray array = new COSArray();
/*  209 */     array.setFloatArray(values);
/*  210 */     setRectangle(new PDRectangle(array));
/*      */     
/*  212 */     setTitle(element.getAttribute("title"));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  217 */     setCreationDate(DateConverter.toCalendar(element.getAttribute("creationdate")));
/*  218 */     String opac = element.getAttribute("opacity");
/*  219 */     if (opac != null && !opac.isEmpty())
/*      */     {
/*  221 */       setOpacity(Float.parseFloat(opac));
/*      */     }
/*  223 */     setSubject(element.getAttribute("subject"));
/*      */     
/*  225 */     String intent = element.getAttribute("intent");
/*  226 */     if (intent.isEmpty())
/*      */     {
/*      */       
/*  229 */       intent = element.getAttribute("IT");
/*      */     }
/*  231 */     setIntent(intent);
/*      */     
/*  233 */     XPath xpath = XPathFactory.newInstance().newXPath();
/*      */     
/*      */     try {
/*  236 */       setContents(xpath.evaluate("contents[1]", element));
/*      */     }
/*  238 */     catch (XPathExpressionException e) {
/*      */       
/*  240 */       LOG.debug("Error while evaluating XPath expression for richtext contents");
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  245 */       Node richContents = (Node)xpath.evaluate("contents-richtext[1]", element, XPathConstants.NODE);
/*      */       
/*  247 */       if (richContents != null)
/*      */       {
/*  249 */         setRichContents(richContentsToString(richContents, true));
/*  250 */         setContents(richContents.getTextContent().trim());
/*      */       }
/*      */     
/*  253 */     } catch (XPathExpressionException e) {
/*      */       
/*  255 */       LOG.debug("Error while evaluating XPath expression for richtext contents");
/*      */     } 
/*      */     
/*  258 */     PDBorderStyleDictionary borderStyle = new PDBorderStyleDictionary();
/*  259 */     String width = element.getAttribute("width");
/*  260 */     if (width != null && !width.isEmpty())
/*      */     {
/*  262 */       borderStyle.setWidth(Float.parseFloat(width));
/*      */     }
/*  264 */     if (borderStyle.getWidth() > 0.0F) {
/*      */       
/*  266 */       String style = element.getAttribute("style");
/*  267 */       if (style != null && !style.isEmpty())
/*      */       {
/*  269 */         if (style.equals("dash")) {
/*      */           
/*  271 */           borderStyle.setStyle("D");
/*      */         }
/*  273 */         else if (style.equals("bevelled")) {
/*      */           
/*  275 */           borderStyle.setStyle("B");
/*      */         }
/*  277 */         else if (style.equals("inset")) {
/*      */           
/*  279 */           borderStyle.setStyle("I");
/*      */         }
/*  281 */         else if (style.equals("underline")) {
/*      */           
/*  283 */           borderStyle.setStyle("U");
/*      */         }
/*  285 */         else if (style.equals("cloudy")) {
/*      */           
/*  287 */           borderStyle.setStyle("S");
/*  288 */           PDBorderEffectDictionary borderEffect = new PDBorderEffectDictionary();
/*  289 */           borderEffect.setStyle("C");
/*  290 */           String intensity = element.getAttribute("intensity");
/*  291 */           if (intensity != null && !intensity.isEmpty())
/*      */           {
/*  293 */             borderEffect.setIntensity(Float.parseFloat(element
/*  294 */                   .getAttribute("intensity")));
/*      */           }
/*  296 */           setBorderEffect(borderEffect);
/*      */         }
/*      */         else {
/*      */           
/*  300 */           borderStyle.setStyle("S");
/*      */         } 
/*      */       }
/*  303 */       String dashes = element.getAttribute("dashes");
/*  304 */       if (dashes != null && !dashes.isEmpty()) {
/*      */         
/*  306 */         String[] dashesValues = dashes.split(",");
/*  307 */         COSArray dashPattern = new COSArray();
/*  308 */         for (String dashesValue : dashesValues)
/*      */         {
/*  310 */           dashPattern.add((COSBase)COSNumber.get(dashesValue));
/*      */         }
/*  312 */         borderStyle.setDashStyle(dashPattern);
/*      */       } 
/*  314 */       setBorderStyle(borderStyle);
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
/*      */   public static FDFAnnotation create(COSDictionary fdfDic) throws IOException {
/*  329 */     FDFAnnotation retval = null;
/*  330 */     if (fdfDic != null)
/*      */     {
/*  332 */       if ("Text".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  334 */         retval = new FDFAnnotationText(fdfDic);
/*      */       }
/*  336 */       else if ("Caret".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  338 */         retval = new FDFAnnotationCaret(fdfDic);
/*      */       }
/*  340 */       else if ("FreeText".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  342 */         retval = new FDFAnnotationFreeText(fdfDic);
/*      */       }
/*  344 */       else if ("FileAttachment".equals(fdfDic
/*  345 */           .getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  347 */         retval = new FDFAnnotationFileAttachment(fdfDic);
/*      */       }
/*  349 */       else if ("Highlight".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  351 */         retval = new FDFAnnotationHighlight(fdfDic);
/*      */       }
/*  353 */       else if ("Ink".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  355 */         retval = new FDFAnnotationInk(fdfDic);
/*      */       }
/*  357 */       else if ("Line".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  359 */         retval = new FDFAnnotationLine(fdfDic);
/*      */       }
/*  361 */       else if ("Link".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  363 */         retval = new FDFAnnotationLink(fdfDic);
/*      */       }
/*  365 */       else if ("Circle".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  367 */         retval = new FDFAnnotationCircle(fdfDic);
/*      */       }
/*  369 */       else if ("Square".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  371 */         retval = new FDFAnnotationSquare(fdfDic);
/*      */       }
/*  373 */       else if ("Polygon".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  375 */         retval = new FDFAnnotationPolygon(fdfDic);
/*      */       }
/*  377 */       else if ("Polyline".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  379 */         retval = new FDFAnnotationPolyline(fdfDic);
/*      */       }
/*  381 */       else if ("Sound".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  383 */         retval = new FDFAnnotationSound(fdfDic);
/*      */       }
/*  385 */       else if ("Squiggly".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  387 */         retval = new FDFAnnotationSquiggly(fdfDic);
/*      */       }
/*  389 */       else if ("Stamp".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  391 */         retval = new FDFAnnotationStamp(fdfDic);
/*      */       }
/*  393 */       else if ("StrikeOut".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  395 */         retval = new FDFAnnotationStrikeOut(fdfDic);
/*      */       }
/*  397 */       else if ("Underline".equals(fdfDic.getNameAsString(COSName.SUBTYPE))) {
/*      */         
/*  399 */         retval = new FDFAnnotationUnderline(fdfDic);
/*      */       }
/*      */       else {
/*      */         
/*  403 */         LOG.warn("Unknown or unsupported annotation type '" + fdfDic
/*  404 */             .getNameAsString(COSName.SUBTYPE) + "'");
/*      */       } 
/*      */     }
/*  407 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSDictionary getCOSObject() {
/*  418 */     return this.annot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Integer getPage() {
/*  428 */     Integer retval = null;
/*  429 */     COSNumber page = (COSNumber)this.annot.getDictionaryObject(COSName.PAGE);
/*  430 */     if (page != null)
/*      */     {
/*  432 */       retval = Integer.valueOf(page.intValue());
/*      */     }
/*  434 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setPage(int page) {
/*  444 */     this.annot.setInt(COSName.PAGE, page);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Color getColor() {
/*  454 */     Color retval = null;
/*  455 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.C);
/*  456 */     if (array != null) {
/*      */       
/*  458 */       float[] rgb = array.toFloatArray();
/*  459 */       if (rgb.length >= 3)
/*      */       {
/*  461 */         retval = new Color(rgb[0], rgb[1], rgb[2]);
/*      */       }
/*      */     } 
/*  464 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setColor(Color c) {
/*  474 */     COSArray color = null;
/*  475 */     if (c != null) {
/*      */       
/*  477 */       float[] colors = c.getRGBColorComponents(null);
/*  478 */       color = new COSArray();
/*  479 */       color.setFloatArray(colors);
/*      */     } 
/*  481 */     this.annot.setItem(COSName.C, (COSBase)color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDate() {
/*  491 */     return this.annot.getString(COSName.M);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setDate(String date) {
/*  501 */     this.annot.setString(COSName.M, date);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInvisible() {
/*  511 */     return this.annot.getFlag(COSName.F, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setInvisible(boolean invisible) {
/*  521 */     this.annot.setFlag(COSName.F, 1, invisible);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHidden() {
/*  531 */     return this.annot.getFlag(COSName.F, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setHidden(boolean hidden) {
/*  541 */     this.annot.setFlag(COSName.F, 2, hidden);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPrinted() {
/*  551 */     return this.annot.getFlag(COSName.F, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setPrinted(boolean printed) {
/*  561 */     this.annot.setFlag(COSName.F, 4, printed);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNoZoom() {
/*  571 */     return this.annot.getFlag(COSName.F, 8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setNoZoom(boolean noZoom) {
/*  581 */     this.annot.setFlag(COSName.F, 8, noZoom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNoRotate() {
/*  591 */     return this.annot.getFlag(COSName.F, 16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setNoRotate(boolean noRotate) {
/*  601 */     this.annot.setFlag(COSName.F, 16, noRotate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNoView() {
/*  611 */     return this.annot.getFlag(COSName.F, 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setNoView(boolean noView) {
/*  621 */     this.annot.setFlag(COSName.F, 32, noView);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReadOnly() {
/*  631 */     return this.annot.getFlag(COSName.F, 64);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setReadOnly(boolean readOnly) {
/*  641 */     this.annot.setFlag(COSName.F, 64, readOnly);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLocked() {
/*  651 */     return this.annot.getFlag(COSName.F, 128);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setLocked(boolean locked) {
/*  661 */     this.annot.setFlag(COSName.F, 128, locked);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isToggleNoView() {
/*  671 */     return this.annot.getFlag(COSName.F, 256);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setToggleNoView(boolean toggleNoView) {
/*  681 */     this.annot.setFlag(COSName.F, 256, toggleNoView);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setName(String name) {
/*  691 */     this.annot.setString(COSName.NM, name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/*  701 */     return this.annot.getString(COSName.NM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRectangle(PDRectangle rectangle) {
/*  711 */     this.annot.setItem(COSName.RECT, (COSObjectable)rectangle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDRectangle getRectangle() {
/*  721 */     PDRectangle retval = null;
/*  722 */     COSArray rectArray = (COSArray)this.annot.getDictionaryObject(COSName.RECT);
/*  723 */     if (rectArray != null)
/*      */     {
/*  725 */       retval = new PDRectangle(rectArray);
/*      */     }
/*  727 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setContents(String contents) {
/*  737 */     this.annot.setString(COSName.CONTENTS, contents);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getContents() {
/*  747 */     return this.annot.getString(COSName.CONTENTS);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setTitle(String title) {
/*  757 */     this.annot.setString(COSName.T, title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTitle() {
/*  767 */     return this.annot.getString(COSName.T);
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
/*      */   public Calendar getCreationDate() throws IOException {
/*  779 */     return this.annot.getDate(COSName.CREATION_DATE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setCreationDate(Calendar date) {
/*  789 */     this.annot.setDate(COSName.CREATION_DATE, date);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setOpacity(float opacity) {
/*  799 */     this.annot.setFloat(COSName.CA, opacity);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getOpacity() {
/*  809 */     return this.annot.getFloat(COSName.CA, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setSubject(String subject) {
/*  820 */     this.annot.setString(COSName.SUBJ, subject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSubject() {
/*  830 */     return this.annot.getString(COSName.SUBJ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setIntent(String intent) {
/*  840 */     this.annot.setName(COSName.IT, intent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getIntent() {
/*  850 */     return this.annot.getNameAsString(COSName.IT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRichContents() {
/*  860 */     return getStringOrStream(this.annot.getDictionaryObject(COSName.RC));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setRichContents(String rc) {
/*  870 */     this.annot.setItem(COSName.RC, (COSBase)new COSString(rc));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setBorderStyle(PDBorderStyleDictionary bs) {
/*  881 */     this.annot.setItem(COSName.BS, (COSObjectable)bs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDBorderStyleDictionary getBorderStyle() {
/*  892 */     COSDictionary bs = (COSDictionary)this.annot.getDictionaryObject(COSName.BS);
/*  893 */     if (bs != null)
/*      */     {
/*  895 */       return new PDBorderStyleDictionary(bs);
/*      */     }
/*      */ 
/*      */     
/*  899 */     return null;
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
/*      */   public final void setBorderEffect(PDBorderEffectDictionary be) {
/*  912 */     this.annot.setItem(COSName.BE, (COSObjectable)be);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDBorderEffectDictionary getBorderEffect() {
/*  923 */     COSDictionary be = (COSDictionary)this.annot.getDictionaryObject(COSName.BE);
/*  924 */     if (be != null)
/*      */     {
/*  926 */       return new PDBorderEffectDictionary(be);
/*      */     }
/*      */ 
/*      */     
/*  930 */     return null;
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
/*      */   protected final String getStringOrStream(COSBase base) {
/*  944 */     if (base == null)
/*      */     {
/*  946 */       return "";
/*      */     }
/*  948 */     if (base instanceof COSString)
/*      */     {
/*  950 */       return ((COSString)base).getString();
/*      */     }
/*  952 */     if (base instanceof COSStream)
/*      */     {
/*  954 */       return ((COSStream)base).toTextString();
/*      */     }
/*      */ 
/*      */     
/*  958 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String richContentsToString(Node node, boolean root) {
/*  964 */     String subString = "";
/*      */     
/*  966 */     NodeList nodelist = node.getChildNodes();
/*  967 */     for (int i = 0; i < nodelist.getLength(); i++) {
/*      */       
/*  969 */       Node child = nodelist.item(i);
/*  970 */       if (child instanceof Element) {
/*      */         
/*  972 */         subString = subString + richContentsToString(child, false);
/*      */       }
/*  974 */       else if (child instanceof CDATASection) {
/*      */         
/*  976 */         subString = subString + "<![CDATA[" + ((CDATASection)child).getData() + "]]>";
/*      */       }
/*  978 */       else if (child instanceof Text) {
/*      */         
/*  980 */         String cdata = ((Text)child).getData();
/*  981 */         if (cdata != null)
/*      */         {
/*  983 */           cdata = cdata.replace("&", "&amp;").replace("<", "&lt;");
/*      */         }
/*  985 */         subString = subString + cdata;
/*      */       } 
/*      */     } 
/*  988 */     if (root)
/*      */     {
/*  990 */       return subString;
/*      */     }
/*      */     
/*  993 */     NamedNodeMap attributes = node.getAttributes();
/*  994 */     StringBuilder builder = new StringBuilder();
/*  995 */     for (int j = 0; j < attributes.getLength(); j++) {
/*      */       
/*  997 */       Node attribute = attributes.item(j);
/*  998 */       String attributeNodeValue = attribute.getNodeValue();
/*  999 */       if (attributeNodeValue != null)
/*      */       {
/* 1001 */         attributeNodeValue = attributeNodeValue.replace("\"", "&quot;");
/*      */       }
/* 1003 */       builder.append(String.format(" %s=\"%s\"", new Object[] { attribute.getNodeName(), attributeNodeValue }));
/*      */     } 
/*      */     
/* 1006 */     return String.format("<%s%s>%s</%s>", new Object[] { node.getNodeName(), builder.toString(), subString, node
/* 1007 */           .getNodeName() });
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */