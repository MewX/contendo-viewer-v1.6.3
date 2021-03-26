/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntIterator;
/*    */ import org.apache.commons.collections.primitives.IntListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class ProxyIntListIterator
/*    */   extends ProxyIntIterator
/*    */   implements IntListIterator
/*    */ {
/*    */   public boolean hasPrevious() {
/* 33 */     return getListIterator().hasPrevious();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 37 */     return getListIterator().nextIndex();
/*    */   }
/*    */   
/*    */   public int previous() {
/* 41 */     return getListIterator().previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 45 */     return getListIterator().previousIndex();
/*    */   }
/*    */   
/*    */   protected final IntIterator getIterator() {
/* 49 */     return (IntIterator)getListIterator();
/*    */   }
/*    */   
/*    */   protected abstract IntListIterator getListIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyIntListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */