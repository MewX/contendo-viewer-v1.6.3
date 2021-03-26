/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NonSerializableDoubleListList
/*    */   extends AbstractDoubleListList
/*    */ {
/*    */   private DoubleList _list;
/*    */   
/*    */   public NonSerializableDoubleListList(DoubleList list) {
/* 39 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected DoubleList getDoubleList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableDoubleListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */