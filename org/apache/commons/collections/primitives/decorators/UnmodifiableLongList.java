/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.primitives.LongList;
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
/*    */ public final class UnmodifiableLongList
/*    */   extends BaseUnmodifiableLongList
/*    */   implements Serializable
/*    */ {
/*    */   private LongList proxied;
/*    */   
/*    */   UnmodifiableLongList(LongList list) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   public static final LongList wrap(LongList list) {
/*    */     if (null == list)
/*    */       return null; 
/*    */     if (list instanceof UnmodifiableLongList)
/*    */       return list; 
/*    */     if (list instanceof Serializable)
/*    */       return new UnmodifiableLongList(list); 
/*    */     return new NonSerializableUnmodifiableLongList(list);
/*    */   }
/*    */   
/*    */   protected LongList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableLongList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */