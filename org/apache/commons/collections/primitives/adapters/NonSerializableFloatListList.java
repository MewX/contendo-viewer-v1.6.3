/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatList;
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
/*    */ final class NonSerializableFloatListList
/*    */   extends AbstractFloatListList
/*    */ {
/*    */   private FloatList _list;
/*    */   
/*    */   public NonSerializableFloatListList(FloatList list) {
/* 39 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected FloatList getFloatList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableFloatListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */