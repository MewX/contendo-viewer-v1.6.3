/*     */ package org.apache.commons.lang3.reflect;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConstructorUtils
/*     */ {
/*     */   public static <T> T invokeConstructor(Class<T> cls, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/*  82 */     args = ArrayUtils.nullToEmpty(args);
/*  83 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/*  84 */     return invokeConstructor(cls, args, parameterTypes);
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
/*     */   
/*     */   public static <T> T invokeConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 110 */     args = ArrayUtils.nullToEmpty(args);
/* 111 */     parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
/* 112 */     Constructor<T> ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
/* 113 */     if (ctor == null) {
/* 114 */       throw new NoSuchMethodException("No such accessible constructor on object: " + cls
/* 115 */           .getName());
/*     */     }
/* 117 */     if (ctor.isVarArgs()) {
/* 118 */       Class<?>[] methodParameterTypes = ctor.getParameterTypes();
/* 119 */       args = MethodUtils.getVarArgs(args, methodParameterTypes);
/*     */     } 
/* 121 */     return ctor.newInstance(args);
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
/*     */   public static <T> T invokeExactConstructor(Class<T> cls, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 146 */     args = ArrayUtils.nullToEmpty(args);
/* 147 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/* 148 */     return invokeExactConstructor(cls, args, parameterTypes);
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
/*     */   
/*     */   public static <T> T invokeExactConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 174 */     args = ArrayUtils.nullToEmpty(args);
/* 175 */     parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
/* 176 */     Constructor<T> ctor = getAccessibleConstructor(cls, parameterTypes);
/* 177 */     if (ctor == null) {
/* 178 */       throw new NoSuchMethodException("No such accessible constructor on object: " + cls
/* 179 */           .getName());
/*     */     }
/* 181 */     return ctor.newInstance(args);
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
/*     */   public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
/* 201 */     Validate.notNull(cls, "class cannot be null", new Object[0]);
/*     */     try {
/* 203 */       return getAccessibleConstructor(cls.getConstructor(parameterTypes));
/* 204 */     } catch (NoSuchMethodException e) {
/* 205 */       return null;
/*     */     } 
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
/*     */   public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor) {
/* 221 */     Validate.notNull(ctor, "constructor cannot be null", new Object[0]);
/* 222 */     return 
/* 223 */       (MemberUtils.isAccessible(ctor) && isAccessible(ctor.getDeclaringClass())) ? ctor : null;
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
/*     */   public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
/* 246 */     Validate.notNull(cls, "class cannot be null", new Object[0]);
/*     */ 
/*     */     
/*     */     try {
/* 250 */       Constructor<T> ctor = cls.getConstructor(parameterTypes);
/* 251 */       MemberUtils.setAccessibleWorkaround(ctor);
/* 252 */       return ctor;
/* 253 */     } catch (NoSuchMethodException noSuchMethodException) {
/*     */       
/* 255 */       Constructor<T> result = null;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 260 */       Constructor[] arrayOfConstructor = (Constructor[])cls.getConstructors();
/*     */ 
/*     */       
/* 263 */       for (Constructor<?> ctor : arrayOfConstructor) {
/*     */         
/* 265 */         if (MemberUtils.isMatchingConstructor(ctor, parameterTypes)) {
/*     */           
/* 267 */           ctor = getAccessibleConstructor(ctor);
/* 268 */           if (ctor != null) {
/* 269 */             MemberUtils.setAccessibleWorkaround(ctor);
/* 270 */             if (result == null || MemberUtils.compareConstructorFit(ctor, result, parameterTypes) < 0) {
/*     */ 
/*     */ 
/*     */               
/* 274 */               Constructor<T> constructor = (Constructor)ctor;
/* 275 */               result = constructor;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 280 */       return result;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isAccessible(Class<?> type) {
/* 291 */     Class<?> cls = type;
/* 292 */     while (cls != null) {
/* 293 */       if (!Modifier.isPublic(cls.getModifiers())) {
/* 294 */         return false;
/*     */       }
/* 296 */       cls = cls.getEnclosingClass();
/*     */     } 
/* 298 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/reflect/ConstructorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */