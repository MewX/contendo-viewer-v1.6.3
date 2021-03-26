/*     */ package org.apache.batik.transcoder.print;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Paper;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterJob;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.ext.awt.RenderingHintsKeyExt;
/*     */ import org.apache.batik.transcoder.SVGAbstractTranscoder;
/*     */ import org.apache.batik.transcoder.Transcoder;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.keys.BooleanKey;
/*     */ import org.apache.batik.transcoder.keys.LengthKey;
/*     */ import org.apache.batik.transcoder.keys.StringKey;
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
/*     */ public class PrintTranscoder
/*     */   extends SVGAbstractTranscoder
/*     */   implements Printable
/*     */ {
/*     */   public static final String KEY_AOI_STR = "aoi";
/*     */   public static final String KEY_HEIGHT_STR = "height";
/*     */   public static final String KEY_LANGUAGE_STR = "language";
/*     */   public static final String KEY_MARGIN_BOTTOM_STR = "marginBottom";
/*     */   public static final String KEY_MARGIN_LEFT_STR = "marginLeft";
/*     */   public static final String KEY_MARGIN_RIGHT_STR = "marginRight";
/*     */   public static final String KEY_MARGIN_TOP_STR = "marginTop";
/*     */   public static final String KEY_PAGE_HEIGHT_STR = "pageHeight";
/*     */   public static final String KEY_PAGE_ORIENTATION_STR = "pageOrientation";
/*     */   public static final String KEY_PAGE_WIDTH_STR = "pageWidth";
/*     */   public static final String KEY_PIXEL_TO_MM_STR = "pixelToMm";
/*     */   public static final String KEY_SCALE_TO_PAGE_STR = "scaleToPage";
/*     */   public static final String KEY_SHOW_PAGE_DIALOG_STR = "showPageDialog";
/*     */   public static final String KEY_SHOW_PRINTER_DIALOG_STR = "showPrinterDialog";
/*     */   public static final String KEY_USER_STYLESHEET_URI_STR = "userStylesheet";
/*     */   public static final String KEY_WIDTH_STR = "width";
/*     */   public static final String KEY_XML_PARSER_CLASSNAME_STR = "xmlParserClassName";
/*     */   public static final String VALUE_MEDIA_PRINT = "print";
/*     */   public static final String VALUE_PAGE_ORIENTATION_LANDSCAPE = "landscape";
/*     */   public static final String VALUE_PAGE_ORIENTATION_PORTRAIT = "portrait";
/*     */   public static final String VALUE_PAGE_ORIENTATION_REVERSE_LANDSCAPE = "reverseLandscape";
/* 110 */   private List inputs = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   private List printedInputs = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   private int curIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BridgeContext theCtx;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrintTranscoder() {
/* 136 */     this.hints.put(KEY_MEDIA, "print");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void transcode(TranscoderInput in, TranscoderOutput out) {
/* 142 */     if (in != null) {
/* 143 */       this.inputs.add(in);
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
/*     */   protected void transcode(Document document, String uri, TranscoderOutput output) throws TranscoderException {
/* 159 */     super.transcode(document, uri, output);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.theCtx = this.ctx;
/* 165 */     this.ctx = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void print() throws PrinterException {
/* 175 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 176 */     PageFormat pageFormat = printerJob.defaultPage();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     Paper paper = pageFormat.getPaper();
/*     */     
/* 183 */     Float pageWidth = (Float)this.hints.get(KEY_PAGE_WIDTH);
/* 184 */     Float pageHeight = (Float)this.hints.get(KEY_PAGE_HEIGHT);
/* 185 */     if (pageWidth != null) {
/* 186 */       paper.setSize(pageWidth.floatValue(), paper.getHeight());
/*     */     }
/*     */     
/* 189 */     if (pageHeight != null) {
/* 190 */       paper.setSize(paper.getWidth(), pageHeight.floatValue());
/*     */     }
/*     */ 
/*     */     
/* 194 */     float x = 0.0F, y = 0.0F;
/* 195 */     float width = (float)paper.getWidth();
/* 196 */     float height = (float)paper.getHeight();
/*     */     
/* 198 */     Float leftMargin = (Float)this.hints.get(KEY_MARGIN_LEFT);
/* 199 */     Float topMargin = (Float)this.hints.get(KEY_MARGIN_TOP);
/* 200 */     Float rightMargin = (Float)this.hints.get(KEY_MARGIN_RIGHT);
/* 201 */     Float bottomMargin = (Float)this.hints.get(KEY_MARGIN_BOTTOM);
/*     */     
/* 203 */     if (leftMargin != null) {
/* 204 */       x = leftMargin.floatValue();
/* 205 */       width -= leftMargin.floatValue();
/*     */     } 
/* 207 */     if (topMargin != null) {
/* 208 */       y = topMargin.floatValue();
/* 209 */       height -= topMargin.floatValue();
/*     */     } 
/* 211 */     if (rightMargin != null) {
/* 212 */       width -= rightMargin.floatValue();
/*     */     }
/* 214 */     if (bottomMargin != null) {
/* 215 */       height -= bottomMargin.floatValue();
/*     */     }
/*     */     
/* 218 */     paper.setImageableArea(x, y, width, height);
/*     */     
/* 220 */     String pageOrientation = (String)this.hints.get(KEY_PAGE_ORIENTATION);
/* 221 */     if ("portrait".equalsIgnoreCase(pageOrientation)) {
/* 222 */       pageFormat.setOrientation(1);
/*     */     }
/* 224 */     else if ("landscape".equalsIgnoreCase(pageOrientation)) {
/* 225 */       pageFormat.setOrientation(0);
/*     */     }
/* 227 */     else if ("reverseLandscape".equalsIgnoreCase(pageOrientation)) {
/* 228 */       pageFormat.setOrientation(2);
/*     */     } 
/*     */     
/* 231 */     pageFormat.setPaper(paper);
/* 232 */     pageFormat = printerJob.validatePage(pageFormat);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     Boolean showPageFormat = (Boolean)this.hints.get(KEY_SHOW_PAGE_DIALOG);
/* 238 */     if (showPageFormat != null && showPageFormat.booleanValue()) {
/* 239 */       PageFormat tmpPageFormat = printerJob.pageDialog(pageFormat);
/* 240 */       if (tmpPageFormat == pageFormat) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 246 */       pageFormat = tmpPageFormat;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 251 */     printerJob.setPrintable(this, pageFormat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 257 */     Boolean showPrinterDialog = (Boolean)this.hints.get(KEY_SHOW_PRINTER_DIALOG);
/* 258 */     if (showPrinterDialog != null && showPrinterDialog.booleanValue() && 
/* 259 */       !printerJob.printDialog()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     printerJob.print();
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
/*     */   public int print(Graphics _g, PageFormat pageFormat, int pageIndex) {
/* 279 */     if (this.printedInputs == null) {
/* 280 */       this.printedInputs = new ArrayList(this.inputs);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     if (pageIndex >= this.printedInputs.size()) {
/* 287 */       this.curIndex = -1;
/* 288 */       if (this.theCtx != null)
/* 289 */         this.theCtx.dispose(); 
/* 290 */       this.userAgent.displayMessage("Done");
/* 291 */       return 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 297 */     if (this.curIndex != pageIndex) {
/* 298 */       if (this.theCtx != null) {
/* 299 */         this.theCtx.dispose();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 305 */         this.width = (int)pageFormat.getImageableWidth();
/* 306 */         this.height = (int)pageFormat.getImageableHeight();
/* 307 */         super.transcode(this.printedInputs.get(pageIndex), null);
/*     */         
/* 309 */         this.curIndex = pageIndex;
/* 310 */       } catch (TranscoderException e) {
/* 311 */         drawError(_g, (Exception)e);
/* 312 */         return 0;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 317 */     Graphics2D g = (Graphics2D)_g;
/* 318 */     g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 320 */     g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */     
/* 322 */     g.setRenderingHint(RenderingHintsKeyExt.KEY_TRANSCODING, "Printing");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     AffineTransform t = g.getTransform();
/* 329 */     Shape clip = g.getClip();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     g.transform(this.curTxf);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 348 */       this.root.paint(g);
/* 349 */     } catch (Exception e) {
/* 350 */       g.setTransform(t);
/* 351 */       g.setClip(clip);
/* 352 */       drawError(_g, e);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     g.setTransform(t);
/* 359 */     g.setClip(clip);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 368 */     return 0;
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
/*     */   protected void setImageSize(float docWidth, float docHeight) {
/* 380 */     Boolean scaleToPage = (Boolean)this.hints.get(KEY_SCALE_TO_PAGE);
/* 381 */     if (scaleToPage != null && !scaleToPage.booleanValue()) {
/* 382 */       float w = docWidth;
/* 383 */       float h = docHeight;
/* 384 */       if (this.hints.containsKey(KEY_AOI)) {
/* 385 */         Rectangle2D aoi = (Rectangle2D)this.hints.get(KEY_AOI);
/* 386 */         w = (float)aoi.getWidth();
/* 387 */         h = (float)aoi.getHeight();
/*     */       } 
/* 389 */       super.setImageSize(w, h);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawError(Graphics g, Exception e) {
/* 397 */     this.userAgent.displayError(e);
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
/* 432 */   public static final TranscodingHints.Key KEY_SHOW_PAGE_DIALOG = (TranscodingHints.Key)new BooleanKey();
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
/* 464 */   public static final TranscodingHints.Key KEY_SHOW_PRINTER_DIALOG = (TranscodingHints.Key)new BooleanKey();
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
/* 493 */   public static final TranscodingHints.Key KEY_PAGE_WIDTH = (TranscodingHints.Key)new LengthKey();
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
/* 521 */   public static final TranscodingHints.Key KEY_PAGE_HEIGHT = (TranscodingHints.Key)new LengthKey();
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
/* 548 */   public static final TranscodingHints.Key KEY_MARGIN_TOP = (TranscodingHints.Key)new LengthKey();
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
/* 576 */   public static final TranscodingHints.Key KEY_MARGIN_RIGHT = (TranscodingHints.Key)new LengthKey();
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
/* 604 */   public static final TranscodingHints.Key KEY_MARGIN_BOTTOM = (TranscodingHints.Key)new LengthKey();
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
/* 627 */   public static final TranscodingHints.Key KEY_MARGIN_LEFT = (TranscodingHints.Key)new LengthKey();
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
/* 655 */   public static final TranscodingHints.Key KEY_PAGE_ORIENTATION = (TranscodingHints.Key)new StringKey();
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
/* 685 */   public static final TranscodingHints.Key KEY_SCALE_TO_PAGE = (TranscodingHints.Key)new BooleanKey();
/*     */   
/*     */   public static final String USAGE = "java org.apache.batik.transcoder.print.PrintTranscoder <svgFileToPrint>";
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 691 */     if (args.length < 1) {
/* 692 */       System.err.println("java org.apache.batik.transcoder.print.PrintTranscoder <svgFileToPrint>");
/* 693 */       System.exit(0);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 699 */     PrintTranscoder transcoder = new PrintTranscoder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 706 */     setTranscoderFloatHint((Transcoder)transcoder, "language", KEY_LANGUAGE);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 711 */     setTranscoderFloatHint((Transcoder)transcoder, "userStylesheet", KEY_USER_STYLESHEET_URI);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 716 */     setTranscoderStringHint((Transcoder)transcoder, "xmlParserClassName", KEY_XML_PARSER_CLASSNAME);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 721 */     setTranscoderBooleanHint((Transcoder)transcoder, "scaleToPage", KEY_SCALE_TO_PAGE);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 726 */     setTranscoderRectangleHint((Transcoder)transcoder, "aoi", KEY_AOI);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 732 */     setTranscoderFloatHint((Transcoder)transcoder, "width", KEY_WIDTH);
/*     */ 
/*     */     
/* 735 */     setTranscoderFloatHint((Transcoder)transcoder, "height", KEY_HEIGHT);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 740 */     setTranscoderFloatHint((Transcoder)transcoder, "pixelToMm", KEY_PIXEL_UNIT_TO_MILLIMETER);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 745 */     setTranscoderStringHint((Transcoder)transcoder, "pageOrientation", KEY_PAGE_ORIENTATION);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 750 */     setTranscoderFloatHint((Transcoder)transcoder, "pageWidth", KEY_PAGE_WIDTH);
/*     */ 
/*     */     
/* 753 */     setTranscoderFloatHint((Transcoder)transcoder, "pageHeight", KEY_PAGE_HEIGHT);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 758 */     setTranscoderFloatHint((Transcoder)transcoder, "marginTop", KEY_MARGIN_TOP);
/*     */ 
/*     */     
/* 761 */     setTranscoderFloatHint((Transcoder)transcoder, "marginRight", KEY_MARGIN_RIGHT);
/*     */ 
/*     */     
/* 764 */     setTranscoderFloatHint((Transcoder)transcoder, "marginBottom", KEY_MARGIN_BOTTOM);
/*     */ 
/*     */     
/* 767 */     setTranscoderFloatHint((Transcoder)transcoder, "marginLeft", KEY_MARGIN_LEFT);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 772 */     setTranscoderBooleanHint((Transcoder)transcoder, "showPageDialog", KEY_SHOW_PAGE_DIALOG);
/*     */ 
/*     */ 
/*     */     
/* 776 */     setTranscoderBooleanHint((Transcoder)transcoder, "showPrinterDialog", KEY_SHOW_PRINTER_DIALOG);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 784 */     for (String arg : args) {
/* 785 */       transcoder.transcode(new TranscoderInput((new File(arg)).toURI().toURL().toString()), (TranscoderOutput)null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 792 */     transcoder.print();
/*     */     
/* 794 */     System.exit(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTranscoderFloatHint(Transcoder transcoder, String property, TranscodingHints.Key key) {
/* 800 */     String str = System.getProperty(property);
/* 801 */     if (str != null) {
/*     */       try {
/* 803 */         Float value = Float.valueOf(Float.parseFloat(str));
/* 804 */         transcoder.addTranscodingHint(key, value);
/* 805 */       } catch (NumberFormatException e) {
/* 806 */         handleValueError(property, str);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTranscoderRectangleHint(Transcoder transcoder, String property, TranscodingHints.Key key) {
/* 814 */     String str = System.getProperty(property);
/* 815 */     if (str != null) {
/* 816 */       StringTokenizer st = new StringTokenizer(str, " ,");
/* 817 */       if (st.countTokens() != 4) {
/* 818 */         handleValueError(property, str);
/*     */       }
/*     */       
/*     */       try {
/* 822 */         String x = st.nextToken();
/* 823 */         String y = st.nextToken();
/* 824 */         String width = st.nextToken();
/* 825 */         String height = st.nextToken();
/* 826 */         Rectangle2D r = new Rectangle2D.Float(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(width), Float.parseFloat(height));
/*     */ 
/*     */ 
/*     */         
/* 830 */         transcoder.addTranscodingHint(key, r);
/* 831 */       } catch (NumberFormatException e) {
/* 832 */         handleValueError(property, str);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTranscoderBooleanHint(Transcoder transcoder, String property, TranscodingHints.Key key) {
/* 840 */     String str = System.getProperty(property);
/* 841 */     if (str != null) {
/* 842 */       Boolean value = "true".equalsIgnoreCase(str) ? Boolean.TRUE : Boolean.FALSE;
/* 843 */       transcoder.addTranscodingHint(key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTranscoderStringHint(Transcoder transcoder, String property, TranscodingHints.Key key) {
/* 850 */     String str = System.getProperty(property);
/* 851 */     if (str != null) {
/* 852 */       transcoder.addTranscodingHint(key, str);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handleValueError(String property, String value) {
/* 858 */     System.err.println("Invalid " + property + " value : " + value);
/* 859 */     System.exit(1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/print/PrintTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */