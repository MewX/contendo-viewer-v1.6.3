/*      */ package org.apache.xml.utils;
/*      */ 
/*      */ import org.apache.xml.res.XMLMessages;
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
/*      */ public class UnImplNode
/*      */   implements Document, Element, Node, NodeList
/*      */ {
/*      */   public void error(String msg) {
/*   61 */     System.out.println("DOM ERROR! class: " + getClass().getName());
/*      */     
/*   63 */     throw new RuntimeException(XMLMessages.createXMLMessage(msg, null));
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
/*      */   public void error(String msg, Object[] args) {
/*   75 */     System.out.println("DOM ERROR! class: " + getClass().getName());
/*      */     
/*   77 */     throw new RuntimeException(XMLMessages.createXMLMessage(msg, args));
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
/*      */   public Node appendChild(Node newChild) throws DOMException {
/*   92 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*   94 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes() {
/*  105 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  107 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType() {
/*  118 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  120 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getParentNode() {
/*  131 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  133 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getChildNodes() {
/*  144 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  146 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getFirstChild() {
/*  157 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  159 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getLastChild() {
/*  170 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  172 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getNextSibling() {
/*  183 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  185 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLength() {
/*  196 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  198 */     return 0;
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
/*      */   public Node item(int index) {
/*  211 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  213 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document getOwnerDocument() {
/*  224 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  226 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTagName() {
/*  237 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  239 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName() {
/*  250 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  252 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void normalize() {
/*  258 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public NodeList getElementsByTagName(String name) {
/*  271 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  273 */     return null;
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
/*      */   public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
/*  288 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  290 */     return null;
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
/*      */   public Attr setAttributeNode(Attr newAttr) throws DOMException {
/*  305 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  307 */     return null;
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
/*      */   public boolean hasAttribute(String name) {
/*  321 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  323 */     return false;
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
/*      */   public boolean hasAttributeNS(String name, String x) {
/*  338 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  340 */     return false;
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
/*      */   public Attr getAttributeNode(String name) {
/*  354 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  356 */     return null;
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
/*      */   public void removeAttribute(String name) throws DOMException {
/*  368 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public void setAttribute(String name, String value) throws DOMException {
/*  381 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public String getAttribute(String name) {
/*  394 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  396 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAttributes() {
/*  407 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  409 */     return false;
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
/*      */   public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
/*  424 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  426 */     return null;
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
/*      */   public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
/*  441 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  443 */     return null;
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
/*      */   public Attr getAttributeNodeNS(String namespaceURI, String localName) {
/*  457 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  459 */     return null;
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
/*      */   public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
/*  473 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
/*  490 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public String getAttributeNS(String namespaceURI, String localName) {
/*  504 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  506 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getPreviousSibling() {
/*  517 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  519 */     return null;
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
/*      */   public Node cloneNode(boolean deep) {
/*  532 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  534 */     return null;
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
/*      */   public String getNodeValue() throws DOMException {
/*  547 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  549 */     return null;
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
/*      */   public void setNodeValue(String nodeValue) throws DOMException {
/*  561 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValue(String value) throws DOMException {
/*  589 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   
/*      */   public Element getOwnerElement() {
/*  611 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  613 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSpecified() {
/*  624 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  626 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NamedNodeMap getAttributes() {
/*  637 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  639 */     return null;
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
/*      */   public Node insertBefore(Node newChild, Node refChild) throws DOMException {
/*  655 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  657 */     return null;
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
/*      */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
/*  673 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  675 */     return null;
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
/*      */   public Node removeChild(Node oldChild) throws DOMException {
/*  690 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  692 */     return null;
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
/*      */   public boolean isSupported(String feature, String version) {
/*  713 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceURI() {
/*  724 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  726 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix() {
/*  737 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  739 */     return null;
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
/*      */   public void setPrefix(String prefix) throws DOMException {
/*  751 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalName() {
/*  762 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  764 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentType getDoctype() {
/*  775 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  777 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMImplementation getImplementation() {
/*  788 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  790 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getDocumentElement() {
/*  801 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  803 */     return null;
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
/*      */   public Element createElement(String tagName) throws DOMException {
/*  818 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  820 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentFragment createDocumentFragment() {
/*  831 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  833 */     return null;
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
/*      */   public Text createTextNode(String data) {
/*  846 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  848 */     return null;
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
/*      */   public Comment createComment(String data) {
/*  861 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  863 */     return null;
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
/*      */   public CDATASection createCDATASection(String data) throws DOMException {
/*  878 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  880 */     return null;
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
/*      */   public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
/*  897 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  899 */     return null;
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
/*      */   public Attr createAttribute(String name) throws DOMException {
/*  914 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  916 */     return null;
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
/*      */   public EntityReference createEntityReference(String name) throws DOMException {
/*  932 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  934 */     return null;
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
/*      */   
/*      */   public Node importNode(Node importedNode, boolean deep) throws DOMException {
/*  956 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  958 */     return null;
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
/*      */   public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
/*  975 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  977 */     return null;
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
/*      */   public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
/*  994 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/*  996 */     return null;
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
/*      */   public Element getElementById(String elementId) {
/* 1009 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1011 */     return null;
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
/*      */   public void setData(String data) throws DOMException {
/* 1024 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public String substringData(int offset, int count) throws DOMException {
/* 1040 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1042 */     return null;
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
/*      */   public void appendData(String arg) throws DOMException {
/* 1054 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public void insertData(int offset, String arg) throws DOMException {
/* 1068 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public void deleteData(int offset, int count) throws DOMException {
/* 1081 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public void replaceData(int offset, int count, String arg) throws DOMException {
/* 1096 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/*      */   public Text splitText(int offset) throws DOMException {
/* 1111 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1113 */     return null;
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
/* 1129 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1131 */     return null;
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
/* 1148 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1150 */     return null;
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
/* 1166 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/* 1183 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1185 */     return false;
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
/* 1201 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/* 1222 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1224 */     return false;
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
/* 1244 */     error("ER_FUNCTION_NOT_SUPPORTED");
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
/* 1261 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */     
/* 1263 */     return null;
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
/* 1279 */     error("ER_FUNCTION_NOT_SUPPORTED");
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/UnImplNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */