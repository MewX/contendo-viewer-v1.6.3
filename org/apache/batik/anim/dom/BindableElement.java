/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BindableElement
/*     */   extends SVGGraphicsElement
/*     */ {
/*     */   protected String namespaceURI;
/*     */   protected String localName;
/*     */   protected XBLOMShadowTreeElement xblShadowTree;
/*     */   
/*     */   protected BindableElement() {}
/*     */   
/*     */   public BindableElement(String prefix, AbstractDocument owner, String ns, String ln) {
/*  66 */     super(prefix, owner);
/*  67 */     this.namespaceURI = ns;
/*  68 */     this.localName = ln;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  75 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  82 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  97 */     return (Node)new BindableElement(null, null, this.namespaceURI, this.localName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShadowTree(XBLOMShadowTreeElement s) {
/* 104 */     this.xblShadowTree = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XBLOMShadowTreeElement getShadowTree() {
/* 111 */     return this.xblShadowTree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSFirstChild() {
/* 120 */     if (this.xblShadowTree != null) {
/* 121 */       return this.xblShadowTree.getFirstChild();
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSLastChild() {
/* 130 */     return getCSSFirstChild();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/BindableElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */