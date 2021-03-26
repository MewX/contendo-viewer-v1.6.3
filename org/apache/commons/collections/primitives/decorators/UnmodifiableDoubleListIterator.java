/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
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
/*    */ public final class UnmodifiableDoubleListIterator
/*    */   extends ProxyDoubleListIterator
/*    */ {
/*    */   private DoubleListIterator proxied;
/*    */   
/*    */   UnmodifiableDoubleListIterator(DoubleListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This DoubleListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(double value) {
/*    */     throw new UnsupportedOperationException("This DoubleListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(double value) {
/*    */     throw new UnsupportedOperationException("This DoubleListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected DoubleListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final DoubleListIterator wrap(DoubleListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableDoubleListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableDoubleListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableDoubleListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */