/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.BooleanList;
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
/*    */ final class NonSerializableBooleanListList
/*    */   extends AbstractBooleanListList
/*    */ {
/*    */   private BooleanList _list;
/*    */   
/*    */   public NonSerializableBooleanListList(BooleanList list) {
/* 39 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected BooleanList getBooleanList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableBooleanListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */