/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.text.Normalizer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Objects;
/*      */ import java.util.regex.Pattern;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StringUtils
/*      */ {
/*      */   private static final int STRING_BUILDER_SIZE = 256;
/*      */   public static final String SPACE = " ";
/*      */   public static final String EMPTY = "";
/*      */   public static final String LF = "\n";
/*      */   public static final String CR = "\r";
/*      */   public static final int INDEX_NOT_FOUND = -1;
/*      */   private static final int PAD_LIMIT = 8192;
/*      */   
/*      */   public static boolean isEmpty(CharSequence cs) {
/*  213 */     return (cs == null || cs.length() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotEmpty(CharSequence cs) {
/*  232 */     return !isEmpty(cs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAnyEmpty(CharSequence... css) {
/*  256 */     if (ArrayUtils.isEmpty((Object[])css)) {
/*  257 */       return false;
/*      */     }
/*  259 */     for (CharSequence cs : css) {
/*  260 */       if (isEmpty(cs)) {
/*  261 */         return true;
/*      */       }
/*      */     } 
/*  264 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNoneEmpty(CharSequence... css) {
/*  288 */     return !isAnyEmpty(css);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAllEmpty(CharSequence... css) {
/*  311 */     if (ArrayUtils.isEmpty((Object[])css)) {
/*  312 */       return true;
/*      */     }
/*  314 */     for (CharSequence cs : css) {
/*  315 */       if (isNotEmpty(cs)) {
/*  316 */         return false;
/*      */       }
/*      */     } 
/*  319 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isBlank(CharSequence cs) {
/*      */     int strLen;
/*  342 */     if (cs == null || (strLen = cs.length()) == 0) {
/*  343 */       return true;
/*      */     }
/*  345 */     for (int i = 0; i < strLen; i++) {
/*  346 */       if (!Character.isWhitespace(cs.charAt(i))) {
/*  347 */         return false;
/*      */       }
/*      */     } 
/*  350 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNotBlank(CharSequence cs) {
/*  373 */     return !isBlank(cs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAnyBlank(CharSequence... css) {
/*  400 */     if (ArrayUtils.isEmpty((Object[])css)) {
/*  401 */       return false;
/*      */     }
/*  403 */     for (CharSequence cs : css) {
/*  404 */       if (isBlank(cs)) {
/*  405 */         return true;
/*      */       }
/*      */     } 
/*  408 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNoneBlank(CharSequence... css) {
/*  435 */     return !isAnyBlank(css);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAllBlank(CharSequence... css) {
/*  460 */     if (ArrayUtils.isEmpty((Object[])css)) {
/*  461 */       return true;
/*      */     }
/*  463 */     for (CharSequence cs : css) {
/*  464 */       if (isNotBlank(cs)) {
/*  465 */         return false;
/*      */       }
/*      */     } 
/*  468 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trim(String str) {
/*  497 */     return (str == null) ? null : str.trim();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimToNull(String str) {
/*  523 */     String ts = trim(str);
/*  524 */     return isEmpty(ts) ? null : ts;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimToEmpty(String str) {
/*  549 */     return (str == null) ? "" : str.trim();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String truncate(String str, int maxWidth) {
/*  584 */     return truncate(str, 0, maxWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String truncate(String str, int offset, int maxWidth) {
/*  647 */     if (offset < 0) {
/*  648 */       throw new IllegalArgumentException("offset cannot be negative");
/*      */     }
/*  650 */     if (maxWidth < 0) {
/*  651 */       throw new IllegalArgumentException("maxWith cannot be negative");
/*      */     }
/*  653 */     if (str == null) {
/*  654 */       return null;
/*      */     }
/*  656 */     if (offset > str.length()) {
/*  657 */       return "";
/*      */     }
/*  659 */     if (str.length() > maxWidth) {
/*  660 */       int ix = (offset + maxWidth > str.length()) ? str.length() : (offset + maxWidth);
/*  661 */       return str.substring(offset, ix);
/*      */     } 
/*  663 */     return str.substring(offset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String strip(String str) {
/*  691 */     return strip(str, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripToNull(String str) {
/*  718 */     if (str == null) {
/*  719 */       return null;
/*      */     }
/*  721 */     str = strip(str, null);
/*  722 */     return str.isEmpty() ? null : str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripToEmpty(String str) {
/*  748 */     return (str == null) ? "" : strip(str, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String strip(String str, String stripChars) {
/*  778 */     if (isEmpty(str)) {
/*  779 */       return str;
/*      */     }
/*  781 */     str = stripStart(str, stripChars);
/*  782 */     return stripEnd(str, stripChars);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripStart(String str, String stripChars) {
/*      */     int strLen;
/*  811 */     if (str == null || (strLen = str.length()) == 0) {
/*  812 */       return str;
/*      */     }
/*  814 */     int start = 0;
/*  815 */     if (stripChars == null) {
/*  816 */       while (start != strLen && Character.isWhitespace(str.charAt(start)))
/*  817 */         start++; 
/*      */     } else {
/*  819 */       if (stripChars.isEmpty()) {
/*  820 */         return str;
/*      */       }
/*  822 */       while (start != strLen && stripChars.indexOf(str.charAt(start)) != -1) {
/*  823 */         start++;
/*      */       }
/*      */     } 
/*  826 */     return str.substring(start);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripEnd(String str, String stripChars) {
/*      */     int end;
/*  856 */     if (str == null || (end = str.length()) == 0) {
/*  857 */       return str;
/*      */     }
/*      */     
/*  860 */     if (stripChars == null) {
/*  861 */       while (end != 0 && Character.isWhitespace(str.charAt(end - 1)))
/*  862 */         end--; 
/*      */     } else {
/*  864 */       if (stripChars.isEmpty()) {
/*  865 */         return str;
/*      */       }
/*  867 */       while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1) {
/*  868 */         end--;
/*      */       }
/*      */     } 
/*  871 */     return str.substring(0, end);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] stripAll(String... strs) {
/*  896 */     return stripAll(strs, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] stripAll(String[] strs, String stripChars) {
/*      */     int strsLen;
/*  926 */     if (strs == null || (strsLen = strs.length) == 0) {
/*  927 */       return strs;
/*      */     }
/*  929 */     String[] newArr = new String[strsLen];
/*  930 */     for (int i = 0; i < strsLen; i++) {
/*  931 */       newArr[i] = strip(strs[i], stripChars);
/*      */     }
/*  933 */     return newArr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripAccents(String input) {
/*  955 */     if (input == null) {
/*  956 */       return null;
/*      */     }
/*  958 */     Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
/*  959 */     StringBuilder decomposed = new StringBuilder(Normalizer.normalize(input, Normalizer.Form.NFD));
/*  960 */     convertRemainingAccentCharacters(decomposed);
/*      */     
/*  962 */     return pattern.matcher(decomposed).replaceAll("");
/*      */   }
/*      */   
/*      */   private static void convertRemainingAccentCharacters(StringBuilder decomposed) {
/*  966 */     for (int i = 0; i < decomposed.length(); i++) {
/*  967 */       if (decomposed.charAt(i) == 'Ł') {
/*  968 */         decomposed.deleteCharAt(i);
/*  969 */         decomposed.insert(i, 'L');
/*  970 */       } else if (decomposed.charAt(i) == 'ł') {
/*  971 */         decomposed.deleteCharAt(i);
/*  972 */         decomposed.insert(i, 'l');
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equals(CharSequence cs1, CharSequence cs2) {
/* 1002 */     if (cs1 == cs2) {
/* 1003 */       return true;
/*      */     }
/* 1005 */     if (cs1 == null || cs2 == null) {
/* 1006 */       return false;
/*      */     }
/* 1008 */     if (cs1.length() != cs2.length()) {
/* 1009 */       return false;
/*      */     }
/* 1011 */     if (cs1 instanceof String && cs2 instanceof String) {
/* 1012 */       return cs1.equals(cs2);
/*      */     }
/*      */     
/* 1015 */     int length = cs1.length();
/* 1016 */     for (int i = 0; i < length; i++) {
/* 1017 */       if (cs1.charAt(i) != cs2.charAt(i)) {
/* 1018 */         return false;
/*      */       }
/*      */     } 
/* 1021 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equalsIgnoreCase(CharSequence cs1, CharSequence cs2) {
/* 1046 */     if (cs1 == cs2) {
/* 1047 */       return true;
/*      */     }
/* 1049 */     if (cs1 == null || cs2 == null) {
/* 1050 */       return false;
/*      */     }
/* 1052 */     if (cs1.length() != cs2.length()) {
/* 1053 */       return false;
/*      */     }
/* 1055 */     return CharSequenceUtils.regionMatches(cs1, true, 0, cs2, 0, cs1.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compare(String str1, String str2) {
/* 1093 */     return compare(str1, str2, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compare(String str1, String str2, boolean nullIsLess) {
/* 1131 */     if (str1 == str2) {
/* 1132 */       return 0;
/*      */     }
/* 1134 */     if (str1 == null) {
/* 1135 */       return nullIsLess ? -1 : 1;
/*      */     }
/* 1137 */     if (str2 == null) {
/* 1138 */       return nullIsLess ? 1 : -1;
/*      */     }
/* 1140 */     return str1.compareTo(str2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compareIgnoreCase(String str1, String str2) {
/* 1181 */     return compareIgnoreCase(str1, str2, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int compareIgnoreCase(String str1, String str2, boolean nullIsLess) {
/* 1224 */     if (str1 == str2) {
/* 1225 */       return 0;
/*      */     }
/* 1227 */     if (str1 == null) {
/* 1228 */       return nullIsLess ? -1 : 1;
/*      */     }
/* 1230 */     if (str2 == null) {
/* 1231 */       return nullIsLess ? 1 : -1;
/*      */     }
/* 1233 */     return str1.compareToIgnoreCase(str2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equalsAny(CharSequence string, CharSequence... searchStrings) {
/* 1256 */     if (ArrayUtils.isNotEmpty(searchStrings)) {
/* 1257 */       for (CharSequence next : searchStrings) {
/* 1258 */         if (equals(string, next)) {
/* 1259 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/* 1263 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean equalsAnyIgnoreCase(CharSequence string, CharSequence... searchStrings) {
/* 1287 */     if (ArrayUtils.isNotEmpty(searchStrings)) {
/* 1288 */       for (CharSequence next : searchStrings) {
/* 1289 */         if (equalsIgnoreCase(string, next)) {
/* 1290 */           return true;
/*      */         }
/*      */       } 
/*      */     }
/* 1294 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOf(CharSequence seq, int searchChar) {
/* 1337 */     if (isEmpty(seq)) {
/* 1338 */       return -1;
/*      */     }
/* 1340 */     return CharSequenceUtils.indexOf(seq, searchChar, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOf(CharSequence seq, int searchChar, int startPos) {
/* 1397 */     if (isEmpty(seq)) {
/* 1398 */       return -1;
/*      */     }
/* 1400 */     return CharSequenceUtils.indexOf(seq, searchChar, startPos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOf(CharSequence seq, CharSequence searchSeq) {
/* 1428 */     if (seq == null || searchSeq == null) {
/* 1429 */       return -1;
/*      */     }
/* 1431 */     return CharSequenceUtils.indexOf(seq, searchSeq, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
/* 1468 */     if (seq == null || searchSeq == null) {
/* 1469 */       return -1;
/*      */     }
/* 1471 */     return CharSequenceUtils.indexOf(seq, searchSeq, startPos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
/* 1525 */     return ordinalIndexOf(str, searchStr, ordinal, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal, boolean lastIndex) {
/* 1544 */     if (str == null || searchStr == null || ordinal <= 0) {
/* 1545 */       return -1;
/*      */     }
/* 1547 */     if (searchStr.length() == 0) {
/* 1548 */       return lastIndex ? str.length() : 0;
/*      */     }
/* 1550 */     int found = 0;
/*      */ 
/*      */     
/* 1553 */     int index = lastIndex ? str.length() : -1;
/*      */     while (true) {
/* 1555 */       if (lastIndex) {
/* 1556 */         index = CharSequenceUtils.lastIndexOf(str, searchStr, index - 1);
/*      */       } else {
/* 1558 */         index = CharSequenceUtils.indexOf(str, searchStr, index + 1);
/*      */       } 
/* 1560 */       if (index < 0) {
/* 1561 */         return index;
/*      */       }
/* 1563 */       found++;
/* 1564 */       if (found >= ordinal) {
/* 1565 */         return index;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
/* 1594 */     return indexOfIgnoreCase(str, searchStr, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
/* 1630 */     if (str == null || searchStr == null) {
/* 1631 */       return -1;
/*      */     }
/* 1633 */     if (startPos < 0) {
/* 1634 */       startPos = 0;
/*      */     }
/* 1636 */     int endLimit = str.length() - searchStr.length() + 1;
/* 1637 */     if (startPos > endLimit) {
/* 1638 */       return -1;
/*      */     }
/* 1640 */     if (searchStr.length() == 0) {
/* 1641 */       return startPos;
/*      */     }
/* 1643 */     for (int i = startPos; i < endLimit; i++) {
/* 1644 */       if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
/* 1645 */         return i;
/*      */       }
/*      */     } 
/* 1648 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOf(CharSequence seq, int searchChar) {
/* 1688 */     if (isEmpty(seq)) {
/* 1689 */       return -1;
/*      */     }
/* 1691 */     return CharSequenceUtils.lastIndexOf(seq, searchChar, seq.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOf(CharSequence seq, int searchChar, int startPos) {
/* 1739 */     if (isEmpty(seq)) {
/* 1740 */       return -1;
/*      */     }
/* 1742 */     return CharSequenceUtils.lastIndexOf(seq, searchChar, startPos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOf(CharSequence seq, CharSequence searchSeq) {
/* 1769 */     if (seq == null || searchSeq == null) {
/* 1770 */       return -1;
/*      */     }
/* 1772 */     return CharSequenceUtils.lastIndexOf(seq, searchSeq, seq.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
/* 1810 */     return ordinalIndexOf(str, searchStr, ordinal, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
/* 1849 */     if (seq == null || searchSeq == null) {
/* 1850 */       return -1;
/*      */     }
/* 1852 */     return CharSequenceUtils.lastIndexOf(seq, searchSeq, startPos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
/* 1879 */     if (str == null || searchStr == null) {
/* 1880 */       return -1;
/*      */     }
/* 1882 */     return lastIndexOfIgnoreCase(str, searchStr, str.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
/* 1918 */     if (str == null || searchStr == null) {
/* 1919 */       return -1;
/*      */     }
/* 1921 */     if (startPos > str.length() - searchStr.length()) {
/* 1922 */       startPos = str.length() - searchStr.length();
/*      */     }
/* 1924 */     if (startPos < 0) {
/* 1925 */       return -1;
/*      */     }
/* 1927 */     if (searchStr.length() == 0) {
/* 1928 */       return startPos;
/*      */     }
/*      */     
/* 1931 */     for (int i = startPos; i >= 0; i--) {
/* 1932 */       if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
/* 1933 */         return i;
/*      */       }
/*      */     } 
/* 1936 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean contains(CharSequence seq, int searchChar) {
/* 1962 */     if (isEmpty(seq)) {
/* 1963 */       return false;
/*      */     }
/* 1965 */     return (CharSequenceUtils.indexOf(seq, searchChar, 0) >= 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean contains(CharSequence seq, CharSequence searchSeq) {
/* 1991 */     if (seq == null || searchSeq == null) {
/* 1992 */       return false;
/*      */     }
/* 1994 */     return (CharSequenceUtils.indexOf(seq, searchSeq, 0) >= 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsIgnoreCase(CharSequence str, CharSequence searchStr) {
/* 2022 */     if (str == null || searchStr == null) {
/* 2023 */       return false;
/*      */     }
/* 2025 */     int len = searchStr.length();
/* 2026 */     int max = str.length() - len;
/* 2027 */     for (int i = 0; i <= max; i++) {
/* 2028 */       if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, len)) {
/* 2029 */         return true;
/*      */       }
/*      */     } 
/* 2032 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsWhitespace(CharSequence seq) {
/* 2047 */     if (isEmpty(seq)) {
/* 2048 */       return false;
/*      */     }
/* 2050 */     int strLen = seq.length();
/* 2051 */     for (int i = 0; i < strLen; i++) {
/* 2052 */       if (Character.isWhitespace(seq.charAt(i))) {
/* 2053 */         return true;
/*      */       }
/*      */     } 
/* 2056 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfAny(CharSequence cs, char... searchChars) {
/* 2085 */     if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
/* 2086 */       return -1;
/*      */     }
/* 2088 */     int csLen = cs.length();
/* 2089 */     int csLast = csLen - 1;
/* 2090 */     int searchLen = searchChars.length;
/* 2091 */     int searchLast = searchLen - 1;
/* 2092 */     for (int i = 0; i < csLen; i++) {
/* 2093 */       char ch = cs.charAt(i);
/* 2094 */       for (int j = 0; j < searchLen; j++) {
/* 2095 */         if (searchChars[j] == ch) {
/* 2096 */           if (i < csLast && j < searchLast && Character.isHighSurrogate(ch)) {
/*      */             
/* 2098 */             if (searchChars[j + 1] == cs.charAt(i + 1)) {
/* 2099 */               return i;
/*      */             }
/*      */           } else {
/* 2102 */             return i;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/* 2107 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfAny(CharSequence cs, String searchChars) {
/* 2134 */     if (isEmpty(cs) || isEmpty(searchChars)) {
/* 2135 */       return -1;
/*      */     }
/* 2137 */     return indexOfAny(cs, searchChars.toCharArray());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsAny(CharSequence cs, char... searchChars) {
/* 2168 */     if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
/* 2169 */       return false;
/*      */     }
/* 2171 */     int csLength = cs.length();
/* 2172 */     int searchLength = searchChars.length;
/* 2173 */     int csLast = csLength - 1;
/* 2174 */     int searchLast = searchLength - 1;
/* 2175 */     for (int i = 0; i < csLength; i++) {
/* 2176 */       char ch = cs.charAt(i);
/* 2177 */       for (int j = 0; j < searchLength; j++) {
/* 2178 */         if (searchChars[j] == ch) {
/* 2179 */           if (Character.isHighSurrogate(ch)) {
/* 2180 */             if (j == searchLast)
/*      */             {
/* 2182 */               return true;
/*      */             }
/* 2184 */             if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
/* 2185 */               return true;
/*      */             }
/*      */           } else {
/*      */             
/* 2189 */             return true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/* 2194 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsAny(CharSequence cs, CharSequence searchChars) {
/* 2229 */     if (searchChars == null) {
/* 2230 */       return false;
/*      */     }
/* 2232 */     return containsAny(cs, CharSequenceUtils.toCharArray(searchChars));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsAny(CharSequence cs, CharSequence... searchCharSequences) {
/* 2261 */     if (isEmpty(cs) || ArrayUtils.isEmpty((Object[])searchCharSequences)) {
/* 2262 */       return false;
/*      */     }
/* 2264 */     for (CharSequence searchCharSequence : searchCharSequences) {
/* 2265 */       if (contains(cs, searchCharSequence)) {
/* 2266 */         return true;
/*      */       }
/*      */     } 
/* 2269 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfAnyBut(CharSequence cs, char... searchChars) {
/* 2299 */     if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
/* 2300 */       return -1;
/*      */     }
/* 2302 */     int csLen = cs.length();
/* 2303 */     int csLast = csLen - 1;
/* 2304 */     int searchLen = searchChars.length;
/* 2305 */     int searchLast = searchLen - 1;
/*      */     
/* 2307 */     for (int i = 0; i < csLen; i++) {
/* 2308 */       char ch = cs.charAt(i);
/* 2309 */       int j = 0; while (true) { if (j < searchLen) {
/* 2310 */           if (searchChars[j] == ch && (
/* 2311 */             i >= csLast || j >= searchLast || !Character.isHighSurrogate(ch) || 
/* 2312 */             searchChars[j + 1] == cs.charAt(i + 1))) {
/*      */             break;
/*      */           }
/*      */           
/*      */           j++;
/*      */           
/*      */           continue;
/*      */         } 
/* 2320 */         return i; }
/*      */     
/* 2322 */     }  return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfAnyBut(CharSequence seq, CharSequence searchChars) {
/* 2349 */     if (isEmpty(seq) || isEmpty(searchChars)) {
/* 2350 */       return -1;
/*      */     }
/* 2352 */     int strLen = seq.length();
/* 2353 */     for (int i = 0; i < strLen; i++) {
/* 2354 */       char ch = seq.charAt(i);
/* 2355 */       boolean chFound = (CharSequenceUtils.indexOf(searchChars, ch, 0) >= 0);
/* 2356 */       if (i + 1 < strLen && Character.isHighSurrogate(ch)) {
/* 2357 */         char ch2 = seq.charAt(i + 1);
/* 2358 */         if (chFound && CharSequenceUtils.indexOf(searchChars, ch2, 0) < 0) {
/* 2359 */           return i;
/*      */         }
/*      */       }
/* 2362 */       else if (!chFound) {
/* 2363 */         return i;
/*      */       } 
/*      */     } 
/*      */     
/* 2367 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsOnly(CharSequence cs, char... valid) {
/* 2396 */     if (valid == null || cs == null) {
/* 2397 */       return false;
/*      */     }
/* 2399 */     if (cs.length() == 0) {
/* 2400 */       return true;
/*      */     }
/* 2402 */     if (valid.length == 0) {
/* 2403 */       return false;
/*      */     }
/* 2405 */     return (indexOfAnyBut(cs, valid) == -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsOnly(CharSequence cs, String validChars) {
/* 2432 */     if (cs == null || validChars == null) {
/* 2433 */       return false;
/*      */     }
/* 2435 */     return containsOnly(cs, validChars.toCharArray());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsNone(CharSequence cs, char... searchChars) {
/* 2464 */     if (cs == null || searchChars == null) {
/* 2465 */       return true;
/*      */     }
/* 2467 */     int csLen = cs.length();
/* 2468 */     int csLast = csLen - 1;
/* 2469 */     int searchLen = searchChars.length;
/* 2470 */     int searchLast = searchLen - 1;
/* 2471 */     for (int i = 0; i < csLen; i++) {
/* 2472 */       char ch = cs.charAt(i);
/* 2473 */       for (int j = 0; j < searchLen; j++) {
/* 2474 */         if (searchChars[j] == ch) {
/* 2475 */           if (Character.isHighSurrogate(ch)) {
/* 2476 */             if (j == searchLast)
/*      */             {
/* 2478 */               return false;
/*      */             }
/* 2480 */             if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
/* 2481 */               return false;
/*      */             }
/*      */           } else {
/*      */             
/* 2485 */             return false;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/* 2490 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsNone(CharSequence cs, String invalidChars) {
/* 2517 */     if (cs == null || invalidChars == null) {
/* 2518 */       return true;
/*      */     }
/* 2520 */     return containsNone(cs, invalidChars.toCharArray());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfAny(CharSequence str, CharSequence... searchStrs) {
/* 2553 */     if (str == null || searchStrs == null) {
/* 2554 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 2558 */     int ret = Integer.MAX_VALUE;
/*      */     
/* 2560 */     int tmp = 0;
/* 2561 */     for (CharSequence search : searchStrs) {
/* 2562 */       if (search != null) {
/*      */ 
/*      */         
/* 2565 */         tmp = CharSequenceUtils.indexOf(str, search, 0);
/* 2566 */         if (tmp != -1)
/*      */         {
/*      */ 
/*      */           
/* 2570 */           if (tmp < ret)
/* 2571 */             ret = tmp; 
/*      */         }
/*      */       } 
/*      */     } 
/* 2575 */     return (ret == Integer.MAX_VALUE) ? -1 : ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int lastIndexOfAny(CharSequence str, CharSequence... searchStrs) {
/* 2605 */     if (str == null || searchStrs == null) {
/* 2606 */       return -1;
/*      */     }
/* 2608 */     int ret = -1;
/* 2609 */     int tmp = 0;
/* 2610 */     for (CharSequence search : searchStrs) {
/* 2611 */       if (search != null) {
/*      */ 
/*      */         
/* 2614 */         tmp = CharSequenceUtils.lastIndexOf(str, search, str.length());
/* 2615 */         if (tmp > ret)
/* 2616 */           ret = tmp; 
/*      */       } 
/*      */     } 
/* 2619 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substring(String str, int start) {
/* 2649 */     if (str == null) {
/* 2650 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2654 */     if (start < 0) {
/* 2655 */       start = str.length() + start;
/*      */     }
/*      */     
/* 2658 */     if (start < 0) {
/* 2659 */       start = 0;
/*      */     }
/* 2661 */     if (start > str.length()) {
/* 2662 */       return "";
/*      */     }
/*      */     
/* 2665 */     return str.substring(start);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substring(String str, int start, int end) {
/* 2704 */     if (str == null) {
/* 2705 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 2709 */     if (end < 0) {
/* 2710 */       end = str.length() + end;
/*      */     }
/* 2712 */     if (start < 0) {
/* 2713 */       start = str.length() + start;
/*      */     }
/*      */ 
/*      */     
/* 2717 */     if (end > str.length()) {
/* 2718 */       end = str.length();
/*      */     }
/*      */ 
/*      */     
/* 2722 */     if (start > end) {
/* 2723 */       return "";
/*      */     }
/*      */     
/* 2726 */     if (start < 0) {
/* 2727 */       start = 0;
/*      */     }
/* 2729 */     if (end < 0) {
/* 2730 */       end = 0;
/*      */     }
/*      */     
/* 2733 */     return str.substring(start, end);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String left(String str, int len) {
/* 2759 */     if (str == null) {
/* 2760 */       return null;
/*      */     }
/* 2762 */     if (len < 0) {
/* 2763 */       return "";
/*      */     }
/* 2765 */     if (str.length() <= len) {
/* 2766 */       return str;
/*      */     }
/* 2768 */     return str.substring(0, len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String right(String str, int len) {
/* 2792 */     if (str == null) {
/* 2793 */       return null;
/*      */     }
/* 2795 */     if (len < 0) {
/* 2796 */       return "";
/*      */     }
/* 2798 */     if (str.length() <= len) {
/* 2799 */       return str;
/*      */     }
/* 2801 */     return str.substring(str.length() - len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String mid(String str, int pos, int len) {
/* 2830 */     if (str == null) {
/* 2831 */       return null;
/*      */     }
/* 2833 */     if (len < 0 || pos > str.length()) {
/* 2834 */       return "";
/*      */     }
/* 2836 */     if (pos < 0) {
/* 2837 */       pos = 0;
/*      */     }
/* 2839 */     if (str.length() <= pos + len) {
/* 2840 */       return str.substring(pos);
/*      */     }
/* 2842 */     return str.substring(pos, pos + len);
/*      */   }
/*      */   
/*      */   private static StringBuilder newStringBuilder(int noOfItems) {
/* 2846 */     return new StringBuilder(noOfItems * 16);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringBefore(String str, String separator) {
/* 2879 */     if (isEmpty(str) || separator == null) {
/* 2880 */       return str;
/*      */     }
/* 2882 */     if (separator.isEmpty()) {
/* 2883 */       return "";
/*      */     }
/* 2885 */     int pos = str.indexOf(separator);
/* 2886 */     if (pos == -1) {
/* 2887 */       return str;
/*      */     }
/* 2889 */     return str.substring(0, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringAfter(String str, String separator) {
/* 2921 */     if (isEmpty(str)) {
/* 2922 */       return str;
/*      */     }
/* 2924 */     if (separator == null) {
/* 2925 */       return "";
/*      */     }
/* 2927 */     int pos = str.indexOf(separator);
/* 2928 */     if (pos == -1) {
/* 2929 */       return "";
/*      */     }
/* 2931 */     return str.substring(pos + separator.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringBeforeLast(String str, String separator) {
/* 2962 */     if (isEmpty(str) || isEmpty(separator)) {
/* 2963 */       return str;
/*      */     }
/* 2965 */     int pos = str.lastIndexOf(separator);
/* 2966 */     if (pos == -1) {
/* 2967 */       return str;
/*      */     }
/* 2969 */     return str.substring(0, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringAfterLast(String str, String separator) {
/* 3002 */     if (isEmpty(str)) {
/* 3003 */       return str;
/*      */     }
/* 3005 */     if (isEmpty(separator)) {
/* 3006 */       return "";
/*      */     }
/* 3008 */     int pos = str.lastIndexOf(separator);
/* 3009 */     if (pos == -1 || pos == str.length() - separator.length()) {
/* 3010 */       return "";
/*      */     }
/* 3012 */     return str.substring(pos + separator.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringBetween(String str, String tag) {
/* 3039 */     return substringBetween(str, tag, tag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String substringBetween(String str, String open, String close) {
/* 3070 */     if (str == null || open == null || close == null) {
/* 3071 */       return null;
/*      */     }
/* 3073 */     int start = str.indexOf(open);
/* 3074 */     if (start != -1) {
/* 3075 */       int end = str.indexOf(close, start + open.length());
/* 3076 */       if (end != -1) {
/* 3077 */         return str.substring(start + open.length(), end);
/*      */       }
/*      */     } 
/* 3080 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] substringsBetween(String str, String open, String close) {
/* 3106 */     if (str == null || isEmpty(open) || isEmpty(close)) {
/* 3107 */       return null;
/*      */     }
/* 3109 */     int strLen = str.length();
/* 3110 */     if (strLen == 0) {
/* 3111 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*      */     }
/* 3113 */     int closeLen = close.length();
/* 3114 */     int openLen = open.length();
/* 3115 */     List<String> list = new ArrayList<>();
/* 3116 */     int pos = 0;
/* 3117 */     while (pos < strLen - closeLen) {
/* 3118 */       int start = str.indexOf(open, pos);
/* 3119 */       if (start < 0) {
/*      */         break;
/*      */       }
/* 3122 */       start += openLen;
/* 3123 */       int end = str.indexOf(close, start);
/* 3124 */       if (end < 0) {
/*      */         break;
/*      */       }
/* 3127 */       list.add(str.substring(start, end));
/* 3128 */       pos = end + closeLen;
/*      */     } 
/* 3130 */     if (list.isEmpty()) {
/* 3131 */       return null;
/*      */     }
/* 3133 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] split(String str) {
/* 3164 */     return split(str, null, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] split(String str, char separatorChar) {
/* 3192 */     return splitWorker(str, separatorChar, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] split(String str, String separatorChars) {
/* 3221 */     return splitWorker(str, separatorChars, -1, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] split(String str, String separatorChars, int max) {
/* 3255 */     return splitWorker(str, separatorChars, max, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitByWholeSeparator(String str, String separator) {
/* 3282 */     return splitByWholeSeparatorWorker(str, separator, -1, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitByWholeSeparator(String str, String separator, int max) {
/* 3313 */     return splitByWholeSeparatorWorker(str, separator, max, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
/* 3342 */     return splitByWholeSeparatorWorker(str, separator, -1, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
/* 3375 */     return splitByWholeSeparatorWorker(str, separator, max, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] splitByWholeSeparatorWorker(String str, String separator, int max, boolean preserveAllTokens) {
/* 3394 */     if (str == null) {
/* 3395 */       return null;
/*      */     }
/*      */     
/* 3398 */     int len = str.length();
/*      */     
/* 3400 */     if (len == 0) {
/* 3401 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*      */     }
/*      */     
/* 3404 */     if (separator == null || "".equals(separator))
/*      */     {
/* 3406 */       return splitWorker(str, null, max, preserveAllTokens);
/*      */     }
/*      */     
/* 3409 */     int separatorLength = separator.length();
/*      */     
/* 3411 */     ArrayList<String> substrings = new ArrayList<>();
/* 3412 */     int numberOfSubstrings = 0;
/* 3413 */     int beg = 0;
/* 3414 */     int end = 0;
/* 3415 */     while (end < len) {
/* 3416 */       end = str.indexOf(separator, beg);
/*      */       
/* 3418 */       if (end > -1) {
/* 3419 */         if (end > beg) {
/* 3420 */           numberOfSubstrings++;
/*      */           
/* 3422 */           if (numberOfSubstrings == max) {
/* 3423 */             end = len;
/* 3424 */             substrings.add(str.substring(beg));
/*      */             
/*      */             continue;
/*      */           } 
/* 3428 */           substrings.add(str.substring(beg, end));
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3433 */           beg = end + separatorLength;
/*      */           
/*      */           continue;
/*      */         } 
/* 3437 */         if (preserveAllTokens) {
/* 3438 */           numberOfSubstrings++;
/* 3439 */           if (numberOfSubstrings == max) {
/* 3440 */             end = len;
/* 3441 */             substrings.add(str.substring(beg));
/*      */           } else {
/* 3443 */             substrings.add("");
/*      */           } 
/*      */         } 
/* 3446 */         beg = end + separatorLength;
/*      */         
/*      */         continue;
/*      */       } 
/* 3450 */       substrings.add(str.substring(beg));
/* 3451 */       end = len;
/*      */     } 
/*      */ 
/*      */     
/* 3455 */     return substrings.<String>toArray(new String[substrings.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str) {
/* 3484 */     return splitWorker(str, null, -1, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str, char separatorChar) {
/* 3520 */     return splitWorker(str, separatorChar, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
/* 3538 */     if (str == null) {
/* 3539 */       return null;
/*      */     }
/* 3541 */     int len = str.length();
/* 3542 */     if (len == 0) {
/* 3543 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*      */     }
/* 3545 */     List<String> list = new ArrayList<>();
/* 3546 */     int i = 0, start = 0;
/* 3547 */     boolean match = false;
/* 3548 */     boolean lastMatch = false;
/* 3549 */     while (i < len) {
/* 3550 */       if (str.charAt(i) == separatorChar) {
/* 3551 */         if (match || preserveAllTokens) {
/* 3552 */           list.add(str.substring(start, i));
/* 3553 */           match = false;
/* 3554 */           lastMatch = true;
/*      */         } 
/* 3556 */         start = ++i;
/*      */         continue;
/*      */       } 
/* 3559 */       lastMatch = false;
/* 3560 */       match = true;
/* 3561 */       i++;
/*      */     } 
/* 3563 */     if (match || (preserveAllTokens && lastMatch)) {
/* 3564 */       list.add(str.substring(start, i));
/*      */     }
/* 3566 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str, String separatorChars) {
/* 3603 */     return splitWorker(str, separatorChars, -1, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
/* 3643 */     return splitWorker(str, separatorChars, max, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
/* 3665 */     if (str == null) {
/* 3666 */       return null;
/*      */     }
/* 3668 */     int len = str.length();
/* 3669 */     if (len == 0) {
/* 3670 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*      */     }
/* 3672 */     List<String> list = new ArrayList<>();
/* 3673 */     int sizePlus1 = 1;
/* 3674 */     int i = 0, start = 0;
/* 3675 */     boolean match = false;
/* 3676 */     boolean lastMatch = false;
/* 3677 */     if (separatorChars == null) {
/*      */       
/* 3679 */       while (i < len) {
/* 3680 */         if (Character.isWhitespace(str.charAt(i))) {
/* 3681 */           if (match || preserveAllTokens) {
/* 3682 */             lastMatch = true;
/* 3683 */             if (sizePlus1++ == max) {
/* 3684 */               i = len;
/* 3685 */               lastMatch = false;
/*      */             } 
/* 3687 */             list.add(str.substring(start, i));
/* 3688 */             match = false;
/*      */           } 
/* 3690 */           start = ++i;
/*      */           continue;
/*      */         } 
/* 3693 */         lastMatch = false;
/* 3694 */         match = true;
/* 3695 */         i++;
/*      */       } 
/* 3697 */     } else if (separatorChars.length() == 1) {
/*      */       
/* 3699 */       char sep = separatorChars.charAt(0);
/* 3700 */       while (i < len) {
/* 3701 */         if (str.charAt(i) == sep) {
/* 3702 */           if (match || preserveAllTokens) {
/* 3703 */             lastMatch = true;
/* 3704 */             if (sizePlus1++ == max) {
/* 3705 */               i = len;
/* 3706 */               lastMatch = false;
/*      */             } 
/* 3708 */             list.add(str.substring(start, i));
/* 3709 */             match = false;
/*      */           } 
/* 3711 */           start = ++i;
/*      */           continue;
/*      */         } 
/* 3714 */         lastMatch = false;
/* 3715 */         match = true;
/* 3716 */         i++;
/*      */       } 
/*      */     } else {
/*      */       
/* 3720 */       while (i < len) {
/* 3721 */         if (separatorChars.indexOf(str.charAt(i)) >= 0) {
/* 3722 */           if (match || preserveAllTokens) {
/* 3723 */             lastMatch = true;
/* 3724 */             if (sizePlus1++ == max) {
/* 3725 */               i = len;
/* 3726 */               lastMatch = false;
/*      */             } 
/* 3728 */             list.add(str.substring(start, i));
/* 3729 */             match = false;
/*      */           } 
/* 3731 */           start = ++i;
/*      */           continue;
/*      */         } 
/* 3734 */         lastMatch = false;
/* 3735 */         match = true;
/* 3736 */         i++;
/*      */       } 
/*      */     } 
/* 3739 */     if (match || (preserveAllTokens && lastMatch)) {
/* 3740 */       list.add(str.substring(start, i));
/*      */     }
/* 3742 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitByCharacterType(String str) {
/* 3765 */     return splitByCharacterType(str, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] splitByCharacterTypeCamelCase(String str) {
/* 3793 */     return splitByCharacterType(str, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] splitByCharacterType(String str, boolean camelCase) {
/* 3811 */     if (str == null) {
/* 3812 */       return null;
/*      */     }
/* 3814 */     if (str.isEmpty()) {
/* 3815 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*      */     }
/* 3817 */     char[] c = str.toCharArray();
/* 3818 */     List<String> list = new ArrayList<>();
/* 3819 */     int tokenStart = 0;
/* 3820 */     int currentType = Character.getType(c[tokenStart]);
/* 3821 */     for (int pos = tokenStart + 1; pos < c.length; pos++) {
/* 3822 */       int type = Character.getType(c[pos]);
/* 3823 */       if (type != currentType) {
/*      */ 
/*      */         
/* 3826 */         if (camelCase && type == 2 && currentType == 1) {
/* 3827 */           int newTokenStart = pos - 1;
/* 3828 */           if (newTokenStart != tokenStart) {
/* 3829 */             list.add(new String(c, tokenStart, newTokenStart - tokenStart));
/* 3830 */             tokenStart = newTokenStart;
/*      */           } 
/*      */         } else {
/* 3833 */           list.add(new String(c, tokenStart, pos - tokenStart));
/* 3834 */           tokenStart = pos;
/*      */         } 
/* 3836 */         currentType = type;
/*      */       } 
/* 3838 */     }  list.add(new String(c, tokenStart, c.length - tokenStart));
/* 3839 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T> String join(T... elements) {
/* 3868 */     return join((Object[])elements, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Object[] array, char separator) {
/* 3894 */     if (array == null) {
/* 3895 */       return null;
/*      */     }
/* 3897 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(long[] array, char separator) {
/* 3926 */     if (array == null) {
/* 3927 */       return null;
/*      */     }
/* 3929 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(int[] array, char separator) {
/* 3958 */     if (array == null) {
/* 3959 */       return null;
/*      */     }
/* 3961 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(short[] array, char separator) {
/* 3990 */     if (array == null) {
/* 3991 */       return null;
/*      */     }
/* 3993 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(byte[] array, char separator) {
/* 4022 */     if (array == null) {
/* 4023 */       return null;
/*      */     }
/* 4025 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(char[] array, char separator) {
/* 4054 */     if (array == null) {
/* 4055 */       return null;
/*      */     }
/* 4057 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(float[] array, char separator) {
/* 4086 */     if (array == null) {
/* 4087 */       return null;
/*      */     }
/* 4089 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(double[] array, char separator) {
/* 4118 */     if (array == null) {
/* 4119 */       return null;
/*      */     }
/* 4121 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Object[] array, char separator, int startIndex, int endIndex) {
/* 4152 */     if (array == null) {
/* 4153 */       return null;
/*      */     }
/* 4155 */     int noOfItems = endIndex - startIndex;
/* 4156 */     if (noOfItems <= 0) {
/* 4157 */       return "";
/*      */     }
/* 4159 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4160 */     for (int i = startIndex; i < endIndex; i++) {
/* 4161 */       if (i > startIndex) {
/* 4162 */         buf.append(separator);
/*      */       }
/* 4164 */       if (array[i] != null) {
/* 4165 */         buf.append(array[i]);
/*      */       }
/*      */     } 
/* 4168 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(long[] array, char separator, int startIndex, int endIndex) {
/* 4203 */     if (array == null) {
/* 4204 */       return null;
/*      */     }
/* 4206 */     int noOfItems = endIndex - startIndex;
/* 4207 */     if (noOfItems <= 0) {
/* 4208 */       return "";
/*      */     }
/* 4210 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4211 */     for (int i = startIndex; i < endIndex; i++) {
/* 4212 */       if (i > startIndex) {
/* 4213 */         buf.append(separator);
/*      */       }
/* 4215 */       buf.append(array[i]);
/*      */     } 
/* 4217 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(int[] array, char separator, int startIndex, int endIndex) {
/* 4252 */     if (array == null) {
/* 4253 */       return null;
/*      */     }
/* 4255 */     int noOfItems = endIndex - startIndex;
/* 4256 */     if (noOfItems <= 0) {
/* 4257 */       return "";
/*      */     }
/* 4259 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4260 */     for (int i = startIndex; i < endIndex; i++) {
/* 4261 */       if (i > startIndex) {
/* 4262 */         buf.append(separator);
/*      */       }
/* 4264 */       buf.append(array[i]);
/*      */     } 
/* 4266 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(byte[] array, char separator, int startIndex, int endIndex) {
/* 4301 */     if (array == null) {
/* 4302 */       return null;
/*      */     }
/* 4304 */     int noOfItems = endIndex - startIndex;
/* 4305 */     if (noOfItems <= 0) {
/* 4306 */       return "";
/*      */     }
/* 4308 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4309 */     for (int i = startIndex; i < endIndex; i++) {
/* 4310 */       if (i > startIndex) {
/* 4311 */         buf.append(separator);
/*      */       }
/* 4313 */       buf.append(array[i]);
/*      */     } 
/* 4315 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(short[] array, char separator, int startIndex, int endIndex) {
/* 4350 */     if (array == null) {
/* 4351 */       return null;
/*      */     }
/* 4353 */     int noOfItems = endIndex - startIndex;
/* 4354 */     if (noOfItems <= 0) {
/* 4355 */       return "";
/*      */     }
/* 4357 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4358 */     for (int i = startIndex; i < endIndex; i++) {
/* 4359 */       if (i > startIndex) {
/* 4360 */         buf.append(separator);
/*      */       }
/* 4362 */       buf.append(array[i]);
/*      */     } 
/* 4364 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(char[] array, char separator, int startIndex, int endIndex) {
/* 4399 */     if (array == null) {
/* 4400 */       return null;
/*      */     }
/* 4402 */     int noOfItems = endIndex - startIndex;
/* 4403 */     if (noOfItems <= 0) {
/* 4404 */       return "";
/*      */     }
/* 4406 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4407 */     for (int i = startIndex; i < endIndex; i++) {
/* 4408 */       if (i > startIndex) {
/* 4409 */         buf.append(separator);
/*      */       }
/* 4411 */       buf.append(array[i]);
/*      */     } 
/* 4413 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(double[] array, char separator, int startIndex, int endIndex) {
/* 4448 */     if (array == null) {
/* 4449 */       return null;
/*      */     }
/* 4451 */     int noOfItems = endIndex - startIndex;
/* 4452 */     if (noOfItems <= 0) {
/* 4453 */       return "";
/*      */     }
/* 4455 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4456 */     for (int i = startIndex; i < endIndex; i++) {
/* 4457 */       if (i > startIndex) {
/* 4458 */         buf.append(separator);
/*      */       }
/* 4460 */       buf.append(array[i]);
/*      */     } 
/* 4462 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(float[] array, char separator, int startIndex, int endIndex) {
/* 4497 */     if (array == null) {
/* 4498 */       return null;
/*      */     }
/* 4500 */     int noOfItems = endIndex - startIndex;
/* 4501 */     if (noOfItems <= 0) {
/* 4502 */       return "";
/*      */     }
/* 4504 */     StringBuilder buf = newStringBuilder(noOfItems);
/* 4505 */     for (int i = startIndex; i < endIndex; i++) {
/* 4506 */       if (i > startIndex) {
/* 4507 */         buf.append(separator);
/*      */       }
/* 4509 */       buf.append(array[i]);
/*      */     } 
/* 4511 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Object[] array, String separator) {
/* 4539 */     if (array == null) {
/* 4540 */       return null;
/*      */     }
/* 4542 */     return join(array, separator, 0, array.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Object[] array, String separator, int startIndex, int endIndex) {
/* 4581 */     if (array == null) {
/* 4582 */       return null;
/*      */     }
/* 4584 */     if (separator == null) {
/* 4585 */       separator = "";
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4590 */     int noOfItems = endIndex - startIndex;
/* 4591 */     if (noOfItems <= 0) {
/* 4592 */       return "";
/*      */     }
/*      */     
/* 4595 */     StringBuilder buf = newStringBuilder(noOfItems);
/*      */     
/* 4597 */     for (int i = startIndex; i < endIndex; i++) {
/* 4598 */       if (i > startIndex) {
/* 4599 */         buf.append(separator);
/*      */       }
/* 4601 */       if (array[i] != null) {
/* 4602 */         buf.append(array[i]);
/*      */       }
/*      */     } 
/* 4605 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Iterator<?> iterator, char separator) {
/* 4625 */     if (iterator == null) {
/* 4626 */       return null;
/*      */     }
/* 4628 */     if (!iterator.hasNext()) {
/* 4629 */       return "";
/*      */     }
/* 4631 */     Object first = iterator.next();
/* 4632 */     if (!iterator.hasNext()) {
/* 4633 */       return Objects.toString(first, "");
/*      */     }
/*      */ 
/*      */     
/* 4637 */     StringBuilder buf = new StringBuilder(256);
/* 4638 */     if (first != null) {
/* 4639 */       buf.append(first);
/*      */     }
/*      */     
/* 4642 */     while (iterator.hasNext()) {
/* 4643 */       buf.append(separator);
/* 4644 */       Object obj = iterator.next();
/* 4645 */       if (obj != null) {
/* 4646 */         buf.append(obj);
/*      */       }
/*      */     } 
/*      */     
/* 4650 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Iterator<?> iterator, String separator) {
/* 4669 */     if (iterator == null) {
/* 4670 */       return null;
/*      */     }
/* 4672 */     if (!iterator.hasNext()) {
/* 4673 */       return "";
/*      */     }
/* 4675 */     Object first = iterator.next();
/* 4676 */     if (!iterator.hasNext()) {
/* 4677 */       return Objects.toString(first, "");
/*      */     }
/*      */ 
/*      */     
/* 4681 */     StringBuilder buf = new StringBuilder(256);
/* 4682 */     if (first != null) {
/* 4683 */       buf.append(first);
/*      */     }
/*      */     
/* 4686 */     while (iterator.hasNext()) {
/* 4687 */       if (separator != null) {
/* 4688 */         buf.append(separator);
/*      */       }
/* 4690 */       Object obj = iterator.next();
/* 4691 */       if (obj != null) {
/* 4692 */         buf.append(obj);
/*      */       }
/*      */     } 
/* 4695 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Iterable<?> iterable, char separator) {
/* 4713 */     if (iterable == null) {
/* 4714 */       return null;
/*      */     }
/* 4716 */     return join(iterable.iterator(), separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(Iterable<?> iterable, String separator) {
/* 4734 */     if (iterable == null) {
/* 4735 */       return null;
/*      */     }
/* 4737 */     return join(iterable.iterator(), separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(List<?> list, char separator, int startIndex, int endIndex) {
/* 4767 */     if (list == null) {
/* 4768 */       return null;
/*      */     }
/* 4770 */     int noOfItems = endIndex - startIndex;
/* 4771 */     if (noOfItems <= 0) {
/* 4772 */       return "";
/*      */     }
/* 4774 */     List<?> subList = list.subList(startIndex, endIndex);
/* 4775 */     return join(subList.iterator(), separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String join(List<?> list, String separator, int startIndex, int endIndex) {
/* 4805 */     if (list == null) {
/* 4806 */       return null;
/*      */     }
/* 4808 */     int noOfItems = endIndex - startIndex;
/* 4809 */     if (noOfItems <= 0) {
/* 4810 */       return "";
/*      */     }
/* 4812 */     List<?> subList = list.subList(startIndex, endIndex);
/* 4813 */     return join(subList.iterator(), separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String joinWith(String separator, Object... objects) {
/* 4837 */     if (objects == null) {
/* 4838 */       throw new IllegalArgumentException("Object varargs must not be null");
/*      */     }
/*      */     
/* 4841 */     String sanitizedSeparator = defaultString(separator);
/*      */     
/* 4843 */     StringBuilder result = new StringBuilder();
/*      */     
/* 4845 */     Iterator<Object> iterator = Arrays.<Object>asList(objects).iterator();
/* 4846 */     while (iterator.hasNext()) {
/* 4847 */       String value = Objects.toString(iterator.next(), "");
/* 4848 */       result.append(value);
/*      */       
/* 4850 */       if (iterator.hasNext()) {
/* 4851 */         result.append(sanitizedSeparator);
/*      */       }
/*      */     } 
/*      */     
/* 4855 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String deleteWhitespace(String str) {
/* 4875 */     if (isEmpty(str)) {
/* 4876 */       return str;
/*      */     }
/* 4878 */     int sz = str.length();
/* 4879 */     char[] chs = new char[sz];
/* 4880 */     int count = 0;
/* 4881 */     for (int i = 0; i < sz; i++) {
/* 4882 */       if (!Character.isWhitespace(str.charAt(i))) {
/* 4883 */         chs[count++] = str.charAt(i);
/*      */       }
/*      */     } 
/* 4886 */     if (count == sz) {
/* 4887 */       return str;
/*      */     }
/* 4889 */     return new String(chs, 0, count);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeStart(String str, String remove) {
/* 4919 */     if (isEmpty(str) || isEmpty(remove)) {
/* 4920 */       return str;
/*      */     }
/* 4922 */     if (str.startsWith(remove)) {
/* 4923 */       return str.substring(remove.length());
/*      */     }
/* 4925 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeStartIgnoreCase(String str, String remove) {
/* 4954 */     if (isEmpty(str) || isEmpty(remove)) {
/* 4955 */       return str;
/*      */     }
/* 4957 */     if (startsWithIgnoreCase(str, remove)) {
/* 4958 */       return str.substring(remove.length());
/*      */     }
/* 4960 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeEnd(String str, String remove) {
/* 4988 */     if (isEmpty(str) || isEmpty(remove)) {
/* 4989 */       return str;
/*      */     }
/* 4991 */     if (str.endsWith(remove)) {
/* 4992 */       return str.substring(0, str.length() - remove.length());
/*      */     }
/* 4994 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeEndIgnoreCase(String str, String remove) {
/* 5024 */     if (isEmpty(str) || isEmpty(remove)) {
/* 5025 */       return str;
/*      */     }
/* 5027 */     if (endsWithIgnoreCase(str, remove)) {
/* 5028 */       return str.substring(0, str.length() - remove.length());
/*      */     }
/* 5030 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String remove(String str, String remove) {
/* 5057 */     if (isEmpty(str) || isEmpty(remove)) {
/* 5058 */       return str;
/*      */     }
/* 5060 */     return replace(str, remove, "", -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeIgnoreCase(String str, String remove) {
/* 5097 */     if (isEmpty(str) || isEmpty(remove)) {
/* 5098 */       return str;
/*      */     }
/* 5100 */     return replaceIgnoreCase(str, remove, "", -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String remove(String str, char remove) {
/* 5123 */     if (isEmpty(str) || str.indexOf(remove) == -1) {
/* 5124 */       return str;
/*      */     }
/* 5126 */     char[] chars = str.toCharArray();
/* 5127 */     int pos = 0;
/* 5128 */     for (int i = 0; i < chars.length; i++) {
/* 5129 */       if (chars[i] != remove) {
/* 5130 */         chars[pos++] = chars[i];
/*      */       }
/*      */     } 
/* 5133 */     return new String(chars, 0, pos);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String removeAll(String text, String regex) {
/* 5183 */     return RegExUtils.removeAll(text, regex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String removeFirst(String text, String regex) {
/* 5232 */     return replaceFirst(text, regex, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceOnce(String text, String searchString, String replacement) {
/* 5261 */     return replace(text, searchString, replacement, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceOnceIgnoreCase(String text, String searchString, String replacement) {
/* 5290 */     return replaceIgnoreCase(text, searchString, replacement, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String replacePattern(String source, String regex, String replacement) {
/* 5336 */     return RegExUtils.replacePattern(source, regex, replacement);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String removePattern(String source, String regex) {
/* 5373 */     return RegExUtils.removePattern(source, regex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String replaceAll(String text, String regex, String replacement) {
/* 5428 */     return RegExUtils.replaceAll(text, regex, replacement);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String replaceFirst(String text, String regex, String replacement) {
/* 5481 */     return RegExUtils.replaceFirst(text, regex, replacement);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replace(String text, String searchString, String replacement) {
/* 5508 */     return replace(text, searchString, replacement, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceIgnoreCase(String text, String searchString, String replacement) {
/* 5536 */     return replaceIgnoreCase(text, searchString, replacement, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replace(String text, String searchString, String replacement, int max) {
/* 5568 */     return replace(text, searchString, replacement, max, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String replace(String text, String searchString, String replacement, int max, boolean ignoreCase) {
/* 5603 */     if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
/* 5604 */       return text;
/*      */     }
/* 5606 */     String searchText = text;
/* 5607 */     if (ignoreCase) {
/* 5608 */       searchText = text.toLowerCase();
/* 5609 */       searchString = searchString.toLowerCase();
/*      */     } 
/* 5611 */     int start = 0;
/* 5612 */     int end = searchText.indexOf(searchString, start);
/* 5613 */     if (end == -1) {
/* 5614 */       return text;
/*      */     }
/* 5616 */     int replLength = searchString.length();
/* 5617 */     int increase = replacement.length() - replLength;
/* 5618 */     increase = (increase < 0) ? 0 : increase;
/* 5619 */     increase *= (max < 0) ? 16 : ((max > 64) ? 64 : max);
/* 5620 */     StringBuilder buf = new StringBuilder(text.length() + increase);
/* 5621 */     while (end != -1) {
/* 5622 */       buf.append(text, start, end).append(replacement);
/* 5623 */       start = end + replLength;
/* 5624 */       if (--max == 0) {
/*      */         break;
/*      */       }
/* 5627 */       end = searchText.indexOf(searchString, start);
/*      */     } 
/* 5629 */     buf.append(text, start, text.length());
/* 5630 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceIgnoreCase(String text, String searchString, String replacement, int max) {
/* 5663 */     return replace(text, searchString, replacement, max, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceEach(String text, String[] searchList, String[] replacementList) {
/* 5706 */     return replaceEach(text, searchList, replacementList, false, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
/* 5754 */     int timeToLive = (searchList == null) ? 0 : searchList.length;
/* 5755 */     return replaceEach(text, searchList, replacementList, true, timeToLive);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String replaceEach(String text, String[] searchList, String[] replacementList, boolean repeat, int timeToLive) {
/* 5814 */     if (text == null || text.isEmpty() || searchList == null || searchList.length == 0 || replacementList == null || replacementList.length == 0)
/*      */     {
/* 5816 */       return text;
/*      */     }
/*      */ 
/*      */     
/* 5820 */     if (timeToLive < 0) {
/* 5821 */       throw new IllegalStateException("Aborting to protect against StackOverflowError - output of one loop is the input of another");
/*      */     }
/*      */ 
/*      */     
/* 5825 */     int searchLength = searchList.length;
/* 5826 */     int replacementLength = replacementList.length;
/*      */ 
/*      */     
/* 5829 */     if (searchLength != replacementLength) {
/* 5830 */       throw new IllegalArgumentException("Search and Replace array lengths don't match: " + searchLength + " vs " + replacementLength);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5837 */     boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];
/*      */ 
/*      */     
/* 5840 */     int textIndex = -1;
/* 5841 */     int replaceIndex = -1;
/* 5842 */     int tempIndex = -1;
/*      */ 
/*      */ 
/*      */     
/* 5846 */     for (int i = 0; i < searchLength; i++) {
/* 5847 */       if (!noMoreMatchesForReplIndex[i] && searchList[i] != null && 
/* 5848 */         !searchList[i].isEmpty() && replacementList[i] != null) {
/*      */ 
/*      */         
/* 5851 */         tempIndex = text.indexOf(searchList[i]);
/*      */ 
/*      */         
/* 5854 */         if (tempIndex == -1) {
/* 5855 */           noMoreMatchesForReplIndex[i] = true;
/*      */         }
/* 5857 */         else if (textIndex == -1 || tempIndex < textIndex) {
/* 5858 */           textIndex = tempIndex;
/* 5859 */           replaceIndex = i;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5866 */     if (textIndex == -1) {
/* 5867 */       return text;
/*      */     }
/*      */     
/* 5870 */     int start = 0;
/*      */ 
/*      */     
/* 5873 */     int increase = 0;
/*      */ 
/*      */     
/* 5876 */     for (int j = 0; j < searchList.length; j++) {
/* 5877 */       if (searchList[j] != null && replacementList[j] != null) {
/*      */ 
/*      */         
/* 5880 */         int greater = replacementList[j].length() - searchList[j].length();
/* 5881 */         if (greater > 0) {
/* 5882 */           increase += 3 * greater;
/*      */         }
/*      */       } 
/*      */     } 
/* 5886 */     increase = Math.min(increase, text.length() / 5);
/*      */     
/* 5888 */     StringBuilder buf = new StringBuilder(text.length() + increase);
/*      */     
/* 5890 */     while (textIndex != -1) {
/*      */       int m;
/* 5892 */       for (m = start; m < textIndex; m++) {
/* 5893 */         buf.append(text.charAt(m));
/*      */       }
/* 5895 */       buf.append(replacementList[replaceIndex]);
/*      */       
/* 5897 */       start = textIndex + searchList[replaceIndex].length();
/*      */       
/* 5899 */       textIndex = -1;
/* 5900 */       replaceIndex = -1;
/* 5901 */       tempIndex = -1;
/*      */ 
/*      */       
/* 5904 */       for (m = 0; m < searchLength; m++) {
/* 5905 */         if (!noMoreMatchesForReplIndex[m] && searchList[m] != null && 
/* 5906 */           !searchList[m].isEmpty() && replacementList[m] != null) {
/*      */ 
/*      */           
/* 5909 */           tempIndex = text.indexOf(searchList[m], start);
/*      */ 
/*      */           
/* 5912 */           if (tempIndex == -1) {
/* 5913 */             noMoreMatchesForReplIndex[m] = true;
/*      */           }
/* 5915 */           else if (textIndex == -1 || tempIndex < textIndex) {
/* 5916 */             textIndex = tempIndex;
/* 5917 */             replaceIndex = m;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 5924 */     int textLength = text.length();
/* 5925 */     for (int k = start; k < textLength; k++) {
/* 5926 */       buf.append(text.charAt(k));
/*      */     }
/* 5928 */     String result = buf.toString();
/* 5929 */     if (!repeat) {
/* 5930 */       return result;
/*      */     }
/*      */     
/* 5933 */     return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceChars(String str, char searchChar, char replaceChar) {
/* 5959 */     if (str == null) {
/* 5960 */       return null;
/*      */     }
/* 5962 */     return str.replace(searchChar, replaceChar);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceChars(String str, String searchChars, String replaceChars) {
/* 6002 */     if (isEmpty(str) || isEmpty(searchChars)) {
/* 6003 */       return str;
/*      */     }
/* 6005 */     if (replaceChars == null) {
/* 6006 */       replaceChars = "";
/*      */     }
/* 6008 */     boolean modified = false;
/* 6009 */     int replaceCharsLength = replaceChars.length();
/* 6010 */     int strLength = str.length();
/* 6011 */     StringBuilder buf = new StringBuilder(strLength);
/* 6012 */     for (int i = 0; i < strLength; i++) {
/* 6013 */       char ch = str.charAt(i);
/* 6014 */       int index = searchChars.indexOf(ch);
/* 6015 */       if (index >= 0) {
/* 6016 */         modified = true;
/* 6017 */         if (index < replaceCharsLength) {
/* 6018 */           buf.append(replaceChars.charAt(index));
/*      */         }
/*      */       } else {
/* 6021 */         buf.append(ch);
/*      */       } 
/*      */     } 
/* 6024 */     if (modified) {
/* 6025 */       return buf.toString();
/*      */     }
/* 6027 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String overlay(String str, String overlay, int start, int end) {
/* 6062 */     if (str == null) {
/* 6063 */       return null;
/*      */     }
/* 6065 */     if (overlay == null) {
/* 6066 */       overlay = "";
/*      */     }
/* 6068 */     int len = str.length();
/* 6069 */     if (start < 0) {
/* 6070 */       start = 0;
/*      */     }
/* 6072 */     if (start > len) {
/* 6073 */       start = len;
/*      */     }
/* 6075 */     if (end < 0) {
/* 6076 */       end = 0;
/*      */     }
/* 6078 */     if (end > len) {
/* 6079 */       end = len;
/*      */     }
/* 6081 */     if (start > end) {
/* 6082 */       int temp = start;
/* 6083 */       start = end;
/* 6084 */       end = temp;
/*      */     } 
/* 6086 */     return str.substring(0, start) + overlay + str
/*      */       
/* 6088 */       .substring(end);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String chomp(String str) {
/* 6119 */     if (isEmpty(str)) {
/* 6120 */       return str;
/*      */     }
/*      */     
/* 6123 */     if (str.length() == 1) {
/* 6124 */       char ch = str.charAt(0);
/* 6125 */       if (ch == '\r' || ch == '\n') {
/* 6126 */         return "";
/*      */       }
/* 6128 */       return str;
/*      */     } 
/*      */     
/* 6131 */     int lastIdx = str.length() - 1;
/* 6132 */     char last = str.charAt(lastIdx);
/*      */     
/* 6134 */     if (last == '\n') {
/* 6135 */       if (str.charAt(lastIdx - 1) == '\r') {
/* 6136 */         lastIdx--;
/*      */       }
/* 6138 */     } else if (last != '\r') {
/* 6139 */       lastIdx++;
/*      */     } 
/* 6141 */     return str.substring(0, lastIdx);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String chomp(String str, String separator) {
/* 6173 */     return removeEnd(str, separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String chop(String str) {
/* 6202 */     if (str == null) {
/* 6203 */       return null;
/*      */     }
/* 6205 */     int strLen = str.length();
/* 6206 */     if (strLen < 2) {
/* 6207 */       return "";
/*      */     }
/* 6209 */     int lastIdx = strLen - 1;
/* 6210 */     String ret = str.substring(0, lastIdx);
/* 6211 */     char last = str.charAt(lastIdx);
/* 6212 */     if (last == '\n' && ret.charAt(lastIdx - 1) == '\r') {
/* 6213 */       return ret.substring(0, lastIdx - 1);
/*      */     }
/* 6215 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String repeat(String str, int repeat) {
/*      */     char ch0, ch1, output2[];
/*      */     int i;
/* 6244 */     if (str == null) {
/* 6245 */       return null;
/*      */     }
/* 6247 */     if (repeat <= 0) {
/* 6248 */       return "";
/*      */     }
/* 6250 */     int inputLength = str.length();
/* 6251 */     if (repeat == 1 || inputLength == 0) {
/* 6252 */       return str;
/*      */     }
/* 6254 */     if (inputLength == 1 && repeat <= 8192) {
/* 6255 */       return repeat(str.charAt(0), repeat);
/*      */     }
/*      */     
/* 6258 */     int outputLength = inputLength * repeat;
/* 6259 */     switch (inputLength) {
/*      */       case 1:
/* 6261 */         return repeat(str.charAt(0), repeat);
/*      */       case 2:
/* 6263 */         ch0 = str.charAt(0);
/* 6264 */         ch1 = str.charAt(1);
/* 6265 */         output2 = new char[outputLength];
/* 6266 */         for (i = repeat * 2 - 2; i >= 0; i--, i--) {
/* 6267 */           output2[i] = ch0;
/* 6268 */           output2[i + 1] = ch1;
/*      */         } 
/* 6270 */         return new String(output2);
/*      */     } 
/* 6272 */     StringBuilder buf = new StringBuilder(outputLength);
/* 6273 */     for (int j = 0; j < repeat; j++) {
/* 6274 */       buf.append(str);
/*      */     }
/* 6276 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String repeat(String str, String separator, int repeat) {
/* 6301 */     if (str == null || separator == null) {
/* 6302 */       return repeat(str, repeat);
/*      */     }
/*      */     
/* 6305 */     String result = repeat(str + separator, repeat);
/* 6306 */     return removeEnd(result, separator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String repeat(char ch, int repeat) {
/* 6332 */     if (repeat <= 0) {
/* 6333 */       return "";
/*      */     }
/* 6335 */     char[] buf = new char[repeat];
/* 6336 */     for (int i = repeat - 1; i >= 0; i--) {
/* 6337 */       buf[i] = ch;
/*      */     }
/* 6339 */     return new String(buf);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String rightPad(String str, int size) {
/* 6362 */     return rightPad(str, size, ' ');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String rightPad(String str, int size, char padChar) {
/* 6387 */     if (str == null) {
/* 6388 */       return null;
/*      */     }
/* 6390 */     int pads = size - str.length();
/* 6391 */     if (pads <= 0) {
/* 6392 */       return str;
/*      */     }
/* 6394 */     if (pads > 8192) {
/* 6395 */       return rightPad(str, size, String.valueOf(padChar));
/*      */     }
/* 6397 */     return str.concat(repeat(padChar, pads));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String rightPad(String str, int size, String padStr) {
/* 6424 */     if (str == null) {
/* 6425 */       return null;
/*      */     }
/* 6427 */     if (isEmpty(padStr)) {
/* 6428 */       padStr = " ";
/*      */     }
/* 6430 */     int padLen = padStr.length();
/* 6431 */     int strLen = str.length();
/* 6432 */     int pads = size - strLen;
/* 6433 */     if (pads <= 0) {
/* 6434 */       return str;
/*      */     }
/* 6436 */     if (padLen == 1 && pads <= 8192) {
/* 6437 */       return rightPad(str, size, padStr.charAt(0));
/*      */     }
/*      */     
/* 6440 */     if (pads == padLen)
/* 6441 */       return str.concat(padStr); 
/* 6442 */     if (pads < padLen) {
/* 6443 */       return str.concat(padStr.substring(0, pads));
/*      */     }
/* 6445 */     char[] padding = new char[pads];
/* 6446 */     char[] padChars = padStr.toCharArray();
/* 6447 */     for (int i = 0; i < pads; i++) {
/* 6448 */       padding[i] = padChars[i % padLen];
/*      */     }
/* 6450 */     return str.concat(new String(padding));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String leftPad(String str, int size) {
/* 6474 */     return leftPad(str, size, ' ');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String leftPad(String str, int size, char padChar) {
/* 6499 */     if (str == null) {
/* 6500 */       return null;
/*      */     }
/* 6502 */     int pads = size - str.length();
/* 6503 */     if (pads <= 0) {
/* 6504 */       return str;
/*      */     }
/* 6506 */     if (pads > 8192) {
/* 6507 */       return leftPad(str, size, String.valueOf(padChar));
/*      */     }
/* 6509 */     return repeat(padChar, pads).concat(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String leftPad(String str, int size, String padStr) {
/* 6536 */     if (str == null) {
/* 6537 */       return null;
/*      */     }
/* 6539 */     if (isEmpty(padStr)) {
/* 6540 */       padStr = " ";
/*      */     }
/* 6542 */     int padLen = padStr.length();
/* 6543 */     int strLen = str.length();
/* 6544 */     int pads = size - strLen;
/* 6545 */     if (pads <= 0) {
/* 6546 */       return str;
/*      */     }
/* 6548 */     if (padLen == 1 && pads <= 8192) {
/* 6549 */       return leftPad(str, size, padStr.charAt(0));
/*      */     }
/*      */     
/* 6552 */     if (pads == padLen)
/* 6553 */       return padStr.concat(str); 
/* 6554 */     if (pads < padLen) {
/* 6555 */       return padStr.substring(0, pads).concat(str);
/*      */     }
/* 6557 */     char[] padding = new char[pads];
/* 6558 */     char[] padChars = padStr.toCharArray();
/* 6559 */     for (int i = 0; i < pads; i++) {
/* 6560 */       padding[i] = padChars[i % padLen];
/*      */     }
/* 6562 */     return (new String(padding)).concat(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int length(CharSequence cs) {
/* 6578 */     return (cs == null) ? 0 : cs.length();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String center(String str, int size) {
/* 6607 */     return center(str, size, ' ');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String center(String str, int size, char padChar) {
/* 6635 */     if (str == null || size <= 0) {
/* 6636 */       return str;
/*      */     }
/* 6638 */     int strLen = str.length();
/* 6639 */     int pads = size - strLen;
/* 6640 */     if (pads <= 0) {
/* 6641 */       return str;
/*      */     }
/* 6643 */     str = leftPad(str, strLen + pads / 2, padChar);
/* 6644 */     str = rightPad(str, size, padChar);
/* 6645 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String center(String str, int size, String padStr) {
/* 6675 */     if (str == null || size <= 0) {
/* 6676 */       return str;
/*      */     }
/* 6678 */     if (isEmpty(padStr)) {
/* 6679 */       padStr = " ";
/*      */     }
/* 6681 */     int strLen = str.length();
/* 6682 */     int pads = size - strLen;
/* 6683 */     if (pads <= 0) {
/* 6684 */       return str;
/*      */     }
/* 6686 */     str = leftPad(str, strLen + pads / 2, padStr);
/* 6687 */     str = rightPad(str, size, padStr);
/* 6688 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String upperCase(String str) {
/* 6713 */     if (str == null) {
/* 6714 */       return null;
/*      */     }
/* 6716 */     return str.toUpperCase();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String upperCase(String str, Locale locale) {
/* 6736 */     if (str == null) {
/* 6737 */       return null;
/*      */     }
/* 6739 */     return str.toUpperCase(locale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String lowerCase(String str) {
/* 6762 */     if (str == null) {
/* 6763 */       return null;
/*      */     }
/* 6765 */     return str.toLowerCase();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String lowerCase(String str, Locale locale) {
/* 6785 */     if (str == null) {
/* 6786 */       return null;
/*      */     }
/* 6788 */     return str.toLowerCase(locale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String capitalize(String str) {
/*      */     int strLen;
/* 6814 */     if (str == null || (strLen = str.length()) == 0) {
/* 6815 */       return str;
/*      */     }
/*      */     
/* 6818 */     int firstCodepoint = str.codePointAt(0);
/* 6819 */     int newCodePoint = Character.toTitleCase(firstCodepoint);
/* 6820 */     if (firstCodepoint == newCodePoint)
/*      */     {
/* 6822 */       return str;
/*      */     }
/*      */     
/* 6825 */     int[] newCodePoints = new int[strLen];
/* 6826 */     int outOffset = 0;
/* 6827 */     newCodePoints[outOffset++] = newCodePoint; int inOffset;
/* 6828 */     for (inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
/* 6829 */       int codepoint = str.codePointAt(inOffset);
/* 6830 */       newCodePoints[outOffset++] = codepoint;
/* 6831 */       inOffset += Character.charCount(codepoint);
/*      */     } 
/* 6833 */     return new String(newCodePoints, 0, outOffset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String uncapitalize(String str) {
/*      */     int strLen;
/* 6859 */     if (str == null || (strLen = str.length()) == 0) {
/* 6860 */       return str;
/*      */     }
/*      */     
/* 6863 */     int firstCodepoint = str.codePointAt(0);
/* 6864 */     int newCodePoint = Character.toLowerCase(firstCodepoint);
/* 6865 */     if (firstCodepoint == newCodePoint)
/*      */     {
/* 6867 */       return str;
/*      */     }
/*      */     
/* 6870 */     int[] newCodePoints = new int[strLen];
/* 6871 */     int outOffset = 0;
/* 6872 */     newCodePoints[outOffset++] = newCodePoint; int inOffset;
/* 6873 */     for (inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
/* 6874 */       int codepoint = str.codePointAt(inOffset);
/* 6875 */       newCodePoints[outOffset++] = codepoint;
/* 6876 */       inOffset += Character.charCount(codepoint);
/*      */     } 
/* 6878 */     return new String(newCodePoints, 0, outOffset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String swapCase(String str) {
/* 6909 */     if (isEmpty(str)) {
/* 6910 */       return str;
/*      */     }
/*      */     
/* 6913 */     int strLen = str.length();
/* 6914 */     int[] newCodePoints = new int[strLen];
/* 6915 */     int outOffset = 0; int i;
/* 6916 */     for (i = 0; i < strLen; ) {
/* 6917 */       int newCodePoint, oldCodepoint = str.codePointAt(i);
/*      */       
/* 6919 */       if (Character.isUpperCase(oldCodepoint)) {
/* 6920 */         newCodePoint = Character.toLowerCase(oldCodepoint);
/* 6921 */       } else if (Character.isTitleCase(oldCodepoint)) {
/* 6922 */         newCodePoint = Character.toLowerCase(oldCodepoint);
/* 6923 */       } else if (Character.isLowerCase(oldCodepoint)) {
/* 6924 */         newCodePoint = Character.toUpperCase(oldCodepoint);
/*      */       } else {
/* 6926 */         newCodePoint = oldCodepoint;
/*      */       } 
/* 6928 */       newCodePoints[outOffset++] = newCodePoint;
/* 6929 */       i += Character.charCount(newCodePoint);
/*      */     } 
/* 6931 */     return new String(newCodePoints, 0, outOffset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int countMatches(CharSequence str, CharSequence sub) {
/* 6957 */     if (isEmpty(str) || isEmpty(sub)) {
/* 6958 */       return 0;
/*      */     }
/* 6960 */     int count = 0;
/* 6961 */     int idx = 0;
/* 6962 */     while ((idx = CharSequenceUtils.indexOf(str, sub, idx)) != -1) {
/* 6963 */       count++;
/* 6964 */       idx += sub.length();
/*      */     } 
/* 6966 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int countMatches(CharSequence str, char ch) {
/* 6989 */     if (isEmpty(str)) {
/* 6990 */       return 0;
/*      */     }
/* 6992 */     int count = 0;
/*      */     
/* 6994 */     for (int i = 0; i < str.length(); i++) {
/* 6995 */       if (ch == str.charAt(i)) {
/* 6996 */         count++;
/*      */       }
/*      */     } 
/* 6999 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAlpha(CharSequence cs) {
/* 7025 */     if (isEmpty(cs)) {
/* 7026 */       return false;
/*      */     }
/* 7028 */     int sz = cs.length();
/* 7029 */     for (int i = 0; i < sz; i++) {
/* 7030 */       if (!Character.isLetter(cs.charAt(i))) {
/* 7031 */         return false;
/*      */       }
/*      */     } 
/* 7034 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAlphaSpace(CharSequence cs) {
/* 7060 */     if (cs == null) {
/* 7061 */       return false;
/*      */     }
/* 7063 */     int sz = cs.length();
/* 7064 */     for (int i = 0; i < sz; i++) {
/* 7065 */       if (!Character.isLetter(cs.charAt(i)) && cs.charAt(i) != ' ') {
/* 7066 */         return false;
/*      */       }
/*      */     } 
/* 7069 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAlphanumeric(CharSequence cs) {
/* 7095 */     if (isEmpty(cs)) {
/* 7096 */       return false;
/*      */     }
/* 7098 */     int sz = cs.length();
/* 7099 */     for (int i = 0; i < sz; i++) {
/* 7100 */       if (!Character.isLetterOrDigit(cs.charAt(i))) {
/* 7101 */         return false;
/*      */       }
/*      */     } 
/* 7104 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAlphanumericSpace(CharSequence cs) {
/* 7130 */     if (cs == null) {
/* 7131 */       return false;
/*      */     }
/* 7133 */     int sz = cs.length();
/* 7134 */     for (int i = 0; i < sz; i++) {
/* 7135 */       if (!Character.isLetterOrDigit(cs.charAt(i)) && cs.charAt(i) != ' ') {
/* 7136 */         return false;
/*      */       }
/*      */     } 
/* 7139 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAsciiPrintable(CharSequence cs) {
/* 7169 */     if (cs == null) {
/* 7170 */       return false;
/*      */     }
/* 7172 */     int sz = cs.length();
/* 7173 */     for (int i = 0; i < sz; i++) {
/* 7174 */       if (!CharUtils.isAsciiPrintable(cs.charAt(i))) {
/* 7175 */         return false;
/*      */       }
/*      */     } 
/* 7178 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNumeric(CharSequence cs) {
/* 7213 */     if (isEmpty(cs)) {
/* 7214 */       return false;
/*      */     }
/* 7216 */     int sz = cs.length();
/* 7217 */     for (int i = 0; i < sz; i++) {
/* 7218 */       if (!Character.isDigit(cs.charAt(i))) {
/* 7219 */         return false;
/*      */       }
/*      */     } 
/* 7222 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNumericSpace(CharSequence cs) {
/* 7252 */     if (cs == null) {
/* 7253 */       return false;
/*      */     }
/* 7255 */     int sz = cs.length();
/* 7256 */     for (int i = 0; i < sz; i++) {
/* 7257 */       if (!Character.isDigit(cs.charAt(i)) && cs.charAt(i) != ' ') {
/* 7258 */         return false;
/*      */       }
/*      */     } 
/* 7261 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getDigits(String str) {
/* 7287 */     if (isEmpty(str)) {
/* 7288 */       return str;
/*      */     }
/* 7290 */     int sz = str.length();
/* 7291 */     StringBuilder strDigits = new StringBuilder(sz);
/* 7292 */     for (int i = 0; i < sz; i++) {
/* 7293 */       char tempChar = str.charAt(i);
/* 7294 */       if (Character.isDigit(tempChar)) {
/* 7295 */         strDigits.append(tempChar);
/*      */       }
/*      */     } 
/* 7298 */     return strDigits.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isWhitespace(CharSequence cs) {
/* 7324 */     if (cs == null) {
/* 7325 */       return false;
/*      */     }
/* 7327 */     int sz = cs.length();
/* 7328 */     for (int i = 0; i < sz; i++) {
/* 7329 */       if (!Character.isWhitespace(cs.charAt(i))) {
/* 7330 */         return false;
/*      */       }
/*      */     } 
/* 7333 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAllLowerCase(CharSequence cs) {
/* 7359 */     if (cs == null || isEmpty(cs)) {
/* 7360 */       return false;
/*      */     }
/* 7362 */     int sz = cs.length();
/* 7363 */     for (int i = 0; i < sz; i++) {
/* 7364 */       if (!Character.isLowerCase(cs.charAt(i))) {
/* 7365 */         return false;
/*      */       }
/*      */     } 
/* 7368 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAllUpperCase(CharSequence cs) {
/* 7394 */     if (cs == null || isEmpty(cs)) {
/* 7395 */       return false;
/*      */     }
/* 7397 */     int sz = cs.length();
/* 7398 */     for (int i = 0; i < sz; i++) {
/* 7399 */       if (!Character.isUpperCase(cs.charAt(i))) {
/* 7400 */         return false;
/*      */       }
/*      */     } 
/* 7403 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isMixedCase(CharSequence cs) {
/* 7429 */     if (isEmpty(cs) || cs.length() == 1) {
/* 7430 */       return false;
/*      */     }
/* 7432 */     boolean containsUppercase = false;
/* 7433 */     boolean containsLowercase = false;
/* 7434 */     int sz = cs.length();
/* 7435 */     for (int i = 0; i < sz; i++) {
/* 7436 */       if (containsUppercase && containsLowercase)
/* 7437 */         return true; 
/* 7438 */       if (Character.isUpperCase(cs.charAt(i))) {
/* 7439 */         containsUppercase = true;
/* 7440 */       } else if (Character.isLowerCase(cs.charAt(i))) {
/* 7441 */         containsLowercase = true;
/*      */       } 
/*      */     } 
/* 7444 */     return (containsUppercase && containsLowercase);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String defaultString(String str) {
/* 7466 */     return defaultString(str, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String defaultString(String str, String defaultStr) {
/* 7487 */     return (str == null) ? defaultStr : str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T extends CharSequence> T firstNonBlank(T... values) {
/* 7517 */     if (values != null) {
/* 7518 */       for (T val : values) {
/* 7519 */         if (isNotBlank((CharSequence)val)) {
/* 7520 */           return val;
/*      */         }
/*      */       } 
/*      */     }
/* 7524 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static <T extends CharSequence> T firstNonEmpty(T... values) {
/* 7552 */     if (values != null) {
/* 7553 */       for (T val : values) {
/* 7554 */         if (isNotEmpty((CharSequence)val)) {
/* 7555 */           return val;
/*      */         }
/*      */       } 
/*      */     }
/* 7559 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends CharSequence> T defaultIfBlank(T str, T defaultStr) {
/* 7583 */     return isBlank((CharSequence)str) ? defaultStr : str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
/* 7605 */     return isEmpty((CharSequence)str) ? defaultStr : str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String rotate(String str, int shift) {
/* 7637 */     if (str == null) {
/* 7638 */       return null;
/*      */     }
/*      */     
/* 7641 */     int strLen = str.length();
/* 7642 */     if (shift == 0 || strLen == 0 || shift % strLen == 0) {
/* 7643 */       return str;
/*      */     }
/*      */     
/* 7646 */     StringBuilder builder = new StringBuilder(strLen);
/* 7647 */     int offset = -(shift % strLen);
/* 7648 */     builder.append(substring(str, offset));
/* 7649 */     builder.append(substring(str, 0, offset));
/* 7650 */     return builder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String reverse(String str) {
/* 7670 */     if (str == null) {
/* 7671 */       return null;
/*      */     }
/* 7673 */     return (new StringBuilder(str)).reverse().toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String reverseDelimited(String str, char separatorChar) {
/* 7696 */     if (str == null) {
/* 7697 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 7701 */     String[] strs = split(str, separatorChar);
/* 7702 */     ArrayUtils.reverse((Object[])strs);
/* 7703 */     return join((Object[])strs, separatorChar);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String abbreviate(String str, int maxWidth) {
/* 7740 */     String defaultAbbrevMarker = "...";
/* 7741 */     return abbreviate(str, "...", 0, maxWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String abbreviate(String str, int offset, int maxWidth) {
/* 7780 */     String defaultAbbrevMarker = "...";
/* 7781 */     return abbreviate(str, "...", offset, maxWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String abbreviate(String str, String abbrevMarker, int maxWidth) {
/* 7821 */     return abbreviate(str, abbrevMarker, 0, maxWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String abbreviate(String str, String abbrevMarker, int offset, int maxWidth) {
/* 7862 */     if (isEmpty(str) || isEmpty(abbrevMarker)) {
/* 7863 */       return str;
/*      */     }
/*      */     
/* 7866 */     int abbrevMarkerLength = abbrevMarker.length();
/* 7867 */     int minAbbrevWidth = abbrevMarkerLength + 1;
/* 7868 */     int minAbbrevWidthOffset = abbrevMarkerLength + abbrevMarkerLength + 1;
/*      */     
/* 7870 */     if (maxWidth < minAbbrevWidth) {
/* 7871 */       throw new IllegalArgumentException(String.format("Minimum abbreviation width is %d", new Object[] { Integer.valueOf(minAbbrevWidth) }));
/*      */     }
/* 7873 */     if (str.length() <= maxWidth) {
/* 7874 */       return str;
/*      */     }
/* 7876 */     if (offset > str.length()) {
/* 7877 */       offset = str.length();
/*      */     }
/* 7879 */     if (str.length() - offset < maxWidth - abbrevMarkerLength) {
/* 7880 */       offset = str.length() - maxWidth - abbrevMarkerLength;
/*      */     }
/* 7882 */     if (offset <= abbrevMarkerLength + 1) {
/* 7883 */       return str.substring(0, maxWidth - abbrevMarkerLength) + abbrevMarker;
/*      */     }
/* 7885 */     if (maxWidth < minAbbrevWidthOffset) {
/* 7886 */       throw new IllegalArgumentException(String.format("Minimum abbreviation width with offset is %d", new Object[] { Integer.valueOf(minAbbrevWidthOffset) }));
/*      */     }
/* 7888 */     if (offset + maxWidth - abbrevMarkerLength < str.length()) {
/* 7889 */       return abbrevMarker + abbreviate(str.substring(offset), abbrevMarker, maxWidth - abbrevMarkerLength);
/*      */     }
/* 7891 */     return abbrevMarker + str.substring(str.length() - maxWidth - abbrevMarkerLength);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String abbreviateMiddle(String str, String middle, int length) {
/* 7924 */     if (isEmpty(str) || isEmpty(middle)) {
/* 7925 */       return str;
/*      */     }
/*      */     
/* 7928 */     if (length >= str.length() || length < middle.length() + 2) {
/* 7929 */       return str;
/*      */     }
/*      */     
/* 7932 */     int targetSting = length - middle.length();
/* 7933 */     int startOffset = targetSting / 2 + targetSting % 2;
/* 7934 */     int endOffset = str.length() - targetSting / 2;
/*      */     
/* 7936 */     return str.substring(0, startOffset) + middle + str
/*      */       
/* 7938 */       .substring(endOffset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String difference(String str1, String str2) {
/* 7972 */     if (str1 == null) {
/* 7973 */       return str2;
/*      */     }
/* 7975 */     if (str2 == null) {
/* 7976 */       return str1;
/*      */     }
/* 7978 */     int at = indexOfDifference(str1, str2);
/* 7979 */     if (at == -1) {
/* 7980 */       return "";
/*      */     }
/* 7982 */     return str2.substring(at);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
/* 8011 */     if (cs1 == cs2) {
/* 8012 */       return -1;
/*      */     }
/* 8014 */     if (cs1 == null || cs2 == null) {
/* 8015 */       return 0;
/*      */     }
/*      */     int i;
/* 8018 */     for (i = 0; i < cs1.length() && i < cs2.length() && 
/* 8019 */       cs1.charAt(i) == cs2.charAt(i); i++);
/*      */ 
/*      */ 
/*      */     
/* 8023 */     if (i < cs2.length() || i < cs1.length()) {
/* 8024 */       return i;
/*      */     }
/* 8026 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfDifference(CharSequence... css) {
/* 8062 */     if (css == null || css.length <= 1) {
/* 8063 */       return -1;
/*      */     }
/* 8065 */     boolean anyStringNull = false;
/* 8066 */     boolean allStringsNull = true;
/* 8067 */     int arrayLen = css.length;
/* 8068 */     int shortestStrLen = Integer.MAX_VALUE;
/* 8069 */     int longestStrLen = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8074 */     for (CharSequence cs : css) {
/* 8075 */       if (cs == null) {
/* 8076 */         anyStringNull = true;
/* 8077 */         shortestStrLen = 0;
/*      */       } else {
/* 8079 */         allStringsNull = false;
/* 8080 */         shortestStrLen = Math.min(cs.length(), shortestStrLen);
/* 8081 */         longestStrLen = Math.max(cs.length(), longestStrLen);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 8086 */     if (allStringsNull || (longestStrLen == 0 && !anyStringNull)) {
/* 8087 */       return -1;
/*      */     }
/*      */ 
/*      */     
/* 8091 */     if (shortestStrLen == 0) {
/* 8092 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 8096 */     int firstDiff = -1;
/* 8097 */     for (int stringPos = 0; stringPos < shortestStrLen; stringPos++) {
/* 8098 */       char comparisonChar = css[0].charAt(stringPos);
/* 8099 */       for (int arrayPos = 1; arrayPos < arrayLen; arrayPos++) {
/* 8100 */         if (css[arrayPos].charAt(stringPos) != comparisonChar) {
/* 8101 */           firstDiff = stringPos;
/*      */           break;
/*      */         } 
/*      */       } 
/* 8105 */       if (firstDiff != -1) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 8110 */     if (firstDiff == -1 && shortestStrLen != longestStrLen)
/*      */     {
/*      */ 
/*      */       
/* 8114 */       return shortestStrLen;
/*      */     }
/* 8116 */     return firstDiff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getCommonPrefix(String... strs) {
/* 8153 */     if (strs == null || strs.length == 0) {
/* 8154 */       return "";
/*      */     }
/* 8156 */     int smallestIndexOfDiff = indexOfDifference((CharSequence[])strs);
/* 8157 */     if (smallestIndexOfDiff == -1) {
/*      */       
/* 8159 */       if (strs[0] == null) {
/* 8160 */         return "";
/*      */       }
/* 8162 */       return strs[0];
/* 8163 */     }  if (smallestIndexOfDiff == 0)
/*      */     {
/* 8165 */       return "";
/*      */     }
/*      */     
/* 8168 */     return strs[0].substring(0, smallestIndexOfDiff);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
/* 8211 */     if (s == null || t == null) {
/* 8212 */       throw new IllegalArgumentException("Strings must not be null");
/*      */     }
/*      */     
/* 8215 */     int n = s.length();
/* 8216 */     int m = t.length();
/*      */     
/* 8218 */     if (n == 0)
/* 8219 */       return m; 
/* 8220 */     if (m == 0) {
/* 8221 */       return n;
/*      */     }
/*      */     
/* 8224 */     if (n > m) {
/*      */       
/* 8226 */       CharSequence tmp = s;
/* 8227 */       s = t;
/* 8228 */       t = tmp;
/* 8229 */       n = m;
/* 8230 */       m = t.length();
/*      */     } 
/*      */     
/* 8233 */     int[] p = new int[n + 1];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int i;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8243 */     for (i = 0; i <= n; i++) {
/* 8244 */       p[i] = i;
/*      */     }
/*      */     
/* 8247 */     for (int j = 1; j <= m; j++) {
/* 8248 */       int upper_left = p[0];
/* 8249 */       char t_j = t.charAt(j - 1);
/* 8250 */       p[0] = j;
/*      */       
/* 8252 */       for (i = 1; i <= n; i++) {
/* 8253 */         int upper = p[i];
/* 8254 */         int cost = (s.charAt(i - 1) == t_j) ? 0 : 1;
/*      */         
/* 8256 */         p[i] = Math.min(Math.min(p[i - 1] + 1, p[i] + 1), upper_left + cost);
/* 8257 */         upper_left = upper;
/*      */       } 
/*      */     } 
/*      */     
/* 8261 */     return p[n];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold) {
/* 8301 */     if (s == null || t == null) {
/* 8302 */       throw new IllegalArgumentException("Strings must not be null");
/*      */     }
/* 8304 */     if (threshold < 0) {
/* 8305 */       throw new IllegalArgumentException("Threshold must not be negative");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8352 */     int n = s.length();
/* 8353 */     int m = t.length();
/*      */ 
/*      */     
/* 8356 */     if (n == 0)
/* 8357 */       return (m <= threshold) ? m : -1; 
/* 8358 */     if (m == 0)
/* 8359 */       return (n <= threshold) ? n : -1; 
/* 8360 */     if (Math.abs(n - m) > threshold)
/*      */     {
/* 8362 */       return -1;
/*      */     }
/*      */     
/* 8365 */     if (n > m) {
/*      */       
/* 8367 */       CharSequence tmp = s;
/* 8368 */       s = t;
/* 8369 */       t = tmp;
/* 8370 */       n = m;
/* 8371 */       m = t.length();
/*      */     } 
/*      */     
/* 8374 */     int[] p = new int[n + 1];
/* 8375 */     int[] d = new int[n + 1];
/*      */ 
/*      */ 
/*      */     
/* 8379 */     int boundary = Math.min(n, threshold) + 1;
/* 8380 */     for (int i = 0; i < boundary; i++) {
/* 8381 */       p[i] = i;
/*      */     }
/*      */ 
/*      */     
/* 8385 */     Arrays.fill(p, boundary, p.length, 2147483647);
/* 8386 */     Arrays.fill(d, 2147483647);
/*      */ 
/*      */     
/* 8389 */     for (int j = 1; j <= m; j++) {
/* 8390 */       char t_j = t.charAt(j - 1);
/* 8391 */       d[0] = j;
/*      */ 
/*      */       
/* 8394 */       int min = Math.max(1, j - threshold);
/* 8395 */       int max = (j > Integer.MAX_VALUE - threshold) ? n : Math.min(n, j + threshold);
/*      */ 
/*      */       
/* 8398 */       if (min > max) {
/* 8399 */         return -1;
/*      */       }
/*      */ 
/*      */       
/* 8403 */       if (min > 1) {
/* 8404 */         d[min - 1] = Integer.MAX_VALUE;
/*      */       }
/*      */ 
/*      */       
/* 8408 */       for (int k = min; k <= max; k++) {
/* 8409 */         if (s.charAt(k - 1) == t_j) {
/*      */           
/* 8411 */           d[k] = p[k - 1];
/*      */         } else {
/*      */           
/* 8414 */           d[k] = 1 + Math.min(Math.min(d[k - 1], p[k]), p[k - 1]);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 8419 */       int[] _d = p;
/* 8420 */       p = d;
/* 8421 */       d = _d;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 8426 */     if (p[n] <= threshold) {
/* 8427 */       return p[n];
/*      */     }
/* 8429 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static double getJaroWinklerDistance(CharSequence first, CharSequence second) {
/* 8469 */     double DEFAULT_SCALING_FACTOR = 0.1D;
/*      */     
/* 8471 */     if (first == null || second == null) {
/* 8472 */       throw new IllegalArgumentException("Strings must not be null");
/*      */     }
/*      */     
/* 8475 */     int[] mtp = matches(first, second);
/* 8476 */     double m = mtp[0];
/* 8477 */     if (m == 0.0D) {
/* 8478 */       return 0.0D;
/*      */     }
/* 8480 */     double j = (m / first.length() + m / second.length() + (m - mtp[1]) / m) / 3.0D;
/* 8481 */     double jw = (j < 0.7D) ? j : (j + Math.min(0.1D, 1.0D / mtp[3]) * mtp[2] * (1.0D - j));
/* 8482 */     return Math.round(jw * 100.0D) / 100.0D;
/*      */   }
/*      */   
/*      */   private static int[] matches(CharSequence first, CharSequence second) {
/*      */     CharSequence max, min;
/* 8487 */     if (first.length() > second.length()) {
/* 8488 */       max = first;
/* 8489 */       min = second;
/*      */     } else {
/* 8491 */       max = second;
/* 8492 */       min = first;
/*      */     } 
/* 8494 */     int range = Math.max(max.length() / 2 - 1, 0);
/* 8495 */     int[] matchIndexes = new int[min.length()];
/* 8496 */     Arrays.fill(matchIndexes, -1);
/* 8497 */     boolean[] matchFlags = new boolean[max.length()];
/* 8498 */     int matches = 0;
/* 8499 */     for (int mi = 0; mi < min.length(); mi++) {
/* 8500 */       char c1 = min.charAt(mi);
/* 8501 */       for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
/* 8502 */         if (!matchFlags[xi] && c1 == max.charAt(xi)) {
/* 8503 */           matchIndexes[mi] = xi;
/* 8504 */           matchFlags[xi] = true;
/* 8505 */           matches++;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 8510 */     char[] ms1 = new char[matches];
/* 8511 */     char[] ms2 = new char[matches]; int i, si;
/* 8512 */     for (i = 0, si = 0; i < min.length(); i++) {
/* 8513 */       if (matchIndexes[i] != -1) {
/* 8514 */         ms1[si] = min.charAt(i);
/* 8515 */         si++;
/*      */       } 
/*      */     } 
/* 8518 */     for (i = 0, si = 0; i < max.length(); i++) {
/* 8519 */       if (matchFlags[i]) {
/* 8520 */         ms2[si] = max.charAt(i);
/* 8521 */         si++;
/*      */       } 
/*      */     } 
/* 8524 */     int transpositions = 0;
/* 8525 */     for (int j = 0; j < ms1.length; j++) {
/* 8526 */       if (ms1[j] != ms2[j]) {
/* 8527 */         transpositions++;
/*      */       }
/*      */     } 
/* 8530 */     int prefix = 0;
/* 8531 */     for (int k = 0; k < min.length() && 
/* 8532 */       first.charAt(k) == second.charAt(k); k++) {
/* 8533 */       prefix++;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 8538 */     return new int[] { matches, transpositions / 2, prefix, max.length() };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static int getFuzzyDistance(CharSequence term, CharSequence query, Locale locale) {
/* 8572 */     if (term == null || query == null)
/* 8573 */       throw new IllegalArgumentException("Strings must not be null"); 
/* 8574 */     if (locale == null) {
/* 8575 */       throw new IllegalArgumentException("Locale must not be null");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8582 */     String termLowerCase = term.toString().toLowerCase(locale);
/* 8583 */     String queryLowerCase = query.toString().toLowerCase(locale);
/*      */ 
/*      */     
/* 8586 */     int score = 0;
/*      */ 
/*      */ 
/*      */     
/* 8590 */     int termIndex = 0;
/*      */ 
/*      */     
/* 8593 */     int previousMatchingCharacterIndex = Integer.MIN_VALUE;
/*      */     
/* 8595 */     for (int queryIndex = 0; queryIndex < queryLowerCase.length(); queryIndex++) {
/* 8596 */       char queryChar = queryLowerCase.charAt(queryIndex);
/*      */       
/* 8598 */       boolean termCharacterMatchFound = false;
/* 8599 */       for (; termIndex < termLowerCase.length() && !termCharacterMatchFound; termIndex++) {
/* 8600 */         char termChar = termLowerCase.charAt(termIndex);
/*      */         
/* 8602 */         if (queryChar == termChar) {
/*      */           
/* 8604 */           score++;
/*      */ 
/*      */ 
/*      */           
/* 8608 */           if (previousMatchingCharacterIndex + 1 == termIndex) {
/* 8609 */             score += 2;
/*      */           }
/*      */           
/* 8612 */           previousMatchingCharacterIndex = termIndex;
/*      */ 
/*      */ 
/*      */           
/* 8616 */           termCharacterMatchFound = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 8621 */     return score;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWith(CharSequence str, CharSequence prefix) {
/* 8650 */     return startsWith(str, prefix, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix) {
/* 8676 */     return startsWith(str, prefix, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean startsWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
/* 8691 */     if (str == null || prefix == null) {
/* 8692 */       return (str == prefix);
/*      */     }
/* 8694 */     if (prefix.length() > str.length()) {
/* 8695 */       return false;
/*      */     }
/* 8697 */     return CharSequenceUtils.regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings) {
/* 8723 */     if (isEmpty(sequence) || ArrayUtils.isEmpty((Object[])searchStrings)) {
/* 8724 */       return false;
/*      */     }
/* 8726 */     for (CharSequence searchString : searchStrings) {
/* 8727 */       if (startsWith(sequence, searchString)) {
/* 8728 */         return true;
/*      */       }
/*      */     } 
/* 8731 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean endsWith(CharSequence str, CharSequence suffix) {
/* 8762 */     return endsWith(str, suffix, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
/* 8789 */     return endsWith(str, suffix, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean endsWith(CharSequence str, CharSequence suffix, boolean ignoreCase) {
/* 8804 */     if (str == null || suffix == null) {
/* 8805 */       return (str == suffix);
/*      */     }
/* 8807 */     if (suffix.length() > str.length()) {
/* 8808 */       return false;
/*      */     }
/* 8810 */     int strOffset = str.length() - suffix.length();
/* 8811 */     return CharSequenceUtils.regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String normalizeSpace(String str) {
/* 8858 */     if (isEmpty(str)) {
/* 8859 */       return str;
/*      */     }
/* 8861 */     int size = str.length();
/* 8862 */     char[] newChars = new char[size];
/* 8863 */     int count = 0;
/* 8864 */     int whitespacesCount = 0;
/* 8865 */     boolean startWhitespaces = true;
/* 8866 */     for (int i = 0; i < size; i++) {
/* 8867 */       char actualChar = str.charAt(i);
/* 8868 */       boolean isWhitespace = Character.isWhitespace(actualChar);
/* 8869 */       if (isWhitespace) {
/* 8870 */         if (whitespacesCount == 0 && !startWhitespaces) {
/* 8871 */           newChars[count++] = " ".charAt(0);
/*      */         }
/* 8873 */         whitespacesCount++;
/*      */       } else {
/* 8875 */         startWhitespaces = false;
/* 8876 */         newChars[count++] = (actualChar == ' ') ? ' ' : actualChar;
/* 8877 */         whitespacesCount = 0;
/*      */       } 
/*      */     } 
/* 8880 */     if (startWhitespaces) {
/* 8881 */       return "";
/*      */     }
/* 8883 */     return (new String(newChars, 0, count - ((whitespacesCount > 0) ? 1 : 0))).trim();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean endsWithAny(CharSequence sequence, CharSequence... searchStrings) {
/* 8908 */     if (isEmpty(sequence) || ArrayUtils.isEmpty((Object[])searchStrings)) {
/* 8909 */       return false;
/*      */     }
/* 8911 */     for (CharSequence searchString : searchStrings) {
/* 8912 */       if (endsWith(sequence, searchString)) {
/* 8913 */         return true;
/*      */       }
/*      */     } 
/* 8916 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String appendIfMissing(String str, CharSequence suffix, boolean ignoreCase, CharSequence... suffixes) {
/* 8931 */     if (str == null || isEmpty(suffix) || endsWith(str, suffix, ignoreCase)) {
/* 8932 */       return str;
/*      */     }
/* 8934 */     if (suffixes != null && suffixes.length > 0) {
/* 8935 */       for (CharSequence s : suffixes) {
/* 8936 */         if (endsWith(str, s, ignoreCase)) {
/* 8937 */           return str;
/*      */         }
/*      */       } 
/*      */     }
/* 8941 */     return str + suffix.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes) {
/* 8979 */     return appendIfMissing(str, suffix, false, suffixes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String appendIfMissingIgnoreCase(String str, CharSequence suffix, CharSequence... suffixes) {
/* 9017 */     return appendIfMissing(str, suffix, true, suffixes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String prependIfMissing(String str, CharSequence prefix, boolean ignoreCase, CharSequence... prefixes) {
/* 9032 */     if (str == null || isEmpty(prefix) || startsWith(str, prefix, ignoreCase)) {
/* 9033 */       return str;
/*      */     }
/* 9035 */     if (prefixes != null && prefixes.length > 0) {
/* 9036 */       for (CharSequence p : prefixes) {
/* 9037 */         if (startsWith(str, p, ignoreCase)) {
/* 9038 */           return str;
/*      */         }
/*      */       } 
/*      */     }
/* 9042 */     return prefix.toString() + str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes) {
/* 9080 */     return prependIfMissing(str, prefix, false, prefixes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String prependIfMissingIgnoreCase(String str, CharSequence prefix, CharSequence... prefixes) {
/* 9118 */     return prependIfMissing(str, prefix, true, prefixes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static String toString(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
/* 9138 */     return (charsetName != null) ? new String(bytes, charsetName) : new String(bytes, Charset.defaultCharset());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toEncodedString(byte[] bytes, Charset charset) {
/* 9155 */     return new String(bytes, (charset != null) ? charset : Charset.defaultCharset());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String wrap(String str, char wrapWith) {
/* 9181 */     if (isEmpty(str) || wrapWith == '\000') {
/* 9182 */       return str;
/*      */     }
/*      */     
/* 9185 */     return wrapWith + str + wrapWith;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String wrap(String str, String wrapWith) {
/* 9219 */     if (isEmpty(str) || isEmpty(wrapWith)) {
/* 9220 */       return str;
/*      */     }
/*      */     
/* 9223 */     return wrapWith.concat(str).concat(wrapWith);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String wrapIfMissing(String str, char wrapWith) {
/* 9252 */     if (isEmpty(str) || wrapWith == '\000') {
/* 9253 */       return str;
/*      */     }
/* 9255 */     StringBuilder builder = new StringBuilder(str.length() + 2);
/* 9256 */     if (str.charAt(0) != wrapWith) {
/* 9257 */       builder.append(wrapWith);
/*      */     }
/* 9259 */     builder.append(str);
/* 9260 */     if (str.charAt(str.length() - 1) != wrapWith) {
/* 9261 */       builder.append(wrapWith);
/*      */     }
/* 9263 */     return builder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String wrapIfMissing(String str, String wrapWith) {
/* 9296 */     if (isEmpty(str) || isEmpty(wrapWith)) {
/* 9297 */       return str;
/*      */     }
/* 9299 */     StringBuilder builder = new StringBuilder(str.length() + wrapWith.length() + wrapWith.length());
/* 9300 */     if (!str.startsWith(wrapWith)) {
/* 9301 */       builder.append(wrapWith);
/*      */     }
/* 9303 */     builder.append(str);
/* 9304 */     if (!str.endsWith(wrapWith)) {
/* 9305 */       builder.append(wrapWith);
/*      */     }
/* 9307 */     return builder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unwrap(String str, String wrapToken) {
/* 9336 */     if (isEmpty(str) || isEmpty(wrapToken)) {
/* 9337 */       return str;
/*      */     }
/*      */     
/* 9340 */     if (startsWith(str, wrapToken) && endsWith(str, wrapToken)) {
/* 9341 */       int startIndex = str.indexOf(wrapToken);
/* 9342 */       int endIndex = str.lastIndexOf(wrapToken);
/* 9343 */       int wrapLength = wrapToken.length();
/* 9344 */       if (startIndex != -1 && endIndex != -1) {
/* 9345 */         return str.substring(startIndex + wrapLength, endIndex);
/*      */       }
/*      */     } 
/*      */     
/* 9349 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unwrap(String str, char wrapChar) {
/* 9377 */     if (isEmpty(str) || wrapChar == '\000') {
/* 9378 */       return str;
/*      */     }
/*      */     
/* 9381 */     if (str.charAt(0) == wrapChar && str.charAt(str.length() - 1) == wrapChar) {
/* 9382 */       int startIndex = 0;
/* 9383 */       int endIndex = str.length() - 1;
/* 9384 */       if (endIndex != -1) {
/* 9385 */         return str.substring(1, endIndex);
/*      */       }
/*      */     } 
/*      */     
/* 9389 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int[] toCodePoints(CharSequence str) {
/* 9409 */     if (str == null) {
/* 9410 */       return null;
/*      */     }
/* 9412 */     if (str.length() == 0) {
/* 9413 */       return ArrayUtils.EMPTY_INT_ARRAY;
/*      */     }
/*      */     
/* 9416 */     String s = str.toString();
/* 9417 */     int[] result = new int[s.codePointCount(0, s.length())];
/* 9418 */     int index = 0;
/* 9419 */     for (int i = 0; i < result.length; i++) {
/* 9420 */       result[i] = s.codePointAt(index);
/* 9421 */       index += Character.charCount(result[i]);
/*      */     } 
/* 9423 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String valueOf(char[] value) {
/* 9435 */     return (value == null) ? null : String.valueOf(value);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */