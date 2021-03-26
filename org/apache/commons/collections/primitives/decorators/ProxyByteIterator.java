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
/*    */ abstract class ProxyByteIterator
/*    */   implements ByteIterator
/*    */ {
/*    */   public boolean hasNext() {
/* 32 */     return getIterator().hasNext();
/*    */   }
/*    */   
/*    */   public byte next() {
/* 36 */     return getIterator().next();
/*    */   }
/*    */   
/*    */   protected abstract ByteIterator getIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyByteIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */