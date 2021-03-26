/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleList;
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
/*    */ final class NonSerializableUnmodifiableDoubleList
/*    */   extends BaseUnmodifiableDoubleList
/*    */ {
/*    */   private DoubleList proxied;
/*    */   
/*    */   NonSerializableUnmodifiableDoubleList(DoubleList list) {
/* 36 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   protected DoubleList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/NonSerializableUnmodifiableDoubleList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */