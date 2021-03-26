/*     */ package org.cyberneko.html.filters;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Locale;
/*     */ import java.util.Vector;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLComponentManager;
/*     */ import org.apache.xerces.xni.parser.XMLConfigurationException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends a
/*     */ {
/*     */   public static final String a = "http://www.w3.org/1999/xhtml";
/*     */   public static final String b = "http://www.w3.org/XML/1998/namespace";
/*     */   public static final String c = "http://www.w3.org/2000/xmlns/";
/*     */   protected static final String f = "http://xml.org/sax/features/namespaces";
/*     */   protected static final String g = "http://cyberneko.org/html/features/override-namespaces";
/*     */   protected static final String h = "http://cyberneko.org/html/features/insert-namespaces";
/*  78 */   private static final String[] w = new String[] { "http://xml.org/sax/features/namespaces", "http://cyberneko.org/html/features/override-namespaces", "http://cyberneko.org/html/features/insert-namespaces" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Boolean[] x = new Boolean[] { null, Boolean.FALSE, Boolean.FALSE };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String i = "http://cyberneko.org/html/properties/names/elems";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String j = "http://cyberneko.org/html/properties/names/attrs";
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String k = "http://cyberneko.org/html/properties/namespaces-uri";
/*     */ 
/*     */ 
/*     */   
/* 103 */   private static final String[] y = new String[] { "http://cyberneko.org/html/properties/names/elems", "http://cyberneko.org/html/properties/names/attrs", "http://cyberneko.org/html/properties/namespaces-uri" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   private static final Object[] z = new Object[] { null, null, "http://www.w3.org/1999/xhtml" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final short l = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final short m = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final short n = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean o;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean p;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean q;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean r;
/*     */ 
/*     */ 
/*     */   
/*     */   protected short s;
/*     */ 
/*     */ 
/*     */   
/*     */   protected short t;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String u;
/*     */ 
/*     */ 
/*     */   
/* 159 */   protected final a v = new a();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   private final QName A = new QName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getRecognizedFeatures() {
/* 176 */     return a(super.getRecognizedFeatures(), w);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getFeatureDefault(String featureId) {
/* 185 */     for (int i = 0; i < w.length; i++) {
/* 186 */       if (w[i].equals(featureId)) {
/* 187 */         return x[i];
/*     */       }
/*     */     } 
/* 190 */     return super.getFeatureDefault(featureId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getRecognizedProperties() {
/* 199 */     return a(super.getRecognizedProperties(), y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getPropertyDefault(String propertyId) {
/* 208 */     for (int i = 0; i < y.length; i++) {
/* 209 */       if (y[i].equals(propertyId)) {
/* 210 */         return z[i];
/*     */       }
/*     */     } 
/* 213 */     return super.getPropertyDefault(propertyId);
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
/*     */   public void reset(XMLComponentManager manager) throws XMLConfigurationException {
/* 227 */     super.reset(manager);
/*     */ 
/*     */     
/* 230 */     this.o = manager.getFeature("http://xml.org/sax/features/namespaces");
/* 231 */     this.q = manager.getFeature("http://cyberneko.org/html/features/override-namespaces");
/* 232 */     this.r = manager.getFeature("http://cyberneko.org/html/features/insert-namespaces");
/*     */ 
/*     */     
/* 235 */     this.s = a(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/elems")));
/* 236 */     this.t = a(String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/names/attrs")));
/* 237 */     this.u = String.valueOf(manager.getProperty("http://cyberneko.org/html/properties/namespaces-uri"));
/*     */ 
/*     */     
/* 240 */     this.v.reset();
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
/*     */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/* 255 */     super.startDocument(locator, encoding, this.v, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 264 */     if (this.o) {
/* 265 */       this.v.pushContext();
/* 266 */       a(element, attrs);
/*     */       
/* 268 */       int dcount = this.v.getDeclaredPrefixCount();
/* 269 */       if (this.d != null && dcount > 0) {
/* 270 */         for (int i = 0; i < dcount; i++) {
/* 271 */           String prefix = this.v.getDeclaredPrefixAt(i);
/* 272 */           String uri = this.v.getURI(prefix);
/* 273 */           org.cyberneko.html.b.a.a().a(this.d, prefix, uri, augs);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 279 */     super.startElement(element, attrs, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 288 */     if (this.o) {
/* 289 */       this.v.pushContext();
/* 290 */       a(element, attrs);
/*     */       
/* 292 */       int dcount = this.v.getDeclaredPrefixCount();
/* 293 */       if (this.d != null && dcount > 0) {
/* 294 */         for (int i = 0; i < dcount; i++) {
/* 295 */           String prefix = this.v.getDeclaredPrefixAt(i);
/* 296 */           String uri = this.v.getURI(prefix);
/* 297 */           org.cyberneko.html.b.a.a().a(this.d, prefix, uri, augs);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 303 */     super.emptyElement(element, attrs, augs);
/*     */ 
/*     */     
/* 306 */     if (this.o) {
/* 307 */       int dcount = this.v.getDeclaredPrefixCount();
/* 308 */       if (this.d != null && dcount > 0) {
/* 309 */         for (int i = dcount - 1; i >= 0; i--) {
/* 310 */           String prefix = this.v.getDeclaredPrefixAt(i);
/* 311 */           org.cyberneko.html.b.a.a().a(this.d, prefix, augs);
/*     */         } 
/*     */       }
/*     */       
/* 315 */       this.v.popContext();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 325 */     if (this.o) {
/* 326 */       a(element, (XMLAttributes)null);
/*     */     }
/*     */ 
/*     */     
/* 330 */     super.endElement(element, augs);
/*     */ 
/*     */     
/* 333 */     if (this.o) {
/* 334 */       int dcount = this.v.getDeclaredPrefixCount();
/* 335 */       if (this.d != null && dcount > 0) {
/* 336 */         for (int i = dcount - 1; i >= 0; i--) {
/* 337 */           String prefix = this.v.getDeclaredPrefixAt(i);
/* 338 */           org.cyberneko.html.b.a.a().a(this.d, prefix, augs);
/*     */         } 
/*     */       }
/*     */       
/* 342 */       this.v.popContext();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void a(QName qname) {
/* 353 */     int index = qname.rawname.indexOf(':');
/* 354 */     if (index != -1) {
/* 355 */       qname.prefix = qname.rawname.substring(0, index);
/* 356 */       qname.localpart = qname.rawname.substring(index + 1);
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
/*     */   protected static final short a(String value) {
/* 368 */     if (value.equals("lower")) return 2; 
/* 369 */     if (value.equals("upper")) return 1; 
/* 370 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static final String a(String name, short mode) {
/* 375 */     switch (mode) { case 1:
/* 376 */         return name.toUpperCase(Locale.ENGLISH);
/* 377 */       case 2: return name.toLowerCase(Locale.ENGLISH); }
/*     */     
/* 379 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(QName element, XMLAttributes attrs) {
/* 390 */     a(element);
/*     */ 
/*     */     
/* 393 */     int attrCount = (attrs != null) ? attrs.getLength() : 0;
/* 394 */     for (int i = attrCount - 1; i >= 0; i--) {
/* 395 */       attrs.getName(i, this.A);
/* 396 */       String aname = this.A.rawname;
/* 397 */       String ANAME = aname.toUpperCase(Locale.ENGLISH);
/* 398 */       if (ANAME.startsWith("XMLNS:") || ANAME.equals("XMLNS")) {
/* 399 */         int anamelen = aname.length();
/*     */ 
/*     */         
/* 402 */         String aprefix = (anamelen > 5) ? aname.substring(0, 5) : null;
/* 403 */         String alocal = (anamelen > 5) ? aname.substring(6) : aname;
/* 404 */         String avalue = attrs.getValue(i);
/*     */ 
/*     */         
/* 407 */         if (anamelen > 5) {
/* 408 */           aprefix = a(aprefix, (short)2);
/* 409 */           alocal = a(alocal, this.s);
/* 410 */           aname = aprefix + ':' + alocal;
/*     */         } else {
/*     */           
/* 413 */           alocal = a(alocal, (short)2);
/* 414 */           aname = alocal;
/*     */         } 
/* 416 */         this.A.setValues(aprefix, alocal, aname, null);
/* 417 */         attrs.setName(i, this.A);
/*     */ 
/*     */         
/* 420 */         String str1 = (alocal != aname) ? alocal : "";
/* 421 */         String uri = (avalue.length() > 0) ? avalue : null;
/* 422 */         if (this.q && str1.equals(element.prefix) && org.cyberneko.html.d.a(element.localpart, null) != null)
/*     */         {
/*     */           
/* 425 */           uri = this.u;
/*     */         }
/* 427 */         this.v.declarePrefix(str1, uri);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 432 */     String prefix = (element.prefix != null) ? element.prefix : "";
/* 433 */     element.uri = this.v.getURI(prefix);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 439 */     if (element.uri != null && element.prefix == null) {
/* 440 */       element.prefix = "";
/*     */     }
/*     */ 
/*     */     
/* 444 */     if (this.r && attrs != null && org.cyberneko.html.d.a(element.localpart, null) != null)
/*     */     {
/* 446 */       if (element.prefix == null || this.v.getURI(element.prefix) == null) {
/*     */         
/* 448 */         String xmlns = "xmlns" + ((element.prefix != null) ? (":" + element.prefix) : "");
/*     */         
/* 450 */         this.A.setValues(null, xmlns, xmlns, null);
/* 451 */         attrs.addAttribute(this.A, "CDATA", this.u);
/* 452 */         a(element, attrs);
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/* 458 */     attrCount = (attrs != null) ? attrs.getLength() : 0;
/* 459 */     for (int j = 0; j < attrCount; j++) {
/* 460 */       attrs.getName(j, this.A);
/* 461 */       a(this.A);
/* 462 */       prefix = !this.A.rawname.equals("xmlns") ? ((this.A.prefix != null) ? this.A.prefix : "") : "xmlns";
/*     */ 
/*     */       
/* 465 */       if (!prefix.equals("")) {
/* 466 */         this.A.uri = prefix.equals("xml") ? "http://www.w3.org/XML/1998/namespace" : this.v.getURI(prefix);
/*     */       }
/*     */ 
/*     */       
/* 470 */       if (prefix.equals("xmlns") && this.A.uri == null) {
/* 471 */         this.A.uri = "http://www.w3.org/2000/xmlns/";
/*     */       }
/* 473 */       attrs.setName(j, this.A);
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
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements NamespaceContext
/*     */   {
/* 495 */     protected int a = 0;
/*     */ 
/*     */     
/* 498 */     protected int[] b = new int[10];
/*     */ 
/*     */     
/* 501 */     protected a[] c = new a[10];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a() {
/* 509 */       pushContext();
/* 510 */       declarePrefix("xml", NamespaceContext.XML_URI);
/* 511 */       declarePrefix("xmlns", NamespaceContext.XMLNS_URI);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getURI(String prefix) {
/* 522 */       for (int i = this.b[this.a] - 1; i >= 0; i--) {
/* 523 */         a entry = this.c[i];
/* 524 */         if (entry.a.equals(prefix)) {
/* 525 */           return entry.b;
/*     */         }
/*     */       } 
/* 528 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getDeclaredPrefixCount() {
/* 533 */       return this.b[this.a] - this.b[this.a - 1];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDeclaredPrefixAt(int index) {
/* 538 */       return (this.c[this.b[this.a - 1] + index]).a;
/*     */     }
/*     */ 
/*     */     
/*     */     public NamespaceContext a() {
/* 543 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void reset() {
/* 550 */       this.b[this.a = 1] = this.b[this.a - 1];
/*     */     }
/*     */ 
/*     */     
/*     */     public void pushContext() {
/* 555 */       if (++this.a == this.b.length) {
/* 556 */         int[] iarray = new int[this.b.length + 10];
/* 557 */         System.arraycopy(this.b, 0, iarray, 0, this.b.length);
/* 558 */         this.b = iarray;
/*     */       } 
/* 560 */       this.b[this.a] = this.b[this.a - 1];
/*     */     }
/*     */ 
/*     */     
/*     */     public void popContext() {
/* 565 */       if (this.a > 1) {
/* 566 */         this.a--;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean declarePrefix(String prefix, String uri) {
/* 572 */       int count = getDeclaredPrefixCount();
/* 573 */       for (int i = 0; i < count; i++) {
/* 574 */         String dprefix = getDeclaredPrefixAt(i);
/* 575 */         if (dprefix.equals(prefix)) {
/* 576 */           return false;
/*     */         }
/*     */       } 
/* 579 */       a entry = new a(prefix, uri);
/* 580 */       if (this.b[this.a] == this.c.length) {
/* 581 */         a[] earray = new a[this.c.length + 10];
/* 582 */         System.arraycopy(this.c, 0, earray, 0, this.c.length);
/* 583 */         this.c = earray;
/*     */       } 
/* 585 */       this.b[this.a] = this.b[this.a] + 1; this.c[this.b[this.a]] = entry;
/* 586 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPrefix(String uri) {
/* 591 */       for (int i = this.b[this.a] - 1; i >= 0; i--) {
/* 592 */         a entry = this.c[i];
/* 593 */         if (entry.b.equals(uri)) {
/* 594 */           return entry.a;
/*     */         }
/*     */       } 
/* 597 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public Enumeration getAllPrefixes() {
/* 602 */       Vector prefixes = new Vector();
/* 603 */       for (int i = this.b[1]; i < this.b[this.a]; i++) {
/* 604 */         String prefix = (this.c[i]).a;
/* 605 */         if (!prefixes.contains(prefix)) {
/* 606 */           prefixes.addElement(prefix);
/*     */         }
/*     */       } 
/* 609 */       return prefixes.elements();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static class a
/*     */     {
/*     */       public String a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public String b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public a(String prefix, String uri) {
/* 635 */         this.a = prefix;
/* 636 */         this.b = uri;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/filters/d.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */