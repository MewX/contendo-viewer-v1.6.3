/*    */ package org.apache.batik.dom;
/*    */ 
/*    */ import org.w3c.dom.DOMException;
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
/*    */ public class GenericAttrNS
/*    */   extends AbstractAttrNS
/*    */ {
/*    */   protected boolean readonly;
/*    */   
/*    */   protected GenericAttrNS() {}
/*    */   
/*    */   public GenericAttrNS(String nsURI, String qname, AbstractDocument owner) throws DOMException {
/* 66 */     super(nsURI, qname, owner);
/* 67 */     setNodeName(qname);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadonly() {
/* 74 */     return this.readonly;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReadonly(boolean v) {
/* 81 */     this.readonly = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 88 */     return new GenericAttrNS();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericAttrNS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */