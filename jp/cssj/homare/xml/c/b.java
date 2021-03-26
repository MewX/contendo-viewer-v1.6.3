/*     */ package jp.cssj.homare.xml.c;
/*     */ 
/*     */ import jp.cssj.homare.xml.c;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends c
/*     */ {
/*  19 */   private final AttributesImpl a = new AttributesImpl();
/*  20 */   private int b = 0;
/*     */   
/*     */   public void startDocument() throws SAXException {
/*  23 */     super.startDocument();
/*  24 */     super.startPrefixMapping("", "http://www.w3.org/1999/xhtml");
/*     */   }
/*     */   
/*     */   public void endDocument() throws SAXException {
/*  28 */     super.endPrefixMapping("");
/*  29 */     super.endDocument();
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  33 */     if (prefix.length() == 0) {
/*  34 */       this.b++;
/*     */     }
/*  36 */     if (uri == null || uri.length() == 0) {
/*  37 */       uri = "http://www.w3.org/1999/xhtml";
/*     */     }
/*  39 */     super.startPrefixMapping(prefix, uri);
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/*  43 */     if (prefix.length() == 0) {
/*  44 */       this.b--;
/*     */     }
/*  46 */     super.endPrefixMapping(prefix);
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/*     */     boolean isHTML;
/*  51 */     if (uri == null || uri.length() == 0 || (this.b == 0 && lName.equalsIgnoreCase(qName))) {
/*  52 */       uri = "http://www.w3.org/1999/xhtml";
/*  53 */       lName = qName = qName.toLowerCase();
/*  54 */       isHTML = true;
/*  55 */     } else if (uri.equals("http://www.w3.org/1999/xhtml")) {
/*  56 */       isHTML = true;
/*  57 */       lName = lName.toLowerCase();
/*  58 */       int colon = qName.indexOf(':');
/*  59 */       if (colon == -1) {
/*  60 */         qName = qName.toLowerCase();
/*     */       } else {
/*  62 */         qName = qName.substring(0, colon + 1) + lName;
/*     */       } 
/*     */     } else {
/*  65 */       isHTML = false;
/*     */     } 
/*  67 */     if (isHTML) {
/*  68 */       this.a.clear();
/*  69 */       for (int i = 0; i < atts.getLength(); i++) {
/*  70 */         String attUri = atts.getURI(i);
/*  71 */         String attLName = atts.getLocalName(i);
/*  72 */         String attQName = atts.getQName(i);
/*  73 */         if (attUri == null || attUri.length() == 0 || attLName.equalsIgnoreCase(attQName)) {
/*  74 */           attUri = "http://www.w3.org/1999/xhtml";
/*  75 */           attLName = attQName = attLName.toLowerCase();
/*  76 */         } else if (attUri.equals("http://www.w3.org/1999/xhtml")) {
/*  77 */           attLName = attLName.toLowerCase();
/*  78 */           int colon = attQName.indexOf(':');
/*  79 */           if (colon == -1) {
/*  80 */             attQName = attQName.toLowerCase();
/*     */           } else {
/*  82 */             attQName = attQName.substring(0, colon + 1) + attLName;
/*     */           } 
/*     */         } 
/*  85 */         this.a.addAttribute(attUri, attLName, attQName, atts.getType(i), atts.getValue(i));
/*     */       } 
/*  87 */       atts = this.a;
/*     */     } 
/*  89 */     super.startElement(uri, lName, qName, atts);
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/*  93 */     if (uri == null || uri.length() == 0 || (this.b == 0 && lName.equalsIgnoreCase(qName))) {
/*  94 */       uri = "http://www.w3.org/1999/xhtml";
/*  95 */       lName = qName = lName.toLowerCase();
/*  96 */     } else if (uri.equals("http://www.w3.org/1999/xhtml")) {
/*  97 */       lName = lName.toLowerCase();
/*  98 */       int colon = qName.indexOf(':');
/*  99 */       if (colon == -1) {
/* 100 */         qName = qName.toLowerCase();
/*     */       } else {
/* 102 */         qName = qName.substring(0, colon + 1) + lName;
/*     */       } 
/*     */     } 
/* 105 */     super.endElement(uri, lName, qName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */