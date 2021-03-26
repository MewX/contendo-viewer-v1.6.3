/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections.Closure;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IfClosure
/*     */   implements Serializable, Closure
/*     */ {
/*     */   static final long serialVersionUID = 3518477308466486130L;
/*     */   private final Predicate iPredicate;
/*     */   private final Closure iTrueClosure;
/*     */   private final Closure iFalseClosure;
/*     */   
/*     */   public static Closure getInstance(Predicate predicate, Closure trueClosure, Closure falseClosure) {
/*  54 */     if (predicate == null) {
/*  55 */       throw new IllegalArgumentException("Predicate must not be null");
/*     */     }
/*  57 */     if (trueClosure == null || falseClosure == null) {
/*  58 */       throw new IllegalArgumentException("Closures must not be null");
/*     */     }
/*  60 */     return new IfClosure(predicate, trueClosure, falseClosure);
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
/*     */   public IfClosure(Predicate predicate, Closure trueClosure, Closure falseClosure) {
/*  73 */     this.iPredicate = predicate;
/*  74 */     this.iTrueClosure = trueClosure;
/*  75 */     this.iFalseClosure = falseClosure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Object input) {
/*  84 */     if (this.iPredicate.evaluate(input) == true) {
/*  85 */       this.iTrueClosure.execute(input);
/*     */     } else {
/*  87 */       this.iFalseClosure.execute(input);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate getPredicate() {
/*  98 */     return this.iPredicate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getTrueClosure() {
/* 108 */     return this.iTrueClosure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getFalseClosure() {
/* 118 */     return this.iFalseClosure;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/IfClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */