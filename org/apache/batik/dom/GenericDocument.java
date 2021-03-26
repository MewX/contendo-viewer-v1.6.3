/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.CDATASection;
/*     */ import org.w3c.dom.Comment;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.EntityReference;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.ProcessingInstruction;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenericDocument
/*     */   extends AbstractDocument
/*     */ {
/*     */   protected static final String ATTR_ID = "id";
/*     */   protected boolean readonly;
/*     */   
/*     */   protected GenericDocument() {}
/*     */   
/*     */   public GenericDocument(DocumentType dt, DOMImplementation impl) {
/*  66 */     super(dt, impl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/*  73 */     return this.readonly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {
/*  80 */     this.readonly = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isId(Attr node) {
/*  88 */     if (node.getNamespaceURI() != null) return false; 
/*  89 */     return "id".equals(node.getNodeName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElement(String tagName) throws DOMException {
/*  97 */     return new GenericElement(tagName.intern(), this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentFragment createDocumentFragment() {
/* 105 */     return new GenericDocumentFragment(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Text createTextNode(String data) {
/* 113 */     return new GenericText(data, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comment createComment(String data) {
/* 121 */     return new GenericComment(data, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CDATASection createCDATASection(String data) throws DOMException {
/* 129 */     return new GenericCDATASection(data, this);
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
/*     */   public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
/* 141 */     return new GenericProcessingInstruction(target, data, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attr createAttribute(String name) throws DOMException {
/* 149 */     return new GenericAttr(name.intern(), this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityReference createEntityReference(String name) throws DOMException {
/* 158 */     return new GenericEntityReference(name, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
/* 167 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/* 168 */       namespaceURI = null;
/*     */     }
/* 170 */     if (namespaceURI == null) {
/* 171 */       return new GenericElement(qualifiedName.intern(), this);
/*     */     }
/* 173 */     return new GenericElementNS(namespaceURI.intern(), qualifiedName.intern(), this);
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
/*     */   public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
/* 185 */     if (namespaceURI != null && namespaceURI.length() == 0) {
/* 186 */       namespaceURI = null;
/*     */     }
/* 188 */     if (namespaceURI == null) {
/* 189 */       return new GenericAttr(qualifiedName.intern(), this);
/*     */     }
/* 191 */     return new GenericAttrNS(namespaceURI.intern(), qualifiedName.intern(), this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 201 */     return new GenericDocument();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */