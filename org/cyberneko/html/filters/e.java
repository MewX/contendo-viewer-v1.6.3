/*     */ package org.cyberneko.html.filters;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.apache.xerces.util.XMLChar;
/*     */ import org.apache.xerces.util.XMLStringBuffer;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XMLString;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLComponentManager;
/*     */ import org.apache.xerces.xni.parser.XMLConfigurationException;
/*     */ import org.cyberneko.html.a;
/*     */ import org.cyberneko.html.b.a;
/*     */ import org.cyberneko.html.g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   extends a
/*     */ {
/*     */   public static final String a = "http://cyberneko.org/html/ns/synthesized/";
/*     */   protected static final String b = "http://xml.org/sax/features/namespaces";
/*     */   protected static final String c = "http://cyberneko.org/html/features/augmentations";
/*  93 */   private static final String[] p = new String[] { "http://xml.org/sax/features/namespaces", "http://cyberneko.org/html/features/augmentations" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   protected static final g f = (g)new g.a();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean g;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean h;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean i;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean j;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean k;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String l;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String m;
/*     */ 
/*     */ 
/*     */   
/*     */   protected NamespaceContext n;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int o;
/*     */ 
/*     */ 
/*     */   
/* 146 */   private QName q = new QName();
/*     */ 
/*     */   
/* 149 */   private final a r = new a();
/*     */ 
/*     */   
/* 152 */   private final XMLStringBuffer s = new XMLStringBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset(XMLComponentManager manager) throws XMLConfigurationException {
/* 162 */     this.k = false;
/*     */ 
/*     */     
/* 165 */     this.g = manager.getFeature("http://xml.org/sax/features/namespaces");
/* 166 */     this.h = manager.getFeature("http://cyberneko.org/html/features/augmentations");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(XMLLocator locator, String encoding, Augmentations augs) throws XNIException {
/* 177 */     this.n = this.g ? new d.a() : null;
/*     */     
/* 179 */     this.o = 0;
/* 180 */     a();
/* 181 */     super.a(locator, encoding, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument(XMLLocator locator, String encoding, NamespaceContext nscontext, Augmentations augs) throws XNIException {
/* 188 */     this.n = nscontext;
/* 189 */     this.o = 0;
/* 190 */     a();
/* 191 */     super.startDocument(locator, encoding, nscontext, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
/* 197 */     if (version == null || !version.equals("1.0")) {
/* 198 */       version = "1.0";
/*     */     }
/* 200 */     if (encoding != null && encoding.length() == 0) {
/* 201 */       encoding = null;
/*     */     }
/* 203 */     if (standalone != null) {
/* 204 */       if (!standalone.equalsIgnoreCase("true") && !standalone.equalsIgnoreCase("false")) {
/*     */         
/* 206 */         standalone = null;
/*     */       } else {
/*     */         
/* 209 */         standalone = standalone.toLowerCase();
/*     */       } 
/*     */     }
/* 212 */     super.xmlDecl(version, encoding, standalone, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(XMLString text, Augmentations augs) throws XNIException {
/* 218 */     StringBuffer str = new StringBuffer(a(text).toString());
/* 219 */     int length = str.length();
/* 220 */     for (int i = length - 1; i >= 0; i--) {
/* 221 */       char c = str.charAt(i);
/* 222 */       if (c == '-') {
/* 223 */         str.insert(i + 1, ' ');
/*     */       }
/*     */     } 
/* 226 */     this.s.length = 0;
/* 227 */     this.s.append(str.toString());
/* 228 */     XMLStringBuffer xMLStringBuffer = this.s;
/* 229 */     super.comment((XMLString)xMLStringBuffer, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
/* 236 */     target = a(target, true);
/* 237 */     data = a(data);
/* 238 */     super.processingInstruction(target, data, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doctypeDecl(String root, String pubid, String sysid, Augmentations augs) throws XNIException {
/* 244 */     this.i = true;
/*     */ 
/*     */     
/* 247 */     this.l = pubid;
/* 248 */     this.m = sysid;
/*     */ 
/*     */     
/* 251 */     if (this.l != null && this.m == null) {
/* 252 */       this.m = "";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 261 */     a(element, attrs);
/* 262 */     super.startElement(element, attrs, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void emptyElement(QName element, XMLAttributes attrs, Augmentations augs) throws XNIException {
/* 268 */     a(element, attrs);
/* 269 */     super.emptyElement(element, attrs, augs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCDATA(Augmentations augs) throws XNIException {
/* 274 */     this.k = true;
/* 275 */     super.startCDATA(augs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endCDATA(Augmentations augs) throws XNIException {
/* 280 */     this.k = false;
/* 281 */     super.endCDATA(augs);
/*     */   }
/*     */ 
/*     */   
/*     */   public void characters(XMLString text, Augmentations augs) throws XNIException {
/*     */     XMLStringBuffer xMLStringBuffer;
/* 287 */     text = a(text);
/* 288 */     if (this.k) {
/* 289 */       StringBuffer str = new StringBuffer(text.toString());
/* 290 */       int length = str.length();
/* 291 */       for (int i = length - 1; i >= 0; i--) {
/* 292 */         char c = str.charAt(i);
/* 293 */         if (c == ']') {
/* 294 */           str.insert(i + 1, ' ');
/*     */         }
/*     */       } 
/* 297 */       this.s.length = 0;
/* 298 */       this.s.append(str.toString());
/* 299 */       xMLStringBuffer = this.s;
/*     */     } 
/* 301 */     super.characters((XMLString)xMLStringBuffer, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(QName element, Augmentations augs) throws XNIException {
/* 307 */     element = a(element);
/* 308 */     if (this.g && 
/* 309 */       element.prefix != null && element.uri == null) {
/* 310 */       element.uri = this.n.getURI(element.prefix);
/*     */     }
/*     */     
/* 313 */     super.endElement(element, augs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a() {
/* 322 */     this.i = false;
/* 323 */     this.j = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(QName element, XMLAttributes attrs) {
/* 330 */     element = a(element);
/* 331 */     int attrCount = (attrs != null) ? attrs.getLength() : 0;
/* 332 */     for (int i = attrCount - 1; i >= 0; i--) {
/*     */       
/* 334 */       attrs.getName(i, this.q);
/* 335 */       attrs.setName(i, a(this.q));
/*     */ 
/*     */       
/* 338 */       if (this.g && 
/* 339 */         !this.q.rawname.equals("xmlns") && !this.q.rawname.startsWith("xmlns:")) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 344 */         attrs.getName(i, this.q);
/* 345 */         if (this.q.prefix != null && this.q.uri == null) {
/* 346 */           a(attrs, this.q.prefix);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 353 */     if (this.g && 
/* 354 */       element.prefix != null && element.uri == null) {
/* 355 */       a(attrs, element.prefix);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 360 */     if (!this.j && this.i) {
/* 361 */       Augmentations augs = b();
/* 362 */       super.doctypeDecl(element.rawname, this.l, this.m, augs);
/*     */     } 
/*     */ 
/*     */     
/* 366 */     this.j = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(XMLAttributes attrs, String ns) {
/* 372 */     String prefix = "xmlns";
/* 373 */     String localpart = ns;
/* 374 */     String qname = prefix + ':' + localpart;
/* 375 */     String uri = "http://cyberneko.org/html/properties/namespaces-uri";
/* 376 */     String atype = "CDATA";
/* 377 */     String avalue = "http://cyberneko.org/html/ns/synthesized/" + this.o++;
/*     */ 
/*     */     
/* 380 */     this.q.setValues(prefix, localpart, qname, uri);
/* 381 */     attrs.addAttribute(this.q, atype, avalue);
/*     */ 
/*     */     
/* 384 */     a.a().a(this.n, ns, avalue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Augmentations b() {
/* 390 */     a augs = null;
/* 391 */     if (this.h) {
/* 392 */       augs = this.r;
/* 393 */       augs.removeAllItems();
/* 394 */       augs.putItem("http://cyberneko.org/html/features/augmentations", f);
/*     */     } 
/* 396 */     return (Augmentations)augs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected QName a(QName qname) {
/* 405 */     qname.prefix = a(qname.prefix, true);
/* 406 */     qname.localpart = a(qname.localpart, true);
/* 407 */     qname.rawname = a(qname.rawname, false);
/* 408 */     return qname;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String a(String name, boolean localpart) {
/* 413 */     if (name == null) {
/* 414 */       return name;
/*     */     }
/* 416 */     StringBuffer str = new StringBuffer();
/* 417 */     int length = name.length();
/* 418 */     boolean seenColon = localpart;
/* 419 */     for (int i = 0; i < length; i++) {
/* 420 */       char c = name.charAt(i);
/* 421 */       if (i == 0) {
/* 422 */         if (!XMLChar.isNameStart(c)) {
/* 423 */           str.append("_u" + a(c, 4) + "_");
/*     */         } else {
/*     */           
/* 426 */           str.append(c);
/*     */         } 
/*     */       } else {
/*     */         
/* 430 */         if ((this.g && c == ':' && seenColon) || !XMLChar.isName(c)) {
/* 431 */           str.append("_u" + a(c, 4) + "_");
/*     */         } else {
/*     */           
/* 434 */           str.append(c);
/*     */         } 
/* 436 */         seenColon = (seenColon || c == ':');
/*     */       } 
/*     */     } 
/* 439 */     return str.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected XMLString a(XMLString text) {
/* 444 */     this.s.length = 0;
/* 445 */     for (int i = 0; i < text.length; i++) {
/* 446 */       char c = text.ch[text.offset + i];
/* 447 */       if (XMLChar.isInvalid(c)) {
/* 448 */         this.s.append("\\u" + a(c, 4));
/*     */       } else {
/*     */         
/* 451 */         this.s.append(c);
/*     */       } 
/*     */     } 
/* 454 */     return (XMLString)this.s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String a(int c, int padlen) {
/* 463 */     StringBuffer str = new StringBuffer(padlen);
/* 464 */     str.append(Integer.toHexString(c));
/* 465 */     int len = padlen - str.length();
/* 466 */     for (int i = 0; i < len; i++) {
/* 467 */       str.insert(0, '0');
/*     */     }
/* 469 */     return str.toString().toUpperCase(Locale.ENGLISH);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/filters/e.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */