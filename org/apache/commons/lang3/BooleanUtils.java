/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import org.apache.commons.lang3.math.NumberUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BooleanUtils
/*      */ {
/*      */   public static Boolean negate(Boolean bool) {
/*   63 */     if (bool == null) {
/*   64 */       return null;
/*      */     }
/*   66 */     return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isTrue(Boolean bool) {
/*   86 */     return Boolean.TRUE.equals(bool);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotTrue(Boolean bool) {
/*  104 */     return !isTrue(bool);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFalse(Boolean bool) {
/*  122 */     return Boolean.FALSE.equals(bool);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotFalse(Boolean bool) {
/*  140 */     return !isFalse(bool);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBoolean(Boolean bool) {
/*  158 */     return (bool != null && bool.booleanValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull) {
/*  175 */     if (bool == null) {
/*  176 */       return valueIfNull;
/*      */     }
/*  178 */     return bool.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBoolean(int value) {
/*  198 */     return (value != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean toBooleanObject(int value) {
/*  216 */     return (value == 0) ? Boolean.FALSE : Boolean.TRUE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean toBooleanObject(Integer value) {
/*  238 */     if (value == null) {
/*  239 */       return null;
/*      */     }
/*  241 */     return (value.intValue() == 0) ? Boolean.FALSE : Boolean.TRUE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBoolean(int value, int trueValue, int falseValue) {
/*  261 */     if (value == trueValue) {
/*  262 */       return true;
/*      */     }
/*  264 */     if (value == falseValue) {
/*  265 */       return false;
/*      */     }
/*      */     
/*  268 */     throw new IllegalArgumentException("The Integer did not match either specified value");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue) {
/*  289 */     if (value == null) {
/*  290 */       if (trueValue == null) {
/*  291 */         return true;
/*      */       }
/*  293 */       if (falseValue == null)
/*  294 */         return false; 
/*      */     } else {
/*  296 */       if (value.equals(trueValue))
/*  297 */         return true; 
/*  298 */       if (value.equals(falseValue)) {
/*  299 */         return false;
/*      */       }
/*      */     } 
/*  302 */     throw new IllegalArgumentException("The Integer did not match either specified value");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue) {
/*  324 */     if (value == trueValue) {
/*  325 */       return Boolean.TRUE;
/*      */     }
/*  327 */     if (value == falseValue) {
/*  328 */       return Boolean.FALSE;
/*      */     }
/*  330 */     if (value == nullValue) {
/*  331 */       return null;
/*      */     }
/*      */     
/*  334 */     throw new IllegalArgumentException("The Integer did not match any specified value");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue) {
/*  356 */     if (value == null) {
/*  357 */       if (trueValue == null) {
/*  358 */         return Boolean.TRUE;
/*      */       }
/*  360 */       if (falseValue == null) {
/*  361 */         return Boolean.FALSE;
/*      */       }
/*  363 */       if (nullValue == null)
/*  364 */         return null; 
/*      */     } else {
/*  366 */       if (value.equals(trueValue))
/*  367 */         return Boolean.TRUE; 
/*  368 */       if (value.equals(falseValue))
/*  369 */         return Boolean.FALSE; 
/*  370 */       if (value.equals(nullValue)) {
/*  371 */         return null;
/*      */       }
/*      */     } 
/*  374 */     throw new IllegalArgumentException("The Integer did not match any specified value");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int toInteger(boolean bool) {
/*  392 */     return bool ? 1 : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer toIntegerObject(boolean bool) {
/*  408 */     return bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer toIntegerObject(Boolean bool) {
/*  426 */     if (bool == null) {
/*  427 */       return null;
/*      */     }
/*  429 */     return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int toInteger(boolean bool, int trueValue, int falseValue) {
/*  446 */     return bool ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue) {
/*  465 */     if (bool == null) {
/*  466 */       return nullValue;
/*      */     }
/*  468 */     return bool.booleanValue() ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue) {
/*  485 */     return bool ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue) {
/*  504 */     if (bool == null) {
/*  505 */       return nullValue;
/*      */     }
/*  507 */     return bool.booleanValue() ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean toBooleanObject(String str) {
/*      */     char ch0;
/*      */     char ch1;
/*      */     char ch2;
/*      */     char ch3;
/*      */     char ch4;
/*  553 */     if (str == "true") {
/*  554 */       return Boolean.TRUE;
/*      */     }
/*  556 */     if (str == null) {
/*  557 */       return null;
/*      */     }
/*  559 */     switch (str.length()) {
/*      */       case 1:
/*  561 */         ch0 = str.charAt(0);
/*  562 */         if (ch0 == 'y' || ch0 == 'Y' || ch0 == 't' || ch0 == 'T')
/*      */         {
/*  564 */           return Boolean.TRUE;
/*      */         }
/*  566 */         if (ch0 == 'n' || ch0 == 'N' || ch0 == 'f' || ch0 == 'F')
/*      */         {
/*  568 */           return Boolean.FALSE;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 2:
/*  573 */         ch0 = str.charAt(0);
/*  574 */         ch1 = str.charAt(1);
/*  575 */         if ((ch0 == 'o' || ch0 == 'O') && (ch1 == 'n' || ch1 == 'N'))
/*      */         {
/*  577 */           return Boolean.TRUE;
/*      */         }
/*  579 */         if ((ch0 == 'n' || ch0 == 'N') && (ch1 == 'o' || ch1 == 'O'))
/*      */         {
/*  581 */           return Boolean.FALSE;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 3:
/*  586 */         ch0 = str.charAt(0);
/*  587 */         ch1 = str.charAt(1);
/*  588 */         ch2 = str.charAt(2);
/*  589 */         if ((ch0 == 'y' || ch0 == 'Y') && (ch1 == 'e' || ch1 == 'E') && (ch2 == 's' || ch2 == 'S'))
/*      */         {
/*      */           
/*  592 */           return Boolean.TRUE;
/*      */         }
/*  594 */         if ((ch0 == 'o' || ch0 == 'O') && (ch1 == 'f' || ch1 == 'F') && (ch2 == 'f' || ch2 == 'F'))
/*      */         {
/*      */           
/*  597 */           return Boolean.FALSE;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 4:
/*  602 */         ch0 = str.charAt(0);
/*  603 */         ch1 = str.charAt(1);
/*  604 */         ch2 = str.charAt(2);
/*  605 */         ch3 = str.charAt(3);
/*  606 */         if ((ch0 == 't' || ch0 == 'T') && (ch1 == 'r' || ch1 == 'R') && (ch2 == 'u' || ch2 == 'U') && (ch3 == 'e' || ch3 == 'E'))
/*      */         {
/*      */ 
/*      */           
/*  610 */           return Boolean.TRUE;
/*      */         }
/*      */         break;
/*      */       
/*      */       case 5:
/*  615 */         ch0 = str.charAt(0);
/*  616 */         ch1 = str.charAt(1);
/*  617 */         ch2 = str.charAt(2);
/*  618 */         ch3 = str.charAt(3);
/*  619 */         ch4 = str.charAt(4);
/*  620 */         if ((ch0 == 'f' || ch0 == 'F') && (ch1 == 'a' || ch1 == 'A') && (ch2 == 'l' || ch2 == 'L') && (ch3 == 's' || ch3 == 'S') && (ch4 == 'e' || ch4 == 'E'))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  625 */           return Boolean.FALSE;
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  633 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString) {
/*  656 */     if (str == null) {
/*  657 */       if (trueString == null) {
/*  658 */         return Boolean.TRUE;
/*      */       }
/*  660 */       if (falseString == null) {
/*  661 */         return Boolean.FALSE;
/*      */       }
/*  663 */       if (nullString == null)
/*  664 */         return null; 
/*      */     } else {
/*  666 */       if (str.equals(trueString))
/*  667 */         return Boolean.TRUE; 
/*  668 */       if (str.equals(falseString))
/*  669 */         return Boolean.FALSE; 
/*  670 */       if (str.equals(nullString)) {
/*  671 */         return null;
/*      */       }
/*      */     } 
/*  674 */     throw new IllegalArgumentException("The String did not match any specified value");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBoolean(String str) {
/*  709 */     return (toBooleanObject(str) == Boolean.TRUE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean toBoolean(String str, String trueString, String falseString) {
/*  727 */     if (str == trueString)
/*  728 */       return true; 
/*  729 */     if (str == falseString)
/*  730 */       return false; 
/*  731 */     if (str != null) {
/*  732 */       if (str.equals(trueString))
/*  733 */         return true; 
/*  734 */       if (str.equals(falseString)) {
/*  735 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  739 */     throw new IllegalArgumentException("The String did not match either specified value");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toStringTrueFalse(Boolean bool) {
/*  758 */     return toString(bool, "true", "false", null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toStringOnOff(Boolean bool) {
/*  775 */     return toString(bool, "on", "off", null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toStringYesNo(Boolean bool) {
/*  792 */     return toString(bool, "yes", "no", null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(Boolean bool, String trueString, String falseString, String nullString) {
/*  811 */     if (bool == null) {
/*  812 */       return nullString;
/*      */     }
/*  814 */     return bool.booleanValue() ? trueString : falseString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toStringTrueFalse(boolean bool) {
/*  832 */     return toString(bool, "true", "false");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toStringOnOff(boolean bool) {
/*  848 */     return toString(bool, "on", "off");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toStringYesNo(boolean bool) {
/*  864 */     return toString(bool, "yes", "no");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(boolean bool, String trueString, String falseString) {
/*  881 */     return bool ? trueString : falseString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean and(boolean... array) {
/*  905 */     if (array == null) {
/*  906 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  908 */     if (array.length == 0) {
/*  909 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*  911 */     for (boolean element : array) {
/*  912 */       if (!element) {
/*  913 */         return false;
/*      */       }
/*      */     } 
/*  916 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean and(Boolean... array) {
/*  939 */     if (array == null) {
/*  940 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  942 */     if (array.length == 0) {
/*  943 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*      */     try {
/*  946 */       boolean[] primitive = ArrayUtils.toPrimitive(array);
/*  947 */       return and(primitive) ? Boolean.TRUE : Boolean.FALSE;
/*  948 */     } catch (NullPointerException ex) {
/*  949 */       throw new IllegalArgumentException("The array must not contain any null elements");
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
/*      */   public static boolean or(boolean... array) {
/*  973 */     if (array == null) {
/*  974 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  976 */     if (array.length == 0) {
/*  977 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*  979 */     for (boolean element : array) {
/*  980 */       if (element) {
/*  981 */         return true;
/*      */       }
/*      */     } 
/*  984 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean or(Boolean... array) {
/* 1008 */     if (array == null) {
/* 1009 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1011 */     if (array.length == 0) {
/* 1012 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*      */     try {
/* 1015 */       boolean[] primitive = ArrayUtils.toPrimitive(array);
/* 1016 */       return or(primitive) ? Boolean.TRUE : Boolean.FALSE;
/* 1017 */     } catch (NullPointerException ex) {
/* 1018 */       throw new IllegalArgumentException("The array must not contain any null elements");
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
/*      */   public static boolean xor(boolean... array) {
/* 1038 */     if (array == null) {
/* 1039 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1041 */     if (array.length == 0) {
/* 1042 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*      */ 
/*      */     
/* 1046 */     boolean result = false;
/* 1047 */     for (boolean element : array) {
/* 1048 */       result ^= element;
/*      */     }
/*      */     
/* 1051 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Boolean xor(Boolean... array) {
/* 1070 */     if (array == null) {
/* 1071 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1073 */     if (array.length == 0) {
/* 1074 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*      */     try {
/* 1077 */       boolean[] primitive = ArrayUtils.toPrimitive(array);
/* 1078 */       return xor(primitive) ? Boolean.TRUE : Boolean.FALSE;
/* 1079 */     } catch (NullPointerException ex) {
/* 1080 */       throw new IllegalArgumentException("The array must not contain any null elements");
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
/*      */   public static int compare(boolean x, boolean y) {
/* 1095 */     if (x == y) {
/* 1096 */       return 0;
/*      */     }
/* 1098 */     return x ? 1 : -1;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/BooleanUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */