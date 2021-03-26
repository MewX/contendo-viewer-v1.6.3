/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSNavigableNode;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractDocumentFragment;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class SVGOMUseShadowRoot
/*     */   extends AbstractDocumentFragment
/*     */   implements CSSNavigableNode, IdContainer
/*     */ {
/*     */   protected Element cssParentElement;
/*     */   protected boolean isLocal;
/*     */   
/*     */   protected SVGOMUseShadowRoot() {}
/*     */   
/*     */   public SVGOMUseShadowRoot(AbstractDocument owner, Element parent, boolean isLocal) {
/*  62 */     this.ownerDocument = owner;
/*  63 */     this.cssParentElement = parent;
/*  64 */     this.isLocal = isLocal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadonly() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadonly(boolean v) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getElementById(String id) {
/*  83 */     return this.ownerDocument.getChildElementById((Node)this, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSParentNode() {
/*  92 */     return this.cssParentElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSPreviousSibling() {
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSNextSibling() {
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSFirstChild() {
/* 113 */     return getFirstChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCSSLastChild() {
/* 120 */     return getLastChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHiddenFromSelectors() {
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeEventTarget getParentNodeEventTarget() {
/* 136 */     return (NodeEventTarget)getCSSParentNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 143 */     return (Node)new SVGOMUseShadowRoot();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGOMUseShadowRoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */