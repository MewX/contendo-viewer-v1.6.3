/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableShortIterator
/*    */   extends ProxyShortIterator
/*    */ {
/*    */   private ShortIterator proxied;
/*    */   
/*    */   UnmodifiableShortIterator(ShortIterator iterator) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This ShortIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected ShortIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final ShortIterator wrap(ShortIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableShortIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableShortIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableShortIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */