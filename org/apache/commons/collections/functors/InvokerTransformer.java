/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InvokerTransformer
/*     */   implements Serializable, Transformer
/*     */ {
/*     */   static final long serialVersionUID = -8653385846894047688L;
/*     */   private final String iMethodName;
/*     */   private final Class[] iParamTypes;
/*     */   private final Object[] iArgs;
/*     */   
/*     */   public static Transformer getInstance(String methodName) {
/*  53 */     if (methodName == null) {
/*  54 */       throw new IllegalArgumentException("The method to invoke must not be null");
/*     */     }
/*  56 */     return new InvokerTransformer(methodName);
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
/*     */   public static Transformer getInstance(String methodName, Class[] paramTypes, Object[] args) {
/*  68 */     if (methodName == null) {
/*  69 */       throw new IllegalArgumentException("The method to invoke must not be null");
/*     */     }
/*  71 */     if ((paramTypes == null && args != null) || (paramTypes != null && args == null) || (paramTypes != null && args != null && paramTypes.length != args.length))
/*     */     {
/*     */       
/*  74 */       throw new IllegalArgumentException("The parameter types must match the arguments");
/*     */     }
/*  76 */     if (paramTypes == null || paramTypes.length == 0) {
/*  77 */       return new InvokerTransformer(methodName);
/*     */     }
/*  79 */     paramTypes = (Class[])paramTypes.clone();
/*  80 */     args = (Object[])args.clone();
/*  81 */     return new InvokerTransformer(methodName, paramTypes, args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private InvokerTransformer(String methodName) {
/*  92 */     this.iMethodName = methodName;
/*  93 */     this.iParamTypes = null;
/*  94 */     this.iArgs = null;
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
/*     */   public InvokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
/* 107 */     this.iMethodName = methodName;
/* 108 */     this.iParamTypes = paramTypes;
/* 109 */     this.iArgs = args;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object transform(Object input) {
/* 119 */     if (input == null) {
/* 120 */       return null;
/*     */     }
/*     */     try {
/* 123 */       Class cls = input.getClass();
/* 124 */       Method method = cls.getMethod(this.iMethodName, this.iParamTypes);
/* 125 */       return method.invoke(input, this.iArgs);
/*     */     }
/* 127 */     catch (NoSuchMethodException ex) {
/* 128 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' does not exist");
/* 129 */     } catch (IllegalAccessException ex) {
/* 130 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' cannot be accessed");
/* 131 */     } catch (InvocationTargetException ex) {
/* 132 */       throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + input.getClass() + "' threw an exception", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/InvokerTransformer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */