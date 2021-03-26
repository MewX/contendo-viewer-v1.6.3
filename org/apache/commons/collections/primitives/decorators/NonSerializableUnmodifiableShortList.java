/*    */ package org.apache.commons.collections.primitives.decorators;
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
/*    */ final class NonSerializableUnmodifiableShortList
/*    */   extends BaseUnmodifiableShortList
/*    */ {
/*    */   private ShortList proxied;
/*    */   
/*    */   NonSerializableUnmodifiableShortList(ShortList list) {
/* 36 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   protected ShortList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/NonSerializableUnmodifiableShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */