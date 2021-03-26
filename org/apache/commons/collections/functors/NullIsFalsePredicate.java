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
/*    */ public final class NullIsFalsePredicate
/*    */   implements Serializable, Predicate, PredicateDecorator
/*    */ {
/*    */   static final long serialVersionUID = -2997501534564735525L;
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate) {
/* 46 */     if (predicate == null) {
/* 47 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 49 */     return new NullIsFalsePredicate(predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NullIsFalsePredicate(Predicate predicate) {
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
/* 72 */       return false;
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/NullIsFalsePredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */