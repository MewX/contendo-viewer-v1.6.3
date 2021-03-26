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
/*    */ public final class ByteListList
/*    */   extends AbstractByteListList
/*    */   implements Serializable
/*    */ {
/*    */   private ByteList _list;
/*    */   
/*    */   public static List wrap(ByteList list) {
/* 50 */     if (null == list)
/* 51 */       return null; 
/* 52 */     if (list instanceof Serializable) {
/* 53 */       return new ByteListList(list);
/*    */     }
/* 55 */     return new NonSerializableByteListList(list);
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
/*    */   public ByteListList(ByteList list) {
/* 72 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected ByteList getByteList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ByteListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */