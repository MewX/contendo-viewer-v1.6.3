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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSVGItem
/*    */   implements SVGItem
/*    */ {
/*    */   protected AbstractSVGList parent;
/*    */   protected String itemStringValue;
/*    */   
/*    */   protected abstract String getStringValue();
/*    */   
/*    */   public void setParent(AbstractSVGList list) {
/* 56 */     this.parent = list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractSVGList getParent() {
/* 63 */     return this.parent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void resetAttribute() {
/* 71 */     if (this.parent != null) {
/* 72 */       this.itemStringValue = null;
/* 73 */       this.parent.itemChanged();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValueAsString() {
/* 82 */     if (this.itemStringValue == null) {
/* 83 */       this.itemStringValue = getStringValue();
/*    */     }
/* 85 */     return this.itemStringValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */