/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.IntList;
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
/*    */ final class NonSerializableUnmodifiableIntList
/*    */   extends BaseUnmodifiableIntList
/*    */ {
/*    */   private IntList proxied;
/*    */   
/*    */   NonSerializableUnmodifiableIntList(IntList list) {
/* 36 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   protected IntList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/NonSerializableUnmodifiableIntList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */