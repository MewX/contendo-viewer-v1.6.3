/*     */ package org.apache.xmlgraphics.util;
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
/*     */ public final class DoubleFormatUtil
/*     */ {
/*     */   public static void formatDouble(double source, int decimals, int precision, StringBuffer target) {
/*  60 */     int scale = (Math.abs(source) >= 1.0D) ? decimals : precision;
/*  61 */     if (tooManyDigitsUsed(source, scale) || tooCloseToRound(source, scale)) {
/*  62 */       formatDoublePrecise(source, decimals, precision, target);
/*     */     } else {
/*  64 */       formatDoubleFast(source, decimals, precision, target);
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
/*     */   public static void formatDoublePrecise(double source, int decimals, int precision, StringBuffer target) {
/*  81 */     if (isRoundedToZero(source, decimals, precision)) {
/*     */       
/*  83 */       target.append('0'); return;
/*     */     } 
/*  85 */     if (Double.isNaN(source) || Double.isInfinite(source)) {
/*     */       
/*  87 */       target.append(Double.toString(source));
/*     */       
/*     */       return;
/*     */     } 
/*  91 */     boolean negative = (source < 0.0D);
/*  92 */     if (negative) {
/*  93 */       source = -source;
/*     */       
/*  95 */       target.append('-');
/*     */     } 
/*  97 */     int scale = (source >= 1.0D) ? decimals : precision;
/*     */ 
/*     */ 
/*     */     
/* 101 */     String s = Double.toString(source);
/* 102 */     if (source >= 0.001D && source < 1.0E7D) {
/*     */       
/* 104 */       int dot = s.indexOf('.');
/* 105 */       String decS = s.substring(dot + 1);
/* 106 */       int decLength = decS.length();
/* 107 */       if (scale >= decLength) {
/* 108 */         if ("0".equals(decS)) {
/*     */           
/* 110 */           target.append(s.substring(0, dot));
/*     */         } else {
/* 112 */           target.append(s);
/*     */           
/* 114 */           for (int l = target.length() - 1; l >= 0 && target.charAt(l) == '0'; l--)
/* 115 */             target.setLength(l); 
/*     */         } 
/*     */         return;
/*     */       } 
/* 119 */       if (scale + 1 < decLength) {
/*     */         
/* 121 */         decLength = scale + 1;
/* 122 */         decS = decS.substring(0, decLength);
/*     */       } 
/* 124 */       long intP = Long.parseLong(s.substring(0, dot));
/* 125 */       long decP = Long.parseLong(decS);
/* 126 */       format(target, scale, intP, decP);
/*     */     } else {
/*     */       
/* 129 */       int dot = s.indexOf('.');
/* 130 */       assert dot >= 0;
/* 131 */       int exp = s.indexOf('E');
/* 132 */       assert exp >= 0;
/* 133 */       int exposant = Integer.parseInt(s.substring(exp + 1));
/* 134 */       String intS = s.substring(0, dot);
/* 135 */       String decS = s.substring(dot + 1, exp);
/* 136 */       int decLength = decS.length();
/* 137 */       if (exposant >= 0) {
/* 138 */         int digits = decLength - exposant;
/* 139 */         if (digits <= 0) {
/*     */ 
/*     */           
/* 142 */           target.append(intS);
/* 143 */           target.append(decS);
/* 144 */           for (int i = -digits; i > 0; i--) {
/* 145 */             target.append('0');
/*     */           }
/* 147 */         } else if (digits <= scale) {
/*     */ 
/*     */           
/* 150 */           target.append(intS);
/* 151 */           target.append(decS.substring(0, exposant));
/* 152 */           target.append('.');
/* 153 */           target.append(decS.substring(exposant));
/*     */         }
/*     */         else {
/*     */           
/* 157 */           long intP = Long.parseLong(intS) * tenPow(exposant) + Long.parseLong(decS.substring(0, exposant));
/* 158 */           long decP = Long.parseLong(decS.substring(exposant, exposant + scale + 1));
/* 159 */           format(target, scale, intP, decP);
/*     */         } 
/*     */       } else {
/*     */         
/* 163 */         exposant = -exposant;
/* 164 */         int digits = scale - exposant + 1;
/* 165 */         if (digits < 0) {
/* 166 */           target.append('0');
/* 167 */         } else if (digits == 0) {
/* 168 */           long decP = Long.parseLong(intS);
/* 169 */           format(target, scale, 0L, decP);
/* 170 */         } else if (decLength < digits) {
/* 171 */           long decP = Long.parseLong(intS) * tenPow(decLength + 1) + Long.parseLong(decS) * 10L;
/* 172 */           format(target, exposant + decLength, 0L, decP);
/*     */         } else {
/* 174 */           long subDecP = Long.parseLong(decS.substring(0, digits));
/* 175 */           long decP = Long.parseLong(intS) * tenPow(digits) + subDecP;
/* 176 */           format(target, scale, 0L, decP);
/*     */         } 
/*     */       } 
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
/*     */   private static boolean isRoundedToZero(double source, int decimals, int precision) {
/* 192 */     return (source == 0.0D || Math.abs(source) < 4.999999999999999D / tenPowDouble(Math.max(decimals, precision) + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   private static final long[] POWERS_OF_TEN_LONG = new long[19];
/* 199 */   private static final double[] POWERS_OF_TEN_DOUBLE = new double[30];
/*     */   static {
/* 201 */     POWERS_OF_TEN_LONG[0] = 1L; int i;
/* 202 */     for (i = 1; i < POWERS_OF_TEN_LONG.length; i++) {
/* 203 */       POWERS_OF_TEN_LONG[i] = POWERS_OF_TEN_LONG[i - 1] * 10L;
/*     */     }
/* 205 */     for (i = 0; i < POWERS_OF_TEN_DOUBLE.length; i++) {
/* 206 */       POWERS_OF_TEN_DOUBLE[i] = Double.parseDouble("1e" + i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long tenPow(int n) {
/* 217 */     assert n >= 0;
/* 218 */     return (n < POWERS_OF_TEN_LONG.length) ? POWERS_OF_TEN_LONG[n] : (long)Math.pow(10.0D, n);
/*     */   }
/*     */   
/*     */   private static double tenPowDouble(int n) {
/* 222 */     assert n >= 0;
/* 223 */     return (n < POWERS_OF_TEN_DOUBLE.length) ? POWERS_OF_TEN_DOUBLE[n] : Math.pow(10.0D, n);
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
/*     */   private static void format(StringBuffer target, int scale, long intP, long decP) {
/* 235 */     if (decP != 0L) {
/*     */ 
/*     */       
/* 238 */       decP += 5L;
/* 239 */       decP /= 10L;
/* 240 */       if (decP >= tenPowDouble(scale)) {
/* 241 */         intP++;
/* 242 */         decP -= tenPow(scale);
/*     */       } 
/* 244 */       if (decP != 0L)
/*     */       {
/* 246 */         while (decP % 10L == 0L) {
/* 247 */           decP /= 10L;
/* 248 */           scale--;
/*     */         } 
/*     */       }
/*     */     } 
/* 252 */     target.append(intP);
/* 253 */     if (decP != 0L) {
/* 254 */       target.append('.');
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 259 */       while (scale > 0 && ((scale > 18) ? (decP < tenPowDouble(--scale)) : (decP < tenPow(--scale))))
/*     */       {
/* 261 */         target.append('0');
/*     */       }
/* 263 */       target.append(decP);
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
/*     */   public static void formatDoubleFast(double source, int decimals, int precision, StringBuffer target) {
/* 280 */     if (isRoundedToZero(source, decimals, precision)) {
/*     */       
/* 282 */       target.append('0'); return;
/*     */     } 
/* 284 */     if (Double.isNaN(source) || Double.isInfinite(source)) {
/*     */       
/* 286 */       target.append(Double.toString(source));
/*     */       
/*     */       return;
/*     */     } 
/* 290 */     boolean isPositive = (source >= 0.0D);
/* 291 */     source = Math.abs(source);
/* 292 */     int scale = (source >= 1.0D) ? decimals : precision;
/*     */     
/* 294 */     long intPart = (long)Math.floor(source);
/* 295 */     double tenScale = tenPowDouble(scale);
/* 296 */     double fracUnroundedPart = (source - intPart) * tenScale;
/* 297 */     long fracPart = Math.round(fracUnroundedPart);
/* 298 */     if (fracPart >= tenScale) {
/* 299 */       intPart++;
/* 300 */       fracPart = Math.round(fracPart - tenScale);
/*     */     } 
/* 302 */     if (fracPart != 0L)
/*     */     {
/* 304 */       while (fracPart % 10L == 0L) {
/* 305 */         fracPart /= 10L;
/* 306 */         scale--;
/*     */       } 
/*     */     }
/*     */     
/* 310 */     if (intPart != 0L || fracPart != 0L) {
/*     */       
/* 312 */       if (!isPositive)
/*     */       {
/* 314 */         target.append('-');
/*     */       }
/*     */       
/* 317 */       target.append(intPart);
/* 318 */       if (fracPart != 0L) {
/*     */         
/* 320 */         target.append('.');
/*     */         
/* 322 */         while (scale > 0 && fracPart < tenPowDouble(--scale)) {
/* 323 */           target.append('0');
/*     */         }
/* 325 */         target.append(fracPart);
/*     */       } 
/*     */     } else {
/* 328 */       target.append('0');
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
/*     */   public static int getExponant(double value) {
/* 341 */     long exp = Double.doubleToRawLongBits(value) & 0x7FF0000000000000L;
/* 342 */     exp >>= 52L;
/* 343 */     return (int)(exp - 1023L);
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
/*     */   private static boolean tooManyDigitsUsed(double source, int scale) {
/* 356 */     double decExp = Math.log10(source);
/* 357 */     return (scale >= 308 || decExp + scale >= 14.5D);
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
/*     */   private static boolean tooCloseToRound(double source, int scale) {
/* 369 */     source = Math.abs(source);
/* 370 */     long intPart = (long)Math.floor(source);
/* 371 */     double fracPart = (source - intPart) * tenPowDouble(scale);
/* 372 */     double decExp = Math.log10(source);
/* 373 */     double range = (decExp + scale >= 12.0D) ? 0.1D : 0.001D;
/* 374 */     double distanceToRound1 = Math.abs(fracPart - Math.floor(fracPart));
/* 375 */     double distanceToRound2 = Math.abs(fracPart - Math.floor(fracPart) - 0.5D);
/* 376 */     return (distanceToRound1 <= range || distanceToRound2 <= range);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/DoubleFormatUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */