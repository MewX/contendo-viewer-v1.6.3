/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.TreeSet;
/*      */ import org.apache.commons.lang3.exception.CloneFailedException;
/*      */ import org.apache.commons.lang3.mutable.MutableInt;
/*      */ import org.apache.commons.lang3.text.StrBuilder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ObjectUtils
/*      */ {
/*      */   private static final char AT_SIGN = '@';
/*   67 */   public static final Null NULL = new Null();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isEmpty(Object object) {
/*  109 */     if (object == null) {
/*  110 */       return true;
/*      */     }
/*  112 */     if (object instanceof CharSequence) {
/*  113 */       return (((CharSequence)object).length() == 0);
/*      */     }
/*  115 */     if (object.getClass().isArray()) {
/*  116 */       return (Array.getLength(object) == 0);
/*      */     }
/*  118 */     if (object instanceof Collection) {
/*  119 */       return ((Collection)object).isEmpty();
/*      */     }
/*  121 */     if (object instanceof Map) {
/*  122 */       return ((Map)object).isEmpty();
/*      */     }
/*  124 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(Object object) {
/*  153 */     return !isEmpty(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> T defaultIfNull(T object, T defaultValue) {
/*  175 */     return (object != null) ? object : defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T> T firstNonNull(T... values) {
/*  202 */     if (values != null) {
/*  203 */       for (T val : values) {
/*  204 */         if (val != null) {
/*  205 */           return val;
/*      */         }
/*      */       } 
/*      */     }
/*  209 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean anyNotNull(Object... values) {
/*  236 */     return (firstNonNull(values) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean allNotNull(Object... values) {
/*  265 */     if (values == null) {
/*  266 */       return false;
/*      */     }
/*      */     
/*  269 */     for (Object val : values) {
/*  270 */       if (val == null) {
/*  271 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  275 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static boolean equals(Object object1, Object object2) {
/*  303 */     if (object1 == object2) {
/*  304 */       return true;
/*      */     }
/*  306 */     if (object1 == null || object2 == null) {
/*  307 */       return false;
/*      */     }
/*  309 */     return object1.equals(object2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean notEqual(Object object1, Object object2) {
/*  332 */     return !equals(object1, object2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static int hashCode(Object obj) {
/*  353 */     return (obj == null) ? 0 : obj.hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static int hashCodeMulti(Object... objects) {
/*  380 */     int hash = 1;
/*  381 */     if (objects != null) {
/*  382 */       for (Object object : objects) {
/*  383 */         int tmpHash = hashCode(object);
/*  384 */         hash = hash * 31 + tmpHash;
/*      */       } 
/*      */     }
/*  387 */     return hash;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String identityToString(Object object) {
/*  409 */     if (object == null) {
/*  410 */       return null;
/*      */     }
/*  412 */     String name = object.getClass().getName();
/*  413 */     String hexString = Integer.toHexString(System.identityHashCode(object));
/*  414 */     StringBuilder builder = new StringBuilder(name.length() + 1 + hexString.length());
/*      */     
/*  416 */     builder.append(name)
/*  417 */       .append('@')
/*  418 */       .append(hexString);
/*      */     
/*  420 */     return builder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void identityToString(Appendable appendable, Object object) throws IOException {
/*  440 */     Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
/*  441 */     appendable.append(object.getClass().getName())
/*  442 */       .append('@')
/*  443 */       .append(Integer.toHexString(System.identityHashCode(object)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static void identityToString(StrBuilder builder, Object object) {
/*  465 */     Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
/*  466 */     String name = object.getClass().getName();
/*  467 */     String hexString = Integer.toHexString(System.identityHashCode(object));
/*  468 */     builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
/*  469 */     builder.append(name)
/*  470 */       .append('@')
/*  471 */       .append(hexString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void identityToString(StringBuffer buffer, Object object) {
/*  490 */     Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
/*  491 */     String name = object.getClass().getName();
/*  492 */     String hexString = Integer.toHexString(System.identityHashCode(object));
/*  493 */     buffer.ensureCapacity(buffer.length() + name.length() + 1 + hexString.length());
/*  494 */     buffer.append(name)
/*  495 */       .append('@')
/*  496 */       .append(hexString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void identityToString(StringBuilder builder, Object object) {
/*  515 */     Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
/*  516 */     String name = object.getClass().getName();
/*  517 */     String hexString = Integer.toHexString(System.identityHashCode(object));
/*  518 */     builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
/*  519 */     builder.append(name)
/*  520 */       .append('@')
/*  521 */       .append(hexString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String toString(Object obj) {
/*  548 */     return (obj == null) ? "" : obj.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String toString(Object obj, String nullStr) {
/*  574 */     return (obj == null) ? nullStr : obj.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T extends Comparable<? super T>> T min(T... values) {
/*  594 */     T result = null;
/*  595 */     if (values != null) {
/*  596 */       for (T value : values) {
/*  597 */         if (compare(value, result, true) < 0) {
/*  598 */           result = value;
/*      */         }
/*      */       } 
/*      */     }
/*  602 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T extends Comparable<? super T>> T max(T... values) {
/*  620 */     T result = null;
/*  621 */     if (values != null) {
/*  622 */       for (T value : values) {
/*  623 */         if (compare(value, result, false) > 0) {
/*  624 */           result = value;
/*      */         }
/*      */       } 
/*      */     }
/*  628 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
/*  642 */     return compare(c1, c2, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
/*  659 */     if (c1 == c2)
/*  660 */       return 0; 
/*  661 */     if (c1 == null)
/*  662 */       return nullGreater ? 1 : -1; 
/*  663 */     if (c2 == null) {
/*  664 */       return nullGreater ? -1 : 1;
/*      */     }
/*  666 */     return c1.compareTo(c2);
/*      */   }
/*      */ 
/*      */ 
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
/*      */   public static <T extends Comparable<? super T>> T median(T... items) {
/*  681 */     Validate.notEmpty(items);
/*  682 */     Validate.noNullElements(items);
/*  683 */     TreeSet<T> sort = new TreeSet<>();
/*  684 */     Collections.addAll(sort, items);
/*      */     
/*  686 */     return (T)sort.toArray()[(sort.size() - 1) / 2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T> T median(Comparator<T> comparator, T... items) {
/*  703 */     Validate.notEmpty(items, "null/empty items", new Object[0]);
/*  704 */     Validate.noNullElements(items);
/*  705 */     Validate.notNull(comparator, "null comparator", new Object[0]);
/*  706 */     TreeSet<T> sort = new TreeSet<>(comparator);
/*  707 */     Collections.addAll(sort, items);
/*      */ 
/*      */     
/*  710 */     T result = (T)sort.toArray()[(sort.size() - 1) / 2];
/*  711 */     return result;
/*      */   }
/*      */ 
/*      */ 
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
/*      */   public static <T> T mode(T... items) {
/*  726 */     if (ArrayUtils.isNotEmpty(items)) {
/*  727 */       HashMap<T, MutableInt> occurrences = new HashMap<>(items.length);
/*  728 */       for (T t : items) {
/*  729 */         MutableInt count = occurrences.get(t);
/*  730 */         if (count == null) {
/*  731 */           occurrences.put(t, new MutableInt(1));
/*      */         } else {
/*  733 */           count.increment();
/*      */         } 
/*      */       } 
/*  736 */       T result = null;
/*  737 */       int max = 0;
/*  738 */       for (Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
/*  739 */         int cmp = ((MutableInt)e.getValue()).intValue();
/*  740 */         if (cmp == max) {
/*  741 */           result = null; continue;
/*  742 */         }  if (cmp > max) {
/*  743 */           max = cmp;
/*  744 */           result = e.getKey();
/*      */         } 
/*      */       } 
/*  747 */       return result;
/*      */     } 
/*  749 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> T clone(T obj) {
/*  764 */     if (obj instanceof Cloneable) {
/*      */       Object result;
/*  766 */       if (obj.getClass().isArray()) {
/*  767 */         Class<?> componentType = obj.getClass().getComponentType();
/*  768 */         if (componentType.isPrimitive()) {
/*  769 */           int length = Array.getLength(obj);
/*  770 */           result = Array.newInstance(componentType, length);
/*  771 */           while (length-- > 0) {
/*  772 */             Array.set(result, length, Array.get(obj, length));
/*      */           }
/*      */         } else {
/*  775 */           result = ((Object[])obj).clone();
/*      */         } 
/*      */       } else {
/*      */         try {
/*  779 */           Method clone = obj.getClass().getMethod("clone", new Class[0]);
/*  780 */           result = clone.invoke(obj, new Object[0]);
/*  781 */         } catch (NoSuchMethodException e) {
/*  782 */           throw new CloneFailedException("Cloneable type " + obj
/*  783 */               .getClass().getName() + " has no clone method", e);
/*      */         }
/*  785 */         catch (IllegalAccessException e) {
/*  786 */           throw new CloneFailedException("Cannot clone Cloneable type " + obj
/*  787 */               .getClass().getName(), e);
/*  788 */         } catch (InvocationTargetException e) {
/*  789 */           throw new CloneFailedException("Exception cloning Cloneable type " + obj
/*  790 */               .getClass().getName(), e.getCause());
/*      */         } 
/*      */       } 
/*      */       
/*  794 */       T checked = (T)result;
/*  795 */       return checked;
/*      */     } 
/*      */     
/*  798 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> T cloneIfPossible(T obj) {
/*  818 */     T clone = clone(obj);
/*  819 */     return (clone == null) ? obj : clone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Null
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 7092611880189329093L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Object readResolve() {
/*  858 */       return ObjectUtils.NULL;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean CONST(boolean v) {
/*  902 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte CONST(byte v) {
/*  923 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte CONST_BYTE(int v) {
/*  947 */     if (v < -128 || v > 127) {
/*  948 */       throw new IllegalArgumentException("Supplied value must be a valid byte literal between -128 and 127: [" + v + "]");
/*      */     }
/*  950 */     return (byte)v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static char CONST(char v) {
/*  971 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short CONST(short v) {
/*  992 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short CONST_SHORT(int v) {
/* 1016 */     if (v < -32768 || v > 32767) {
/* 1017 */       throw new IllegalArgumentException("Supplied value must be a valid byte literal between -32768 and 32767: [" + v + "]");
/*      */     }
/* 1019 */     return (short)v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int CONST(int v) {
/* 1041 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long CONST(long v) {
/* 1062 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float CONST(float v) {
/* 1083 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double CONST(double v) {
/* 1104 */     return v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> T CONST(T v) {
/* 1126 */     return v;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/ObjectUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */