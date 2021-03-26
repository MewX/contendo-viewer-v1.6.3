/*     */ package org.apache.batik.swing.svg;
/*     */ 
/*     */ import org.apache.batik.bridge.ExternalResourceSecurity;
/*     */ import org.apache.batik.bridge.RelaxedExternalResourceSecurity;
/*     */ import org.apache.batik.bridge.RelaxedScriptSecurity;
/*     */ import org.apache.batik.bridge.ScriptSecurity;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.apache.batik.util.XMLResourceDescriptor;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGUserAgentAdapter
/*     */   implements SVGUserAgent
/*     */ {
/*     */   public void displayError(String message) {
/*  59 */     System.err.println(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayError(Exception ex) {
/*  66 */     ex.printStackTrace();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayMessage(String message) {
/*  74 */     System.out.println(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showAlert(String message) {
/*  81 */     System.err.println(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String showPrompt(String message) {
/*  88 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String showPrompt(String message, String defaultValue) {
/*  95 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean showConfirm(String message) {
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/* 109 */     return 0.26458332F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/* 118 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultFontFamily() {
/* 126 */     return "Serif";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMediumFontSize() {
/* 134 */     return 228.59999F / 72.0F * getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLighterFontWeight(float f) {
/* 142 */     int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 143 */     switch (weight) { case 100:
/* 144 */         return 100.0F;
/* 145 */       case 200: return 100.0F;
/* 146 */       case 300: return 200.0F;
/* 147 */       case 400: return 300.0F;
/* 148 */       case 500: return 400.0F;
/* 149 */       case 600: return 400.0F;
/* 150 */       case 700: return 400.0F;
/* 151 */       case 800: return 400.0F;
/* 152 */       case 900: return 400.0F; }
/*     */     
/* 154 */     throw new IllegalArgumentException("Bad Font Weight: " + f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBolderFontWeight(float f) {
/* 163 */     int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 164 */     switch (weight) { case 100:
/* 165 */         return 600.0F;
/* 166 */       case 200: return 600.0F;
/* 167 */       case 300: return 600.0F;
/* 168 */       case 400: return 600.0F;
/* 169 */       case 500: return 600.0F;
/* 170 */       case 600: return 700.0F;
/* 171 */       case 700: return 800.0F;
/* 172 */       case 800: return 900.0F;
/* 173 */       case 900: return 900.0F; }
/*     */     
/* 175 */     throw new IllegalArgumentException("Bad Font Weight: " + f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguages() {
/* 184 */     return "en";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserStyleSheetURI() {
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLParserClassName() {
/* 199 */     return XMLResourceDescriptor.getXMLParserClassName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXMLParserValidating() {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMedia() {
/* 214 */     return "screen";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateStyleSheet() {
/* 221 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openLink(String uri, boolean newc) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean supportExtension(String s) {
/* 237 */     return false;
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
/*     */   public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
/* 259 */     return (ScriptSecurity)new RelaxedScriptSecurity(scriptType, scriptURL, docURL);
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
/*     */   public void checkLoadScript(String scriptType, ParsedURL scriptURL, ParsedURL docURL) throws SecurityException {
/* 294 */     ScriptSecurity s = getScriptSecurity(scriptType, scriptURL, docURL);
/*     */ 
/*     */ 
/*     */     
/* 298 */     if (s != null) {
/* 299 */       s.checkLoadScript();
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
/* 317 */     return (ExternalResourceSecurity)new RelaxedExternalResourceSecurity(resourceURL, docURL);
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
/*     */   public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
/* 346 */     ExternalResourceSecurity s = getExternalResourceSecurity(resourceURL, docURL);
/*     */ 
/*     */     
/* 349 */     if (s != null)
/* 350 */       s.checkLoadExternalResource(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGUserAgentAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */