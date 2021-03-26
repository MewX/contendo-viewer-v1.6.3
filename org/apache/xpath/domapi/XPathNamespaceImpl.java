/*     */ package org.apache.xpath.domapi;
/*     */ 
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.xpath.XPathNamespace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XPathNamespaceImpl
/*     */   implements XPathNamespace
/*     */ {
/*  73 */   Node m_attributeNode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPathNamespaceImpl(Node node) {
/*  79 */     this.m_attributeNode = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getOwnerElement() {
/*  86 */     return ((Attr)this.m_attributeNode).getOwnerElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  93 */     return "#namespace";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeValue() throws DOMException {
/* 100 */     return this.m_attributeNode.getNodeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeValue(String arg0) throws DOMException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNodeType() {
/* 113 */     return 13;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getParentNode() {
/* 120 */     return this.m_attributeNode.getParentNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getChildNodes() {
/* 127 */     return this.m_attributeNode.getChildNodes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getFirstChild() {
/* 134 */     return this.m_attributeNode.getFirstChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getLastChild() {
/* 141 */     return this.m_attributeNode.getLastChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getPreviousSibling() {
/* 148 */     return this.m_attributeNode.getPreviousSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getNextSibling() {
/* 155 */     return this.m_attributeNode.getNextSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedNodeMap getAttributes() {
/* 162 */     return this.m_attributeNode.getAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document getOwnerDocument() {
/* 169 */     return this.m_attributeNode.getOwnerDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node insertBefore(Node arg0, Node arg1) throws DOMException {
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node replaceChild(Node arg0, Node arg1) throws DOMException {
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node removeChild(Node arg0) throws DOMException {
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node appendChild(Node arg0) throws DOMException {
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChildNodes() {
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean arg0) {
/* 211 */     throw new DOMException((short)9, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void normalize() {
/* 218 */     this.m_attributeNode.normalize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSupported(String arg0, String arg1) {
/* 225 */     return this.m_attributeNode.isSupported(arg0, arg1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/* 235 */     return this.m_attributeNode.getNodeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 242 */     return this.m_attributeNode.getPrefix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrefix(String arg0) throws DOMException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 257 */     return this.m_attributeNode.getPrefix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAttributes() {
/* 264 */     return this.m_attributeNode.hasAttributes();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/domapi/XPathNamespaceImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */