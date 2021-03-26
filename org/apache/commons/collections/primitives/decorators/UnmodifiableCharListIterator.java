/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.CharListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableCharListIterator
/*    */   extends ProxyCharListIterator
/*    */ {
/*    */   private CharListIterator proxied;
/*    */   
/*    */   UnmodifiableCharListIterator(CharListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This CharListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(char value) {
/*    */     throw new UnsupportedOperationException("This CharListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(char value) {
/*    */     throw new UnsupportedOperationException("This CharListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected CharListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final CharListIterator wrap(CharListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableCharListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableCharListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableCharListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */