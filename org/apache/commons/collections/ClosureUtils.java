/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.functors.ChainedClosure;
/*     */ import org.apache.commons.collections.functors.EqualPredicate;
/*     */ import org.apache.commons.collections.functors.ExceptionClosure;
/*     */ import org.apache.commons.collections.functors.ForClosure;
/*     */ import org.apache.commons.collections.functors.IfClosure;
/*     */ import org.apache.commons.collections.functors.InvokerTransformer;
/*     */ import org.apache.commons.collections.functors.NOPClosure;
/*     */ import org.apache.commons.collections.functors.SwitchClosure;
/*     */ import org.apache.commons.collections.functors.TransformerClosure;
/*     */ import org.apache.commons.collections.functors.WhileClosure;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClosureUtils
/*     */ {
/*     */   public static Closure exceptionClosure() {
/*  73 */     return ExceptionClosure.INSTANCE;
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
/*     */   public static Closure nopClosure() {
/*  85 */     return NOPClosure.INSTANCE;
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
/*     */   public static Closure asClosure(Transformer transformer) {
/*  99 */     return TransformerClosure.getInstance(transformer);
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
/*     */   public static Closure forClosure(int count, Closure closure) {
/* 114 */     return ForClosure.getInstance(count, closure);
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
/*     */   public static Closure whileClosure(Predicate predicate, Closure closure) {
/* 129 */     return WhileClosure.getInstance(predicate, closure, false);
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
/*     */   public static Closure doWhileClosure(Closure closure, Predicate predicate) {
/* 144 */     return WhileClosure.getInstance(predicate, closure, true);
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
/*     */   public static Closure invokerClosure(String methodName) {
/* 160 */     return asClosure(InvokerTransformer.getInstance(methodName));
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
/*     */   public static Closure invokerClosure(String methodName, Class[] paramTypes, Object[] args) {
/* 179 */     return asClosure(InvokerTransformer.getInstance(methodName, paramTypes, args));
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
/*     */   public static Closure chainedClosure(Closure closure1, Closure closure2) {
/* 194 */     return ChainedClosure.getInstance(closure1, closure2);
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
/*     */   public static Closure chainedClosure(Closure[] closures) {
/* 209 */     return ChainedClosure.getInstance(closures);
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
/*     */   public static Closure chainedClosure(Collection closures) {
/* 226 */     return ChainedClosure.getInstance(closures);
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
/*     */   public static Closure ifClosure(Predicate predicate, Closure trueClosure, Closure falseClosure) {
/* 243 */     return IfClosure.getInstance(predicate, trueClosure, falseClosure);
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
/*     */   public static Closure switchClosure(Predicate[] predicates, Closure[] closures) {
/* 264 */     return SwitchClosure.getInstance(predicates, closures, null);
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
/*     */   public static Closure switchClosure(Predicate[] predicates, Closure[] closures, Closure defaultClosure) {
/* 287 */     return SwitchClosure.getInstance(predicates, closures, defaultClosure);
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
/*     */   public static Closure switchClosure(Map predicatesAndClosures) {
/* 311 */     return SwitchClosure.getInstance(predicatesAndClosures);
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
/*     */   public static Closure switchMapClosure(Map objectsAndClosures) {
/* 332 */     Closure[] trs = null;
/* 333 */     Predicate[] preds = null;
/* 334 */     if (objectsAndClosures == null) {
/* 335 */       throw new IllegalArgumentException("The object and closure map must not be null");
/*     */     }
/* 337 */     Closure def = (Closure)objectsAndClosures.remove(null);
/* 338 */     int size = objectsAndClosures.size();
/* 339 */     trs = new Closure[size];
/* 340 */     preds = new Predicate[size];
/* 341 */     int i = 0;
/* 342 */     for (Iterator it = objectsAndClosures.entrySet().iterator(); it.hasNext(); ) {
/* 343 */       Map.Entry entry = it.next();
/* 344 */       preds[i] = EqualPredicate.getInstance(entry.getKey());
/* 345 */       trs[i] = (Closure)entry.getValue();
/* 346 */       i++;
/*     */     } 
/* 348 */     return switchClosure(preds, trs, def);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/ClosureUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */