/*     */ package jp.cssj.homare.xml;
/*     */ 
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements h
/*     */ {
/*     */   protected g e;
/*     */   
/*     */   public c() {}
/*     */   
/*     */   public c(g outHandler) {
/*  21 */     this.e = outHandler;
/*     */   }
/*     */   
/*     */   public void comment(char[] ch, int off, int len) throws SAXException {
/*  25 */     this.e.comment(ch, off, len);
/*     */   }
/*     */   
/*     */   public void endCDATA() throws SAXException {
/*  29 */     this.e.endCDATA();
/*     */   }
/*     */   
/*     */   public void endDTD() throws SAXException {
/*  33 */     this.e.endDTD();
/*     */   }
/*     */   
/*     */   public void endEntity(String name) throws SAXException {
/*  37 */     this.e.endEntity(name);
/*     */   }
/*     */   
/*     */   public void startCDATA() throws SAXException {
/*  41 */     this.e.startCDATA();
/*     */   }
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  45 */     this.e.startDTD(name, publicId, systemId);
/*     */   }
/*     */   
/*     */   public void startEntity(String name) throws SAXException {
/*  49 */     this.e.startEntity(name);
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/*  53 */     this.e.characters(ch, start, length);
/*     */   }
/*     */   
/*     */   public void endDocument() throws SAXException {
/*  57 */     this.e.endDocument();
/*     */   }
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/*  61 */     this.e.endElement(uri, lName, qName);
/*     */   }
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/*  65 */     this.e.endPrefixMapping(prefix);
/*     */   }
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  69 */     this.e.ignorableWhitespace(ch, start, length);
/*     */   }
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/*  73 */     this.e.processingInstruction(target, data);
/*     */   }
/*     */   
/*     */   public void setDocumentLocator(Locator locator) {
/*  77 */     this.e.setDocumentLocator(locator);
/*     */   }
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {
/*  81 */     this.e.skippedEntity(name);
/*     */   }
/*     */   
/*     */   public void startDocument() throws SAXException {
/*  85 */     this.e.startDocument();
/*     */   }
/*     */   
/*     */   public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException {
/*  89 */     this.e.startElement(uri, lName, qName, atts);
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  93 */     this.e.startPrefixMapping(prefix, uri);
/*     */   }
/*     */   
/*     */   public void a(g handler) {
/*  97 */     this.e = handler;
/*     */   }
/*     */   
/*     */   public g a() {
/* 101 */     return this.e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/xml/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */