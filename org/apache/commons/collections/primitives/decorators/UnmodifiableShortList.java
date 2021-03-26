/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public final class UnmodifiableShortList
/*    */   extends BaseUnmodifiableShortList
/*    */   implements Serializable
/*    */ {
/*    */   private ShortList proxied;
/*    */   
/*    */   UnmodifiableShortList(ShortList list) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   public static final ShortList wrap(ShortList list) {
/*    */     if (null == list)
/*    */       return null; 
/*    */     if (list instanceof UnmodifiableShortList)
/*    */       return list; 
/*    */     if (list instanceof Serializable)
/*    */       return new UnmodifiableShortList(list); 
/*    */     return new NonSerializableUnmodifiableShortList(list);
/*    */   }
/*    */   
/*    */   protected ShortList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */