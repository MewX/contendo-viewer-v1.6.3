/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableShortListIterator
/*    */   extends ProxyShortListIterator
/*    */ {
/*    */   private ShortListIterator proxied;
/*    */   
/*    */   UnmodifiableShortListIterator(ShortListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This ShortListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(short value) {
/*    */     throw new UnsupportedOperationException("This ShortListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(short value) {
/*    */     throw new UnsupportedOperationException("This ShortListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected ShortListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final ShortListIterator wrap(ShortListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableShortListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableShortListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableShortListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */