/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import java.util.Vector;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.apache.xalan.xsltc.runtime.Constants;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
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
/*     */ public class SAX2DOM
/*     */   implements Constants, ContentHandler, LexicalHandler
/*     */ {
/*  47 */   private Node _root = null;
/*  48 */   private Document _document = null;
/*  49 */   private Stack _nodeStk = new Stack();
/*  50 */   private Vector _namespaceDecls = null;
/*     */   
/*     */   public SAX2DOM() throws ParserConfigurationException {
/*  53 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*     */     
/*  55 */     this._document = factory.newDocumentBuilder().newDocument();
/*  56 */     this._root = this._document;
/*     */   }
/*     */   
/*     */   public SAX2DOM(Node root) throws ParserConfigurationException {
/*  60 */     this._root = root;
/*  61 */     if (root instanceof Document) {
/*  62 */       this._document = (Document)root;
/*     */     }
/*  64 */     else if (root != null) {
/*  65 */       this._document = root.getOwnerDocument();
/*     */     } else {
/*     */       
/*  68 */       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*     */       
/*  70 */       this._document = factory.newDocumentBuilder().newDocument();
/*  71 */       this._root = this._document;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Node getDOM() {
/*  76 */     return this._root;
/*     */   }
/*     */   
/*     */   public void characters(char[] ch, int start, int length) {
/*  80 */     Node last = this._nodeStk.peek();
/*     */ 
/*     */     
/*  83 */     if (last != this._document) {
/*  84 */       String text = new String(ch, start, length);
/*  85 */       last.appendChild(this._document.createTextNode(text));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startDocument() {
/*  90 */     this._nodeStk.push(this._root);
/*     */   }
/*     */   
/*     */   public void endDocument() {
/*  94 */     this._nodeStk.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startElement(String namespace, String localName, String qName, Attributes attrs) {
/* 100 */     Element tmp = this._document.createElementNS(namespace, qName);
/*     */ 
/*     */     
/* 103 */     if (this._namespaceDecls != null) {
/* 104 */       int nDecls = this._namespaceDecls.size();
/* 105 */       for (int j = 0; j < nDecls; j++) {
/* 106 */         String prefix = this._namespaceDecls.elementAt(j++);
/*     */         
/* 108 */         if (prefix == null || prefix.equals("")) {
/* 109 */           tmp.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", this._namespaceDecls.elementAt(j));
/*     */         }
/*     */         else {
/*     */           
/* 113 */           tmp.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, this._namespaceDecls.elementAt(j));
/*     */         } 
/*     */       } 
/*     */       
/* 117 */       this._namespaceDecls.clear();
/*     */     } 
/*     */ 
/*     */     
/* 121 */     int nattrs = attrs.getLength();
/* 122 */     for (int i = 0; i < nattrs; i++) {
/* 123 */       if (attrs.getLocalName(i) == null) {
/* 124 */         tmp.setAttribute(attrs.getQName(i), attrs.getValue(i));
/*     */       } else {
/*     */         
/* 127 */         tmp.setAttributeNS(attrs.getURI(i), attrs.getQName(i), attrs.getValue(i));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 133 */     Node last = this._nodeStk.peek();
/* 134 */     last.appendChild(tmp);
/*     */ 
/*     */     
/* 137 */     this._nodeStk.push(tmp);
/*     */   }
/*     */   
/*     */   public void endElement(String namespace, String localName, String qName) {
/* 141 */     this._nodeStk.pop();
/*     */   }
/*     */   
/*     */   public void startPrefixMapping(String prefix, String uri) {
/* 145 */     if (this._namespaceDecls == null) {
/* 146 */       this._namespaceDecls = new Vector(2);
/*     */     }
/* 148 */     this._namespaceDecls.addElement(prefix);
/* 149 */     this._namespaceDecls.addElement(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPrefixMapping(String prefix) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableWhitespace(char[] ch, int start, int length) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processingInstruction(String target, String data) {
/* 167 */     Node last = this._nodeStk.peek();
/* 168 */     ProcessingInstruction pi = this._document.createProcessingInstruction(target, data);
/*     */     
/* 170 */     if (pi != null) last.appendChild(pi);
/*     */   
/*     */   }
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
/*     */   public void skippedEntity(String name) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(char[] ch, int start, int length) {
/* 192 */     Node last = this._nodeStk.peek();
/* 193 */     Comment comment = this._document.createComment(new String(ch, start, length));
/* 194 */     if (comment != null) last.appendChild(comment); 
/*     */   }
/*     */   
/*     */   public void startCDATA() {}
/*     */   
/*     */   public void endCDATA() {}
/*     */   
/*     */   public void startEntity(String name) {}
/*     */   
/*     */   public void endDTD() {}
/*     */   
/*     */   public void endEntity(String name) {}
/*     */   
/*     */   public void startDTD(String name, String publicId, String systemId) throws SAXException {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/SAX2DOM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */