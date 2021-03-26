/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ public final class UnmodifiableByteList
/*    */   extends BaseUnmodifiableByteList
/*    */   implements Serializable
/*    */ {
/*    */   private ByteList proxied;
/*    */   
/*    */   UnmodifiableByteList(ByteList list) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   public static final ByteList wrap(ByteList list) {
/*    */     if (null == list)
/*    */       return null; 
/*    */     if (list instanceof UnmodifiableByteList)
/*    */       return list; 
/*    */     if (list instanceof Serializable)
/*    */       return new UnmodifiableByteList(list); 
/*    */     return new NonSerializableUnmodifiableByteList(list);
/*    */   }
/*    */   
/*    */   protected ByteList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */