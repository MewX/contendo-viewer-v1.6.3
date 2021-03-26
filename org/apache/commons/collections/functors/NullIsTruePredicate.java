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
/*    */ public final class NullIsTruePredicate
/*    */   implements Serializable, Predicate, PredicateDecorator
/*    */ {
/*    */   static final long serialVersionUID = -7625133768987126273L;
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 46 */     if (predicate == null) {
/* 47 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 49 */     return new NullIsTruePredicate(predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NullIsTruePredicate(Predicate predicate) {
/* 60 */     this.iPredicate = predicate;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 71 */     if (object == null) {
/* 72 */       return true;
/*    */     }
/* 74 */     return this.iPredicate.evaluate(object);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 84 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/NullIsTruePredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */