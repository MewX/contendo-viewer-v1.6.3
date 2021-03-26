/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.CharList;
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
/*    */ final class NonSerializableCharListList
/*    */   extends AbstractCharListList
/*    */ {
/*    */   private CharList _list;
/*    */   
/*    */   public NonSerializableCharListList(CharList list) {
/* 39 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected CharList getCharList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableCharListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */