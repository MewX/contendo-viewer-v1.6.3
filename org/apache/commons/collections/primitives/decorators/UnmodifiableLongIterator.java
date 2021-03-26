/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.LongIterator;
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
/*    */ public final class UnmodifiableLongIterator
/*    */   extends ProxyLongIterator
/*    */ {
/*    */   private LongIterator proxied;
/*    */   
/*    */   UnmodifiableLongIterator(LongIterator iterator) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This LongIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected LongIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final LongIterator wrap(LongIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableLongIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableLongIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableLongIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */