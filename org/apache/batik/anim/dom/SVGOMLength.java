/*    */ package org.apache.batik.anim.dom;
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
/*    */ public class SVGOMLength
/*    */   extends AbstractSVGLength
/*    */ {
/*    */   protected AbstractElement element;
/*    */   
/*    */   public SVGOMLength(AbstractElement elt) {
/* 49 */     super((short)0);
/* 50 */     this.element = elt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected SVGOMElement getAssociatedElement() {
/* 56 */     return (SVGOMElement)this.element;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */