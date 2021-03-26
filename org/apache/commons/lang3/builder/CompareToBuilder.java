/*      */ package org.apache.commons.lang3.builder;
/*      */ 
/*      */ import java.lang.reflect.AccessibleObject;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CompareToBuilder
/*      */   implements Builder<Integer>
/*      */ {
/*  111 */   private int comparison = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int reflectionCompare(Object lhs, Object rhs) {
/*  142 */     return reflectionCompare(lhs, rhs, false, null, new String[0]);
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
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients) {
/*  174 */     return reflectionCompare(lhs, rhs, compareTransients, null, new String[0]);
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
/*      */   public static int reflectionCompare(Object lhs, Object rhs, Collection<String> excludeFields) {
/*  207 */     return reflectionCompare(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
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
/*      */   public static int reflectionCompare(Object lhs, Object rhs, String... excludeFields) {
/*  240 */     return reflectionCompare(lhs, rhs, false, null, excludeFields);
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
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class<?> reflectUpToClass, String... excludeFields) {
/*  282 */     if (lhs == rhs) {
/*  283 */       return 0;
/*      */     }
/*  285 */     if (lhs == null || rhs == null) {
/*  286 */       throw new NullPointerException();
/*      */     }
/*  288 */     Class<?> lhsClazz = lhs.getClass();
/*  289 */     if (!lhsClazz.isInstance(rhs)) {
/*  290 */       throw new ClassCastException();
/*      */     }
/*  292 */     CompareToBuilder compareToBuilder = new CompareToBuilder();
/*  293 */     reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*  294 */     while (lhsClazz.getSuperclass() != null && lhsClazz != reflectUpToClass) {
/*  295 */       lhsClazz = lhsClazz.getSuperclass();
/*  296 */       reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*      */     } 
/*  298 */     return compareToBuilder.toComparison();
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
/*      */   private static void reflectionAppend(Object lhs, Object rhs, Class<?> clazz, CompareToBuilder builder, boolean useTransients, String[] excludeFields) {
/*  320 */     Field[] fields = clazz.getDeclaredFields();
/*  321 */     AccessibleObject.setAccessible((AccessibleObject[])fields, true);
/*  322 */     for (int i = 0; i < fields.length && builder.comparison == 0; i++) {
/*  323 */       Field f = fields[i];
/*  324 */       if (!ArrayUtils.contains((Object[])excludeFields, f.getName()) && 
/*  325 */         !f.getName().contains("$") && (useTransients || 
/*  326 */         !Modifier.isTransient(f.getModifiers())) && 
/*  327 */         !Modifier.isStatic(f.getModifiers())) {
/*      */         try {
/*  329 */           builder.append(f.get(lhs), f.get(rhs));
/*  330 */         } catch (IllegalAccessException e) {
/*      */ 
/*      */           
/*  333 */           throw new InternalError("Unexpected IllegalAccessException");
/*      */         } 
/*      */       }
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
/*      */   public CompareToBuilder appendSuper(int superCompareTo) {
/*  349 */     if (this.comparison != 0) {
/*  350 */       return this;
/*      */     }
/*  352 */     this.comparison = superCompareTo;
/*  353 */     return this;
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
/*      */   public CompareToBuilder append(Object lhs, Object rhs) {
/*  377 */     return append(lhs, rhs, (Comparator<?>)null);
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
/*      */   public CompareToBuilder append(Object lhs, Object rhs, Comparator<?> comparator) {
/*  406 */     if (this.comparison != 0) {
/*  407 */       return this;
/*      */     }
/*  409 */     if (lhs == rhs) {
/*  410 */       return this;
/*      */     }
/*  412 */     if (lhs == null) {
/*  413 */       this.comparison = -1;
/*  414 */       return this;
/*      */     } 
/*  416 */     if (rhs == null) {
/*  417 */       this.comparison = 1;
/*  418 */       return this;
/*      */     } 
/*  420 */     if (lhs.getClass().isArray()) {
/*      */       
/*  422 */       appendArray(lhs, rhs, comparator);
/*      */     
/*      */     }
/*  425 */     else if (comparator == null) {
/*      */       
/*  427 */       Comparable<Object> comparable = (Comparable<Object>)lhs;
/*  428 */       this.comparison = comparable.compareTo(rhs);
/*      */     } else {
/*      */       
/*  431 */       Comparator<Object> comparator2 = (Comparator)comparator;
/*  432 */       this.comparison = comparator2.compare(lhs, rhs);
/*      */     } 
/*      */     
/*  435 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void appendArray(Object lhs, Object rhs, Comparator<?> comparator) {
/*  442 */     if (lhs instanceof long[]) {
/*  443 */       append((long[])lhs, (long[])rhs);
/*  444 */     } else if (lhs instanceof int[]) {
/*  445 */       append((int[])lhs, (int[])rhs);
/*  446 */     } else if (lhs instanceof short[]) {
/*  447 */       append((short[])lhs, (short[])rhs);
/*  448 */     } else if (lhs instanceof char[]) {
/*  449 */       append((char[])lhs, (char[])rhs);
/*  450 */     } else if (lhs instanceof byte[]) {
/*  451 */       append((byte[])lhs, (byte[])rhs);
/*  452 */     } else if (lhs instanceof double[]) {
/*  453 */       append((double[])lhs, (double[])rhs);
/*  454 */     } else if (lhs instanceof float[]) {
/*  455 */       append((float[])lhs, (float[])rhs);
/*  456 */     } else if (lhs instanceof boolean[]) {
/*  457 */       append((boolean[])lhs, (boolean[])rhs);
/*      */     }
/*      */     else {
/*      */       
/*  461 */       append((Object[])lhs, (Object[])rhs, comparator);
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
/*      */   public CompareToBuilder append(long lhs, long rhs) {
/*  475 */     if (this.comparison != 0) {
/*  476 */       return this;
/*      */     }
/*  478 */     this.comparison = Long.compare(lhs, rhs);
/*  479 */     return this;
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
/*      */   public CompareToBuilder append(int lhs, int rhs) {
/*  491 */     if (this.comparison != 0) {
/*  492 */       return this;
/*      */     }
/*  494 */     this.comparison = Integer.compare(lhs, rhs);
/*  495 */     return this;
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
/*      */   public CompareToBuilder append(short lhs, short rhs) {
/*  507 */     if (this.comparison != 0) {
/*  508 */       return this;
/*      */     }
/*  510 */     this.comparison = Short.compare(lhs, rhs);
/*  511 */     return this;
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
/*      */   public CompareToBuilder append(char lhs, char rhs) {
/*  523 */     if (this.comparison != 0) {
/*  524 */       return this;
/*      */     }
/*  526 */     this.comparison = Character.compare(lhs, rhs);
/*  527 */     return this;
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
/*      */   public CompareToBuilder append(byte lhs, byte rhs) {
/*  539 */     if (this.comparison != 0) {
/*  540 */       return this;
/*      */     }
/*  542 */     this.comparison = Byte.compare(lhs, rhs);
/*  543 */     return this;
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
/*      */   public CompareToBuilder append(double lhs, double rhs) {
/*  560 */     if (this.comparison != 0) {
/*  561 */       return this;
/*      */     }
/*  563 */     this.comparison = Double.compare(lhs, rhs);
/*  564 */     return this;
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
/*      */   public CompareToBuilder append(float lhs, float rhs) {
/*  581 */     if (this.comparison != 0) {
/*  582 */       return this;
/*      */     }
/*  584 */     this.comparison = Float.compare(lhs, rhs);
/*  585 */     return this;
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
/*      */   public CompareToBuilder append(boolean lhs, boolean rhs) {
/*  597 */     if (this.comparison != 0) {
/*  598 */       return this;
/*      */     }
/*  600 */     if (lhs == rhs) {
/*  601 */       return this;
/*      */     }
/*  603 */     if (lhs) {
/*  604 */       this.comparison = 1;
/*      */     } else {
/*  606 */       this.comparison = -1;
/*      */     } 
/*  608 */     return this;
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
/*      */   public CompareToBuilder append(Object[] lhs, Object[] rhs) {
/*  633 */     return append(lhs, rhs, (Comparator<?>)null);
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
/*      */   public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator<?> comparator) {
/*  660 */     if (this.comparison != 0) {
/*  661 */       return this;
/*      */     }
/*  663 */     if (lhs == rhs) {
/*  664 */       return this;
/*      */     }
/*  666 */     if (lhs == null) {
/*  667 */       this.comparison = -1;
/*  668 */       return this;
/*      */     } 
/*  670 */     if (rhs == null) {
/*  671 */       this.comparison = 1;
/*  672 */       return this;
/*      */     } 
/*  674 */     if (lhs.length != rhs.length) {
/*  675 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  676 */       return this;
/*      */     } 
/*  678 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  679 */       append(lhs[i], rhs[i], comparator);
/*      */     }
/*  681 */     return this;
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
/*      */   public CompareToBuilder append(long[] lhs, long[] rhs) {
/*  700 */     if (this.comparison != 0) {
/*  701 */       return this;
/*      */     }
/*  703 */     if (lhs == rhs) {
/*  704 */       return this;
/*      */     }
/*  706 */     if (lhs == null) {
/*  707 */       this.comparison = -1;
/*  708 */       return this;
/*      */     } 
/*  710 */     if (rhs == null) {
/*  711 */       this.comparison = 1;
/*  712 */       return this;
/*      */     } 
/*  714 */     if (lhs.length != rhs.length) {
/*  715 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  716 */       return this;
/*      */     } 
/*  718 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  719 */       append(lhs[i], rhs[i]);
/*      */     }
/*  721 */     return this;
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
/*      */   public CompareToBuilder append(int[] lhs, int[] rhs) {
/*  740 */     if (this.comparison != 0) {
/*  741 */       return this;
/*      */     }
/*  743 */     if (lhs == rhs) {
/*  744 */       return this;
/*      */     }
/*  746 */     if (lhs == null) {
/*  747 */       this.comparison = -1;
/*  748 */       return this;
/*      */     } 
/*  750 */     if (rhs == null) {
/*  751 */       this.comparison = 1;
/*  752 */       return this;
/*      */     } 
/*  754 */     if (lhs.length != rhs.length) {
/*  755 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  756 */       return this;
/*      */     } 
/*  758 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  759 */       append(lhs[i], rhs[i]);
/*      */     }
/*  761 */     return this;
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
/*      */   public CompareToBuilder append(short[] lhs, short[] rhs) {
/*  780 */     if (this.comparison != 0) {
/*  781 */       return this;
/*      */     }
/*  783 */     if (lhs == rhs) {
/*  784 */       return this;
/*      */     }
/*  786 */     if (lhs == null) {
/*  787 */       this.comparison = -1;
/*  788 */       return this;
/*      */     } 
/*  790 */     if (rhs == null) {
/*  791 */       this.comparison = 1;
/*  792 */       return this;
/*      */     } 
/*  794 */     if (lhs.length != rhs.length) {
/*  795 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  796 */       return this;
/*      */     } 
/*  798 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  799 */       append(lhs[i], rhs[i]);
/*      */     }
/*  801 */     return this;
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
/*      */   public CompareToBuilder append(char[] lhs, char[] rhs) {
/*  820 */     if (this.comparison != 0) {
/*  821 */       return this;
/*      */     }
/*  823 */     if (lhs == rhs) {
/*  824 */       return this;
/*      */     }
/*  826 */     if (lhs == null) {
/*  827 */       this.comparison = -1;
/*  828 */       return this;
/*      */     } 
/*  830 */     if (rhs == null) {
/*  831 */       this.comparison = 1;
/*  832 */       return this;
/*      */     } 
/*  834 */     if (lhs.length != rhs.length) {
/*  835 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  836 */       return this;
/*      */     } 
/*  838 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  839 */       append(lhs[i], rhs[i]);
/*      */     }
/*  841 */     return this;
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
/*      */   public CompareToBuilder append(byte[] lhs, byte[] rhs) {
/*  860 */     if (this.comparison != 0) {
/*  861 */       return this;
/*      */     }
/*  863 */     if (lhs == rhs) {
/*  864 */       return this;
/*      */     }
/*  866 */     if (lhs == null) {
/*  867 */       this.comparison = -1;
/*  868 */       return this;
/*      */     } 
/*  870 */     if (rhs == null) {
/*  871 */       this.comparison = 1;
/*  872 */       return this;
/*      */     } 
/*  874 */     if (lhs.length != rhs.length) {
/*  875 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  876 */       return this;
/*      */     } 
/*  878 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  879 */       append(lhs[i], rhs[i]);
/*      */     }
/*  881 */     return this;
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
/*      */   public CompareToBuilder append(double[] lhs, double[] rhs) {
/*  900 */     if (this.comparison != 0) {
/*  901 */       return this;
/*      */     }
/*  903 */     if (lhs == rhs) {
/*  904 */       return this;
/*      */     }
/*  906 */     if (lhs == null) {
/*  907 */       this.comparison = -1;
/*  908 */       return this;
/*      */     } 
/*  910 */     if (rhs == null) {
/*  911 */       this.comparison = 1;
/*  912 */       return this;
/*      */     } 
/*  914 */     if (lhs.length != rhs.length) {
/*  915 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  916 */       return this;
/*      */     } 
/*  918 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  919 */       append(lhs[i], rhs[i]);
/*      */     }
/*  921 */     return this;
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
/*      */   public CompareToBuilder append(float[] lhs, float[] rhs) {
/*  940 */     if (this.comparison != 0) {
/*  941 */       return this;
/*      */     }
/*  943 */     if (lhs == rhs) {
/*  944 */       return this;
/*      */     }
/*  946 */     if (lhs == null) {
/*  947 */       this.comparison = -1;
/*  948 */       return this;
/*      */     } 
/*  950 */     if (rhs == null) {
/*  951 */       this.comparison = 1;
/*  952 */       return this;
/*      */     } 
/*  954 */     if (lhs.length != rhs.length) {
/*  955 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  956 */       return this;
/*      */     } 
/*  958 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  959 */       append(lhs[i], rhs[i]);
/*      */     }
/*  961 */     return this;
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
/*      */   public CompareToBuilder append(boolean[] lhs, boolean[] rhs) {
/*  980 */     if (this.comparison != 0) {
/*  981 */       return this;
/*      */     }
/*  983 */     if (lhs == rhs) {
/*  984 */       return this;
/*      */     }
/*  986 */     if (lhs == null) {
/*  987 */       this.comparison = -1;
/*  988 */       return this;
/*      */     } 
/*  990 */     if (rhs == null) {
/*  991 */       this.comparison = 1;
/*  992 */       return this;
/*      */     } 
/*  994 */     if (lhs.length != rhs.length) {
/*  995 */       this.comparison = (lhs.length < rhs.length) ? -1 : 1;
/*  996 */       return this;
/*      */     } 
/*  998 */     for (int i = 0; i < lhs.length && this.comparison == 0; i++) {
/*  999 */       append(lhs[i], rhs[i]);
/*      */     }
/* 1001 */     return this;
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
/*      */   public int toComparison() {
/* 1015 */     return this.comparison;
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
/*      */   public Integer build() {
/* 1030 */     return Integer.valueOf(toComparison());
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/builder/CompareToBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */