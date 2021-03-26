/*    */ package org.apache.batik.dom;
/*    */ 
/*    */ import org.w3c.dom.DocumentFragment;
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
/*    */ public abstract class AbstractDocumentFragment
/*    */   extends AbstractParentNode
/*    */   implements DocumentFragment
/*    */ {
/*    */   public String getNodeName() {
/* 41 */     return "#document-fragment";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getNodeType() {
/* 49 */     return 11;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void checkChildType(Node n, boolean replace) {
/* 56 */     switch (n.getNodeType()) {
/*    */       case 1:
/*    */       case 3:
/*    */       case 4:
/*    */       case 5:
/*    */       case 7:
/*    */       case 8:
/*    */       case 11:
/*    */         return;
/*    */     } 
/* 66 */     throw createDOMException((short)3, "child.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractDocumentFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */