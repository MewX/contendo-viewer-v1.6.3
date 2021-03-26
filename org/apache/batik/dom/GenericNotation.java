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
/*    */ public class GenericNotation
/*    */   extends AbstractNotation
/*    */ {
/*    */   protected boolean readonly;
/*    */   
/*    */   protected GenericNotation() {}
/*    */   
/*    */   public GenericNotation(String name, String pubId, String sysId, AbstractDocument owner) {
/* 48 */     this.ownerDocument = owner;
/* 49 */     setNodeName(name);
/* 50 */     setPublicId(pubId);
/* 51 */     setSystemId(sysId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadonly() {
/* 58 */     return this.readonly;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReadonly(boolean v) {
/* 65 */     this.readonly = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 72 */     return new GenericNotation();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericNotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */