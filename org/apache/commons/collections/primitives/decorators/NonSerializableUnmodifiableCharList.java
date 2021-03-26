/*    */ package org.apache.commons.collections.primitives.decorators;
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
/*    */ final class NonSerializableUnmodifiableCharList
/*    */   extends BaseUnmodifiableCharList
/*    */ {
/*    */   private CharList proxied;
/*    */   
/*    */   NonSerializableUnmodifiableCharList(CharList list) {
/* 36 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   protected CharList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/NonSerializableUnmodifiableCharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */