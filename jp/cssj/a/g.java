/*      */ package jp.cssj.a;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
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
/*      */ import org.cyberneko.html.b;
/*      */ import org.cyberneko.html.d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class g
/*      */   implements XMLDocumentFilter, b
/*      */ {
/*      */   protected static final String a = "http://xml.org/sax/features/namespaces";
/*      */   protected static final String b = "http://cyberneko.org/html/features/balance-tags/document-fragment";
/*   75 */   private static final String[] v = new String[] { "http://xml.org/sax/features/namespaces", "http://cyberneko.org/html/features/balance-tags/document-fragment" };
/*      */ 
/*      */   
/*   78 */   private static final Boolean[] w = new Boolean[] { null, Boolean.FALSE };
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String c = "http://cyberneko.org/html/properties/names/elems";
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String d = "http://cyberneko.org/html/properties/names/attrs";
/*      */ 
/*      */   
/*   89 */   private static final String[] x = new String[] { "http://cyberneko.org/html/properties/names/elems", "http://cyberneko.org/html/properties/names/attrs" };
/*      */ 
/*      */   
/*   92 */   private static final Object[] y = new Object[] { null, null };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short e = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short f = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short g = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final short h = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean i;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean j;
/*      */ 
/*      */ 
/*      */   
/*  122 */   private final XMLAttributes z = (XMLAttributes)new XMLAttributesImpl();
/*      */ 
/*      */ 
/*      */   
/*      */   protected short k;
/*      */ 
/*      */   
/*      */   protected short l;
/*      */ 
/*      */   
/*      */   protected c m;
/*      */ 
/*      */   
/*      */   protected XMLDocumentSource n;
/*      */ 
/*      */   
/*  138 */   protected org.cyberneko.html.filters.a o = new org.cyberneko.html.filters.a();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  143 */   protected final e p = new e();
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean q;
/*      */ 
/*      */   
/*      */   protected boolean r;
/*      */ 
/*      */   
/*      */   protected boolean s;
/*      */ 
/*      */   
/*      */   protected boolean t;
/*      */ 
/*      */   
/*      */   protected boolean u;
/*      */ 
/*      */   
/*  162 */   private h A = new h();
/*      */   
/*      */   public g() {
/*  165 */     this.m = c.a("legacy.xml");
/*      */   }
/*      */   
/*      */   public void a(c props) {
/*  169 */     this.m = props;
/*      */   }
/*      */   
/*      */   public c a() {
/*  173 */     return this.m;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Boolean getFeatureDefault(String featureId) {
/*  182 */     int length = (v != null) ? v.length : 0;
/*  183 */     for (int i = 0; i < length; i++) {
/*  184 */       if (v[i].equals(featureId)) {
/*  185 */         return w[i];
/*      */       }
/*      */     } 
/*  188 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getPropertyDefault(String propertyId) {
/*  193 */     int length = (x != null) ? x.length : 0;
/*  194 */     for (int i = 0; i < length; i++) {
/*  195 */       if (x[i].equals(propertyId)) {
/*  196 */         return y[i];
/*      */       }
/*      */     } 
/*  199 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getRecognizedFeatures() {
/*  208 */     return v;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getRecognizedProperties() {
/*  213 */     return x;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset(XMLComponentManager manager) throws XMLConfigurationException {
/*  219 */     this.i = manager.getFeature("http://xml.org/sax/features/namespaces");
/*  220 */     this.j = manager.getFeature("http://cyberneko.org/html/features/balance-tags/document-fragment");
/*      */ 
/*      */     
/*  223 */     this.k = a(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/elems")));
/*  224 */     this.l = a(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/attrs")));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String featureId, boolean state) throws XMLConfigurationException {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
/*  235 */     if (propertyId.equals("http://cyberneko.org/html/properties/names/elems")) {
/*  236 */       this.k = a(String.valueOf(value));
/*      */       
/*      */       return;
/*      */     } 
/*  240 */     if (propertyId.equals("http://cyberneko.org/html/properties/names/attrs")) {
/*  241 */       this.l = a(String.valueOf(value));
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
/*  253 */     a filter = new a();
/*  254 */     filter.setDocumentHandler(handler);
/*  255 */     this.o = filter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLDocumentHandler getDocumentHandler() {
/*  262 */     return this.o.getDocumentHandler();
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
/*      */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/*  275 */     this.p.a = 0;
/*  276 */     this.q = false;
/*  277 */     this.r = false;
/*  278 */     this.s = false;
/*  279 */     this.t = false;
/*  280 */     this.u = false;
/*  281 */     if (!this.j) {
/*  282 */       this.A.a();
/*      */     }
/*      */ 
/*      */     
/*  286 */     org.cyberneko.html.b.a.a().a((XMLDocumentHandler)this.o, locator, encoding, nscontext, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
/*  295 */     this.o.getDocumentHandler().xmlDecl(version, encoding, standalone, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void doctypeDecl(String rootElementName, String publicId, String systemId, Augmentations augs) throws XNIException {
/*  301 */     this.q = true;
/*  302 */     if (!this.s && !this.r) {
/*  303 */       this.r = true;
/*  304 */       this.o.getDocumentHandler().doctypeDecl(rootElementName, publicId, systemId, augs);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument(Augmentations augs) throws XNIException {
/*  311 */     if (!this.u && !this.j) {
/*  312 */       QName body = b("body");
/*  313 */       startElement(body, null, null);
/*      */     } 
/*      */ 
/*      */     
/*  317 */     int length = this.p.a;
/*  318 */     for (int i = 0; i < length; i++) {
/*  319 */       d info = this.p.b();
/*  320 */       a(info.b, (Augmentations)null);
/*      */     } 
/*      */ 
/*      */     
/*  324 */     this.o.getDocumentHandler().endDocument(augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/*  329 */     this.q = true;
/*  330 */     if (!this.u) {
/*  331 */       this.o.getDocumentHandler().comment(text, augs);
/*      */       return;
/*      */     } 
/*  334 */     this.o.comment(text, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/*  339 */     this.q = true;
/*  340 */     if (!this.u) {
/*  341 */       this.o.getDocumentHandler().processingInstruction(target, data, augs);
/*      */       
/*      */       return;
/*      */     } 
/*  345 */     this.o.processingInstruction(target, data, augs);
/*      */   }
/*      */   
/*      */   private boolean c() {
/*  349 */     for (int i = this.p.a - 1; i >= 0; i--) {
/*  350 */       d parent = this.p.b[i];
/*  351 */       if (parent.a.a == 44) {
/*  352 */         return true;
/*      */       }
/*      */     } 
/*  355 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void startElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  360 */     this.q = true;
/*  361 */     c.a prop = a(element);
/*      */     
/*  363 */     if (prop.a == 46) {
/*  364 */       if (this.s) {
/*      */         return;
/*      */       }
/*  367 */       a(prop, element, attrs, augs);
/*  368 */       this.s = true;
/*      */       return;
/*      */     } 
/*  371 */     if (prop.a == 44) {
/*  372 */       if (this.t) {
/*      */         return;
/*      */       }
/*  375 */       if (!this.s) {
/*  376 */         QName html = b("html");
/*  377 */         a(a(html), html, b(), null);
/*  378 */         this.s = true;
/*      */       } 
/*  380 */       a(prop, element, attrs, augs);
/*  381 */       this.t = true;
/*      */       
/*      */       return;
/*      */     } 
/*  385 */     if (prop.a(1)) {
/*      */       
/*  387 */       if (this.u) {
/*      */         return;
/*      */       }
/*  390 */       if (this.j) {
/*  391 */         a(prop, element, attrs, augs);
/*      */         return;
/*      */       } 
/*  394 */       if (!this.s) {
/*  395 */         QName html = b("html");
/*  396 */         a(a(html), html, b(), null);
/*  397 */         this.s = true;
/*      */       } 
/*  399 */       if (!this.t) {
/*  400 */         XMLDocumentHandler handler = this.o.getDocumentHandler();
/*  401 */         QName head = b("head");
/*  402 */         handler.startElement(head, b(), null);
/*  403 */         handler.endElement(head, null);
/*  404 */         this.t = true;
/*      */       } 
/*  406 */       while ((this.p.a()).a.a != 46) {
/*  407 */         a((Augmentations)null);
/*      */       }
/*  409 */       a(prop, element, attrs, augs);
/*  410 */       this.u = true;
/*  411 */       this.A.a((XMLDocumentHandler)this.o);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  416 */     if (this.t) {
/*  417 */       if (!this.j && !prop.a(2)) {
/*  418 */         QName body = b("body");
/*  419 */         startElement(body, null, null);
/*      */       } 
/*  421 */     } else if (prop.a(2)) {
/*  422 */       QName head = b("head");
/*  423 */       startElement(head, b(), null);
/*      */     } 
/*      */ 
/*      */     
/*  427 */     if (this.p.a >= 1) {
/*      */       
/*  429 */       d parent = this.p.a();
/*  430 */       if (!this.u && (c() || parent.a.a(2))) {
/*  431 */         a(prop, element, attrs, augs);
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  436 */       if (prop.b(1)) {
/*      */         
/*  438 */         int close = 0; int i;
/*  439 */         for (i = this.p.a - 1; i >= 0; i--) {
/*  440 */           d info = this.p.b[i];
/*  441 */           if (info.a.a == 46 || info.a.a == 44) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/*  446 */           if (prop.a(1, info.a.a)) {
/*  447 */             close = this.p.a - i - 1;
/*      */             break;
/*      */           } 
/*      */         } 
/*  451 */         for (i = 0; i < close; i++) {
/*  452 */           d info = this.p.b();
/*  453 */           a(info.b, (Augmentations)null);
/*      */         } 
/*      */       } 
/*      */       
/*  457 */       if (prop.b(3)) {
/*      */         
/*  459 */         int close = 0; int i;
/*  460 */         for (i = this.p.a - 1; i >= 0; i--) {
/*  461 */           d info = this.p.b[i];
/*  462 */           if (info.a.a == 46 || info.a.a == 44) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/*  467 */           if (prop.a(3, info.a.a)) {
/*  468 */             close = this.p.a - i;
/*      */           }
/*  470 */           if (prop.b(8) && prop
/*  471 */             .a(8, info.a.a)) {
/*      */             break;
/*      */           }
/*      */           
/*  475 */           if (prop.b(1) && prop
/*  476 */             .a(1, info.a.a)) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */         
/*  481 */         for (i = 0; i < close; i++) {
/*  482 */           d info = this.p.b();
/*  483 */           a(info.b, (Augmentations)null);
/*      */         } 
/*      */       } 
/*      */     } 
/*  487 */     if (this.p.a >= 1) {
/*      */       
/*  489 */       d parent = this.p.a();
/*      */       
/*  491 */       if (parent.a.b(5) && parent.a
/*  492 */         .a(5, prop.a)) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  498 */       parent = this.p.a();
/*      */       
/*  500 */       if (prop.b(2) && 
/*  501 */         !prop.a(2, parent.a.a)) {
/*  502 */         String parentName = (d.a(prop.c[2][0])).g;
/*  503 */         QName parentTag = b(parentName);
/*  504 */         startElement(parentTag, b(), null);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  511 */     List<d> continueTags = null;
/*  512 */     if (prop.b(7)) {
/*  513 */       while (this.p.a >= 1) {
/*  514 */         d info = this.p.a();
/*  515 */         if (!prop.a(7, info.a.a)) {
/*      */           break;
/*      */         }
/*  518 */         info = this.p.b();
/*  519 */         a(info.b, (Augmentations)null);
/*  520 */         if (continueTags == null) {
/*  521 */           continueTags = new ArrayList<>();
/*      */         }
/*  523 */         continueTags.add(info);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  528 */     if (prop.a(4)) {
/*  529 */       if (attrs == null) {
/*  530 */         attrs = b();
/*      */       }
/*  532 */       this.o.emptyElement(element, attrs, augs);
/*      */     } else {
/*  534 */       this.p.a(new d(prop, element, attrs));
/*  535 */       if (attrs == null) {
/*  536 */         attrs = b();
/*      */       }
/*  538 */       this.o.getDocumentHandler().startElement(element, attrs, augs);
/*      */     } 
/*      */     
/*  541 */     if (continueTags != null) {
/*  542 */       for (int i = continueTags.size() - 1; i >= 0; i--) {
/*  543 */         d info = continueTags.get(i);
/*  544 */         startElement(info.b, info.c, null);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private QName b(String tagName) {
/*  550 */     tagName = a(tagName, this.k);
/*  551 */     return new QName(null, tagName, tagName, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void emptyElement(QName elem, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  556 */     startElement(elem, attrs, augs);
/*  557 */     c.a prop = a(elem);
/*  558 */     if (!prop.a(4)) {
/*  559 */       endElement(elem, augs);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/*  566 */     this.q = true;
/*      */     
/*  568 */     this.o.startGeneralEntity(name, id, encoding, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
/*  573 */     this.q = true;
/*      */     
/*  575 */     this.o.textDecl(version, encoding, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/*  581 */     this.o.endGeneralEntity(name, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void startCDATA(Augmentations augs) throws XNIException {
/*  586 */     this.q = true;
/*  587 */     if (!this.u && this.p.a >= 1) {
/*  588 */       d parent = this.p.a();
/*  589 */       if (parent.a.a(2)) {
/*  590 */         this.o.getDocumentHandler().startCDATA(augs);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  596 */     this.o.startCDATA(augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void endCDATA(Augmentations augs) throws XNIException {
/*  601 */     if (!this.u && this.p.a >= 1) {
/*  602 */       d parent = this.p.a();
/*  603 */       if (parent.a.a(2)) {
/*  604 */         this.o.getDocumentHandler().endCDATA(augs);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  610 */     this.o.endCDATA(augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/*  615 */     if (!this.j)
/*      */     {
/*  617 */       if (!this.q && 
/*  618 */         a(text)) {
/*      */         return;
/*      */       }
/*      */     }
/*      */     
/*  623 */     this.q = true;
/*      */ 
/*      */     
/*  626 */     if (this.p.a >= 1) {
/*  627 */       d parent = this.p.a();
/*      */       
/*  629 */       if (parent.a.b(9) && 
/*  630 */         !a(text)) {
/*      */         
/*  632 */         String parentName = (d.a(parent.a.c[9][0])).g;
/*  633 */         QName parentTag = b(parentName);
/*  634 */         startElement(parentTag, b(), null);
/*      */       } 
/*      */       
/*  637 */       if (parent.a.a(16) && 
/*  638 */         !a(text)) {
/*      */         return;
/*      */       }
/*      */       
/*  642 */       if (parent.a.a(32) && 
/*  643 */         !a(text)) {
/*  644 */         parent = this.p.b();
/*  645 */         a(parent.b, (Augmentations)null);
/*      */       } 
/*      */       
/*  648 */       if (!this.u) {
/*  649 */         if (parent.a.a(2)) {
/*  650 */           this.o.getDocumentHandler().characters(text, augs);
/*      */           return;
/*      */         } 
/*  653 */         if ((parent.a.a == 46 || c()) && 
/*  654 */           a(text)) {
/*  655 */           this.o.getDocumentHandler().characters(text, augs);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  663 */     this.o.characters(text, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
/*  668 */     characters(text, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(QName element, Augmentations augs) throws XNIException {
/*  674 */     c.a prop = a(element);
/*      */     
/*  676 */     if (prop.a == 46 || prop.a == 44) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  682 */     if (!this.u && this.p.a > 0) {
/*      */       
/*  684 */       d d = this.p.a();
/*  685 */       if (c() || (d.a.a(2) && !d.a.a(4))) {
/*  686 */         boolean match = false;
/*  687 */         for (int k = this.p.a - 1; k >= 0; k--) {
/*  688 */           d info = this.p.b[k];
/*  689 */           if (info.a.a == 44) {
/*      */             break;
/*      */           }
/*  692 */           if (info.a.a == prop.a) {
/*  693 */             match = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*  697 */         if (!match) {
/*      */           return;
/*      */         }
/*      */         while (true) {
/*  701 */           a(augs);
/*  702 */           if (d.a.a == prop.a) {
/*      */             break;
/*      */           }
/*  705 */           d = this.p.a();
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  712 */     int close = 0;
/*  713 */     for (int i = this.p.a - 1; i >= 0; i--) {
/*  714 */       d info = this.p.b[i];
/*  715 */       if (info.a.a == 46 || info.a.a == 44) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  720 */       if (info.a.a == prop.a) {
/*      */         
/*  722 */         close = this.p.a - i;
/*      */         break;
/*      */       } 
/*  725 */       if (prop.b(0) && prop
/*  726 */         .a(0, info.a.a)) {
/*      */         
/*  728 */         close = this.p.a - i;
/*  729 */         prop = info.a;
/*  730 */         element = info.b;
/*      */         break;
/*      */       } 
/*  733 */       if (!prop.b(4) || 
/*  734 */         !prop.a(4, info.a.a)) {
/*      */ 
/*      */ 
/*      */         
/*  738 */         if (prop.b(8) && prop
/*  739 */           .a(8, info.a.a)) {
/*      */           break;
/*      */         }
/*      */         
/*  743 */         if (prop.b(1) && prop.a(1, info.a.a)) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  750 */     if (close == 0) {
/*  751 */       if (prop.a(8)) {
/*      */         
/*  753 */         startElement(element, null, null);
/*  754 */         if (!prop.a(4)) {
/*  755 */           endElement(element, augs);
/*      */         }
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  762 */     d parent = this.p.a();
/*  763 */     if (parent.a.a != prop.a && 
/*  764 */       parent.a.b(6) && parent.a
/*  765 */       .a(6, prop.a)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  771 */     List<d> continueTags = null; int j;
/*  772 */     for (j = 0; j < close; j++) {
/*  773 */       d info = this.p.b();
/*  774 */       a(info.b, (Augmentations)null);
/*  775 */       if (j == close - 1) {
/*      */         break;
/*      */       }
/*      */       
/*  779 */       if (!prop.b(4) || 
/*  780 */         !prop.a(4, parent.a.a)) {
/*      */ 
/*      */         
/*  783 */         if (continueTags == null) {
/*  784 */           continueTags = new ArrayList<>();
/*      */         }
/*  786 */         continueTags.add(info);
/*      */       } 
/*  788 */     }  if (continueTags != null) {
/*  789 */       for (j = continueTags.size() - 1; j >= 0; j--) {
/*  790 */         d info = continueTags.get(j);
/*  791 */         startElement(info.b, info.c, null);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentSource(XMLDocumentSource source) {
/*  800 */     this.n = source;
/*      */   }
/*      */ 
/*      */   
/*      */   public XMLDocumentSource getDocumentSource() {
/*  805 */     return this.n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/*  812 */     startDocument(locator, encoding, null, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(String prefix, String uri, Augmentations augs) throws XNIException {
/*  817 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(String prefix, Augmentations augs) throws XNIException {
/*  822 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected c.a a(QName elementName) {
/*  831 */     String name = elementName.rawname;
/*  832 */     if (this.i && "http://www.w3.org/1999/xhtml".equals(elementName.uri)) {
/*  833 */       int index = name.indexOf(':');
/*  834 */       if (index != -1) {
/*  835 */         name = name.substring(index + 1);
/*      */       }
/*      */     } 
/*  838 */     d.a element = d.a(name);
/*  839 */     c.a prop = this.m.a(element.f);
/*  840 */     return prop;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void a(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  846 */     if (attrs == null) {
/*  847 */       attrs = b();
/*      */     }
/*  849 */     this.o.startElement(element, attrs, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void a(QName element, Augmentations augs) throws XNIException {
/*  854 */     this.o.endElement(element, augs);
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void a(c.a prop, QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  859 */     XMLDocumentHandler handler = this.o.getDocumentHandler();
/*  860 */     if (attrs == null) {
/*  861 */       attrs = b();
/*      */     }
/*  863 */     if (!prop.a(4)) {
/*  864 */       this.p.a(new d(prop, element, null));
/*  865 */       handler.startElement(element, attrs, augs);
/*      */     } else {
/*  867 */       handler.emptyElement(element, attrs, augs);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void a(Augmentations augs) throws XNIException {
/*  873 */     d info = this.p.b();
/*  874 */     XMLDocumentHandler handler = this.o.getDocumentHandler();
/*  875 */     handler.endElement(info.b, augs);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final XMLAttributes b() {
/*  881 */     this.z.removeAllAttributes();
/*  882 */     return this.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String a(String name, short mode) {
/*  891 */     switch (mode) {
/*      */       case 1:
/*  893 */         return name.toUpperCase();
/*      */       case 2:
/*  895 */         return name.toLowerCase();
/*      */     } 
/*  897 */     return name;
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
/*  908 */     if (value.equals("lower")) {
/*  909 */       return 2;
/*      */     }
/*  911 */     if (value.equals("upper")) {
/*  912 */       return 1;
/*      */     }
/*  914 */     return 0;
/*      */   }
/*      */   
/*      */   protected static boolean a(XMLString text) {
/*  918 */     for (int i = 0; i < text.length; i++) {
/*  919 */       if (!Character.isWhitespace(text.ch[text.offset + i])) {
/*  920 */         return false;
/*      */       }
/*      */     } 
/*  923 */     return true;
/*      */   }
/*      */   
/*      */   private class a extends org.cyberneko.html.filters.a {
/*      */     public void comment(XMLString text, Augmentations augs) throws XNIException {
/*  928 */       if (g.a(this.a).c()) {
/*  929 */         g.a(this.a).b(text, augs);
/*      */         return;
/*      */       } 
/*  932 */       super.comment(text, augs);
/*      */     }
/*      */     private a(g this$0) {}
/*      */     public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/*  936 */       if (g.a(this.a).c()) {
/*  937 */         g.a(this.a).a(target, data, augs);
/*      */         return;
/*      */       } 
/*  940 */       super.processingInstruction(target, data, augs);
/*      */     }
/*      */ 
/*      */     
/*      */     public void startGeneralEntity(String name, XMLResourceIdentifier id, String encoding, Augmentations augs) throws XNIException {
/*  945 */       if (g.a(this.a).c()) {
/*  946 */         g.a(this.a).a(name, id, encoding, augs);
/*      */         return;
/*      */       } 
/*  949 */       super.startGeneralEntity(name, id, encoding, augs);
/*      */     }
/*      */     
/*      */     public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
/*  953 */       if (g.a(this.a).c()) {
/*  954 */         g.a(this.a).a(version, encoding, augs);
/*      */         return;
/*      */       } 
/*  957 */       super.textDecl(version, encoding, augs);
/*      */     }
/*      */     
/*      */     public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
/*  961 */       if (g.a(this.a).c()) {
/*  962 */         g.a(this.a).a(name, augs);
/*      */         return;
/*      */       } 
/*  965 */       super.endGeneralEntity(name, augs);
/*      */     }
/*      */     
/*      */     public void startCDATA(Augmentations augs) throws XNIException {
/*  969 */       if (g.a(this.a).c()) {
/*  970 */         g.a(this.a).b(augs);
/*      */         return;
/*      */       } 
/*  973 */       super.startCDATA(augs);
/*      */     }
/*      */     
/*      */     public void endCDATA(Augmentations augs) throws XNIException {
/*  977 */       if (g.a(this.a).c()) {
/*  978 */         g.a(this.a).a(augs);
/*      */         return;
/*      */       } 
/*  981 */       super.endCDATA(augs);
/*      */     }
/*      */ 
/*      */     
/*      */     public void emptyElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  986 */       if (g.a(this.a).c()) {
/*  987 */         g.a(this.a).b(element, attrs, augs);
/*      */         return;
/*      */       } 
/*  990 */       if (attrs == null) {
/*  991 */         attrs = this.a.b();
/*      */       }
/*  993 */       super.emptyElement(element, attrs, augs);
/*      */     }
/*      */ 
/*      */     
/*      */     public void startElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/*  998 */       if (g.a(this.a).c()) {
/*  999 */         g.a(this.a).a(element, attrs, augs);
/*      */         return;
/*      */       } 
/* 1002 */       if (attrs == null) {
/* 1003 */         attrs = this.a.b();
/*      */       }
/* 1005 */       super.startElement(element, attrs, augs);
/*      */     }
/*      */     
/*      */     public void characters(XMLString text, Augmentations augs) throws XNIException {
/* 1009 */       if (g.a(this.a).c()) {
/* 1010 */         g.a(this.a).a(text, augs);
/*      */         return;
/*      */       } 
/* 1013 */       super.characters(text, augs);
/*      */     }
/*      */     
/*      */     public void endElement(QName element, Augmentations augs) throws XNIException {
/* 1017 */       if (g.a(this.a).c()) {
/* 1018 */         g.a(this.a).a(element, augs);
/*      */         return;
/*      */       } 
/* 1021 */       super.endElement(element, augs);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */