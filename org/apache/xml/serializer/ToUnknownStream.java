/*      */ package org.apache.xml.serializer;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Writer;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.Transformer;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
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
/*      */ public class ToUnknownStream
/*      */   extends SerializerBase
/*      */ {
/*      */   private SerializationHandler m_handler;
/*      */   private static final String EMPTYSTRING = "";
/*      */   private boolean m_wrapped_handler_not_initialized = false;
/*      */   private String m_firstElementPrefix;
/*      */   private String m_firstElementName;
/*      */   private String m_firstElementURI;
/*   85 */   private String m_firstElementLocalName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_firstTagNotEmitted = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   96 */   private Vector m_namespaceURI = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  101 */   private Vector m_namespacePrefix = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_needToCallStartDocument = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_setVersion_called = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_setDoctypeSystem_called = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_setDoctypePublic_called = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_setMediaType_called = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ToUnknownStream() {
/*  136 */     this.m_handler = new ToXMLStream();
/*      */   }
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
/*      */   public ContentHandler asContentHandler() throws IOException {
/*  150 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*  158 */     this.m_handler.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Properties getOutputFormat() {
/*  167 */     return this.m_handler.getOutputFormat();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public OutputStream getOutputStream() {
/*  176 */     return this.m_handler.getOutputStream();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Writer getWriter() {
/*  185 */     return this.m_handler.getWriter();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean reset() {
/*  195 */     return this.m_handler.reset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void serialize(Node node) throws IOException {
/*  206 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  208 */       flush();
/*      */     }
/*  210 */     this.m_handler.serialize(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setEscaping(boolean escape) throws SAXException {
/*  218 */     return this.m_handler.setEscaping(escape);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOutputFormat(Properties format) {
/*  228 */     this.m_handler.setOutputFormat(format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOutputStream(OutputStream output) {
/*  238 */     this.m_handler.setOutputStream(output);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWriter(Writer writer) {
/*  248 */     this.m_handler.setWriter(writer);
/*      */   }
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
/*      */   public void addAttribute(String uri, String localName, String rawName, String type, String value) throws SAXException {
/*  268 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  270 */       flush();
/*      */     }
/*  272 */     this.m_handler.addAttribute(uri, localName, rawName, type, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttribute(String rawName, String value) {
/*  282 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  284 */       flush();
/*      */     }
/*  286 */     this.m_handler.addAttribute(rawName, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUniqueAttribute(String rawName, String value, int flags) throws SAXException {
/*  296 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  298 */       flush();
/*      */     }
/*  300 */     this.m_handler.addUniqueAttribute(rawName, value, flags);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(String chars) throws SAXException {
/*  312 */     int length = chars.length();
/*  313 */     if (length > this.m_charsBuff.length)
/*      */     {
/*  315 */       this.m_charsBuff = new char[length * 2 + 1];
/*      */     }
/*  317 */     chars.getChars(0, length, this.m_charsBuff, 0);
/*  318 */     characters(this.m_charsBuff, 0, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String elementName) throws SAXException {
/*  327 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  329 */       flush();
/*      */     }
/*  331 */     this.m_handler.endElement(elementName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  342 */     startPrefixMapping(prefix, uri, true);
/*      */   }
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
/*      */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {
/*  360 */     if (this.m_firstTagNotEmitted && this.m_firstElementURI == null && this.m_firstElementName != null) {
/*      */       
/*  362 */       String prefix1 = SerializerBase.getPrefixPart(this.m_firstElementName);
/*  363 */       if (prefix1 == null && "".equals(prefix))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  369 */         this.m_firstElementURI = uri;
/*      */       }
/*      */     } 
/*  372 */     startPrefixMapping(prefix, uri, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/*  378 */     boolean pushed = false;
/*  379 */     if (this.m_firstTagNotEmitted) {
/*      */       
/*  381 */       if (this.m_firstElementName != null && shouldFlush)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  387 */         flush();
/*  388 */         pushed = this.m_handler.startPrefixMapping(prefix, uri, shouldFlush);
/*      */       }
/*      */       else
/*      */       {
/*  392 */         if (this.m_namespacePrefix == null) {
/*      */           
/*  394 */           this.m_namespacePrefix = new Vector();
/*  395 */           this.m_namespaceURI = new Vector();
/*      */         } 
/*  397 */         this.m_namespacePrefix.addElement(prefix);
/*  398 */         this.m_namespaceURI.addElement(uri);
/*      */         
/*  400 */         if (this.m_firstElementURI == null)
/*      */         {
/*  402 */           if (prefix.equals(this.m_firstElementPrefix)) {
/*  403 */             this.m_firstElementURI = uri;
/*      */           }
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  410 */       pushed = this.m_handler.startPrefixMapping(prefix, uri, shouldFlush);
/*      */     } 
/*  412 */     return pushed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(String version) {
/*  422 */     this.m_handler.setVersion(version);
/*      */ 
/*      */ 
/*      */     
/*  426 */     this.m_setVersion_called = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDocument() throws SAXException {
/*  434 */     this.m_needToCallStartDocument = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String qName) throws SAXException {
/*  441 */     startElement(null, null, qName, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void startElement(String namespaceURI, String localName, String qName) throws SAXException {
/*  446 */     startElement(namespaceURI, localName, qName, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startElement(String namespaceURI, String localName, String elementName, Attributes atts) throws SAXException {
/*  456 */     if (this.m_firstTagNotEmitted) {
/*      */ 
/*      */       
/*  459 */       if (this.m_firstElementName != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  465 */         flush();
/*  466 */         this.m_handler.startElement(namespaceURI, localName, elementName, atts);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         
/*  475 */         this.m_wrapped_handler_not_initialized = true;
/*  476 */         this.m_firstElementName = elementName;
/*      */ 
/*      */         
/*  479 */         this.m_firstElementPrefix = getPrefixPartUnknown(elementName);
/*      */ 
/*      */         
/*  482 */         this.m_firstElementURI = namespaceURI;
/*      */ 
/*      */         
/*  485 */         this.m_firstElementLocalName = localName;
/*      */         
/*  487 */         if (this.m_tracer != null) {
/*  488 */           firePseudoElement(elementName);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  497 */         if (atts != null) {
/*  498 */           super.addAttributes(atts);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  503 */         if (atts != null) {
/*  504 */           flush();
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  512 */       this.m_handler.startElement(namespaceURI, localName, elementName, atts);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(String comment) throws SAXException {
/*  522 */     if (this.m_firstTagNotEmitted && this.m_firstElementName != null) {
/*      */       
/*  524 */       emitFirstTag();
/*      */     }
/*  526 */     else if (this.m_needToCallStartDocument) {
/*      */       
/*  528 */       this.m_handler.startDocument();
/*  529 */       this.m_needToCallStartDocument = false;
/*      */     } 
/*      */     
/*  532 */     this.m_handler.comment(comment);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDoctypePublic() {
/*  542 */     return this.m_handler.getDoctypePublic();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDoctypeSystem() {
/*  551 */     return this.m_handler.getDoctypeSystem();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEncoding() {
/*  560 */     return this.m_handler.getEncoding();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIndent() {
/*  569 */     return this.m_handler.getIndent();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIndentAmount() {
/*  578 */     return this.m_handler.getIndentAmount();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMediaType() {
/*  587 */     return this.m_handler.getMediaType();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getOmitXMLDeclaration() {
/*  596 */     return this.m_handler.getOmitXMLDeclaration();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStandalone() {
/*  605 */     return this.m_handler.getStandalone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getVersion() {
/*  614 */     return this.m_handler.getVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctype(String system, String pub) {
/*  622 */     this.m_handler.setDoctypePublic(pub);
/*  623 */     this.m_handler.setDoctypeSystem(system);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctypePublic(String doctype) {
/*  634 */     this.m_handler.setDoctypePublic(doctype);
/*  635 */     this.m_setDoctypePublic_called = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctypeSystem(String doctype) {
/*  646 */     this.m_handler.setDoctypeSystem(doctype);
/*  647 */     this.m_setDoctypeSystem_called = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(String encoding) {
/*  656 */     this.m_handler.setEncoding(encoding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIndent(boolean indent) {
/*  665 */     this.m_handler.setIndent(indent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIndentAmount(int value) {
/*  673 */     this.m_handler.setIndentAmount(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMediaType(String mediaType) {
/*  681 */     this.m_handler.setMediaType(mediaType);
/*  682 */     this.m_setMediaType_called = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOmitXMLDeclaration(boolean b) {
/*  691 */     this.m_handler.setOmitXMLDeclaration(b);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStandalone(String standalone) {
/*  700 */     this.m_handler.setStandalone(standalone);
/*      */   }
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
/*      */   public void attributeDecl(String arg0, String arg1, String arg2, String arg3, String arg4) throws SAXException {
/*  719 */     this.m_handler.attributeDecl(arg0, arg1, arg2, arg3, arg4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void elementDecl(String arg0, String arg1) throws SAXException {
/*  728 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  730 */       emitFirstTag();
/*      */     }
/*  732 */     this.m_handler.elementDecl(arg0, arg1);
/*      */   }
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
/*      */   public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {
/*  745 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  747 */       flush();
/*      */     }
/*  749 */     this.m_handler.externalEntityDecl(name, publicId, systemId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void internalEntityDecl(String arg0, String arg1) throws SAXException {
/*  759 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  761 */       flush();
/*      */     }
/*  763 */     this.m_handler.internalEntityDecl(arg0, arg1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] characters, int offset, int length) throws SAXException {
/*  773 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  775 */       flush();
/*      */     }
/*      */     
/*  778 */     this.m_handler.characters(characters, offset, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDocument() throws SAXException {
/*  788 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  790 */       flush();
/*      */     }
/*      */     
/*  793 */     this.m_handler.endDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
/*  805 */     if (this.m_firstTagNotEmitted) {
/*      */       
/*  807 */       flush();
/*  808 */       if (namespaceURI == null && this.m_firstElementURI != null) {
/*  809 */         namespaceURI = this.m_firstElementURI;
/*      */       }
/*      */       
/*  812 */       if (localName == null && this.m_firstElementLocalName != null) {
/*  813 */         localName = this.m_firstElementLocalName;
/*      */       }
/*      */     } 
/*  816 */     this.m_handler.endElement(namespaceURI, localName, qName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endPrefixMapping(String prefix) throws SAXException {
/*  825 */     this.m_handler.endPrefixMapping(prefix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/*  835 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  837 */       flush();
/*      */     }
/*  839 */     this.m_handler.ignorableWhitespace(ch, start, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/*  849 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  851 */       flush();
/*      */     }
/*      */     
/*  854 */     this.m_handler.processingInstruction(target, data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentLocator(Locator locator) {
/*  863 */     this.m_handler.setDocumentLocator(locator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void skippedEntity(String name) throws SAXException {
/*  872 */     this.m_handler.skippedEntity(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/*  883 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  885 */       flush();
/*      */     }
/*      */     
/*  888 */     this.m_handler.comment(ch, start, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCDATA() throws SAXException {
/*  898 */     this.m_handler.endCDATA();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDTD() throws SAXException {
/*  908 */     this.m_handler.endDTD();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endEntity(String name) throws SAXException {
/*  917 */     if (this.m_firstTagNotEmitted)
/*      */     {
/*  919 */       emitFirstTag();
/*      */     }
/*  921 */     this.m_handler.endEntity(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startCDATA() throws SAXException {
/*  930 */     this.m_handler.startCDATA();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/*  940 */     this.m_handler.startDTD(name, publicId, systemId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startEntity(String name) throws SAXException {
/*  949 */     this.m_handler.startEntity(name);
/*      */   }
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
/*      */   private void initStreamOutput() throws SAXException {
/*  963 */     boolean firstElementIsHTML = isFirstElemHTML();
/*      */     
/*  965 */     if (firstElementIsHTML) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  970 */       SerializationHandler oldHandler = this.m_handler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  977 */       Properties htmlProperties = OutputPropertiesFactory.getDefaultMethodProperties("html");
/*      */       
/*  979 */       Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  986 */       this.m_handler = (SerializationHandler)serializer;
/*      */ 
/*      */       
/*  989 */       Writer writer = oldHandler.getWriter();
/*      */       
/*  991 */       if (null != writer) {
/*  992 */         this.m_handler.setWriter(writer);
/*      */       } else {
/*      */         
/*  995 */         OutputStream os = oldHandler.getOutputStream();
/*      */         
/*  997 */         if (null != os) {
/*  998 */           this.m_handler.setOutputStream(os);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1005 */       this.m_handler.setVersion(oldHandler.getVersion());
/*      */ 
/*      */ 
/*      */       
/* 1009 */       this.m_handler.setDoctypeSystem(oldHandler.getDoctypeSystem());
/*      */ 
/*      */ 
/*      */       
/* 1013 */       this.m_handler.setDoctypePublic(oldHandler.getDoctypePublic());
/*      */ 
/*      */ 
/*      */       
/* 1017 */       this.m_handler.setMediaType(oldHandler.getMediaType());
/*      */ 
/*      */       
/* 1020 */       this.m_handler.setTransformer(oldHandler.getTransformer());
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1027 */     if (this.m_needToCallStartDocument) {
/*      */       
/* 1029 */       this.m_handler.startDocument();
/* 1030 */       this.m_needToCallStartDocument = false;
/*      */     } 
/*      */ 
/*      */     
/* 1034 */     this.m_wrapped_handler_not_initialized = false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void emitFirstTag() throws SAXException {
/* 1039 */     if (this.m_firstElementName != null) {
/*      */       
/* 1041 */       if (this.m_wrapped_handler_not_initialized) {
/*      */         
/* 1043 */         initStreamOutput();
/* 1044 */         this.m_wrapped_handler_not_initialized = false;
/*      */       } 
/*      */       
/* 1047 */       this.m_handler.startElement(this.m_firstElementURI, null, this.m_firstElementName, this.m_attributes);
/*      */       
/* 1049 */       this.m_attributes = null;
/*      */ 
/*      */       
/* 1052 */       if (this.m_namespacePrefix != null) {
/*      */         
/* 1054 */         int n = this.m_namespacePrefix.size();
/* 1055 */         for (int i = 0; i < n; i++) {
/*      */           
/* 1057 */           String prefix = this.m_namespacePrefix.elementAt(i);
/*      */           
/* 1059 */           String uri = this.m_namespaceURI.elementAt(i);
/* 1060 */           this.m_handler.startPrefixMapping(prefix, uri, false);
/*      */         } 
/* 1062 */         this.m_namespacePrefix = null;
/* 1063 */         this.m_namespaceURI = null;
/*      */       } 
/* 1065 */       this.m_firstTagNotEmitted = false;
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
/*      */   private String getLocalNameUnknown(String value) {
/* 1077 */     int idx = value.lastIndexOf(':');
/* 1078 */     if (idx >= 0)
/* 1079 */       value = value.substring(idx + 1); 
/* 1080 */     idx = value.lastIndexOf('@');
/* 1081 */     if (idx >= 0)
/* 1082 */       value = value.substring(idx + 1); 
/* 1083 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getPrefixPartUnknown(String qname) {
/* 1094 */     int index = qname.indexOf(':');
/* 1095 */     return (index > 0) ? qname.substring(0, index) : "";
/*      */   }
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
/*      */   private boolean isFirstElemHTML() {
/* 1110 */     boolean isHTML = getLocalNameUnknown(this.m_firstElementName).equalsIgnoreCase("html");
/*      */ 
/*      */ 
/*      */     
/* 1114 */     if (isHTML && this.m_firstElementURI != null && !"".equals(this.m_firstElementURI))
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1119 */       isHTML = false;
/*      */     }
/*      */     
/* 1122 */     if (isHTML && this.m_namespacePrefix != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1128 */       int max = this.m_namespacePrefix.size();
/* 1129 */       for (int i = 0; i < max; i++) {
/*      */         
/* 1131 */         String prefix = this.m_namespacePrefix.elementAt(i);
/* 1132 */         String uri = this.m_namespaceURI.elementAt(i);
/*      */         
/* 1134 */         if (this.m_firstElementPrefix != null && this.m_firstElementPrefix.equals(prefix) && !"".equals(uri)) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1139 */           isHTML = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1145 */     return isHTML;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMSerializer asDOMSerializer() throws IOException {
/* 1152 */     return this.m_handler.asDOMSerializer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCdataSectionElements(Vector URI_and_localNames) {
/* 1162 */     this.m_handler.setCdataSectionElements(URI_and_localNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttributes(Attributes atts) throws SAXException {
/* 1169 */     this.m_handler.addAttributes(atts);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NamespaceMappings getNamespaceMappings() {
/* 1179 */     NamespaceMappings mappings = null;
/* 1180 */     if (this.m_handler != null)
/*      */     {
/* 1182 */       mappings = this.m_handler.getNamespaceMappings();
/*      */     }
/* 1184 */     return mappings;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flushPending() throws SAXException {
/* 1192 */     flush();
/*      */     
/* 1194 */     this.m_handler.flushPending();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void flush() {
/*      */     
/* 1201 */     try { if (this.m_firstTagNotEmitted)
/*      */       {
/* 1203 */         emitFirstTag();
/*      */       }
/* 1205 */       if (this.m_needToCallStartDocument)
/*      */       
/* 1207 */       { this.m_handler.startDocument();
/* 1208 */         this.m_needToCallStartDocument = false; }  } catch (SAXException e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/* 1213 */       throw new RuntimeException(e.toString()); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix(String namespaceURI) {
/* 1224 */     return this.m_handler.getPrefix(namespaceURI);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void entityReference(String entityName) throws SAXException {
/* 1231 */     this.m_handler.entityReference(entityName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURI(String qname, boolean isElement) {
/* 1239 */     return this.m_handler.getNamespaceURI(qname, isElement);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getNamespaceURIFromPrefix(String prefix) {
/* 1244 */     return this.m_handler.getNamespaceURIFromPrefix(prefix);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTransformer(Transformer t) {
/* 1249 */     this.m_handler.setTransformer(t);
/* 1250 */     if (t instanceof SerializerTrace && ((SerializerTrace)t).hasTraceListeners()) {
/*      */       
/* 1252 */       this.m_tracer = (SerializerTrace)t;
/*      */     } else {
/* 1254 */       this.m_tracer = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public Transformer getTransformer() {
/* 1259 */     return this.m_handler.getTransformer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setContentHandler(ContentHandler ch) {
/* 1267 */     this.m_handler.setContentHandler(ch);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSourceLocator(SourceLocator locator) {
/* 1278 */     this.m_handler.setSourceLocator(locator);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void firePseudoElement(String elementName) {
/* 1284 */     if (this.m_tracer != null) {
/* 1285 */       StringBuffer sb = new StringBuffer();
/*      */       
/* 1287 */       sb.append('<');
/* 1288 */       sb.append(elementName);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1293 */       char[] ch = sb.toString().toCharArray();
/* 1294 */       this.m_tracer.fireGenerateEvent(11, ch, 0, ch.length);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToUnknownStream.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */