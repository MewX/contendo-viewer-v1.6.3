/*     */ package jp.cssj.homare.xml.html;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.Charset;
/*     */ import jp.cssj.a.g;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.b.d;
/*     */ import jp.cssj.homare.xml.e;
/*     */ import jp.cssj.homare.xml.g;
/*     */ import org.apache.xerces.xni.Augmentations;
/*     */ import org.apache.xerces.xni.NamespaceContext;
/*     */ import org.apache.xerces.xni.QName;
/*     */ import org.apache.xerces.xni.XMLAttributes;
/*     */ import org.apache.xerces.xni.XMLLocator;
/*     */ import org.apache.xerces.xni.XNIException;
/*     */ import org.apache.xerces.xni.parser.XMLDocumentFilter;
/*     */ import org.cyberneko.html.filters.a;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
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
/*     */   implements e
/*     */ {
/*     */   public void a(m ua, b source, g xmlHandler) throws SAXException, IOException {
/*  41 */     org.cyberneko.html.a.c parser = new org.cyberneko.html.a.c();
/*     */     
/*  43 */     parser.setProperty("http://cyberneko.org/html/properties/names/elems", "match");
/*  44 */     parser.setProperty("http://cyberneko.org/html/properties/names/attrs", "no-change");
/*  45 */     parser.setFeature("http://cyberneko.org/html/features/scanner/ignore-specified-charset", false);
/*  46 */     parser.setFeature("http://xml.org/sax/features/namespaces", true);
/*  47 */     parser.setFeature("http://cyberneko.org/html/features/scanner/cdata-sections", true);
/*  48 */     parser.setFeature("http://cyberneko.org/html/features/balance-tags", false);
/*     */     
/*  50 */     g balancer = new g();
/*  51 */     XMLDocumentFilter[] filters = { (XMLDocumentFilter)new a(this, ua, balancer)
/*     */         {
/*     */           private boolean f = true;
/*     */           
/*     */           public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
/*  56 */             super.startDocument(locator, encoding, namespaceContext, augs);
/*  57 */             e.a.set(locator);
/*     */           }
/*     */           
/*     */           public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
/*  61 */             if (element.uri != null && (element.prefix == null || element.prefix.length() == 0)) {
/*  62 */               element.uri = null;
/*     */             }
/*  64 */             super.startElement(element, attributes, augs);
/*  65 */             if (this.f && element.localpart.equalsIgnoreCase("body")) {
/*     */               
/*  67 */               if (this.a.c().b() <= 1) {
/*  68 */                 this.b.a(jp.cssj.a.c.a("html4.xml"));
/*     */               }
/*  70 */               this.f = false;
/*     */             } 
/*     */           }
/*     */           
/*     */           public void endElement(QName element, Augmentations augs) throws XNIException {
/*  75 */             if (element.uri != null && (element.prefix == null || element.prefix.length() == 0)) {
/*  76 */               element.uri = null;
/*     */             }
/*  78 */             super.endElement(element, augs);
/*     */           }
/*     */         }, (XMLDocumentFilter)balancer };
/*     */     
/*  82 */     parser.setProperty("http://cyberneko.org/html/properties/filters", filters);
/*     */     
/*  84 */     parser.setProperty("http://xml.org/sax/properties/lexical-handler", xmlHandler);
/*  85 */     parser.setContentHandler((ContentHandler)xmlHandler);
/*     */     
/*  87 */     if (source.i()) {
/*     */       
/*  89 */       a(ua, source, parser);
/*     */     } else {
/*     */       
/*  92 */       b(ua, source, parser);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(m ua, b source, org.cyberneko.html.a.c parser) throws SAXException, IOException {
/*  99 */     try (Reader in = new BufferedReader(source.j())) {
/* 100 */       String encoding = source.a();
/* 101 */       if (encoding != null) {
/* 102 */         ua.c().a(encoding);
/*     */       }
/* 104 */       InputSource inputSource = new InputSource(in);
/* 105 */       parser.parse(inputSource);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(m ua, b source, org.cyberneko.html.a.c parser) throws SAXException, IOException {
/* 113 */     InputStream in = new BufferedInputStream(source.h());
/* 114 */     String encoding = d.a(in);
/*     */     
/* 116 */     if (encoding != null) {
/* 117 */       try (Reader r = new InputStreamReader(in, encoding)) {
/* 118 */         ua.c().a(encoding);
/* 119 */         InputSource inputSource = new InputSource(r);
/* 120 */         parser.parse(inputSource);
/*     */       } 
/*     */     } else {
/*     */       try {
/* 124 */         encoding = B.d.a(ua);
/* 125 */         if (encoding.equalsIgnoreCase("JISUniAutoDetect")) {
/* 126 */           Charset cs = b.a(in);
/* 127 */           if (cs != null) {
/* 128 */             encoding = cs.name();
/*     */           }
/*     */         } 
/* 131 */         String declEncoding = d.b(in);
/* 132 */         if (declEncoding != null) {
/* 133 */           encoding = declEncoding;
/*     */         }
/* 135 */         parser.setProperty("http://cyberneko.org/html/properties/default-encoding", encoding);
/* 136 */         ua.c().a(encoding);
/* 137 */         InputSource inputSource = new InputSource(in);
/* 138 */         parser.parse(inputSource);
/*     */       } finally {
/* 140 */         in.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/html/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */