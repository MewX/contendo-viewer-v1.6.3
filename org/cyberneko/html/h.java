/*      */ package org.cyberneko.html;
/*      */ 
/*      */ import java.io.EOFException;
/*      */ import java.io.File;
/*      */ import java.io.FilterInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.URL;
/*      */ import java.util.BitSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Stack;
/*      */ import org.apache.xerces.util.EncodingMap;
/*      */ import org.apache.xerces.util.NamespaceSupport;
/*      */ import org.apache.xerces.util.URI;
/*      */ import org.apache.xerces.util.XMLAttributesImpl;
/*      */ import org.apache.xerces.util.XMLResourceIdentifierImpl;
/*      */ import org.apache.xerces.util.XMLStringBuffer;
/*      */ import org.apache.xerces.xni.Augmentations;
/*      */ import org.apache.xerces.xni.NamespaceContext;
/*      */ import org.apache.xerces.xni.QName;
/*      */ import org.apache.xerces.xni.XMLAttributes;
/*      */ import org.apache.xerces.xni.XMLDocumentHandler;
/*      */ import org.apache.xerces.xni.XMLLocator;
/*      */ import org.apache.xerces.xni.XMLResourceIdentifier;
/*      */ import org.apache.xerces.xni.XMLString;
/*      */ import org.apache.xerces.xni.XNIException;
/*      */ import org.apache.xerces.xni.parser.XMLComponentManager;
/*      */ import org.apache.xerces.xni.parser.XMLConfigurationException;
/*      */ import org.apache.xerces.xni.parser.XMLDocumentScanner;
/*      */ import org.apache.xerces.xni.parser.XMLInputSource;
/*      */ import org.cyberneko.html.b.a;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class h
/*      */   implements XMLLocator, XMLDocumentScanner, b
/*      */ {
/*      */   public static final String a = "-//W3C//DTD HTML 4.01//EN";
/*      */   public static final String b = "http://www.w3.org/TR/html4/strict.dtd";
/*      */   public static final String c = "-//W3C//DTD HTML 4.01 Transitional//EN";
/*      */   public static final String d = "http://www.w3.org/TR/html4/loose.dtd";
/*      */   public static final String e = "-//W3C//DTD HTML 4.01 Frameset//EN";
/*      */   public static final String f = "http://www.w3.org/TR/html4/frameset.dtd";
/*      */   protected static final String g = "http://cyberneko.org/html/features/augmentations";
/*      */   protected static final String h = "http://cyberneko.org/html/features/report-errors";
/*      */   public static final String i = "http://apache.org/xml/features/scanner/notify-char-refs";
/*      */   public static final String j = "http://apache.org/xml/features/scanner/notify-builtin-refs";
/*      */   public static final String k = "http://cyberneko.org/html/features/scanner/notify-builtin-refs";
/*      */   public static final String l = "http://cyberneko.org/html/features/scanner/fix-mswindows-refs";
/*      */   public static final String m = "http://cyberneko.org/html/features/scanner/script/strip-comment-delims";
/*      */   public static final String n = "http://cyberneko.org/html/features/scanner/script/strip-cdata-delims";
/*      */   public static final String o = "http://cyberneko.org/html/features/scanner/style/strip-comment-delims";
/*      */   public static final String p = "http://cyberneko.org/html/features/scanner/style/strip-cdata-delims";
/*      */   public static final String q = "http://cyberneko.org/html/features/scanner/ignore-specified-charset";
/*      */   public static final String r = "http://cyberneko.org/html/features/scanner/cdata-sections";
/*      */   public static final String s = "http://cyberneko.org/html/features/override-doctype";
/*      */   public static final String t = "http://cyberneko.org/html/features/insert-doctype";
/*      */   public static final String u = "http://cyberneko.org/html/features/parse-noscript-content";
/*      */   public static final String v = "http://cyberneko.org/html/features/scanner/allow-selfclosing-iframe";
/*      */   public static final String w = "http://cyberneko.org/html/features/scanner/allow-selfclosing-tags";
/*      */   protected static final String x = "http://cyberneko.org/html/features/scanner/normalize-attrs";
/*      */   
/*      */   static boolean[] a(h x0) {
/*   98 */     return x0.aU; } static boolean a(h x0, XMLStringBuffer x1, String x2) { return x0.a(x1, x2); } static boolean b(h x0) throws IOException { return x0.j(); } static XMLStringBuffer c(h x0) { return x0.aR; } static XMLStringBuffer d(h x0) { return x0.aQ; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  219 */   private static final String[] aH = new String[] { "http://cyberneko.org/html/features/augmentations", "http://cyberneko.org/html/features/report-errors", "http://apache.org/xml/features/scanner/notify-char-refs", "http://apache.org/xml/features/scanner/notify-builtin-refs", "http://cyberneko.org/html/features/scanner/notify-builtin-refs", "http://cyberneko.org/html/features/scanner/fix-mswindows-refs", "http://cyberneko.org/html/features/scanner/script/strip-cdata-delims", "http://cyberneko.org/html/features/scanner/script/strip-comment-delims", "http://cyberneko.org/html/features/scanner/style/strip-cdata-delims", "http://cyberneko.org/html/features/scanner/style/strip-comment-delims", "http://cyberneko.org/html/features/scanner/ignore-specified-charset", "http://cyberneko.org/html/features/scanner/cdata-sections", "http://cyberneko.org/html/features/override-doctype", "http://cyberneko.org/html/features/insert-doctype", "http://cyberneko.org/html/features/scanner/normalize-attrs", "http://cyberneko.org/html/features/parse-noscript-content", "http://cyberneko.org/html/features/scanner/allow-selfclosing-iframe", "http://cyberneko.org/html/features/scanner/allow-selfclosing-tags" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  241 */   private static final Boolean[] aI = new Boolean[] { null, null, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String y = "http://cyberneko.org/html/properties/names/elems";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String z = "http://cyberneko.org/html/properties/names/attrs";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String A = "http://cyberneko.org/html/properties/default-encoding";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String B = "http://cyberneko.org/html/properties/error-reporter";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String C = "http://cyberneko.org/html/properties/doctype/pubid";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String D = "http://cyberneko.org/html/properties/doctype/sysid";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  283 */   private static final String[] aJ = new String[] { "http://cyberneko.org/html/properties/names/elems", "http://cyberneko.org/html/properties/names/attrs", "http://cyberneko.org/html/properties/default-encoding", "http://cyberneko.org/html/properties/error-reporter", "http://cyberneko.org/html/properties/doctype/pubid", "http://cyberneko.org/html/properties/doctype/sysid" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  293 */   private static final Object[] aK = new Object[] { null, null, "Windows-1252", null, "-//W3C//DTD HTML 4.01 Transitional//EN", "http://www.w3.org/TR/html4/loose.dtd" };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short E = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short F = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short G = 10;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short H = 11;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short I = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short J = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short K = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int L = 2048;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final boolean aL = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final boolean aM = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final boolean aN = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final boolean aO = false;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final boolean M = false;
/*      */ 
/*      */ 
/*      */   
/*  352 */   protected static final g N = new g.a();
/*      */ 
/*      */   
/*  355 */   private static final BitSet aP = new BitSet(); protected boolean O; protected boolean P; protected boolean Q; protected boolean R; protected boolean S; protected boolean T; protected boolean U; protected boolean V;
/*      */   static {
/*  357 */     String str = "-.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
/*  358 */     for (int i = 0; i < "-.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".length(); i++) {
/*  359 */       char c1 = "-.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt(i);
/*  360 */       aP.set(c1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean W;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean X;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean Y;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean Z;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean aa;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean ab;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean ac;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean ad;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean ae;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean af;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean ag;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short ah;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short ai;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String aj;
/*      */ 
/*      */ 
/*      */   
/*      */   protected f ak;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String al;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String am;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int an;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int ao;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int ap;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int aq;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int ar;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int as;
/*      */ 
/*      */ 
/*      */   
/*      */   protected d at;
/*      */ 
/*      */ 
/*      */   
/*      */   protected b au;
/*      */ 
/*      */ 
/*      */   
/*  475 */   protected final Stack av = new Stack();
/*      */ 
/*      */ 
/*      */   
/*      */   protected e aw;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short ax;
/*      */ 
/*      */   
/*      */   protected XMLDocumentHandler ay;
/*      */ 
/*      */   
/*      */   protected String az;
/*      */ 
/*      */   
/*      */   protected String aA;
/*      */ 
/*      */   
/*      */   protected boolean aB;
/*      */ 
/*      */   
/*      */   protected int aC;
/*      */ 
/*      */   
/*      */   protected int aD;
/*      */ 
/*      */   
/*  504 */   protected e aE = new a(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  511 */   protected f aF = new f(this);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  516 */   protected final XMLStringBuffer aG = new XMLStringBuffer(1024);
/*      */ 
/*      */   
/*  519 */   private final XMLStringBuffer aQ = new XMLStringBuffer(1024);
/*      */ 
/*      */   
/*  522 */   private final XMLStringBuffer aR = new XMLStringBuffer(128);
/*      */ 
/*      */   
/*  525 */   private final a aS = new a();
/*      */ 
/*      */   
/*  528 */   private final c aT = new c();
/*      */ 
/*      */   
/*  531 */   private final boolean[] aU = new boolean[] { false };
/*      */ 
/*      */   
/*  534 */   private final XMLResourceIdentifierImpl aV = new XMLResourceIdentifierImpl();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(XMLInputSource inputSource) {
/*  555 */     Reader reader = c(inputSource);
/*      */     
/*  557 */     this.av.push(this.au);
/*  558 */     String encoding = inputSource.getEncoding();
/*  559 */     String publicId = inputSource.getPublicId();
/*  560 */     String baseSystemId = inputSource.getBaseSystemId();
/*  561 */     String literalSystemId = inputSource.getSystemId();
/*  562 */     String expandedSystemId = a(literalSystemId, baseSystemId);
/*  563 */     this.au = new b(reader, encoding, publicId, baseSystemId, literalSystemId, expandedSystemId);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Reader c(XMLInputSource inputSource) {
/*  569 */     Reader reader = inputSource.getCharacterStream();
/*  570 */     if (reader == null) {
/*      */       try {
/*  572 */         return new InputStreamReader(inputSource.getByteStream(), this.aA);
/*      */       }
/*  574 */       catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*      */     }
/*      */ 
/*      */     
/*  578 */     return reader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void b(XMLInputSource inputSource) {
/*  589 */     e previousScanner = this.aw;
/*  590 */     short previousScannerState = this.ax;
/*  591 */     b previousEntity = this.au;
/*  592 */     Reader reader = c(inputSource);
/*      */     
/*  594 */     String encoding = inputSource.getEncoding();
/*  595 */     String publicId = inputSource.getPublicId();
/*  596 */     String baseSystemId = inputSource.getBaseSystemId();
/*  597 */     String literalSystemId = inputSource.getSystemId();
/*  598 */     String expandedSystemId = a(literalSystemId, baseSystemId);
/*  599 */     this.au = new b(reader, encoding, publicId, baseSystemId, literalSystemId, expandedSystemId);
/*      */ 
/*      */     
/*  602 */     a(this.aE);
/*  603 */     a((short)0);
/*      */     try {
/*      */       do {
/*  606 */         this.aw.a(false);
/*  607 */       } while (this.ax != 11);
/*      */     }
/*  609 */     catch (IOException iOException) {}
/*      */ 
/*      */     
/*  612 */     a(previousScanner);
/*  613 */     a(previousScannerState);
/*  614 */     this.au = previousEntity;
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
/*      */   public void a(boolean closeall) {
/*  628 */     int size = this.av.size();
/*  629 */     if (size > 0) {
/*      */       
/*  631 */       if (this.au != null) {
/*  632 */         b.a(this.au);
/*      */       }
/*      */       
/*  635 */       for (int i = closeall ? 0 : 1; i < size; i++) {
/*  636 */         this.au = this.av.pop();
/*  637 */         b.a(this.au);
/*      */       }
/*      */     
/*  640 */     } else if (closeall && this.au != null) {
/*  641 */       b.a(this.au);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEncoding() {
/*  651 */     return (this.au != null) ? b.b(this.au) : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPublicId() {
/*  656 */     return (this.au != null) ? this.au.a : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getBaseSystemId() {
/*  661 */     return (this.au != null) ? this.au.b : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLiteralSystemId() {
/*  666 */     return (this.au != null) ? this.au.c : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getExpandedSystemId() {
/*  671 */     return (this.au != null) ? this.au.d : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLineNumber() {
/*  676 */     return (this.au != null) ? this.au.c() : -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getColumnNumber() {
/*  681 */     return (this.au != null) ? b.c(this.au) : -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getXMLVersion() {
/*  686 */     this.au.getClass(); return (this.au != null) ? "1.0" : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCharacterOffset() {
/*  691 */     return (this.au != null) ? b.d(this.au) : -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean getFeatureDefault(String featureId) {
/*  700 */     int length = (aH != null) ? aH.length : 0;
/*  701 */     for (int i = 0; i < length; i++) {
/*  702 */       if (aH[i].equals(featureId)) {
/*  703 */         return aI[i];
/*      */       }
/*      */     } 
/*  706 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getPropertyDefault(String propertyId) {
/*  711 */     int length = (aJ != null) ? aJ.length : 0;
/*  712 */     for (int i = 0; i < length; i++) {
/*  713 */       if (aJ[i].equals(propertyId)) {
/*  714 */         return aK[i];
/*      */       }
/*      */     } 
/*  717 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getRecognizedFeatures() {
/*  726 */     return aH;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getRecognizedProperties() {
/*  731 */     return aJ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset(XMLComponentManager manager) throws XMLConfigurationException {
/*  739 */     this.O = manager.getFeature("http://cyberneko.org/html/features/augmentations");
/*  740 */     this.P = manager.getFeature("http://cyberneko.org/html/features/report-errors");
/*  741 */     this.Q = manager.getFeature("http://apache.org/xml/features/scanner/notify-char-refs");
/*  742 */     this.R = manager.getFeature("http://apache.org/xml/features/scanner/notify-builtin-refs");
/*  743 */     this.S = manager.getFeature("http://cyberneko.org/html/features/scanner/notify-builtin-refs");
/*  744 */     this.T = manager.getFeature("http://cyberneko.org/html/features/scanner/fix-mswindows-refs");
/*  745 */     this.U = manager.getFeature("http://cyberneko.org/html/features/scanner/script/strip-cdata-delims");
/*  746 */     this.V = manager.getFeature("http://cyberneko.org/html/features/scanner/script/strip-comment-delims");
/*  747 */     this.W = manager.getFeature("http://cyberneko.org/html/features/scanner/style/strip-cdata-delims");
/*  748 */     this.X = manager.getFeature("http://cyberneko.org/html/features/scanner/style/strip-comment-delims");
/*  749 */     this.Y = manager.getFeature("http://cyberneko.org/html/features/scanner/ignore-specified-charset");
/*  750 */     this.Z = manager.getFeature("http://cyberneko.org/html/features/scanner/cdata-sections");
/*  751 */     this.aa = manager.getFeature("http://cyberneko.org/html/features/override-doctype");
/*  752 */     this.ab = manager.getFeature("http://cyberneko.org/html/features/insert-doctype");
/*  753 */     this.ac = manager.getFeature("http://cyberneko.org/html/features/scanner/normalize-attrs");
/*  754 */     this.ad = manager.getFeature("http://cyberneko.org/html/features/parse-noscript-content");
/*  755 */     this.af = manager.getFeature("http://cyberneko.org/html/features/scanner/allow-selfclosing-iframe");
/*  756 */     this.ag = manager.getFeature("http://cyberneko.org/html/features/scanner/allow-selfclosing-tags");
/*      */ 
/*      */     
/*  759 */     this.ah = b(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/elems")));
/*  760 */     this.ai = b(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/attrs")));
/*  761 */     this.aj = String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/default-encoding"));
/*  762 */     this.ak = (f)manager.getProperty("http://cyberneko.org/html/properties/error-reporter");
/*  763 */     this.al = String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/doctype/pubid"));
/*  764 */     this.am = String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/doctype/sysid"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String featureId, boolean state) {
/*  771 */     if (featureId.equals("http://cyberneko.org/html/features/augmentations")) {
/*  772 */       this.O = state;
/*      */     }
/*  774 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/ignore-specified-charset")) {
/*  775 */       this.Y = state;
/*      */     }
/*  777 */     else if (featureId.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
/*  778 */       this.Q = state;
/*      */     }
/*  780 */     else if (featureId.equals("http://apache.org/xml/features/scanner/notify-builtin-refs")) {
/*  781 */       this.R = state;
/*      */     }
/*  783 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/notify-builtin-refs")) {
/*  784 */       this.S = state;
/*      */     }
/*  786 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/fix-mswindows-refs")) {
/*  787 */       this.T = state;
/*      */     }
/*  789 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/script/strip-cdata-delims")) {
/*  790 */       this.U = state;
/*      */     }
/*  792 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/script/strip-comment-delims")) {
/*  793 */       this.V = state;
/*      */     }
/*  795 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/style/strip-cdata-delims")) {
/*  796 */       this.W = state;
/*      */     }
/*  798 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/style/strip-comment-delims")) {
/*  799 */       this.X = state;
/*      */     }
/*  801 */     else if (featureId.equals("http://cyberneko.org/html/features/parse-noscript-content")) {
/*  802 */       this.ad = state;
/*      */     }
/*  804 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/allow-selfclosing-iframe")) {
/*  805 */       this.af = state;
/*      */     }
/*  807 */     else if (featureId.equals("http://cyberneko.org/html/features/scanner/allow-selfclosing-tags")) {
/*  808 */       this.ag = state;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
/*  817 */     if (propertyId.equals("http://cyberneko.org/html/properties/names/elems")) {
/*  818 */       this.ah = b(String.valueOf(value));
/*      */       
/*      */       return;
/*      */     } 
/*  822 */     if (propertyId.equals("http://cyberneko.org/html/properties/names/attrs")) {
/*  823 */       this.ai = b(String.valueOf(value));
/*      */       
/*      */       return;
/*      */     } 
/*  827 */     if (propertyId.equals("http://cyberneko.org/html/properties/default-encoding")) {
/*  828 */       this.aj = String.valueOf(value);
/*      */       return;
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
/*      */   public void setInputSource(XMLInputSource source) throws IOException {
/*  842 */     this.aC = 0;
/*  843 */     this.aD = -1;
/*  844 */     this.at = null;
/*  845 */     this.av.removeAllElements();
/*      */     
/*  847 */     this.an = 1;
/*  848 */     this.ao = 1;
/*  849 */     this.ap = 0;
/*  850 */     this.aq = this.an;
/*  851 */     this.ar = this.ao;
/*  852 */     this.as = this.ap;
/*      */ 
/*      */     
/*  855 */     this.az = this.aj;
/*  856 */     this.aA = this.az;
/*      */ 
/*      */     
/*  859 */     String encoding = source.getEncoding();
/*  860 */     String publicId = source.getPublicId();
/*  861 */     String baseSystemId = source.getBaseSystemId();
/*  862 */     String literalSystemId = source.getSystemId();
/*  863 */     String expandedSystemId = a(literalSystemId, baseSystemId);
/*      */ 
/*      */     
/*  866 */     Reader reader = source.getCharacterStream();
/*  867 */     if (reader == null) {
/*  868 */       InputStream inputStream = source.getByteStream();
/*  869 */       if (inputStream == null) {
/*  870 */         URL url = new URL(expandedSystemId);
/*  871 */         inputStream = url.openStream();
/*      */       } 
/*  873 */       this.at = new d(inputStream);
/*  874 */       String[] encodings = new String[2];
/*  875 */       if (encoding == null) {
/*  876 */         this.at.a(encodings);
/*      */       } else {
/*      */         
/*  879 */         encodings[0] = encoding;
/*      */       } 
/*  881 */       if (encodings[0] == null) {
/*  882 */         encodings[0] = this.aj;
/*  883 */         if (this.P) {
/*  884 */           this.ak.b("HTML1000", null);
/*      */         }
/*      */       } 
/*  887 */       if (encodings[1] == null) {
/*  888 */         encodings[1] = EncodingMap.getIANA2JavaMapping(encodings[0].toUpperCase(Locale.ENGLISH));
/*  889 */         if (encodings[1] == null) {
/*  890 */           encodings[1] = encodings[0];
/*  891 */           if (this.P) {
/*  892 */             this.ak.b("HTML1001", new Object[] { encodings[0] });
/*      */           }
/*      */         } 
/*      */       } 
/*  896 */       this.az = encodings[0];
/*  897 */       this.aA = encodings[1];
/*      */       
/*  899 */       this.aB = (this.az == null || this.az.toUpperCase(Locale.ENGLISH).startsWith("ISO-8859") || this.az.equalsIgnoreCase(this.aj));
/*      */ 
/*      */       
/*  902 */       encoding = this.az;
/*  903 */       reader = new InputStreamReader(this.at, this.aA);
/*      */     } 
/*  905 */     this.au = new b(reader, encoding, publicId, baseSystemId, literalSystemId, expandedSystemId);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  910 */     a(this.aE);
/*  911 */     a((short)10);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean scanDocument(boolean complete) throws XNIException, IOException {
/*      */     while (true) {
/*  918 */       if (!this.aw.a(complete)) {
/*  919 */         return false;
/*      */       }
/*  921 */       if (!complete)
/*  922 */         return true; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setDocumentHandler(XMLDocumentHandler handler) {
/*  927 */     this.ay = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLDocumentHandler getDocumentHandler() {
/*  934 */     return this.ay;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static String a(XMLAttributes attrs, String aname) {
/*  943 */     int length = (attrs != null) ? attrs.getLength() : 0;
/*  944 */     for (int i = 0; i < length; i++) {
/*  945 */       if (attrs.getQName(i).equalsIgnoreCase(aname)) {
/*  946 */         return attrs.getValue(i);
/*      */       }
/*      */     } 
/*  949 */     return null;
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
/*      */   public static String a(String systemId, String baseSystemId) {
/*  968 */     if (systemId == null || systemId.length() == 0) {
/*  969 */       return systemId;
/*      */     }
/*      */     
/*      */     try {
/*  973 */       URI uRI = new URI(systemId);
/*  974 */       if (uRI != null) {
/*  975 */         return systemId;
/*      */       }
/*      */     }
/*  978 */     catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {}
/*      */ 
/*      */ 
/*      */     
/*  982 */     String id = a(systemId);
/*      */ 
/*      */     
/*  985 */     URI base = null;
/*  986 */     URI uri = null;
/*      */     try {
/*  988 */       if (baseSystemId == null || baseSystemId.length() == 0 || baseSystemId.equals(systemId)) {
/*      */         String str;
/*      */         
/*      */         try {
/*  992 */           str = a(System.getProperty("user.dir"));
/*      */         }
/*  994 */         catch (SecurityException se) {
/*  995 */           str = "";
/*      */         } 
/*  997 */         if (!str.endsWith("/")) {
/*  998 */           str = str + "/";
/*      */         }
/* 1000 */         base = new URI("file", "", str, null, null);
/*      */       } else {
/*      */         
/*      */         try {
/* 1004 */           base = new URI(a(baseSystemId));
/*      */         }
/* 1006 */         catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
/*      */           String str;
/*      */           try {
/* 1009 */             str = a(System.getProperty("user.dir"));
/*      */           }
/* 1011 */           catch (SecurityException se) {
/* 1012 */             str = "";
/*      */           } 
/* 1014 */           if (baseSystemId.indexOf(':') != -1) {
/*      */ 
/*      */             
/* 1017 */             base = new URI("file", "", a(baseSystemId), null, null);
/*      */           } else {
/*      */             
/* 1020 */             if (!str.endsWith("/")) {
/* 1021 */               str = str + "/";
/*      */             }
/* 1023 */             str = str + a(baseSystemId);
/* 1024 */             base = new URI("file", "", str, null, null);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1029 */       uri = new URI(base, id);
/*      */     }
/* 1031 */     catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {}
/*      */ 
/*      */ 
/*      */     
/* 1035 */     if (uri == null) {
/* 1036 */       return systemId;
/*      */     }
/* 1038 */     return uri.toString();
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
/*      */   protected static String a(String str) {
/* 1052 */     str = str.replace(File.separatorChar, '/');
/*      */ 
/*      */     
/* 1055 */     if (str.length() >= 2) {
/* 1056 */       char ch1 = str.charAt(1);
/*      */       
/* 1058 */       if (ch1 == ':') {
/* 1059 */         char ch0 = String.valueOf(str.charAt(0)).toUpperCase(Locale.ENGLISH).charAt(0);
/* 1060 */         if (ch0 >= 'A' && ch0 <= 'Z') {
/* 1061 */           str = "/" + str;
/*      */         
/*      */         }
/*      */       }
/* 1065 */       else if (ch1 == '/' && str.charAt(0) == '/') {
/* 1066 */         str = "file:" + str;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1071 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String a(String name, short mode) {
/* 1077 */     switch (mode) { case 1:
/* 1078 */         return name.toUpperCase(Locale.ENGLISH);
/* 1079 */       case 2: return name.toLowerCase(Locale.ENGLISH); }
/*      */     
/* 1081 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short b(String value) {
/* 1092 */     if (value.equals("lower")) {
/* 1093 */       return 2;
/*      */     }
/* 1095 */     if (value.equals("upper")) {
/* 1096 */       return 1;
/*      */     }
/* 1098 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int a(int origChar) {
/* 1109 */     switch (origChar) { case 130:
/* 1110 */         return 8218;
/* 1111 */       case 131: return 402;
/* 1112 */       case 132: return 8222;
/* 1113 */       case 133: return 8230;
/* 1114 */       case 134: return 8224;
/* 1115 */       case 135: return 8225;
/* 1116 */       case 136: return 710;
/* 1117 */       case 137: return 8240;
/* 1118 */       case 138: return 352;
/* 1119 */       case 139: return 8249;
/* 1120 */       case 140: return 338;
/* 1121 */       case 145: return 8216;
/* 1122 */       case 146: return 8217;
/* 1123 */       case 147: return 8220;
/* 1124 */       case 148: return 8221;
/* 1125 */       case 149: return 8226;
/* 1126 */       case 150: return 8211;
/* 1127 */       case 151: return 8212;
/* 1128 */       case 152: return 732;
/* 1129 */       case 153: return 8482;
/* 1130 */       case 154: return 353;
/* 1131 */       case 155: return 8250;
/* 1132 */       case 156: return 339;
/* 1133 */       case 159: return 376; }
/*      */     
/* 1135 */     return origChar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int a() throws IOException {
/* 1145 */     return this.au.b();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(e scanner) {
/* 1153 */     this.aw = scanner;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void a(short state) {
/* 1163 */     this.ax = state;
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
/*      */   protected void b() throws IOException {
/* 1180 */     String root = null;
/* 1181 */     String pubid = null;
/* 1182 */     String sysid = null;
/*      */     
/* 1184 */     if (d()) {
/* 1185 */       root = b(true);
/* 1186 */       if (root == null) {
/* 1187 */         if (this.P) {
/* 1188 */           this.ak.c("HTML1014", null);
/*      */         }
/*      */       } else {
/*      */         
/* 1192 */         root = a(root, this.ah);
/*      */       } 
/* 1194 */       if (d()) {
/* 1195 */         if (a("PUBLIC", false)) {
/* 1196 */           d();
/* 1197 */           pubid = c();
/* 1198 */           if (d()) {
/* 1199 */             sysid = c();
/*      */           }
/*      */         }
/* 1202 */         else if (a("SYSTEM", false)) {
/* 1203 */           d();
/* 1204 */           sysid = c();
/*      */         } 
/*      */       }
/*      */     } 
/*      */     int i;
/* 1209 */     while ((i = this.au.b()) != -1) {
/* 1210 */       if (i == 60) {
/* 1211 */         b.e(this.au);
/*      */         break;
/*      */       } 
/* 1214 */       if (i == 62) {
/*      */         break;
/*      */       }
/* 1217 */       if (i == 91) {
/* 1218 */         c(true);
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1223 */     if (this.ay != null) {
/* 1224 */       if (this.aa) {
/* 1225 */         pubid = this.al;
/* 1226 */         sysid = this.am;
/*      */       } 
/* 1228 */       this.aq = this.au.c();
/* 1229 */       this.ar = b.c(this.au);
/* 1230 */       this.as = b.d(this.au);
/* 1231 */       this.ay.doctypeDecl(root, pubid, sysid, f());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String c() throws IOException {
/* 1238 */     int quote = this.au.b();
/* 1239 */     if (quote == 39 || quote == 34) {
/* 1240 */       StringBuffer str = new StringBuffer();
/*      */       int i;
/* 1242 */       while ((i = this.au.b()) != -1 && 
/* 1243 */         i != quote) {
/*      */ 
/*      */         
/* 1246 */         if (i == 13 || i == 10) {
/* 1247 */           b.e(this.au);
/*      */ 
/*      */           
/* 1250 */           e();
/* 1251 */           str.append(' '); continue;
/*      */         } 
/* 1253 */         if (i == 60) {
/* 1254 */           b.e(this.au);
/*      */           
/*      */           break;
/*      */         } 
/* 1258 */         str.append((char)i);
/*      */       } 
/*      */       
/* 1261 */       if (i == -1) {
/* 1262 */         if (this.P) {
/* 1263 */           this.ak.c("HTML1007", null);
/*      */         }
/* 1265 */         throw new EOFException();
/*      */       } 
/* 1267 */       return str.toString();
/*      */     } 
/* 1269 */     b.e(this.au);
/* 1270 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String b(boolean strict) throws IOException {
/* 1278 */     if (this.au.h == this.au.i && 
/* 1279 */       this.au.a(0) == -1)
/*      */     {
/*      */ 
/*      */       
/* 1283 */       return null;
/*      */     }
/*      */     
/* 1286 */     int offset = this.au.h;
/*      */     while (true) {
/* 1288 */       if (this.au.a()) {
/* 1289 */         char c1 = b.f(this.au);
/* 1290 */         if ((strict && !Character.isLetterOrDigit(c1) && c1 != '-' && c1 != '.' && c1 != ':' && c1 != '_') || (!strict && (Character.isWhitespace(c1) || c1 == '=' || c1 == '/' || c1 == '>'))) {
/*      */           
/* 1292 */           b.e(this.au);
/*      */         } else {
/*      */           continue;
/*      */         } 
/* 1296 */       }  if (this.au.h == this.au.i) {
/* 1297 */         int i = this.au.i - offset;
/* 1298 */         System.arraycopy(this.au.g, offset, this.au.g, 0, i);
/* 1299 */         int count = this.au.a(i);
/* 1300 */         offset = 0;
/* 1301 */         if (count == -1) {
/*      */           break;
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1309 */     int length = this.au.h - offset;
/* 1310 */     String name = (length > 0) ? new String(this.au.g, offset, length) : null;
/*      */ 
/*      */ 
/*      */     
/* 1314 */     return name;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int a(XMLStringBuffer str, boolean content) throws IOException {
/*      */     String name;
/* 1320 */     str.clear();
/* 1321 */     str.append('&');
/* 1322 */     boolean endsWithSemicolon = false;
/*      */     while (true) {
/* 1324 */       int j = this.au.b();
/* 1325 */       if (j == 59) {
/* 1326 */         str.append(';');
/* 1327 */         endsWithSemicolon = true;
/*      */         break;
/*      */       } 
/* 1330 */       if (j == -1) {
/*      */         break;
/*      */       }
/* 1333 */       if (!aP.get(j) && j != 35) {
/* 1334 */         b.e(this.au);
/*      */         break;
/*      */       } 
/* 1337 */       str.append((char)j);
/*      */     } 
/*      */     
/* 1340 */     if (!endsWithSemicolon && 
/* 1341 */       this.P) {
/* 1342 */       this.ak.b("HTML1004", null);
/*      */     }
/*      */     
/* 1345 */     if (str.length == 1) {
/* 1346 */       if (content && this.ay != null && this.aC >= this.aD) {
/* 1347 */         this.aq = this.au.c();
/* 1348 */         this.ar = b.c(this.au);
/* 1349 */         this.as = b.d(this.au);
/* 1350 */         this.ay.characters((XMLString)str, f());
/*      */       } 
/* 1352 */       return -1;
/*      */     } 
/*      */ 
/*      */     
/* 1356 */     if (endsWithSemicolon) {
/* 1357 */       name = str.toString().substring(1, str.length - 1);
/*      */     } else {
/* 1359 */       name = str.toString().substring(1);
/*      */     } 
/* 1361 */     if (name.startsWith("#")) {
/* 1362 */       int value = -1;
/*      */       try {
/* 1364 */         if (name.startsWith("#x") || name.startsWith("#X")) {
/* 1365 */           value = Integer.parseInt(name.substring(2), 16);
/*      */         } else {
/*      */           
/* 1368 */           value = Integer.parseInt(name.substring(1));
/*      */         } 
/*      */         
/* 1371 */         if (this.T && this.aB) {
/* 1372 */           value = a(value);
/*      */         }
/* 1374 */         if (content && this.ay != null && this.aC >= this.aD) {
/* 1375 */           this.aq = this.au.c();
/* 1376 */           this.ar = b.c(this.au);
/* 1377 */           this.as = b.d(this.au);
/* 1378 */           if (this.Q) {
/* 1379 */             XMLResourceIdentifier id = h();
/* 1380 */             String encoding = null;
/* 1381 */             this.ay.startGeneralEntity(name, id, encoding, f());
/*      */           } 
/* 1383 */           str.clear();
/* 1384 */           str.append((char)value);
/* 1385 */           this.ay.characters((XMLString)str, f());
/* 1386 */           if (this.Q) {
/* 1387 */             this.ay.endGeneralEntity(name, f());
/*      */           }
/*      */         }
/*      */       
/* 1391 */       } catch (NumberFormatException numberFormatException) {
/* 1392 */         if (this.P) {
/* 1393 */           this.ak.c("HTML1005", new Object[] { name });
/*      */         }
/* 1395 */         if (content && this.ay != null && this.aC >= this.aD) {
/* 1396 */           this.aq = this.au.c();
/* 1397 */           this.ar = b.c(this.au);
/* 1398 */           this.as = b.d(this.au);
/* 1399 */           this.ay.characters((XMLString)str, f());
/*      */         } 
/*      */       } 
/* 1402 */       return value;
/*      */     } 
/*      */     
/* 1405 */     int i = e.a(name);
/*      */ 
/*      */ 
/*      */     
/* 1409 */     boolean invalidEntityInAttribute = (!content && !endsWithSemicolon && i > 256);
/* 1410 */     if (i == -1 || invalidEntityInAttribute) {
/* 1411 */       if (this.P) {
/* 1412 */         this.ak.b("HTML1006", new Object[] { name });
/*      */       }
/* 1414 */       if (content && this.ay != null && this.aC >= this.aD) {
/* 1415 */         this.aq = this.au.c();
/* 1416 */         this.ar = b.c(this.au);
/* 1417 */         this.as = b.d(this.au);
/* 1418 */         this.ay.characters((XMLString)str, f());
/*      */       } 
/* 1420 */       return -1;
/*      */     } 
/* 1422 */     if (content && this.ay != null && this.aC >= this.aD) {
/* 1423 */       this.aq = this.au.c();
/* 1424 */       this.ar = b.c(this.au);
/* 1425 */       this.as = b.d(this.au);
/* 1426 */       boolean notify = (this.S || (this.R && c(name)));
/* 1427 */       if (notify) {
/* 1428 */         XMLResourceIdentifier id = h();
/* 1429 */         String encoding = null;
/* 1430 */         this.ay.startGeneralEntity(name, id, encoding, f());
/*      */       } 
/* 1432 */       str.clear();
/* 1433 */       str.append((char)i);
/* 1434 */       this.ay.characters((XMLString)str, f());
/* 1435 */       if (notify) {
/* 1436 */         this.ay.endGeneralEntity(name, f());
/*      */       }
/*      */     } 
/* 1439 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean a(String s, boolean caseSensitive) throws IOException {
/* 1445 */     int length = (s != null) ? s.length() : 0;
/* 1446 */     for (int i = 0; i < length; i++) {
/* 1447 */       if (this.au.h == this.au.i) {
/* 1448 */         System.arraycopy(this.au.g, this.au.h - i, this.au.g, 0, i);
/* 1449 */         if (this.au.a(i) == -1) {
/* 1450 */           this.au.h = 0;
/* 1451 */           return false;
/*      */         } 
/*      */       } 
/* 1454 */       char c0 = s.charAt(i);
/* 1455 */       char c1 = b.f(this.au);
/* 1456 */       if (!caseSensitive) {
/* 1457 */         c0 = String.valueOf(c0).toUpperCase(Locale.ENGLISH).charAt(0);
/* 1458 */         c1 = String.valueOf(c1).toUpperCase(Locale.ENGLISH).charAt(0);
/*      */       } 
/* 1460 */       if (c0 != c1) {
/* 1461 */         b.a(this.au, i + 1);
/* 1462 */         return false;
/*      */       } 
/*      */     } 
/* 1465 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean c(boolean balance) throws IOException {
/* 1473 */     int depth = 1;
/* 1474 */     boolean slashgt = false;
/*      */     
/* 1476 */     while (this.au.h != this.au.i || 
/* 1477 */       this.au.a(0) != -1)
/*      */     
/*      */     { 
/*      */       
/* 1481 */       while (this.au.a())
/* 1482 */       { char c1 = b.f(this.au);
/* 1483 */         if (balance && c1 == '<') {
/* 1484 */           depth++; continue;
/*      */         } 
/* 1486 */         if (c1 == '>')
/* 1487 */         { depth--;
/* 1488 */           if (depth == 0)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1519 */             return slashgt; }  continue; }  if (c1 == '/') { if (this.au.h != this.au.i || this.au.a(0) != -1) { c1 = b.f(this.au); if (c1 == '>') { slashgt = true; depth--; if (depth == 0) return slashgt;  continue; }  b.e(this.au); continue; }  } else { if (c1 == '\r' || c1 == '\n') { b.e(this.au); e(); }  continue; }  return slashgt; }  }  return slashgt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean d() throws IOException {
/* 1527 */     boolean spaces = false;
/*      */     
/* 1529 */     while (this.au.h != this.au.i || 
/* 1530 */       this.au.a(0) != -1) {
/*      */ 
/*      */ 
/*      */       
/* 1534 */       char c1 = b.f(this.au);
/* 1535 */       if (!Character.isWhitespace(c1)) {
/* 1536 */         b.e(this.au);
/*      */         break;
/*      */       } 
/* 1539 */       spaces = true;
/* 1540 */       if (c1 == '\r' || c1 == '\n') {
/* 1541 */         b.e(this.au);
/* 1542 */         e();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1549 */     return spaces;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int e() throws IOException {
/* 1558 */     if (!this.au.a() && 
/* 1559 */       this.au.a(0) == -1)
/*      */     {
/*      */ 
/*      */       
/* 1563 */       return 0;
/*      */     }
/*      */     
/* 1566 */     char c1 = b.g(this.au);
/* 1567 */     int newlines = 0;
/* 1568 */     int offset = this.au.h;
/* 1569 */     if (c1 == '\n' || c1 == '\r') {
/*      */       do {
/* 1571 */         c1 = b.f(this.au);
/* 1572 */         if (c1 == '\r') {
/* 1573 */           newlines++;
/* 1574 */           if (this.au.h == this.au.i) {
/* 1575 */             offset = 0;
/* 1576 */             this.au.h = newlines;
/* 1577 */             if (this.au.a(newlines) == -1) {
/*      */               break;
/*      */             }
/*      */           } 
/* 1581 */           if (b.g(this.au) == '\n') {
/* 1582 */             this.au.h++;
/* 1583 */             this.au.f++;
/* 1584 */             offset++;
/*      */           }
/*      */         
/* 1587 */         } else if (c1 == '\n') {
/* 1588 */           newlines++;
/* 1589 */           if (this.au.h == this.au.i) {
/* 1590 */             offset = 0;
/* 1591 */             this.au.h = newlines;
/* 1592 */             if (this.au.a(newlines) == -1) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           
/* 1598 */           b.e(this.au);
/*      */           break;
/*      */         } 
/* 1601 */       } while (this.au.h < this.au.i - 1);
/* 1602 */       b.b(this.au, newlines);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1607 */     return newlines;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final Augmentations f() {
/* 1614 */     a augs = null;
/* 1615 */     if (this.O) {
/* 1616 */       this.aT.a(this.an, this.ao, this.ap, this.aq, this.ar, this.as);
/*      */ 
/*      */       
/* 1619 */       augs = this.aS;
/* 1620 */       augs.removeAllItems();
/* 1621 */       augs.putItem("http://cyberneko.org/html/features/augmentations", this.aT);
/*      */     } 
/* 1623 */     return augs;
/*      */   }
/*      */ 
/*      */   
/*      */   protected final Augmentations g() {
/* 1628 */     a augs = null;
/* 1629 */     if (this.O) {
/* 1630 */       augs = this.aS;
/* 1631 */       augs.removeAllItems();
/* 1632 */       augs.putItem("http://cyberneko.org/html/features/augmentations", N);
/*      */     } 
/* 1634 */     return augs;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final XMLResourceIdentifier h() {
/* 1640 */     this.aV.clear();
/* 1641 */     return (XMLResourceIdentifier)this.aV;
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
/*      */   protected static boolean c(String name) {
/* 1656 */     return (name.equals("amp") || name.equals("lt") || name.equals("gt") || name.equals("quot") || name.equals("apos"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface e
/*      */   {
/*      */     boolean a(boolean param1Boolean) throws IOException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class b
/*      */   {
/*      */     private Reader j;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String k;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final String a;
/*      */ 
/*      */ 
/*      */     
/*      */     public final String b;
/*      */ 
/*      */ 
/*      */     
/*      */     public final String c;
/*      */ 
/*      */ 
/*      */     
/*      */     public final String d;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static void a(b x0)
/*      */     {
/* 1703 */       x0.f(); } static String b(b x0) { return x0.k; } static int c(b x0) { return x0.i(); } static int d(b x0) { return x0.j(); } static void e(b x0) { x0.g(); } static char f(b x0) { return x0.e(); } static void a(b x0, int x1) { x0.b(x1); } static char g(b x0) { return x0.d(); } static void b(b x0, int x1) { x0.c(x1); } static void a(b x0, int x1, int x2, int x3) { x0.a(x1, x2, x3); } static void a(b x0, XMLStringBuffer x1, int x2, int x3, int x4) { x0.a(x1, x2, x3, x4); } static void h(b x0) { x0.h(); } static void a(b x0, InputStreamReader x1) { x0.a(x1); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1728 */     public final String e = "1.0";
/*      */ 
/*      */     
/* 1731 */     private int l = 1;
/*      */ 
/*      */     
/* 1734 */     private int m = 1;
/*      */ 
/*      */     
/* 1737 */     public int f = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1742 */     public char[] g = new char[2048];
/*      */ 
/*      */     
/* 1745 */     public int h = 0;
/*      */ 
/*      */     
/* 1748 */     public int i = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean n = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public b(Reader stream, String encoding, String publicId, String baseSystemId, String literalSystemId, String expandedSystemId) {
/* 1760 */       this.j = stream;
/* 1761 */       this.k = encoding;
/* 1762 */       this.a = publicId;
/* 1763 */       this.b = baseSystemId;
/* 1764 */       this.c = literalSystemId;
/* 1765 */       this.d = expandedSystemId;
/*      */     }
/*      */     
/*      */     private char d() {
/* 1769 */       return this.g[this.h];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private char e() {
/* 1777 */       this.f++;
/* 1778 */       this.m++;
/* 1779 */       return this.g[this.h++];
/*      */     }
/*      */     private void f() {
/*      */       try {
/* 1783 */         this.j.close();
/*      */       }
/* 1785 */       catch (IOException iOException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean a() {
/* 1794 */       return (this.h < this.i);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int a(int offset) throws IOException {
/* 1808 */       if (offset == this.g.length) {
/* 1809 */         int adjust = this.g.length / 4;
/* 1810 */         char[] array = new char[this.g.length + adjust];
/* 1811 */         System.arraycopy(this.g, 0, array, 0, this.i);
/* 1812 */         this.g = array;
/*      */       } 
/*      */       
/* 1815 */       int count = this.j.read(this.g, offset, this.g.length - offset);
/* 1816 */       if (count == -1) {
/* 1817 */         this.n = true;
/*      */       }
/* 1819 */       this.i = (count != -1) ? (count + offset) : offset;
/* 1820 */       this.h = offset;
/*      */ 
/*      */ 
/*      */       
/* 1824 */       return count;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int b() throws IOException {
/* 1832 */       if (this.h == this.i) {
/* 1833 */         if (this.n) {
/* 1834 */           return -1;
/*      */         }
/* 1836 */         if (a(0) == -1)
/*      */         {
/*      */ 
/*      */           
/* 1840 */           return -1;
/*      */         }
/*      */       } 
/* 1843 */       char c = this.g[this.h++];
/* 1844 */       this.f++;
/* 1845 */       this.m++;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1850 */       return c;
/*      */     }
/*      */ 
/*      */     
/*      */     private void a(String prefix) {
/* 1855 */       a(prefix, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void a(String prefix, String suffix) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void a(InputStreamReader inputStreamReader) {
/* 1906 */       this.j = inputStreamReader;
/* 1907 */       this.h = this.i = this.f = 0;
/* 1908 */       this.l = this.m = 1;
/* 1909 */       this.k = inputStreamReader.getEncoding();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void g() {
/* 1916 */       this.h--;
/* 1917 */       this.f--;
/* 1918 */       this.m--;
/*      */     }
/*      */     private void b(int i) {
/* 1921 */       this.h -= i;
/* 1922 */       this.f -= i;
/* 1923 */       this.m -= i;
/*      */     }
/*      */     
/*      */     private void h() {
/* 1927 */       this.l++;
/* 1928 */       this.m = 1;
/*      */     }
/*      */     
/*      */     private void c(int nbLines) {
/* 1932 */       this.l += nbLines;
/* 1933 */       this.m = 1;
/*      */     }
/*      */     
/*      */     public int c() {
/* 1937 */       return this.l;
/*      */     }
/*      */ 
/*      */     
/*      */     private void a(XMLStringBuffer buffer, int lineNumber, int columnNumber, int characterOffset) {
/* 1942 */       this.l = lineNumber;
/* 1943 */       this.m = columnNumber;
/* 1944 */       this.f = characterOffset;
/* 1945 */       this.g = buffer.ch;
/* 1946 */       this.h = buffer.offset;
/* 1947 */       this.i = buffer.length;
/*      */     }
/*      */     
/*      */     private int i() {
/* 1951 */       return this.m;
/*      */     }
/*      */ 
/*      */     
/*      */     private void a(int originalOffset, int originalColumnNumber, int originalCharacterOffset) {
/* 1956 */       this.h = originalOffset;
/* 1957 */       this.m = originalColumnNumber;
/* 1958 */       this.f = originalCharacterOffset;
/*      */     }
/*      */     
/*      */     private int j() {
/* 1962 */       return this.f;
/*      */     }
/*      */   }
/*      */   
/*      */   public class a implements e { private final QName a;
/*      */     private final XMLAttributesImpl b;
/*      */     private final h c;
/*      */     
/*      */     public a(h this$0) {
/* 1971 */       this.c = this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1981 */       this.a = new QName();
/*      */ 
/*      */       
/* 1984 */       this.b = new XMLAttributesImpl();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(boolean complete) throws IOException {
/*      */       while (true) {
/*      */         boolean bool;
/*      */         try {
/*      */           int c;
/* 1995 */           bool = false;
/* 1996 */           switch (this.c.ax) {
/*      */             case 0:
/* 1998 */               this.c.an = this.c.au.c();
/* 1999 */               this.c.ao = h.b.c(this.c.au);
/* 2000 */               this.c.ap = h.b.d(this.c.au);
/* 2001 */               c = this.c.au.b();
/* 2002 */               if (c == 60) {
/* 2003 */                 this.c.a((short)1);
/* 2004 */                 bool = true; break;
/*      */               } 
/* 2006 */               if (c == 38) {
/* 2007 */                 this.c.a(this.c.aG, true); break;
/*      */               } 
/* 2009 */               if (c == -1) {
/* 2010 */                 throw new EOFException();
/*      */               }
/*      */               
/* 2013 */               h.b.e(this.c.au);
/* 2014 */               a();
/*      */               break;
/*      */ 
/*      */             
/*      */             case 1:
/* 2019 */               c = this.c.au.b();
/* 2020 */               if (c == 33) {
/* 2021 */                 if (this.c.a("--", false)) {
/* 2022 */                   c();
/*      */                 }
/* 2024 */                 else if (this.c.a("[CDATA[", false)) {
/* 2025 */                   b();
/*      */                 }
/* 2027 */                 else if (this.c.a("DOCTYPE", false)) {
/* 2028 */                   this.c.b();
/*      */                 } else {
/*      */                   
/* 2031 */                   if (this.c.P) {
/* 2032 */                     this.c.ak.c("HTML1002", null);
/*      */                   }
/* 2034 */                   this.c.c(true);
/*      */                 }
/*      */               
/* 2037 */               } else if (c == 63) {
/* 2038 */                 d();
/*      */               }
/* 2040 */               else if (c == 47) {
/* 2041 */                 e();
/*      */               } else {
/* 2043 */                 if (c == -1) {
/* 2044 */                   if (this.c.P) {
/* 2045 */                     this.c.ak.c("HTML1003", null);
/*      */                   }
/* 2047 */                   if (this.c.ay != null && this.c.aC >= this.c.aD) {
/* 2048 */                     this.c.aG.clear();
/* 2049 */                     this.c.aG.append('<');
/* 2050 */                     this.c.ay.characters((XMLString)this.c.aG, null);
/*      */                   } 
/* 2052 */                   throw new EOFException();
/*      */                 } 
/*      */                 
/* 2055 */                 h.b.e(this.c.au);
/* 2056 */                 this.c.aC++;
/* 2057 */                 h.a(this.c)[0] = false;
/* 2058 */                 String ename = a(h.a(this.c));
/* 2059 */                 String enameLC = (ename == null) ? null : ename.toLowerCase();
/* 2060 */                 this.c.an = this.c.au.c();
/* 2061 */                 this.c.ao = h.b.c(this.c.au);
/* 2062 */                 this.c.ap = h.b.d(this.c.au);
/* 2063 */                 if ("script".equals(enameLC)) {
/* 2064 */                   f();
/*      */                 }
/* 2066 */                 else if (!this.c.ag && !this.c.af && "iframe".equals(enameLC)) {
/* 2067 */                   a("iframe");
/*      */                 }
/* 2069 */                 else if (!this.c.ad && "noscript".equals(enameLC)) {
/* 2070 */                   a("noscript");
/*      */                 }
/* 2072 */                 else if (!this.c.ae && "noframes".equals(enameLC)) {
/* 2073 */                   a("noframes");
/*      */                 }
/* 2075 */                 else if (ename != null && !h.a(this.c)[0] && d.a(enameLC).e() && (!ename.equalsIgnoreCase("TITLE") || d(enameLC))) {
/*      */ 
/*      */                   
/* 2078 */                   this.c.a(this.c.aF.a(ename));
/* 2079 */                   this.c.a((short)0);
/* 2080 */                   return true;
/*      */                 } 
/*      */               } 
/* 2083 */               this.c.a((short)0);
/*      */               break;
/*      */             
/*      */             case 10:
/* 2087 */               if (this.c.ay != null && this.c.aC >= this.c.aD) {
/*      */ 
/*      */ 
/*      */                 
/* 2091 */                 XMLLocator locator = this.c;
/* 2092 */                 String encoding = this.c.az;
/* 2093 */                 Augmentations augs = this.c.f();
/* 2094 */                 NamespaceSupport namespaceSupport = new NamespaceSupport();
/* 2095 */                 a.a().a(this.c.ay, locator, encoding, (NamespaceContext)namespaceSupport, augs);
/*      */               } 
/* 2097 */               if (this.c.ab && this.c.ay != null) {
/* 2098 */                 String root = (d.a((short)46)).g;
/* 2099 */                 root = h.a(root, this.c.ah);
/* 2100 */                 String pubid = this.c.al;
/* 2101 */                 String sysid = this.c.am;
/* 2102 */                 this.c.ay.doctypeDecl(root, pubid, sysid, this.c.g());
/*      */               } 
/*      */               
/* 2105 */               this.c.a((short)0);
/*      */               break;
/*      */             
/*      */             case 11:
/* 2109 */               if (this.c.ay != null && this.c.aC >= this.c.aD && complete) {
/*      */ 
/*      */ 
/*      */                 
/* 2113 */                 this.c.aq = this.c.au.c();
/* 2114 */                 this.c.ar = h.b.c(this.c.au);
/* 2115 */                 this.c.as = h.b.d(this.c.au);
/* 2116 */                 this.c.ay.endDocument(this.c.f());
/*      */               } 
/* 2118 */               return false;
/*      */             
/*      */             default:
/* 2121 */               throw new RuntimeException("unknown scanner state: " + this.c.ax);
/*      */           } 
/*      */ 
/*      */         
/* 2125 */         } catch (EOFException eOFException) {
/* 2126 */           if (this.c.av.empty()) {
/* 2127 */             this.c.a((short)11);
/*      */           } else {
/*      */             
/* 2130 */             this.c.au = this.c.av.pop();
/*      */           } 
/* 2132 */           bool = true;
/*      */         } 
/* 2134 */         if (!bool && !complete) {
/* 2135 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void a(String tagName) throws IOException {
/* 2145 */       XMLStringBuffer buffer = new XMLStringBuffer();
/* 2146 */       String end = "/" + tagName;
/* 2147 */       int lengthToScan = tagName.length() + 2;
/*      */       
/*      */       while (true) {
/* 2150 */         int c = this.c.au.b();
/* 2151 */         if (c == -1) {
/*      */           break;
/*      */         }
/* 2154 */         if (c == 60) {
/* 2155 */           String next = a(lengthToScan) + " ";
/* 2156 */           if (next.length() >= lengthToScan && end.equalsIgnoreCase(next.substring(0, end.length())) && ('>' == next.charAt(lengthToScan - 1) || Character.isWhitespace(next.charAt(lengthToScan - 1)))) {
/*      */             
/* 2158 */             h.b.e(this.c.au);
/*      */             break;
/*      */           } 
/*      */         } 
/* 2162 */         if (c == 13 || c == 10) {
/* 2163 */           h.b.e(this.c.au);
/* 2164 */           int newlines = this.c.e();
/* 2165 */           for (int i = 0; i < newlines; i++) {
/* 2166 */             buffer.append('\n');
/*      */           }
/*      */           continue;
/*      */         } 
/* 2170 */         buffer.append((char)c);
/*      */       } 
/*      */       
/* 2173 */       if (buffer.length > 0 && this.c.ay != null) {
/* 2174 */         this.c.aq = this.c.au.c();
/* 2175 */         this.c.ar = h.b.c(this.c.au);
/* 2176 */         this.c.as = h.b.d(this.c.au);
/* 2177 */         this.c.ay.characters((XMLString)buffer, this.c.f());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void f() throws IOException {
/* 2183 */       XMLStringBuffer buffer = new XMLStringBuffer();
/* 2184 */       boolean waitForEndComment = false;
/*      */       while (true) {
/* 2186 */         int c = this.c.au.b();
/* 2187 */         if (c == -1) {
/*      */           break;
/*      */         }
/* 2190 */         if (c == 45 && h.a(this.c, buffer, "<!-")) {
/*      */           
/* 2192 */           waitForEndComment = h.b(this.c);
/*      */         }
/* 2194 */         else if (!waitForEndComment && c == 60) {
/* 2195 */           String next = a(8) + " ";
/* 2196 */           if (next.length() >= 8 && "/script".equalsIgnoreCase(next.substring(0, 7)) && ('>' == next.charAt(7) || Character.isWhitespace(next.charAt(7)))) {
/*      */             
/* 2198 */             h.b.e(this.c.au);
/*      */             
/*      */             break;
/*      */           } 
/* 2202 */         } else if (c == 62 && h.a(this.c, buffer, "--")) {
/* 2203 */           waitForEndComment = false;
/*      */         } 
/*      */         
/* 2206 */         if (c == 13 || c == 10) {
/* 2207 */           h.b.e(this.c.au);
/* 2208 */           int newlines = this.c.e();
/* 2209 */           for (int i = 0; i < newlines; i++) {
/* 2210 */             buffer.append('\n');
/*      */           }
/*      */           continue;
/*      */         } 
/* 2214 */         buffer.append((char)c);
/*      */       } 
/*      */ 
/*      */       
/* 2218 */       if (this.c.V) {
/* 2219 */         h.a(buffer, "<!--", "-->");
/*      */       }
/* 2221 */       if (this.c.U) {
/* 2222 */         h.a(buffer, "<![CDATA[", "]]>");
/*      */       }
/*      */       
/* 2225 */       if (buffer.length > 0 && this.c.ay != null && this.c.aC >= this.c.aD) {
/*      */ 
/*      */ 
/*      */         
/* 2229 */         this.c.aq = this.c.au.c();
/* 2230 */         this.c.ar = h.b.c(this.c.au);
/* 2231 */         this.c.as = h.b.d(this.c.au);
/* 2232 */         this.c.ay.characters((XMLString)buffer, this.c.f());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String a(int len) throws IOException {
/* 2244 */       int originalOffset = this.c.au.h;
/* 2245 */       int originalColumnNumber = h.b.c(this.c.au);
/* 2246 */       int originalCharacterOffset = h.b.d(this.c.au);
/*      */       
/* 2248 */       char[] buff = new char[len];
/* 2249 */       int nbRead = 0;
/* 2250 */       for (nbRead = 0; nbRead < len; nbRead++) {
/*      */         
/* 2252 */         if (this.c.au.h == this.c.au.i) {
/* 2253 */           if (this.c.au.i == this.c.au.g.length) {
/* 2254 */             this.c.au.a(this.c.au.g.length);
/*      */           } else {
/*      */             break;
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 2261 */         int c = this.c.au.b();
/* 2262 */         if (c == -1) {
/*      */           break;
/*      */         }
/* 2265 */         buff[nbRead] = (char)c;
/*      */       } 
/* 2267 */       h.b.a(this.c.au, originalOffset, originalColumnNumber, originalCharacterOffset);
/* 2268 */       return new String(buff, 0, nbRead);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void a() throws IOException {
/*      */       int next;
/* 2280 */       this.c.aG.clear();
/*      */       do {
/* 2282 */         int newlines = this.c.e();
/* 2283 */         if (newlines == 0 && this.c.au.h == this.c.au.i) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2290 */         int offset = this.c.au.h - newlines;
/* 2291 */         for (int i = offset; i < this.c.au.h; i++) {
/* 2292 */           this.c.au.g[i] = '\n';
/*      */         }
/* 2294 */         while (this.c.au.a()) {
/* 2295 */           char c = h.b.f(this.c.au);
/* 2296 */           if (c == '<' || c == '&' || c == '\n' || c == '\r') {
/* 2297 */             h.b.e(this.c.au);
/*      */             break;
/*      */           } 
/*      */         } 
/* 2301 */         if (this.c.au.h > offset && this.c.ay != null && this.c.aC >= this.c.aD) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2307 */           this.c.aq = this.c.au.c();
/* 2308 */           this.c.ar = h.b.c(this.c.au);
/* 2309 */           this.c.as = h.b.d(this.c.au);
/* 2310 */           this.c.aG.append(this.c.au.g, offset, this.c.au.h - offset);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2316 */         boolean hasNext = (this.c.au.h < this.c.au.g.length);
/* 2317 */         next = hasNext ? h.b.g(this.c.au) : -1;
/*      */       }
/* 2319 */       while (next != 38 && next != 60 && next != -1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2325 */       if (this.c.aG.length != 0) {
/* 2326 */         this.c.ay.characters((XMLString)this.c.aG, this.c.f());
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void b() throws IOException {
/* 2336 */       this.c.aG.clear();
/* 2337 */       if (this.c.Z) {
/* 2338 */         if (this.c.ay != null && this.c.aC >= this.c.aD) {
/* 2339 */           this.c.aq = this.c.au.c();
/* 2340 */           this.c.ar = h.b.c(this.c.au);
/* 2341 */           this.c.as = h.b.d(this.c.au);
/*      */ 
/*      */ 
/*      */           
/* 2345 */           this.c.ay.startCDATA(this.c.f());
/*      */         } 
/*      */       } else {
/*      */         
/* 2349 */         this.c.aG.append("[CDATA[");
/*      */       } 
/* 2351 */       boolean eof = a(this.c.aG, ']');
/* 2352 */       if (!this.c.Z) {
/* 2353 */         this.c.aG.append("]]");
/*      */       }
/* 2355 */       if (this.c.ay != null && this.c.aC >= this.c.aD) {
/* 2356 */         this.c.aq = this.c.au.c();
/* 2357 */         this.c.ar = h.b.c(this.c.au);
/* 2358 */         this.c.as = h.b.d(this.c.au);
/* 2359 */         if (this.c.Z) {
/*      */ 
/*      */ 
/*      */           
/* 2363 */           this.c.ay.characters((XMLString)this.c.aG, this.c.f());
/*      */ 
/*      */ 
/*      */           
/* 2367 */           this.c.ay.endCDATA(this.c.f());
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 2373 */           this.c.ay.comment((XMLString)this.c.aG, this.c.f());
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2379 */       if (eof) {
/* 2380 */         throw new EOFException();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void c() throws IOException {
/* 2389 */       this.c.aq = this.c.au.c();
/* 2390 */       this.c.ar = h.b.c(this.c.au);
/* 2391 */       this.c.as = h.b.d(this.c.au);
/* 2392 */       XMLStringBuffer buffer = new XMLStringBuffer();
/* 2393 */       boolean eof = a(buffer, '-');
/*      */       
/* 2395 */       if (eof) {
/* 2396 */         h.b.a(this.c.au, buffer, this.c.aq, this.c.ar, this.c.as);
/* 2397 */         buffer = new XMLStringBuffer();
/*      */         while (true) {
/* 2399 */           int c = this.c.au.b();
/* 2400 */           if (c == -1) {
/* 2401 */             if (this.c.P) {
/* 2402 */               this.c.ak.c("HTML1007", null);
/*      */             }
/* 2404 */             eof = true;
/*      */             break;
/*      */           } 
/* 2407 */           if (c != 62) {
/* 2408 */             buffer.append((char)c);
/*      */             continue;
/*      */           } 
/* 2411 */           if (c == 10 || c == 13) {
/* 2412 */             h.b.e(this.c.au);
/* 2413 */             int newlines = this.c.e();
/* 2414 */             for (int i = 0; i < newlines; i++) {
/* 2415 */               buffer.append('\n');
/*      */             }
/*      */             continue;
/*      */           } 
/* 2419 */           eof = false;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2423 */       if (this.c.ay != null && this.c.aC >= this.c.aD) {
/*      */ 
/*      */ 
/*      */         
/* 2427 */         this.c.aq = this.c.au.c();
/* 2428 */         this.c.ar = h.b.c(this.c.au);
/* 2429 */         this.c.as = h.b.d(this.c.au);
/* 2430 */         this.c.ay.comment((XMLString)buffer, this.c.f());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2435 */       if (eof) {
/* 2436 */         throw new EOFException();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean a(XMLStringBuffer buffer, char cend) throws IOException {
/* 2443 */       int c = -1;
/*      */       while (true) {
/* 2445 */         c = this.c.au.b();
/* 2446 */         if (c == cend) {
/* 2447 */           int count = 1;
/*      */           while (true) {
/* 2449 */             c = this.c.au.b();
/* 2450 */             if (c == cend) {
/* 2451 */               count++;
/*      */               continue;
/*      */             } 
/*      */             break;
/*      */           } 
/* 2456 */           if (c == -1) {
/* 2457 */             if (this.c.P) {
/* 2458 */               this.c.ak.c("HTML1007", null);
/*      */             }
/*      */             break;
/*      */           } 
/* 2462 */           if (count < 2) {
/* 2463 */             buffer.append(cend);
/*      */             
/* 2465 */             h.b.e(this.c.au);
/*      */             
/*      */             continue;
/*      */           } 
/* 2469 */           if (c != 62) {
/* 2470 */             for (int j = 0; j < count; j++) {
/* 2471 */               buffer.append(cend);
/*      */             }
/* 2473 */             h.b.e(this.c.au);
/*      */             continue;
/*      */           } 
/* 2476 */           for (int i = 0; i < count - 2; i++) {
/* 2477 */             buffer.append(cend);
/*      */           }
/*      */           break;
/*      */         } 
/* 2481 */         if (c == 10 || c == 13) {
/* 2482 */           h.b.e(this.c.au);
/* 2483 */           int newlines = this.c.e();
/* 2484 */           for (int i = 0; i < newlines; i++) {
/* 2485 */             buffer.append('\n');
/*      */           }
/*      */           continue;
/*      */         } 
/* 2489 */         if (c == -1) {
/* 2490 */           if (this.c.P) {
/* 2491 */             this.c.ak.c("HTML1007", null);
/*      */           }
/*      */           break;
/*      */         } 
/* 2495 */         buffer.append((char)c);
/*      */       } 
/* 2497 */       return (c == -1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void d() throws IOException {
/* 2505 */       if (this.c.P) {
/* 2506 */         this.c.ak.b("HTML1008", null);
/*      */       }
/*      */ 
/*      */       
/* 2510 */       String target = this.c.b(true);
/* 2511 */       if (target != null && !target.equalsIgnoreCase("xml")) {
/*      */         while (true) {
/* 2513 */           int c = this.c.au.b();
/* 2514 */           if (c == 13 || c == 10) {
/* 2515 */             if (c == 13) {
/* 2516 */               c = this.c.au.b();
/* 2517 */               if (c != 10) {
/* 2518 */                 this.c.au.h--;
/* 2519 */                 this.c.au.f--;
/*      */               } 
/*      */             } 
/* 2522 */             h.b.h(this.c.au);
/*      */             continue;
/*      */           } 
/* 2525 */           if (c == -1) {
/*      */             break;
/*      */           }
/* 2528 */           if (c != 32 && c != 9) {
/* 2529 */             h.b.e(this.c.au);
/*      */             break;
/*      */           } 
/*      */         } 
/* 2533 */         this.c.aG.clear();
/*      */         while (true) {
/* 2535 */           int c = this.c.au.b();
/* 2536 */           if (c == 63 || c == 47) {
/* 2537 */             char c0 = (char)c;
/* 2538 */             c = this.c.au.b();
/* 2539 */             if (c == 62) {
/*      */               break;
/*      */             }
/* 2542 */             this.c.aG.append(c0);
/* 2543 */             h.b.e(this.c.au);
/*      */             continue;
/*      */           } 
/* 2546 */           if (c == 13 || c == 10) {
/* 2547 */             this.c.aG.append('\n');
/* 2548 */             if (c == 13) {
/* 2549 */               c = this.c.au.b();
/* 2550 */               if (c != 10) {
/* 2551 */                 this.c.au.h--;
/* 2552 */                 this.c.au.f--;
/*      */               } 
/*      */             } 
/* 2555 */             h.b.h(this.c.au);
/*      */             continue;
/*      */           } 
/* 2558 */           if (c == -1) {
/*      */             break;
/*      */           }
/*      */           
/* 2562 */           this.c.aG.append((char)c);
/*      */         } 
/*      */         
/* 2565 */         XMLStringBuffer xMLStringBuffer = this.c.aG;
/* 2566 */         if (this.c.ay != null) {
/* 2567 */           this.c.aq = this.c.au.c();
/* 2568 */           this.c.ar = h.b.c(this.c.au);
/* 2569 */           this.c.as = h.b.d(this.c.au);
/* 2570 */           this.c.ay.processingInstruction(target, (XMLString)xMLStringBuffer, this.c.f());
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2576 */         int beginLineNumber = this.c.an;
/* 2577 */         int beginColumnNumber = this.c.ao;
/* 2578 */         int beginCharacterOffset = this.c.ap;
/* 2579 */         this.b.removeAllAttributes();
/* 2580 */         int aindex = 0;
/* 2581 */         while (a(this.b)) {
/*      */           
/* 2583 */           if (this.b.getValue(aindex).length() == 0) {
/* 2584 */             this.b.removeAttributeAt(aindex);
/*      */             continue;
/*      */           } 
/* 2587 */           this.b.getName(aindex, this.a);
/* 2588 */           this.a.rawname = this.a.rawname.toLowerCase();
/* 2589 */           this.b.setName(aindex, this.a);
/* 2590 */           aindex++;
/*      */         } 
/*      */         
/* 2593 */         if (this.c.ay != null) {
/* 2594 */           String version = this.b.getValue("version");
/* 2595 */           String encoding = this.b.getValue("encoding");
/* 2596 */           String standalone = this.b.getValue("standalone");
/*      */ 
/*      */ 
/*      */           
/* 2600 */           boolean xmlDeclNow = (this.c.Y || !c(encoding));
/* 2601 */           if (xmlDeclNow) {
/* 2602 */             this.c.an = beginLineNumber;
/* 2603 */             this.c.ao = beginColumnNumber;
/* 2604 */             this.c.ap = beginCharacterOffset;
/* 2605 */             this.c.aq = this.c.au.c();
/* 2606 */             this.c.ar = h.b.c(this.c.au);
/* 2607 */             this.c.as = h.b.d(this.c.au);
/* 2608 */             this.c.ay.xmlDecl(version, encoding, standalone, this.c.f());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String a(boolean[] empty) throws IOException {
/* 2626 */       String ename = this.c.b(true);
/* 2627 */       int length = (ename != null) ? ename.length() : 0;
/* 2628 */       int c = (length > 0) ? ename.charAt(0) : -1;
/* 2629 */       if (length == 0 || ((c < 97 || c > 122) && (c < 65 || c > 90))) {
/* 2630 */         if (this.c.P) {
/* 2631 */           this.c.ak.c("HTML1009", null);
/*      */         }
/* 2633 */         if (this.c.ay != null && this.c.aC >= this.c.aD) {
/* 2634 */           this.c.aG.clear();
/* 2635 */           this.c.aG.append('<');
/* 2636 */           if (length > 0) {
/* 2637 */             this.c.aG.append(ename);
/*      */           }
/* 2639 */           this.c.ay.characters((XMLString)this.c.aG, null);
/*      */         } 
/* 2641 */         return null;
/*      */       } 
/* 2643 */       ename = h.a(ename, this.c.ah);
/* 2644 */       this.b.removeAllAttributes();
/* 2645 */       int beginLineNumber = this.c.an;
/* 2646 */       int beginColumnNumber = this.c.ao;
/* 2647 */       int beginCharacterOffset = this.c.ap;
/* 2648 */       while (a(this.b, empty));
/*      */ 
/*      */       
/* 2651 */       this.c.an = beginLineNumber;
/* 2652 */       this.c.ao = beginColumnNumber;
/* 2653 */       this.c.ap = beginCharacterOffset;
/* 2654 */       if (this.c.at != null && this.c.aD == -1) {
/* 2655 */         if (ename.equalsIgnoreCase("META") && !this.c.Y) {
/*      */ 
/*      */ 
/*      */           
/* 2659 */           String httpEquiv = h.a((XMLAttributes)this.b, "http-equiv");
/* 2660 */           if (httpEquiv != null && httpEquiv.equalsIgnoreCase("content-type")) {
/*      */ 
/*      */ 
/*      */             
/* 2664 */             String content = h.a((XMLAttributes)this.b, "content");
/* 2665 */             if (content != null) {
/* 2666 */               content = b(content);
/* 2667 */               int index1 = content.toLowerCase().indexOf("charset=");
/* 2668 */               if (index1 != -1) {
/* 2669 */                 int index2 = content.indexOf(';', index1);
/* 2670 */                 String charset = (index2 != -1) ? content.substring(index1 + 8, index2) : content.substring(index1 + 8);
/* 2671 */                 c(charset);
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/* 2676 */             String metaCharset = h.a((XMLAttributes)this.b, "charset");
/* 2677 */             if (metaCharset != null) {
/* 2678 */               c(metaCharset);
/*      */             }
/*      */           }
/*      */         
/* 2682 */         } else if (ename.equalsIgnoreCase("BODY")) {
/* 2683 */           this.c.at.b();
/* 2684 */           this.c.at = null;
/*      */         } else {
/*      */           
/* 2687 */           d.a element = d.a(ename);
/* 2688 */           if (element.j != null && element.j.length > 0 && 
/* 2689 */             (element.j[0]).f == 14) {
/* 2690 */             this.c.at.b();
/* 2691 */             this.c.at = null;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 2696 */       if (this.c.ay != null && this.c.aC >= this.c.aD) {
/* 2697 */         this.a.setValues(null, ename, ename, null);
/*      */ 
/*      */ 
/*      */         
/* 2701 */         this.c.aq = this.c.au.c();
/* 2702 */         this.c.ar = h.b.c(this.c.au);
/* 2703 */         this.c.as = h.b.d(this.c.au);
/* 2704 */         if (empty[0]) {
/* 2705 */           this.c.ay.emptyElement(this.a, (XMLAttributes)this.b, this.c.f());
/*      */         } else {
/*      */           
/* 2708 */           this.c.ay.startElement(this.a, (XMLAttributes)this.b, this.c.f());
/*      */         } 
/*      */       } 
/* 2711 */       return ename;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String b(String content) {
/* 2718 */       StringBuffer sb = null;
/* 2719 */       for (int i = content.length() - 1; i >= 0; i--) {
/* 2720 */         if (Character.isWhitespace(content.charAt(i))) {
/* 2721 */           if (sb == null) {
/* 2722 */             sb = new StringBuffer(content);
/*      */           }
/* 2724 */           sb.deleteCharAt(i);
/*      */         } 
/*      */       } 
/* 2727 */       return (sb == null) ? content : sb.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean c(String charset) {
/* 2736 */       if (charset == null || this.c.at == null) {
/* 2737 */         return false;
/*      */       }
/* 2739 */       charset = charset.trim();
/* 2740 */       boolean encodingChanged = false;
/*      */       try {
/* 2742 */         String ianaEncoding = charset;
/* 2743 */         String javaEncoding = EncodingMap.getIANA2JavaMapping(ianaEncoding.toUpperCase(Locale.ENGLISH));
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2748 */         if (javaEncoding == null) {
/* 2749 */           javaEncoding = ianaEncoding;
/* 2750 */           if (this.c.P) {
/* 2751 */             this.c.ak.c("HTML1001", new Object[] { ianaEncoding });
/*      */           }
/*      */         } 
/*      */         
/* 2755 */         if (!javaEncoding.equals(this.c.aA)) {
/* 2756 */           if (!this.c.b(javaEncoding, this.c.aA)) {
/* 2757 */             if (this.c.P) {
/* 2758 */               this.c.ak.c("HTML1015", new Object[] { javaEncoding, this.c.aA });
/*      */             }
/*      */           }
/*      */           else {
/*      */             
/* 2763 */             this.c.aB = (ianaEncoding == null || ianaEncoding.toUpperCase(Locale.ENGLISH).startsWith("ISO-8859") || ianaEncoding.equalsIgnoreCase(this.c.aj));
/*      */ 
/*      */             
/* 2766 */             this.c.aA = javaEncoding;
/* 2767 */             h.b.a(this.c.au, new InputStreamReader(this.c.at, javaEncoding));
/* 2768 */             this.c.at.a();
/* 2769 */             this.c.aD = this.c.aC;
/* 2770 */             this.c.aC = 0;
/* 2771 */             encodingChanged = true;
/*      */           }
/*      */         
/*      */         }
/* 2775 */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 2776 */         if (this.c.P) {
/* 2777 */           this.c.ak.c("HTML1010", new Object[] { charset });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2782 */         this.c.at.b();
/* 2783 */         this.c.at = null;
/*      */       } 
/* 2785 */       return encodingChanged;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean a(XMLAttributesImpl attributes, boolean[] empty) throws IOException {
/* 2799 */       return a(attributes, empty, '/');
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean a(XMLAttributesImpl attributes) throws IOException {
/* 2809 */       return a(attributes, h.a(this.c), '?');
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean a(XMLAttributesImpl attributes, boolean[] empty, char endc) throws IOException {
/* 2825 */       boolean skippedSpaces = this.c.d();
/* 2826 */       this.c.an = this.c.au.c();
/* 2827 */       this.c.ao = h.b.c(this.c.au);
/* 2828 */       this.c.ap = h.b.d(this.c.au);
/* 2829 */       int c = this.c.au.b();
/* 2830 */       if (c == -1) {
/* 2831 */         if (this.c.P) {
/* 2832 */           this.c.ak.c("HTML1007", null);
/*      */         }
/* 2834 */         return false;
/*      */       } 
/* 2836 */       if (c == 62) {
/* 2837 */         return false;
/*      */       }
/* 2839 */       if (c == 60) {
/* 2840 */         h.b.e(this.c.au);
/* 2841 */         return false;
/*      */       } 
/* 2843 */       h.b.e(this.c.au);
/* 2844 */       String aname = this.c.b(false);
/* 2845 */       if (aname == null) {
/* 2846 */         if (this.c.P) {
/* 2847 */           this.c.ak.c("HTML1011", null);
/*      */         }
/* 2849 */         empty[0] = this.c.c(false);
/* 2850 */         return false;
/*      */       } 
/* 2852 */       if (!skippedSpaces && this.c.P) {
/* 2853 */         this.c.ak.c("HTML1013", new Object[] { aname });
/*      */       }
/* 2855 */       aname = h.a(aname, this.c.ai);
/* 2856 */       this.c.d();
/* 2857 */       c = this.c.au.b();
/* 2858 */       if (c == -1) {
/* 2859 */         if (this.c.P) {
/* 2860 */           this.c.ak.c("HTML1007", null);
/*      */         }
/* 2862 */         throw new EOFException();
/*      */       } 
/* 2864 */       if (c == 47 || c == 62) {
/* 2865 */         this.a.setValues(null, aname, aname, null);
/* 2866 */         attributes.addAttribute(this.a, "CDATA", "");
/* 2867 */         attributes.setSpecified(attributes.getLength() - 1, true);
/* 2868 */         if (this.c.O) {
/* 2869 */           a((XMLAttributes)attributes, attributes.getLength() - 1);
/*      */         }
/* 2871 */         if (c == 47) {
/* 2872 */           h.b.e(this.c.au);
/* 2873 */           empty[0] = this.c.c(false);
/*      */         } 
/* 2875 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2894 */       if (c == 61) {
/* 2895 */         this.c.d();
/* 2896 */         c = this.c.au.b();
/* 2897 */         if (c == -1) {
/* 2898 */           if (this.c.P) {
/* 2899 */             this.c.ak.c("HTML1007", null);
/*      */           }
/* 2901 */           throw new EOFException();
/*      */         } 
/*      */         
/* 2904 */         if (c == 62) {
/* 2905 */           this.a.setValues(null, aname, aname, null);
/* 2906 */           attributes.addAttribute(this.a, "CDATA", "");
/* 2907 */           attributes.setSpecified(attributes.getLength() - 1, true);
/* 2908 */           if (this.c.O) {
/* 2909 */             a((XMLAttributes)attributes, attributes.getLength() - 1);
/*      */           }
/* 2911 */           return false;
/*      */         } 
/* 2913 */         this.c.aG.clear();
/* 2914 */         h.c(this.c).clear();
/* 2915 */         if (c != 39 && c != 34) {
/* 2916 */           h.b.e(this.c.au);
/*      */           while (true) {
/* 2918 */             c = this.c.au.b();
/*      */             
/* 2920 */             if (Character.isWhitespace((char)c) || c == 62) {
/*      */               
/* 2922 */               h.b.e(this.c.au);
/*      */               break;
/*      */             } 
/* 2925 */             if (c == -1) {
/* 2926 */               if (this.c.P) {
/* 2927 */                 this.c.ak.c("HTML1007", null);
/*      */               }
/* 2929 */               throw new EOFException();
/*      */             } 
/* 2931 */             if (c == 38) {
/* 2932 */               int ce = this.c.a(h.d(this.c), false);
/* 2933 */               if (ce != -1) {
/* 2934 */                 this.c.aG.append((char)ce);
/*      */               } else {
/*      */                 
/* 2937 */                 this.c.aG.append((XMLString)h.d(this.c));
/*      */               } 
/* 2939 */               h.c(this.c).append((XMLString)h.d(this.c));
/*      */               continue;
/*      */             } 
/* 2942 */             this.c.aG.append((char)c);
/* 2943 */             h.c(this.c).append((char)c);
/*      */           } 
/*      */           
/* 2946 */           this.a.setValues(null, aname, aname, null);
/* 2947 */           String str = this.c.aG.toString();
/* 2948 */           attributes.addAttribute(this.a, "CDATA", str);
/*      */           
/* 2950 */           int i = attributes.getLength() - 1;
/* 2951 */           attributes.setSpecified(i, true);
/* 2952 */           attributes.setNonNormalizedValue(i, h.c(this.c).toString());
/* 2953 */           if (this.c.O) {
/* 2954 */             a((XMLAttributes)attributes, attributes.getLength() - 1);
/*      */           }
/* 2956 */           return true;
/*      */         } 
/* 2958 */         char quote = (char)c;
/* 2959 */         boolean isStart = true;
/* 2960 */         boolean prevSpace = false;
/*      */         do {
/* 2962 */           boolean acceptSpace = (!this.c.ac || (!isStart && !prevSpace));
/* 2963 */           c = this.c.au.b();
/* 2964 */           if (c == -1) {
/* 2965 */             if (this.c.P) {
/* 2966 */               this.c.ak.c("HTML1007", null);
/*      */             }
/*      */             
/*      */             break;
/*      */           } 
/* 2971 */           if (c == 38) {
/* 2972 */             isStart = false;
/* 2973 */             int ce = this.c.a(h.d(this.c), false);
/* 2974 */             if (ce != -1) {
/* 2975 */               this.c.aG.append((char)ce);
/*      */             } else {
/*      */               
/* 2978 */               this.c.aG.append((XMLString)h.d(this.c));
/*      */             } 
/* 2980 */             h.c(this.c).append((XMLString)h.d(this.c));
/*      */           }
/* 2982 */           else if (c == 32 || c == 9) {
/* 2983 */             if (acceptSpace) {
/* 2984 */               this.c.aG.append(this.c.ac ? 32 : (char)c);
/*      */             }
/* 2986 */             h.c(this.c).append((char)c);
/*      */           }
/* 2988 */           else if (c == 13 || c == 10) {
/* 2989 */             if (c == 13) {
/* 2990 */               int c2 = this.c.au.b();
/* 2991 */               if (c2 != 10) {
/* 2992 */                 h.b.e(this.c.au);
/*      */               } else {
/*      */                 
/* 2995 */                 h.c(this.c).append('\r');
/* 2996 */                 c = c2;
/*      */               } 
/*      */             } 
/* 2999 */             if (acceptSpace) {
/* 3000 */               this.c.aG.append(this.c.ac ? 32 : 10);
/*      */             }
/* 3002 */             h.b.h(this.c.au);
/* 3003 */             h.c(this.c).append((char)c);
/*      */           }
/* 3005 */           else if (c != quote) {
/* 3006 */             isStart = false;
/* 3007 */             this.c.aG.append((char)c);
/* 3008 */             h.c(this.c).append((char)c);
/*      */           } 
/* 3010 */           prevSpace = (c == 32 || c == 9 || c == 13 || c == 10);
/* 3011 */           isStart = (isStart && prevSpace);
/* 3012 */         } while (c != quote);
/*      */         
/* 3014 */         if (this.c.ac && this.c.aG.length > 0)
/*      */         {
/* 3016 */           if (this.c.aG.ch[this.c.aG.length - 1] == ' ') {
/* 3017 */             this.c.aG.length--;
/*      */           }
/*      */         }
/*      */         
/* 3021 */         this.a.setValues(null, aname, aname, null);
/* 3022 */         String avalue = this.c.aG.toString();
/* 3023 */         attributes.addAttribute(this.a, "CDATA", avalue);
/*      */         
/* 3025 */         int lastattr = attributes.getLength() - 1;
/* 3026 */         attributes.setSpecified(lastattr, true);
/* 3027 */         attributes.setNonNormalizedValue(lastattr, h.c(this.c).toString());
/* 3028 */         if (this.c.O) {
/* 3029 */           a((XMLAttributes)attributes, attributes.getLength() - 1);
/*      */         }
/*      */       } else {
/*      */         
/* 3033 */         this.a.setValues(null, aname, aname, null);
/* 3034 */         attributes.addAttribute(this.a, "CDATA", "");
/* 3035 */         attributes.setSpecified(attributes.getLength() - 1, true);
/* 3036 */         h.b.e(this.c.au);
/* 3037 */         if (this.c.O) {
/* 3038 */           a((XMLAttributes)attributes, attributes.getLength() - 1);
/*      */         }
/*      */       } 
/* 3041 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(XMLAttributes attributes, int index) {
/* 3046 */       this.c.aq = this.c.au.c();
/* 3047 */       this.c.ar = h.b.c(this.c.au);
/* 3048 */       this.c.as = h.b.d(this.c.au);
/* 3049 */       h.c locationItem = new h.c();
/* 3050 */       locationItem.a(this.c.an, this.c.ao, this.c.ap, this.c.aq, this.c.ar, this.c.as);
/*      */ 
/*      */       
/* 3053 */       Augmentations augs = attributes.getAugmentations(index);
/* 3054 */       augs.putItem("http://cyberneko.org/html/features/augmentations", locationItem);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void e() throws IOException {
/* 3059 */       String ename = this.c.b(true);
/* 3060 */       if (this.c.P && ename == null) {
/* 3061 */         this.c.ak.c("HTML1012", null);
/*      */       }
/* 3063 */       this.c.c(false);
/* 3064 */       if (ename != null) {
/* 3065 */         ename = h.a(ename, this.c.ah);
/* 3066 */         if (this.c.ay != null && this.c.aC >= this.c.aD) {
/* 3067 */           this.a.setValues(null, ename, ename, null);
/*      */ 
/*      */ 
/*      */           
/* 3071 */           this.c.aq = this.c.au.c();
/* 3072 */           this.c.ar = h.b.c(this.c.au);
/* 3073 */           this.c.as = h.b.d(this.c.au);
/* 3074 */           this.c.ay.endElement(this.a, this.c.f());
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean d(String ename) {
/* 3087 */       String content = new String(this.c.au.g, this.c.au.h, this.c.au.i - this.c.au.h); return 
/*      */         
/* 3089 */         (content.toLowerCase().indexOf("</" + ename.toLowerCase() + ">") != -1);
/*      */     } }
/*      */   
/*      */   public class f implements e { protected String a;
/*      */     protected boolean b;
/*      */     protected boolean c;
/*      */     protected boolean d;
/*      */     private final QName e;
/*      */     private final XMLStringBuffer f;
/*      */     private final h g;
/*      */     
/*      */     public f(h this$0) {
/* 3101 */       this.g = this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3123 */       this.e = new QName();
/*      */ 
/*      */       
/* 3126 */       this.f = new XMLStringBuffer();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public h.e a(String ename) {
/* 3134 */       this.a = ename;
/* 3135 */       this.b = this.a.equalsIgnoreCase("STYLE");
/* 3136 */       this.c = this.a.equalsIgnoreCase("TEXTAREA");
/* 3137 */       this.d = this.a.equalsIgnoreCase("TITLE");
/* 3138 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(boolean complete) throws IOException {
/*      */       while (true) {
/*      */         boolean next;
/*      */         try {
/*      */           int c, delimiter, i;
/* 3150 */           next = false;
/* 3151 */           switch (this.g.ax) {
/*      */             case 0:
/* 3153 */               this.g.an = this.g.au.c();
/* 3154 */               this.g.ao = h.b.c(this.g.au);
/* 3155 */               this.g.ap = h.b.d(this.g.au);
/* 3156 */               c = this.g.au.b();
/* 3157 */               if (c == 60) {
/* 3158 */                 this.g.a((short)1);
/*      */                 break;
/*      */               } 
/* 3161 */               if (c == 38) {
/* 3162 */                 if (this.c || this.d) {
/* 3163 */                   this.g.a(this.f, true);
/*      */                   break;
/*      */                 } 
/* 3166 */                 this.f.clear();
/* 3167 */                 this.f.append('&');
/*      */               } else {
/* 3169 */                 if (c == -1) {
/* 3170 */                   if (this.g.P) {
/* 3171 */                     this.g.ak.c("HTML1007", null);
/*      */                   }
/* 3173 */                   throw new EOFException();
/*      */                 } 
/*      */                 
/* 3176 */                 h.b.e(this.g.au);
/* 3177 */                 this.f.clear();
/*      */               } 
/* 3179 */               a(this.f, -1);
/*      */               break;
/*      */             
/*      */             case 1:
/* 3183 */               delimiter = -1;
/* 3184 */               i = this.g.au.b();
/* 3185 */               if (i == 47) {
/* 3186 */                 String ename = this.g.b(true);
/* 3187 */                 if (ename != null) {
/* 3188 */                   if (ename.equalsIgnoreCase(this.a)) {
/* 3189 */                     if (this.g.au.b() == 62) {
/* 3190 */                       ename = h.a(ename, this.g.ah);
/* 3191 */                       if (this.g.ay != null && this.g.aC >= this.g.aD) {
/* 3192 */                         this.e.setValues(null, ename, ename, null);
/*      */ 
/*      */ 
/*      */                         
/* 3196 */                         this.g.aq = this.g.au.c();
/* 3197 */                         this.g.ar = h.b.c(this.g.au);
/* 3198 */                         this.g.as = h.b.d(this.g.au);
/* 3199 */                         this.g.ay.endElement(this.e, this.g.f());
/*      */                       } 
/* 3201 */                       this.g.a(this.g.aE);
/* 3202 */                       this.g.a((short)0);
/* 3203 */                       return true;
/*      */                     } 
/* 3205 */                     h.b.e(this.g.au);
/*      */                   } 
/* 3207 */                   this.f.clear();
/* 3208 */                   this.f.append("</");
/* 3209 */                   this.f.append(ename);
/*      */                 } else {
/*      */                   
/* 3212 */                   this.f.clear();
/* 3213 */                   this.f.append("</");
/*      */                 } 
/*      */               } else {
/*      */                 
/* 3217 */                 this.f.clear();
/* 3218 */                 this.f.append('<');
/* 3219 */                 this.f.append((char)i);
/*      */               } 
/* 3221 */               a(this.f, delimiter);
/* 3222 */               this.g.a((short)0);
/*      */               break;
/*      */           } 
/*      */ 
/*      */         
/* 3227 */         } catch (EOFException eOFException) {
/* 3228 */           this.g.a(this.g.aE);
/* 3229 */           if (this.g.av.empty()) {
/* 3230 */             this.g.a((short)11);
/*      */           } else {
/*      */             
/* 3233 */             this.g.au = this.g.av.pop();
/* 3234 */             this.g.a((short)0);
/*      */           } 
/* 3236 */           return true;
/*      */         } 
/*      */         
/* 3239 */         if (!next && !complete) {
/* 3240 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void a(XMLStringBuffer buffer, int delimiter) throws IOException {
/*      */       while (true) {
/* 3255 */         int c = this.g.au.b();
/*      */         
/* 3257 */         if (c == -1 || c == 60 || c == 38) {
/* 3258 */           if (c != -1) {
/* 3259 */             h.b.e(this.g.au);
/*      */           }
/*      */           
/*      */           break;
/*      */         } 
/* 3264 */         if (c == 13 || c == 10) {
/* 3265 */           h.b.e(this.g.au);
/* 3266 */           int newlines = this.g.e();
/* 3267 */           for (int i = 0; i < newlines; i++) {
/* 3268 */             buffer.append('\n');
/*      */           }
/*      */           continue;
/*      */         } 
/* 3272 */         buffer.append((char)c);
/* 3273 */         if (c == 10) {
/* 3274 */           h.b.h(this.g.au);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 3279 */       if (this.b) {
/* 3280 */         if (this.g.X) {
/* 3281 */           h.a(buffer, "<!--", "-->");
/*      */         }
/* 3283 */         if (this.g.W) {
/* 3284 */           h.a(buffer, "<![CDATA[", "]]>");
/*      */         }
/*      */       } 
/*      */       
/* 3288 */       if (buffer.length > 0 && this.g.ay != null && this.g.aC >= this.g.aD) {
/*      */ 
/*      */ 
/*      */         
/* 3292 */         this.g.aq = this.g.au.c();
/* 3293 */         this.g.ar = h.b.c(this.g.au);
/* 3294 */         this.g.as = h.b.d(this.g.au);
/* 3295 */         this.g.ay.characters((XMLString)buffer, this.g.f());
/*      */       } 
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class d
/*      */     extends FilterInputStream
/*      */   {
/*      */     private static final boolean i = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean a = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean b = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean c = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3354 */     protected byte[] d = new byte[1024];
/*      */ 
/*      */     
/* 3357 */     protected int e = 0;
/*      */ 
/*      */     
/* 3360 */     protected int f = 0;
/*      */ 
/*      */     
/* 3363 */     public int g = 0;
/*      */ 
/*      */     
/* 3366 */     public int h = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public d(InputStream in) {
/* 3374 */       super(in);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void a(String[] encodings) throws IOException {
/* 3383 */       if (this.c) {
/* 3384 */         throw new IOException("Should not detect encoding twice.");
/*      */       }
/* 3386 */       this.c = true;
/* 3387 */       int b1 = read();
/* 3388 */       if (b1 == -1) {
/*      */         return;
/*      */       }
/* 3391 */       int b2 = read();
/* 3392 */       if (b2 == -1) {
/* 3393 */         this.h = 1;
/*      */         
/*      */         return;
/*      */       } 
/* 3397 */       if (b1 == 239 && b2 == 187) {
/* 3398 */         int b3 = read();
/* 3399 */         if (b3 == 191) {
/* 3400 */           this.g = 3;
/* 3401 */           encodings[0] = "UTF-8";
/* 3402 */           encodings[1] = "UTF8";
/*      */           return;
/*      */         } 
/* 3405 */         this.h = 3;
/*      */       } 
/*      */       
/* 3408 */       if (b1 == 255 && b2 == 254) {
/* 3409 */         encodings[0] = "UTF-16";
/* 3410 */         encodings[1] = "UnicodeLittleUnmarked";
/*      */         
/*      */         return;
/*      */       } 
/* 3414 */       if (b1 == 254 && b2 == 255) {
/* 3415 */         encodings[0] = "UTF-16";
/* 3416 */         encodings[1] = "UnicodeBigUnmarked";
/*      */         
/*      */         return;
/*      */       } 
/* 3420 */       this.h = 2;
/*      */     }
/*      */ 
/*      */     
/*      */     public void a() {
/* 3425 */       this.a = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void b() {
/* 3437 */       if (!this.a) {
/* 3438 */         this.b = true;
/* 3439 */         this.d = null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int read() throws IOException {
/* 3452 */       if (this.g < this.h) {
/* 3453 */         return this.d[this.g++];
/*      */       }
/* 3455 */       if (this.b) {
/* 3456 */         return this.in.read();
/*      */       }
/* 3458 */       if (this.a) {
/* 3459 */         int i = this.d[this.e++];
/* 3460 */         if (this.e == this.f) {
/* 3461 */           this.b = true;
/* 3462 */           this.d = null;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3467 */         return i;
/*      */       } 
/* 3469 */       int c = this.in.read();
/* 3470 */       if (c != -1) {
/* 3471 */         if (this.f == this.d.length) {
/* 3472 */           byte[] newarray = new byte[this.f + 1024];
/* 3473 */           System.arraycopy(this.d, 0, newarray, 0, this.f);
/* 3474 */           this.d = newarray;
/*      */         } 
/* 3476 */         this.d[this.f++] = (byte)c;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3481 */       return c;
/*      */     }
/*      */ 
/*      */     
/*      */     public int read(byte[] array) throws IOException {
/* 3486 */       return read(array, 0, array.length);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int read(byte[] array, int offset, int length) throws IOException {
/* 3494 */       if (this.g < this.h) {
/* 3495 */         int i = this.h - this.g;
/* 3496 */         if (i > length) {
/* 3497 */           i = length;
/*      */         }
/* 3499 */         System.arraycopy(this.d, this.g, array, offset, i);
/* 3500 */         this.g += i;
/* 3501 */         return i;
/*      */       } 
/* 3503 */       if (this.b) {
/* 3504 */         return this.in.read(array, offset, length);
/*      */       }
/* 3506 */       if (this.a) {
/* 3507 */         if (this.e + length > this.f) {
/* 3508 */           length = this.f - this.e;
/*      */         }
/* 3510 */         System.arraycopy(this.d, this.e, array, offset, length);
/* 3511 */         this.e += length;
/* 3512 */         if (this.e == this.f) {
/* 3513 */           this.b = true;
/* 3514 */           this.d = null;
/*      */         } 
/* 3516 */         return length;
/*      */       } 
/* 3518 */       int count = this.in.read(array, offset, length);
/* 3519 */       if (count != -1) {
/* 3520 */         if (this.f + count > this.d.length) {
/* 3521 */           byte[] newarray = new byte[this.f + count + 512];
/* 3522 */           System.arraycopy(this.d, 0, newarray, 0, this.f);
/* 3523 */           this.d = newarray;
/*      */         } 
/* 3525 */         System.arraycopy(array, offset, this.d, this.f, count);
/* 3526 */         this.f += count;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3531 */       return count;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class c
/*      */     implements Cloneable, g
/*      */   {
/*      */     protected int a;
/*      */ 
/*      */ 
/*      */     
/*      */     protected int b;
/*      */ 
/*      */ 
/*      */     
/*      */     protected int c;
/*      */ 
/*      */ 
/*      */     
/*      */     protected int d;
/*      */ 
/*      */ 
/*      */     
/*      */     protected int e;
/*      */ 
/*      */ 
/*      */     
/*      */     protected int f;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public c() {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     c(c other) {
/* 3573 */       a(other.a, other.b, other.c, other.d, other.e, other.f);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void a(int beginLine, int beginColumn, int beginOffset, int endLine, int endColumn, int endOffset) {
/* 3580 */       this.a = beginLine;
/* 3581 */       this.b = beginColumn;
/* 3582 */       this.c = beginOffset;
/* 3583 */       this.d = endLine;
/* 3584 */       this.e = endColumn;
/* 3585 */       this.f = endOffset;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int a() {
/* 3596 */       return this.a;
/*      */     }
/*      */ 
/*      */     
/*      */     public int b() {
/* 3601 */       return this.b;
/*      */     }
/*      */ 
/*      */     
/*      */     public int c() {
/* 3606 */       return this.c;
/*      */     }
/*      */ 
/*      */     
/*      */     public int d() {
/* 3611 */       return this.d;
/*      */     }
/*      */ 
/*      */     
/*      */     public int e() {
/* 3616 */       return this.e;
/*      */     }
/*      */ 
/*      */     
/*      */     public int f() {
/* 3621 */       return this.f;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean g() {
/* 3628 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 3637 */       StringBuffer str = new StringBuffer();
/* 3638 */       str.append(this.a);
/* 3639 */       str.append(':');
/* 3640 */       str.append(this.b);
/* 3641 */       str.append(':');
/* 3642 */       str.append(this.c);
/* 3643 */       str.append(':');
/* 3644 */       str.append(this.d);
/* 3645 */       str.append(':');
/* 3646 */       str.append(this.e);
/* 3647 */       str.append(':');
/* 3648 */       str.append(this.f);
/* 3649 */       return str.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean b(String encoding1, String encoding2) {
/* 3660 */     String reference = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=";
/*      */     try {
/* 3662 */       byte[] bytesEncoding1 = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=".getBytes(encoding1);
/* 3663 */       String referenceWithEncoding2 = new String(bytesEncoding1, encoding2);
/* 3664 */       return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=".equals(referenceWithEncoding2);
/*      */     }
/* 3666 */     catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 3667 */       return false;
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean a(XMLStringBuffer buffer, String string) {
/* 3672 */     int l = string.length();
/* 3673 */     if (buffer.length < l) {
/* 3674 */       return false;
/*      */     }
/* 3676 */     String s = new String(buffer.ch, buffer.length - l, l);
/* 3677 */     return string.equals(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int i() throws IOException {
/* 3685 */     if (this.au.h == this.au.i && 
/* 3686 */       this.au.a(this.au.i) < 1)
/*      */     {
/*      */ 
/*      */       
/* 3690 */       return -1;
/*      */     }
/*      */     
/* 3693 */     char c1 = b.f(this.au);
/*      */ 
/*      */ 
/*      */     
/* 3697 */     return c1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean j() throws IOException {
/* 3704 */     int nbCaret = 0;
/* 3705 */     int originalOffset = this.au.h;
/* 3706 */     int originalColumnNumber = b.c(this.au);
/* 3707 */     int originalCharacterOffset = b.d(this.au);
/*      */     
/*      */     while (true) {
/* 3710 */       int i = i();
/* 3711 */       if (i == -1) {
/* 3712 */         b.a(this.au, originalOffset, originalColumnNumber, originalCharacterOffset);
/* 3713 */         return false;
/*      */       } 
/* 3715 */       if (i == 62 && nbCaret >= 2) {
/* 3716 */         b.a(this.au, originalOffset, originalColumnNumber, originalCharacterOffset);
/* 3717 */         return true;
/*      */       } 
/* 3719 */       if (i == 45) {
/* 3720 */         nbCaret++;
/*      */         continue;
/*      */       } 
/* 3723 */       nbCaret = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void a(XMLStringBuffer buffer, String startMarker, String endMarker) {
/* 3733 */     int i = 0;
/* 3734 */     int startContent = -1;
/* 3735 */     int l1 = startMarker.length();
/* 3736 */     int l2 = endMarker.length();
/* 3737 */     while (i < buffer.length - l1 - l2) {
/* 3738 */       char c1 = buffer.ch[buffer.offset + i];
/* 3739 */       if (Character.isWhitespace(c1)) {
/* 3740 */         i++; continue;
/*      */       } 
/* 3742 */       if (c1 == startMarker.charAt(0) && startMarker.equals(new String(buffer.ch, buffer.offset + i, l1))) {
/*      */         
/* 3744 */         startContent = buffer.offset + i + l1;
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/* 3751 */     if (startContent == -1) {
/*      */       return;
/*      */     }
/*      */     
/* 3755 */     i = buffer.length - 1;
/* 3756 */     while (i > startContent + l2) {
/* 3757 */       char c1 = buffer.ch[buffer.offset + i];
/* 3758 */       if (Character.isWhitespace(c1)) {
/* 3759 */         i--; continue;
/*      */       } 
/* 3761 */       if (c1 == endMarker.charAt(l2 - 1) && endMarker.equals(new String(buffer.ch, buffer.offset + i - l2 + 1, l2))) {
/*      */ 
/*      */         
/* 3764 */         buffer.length = buffer.offset + i - startContent - 2;
/* 3765 */         buffer.offset = startContent;
/*      */         return;
/*      */       } 
/*      */       return;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/h.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */