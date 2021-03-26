/*     */ package jp.cssj.homare.xml;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.DefaultHandler2;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ 
/*     */ 
/*     */ public class i
/*     */   implements g
/*     */ {
/*     */   protected final LexicalHandler a;
/*     */   protected final ContentHandler b;
/*     */   protected static final DefaultHandler2 c;
/*     */   
/*     */   static {
/*  19 */     c = new DefaultHandler2();
/*     */   }
/*     */   public i(ContentHandler contentHandler, LexicalHandler lexicalHandler) {
/*  22 */     if (lexicalHandler == null) {
/*  23 */       lexicalHandler = c;
/*     */     }
/*  25 */     if (contentHandler == null) {
/*  26 */       contentHandler = c;
/*     */     }
/*  28 */     this.a = lexicalHandler;
/*  29 */     this.b = contentHandler;
/*     */   }
/*     */   
/*     */   public i(g xmlHandler) {
/*  33 */     this.a = xmlHandler;
/*  34 */     this.b = xmlHandler;
/*     */   }
/*     */   
/*     */   public void comment(char[] ch, int off, int len) throws SAXException {
/*  38 */     this.a.comment(ch, off, len);
/*     */   }
/*     */   
/*     */   public void endCDATA() throws SAXException {
/*  42 */     this.a.endCDATA();
/*     */   }
/*     */   
/*     */   public void endDTD() throws SAXException {
/*  46 */     this.a.endDTD();
/*     */   }
/*     */   
/*     */   public void endEntity(String name) throws SAXException {
/*  50 */     this.a.endEntity(name);
/*     */   }
/*     */   
/*     */   public void startCDATA() throws SAXException {
/*  54 */     this.a.startCDATA();
/*     */   }
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  58 */     this.a.startDTD(name, publicId, systemId);
/*     */   }
/*     */   
/*     */   public void startEntity(String name) throws SAXException {
/*  62 */     this.a.startEntity(name);
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  66 */     this.b.characters(ch, start, length);
/*     */   }
/*     */   
/*     */   public void endDocument() throws SAXException {
/*  70 */     this.b.endDocument();
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/*  74 */     this.b.endElement(uri, lName, qName);
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/*  78 */     this.b.endPrefixMapping(prefix);
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  82 */     this.b.ignorableWhitespace(ch, start, length);
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/*  86 */     this.b.processingInstruction(target, data);
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/*  90 */     this.b.setDocumentLocator(locator);
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {
/*  94 */     this.b.skippedEntity(name);
/*     */   }
/*     */   
/*     */   public void startDocument() throws SAXException {
/*  98 */     this.b.startDocument();
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/* 102 */     this.b.startElement(uri, lName, qName, atts);
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 106 */     if (!d && prefix == null) throw new AssertionError(); 
/* 107 */     if (!d && uri == null) throw new AssertionError(); 
/* 108 */     this.b.startPrefixMapping(prefix, uri);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */