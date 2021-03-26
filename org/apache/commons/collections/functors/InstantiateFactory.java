/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.Factory;
/*     */ import org.apache.commons.collections.FunctorException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InstantiateFactory
/*     */   implements Serializable, Factory
/*     */ {
/*     */   static final long serialVersionUID = -7732226881069447957L;
/*     */   private final Class iClassToInstantiate;
/*     */   private final Class[] iParamTypes;
/*     */   private final Object[] iArgs;
/*  45 */   private transient Constructor iConstructor = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Factory getInstance(Class classToInstantiate, Class[] paramTypes, Object[] args) {
/*  56 */     if (classToInstantiate == null) {
/*  57 */       throw new IllegalArgumentException("Class to instantiate must not be null");
/*     */     }
/*  59 */     if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))
/*     */     {
/*     */       
/*  62 */       throw new IllegalArgumentException("Parameter types must match the arguments");
/*     */     }
/*     */     
/*  65 */     if (paramTypes == null || paramTypes.length == 0) {
/*  66 */       return new InstantiateFactory(classToInstantiate);
/*     */     }
/*  68 */     paramTypes = (Class[])paramTypes.clone();
/*  69 */     args = (Object[])args.clone();
/*  70 */     return new InstantiateFactory(classToInstantiate, paramTypes, args);
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
/*     */   public InstantiateFactory(Class classToInstantiate) {
/*  82 */     this.iClassToInstantiate = classToInstantiate;
/*  83 */     this.iParamTypes = null;
/*  84 */     this.iArgs = null;
/*  85 */     findConstructor();
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
/*     */   public InstantiateFactory(Class classToInstantiate, Class[] paramTypes, Object[] args) {
/*  98 */     this.iClassToInstantiate = classToInstantiate;
/*  99 */     this.iParamTypes = paramTypes;
/* 100 */     this.iArgs = args;
/* 101 */     findConstructor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void findConstructor() {
/*     */     try {
/* 109 */       this.iConstructor = this.iClassToInstantiate.getConstructor(this.iParamTypes);
/*     */     }
/* 111 */     catch (NoSuchMethodException ex) {
/* 112 */       throw new IllegalArgumentException("InstantiateFactory: The constructor must exist and be public ");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object create() {
/* 123 */     if (this.iConstructor == null) {
/* 124 */       findConstructor();
/*     */     }
/*     */     
/*     */     try {
/* 128 */       return this.iConstructor.newInstance(this.iArgs);
/*     */     }
/* 130 */     catch (InstantiationException ex) {
/* 131 */       throw new FunctorException("InstantiateFactory: InstantiationException", ex);
/* 132 */     } catch (IllegalAccessException ex) {
/* 133 */       throw new FunctorException("InstantiateFactory: Constructor must be public", ex);
/* 134 */     } catch (InvocationTargetException ex) {
/* 135 */       throw new FunctorException("InstantiateFactory: Constructor threw an exception", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/InstantiateFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */