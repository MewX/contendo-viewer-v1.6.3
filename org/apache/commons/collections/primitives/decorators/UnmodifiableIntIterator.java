/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntIterator;
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
/*    */ public final class UnmodifiableIntIterator
/*    */   extends ProxyIntIterator
/*    */ {
/*    */   private IntIterator proxied;
/*    */   
/*    */   UnmodifiableIntIterator(IntIterator iterator) {
/* 51 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This IntIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected IntIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final IntIterator wrap(IntIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableIntIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableIntIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableIntIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */