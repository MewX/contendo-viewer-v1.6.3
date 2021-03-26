/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public final class UnmodifiableDoubleList
/*    */   extends BaseUnmodifiableDoubleList
/*    */   implements Serializable
/*    */ {
/*    */   private DoubleList proxied;
/*    */   
/*    */   UnmodifiableDoubleList(DoubleList list) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   public static final DoubleList wrap(DoubleList list) {
/*    */     if (null == list)
/*    */       return null; 
/*    */     if (list instanceof UnmodifiableDoubleList)
/*    */       return list; 
/*    */     if (list instanceof Serializable)
/*    */       return new UnmodifiableDoubleList(list); 
/*    */     return new NonSerializableUnmodifiableDoubleList(list);
/*    */   }
/*    */   
/*    */   protected DoubleList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableDoubleList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */