/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.Transformer;
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
/*     */ 
/*     */ public final class TransformedPredicate
/*     */   implements Serializable, Predicate, PredicateDecorator
/*     */ {
/*     */   static final long serialVersionUID = -5596090919668315834L;
/*     */   private final Transformer iTransformer;
/*     */   private final Predicate iPredicate;
/*     */   
/*     */   public static Predicate getInstance(Transformer transformer, Predicate predicate) {
/*  51 */     if (transformer == null) {
/*  52 */       throw new IllegalArgumentException("The transformer to call must not be null");
/*     */     }
/*  54 */     if (predicate == null) {
/*  55 */       throw new IllegalArgumentException("The predicate to call must not be null");
/*     */     }
/*  57 */     return new TransformedPredicate(transformer, predicate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformedPredicate(Transformer transformer, Predicate predicate) {
/*  68 */     this.iTransformer = transformer;
/*  69 */     this.iPredicate = predicate;
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
/*  80 */     Object result = this.iTransformer.transform(object);
/*  81 */     return this.iPredicate.evaluate(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate[] getPredicates() {
/*  91 */     return new Predicate[] { this.iPredicate };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer getTransformer() {
/* 100 */     return this.iTransformer;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/TransformedPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */