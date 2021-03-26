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
/*    */ 
/*    */ public final class AndPredicate
/*    */   implements Serializable, Predicate, PredicateDecorator
/*    */ {
/*    */   static final long serialVersionUID = 4189014213763186912L;
/*    */   private final Predicate iPredicate1;
/*    */   private final Predicate iPredicate2;
/*    */   
/*    */   public static Predicate getInstance(Predicate predicate1, Predicate predicate2) {
/* 49 */     if (predicate1 == null || predicate2 == null) {
/* 50 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 52 */     return new AndPredicate(predicate1, predicate2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AndPredicate(Predicate predicate1, Predicate predicate2) {
/* 64 */     this.iPredicate1 = predicate1;
/* 65 */     this.iPredicate2 = predicate2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 75 */     return (this.iPredicate1.evaluate(object) && this.iPredicate2.evaluate(object));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate[] getPredicates() {
/* 85 */     return new Predicate[] { this.iPredicate1, this.iPredicate2 };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/AndPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */