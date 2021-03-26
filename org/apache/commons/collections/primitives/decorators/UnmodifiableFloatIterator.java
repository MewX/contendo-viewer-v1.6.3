/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableFloatIterator
/*    */   extends ProxyFloatIterator
/*    */ {
/*    */   private FloatIterator proxied;
/*    */   
/*    */   UnmodifiableFloatIterator(FloatIterator iterator) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This FloatIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected FloatIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final FloatIterator wrap(FloatIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableFloatIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableFloatIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableFloatIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */