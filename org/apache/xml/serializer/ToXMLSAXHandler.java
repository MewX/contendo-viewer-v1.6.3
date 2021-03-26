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
/*     */ public class ToXMLSAXHandler
/*     */   extends ToSAXHandler
/*     */ {
/*     */   protected boolean m_escapeSetting = false;
/*     */   
/*     */   public ToXMLSAXHandler() {
/*  55 */     this.m_prefixMap = new NamespaceMappings();
/*  56 */     initCDATA();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Properties getOutputFormat() {
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() {
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Writer getWriter() {
/*  80 */     return null;
/*     */   }
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
/*     */   public void serialize(Node node) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setEscaping(boolean escape) throws SAXException {
/* 103 */     boolean oldEscapeSetting = this.m_escapeSetting;
/* 104 */     this.m_escapeSetting = escape;
/*     */     
/* 106 */     if (escape) {
/* 107 */       processingInstruction("javax.xml.transform.enable-output-escaping", "");
/*     */     } else {
/* 109 */       processingInstruction("javax.xml.transform.disable-output-escaping", "");
/*     */     } 
/*     */     
/* 112 */     return oldEscapeSetting;
/*     */   }
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
/*     */   public void endDocument() throws SAXException {
/* 179 */     flushPending();
/*     */ 
/*     */     
/* 182 */     this.m_saxHandler.endDocument();
/*     */     
/* 184 */     if (this.m_tracer != null) {
/* 185 */       fireEndDoc();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closeStartTag() throws SAXException {
/* 195 */     this.m_elemContext.m_startTagOpen = false;
/*     */     
/* 197 */     String localName = SerializerBase.getLocalName(this.m_elemContext.m_elementName);
/* 198 */     String uri = getNamespaceURI(this.m_elemContext.m_elementName, true);
/*     */ 
/*     */     
/* 201 */     if (this.m_needToCallStartDocument)
/*     */     {
/* 203 */       startDocumentInternal();
/*     */     }
/* 205 */     this.m_saxHandler.startElement(uri, localName, this.m_elemContext.m_elementName, this.m_attributes);
/*     */ 
/*     */     
/* 208 */     this.m_attributes.clear();
/*     */     
/* 210 */     if (this.m_state != null) {
/* 211 */       this.m_state.setCurrentNode(null);
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
/*     */   public void closeCDATA() throws SAXException {
/* 225 */     if (this.m_lexHandler != null && this.m_cdataTagOpen) {
/* 226 */       this.m_lexHandler.endCDATA();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     this.m_cdataTagOpen = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/* 244 */     flushPending();
/*     */     
/* 246 */     if (namespaceURI == null)
/*     */     {
/* 248 */       if (this.m_elemContext.m_elementURI != null) {
/* 249 */         namespaceURI = this.m_elemContext.m_elementURI;
/*     */       } else {
/* 251 */         namespaceURI = getNamespaceURI(qName, true);
/*     */       } 
/*     */     }
/* 254 */     if (localName == null)
/*     */     {
/* 256 */       if (this.m_elemContext.m_elementLocalName != null) {
/* 257 */         localName = this.m_elemContext.m_elementLocalName;
/*     */       } else {
/* 259 */         localName = SerializerBase.getLocalName(qName);
/*     */       } 
/*     */     }
/* 262 */     this.m_saxHandler.endElement(namespaceURI, localName, qName);
/*     */     
/* 264 */     if (this.m_tracer != null) {
/* 265 */       fireEndElem(qName);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 270 */     this.m_prefixMap.popNamespaces(this.m_elemContext.m_currentElemDepth, this.m_saxHandler);
/*     */     
/* 272 */     this.m_elemContext = this.m_elemContext.m_prev;
/*     */   }
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
/*     */   public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
/* 292 */     this.m_saxHandler.ignorableWhitespace(arg0, arg1, arg2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentLocator(Locator arg0) {
/* 300 */     this.m_saxHandler.setDocumentLocator(arg0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skippedEntity(String arg0) throws SAXException {
/* 308 */     this.m_saxHandler.skippedEntity(arg0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 319 */     startPrefixMapping(prefix, uri, true);
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
/*     */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/*     */     int i;
/* 347 */     if (shouldFlush) {
/*     */       
/* 349 */       flushPending();
/*     */       
/* 351 */       i = this.m_elemContext.m_currentElemDepth + 1;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 356 */       i = this.m_elemContext.m_currentElemDepth;
/*     */     } 
/* 358 */     boolean pushed = this.m_prefixMap.pushNamespace(prefix, uri, i);
/*     */     
/* 360 */     if (pushed) {
/*     */       
/* 362 */       this.m_saxHandler.startPrefixMapping(prefix, uri);
/*     */       
/* 364 */       if (getShouldOutputNSAttr())
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 373 */         if ("".equals(prefix)) {
/*     */           
/* 375 */           String name = "xmlns";
/* 376 */           addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
/*     */ 
/*     */         
/*     */         }
/* 380 */         else if (!"".equals(uri)) {
/*     */           
/* 382 */           String str = "xmlns:" + prefix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 388 */           addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, str, "CDATA", uri);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 393 */     return pushed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(char[] arg0, int arg1, int arg2) throws SAXException {
/* 402 */     flushPending();
/* 403 */     if (this.m_lexHandler != null) {
/* 404 */       this.m_lexHandler.comment(arg0, arg1, arg2);
/*     */     }
/* 406 */     if (this.m_tracer != null) {
/* 407 */       fireCommentEvent(arg0, arg1, arg2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDTD() throws SAXException {
/* 443 */     if (this.m_lexHandler != null) {
/* 444 */       this.m_lexHandler.endDTD();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startEntity(String arg0) throws SAXException {
/* 452 */     if (this.m_lexHandler != null) {
/* 453 */       this.m_lexHandler.startEntity(arg0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(String chars) throws SAXException {
/* 461 */     int length = chars.length();
/* 462 */     if (length > this.m_charsBuff.length)
/*     */     {
/* 464 */       this.m_charsBuff = new char[length * 2 + 1];
/*     */     }
/* 466 */     chars.getChars(0, length, this.m_charsBuff, 0);
/* 467 */     characters(this.m_charsBuff, 0, length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ToXMLSAXHandler(ContentHandler handler, String encoding) {
/* 473 */     super(handler, encoding);
/*     */     
/* 475 */     initCDATA();
/*     */     
/* 477 */     this.m_prefixMap = new NamespaceMappings();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToXMLSAXHandler(ContentHandler handler, LexicalHandler lex, String encoding) {
/* 485 */     super(handler, lex, encoding);
/*     */     
/* 487 */     initCDATA();
/*     */     
/* 489 */     this.m_prefixMap = new NamespaceMappings();
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
/*     */   public void startElement(String elementNamespaceURI, String elementLocalName, String elementName) throws SAXException {
/* 501 */     startElement(elementNamespaceURI, elementLocalName, elementName, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String elementName) throws SAXException {
/* 508 */     startElement(null, null, elementName, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int off, int len) throws SAXException {
/* 516 */     if (this.m_needToCallStartDocument) {
/*     */       
/* 518 */       startDocumentInternal();
/* 519 */       this.m_needToCallStartDocument = false;
/*     */     } 
/*     */     
/* 522 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 524 */       closeStartTag();
/* 525 */       this.m_elemContext.m_startTagOpen = false;
/*     */     } 
/*     */     
/* 528 */     if (this.m_elemContext.m_isCdataSection && !this.m_cdataTagOpen && this.m_lexHandler != null) {
/*     */ 
/*     */       
/* 531 */       this.m_lexHandler.startCDATA();
/*     */ 
/*     */ 
/*     */       
/* 535 */       this.m_cdataTagOpen = true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 542 */     this.m_saxHandler.characters(ch, off, len);
/*     */ 
/*     */     
/* 545 */     if (this.m_tracer != null) {
/* 546 */       fireCharEvent(ch, off, len);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endElement(String elemName) throws SAXException {
/* 555 */     endElement(null, null, elemName);
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
/*     */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {
/* 569 */     startPrefixMapping(prefix, uri, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 580 */     flushPending();
/*     */ 
/*     */     
/* 583 */     this.m_saxHandler.processingInstruction(target, data);
/*     */ 
/*     */ 
/*     */     
/* 587 */     if (this.m_tracer != null) {
/* 588 */       fireEscapingEvent(target, data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean popNamespace(String prefix) {
/*     */     
/* 599 */     try { if (this.m_prefixMap.popNamespace(prefix))
/*     */       
/* 601 */       { this.m_saxHandler.endPrefixMapping(prefix);
/* 602 */         return true; }  } catch (SAXException sAXException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 609 */     return false;
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
/*     */   public void startCDATA() throws SAXException {
/* 622 */     if (!this.m_cdataTagOpen) {
/*     */       
/* 624 */       flushPending();
/* 625 */       if (this.m_lexHandler != null) {
/* 626 */         this.m_lexHandler.startCDATA();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 631 */         this.m_cdataTagOpen = true;
/*     */       } 
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
/*     */   public void startElement(String namespaceURI, String localName, String name, Attributes atts) throws SAXException {
/* 646 */     flushPending();
/* 647 */     super.startElement(namespaceURI, localName, name, atts);
/*     */ 
/*     */     
/* 650 */     if (this.m_needToOutputDocTypeDecl) {
/*     */       
/* 652 */       String doctypeSystem = getDoctypeSystem();
/* 653 */       if (doctypeSystem != null && this.m_lexHandler != null) {
/*     */         
/* 655 */         String doctypePublic = getDoctypePublic();
/* 656 */         if (doctypeSystem != null) {
/* 657 */           this.m_lexHandler.startDTD(name, doctypePublic, doctypeSystem);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 662 */       this.m_needToOutputDocTypeDecl = false;
/*     */     } 
/* 664 */     this.m_elemContext = this.m_elemContext.push(namespaceURI, localName, name);
/*     */ 
/*     */ 
/*     */     
/* 668 */     if (namespaceURI != null) {
/* 669 */       ensurePrefixIsDeclared(namespaceURI, name);
/*     */     }
/*     */     
/* 672 */     if (atts != null) {
/* 673 */       addAttributes(atts);
/*     */     }
/*     */ 
/*     */     
/* 677 */     this.m_elemContext.m_isCdataSection = isCdataSection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensurePrefixIsDeclared(String ns, String rawName) throws SAXException {
/* 685 */     if (ns != null && ns.length() > 0) {
/*     */       int index;
/*     */       
/* 688 */       String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 693 */       if (null != prefix) {
/*     */         
/* 695 */         String foundURI = this.m_prefixMap.lookupNamespace(prefix);
/*     */         
/* 697 */         if (null == foundURI || !foundURI.equals(ns)) {
/*     */           
/* 699 */           startPrefixMapping(prefix, ns, false);
/*     */           
/* 701 */           if (getShouldOutputNSAttr())
/*     */           {
/*     */             
/* 704 */             addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, "xmlns" + ((prefix.length() == 0) ? "" : ":") + prefix, "CDATA", ns);
/*     */           }
/*     */         } 
/*     */       } 
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
/*     */   public void addAttribute(String uri, String localName, String rawName, String type, String value) throws SAXException {
/* 736 */     if (this.m_elemContext.m_startTagOpen) {
/*     */       
/* 738 */       ensurePrefixIsDeclared(uri, rawName);
/* 739 */       addAttributeAlways(uri, localName, rawName, type, value);
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
/*     */   public boolean reset() {
/* 754 */     boolean wasReset = false;
/* 755 */     if (super.reset()) {
/*     */       
/* 757 */       resetToXMLSAXHandler();
/* 758 */       wasReset = true;
/*     */     } 
/* 760 */     return wasReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetToXMLSAXHandler() {
/* 769 */     this.m_escapeSetting = false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToXMLSAXHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */