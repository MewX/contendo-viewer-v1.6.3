/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.batik.gvt.event.EventDispatcher;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.SVGFeatureStrings;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGAElement;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserAgentAdapter
/*     */   implements UserAgent
/*     */ {
/*  47 */   protected Set FEATURES = new HashSet();
/*  48 */   protected Set extensions = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BridgeContext ctx;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBridgeContext(BridgeContext ctx) {
/*  59 */     this.ctx = ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStdFeatures() {
/*  67 */     SVGFeatureStrings.addSupportedFeatureStrings(this.FEATURES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension2D getViewportSize() {
/*  74 */     return new Dimension(1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayMessage(String message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayError(String message) {
/*  87 */     displayMessage(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayError(Exception e) {
/*  94 */     displayError(e.getMessage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showAlert(String message) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String showPrompt(String message) {
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String showPrompt(String message, String defaultValue) {
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean showConfirm(String message) {
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/* 128 */     return 0.26458332F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/* 137 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultFontFamily() {
/* 145 */     return "Arial, Helvetica, sans-serif";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMediumFontSize() {
/* 153 */     return 228.59999F / 72.0F * getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLighterFontWeight(float f) {
/* 160 */     return getStandardLighterFontWeight(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBolderFontWeight(float f) {
/* 167 */     return getStandardBolderFontWeight(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguages() {
/* 175 */     return "en";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMedia() {
/* 182 */     return "all";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateStyleSheet() {
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserStyleSheetURI() {
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLParserClassName() {
/* 203 */     return XMLResourceDescriptor.getXMLParserClassName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXMLParserValidating() {
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventDispatcher getEventDispatcher() {
/* 217 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openLink(SVGAElement elt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSVGCursor(Cursor cursor) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextSelection(Mark start, Mark end) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deselectAll() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runThread(Thread t) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransform(AffineTransform at) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point getClientAreaLocationOnScreen() {
/* 263 */     return new Point();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFeature(String s) {
/* 271 */     return this.FEATURES.contains(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean supportExtension(String s) {
/* 279 */     return this.extensions.contains(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerExtension(BridgeExtension ext) {
/* 287 */     Iterator i = ext.getImplementedExtensions();
/* 288 */     while (i.hasNext()) {
/* 289 */       this.extensions.add(i.next());
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
/*     */   
/*     */   public void handleElement(Element elt, Object data) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
/* 319 */     return new DefaultScriptSecurity(scriptType, scriptURL, docURL);
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
/*     */   public void checkLoadScript(String scriptType, ParsedURL scriptURL, ParsedURL docURL) throws SecurityException {
/* 343 */     ScriptSecurity s = getScriptSecurity(scriptType, scriptURL, docURL);
/*     */ 
/*     */     
/* 346 */     if (s != null) {
/* 347 */       s.checkLoadScript();
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
/*     */ 
/*     */   
/*     */   public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourceURL, ParsedURL docURL) {
/* 365 */     return new RelaxedExternalResourceSecurity(resourceURL, docURL);
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
/*     */   public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
/* 387 */     ExternalResourceSecurity s = getExternalResourceSecurity(resourceURL, docURL);
/*     */ 
/*     */     
/* 390 */     if (s != null) {
/* 391 */       s.checkLoadExternalResource();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getStandardLighterFontWeight(float f) {
/* 400 */     int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 401 */     switch (weight) { case 100:
/* 402 */         return 100.0F;
/* 403 */       case 200: return 100.0F;
/* 404 */       case 300: return 200.0F;
/* 405 */       case 400: return 300.0F;
/* 406 */       case 500: return 400.0F;
/* 407 */       case 600: return 400.0F;
/* 408 */       case 700: return 400.0F;
/* 409 */       case 800: return 400.0F;
/* 410 */       case 900: return 400.0F; }
/*     */     
/* 412 */     throw new IllegalArgumentException("Bad Font Weight: " + f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getStandardBolderFontWeight(float f) {
/* 421 */     int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 422 */     switch (weight) { case 100:
/* 423 */         return 600.0F;
/* 424 */       case 200: return 600.0F;
/* 425 */       case 300: return 600.0F;
/* 426 */       case 400: return 600.0F;
/* 427 */       case 500: return 600.0F;
/* 428 */       case 600: return 700.0F;
/* 429 */       case 700: return 800.0F;
/* 430 */       case 800: return 900.0F;
/* 431 */       case 900: return 900.0F; }
/*     */     
/* 433 */     throw new IllegalArgumentException("Bad Font Weight: " + f);
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
/*     */   public SVGDocument getBrokenLinkDocument(Element e, String url, String message) {
/* 448 */     throw new BridgeException(this.ctx, e, "uri.image.broken", new Object[] { url, message });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadDocument(String url) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontFamilyResolver getFontFamilyResolver() {
/* 465 */     return DefaultFontFamilyResolver.SINGLETON;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/UserAgentAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */