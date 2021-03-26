/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Properties;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
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
/*     */ public class ToHTMLSAXHandler
/*     */   extends ToSAXHandler
/*     */ {
/*     */   protected boolean m_escapeSetting = false;
/*     */   
/*     */   public Properties getOutputFormat() {
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() {
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Writer getWriter() {
/*  78 */     return null;
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
/*     */   public void indent(int n) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void serialize(Node node) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setEscaping(boolean escape) throws SAXException {
/* 108 */     boolean oldEscapeSetting = this.m_escapeSetting;
/* 109 */     this.m_escapeSetting = escape;
/*     */     
/* 111 */     if (escape) {
/* 112 */       processingInstruction("javax.xml.transform.enable-output-escaping", "");
/*     */     } else {
/* 114 */       processingInstruction("javax.xml.transform.disable-output-escaping", "");
/*     */     } 
/*     */     
/* 117 */     return oldEscapeSetting;
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
/*     */   public void setIndent(boolean indent) {}
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
/*     */   public void setOutputFormat(Properties format) {}
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
/*     */   public void setOutputStream(OutputStream output) {}
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
/*     */   public void setWriter(Writer writer) {}
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
/*     */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {}
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
/*     */   public void elementDecl(String name, String model) throws SAXException {}
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
/*     */   public void externalEntityDecl(String arg0, String arg1, String arg2) throws SAXException {}
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
/*     */   public void internalEntityDecl(String name, String value) throws SAXException {}
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
/*     */   public void endElement(String uri, String localName, String qName) throws SAXException {
/* 237 */     flushPending();
/* 238 */     this.m_saxHandler.endElement(uri, localName, qName);
/*     */ 
/*     */     
/* 241 */     if (this.m_tracer != null) {
/* 242 */       fireEndElem(qName);
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
/*     */   public void endPrefixMapping(String prefix) throws SAXException {}
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
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
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
/*     */   public void processingInstruction(String arg0, String arg1) throws SAXException {
/* 284 */     flushPending();
/* 285 */     this.m_saxHandler.processingInstruction(arg0, arg1);
/*     */ 
/*     */ 
/*     */     
/* 289 */     if (this.m_tracer != null) {
/* 290 */       fireEscapingEvent(arg0, arg1);
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
/*     */   public void setDocumentLocator(Locator arg0) {}
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
/*     */   public void skippedEntity(String arg0) throws SAXException {}
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
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
/* 341 */     flushPending();
/* 342 */     super.startElement(namespaceURI, localName, qName, atts);
/* 343 */     this.m_saxHandler.startElement(namespaceURI, localName, qName, atts);
/* 344 */     this.m_elemContext.m_startTagOpen = false;
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
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 359 */     flushPending();
/* 360 */     this.m_lexHandler.comment(ch, start, length);
/*     */ 
/*     */     
/* 363 */     if (this.m_tracer != null) {
/* 364 */       fireCommentEvent(ch, start, length);
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
/*     */   public void endCDATA() throws SAXException {}
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
/*     */   public void endDTD() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCDATA() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startEntity(String arg0) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/* 419 */     flushPending();
/*     */ 
/*     */     
/* 422 */     this.m_saxHandler.endDocument();
/*     */     
/* 424 */     if (this.m_tracer != null) {
/* 425 */       fireEndDoc();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeStartTag() throws SAXException {
/* 435 */     this.m_elemContext.m_startTagOpen = false;
/*     */ 
/*     */     
/* 438 */     this.m_saxHandler.startElement("", this.m_elemContext.m_elementName, this.m_elemContext.m_elementName, this.m_attributes);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 443 */     this.m_attributes.clear();
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
/*     */   public void close() {}
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
/*     */   public void characters(String chars) throws SAXException {
/* 467 */     int length = chars.length();
/* 468 */     if (length > this.m_charsBuff.length)
/*     */     {
/* 470 */       this.m_charsBuff = new char[length * 2 + 1];
/*     */     }
/* 472 */     chars.getChars(0, length, this.m_charsBuff, 0);
/* 473 */     characters(this.m_charsBuff, 0, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToHTMLSAXHandler(ContentHandler handler, String encoding) {
/* 484 */     super(handler, encoding);
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
/*     */   public ToHTMLSAXHandler(ContentHandler handler, LexicalHandler lex, String encoding) {
/* 497 */     super(handler, lex, encoding);
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
/*     */   
/*     */   public void startElement(String elementNamespaceURI, String elementLocalName, String elementName) throws SAXException {
/* 517 */     super.startElement(elementNamespaceURI, elementLocalName, elementName);
/*     */     
/* 519 */     flushPending();
/*     */ 
/*     */     
/* 522 */     if (this.m_lexHandler != null) {
/*     */       
/* 524 */       String doctypeSystem = getDoctypeSystem();
/* 525 */       String doctypePublic = getDoctypePublic();
/* 526 */       if (doctypeSystem != null || doctypePublic != null) {
/* 527 */         this.m_lexHandler.startDTD(elementName, doctypePublic, doctypeSystem);
/*     */       }
/*     */ 
/*     */       
/* 531 */       this.m_lexHandler = null;
/*     */     } 
/* 533 */     this.m_elemContext = this.m_elemContext.push(elementNamespaceURI, elementLocalName, elementName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String elementName) throws SAXException {
/* 544 */     startElement(null, null, elementName);
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
/*     */   public void endElement(String elementName) throws SAXException {
/* 557 */     flushPending();
/* 558 */     this.m_saxHandler.endElement("", elementName, elementName);
/*     */ 
/*     */     
/* 561 */     if (this.m_tracer != null) {
/* 562 */       fireEndElem(elementName);
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
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 597 */     flushPending();
/* 598 */     this.m_saxHandler.characters(ch, off, len);
/*     */ 
/*     */     
/* 601 */     if (this.m_tracer != null) {
/* 602 */       fireCharEvent(ch, off, len);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushPending() throws SAXException {
/* 611 */     if (this.m_needToCallStartDocument) {
/*     */       
/* 613 */       startDocumentInternal();
/* 614 */       this.m_needToCallStartDocument = false;
/*     */     } 
/*     */     
/* 617 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 619 */       closeStartTag();
/* 620 */       this.m_elemContext.m_startTagOpen = false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/* 649 */     if (shouldFlush)
/* 650 */       flushPending(); 
/* 651 */     this.m_saxHandler.startPrefixMapping(prefix, uri);
/* 652 */     return false;
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
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 673 */     startPrefixMapping(prefix, uri, true);
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
/*     */   
/*     */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {
/* 693 */     if (this.m_elemContext.m_elementURI == null) {
/*     */       
/* 695 */       String prefix1 = SerializerBase.getPrefixPart(this.m_elemContext.m_elementName);
/* 696 */       if (prefix1 == null && "".equals(prefix))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 702 */         this.m_elemContext.m_elementURI = uri;
/*     */       }
/*     */     } 
/* 705 */     startPrefixMapping(prefix, uri, false);
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
/*     */   public boolean reset() {
/* 718 */     boolean wasReset = false;
/* 719 */     if (super.reset()) {
/*     */       
/* 721 */       resetToHTMLSAXHandler();
/* 722 */       wasReset = true;
/*     */     } 
/* 724 */     return wasReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetToHTMLSAXHandler() {
/* 733 */     this.m_escapeSetting = false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToHTMLSAXHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */