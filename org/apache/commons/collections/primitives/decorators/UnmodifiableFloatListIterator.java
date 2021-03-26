/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableFloatListIterator
/*    */   extends ProxyFloatListIterator
/*    */ {
/*    */   private FloatListIterator proxied;
/*    */   
/*    */   UnmodifiableFloatListIterator(FloatListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This FloatListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(float value) {
/*    */     throw new UnsupportedOperationException("This FloatListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(float value) {
/*    */     throw new UnsupportedOperationException("This FloatListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected FloatListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final FloatListIterator wrap(FloatListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableFloatListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableFloatListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableFloatListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */