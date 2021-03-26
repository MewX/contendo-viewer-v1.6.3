/*      */ package org.cyberneko.html;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.apache.xerces.util.XMLAttributesImpl;
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
/*      */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*      */ import org.apache.xerces.xni.parser.XMLDocumentSource;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class i
/*      */   implements XMLDocumentFilter, b
/*      */ {
/*      */   protected static final String a = "http://xml.org/sax/features/namespaces";
/*      */   protected static final String b = "http://cyberneko.org/html/features/augmentations";
/*      */   protected static final String c = "http://cyberneko.org/html/features/report-errors";
/*      */   protected static final String d = "http://cyberneko.org/html/features/document-fragment";
/*      */   protected static final String e = "http://cyberneko.org/html/features/balance-tags/document-fragment";
/*      */   protected static final String f = "http://cyberneko.org/html/features/balance-tags/ignore-outside-content";
/*  102 */   private static final String[] L = new String[] { "http://xml.org/sax/features/namespaces", "http://cyberneko.org/html/features/augmentations", "http://cyberneko.org/html/features/report-errors", "http://cyberneko.org/html/features/document-fragment", "http://cyberneko.org/html/features/balance-tags/document-fragment", "http://cyberneko.org/html/features/balance-tags/ignore-outside-content" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   private static final Boolean[] M = new Boolean[] { null, null, null, null, Boolean.FALSE, Boolean.FALSE };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String g = "http://cyberneko.org/html/properties/names/elems";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String h = "http://cyberneko.org/html/properties/names/attrs";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String i = "http://cyberneko.org/html/properties/error-reporter";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String j = "http://cyberneko.org/html/properties/balance-tags/fragment-context-stack";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   private static final String[] N = new String[] { "http://cyberneko.org/html/properties/names/elems", "http://cyberneko.org/html/properties/names/attrs", "http://cyberneko.org/html/properties/error-reporter", "http://cyberneko.org/html/properties/balance-tags/fragment-context-stack" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  147 */   private static final Object[] O = new Object[] { null, null, null, null };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short k = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short l = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short m = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short n = 2;
/*      */ 
/*      */ 
/*      */   
/*  171 */   protected static final g o = new g.a();
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean p;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean q;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean r;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean s;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean t;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean u;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean v;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short w;
/*      */ 
/*      */ 
/*      */   
/*      */   protected short x;
/*      */ 
/*      */ 
/*      */   
/*      */   protected f y;
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMLDocumentSource z;
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMLDocumentHandler A;
/*      */ 
/*      */ 
/*      */   
/*  223 */   protected final c B = new c();
/*      */ 
/*      */   
/*  226 */   protected final c C = new c();
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean D;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean E;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean F;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean G;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean H;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean I;
/*      */ 
/*      */   
/*      */   private boolean P;
/*      */ 
/*      */   
/*      */   private boolean Q;
/*      */ 
/*      */   
/*      */   protected boolean J;
/*      */ 
/*      */   
/*  262 */   private final QName R = new QName();
/*      */ 
/*      */   
/*  265 */   private final XMLAttributes S = (XMLAttributes)new XMLAttributesImpl();
/*      */ 
/*      */   
/*  268 */   private final a T = new a();
/*      */   
/*      */   protected j K;
/*  271 */   private k U = new k();
/*      */ 
/*      */   
/*      */   private boolean V = false;
/*      */ 
/*      */   
/*      */   private boolean W = false;
/*      */   
/*  279 */   private QName[] X = null;
/*  280 */   private int Y = 0;
/*      */   
/*  282 */   private List Z = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean getFeatureDefault(String featureId) {
/*  290 */     int length = (L != null) ? L.length : 0;
/*  291 */     for (int m = 0; m < length; m++) {
/*  292 */       if (L[m].equals(featureId)) {
/*  293 */         return M[m];
/*      */       }
/*      */     } 
/*  296 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getPropertyDefault(String propertyId) {
/*  301 */     int length = (N != null) ? N.length : 0;
/*  302 */     for (int m = 0; m < length; m++) {
/*  303 */       if (N[m].equals(propertyId)) {
/*  304 */         return O[m];
/*      */       }
/*      */     } 
/*  307 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getRecognizedFeatures() {
/*  316 */     return L;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getRecognizedProperties() {
/*  321 */     return N;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset(XMLComponentManager manager) throws XMLConfigurationException {
/*  329 */     this.p = manager.getFeature("http://xml.org/sax/features/namespaces");
/*  330 */     this.q = manager.getFeature("http://cyberneko.org/html/features/augmentations");
/*  331 */     this.r = manager.getFeature("http://cyberneko.org/html/features/report-errors");
/*  332 */     this.s = (manager.getFeature("http://cyberneko.org/html/features/balance-tags/document-fragment") || manager.getFeature("http://cyberneko.org/html/features/document-fragment"));
/*      */     
/*  334 */     this.t = manager.getFeature("http://cyberneko.org/html/features/balance-tags/ignore-outside-content");
/*  335 */     this.u = manager.getFeature("http://cyberneko.org/html/features/scanner/allow-selfclosing-iframe");
/*  336 */     this.v = manager.getFeature("http://cyberneko.org/html/features/scanner/allow-selfclosing-tags");
/*      */ 
/*      */     
/*  339 */     this.w = a(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/elems")));
/*  340 */     this.x = a(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/attrs")));
/*  341 */     this.y = (f)manager.getProperty("http://cyberneko.org/html/properties/error-reporter");
/*      */     
/*  343 */     this.X = (QName[])manager.getProperty("http://cyberneko.org/html/properties/balance-tags/fragment-context-stack");
/*  344 */     this.D = false;
/*  345 */     this.E = false;
/*  346 */     this.F = false;
/*  347 */     this.G = false;
/*  348 */     this.H = false;
/*  349 */     this.I = false;
/*  350 */     this.P = false;
/*  351 */     this.Q = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
/*  359 */     if (featureId.equals("http://cyberneko.org/html/features/augmentations")) {
/*  360 */       this.q = state;
/*      */       return;
/*      */     } 
/*  363 */     if (featureId.equals("http://cyberneko.org/html/features/report-errors")) {
/*  364 */       this.r = state;
/*      */       return;
/*      */     } 
/*  367 */     if (featureId.equals("http://cyberneko.org/html/features/balance-tags/ignore-outside-content")) {
/*  368 */       this.t = state;
/*      */       return;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
/*  378 */     if (propertyId.equals("http://cyberneko.org/html/properties/names/elems")) {
/*  379 */       this.w = a(String.valueOf(value));
/*      */       
/*      */       return;
/*      */     } 
/*  383 */     if (propertyId.equals("http://cyberneko.org/html/properties/names/attrs")) {
/*  384 */       this.x = a(String.valueOf(value));
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
/*      */   public void setDocumentHandler(XMLDocumentHandler handler) {
/*  396 */     this.A = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLDocumentHandler getDocumentHandler() {
/*  403 */     return this.A;
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
/*      */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/*  418 */     this.B.a = 0;
/*  419 */     if (this.X != null) {
/*  420 */       this.Y = this.X.length;
/*  421 */       for (int m = 0; m < this.X.length; m++) {
/*  422 */         QName name = this.X[m];
/*  423 */         d.a elt = d.a(name.localpart);
/*  424 */         this.B.a(new b(elt, name));
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  429 */       this.Y = 0;
/*      */     } 
/*      */ 
/*      */     
/*  433 */     if (this.A != null) {
/*  434 */       a.a().a(this.A, locator, encoding, nscontext, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
/*  444 */     if (!this.D && this.A != null) {
/*  445 */       this.A.xmlDecl(version, encoding, standalone, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void doctypeDecl(String rootElementName, String publicId, String systemId, Augmentations augs) throws XNIException {
/*  452 */     this.D = true;
/*  453 */     if (this.r) {
/*  454 */       if (this.F) {
/*  455 */         this.y.c("HTML2010", null);
/*      */       }
/*  457 */       else if (this.E) {
/*  458 */         this.y.c("HTML2011", null);
/*      */       } 
/*      */     }
/*  461 */     if (!this.F && !this.E) {
/*  462 */       this.E = true;
/*  463 */       if (this.A != null) {
/*  464 */         this.A.doctypeDecl(rootElementName, publicId, systemId, augs);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument(Augmentations augs) throws XNIException {
/*  473 */     this.t = true;
/*  474 */     c();
/*      */ 
/*      */     
/*  477 */     if (!this.F && !this.s) {
/*  478 */       if (this.r) {
/*  479 */         this.y.c("HTML2000", null);
/*      */       }
/*  481 */       if (this.A != null) {
/*  482 */         this.G = false;
/*  483 */         e();
/*  484 */         String body = a("body", this.w);
/*  485 */         this.R.setValues(null, body, body, null);
/*  486 */         a(this.R, b());
/*      */         
/*  488 */         String ename = a("html", this.w);
/*  489 */         this.R.setValues(null, ename, ename, null);
/*  490 */         a(this.R, b());
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  496 */       int length = this.B.a - this.Y;
/*  497 */       for (int m = 0; m < length; m++) {
/*  498 */         b info = this.B.b();
/*  499 */         if (this.r) {
/*  500 */           String ename = info.b.rawname;
/*  501 */           this.y.b("HTML2001", new Object[] { ename });
/*      */         } 
/*  503 */         if (this.A != null) {
/*  504 */           a(info.b, b());
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  510 */     if (this.A != null) {
/*  511 */       this.A.endDocument(augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void c() {
/*  521 */     List toConsume = new ArrayList(this.Z);
/*  522 */     this.Z.clear();
/*  523 */     for (int m = 0; m < toConsume.size(); m++) {
/*  524 */       a entry = toConsume.get(m);
/*  525 */       this.W = true;
/*  526 */       endElement(a.a(entry), a.b(entry));
/*      */     } 
/*  528 */     this.Z.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/*  533 */     this.D = true;
/*  534 */     d();
/*  535 */     if (this.A != null) {
/*  536 */       this.A.comment(text, augs);
/*      */     }
/*      */   }
/*      */   
/*      */   private void d() {
/*  541 */     if (!this.U.a()) {
/*  542 */       if (!this.I) {
/*  543 */         e();
/*      */       }
/*  545 */       this.U.a((XMLDocumentHandler)this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/*  552 */     this.D = true;
/*  553 */     d();
/*  554 */     if (this.A != null) {
/*  555 */       this.A.processingInstruction(target, data, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(QName elem, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  562 */     this.D = true;
/*      */     
/*  564 */     boolean isForcedCreation = this.V;
/*  565 */     this.V = false;
/*      */ 
/*      */     
/*  568 */     if (this.G) {
/*  569 */       c(elem, attrs, augs);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  574 */     d.a element = a(elem);
/*  575 */     short elementCode = element.f;
/*      */ 
/*      */     
/*  578 */     if (isForcedCreation && (elementCode == 101 || elementCode == 91)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  583 */     if (this.F && elementCode == 46) {
/*  584 */       c(elem, attrs, augs);
/*      */       
/*      */       return;
/*      */     } 
/*  588 */     if (this.Q && elementCode != 36 && elementCode != 37 && elementCode != 70) {
/*  589 */       c(elem, attrs, augs);
/*      */       
/*      */       return;
/*      */     } 
/*  593 */     if (elementCode == 44) {
/*  594 */       if (this.H) {
/*  595 */         c(elem, attrs, augs);
/*      */         return;
/*      */       } 
/*  598 */       this.H = true;
/*      */     }
/*  600 */     else if (elementCode == 37) {
/*      */       
/*  602 */       if (!this.H) {
/*  603 */         QName head = b("head");
/*  604 */         b(head, null, b());
/*  605 */         endElement(head, b());
/*      */       } 
/*  607 */       c();
/*  608 */       this.Q = true;
/*      */     }
/*  610 */     else if (elementCode == 14) {
/*      */       
/*  612 */       if (!this.H) {
/*  613 */         QName head = b("head");
/*  614 */         b(head, null, b());
/*  615 */         endElement(head, b());
/*      */       } 
/*  617 */       c();
/*      */       
/*  619 */       if (this.I) {
/*  620 */         c(elem, attrs, augs);
/*      */         return;
/*      */       } 
/*  623 */       this.I = true;
/*      */     }
/*  625 */     else if (elementCode == 35) {
/*  626 */       if (this.J) {
/*  627 */         c(elem, attrs, augs);
/*      */         return;
/*      */       } 
/*  630 */       this.J = true;
/*      */     }
/*  632 */     else if (elementCode == 117) {
/*  633 */       c();
/*      */     } 
/*      */ 
/*      */     
/*  637 */     if (element.j != null) {
/*  638 */       d.a preferedParent = element.j[0];
/*  639 */       if (!this.s || (preferedParent.f != 44 && preferedParent.f != 14))
/*      */       {
/*      */         
/*  642 */         if (!this.F && !this.s) {
/*  643 */           String pname = preferedParent.g;
/*  644 */           pname = a(pname, this.w);
/*  645 */           if (this.r) {
/*  646 */             String ename = elem.rawname;
/*  647 */             this.y.b("HTML2002", new Object[] { ename, pname });
/*      */           } 
/*  649 */           QName qname = new QName(null, pname, pname, null);
/*  650 */           boolean parentCreated = b(qname, null, b());
/*  651 */           if (!parentCreated) {
/*  652 */             if (!isForcedCreation) {
/*  653 */               c(elem, attrs, augs);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*  659 */         } else if (preferedParent.f != 44 || (!this.I && !this.s)) {
/*  660 */           int n = a(element.j, element.k);
/*  661 */           if (n == -1) {
/*  662 */             String pname = a(preferedParent.g, this.w);
/*  663 */             QName qname = new QName(null, pname, pname, null);
/*  664 */             if (this.r) {
/*  665 */               String ename = elem.rawname;
/*  666 */               this.y.b("HTML2004", new Object[] { ename, pname });
/*      */             } 
/*  668 */             boolean parentCreated = b(qname, null, b());
/*  669 */             if (!parentCreated) {
/*  670 */               if (!isForcedCreation) {
/*  671 */                 c(elem, attrs, augs);
/*      */               }
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  681 */     int depth = 0;
/*  682 */     if (element.h == 0) {
/*  683 */       int length = this.B.a;
/*  684 */       this.C.a = 0;
/*  685 */       for (int n = length - 1; n >= 0; n--) {
/*  686 */         b info = this.B.b[n];
/*  687 */         if (!info.a.a()) {
/*      */           break;
/*      */         }
/*  690 */         this.C.a(info);
/*  691 */         endElement(info.b, b());
/*      */       } 
/*  693 */       depth = this.C.a;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  699 */     if ((this.B.a > 1 && (this.B.a()).a.f == 90) || (this.B.a > 2 && (this.B.b[this.B.a - 2]).a.f == 44)) {
/*      */ 
/*      */       
/*  702 */       b info = this.B.b();
/*  703 */       if (this.A != null) {
/*  704 */         a(info.b, b());
/*      */       }
/*      */     } 
/*  707 */     if (element.l != null) {
/*  708 */       int length = this.B.a;
/*  709 */       for (int n = length - 1; n >= 0; n--) {
/*  710 */         b info = this.B.b[n];
/*      */ 
/*      */         
/*  713 */         if (element.a(info.a.f)) {
/*  714 */           if (this.r) {
/*  715 */             String ename = elem.rawname;
/*  716 */             String iname = info.b.rawname;
/*  717 */             this.y.b("HTML2005", new Object[] { ename, iname });
/*      */           } 
/*  719 */           for (int i1 = length - 1; i1 >= n; i1--) {
/*  720 */             info = this.B.b();
/*  721 */             if (this.A != null)
/*      */             {
/*  723 */               a(info.b, b());
/*      */             }
/*      */           } 
/*  726 */           length = n;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  731 */         else if (info.a.b() || element.a(info.a)) {
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  738 */     this.F = true;
/*  739 */     if (element != null && element.c()) {
/*  740 */       if (attrs == null) {
/*  741 */         attrs = a();
/*      */       }
/*  743 */       if (this.A != null) {
/*  744 */         this.A.emptyElement(elem, attrs, augs);
/*      */       }
/*      */     } else {
/*      */       
/*  748 */       boolean inline = (element != null && element.a());
/*  749 */       this.B.a(new b(element, elem, inline ? attrs : null));
/*  750 */       if (attrs == null) {
/*  751 */         attrs = a();
/*      */       }
/*  753 */       if (this.A != null) {
/*  754 */         a(elem, attrs, augs);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  759 */     for (int m = 0; m < depth; m++) {
/*  760 */       b info = this.C.b();
/*  761 */       b(info.b, info.c, b());
/*      */     } 
/*      */     
/*  764 */     if (elementCode == 14) {
/*  765 */       this.U.a((XMLDocumentHandler)this);
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
/*      */   private boolean b(QName elem, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  777 */     this.V = true;
/*  778 */     startElement(elem, attrs, augs);
/*      */     
/*  780 */     return (this.B.a > 0 && elem.equals((this.B.a()).b));
/*      */   }
/*      */   
/*      */   private QName b(String tagName) {
/*  784 */     tagName = a(tagName, this.w);
/*  785 */     return new QName(null, tagName, tagName, "http://www.w3.org/1999/xhtml");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void emptyElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  791 */     startElement(element, attrs, augs);
/*      */     
/*  793 */     d.a elem = a(element);
/*  794 */     if (elem.c() || this.v || elem.f == 117 || (elem.f == 48 && this.u))
/*      */     {
/*      */ 
/*      */       
/*  798 */       endElement(element, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/*  807 */     this.D = true;
/*      */ 
/*      */     
/*  810 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  815 */     if (!this.s) {
/*  816 */       boolean insertBody = !this.F;
/*  817 */       if (!insertBody) {
/*  818 */         b info = this.B.a();
/*  819 */         if (info.a.f == 44 || info.a.f == 46) {
/*      */           
/*  821 */           String hname = a("head", this.w);
/*  822 */           String bname = a("body", this.w);
/*  823 */           if (this.r) {
/*  824 */             this.y.b("HTML2009", new Object[] { hname, bname });
/*      */           }
/*  826 */           this.R.setValues(null, hname, hname, null);
/*  827 */           endElement(this.R, b());
/*  828 */           insertBody = true;
/*      */         } 
/*      */       } 
/*  831 */       if (insertBody) {
/*  832 */         e();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  837 */     if (this.A != null) {
/*  838 */       this.A.startGeneralEntity(name, id, encoding, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void e() {
/*  847 */     QName body = b("body");
/*  848 */     if (this.r) {
/*  849 */       this.y.b("HTML2006", new Object[] { body.localpart });
/*      */     }
/*  851 */     b(body, null, b());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
/*  857 */     this.D = true;
/*      */ 
/*      */     
/*  860 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  865 */     if (this.A != null) {
/*  866 */       this.A.textDecl(version, encoding, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/*  875 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  880 */     if (this.A != null) {
/*  881 */       this.A.endGeneralEntity(name, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startCDATA(Augmentations augs) throws XNIException {
/*  888 */     this.D = true;
/*      */     
/*  890 */     d();
/*      */ 
/*      */     
/*  893 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  898 */     if (this.A != null) {
/*  899 */       this.A.startCDATA(augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCDATA(Augmentations augs) throws XNIException {
/*  908 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  913 */     if (this.A != null) {
/*  914 */       this.A.endCDATA(augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/*  922 */     if (this.G || this.P) {
/*      */       return;
/*      */     }
/*      */     
/*  926 */     if (this.B.a == 0 && !this.s) {
/*      */       
/*  928 */       this.U.a(text, augs);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  933 */     boolean whitespace = true;
/*  934 */     for (int m = 0; m < text.length; m++) {
/*  935 */       if (!Character.isWhitespace(text.ch[text.offset + m])) {
/*  936 */         whitespace = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  941 */     if (!this.s) {
/*      */       
/*  943 */       if (!this.F) {
/*  944 */         if (whitespace) {
/*      */           return;
/*      */         }
/*  947 */         e();
/*      */       } 
/*      */       
/*  950 */       if (whitespace && (this.B.a < 2 || this.Z.size() == 1)) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  959 */       if (!whitespace) {
/*  960 */         b info = this.B.a();
/*  961 */         if (info.a.f == 44 || info.a.f == 46) {
/*      */           
/*  963 */           String hname = a("head", this.w);
/*  964 */           String bname = a("body", this.w);
/*  965 */           if (this.r) {
/*  966 */             this.y.b("HTML2009", new Object[] { hname, bname });
/*      */           }
/*  968 */           e();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  974 */     if (this.A != null) {
/*  975 */       this.A.characters(text, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
/*  983 */     characters(text, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void endElement(QName element, Augmentations augs) throws XNIException {
/*  988 */     boolean forcedEndElement = this.W;
/*      */     
/*  990 */     if (this.G) {
/*  991 */       b(element, augs);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  996 */     d.a elem = a(element);
/*      */ 
/*      */     
/*  999 */     if (!this.t && (elem.f == 14 || elem.f == 46)) {
/*      */       
/* 1001 */       this.Z.add(new a(element, augs));
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1006 */     if (this.Q && elem.f != 36 && elem.f != 37) {
/* 1007 */       b(element, augs);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1012 */     if (elem.f == 46) {
/* 1013 */       this.G = true;
/*      */     }
/* 1015 */     else if (this.t) {
/* 1016 */       if (elem.f == 14) {
/* 1017 */         this.P = true;
/*      */       }
/* 1019 */       else if (this.P) {
/* 1020 */         b(element, augs);
/*      */         
/*      */         return;
/*      */       } 
/* 1024 */     } else if (elem.f == 35) {
/* 1025 */       this.J = false;
/*      */     }
/* 1027 */     else if (elem.f == 44 && !forcedEndElement) {
/*      */       
/* 1029 */       this.Z.add(new a(element, augs));
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1034 */     int depth = a(elem);
/* 1035 */     if (depth == -1) {
/* 1036 */       if (elem.f == 77) {
/* 1037 */         b(element, a(), b());
/* 1038 */         endElement(element, augs);
/*      */       }
/* 1040 */       else if (!elem.c()) {
/* 1041 */         b(element, augs);
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1047 */     if (depth > 1 && elem.a()) {
/* 1048 */       int size = this.B.a;
/* 1049 */       this.C.a = 0;
/* 1050 */       for (int n = 0; n < depth - 1; n++) {
/* 1051 */         b info = this.B.b[size - n - 1];
/* 1052 */         d.a pelem = info.a;
/* 1053 */         if (pelem.a() || pelem.f == 34)
/*      */         {
/*      */ 
/*      */           
/* 1057 */           this.C.a(info);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1063 */     for (int m = 0; m < depth; m++) {
/* 1064 */       b info = this.B.b();
/* 1065 */       if (this.r && m < depth - 1) {
/* 1066 */         String ename = a(element.rawname, this.w);
/* 1067 */         String iname = info.b.rawname;
/* 1068 */         this.y.b("HTML2007", new Object[] { ename, iname });
/*      */       } 
/* 1070 */       if (this.A != null)
/*      */       {
/* 1072 */         a(info.b, (m < depth - 1) ? b() : augs);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1077 */     if (depth > 1) {
/* 1078 */       int size = this.C.a;
/* 1079 */       for (int n = 0; n < size; n++) {
/* 1080 */         b info = this.C.b();
/* 1081 */         XMLAttributes attributes = info.c;
/* 1082 */         if (this.r) {
/* 1083 */           String iname = info.b.rawname;
/* 1084 */           this.y.b("HTML2008", new Object[] { iname });
/*      */         } 
/* 1086 */         b(info.b, attributes, b());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentSource(XMLDocumentSource source) {
/* 1096 */     this.z = source;
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLDocumentSource getDocumentSource() {
/* 1101 */     return this.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/* 1109 */     startDocument(locator, encoding, null, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(String prefix, String uri, Augmentations augs) throws XNIException {
/* 1117 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1122 */     if (this.A != null) {
/* 1123 */       a.a().a(this.A, prefix, uri, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(String prefix, Augmentations augs) throws XNIException {
/* 1133 */     if (this.G) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1138 */     if (this.A != null) {
/* 1139 */       a.a().a(this.A, prefix, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected d.a a(QName elementName) {
/* 1150 */     String name = elementName.rawname;
/* 1151 */     if (this.p && "http://www.w3.org/1999/xhtml".equals(elementName.uri)) {
/* 1152 */       int index = name.indexOf(':');
/* 1153 */       if (index != -1) {
/* 1154 */         name = name.substring(index + 1);
/*      */       }
/*      */     } 
/* 1157 */     return d.a(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void a(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 1164 */     this.A.startElement(element, attrs, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void a(QName element, Augmentations augs) throws XNIException {
/* 1170 */     this.A.endElement(element, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int a(d.a element) {
/* 1180 */     boolean container = element.d();
/* 1181 */     short elementCode = element.f;
/* 1182 */     boolean tableBodyOrHtml = (elementCode == 101 || elementCode == 14 || elementCode == 46);
/*      */     
/* 1184 */     int depth = -1;
/* 1185 */     for (int m = this.B.a - 1; m >= this.Y; m--) {
/* 1186 */       b info = this.B.b[m];
/* 1187 */       if (info.a.f == element.f) {
/* 1188 */         depth = this.B.a - m;
/*      */         break;
/*      */       } 
/* 1191 */       if (!container && info.a.b()) {
/*      */         break;
/*      */       }
/* 1194 */       if (info.a.f == 101 && !tableBodyOrHtml) {
/* 1195 */         return -1;
/*      */       }
/*      */     } 
/* 1198 */     return depth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int a(d.a[] parents, short bounds) {
/* 1208 */     if (parents != null) {
/* 1209 */       for (int m = this.B.a - 1; m >= 0; m--) {
/* 1210 */         b info = this.B.b[m];
/* 1211 */         if (info.a.f == bounds) {
/*      */           break;
/*      */         }
/* 1214 */         for (int n = 0; n < parents.length; n++) {
/* 1215 */           if (info.a.f == (parents[n]).f) {
/* 1216 */             return this.B.a - m;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/* 1221 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected final XMLAttributes a() {
/* 1226 */     this.S.removeAllAttributes();
/* 1227 */     return this.S;
/*      */   }
/*      */ 
/*      */   
/*      */   protected final Augmentations b() {
/* 1232 */     a augs = null;
/* 1233 */     if (this.q) {
/* 1234 */       augs = this.T;
/* 1235 */       augs.removeAllItems();
/* 1236 */       augs.putItem("http://cyberneko.org/html/features/augmentations", o);
/*      */     } 
/* 1238 */     return augs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String a(String name, short mode) {
/* 1247 */     switch (mode) { case 1:
/* 1248 */         return name.toUpperCase(Locale.ENGLISH);
/* 1249 */       case 2: return name.toLowerCase(Locale.ENGLISH); }
/*      */     
/* 1251 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short a(String value) {
/* 1262 */     if (value.equals("lower")) {
/* 1263 */       return 2;
/*      */     }
/* 1265 */     if (value.equals("upper")) {
/* 1266 */       return 1;
/*      */     }
/* 1268 */     return 0;
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
/*      */   public static class b
/*      */   {
/*      */     public d.a a;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public QName b;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XMLAttributes c;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public b(d.a element, QName qname) {
/* 1318 */       this(element, qname, null);
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
/*      */     public b(d.a element, QName qname, XMLAttributes attributes) {
/* 1332 */       this.a = element;
/* 1333 */       this.b = new QName(qname);
/* 1334 */       if (attributes != null) {
/* 1335 */         int length = attributes.getLength();
/* 1336 */         if (length > 0) {
/* 1337 */           QName aqname = new QName();
/* 1338 */           XMLAttributesImpl xMLAttributesImpl = new XMLAttributesImpl();
/* 1339 */           for (int i = 0; i < length; i++) {
/* 1340 */             attributes.getName(i, aqname);
/* 1341 */             String type = attributes.getType(i);
/* 1342 */             String value = attributes.getValue(i);
/* 1343 */             String nonNormalizedValue = attributes.getNonNormalizedValue(i);
/* 1344 */             boolean specified = attributes.isSpecified(i);
/* 1345 */             xMLAttributesImpl.addAttribute(aqname, type, value);
/* 1346 */             xMLAttributesImpl.setNonNormalizedValue(i, nonNormalizedValue);
/* 1347 */             xMLAttributesImpl.setSpecified(i, specified);
/*      */           } 
/* 1349 */           this.c = (XMLAttributes)xMLAttributesImpl;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1358 */       return super.toString() + this.b;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class c
/*      */   {
/*      */     public int a;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1373 */     public i.b[] b = new i.b[10];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void a(i.b info) {
/* 1381 */       if (this.a == this.b.length) {
/* 1382 */         i.b[] newarray = new i.b[this.a + 10];
/* 1383 */         System.arraycopy(this.b, 0, newarray, 0, this.a);
/* 1384 */         this.b = newarray;
/*      */       } 
/* 1386 */       this.b[this.a++] = info;
/*      */     }
/*      */ 
/*      */     
/*      */     public i.b a() {
/* 1391 */       return this.b[this.a - 1];
/*      */     }
/*      */ 
/*      */     
/*      */     public i.b b() {
/* 1396 */       return this.b[--this.a];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1403 */       StringBuffer sb = new StringBuffer("InfoStack(");
/* 1404 */       for (int i = this.a - 1; i >= 0; i--) {
/* 1405 */         sb.append(this.b[i]);
/* 1406 */         if (i != 0)
/* 1407 */           sb.append(", "); 
/*      */       } 
/* 1409 */       sb.append(")");
/* 1410 */       return sb.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void a(j tagBalancingListener) {
/* 1417 */     this.K = tagBalancingListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void c(QName elem, XMLAttributes attrs, Augmentations augs) {
/* 1425 */     if (this.K != null) {
/* 1426 */       this.K.a(elem, attrs, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void b(QName element, Augmentations augs) {
/* 1433 */     if (this.K != null)
/* 1434 */       this.K.a(element, augs); 
/*      */   }
/*      */   static class a {
/*      */     private final QName a;
/*      */     private final Augmentations b;
/*      */     
/* 1440 */     static Augmentations b(a x0) { return x0.b; } static QName a(a x0) { return x0.a; }
/*      */ 
/*      */     
/*      */     a(QName element, Augmentations augs) {
/* 1444 */       this.a = new QName(element);
/* 1445 */       this.b = (augs == null) ? null : new a(augs);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/i.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */