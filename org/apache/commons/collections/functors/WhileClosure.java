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
/*     */ public class WhileClosure
/*     */   implements Serializable, Closure
/*     */ {
/*     */   static final long serialVersionUID = -3110538116913760108L;
/*     */   private final Predicate iPredicate;
/*     */   private final Closure iClosure;
/*     */   private final boolean iDoLoop;
/*     */   
/*     */   public static Closure getInstance(Predicate predicate, Closure closure, boolean doLoop) {
/*  54 */     if (predicate == null) {
/*  55 */       throw new IllegalArgumentException("Predicate must not be null");
/*     */     }
/*  57 */     if (closure == null) {
/*  58 */       throw new IllegalArgumentException("Closure must not be null");
/*     */     }
/*  60 */     return new WhileClosure(predicate, closure, doLoop);
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
/*     */   public WhileClosure(Predicate predicate, Closure closure, boolean doLoop) {
/*  73 */     this.iPredicate = predicate;
/*  74 */     this.iClosure = closure;
/*  75 */     this.iDoLoop = doLoop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Object input) {
/*  84 */     if (this.iDoLoop) {
/*  85 */       this.iClosure.execute(input);
/*     */     }
/*  87 */     while (this.iPredicate.evaluate(input)) {
/*  88 */       this.iClosure.execute(input);
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
/*  99 */     return this.iPredicate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getClosure() {
/* 109 */     return this.iClosure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoLoop() {
/* 119 */     return this.iDoLoop;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/WhileClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */