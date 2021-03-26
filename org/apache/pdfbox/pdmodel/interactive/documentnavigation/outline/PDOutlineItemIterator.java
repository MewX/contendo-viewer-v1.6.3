/*    */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class PDOutlineItemIterator
/*    */   implements Iterator<PDOutlineItem>
/*    */ {
/*    */   private PDOutlineItem currentItem;
/*    */   private final PDOutlineItem startingItem;
/*    */   
/*    */   PDOutlineItemIterator(PDOutlineItem startingItem) {
/* 34 */     this.startingItem = startingItem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasNext() {
/* 40 */     return (this.startingItem != null && (this.currentItem == null || (this.currentItem
/* 41 */       .getNextSibling() != null && 
/* 42 */       !this.startingItem.equals(this.currentItem.getNextSibling()))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PDOutlineItem next() {
/* 48 */     if (this.currentItem == null) {
/*    */       
/* 50 */       this.currentItem = this.startingItem;
/*    */     }
/*    */     else {
/*    */       
/* 54 */       this.currentItem = this.currentItem.getNextSibling();
/*    */     } 
/* 56 */     return this.currentItem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove() {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/outline/PDOutlineItemIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */