/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang3.mutable.MutableObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ClassUtils
/*      */ {
/*      */   public static final char PACKAGE_SEPARATOR_CHAR = '.';
/*      */   
/*      */   public enum Interfaces
/*      */   {
/*   53 */     INCLUDE, EXCLUDE;
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
/*   64 */   public static final String PACKAGE_SEPARATOR = String.valueOf('.');
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   74 */   public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   79 */   private static final Map<String, Class<?>> namePrimitiveMap = new HashMap<>();
/*      */   static {
/*   81 */     namePrimitiveMap.put("boolean", boolean.class);
/*   82 */     namePrimitiveMap.put("byte", byte.class);
/*   83 */     namePrimitiveMap.put("char", char.class);
/*   84 */     namePrimitiveMap.put("short", short.class);
/*   85 */     namePrimitiveMap.put("int", int.class);
/*   86 */     namePrimitiveMap.put("long", long.class);
/*   87 */     namePrimitiveMap.put("double", double.class);
/*   88 */     namePrimitiveMap.put("float", float.class);
/*   89 */     namePrimitiveMap.put("void", void.class);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<>();
/*      */   static {
/*   97 */     primitiveWrapperMap.put(boolean.class, Boolean.class);
/*   98 */     primitiveWrapperMap.put(byte.class, Byte.class);
/*   99 */     primitiveWrapperMap.put(char.class, Character.class);
/*  100 */     primitiveWrapperMap.put(short.class, Short.class);
/*  101 */     primitiveWrapperMap.put(int.class, Integer.class);
/*  102 */     primitiveWrapperMap.put(long.class, Long.class);
/*  103 */     primitiveWrapperMap.put(double.class, Double.class);
/*  104 */     primitiveWrapperMap.put(float.class, Float.class);
/*  105 */     primitiveWrapperMap.put(void.class, void.class);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  111 */   private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap = new HashMap<>();
/*      */   static {
/*  113 */     for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperMap.entrySet()) {
/*  114 */       Class<?> primitiveClass = entry.getKey();
/*  115 */       Class<?> wrapperClass = entry.getValue();
/*  116 */       if (!primitiveClass.equals(wrapperClass)) {
/*  117 */         wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  136 */     Map<String, String> m = new HashMap<>();
/*  137 */     m.put("int", "I");
/*  138 */     m.put("boolean", "Z");
/*  139 */     m.put("float", "F");
/*  140 */     m.put("long", "J");
/*  141 */     m.put("short", "S");
/*  142 */     m.put("byte", "B");
/*  143 */     m.put("double", "D");
/*  144 */     m.put("char", "C");
/*  145 */     Map<String, String> r = new HashMap<>();
/*  146 */     for (Map.Entry<String, String> e : m.entrySet()) {
/*  147 */       r.put(e.getValue(), e.getKey());
/*      */     }
/*  149 */     abbreviationMap = Collections.unmodifiableMap(m);
/*  150 */     reverseAbbreviationMap = Collections.unmodifiableMap(r);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final Map<String, String> abbreviationMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final Map<String, String> reverseAbbreviationMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getShortClassName(Object object, String valueIfNull) {
/*  175 */     if (object == null) {
/*  176 */       return valueIfNull;
/*      */     }
/*  178 */     return getShortClassName(object.getClass());
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
/*      */   public static String getShortClassName(Class<?> cls) {
/*  192 */     if (cls == null) {
/*  193 */       return "";
/*      */     }
/*  195 */     return getShortClassName(cls.getName());
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
/*      */   public static String getShortClassName(String className) {
/*  211 */     if (StringUtils.isEmpty(className)) {
/*  212 */       return "";
/*      */     }
/*      */     
/*  215 */     StringBuilder arrayPrefix = new StringBuilder();
/*      */ 
/*      */     
/*  218 */     if (className.startsWith("[")) {
/*  219 */       while (className.charAt(0) == '[') {
/*  220 */         className = className.substring(1);
/*  221 */         arrayPrefix.append("[]");
/*      */       } 
/*      */       
/*  224 */       if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
/*  225 */         className = className.substring(1, className.length() - 1);
/*      */       }
/*      */       
/*  228 */       if (reverseAbbreviationMap.containsKey(className)) {
/*  229 */         className = reverseAbbreviationMap.get(className);
/*      */       }
/*      */     } 
/*      */     
/*  233 */     int lastDotIdx = className.lastIndexOf('.');
/*  234 */     int innerIdx = className.indexOf('$', 
/*  235 */         (lastDotIdx == -1) ? 0 : (lastDotIdx + 1));
/*  236 */     String out = className.substring(lastDotIdx + 1);
/*  237 */     if (innerIdx != -1) {
/*  238 */       out = out.replace('$', '.');
/*      */     }
/*  240 */     return out + arrayPrefix;
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
/*      */   public static String getSimpleName(Class<?> cls) {
/*  252 */     return getSimpleName(cls, "");
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
/*      */   public static String getSimpleName(Class<?> cls, String valueIfNull) {
/*  265 */     return (cls == null) ? valueIfNull : cls.getSimpleName();
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
/*      */   public static String getSimpleName(Object object) {
/*  277 */     return getSimpleName(object, "");
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
/*      */   public static String getSimpleName(Object object, String valueIfNull) {
/*  290 */     return (object == null) ? valueIfNull : object.getClass().getSimpleName();
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
/*      */   public static String getName(Class<?> cls) {
/*  302 */     return getName(cls, "");
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
/*      */   public static String getName(Class<?> cls, String valueIfNull) {
/*  315 */     return (cls == null) ? valueIfNull : cls.getName();
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
/*      */   public static String getName(Object object) {
/*  327 */     return getName(object, "");
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
/*      */   public static String getName(Object object, String valueIfNull) {
/*  340 */     return (object == null) ? valueIfNull : object.getClass().getName();
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
/*      */   public static String getPackageName(Object object, String valueIfNull) {
/*  353 */     if (object == null) {
/*  354 */       return valueIfNull;
/*      */     }
/*  356 */     return getPackageName(object.getClass());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getPackageName(Class<?> cls) {
/*  366 */     if (cls == null) {
/*  367 */       return "";
/*      */     }
/*  369 */     return getPackageName(cls.getName());
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
/*      */   public static String getPackageName(String className) {
/*  382 */     if (StringUtils.isEmpty(className)) {
/*  383 */       return "";
/*      */     }
/*      */ 
/*      */     
/*  387 */     while (className.charAt(0) == '[') {
/*  388 */       className = className.substring(1);
/*      */     }
/*      */     
/*  391 */     if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
/*  392 */       className = className.substring(1);
/*      */     }
/*      */     
/*  395 */     int i = className.lastIndexOf('.');
/*  396 */     if (i == -1) {
/*  397 */       return "";
/*      */     }
/*  399 */     return className.substring(0, i);
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
/*      */   public static String getAbbreviatedName(Class<?> cls, int len) {
/*  415 */     if (cls == null) {
/*  416 */       return "";
/*      */     }
/*  418 */     return getAbbreviatedName(cls.getName(), len);
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
/*      */   public static String getAbbreviatedName(String className, int len) {
/*  447 */     if (len <= 0) {
/*  448 */       throw new IllegalArgumentException("len must be > 0");
/*      */     }
/*  450 */     if (className == null) {
/*  451 */       return "";
/*      */     }
/*      */     
/*  454 */     int availableSpace = len;
/*  455 */     int packageLevels = StringUtils.countMatches(className, '.');
/*  456 */     String[] output = new String[packageLevels + 1];
/*  457 */     int endIndex = className.length() - 1;
/*  458 */     for (int level = packageLevels; level >= 0; level--) {
/*  459 */       int startIndex = className.lastIndexOf('.', endIndex);
/*  460 */       String part = className.substring(startIndex + 1, endIndex + 1);
/*  461 */       availableSpace -= part.length();
/*  462 */       if (level > 0)
/*      */       {
/*  464 */         availableSpace--;
/*      */       }
/*  466 */       if (level == packageLevels) {
/*      */         
/*  468 */         output[level] = part;
/*      */       }
/*  470 */       else if (availableSpace > 0) {
/*  471 */         output[level] = part;
/*      */       } else {
/*      */         
/*  474 */         output[level] = part.substring(0, 1);
/*      */       } 
/*      */       
/*  477 */       endIndex = startIndex - 1;
/*      */     } 
/*      */     
/*  480 */     return StringUtils.join((Object[])output, '.');
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
/*      */   public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
/*  493 */     if (cls == null) {
/*  494 */       return null;
/*      */     }
/*  496 */     List<Class<?>> classes = new ArrayList<>();
/*  497 */     Class<?> superclass = cls.getSuperclass();
/*  498 */     while (superclass != null) {
/*  499 */       classes.add(superclass);
/*  500 */       superclass = superclass.getSuperclass();
/*      */     } 
/*  502 */     return classes;
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
/*      */   public static List<Class<?>> getAllInterfaces(Class<?> cls) {
/*  519 */     if (cls == null) {
/*  520 */       return null;
/*      */     }
/*      */     
/*  523 */     LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<>();
/*  524 */     getAllInterfaces(cls, interfacesFound);
/*      */     
/*  526 */     return new ArrayList<>(interfacesFound);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound) {
/*  536 */     while (cls != null) {
/*  537 */       Class<?>[] interfaces = cls.getInterfaces();
/*      */       
/*  539 */       for (Class<?> i : interfaces) {
/*  540 */         if (interfacesFound.add(i)) {
/*  541 */           getAllInterfaces(i, interfacesFound);
/*      */         }
/*      */       } 
/*      */       
/*  545 */       cls = cls.getSuperclass();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Class<?>> convertClassNamesToClasses(List<String> classNames) {
/*  564 */     if (classNames == null) {
/*  565 */       return null;
/*      */     }
/*  567 */     List<Class<?>> classes = new ArrayList<>(classNames.size());
/*  568 */     for (String className : classNames) {
/*      */       try {
/*  570 */         classes.add(Class.forName(className));
/*  571 */       } catch (Exception ex) {
/*  572 */         classes.add(null);
/*      */       } 
/*      */     } 
/*  575 */     return classes;
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
/*      */   public static List<String> convertClassesToClassNames(List<Class<?>> classes) {
/*  591 */     if (classes == null) {
/*  592 */       return null;
/*      */     }
/*  594 */     List<String> classNames = new ArrayList<>(classes.size());
/*  595 */     for (Class<?> cls : classes) {
/*  596 */       if (cls == null) {
/*  597 */         classNames.add(null); continue;
/*      */       } 
/*  599 */       classNames.add(cls.getName());
/*      */     } 
/*      */     
/*  602 */     return classNames;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAssignable(Class<?>[] classArray, Class<?>... toClassArray) {
/*  644 */     return isAssignable(classArray, toClassArray, true);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing) {
/*  680 */     if (!ArrayUtils.isSameLength((Object[])classArray, (Object[])toClassArray)) {
/*  681 */       return false;
/*      */     }
/*  683 */     if (classArray == null) {
/*  684 */       classArray = ArrayUtils.EMPTY_CLASS_ARRAY;
/*      */     }
/*  686 */     if (toClassArray == null) {
/*  687 */       toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY;
/*      */     }
/*  689 */     for (int i = 0; i < classArray.length; i++) {
/*  690 */       if (!isAssignable(classArray[i], toClassArray[i], autoboxing)) {
/*  691 */         return false;
/*      */       }
/*      */     } 
/*  694 */     return true;
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
/*      */   public static boolean isPrimitiveOrWrapper(Class<?> type) {
/*  708 */     if (type == null) {
/*  709 */       return false;
/*      */     }
/*  711 */     return (type.isPrimitive() || isPrimitiveWrapper(type));
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
/*      */   public static boolean isPrimitiveWrapper(Class<?> type) {
/*  725 */     return wrapperPrimitiveMap.containsKey(type);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAssignable(Class<?> cls, Class<?> toClass) {
/*  760 */     return isAssignable(cls, toClass, true);
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
/*      */   
/*      */   public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing) {
/*  791 */     if (toClass == null) {
/*  792 */       return false;
/*      */     }
/*      */     
/*  795 */     if (cls == null) {
/*  796 */       return !toClass.isPrimitive();
/*      */     }
/*      */     
/*  799 */     if (autoboxing) {
/*  800 */       if (cls.isPrimitive() && !toClass.isPrimitive()) {
/*  801 */         cls = primitiveToWrapper(cls);
/*  802 */         if (cls == null) {
/*  803 */           return false;
/*      */         }
/*      */       } 
/*  806 */       if (toClass.isPrimitive() && !cls.isPrimitive()) {
/*  807 */         cls = wrapperToPrimitive(cls);
/*  808 */         if (cls == null) {
/*  809 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*  813 */     if (cls.equals(toClass)) {
/*  814 */       return true;
/*      */     }
/*  816 */     if (cls.isPrimitive()) {
/*  817 */       if (!toClass.isPrimitive()) {
/*  818 */         return false;
/*      */       }
/*  820 */       if (int.class.equals(cls)) {
/*  821 */         return (long.class.equals(toClass) || float.class
/*  822 */           .equals(toClass) || double.class
/*  823 */           .equals(toClass));
/*      */       }
/*  825 */       if (long.class.equals(cls)) {
/*  826 */         return (float.class.equals(toClass) || double.class
/*  827 */           .equals(toClass));
/*      */       }
/*  829 */       if (boolean.class.equals(cls)) {
/*  830 */         return false;
/*      */       }
/*  832 */       if (double.class.equals(cls)) {
/*  833 */         return false;
/*      */       }
/*  835 */       if (float.class.equals(cls)) {
/*  836 */         return double.class.equals(toClass);
/*      */       }
/*  838 */       if (char.class.equals(cls)) {
/*  839 */         return (int.class.equals(toClass) || long.class
/*  840 */           .equals(toClass) || float.class
/*  841 */           .equals(toClass) || double.class
/*  842 */           .equals(toClass));
/*      */       }
/*  844 */       if (short.class.equals(cls)) {
/*  845 */         return (int.class.equals(toClass) || long.class
/*  846 */           .equals(toClass) || float.class
/*  847 */           .equals(toClass) || double.class
/*  848 */           .equals(toClass));
/*      */       }
/*  850 */       if (byte.class.equals(cls)) {
/*  851 */         return (short.class.equals(toClass) || int.class
/*  852 */           .equals(toClass) || long.class
/*  853 */           .equals(toClass) || float.class
/*  854 */           .equals(toClass) || double.class
/*  855 */           .equals(toClass));
/*      */       }
/*      */       
/*  858 */       return false;
/*      */     } 
/*  860 */     return toClass.isAssignableFrom(cls);
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
/*      */   public static Class<?> primitiveToWrapper(Class<?> cls) {
/*  876 */     Class<?> convertedClass = cls;
/*  877 */     if (cls != null && cls.isPrimitive()) {
/*  878 */       convertedClass = primitiveWrapperMap.get(cls);
/*      */     }
/*  880 */     return convertedClass;
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
/*      */   public static Class<?>[] primitivesToWrappers(Class<?>... classes) {
/*  894 */     if (classes == null) {
/*  895 */       return null;
/*      */     }
/*      */     
/*  898 */     if (classes.length == 0) {
/*  899 */       return classes;
/*      */     }
/*      */     
/*  902 */     Class<?>[] convertedClasses = new Class[classes.length];
/*  903 */     for (int i = 0; i < classes.length; i++) {
/*  904 */       convertedClasses[i] = primitiveToWrapper(classes[i]);
/*      */     }
/*  906 */     return convertedClasses;
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
/*      */   public static Class<?> wrapperToPrimitive(Class<?> cls) {
/*  926 */     return wrapperPrimitiveMap.get(cls);
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
/*      */   public static Class<?>[] wrappersToPrimitives(Class<?>... classes) {
/*  944 */     if (classes == null) {
/*  945 */       return null;
/*      */     }
/*      */     
/*  948 */     if (classes.length == 0) {
/*  949 */       return classes;
/*      */     }
/*      */     
/*  952 */     Class<?>[] convertedClasses = new Class[classes.length];
/*  953 */     for (int i = 0; i < classes.length; i++) {
/*  954 */       convertedClasses[i] = wrapperToPrimitive(classes[i]);
/*      */     }
/*  956 */     return convertedClasses;
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
/*      */   public static boolean isInnerClass(Class<?> cls) {
/*  969 */     return (cls != null && cls.getEnclosingClass() != null);
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
/*      */   public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize) throws ClassNotFoundException {
/*      */     try {
/*      */       Class<?> clazz;
/*  990 */       if (namePrimitiveMap.containsKey(className)) {
/*  991 */         clazz = namePrimitiveMap.get(className);
/*      */       } else {
/*  993 */         clazz = Class.forName(toCanonicalName(className), initialize, classLoader);
/*      */       } 
/*  995 */       return clazz;
/*  996 */     } catch (ClassNotFoundException ex) {
/*      */       
/*  998 */       int lastDotIndex = className.lastIndexOf('.');
/*      */       
/* 1000 */       if (lastDotIndex != -1) {
/*      */         try {
/* 1002 */           return getClass(classLoader, className.substring(0, lastDotIndex) + '$' + className
/* 1003 */               .substring(lastDotIndex + 1), initialize);
/*      */         }
/* 1005 */         catch (ClassNotFoundException classNotFoundException) {}
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1010 */       throw ex;
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
/*      */ 
/*      */   
/*      */   public static Class<?> getClass(ClassLoader classLoader, String className) throws ClassNotFoundException {
/* 1027 */     return getClass(classLoader, className, true);
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
/*      */   public static Class<?> getClass(String className) throws ClassNotFoundException {
/* 1042 */     return getClass(className, true);
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
/*      */   public static Class<?> getClass(String className, boolean initialize) throws ClassNotFoundException {
/* 1057 */     ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
/* 1058 */     ClassLoader loader = (contextCL == null) ? ClassUtils.class.getClassLoader() : contextCL;
/* 1059 */     return getClass(loader, className, initialize);
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
/*      */   public static Method getPublicMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
/* 1089 */     Method declaredMethod = cls.getMethod(methodName, parameterTypes);
/* 1090 */     if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers())) {
/* 1091 */       return declaredMethod;
/*      */     }
/*      */     
/* 1094 */     List<Class<?>> candidateClasses = new ArrayList<>();
/* 1095 */     candidateClasses.addAll(getAllInterfaces(cls));
/* 1096 */     candidateClasses.addAll(getAllSuperclasses(cls));
/*      */     
/* 1098 */     for (Class<?> candidateClass : candidateClasses) {
/* 1099 */       Method candidateMethod; if (!Modifier.isPublic(candidateClass.getModifiers())) {
/*      */         continue;
/*      */       }
/*      */       
/*      */       try {
/* 1104 */         candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
/* 1105 */       } catch (NoSuchMethodException ex) {
/*      */         continue;
/*      */       } 
/* 1108 */       if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) {
/* 1109 */         return candidateMethod;
/*      */       }
/*      */     } 
/*      */     
/* 1113 */     throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + 
/* 1114 */         ArrayUtils.toString(parameterTypes));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String toCanonicalName(String className) {
/* 1125 */     className = StringUtils.deleteWhitespace(className);
/* 1126 */     Validate.notNull(className, "className must not be null.", new Object[0]);
/* 1127 */     if (className.endsWith("[]")) {
/* 1128 */       StringBuilder classNameBuffer = new StringBuilder();
/* 1129 */       while (className.endsWith("[]")) {
/* 1130 */         className = className.substring(0, className.length() - 2);
/* 1131 */         classNameBuffer.append("[");
/*      */       } 
/* 1133 */       String abbreviation = abbreviationMap.get(className);
/* 1134 */       if (abbreviation != null) {
/* 1135 */         classNameBuffer.append(abbreviation);
/*      */       } else {
/* 1137 */         classNameBuffer.append("L").append(className).append(";");
/*      */       } 
/* 1139 */       className = classNameBuffer.toString();
/*      */     } 
/* 1141 */     return className;
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
/*      */   public static Class<?>[] toClass(Object... array) {
/* 1155 */     if (array == null)
/* 1156 */       return null; 
/* 1157 */     if (array.length == 0) {
/* 1158 */       return ArrayUtils.EMPTY_CLASS_ARRAY;
/*      */     }
/* 1160 */     Class<?>[] classes = new Class[array.length];
/* 1161 */     for (int i = 0; i < array.length; i++) {
/* 1162 */       classes[i] = (array[i] == null) ? null : array[i].getClass();
/*      */     }
/* 1164 */     return classes;
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
/*      */   public static String getShortCanonicalName(Object object, String valueIfNull) {
/* 1178 */     if (object == null) {
/* 1179 */       return valueIfNull;
/*      */     }
/* 1181 */     return getShortCanonicalName(object.getClass().getName());
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
/*      */   public static String getCanonicalName(Class<?> cls) {
/* 1193 */     return getCanonicalName(cls, "");
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
/*      */   public static String getCanonicalName(Class<?> cls, String valueIfNull) {
/* 1206 */     if (cls == null) {
/* 1207 */       return valueIfNull;
/*      */     }
/* 1209 */     String canonicalName = cls.getCanonicalName();
/* 1210 */     return (canonicalName == null) ? valueIfNull : canonicalName;
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
/*      */   public static String getCanonicalName(Object object) {
/* 1222 */     return getCanonicalName(object, "");
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
/*      */   public static String getCanonicalName(Object object, String valueIfNull) {
/* 1235 */     if (object == null) {
/* 1236 */       return valueIfNull;
/*      */     }
/* 1238 */     String canonicalName = object.getClass().getCanonicalName();
/* 1239 */     return (canonicalName == null) ? valueIfNull : canonicalName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getShortCanonicalName(Class<?> cls) {
/* 1250 */     if (cls == null) {
/* 1251 */       return "";
/*      */     }
/* 1253 */     return getShortCanonicalName(cls.getName());
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
/*      */   public static String getShortCanonicalName(String canonicalName) {
/* 1266 */     return getShortClassName(getCanonicalName(canonicalName));
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
/*      */   public static String getPackageCanonicalName(Object object, String valueIfNull) {
/* 1280 */     if (object == null) {
/* 1281 */       return valueIfNull;
/*      */     }
/* 1283 */     return getPackageCanonicalName(object.getClass().getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getPackageCanonicalName(Class<?> cls) {
/* 1294 */     if (cls == null) {
/* 1295 */       return "";
/*      */     }
/* 1297 */     return getPackageCanonicalName(cls.getName());
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
/*      */   public static String getPackageCanonicalName(String canonicalName) {
/* 1311 */     return getPackageName(getCanonicalName(canonicalName));
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
/*      */   private static String getCanonicalName(String className) {
/* 1331 */     className = StringUtils.deleteWhitespace(className);
/* 1332 */     if (className == null) {
/* 1333 */       return null;
/*      */     }
/* 1335 */     int dim = 0;
/* 1336 */     while (className.startsWith("[")) {
/* 1337 */       dim++;
/* 1338 */       className = className.substring(1);
/*      */     } 
/* 1340 */     if (dim < 1) {
/* 1341 */       return className;
/*      */     }
/* 1343 */     if (className.startsWith("L")) {
/* 1344 */       className = className.substring(1, 
/*      */           
/* 1346 */           className.endsWith(";") ? (
/* 1347 */           className.length() - 1) : 
/* 1348 */           className.length());
/*      */     }
/* 1350 */     else if (!className.isEmpty()) {
/* 1351 */       className = reverseAbbreviationMap.get(className.substring(0, 1));
/*      */     } 
/*      */     
/* 1354 */     StringBuilder canonicalClassNameBuffer = new StringBuilder(className);
/* 1355 */     for (int i = 0; i < dim; i++) {
/* 1356 */       canonicalClassNameBuffer.append("[]");
/*      */     }
/* 1358 */     return canonicalClassNameBuffer.toString();
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
/*      */   public static Iterable<Class<?>> hierarchy(Class<?> type) {
/* 1370 */     return hierarchy(type, Interfaces.EXCLUDE);
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
/*      */   public static Iterable<Class<?>> hierarchy(final Class<?> type, Interfaces interfacesBehavior) {
/* 1382 */     final Iterable<Class<?>> classes = new Iterable<Class<?>>()
/*      */       {
/*      */         public Iterator<Class<?>> iterator()
/*      */         {
/* 1386 */           final MutableObject<Class<?>> next = new MutableObject(type);
/* 1387 */           return new Iterator<Class<?>>()
/*      */             {
/*      */               public boolean hasNext()
/*      */               {
/* 1391 */                 return (next.getValue() != null);
/*      */               }
/*      */ 
/*      */               
/*      */               public Class<?> next() {
/* 1396 */                 Class<?> result = (Class)next.getValue();
/* 1397 */                 next.setValue(result.getSuperclass());
/* 1398 */                 return result;
/*      */               }
/*      */ 
/*      */               
/*      */               public void remove() {
/* 1403 */                 throw new UnsupportedOperationException();
/*      */               }
/*      */             };
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 1410 */     if (interfacesBehavior != Interfaces.INCLUDE) {
/* 1411 */       return classes;
/*      */     }
/* 1413 */     return new Iterable<Class<?>>()
/*      */       {
/*      */         public Iterator<Class<?>> iterator()
/*      */         {
/* 1417 */           final Set<Class<?>> seenInterfaces = new HashSet<>();
/* 1418 */           final Iterator<Class<?>> wrapped = classes.iterator();
/*      */           
/* 1420 */           return new Iterator<Class<?>>() {
/* 1421 */               Iterator<Class<?>> interfaces = Collections.<Class<?>>emptySet().iterator();
/*      */ 
/*      */               
/*      */               public boolean hasNext() {
/* 1425 */                 return (this.interfaces.hasNext() || wrapped.hasNext());
/*      */               }
/*      */ 
/*      */               
/*      */               public Class<?> next() {
/* 1430 */                 if (this.interfaces.hasNext()) {
/* 1431 */                   Class<?> nextInterface = this.interfaces.next();
/* 1432 */                   seenInterfaces.add(nextInterface);
/* 1433 */                   return nextInterface;
/*      */                 } 
/* 1435 */                 Class<?> nextSuperclass = wrapped.next();
/* 1436 */                 Set<Class<?>> currentInterfaces = new LinkedHashSet<>();
/* 1437 */                 walkInterfaces(currentInterfaces, nextSuperclass);
/* 1438 */                 this.interfaces = currentInterfaces.iterator();
/* 1439 */                 return nextSuperclass;
/*      */               }
/*      */               
/*      */               private void walkInterfaces(Set<Class<?>> addTo, Class<?> c) {
/* 1443 */                 for (Class<?> iface : c.getInterfaces()) {
/* 1444 */                   if (!seenInterfaces.contains(iface)) {
/* 1445 */                     addTo.add(iface);
/*      */                   }
/* 1447 */                   walkInterfaces(addTo, iface);
/*      */                 } 
/*      */               }
/*      */ 
/*      */               
/*      */               public void remove() {
/* 1453 */                 throw new UnsupportedOperationException();
/*      */               }
/*      */             };
/*      */         }
/*      */       };
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/ClassUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */