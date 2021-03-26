/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.Writer;
/*     */ import java.util.Stack;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.Text;
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
/*     */ public class DOMBuilder
/*     */   implements ContentHandler, LexicalHandler
/*     */ {
/*     */   public Document m_doc;
/*  51 */   protected Node m_currentNode = null;
/*     */ 
/*     */   
/*  54 */   public DocumentFragment m_docFrag = null;
/*     */ 
/*     */   
/*  57 */   protected Stack m_elemStack = new Stack();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_inCData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getRootNode() {
/* 104 */     return (null != this.m_docFrag) ? this.m_docFrag : this.m_doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCurrentNode() {
/* 114 */     return this.m_currentNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Writer getWriter() {
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void append(Node newNode) throws SAXException {
/* 135 */     Node currentNode = this.m_currentNode;
/*     */     
/* 137 */     if (null != currentNode) {
/*     */       
/* 139 */       currentNode.appendChild(newNode);
/*     */ 
/*     */     
/*     */     }
/* 143 */     else if (null != this.m_docFrag) {
/*     */       
/* 145 */       this.m_docFrag.appendChild(newNode);
/*     */     }
/*     */     else {
/*     */       
/* 149 */       boolean ok = true;
/* 150 */       short type = newNode.getNodeType();
/*     */       
/* 152 */       if (type == 3) {
/*     */         
/* 154 */         String data = newNode.getNodeValue();
/*     */         
/* 156 */         if (null != data && data.trim().length() > 0)
/*     */         {
/* 158 */           throw new SAXException(XMLMessages.createXMLMessage("ER_CANT_OUTPUT_TEXT_BEFORE_DOC", null));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 163 */         ok = false;
/*     */       }
/* 165 */       else if (type == 1) {
/*     */         
/* 167 */         if (this.m_doc.getDocumentElement() != null)
/*     */         {
/* 169 */           throw new SAXException(XMLMessages.createXMLMessage("ER_CANT_HAVE_MORE_THAN_ONE_ROOT", null));
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 175 */       if (ok) {
/* 176 */         this.m_doc.appendChild(newNode);
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
/*     */   public void setDocumentLocator(Locator locator) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument() throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String ns, String localName, String name, Attributes atts) throws SAXException {
/*     */     Element element;
/* 271 */     if (null == ns || ns.length() == 0) {
/* 272 */       element = this.m_doc.createElementNS(null, name);
/*     */     } else {
/* 274 */       element = this.m_doc.createElementNS(ns, name);
/*     */     } 
/* 276 */     append(element);
/*     */ 
/*     */ 
/*     */     
/* 280 */     try { int nAtts = atts.getLength();
/*     */       
/* 282 */       if (0 != nAtts)
/*     */       {
/* 284 */         for (int i = 0; i < nAtts; i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 289 */           if (atts.getType(i).equalsIgnoreCase("ID")) {
/* 290 */             setIDAttribute(atts.getValue(i), element);
/*     */           }
/* 292 */           String attrNS = atts.getURI(i);
/*     */           
/* 294 */           if ("".equals(attrNS)) {
/* 295 */             attrNS = null;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 300 */           String attrQName = atts.getQName(i);
/*     */ 
/*     */           
/* 303 */           if (attrQName.startsWith("xmlns:")) {
/* 304 */             attrNS = "http://www.w3.org/2000/xmlns/";
/*     */           }
/*     */           
/* 307 */           element.setAttributeNS(attrNS, attrQName, atts.getValue(i));
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 313 */       this.m_elemStack.push(element);
/*     */       
/* 315 */       this.m_currentNode = element; } catch (Exception de)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       throw new SAXException(de); }
/*     */   
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
/*     */   public void endElement(String ns, String localName, String name) throws SAXException {
/* 349 */     this.m_elemStack.pop();
/* 350 */     this.m_currentNode = this.m_elemStack.isEmpty() ? null : this.m_elemStack.peek();
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
/*     */   public void setIDAttribute(String id, Element elem) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void characters(char[] ch, int start, int length) throws SAXException {
/* 390 */     if (isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
/*     */       return;
/*     */     }
/*     */     
/* 394 */     if (this.m_inCData) {
/*     */       
/* 396 */       cdata(ch, start, length);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 401 */     String s = new String(ch, start, length);
/*     */     
/* 403 */     Node childNode = (this.m_currentNode != null) ? this.m_currentNode.getLastChild() : null;
/* 404 */     if (childNode != null && childNode.getNodeType() == 3) {
/* 405 */       ((Text)childNode).appendData(s);
/*     */     } else {
/*     */       
/* 408 */       Text text = this.m_doc.createTextNode(s);
/* 409 */       append(text);
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
/*     */   public void charactersRaw(char[] ch, int start, int length) throws SAXException {
/* 426 */     if (isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 431 */     String s = new String(ch, start, length);
/*     */     
/* 433 */     append(this.m_doc.createProcessingInstruction("xslt-next-is-raw", "formatter-to-dom"));
/*     */     
/* 435 */     append(this.m_doc.createTextNode(s));
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
/*     */   public void startEntity(String name) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endEntity(String name) throws SAXException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void entityReference(String name) throws SAXException {
/* 474 */     append(this.m_doc.createEntityReference(name));
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
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 502 */     if (isOutsideDocElem()) {
/*     */       return;
/*     */     }
/* 505 */     String s = new String(ch, start, length);
/*     */     
/* 507 */     append(this.m_doc.createTextNode(s));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isOutsideDocElem() {
/* 517 */     return (null == this.m_docFrag && this.m_elemStack.size() == 0 && (null == this.m_currentNode || this.m_currentNode.getNodeType() == 9));
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
/*     */   public void processingInstruction(String target, String data) throws SAXException {
/* 538 */     append(this.m_doc.createProcessingInstruction(target, data));
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
/*     */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 554 */     append(this.m_doc.createComment(new String(ch, start, length)));
/*     */   }
/*     */   
/*     */   public DOMBuilder(Document doc, Node node) {
/* 558 */     this.m_inCData = false; this.m_doc = doc; this.m_currentNode = node; } public DOMBuilder(Document doc, DocumentFragment docFrag) { this.m_inCData = false; this.m_doc = doc; this.m_docFrag = docFrag; } public DOMBuilder(Document doc) { this.m_inCData = false;
/*     */     this.m_doc = doc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCDATA() throws SAXException {
/* 567 */     this.m_inCData = true;
/* 568 */     append(this.m_doc.createCDATASection(""));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCDATA() throws SAXException {
/* 578 */     this.m_inCData = false;
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
/*     */   public void cdata(char[] ch, int start, int length) throws SAXException {
/* 606 */     if (isOutsideDocElem() && XMLCharacterRecognizer.isWhiteSpace(ch, start, length)) {
/*     */       return;
/*     */     }
/*     */     
/* 610 */     String s = new String(ch, start, length);
/*     */     
/* 612 */     CDATASection section = (CDATASection)this.m_currentNode.getLastChild();
/* 613 */     section.appendData(s);
/*     */   }
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {}
/*     */   
/*     */   public void endDTD() throws SAXException {}
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException {}
/*     */   
/*     */   public void endPrefixMapping(String prefix) throws SAXException {}
/*     */   
/*     */   public void skippedEntity(String name) throws SAXException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/DOMBuilder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */