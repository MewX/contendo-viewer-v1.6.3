/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortList;
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
/*    */ final class NonSerializableShortListList
/*    */   extends AbstractShortListList
/*    */ {
/*    */   private ShortList _list;
/*    */   
/*    */   public NonSerializableShortListList(ShortList list) {
/* 39 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected ShortList getShortList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableShortListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */