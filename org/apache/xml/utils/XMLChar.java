/*     */ package org.apache.xml.utils;
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
/*     */ public class XMLChar
/*     */ {
/*  52 */   public static final byte[] CHARS = new byte[65536];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_VALID = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_SPACE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_NAME_START = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_NAME = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_PUBID = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_CONTENT = 32;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_NCNAME_START = 64;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MASK_NCNAME = 128;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  96 */     int[] charRange = { 9, 10, 13, 13, 32, 55295, 57344, 65533 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     int[] spaceChar = { 32, 9, 13, 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     int[] nameChar = { 45, 46 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     int[] nameStartChar = { 58, 95 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     int[] pubidChar = { 10, 13, 32, 33, 35, 36, 37, 61, 95 };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     int[] pubidRange = { 39, 59, 63, 90, 97, 122 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     int[] letterRange = { 65, 90, 97, 122, 192, 214, 216, 246, 248, 305, 308, 318, 321, 328, 330, 382, 384, 451, 461, 496, 500, 501, 506, 535, 592, 680, 699, 705, 904, 906, 910, 929, 931, 974, 976, 982, 994, 1011, 1025, 1036, 1038, 1103, 1105, 1116, 1118, 1153, 1168, 1220, 1223, 1224, 1227, 1228, 1232, 1259, 1262, 1269, 1272, 1273, 1329, 1366, 1377, 1414, 1488, 1514, 1520, 1522, 1569, 1594, 1601, 1610, 1649, 1719, 1722, 1726, 1728, 1742, 1744, 1747, 1765, 1766, 2309, 2361, 2392, 2401, 2437, 2444, 2447, 2448, 2451, 2472, 2474, 2480, 2486, 2489, 2524, 2525, 2527, 2529, 2544, 2545, 2565, 2570, 2575, 2576, 2579, 2600, 2602, 2608, 2610, 2611, 2613, 2614, 2616, 2617, 2649, 2652, 2674, 2676, 2693, 2699, 2703, 2705, 2707, 2728, 2730, 2736, 2738, 2739, 2741, 2745, 2821, 2828, 2831, 2832, 2835, 2856, 2858, 2864, 2866, 2867, 2870, 2873, 2908, 2909, 2911, 2913, 2949, 2954, 2958, 2960, 2962, 2965, 2969, 2970, 2974, 2975, 2979, 2980, 2984, 2986, 2990, 2997, 2999, 3001, 3077, 3084, 3086, 3088, 3090, 3112, 3114, 3123, 3125, 3129, 3168, 3169, 3205, 3212, 3214, 3216, 3218, 3240, 3242, 3251, 3253, 3257, 3296, 3297, 3333, 3340, 3342, 3344, 3346, 3368, 3370, 3385, 3424, 3425, 3585, 3630, 3634, 3635, 3648, 3653, 3713, 3714, 3719, 3720, 3732, 3735, 3737, 3743, 3745, 3747, 3754, 3755, 3757, 3758, 3762, 3763, 3776, 3780, 3904, 3911, 3913, 3945, 4256, 4293, 4304, 4342, 4354, 4355, 4357, 4359, 4363, 4364, 4366, 4370, 4436, 4437, 4447, 4449, 4461, 4462, 4466, 4467, 4526, 4527, 4535, 4536, 4540, 4546, 7680, 7835, 7840, 7929, 7936, 7957, 7960, 7965, 7968, 8005, 8008, 8013, 8016, 8023, 8031, 8061, 8064, 8116, 8118, 8124, 8130, 8132, 8134, 8140, 8144, 8147, 8150, 8155, 8160, 8172, 8178, 8180, 8182, 8188, 8490, 8491, 8576, 8578, 12353, 12436, 12449, 12538, 12549, 12588, 44032, 55203, 12321, 12329, 19968, 40869 };
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
/* 185 */     int[] letterChar = { 902, 908, 986, 988, 990, 992, 1369, 1749, 2365, 2482, 2654, 2701, 2749, 2784, 2877, 2972, 3294, 3632, 3716, 3722, 3725, 3749, 3751, 3760, 3773, 4352, 4361, 4412, 4414, 4416, 4428, 4430, 4432, 4441, 4451, 4453, 4455, 4457, 4469, 4510, 4520, 4523, 4538, 4587, 4592, 4601, 8025, 8027, 8029, 8126, 8486, 8494, 12295 };
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
/* 202 */     int[] combiningCharRange = { 768, 837, 864, 865, 1155, 1158, 1425, 1441, 1443, 1465, 1467, 1469, 1473, 1474, 1611, 1618, 1750, 1756, 1757, 1759, 1760, 1764, 1767, 1768, 1770, 1773, 2305, 2307, 2366, 2380, 2385, 2388, 2402, 2403, 2433, 2435, 2496, 2500, 2503, 2504, 2507, 2509, 2530, 2531, 2624, 2626, 2631, 2632, 2635, 2637, 2672, 2673, 2689, 2691, 2750, 2757, 2759, 2761, 2763, 2765, 2817, 2819, 2878, 2883, 2887, 2888, 2891, 2893, 2902, 2903, 2946, 2947, 3006, 3010, 3014, 3016, 3018, 3021, 3073, 3075, 3134, 3140, 3142, 3144, 3146, 3149, 3157, 3158, 3202, 3203, 3262, 3268, 3270, 3272, 3274, 3277, 3285, 3286, 3330, 3331, 3390, 3395, 3398, 3400, 3402, 3405, 3636, 3642, 3655, 3662, 3764, 3769, 3771, 3772, 3784, 3789, 3864, 3865, 3953, 3972, 3974, 3979, 3984, 3989, 3993, 4013, 4017, 4023, 8400, 8412, 12330, 12335 };
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
/* 222 */     int[] combiningCharChar = { 1471, 1476, 1648, 2364, 2381, 2492, 2494, 2495, 2519, 2562, 2620, 2622, 2623, 2748, 2876, 3031, 3415, 3633, 3761, 3893, 3895, 3897, 3902, 3903, 3991, 4025, 8417, 12441, 12442 };
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
/* 233 */     int[] digitRange = { 48, 57, 1632, 1641, 1776, 1785, 2406, 2415, 2534, 2543, 2662, 2671, 2790, 2799, 2918, 2927, 3047, 3055, 3174, 3183, 3302, 3311, 3430, 3439, 3664, 3673, 3792, 3801, 3872, 3881 };
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
/* 244 */     int[] extenderRange = { 12337, 12341, 12445, 12446, 12540, 12542 };
/*     */ 
/*     */ 
/*     */     
/* 248 */     int[] extenderChar = { 183, 720, 721, 903, 1600, 3654, 3782, 12293 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     int[] specialChar = { 60, 38, 10, 13, 93 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     for (int i = 0; i < charRange.length; i += 2) {
/* 266 */       for (int i10 = charRange[i]; i10 <= charRange[i + 1]; i10++) {
/* 267 */         CHARS[i10] = (byte)(CHARS[i10] | 0x21);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 272 */     for (int j = 0; j < specialChar.length; j++) {
/* 273 */       CHARS[specialChar[j]] = (byte)(CHARS[specialChar[j]] & 0xFFFFFFDF);
/*     */     }
/*     */ 
/*     */     
/* 277 */     for (int k = 0; k < spaceChar.length; k++) {
/* 278 */       CHARS[spaceChar[k]] = (byte)(CHARS[spaceChar[k]] | 0x2);
/*     */     }
/*     */ 
/*     */     
/* 282 */     for (int m = 0; m < nameStartChar.length; m++) {
/* 283 */       CHARS[nameStartChar[m]] = (byte)(CHARS[nameStartChar[m]] | 0xCC);
/*     */     }
/*     */     
/* 286 */     for (int n = 0; n < letterRange.length; n += 2) {
/* 287 */       for (int i10 = letterRange[n]; i10 <= letterRange[n + 1]; i10++) {
/* 288 */         CHARS[i10] = (byte)(CHARS[i10] | 0xCC);
/*     */       }
/*     */     } 
/*     */     
/* 292 */     for (int i1 = 0; i1 < letterChar.length; i1++) {
/* 293 */       CHARS[letterChar[i1]] = (byte)(CHARS[letterChar[i1]] | 0xCC);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 298 */     for (int i2 = 0; i2 < nameChar.length; i2++) {
/* 299 */       CHARS[nameChar[i2]] = (byte)(CHARS[nameChar[i2]] | 0x88);
/*     */     }
/* 301 */     for (int i3 = 0; i3 < digitRange.length; i3 += 2) {
/* 302 */       for (int i10 = digitRange[i3]; i10 <= digitRange[i3 + 1]; i10++) {
/* 303 */         CHARS[i10] = (byte)(CHARS[i10] | 0x88);
/*     */       }
/*     */     } 
/* 306 */     for (int i4 = 0; i4 < combiningCharRange.length; i4 += 2) {
/* 307 */       for (int i10 = combiningCharRange[i4]; i10 <= combiningCharRange[i4 + 1]; i10++) {
/* 308 */         CHARS[i10] = (byte)(CHARS[i10] | 0x88);
/*     */       }
/*     */     } 
/* 311 */     for (int i5 = 0; i5 < combiningCharChar.length; i5++) {
/* 312 */       CHARS[combiningCharChar[i5]] = (byte)(CHARS[combiningCharChar[i5]] | 0x88);
/*     */     }
/* 314 */     for (int i6 = 0; i6 < extenderRange.length; i6 += 2) {
/* 315 */       for (int i10 = extenderRange[i6]; i10 <= extenderRange[i6 + 1]; i10++) {
/* 316 */         CHARS[i10] = (byte)(CHARS[i10] | 0x88);
/*     */       }
/*     */     } 
/* 319 */     for (int i7 = 0; i7 < extenderChar.length; i7++) {
/* 320 */       CHARS[extenderChar[i7]] = (byte)(CHARS[extenderChar[i7]] | 0x88);
/*     */     }
/*     */ 
/*     */     
/* 324 */     CHARS[58] = (byte)(CHARS[58] & 0xFFFFFF3F);
/*     */ 
/*     */     
/* 327 */     for (int i8 = 0; i8 < pubidChar.length; i8++) {
/* 328 */       CHARS[pubidChar[i8]] = (byte)(CHARS[pubidChar[i8]] | 0x10);
/*     */     }
/* 330 */     for (int i9 = 0; i9 < pubidRange.length; i9 += 2) {
/* 331 */       for (int i10 = pubidRange[i9]; i10 <= pubidRange[i9 + 1]; i10++) {
/* 332 */         CHARS[i10] = (byte)(CHARS[i10] | 0x10);
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
/*     */   
/*     */   public static boolean isSupplemental(int c) {
/* 348 */     return (c >= 65536 && c <= 1114111);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int supplemental(char h, char l) {
/* 359 */     return (h - 55296) * 1024 + l - 56320 + 65536;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char highSurrogate(int c) {
/* 368 */     return (char)((c - 65536 >> 10) + 55296);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char lowSurrogate(int c) {
/* 377 */     return (char)((c - 65536 & 0x3FF) + 56320);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isHighSurrogate(int c) {
/* 386 */     return (55296 <= c && c <= 56319);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLowSurrogate(int c) {
/* 395 */     return (56320 <= c && c <= 57343);
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
/*     */   public static boolean isValid(int c) {
/* 410 */     return ((c < 65536 && (CHARS[c] & 0x1) != 0) || (65536 <= c && c <= 1114111));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isInvalid(int c) {
/* 420 */     return !isValid(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isContent(int c) {
/* 429 */     return ((c < 65536 && (CHARS[c] & 0x20) != 0) || (65536 <= c && c <= 1114111));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMarkup(int c) {
/* 440 */     return (c == 60 || c == 38 || c == 37);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSpace(int c) {
/* 450 */     return (c < 65536 && (CHARS[c] & 0x2) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNameStart(int c) {
/* 461 */     return (c < 65536 && (CHARS[c] & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isName(int c) {
/* 472 */     return (c < 65536 && (CHARS[c] & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNCNameStart(int c) {
/* 483 */     return (c < 65536 && (CHARS[c] & 0x40) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNCName(int c) {
/* 494 */     return (c < 65536 && (CHARS[c] & 0x80) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPubid(int c) {
/* 505 */     return (c < 65536 && (CHARS[c] & 0x10) != 0);
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
/*     */   public static boolean isValidName(String name) {
/* 519 */     if (name.length() == 0)
/* 520 */       return false; 
/* 521 */     char ch = name.charAt(0);
/* 522 */     if (!isNameStart(ch))
/* 523 */       return false; 
/* 524 */     for (int i = 1; i < name.length(); i++) {
/* 525 */       ch = name.charAt(i);
/* 526 */       if (!isName(ch)) {
/* 527 */         return false;
/*     */       }
/*     */     } 
/* 530 */     return true;
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
/*     */   public static boolean isValidNCName(String ncName) {
/* 546 */     if (ncName.length() == 0)
/* 547 */       return false; 
/* 548 */     char ch = ncName.charAt(0);
/* 549 */     if (!isNCNameStart(ch))
/* 550 */       return false; 
/* 551 */     for (int i = 1; i < ncName.length(); i++) {
/* 552 */       ch = ncName.charAt(i);
/* 553 */       if (!isNCName(ch)) {
/* 554 */         return false;
/*     */       }
/*     */     } 
/* 557 */     return true;
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
/*     */   public static boolean isValidNmtoken(String nmtoken) {
/* 571 */     if (nmtoken.length() == 0)
/* 572 */       return false; 
/* 573 */     for (int i = 0; i < nmtoken.length(); i++) {
/* 574 */       char ch = nmtoken.charAt(i);
/* 575 */       if (!isName(ch)) {
/* 576 */         return false;
/*     */       }
/*     */     } 
/* 579 */     return true;
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
/*     */   public static boolean isValidIANAEncoding(String ianaEncoding) {
/* 597 */     if (ianaEncoding != null) {
/* 598 */       int length = ianaEncoding.length();
/* 599 */       if (length > 0) {
/* 600 */         char c = ianaEncoding.charAt(0);
/* 601 */         if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
/* 602 */           for (int i = 1; i < length; i++) {
/* 603 */             c = ianaEncoding.charAt(i);
/* 604 */             if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '.' && c != '_' && c != '-')
/*     */             {
/*     */               
/* 607 */               return false;
/*     */             }
/*     */           } 
/* 610 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 614 */     return false;
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
/*     */   public static boolean isValidJavaEncoding(String javaEncoding) {
/* 626 */     if (javaEncoding != null) {
/* 627 */       int length = javaEncoding.length();
/* 628 */       if (length > 0) {
/* 629 */         for (int i = 1; i < length; i++) {
/* 630 */           char c = javaEncoding.charAt(i);
/* 631 */           if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '.' && c != '_' && c != '-')
/*     */           {
/*     */             
/* 634 */             return false;
/*     */           }
/*     */         } 
/* 637 */         return true;
/*     */       } 
/*     */     } 
/* 640 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValidQName(String str) {
/* 650 */     int colon = str.indexOf(':');
/*     */     
/* 652 */     if (colon == 0 || colon == str.length() - 1) {
/* 653 */       return false;
/*     */     }
/*     */     
/* 656 */     if (colon > 0) {
/* 657 */       String prefix = str.substring(0, colon);
/* 658 */       String localPart = str.substring(colon + 1);
/* 659 */       return (isValidNCName(prefix) && isValidNCName(localPart));
/*     */     } 
/*     */     
/* 662 */     return isValidNCName(str);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/XMLChar.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */