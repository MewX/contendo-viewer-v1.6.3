/*    */ package org.apache.batik.extension;
/*    */ 
/*    */ import org.apache.batik.anim.dom.SVGOMElement;
/*    */ import org.apache.batik.dom.AbstractDocument;
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
/*    */ public abstract class ExtensionElement
/*    */   extends SVGOMElement
/*    */ {
/*    */   protected ExtensionElement() {}
/*    */   
/*    */   protected ExtensionElement(String name, AbstractDocument owner) {
/* 46 */     super(name, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadonly() {
/* 53 */     return false;
/*    */   }
/*    */   
/*    */   public void setReadonly(boolean v) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/ExtensionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */