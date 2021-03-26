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
/*    */ public class GenericComment
/*    */   extends AbstractComment
/*    */ {
/*    */   protected boolean readonly;
/*    */   
/*    */   public GenericComment() {}
/*    */   
/*    */   public GenericComment(String value, AbstractDocument owner) {
/* 45 */     this.ownerDocument = owner;
/* 46 */     setNodeValue(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadonly() {
/* 53 */     return this.readonly;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReadonly(boolean v) {
/* 60 */     this.readonly = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 67 */     return new GenericComment();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericComment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */