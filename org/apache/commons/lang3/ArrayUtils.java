/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.Arrays;
/*      */ import java.util.BitSet;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import org.apache.commons.lang3.builder.EqualsBuilder;
/*      */ import org.apache.commons.lang3.builder.HashCodeBuilder;
/*      */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*      */ import org.apache.commons.lang3.builder.ToStringStyle;
/*      */ import org.apache.commons.lang3.math.NumberUtils;
/*      */ import org.apache.commons.lang3.mutable.MutableInt;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ArrayUtils
/*      */ {
/*   51 */   public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*      */ 
/*      */ 
/*      */   
/*   55 */   public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
/*      */ 
/*      */ 
/*      */   
/*   59 */   public static final String[] EMPTY_STRING_ARRAY = new String[0];
/*      */ 
/*      */ 
/*      */   
/*   63 */   public static final long[] EMPTY_LONG_ARRAY = new long[0];
/*      */ 
/*      */ 
/*      */   
/*   67 */   public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
/*      */ 
/*      */ 
/*      */   
/*   71 */   public static final int[] EMPTY_INT_ARRAY = new int[0];
/*      */ 
/*      */ 
/*      */   
/*   75 */   public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
/*      */ 
/*      */ 
/*      */   
/*   79 */   public static final short[] EMPTY_SHORT_ARRAY = new short[0];
/*      */ 
/*      */ 
/*      */   
/*   83 */   public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
/*      */ 
/*      */ 
/*      */   
/*   87 */   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*      */ 
/*      */ 
/*      */   
/*   91 */   public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
/*      */ 
/*      */ 
/*      */   
/*   95 */   public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
/*      */ 
/*      */ 
/*      */   
/*   99 */   public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
/*      */ 
/*      */ 
/*      */   
/*  103 */   public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
/*      */ 
/*      */ 
/*      */   
/*  107 */   public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
/*      */ 
/*      */ 
/*      */   
/*  111 */   public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
/*      */ 
/*      */ 
/*      */   
/*  115 */   public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
/*      */ 
/*      */ 
/*      */   
/*  119 */   public static final char[] EMPTY_CHAR_ARRAY = new char[0];
/*      */ 
/*      */ 
/*      */   
/*  123 */   public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INDEX_NOT_FOUND = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(Object array) {
/*  161 */     return toString(array, "{}");
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
/*      */   public static String toString(Object array, String stringIfNull) {
/*  177 */     if (array == null) {
/*  178 */       return stringIfNull;
/*      */     }
/*  180 */     return (new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE)).append(array).toString();
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
/*      */   public static int hashCode(Object array) {
/*  192 */     return (new HashCodeBuilder()).append(array).toHashCode();
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
/*      */   @Deprecated
/*      */   public static boolean isEquals(Object array1, Object array2) {
/*  209 */     return (new EqualsBuilder()).append(array1, array2).isEquals();
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
/*      */   public static Map<Object, Object> toMap(Object[] array) {
/*  240 */     if (array == null) {
/*  241 */       return null;
/*      */     }
/*  243 */     Map<Object, Object> map = new HashMap<>((int)(array.length * 1.5D));
/*  244 */     for (int i = 0; i < array.length; i++) {
/*  245 */       Object object = array[i];
/*  246 */       if (object instanceof Map.Entry) {
/*  247 */         Map.Entry<?, ?> entry = (Map.Entry<?, ?>)object;
/*  248 */         map.put(entry.getKey(), entry.getValue());
/*  249 */       } else if (object instanceof Object[]) {
/*  250 */         Object[] entry = (Object[])object;
/*  251 */         if (entry.length < 2) {
/*  252 */           throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2");
/*      */         }
/*      */ 
/*      */         
/*  256 */         map.put(entry[0], entry[1]);
/*      */       } else {
/*  258 */         throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  263 */     return map;
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
/*      */   
/*      */   public static <T> T[] toArray(T... items) {
/*  306 */     return items;
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
/*      */   public static <T> T[] clone(T[] array) {
/*  325 */     if (array == null) {
/*  326 */       return null;
/*      */     }
/*  328 */     return (T[])array.clone();
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
/*      */   public static long[] clone(long[] array) {
/*  341 */     if (array == null) {
/*  342 */       return null;
/*      */     }
/*  344 */     return (long[])array.clone();
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
/*      */   public static int[] clone(int[] array) {
/*  357 */     if (array == null) {
/*  358 */       return null;
/*      */     }
/*  360 */     return (int[])array.clone();
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
/*      */   public static short[] clone(short[] array) {
/*  373 */     if (array == null) {
/*  374 */       return null;
/*      */     }
/*  376 */     return (short[])array.clone();
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
/*      */   public static char[] clone(char[] array) {
/*  389 */     if (array == null) {
/*  390 */       return null;
/*      */     }
/*  392 */     return (char[])array.clone();
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
/*      */   public static byte[] clone(byte[] array) {
/*  405 */     if (array == null) {
/*  406 */       return null;
/*      */     }
/*  408 */     return (byte[])array.clone();
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
/*      */   public static double[] clone(double[] array) {
/*  421 */     if (array == null) {
/*  422 */       return null;
/*      */     }
/*  424 */     return (double[])array.clone();
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
/*      */   public static float[] clone(float[] array) {
/*  437 */     if (array == null) {
/*  438 */       return null;
/*      */     }
/*  440 */     return (float[])array.clone();
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
/*      */   public static boolean[] clone(boolean[] array) {
/*  453 */     if (array == null) {
/*  454 */       return null;
/*      */     }
/*  456 */     return (boolean[])array.clone();
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
/*      */   public static <T> T[] nullToEmpty(T[] array, Class<T[]> type) {
/*  475 */     if (type == null) {
/*  476 */       throw new IllegalArgumentException("The type must not be null");
/*      */     }
/*      */     
/*  479 */     if (array == null) {
/*  480 */       return type.cast(Array.newInstance(type.getComponentType(), 0));
/*      */     }
/*  482 */     return array;
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
/*      */   public static Object[] nullToEmpty(Object[] array) {
/*  500 */     if (isEmpty(array)) {
/*  501 */       return EMPTY_OBJECT_ARRAY;
/*      */     }
/*  503 */     return array;
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
/*      */   public static Class<?>[] nullToEmpty(Class<?>[] array) {
/*  520 */     if (isEmpty((Object[])array)) {
/*  521 */       return EMPTY_CLASS_ARRAY;
/*      */     }
/*  523 */     return array;
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
/*      */   public static String[] nullToEmpty(String[] array) {
/*  540 */     if (isEmpty((Object[])array)) {
/*  541 */       return EMPTY_STRING_ARRAY;
/*      */     }
/*  543 */     return array;
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
/*      */   public static long[] nullToEmpty(long[] array) {
/*  560 */     if (isEmpty(array)) {
/*  561 */       return EMPTY_LONG_ARRAY;
/*      */     }
/*  563 */     return array;
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
/*      */   public static int[] nullToEmpty(int[] array) {
/*  580 */     if (isEmpty(array)) {
/*  581 */       return EMPTY_INT_ARRAY;
/*      */     }
/*  583 */     return array;
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
/*      */   public static short[] nullToEmpty(short[] array) {
/*  600 */     if (isEmpty(array)) {
/*  601 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/*  603 */     return array;
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
/*      */   public static char[] nullToEmpty(char[] array) {
/*  620 */     if (isEmpty(array)) {
/*  621 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/*  623 */     return array;
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
/*      */   public static byte[] nullToEmpty(byte[] array) {
/*  640 */     if (isEmpty(array)) {
/*  641 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/*  643 */     return array;
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
/*      */   public static double[] nullToEmpty(double[] array) {
/*  660 */     if (isEmpty(array)) {
/*  661 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/*  663 */     return array;
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
/*      */   public static float[] nullToEmpty(float[] array) {
/*  680 */     if (isEmpty(array)) {
/*  681 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/*  683 */     return array;
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
/*      */   public static boolean[] nullToEmpty(boolean[] array) {
/*  700 */     if (isEmpty(array)) {
/*  701 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/*  703 */     return array;
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
/*      */   public static Long[] nullToEmpty(Long[] array) {
/*  720 */     if (isEmpty((Object[])array)) {
/*  721 */       return EMPTY_LONG_OBJECT_ARRAY;
/*      */     }
/*  723 */     return array;
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
/*      */   public static Integer[] nullToEmpty(Integer[] array) {
/*  740 */     if (isEmpty((Object[])array)) {
/*  741 */       return EMPTY_INTEGER_OBJECT_ARRAY;
/*      */     }
/*  743 */     return array;
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
/*      */   public static Short[] nullToEmpty(Short[] array) {
/*  760 */     if (isEmpty((Object[])array)) {
/*  761 */       return EMPTY_SHORT_OBJECT_ARRAY;
/*      */     }
/*  763 */     return array;
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
/*      */   public static Character[] nullToEmpty(Character[] array) {
/*  780 */     if (isEmpty((Object[])array)) {
/*  781 */       return EMPTY_CHARACTER_OBJECT_ARRAY;
/*      */     }
/*  783 */     return array;
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
/*      */   public static Byte[] nullToEmpty(Byte[] array) {
/*  800 */     if (isEmpty((Object[])array)) {
/*  801 */       return EMPTY_BYTE_OBJECT_ARRAY;
/*      */     }
/*  803 */     return array;
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
/*      */   public static Double[] nullToEmpty(Double[] array) {
/*  820 */     if (isEmpty((Object[])array)) {
/*  821 */       return EMPTY_DOUBLE_OBJECT_ARRAY;
/*      */     }
/*  823 */     return array;
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
/*      */   public static Float[] nullToEmpty(Float[] array) {
/*  840 */     if (isEmpty((Object[])array)) {
/*  841 */       return EMPTY_FLOAT_OBJECT_ARRAY;
/*      */     }
/*  843 */     return array;
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
/*      */   public static Boolean[] nullToEmpty(Boolean[] array) {
/*  860 */     if (isEmpty((Object[])array)) {
/*  861 */       return EMPTY_BOOLEAN_OBJECT_ARRAY;
/*      */     }
/*  863 */     return array;
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
/*      */   public static <T> T[] subarray(T[] array, int startIndexInclusive, int endIndexExclusive) {
/*  898 */     if (array == null) {
/*  899 */       return null;
/*      */     }
/*  901 */     if (startIndexInclusive < 0) {
/*  902 */       startIndexInclusive = 0;
/*      */     }
/*  904 */     if (endIndexExclusive > array.length) {
/*  905 */       endIndexExclusive = array.length;
/*      */     }
/*  907 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  908 */     Class<?> type = array.getClass().getComponentType();
/*  909 */     if (newSize <= 0) {
/*      */       
/*  911 */       T[] emptyArray = (T[])Array.newInstance(type, 0);
/*  912 */       return emptyArray;
/*      */     } 
/*      */ 
/*      */     
/*  916 */     T[] subarray = (T[])Array.newInstance(type, newSize);
/*  917 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  918 */     return subarray;
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
/*      */   public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive) {
/*  942 */     if (array == null) {
/*  943 */       return null;
/*      */     }
/*  945 */     if (startIndexInclusive < 0) {
/*  946 */       startIndexInclusive = 0;
/*      */     }
/*  948 */     if (endIndexExclusive > array.length) {
/*  949 */       endIndexExclusive = array.length;
/*      */     }
/*  951 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  952 */     if (newSize <= 0) {
/*  953 */       return EMPTY_LONG_ARRAY;
/*      */     }
/*      */     
/*  956 */     long[] subarray = new long[newSize];
/*  957 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  958 */     return subarray;
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
/*      */   public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive) {
/*  982 */     if (array == null) {
/*  983 */       return null;
/*      */     }
/*  985 */     if (startIndexInclusive < 0) {
/*  986 */       startIndexInclusive = 0;
/*      */     }
/*  988 */     if (endIndexExclusive > array.length) {
/*  989 */       endIndexExclusive = array.length;
/*      */     }
/*  991 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  992 */     if (newSize <= 0) {
/*  993 */       return EMPTY_INT_ARRAY;
/*      */     }
/*      */     
/*  996 */     int[] subarray = new int[newSize];
/*  997 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  998 */     return subarray;
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
/*      */   public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1022 */     if (array == null) {
/* 1023 */       return null;
/*      */     }
/* 1025 */     if (startIndexInclusive < 0) {
/* 1026 */       startIndexInclusive = 0;
/*      */     }
/* 1028 */     if (endIndexExclusive > array.length) {
/* 1029 */       endIndexExclusive = array.length;
/*      */     }
/* 1031 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1032 */     if (newSize <= 0) {
/* 1033 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/*      */     
/* 1036 */     short[] subarray = new short[newSize];
/* 1037 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1038 */     return subarray;
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
/*      */   public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1062 */     if (array == null) {
/* 1063 */       return null;
/*      */     }
/* 1065 */     if (startIndexInclusive < 0) {
/* 1066 */       startIndexInclusive = 0;
/*      */     }
/* 1068 */     if (endIndexExclusive > array.length) {
/* 1069 */       endIndexExclusive = array.length;
/*      */     }
/* 1071 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1072 */     if (newSize <= 0) {
/* 1073 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/*      */     
/* 1076 */     char[] subarray = new char[newSize];
/* 1077 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1078 */     return subarray;
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
/*      */   public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1102 */     if (array == null) {
/* 1103 */       return null;
/*      */     }
/* 1105 */     if (startIndexInclusive < 0) {
/* 1106 */       startIndexInclusive = 0;
/*      */     }
/* 1108 */     if (endIndexExclusive > array.length) {
/* 1109 */       endIndexExclusive = array.length;
/*      */     }
/* 1111 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1112 */     if (newSize <= 0) {
/* 1113 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/*      */     
/* 1116 */     byte[] subarray = new byte[newSize];
/* 1117 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1118 */     return subarray;
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
/*      */   public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1142 */     if (array == null) {
/* 1143 */       return null;
/*      */     }
/* 1145 */     if (startIndexInclusive < 0) {
/* 1146 */       startIndexInclusive = 0;
/*      */     }
/* 1148 */     if (endIndexExclusive > array.length) {
/* 1149 */       endIndexExclusive = array.length;
/*      */     }
/* 1151 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1152 */     if (newSize <= 0) {
/* 1153 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/*      */     
/* 1156 */     double[] subarray = new double[newSize];
/* 1157 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1158 */     return subarray;
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
/*      */   public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1182 */     if (array == null) {
/* 1183 */       return null;
/*      */     }
/* 1185 */     if (startIndexInclusive < 0) {
/* 1186 */       startIndexInclusive = 0;
/*      */     }
/* 1188 */     if (endIndexExclusive > array.length) {
/* 1189 */       endIndexExclusive = array.length;
/*      */     }
/* 1191 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1192 */     if (newSize <= 0) {
/* 1193 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/*      */     
/* 1196 */     float[] subarray = new float[newSize];
/* 1197 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1198 */     return subarray;
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
/*      */   public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1222 */     if (array == null) {
/* 1223 */       return null;
/*      */     }
/* 1225 */     if (startIndexInclusive < 0) {
/* 1226 */       startIndexInclusive = 0;
/*      */     }
/* 1228 */     if (endIndexExclusive > array.length) {
/* 1229 */       endIndexExclusive = array.length;
/*      */     }
/* 1231 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1232 */     if (newSize <= 0) {
/* 1233 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/*      */     
/* 1236 */     boolean[] subarray = new boolean[newSize];
/* 1237 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1238 */     return subarray;
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
/*      */   public static boolean isSameLength(Object[] array1, Object[] array2) {
/* 1255 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(long[] array1, long[] array2) {
/* 1268 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(int[] array1, int[] array2) {
/* 1281 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(short[] array1, short[] array2) {
/* 1294 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(char[] array1, char[] array2) {
/* 1307 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(byte[] array1, byte[] array2) {
/* 1320 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(double[] array1, double[] array2) {
/* 1333 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(float[] array1, float[] array2) {
/* 1346 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static boolean isSameLength(boolean[] array1, boolean[] array2) {
/* 1359 */     return (getLength(array1) == getLength(array2));
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
/*      */   public static int getLength(Object array) {
/* 1384 */     if (array == null) {
/* 1385 */       return 0;
/*      */     }
/* 1387 */     return Array.getLength(array);
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
/*      */   public static boolean isSameType(Object array1, Object array2) {
/* 1400 */     if (array1 == null || array2 == null) {
/* 1401 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1403 */     return array1.getClass().getName().equals(array2.getClass().getName());
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
/*      */   public static void reverse(Object[] array) {
/* 1418 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1421 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(long[] array) {
/* 1432 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1435 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(int[] array) {
/* 1446 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1449 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(short[] array) {
/* 1460 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1463 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(char[] array) {
/* 1474 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1477 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(byte[] array) {
/* 1488 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1491 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(double[] array) {
/* 1502 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1505 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(float[] array) {
/* 1516 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1519 */     reverse(array, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(boolean[] array) {
/* 1530 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1533 */     reverse(array, 0, array.length);
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
/*      */   public static void reverse(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1554 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1557 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1558 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1560 */     while (j > i) {
/* 1561 */       boolean tmp = array[j];
/* 1562 */       array[j] = array[i];
/* 1563 */       array[i] = tmp;
/* 1564 */       j--;
/* 1565 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(byte[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1587 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1590 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1591 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1593 */     while (j > i) {
/* 1594 */       byte tmp = array[j];
/* 1595 */       array[j] = array[i];
/* 1596 */       array[i] = tmp;
/* 1597 */       j--;
/* 1598 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(char[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1620 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1623 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1624 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1626 */     while (j > i) {
/* 1627 */       char tmp = array[j];
/* 1628 */       array[j] = array[i];
/* 1629 */       array[i] = tmp;
/* 1630 */       j--;
/* 1631 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(double[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1653 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1656 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1657 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1659 */     while (j > i) {
/* 1660 */       double tmp = array[j];
/* 1661 */       array[j] = array[i];
/* 1662 */       array[i] = tmp;
/* 1663 */       j--;
/* 1664 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(float[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1686 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1689 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1690 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1692 */     while (j > i) {
/* 1693 */       float tmp = array[j];
/* 1694 */       array[j] = array[i];
/* 1695 */       array[i] = tmp;
/* 1696 */       j--;
/* 1697 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(int[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1719 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1722 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1723 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1725 */     while (j > i) {
/* 1726 */       int tmp = array[j];
/* 1727 */       array[j] = array[i];
/* 1728 */       array[i] = tmp;
/* 1729 */       j--;
/* 1730 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(long[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1752 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1755 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1756 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1758 */     while (j > i) {
/* 1759 */       long tmp = array[j];
/* 1760 */       array[j] = array[i];
/* 1761 */       array[i] = tmp;
/* 1762 */       j--;
/* 1763 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(Object[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1785 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1788 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1789 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1791 */     while (j > i) {
/* 1792 */       Object tmp = array[j];
/* 1793 */       array[j] = array[i];
/* 1794 */       array[i] = tmp;
/* 1795 */       j--;
/* 1796 */       i++;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reverse(short[] array, int startIndexInclusive, int endIndexExclusive) {
/* 1818 */     if (array == null) {
/*      */       return;
/*      */     }
/* 1821 */     int i = (startIndexInclusive < 0) ? 0 : startIndexInclusive;
/* 1822 */     int j = Math.min(array.length, endIndexExclusive) - 1;
/*      */     
/* 1824 */     while (j > i) {
/* 1825 */       short tmp = array[j];
/* 1826 */       array[j] = array[i];
/* 1827 */       array[i] = tmp;
/* 1828 */       j--;
/* 1829 */       i++;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(Object[] array, int offset1, int offset2) {
/* 1857 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 1860 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(long[] array, int offset1, int offset2) {
/* 1886 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 1889 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(int[] array, int offset1, int offset2) {
/* 1914 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 1917 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(short[] array, int offset1, int offset2) {
/* 1942 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 1945 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(char[] array, int offset1, int offset2) {
/* 1970 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 1973 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(byte[] array, int offset1, int offset2) {
/* 1998 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 2001 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(double[] array, int offset1, int offset2) {
/* 2026 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 2029 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(float[] array, int offset1, int offset2) {
/* 2054 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 2057 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(boolean[] array, int offset1, int offset2) {
/* 2082 */     if (array == null || array.length == 0) {
/*      */       return;
/*      */     }
/* 2085 */     swap(array, offset1, offset2, 1);
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
/*      */   public static void swap(boolean[] array, int offset1, int offset2, int len) {
/* 2113 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2116 */     if (offset1 < 0) {
/* 2117 */       offset1 = 0;
/*      */     }
/* 2119 */     if (offset2 < 0) {
/* 2120 */       offset2 = 0;
/*      */     }
/* 2122 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2123 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2124 */       boolean aux = array[offset1];
/* 2125 */       array[offset1] = array[offset2];
/* 2126 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(byte[] array, int offset1, int offset2, int len) {
/* 2155 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2158 */     if (offset1 < 0) {
/* 2159 */       offset1 = 0;
/*      */     }
/* 2161 */     if (offset2 < 0) {
/* 2162 */       offset2 = 0;
/*      */     }
/* 2164 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2165 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2166 */       byte aux = array[offset1];
/* 2167 */       array[offset1] = array[offset2];
/* 2168 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(char[] array, int offset1, int offset2, int len) {
/* 2197 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2200 */     if (offset1 < 0) {
/* 2201 */       offset1 = 0;
/*      */     }
/* 2203 */     if (offset2 < 0) {
/* 2204 */       offset2 = 0;
/*      */     }
/* 2206 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2207 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2208 */       char aux = array[offset1];
/* 2209 */       array[offset1] = array[offset2];
/* 2210 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(double[] array, int offset1, int offset2, int len) {
/* 2239 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2242 */     if (offset1 < 0) {
/* 2243 */       offset1 = 0;
/*      */     }
/* 2245 */     if (offset2 < 0) {
/* 2246 */       offset2 = 0;
/*      */     }
/* 2248 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2249 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2250 */       double aux = array[offset1];
/* 2251 */       array[offset1] = array[offset2];
/* 2252 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(float[] array, int offset1, int offset2, int len) {
/* 2281 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2284 */     if (offset1 < 0) {
/* 2285 */       offset1 = 0;
/*      */     }
/* 2287 */     if (offset2 < 0) {
/* 2288 */       offset2 = 0;
/*      */     }
/* 2290 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2291 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2292 */       float aux = array[offset1];
/* 2293 */       array[offset1] = array[offset2];
/* 2294 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(int[] array, int offset1, int offset2, int len) {
/* 2324 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2327 */     if (offset1 < 0) {
/* 2328 */       offset1 = 0;
/*      */     }
/* 2330 */     if (offset2 < 0) {
/* 2331 */       offset2 = 0;
/*      */     }
/* 2333 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2334 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2335 */       int aux = array[offset1];
/* 2336 */       array[offset1] = array[offset2];
/* 2337 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(long[] array, int offset1, int offset2, int len) {
/* 2366 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2369 */     if (offset1 < 0) {
/* 2370 */       offset1 = 0;
/*      */     }
/* 2372 */     if (offset2 < 0) {
/* 2373 */       offset2 = 0;
/*      */     }
/* 2375 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2376 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2377 */       long aux = array[offset1];
/* 2378 */       array[offset1] = array[offset2];
/* 2379 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(Object[] array, int offset1, int offset2, int len) {
/* 2408 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2411 */     if (offset1 < 0) {
/* 2412 */       offset1 = 0;
/*      */     }
/* 2414 */     if (offset2 < 0) {
/* 2415 */       offset2 = 0;
/*      */     }
/* 2417 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2418 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2419 */       Object aux = array[offset1];
/* 2420 */       array[offset1] = array[offset2];
/* 2421 */       array[offset2] = aux;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void swap(short[] array, int offset1, int offset2, int len) {
/* 2450 */     if (array == null || array.length == 0 || offset1 >= array.length || offset2 >= array.length) {
/*      */       return;
/*      */     }
/* 2453 */     if (offset1 < 0) {
/* 2454 */       offset1 = 0;
/*      */     }
/* 2456 */     if (offset2 < 0) {
/* 2457 */       offset2 = 0;
/*      */     }
/* 2459 */     if (offset1 == offset2) {
/*      */       return;
/*      */     }
/* 2462 */     len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);
/* 2463 */     for (int i = 0; i < len; i++, offset1++, offset2++) {
/* 2464 */       short aux = array[offset1];
/* 2465 */       array[offset1] = array[offset2];
/* 2466 */       array[offset2] = aux;
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
/*      */   public static void shift(Object[] array, int offset) {
/* 2485 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2488 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(long[] array, int offset) {
/* 2504 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2507 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(int[] array, int offset) {
/* 2523 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2526 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(short[] array, int offset) {
/* 2542 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2545 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(char[] array, int offset) {
/* 2561 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2564 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(byte[] array, int offset) {
/* 2580 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2583 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(double[] array, int offset) {
/* 2599 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2602 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(float[] array, int offset) {
/* 2618 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2621 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(boolean[] array, int offset) {
/* 2637 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2640 */     shift(array, 0, array.length, offset);
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
/*      */   public static void shift(boolean[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 2663 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2666 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 2669 */     if (startIndexInclusive < 0) {
/* 2670 */       startIndexInclusive = 0;
/*      */     }
/* 2672 */     if (endIndexExclusive >= array.length) {
/* 2673 */       endIndexExclusive = array.length;
/*      */     }
/* 2675 */     int n = endIndexExclusive - startIndexInclusive;
/* 2676 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 2679 */     offset %= n;
/* 2680 */     if (offset < 0) {
/* 2681 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 2685 */     while (n > 1 && offset > 0) {
/* 2686 */       int n_offset = n - offset;
/*      */       
/* 2688 */       if (offset > n_offset) {
/* 2689 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 2690 */         n = offset;
/* 2691 */         offset -= n_offset; continue;
/* 2692 */       }  if (offset < n_offset) {
/* 2693 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 2694 */         startIndexInclusive += offset;
/* 2695 */         n = n_offset; continue;
/*      */       } 
/* 2697 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(byte[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 2723 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2726 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 2729 */     if (startIndexInclusive < 0) {
/* 2730 */       startIndexInclusive = 0;
/*      */     }
/* 2732 */     if (endIndexExclusive >= array.length) {
/* 2733 */       endIndexExclusive = array.length;
/*      */     }
/* 2735 */     int n = endIndexExclusive - startIndexInclusive;
/* 2736 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 2739 */     offset %= n;
/* 2740 */     if (offset < 0) {
/* 2741 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 2745 */     while (n > 1 && offset > 0) {
/* 2746 */       int n_offset = n - offset;
/*      */       
/* 2748 */       if (offset > n_offset) {
/* 2749 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 2750 */         n = offset;
/* 2751 */         offset -= n_offset; continue;
/* 2752 */       }  if (offset < n_offset) {
/* 2753 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 2754 */         startIndexInclusive += offset;
/* 2755 */         n = n_offset; continue;
/*      */       } 
/* 2757 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(char[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 2783 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2786 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 2789 */     if (startIndexInclusive < 0) {
/* 2790 */       startIndexInclusive = 0;
/*      */     }
/* 2792 */     if (endIndexExclusive >= array.length) {
/* 2793 */       endIndexExclusive = array.length;
/*      */     }
/* 2795 */     int n = endIndexExclusive - startIndexInclusive;
/* 2796 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 2799 */     offset %= n;
/* 2800 */     if (offset < 0) {
/* 2801 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 2805 */     while (n > 1 && offset > 0) {
/* 2806 */       int n_offset = n - offset;
/*      */       
/* 2808 */       if (offset > n_offset) {
/* 2809 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 2810 */         n = offset;
/* 2811 */         offset -= n_offset; continue;
/* 2812 */       }  if (offset < n_offset) {
/* 2813 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 2814 */         startIndexInclusive += offset;
/* 2815 */         n = n_offset; continue;
/*      */       } 
/* 2817 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(double[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 2843 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2846 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 2849 */     if (startIndexInclusive < 0) {
/* 2850 */       startIndexInclusive = 0;
/*      */     }
/* 2852 */     if (endIndexExclusive >= array.length) {
/* 2853 */       endIndexExclusive = array.length;
/*      */     }
/* 2855 */     int n = endIndexExclusive - startIndexInclusive;
/* 2856 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 2859 */     offset %= n;
/* 2860 */     if (offset < 0) {
/* 2861 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 2865 */     while (n > 1 && offset > 0) {
/* 2866 */       int n_offset = n - offset;
/*      */       
/* 2868 */       if (offset > n_offset) {
/* 2869 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 2870 */         n = offset;
/* 2871 */         offset -= n_offset; continue;
/* 2872 */       }  if (offset < n_offset) {
/* 2873 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 2874 */         startIndexInclusive += offset;
/* 2875 */         n = n_offset; continue;
/*      */       } 
/* 2877 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(float[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 2903 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2906 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 2909 */     if (startIndexInclusive < 0) {
/* 2910 */       startIndexInclusive = 0;
/*      */     }
/* 2912 */     if (endIndexExclusive >= array.length) {
/* 2913 */       endIndexExclusive = array.length;
/*      */     }
/* 2915 */     int n = endIndexExclusive - startIndexInclusive;
/* 2916 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 2919 */     offset %= n;
/* 2920 */     if (offset < 0) {
/* 2921 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 2925 */     while (n > 1 && offset > 0) {
/* 2926 */       int n_offset = n - offset;
/*      */       
/* 2928 */       if (offset > n_offset) {
/* 2929 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 2930 */         n = offset;
/* 2931 */         offset -= n_offset; continue;
/* 2932 */       }  if (offset < n_offset) {
/* 2933 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 2934 */         startIndexInclusive += offset;
/* 2935 */         n = n_offset; continue;
/*      */       } 
/* 2937 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(int[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 2963 */     if (array == null) {
/*      */       return;
/*      */     }
/* 2966 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 2969 */     if (startIndexInclusive < 0) {
/* 2970 */       startIndexInclusive = 0;
/*      */     }
/* 2972 */     if (endIndexExclusive >= array.length) {
/* 2973 */       endIndexExclusive = array.length;
/*      */     }
/* 2975 */     int n = endIndexExclusive - startIndexInclusive;
/* 2976 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 2979 */     offset %= n;
/* 2980 */     if (offset < 0) {
/* 2981 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 2985 */     while (n > 1 && offset > 0) {
/* 2986 */       int n_offset = n - offset;
/*      */       
/* 2988 */       if (offset > n_offset) {
/* 2989 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 2990 */         n = offset;
/* 2991 */         offset -= n_offset; continue;
/* 2992 */       }  if (offset < n_offset) {
/* 2993 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 2994 */         startIndexInclusive += offset;
/* 2995 */         n = n_offset; continue;
/*      */       } 
/* 2997 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(long[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 3023 */     if (array == null) {
/*      */       return;
/*      */     }
/* 3026 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 3029 */     if (startIndexInclusive < 0) {
/* 3030 */       startIndexInclusive = 0;
/*      */     }
/* 3032 */     if (endIndexExclusive >= array.length) {
/* 3033 */       endIndexExclusive = array.length;
/*      */     }
/* 3035 */     int n = endIndexExclusive - startIndexInclusive;
/* 3036 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 3039 */     offset %= n;
/* 3040 */     if (offset < 0) {
/* 3041 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 3045 */     while (n > 1 && offset > 0) {
/* 3046 */       int n_offset = n - offset;
/*      */       
/* 3048 */       if (offset > n_offset) {
/* 3049 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 3050 */         n = offset;
/* 3051 */         offset -= n_offset; continue;
/* 3052 */       }  if (offset < n_offset) {
/* 3053 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 3054 */         startIndexInclusive += offset;
/* 3055 */         n = n_offset; continue;
/*      */       } 
/* 3057 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(Object[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 3083 */     if (array == null) {
/*      */       return;
/*      */     }
/* 3086 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 3089 */     if (startIndexInclusive < 0) {
/* 3090 */       startIndexInclusive = 0;
/*      */     }
/* 3092 */     if (endIndexExclusive >= array.length) {
/* 3093 */       endIndexExclusive = array.length;
/*      */     }
/* 3095 */     int n = endIndexExclusive - startIndexInclusive;
/* 3096 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 3099 */     offset %= n;
/* 3100 */     if (offset < 0) {
/* 3101 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 3105 */     while (n > 1 && offset > 0) {
/* 3106 */       int n_offset = n - offset;
/*      */       
/* 3108 */       if (offset > n_offset) {
/* 3109 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 3110 */         n = offset;
/* 3111 */         offset -= n_offset; continue;
/* 3112 */       }  if (offset < n_offset) {
/* 3113 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 3114 */         startIndexInclusive += offset;
/* 3115 */         n = n_offset; continue;
/*      */       } 
/* 3117 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shift(short[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
/* 3143 */     if (array == null) {
/*      */       return;
/*      */     }
/* 3146 */     if (startIndexInclusive >= array.length - 1 || endIndexExclusive <= 0) {
/*      */       return;
/*      */     }
/* 3149 */     if (startIndexInclusive < 0) {
/* 3150 */       startIndexInclusive = 0;
/*      */     }
/* 3152 */     if (endIndexExclusive >= array.length) {
/* 3153 */       endIndexExclusive = array.length;
/*      */     }
/* 3155 */     int n = endIndexExclusive - startIndexInclusive;
/* 3156 */     if (n <= 1) {
/*      */       return;
/*      */     }
/* 3159 */     offset %= n;
/* 3160 */     if (offset < 0) {
/* 3161 */       offset += n;
/*      */     }
/*      */ 
/*      */     
/* 3165 */     while (n > 1 && offset > 0) {
/* 3166 */       int n_offset = n - offset;
/*      */       
/* 3168 */       if (offset > n_offset) {
/* 3169 */         swap(array, startIndexInclusive, startIndexInclusive + n - n_offset, n_offset);
/* 3170 */         n = offset;
/* 3171 */         offset -= n_offset; continue;
/* 3172 */       }  if (offset < n_offset) {
/* 3173 */         swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
/* 3174 */         startIndexInclusive += offset;
/* 3175 */         n = n_offset; continue;
/*      */       } 
/* 3177 */       swap(array, startIndexInclusive, startIndexInclusive + n_offset, offset);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOf(Object[] array, Object objectToFind) {
/* 3199 */     return indexOf(array, objectToFind, 0);
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
/*      */   public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
/* 3217 */     if (array == null) {
/* 3218 */       return -1;
/*      */     }
/* 3220 */     if (startIndex < 0) {
/* 3221 */       startIndex = 0;
/*      */     }
/* 3223 */     if (objectToFind == null) {
/* 3224 */       for (int i = startIndex; i < array.length; i++) {
/* 3225 */         if (array[i] == null) {
/* 3226 */           return i;
/*      */         }
/*      */       } 
/*      */     } else {
/* 3230 */       for (int i = startIndex; i < array.length; i++) {
/* 3231 */         if (objectToFind.equals(array[i])) {
/* 3232 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/* 3236 */     return -1;
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
/*      */   public static int lastIndexOf(Object[] array, Object objectToFind) {
/* 3250 */     return lastIndexOf(array, objectToFind, 2147483647);
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
/*      */   public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
/* 3268 */     if (array == null) {
/* 3269 */       return -1;
/*      */     }
/* 3271 */     if (startIndex < 0)
/* 3272 */       return -1; 
/* 3273 */     if (startIndex >= array.length) {
/* 3274 */       startIndex = array.length - 1;
/*      */     }
/* 3276 */     if (objectToFind == null) {
/* 3277 */       for (int i = startIndex; i >= 0; i--) {
/* 3278 */         if (array[i] == null) {
/* 3279 */           return i;
/*      */         }
/*      */       } 
/* 3282 */     } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 3283 */       for (int i = startIndex; i >= 0; i--) {
/* 3284 */         if (objectToFind.equals(array[i])) {
/* 3285 */           return i;
/*      */         }
/*      */       } 
/*      */     } 
/* 3289 */     return -1;
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
/*      */   public static boolean contains(Object[] array, Object objectToFind) {
/* 3302 */     return (indexOf(array, objectToFind) != -1);
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
/*      */   public static int indexOf(long[] array, long valueToFind) {
/* 3318 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(long[] array, long valueToFind, int startIndex) {
/* 3336 */     if (array == null) {
/* 3337 */       return -1;
/*      */     }
/* 3339 */     if (startIndex < 0) {
/* 3340 */       startIndex = 0;
/*      */     }
/* 3342 */     for (int i = startIndex; i < array.length; i++) {
/* 3343 */       if (valueToFind == array[i]) {
/* 3344 */         return i;
/*      */       }
/*      */     } 
/* 3347 */     return -1;
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
/*      */   public static int lastIndexOf(long[] array, long valueToFind) {
/* 3361 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
/* 3379 */     if (array == null) {
/* 3380 */       return -1;
/*      */     }
/* 3382 */     if (startIndex < 0)
/* 3383 */       return -1; 
/* 3384 */     if (startIndex >= array.length) {
/* 3385 */       startIndex = array.length - 1;
/*      */     }
/* 3387 */     for (int i = startIndex; i >= 0; i--) {
/* 3388 */       if (valueToFind == array[i]) {
/* 3389 */         return i;
/*      */       }
/*      */     } 
/* 3392 */     return -1;
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
/*      */   public static boolean contains(long[] array, long valueToFind) {
/* 3405 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static int indexOf(int[] array, int valueToFind) {
/* 3421 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(int[] array, int valueToFind, int startIndex) {
/* 3439 */     if (array == null) {
/* 3440 */       return -1;
/*      */     }
/* 3442 */     if (startIndex < 0) {
/* 3443 */       startIndex = 0;
/*      */     }
/* 3445 */     for (int i = startIndex; i < array.length; i++) {
/* 3446 */       if (valueToFind == array[i]) {
/* 3447 */         return i;
/*      */       }
/*      */     } 
/* 3450 */     return -1;
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
/*      */   public static int lastIndexOf(int[] array, int valueToFind) {
/* 3464 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
/* 3482 */     if (array == null) {
/* 3483 */       return -1;
/*      */     }
/* 3485 */     if (startIndex < 0)
/* 3486 */       return -1; 
/* 3487 */     if (startIndex >= array.length) {
/* 3488 */       startIndex = array.length - 1;
/*      */     }
/* 3490 */     for (int i = startIndex; i >= 0; i--) {
/* 3491 */       if (valueToFind == array[i]) {
/* 3492 */         return i;
/*      */       }
/*      */     } 
/* 3495 */     return -1;
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
/*      */   public static boolean contains(int[] array, int valueToFind) {
/* 3508 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static int indexOf(short[] array, short valueToFind) {
/* 3524 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(short[] array, short valueToFind, int startIndex) {
/* 3542 */     if (array == null) {
/* 3543 */       return -1;
/*      */     }
/* 3545 */     if (startIndex < 0) {
/* 3546 */       startIndex = 0;
/*      */     }
/* 3548 */     for (int i = startIndex; i < array.length; i++) {
/* 3549 */       if (valueToFind == array[i]) {
/* 3550 */         return i;
/*      */       }
/*      */     } 
/* 3553 */     return -1;
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
/*      */   public static int lastIndexOf(short[] array, short valueToFind) {
/* 3567 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
/* 3585 */     if (array == null) {
/* 3586 */       return -1;
/*      */     }
/* 3588 */     if (startIndex < 0)
/* 3589 */       return -1; 
/* 3590 */     if (startIndex >= array.length) {
/* 3591 */       startIndex = array.length - 1;
/*      */     }
/* 3593 */     for (int i = startIndex; i >= 0; i--) {
/* 3594 */       if (valueToFind == array[i]) {
/* 3595 */         return i;
/*      */       }
/*      */     } 
/* 3598 */     return -1;
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
/*      */   public static boolean contains(short[] array, short valueToFind) {
/* 3611 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static int indexOf(char[] array, char valueToFind) {
/* 3628 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(char[] array, char valueToFind, int startIndex) {
/* 3647 */     if (array == null) {
/* 3648 */       return -1;
/*      */     }
/* 3650 */     if (startIndex < 0) {
/* 3651 */       startIndex = 0;
/*      */     }
/* 3653 */     for (int i = startIndex; i < array.length; i++) {
/* 3654 */       if (valueToFind == array[i]) {
/* 3655 */         return i;
/*      */       }
/*      */     } 
/* 3658 */     return -1;
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
/*      */   public static int lastIndexOf(char[] array, char valueToFind) {
/* 3673 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
/* 3692 */     if (array == null) {
/* 3693 */       return -1;
/*      */     }
/* 3695 */     if (startIndex < 0)
/* 3696 */       return -1; 
/* 3697 */     if (startIndex >= array.length) {
/* 3698 */       startIndex = array.length - 1;
/*      */     }
/* 3700 */     for (int i = startIndex; i >= 0; i--) {
/* 3701 */       if (valueToFind == array[i]) {
/* 3702 */         return i;
/*      */       }
/*      */     } 
/* 3705 */     return -1;
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
/*      */   public static boolean contains(char[] array, char valueToFind) {
/* 3719 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static int indexOf(byte[] array, byte valueToFind) {
/* 3735 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
/* 3753 */     if (array == null) {
/* 3754 */       return -1;
/*      */     }
/* 3756 */     if (startIndex < 0) {
/* 3757 */       startIndex = 0;
/*      */     }
/* 3759 */     for (int i = startIndex; i < array.length; i++) {
/* 3760 */       if (valueToFind == array[i]) {
/* 3761 */         return i;
/*      */       }
/*      */     } 
/* 3764 */     return -1;
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
/*      */   public static int lastIndexOf(byte[] array, byte valueToFind) {
/* 3778 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
/* 3796 */     if (array == null) {
/* 3797 */       return -1;
/*      */     }
/* 3799 */     if (startIndex < 0)
/* 3800 */       return -1; 
/* 3801 */     if (startIndex >= array.length) {
/* 3802 */       startIndex = array.length - 1;
/*      */     }
/* 3804 */     for (int i = startIndex; i >= 0; i--) {
/* 3805 */       if (valueToFind == array[i]) {
/* 3806 */         return i;
/*      */       }
/*      */     } 
/* 3809 */     return -1;
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
/*      */   public static boolean contains(byte[] array, byte valueToFind) {
/* 3822 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static int indexOf(double[] array, double valueToFind) {
/* 3838 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(double[] array, double valueToFind, double tolerance) {
/* 3855 */     return indexOf(array, valueToFind, 0, tolerance);
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
/*      */   public static int indexOf(double[] array, double valueToFind, int startIndex) {
/* 3873 */     if (isEmpty(array)) {
/* 3874 */       return -1;
/*      */     }
/* 3876 */     if (startIndex < 0) {
/* 3877 */       startIndex = 0;
/*      */     }
/* 3879 */     for (int i = startIndex; i < array.length; i++) {
/* 3880 */       if (valueToFind == array[i]) {
/* 3881 */         return i;
/*      */       }
/*      */     } 
/* 3884 */     return -1;
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
/*      */   public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
/* 3905 */     if (isEmpty(array)) {
/* 3906 */       return -1;
/*      */     }
/* 3908 */     if (startIndex < 0) {
/* 3909 */       startIndex = 0;
/*      */     }
/* 3911 */     double min = valueToFind - tolerance;
/* 3912 */     double max = valueToFind + tolerance;
/* 3913 */     for (int i = startIndex; i < array.length; i++) {
/* 3914 */       if (array[i] >= min && array[i] <= max) {
/* 3915 */         return i;
/*      */       }
/*      */     } 
/* 3918 */     return -1;
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
/*      */   public static int lastIndexOf(double[] array, double valueToFind) {
/* 3932 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
/* 3949 */     return lastIndexOf(array, valueToFind, 2147483647, tolerance);
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
/*      */   public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
/* 3967 */     if (isEmpty(array)) {
/* 3968 */       return -1;
/*      */     }
/* 3970 */     if (startIndex < 0)
/* 3971 */       return -1; 
/* 3972 */     if (startIndex >= array.length) {
/* 3973 */       startIndex = array.length - 1;
/*      */     }
/* 3975 */     for (int i = startIndex; i >= 0; i--) {
/* 3976 */       if (valueToFind == array[i]) {
/* 3977 */         return i;
/*      */       }
/*      */     } 
/* 3980 */     return -1;
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
/*      */   public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
/* 4001 */     if (isEmpty(array)) {
/* 4002 */       return -1;
/*      */     }
/* 4004 */     if (startIndex < 0)
/* 4005 */       return -1; 
/* 4006 */     if (startIndex >= array.length) {
/* 4007 */       startIndex = array.length - 1;
/*      */     }
/* 4009 */     double min = valueToFind - tolerance;
/* 4010 */     double max = valueToFind + tolerance;
/* 4011 */     for (int i = startIndex; i >= 0; i--) {
/* 4012 */       if (array[i] >= min && array[i] <= max) {
/* 4013 */         return i;
/*      */       }
/*      */     } 
/* 4016 */     return -1;
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
/*      */   public static boolean contains(double[] array, double valueToFind) {
/* 4029 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static boolean contains(double[] array, double valueToFind, double tolerance) {
/* 4046 */     return (indexOf(array, valueToFind, 0, tolerance) != -1);
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
/*      */   public static int indexOf(float[] array, float valueToFind) {
/* 4062 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(float[] array, float valueToFind, int startIndex) {
/* 4080 */     if (isEmpty(array)) {
/* 4081 */       return -1;
/*      */     }
/* 4083 */     if (startIndex < 0) {
/* 4084 */       startIndex = 0;
/*      */     }
/* 4086 */     for (int i = startIndex; i < array.length; i++) {
/* 4087 */       if (valueToFind == array[i]) {
/* 4088 */         return i;
/*      */       }
/*      */     } 
/* 4091 */     return -1;
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
/*      */   public static int lastIndexOf(float[] array, float valueToFind) {
/* 4105 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
/* 4123 */     if (isEmpty(array)) {
/* 4124 */       return -1;
/*      */     }
/* 4126 */     if (startIndex < 0)
/* 4127 */       return -1; 
/* 4128 */     if (startIndex >= array.length) {
/* 4129 */       startIndex = array.length - 1;
/*      */     }
/* 4131 */     for (int i = startIndex; i >= 0; i--) {
/* 4132 */       if (valueToFind == array[i]) {
/* 4133 */         return i;
/*      */       }
/*      */     } 
/* 4136 */     return -1;
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
/*      */   public static boolean contains(float[] array, float valueToFind) {
/* 4149 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static int indexOf(boolean[] array, boolean valueToFind) {
/* 4165 */     return indexOf(array, valueToFind, 0);
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
/*      */   public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
/* 4184 */     if (isEmpty(array)) {
/* 4185 */       return -1;
/*      */     }
/* 4187 */     if (startIndex < 0) {
/* 4188 */       startIndex = 0;
/*      */     }
/* 4190 */     for (int i = startIndex; i < array.length; i++) {
/* 4191 */       if (valueToFind == array[i]) {
/* 4192 */         return i;
/*      */       }
/*      */     } 
/* 4195 */     return -1;
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
/*      */   public static int lastIndexOf(boolean[] array, boolean valueToFind) {
/* 4210 */     return lastIndexOf(array, valueToFind, 2147483647);
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
/*      */   public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
/* 4228 */     if (isEmpty(array)) {
/* 4229 */       return -1;
/*      */     }
/* 4231 */     if (startIndex < 0)
/* 4232 */       return -1; 
/* 4233 */     if (startIndex >= array.length) {
/* 4234 */       startIndex = array.length - 1;
/*      */     }
/* 4236 */     for (int i = startIndex; i >= 0; i--) {
/* 4237 */       if (valueToFind == array[i]) {
/* 4238 */         return i;
/*      */       }
/*      */     } 
/* 4241 */     return -1;
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
/*      */   public static boolean contains(boolean[] array, boolean valueToFind) {
/* 4254 */     return (indexOf(array, valueToFind) != -1);
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
/*      */   public static char[] toPrimitive(Character[] array) {
/* 4272 */     if (array == null)
/* 4273 */       return null; 
/* 4274 */     if (array.length == 0) {
/* 4275 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/* 4277 */     char[] result = new char[array.length];
/* 4278 */     for (int i = 0; i < array.length; i++) {
/* 4279 */       result[i] = array[i].charValue();
/*      */     }
/* 4281 */     return result;
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
/*      */   public static char[] toPrimitive(Character[] array, char valueForNull) {
/* 4294 */     if (array == null)
/* 4295 */       return null; 
/* 4296 */     if (array.length == 0) {
/* 4297 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/* 4299 */     char[] result = new char[array.length];
/* 4300 */     for (int i = 0; i < array.length; i++) {
/* 4301 */       Character b = array[i];
/* 4302 */       result[i] = (b == null) ? valueForNull : b.charValue();
/*      */     } 
/* 4304 */     return result;
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
/*      */   public static Character[] toObject(char[] array) {
/* 4316 */     if (array == null)
/* 4317 */       return null; 
/* 4318 */     if (array.length == 0) {
/* 4319 */       return EMPTY_CHARACTER_OBJECT_ARRAY;
/*      */     }
/* 4321 */     Character[] result = new Character[array.length];
/* 4322 */     for (int i = 0; i < array.length; i++) {
/* 4323 */       result[i] = Character.valueOf(array[i]);
/*      */     }
/* 4325 */     return result;
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
/*      */   public static long[] toPrimitive(Long[] array) {
/* 4340 */     if (array == null)
/* 4341 */       return null; 
/* 4342 */     if (array.length == 0) {
/* 4343 */       return EMPTY_LONG_ARRAY;
/*      */     }
/* 4345 */     long[] result = new long[array.length];
/* 4346 */     for (int i = 0; i < array.length; i++) {
/* 4347 */       result[i] = array[i].longValue();
/*      */     }
/* 4349 */     return result;
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
/*      */   public static long[] toPrimitive(Long[] array, long valueForNull) {
/* 4362 */     if (array == null)
/* 4363 */       return null; 
/* 4364 */     if (array.length == 0) {
/* 4365 */       return EMPTY_LONG_ARRAY;
/*      */     }
/* 4367 */     long[] result = new long[array.length];
/* 4368 */     for (int i = 0; i < array.length; i++) {
/* 4369 */       Long b = array[i];
/* 4370 */       result[i] = (b == null) ? valueForNull : b.longValue();
/*      */     } 
/* 4372 */     return result;
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
/*      */   public static Long[] toObject(long[] array) {
/* 4384 */     if (array == null)
/* 4385 */       return null; 
/* 4386 */     if (array.length == 0) {
/* 4387 */       return EMPTY_LONG_OBJECT_ARRAY;
/*      */     }
/* 4389 */     Long[] result = new Long[array.length];
/* 4390 */     for (int i = 0; i < array.length; i++) {
/* 4391 */       result[i] = Long.valueOf(array[i]);
/*      */     }
/* 4393 */     return result;
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
/*      */   public static int[] toPrimitive(Integer[] array) {
/* 4408 */     if (array == null)
/* 4409 */       return null; 
/* 4410 */     if (array.length == 0) {
/* 4411 */       return EMPTY_INT_ARRAY;
/*      */     }
/* 4413 */     int[] result = new int[array.length];
/* 4414 */     for (int i = 0; i < array.length; i++) {
/* 4415 */       result[i] = array[i].intValue();
/*      */     }
/* 4417 */     return result;
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
/*      */   public static int[] toPrimitive(Integer[] array, int valueForNull) {
/* 4430 */     if (array == null)
/* 4431 */       return null; 
/* 4432 */     if (array.length == 0) {
/* 4433 */       return EMPTY_INT_ARRAY;
/*      */     }
/* 4435 */     int[] result = new int[array.length];
/* 4436 */     for (int i = 0; i < array.length; i++) {
/* 4437 */       Integer b = array[i];
/* 4438 */       result[i] = (b == null) ? valueForNull : b.intValue();
/*      */     } 
/* 4440 */     return result;
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
/*      */   public static Integer[] toObject(int[] array) {
/* 4452 */     if (array == null)
/* 4453 */       return null; 
/* 4454 */     if (array.length == 0) {
/* 4455 */       return EMPTY_INTEGER_OBJECT_ARRAY;
/*      */     }
/* 4457 */     Integer[] result = new Integer[array.length];
/* 4458 */     for (int i = 0; i < array.length; i++) {
/* 4459 */       result[i] = Integer.valueOf(array[i]);
/*      */     }
/* 4461 */     return result;
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
/*      */   public static short[] toPrimitive(Short[] array) {
/* 4476 */     if (array == null)
/* 4477 */       return null; 
/* 4478 */     if (array.length == 0) {
/* 4479 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/* 4481 */     short[] result = new short[array.length];
/* 4482 */     for (int i = 0; i < array.length; i++) {
/* 4483 */       result[i] = array[i].shortValue();
/*      */     }
/* 4485 */     return result;
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
/*      */   public static short[] toPrimitive(Short[] array, short valueForNull) {
/* 4498 */     if (array == null)
/* 4499 */       return null; 
/* 4500 */     if (array.length == 0) {
/* 4501 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/* 4503 */     short[] result = new short[array.length];
/* 4504 */     for (int i = 0; i < array.length; i++) {
/* 4505 */       Short b = array[i];
/* 4506 */       result[i] = (b == null) ? valueForNull : b.shortValue();
/*      */     } 
/* 4508 */     return result;
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
/*      */   public static Short[] toObject(short[] array) {
/* 4520 */     if (array == null)
/* 4521 */       return null; 
/* 4522 */     if (array.length == 0) {
/* 4523 */       return EMPTY_SHORT_OBJECT_ARRAY;
/*      */     }
/* 4525 */     Short[] result = new Short[array.length];
/* 4526 */     for (int i = 0; i < array.length; i++) {
/* 4527 */       result[i] = Short.valueOf(array[i]);
/*      */     }
/* 4529 */     return result;
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
/*      */   public static byte[] toPrimitive(Byte[] array) {
/* 4544 */     if (array == null)
/* 4545 */       return null; 
/* 4546 */     if (array.length == 0) {
/* 4547 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/* 4549 */     byte[] result = new byte[array.length];
/* 4550 */     for (int i = 0; i < array.length; i++) {
/* 4551 */       result[i] = array[i].byteValue();
/*      */     }
/* 4553 */     return result;
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
/*      */   public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
/* 4566 */     if (array == null)
/* 4567 */       return null; 
/* 4568 */     if (array.length == 0) {
/* 4569 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/* 4571 */     byte[] result = new byte[array.length];
/* 4572 */     for (int i = 0; i < array.length; i++) {
/* 4573 */       Byte b = array[i];
/* 4574 */       result[i] = (b == null) ? valueForNull : b.byteValue();
/*      */     } 
/* 4576 */     return result;
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
/*      */   public static Byte[] toObject(byte[] array) {
/* 4588 */     if (array == null)
/* 4589 */       return null; 
/* 4590 */     if (array.length == 0) {
/* 4591 */       return EMPTY_BYTE_OBJECT_ARRAY;
/*      */     }
/* 4593 */     Byte[] result = new Byte[array.length];
/* 4594 */     for (int i = 0; i < array.length; i++) {
/* 4595 */       result[i] = Byte.valueOf(array[i]);
/*      */     }
/* 4597 */     return result;
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
/*      */   public static double[] toPrimitive(Double[] array) {
/* 4612 */     if (array == null)
/* 4613 */       return null; 
/* 4614 */     if (array.length == 0) {
/* 4615 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/* 4617 */     double[] result = new double[array.length];
/* 4618 */     for (int i = 0; i < array.length; i++) {
/* 4619 */       result[i] = array[i].doubleValue();
/*      */     }
/* 4621 */     return result;
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
/*      */   public static double[] toPrimitive(Double[] array, double valueForNull) {
/* 4634 */     if (array == null)
/* 4635 */       return null; 
/* 4636 */     if (array.length == 0) {
/* 4637 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/* 4639 */     double[] result = new double[array.length];
/* 4640 */     for (int i = 0; i < array.length; i++) {
/* 4641 */       Double b = array[i];
/* 4642 */       result[i] = (b == null) ? valueForNull : b.doubleValue();
/*      */     } 
/* 4644 */     return result;
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
/*      */   public static Double[] toObject(double[] array) {
/* 4656 */     if (array == null)
/* 4657 */       return null; 
/* 4658 */     if (array.length == 0) {
/* 4659 */       return EMPTY_DOUBLE_OBJECT_ARRAY;
/*      */     }
/* 4661 */     Double[] result = new Double[array.length];
/* 4662 */     for (int i = 0; i < array.length; i++) {
/* 4663 */       result[i] = Double.valueOf(array[i]);
/*      */     }
/* 4665 */     return result;
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
/*      */   public static float[] toPrimitive(Float[] array) {
/* 4680 */     if (array == null)
/* 4681 */       return null; 
/* 4682 */     if (array.length == 0) {
/* 4683 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/* 4685 */     float[] result = new float[array.length];
/* 4686 */     for (int i = 0; i < array.length; i++) {
/* 4687 */       result[i] = array[i].floatValue();
/*      */     }
/* 4689 */     return result;
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
/*      */   public static float[] toPrimitive(Float[] array, float valueForNull) {
/* 4702 */     if (array == null)
/* 4703 */       return null; 
/* 4704 */     if (array.length == 0) {
/* 4705 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/* 4707 */     float[] result = new float[array.length];
/* 4708 */     for (int i = 0; i < array.length; i++) {
/* 4709 */       Float b = array[i];
/* 4710 */       result[i] = (b == null) ? valueForNull : b.floatValue();
/*      */     } 
/* 4712 */     return result;
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
/*      */   public static Float[] toObject(float[] array) {
/* 4724 */     if (array == null)
/* 4725 */       return null; 
/* 4726 */     if (array.length == 0) {
/* 4727 */       return EMPTY_FLOAT_OBJECT_ARRAY;
/*      */     }
/* 4729 */     Float[] result = new Float[array.length];
/* 4730 */     for (int i = 0; i < array.length; i++) {
/* 4731 */       result[i] = Float.valueOf(array[i]);
/*      */     }
/* 4733 */     return result;
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
/*      */   public static Object toPrimitive(Object array) {
/* 4746 */     if (array == null) {
/* 4747 */       return null;
/*      */     }
/* 4749 */     Class<?> ct = array.getClass().getComponentType();
/* 4750 */     Class<?> pt = ClassUtils.wrapperToPrimitive(ct);
/* 4751 */     if (int.class.equals(pt)) {
/* 4752 */       return toPrimitive((Integer[])array);
/*      */     }
/* 4754 */     if (long.class.equals(pt)) {
/* 4755 */       return toPrimitive((Long[])array);
/*      */     }
/* 4757 */     if (short.class.equals(pt)) {
/* 4758 */       return toPrimitive((Short[])array);
/*      */     }
/* 4760 */     if (double.class.equals(pt)) {
/* 4761 */       return toPrimitive((Double[])array);
/*      */     }
/* 4763 */     if (float.class.equals(pt)) {
/* 4764 */       return toPrimitive((Float[])array);
/*      */     }
/* 4766 */     return array;
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
/*      */   public static boolean[] toPrimitive(Boolean[] array) {
/* 4781 */     if (array == null)
/* 4782 */       return null; 
/* 4783 */     if (array.length == 0) {
/* 4784 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/* 4786 */     boolean[] result = new boolean[array.length];
/* 4787 */     for (int i = 0; i < array.length; i++) {
/* 4788 */       result[i] = array[i].booleanValue();
/*      */     }
/* 4790 */     return result;
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
/*      */   public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
/* 4803 */     if (array == null)
/* 4804 */       return null; 
/* 4805 */     if (array.length == 0) {
/* 4806 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/* 4808 */     boolean[] result = new boolean[array.length];
/* 4809 */     for (int i = 0; i < array.length; i++) {
/* 4810 */       Boolean b = array[i];
/* 4811 */       result[i] = (b == null) ? valueForNull : b.booleanValue();
/*      */     } 
/* 4813 */     return result;
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
/*      */   public static Boolean[] toObject(boolean[] array) {
/* 4825 */     if (array == null)
/* 4826 */       return null; 
/* 4827 */     if (array.length == 0) {
/* 4828 */       return EMPTY_BOOLEAN_OBJECT_ARRAY;
/*      */     }
/* 4830 */     Boolean[] result = new Boolean[array.length];
/* 4831 */     for (int i = 0; i < array.length; i++) {
/* 4832 */       result[i] = array[i] ? Boolean.TRUE : Boolean.FALSE;
/*      */     }
/* 4834 */     return result;
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
/*      */   public static boolean isEmpty(Object[] array) {
/* 4846 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(long[] array) {
/* 4857 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(int[] array) {
/* 4868 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(short[] array) {
/* 4879 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(char[] array) {
/* 4890 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(byte[] array) {
/* 4901 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(double[] array) {
/* 4912 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(float[] array) {
/* 4923 */     return (getLength(array) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(boolean[] array) {
/* 4934 */     return (getLength(array) == 0);
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
/*      */   public static <T> boolean isNotEmpty(T[] array) {
/* 4947 */     return !isEmpty((Object[])array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(long[] array) {
/* 4958 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(int[] array) {
/* 4969 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(short[] array) {
/* 4980 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(char[] array) {
/* 4991 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(byte[] array) {
/* 5002 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(double[] array) {
/* 5013 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(float[] array) {
/* 5024 */     return !isEmpty(array);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(boolean[] array) {
/* 5035 */     return !isEmpty(array);
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
/*      */   public static <T> T[] addAll(T[] array1, T... array2) {
/* 5063 */     if (array1 == null)
/* 5064 */       return clone(array2); 
/* 5065 */     if (array2 == null) {
/* 5066 */       return clone(array1);
/*      */     }
/* 5068 */     Class<?> type1 = array1.getClass().getComponentType();
/*      */     
/* 5070 */     T[] joinedArray = (T[])Array.newInstance(type1, array1.length + array2.length);
/* 5071 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/*      */     try {
/* 5073 */       System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5074 */     } catch (ArrayStoreException ase) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5081 */       Class<?> type2 = array2.getClass().getComponentType();
/* 5082 */       if (!type1.isAssignableFrom(type2)) {
/* 5083 */         throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1
/* 5084 */             .getName(), ase);
/*      */       }
/* 5086 */       throw ase;
/*      */     } 
/* 5088 */     return joinedArray;
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
/*      */   public static boolean[] addAll(boolean[] array1, boolean... array2) {
/* 5109 */     if (array1 == null)
/* 5110 */       return clone(array2); 
/* 5111 */     if (array2 == null) {
/* 5112 */       return clone(array1);
/*      */     }
/* 5114 */     boolean[] joinedArray = new boolean[array1.length + array2.length];
/* 5115 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5116 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5117 */     return joinedArray;
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
/*      */   public static char[] addAll(char[] array1, char... array2) {
/* 5138 */     if (array1 == null)
/* 5139 */       return clone(array2); 
/* 5140 */     if (array2 == null) {
/* 5141 */       return clone(array1);
/*      */     }
/* 5143 */     char[] joinedArray = new char[array1.length + array2.length];
/* 5144 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5145 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5146 */     return joinedArray;
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
/*      */   public static byte[] addAll(byte[] array1, byte... array2) {
/* 5167 */     if (array1 == null)
/* 5168 */       return clone(array2); 
/* 5169 */     if (array2 == null) {
/* 5170 */       return clone(array1);
/*      */     }
/* 5172 */     byte[] joinedArray = new byte[array1.length + array2.length];
/* 5173 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5174 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5175 */     return joinedArray;
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
/*      */   public static short[] addAll(short[] array1, short... array2) {
/* 5196 */     if (array1 == null)
/* 5197 */       return clone(array2); 
/* 5198 */     if (array2 == null) {
/* 5199 */       return clone(array1);
/*      */     }
/* 5201 */     short[] joinedArray = new short[array1.length + array2.length];
/* 5202 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5203 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5204 */     return joinedArray;
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
/*      */   public static int[] addAll(int[] array1, int... array2) {
/* 5225 */     if (array1 == null)
/* 5226 */       return clone(array2); 
/* 5227 */     if (array2 == null) {
/* 5228 */       return clone(array1);
/*      */     }
/* 5230 */     int[] joinedArray = new int[array1.length + array2.length];
/* 5231 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5232 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5233 */     return joinedArray;
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
/*      */   public static long[] addAll(long[] array1, long... array2) {
/* 5254 */     if (array1 == null)
/* 5255 */       return clone(array2); 
/* 5256 */     if (array2 == null) {
/* 5257 */       return clone(array1);
/*      */     }
/* 5259 */     long[] joinedArray = new long[array1.length + array2.length];
/* 5260 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5261 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5262 */     return joinedArray;
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
/*      */   public static float[] addAll(float[] array1, float... array2) {
/* 5283 */     if (array1 == null)
/* 5284 */       return clone(array2); 
/* 5285 */     if (array2 == null) {
/* 5286 */       return clone(array1);
/*      */     }
/* 5288 */     float[] joinedArray = new float[array1.length + array2.length];
/* 5289 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5290 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5291 */     return joinedArray;
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
/*      */   public static double[] addAll(double[] array1, double... array2) {
/* 5312 */     if (array1 == null)
/* 5313 */       return clone(array2); 
/* 5314 */     if (array2 == null) {
/* 5315 */       return clone(array1);
/*      */     }
/* 5317 */     double[] joinedArray = new double[array1.length + array2.length];
/* 5318 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 5319 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 5320 */     return joinedArray;
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
/*      */   public static <T> T[] add(T[] array, T element) {
/*      */     Class<?> type;
/* 5354 */     if (array != null) {
/* 5355 */       type = array.getClass().getComponentType();
/* 5356 */     } else if (element != null) {
/* 5357 */       type = element.getClass();
/*      */     } else {
/* 5359 */       throw new IllegalArgumentException("Arguments cannot both be null");
/*      */     } 
/*      */ 
/*      */     
/* 5363 */     T[] newArray = (T[])copyArrayGrow1(array, type);
/* 5364 */     newArray[newArray.length - 1] = element;
/* 5365 */     return newArray;
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
/*      */   public static boolean[] add(boolean[] array, boolean element) {
/* 5390 */     boolean[] newArray = (boolean[])copyArrayGrow1(array, boolean.class);
/* 5391 */     newArray[newArray.length - 1] = element;
/* 5392 */     return newArray;
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
/*      */   public static byte[] add(byte[] array, byte element) {
/* 5417 */     byte[] newArray = (byte[])copyArrayGrow1(array, byte.class);
/* 5418 */     newArray[newArray.length - 1] = element;
/* 5419 */     return newArray;
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
/*      */   public static char[] add(char[] array, char element) {
/* 5444 */     char[] newArray = (char[])copyArrayGrow1(array, char.class);
/* 5445 */     newArray[newArray.length - 1] = element;
/* 5446 */     return newArray;
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
/*      */   public static double[] add(double[] array, double element) {
/* 5471 */     double[] newArray = (double[])copyArrayGrow1(array, double.class);
/* 5472 */     newArray[newArray.length - 1] = element;
/* 5473 */     return newArray;
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
/*      */   public static float[] add(float[] array, float element) {
/* 5498 */     float[] newArray = (float[])copyArrayGrow1(array, float.class);
/* 5499 */     newArray[newArray.length - 1] = element;
/* 5500 */     return newArray;
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
/*      */   public static int[] add(int[] array, int element) {
/* 5525 */     int[] newArray = (int[])copyArrayGrow1(array, int.class);
/* 5526 */     newArray[newArray.length - 1] = element;
/* 5527 */     return newArray;
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
/*      */   public static long[] add(long[] array, long element) {
/* 5552 */     long[] newArray = (long[])copyArrayGrow1(array, long.class);
/* 5553 */     newArray[newArray.length - 1] = element;
/* 5554 */     return newArray;
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
/*      */   public static short[] add(short[] array, short element) {
/* 5579 */     short[] newArray = (short[])copyArrayGrow1(array, short.class);
/* 5580 */     newArray[newArray.length - 1] = element;
/* 5581 */     return newArray;
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
/*      */   private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType) {
/* 5594 */     if (array != null) {
/* 5595 */       int arrayLength = Array.getLength(array);
/* 5596 */       Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
/* 5597 */       System.arraycopy(array, 0, newArray, 0, arrayLength);
/* 5598 */       return newArray;
/*      */     } 
/* 5600 */     return Array.newInstance(newArrayComponentType, 1);
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
/*      */   @Deprecated
/*      */   public static <T> T[] add(T[] array, int index, T element) {
/* 5637 */     Class<?> clss = null;
/* 5638 */     if (array != null) {
/* 5639 */       clss = array.getClass().getComponentType();
/* 5640 */     } else if (element != null) {
/* 5641 */       clss = element.getClass();
/*      */     } else {
/* 5643 */       throw new IllegalArgumentException("Array and element cannot both be null");
/*      */     } 
/*      */     
/* 5646 */     T[] newArray = (T[])add(array, index, element, clss);
/* 5647 */     return newArray;
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
/*      */   @Deprecated
/*      */   public static boolean[] add(boolean[] array, int index, boolean element) {
/* 5681 */     return (boolean[])add(array, index, Boolean.valueOf(element), boolean.class);
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
/*      */   @Deprecated
/*      */   public static char[] add(char[] array, int index, char element) {
/* 5717 */     return (char[])add(array, index, Character.valueOf(element), char.class);
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
/*      */   @Deprecated
/*      */   public static byte[] add(byte[] array, int index, byte element) {
/* 5752 */     return (byte[])add(array, index, Byte.valueOf(element), byte.class);
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
/*      */   @Deprecated
/*      */   public static short[] add(short[] array, int index, short element) {
/* 5787 */     return (short[])add(array, index, Short.valueOf(element), short.class);
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
/*      */   @Deprecated
/*      */   public static int[] add(int[] array, int index, int element) {
/* 5822 */     return (int[])add(array, index, Integer.valueOf(element), int.class);
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
/*      */   @Deprecated
/*      */   public static long[] add(long[] array, int index, long element) {
/* 5857 */     return (long[])add(array, index, Long.valueOf(element), long.class);
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
/*      */   @Deprecated
/*      */   public static float[] add(float[] array, int index, float element) {
/* 5892 */     return (float[])add(array, index, Float.valueOf(element), float.class);
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
/*      */   @Deprecated
/*      */   public static double[] add(double[] array, int index, double element) {
/* 5927 */     return (double[])add(array, index, Double.valueOf(element), double.class);
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
/*      */   private static Object add(Object array, int index, Object element, Class<?> clss) {
/* 5942 */     if (array == null) {
/* 5943 */       if (index != 0) {
/* 5944 */         throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
/*      */       }
/* 5946 */       Object joinedArray = Array.newInstance(clss, 1);
/* 5947 */       Array.set(joinedArray, 0, element);
/* 5948 */       return joinedArray;
/*      */     } 
/* 5950 */     int length = Array.getLength(array);
/* 5951 */     if (index > length || index < 0) {
/* 5952 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/*      */     }
/* 5954 */     Object result = Array.newInstance(clss, length + 1);
/* 5955 */     System.arraycopy(array, 0, result, 0, index);
/* 5956 */     Array.set(result, index, element);
/* 5957 */     if (index < length) {
/* 5958 */       System.arraycopy(array, index, result, index + 1, length - index);
/*      */     }
/* 5960 */     return result;
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
/*      */   public static <T> T[] remove(T[] array, int index) {
/* 5994 */     return (T[])remove(array, index);
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
/*      */   public static <T> T[] removeElement(T[] array, Object element) {
/* 6024 */     int index = indexOf((Object[])array, element);
/* 6025 */     if (index == -1) {
/* 6026 */       return clone(array);
/*      */     }
/* 6028 */     return remove(array, index);
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
/*      */   public static boolean[] remove(boolean[] array, int index) {
/* 6060 */     return (boolean[])remove(array, index);
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
/*      */   public static boolean[] removeElement(boolean[] array, boolean element) {
/* 6089 */     int index = indexOf(array, element);
/* 6090 */     if (index == -1) {
/* 6091 */       return clone(array);
/*      */     }
/* 6093 */     return remove(array, index);
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
/*      */   public static byte[] remove(byte[] array, int index) {
/* 6125 */     return (byte[])remove(array, index);
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
/*      */   public static byte[] removeElement(byte[] array, byte element) {
/* 6154 */     int index = indexOf(array, element);
/* 6155 */     if (index == -1) {
/* 6156 */       return clone(array);
/*      */     }
/* 6158 */     return remove(array, index);
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
/*      */   public static char[] remove(char[] array, int index) {
/* 6190 */     return (char[])remove(array, index);
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
/*      */   public static char[] removeElement(char[] array, char element) {
/* 6219 */     int index = indexOf(array, element);
/* 6220 */     if (index == -1) {
/* 6221 */       return clone(array);
/*      */     }
/* 6223 */     return remove(array, index);
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
/*      */   public static double[] remove(double[] array, int index) {
/* 6255 */     return (double[])remove(array, index);
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
/*      */   public static double[] removeElement(double[] array, double element) {
/* 6284 */     int index = indexOf(array, element);
/* 6285 */     if (index == -1) {
/* 6286 */       return clone(array);
/*      */     }
/* 6288 */     return remove(array, index);
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
/*      */   public static float[] remove(float[] array, int index) {
/* 6320 */     return (float[])remove(array, index);
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
/*      */   public static float[] removeElement(float[] array, float element) {
/* 6349 */     int index = indexOf(array, element);
/* 6350 */     if (index == -1) {
/* 6351 */       return clone(array);
/*      */     }
/* 6353 */     return remove(array, index);
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
/*      */   public static int[] remove(int[] array, int index) {
/* 6385 */     return (int[])remove(array, index);
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
/*      */   public static int[] removeElement(int[] array, int element) {
/* 6414 */     int index = indexOf(array, element);
/* 6415 */     if (index == -1) {
/* 6416 */       return clone(array);
/*      */     }
/* 6418 */     return remove(array, index);
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
/*      */   public static long[] remove(long[] array, int index) {
/* 6450 */     return (long[])remove(array, index);
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
/*      */   public static long[] removeElement(long[] array, long element) {
/* 6479 */     int index = indexOf(array, element);
/* 6480 */     if (index == -1) {
/* 6481 */       return clone(array);
/*      */     }
/* 6483 */     return remove(array, index);
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
/*      */   public static short[] remove(short[] array, int index) {
/* 6515 */     return (short[])remove(array, index);
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
/*      */   public static short[] removeElement(short[] array, short element) {
/* 6544 */     int index = indexOf(array, element);
/* 6545 */     if (index == -1) {
/* 6546 */       return clone(array);
/*      */     }
/* 6548 */     return remove(array, index);
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
/*      */   private static Object remove(Object array, int index) {
/* 6573 */     int length = getLength(array);
/* 6574 */     if (index < 0 || index >= length) {
/* 6575 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/*      */     }
/*      */     
/* 6578 */     Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
/* 6579 */     System.arraycopy(array, 0, result, 0, index);
/* 6580 */     if (index < length - 1) {
/* 6581 */       System.arraycopy(array, index + 1, result, index, length - index - 1);
/*      */     }
/*      */     
/* 6584 */     return result;
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
/*      */   public static <T> T[] removeAll(T[] array, int... indices) {
/* 6615 */     return (T[])removeAll(array, indices);
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
/*      */   @SafeVarargs
/*      */   public static <T> T[] removeElements(T[] array, T... values) {
/* 6648 */     if (isEmpty((Object[])array) || isEmpty((Object[])values)) {
/* 6649 */       return clone(array);
/*      */     }
/* 6651 */     HashMap<T, MutableInt> occurrences = new HashMap<>(values.length);
/* 6652 */     for (T v : values) {
/* 6653 */       MutableInt count = occurrences.get(v);
/* 6654 */       if (count == null) {
/* 6655 */         occurrences.put(v, new MutableInt(1));
/*      */       } else {
/* 6657 */         count.increment();
/*      */       } 
/*      */     } 
/* 6660 */     BitSet toRemove = new BitSet();
/* 6661 */     for (int i = 0; i < array.length; i++) {
/* 6662 */       T key = array[i];
/* 6663 */       MutableInt count = occurrences.get(key);
/* 6664 */       if (count != null) {
/* 6665 */         if (count.decrementAndGet() == 0) {
/* 6666 */           occurrences.remove(key);
/*      */         }
/* 6668 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/*      */     
/* 6672 */     T[] result = (T[])removeAll(array, toRemove);
/* 6673 */     return result;
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
/*      */   public static byte[] removeAll(byte[] array, int... indices) {
/* 6706 */     return (byte[])removeAll(array, indices);
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
/*      */   public static byte[] removeElements(byte[] array, byte... values) {
/* 6737 */     if (isEmpty(array) || isEmpty(values)) {
/* 6738 */       return clone(array);
/*      */     }
/* 6740 */     Map<Byte, MutableInt> occurrences = new HashMap<>(values.length);
/* 6741 */     for (byte v : values) {
/* 6742 */       Byte boxed = Byte.valueOf(v);
/* 6743 */       MutableInt count = occurrences.get(boxed);
/* 6744 */       if (count == null) {
/* 6745 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 6747 */         count.increment();
/*      */       } 
/*      */     } 
/* 6750 */     BitSet toRemove = new BitSet();
/* 6751 */     for (int i = 0; i < array.length; i++) {
/* 6752 */       byte key = array[i];
/* 6753 */       MutableInt count = occurrences.get(Byte.valueOf(key));
/* 6754 */       if (count != null) {
/* 6755 */         if (count.decrementAndGet() == 0) {
/* 6756 */           occurrences.remove(Byte.valueOf(key));
/*      */         }
/* 6758 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 6761 */     return (byte[])removeAll(array, toRemove);
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
/*      */   public static short[] removeAll(short[] array, int... indices) {
/* 6794 */     return (short[])removeAll(array, indices);
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
/*      */   public static short[] removeElements(short[] array, short... values) {
/* 6825 */     if (isEmpty(array) || isEmpty(values)) {
/* 6826 */       return clone(array);
/*      */     }
/* 6828 */     HashMap<Short, MutableInt> occurrences = new HashMap<>(values.length);
/* 6829 */     for (short v : values) {
/* 6830 */       Short boxed = Short.valueOf(v);
/* 6831 */       MutableInt count = occurrences.get(boxed);
/* 6832 */       if (count == null) {
/* 6833 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 6835 */         count.increment();
/*      */       } 
/*      */     } 
/* 6838 */     BitSet toRemove = new BitSet();
/* 6839 */     for (int i = 0; i < array.length; i++) {
/* 6840 */       short key = array[i];
/* 6841 */       MutableInt count = occurrences.get(Short.valueOf(key));
/* 6842 */       if (count != null) {
/* 6843 */         if (count.decrementAndGet() == 0) {
/* 6844 */           occurrences.remove(Short.valueOf(key));
/*      */         }
/* 6846 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 6849 */     return (short[])removeAll(array, toRemove);
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
/*      */   public static int[] removeAll(int[] array, int... indices) {
/* 6882 */     return (int[])removeAll(array, indices);
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
/*      */   public static int[] removeElements(int[] array, int... values) {
/* 6913 */     if (isEmpty(array) || isEmpty(values)) {
/* 6914 */       return clone(array);
/*      */     }
/* 6916 */     HashMap<Integer, MutableInt> occurrences = new HashMap<>(values.length);
/* 6917 */     for (int v : values) {
/* 6918 */       Integer boxed = Integer.valueOf(v);
/* 6919 */       MutableInt count = occurrences.get(boxed);
/* 6920 */       if (count == null) {
/* 6921 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 6923 */         count.increment();
/*      */       } 
/*      */     } 
/* 6926 */     BitSet toRemove = new BitSet();
/* 6927 */     for (int i = 0; i < array.length; i++) {
/* 6928 */       int key = array[i];
/* 6929 */       MutableInt count = occurrences.get(Integer.valueOf(key));
/* 6930 */       if (count != null) {
/* 6931 */         if (count.decrementAndGet() == 0) {
/* 6932 */           occurrences.remove(Integer.valueOf(key));
/*      */         }
/* 6934 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 6937 */     return (int[])removeAll(array, toRemove);
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
/*      */   public static char[] removeAll(char[] array, int... indices) {
/* 6970 */     return (char[])removeAll(array, indices);
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
/*      */   public static char[] removeElements(char[] array, char... values) {
/* 7001 */     if (isEmpty(array) || isEmpty(values)) {
/* 7002 */       return clone(array);
/*      */     }
/* 7004 */     HashMap<Character, MutableInt> occurrences = new HashMap<>(values.length);
/* 7005 */     for (char v : values) {
/* 7006 */       Character boxed = Character.valueOf(v);
/* 7007 */       MutableInt count = occurrences.get(boxed);
/* 7008 */       if (count == null) {
/* 7009 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 7011 */         count.increment();
/*      */       } 
/*      */     } 
/* 7014 */     BitSet toRemove = new BitSet();
/* 7015 */     for (int i = 0; i < array.length; i++) {
/* 7016 */       char key = array[i];
/* 7017 */       MutableInt count = occurrences.get(Character.valueOf(key));
/* 7018 */       if (count != null) {
/* 7019 */         if (count.decrementAndGet() == 0) {
/* 7020 */           occurrences.remove(Character.valueOf(key));
/*      */         }
/* 7022 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 7025 */     return (char[])removeAll(array, toRemove);
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
/*      */   public static long[] removeAll(long[] array, int... indices) {
/* 7058 */     return (long[])removeAll(array, indices);
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
/*      */   public static long[] removeElements(long[] array, long... values) {
/* 7089 */     if (isEmpty(array) || isEmpty(values)) {
/* 7090 */       return clone(array);
/*      */     }
/* 7092 */     HashMap<Long, MutableInt> occurrences = new HashMap<>(values.length);
/* 7093 */     for (long v : values) {
/* 7094 */       Long boxed = Long.valueOf(v);
/* 7095 */       MutableInt count = occurrences.get(boxed);
/* 7096 */       if (count == null) {
/* 7097 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 7099 */         count.increment();
/*      */       } 
/*      */     } 
/* 7102 */     BitSet toRemove = new BitSet();
/* 7103 */     for (int i = 0; i < array.length; i++) {
/* 7104 */       long key = array[i];
/* 7105 */       MutableInt count = occurrences.get(Long.valueOf(key));
/* 7106 */       if (count != null) {
/* 7107 */         if (count.decrementAndGet() == 0) {
/* 7108 */           occurrences.remove(Long.valueOf(key));
/*      */         }
/* 7110 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 7113 */     return (long[])removeAll(array, toRemove);
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
/*      */   public static float[] removeAll(float[] array, int... indices) {
/* 7146 */     return (float[])removeAll(array, indices);
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
/*      */   public static float[] removeElements(float[] array, float... values) {
/* 7177 */     if (isEmpty(array) || isEmpty(values)) {
/* 7178 */       return clone(array);
/*      */     }
/* 7180 */     HashMap<Float, MutableInt> occurrences = new HashMap<>(values.length);
/* 7181 */     for (float v : values) {
/* 7182 */       Float boxed = Float.valueOf(v);
/* 7183 */       MutableInt count = occurrences.get(boxed);
/* 7184 */       if (count == null) {
/* 7185 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 7187 */         count.increment();
/*      */       } 
/*      */     } 
/* 7190 */     BitSet toRemove = new BitSet();
/* 7191 */     for (int i = 0; i < array.length; i++) {
/* 7192 */       float key = array[i];
/* 7193 */       MutableInt count = occurrences.get(Float.valueOf(key));
/* 7194 */       if (count != null) {
/* 7195 */         if (count.decrementAndGet() == 0) {
/* 7196 */           occurrences.remove(Float.valueOf(key));
/*      */         }
/* 7198 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 7201 */     return (float[])removeAll(array, toRemove);
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
/*      */   public static double[] removeAll(double[] array, int... indices) {
/* 7234 */     return (double[])removeAll(array, indices);
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
/*      */   public static double[] removeElements(double[] array, double... values) {
/* 7265 */     if (isEmpty(array) || isEmpty(values)) {
/* 7266 */       return clone(array);
/*      */     }
/* 7268 */     HashMap<Double, MutableInt> occurrences = new HashMap<>(values.length);
/* 7269 */     for (double v : values) {
/* 7270 */       Double boxed = Double.valueOf(v);
/* 7271 */       MutableInt count = occurrences.get(boxed);
/* 7272 */       if (count == null) {
/* 7273 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 7275 */         count.increment();
/*      */       } 
/*      */     } 
/* 7278 */     BitSet toRemove = new BitSet();
/* 7279 */     for (int i = 0; i < array.length; i++) {
/* 7280 */       double key = array[i];
/* 7281 */       MutableInt count = occurrences.get(Double.valueOf(key));
/* 7282 */       if (count != null) {
/* 7283 */         if (count.decrementAndGet() == 0) {
/* 7284 */           occurrences.remove(Double.valueOf(key));
/*      */         }
/* 7286 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 7289 */     return (double[])removeAll(array, toRemove);
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
/*      */   public static boolean[] removeAll(boolean[] array, int... indices) {
/* 7318 */     return (boolean[])removeAll(array, indices);
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
/*      */   public static boolean[] removeElements(boolean[] array, boolean... values) {
/* 7349 */     if (isEmpty(array) || isEmpty(values)) {
/* 7350 */       return clone(array);
/*      */     }
/* 7352 */     HashMap<Boolean, MutableInt> occurrences = new HashMap<>(2);
/* 7353 */     for (boolean v : values) {
/* 7354 */       Boolean boxed = Boolean.valueOf(v);
/* 7355 */       MutableInt count = occurrences.get(boxed);
/* 7356 */       if (count == null) {
/* 7357 */         occurrences.put(boxed, new MutableInt(1));
/*      */       } else {
/* 7359 */         count.increment();
/*      */       } 
/*      */     } 
/* 7362 */     BitSet toRemove = new BitSet();
/* 7363 */     for (int i = 0; i < array.length; i++) {
/* 7364 */       boolean key = array[i];
/* 7365 */       MutableInt count = occurrences.get(Boolean.valueOf(key));
/* 7366 */       if (count != null) {
/* 7367 */         if (count.decrementAndGet() == 0) {
/* 7368 */           occurrences.remove(Boolean.valueOf(key));
/*      */         }
/* 7370 */         toRemove.set(i);
/*      */       } 
/*      */     } 
/* 7373 */     return (boolean[])removeAll(array, toRemove);
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
/*      */   static Object removeAll(Object array, int... indices) {
/* 7385 */     int length = getLength(array);
/* 7386 */     int diff = 0;
/* 7387 */     int[] clonedIndices = clone(indices);
/* 7388 */     Arrays.sort(clonedIndices);
/*      */ 
/*      */     
/* 7391 */     if (isNotEmpty(clonedIndices)) {
/* 7392 */       int i = clonedIndices.length;
/* 7393 */       int prevIndex = length;
/* 7394 */       while (--i >= 0) {
/* 7395 */         int index = clonedIndices[i];
/* 7396 */         if (index < 0 || index >= length) {
/* 7397 */           throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/*      */         }
/* 7399 */         if (index >= prevIndex) {
/*      */           continue;
/*      */         }
/* 7402 */         diff++;
/* 7403 */         prevIndex = index;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 7408 */     Object result = Array.newInstance(array.getClass().getComponentType(), length - diff);
/* 7409 */     if (diff < length) {
/* 7410 */       int end = length;
/* 7411 */       int dest = length - diff;
/* 7412 */       for (int i = clonedIndices.length - 1; i >= 0; i--) {
/* 7413 */         int index = clonedIndices[i];
/* 7414 */         if (end - index > 1) {
/* 7415 */           int cp = end - index - 1;
/* 7416 */           dest -= cp;
/* 7417 */           System.arraycopy(array, index + 1, result, dest, cp);
/*      */         } 
/*      */         
/* 7420 */         end = index;
/*      */       } 
/* 7422 */       if (end > 0) {
/* 7423 */         System.arraycopy(array, 0, result, 0, end);
/*      */       }
/*      */     } 
/* 7426 */     return result;
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
/*      */   static Object removeAll(Object array, BitSet indices) {
/* 7439 */     int srcLength = getLength(array);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7446 */     int removals = indices.cardinality();
/* 7447 */     Object result = Array.newInstance(array.getClass().getComponentType(), srcLength - removals);
/* 7448 */     int srcIndex = 0;
/* 7449 */     int destIndex = 0;
/*      */     
/*      */     int set;
/* 7452 */     while ((set = indices.nextSetBit(srcIndex)) != -1) {
/* 7453 */       int i = set - srcIndex;
/* 7454 */       if (i > 0) {
/* 7455 */         System.arraycopy(array, srcIndex, result, destIndex, i);
/* 7456 */         destIndex += i;
/*      */       } 
/* 7458 */       srcIndex = indices.nextClearBit(set);
/*      */     } 
/* 7460 */     int count = srcLength - srcIndex;
/* 7461 */     if (count > 0) {
/* 7462 */       System.arraycopy(array, srcIndex, result, destIndex, count);
/*      */     }
/* 7464 */     return result;
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
/*      */   public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
/* 7477 */     return isSorted(array, new Comparator<T>()
/*      */         {
/*      */           public int compare(T o1, T o2) {
/* 7480 */             return o1.compareTo(o2);
/*      */           }
/*      */         });
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
/*      */   public static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
/* 7496 */     if (comparator == null) {
/* 7497 */       throw new IllegalArgumentException("Comparator should not be null.");
/*      */     }
/*      */     
/* 7500 */     if (array == null || array.length < 2) {
/* 7501 */       return true;
/*      */     }
/*      */     
/* 7504 */     T previous = array[0];
/* 7505 */     int n = array.length;
/* 7506 */     for (int i = 1; i < n; i++) {
/* 7507 */       T current = array[i];
/* 7508 */       if (comparator.compare(previous, current) > 0) {
/* 7509 */         return false;
/*      */       }
/*      */       
/* 7512 */       previous = current;
/*      */     } 
/* 7514 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(int[] array) {
/* 7525 */     if (array == null || array.length < 2) {
/* 7526 */       return true;
/*      */     }
/*      */     
/* 7529 */     int previous = array[0];
/* 7530 */     int n = array.length;
/* 7531 */     for (int i = 1; i < n; i++) {
/* 7532 */       int current = array[i];
/* 7533 */       if (NumberUtils.compare(previous, current) > 0) {
/* 7534 */         return false;
/*      */       }
/*      */       
/* 7537 */       previous = current;
/*      */     } 
/* 7539 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(long[] array) {
/* 7550 */     if (array == null || array.length < 2) {
/* 7551 */       return true;
/*      */     }
/*      */     
/* 7554 */     long previous = array[0];
/* 7555 */     int n = array.length;
/* 7556 */     for (int i = 1; i < n; i++) {
/* 7557 */       long current = array[i];
/* 7558 */       if (NumberUtils.compare(previous, current) > 0) {
/* 7559 */         return false;
/*      */       }
/*      */       
/* 7562 */       previous = current;
/*      */     } 
/* 7564 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(short[] array) {
/* 7575 */     if (array == null || array.length < 2) {
/* 7576 */       return true;
/*      */     }
/*      */     
/* 7579 */     short previous = array[0];
/* 7580 */     int n = array.length;
/* 7581 */     for (int i = 1; i < n; i++) {
/* 7582 */       short current = array[i];
/* 7583 */       if (NumberUtils.compare(previous, current) > 0) {
/* 7584 */         return false;
/*      */       }
/*      */       
/* 7587 */       previous = current;
/*      */     } 
/* 7589 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(double[] array) {
/* 7600 */     if (array == null || array.length < 2) {
/* 7601 */       return true;
/*      */     }
/*      */     
/* 7604 */     double previous = array[0];
/* 7605 */     int n = array.length;
/* 7606 */     for (int i = 1; i < n; i++) {
/* 7607 */       double current = array[i];
/* 7608 */       if (Double.compare(previous, current) > 0) {
/* 7609 */         return false;
/*      */       }
/*      */       
/* 7612 */       previous = current;
/*      */     } 
/* 7614 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(float[] array) {
/* 7625 */     if (array == null || array.length < 2) {
/* 7626 */       return true;
/*      */     }
/*      */     
/* 7629 */     float previous = array[0];
/* 7630 */     int n = array.length;
/* 7631 */     for (int i = 1; i < n; i++) {
/* 7632 */       float current = array[i];
/* 7633 */       if (Float.compare(previous, current) > 0) {
/* 7634 */         return false;
/*      */       }
/*      */       
/* 7637 */       previous = current;
/*      */     } 
/* 7639 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(byte[] array) {
/* 7650 */     if (array == null || array.length < 2) {
/* 7651 */       return true;
/*      */     }
/*      */     
/* 7654 */     byte previous = array[0];
/* 7655 */     int n = array.length;
/* 7656 */     for (int i = 1; i < n; i++) {
/* 7657 */       byte current = array[i];
/* 7658 */       if (NumberUtils.compare(previous, current) > 0) {
/* 7659 */         return false;
/*      */       }
/*      */       
/* 7662 */       previous = current;
/*      */     } 
/* 7664 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSorted(char[] array) {
/* 7675 */     if (array == null || array.length < 2) {
/* 7676 */       return true;
/*      */     }
/*      */     
/* 7679 */     char previous = array[0];
/* 7680 */     int n = array.length;
/* 7681 */     for (int i = 1; i < n; i++) {
/* 7682 */       char current = array[i];
/* 7683 */       if (CharUtils.compare(previous, current) > 0) {
/* 7684 */         return false;
/*      */       }
/*      */       
/* 7687 */       previous = current;
/*      */     } 
/* 7689 */     return true;
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
/*      */   public static boolean isSorted(boolean[] array) {
/* 7701 */     if (array == null || array.length < 2) {
/* 7702 */       return true;
/*      */     }
/*      */     
/* 7705 */     boolean previous = array[0];
/* 7706 */     int n = array.length;
/* 7707 */     for (int i = 1; i < n; i++) {
/* 7708 */       boolean current = array[i];
/* 7709 */       if (BooleanUtils.compare(previous, current) > 0) {
/* 7710 */         return false;
/*      */       }
/*      */       
/* 7713 */       previous = current;
/*      */     } 
/* 7715 */     return true;
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
/*      */   public static boolean[] removeAllOccurences(boolean[] array, boolean element) {
/* 7734 */     int index = indexOf(array, element);
/* 7735 */     if (index == -1) {
/* 7736 */       return clone(array);
/*      */     }
/*      */     
/* 7739 */     int[] indices = new int[array.length - index];
/* 7740 */     indices[0] = index;
/* 7741 */     int count = 1;
/*      */     
/* 7743 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7744 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7747 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static char[] removeAllOccurences(char[] array, char element) {
/* 7766 */     int index = indexOf(array, element);
/* 7767 */     if (index == -1) {
/* 7768 */       return clone(array);
/*      */     }
/*      */     
/* 7771 */     int[] indices = new int[array.length - index];
/* 7772 */     indices[0] = index;
/* 7773 */     int count = 1;
/*      */     
/* 7775 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7776 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7779 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static byte[] removeAllOccurences(byte[] array, byte element) {
/* 7798 */     int index = indexOf(array, element);
/* 7799 */     if (index == -1) {
/* 7800 */       return clone(array);
/*      */     }
/*      */     
/* 7803 */     int[] indices = new int[array.length - index];
/* 7804 */     indices[0] = index;
/* 7805 */     int count = 1;
/*      */     
/* 7807 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7808 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7811 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static short[] removeAllOccurences(short[] array, short element) {
/* 7830 */     int index = indexOf(array, element);
/* 7831 */     if (index == -1) {
/* 7832 */       return clone(array);
/*      */     }
/*      */     
/* 7835 */     int[] indices = new int[array.length - index];
/* 7836 */     indices[0] = index;
/* 7837 */     int count = 1;
/*      */     
/* 7839 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7840 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7843 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static int[] removeAllOccurences(int[] array, int element) {
/* 7862 */     int index = indexOf(array, element);
/* 7863 */     if (index == -1) {
/* 7864 */       return clone(array);
/*      */     }
/*      */     
/* 7867 */     int[] indices = new int[array.length - index];
/* 7868 */     indices[0] = index;
/* 7869 */     int count = 1;
/*      */     
/* 7871 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7872 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7875 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static long[] removeAllOccurences(long[] array, long element) {
/* 7894 */     int index = indexOf(array, element);
/* 7895 */     if (index == -1) {
/* 7896 */       return clone(array);
/*      */     }
/*      */     
/* 7899 */     int[] indices = new int[array.length - index];
/* 7900 */     indices[0] = index;
/* 7901 */     int count = 1;
/*      */     
/* 7903 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7904 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7907 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static float[] removeAllOccurences(float[] array, float element) {
/* 7926 */     int index = indexOf(array, element);
/* 7927 */     if (index == -1) {
/* 7928 */       return clone(array);
/*      */     }
/*      */     
/* 7931 */     int[] indices = new int[array.length - index];
/* 7932 */     indices[0] = index;
/* 7933 */     int count = 1;
/*      */     
/* 7935 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7936 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7939 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static double[] removeAllOccurences(double[] array, double element) {
/* 7958 */     int index = indexOf(array, element);
/* 7959 */     if (index == -1) {
/* 7960 */       return clone(array);
/*      */     }
/*      */     
/* 7963 */     int[] indices = new int[array.length - index];
/* 7964 */     indices[0] = index;
/* 7965 */     int count = 1;
/*      */     
/* 7967 */     while ((index = indexOf(array, element, indices[count - 1] + 1)) != -1) {
/* 7968 */       indices[count++] = index;
/*      */     }
/*      */     
/* 7971 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static <T> T[] removeAllOccurences(T[] array, T element) {
/* 7991 */     int index = indexOf((Object[])array, element);
/* 7992 */     if (index == -1) {
/* 7993 */       return clone(array);
/*      */     }
/*      */     
/* 7996 */     int[] indices = new int[array.length - index];
/* 7997 */     indices[0] = index;
/* 7998 */     int count = 1;
/*      */     
/* 8000 */     while ((index = indexOf((Object[])array, element, indices[count - 1] + 1)) != -1) {
/* 8001 */       indices[count++] = index;
/*      */     }
/*      */     
/* 8004 */     return removeAll(array, Arrays.copyOf(indices, count));
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
/*      */   public static String[] toStringArray(Object[] array) {
/* 8019 */     if (array == null)
/* 8020 */       return null; 
/* 8021 */     if (array.length == 0) {
/* 8022 */       return EMPTY_STRING_ARRAY;
/*      */     }
/*      */     
/* 8025 */     String[] result = new String[array.length];
/* 8026 */     for (int i = 0; i < array.length; i++) {
/* 8027 */       result[i] = array[i].toString();
/*      */     }
/*      */     
/* 8030 */     return result;
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
/*      */   public static String[] toStringArray(Object[] array, String valueForNullElements) {
/* 8045 */     if (null == array)
/* 8046 */       return null; 
/* 8047 */     if (array.length == 0) {
/* 8048 */       return EMPTY_STRING_ARRAY;
/*      */     }
/*      */     
/* 8051 */     String[] result = new String[array.length];
/* 8052 */     for (int i = 0; i < array.length; i++) {
/* 8053 */       Object object = array[i];
/* 8054 */       result[i] = (object == null) ? valueForNullElements : object.toString();
/*      */     } 
/*      */     
/* 8057 */     return result;
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
/*      */   public static boolean[] insert(int index, boolean[] array, boolean... values) {
/* 8080 */     if (array == null) {
/* 8081 */       return null;
/*      */     }
/* 8083 */     if (values == null || values.length == 0) {
/* 8084 */       return clone(array);
/*      */     }
/* 8086 */     if (index < 0 || index > array.length) {
/* 8087 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8090 */     boolean[] result = new boolean[array.length + values.length];
/*      */     
/* 8092 */     System.arraycopy(values, 0, result, index, values.length);
/* 8093 */     if (index > 0) {
/* 8094 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8096 */     if (index < array.length) {
/* 8097 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8099 */     return result;
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
/*      */   public static byte[] insert(int index, byte[] array, byte... values) {
/* 8122 */     if (array == null) {
/* 8123 */       return null;
/*      */     }
/* 8125 */     if (values == null || values.length == 0) {
/* 8126 */       return clone(array);
/*      */     }
/* 8128 */     if (index < 0 || index > array.length) {
/* 8129 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8132 */     byte[] result = new byte[array.length + values.length];
/*      */     
/* 8134 */     System.arraycopy(values, 0, result, index, values.length);
/* 8135 */     if (index > 0) {
/* 8136 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8138 */     if (index < array.length) {
/* 8139 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8141 */     return result;
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
/*      */   public static char[] insert(int index, char[] array, char... values) {
/* 8164 */     if (array == null) {
/* 8165 */       return null;
/*      */     }
/* 8167 */     if (values == null || values.length == 0) {
/* 8168 */       return clone(array);
/*      */     }
/* 8170 */     if (index < 0 || index > array.length) {
/* 8171 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8174 */     char[] result = new char[array.length + values.length];
/*      */     
/* 8176 */     System.arraycopy(values, 0, result, index, values.length);
/* 8177 */     if (index > 0) {
/* 8178 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8180 */     if (index < array.length) {
/* 8181 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8183 */     return result;
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
/*      */   public static double[] insert(int index, double[] array, double... values) {
/* 8206 */     if (array == null) {
/* 8207 */       return null;
/*      */     }
/* 8209 */     if (values == null || values.length == 0) {
/* 8210 */       return clone(array);
/*      */     }
/* 8212 */     if (index < 0 || index > array.length) {
/* 8213 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8216 */     double[] result = new double[array.length + values.length];
/*      */     
/* 8218 */     System.arraycopy(values, 0, result, index, values.length);
/* 8219 */     if (index > 0) {
/* 8220 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8222 */     if (index < array.length) {
/* 8223 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8225 */     return result;
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
/*      */   public static float[] insert(int index, float[] array, float... values) {
/* 8248 */     if (array == null) {
/* 8249 */       return null;
/*      */     }
/* 8251 */     if (values == null || values.length == 0) {
/* 8252 */       return clone(array);
/*      */     }
/* 8254 */     if (index < 0 || index > array.length) {
/* 8255 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8258 */     float[] result = new float[array.length + values.length];
/*      */     
/* 8260 */     System.arraycopy(values, 0, result, index, values.length);
/* 8261 */     if (index > 0) {
/* 8262 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8264 */     if (index < array.length) {
/* 8265 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8267 */     return result;
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
/*      */   public static int[] insert(int index, int[] array, int... values) {
/* 8290 */     if (array == null) {
/* 8291 */       return null;
/*      */     }
/* 8293 */     if (values == null || values.length == 0) {
/* 8294 */       return clone(array);
/*      */     }
/* 8296 */     if (index < 0 || index > array.length) {
/* 8297 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8300 */     int[] result = new int[array.length + values.length];
/*      */     
/* 8302 */     System.arraycopy(values, 0, result, index, values.length);
/* 8303 */     if (index > 0) {
/* 8304 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8306 */     if (index < array.length) {
/* 8307 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8309 */     return result;
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
/*      */   public static long[] insert(int index, long[] array, long... values) {
/* 8332 */     if (array == null) {
/* 8333 */       return null;
/*      */     }
/* 8335 */     if (values == null || values.length == 0) {
/* 8336 */       return clone(array);
/*      */     }
/* 8338 */     if (index < 0 || index > array.length) {
/* 8339 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8342 */     long[] result = new long[array.length + values.length];
/*      */     
/* 8344 */     System.arraycopy(values, 0, result, index, values.length);
/* 8345 */     if (index > 0) {
/* 8346 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8348 */     if (index < array.length) {
/* 8349 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8351 */     return result;
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
/*      */   public static short[] insert(int index, short[] array, short... values) {
/* 8374 */     if (array == null) {
/* 8375 */       return null;
/*      */     }
/* 8377 */     if (values == null || values.length == 0) {
/* 8378 */       return clone(array);
/*      */     }
/* 8380 */     if (index < 0 || index > array.length) {
/* 8381 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8384 */     short[] result = new short[array.length + values.length];
/*      */     
/* 8386 */     System.arraycopy(values, 0, result, index, values.length);
/* 8387 */     if (index > 0) {
/* 8388 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8390 */     if (index < array.length) {
/* 8391 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8393 */     return result;
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
/*      */   @SafeVarargs
/*      */   public static <T> T[] insert(int index, T[] array, T... values) {
/* 8426 */     if (array == null) {
/* 8427 */       return null;
/*      */     }
/* 8429 */     if (values == null || values.length == 0) {
/* 8430 */       return clone(array);
/*      */     }
/* 8432 */     if (index < 0 || index > array.length) {
/* 8433 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
/*      */     }
/*      */     
/* 8436 */     Class<?> type = array.getClass().getComponentType();
/*      */ 
/*      */     
/* 8439 */     T[] result = (T[])Array.newInstance(type, array.length + values.length);
/*      */     
/* 8441 */     System.arraycopy(values, 0, result, index, values.length);
/* 8442 */     if (index > 0) {
/* 8443 */       System.arraycopy(array, 0, result, 0, index);
/*      */     }
/* 8445 */     if (index < array.length) {
/* 8446 */       System.arraycopy(array, index, result, index + values.length, array.length - index);
/*      */     }
/* 8448 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shuffle(Object[] array) {
/* 8459 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(Object[] array, Random random) {
/* 8471 */     for (int i = array.length; i > 1; i--) {
/* 8472 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(boolean[] array) {
/* 8484 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(boolean[] array, Random random) {
/* 8496 */     for (int i = array.length; i > 1; i--) {
/* 8497 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(byte[] array) {
/* 8509 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(byte[] array, Random random) {
/* 8521 */     for (int i = array.length; i > 1; i--) {
/* 8522 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(char[] array) {
/* 8534 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(char[] array, Random random) {
/* 8546 */     for (int i = array.length; i > 1; i--) {
/* 8547 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(short[] array) {
/* 8559 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(short[] array, Random random) {
/* 8571 */     for (int i = array.length; i > 1; i--) {
/* 8572 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(int[] array) {
/* 8584 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(int[] array, Random random) {
/* 8596 */     for (int i = array.length; i > 1; i--) {
/* 8597 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(long[] array) {
/* 8609 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(long[] array, Random random) {
/* 8621 */     for (int i = array.length; i > 1; i--) {
/* 8622 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(float[] array) {
/* 8634 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(float[] array, Random random) {
/* 8646 */     for (int i = array.length; i > 1; i--) {
/* 8647 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static void shuffle(double[] array) {
/* 8659 */     shuffle(array, new Random());
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
/*      */   public static void shuffle(double[] array, Random random) {
/* 8671 */     for (int i = array.length; i > 1; i--) {
/* 8672 */       swap(array, i - 1, random.nextInt(i), 1);
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
/*      */   public static <T> boolean isArrayIndexValid(T[] array, int index) {
/* 8685 */     if (getLength(array) == 0 || array.length <= index) {
/* 8686 */       return false;
/*      */     }
/*      */     
/* 8689 */     return (index >= 0);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/ArrayUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */