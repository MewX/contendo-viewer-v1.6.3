/*    */ package org.apache.commons.collections.primitives.decorators;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ public final class UnmodifiableCharList
/*    */   extends BaseUnmodifiableCharList
/*    */   implements Serializable
/*    */ {
/*    */   private CharList proxied;
/*    */   
/*    */   UnmodifiableCharList(CharList list) {
/* 50 */     this.proxied = null;
/*    */     this.proxied = list;
/*    */   }
/*    */   
/*    */   public static final CharList wrap(CharList list) {
/*    */     if (null == list)
/*    */       return null; 
/*    */     if (list instanceof UnmodifiableCharList)
/*    */       return list; 
/*    */     if (list instanceof Serializable)
/*    */       return new UnmodifiableCharList(list); 
/*    */     return new NonSerializableUnmodifiableCharList(list);
/*    */   }
/*    */   
/*    */   protected CharList getProxiedList() {
/*    */     return this.proxied;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/decorators/UnmodifiableCharList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */