/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatIterator;
/*    */ import org.apache.commons.collections.primitives.FloatListIterator;
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
/*    */ abstract class ProxyFloatListIterator
/*    */   extends ProxyFloatIterator
/*    */   implements FloatListIterator
/*    */ {
/*    */   public boolean hasPrevious() {
/* 33 */     return getListIterator().hasPrevious();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 37 */     return getListIterator().nextIndex();
/*    */   }
/*    */   
/*    */   public float previous() {
/* 41 */     return getListIterator().previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 45 */     return getListIterator().previousIndex();
/*    */   }
/*    */   
/*    */   protected final FloatIterator getIterator() {
/* 49 */     return (FloatIterator)getListIterator();
/*    */   }
/*    */   
/*    */   protected abstract FloatListIterator getListIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyFloatListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */