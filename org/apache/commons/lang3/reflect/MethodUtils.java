/*      */ package org.apache.commons.lang3.reflect;
/*      */ 
/*      */ import java.lang.annotation.Annotation;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.lang.reflect.Type;
/*      */ import java.lang.reflect.TypeVariable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ import org.apache.commons.lang3.ClassUtils;
/*      */ import org.apache.commons.lang3.Validate;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MethodUtils
/*      */ {
/*      */   public static Object invokeMethod(Object object, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*   95 */     return invokeMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, (Class<?>[])null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeMethod(Object object, boolean forceAccess, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  118 */     return invokeMethod(object, forceAccess, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeMethod(Object object, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  146 */     args = ArrayUtils.nullToEmpty(args);
/*  147 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/*  148 */     return invokeMethod(object, methodName, args, parameterTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  177 */     args = ArrayUtils.nullToEmpty(args);
/*  178 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/*  179 */     return invokeMethod(object, forceAccess, methodName, args, parameterTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeMethod(Object object, boolean forceAccess, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*      */     String messagePrefix;
/*  204 */     parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
/*  205 */     args = ArrayUtils.nullToEmpty(args);
/*      */ 
/*      */     
/*  208 */     Method method = null;
/*      */     
/*  210 */     if (forceAccess) {
/*  211 */       messagePrefix = "No such method: ";
/*  212 */       method = getMatchingMethod(object.getClass(), methodName, parameterTypes);
/*      */       
/*  214 */       if (method != null && !method.isAccessible()) {
/*  215 */         method.setAccessible(true);
/*      */       }
/*      */     } else {
/*  218 */       messagePrefix = "No such accessible method: ";
/*  219 */       method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
/*      */     } 
/*      */ 
/*      */     
/*  223 */     if (method == null) {
/*  224 */       throw new NoSuchMethodException(messagePrefix + methodName + "() on object: " + object
/*      */           
/*  226 */           .getClass().getName());
/*      */     }
/*  228 */     args = toVarArgs(method, args);
/*      */     
/*  230 */     return method.invoke(object, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  256 */     return invokeMethod(object, false, methodName, args, parameterTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeExactMethod(Object object, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  280 */     return invokeExactMethod(object, methodName, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeExactMethod(Object object, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  303 */     args = ArrayUtils.nullToEmpty(args);
/*  304 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/*  305 */     return invokeExactMethod(object, methodName, args, parameterTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  331 */     args = ArrayUtils.nullToEmpty(args);
/*  332 */     parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
/*  333 */     Method method = getAccessibleMethod(object.getClass(), methodName, parameterTypes);
/*      */     
/*  335 */     if (method == null) {
/*  336 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object
/*      */           
/*  338 */           .getClass().getName());
/*      */     }
/*  340 */     return method.invoke(object, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  366 */     args = ArrayUtils.nullToEmpty(args);
/*  367 */     parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
/*  368 */     Method method = getAccessibleMethod(cls, methodName, parameterTypes);
/*  369 */     if (method == null) {
/*  370 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls
/*  371 */           .getName());
/*      */     }
/*  373 */     return method.invoke(null, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeStaticMethod(Class<?> cls, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  403 */     args = ArrayUtils.nullToEmpty(args);
/*  404 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/*  405 */     return invokeStaticMethod(cls, methodName, args, parameterTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  434 */     args = ArrayUtils.nullToEmpty(args);
/*  435 */     parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
/*  436 */     Method method = getMatchingAccessibleMethod(cls, methodName, parameterTypes);
/*      */     
/*  438 */     if (method == null) {
/*  439 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls
/*  440 */           .getName());
/*      */     }
/*  442 */     args = toVarArgs(method, args);
/*  443 */     return method.invoke(null, args);
/*      */   }
/*      */   
/*      */   private static Object[] toVarArgs(Method method, Object[] args) {
/*  447 */     if (method.isVarArgs()) {
/*  448 */       Class<?>[] methodParameterTypes = method.getParameterTypes();
/*  449 */       args = getVarArgs(args, methodParameterTypes);
/*      */     } 
/*  451 */     return args;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Object[] getVarArgs(Object[] args, Class<?>[] methodParameterTypes) {
/*  465 */     if (args.length == methodParameterTypes.length && args[args.length - 1]
/*  466 */       .getClass().equals(methodParameterTypes[methodParameterTypes.length - 1]))
/*      */     {
/*  468 */       return args;
/*      */     }
/*      */ 
/*      */     
/*  472 */     Object[] newArgs = new Object[methodParameterTypes.length];
/*      */ 
/*      */     
/*  475 */     System.arraycopy(args, 0, newArgs, 0, methodParameterTypes.length - 1);
/*      */ 
/*      */     
/*  478 */     Class<?> varArgComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
/*  479 */     int varArgLength = args.length - methodParameterTypes.length + 1;
/*      */     
/*  481 */     Object varArgsArray = Array.newInstance(ClassUtils.primitiveToWrapper(varArgComponentType), varArgLength);
/*      */     
/*  483 */     System.arraycopy(args, methodParameterTypes.length - 1, varArgsArray, 0, varArgLength);
/*      */     
/*  485 */     if (varArgComponentType.isPrimitive())
/*      */     {
/*  487 */       varArgsArray = ArrayUtils.toPrimitive(varArgsArray);
/*      */     }
/*      */ 
/*      */     
/*  491 */     newArgs[methodParameterTypes.length - 1] = varArgsArray;
/*      */ 
/*      */     
/*  494 */     return newArgs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
/*  518 */     args = ArrayUtils.nullToEmpty(args);
/*  519 */     Class<?>[] parameterTypes = ClassUtils.toClass(args);
/*  520 */     return invokeExactStaticMethod(cls, methodName, args, parameterTypes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
/*      */     try {
/*  538 */       return getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
/*      */     }
/*  540 */     catch (NoSuchMethodException e) {
/*  541 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method getAccessibleMethod(Method method) {
/*  554 */     if (!MemberUtils.isAccessible(method)) {
/*  555 */       return null;
/*      */     }
/*      */     
/*  558 */     Class<?> cls = method.getDeclaringClass();
/*  559 */     if (Modifier.isPublic(cls.getModifiers())) {
/*  560 */       return method;
/*      */     }
/*  562 */     String methodName = method.getName();
/*  563 */     Class<?>[] parameterTypes = method.getParameterTypes();
/*      */ 
/*      */     
/*  566 */     method = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
/*      */ 
/*      */ 
/*      */     
/*  570 */     if (method == null) {
/*  571 */       method = getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
/*      */     }
/*      */     
/*  574 */     return method;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?>... parameterTypes) {
/*  589 */     Class<?> parentClass = cls.getSuperclass();
/*  590 */     while (parentClass != null) {
/*  591 */       if (Modifier.isPublic(parentClass.getModifiers())) {
/*      */         try {
/*  593 */           return parentClass.getMethod(methodName, parameterTypes);
/*  594 */         } catch (NoSuchMethodException e) {
/*  595 */           return null;
/*      */         } 
/*      */       }
/*  598 */       parentClass = parentClass.getSuperclass();
/*      */     } 
/*  600 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?>... parameterTypes) {
/*  621 */     for (; cls != null; cls = cls.getSuperclass()) {
/*      */ 
/*      */       
/*  624 */       Class<?>[] interfaces = cls.getInterfaces();
/*  625 */       for (Class<?> anInterface : interfaces) {
/*      */         
/*  627 */         if (Modifier.isPublic(anInterface.getModifiers()))
/*      */           
/*      */           try {
/*      */ 
/*      */             
/*  632 */             return anInterface.getDeclaredMethod(methodName, parameterTypes);
/*      */           }
/*  634 */           catch (NoSuchMethodException noSuchMethodException) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  641 */             Method method = getAccessibleMethodFromInterfaceNest(anInterface, methodName, parameterTypes);
/*      */             
/*  643 */             if (method != null)
/*  644 */               return method; 
/*      */           }  
/*      */       } 
/*      */     } 
/*  648 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
/*      */     try {
/*  676 */       Method method = cls.getMethod(methodName, parameterTypes);
/*  677 */       MemberUtils.setAccessibleWorkaround(method);
/*  678 */       return method;
/*  679 */     } catch (NoSuchMethodException noSuchMethodException) {
/*      */ 
/*      */       
/*  682 */       Method bestMatch = null;
/*  683 */       Method[] methods = cls.getMethods();
/*  684 */       for (Method method : methods) {
/*      */         
/*  686 */         if (method.getName().equals(methodName) && 
/*  687 */           MemberUtils.isMatchingMethod(method, parameterTypes)) {
/*      */           
/*  689 */           Method accessibleMethod = getAccessibleMethod(method);
/*  690 */           if (accessibleMethod != null && (bestMatch == null || MemberUtils.compareMethodFit(accessibleMethod, bestMatch, parameterTypes) < 0))
/*      */           {
/*      */ 
/*      */             
/*  694 */             bestMatch = accessibleMethod;
/*      */           }
/*      */         } 
/*      */       } 
/*  698 */       if (bestMatch != null) {
/*  699 */         MemberUtils.setAccessibleWorkaround(bestMatch);
/*      */       }
/*      */       
/*  702 */       if (bestMatch != null && bestMatch.isVarArgs() && (bestMatch.getParameterTypes()).length > 0 && parameterTypes.length > 0) {
/*  703 */         Class<?>[] methodParameterTypes = bestMatch.getParameterTypes();
/*  704 */         Class<?> methodParameterComponentType = methodParameterTypes[methodParameterTypes.length - 1].getComponentType();
/*  705 */         String methodParameterComponentTypeName = ClassUtils.primitiveToWrapper(methodParameterComponentType).getName();
/*  706 */         String parameterTypeName = parameterTypes[parameterTypes.length - 1].getName();
/*  707 */         String parameterTypeSuperClassName = parameterTypes[parameterTypes.length - 1].getSuperclass().getName();
/*      */         
/*  709 */         if (!methodParameterComponentTypeName.equals(parameterTypeName) && 
/*  710 */           !methodParameterComponentTypeName.equals(parameterTypeSuperClassName)) {
/*  711 */           return null;
/*      */         }
/*      */       } 
/*      */       
/*  715 */       return bestMatch;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method getMatchingMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
/*  730 */     Validate.notNull(cls, "Null class not allowed.", new Object[0]);
/*  731 */     Validate.notEmpty(methodName, "Null or blank methodName not allowed.", new Object[0]);
/*      */ 
/*      */     
/*  734 */     Method[] methodArray = cls.getDeclaredMethods();
/*  735 */     List<Class<?>> superclassList = ClassUtils.getAllSuperclasses(cls);
/*  736 */     for (Class<?> klass : superclassList) {
/*  737 */       methodArray = (Method[])ArrayUtils.addAll((Object[])methodArray, (Object[])klass.getDeclaredMethods());
/*      */     }
/*      */     
/*  740 */     Method inexactMatch = null;
/*  741 */     for (Method method : methodArray) {
/*  742 */       if (methodName.equals(method.getName()) && 
/*  743 */         Objects.deepEquals(parameterTypes, method.getParameterTypes()))
/*  744 */         return method; 
/*  745 */       if (methodName.equals(method.getName()) && 
/*  746 */         ClassUtils.isAssignable(parameterTypes, method.getParameterTypes(), true)) {
/*  747 */         if (inexactMatch == null) {
/*  748 */           inexactMatch = method;
/*  749 */         } else if (distance(parameterTypes, method.getParameterTypes()) < 
/*  750 */           distance(parameterTypes, inexactMatch.getParameterTypes())) {
/*  751 */           inexactMatch = method;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  756 */     return inexactMatch;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int distance(Class<?>[] classArray, Class<?>[] toClassArray) {
/*  767 */     int answer = 0;
/*      */     
/*  769 */     if (!ClassUtils.isAssignable(classArray, toClassArray, true)) {
/*  770 */       return -1;
/*      */     }
/*  772 */     for (int offset = 0; offset < classArray.length; offset++) {
/*      */       
/*  774 */       if (!classArray[offset].equals(toClassArray[offset]))
/*      */       {
/*  776 */         if (ClassUtils.isAssignable(classArray[offset], toClassArray[offset], true) && 
/*  777 */           !ClassUtils.isAssignable(classArray[offset], toClassArray[offset], false)) {
/*  778 */           answer++;
/*      */         } else {
/*  780 */           answer += 2;
/*      */         } 
/*      */       }
/*      */     } 
/*  784 */     return answer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Set<Method> getOverrideHierarchy(Method method, ClassUtils.Interfaces interfacesBehavior) {
/*  796 */     Validate.notNull(method);
/*  797 */     Set<Method> result = new LinkedHashSet<>();
/*  798 */     result.add(method);
/*      */     
/*  800 */     Class<?>[] parameterTypes = method.getParameterTypes();
/*      */     
/*  802 */     Class<?> declaringClass = method.getDeclaringClass();
/*      */     
/*  804 */     Iterator<Class<?>> hierarchy = ClassUtils.hierarchy(declaringClass, interfacesBehavior).iterator();
/*      */     
/*  806 */     hierarchy.next();
/*  807 */     label21: while (hierarchy.hasNext()) {
/*  808 */       Class<?> c = hierarchy.next();
/*  809 */       Method m = getMatchingAccessibleMethod(c, method.getName(), parameterTypes);
/*  810 */       if (m == null) {
/*      */         continue;
/*      */       }
/*  813 */       if (Arrays.equals((Object[])m.getParameterTypes(), (Object[])parameterTypes)) {
/*      */         
/*  815 */         result.add(m);
/*      */         
/*      */         continue;
/*      */       } 
/*  819 */       Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(declaringClass, m.getDeclaringClass());
/*  820 */       for (int i = 0; i < parameterTypes.length; i++) {
/*  821 */         Type childType = TypeUtils.unrollVariables(typeArguments, method.getGenericParameterTypes()[i]);
/*  822 */         Type parentType = TypeUtils.unrollVariables(typeArguments, m.getGenericParameterTypes()[i]);
/*  823 */         if (!TypeUtils.equals(childType, parentType)) {
/*      */           continue label21;
/*      */         }
/*      */       } 
/*  827 */       result.add(m);
/*      */     } 
/*  829 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
/*  844 */     return getMethodsWithAnnotation(cls, annotationCls, false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
/*  859 */     return getMethodsListWithAnnotation(cls, annotationCls, false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
/*  879 */     List<Method> annotatedMethodsList = getMethodsListWithAnnotation(cls, annotationCls, searchSupers, ignoreAccess);
/*      */     
/*  881 */     return annotatedMethodsList.<Method>toArray(new Method[annotatedMethodsList.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls, boolean searchSupers, boolean ignoreAccess) {
/*  903 */     Validate.isTrue((cls != null), "The class must not be null", new Object[0]);
/*  904 */     Validate.isTrue((annotationCls != null), "The annotation class must not be null", new Object[0]);
/*      */     
/*  906 */     List<Class<?>> classes = searchSupers ? getAllSuperclassesAndInterfaces(cls) : new ArrayList<>();
/*  907 */     classes.add(0, cls);
/*  908 */     List<Method> annotatedMethods = new ArrayList<>();
/*  909 */     for (Class<?> acls : classes) {
/*  910 */       Method[] methods = ignoreAccess ? acls.getDeclaredMethods() : acls.getMethods();
/*  911 */       for (Method method : methods) {
/*  912 */         if (method.getAnnotation(annotationCls) != null) {
/*  913 */           annotatedMethods.add(method);
/*      */         }
/*      */       } 
/*      */     } 
/*  917 */     return annotatedMethods;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationCls, boolean searchSupers, boolean ignoreAccess) {
/*  946 */     Validate.isTrue((method != null), "The method must not be null", new Object[0]);
/*  947 */     Validate.isTrue((annotationCls != null), "The annotation class must not be null", new Object[0]);
/*  948 */     if (!ignoreAccess && !MemberUtils.isAccessible(method)) {
/*  949 */       return null;
/*      */     }
/*      */     
/*  952 */     A annotation = method.getAnnotation(annotationCls);
/*      */     
/*  954 */     if (annotation == null && searchSupers) {
/*  955 */       Class<?> mcls = method.getDeclaringClass();
/*  956 */       List<Class<?>> classes = getAllSuperclassesAndInterfaces(mcls);
/*  957 */       for (Class<?> acls : classes) {
/*      */         Method equivalentMethod;
/*      */         
/*      */         try {
/*  961 */           equivalentMethod = ignoreAccess ? acls.getDeclaredMethod(method.getName(), method.getParameterTypes()) : acls.getMethod(method.getName(), method.getParameterTypes());
/*  962 */         } catch (NoSuchMethodException e) {
/*      */           continue;
/*      */         } 
/*      */         
/*  966 */         annotation = equivalentMethod.getAnnotation(annotationCls);
/*  967 */         if (annotation != null) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  973 */     return annotation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static List<Class<?>> getAllSuperclassesAndInterfaces(Class<?> cls) {
/*  987 */     if (cls == null) {
/*  988 */       return null;
/*      */     }
/*      */     
/*  991 */     List<Class<?>> allSuperClassesAndInterfaces = new ArrayList<>();
/*  992 */     List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
/*  993 */     int superClassIndex = 0;
/*  994 */     List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(cls);
/*  995 */     int interfaceIndex = 0;
/*  996 */     while (interfaceIndex < allInterfaces.size() || superClassIndex < allSuperclasses
/*  997 */       .size()) {
/*      */       Class<?> acls;
/*  999 */       if (interfaceIndex >= allInterfaces.size()) {
/* 1000 */         acls = allSuperclasses.get(superClassIndex++);
/* 1001 */       } else if (superClassIndex >= allSuperclasses.size()) {
/* 1002 */         acls = allInterfaces.get(interfaceIndex++);
/* 1003 */       } else if (interfaceIndex < superClassIndex) {
/* 1004 */         acls = allInterfaces.get(interfaceIndex++);
/* 1005 */       } else if (superClassIndex < interfaceIndex) {
/* 1006 */         acls = allSuperclasses.get(superClassIndex++);
/*      */       } else {
/* 1008 */         acls = allInterfaces.get(interfaceIndex++);
/*      */       } 
/* 1010 */       allSuperClassesAndInterfaces.add(acls);
/*      */     } 
/* 1012 */     return allSuperClassesAndInterfaces;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/reflect/MethodUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */