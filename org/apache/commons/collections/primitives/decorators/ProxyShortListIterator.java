/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortIterator;
/*    */ import org.apache.commons.collections.primitives.ShortListIterator;
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
/*    */ abstract class ProxyShortListIterator
/*    */   extends ProxyShortIterator
/*    */   implements ShortListIterator
/*    */ {
/*    */   public boolean hasPrevious() {
/* 33 */     return getListIterator().hasPrevious();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 37 */     return getListIterator().nextIndex();
/*    */   }
/*    */   
/*    */   public short previous() {
/* 41 */     return getListIterator().previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 45 */     return getListIterator().previousIndex();
/*    */   }
/*    */   
/*    */   protected final ShortIterator getIterator() {
/* 49 */     return (ShortIterator)getListIterator();
/*    */   }
/*    */   
/*    */   protected abstract ShortListIterator getListIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyShortListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */