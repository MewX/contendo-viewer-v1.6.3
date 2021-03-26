/*      */ package org.apache.xml.dtm.ref;
/*      */ 
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMDOMException;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.CDATASection;
/*      */ import org.w3c.dom.Comment;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentFragment;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.EntityReference;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.ProcessingInstruction;
/*      */ import org.w3c.dom.Text;
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
/*      */ public class DTMNodeProxy
/*      */   implements Attr, Comment, Document, DocumentFragment, Element, Node, ProcessingInstruction, Text
/*      */ {
/*      */   public DTM dtm;
/*      */   int node;
/*   66 */   static final DOMImplementation implementation = new DTMNodeProxyImplementation();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMNodeProxy(DTM dtm, int node) {
/*   76 */     this.dtm = dtm;
/*   77 */     this.node = node;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DTM getDTM() {
/*   87 */     return this.dtm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getDTMNodeNumber() {
/*   97 */     return this.node;
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
/*      */   public final boolean equals(Node node) {
/*      */     
/*  112 */     try { DTMNodeProxy dtmp = (DTMNodeProxy)node;
/*      */ 
/*      */ 
/*      */       
/*  116 */       return (dtmp.node == this.node && dtmp.dtm == this.dtm); } catch (ClassCastException cce)
/*      */     
/*      */     { 
/*      */       
/*  120 */       return false; }
/*      */   
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
/*      */   public final boolean equals(Object node) {
/*      */     
/*  140 */     try { return equals((Node)node); } catch (ClassCastException cce)
/*      */     
/*      */     { 
/*      */       
/*  144 */       return false; }
/*      */   
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
/*      */   public final boolean sameNodeAs(Node other) {
/*  158 */     if (!(other instanceof DTMNodeProxy)) {
/*  159 */       return false;
/*      */     }
/*  161 */     DTMNodeProxy that = (DTMNodeProxy)other;
/*      */     
/*  163 */     return (this.dtm == that.dtm && this.node == that.node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getNodeName() {
/*  173 */     return this.dtm.getNodeName(this.node);
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
/*      */   public final String getTarget() {
/*  191 */     return this.dtm.getNodeName(this.node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getLocalName() {
/*  201 */     return this.dtm.getLocalName(this.node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getPrefix() {
/*  210 */     return this.dtm.getPrefix(this.node);
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
/*      */   public final void setPrefix(String prefix) throws DOMException {
/*  222 */     throw new DTMDOMException((short)7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getNamespaceURI() {
/*  232 */     return this.dtm.getNamespaceURI(this.node);
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
/*      */   
/*      */   public final boolean supports(String feature, String version) {
/*  253 */     return implementation.hasFeature(feature, version);
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
/*      */   public final boolean isSupported(String feature, String version) {
/*  269 */     return implementation.hasFeature(feature, version);
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
/*      */   public final String getNodeValue() throws DOMException {
/*  282 */     return this.dtm.getNodeValue(this.node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getStringValue() throws DOMException {
/*  292 */     return this.dtm.getStringValue(this.node).toString();
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
/*      */   public final void setNodeValue(String nodeValue) throws DOMException {
/*  304 */     throw new DTMDOMException((short)7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final short getNodeType() {
/*  314 */     return this.dtm.getNodeType(this.node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Node getParentNode() {
/*  325 */     if (getNodeType() == 2) {
/*  326 */       return null;
/*      */     }
/*  328 */     int newnode = this.dtm.getParent(this.node);
/*      */     
/*  330 */     return (newnode == -1) ? null : this.dtm.getNode(newnode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Node getOwnerNode() {
/*  341 */     int newnode = this.dtm.getParent(this.node);
/*      */     
/*  343 */     return (newnode == -1) ? null : this.dtm.getNode(newnode);
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
/*      */   public final NodeList getChildNodes() {
/*  357 */     return new DTMChildIterNodeList(this.dtm, this.node);
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
/*      */   public final Node getFirstChild() {
/*  370 */     int newnode = this.dtm.getFirstChild(this.node);
/*      */     
/*  372 */     return (newnode == -1) ? null : this.dtm.getNode(newnode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Node getLastChild() {
/*  383 */     int newnode = this.dtm.getLastChild(this.node);
/*      */     
/*  385 */     return (newnode == -1) ? null : this.dtm.getNode(newnode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Node getPreviousSibling() {
/*  396 */     int newnode = this.dtm.getPreviousSibling(this.node);
/*      */     
/*  398 */     return (newnode == -1) ? null : this.dtm.getNode(newnode);
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
/*      */   public final Node getNextSibling() {
/*  410 */     if (this.dtm.getNodeType(this.node) == 2) {
/*  411 */       return null;
/*      */     }
/*  413 */     int newnode = this.dtm.getNextSibling(this.node);
/*      */     
/*  415 */     return (newnode == -1) ? null : this.dtm.getNode(newnode);
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
/*      */   public final NamedNodeMap getAttributes() {
/*  428 */     return new DTMNamedNodeMap(this.dtm, this.node);
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
/*      */   public boolean hasAttribute(String name) {
/*  441 */     return (-1 != this.dtm.getAttributeNode(this.node, null, name));
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
/*      */   public boolean hasAttributeNS(String name, String x) {
/*  455 */     return (-1 != this.dtm.getAttributeNode(this.node, x, name));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Document getOwnerDocument() {
/*  466 */     return (Document)this.dtm.getNode(this.dtm.getOwnerDocument(this.node));
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
/*      */   public final Node insertBefore(Node newChild, Node refChild) throws DOMException {
/*  482 */     throw new DTMDOMException((short)7);
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
/*      */   public final Node replaceChild(Node newChild, Node oldChild) throws DOMException {
/*  498 */     throw new DTMDOMException((short)7);
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
/*      */   public final Node removeChild(Node oldChild) throws DOMException {
/*  512 */     throw new DTMDOMException((short)7);
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
/*      */   public final Node appendChild(Node newChild) throws DOMException {
/*  526 */     throw new DTMDOMException((short)7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasChildNodes() {
/*  536 */     return (-1 != this.dtm.getFirstChild(this.node));
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
/*      */   public final Node cloneNode(boolean deep) {
/*  548 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DocumentType getDoctype() {
/*  558 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DOMImplementation getImplementation() {
/*  568 */     return implementation;
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
/*      */   public final Element getDocumentElement() {
/*  580 */     int dochandle = this.dtm.getDocument();
/*  581 */     int elementhandle = -1;
/*  582 */     int kidhandle = this.dtm.getFirstChild(dochandle);
/*  583 */     for (; kidhandle != -1; 
/*  584 */       kidhandle = this.dtm.getNextSibling(kidhandle)) {
/*      */       
/*  586 */       switch (this.dtm.getNodeType(kidhandle)) {
/*      */         
/*      */         case 1:
/*  589 */           if (elementhandle != -1) {
/*      */             
/*  591 */             elementhandle = -1;
/*  592 */             kidhandle = this.dtm.getLastChild(dochandle);
/*      */             break;
/*      */           } 
/*  595 */           elementhandle = kidhandle;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 7:
/*      */         case 8:
/*      */         case 10:
/*      */           break;
/*      */         
/*      */         default:
/*  605 */           elementhandle = -1;
/*  606 */           kidhandle = this.dtm.getLastChild(dochandle);
/*      */           break;
/*      */       } 
/*      */     } 
/*  610 */     if (elementhandle == -1) {
/*  611 */       throw new DTMDOMException((short)9);
/*      */     }
/*  613 */     return (Element)this.dtm.getNode(elementhandle);
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
/*      */   public final Element createElement(String tagName) throws DOMException {
/*  627 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final DocumentFragment createDocumentFragment() {
/*  637 */     throw new DTMDOMException((short)9);
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
/*      */   public final Text createTextNode(String data) {
/*  649 */     throw new DTMDOMException((short)9);
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
/*      */   public final Comment createComment(String data) {
/*  661 */     throw new DTMDOMException((short)9);
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
/*      */   public final CDATASection createCDATASection(String data) throws DOMException {
/*  676 */     throw new DTMDOMException((short)9);
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
/*      */   public final ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
/*  692 */     throw new DTMDOMException((short)9);
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
/*      */   public final Attr createAttribute(String name) throws DOMException {
/*  706 */     throw new DTMDOMException((short)9);
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
/*      */   public final EntityReference createEntityReference(String name) throws DOMException {
/*  721 */     throw new DTMDOMException((short)9);
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
/*      */   public final NodeList getElementsByTagName(String tagname) {
/*  733 */     throw new DTMDOMException((short)9);
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
/*      */   public final Node importNode(Node importedNode, boolean deep) throws DOMException {
/*  749 */     throw new DTMDOMException((short)7);
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
/*      */   public final Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
/*  765 */     throw new DTMDOMException((short)9);
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
/*      */   public final Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
/*  781 */     throw new DTMDOMException((short)9);
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
/*      */   public final NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
/*  795 */     throw new DTMDOMException((short)9);
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
/*      */   public final Element getElementById(String elementId) {
/*  807 */     throw new DTMDOMException((short)9);
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
/*      */   public final Text splitText(int offset) throws DOMException {
/*  821 */     throw new DTMDOMException((short)9);
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
/*      */   public final String getData() throws DOMException {
/*  833 */     return this.dtm.getNodeValue(this.node);
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
/*      */   public final void setData(String data) throws DOMException {
/*  845 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getLength() {
/*  856 */     return this.dtm.getNodeValue(this.node).length();
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
/*      */   public final String substringData(int offset, int count) throws DOMException {
/*  871 */     return getData().substring(offset, offset + count);
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
/*      */   public final void appendData(String arg) throws DOMException {
/*  883 */     throw new DTMDOMException((short)9);
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
/*      */   public final void insertData(int offset, String arg) throws DOMException {
/*  896 */     throw new DTMDOMException((short)9);
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
/*      */   public final void deleteData(int offset, int count) throws DOMException {
/*  909 */     throw new DTMDOMException((short)9);
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
/*      */   public final void replaceData(int offset, int count, String arg) throws DOMException {
/*  924 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getTagName() {
/*  934 */     return this.dtm.getNodeName(this.node);
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
/*      */   public final String getAttribute(String name) {
/*  947 */     DTMNamedNodeMap map = new DTMNamedNodeMap(this.dtm, this.node);
/*  948 */     Node node = map.getNamedItem(name);
/*  949 */     return (null == node) ? null : node.getNodeValue();
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
/*      */   public final void setAttribute(String name, String value) throws DOMException {
/*  963 */     throw new DTMDOMException((short)9);
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
/*      */   public final void removeAttribute(String name) throws DOMException {
/*  975 */     throw new DTMDOMException((short)9);
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
/*      */   public final Attr getAttributeNode(String name) {
/*  988 */     DTMNamedNodeMap map = new DTMNamedNodeMap(this.dtm, this.node);
/*  989 */     return (Attr)map.getNamedItem(name);
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
/*      */   public final Attr setAttributeNode(Attr newAttr) throws DOMException {
/* 1003 */     throw new DTMDOMException((short)9);
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
/*      */   public final Attr removeAttributeNode(Attr oldAttr) throws DOMException {
/* 1017 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAttributes() {
/* 1027 */     return (-1 != this.dtm.getFirstAttribute(this.node));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void normalize() {
/* 1033 */     throw new DTMDOMException((short)9);
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
/*      */   public final String getAttributeNS(String namespaceURI, String localName) {
/* 1046 */     DTMNamedNodeMap map = new DTMNamedNodeMap(this.dtm, this.node);
/* 1047 */     Node node = map.getNamedItemNS(namespaceURI, localName);
/* 1048 */     return (null == node) ? null : node.getNodeValue();
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
/*      */   public final void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
/* 1064 */     throw new DTMDOMException((short)9);
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
/*      */   public final void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
/* 1078 */     throw new DTMDOMException((short)9);
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
/*      */   public final Attr getAttributeNodeNS(String namespaceURI, String localName) {
/* 1091 */     throw new DTMDOMException((short)9);
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
/*      */   public final Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
/* 1105 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getName() {
/* 1115 */     return this.dtm.getNodeName(this.node);
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
/*      */   public final boolean getSpecified() {
/* 1129 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String getValue() {
/* 1139 */     return this.dtm.getNodeValue(this.node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setValue(String value) {
/* 1149 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Element getOwnerElement() {
/* 1160 */     if (getNodeType() != 2) {
/* 1161 */       return null;
/*      */     }
/*      */     
/* 1164 */     int newnode = this.dtm.getParent(this.node);
/* 1165 */     return (newnode == -1) ? null : (Element)this.dtm.getNode(newnode);
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
/*      */   public Node adoptNode(Node source) throws DOMException {
/* 1181 */     throw new DTMDOMException((short)9);
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
/*      */   public String getEncoding() {
/* 1198 */     throw new DTMDOMException((short)9);
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
/*      */   public void setEncoding(String encoding) {
/* 1214 */     throw new DTMDOMException((short)9);
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
/*      */   public boolean getStandalone() {
/* 1231 */     throw new DTMDOMException((short)9);
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
/*      */   public void setStandalone(boolean standalone) {
/* 1247 */     throw new DTMDOMException((short)9);
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
/*      */   
/*      */   public boolean getStrictErrorChecking() {
/* 1268 */     throw new DTMDOMException((short)9);
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
/*      */   public void setStrictErrorChecking(boolean strictErrorChecking) {
/* 1288 */     throw new DTMDOMException((short)9);
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
/*      */   public String getVersion() {
/* 1305 */     throw new DTMDOMException((short)9);
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
/*      */   public void setVersion(String version) {
/* 1321 */     throw new DTMDOMException((short)9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class DTMNodeProxyImplementation
/*      */     implements DOMImplementation
/*      */   {
/*      */     public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) {
/* 1331 */       throw new DTMDOMException((short)9);
/*      */     }
/*      */ 
/*      */     
/*      */     public Document createDocument(String namespaceURI, String qualfiedName, DocumentType doctype) {
/* 1336 */       throw new DTMDOMException((short)9);
/*      */     }
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
/*      */     public boolean hasFeature(String feature, String version) {
/* 1349 */       if (("CORE".equals(feature.toUpperCase()) || "XML".equals(feature.toUpperCase())) && ("1.0".equals(version) || "2.0".equals(version)))
/*      */       {
/*      */ 
/*      */         
/* 1353 */         return true; } 
/* 1354 */       return false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMNodeProxy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */