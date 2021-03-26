/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class SwitchTransformer
/*     */   implements Serializable, Transformer
/*     */ {
/*     */   static final long serialVersionUID = -6404460890903469332L;
/*     */   private final Predicate[] iPredicates;
/*     */   private final Transformer[] iTransformers;
/*     */   private final Transformer iDefault;
/*     */   
/*     */   public static Transformer getInstance(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
/*  57 */     FunctorUtils.validate(predicates);
/*  58 */     FunctorUtils.validate(transformers);
/*  59 */     if (predicates.length != transformers.length) {
/*  60 */       throw new IllegalArgumentException("The predicate and transformer arrays must be the same size");
/*     */     }
/*  62 */     if (predicates.length == 0) {
/*  63 */       return (defaultTransformer == null) ? ConstantTransformer.NULL_INSTANCE : defaultTransformer;
/*     */     }
/*  65 */     predicates = FunctorUtils.copy(predicates);
/*  66 */     transformers = FunctorUtils.copy(transformers);
/*  67 */     return new SwitchTransformer(predicates, transformers, defaultTransformer);
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
/*     */   public static Transformer getInstance(Map predicatesAndTransformers) {
/*  88 */     Transformer[] transformers = null;
/*  89 */     Predicate[] preds = null;
/*  90 */     if (predicatesAndTransformers == null) {
/*  91 */       throw new IllegalArgumentException("The predicate and transformer map must not be null");
/*     */     }
/*  93 */     if (predicatesAndTransformers.size() == 0) {
/*  94 */       return ConstantTransformer.NULL_INSTANCE;
/*     */     }
/*     */     
/*  97 */     Transformer defaultTransformer = (Transformer)predicatesAndTransformers.remove(null);
/*  98 */     int size = predicatesAndTransformers.size();
/*  99 */     if (size == 0) {
/* 100 */       return (defaultTransformer == null) ? ConstantTransformer.NULL_INSTANCE : defaultTransformer;
/*     */     }
/* 102 */     transformers = new Transformer[size];
/* 103 */     preds = new Predicate[size];
/* 104 */     int i = 0;
/* 105 */     for (Iterator it = predicatesAndTransformers.entrySet().iterator(); it.hasNext(); ) {
/* 106 */       Map.Entry entry = it.next();
/* 107 */       preds[i] = (Predicate)entry.getKey();
/* 108 */       transformers[i] = (Transformer)entry.getValue();
/* 109 */       i++;
/*     */     } 
/* 111 */     return new SwitchTransformer(preds, transformers, defaultTransformer);
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
/*     */   public SwitchTransformer(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
/* 124 */     this.iPredicates = predicates;
/* 125 */     this.iTransformers = transformers;
/* 126 */     this.iDefault = (defaultTransformer == null) ? ConstantTransformer.NULL_INSTANCE : defaultTransformer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object transform(Object input) {
/* 137 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 138 */       if (this.iPredicates[i].evaluate(input) == true) {
/* 139 */         return this.iTransformers[i].transform(input);
/*     */       }
/*     */     } 
/* 142 */     return this.iDefault.transform(input);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 152 */     return this.iPredicates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer[] getTransformers() {
/* 162 */     return this.iTransformers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer getDefaultTransformer() {
/* 172 */     return this.iDefault;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/SwitchTransformer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */