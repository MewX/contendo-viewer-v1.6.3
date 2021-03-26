/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AllPredicate
/*     */   implements Serializable, Predicate, PredicateDecorator
/*     */ {
/*     */   static final long serialVersionUID = -3094696765038308799L;
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   public static Predicate getInstance(Predicate[] predicates) {
/*  49 */     FunctorUtils.validateMin2(predicates);
/*  50 */     predicates = FunctorUtils.copy(predicates);
/*  51 */     return new AllPredicate(predicates);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Predicate getInstance(Collection predicates) {
/*  64 */     Predicate[] preds = FunctorUtils.validate(predicates);
/*  65 */     return new AllPredicate(preds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AllPredicate(Predicate[] predicates) {
/*  76 */     this.iPredicates = predicates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean evaluate(Object object) {
/*  86 */     for (int i = 0; i < this.iPredicates.length; i++) {
/*  87 */       if (!this.iPredicates[i].evaluate(object)) {
/*  88 */         return false;
/*     */       }
/*     */     } 
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 101 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/AllPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */