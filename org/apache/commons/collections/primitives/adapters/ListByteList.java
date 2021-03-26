/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.collections.primitives.ByteList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListByteList
/*    */   extends AbstractListByteList
/*    */   implements Serializable
/*    */ {
/*    */   private List _list;
/*    */   
/*    */   public static ByteList wrap(List list) {
/* 50 */     if (null == list)
/* 51 */       return null; 
/* 52 */     if (list instanceof Serializable) {
/* 53 */       return new ListByteList(list);
/*    */     }
/* 55 */     return new NonSerializableListByteList(list);
/*    */   }
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
/*    */   public ListByteList(List list) {
/* 72 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected List getList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ListByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */