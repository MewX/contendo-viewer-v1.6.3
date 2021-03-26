/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
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
/*    */ public final class UnmodifiableLongListIterator
/*    */   extends ProxyLongListIterator
/*    */ {
/*    */   private LongListIterator proxied;
/*    */   
/*    */   UnmodifiableLongListIterator(LongListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This LongListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(long value) {
/*    */     throw new UnsupportedOperationException("This LongListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(long value) {
/*    */     throw new UnsupportedOperationException("This LongListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected LongListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final LongListIterator wrap(LongListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableLongListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableLongListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableLongListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */