/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class ProxyIntIterator
/*    */   implements IntIterator
/*    */ {
/*    */   public boolean hasNext() {
/* 32 */     return getIterator().hasNext();
/*    */   }
/*    */   
/*    */   public int next() {
/* 36 */     return getIterator().next();
/*    */   }
/*    */   
/*    */   protected abstract IntIterator getIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyIntIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */