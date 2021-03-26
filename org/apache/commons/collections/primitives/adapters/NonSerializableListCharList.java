/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.List;
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
/*    */ final class NonSerializableListCharList
/*    */   extends AbstractListCharList
/*    */ {
/*    */   private List _list;
/*    */   
/*    */   protected NonSerializableListCharList(List list) {
/* 36 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected List getList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableListCharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */