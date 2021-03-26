/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Closure;
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
/*     */ 
/*     */ class FunctorUtils
/*     */ {
/*     */   static Predicate[] copy(Predicate[] predicates) {
/*  49 */     if (predicates == null) {
/*  50 */       return null;
/*     */     }
/*  52 */     return (Predicate[])predicates.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validate(Predicate[] predicates) {
/*  61 */     if (predicates == null) {
/*  62 */       throw new IllegalArgumentException("The predicate array must not be null");
/*     */     }
/*  64 */     for (int i = 0; i < predicates.length; i++) {
/*  65 */       if (predicates[i] == null) {
/*  66 */         throw new IllegalArgumentException("The predicate array must not contain a null predicate, index " + i + " was null");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validateMin2(Predicate[] predicates) {
/*  77 */     if (predicates == null) {
/*  78 */       throw new IllegalArgumentException("The predicate array must not be null");
/*     */     }
/*  80 */     if (predicates.length < 2) {
/*  81 */       throw new IllegalArgumentException("At least 2 predicates must be specified in the predicate array, size was " + predicates.length);
/*     */     }
/*     */     
/*  84 */     for (int i = 0; i < predicates.length; i++) {
/*  85 */       if (predicates[i] == null) {
/*  86 */         throw new IllegalArgumentException("The predicate array must not contain a null predicate, index " + i + " was null");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Predicate[] validate(Collection predicates) {
/*  98 */     if (predicates == null) {
/*  99 */       throw new IllegalArgumentException("The predicate collection must not be null");
/*     */     }
/* 101 */     if (predicates.size() < 2) {
/* 102 */       throw new IllegalArgumentException("At least 2 predicates must be specified in the predicate collection, size was " + predicates.size());
/*     */     }
/*     */ 
/*     */     
/* 106 */     Predicate[] preds = new Predicate[predicates.size()];
/* 107 */     int i = 0;
/* 108 */     for (Iterator it = predicates.iterator(); it.hasNext(); ) {
/* 109 */       preds[i] = it.next();
/* 110 */       if (preds[i] == null) {
/* 111 */         throw new IllegalArgumentException("The predicate collection must not contain a null predicate, index " + i + " was null");
/*     */       }
/* 113 */       i++;
/*     */     } 
/* 115 */     return preds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Closure[] copy(Closure[] closures) {
/* 125 */     if (closures == null) {
/* 126 */       return null;
/*     */     }
/* 128 */     return (Closure[])closures.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validate(Closure[] closures) {
/* 137 */     if (closures == null) {
/* 138 */       throw new IllegalArgumentException("The closure array must not be null");
/*     */     }
/* 140 */     for (int i = 0; i < closures.length; i++) {
/* 141 */       if (closures[i] == null) {
/* 142 */         throw new IllegalArgumentException("The closure array must not contain a null closure, index " + i + " was null");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Transformer[] copy(Transformer[] transformers) {
/* 154 */     if (transformers == null) {
/* 155 */       return null;
/*     */     }
/* 157 */     return (Transformer[])transformers.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void validate(Transformer[] transformers) {
/* 166 */     if (transformers == null) {
/* 167 */       throw new IllegalArgumentException("The transformer array must not be null");
/*     */     }
/* 169 */     for (int i = 0; i < transformers.length; i++) {
/* 170 */       if (transformers[i] == null)
/* 171 */         throw new IllegalArgumentException("The transformer array must not contain a null transformer, index " + i + " was null"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/FunctorUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */