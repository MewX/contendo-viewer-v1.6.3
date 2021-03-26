/*    */ package org.apache.batik.dom.svg;
/*    */ 
/*    */ import org.w3c.dom.svg.SVGNumber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSVGNumber
/*    */   implements SVGNumber
/*    */ {
/*    */   protected float value;
/*    */   
/*    */   public float getValue() {
/* 40 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(float f) {
/* 47 */     this.value = f;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */