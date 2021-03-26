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
/*     */ public final class OnePredicate
/*     */   implements Serializable, Predicate, PredicateDecorator
/*     */ {
/*     */   static final long serialVersionUID = -8125389089924745785L;
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   public static Predicate getInstance(Predicate[] predicates) {
/*  49 */     FunctorUtils.validateMin2(predicates);
/*  50 */     predicates = FunctorUtils.copy(predicates);
/*  51 */     return new OnePredicate(predicates);
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
/*  65 */     return new OnePredicate(preds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OnePredicate(Predicate[] predicates) {
/*  76 */     this.iPredicates = predicates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean evaluate(Object object) {
/*  87 */     boolean match = false;
/*  88 */     for (int i = 0; i < this.iPredicates.length; i++) {
/*  89 */       if (this.iPredicates[i].evaluate(object)) {
/*  90 */         if (match) {
/*  91 */           return false;
/*     */         }
/*  93 */         match = true;
/*     */       } 
/*     */     } 
/*  96 */     return match;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 106 */     return this.iPredicates;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/OnePredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */