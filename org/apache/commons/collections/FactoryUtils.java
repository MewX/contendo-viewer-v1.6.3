/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import org.apache.commons.collections.functors.ConstantFactory;
/*     */ import org.apache.commons.collections.functors.ExceptionFactory;
/*     */ import org.apache.commons.collections.functors.InstantiateFactory;
/*     */ import org.apache.commons.collections.functors.PrototypeFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FactoryUtils
/*     */ {
/*     */   public static Factory exceptionFactory() {
/*  58 */     return ExceptionFactory.INSTANCE;
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
/*     */   public static Factory nullFactory() {
/*  70 */     return ConstantFactory.NULL_INSTANCE;
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
/*     */   public static Factory constantFactory(Object constantToReturn) {
/*  85 */     return ConstantFactory.getInstance(constantToReturn);
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
/*     */   public static Factory prototypeFactory(Object prototype) {
/* 106 */     return PrototypeFactory.getInstance(prototype);
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
/*     */   public static Factory instantiateFactory(Class classToInstantiate) {
/* 120 */     return InstantiateFactory.getInstance(classToInstantiate, null, null);
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
/*     */   public static Factory instantiateFactory(Class classToInstantiate, Class[] paramTypes, Object[] args) {
/* 138 */     return InstantiateFactory.getInstance(classToInstantiate, paramTypes, args);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/FactoryUtils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */