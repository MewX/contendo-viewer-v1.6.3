/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class ProxyFloatIterator
/*    */   implements FloatIterator
/*    */ {
/*    */   public boolean hasNext() {
/* 32 */     return getIterator().hasNext();
/*    */   }
/*    */   
/*    */   public float next() {
/* 36 */     return getIterator().next();
/*    */   }
/*    */   
/*    */   protected abstract FloatIterator getIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/ProxyFloatIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */