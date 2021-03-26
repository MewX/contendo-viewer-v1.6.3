/*     */ package org.apache.xml.dtm.ref.dom2dtm;
/*     */ 
/*     */ import org.apache.xml.dtm.DTMException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOM2DTMdefaultNamespaceDeclarationNode
/*     */   implements Attr
/*     */ {
/*  50 */   final String NOT_SUPPORTED_ERR = "Unsupported operation on pseudonode"; Element pseudoparent;
/*     */   String prefix;
/*     */   String uri;
/*     */   String nodename;
/*     */   int handle;
/*     */   
/*     */   DOM2DTMdefaultNamespaceDeclarationNode(Element pseudoparent, String prefix, String uri, int handle) {
/*  57 */     this.pseudoparent = pseudoparent;
/*  58 */     this.prefix = prefix;
/*  59 */     this.uri = uri;
/*  60 */     this.handle = handle;
/*  61 */     this.nodename = "xmlns:" + prefix;
/*     */   }
/*  63 */   public String getNodeName() { return this.nodename; }
/*  64 */   public String getName() { return this.nodename; }
/*  65 */   public String getNamespaceURI() { return "http://www.w3.org/2000/xmlns/"; }
/*  66 */   public String getPrefix() { return this.prefix; }
/*  67 */   public String getLocalName() { return this.prefix; }
/*  68 */   public String getNodeValue() { return this.uri; }
/*  69 */   public String getValue() { return this.uri; } public Element getOwnerElement() {
/*  70 */     return this.pseudoparent;
/*     */   }
/*  72 */   public boolean isSupported(String feature, String version) { return false; }
/*  73 */   public boolean hasChildNodes() { return false; }
/*  74 */   public boolean hasAttributes() { return false; }
/*  75 */   public Node getParentNode() { return null; }
/*  76 */   public Node getFirstChild() { return null; }
/*  77 */   public Node getLastChild() { return null; }
/*  78 */   public Node getPreviousSibling() { return null; }
/*  79 */   public Node getNextSibling() { return null; } public boolean getSpecified() {
/*  80 */     return false;
/*     */   }
/*  82 */   public NodeList getChildNodes() { return null; } public void normalize() {} public NamedNodeMap getAttributes() {
/*  83 */     return null; }
/*  84 */   public short getNodeType() { return 2; }
/*  85 */   public void setNodeValue(String value) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  86 */   public void setValue(String value) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  87 */   public void setPrefix(String value) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  88 */   public Node insertBefore(Node a, Node b) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  89 */   public Node replaceChild(Node a, Node b) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  90 */   public Node appendChild(Node a) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  91 */   public Node removeChild(Node a) { throw new DTMException("Unsupported operation on pseudonode"); }
/*  92 */   public Document getOwnerDocument() { return this.pseudoparent.getOwnerDocument(); } public Node cloneNode(boolean deep) {
/*  93 */     throw new DTMException("Unsupported operation on pseudonode");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHandleOfNode() {
/* 102 */     return this.handle;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/dom2dtm/DOM2DTMdefaultNamespaceDeclarationNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */