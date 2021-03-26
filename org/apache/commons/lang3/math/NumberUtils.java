/*      */ package org.apache.commons.lang3.math;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.math.RoundingMode;
/*      */ import org.apache.commons.lang3.StringUtils;
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
/*      */ public class NumberUtils
/*      */ {
/*   35 */   public static final Long LONG_ZERO = Long.valueOf(0L);
/*      */   
/*   37 */   public static final Long LONG_ONE = Long.valueOf(1L);
/*      */   
/*   39 */   public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
/*      */   
/*   41 */   public static final Integer INTEGER_ZERO = Integer.valueOf(0);
/*      */   
/*   43 */   public static final Integer INTEGER_ONE = Integer.valueOf(1);
/*      */   
/*   45 */   public static final Integer INTEGER_TWO = Integer.valueOf(2);
/*      */   
/*   47 */   public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
/*      */   
/*   49 */   public static final Short SHORT_ZERO = Short.valueOf((short)0);
/*      */   
/*   51 */   public static final Short SHORT_ONE = Short.valueOf((short)1);
/*      */   
/*   53 */   public static final Short SHORT_MINUS_ONE = Short.valueOf((short)-1);
/*      */   
/*   55 */   public static final Byte BYTE_ZERO = Byte.valueOf((byte)0);
/*      */   
/*   57 */   public static final Byte BYTE_ONE = Byte.valueOf((byte)1);
/*      */   
/*   59 */   public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte)-1);
/*      */   
/*   61 */   public static final Double DOUBLE_ZERO = Double.valueOf(0.0D);
/*      */   
/*   63 */   public static final Double DOUBLE_ONE = Double.valueOf(1.0D);
/*      */   
/*   65 */   public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0D);
/*      */   
/*   67 */   public static final Float FLOAT_ZERO = Float.valueOf(0.0F);
/*      */   
/*   69 */   public static final Float FLOAT_ONE = Float.valueOf(1.0F);
/*      */   
/*   71 */   public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int toInt(String str) {
/*  104 */     return toInt(str, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int toInt(String str, int defaultValue) {
/*  125 */     if (str == null) {
/*  126 */       return defaultValue;
/*      */     }
/*      */     try {
/*  129 */       return Integer.parseInt(str);
/*  130 */     } catch (NumberFormatException nfe) {
/*  131 */       return defaultValue;
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
/*      */   public static long toLong(String str) {
/*  153 */     return toLong(str, 0L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long toLong(String str, long defaultValue) {
/*  174 */     if (str == null) {
/*  175 */       return defaultValue;
/*      */     }
/*      */     try {
/*  178 */       return Long.parseLong(str);
/*  179 */     } catch (NumberFormatException nfe) {
/*  180 */       return defaultValue;
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
/*      */   public static float toFloat(String str) {
/*  203 */     return toFloat(str, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float toFloat(String str, float defaultValue) {
/*  226 */     if (str == null) {
/*  227 */       return defaultValue;
/*      */     }
/*      */     try {
/*  230 */       return Float.parseFloat(str);
/*  231 */     } catch (NumberFormatException nfe) {
/*  232 */       return defaultValue;
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
/*      */   public static double toDouble(String str) {
/*  255 */     return toDouble(str, 0.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double toDouble(String str, double defaultValue) {
/*  278 */     if (str == null) {
/*  279 */       return defaultValue;
/*      */     }
/*      */     try {
/*  282 */       return Double.parseDouble(str);
/*  283 */     } catch (NumberFormatException nfe) {
/*  284 */       return defaultValue;
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
/*      */   public static double toDouble(BigDecimal value) {
/*  305 */     return toDouble(value, 0.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double toDouble(BigDecimal value, double defaultValue) {
/*  326 */     return (value == null) ? defaultValue : value.doubleValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte toByte(String str) {
/*  348 */     return toByte(str, (byte)0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte toByte(String str, byte defaultValue) {
/*  369 */     if (str == null) {
/*  370 */       return defaultValue;
/*      */     }
/*      */     try {
/*  373 */       return Byte.parseByte(str);
/*  374 */     } catch (NumberFormatException nfe) {
/*  375 */       return defaultValue;
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
/*      */   public static short toShort(String str) {
/*  397 */     return toShort(str, (short)0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short toShort(String str, short defaultValue) {
/*  418 */     if (str == null) {
/*  419 */       return defaultValue;
/*      */     }
/*      */     try {
/*  422 */       return Short.parseShort(str);
/*  423 */     } catch (NumberFormatException nfe) {
/*  424 */       return defaultValue;
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
/*      */   public static BigDecimal toScaledBigDecimal(BigDecimal value) {
/*  441 */     return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(BigDecimal value, int scale, RoundingMode roundingMode) {
/*  457 */     if (value == null) {
/*  458 */       return BigDecimal.ZERO;
/*      */     }
/*  460 */     return value.setScale(scale, 
/*      */         
/*  462 */         (roundingMode == null) ? RoundingMode.HALF_EVEN : roundingMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(Float value) {
/*  479 */     return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(Float value, int scale, RoundingMode roundingMode) {
/*  495 */     if (value == null) {
/*  496 */       return BigDecimal.ZERO;
/*      */     }
/*  498 */     return toScaledBigDecimal(
/*  499 */         BigDecimal.valueOf(value.floatValue()), scale, roundingMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(Double value) {
/*  518 */     return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(Double value, int scale, RoundingMode roundingMode) {
/*  534 */     if (value == null) {
/*  535 */       return BigDecimal.ZERO;
/*      */     }
/*  537 */     return toScaledBigDecimal(
/*  538 */         BigDecimal.valueOf(value.doubleValue()), scale, roundingMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(String value) {
/*  557 */     return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal toScaledBigDecimal(String value, int scale, RoundingMode roundingMode) {
/*  573 */     if (value == null) {
/*  574 */       return BigDecimal.ZERO;
/*      */     }
/*  576 */     return toScaledBigDecimal(
/*  577 */         createBigDecimal(value), scale, roundingMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Number createNumber(String str) {
/*      */     String mant, dec, exp;
/*  651 */     if (str == null) {
/*  652 */       return null;
/*      */     }
/*  654 */     if (StringUtils.isBlank(str)) {
/*  655 */       throw new NumberFormatException("A blank string is not a valid number");
/*      */     }
/*      */     
/*  658 */     String[] hex_prefixes = { "0x", "0X", "-0x", "-0X", "#", "-#" };
/*  659 */     int pfxLen = 0;
/*  660 */     for (String pfx : hex_prefixes) {
/*  661 */       if (str.startsWith(pfx)) {
/*  662 */         pfxLen += pfx.length();
/*      */         break;
/*      */       } 
/*      */     } 
/*  666 */     if (pfxLen > 0) {
/*  667 */       char firstSigDigit = Character.MIN_VALUE;
/*  668 */       for (int i = pfxLen; i < str.length(); ) {
/*  669 */         firstSigDigit = str.charAt(i);
/*  670 */         if (firstSigDigit == '0') {
/*  671 */           pfxLen++;
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*  676 */       int hexDigits = str.length() - pfxLen;
/*  677 */       if (hexDigits > 16 || (hexDigits == 16 && firstSigDigit > '7')) {
/*  678 */         return createBigInteger(str);
/*      */       }
/*  680 */       if (hexDigits > 8 || (hexDigits == 8 && firstSigDigit > '7')) {
/*  681 */         return createLong(str);
/*      */       }
/*  683 */       return createInteger(str);
/*      */     } 
/*  685 */     char lastChar = str.charAt(str.length() - 1);
/*      */ 
/*      */ 
/*      */     
/*  689 */     int decPos = str.indexOf('.');
/*  690 */     int expPos = str.indexOf('e') + str.indexOf('E') + 1;
/*      */ 
/*      */ 
/*      */     
/*  694 */     if (decPos > -1) {
/*  695 */       if (expPos > -1) {
/*  696 */         if (expPos < decPos || expPos > str.length()) {
/*  697 */           throw new NumberFormatException(str + " is not a valid number.");
/*      */         }
/*  699 */         dec = str.substring(decPos + 1, expPos);
/*      */       } else {
/*  701 */         dec = str.substring(decPos + 1);
/*      */       } 
/*  703 */       mant = getMantissa(str, decPos);
/*      */     } else {
/*  705 */       if (expPos > -1) {
/*  706 */         if (expPos > str.length()) {
/*  707 */           throw new NumberFormatException(str + " is not a valid number.");
/*      */         }
/*  709 */         mant = getMantissa(str, expPos);
/*      */       } else {
/*  711 */         mant = getMantissa(str);
/*      */       } 
/*  713 */       dec = null;
/*      */     } 
/*  715 */     if (!Character.isDigit(lastChar) && lastChar != '.') {
/*  716 */       if (expPos > -1 && expPos < str.length() - 1) {
/*  717 */         exp = str.substring(expPos + 1, str.length() - 1);
/*      */       } else {
/*  719 */         exp = null;
/*      */       } 
/*      */       
/*  722 */       String numeric = str.substring(0, str.length() - 1);
/*  723 */       boolean bool = (isAllZeros(mant) && isAllZeros(exp));
/*  724 */       switch (lastChar) {
/*      */         case 'L':
/*      */         case 'l':
/*  727 */           if (dec == null && exp == null && ((
/*      */             
/*  729 */             !numeric.isEmpty() && numeric.charAt(0) == '-' && isDigits(numeric.substring(1))) || isDigits(numeric))) {
/*      */             try {
/*  731 */               return createLong(numeric);
/*  732 */             } catch (NumberFormatException numberFormatException) {
/*      */ 
/*      */               
/*  735 */               return createBigInteger(numeric);
/*      */             } 
/*      */           }
/*  738 */           throw new NumberFormatException(str + " is not a valid number.");
/*      */         case 'F':
/*      */         case 'f':
/*      */           try {
/*  742 */             Float f = createFloat(str);
/*  743 */             if (!f.isInfinite() && (f.floatValue() != 0.0F || bool))
/*      */             {
/*      */               
/*  746 */               return f;
/*      */             }
/*      */           }
/*  749 */           catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */ 
/*      */         
/*      */         case 'D':
/*      */         case 'd':
/*      */           try {
/*  756 */             Double d = createDouble(str);
/*  757 */             if (!d.isInfinite() && (d.floatValue() != 0.0D || bool)) {
/*  758 */               return d;
/*      */             }
/*  760 */           } catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */           
/*      */           try {
/*  764 */             return createBigDecimal(numeric);
/*  765 */           } catch (NumberFormatException numberFormatException) {
/*      */             break;
/*      */           } 
/*      */       } 
/*      */       
/*  770 */       throw new NumberFormatException(str + " is not a valid number.");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  776 */     if (expPos > -1 && expPos < str.length() - 1) {
/*  777 */       exp = str.substring(expPos + 1, str.length());
/*      */     } else {
/*  779 */       exp = null;
/*      */     } 
/*  781 */     if (dec == null && exp == null) {
/*      */       
/*      */       try {
/*  784 */         return createInteger(str);
/*  785 */       } catch (NumberFormatException numberFormatException) {
/*      */ 
/*      */         
/*      */         try {
/*  789 */           return createLong(str);
/*  790 */         } catch (NumberFormatException numberFormatException1) {
/*      */ 
/*      */           
/*  793 */           return createBigInteger(str);
/*      */         } 
/*      */       } 
/*      */     }
/*  797 */     boolean allZeros = (isAllZeros(mant) && isAllZeros(exp));
/*      */     try {
/*  799 */       Float f = createFloat(str);
/*  800 */       Double d = createDouble(str);
/*  801 */       if (!f.isInfinite() && (f
/*  802 */         .floatValue() != 0.0F || allZeros) && f
/*  803 */         .toString().equals(d.toString())) {
/*  804 */         return f;
/*      */       }
/*  806 */       if (!d.isInfinite() && (d.doubleValue() != 0.0D || allZeros)) {
/*  807 */         BigDecimal b = createBigDecimal(str);
/*  808 */         if (b.compareTo(BigDecimal.valueOf(d.doubleValue())) == 0) {
/*  809 */           return d;
/*      */         }
/*  811 */         return b;
/*      */       } 
/*  813 */     } catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */     
/*  816 */     return createBigDecimal(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getMantissa(String str) {
/*  828 */     return getMantissa(str, str.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getMantissa(String str, int stopPos) {
/*  841 */     char firstChar = str.charAt(0);
/*  842 */     boolean hasSign = (firstChar == '-' || firstChar == '+');
/*      */     
/*  844 */     return hasSign ? str.substring(1, stopPos) : str.substring(0, stopPos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isAllZeros(String str) {
/*  856 */     if (str == null) {
/*  857 */       return true;
/*      */     }
/*  859 */     for (int i = str.length() - 1; i >= 0; i--) {
/*  860 */       if (str.charAt(i) != '0') {
/*  861 */         return false;
/*      */       }
/*      */     } 
/*  864 */     return !str.isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Float createFloat(String str) {
/*  878 */     if (str == null) {
/*  879 */       return null;
/*      */     }
/*  881 */     return Float.valueOf(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Double createDouble(String str) {
/*  894 */     if (str == null) {
/*  895 */       return null;
/*      */     }
/*  897 */     return Double.valueOf(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Integer createInteger(String str) {
/*  912 */     if (str == null) {
/*  913 */       return null;
/*      */     }
/*      */     
/*  916 */     return Integer.decode(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Long createLong(String str) {
/*  931 */     if (str == null) {
/*  932 */       return null;
/*      */     }
/*  934 */     return Long.decode(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigInteger createBigInteger(String str) {
/*  948 */     if (str == null) {
/*  949 */       return null;
/*      */     }
/*  951 */     int pos = 0;
/*  952 */     int radix = 10;
/*  953 */     boolean negate = false;
/*  954 */     if (str.startsWith("-")) {
/*  955 */       negate = true;
/*  956 */       pos = 1;
/*      */     } 
/*  958 */     if (str.startsWith("0x", pos) || str.startsWith("0X", pos)) {
/*  959 */       radix = 16;
/*  960 */       pos += 2;
/*  961 */     } else if (str.startsWith("#", pos)) {
/*  962 */       radix = 16;
/*  963 */       pos++;
/*  964 */     } else if (str.startsWith("0", pos) && str.length() > pos + 1) {
/*  965 */       radix = 8;
/*  966 */       pos++;
/*      */     } 
/*      */     
/*  969 */     BigInteger value = new BigInteger(str.substring(pos), radix);
/*  970 */     return negate ? value.negate() : value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BigDecimal createBigDecimal(String str) {
/*  983 */     if (str == null) {
/*  984 */       return null;
/*      */     }
/*      */     
/*  987 */     if (StringUtils.isBlank(str)) {
/*  988 */       throw new NumberFormatException("A blank string is not a valid number");
/*      */     }
/*  990 */     if (str.trim().startsWith("--"))
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  995 */       throw new NumberFormatException(str + " is not a valid number.");
/*      */     }
/*  997 */     return new BigDecimal(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long min(long... array) {
/* 1013 */     validateArray(array);
/*      */ 
/*      */     
/* 1016 */     long min = array[0];
/* 1017 */     for (int i = 1; i < array.length; i++) {
/* 1018 */       if (array[i] < min) {
/* 1019 */         min = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1023 */     return min;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int min(int... array) {
/* 1037 */     validateArray(array);
/*      */ 
/*      */     
/* 1040 */     int min = array[0];
/* 1041 */     for (int j = 1; j < array.length; j++) {
/* 1042 */       if (array[j] < min) {
/* 1043 */         min = array[j];
/*      */       }
/*      */     } 
/*      */     
/* 1047 */     return min;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short min(short... array) {
/* 1061 */     validateArray(array);
/*      */ 
/*      */     
/* 1064 */     short min = array[0];
/* 1065 */     for (int i = 1; i < array.length; i++) {
/* 1066 */       if (array[i] < min) {
/* 1067 */         min = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1071 */     return min;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte min(byte... array) {
/* 1085 */     validateArray(array);
/*      */ 
/*      */     
/* 1088 */     byte min = array[0];
/* 1089 */     for (int i = 1; i < array.length; i++) {
/* 1090 */       if (array[i] < min) {
/* 1091 */         min = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1095 */     return min;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double min(double... array) {
/* 1110 */     validateArray(array);
/*      */ 
/*      */     
/* 1113 */     double min = array[0];
/* 1114 */     for (int i = 1; i < array.length; i++) {
/* 1115 */       if (Double.isNaN(array[i])) {
/* 1116 */         return Double.NaN;
/*      */       }
/* 1118 */       if (array[i] < min) {
/* 1119 */         min = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1123 */     return min;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float min(float... array) {
/* 1138 */     validateArray(array);
/*      */ 
/*      */     
/* 1141 */     float min = array[0];
/* 1142 */     for (int i = 1; i < array.length; i++) {
/* 1143 */       if (Float.isNaN(array[i])) {
/* 1144 */         return Float.NaN;
/*      */       }
/* 1146 */       if (array[i] < min) {
/* 1147 */         min = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1151 */     return min;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long max(long... array) {
/* 1167 */     validateArray(array);
/*      */ 
/*      */     
/* 1170 */     long max = array[0];
/* 1171 */     for (int j = 1; j < array.length; j++) {
/* 1172 */       if (array[j] > max) {
/* 1173 */         max = array[j];
/*      */       }
/*      */     } 
/*      */     
/* 1177 */     return max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int max(int... array) {
/* 1191 */     validateArray(array);
/*      */ 
/*      */     
/* 1194 */     int max = array[0];
/* 1195 */     for (int j = 1; j < array.length; j++) {
/* 1196 */       if (array[j] > max) {
/* 1197 */         max = array[j];
/*      */       }
/*      */     } 
/*      */     
/* 1201 */     return max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short max(short... array) {
/* 1215 */     validateArray(array);
/*      */ 
/*      */     
/* 1218 */     short max = array[0];
/* 1219 */     for (int i = 1; i < array.length; i++) {
/* 1220 */       if (array[i] > max) {
/* 1221 */         max = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1225 */     return max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte max(byte... array) {
/* 1239 */     validateArray(array);
/*      */ 
/*      */     
/* 1242 */     byte max = array[0];
/* 1243 */     for (int i = 1; i < array.length; i++) {
/* 1244 */       if (array[i] > max) {
/* 1245 */         max = array[i];
/*      */       }
/*      */     } 
/*      */     
/* 1249 */     return max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double max(double... array) {
/* 1264 */     validateArray(array);
/*      */ 
/*      */     
/* 1267 */     double max = array[0];
/* 1268 */     for (int j = 1; j < array.length; j++) {
/* 1269 */       if (Double.isNaN(array[j])) {
/* 1270 */         return Double.NaN;
/*      */       }
/* 1272 */       if (array[j] > max) {
/* 1273 */         max = array[j];
/*      */       }
/*      */     } 
/*      */     
/* 1277 */     return max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float max(float... array) {
/* 1292 */     validateArray(array);
/*      */ 
/*      */     
/* 1295 */     float max = array[0];
/* 1296 */     for (int j = 1; j < array.length; j++) {
/* 1297 */       if (Float.isNaN(array[j])) {
/* 1298 */         return Float.NaN;
/*      */       }
/* 1300 */       if (array[j] > max) {
/* 1301 */         max = array[j];
/*      */       }
/*      */     } 
/*      */     
/* 1305 */     return max;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void validateArray(Object array) {
/* 1315 */     Validate.isTrue((array != null), "The Array must not be null", new Object[0]);
/* 1316 */     Validate.isTrue((Array.getLength(array) != 0), "Array cannot be empty.", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long min(long a, long b, long c) {
/* 1330 */     if (b < a) {
/* 1331 */       a = b;
/*      */     }
/* 1333 */     if (c < a) {
/* 1334 */       a = c;
/*      */     }
/* 1336 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int min(int a, int b, int c) {
/* 1348 */     if (b < a) {
/* 1349 */       a = b;
/*      */     }
/* 1351 */     if (c < a) {
/* 1352 */       a = c;
/*      */     }
/* 1354 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short min(short a, short b, short c) {
/* 1366 */     if (b < a) {
/* 1367 */       a = b;
/*      */     }
/* 1369 */     if (c < a) {
/* 1370 */       a = c;
/*      */     }
/* 1372 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte min(byte a, byte b, byte c) {
/* 1384 */     if (b < a) {
/* 1385 */       a = b;
/*      */     }
/* 1387 */     if (c < a) {
/* 1388 */       a = c;
/*      */     }
/* 1390 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double min(double a, double b, double c) {
/* 1406 */     return Math.min(Math.min(a, b), c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float min(float a, float b, float c) {
/* 1422 */     return Math.min(Math.min(a, b), c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long max(long a, long b, long c) {
/* 1436 */     if (b > a) {
/* 1437 */       a = b;
/*      */     }
/* 1439 */     if (c > a) {
/* 1440 */       a = c;
/*      */     }
/* 1442 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int max(int a, int b, int c) {
/* 1454 */     if (b > a) {
/* 1455 */       a = b;
/*      */     }
/* 1457 */     if (c > a) {
/* 1458 */       a = c;
/*      */     }
/* 1460 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short max(short a, short b, short c) {
/* 1472 */     if (b > a) {
/* 1473 */       a = b;
/*      */     }
/* 1475 */     if (c > a) {
/* 1476 */       a = c;
/*      */     }
/* 1478 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte max(byte a, byte b, byte c) {
/* 1490 */     if (b > a) {
/* 1491 */       a = b;
/*      */     }
/* 1493 */     if (c > a) {
/* 1494 */       a = c;
/*      */     }
/* 1496 */     return a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double max(double a, double b, double c) {
/* 1512 */     return Math.max(Math.max(a, b), c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float max(float a, float b, float c) {
/* 1528 */     return Math.max(Math.max(a, b), c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isDigits(String str) {
/* 1543 */     return StringUtils.isNumeric(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static boolean isNumber(String str) {
/* 1573 */     return isCreatable(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCreatable(String str) {
/* 1599 */     if (StringUtils.isEmpty(str)) {
/* 1600 */       return false;
/*      */     }
/* 1602 */     char[] chars = str.toCharArray();
/* 1603 */     int sz = chars.length;
/* 1604 */     boolean hasExp = false;
/* 1605 */     boolean hasDecPoint = false;
/* 1606 */     boolean allowSigns = false;
/* 1607 */     boolean foundDigit = false;
/*      */     
/* 1609 */     int start = (chars[0] == '-' || chars[0] == '+') ? 1 : 0;
/* 1610 */     if (sz > start + 1 && chars[start] == '0' && !StringUtils.contains(str, 46)) {
/* 1611 */       if (chars[start + 1] == 'x' || chars[start + 1] == 'X') {
/* 1612 */         int j = start + 2;
/* 1613 */         if (j == sz) {
/* 1614 */           return false;
/*      */         }
/*      */         
/* 1617 */         for (; j < chars.length; j++) {
/* 1618 */           if ((chars[j] < '0' || chars[j] > '9') && (chars[j] < 'a' || chars[j] > 'f') && (chars[j] < 'A' || chars[j] > 'F'))
/*      */           {
/*      */             
/* 1621 */             return false;
/*      */           }
/*      */         } 
/* 1624 */         return true;
/* 1625 */       }  if (Character.isDigit(chars[start + 1])) {
/*      */         
/* 1627 */         int j = start + 1;
/* 1628 */         for (; j < chars.length; j++) {
/* 1629 */           if (chars[j] < '0' || chars[j] > '7') {
/* 1630 */             return false;
/*      */           }
/*      */         } 
/* 1633 */         return true;
/*      */       } 
/*      */     } 
/* 1636 */     sz--;
/*      */     
/* 1638 */     int i = start;
/*      */ 
/*      */     
/* 1641 */     while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
/* 1642 */       if (chars[i] >= '0' && chars[i] <= '9') {
/* 1643 */         foundDigit = true;
/* 1644 */         allowSigns = false;
/*      */       }
/* 1646 */       else if (chars[i] == '.') {
/* 1647 */         if (hasDecPoint || hasExp)
/*      */         {
/* 1649 */           return false;
/*      */         }
/* 1651 */         hasDecPoint = true;
/* 1652 */       } else if (chars[i] == 'e' || chars[i] == 'E') {
/*      */         
/* 1654 */         if (hasExp)
/*      */         {
/* 1656 */           return false;
/*      */         }
/* 1658 */         if (!foundDigit) {
/* 1659 */           return false;
/*      */         }
/* 1661 */         hasExp = true;
/* 1662 */         allowSigns = true;
/* 1663 */       } else if (chars[i] == '+' || chars[i] == '-') {
/* 1664 */         if (!allowSigns) {
/* 1665 */           return false;
/*      */         }
/* 1667 */         allowSigns = false;
/* 1668 */         foundDigit = false;
/*      */       } else {
/* 1670 */         return false;
/*      */       } 
/* 1672 */       i++;
/*      */     } 
/* 1674 */     if (i < chars.length) {
/* 1675 */       if (chars[i] >= '0' && chars[i] <= '9')
/*      */       {
/* 1677 */         return true;
/*      */       }
/* 1679 */       if (chars[i] == 'e' || chars[i] == 'E')
/*      */       {
/* 1681 */         return false;
/*      */       }
/* 1683 */       if (chars[i] == '.') {
/* 1684 */         if (hasDecPoint || hasExp)
/*      */         {
/* 1686 */           return false;
/*      */         }
/*      */         
/* 1689 */         return foundDigit;
/*      */       } 
/* 1691 */       if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F'))
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1696 */         return foundDigit;
/*      */       }
/* 1698 */       if (chars[i] == 'l' || chars[i] == 'L')
/*      */       {
/*      */         
/* 1701 */         return (foundDigit && !hasExp && !hasDecPoint);
/*      */       }
/*      */       
/* 1704 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1708 */     return (!allowSigns && foundDigit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isParsable(String str) {
/* 1729 */     if (StringUtils.isEmpty(str)) {
/* 1730 */       return false;
/*      */     }
/* 1732 */     if (str.charAt(str.length() - 1) == '.') {
/* 1733 */       return false;
/*      */     }
/* 1735 */     if (str.charAt(0) == '-') {
/* 1736 */       if (str.length() == 1) {
/* 1737 */         return false;
/*      */       }
/* 1739 */       return withDecimalsParsing(str, 1);
/*      */     } 
/* 1741 */     return withDecimalsParsing(str, 0);
/*      */   }
/*      */   
/*      */   private static boolean withDecimalsParsing(String str, int beginIdx) {
/* 1745 */     int decimalPoints = 0;
/* 1746 */     for (int i = beginIdx; i < str.length(); i++) {
/* 1747 */       boolean isDecimalPoint = (str.charAt(i) == '.');
/* 1748 */       if (isDecimalPoint) {
/* 1749 */         decimalPoints++;
/*      */       }
/* 1751 */       if (decimalPoints > 1) {
/* 1752 */         return false;
/*      */       }
/* 1754 */       if (!isDecimalPoint && !Character.isDigit(str.charAt(i))) {
/* 1755 */         return false;
/*      */       }
/*      */     } 
/* 1758 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compare(int x, int y) {
/* 1772 */     if (x == y) {
/* 1773 */       return 0;
/*      */     }
/* 1775 */     return (x < y) ? -1 : 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compare(long x, long y) {
/* 1789 */     if (x == y) {
/* 1790 */       return 0;
/*      */     }
/* 1792 */     return (x < y) ? -1 : 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compare(short x, short y) {
/* 1806 */     if (x == y) {
/* 1807 */       return 0;
/*      */     }
/* 1809 */     return (x < y) ? -1 : 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compare(byte x, byte y) {
/* 1823 */     return x - y;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/math/NumberUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */