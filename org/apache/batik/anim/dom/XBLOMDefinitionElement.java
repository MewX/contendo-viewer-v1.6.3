/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.dom.util.DOMUtilities;
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
/*    */ public class XBLOMDefinitionElement
/*    */   extends XBLOMElement
/*    */ {
/*    */   protected XBLOMDefinitionElement() {}
/*    */   
/*    */   public XBLOMDefinitionElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "definition";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 61 */     return (Node)new XBLOMDefinitionElement();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getElementNamespaceURI() {
/* 68 */     String qname = getAttributeNS(null, "element");
/* 69 */     String prefix = DOMUtilities.getPrefix(qname);
/* 70 */     String ns = lookupNamespaceURI(prefix);
/* 71 */     if (ns == null) {
/* 72 */       throw createDOMException((short)14, "prefix", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), prefix });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 79 */     return ns;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getElementLocalName() {
/* 86 */     String qname = getAttributeNS(null, "element");
/* 87 */     return DOMUtilities.getLocalName(qname);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/XBLOMDefinitionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */