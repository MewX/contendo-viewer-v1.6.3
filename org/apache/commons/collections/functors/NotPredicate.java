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
/*    */ public final class NotPredicate
/*    */   implements Serializable, Predicate, PredicateDecorator
/*    */ {
/*    */   static final long serialVersionUID = -2654603322338049674L;
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 46 */     if (predicate == null) {
/* 47 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 49 */     return new NotPredicate(predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NotPredicate(Predicate predicate) {
/* 60 */     this.iPredicate = predicate;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 70 */     return !this.iPredicate.evaluate(object);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 80 */     return new Predicate[] { this.iPredicate };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/NotPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */