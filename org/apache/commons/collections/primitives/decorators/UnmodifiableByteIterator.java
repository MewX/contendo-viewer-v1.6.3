/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ByteIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableByteIterator
/*    */   extends ProxyByteIterator
/*    */ {
/*    */   private ByteIterator proxied;
/*    */   
/*    */   UnmodifiableByteIterator(ByteIterator iterator) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = iterator;
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     throw new UnsupportedOperationException("This ByteIterator is not modifiable.");
/*    */   }
/*    */   
/*    */   protected ByteIterator getIterator() {
/*    */     return this.proxied;
/*    */   }
/*    */   
/*    */   public static final ByteIterator wrap(ByteIterator iterator) {
/*    */     if (null == iterator)
/*    */       return null; 
/*    */     if (iterator instanceof UnmodifiableByteIterator)
/*    */       return iterator; 
/*    */     return new UnmodifiableByteIterator(iterator);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableByteIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */