/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.commons.collections.FunctorException;
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
/*     */ public class InstantiateTransformer
/*     */   implements Serializable, Transformer
/*     */ {
/*     */   static final long serialVersionUID = 3786388740793356347L;
/*  39 */   public static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Class[] iParamTypes;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Object[] iArgs;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Transformer getInstance(Class[] paramTypes, Object[] args) {
/*  54 */     if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))
/*     */     {
/*     */       
/*  57 */       throw new IllegalArgumentException("Parameter types must match the arguments");
/*     */     }
/*     */     
/*  60 */     if (paramTypes == null || paramTypes.length == 0) {
/*  61 */       return NO_ARG_INSTANCE;
/*     */     }
/*  63 */     paramTypes = (Class[])paramTypes.clone();
/*  64 */     args = (Object[])args.clone();
/*     */     
/*  66 */     return new InstantiateTransformer(paramTypes, args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private InstantiateTransformer() {
/*  74 */     this.iParamTypes = null;
/*  75 */     this.iArgs = null;
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
/*     */   public InstantiateTransformer(Class[] paramTypes, Object[] args) {
/*  87 */     this.iParamTypes = paramTypes;
/*  88 */     this.iArgs = args;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object transform(Object input) {
/*     */     try {
/*  99 */       if (!(input instanceof Class)) {
/* 100 */         throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a " + ((input == null) ? "null object" : input.getClass().getName()));
/*     */       }
/*     */ 
/*     */       
/* 104 */       Constructor con = ((Class)input).getConstructor(this.iParamTypes);
/* 105 */       return con.newInstance(this.iArgs);
/*     */     }
/* 107 */     catch (NoSuchMethodException ex) {
/* 108 */       throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
/* 109 */     } catch (InstantiationException ex) {
/* 110 */       throw new FunctorException("InstantiateTransformer: InstantiationException", ex);
/* 111 */     } catch (IllegalAccessException ex) {
/* 112 */       throw new FunctorException("InstantiateTransformer: Constructor must be public", ex);
/* 113 */     } catch (InvocationTargetException ex) {
/* 114 */       throw new FunctorException("InstantiateTransformer: Constructor threw an exception", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/InstantiateTransformer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */