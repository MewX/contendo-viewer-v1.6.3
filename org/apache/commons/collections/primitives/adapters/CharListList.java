/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CharListList
/*    */   extends AbstractCharListList
/*    */   implements Serializable
/*    */ {
/*    */   private CharList _list;
/*    */   
/*    */   public static List wrap(CharList list) {
/* 50 */     if (null == list)
/* 51 */       return null; 
/* 52 */     if (list instanceof Serializable) {
/* 53 */       return new CharListList(list);
/*    */     }
/* 55 */     return new NonSerializableCharListList(list);
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
/*    */   public CharListList(CharList list) {
/* 72 */     this._list = null;
/*    */     this._list = list;
/*    */   }
/*    */   
/*    */   protected CharList getCharList() {
/*    */     return this._list;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/CharListList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */