/*    */ package org.apache.batik.dom;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractParentChildNode
/*    */   extends AbstractParentNode
/*    */ {
/*    */   protected Node parentNode;
/*    */   protected Node previousSibling;
/*    */   protected Node nextSibling;
/*    */   
/*    */   public Node getParentNode() {
/* 51 */     return this.parentNode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParentNode(Node v) {
/* 58 */     this.parentNode = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPreviousSibling(Node v) {
/* 65 */     this.previousSibling = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Node getPreviousSibling() {
/* 73 */     return this.previousSibling;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNextSibling(Node v) {
/* 80 */     this.nextSibling = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Node getNextSibling() {
/* 88 */     return this.nextSibling;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractParentChildNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */