/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
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
/*    */ final class NonSerializableUnmodifiableByteList
/*    */   extends BaseUnmodifiableByteList
/*    */ {
/*    */   private ByteList proxied;
/*    */   
/*    */   NonSerializableUnmodifiableByteList(ByteList list) {
/* 36 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   protected ByteList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/NonSerializableUnmodifiableByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */