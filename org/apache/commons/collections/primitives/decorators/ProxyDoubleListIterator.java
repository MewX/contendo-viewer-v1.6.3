/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleIterator;
/*    */ import org.apache.commons.collections.primitives.DoubleListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class ProxyDoubleListIterator
/*    */   extends ProxyDoubleIterator
/*    */   implements DoubleListIterator
/*    */ {
/*    */   public boolean hasPrevious() {
/* 33 */     return getListIterator().hasPrevious();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 37 */     return getListIterator().nextIndex();
/*    */   }
/*    */   
/*    */   public double previous() {
/* 41 */     return getListIterator().previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 45 */     return getListIterator().previousIndex();
/*    */   }
/*    */   
/*    */   protected final DoubleIterator getIterator() {
/* 49 */     return (DoubleIterator)getListIterator();
/*    */   }
/*    */   
/*    */   protected abstract DoubleListIterator getListIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyDoubleListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */