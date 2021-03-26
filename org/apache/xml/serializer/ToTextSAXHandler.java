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
/*     */ public class ToTextSAXHandler
/*     */   extends ToSAXHandler
/*     */ {
/*     */   public void endElement(String elemName) throws SAXException {
/*  50 */     if (this.m_tracer != null) {
/*  51 */       fireEndElem(elemName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String arg0, String arg1, String arg2) throws SAXException {
/*  60 */     if (this.m_tracer != null) {
/*  61 */       fireEndElem(arg2);
/*     */     }
/*     */   }
/*     */   
/*     */   public ToTextSAXHandler(ContentHandler hdlr, LexicalHandler lex, String encoding) {
/*  66 */     super(hdlr, lex, encoding);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToTextSAXHandler(ContentHandler handler, String encoding) {
/*  74 */     super(handler, encoding);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/*  80 */     if (this.m_tracer != null) {
/*  81 */       fireCommentEvent(ch, start, length);
/*     */     }
/*     */   }
/*     */   
/*     */   public void comment(String data) throws SAXException {
/*  86 */     int length = data.length();
/*  87 */     if (length > this.m_charsBuff.length)
/*     */     {
/*  89 */       this.m_charsBuff = new char[length * 2 + 1];
/*     */     }
/*  91 */     data.getChars(0, length, this.m_charsBuff, 0);
/*  92 */     comment(this.m_charsBuff, 0, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Properties getOutputFormat() {
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() {
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Writer getWriter() {
/* 116 */     return null;
/*     */   }
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
/*     */   public boolean reset() {
/* 133 */     return false;
/*     */   }
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
/*     */   public boolean setEscaping(boolean escape) {
/* 148 */     return false;
/*     */   }
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
/*     */   public void setOutputFormat(Properties format) {}
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
/*     */   public void setWriter(Writer writer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String uri, String localName, String rawName, String type, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attributeDecl(String arg0, String arg1, String arg2, String arg3, String arg4) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void elementDecl(String arg0, String arg1) throws SAXException {}
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
/*     */   public void internalEntityDecl(String arg0, String arg1) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPrefixMapping(String arg0) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String arg0, String arg1) throws SAXException {
/* 249 */     if (this.m_tracer != null) {
/* 250 */       fireEscapingEvent(arg0, arg1);
/*     */     }
/*     */   }
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
/*     */   public void skippedEntity(String arg0) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
/* 277 */     flushPending();
/* 278 */     super.startElement(arg0, arg1, arg2, arg3);
/*     */   }
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
/*     */   public void endDTD() throws SAXException {}
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
/*     */   public void startEntity(String arg0) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String elementNamespaceURI, String elementLocalName, String elementName) throws SAXException {
/* 320 */     super.startElement(elementNamespaceURI, elementLocalName, elementName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String elementName) throws SAXException {
/* 326 */     super.startElement(elementName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {
/* 336 */     flushPending();
/* 337 */     this.m_saxHandler.endDocument();
/*     */     
/* 339 */     if (this.m_tracer != null) {
/* 340 */       fireEndDoc();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(String characters) throws SAXException {
/* 350 */     int length = characters.length();
/* 351 */     if (length > this.m_charsBuff.length)
/*     */     {
/* 353 */       this.m_charsBuff = new char[length * 2 + 1];
/*     */     }
/* 355 */     characters.getChars(0, length, this.m_charsBuff, 0);
/*     */     
/* 357 */     this.m_saxHandler.characters(this.m_charsBuff, 0, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] characters, int offset, int length) throws SAXException {
/* 367 */     this.m_saxHandler.characters(characters, offset, length);
/*     */ 
/*     */     
/* 370 */     if (this.m_tracer != null) {
/* 371 */       fireCharEvent(characters, offset, length);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(String name, String value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/* 390 */     return false;
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {}
/*     */   
/*     */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToTextSAXHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */