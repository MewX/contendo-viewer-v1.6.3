/*     */ package org.apache.pdfbox.util;
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
/*     */ public class NumberFormatUtil
/*     */ {
/*     */   private static final int MAX_FRACTION_DIGITS = 5;
/*  38 */   private static final long[] POWER_OF_TENS = new long[19]; static {
/*  39 */     POWER_OF_TENS[0] = 1L;
/*     */     int exp;
/*  41 */     for (exp = 1; exp < POWER_OF_TENS.length; exp++)
/*     */     {
/*  43 */       POWER_OF_TENS[exp] = POWER_OF_TENS[exp - 1] * 10L; } 
/*     */   }
/*     */   
/*  46 */   private static final int[] POWER_OF_TENS_INT = new int[10]; static {
/*  47 */     POWER_OF_TENS_INT[0] = 1;
/*     */     
/*  49 */     for (exp = 1; exp < POWER_OF_TENS_INT.length; exp++)
/*     */     {
/*  51 */       POWER_OF_TENS_INT[exp] = POWER_OF_TENS_INT[exp - 1] * 10;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int formatFloatFast(float value, int maxFractionDigits, byte[] asciiBuffer) {
/*  77 */     if (Float.isNaN(value) || 
/*  78 */       Float.isInfinite(value) || value > 9.223372E18F || value <= -9.223372E18F || maxFractionDigits > 5)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  83 */       return -1;
/*     */     }
/*     */     
/*  86 */     int offset = 0;
/*  87 */     long integerPart = (long)value;
/*     */ 
/*     */     
/*  90 */     if (value < 0.0F) {
/*     */       
/*  92 */       asciiBuffer[offset++] = 45;
/*  93 */       integerPart = -integerPart;
/*     */     } 
/*     */ 
/*     */     
/*  97 */     long fractionPart = (long)((Math.abs(value) - integerPart) * POWER_OF_TENS[maxFractionDigits] + 0.5D);
/*     */ 
/*     */     
/* 100 */     if (fractionPart >= POWER_OF_TENS[maxFractionDigits]) {
/* 101 */       integerPart++;
/* 102 */       fractionPart -= POWER_OF_TENS[maxFractionDigits];
/*     */     } 
/*     */ 
/*     */     
/* 106 */     offset = formatPositiveNumber(integerPart, getExponent(integerPart), false, asciiBuffer, offset);
/*     */     
/* 108 */     if (fractionPart > 0L && maxFractionDigits > 0) {
/*     */       
/* 110 */       asciiBuffer[offset++] = 46;
/* 111 */       offset = formatPositiveNumber(fractionPart, maxFractionDigits - 1, true, asciiBuffer, offset);
/*     */     } 
/*     */     
/* 114 */     return offset;
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
/*     */   private static int formatPositiveNumber(long number, int exp, boolean omitTrailingZeros, byte[] asciiBuffer, int startOffset) {
/* 132 */     int offset = startOffset;
/* 133 */     long remaining = number;
/*     */     
/* 135 */     while (remaining > 2147483647L && (!omitTrailingZeros || remaining > 0L)) {
/*     */       
/* 137 */       long digit = remaining / POWER_OF_TENS[exp];
/* 138 */       remaining -= digit * POWER_OF_TENS[exp];
/*     */       
/* 140 */       asciiBuffer[offset++] = (byte)(int)(48L + digit);
/* 141 */       exp--;
/*     */     } 
/*     */ 
/*     */     
/* 145 */     int remainingInt = (int)remaining;
/* 146 */     while (exp >= 0 && (!omitTrailingZeros || remainingInt > 0)) {
/*     */       
/* 148 */       int digit = remainingInt / POWER_OF_TENS_INT[exp];
/* 149 */       remainingInt -= digit * POWER_OF_TENS_INT[exp];
/*     */       
/* 151 */       asciiBuffer[offset++] = (byte)(48 + digit);
/* 152 */       exp--;
/*     */     } 
/*     */     
/* 155 */     return offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getExponent(long number) {
/* 163 */     for (int exp = 0; exp < POWER_OF_TENS.length - 1; exp++) {
/*     */       
/* 165 */       if (number < POWER_OF_TENS[exp + 1])
/*     */       {
/* 167 */         return exp;
/*     */       }
/*     */     } 
/*     */     
/* 171 */     return POWER_OF_TENS.length - 1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/NumberFormatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */