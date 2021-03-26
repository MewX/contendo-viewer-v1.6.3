/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.functors.ChainedTransformer;
/*     */ import org.apache.commons.collections.functors.CloneTransformer;
/*     */ import org.apache.commons.collections.functors.ClosureTransformer;
/*     */ import org.apache.commons.collections.functors.ConstantTransformer;
/*     */ import org.apache.commons.collections.functors.EqualPredicate;
/*     */ import org.apache.commons.collections.functors.ExceptionTransformer;
/*     */ import org.apache.commons.collections.functors.FactoryTransformer;
/*     */ import org.apache.commons.collections.functors.InstantiateTransformer;
/*     */ import org.apache.commons.collections.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections.functors.MapTransformer;
/*     */ import org.apache.commons.collections.functors.NOPTransformer;
/*     */ import org.apache.commons.collections.functors.PredicateTransformer;
/*     */ import org.apache.commons.collections.functors.StringValueTransformer;
/*     */ import org.apache.commons.collections.functors.SwitchTransformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformerUtils
/*     */ {
/*     */   public static Transformer exceptionTransformer() {
/*  83 */     return ExceptionTransformer.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Transformer nullTransformer() {
/*  94 */     return ConstantTransformer.NULL_INSTANCE;
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
/*     */   public static Transformer nopTransformer() {
/* 107 */     return NOPTransformer.INSTANCE;
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
/*     */   public static Transformer cloneTransformer() {
/* 125 */     return CloneTransformer.INSTANCE;
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
/*     */   public static Transformer constantTransformer(Object constantToReturn) {
/* 138 */     return ConstantTransformer.getInstance(constantToReturn);
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
/*     */   public static Transformer asTransformer(Closure closure) {
/* 152 */     return ClosureTransformer.getInstance(closure);
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
/*     */   public static Transformer asTransformer(Predicate predicate) {
/* 166 */     return PredicateTransformer.getInstance(predicate);
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
/*     */   public static Transformer asTransformer(Factory factory) {
/* 180 */     return FactoryTransformer.getInstance(factory);
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
/*     */   public static Transformer chainedTransformer(Transformer transformer1, Transformer transformer2) {
/* 195 */     return ChainedTransformer.getInstance(transformer1, transformer2);
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
/*     */   public static Transformer chainedTransformer(Transformer[] transformers) {
/* 210 */     return ChainedTransformer.getInstance(transformers);
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
/*     */   public static Transformer chainedTransformer(Collection transformers) {
/* 226 */     return ChainedTransformer.getInstance(transformers);
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
/*     */   public static Transformer switchTransformer(Predicate predicate, Transformer trueTransformer, Transformer falseTransformer) {
/* 243 */     return SwitchTransformer.getInstance(new Predicate[] { predicate }, new Transformer[] { trueTransformer }, falseTransformer);
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
/*     */   public static Transformer switchTransformer(Predicate[] predicates, Transformer[] transformers) {
/* 263 */     return SwitchTransformer.getInstance(predicates, transformers, null);
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
/*     */   
/*     */   public static Transformer switchTransformer(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
/* 285 */     return SwitchTransformer.getInstance(predicates, transformers, defaultTransformer);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Transformer switchTransformer(Map predicatesAndTransformers) {
/* 310 */     return SwitchTransformer.getInstance(predicatesAndTransformers);
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
/*     */   public static Transformer switchMapTransformer(Map objectsAndTransformers) {
/* 331 */     Transformer[] trs = null;
/* 332 */     Predicate[] preds = null;
/* 333 */     if (objectsAndTransformers == null) {
/* 334 */       throw new IllegalArgumentException("The object and transformer map must not be null");
/*     */     }
/* 336 */     Transformer def = (Transformer)objectsAndTransformers.remove(null);
/* 337 */     int size = objectsAndTransformers.size();
/* 338 */     trs = new Transformer[size];
/* 339 */     preds = new Predicate[size];
/* 340 */     int i = 0;
/* 341 */     for (Iterator it = objectsAndTransformers.entrySet().iterator(); it.hasNext(); ) {
/* 342 */       Map.Entry entry = it.next();
/* 343 */       preds[i] = EqualPredicate.getInstance(entry.getKey());
/* 344 */       trs[i] = (Transformer)entry.getValue();
/* 345 */       i++;
/*     */     } 
/* 347 */     return switchTransformer(preds, trs, def);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Transformer instantiateTransformer() {
/* 358 */     return InstantiateTransformer.NO_ARG_INSTANCE;
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
/*     */   public static Transformer instantiateTransformer(Class[] paramTypes, Object[] args) {
/* 374 */     return InstantiateTransformer.getInstance(paramTypes, args);
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
/*     */   public static Transformer mapTransformer(Map map) {
/* 388 */     return MapTransformer.getInstance(map);
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
/*     */   public static Transformer invokerTransformer(String methodName) {
/* 407 */     return InvokerTransformer.getInstance(methodName, null, null);
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
/*     */   public static Transformer invokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
/* 425 */     return InvokerTransformer.getInstance(methodName, paramTypes, args);
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
/*     */   public static Transformer stringValueTransformer() {
/* 438 */     return StringValueTransformer.INSTANCE;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/TransformerUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */