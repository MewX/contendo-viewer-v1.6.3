/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.LongIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class ProxyLongIterator
/*    */   implements LongIterator
/*    */ {
/*    */   public boolean hasNext() {
/* 32 */     return getIterator().hasNext();
/*    */   }
/*    */   
/*    */   public long next() {
/* 36 */     return getIterator().next();
/*    */   }
/*    */   
/*    */   protected abstract LongIterator getIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyLongIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */