/*      */ package jp.cssj.homare.css;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.StringReader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.text.ParseException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import jp.cssj.b.a.g;
/*      */ import jp.cssj.homare.css.a.a;
/*      */ import jp.cssj.homare.css.a.c;
/*      */ import jp.cssj.homare.css.d.e;
/*      */ import jp.cssj.homare.impl.a.c.G;
/*      */ import jp.cssj.homare.ua.a.B;
/*      */ import jp.cssj.homare.ua.m;
/*      */ import jp.cssj.homare.xml.g;
/*      */ import jp.cssj.sakae.sac.css.CSSException;
/*      */ import jp.cssj.sakae.sac.css.InputSource;
/*      */ import jp.cssj.sakae.sac.parser.Parser;
/*      */ import org.apache.xerces.xni.XMLLocator;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.helpers.AttributesImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class b
/*      */   implements g
/*      */ {
/*      */   static {
/*   63 */     b = Logger.getLogger(b.class.getName());
/*   64 */     c = new AttributesImpl();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  236 */     z = new HashSet<>();
/*      */ 
/*      */     
/*  239 */     z.add("-//W3C//DTD HTML 4.01//EN".toLowerCase());
/*  240 */     z.add("-//W3C//DTD HTML 4.01 Transitional//EN".toLowerCase());
/*  241 */     z.add("-//W3C//DTD XHTML 1.0 Transitional//EN".toLowerCase());
/*  242 */     z.add("-//W3C//DTD XHTML 1.0 Strict//EN".toLowerCase());
/*  243 */     z.add("-//W3C//DTD HTML 4.01//EN".toLowerCase());
/*  244 */     z.add("-//W3C//DTD XHTML 1.1//EN".toLowerCase());
/*      */   }
/*      */   private final AttributesImpl d = new AttributesImpl(); private jp.cssj.homare.xml.f j = null; private String k = "text/css"; private boolean l = true; private a m = null; private e n = null; private int o = 0; private List<d> p = null; private List<e> q = null; private boolean s = false; private h t = null; private int u = 0; private Map<String, String> w = new HashMap<>(); private static final Logger b; private static final Attributes c; private final m e;
/*      */   
/*  248 */   public void startDTD(String name, String publicId, String systemId) throws SAXException { if (publicId == null) {
/*      */       return;
/*      */     }
/*  251 */     if (this.n == null && 
/*  252 */       z.contains(publicId.toLowerCase()))
/*  253 */       this.e.c().a((byte)1);  }
/*      */   private final jp.cssj.homare.b.d.b f;
/*      */   private final e g;
/*      */   private final Parser h; private final k i; private int r; private c v; private XMLLocator x; private Locator y; private static final Set<String> z; public b(m ua, jp.cssj.homare.b.d.b imposition) { this.e = ua; this.f = imposition; l styleContext = new l(new d()); this.g = new e(this.e); this.g.a(styleContext.a); this.i = new k(ua, styleContext); this.h = new Parser(); this.h.setDocumentHandler(this.g); String defaultStyle = B.e.a(this.e); if (defaultStyle != null)
/*      */       try { URI defaultStyleURI = jp.cssj.e.e.d.a(this.e.c().c(), defaultStyle); jp.cssj.e.b styleSource = this.e.b(defaultStyleURI); try { InputSource inputSource = jp.cssj.homare.xml.b.d.a(styleSource, styleSource.a(), null, null); try { this.h.parseStyleSheet(inputSource); }
/*      */           catch (CSSException cSSException) { this.e.a((short)10241, inputSource.getURI(), cSSException.getMessage()); }
/*      */            }
/*      */         finally { this.e.a(styleSource); }
/*      */          }
/*      */       catch (URISyntaxException uRISyntaxException) { this.e.a((short)10243, defaultStyle); }
/*      */       catch (IOException iOException) { this.e.a((short)10243, defaultStyle); }
/*      */         } private void a(String href, String type, String title, String mediaTypes, String charset, boolean alternate) { URI uri; boolean apply; if (href == null)
/*      */       return;  if (type == null)
/*      */       type = this.k;  if (!g.a(type, "text/css"))
/*      */       return;  try {
/*      */       uri = this.i.b(); uri = jp.cssj.e.e.d.a(this.e.c().c(), uri, href);
/*      */     } catch (URISyntaxException uRISyntaxException) {
/*      */       this.e.a((short)10243, href); return;
/*      */     }  if (this.j != null) {
/*      */       apply = this.j.a(uri, "text/css", title, mediaTypes, alternate);
/*      */     } else {
/*      */       apply = !alternate;
/*      */     }  if (apply && !this.e.b(mediaTypes))
/*      */       apply = false;  if (apply)
/*      */       try {
/*      */         jp.cssj.e.b source = this.e.b(uri); try {
/*      */           if (charset == null)
/*      */             charset = this.e.c().c();  a(jp.cssj.homare.xml.b.d.a(source, source.a(), mediaTypes, title), charset);
/*      */         } finally {
/*      */           this.e.a(source);
/*      */         } 
/*      */       } catch (IOException iOException) {
/*      */         this.e.a((short)10243, href);
/*      */       }   } private void a(InputSource inputSource, String defaultCharset) throws IOException { try {
/*      */       if (defaultCharset != null)
/*      */         this.h.setDefaultCharset(defaultCharset);  this.h.parseStyleSheet(inputSource);
/*      */     } catch (CSSException cSSException) {
/*      */       this.e.a((short)10241, inputSource.getURI(), cSSException.getMessage());
/*  291 */     }  } public void processingInstruction(String target, String data) throws SAXException { if (target.equals("xml-stylesheet")) {
/*      */       try {
/*      */         boolean alternate;
/*  294 */         jp.cssj.homare.xml.b.d.a(data.toCharArray(), 0, data.length(), this.d);
/*  295 */         String type = this.d.getValue("type");
/*  296 */         String href = this.d.getValue("href");
/*  297 */         String title = this.d.getValue("title");
/*  298 */         String mediaTypes = this.d.getValue("media");
/*  299 */         String charset = this.d.getValue("charset");
/*  300 */         String alternateStr = this.d.getValue("alternate");
/*      */ 
/*      */         
/*  303 */         if (alternateStr != null && alternateStr.equals("yes")) {
/*  304 */           alternate = true;
/*      */         } else {
/*  306 */           alternate = false;
/*      */         } 
/*  308 */         a(href, type, title, mediaTypes, charset, alternate);
/*  309 */       } catch (ParseException parseException) {
/*  310 */         this.e.a((short)10245, "xml-stylesheet", data);
/*      */       } 
/*  312 */       this.d.clear();
/*  313 */     } else if (target.equals("jp.cssj.stylesheet")) {
/*      */       
/*      */       try {
/*  316 */         String styleSheet = jp.cssj.homare.xml.b.d.a(data.toCharArray(), 0, data.length(), this.d);
/*  317 */         String media = this.d.getValue("media");
/*  318 */         String type = this.d.getValue("type");
/*      */         
/*  320 */         if (type == null) {
/*  321 */           type = this.k;
/*      */         }
/*  323 */         if (type.equals("text/css") && this.e.b(media)) {
/*  324 */           InputSource inputSource = new InputSource(new StringReader(styleSheet));
/*  325 */           inputSource.setEncoding(this.e.c().c());
/*  326 */           inputSource.setMedia(media);
/*  327 */           inputSource.setURI(this.i.b().toString());
/*      */           try {
/*  329 */             a(inputSource, (String)null);
/*  330 */           } catch (IOException iOException) {
/*  331 */             throw new SAXException(iOException);
/*      */           } 
/*      */         } 
/*  334 */       } catch (ParseException parseException) {
/*  335 */         this.e.a((short)10245, "jp.cssj.stylesheet", data);
/*      */       } 
/*  337 */       this.d.clear();
/*  338 */     } else if (target.equals("jp.cssj.document-info")) {
/*      */       
/*      */       try {
/*  341 */         jp.cssj.homare.xml.b.d.a(data.toCharArray(), 0, data.length(), this.d);
/*  342 */         String name = this.d.getValue("name");
/*  343 */         String value = this.d.getValue("value");
/*  344 */         this.e.b(name, value);
/*  345 */       } catch (ParseException parseException) {
/*  346 */         this.e.a((short)10245, "jp.cssj.document-info", data);
/*      */       } 
/*  348 */       this.d.clear();
/*  349 */     } else if (target.equals("jp.cssj.default-encoding")) {
/*      */       
/*      */       try {
/*  352 */         this.e.c().a(data);
/*  353 */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  354 */         this.e.a((short)10269, data);
/*      */       } 
/*  356 */     } else if (target.equals("jp.cssj.default-style-type")) {
/*      */       
/*  358 */       this.k = data;
/*  359 */     } else if (target.equals("jp.cssj.base-uri")) {
/*      */ 
/*      */       
/*      */       try {
/*  363 */         URI uri = this.i.b();
/*  364 */         uri = jp.cssj.e.e.d.a(this.e.c().c(), uri, data);
/*  365 */         this.e.c().a(uri);
/*  366 */         this.i.a(uri);
/*  367 */       } catch (URISyntaxException uRISyntaxException) {
/*  368 */         this.e.a((short)10252, uRISyntaxException.getMessage());
/*      */       } 
/*      */     }  }
/*      */   public void a(jp.cssj.homare.xml.f ssh) { this.j = ssh; }
/*      */   public void startDocument() throws SAXException {}
/*      */   public void endDocument() throws SAXException { b(); this.n.g(); } public void setDocumentLocator(Locator locator) { this.x = jp.cssj.homare.xml.e.a.get();
/*  374 */     this.y = locator; } public void endDTD() throws SAXException {} public void comment(char[] ch, int off, int len) throws SAXException {} public void startCDATA() throws SAXException {} public void endCDATA() throws SAXException {} public void startEntity(String name) throws SAXException {} public void skippedEntity(String name) throws SAXException {} public void endEntity(String name) throws SAXException {} public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {} private void b() { if (this.n == null)
/*      */     {
/*  376 */       this.n = new e(this.i.a(), this.e, this.f);
/*      */     } }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/*  382 */     b();
/*      */     
/*  384 */     int charOffset = this.x.getCharacterOffset();
/*      */ 
/*      */     
/*  387 */     if (this.p != null) {
/*  388 */       e e1 = new e(charOffset, uri, lName, qName, atts);
/*  389 */       this.q.add(e1);
/*  390 */       this.p.add(e1);
/*      */     } else {
/*  392 */       a(charOffset, uri, lName, qName, atts);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void characters(char[] ch, int off, int len) throws SAXException {
/*  397 */     if (len == 0) {
/*      */       return;
/*      */     }
/*      */     
/*  401 */     int charOffset = this.x.getCharacterOffset() - len;
/*      */ 
/*      */     
/*  404 */     if (this.p != null) {
/*  405 */       this.p.add(new a(charOffset, ch, off, len));
/*      */     } else {
/*  407 */       a(charOffset, ch, off, len);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void endElement(String uri, String lName, String qName) throws SAXException {
/*  413 */     if (this.p != null) {
/*  414 */       if (this.q.size() == 1) {
/*  415 */         for (this.r = 0; this.r < this.p.size(); this.r++) {
/*  416 */           d d = this.p.get(this.r);
/*  417 */           d.a(this);
/*      */         } 
/*  419 */         a(uri, lName, qName);
/*  420 */         this.p = null;
/*  421 */         this.q = null;
/*      */       } else {
/*  423 */         e e1 = this.q.remove(this.q.size() - 1);
/*  424 */         e1.k = this.p.size();
/*  425 */         this.p.add(new b(e1));
/*      */       } 
/*      */     } else {
/*  428 */       a(uri, lName, qName);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  433 */     if (this.p != null) {
/*  434 */       this.p.add(new f(prefix, uri));
/*      */     } else {
/*  436 */       a(prefix, uri);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void endPrefixMapping(String prefix) throws SAXException {
/*  441 */     if (this.p != null) {
/*  442 */       this.p.add(new c(prefix));
/*      */     } else {
/*  444 */       a(prefix);
/*      */     } 
/*      */   }
/*      */   
/*      */   void a(int charOffset, String uri, String lName, String qName, Attributes atts) throws SAXException {
/*      */     String[] styleClasses;
/*      */     byte[] pseudoClasses;
/*      */     Locale loca;
/*  452 */     if (this.o > 0) {
/*  453 */       this.o++;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  458 */     if (this.u > 0) {
/*      */       try {
/*  460 */         this.t.startElement(uri, lName, qName, atts);
/*  461 */       } catch (Exception exception) {
/*  462 */         this.e.a((short)10259, exception.getMessage());
/*      */       } 
/*  464 */       this.u++;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  469 */     String href = jp.cssj.homare.xml.b.j.a(atts);
/*      */ 
/*      */     
/*  472 */     String styleClass = atts.getValue("http://www.w3.org/1999/xhtml", jp.cssj.homare.xml.c.a.E.b);
/*      */     
/*  474 */     if (styleClass == null) {
/*  475 */       styleClasses = null;
/*  476 */     } else if (styleClass.indexOf(' ') == -1) {
/*  477 */       styleClasses = new String[] { styleClass };
/*      */     } else {
/*  479 */       List<String> list = new ArrayList<>();
/*  480 */       for (StringTokenizer i = new StringTokenizer(styleClass, " "); i.hasMoreTokens();) {
/*  481 */         list.add(i.nextToken());
/*      */       }
/*  483 */       styleClasses = list.<String>toArray(new String[list.size()]);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  489 */     int len = 0;
/*  490 */     if (this.l) {
/*  491 */       len++;
/*      */     }
/*  493 */     if (href != null) {
/*  494 */       len++;
/*      */     }
/*  496 */     if (this.s) {
/*  497 */       len++;
/*      */     }
/*  499 */     if (len == 0) {
/*  500 */       pseudoClasses = null;
/*      */     } else {
/*  502 */       pseudoClasses = new byte[len];
/*      */     } 
/*      */     
/*  505 */     if (this.l) {
/*  506 */       pseudoClasses[--len] = 6;
/*      */     }
/*  508 */     if (href != null) {
/*  509 */       pseudoClasses[--len] = 7;
/*      */     }
/*  511 */     if (this.s) {
/*  512 */       pseudoClasses[--len] = 101;
/*      */     }
/*      */     
/*  515 */     this.l = true;
/*      */ 
/*      */     
/*  518 */     String id = atts.getValue("http://www.w3.org/1999/xhtml", jp.cssj.homare.xml.c.a.D.b);
/*      */ 
/*      */     
/*  521 */     String lang = atts.getValue("http://www.w3.org/1999/xhtml", jp.cssj.homare.xml.c.a.G.b);
/*  522 */     if (lang != null) {
/*  523 */       lang = lang.trim().toLowerCase();
/*      */     }
/*      */ 
/*      */     
/*  527 */     if (atts.getLength() == 0) {
/*  528 */       atts = c;
/*      */     } else {
/*  530 */       atts = new AttributesImpl(atts);
/*      */     } 
/*      */ 
/*      */     
/*  534 */     URI link = null;
/*  535 */     c parentStyle = this.n.c();
/*  536 */     if (href != null) {
/*      */       try {
/*  538 */         short conf = B.at.a(this.e);
/*  539 */         if (conf == 1 || href.startsWith("#")) {
/*      */           
/*  541 */           link = jp.cssj.e.e.d.a(this.e.c().c(), href);
/*  542 */           if (link.isAbsolute()) {
/*  543 */             link = this.e.c().a().relativize(link);
/*      */           }
/*      */         } else {
/*      */           URI base;
/*      */           
/*  548 */           String str = B.au.a(this.e);
/*  549 */           if (str == null) {
/*  550 */             base = this.e.c().a();
/*      */           } else {
/*      */             try {
/*  553 */               base = jp.cssj.e.e.d.a(this.e.c().c(), str);
/*  554 */             } catch (URISyntaxException uRISyntaxException) {
/*  555 */               this.e.a((short)10244, B.au.a, str);
/*      */               
/*  557 */               base = this.e.c().a();
/*      */             } 
/*      */           } 
/*  560 */           link = jp.cssj.e.e.d.a(this.e.c().c(), base, href);
/*      */         } 
/*      */         
/*  563 */         AttributesImpl attsi = (AttributesImpl)atts;
/*  564 */         jp.cssj.homare.xml.b.j.a(attsi);
/*  565 */       } catch (URISyntaxException uRISyntaxException) {
/*  566 */         this.e.a((short)10252, uRISyntaxException.getMessage());
/*      */       } 
/*  568 */     } else if (parentStyle != null) {
/*  569 */       link = jp.cssj.homare.impl.a.c.c.f.c(parentStyle);
/*  570 */       if (atts.getLength() == 0) {
/*  571 */         atts = new AttributesImpl(atts);
/*      */       }
/*      */     } 
/*  574 */     if (link != null) {
/*  575 */       AttributesImpl attsi = (AttributesImpl)atts;
/*  576 */       jp.cssj.homare.xml.b.j.a(attsi, link.toASCIIString());
/*      */     } 
/*      */ 
/*      */     
/*  580 */     if (lang == null) {
/*  581 */       loca = null;
/*  582 */     } else if (lang.equals("ja")) {
/*  583 */       loca = Locale.JAPANESE;
/*  584 */     } else if (lang.equals("en")) {
/*  585 */       loca = Locale.ENGLISH;
/*      */     } else {
/*  587 */       loca = new Locale(lang);
/*      */     } 
/*      */     
/*  590 */     a ce = new a(uri, lName, id, styleClasses, pseudoClasses, loca, atts, this.m, charOffset);
/*      */     
/*  592 */     this.m = null;
/*      */ 
/*      */     
/*  595 */     c style = c.a(this.e, parentStyle, ce);
/*  596 */     this.i.a(style);
/*  597 */     if (link != null) {
/*  598 */       jp.cssj.homare.impl.a.c.c.f.a(style, link);
/*      */     }
/*      */ 
/*      */     
/*  602 */     short display = (short)G.c(style);
/*  603 */     if (display == 0) {
/*      */       
/*  605 */       this.i.c();
/*  606 */       this.o = 1;
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  612 */     InlineObjectFactory factory = (InlineObjectFactory)jp.cssj.c.b.a().a(InlineObjectFactory.class, ce);
/*  613 */     if (factory != null) {
/*  614 */       if (this.t == null) {
/*  615 */         this.t = factory.createInlineObject();
/*  616 */         this.t.setDocumentLocator(this.y);
/*      */       } 
/*  618 */       if (!this.w.isEmpty()) {
/*  619 */         this.d.clear();
/*  620 */         this.d.setAttributes(atts);
/*  621 */         atts = this.d;
/*  622 */         for (Iterator<?> i = this.w.entrySet().iterator(); i.hasNext(); ) {
/*  623 */           Map.Entry<?, ?> entry = (Map.Entry<?, ?>)i.next();
/*  624 */           String prefix = (String)entry.getKey();
/*  625 */           String namespaceURI = (String)entry.getValue();
/*  626 */           this.d.addAttribute("http://www.w3.org/2000/xmlns/", prefix, 
/*  627 */               (prefix.length() == 0) ? "xmlns" : ("xmlns:" + prefix), "CDATA", namespaceURI);
/*      */         } 
/*      */       } 
/*      */       try {
/*  631 */         this.t.startDocument();
/*  632 */         this.t.startElement(uri, lName, qName, atts);
/*  633 */       } catch (Exception exception) {
/*  634 */         b.log(Level.FINE, "", exception);
/*  635 */         this.e.a((short)10259, exception.getMessage());
/*      */       } 
/*  637 */       this.u = 1;
/*  638 */       c.a(lName, style);
/*  639 */       this.v = style;
/*      */       
/*      */       return;
/*      */     } 
/*  643 */     this.n.a(style);
/*      */ 
/*      */     
/*  646 */     String text = jp.cssj.homare.impl.a.c.c.e.d(style);
/*  647 */     if (text != null && text.length() > 0) {
/*  648 */       char[] ch = text.toCharArray();
/*  649 */       this.n.a(-1, ch, 0, ch.length);
/*      */     } 
/*  651 */     if (jp.cssj.homare.impl.a.c.c.e.c(style) != null)
/*      */     {
/*  653 */       this.o = 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void a(int charOffset, char[] ch, int off, int len) throws SAXException {
/*  659 */     if (!a && len <= 0) throw new AssertionError(); 
/*  660 */     if (this.u > 0) {
/*      */       
/*      */       try {
/*  663 */         this.t.characters(ch, off, len);
/*  664 */       } catch (Exception exception) {
/*  665 */         b.log(Level.FINE, "", exception);
/*  666 */         this.e.a((short)10259, exception.getMessage());
/*      */       } 
/*  668 */     } else if (this.o <= 0 && len > 0) {
/*      */       
/*  670 */       if (this.s) {
/*  671 */         for (int i = 0; i < len; i++) {
/*  672 */           if (!Character.isWhitespace(ch[i + off])) {
/*  673 */             this.s = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  678 */       if (this.n != null) {
/*  679 */         this.n.a(charOffset, ch, off, len);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void a(String uri, String lName, String qName) throws SAXException {
/*  686 */     if (this.u > 0) {
/*      */       try {
/*  688 */         this.t.endElement(uri, lName, qName);
/*  689 */       } catch (Exception exception) {
/*  690 */         b.log(Level.FINE, "", exception);
/*  691 */         this.e.a((short)10259, exception.getMessage());
/*      */       } 
/*  693 */       this.u--;
/*  694 */       if (this.u == 0) {
/*      */         
/*  696 */         jp.cssj.sakae.c.b.b image = null;
/*      */         try {
/*  698 */           this.t.endDocument();
/*  699 */           image = this.t.a(this.e);
/*  700 */         } catch (Exception exception) {
/*  701 */           b.log(Level.FINE, "", exception);
/*  702 */           this.e.a((short)10259, exception.getMessage());
/*      */         } 
/*  704 */         if (image != null) {
/*  705 */           jp.cssj.homare.impl.a.c.c.e.a(this.v, image);
/*  706 */           this.n.a(this.v);
/*  707 */           this.n.d();
/*      */         } else {
/*  709 */           this.e.a((short)10259, "インラインオブジェクトを読み込めませんでした");
/*      */         } 
/*  711 */         this.v = null;
/*  712 */         this.t = null;
/*  713 */         this.i.c();
/*      */       } 
/*      */       return;
/*      */     } 
/*  717 */     c currentStyle = this.n.c();
/*  718 */     if (this.o > 0) {
/*  719 */       this.o--;
/*  720 */       if (this.o == 0) {
/*  721 */         if (currentStyle == null) {
/*      */           return;
/*      */         }
/*      */         
/*  725 */         if (jp.cssj.homare.impl.a.c.c.e.c(currentStyle) == null) {
/*      */           return;
/*      */         }
/*      */       } else {
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  733 */     this.n.d();
/*  734 */     this.i.c();
/*  735 */     this.m = currentStyle.e().a();
/*  736 */     this.l = false;
/*  737 */     this.s = false;
/*      */   }
/*      */   
/*      */   void a(String prefix, String uri) throws SAXException {
/*  741 */     this.w.put(prefix, uri);
/*  742 */     if (this.u > 0) {
/*      */       try {
/*  744 */         this.t.startPrefixMapping(prefix, uri);
/*  745 */       } catch (Exception exception) {
/*  746 */         b.log(Level.FINE, "", exception);
/*  747 */         this.e.a((short)10259, exception.getMessage());
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   void a(String prefix) throws SAXException {
/*  753 */     this.w.remove(prefix);
/*  754 */     if (this.u > 0) {
/*      */       try {
/*  756 */         this.t.endPrefixMapping(prefix);
/*  757 */       } catch (Exception exception) {
/*  758 */         b.log(Level.FINE, "", exception);
/*  759 */         this.e.a((short)10259, exception.getMessage());
/*      */       } 
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
/*      */   static boolean a(c parentStyle, a ce) {
/*  772 */     if (parentStyle == null) {
/*  773 */       return false;
/*      */     }
/*  775 */     short code = a.a(ce);
/*  776 */     if (code == 0) {
/*  777 */       return true;
/*      */     }
/*  779 */     switch (code) {
/*      */       case 502:
/*      */       case 603:
/*      */       case 804:
/*      */       case 805:
/*      */       case 1301:
/*      */       case 1704:
/*      */       case 1804:
/*  787 */         return false;
/*      */     } 
/*  789 */     switch (G.c(parentStyle)) {
/*      */       case 6:
/*      */       case 7:
/*  792 */         switch (code) {
/*      */           case 301:
/*      */           case 305:
/*      */           case 306:
/*      */           case 1802:
/*      */           case 1803:
/*      */           case 1805:
/*      */           case 1806:
/*      */           case 1807:
/*      */           case 1809:
/*  802 */             return false;
/*      */         } 
/*  804 */         return true;
/*      */ 
/*      */       
/*      */       case 8:
/*      */       case 11:
/*      */       case 12:
/*  810 */         switch (code) {
/*      */           case 301:
/*      */           case 1803:
/*      */           case 1806:
/*      */           case 1809:
/*  815 */             return false;
/*      */         } 
/*  817 */         return true;
/*      */ 
/*      */       
/*      */       case 9:
/*      */       case 10:
/*  822 */         switch (code) {
/*      */           case 301:
/*      */           case 305:
/*      */           case 306:
/*  826 */             return false;
/*      */         } 
/*  828 */         return true;
/*      */ 
/*      */       
/*      */       case 13:
/*  832 */         switch (code) {
/*      */           case 301:
/*      */           case 1803:
/*      */           case 1806:
/*  836 */             return false;
/*      */         } 
/*  838 */         return true;
/*      */     } 
/*      */     
/*  841 */     switch (code) {
/*      */       case 1803:
/*      */       case 1806:
/*  844 */         switch (G.c(parentStyle)) {
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/*      */           case 11:
/*      */           case 12:
/*      */           case 13:
/*  851 */             return false;
/*      */         } 
/*  853 */         return true;
/*      */ 
/*      */       
/*      */       case 1809:
/*  857 */         switch (G.c(parentStyle)) {
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/*      */           case 11:
/*      */           case 12:
/*  863 */             return false;
/*      */         } 
/*  865 */         return true;
/*      */ 
/*      */       
/*      */       case 1802:
/*      */       case 1805:
/*      */       case 1807:
/*  871 */         switch (G.c(parentStyle)) {
/*      */           case 6:
/*      */           case 7:
/*  874 */             return false;
/*      */         } 
/*  876 */         return true;
/*      */ 
/*      */       
/*      */       case 305:
/*      */       case 306:
/*  881 */         switch (G.c(parentStyle)) {
/*      */           case 6:
/*      */           case 7:
/*      */           case 9:
/*      */           case 10:
/*  886 */             return false;
/*      */         } 
/*  888 */         return true;
/*      */     } 
/*      */ 
/*      */     
/*  892 */     return false;
/*      */   }
/*      */   
/*      */   static interface d {
/*      */     public static final byte c = 1;
/*      */     public static final byte d = 2;
/*      */     public static final byte e = 3;
/*      */     public static final byte f = 4;
/*      */     public static final byte g = 5;
/*      */     
/*      */     byte b();
/*      */     
/*      */     void a(b param1b) throws SAXException; }
/*      */   
/*      */   static class e implements d { final int a;
/*      */     final String b;
/*      */     final String h;
/*      */     final String i;
/*      */     final Attributes j;
/*      */     int k;
/*      */     
/*      */     e(int charOffset, String uri, String lName, String qName, Attributes atts) {
/*  914 */       this.a = charOffset;
/*  915 */       this.b = uri;
/*  916 */       this.h = lName;
/*  917 */       this.i = qName;
/*  918 */       this.j = (atts.getLength() == 0) ? b.a() : new AttributesImpl(atts);
/*      */     }
/*      */     
/*      */     public void a(b p) throws SAXException {
/*  922 */       p.a(this.a, this.b, this.h, this.i, this.j);
/*      */     }
/*      */     
/*      */     public byte b() {
/*  926 */       return 1;
/*      */     } }
/*      */ 
/*      */   
/*      */   static class a implements d {
/*      */     final int a;
/*      */     final char[] b;
/*      */     
/*      */     a(int charOffset, char[] ch, int off, int len) {
/*  935 */       this.a = charOffset;
/*  936 */       this.b = new char[len];
/*  937 */       System.arraycopy(ch, off, this.b, 0, len);
/*      */     }
/*      */     
/*      */     public void a(b p) throws SAXException {
/*  941 */       p.a(this.a, this.b, 0, this.b.length);
/*      */     }
/*      */     
/*      */     public boolean a() {
/*  945 */       for (int i = 0; i < this.b.length; i++) {
/*  946 */         if (!Character.isWhitespace(this.b[i])) {
/*  947 */           return false;
/*      */         }
/*      */       } 
/*  950 */       return true;
/*      */     }
/*      */     
/*      */     public byte b() {
/*  954 */       return 2;
/*      */     }
/*      */   }
/*      */   
/*      */   static class b implements d {
/*      */     final b.e a;
/*      */     
/*      */     b(b.e e1) {
/*  962 */       this.a = e1;
/*      */     }
/*      */     
/*      */     public void a(b p) throws SAXException {
/*  966 */       p.a(this.a.b, this.a.h, this.a.i);
/*      */     }
/*      */     
/*      */     public byte b() {
/*  970 */       return 3;
/*      */     } }
/*      */   
/*      */   static class f implements d {
/*      */     final String a;
/*      */     final String b;
/*      */     
/*      */     f(String prefix, String uri) {
/*  978 */       this.b = uri;
/*  979 */       this.a = prefix;
/*      */     }
/*      */     
/*      */     public void a(b p) throws SAXException {
/*  983 */       p.a(this.a, this.b);
/*      */     }
/*      */     
/*      */     public byte b() {
/*  987 */       return 4;
/*      */     }
/*      */   }
/*      */   
/*      */   static class c implements d {
/*      */     final String a;
/*      */     
/*      */     c(String prefix) {
/*  995 */       this.a = prefix;
/*      */     }
/*      */     
/*      */     public void a(b p) throws SAXException {
/*  999 */       p.a(this.a);
/*      */     }
/*      */     
/*      */     public byte b() {
/* 1003 */       return 5;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */