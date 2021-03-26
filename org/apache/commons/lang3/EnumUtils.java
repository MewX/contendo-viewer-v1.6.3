/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class EnumUtils
/*     */ {
/*     */   private static final String NULL_ELEMENTS_NOT_PERMITTED = "null elements not permitted";
/*     */   private static final String CANNOT_STORE_S_S_VALUES_IN_S_BITS = "Cannot store %s %s values in %s bits";
/*     */   private static final String S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE = "%s does not seem to be an Enum type";
/*     */   private static final String ENUM_CLASS_MUST_BE_DEFINED = "EnumClass must be defined.";
/*     */   
/*     */   public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass) {
/*  58 */     Map<String, E> map = new LinkedHashMap<>();
/*  59 */     for (Enum enum_ : (Enum[])enumClass.getEnumConstants()) {
/*  60 */       map.put(enum_.name(), (E)enum_);
/*     */     }
/*  62 */     return map;
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
/*     */   public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass) {
/*  75 */     return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
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
/*     */   public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName) {
/*  90 */     return (getEnum(enumClass, enumName) != null);
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
/*     */   public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(Class<E> enumClass, String enumName) {
/* 107 */     return (getEnumIgnoreCase(enumClass, enumName) != null);
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
/*     */   public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName) {
/* 122 */     if (enumName == null) {
/* 123 */       return null;
/*     */     }
/*     */     try {
/* 126 */       return Enum.valueOf(enumClass, enumName);
/* 127 */     } catch (IllegalArgumentException ex) {
/* 128 */       return null;
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
/*     */   
/*     */   public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> enumClass, String enumName) {
/* 145 */     if (enumName == null || !enumClass.isEnum()) {
/* 146 */       return null;
/*     */     }
/* 148 */     for (Enum enum_ : (Enum[])enumClass.getEnumConstants()) {
/* 149 */       if (enum_.name().equalsIgnoreCase(enumName)) {
/* 150 */         return (E)enum_;
/*     */       }
/*     */     } 
/* 153 */     return null;
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
/*     */   public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, Iterable<? extends E> values) {
/* 175 */     checkBitVectorable(enumClass);
/* 176 */     Validate.notNull(values);
/* 177 */     long total = 0L;
/* 178 */     for (Enum enum_ : values) {
/* 179 */       Validate.isTrue((enum_ != null), "null elements not permitted", new Object[0]);
/* 180 */       total |= 1L << enum_.ordinal();
/*     */     } 
/* 182 */     return total;
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
/*     */   public static <E extends Enum<E>> long[] generateBitVectors(Class<E> enumClass, Iterable<? extends E> values) {
/* 202 */     asEnum(enumClass);
/* 203 */     Validate.notNull(values);
/* 204 */     EnumSet<E> condensed = EnumSet.noneOf(enumClass);
/* 205 */     for (Enum enum_ : values) {
/* 206 */       Validate.isTrue((enum_ != null), "null elements not permitted", new Object[0]);
/* 207 */       condensed.add((E)enum_);
/*     */     } 
/* 209 */     long[] result = new long[(((Enum[])enumClass.getEnumConstants()).length - 1) / 64 + 1];
/* 210 */     for (Enum enum_ : condensed) {
/* 211 */       result[enum_.ordinal() / 64] = result[enum_.ordinal() / 64] | 1L << enum_.ordinal() % 64;
/*     */     }
/* 213 */     ArrayUtils.reverse(result);
/* 214 */     return result;
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
/*     */   @SafeVarargs
/*     */   public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, E... values) {
/* 236 */     Validate.noNullElements(values);
/* 237 */     return generateBitVector(enumClass, Arrays.asList(values));
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
/*     */   @SafeVarargs
/*     */   public static <E extends Enum<E>> long[] generateBitVectors(Class<E> enumClass, E... values) {
/* 258 */     asEnum(enumClass);
/* 259 */     Validate.noNullElements(values);
/* 260 */     EnumSet<E> condensed = EnumSet.noneOf(enumClass);
/* 261 */     Collections.addAll(condensed, values);
/* 262 */     long[] result = new long[(((Enum[])enumClass.getEnumConstants()).length - 1) / 64 + 1];
/* 263 */     for (Enum enum_ : condensed) {
/* 264 */       result[enum_.ordinal() / 64] = result[enum_.ordinal() / 64] | 1L << enum_.ordinal() % 64;
/*     */     }
/* 266 */     ArrayUtils.reverse(result);
/* 267 */     return result;
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
/*     */   public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> enumClass, long value) {
/* 284 */     checkBitVectorable(enumClass).getEnumConstants();
/* 285 */     return processBitVectors(enumClass, new long[] { value });
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
/*     */   public static <E extends Enum<E>> EnumSet<E> processBitVectors(Class<E> enumClass, long... values) {
/* 302 */     EnumSet<E> results = EnumSet.noneOf(asEnum(enumClass));
/* 303 */     long[] lvalues = ArrayUtils.clone(Validate.<long[]>notNull(values));
/* 304 */     ArrayUtils.reverse(lvalues);
/* 305 */     for (Enum enum_ : (Enum[])enumClass.getEnumConstants()) {
/* 306 */       int block = enum_.ordinal() / 64;
/* 307 */       if (block < lvalues.length && (lvalues[block] & 1L << enum_.ordinal() % 64) != 0L) {
/* 308 */         results.add((E)enum_);
/*     */       }
/*     */     } 
/* 311 */     return results;
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
/*     */   private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> enumClass) {
/* 324 */     Enum[] arrayOfEnum = asEnum(enumClass).getEnumConstants();
/* 325 */     Validate.isTrue((arrayOfEnum.length <= 64), "Cannot store %s %s values in %s bits", new Object[] {
/* 326 */           Integer.valueOf(arrayOfEnum.length), enumClass.getSimpleName(), Integer.valueOf(64)
/*     */         });
/* 328 */     return enumClass;
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
/*     */   private static <E extends Enum<E>> Class<E> asEnum(Class<E> enumClass) {
/* 341 */     Validate.notNull(enumClass, "EnumClass must be defined.", new Object[0]);
/* 342 */     Validate.isTrue(enumClass.isEnum(), "%s does not seem to be an Enum type", new Object[] { enumClass });
/* 343 */     return enumClass;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/EnumUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */