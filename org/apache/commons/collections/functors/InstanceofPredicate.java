/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
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
/*    */ public final class InstanceofPredicate
/*    */   implements Serializable, Predicate
/*    */ {
/*    */   static final long serialVersionUID = -6682656911025165584L;
/*    */   private final Class iType;
/*    */   
/*    */   public static Predicate getInstance(Class type) {
/* 47 */     if (type == null) {
/* 48 */       throw new IllegalArgumentException("The type to check instanceof must not be null");
/*    */     }
/* 50 */     return new InstanceofPredicate(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InstanceofPredicate(Class type) {
/* 61 */     this.iType = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 71 */     return this.iType.isInstance(object);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Class getType() {
/* 81 */     return this.iType;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/InstanceofPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */