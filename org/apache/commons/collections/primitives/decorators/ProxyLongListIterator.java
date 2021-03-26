/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.LongIterator;
/*    */ import org.apache.commons.collections.primitives.LongListIterator;
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
/*    */ abstract class ProxyLongListIterator
/*    */   extends ProxyLongIterator
/*    */   implements LongListIterator
/*    */ {
/*    */   public boolean hasPrevious() {
/* 33 */     return getListIterator().hasPrevious();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 37 */     return getListIterator().nextIndex();
/*    */   }
/*    */   
/*    */   public long previous() {
/* 41 */     return getListIterator().previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 45 */     return getListIterator().previousIndex();
/*    */   }
/*    */   
/*    */   protected final LongIterator getIterator() {
/* 49 */     return (LongIterator)getListIterator();
/*    */   }
/*    */   
/*    */   protected abstract LongListIterator getListIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyLongListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */