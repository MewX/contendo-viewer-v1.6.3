/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.CharIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableCharIterator
/*    */   extends ProxyCharIterator
/*    */ {
/*    */   private CharIterator proxied;
/*    */   
/*    */   UnmodifiableCharIterator(CharIterator iterator) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This CharIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected CharIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final CharIterator wrap(CharIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableCharIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableCharIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableCharIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */