/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleIterator;
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
/*    */ public final class UnmodifiableDoubleIterator
/*    */   extends ProxyDoubleIterator
/*    */ {
/*    */   private DoubleIterator proxied;
/*    */   
/*    */   UnmodifiableDoubleIterator(DoubleIterator iterator) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This DoubleIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected DoubleIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final DoubleIterator wrap(DoubleIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableDoubleIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableDoubleIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableDoubleIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */