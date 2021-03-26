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
/*    */ abstract class ProxyShortIterator
/*    */   implements ShortIterator
/*    */ {
/*    */   public boolean hasNext() {
/* 32 */     return getIterator().hasNext();
/*    */   }
/*    */   
/*    */   public short next() {
/* 36 */     return getIterator().next();
/*    */   }
/*    */   
/*    */   protected abstract ShortIterator getIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyShortIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */