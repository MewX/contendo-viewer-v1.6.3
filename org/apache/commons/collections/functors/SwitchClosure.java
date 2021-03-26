/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class SwitchClosure
/*     */   implements Serializable, Closure
/*     */ {
/*     */   static final long serialVersionUID = 3518477308466486130L;
/*     */   private final Predicate[] iPredicates;
/*     */   private final Closure[] iClosures;
/*     */   private final Closure iDefault;
/*     */   
/*     */   public static Closure getInstance(Predicate[] predicates, Closure[] closures, Closure defaultClosure) {
/*  57 */     FunctorUtils.validate(predicates);
/*  58 */     FunctorUtils.validate(closures);
/*  59 */     if (predicates.length != closures.length) {
/*  60 */       throw new IllegalArgumentException("The predicate and closure arrays must be the same size");
/*     */     }
/*  62 */     if (predicates.length == 0) {
/*  63 */       return (defaultClosure == null) ? NOPClosure.INSTANCE : defaultClosure;
/*     */     }
/*  65 */     predicates = FunctorUtils.copy(predicates);
/*  66 */     closures = FunctorUtils.copy(closures);
/*  67 */     return new SwitchClosure(predicates, closures, defaultClosure);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Closure getInstance(Map predicatesAndClosures) {
/*  88 */     Closure[] closures = null;
/*  89 */     Predicate[] preds = null;
/*  90 */     if (predicatesAndClosures == null) {
/*  91 */       throw new IllegalArgumentException("The predicate and closure map must not be null");
/*     */     }
/*  93 */     if (predicatesAndClosures.size() == 0) {
/*  94 */       return NOPClosure.INSTANCE;
/*     */     }
/*     */     
/*  97 */     Closure defaultClosure = (Closure)predicatesAndClosures.remove(null);
/*  98 */     int size = predicatesAndClosures.size();
/*  99 */     if (size == 0) {
/* 100 */       return (defaultClosure == null) ? NOPClosure.INSTANCE : defaultClosure;
/*     */     }
/* 102 */     closures = new Closure[size];
/* 103 */     preds = new Predicate[size];
/* 104 */     int i = 0;
/* 105 */     for (Iterator it = predicatesAndClosures.entrySet().iterator(); it.hasNext(); ) {
/* 106 */       Map.Entry entry = it.next();
/* 107 */       preds[i] = (Predicate)entry.getKey();
/* 108 */       closures[i] = (Closure)entry.getValue();
/* 109 */       i++;
/*     */     } 
/* 111 */     return new SwitchClosure(preds, closures, defaultClosure);
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
/*     */   public SwitchClosure(Predicate[] predicates, Closure[] closures, Closure defaultClosure) {
/* 124 */     this.iPredicates = predicates;
/* 125 */     this.iClosures = closures;
/* 126 */     this.iDefault = (defaultClosure == null) ? NOPClosure.INSTANCE : defaultClosure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(Object input) {
/* 135 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 136 */       if (this.iPredicates[i].evaluate(input) == true) {
/* 137 */         this.iClosures[i].execute(input);
/*     */         return;
/*     */       } 
/*     */     } 
/* 141 */     this.iDefault.execute(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 151 */     return this.iPredicates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure[] getClosures() {
/* 161 */     return this.iClosures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getDefaultClosure() {
/* 171 */     return this.iDefault;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/SwitchClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */