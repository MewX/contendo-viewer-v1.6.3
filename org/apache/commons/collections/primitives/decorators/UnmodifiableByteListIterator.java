/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ByteListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableByteListIterator
/*    */   extends ProxyByteListIterator
/*    */ {
/*    */   private ByteListIterator proxied;
/*    */   
/*    */   UnmodifiableByteListIterator(ByteListIterator iterator) {
/* 59 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This ByteListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void add(byte value) {
/*    */     throw new UnsupportedOperationException("This ByteListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   public void set(byte value) {
/*    */     throw new UnsupportedOperationException("This ByteListIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected ByteListIterator getListIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final ByteListIterator wrap(ByteListIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableByteListIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableByteListIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableByteListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */