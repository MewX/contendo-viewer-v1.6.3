/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.dom.svg.IdContainer;
/*    */ import org.apache.batik.dom.xbl.XBLShadowTreeElement;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XBLOMShadowTreeElement
/*    */   extends XBLOMElement
/*    */   implements IdContainer, XBLShadowTreeElement
/*    */ {
/*    */   protected XBLOMShadowTreeElement() {}
/*    */   
/*    */   public XBLOMShadowTreeElement(String prefix, AbstractDocument owner) {
/* 50 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 57 */     return "shadowTree";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 64 */     return (Node)new XBLOMShadowTreeElement();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element getElementById(String elementId) {
/* 73 */     return getElementById(elementId, (Node)this);
/*    */   }
/*    */   
/*    */   protected Element getElementById(String elementId, Node n) {
/* 77 */     if (n.getNodeType() == 1) {
/* 78 */       Element e = (Element)n;
/* 79 */       if (e.getAttributeNS(null, "id").equals(elementId)) {
/* 80 */         return (Element)n;
/*    */       }
/*    */     } 
/* 83 */     for (Node m = n.getFirstChild(); m != null; m = m.getNextSibling()) {
/* 84 */       Element result = getElementById(elementId, m);
/* 85 */       if (result != null) {
/* 86 */         return result;
/*    */       }
/*    */     } 
/* 89 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Node getCSSParentNode() {
/* 99 */     return this.ownerDocument.getXBLManager().getXblBoundElement((Node)this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/XBLOMShadowTreeElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */