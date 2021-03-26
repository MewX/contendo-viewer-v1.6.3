/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
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
/*    */ public final class UnmodifiableIntListIterator
/*    */   extends ProxyIntListIterator
/*    */ {
/*    */   private IntListIterator proxied;
/*    */   
/*    */   UnmodifiableIntListIterator(IntListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This IntListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(int value) {
/*    */     throw new UnsupportedOperationException("This IntListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(int value) {
/*    */     throw new UnsupportedOperationException("This IntListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected IntListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final IntListIterator wrap(IntListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableIntListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableIntListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableIntListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */