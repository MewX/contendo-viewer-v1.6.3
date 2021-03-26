/*      */ package org.apache.commons.lang3.builder;
/*      */ 
/*      */ import java.lang.reflect.AccessibleObject;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ import org.apache.commons.lang3.ClassUtils;
/*      */ import org.apache.commons.lang3.tuple.Pair;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EqualsBuilder
/*      */   implements Builder<Boolean>
/*      */ {
/*   98 */   private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Set<Pair<IDKey, IDKey>> getRegistry() {
/*  127 */     return REGISTRY.get();
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
/*      */   static Pair<IDKey, IDKey> getRegisterPair(Object lhs, Object rhs) {
/*  141 */     IDKey left = new IDKey(lhs);
/*  142 */     IDKey right = new IDKey(rhs);
/*  143 */     return Pair.of(left, right);
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
/*      */   static boolean isRegistered(Object lhs, Object rhs) {
/*  160 */     Set<Pair<IDKey, IDKey>> registry = getRegistry();
/*  161 */     Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
/*  162 */     Pair<IDKey, IDKey> swappedPair = Pair.of(pair.getRight(), pair.getLeft());
/*      */     
/*  164 */     return (registry != null && (registry
/*  165 */       .contains(pair) || registry.contains(swappedPair)));
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
/*      */   private static void register(Object lhs, Object rhs) {
/*  178 */     Set<Pair<IDKey, IDKey>> registry = getRegistry();
/*  179 */     if (registry == null) {
/*  180 */       registry = new HashSet<>();
/*  181 */       REGISTRY.set(registry);
/*      */     } 
/*  183 */     Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
/*  184 */     registry.add(pair);
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
/*      */   private static void unregister(Object lhs, Object rhs) {
/*  200 */     Set<Pair<IDKey, IDKey>> registry = getRegistry();
/*  201 */     if (registry != null) {
/*  202 */       Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
/*  203 */       registry.remove(pair);
/*  204 */       if (registry.isEmpty()) {
/*  205 */         REGISTRY.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isEquals = true;
/*      */   
/*      */   private boolean testTransients = false;
/*      */   
/*      */   private boolean testRecursive = false;
/*      */   
/*      */   private List<Class<?>> bypassReflectionClasses;
/*      */   
/*  219 */   private Class<?> reflectUpToClass = null;
/*  220 */   private String[] excludeFields = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder() {
/*  230 */     this.bypassReflectionClasses = new ArrayList<>();
/*  231 */     this.bypassReflectionClasses.add(String.class);
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
/*      */   public EqualsBuilder setTestTransients(boolean testTransients) {
/*  243 */     this.testTransients = testTransients;
/*  244 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder setTestRecursive(boolean testRecursive) {
/*  254 */     this.testRecursive = testRecursive;
/*  255 */     return this;
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
/*      */   public EqualsBuilder setBypassReflectionClasses(List<Class<?>> bypassReflectionClasses) {
/*  271 */     this.bypassReflectionClasses = bypassReflectionClasses;
/*  272 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder setReflectUpToClass(Class<?> reflectUpToClass) {
/*  282 */     this.reflectUpToClass = reflectUpToClass;
/*  283 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder setExcludeFields(String... excludeFields) {
/*  293 */     this.excludeFields = excludeFields;
/*  294 */     return this;
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
/*      */   public static boolean reflectionEquals(Object lhs, Object rhs, Collection<String> excludeFields) {
/*  321 */     return reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
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
/*      */   public static boolean reflectionEquals(Object lhs, Object rhs, String... excludeFields) {
/*  347 */     return reflectionEquals(lhs, rhs, false, null, excludeFields);
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
/*      */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients) {
/*  374 */     return reflectionEquals(lhs, rhs, testTransients, null, new String[0]);
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
/*      */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, String... excludeFields) {
/*  408 */     return reflectionEquals(lhs, rhs, testTransients, reflectUpToClass, false, excludeFields);
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
/*      */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, boolean testRecursive, String... excludeFields) {
/*  449 */     if (lhs == rhs) {
/*  450 */       return true;
/*      */     }
/*  452 */     if (lhs == null || rhs == null) {
/*  453 */       return false;
/*      */     }
/*  455 */     return (new EqualsBuilder())
/*  456 */       .setExcludeFields(excludeFields)
/*  457 */       .setReflectUpToClass(reflectUpToClass)
/*  458 */       .setTestTransients(testTransients)
/*  459 */       .setTestRecursive(testRecursive)
/*  460 */       .reflectionAppend(lhs, rhs)
/*  461 */       .isEquals();
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
/*      */   public EqualsBuilder reflectionAppend(Object lhs, Object rhs) {
/*      */     Class<?> testClass;
/*  492 */     if (!this.isEquals) {
/*  493 */       return this;
/*      */     }
/*  495 */     if (lhs == rhs) {
/*  496 */       return this;
/*      */     }
/*  498 */     if (lhs == null || rhs == null) {
/*  499 */       this.isEquals = false;
/*  500 */       return this;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  507 */     Class<?> lhsClass = lhs.getClass();
/*  508 */     Class<?> rhsClass = rhs.getClass();
/*      */     
/*  510 */     if (lhsClass.isInstance(rhs)) {
/*  511 */       testClass = lhsClass;
/*  512 */       if (!rhsClass.isInstance(lhs))
/*      */       {
/*  514 */         testClass = rhsClass;
/*      */       }
/*  516 */     } else if (rhsClass.isInstance(lhs)) {
/*  517 */       testClass = rhsClass;
/*  518 */       if (!lhsClass.isInstance(rhs))
/*      */       {
/*  520 */         testClass = lhsClass;
/*      */       }
/*      */     } else {
/*      */       
/*  524 */       this.isEquals = false;
/*  525 */       return this;
/*      */     } 
/*      */     
/*      */     try {
/*  529 */       if (testClass.isArray()) {
/*  530 */         append(lhs, rhs);
/*      */       
/*      */       }
/*  533 */       else if (this.bypassReflectionClasses != null && (this.bypassReflectionClasses
/*  534 */         .contains(lhsClass) || this.bypassReflectionClasses.contains(rhsClass))) {
/*  535 */         this.isEquals = lhs.equals(rhs);
/*      */       } else {
/*  537 */         reflectionAppend(lhs, rhs, testClass);
/*  538 */         while (testClass.getSuperclass() != null && testClass != this.reflectUpToClass) {
/*  539 */           testClass = testClass.getSuperclass();
/*  540 */           reflectionAppend(lhs, rhs, testClass);
/*      */         }
/*      */       
/*      */       } 
/*  544 */     } catch (IllegalArgumentException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  550 */       this.isEquals = false;
/*  551 */       return this;
/*      */     } 
/*  553 */     return this;
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
/*      */   private void reflectionAppend(Object lhs, Object rhs, Class<?> clazz) {
/*  569 */     if (isRegistered(lhs, rhs)) {
/*      */       return;
/*      */     }
/*      */     
/*      */     try {
/*  574 */       register(lhs, rhs);
/*  575 */       Field[] fields = clazz.getDeclaredFields();
/*  576 */       AccessibleObject.setAccessible((AccessibleObject[])fields, true);
/*  577 */       for (int i = 0; i < fields.length && this.isEquals; i++) {
/*  578 */         Field f = fields[i];
/*  579 */         if (!ArrayUtils.contains((Object[])this.excludeFields, f.getName()) && 
/*  580 */           !f.getName().contains("$") && (this.testTransients || 
/*  581 */           !Modifier.isTransient(f.getModifiers())) && 
/*  582 */           !Modifier.isStatic(f.getModifiers()) && 
/*  583 */           !f.isAnnotationPresent((Class)EqualsExclude.class)) {
/*      */           try {
/*  585 */             append(f.get(lhs), f.get(rhs));
/*  586 */           } catch (IllegalAccessException e) {
/*      */ 
/*      */             
/*  589 */             throw new InternalError("Unexpected IllegalAccessException");
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } finally {
/*  594 */       unregister(lhs, rhs);
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
/*      */   public EqualsBuilder appendSuper(boolean superEquals) {
/*  608 */     if (!this.isEquals) {
/*  609 */       return this;
/*      */     }
/*  611 */     this.isEquals = superEquals;
/*  612 */     return this;
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
/*      */   public EqualsBuilder append(Object lhs, Object rhs) {
/*  629 */     if (!this.isEquals) {
/*  630 */       return this;
/*      */     }
/*  632 */     if (lhs == rhs) {
/*  633 */       return this;
/*      */     }
/*  635 */     if (lhs == null || rhs == null) {
/*  636 */       setEquals(false);
/*  637 */       return this;
/*      */     } 
/*  639 */     Class<?> lhsClass = lhs.getClass();
/*  640 */     if (lhsClass.isArray()) {
/*      */ 
/*      */       
/*  643 */       appendArray(lhs, rhs);
/*      */     
/*      */     }
/*  646 */     else if (this.testRecursive && !ClassUtils.isPrimitiveOrWrapper(lhsClass)) {
/*  647 */       reflectionAppend(lhs, rhs);
/*      */     } else {
/*  649 */       this.isEquals = lhs.equals(rhs);
/*      */     } 
/*      */     
/*  652 */     return this;
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
/*      */   private void appendArray(Object lhs, Object rhs) {
/*  665 */     if (lhs.getClass() != rhs.getClass()) {
/*  666 */       setEquals(false);
/*  667 */     } else if (lhs instanceof long[]) {
/*  668 */       append((long[])lhs, (long[])rhs);
/*  669 */     } else if (lhs instanceof int[]) {
/*  670 */       append((int[])lhs, (int[])rhs);
/*  671 */     } else if (lhs instanceof short[]) {
/*  672 */       append((short[])lhs, (short[])rhs);
/*  673 */     } else if (lhs instanceof char[]) {
/*  674 */       append((char[])lhs, (char[])rhs);
/*  675 */     } else if (lhs instanceof byte[]) {
/*  676 */       append((byte[])lhs, (byte[])rhs);
/*  677 */     } else if (lhs instanceof double[]) {
/*  678 */       append((double[])lhs, (double[])rhs);
/*  679 */     } else if (lhs instanceof float[]) {
/*  680 */       append((float[])lhs, (float[])rhs);
/*  681 */     } else if (lhs instanceof boolean[]) {
/*  682 */       append((boolean[])lhs, (boolean[])rhs);
/*      */     } else {
/*      */       
/*  685 */       append((Object[])lhs, (Object[])rhs);
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
/*      */   public EqualsBuilder append(long lhs, long rhs) {
/*  701 */     if (!this.isEquals) {
/*  702 */       return this;
/*      */     }
/*  704 */     this.isEquals = (lhs == rhs);
/*  705 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder append(int lhs, int rhs) {
/*  716 */     if (!this.isEquals) {
/*  717 */       return this;
/*      */     }
/*  719 */     this.isEquals = (lhs == rhs);
/*  720 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder append(short lhs, short rhs) {
/*  731 */     if (!this.isEquals) {
/*  732 */       return this;
/*      */     }
/*  734 */     this.isEquals = (lhs == rhs);
/*  735 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder append(char lhs, char rhs) {
/*  746 */     if (!this.isEquals) {
/*  747 */       return this;
/*      */     }
/*  749 */     this.isEquals = (lhs == rhs);
/*  750 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder append(byte lhs, byte rhs) {
/*  761 */     if (!this.isEquals) {
/*  762 */       return this;
/*      */     }
/*  764 */     this.isEquals = (lhs == rhs);
/*  765 */     return this;
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
/*      */   public EqualsBuilder append(double lhs, double rhs) {
/*  782 */     if (!this.isEquals) {
/*  783 */       return this;
/*      */     }
/*  785 */     return append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
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
/*      */   public EqualsBuilder append(float lhs, float rhs) {
/*  802 */     if (!this.isEquals) {
/*  803 */       return this;
/*      */     }
/*  805 */     return append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EqualsBuilder append(boolean lhs, boolean rhs) {
/*  816 */     if (!this.isEquals) {
/*  817 */       return this;
/*      */     }
/*  819 */     this.isEquals = (lhs == rhs);
/*  820 */     return this;
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
/*      */   public EqualsBuilder append(Object[] lhs, Object[] rhs) {
/*  837 */     if (!this.isEquals) {
/*  838 */       return this;
/*      */     }
/*  840 */     if (lhs == rhs) {
/*  841 */       return this;
/*      */     }
/*  843 */     if (lhs == null || rhs == null) {
/*  844 */       setEquals(false);
/*  845 */       return this;
/*      */     } 
/*  847 */     if (lhs.length != rhs.length) {
/*  848 */       setEquals(false);
/*  849 */       return this;
/*      */     } 
/*  851 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/*  852 */       append(lhs[i], rhs[i]);
/*      */     }
/*  854 */     return this;
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
/*      */   public EqualsBuilder append(long[] lhs, long[] rhs) {
/*  868 */     if (!this.isEquals) {
/*  869 */       return this;
/*      */     }
/*  871 */     if (lhs == rhs) {
/*  872 */       return this;
/*      */     }
/*  874 */     if (lhs == null || rhs == null) {
/*  875 */       setEquals(false);
/*  876 */       return this;
/*      */     } 
/*  878 */     if (lhs.length != rhs.length) {
/*  879 */       setEquals(false);
/*  880 */       return this;
/*      */     } 
/*  882 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/*  883 */       append(lhs[i], rhs[i]);
/*      */     }
/*  885 */     return this;
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
/*      */   public EqualsBuilder append(int[] lhs, int[] rhs) {
/*  899 */     if (!this.isEquals) {
/*  900 */       return this;
/*      */     }
/*  902 */     if (lhs == rhs) {
/*  903 */       return this;
/*      */     }
/*  905 */     if (lhs == null || rhs == null) {
/*  906 */       setEquals(false);
/*  907 */       return this;
/*      */     } 
/*  909 */     if (lhs.length != rhs.length) {
/*  910 */       setEquals(false);
/*  911 */       return this;
/*      */     } 
/*  913 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/*  914 */       append(lhs[i], rhs[i]);
/*      */     }
/*  916 */     return this;
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
/*      */   public EqualsBuilder append(short[] lhs, short[] rhs) {
/*  930 */     if (!this.isEquals) {
/*  931 */       return this;
/*      */     }
/*  933 */     if (lhs == rhs) {
/*  934 */       return this;
/*      */     }
/*  936 */     if (lhs == null || rhs == null) {
/*  937 */       setEquals(false);
/*  938 */       return this;
/*      */     } 
/*  940 */     if (lhs.length != rhs.length) {
/*  941 */       setEquals(false);
/*  942 */       return this;
/*      */     } 
/*  944 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/*  945 */       append(lhs[i], rhs[i]);
/*      */     }
/*  947 */     return this;
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
/*      */   public EqualsBuilder append(char[] lhs, char[] rhs) {
/*  961 */     if (!this.isEquals) {
/*  962 */       return this;
/*      */     }
/*  964 */     if (lhs == rhs) {
/*  965 */       return this;
/*      */     }
/*  967 */     if (lhs == null || rhs == null) {
/*  968 */       setEquals(false);
/*  969 */       return this;
/*      */     } 
/*  971 */     if (lhs.length != rhs.length) {
/*  972 */       setEquals(false);
/*  973 */       return this;
/*      */     } 
/*  975 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/*  976 */       append(lhs[i], rhs[i]);
/*      */     }
/*  978 */     return this;
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
/*      */   public EqualsBuilder append(byte[] lhs, byte[] rhs) {
/*  992 */     if (!this.isEquals) {
/*  993 */       return this;
/*      */     }
/*  995 */     if (lhs == rhs) {
/*  996 */       return this;
/*      */     }
/*  998 */     if (lhs == null || rhs == null) {
/*  999 */       setEquals(false);
/* 1000 */       return this;
/*      */     } 
/* 1002 */     if (lhs.length != rhs.length) {
/* 1003 */       setEquals(false);
/* 1004 */       return this;
/*      */     } 
/* 1006 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/* 1007 */       append(lhs[i], rhs[i]);
/*      */     }
/* 1009 */     return this;
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
/*      */   public EqualsBuilder append(double[] lhs, double[] rhs) {
/* 1023 */     if (!this.isEquals) {
/* 1024 */       return this;
/*      */     }
/* 1026 */     if (lhs == rhs) {
/* 1027 */       return this;
/*      */     }
/* 1029 */     if (lhs == null || rhs == null) {
/* 1030 */       setEquals(false);
/* 1031 */       return this;
/*      */     } 
/* 1033 */     if (lhs.length != rhs.length) {
/* 1034 */       setEquals(false);
/* 1035 */       return this;
/*      */     } 
/* 1037 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/* 1038 */       append(lhs[i], rhs[i]);
/*      */     }
/* 1040 */     return this;
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
/*      */   public EqualsBuilder append(float[] lhs, float[] rhs) {
/* 1054 */     if (!this.isEquals) {
/* 1055 */       return this;
/*      */     }
/* 1057 */     if (lhs == rhs) {
/* 1058 */       return this;
/*      */     }
/* 1060 */     if (lhs == null || rhs == null) {
/* 1061 */       setEquals(false);
/* 1062 */       return this;
/*      */     } 
/* 1064 */     if (lhs.length != rhs.length) {
/* 1065 */       setEquals(false);
/* 1066 */       return this;
/*      */     } 
/* 1068 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/* 1069 */       append(lhs[i], rhs[i]);
/*      */     }
/* 1071 */     return this;
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
/*      */   public EqualsBuilder append(boolean[] lhs, boolean[] rhs) {
/* 1085 */     if (!this.isEquals) {
/* 1086 */       return this;
/*      */     }
/* 1088 */     if (lhs == rhs) {
/* 1089 */       return this;
/*      */     }
/* 1091 */     if (lhs == null || rhs == null) {
/* 1092 */       setEquals(false);
/* 1093 */       return this;
/*      */     } 
/* 1095 */     if (lhs.length != rhs.length) {
/* 1096 */       setEquals(false);
/* 1097 */       return this;
/*      */     } 
/* 1099 */     for (int i = 0; i < lhs.length && this.isEquals; i++) {
/* 1100 */       append(lhs[i], rhs[i]);
/*      */     }
/* 1102 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEquals() {
/* 1112 */     return this.isEquals;
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
/*      */   public Boolean build() {
/* 1126 */     return Boolean.valueOf(isEquals());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setEquals(boolean isEquals) {
/* 1136 */     this.isEquals = isEquals;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset() {
/* 1144 */     this.isEquals = true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/builder/EqualsBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */