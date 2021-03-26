/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Vector;
/*     */ import org.apache.xerces.util.DefaultErrorHandler;
/*     */ import org.apache.xerces.util.ParserConfigurationSettings;
/*     */ import org.apache.xerces.xni.XMLDTDContentModelHandler;
/*     */ import org.apache.xerces.xni.XMLDTDHandler;
/*     */ import org.apache.xerces.xni.XMLDocumentHandler;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLComponentManager;
/*     */ import org.apache.xerces.xni.parser.XMLConfigurationException;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentSource;
/*     */ import org.apache.xerces.xni.parser.XMLEntityResolver;
/*     */ import org.apache.xerces.xni.parser.XMLErrorHandler;
/*     */ import org.apache.xerces.xni.parser.XMLInputSource;
/*     */ import org.apache.xerces.xni.parser.XMLParseException;
/*     */ import org.apache.xerces.xni.parser.XMLPullParserConfiguration;
/*     */ import org.cyberneko.html.b.a;
/*     */ import org.cyberneko.html.filters.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends ParserConfigurationSettings
/*     */   implements XMLPullParserConfiguration
/*     */ {
/*     */   protected static final String a = "http://xml.org/sax/features/namespaces";
/*     */   protected static final String b = "http://cyberneko.org/html/features/augmentations";
/*     */   protected static final String c = "http://cyberneko.org/html/features/report-errors";
/*     */   protected static final String d = "http://cyberneko.org/html/features/report-errors/simple";
/*     */   protected static final String e = "http://cyberneko.org/html/features/balance-tags";
/*     */   protected static final String f = "http://cyberneko.org/html/properties/names/elems";
/*     */   protected static final String g = "http://cyberneko.org/html/properties/names/attrs";
/*     */   protected static final String h = "http://cyberneko.org/html/properties/filters";
/*     */   protected static final String i = "http://cyberneko.org/html/properties/error-reporter";
/*     */   protected static final String j = "http://cyberneko.org/html";
/* 126 */   private static final Class[] A = new Class[] { (z == null) ? (z = a("org.apache.xerces.xni.parser.XMLDocumentSource")) : z }; static Class a(String x0) { try { return Class.forName(x0); } catch (ClassNotFoundException x1) { throw new NoClassDefFoundError(x1.getMessage()); }
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLDocumentHandler k;
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLDTDHandler l;
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLDTDContentModelHandler m;
/*     */ 
/*     */   
/* 144 */   protected XMLErrorHandler n = (XMLErrorHandler)new DefaultErrorHandler();
/*     */ 
/*     */ 
/*     */   
/*     */   protected XMLEntityResolver o;
/*     */ 
/*     */ 
/*     */   
/* 152 */   protected Locale p = Locale.getDefault();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean q;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   protected final Vector r = new Vector(2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   protected final h s = a();
/*     */ 
/*     */   
/* 173 */   protected final i t = new i();
/*     */ 
/*     */   
/* 176 */   protected final d u = new d();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   protected final f v = new a(this);
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean w = false;
/*     */ 
/*     */   
/*     */   protected static boolean x = false;
/*     */ 
/*     */   
/*     */   protected static boolean y = false;
/*     */ 
/*     */   
/*     */   static Class z;
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/* 200 */       String VERSION = "org.apache.xerces.impl.Version";
/* 201 */       Object version = l.a(VERSION, VERSION);
/* 202 */       Field field = version.getClass().getField("fVersion");
/* 203 */       String versionStr = String.valueOf(field.get(version));
/* 204 */       w = versionStr.equals("Xerces-J 2.0.0");
/* 205 */       x = versionStr.equals("Xerces-J 2.0.1");
/* 206 */       y = versionStr.startsWith("XML4J 4.0.");
/*     */     }
/* 208 */     catch (Throwable throwable) {}
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
/*     */   public c() {
/* 221 */     a(this.s);
/* 222 */     a(this.t);
/* 223 */     a((b)this.u);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     String VALIDATION = "http://xml.org/sax/features/validation";
/* 231 */     String[] recognizedFeatures = { "http://cyberneko.org/html/features/augmentations", "http://xml.org/sax/features/namespaces", VALIDATION, "http://cyberneko.org/html/features/report-errors", "http://cyberneko.org/html/features/report-errors/simple", "http://cyberneko.org/html/features/balance-tags" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 239 */     addRecognizedFeatures(recognizedFeatures);
/* 240 */     setFeature("http://cyberneko.org/html/features/augmentations", false);
/* 241 */     setFeature("http://xml.org/sax/features/namespaces", true);
/* 242 */     setFeature(VALIDATION, false);
/* 243 */     setFeature("http://cyberneko.org/html/features/report-errors", false);
/* 244 */     setFeature("http://cyberneko.org/html/features/report-errors/simple", false);
/* 245 */     setFeature("http://cyberneko.org/html/features/balance-tags", true);
/*     */ 
/*     */     
/* 248 */     if (w) {
/*     */ 
/*     */ 
/*     */       
/* 252 */       recognizedFeatures = new String[] { "http://apache.org/xml/features/scanner/notify-builtin-refs" };
/*     */ 
/*     */       
/* 255 */       addRecognizedFeatures(recognizedFeatures);
/*     */     } 
/*     */ 
/*     */     
/* 259 */     if (w || x || y) {
/*     */ 
/*     */ 
/*     */       
/* 263 */       recognizedFeatures = new String[] { "http://apache.org/xml/features/validation/schema/normalized-value", "http://apache.org/xml/features/scanner/notify-char-refs" };
/*     */ 
/*     */ 
/*     */       
/* 267 */       addRecognizedFeatures(recognizedFeatures);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     String[] recognizedProperties = { "http://cyberneko.org/html/properties/names/elems", "http://cyberneko.org/html/properties/names/attrs", "http://cyberneko.org/html/properties/filters", "http://cyberneko.org/html/properties/error-reporter" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     addRecognizedProperties(recognizedProperties);
/* 282 */     setProperty("http://cyberneko.org/html/properties/names/elems", "upper");
/* 283 */     setProperty("http://cyberneko.org/html/properties/names/attrs", "lower");
/* 284 */     setProperty("http://cyberneko.org/html/properties/error-reporter", this.v);
/*     */ 
/*     */     
/* 287 */     if (w) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 293 */       String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
/* 294 */       recognizedProperties = new String[] { SYMBOL_TABLE };
/*     */ 
/*     */       
/* 297 */       addRecognizedProperties(recognizedProperties);
/* 298 */       Object symbolTable = l.a("org.apache.xerces.util.SymbolTable", "org.apache.xerces.util.SymbolTable");
/*     */       
/* 300 */       setProperty(SYMBOL_TABLE, symbolTable);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected h a() {
/* 306 */     return new h();
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
/*     */   public void a(XMLInputSource inputSource) {
/* 330 */     this.s.a(inputSource);
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
/*     */   public void b(XMLInputSource inputSource) {
/* 342 */     this.s.b(inputSource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
/* 351 */     super.setFeature(featureId, state);
/* 352 */     int size = this.r.size();
/* 353 */     for (int j = 0; j < size; j++) {
/* 354 */       b component = this.r.elementAt(j);
/* 355 */       component.setFeature(featureId, state);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
/* 362 */     super.setProperty(propertyId, value);
/*     */     
/* 364 */     if (propertyId.equals("http://cyberneko.org/html/properties/filters")) {
/* 365 */       XMLDocumentFilter[] filters = (XMLDocumentFilter[])getProperty("http://cyberneko.org/html/properties/filters");
/* 366 */       if (filters != null) {
/* 367 */         for (int k = 0; k < filters.length; k++) {
/* 368 */           XMLDocumentFilter filter = filters[k];
/* 369 */           if (filter instanceof b) {
/* 370 */             a((b)filter);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 376 */     int size = this.r.size();
/* 377 */     for (int j = 0; j < size; j++) {
/* 378 */       b component = this.r.elementAt(j);
/* 379 */       component.setProperty(propertyId, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDocumentHandler(XMLDocumentHandler handler) {
/* 385 */     this.k = handler;
/* 386 */     if (handler instanceof j) {
/* 387 */       this.t.a((j)handler);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLDocumentHandler getDocumentHandler() {
/* 393 */     return this.k;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDTDHandler(XMLDTDHandler handler) {
/* 398 */     this.l = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLDTDHandler getDTDHandler() {
/* 403 */     return this.l;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDTDContentModelHandler(XMLDTDContentModelHandler handler) {
/* 408 */     this.m = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLDTDContentModelHandler getDTDContentModelHandler() {
/* 413 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setErrorHandler(XMLErrorHandler handler) {
/* 418 */     this.n = handler;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLErrorHandler getErrorHandler() {
/* 423 */     return this.n;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityResolver(XMLEntityResolver resolver) {
/* 428 */     this.o = resolver;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMLEntityResolver getEntityResolver() {
/* 433 */     return this.o;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLocale(Locale locale) {
/* 438 */     if (locale == null) {
/* 439 */       locale = Locale.getDefault();
/*     */     }
/* 441 */     this.p = locale;
/*     */   }
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 446 */     return this.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void parse(XMLInputSource source) throws XNIException, IOException {
/* 451 */     setInputSource(source);
/* 452 */     parse(true);
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
/*     */   public void setInputSource(XMLInputSource inputSource) throws XMLConfigurationException, IOException {
/* 475 */     b();
/* 476 */     this.q = (inputSource.getByteStream() == null && inputSource.getCharacterStream() == null);
/*     */     
/* 478 */     this.s.setInputSource(inputSource);
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
/*     */   public boolean parse(boolean complete) throws XNIException, IOException {
/*     */     try {
/* 499 */       boolean more = this.s.scanDocument(complete);
/* 500 */       if (!more) {
/* 501 */         cleanup();
/*     */       }
/* 503 */       return more;
/*     */     }
/* 505 */     catch (XNIException e) {
/* 506 */       cleanup();
/* 507 */       throw e;
/*     */     }
/* 509 */     catch (IOException e) {
/* 510 */       cleanup();
/* 511 */       throw e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanup() {
/* 521 */     this.s.a(this.q);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(b component) {
/* 532 */     this.r.addElement(component);
/*     */ 
/*     */     
/* 535 */     String[] features = component.getRecognizedFeatures();
/* 536 */     addRecognizedFeatures(features);
/* 537 */     int featureCount = (features != null) ? features.length : 0;
/* 538 */     for (int j = 0; j < featureCount; j++) {
/* 539 */       Boolean state = component.getFeatureDefault(features[j]);
/* 540 */       if (state != null) {
/* 541 */         setFeature(features[j], state.booleanValue());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 546 */     String[] properties = component.getRecognizedProperties();
/* 547 */     addRecognizedProperties(properties);
/* 548 */     int propertyCount = (properties != null) ? properties.length : 0;
/* 549 */     for (int k = 0; k < propertyCount; k++) {
/* 550 */       Object value = component.getPropertyDefault(properties[k]);
/* 551 */       if (value != null) {
/* 552 */         setProperty(properties[k], value);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b() throws XMLConfigurationException {
/*     */     d d1;
/*     */     XMLDocumentFilter xMLDocumentFilter;
/* 562 */     int size = this.r.size();
/* 563 */     for (int j = 0; j < size; j++) {
/* 564 */       b component = this.r.elementAt(j);
/* 565 */       component.reset((XMLComponentManager)this);
/*     */     } 
/*     */ 
/*     */     
/* 569 */     h h1 = this.s;
/* 570 */     if (getFeature("http://xml.org/sax/features/namespaces")) {
/* 571 */       h1.setDocumentHandler((XMLDocumentHandler)this.u);
/* 572 */       this.u.setDocumentSource((XMLDocumentSource)this.t);
/* 573 */       d1 = this.u;
/*     */     } 
/* 575 */     if (getFeature("http://cyberneko.org/html/features/balance-tags")) {
/* 576 */       d1.setDocumentHandler((XMLDocumentHandler)this.t);
/* 577 */       this.t.setDocumentSource((XMLDocumentSource)this.s);
/* 578 */       xMLDocumentFilter = this.t;
/*     */     } 
/* 580 */     XMLDocumentFilter[] filters = (XMLDocumentFilter[])getProperty("http://cyberneko.org/html/properties/filters");
/* 581 */     if (filters != null) {
/* 582 */       for (int k = 0; k < filters.length; k++) {
/* 583 */         XMLDocumentFilter filter = filters[k];
/* 584 */         a.a().a(filter, (XMLDocumentSource)xMLDocumentFilter);
/* 585 */         xMLDocumentFilter.setDocumentHandler((XMLDocumentHandler)filter);
/* 586 */         xMLDocumentFilter = filter;
/*     */       } 
/*     */     }
/* 589 */     xMLDocumentFilter.setDocumentHandler(this.k);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class a
/*     */     implements f
/*     */   {
/*     */     protected Locale a;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ResourceBundle b;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final c c;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected a(c this$0) {
/* 615 */       this.c = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String a(String key, Object[] args) {
/* 634 */       if (!this.c.getFeature("http://cyberneko.org/html/features/report-errors/simple")) {
/* 635 */         if (!this.c.p.equals(this.a)) {
/* 636 */           this.b = null;
/* 637 */           this.a = this.c.p;
/*     */         } 
/* 639 */         if (this.b == null) {
/* 640 */           this.b = ResourceBundle.getBundle("org/cyberneko/html/res/ErrorMessages", this.c.p);
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 645 */           String value = this.b.getString(key);
/* 646 */           String message = MessageFormat.format(value, args);
/* 647 */           return message;
/*     */         }
/* 649 */         catch (MissingResourceException missingResourceException) {}
/*     */       } 
/*     */ 
/*     */       
/* 653 */       return e(key, args);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void b(String key, Object[] args) throws XMLParseException {
/* 659 */       if (this.c.n != null) {
/* 660 */         this.c.n.warning("http://cyberneko.org/html", key, d(key, args));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c(String key, Object[] args) throws XMLParseException {
/* 667 */       if (this.c.n != null) {
/* 668 */         this.c.n.error("http://cyberneko.org/html", key, d(key, args));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected XMLParseException d(String key, Object[] args) {
/* 678 */       String message = a(key, args);
/* 679 */       return new XMLParseException(this.c.s, message);
/*     */     }
/*     */ 
/*     */     
/*     */     protected String e(String key, Object[] args) {
/* 684 */       StringBuffer str = new StringBuffer();
/* 685 */       str.append("http://cyberneko.org/html");
/* 686 */       str.append('#');
/* 687 */       str.append(key);
/* 688 */       if (args != null && args.length > 0) {
/* 689 */         str.append('\t');
/* 690 */         for (int i = 0; i < args.length; i++) {
/* 691 */           if (i > 0) {
/* 692 */             str.append('\t');
/*     */           }
/* 694 */           str.append(String.valueOf(args[i]));
/*     */         } 
/*     */       } 
/* 697 */       return str.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/c.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */