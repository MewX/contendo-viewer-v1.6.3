/*    */ package org.apache.batik.dom.svg;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGNumberItem
/*    */   extends AbstractSVGNumber
/*    */   implements SVGItem
/*    */ {
/*    */   protected AbstractSVGList parentList;
/*    */   
/*    */   public SVGNumberItem(float value) {
/* 37 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValueAsString() {
/* 44 */     return Float.toString(this.value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParent(AbstractSVGList list) {
/* 51 */     this.parentList = list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractSVGList getParent() {
/* 58 */     return this.parentList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void reset() {
/* 66 */     if (this.parentList != null)
/* 67 */       this.parentList.itemChanged(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGNumberItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */