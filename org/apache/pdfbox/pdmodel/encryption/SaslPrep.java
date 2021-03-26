/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.nio.CharBuffer;
/*     */ import java.text.Normalizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SaslPrep
/*     */ {
/*     */   static String saslPrepQuery(String str) {
/*  47 */     return saslPrep(str, true);
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
/*     */   static String saslPrepStored(String str) {
/*  65 */     return saslPrep(str, false);
/*     */   }
/*     */   
/*     */   private static String saslPrep(String str, boolean allowUnassigned) {
/*     */     int k;
/*  70 */     char[] chars = str.toCharArray();
/*     */ 
/*     */ 
/*     */     
/*  74 */     for (int i = 0; i < str.length(); i++) {
/*     */       
/*  76 */       char ch = str.charAt(i);
/*  77 */       if (nonAsciiSpace(ch))
/*     */       {
/*  79 */         chars[i] = ' ';
/*     */       }
/*     */     } 
/*     */     
/*  83 */     int length = 0;
/*  84 */     for (int j = 0; j < str.length(); j++) {
/*     */       
/*  86 */       char ch = chars[j];
/*  87 */       if (!mappedToNothing(ch))
/*     */       {
/*  89 */         chars[length++] = ch;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  94 */     String normalized = Normalizer.normalize(CharBuffer.wrap(chars, 0, length), Normalizer.Form.NFKC);
/*     */     
/*  96 */     boolean containsRandALCat = false;
/*  97 */     boolean containsLCat = false;
/*  98 */     boolean initialRandALCat = false;
/*  99 */     for (int m = 0; m < normalized.length(); ) {
/*     */       
/* 101 */       int codepoint = normalized.codePointAt(m);
/*     */       
/* 103 */       if (prohibited(codepoint))
/*     */       {
/* 105 */         throw new IllegalArgumentException("Prohibited character " + codepoint + " at position " + m);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 110 */       byte directionality = Character.getDirectionality(codepoint);
/* 111 */       boolean isRandALcat = (directionality == 1 || directionality == 2);
/*     */       
/* 113 */       containsRandALCat |= isRandALcat;
/* 114 */       k = containsLCat | ((directionality == 0) ? 1 : 0);
/*     */       
/* 116 */       int n = initialRandALCat | ((m == 0 && isRandALcat) ? 1 : 0);
/* 117 */       if (!allowUnassigned && !Character.isDefined(codepoint))
/*     */       {
/* 119 */         throw new IllegalArgumentException("Character at position " + m + " is unassigned");
/*     */       }
/*     */       
/* 122 */       m += Character.charCount(codepoint);
/*     */       
/* 124 */       if (n != 0 && m >= normalized.length() && !isRandALcat)
/*     */       {
/* 126 */         throw new IllegalArgumentException("First character is RandALCat, but last character is not");
/*     */       }
/*     */     } 
/* 129 */     if (containsRandALCat && k != 0)
/*     */     {
/* 131 */       throw new IllegalArgumentException("Contains both RandALCat characters and LCat characters");
/*     */     }
/* 133 */     return normalized;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean prohibited(int codepoint) {
/* 144 */     return (nonAsciiSpace((char)codepoint) || 
/* 145 */       asciiControl((char)codepoint) || 
/* 146 */       nonAsciiControl(codepoint) || 
/* 147 */       privateUse(codepoint) || 
/* 148 */       nonCharacterCodePoint(codepoint) || 
/* 149 */       surrogateCodePoint(codepoint) || 
/* 150 */       inappropriateForPlainText(codepoint) || 
/* 151 */       inappropriateForCanonical(codepoint) || 
/* 152 */       changeDisplayProperties(codepoint) || 
/* 153 */       tagging(codepoint));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean tagging(int codepoint) {
/* 164 */     return (codepoint == 917505 || (917536 <= codepoint && codepoint <= 917631));
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
/*     */   private static boolean changeDisplayProperties(int codepoint) {
/* 176 */     return (codepoint == 832 || codepoint == 833 || codepoint == 8206 || codepoint == 8207 || codepoint == 8234 || codepoint == 8235 || codepoint == 8236 || codepoint == 8237 || codepoint == 8238 || codepoint == 8298 || codepoint == 8299 || codepoint == 8300 || codepoint == 8301 || codepoint == 8302 || codepoint == 8303);
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
/*     */   
/*     */   private static boolean inappropriateForCanonical(int codepoint) {
/* 202 */     return (12272 <= codepoint && codepoint <= 12283);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean inappropriateForPlainText(int codepoint) {
/* 213 */     return (codepoint == 65529 || codepoint == 65530 || codepoint == 65531 || codepoint == 65532 || codepoint == 65533);
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
/*     */   private static boolean surrogateCodePoint(int codepoint) {
/* 229 */     return (55296 <= codepoint && codepoint <= 57343);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean nonCharacterCodePoint(int codepoint) {
/* 240 */     return ((64976 <= codepoint && codepoint <= 65007) || (65534 <= codepoint && codepoint <= 65535) || (131070 <= codepoint && codepoint <= 131071) || (196606 <= codepoint && codepoint <= 196607) || (262142 <= codepoint && codepoint <= 262143) || (327678 <= codepoint && codepoint <= 327679) || (393214 <= codepoint && codepoint <= 393215) || (458750 <= codepoint && codepoint <= 458751) || (524286 <= codepoint && codepoint <= 524287) || (589822 <= codepoint && codepoint <= 589823) || (655358 <= codepoint && codepoint <= 655359) || (720894 <= codepoint && codepoint <= 720895) || (786430 <= codepoint && codepoint <= 786431) || (851966 <= codepoint && codepoint <= 851967) || (917502 <= codepoint && codepoint <= 917503) || (983038 <= codepoint && codepoint <= 983039) || (1048574 <= codepoint && codepoint <= 1048575) || (1114110 <= codepoint && codepoint <= 1114111));
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
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean privateUse(int codepoint) {
/* 268 */     return ((57344 <= codepoint && codepoint <= 63743) || (61440 <= codepoint && codepoint <= 1048573) || (1048576 <= codepoint && codepoint <= 1114109));
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
/*     */   private static boolean nonAsciiControl(int codepoint) {
/* 280 */     return ((128 <= codepoint && codepoint <= 159) || codepoint == 1757 || codepoint == 1807 || codepoint == 6158 || codepoint == 8204 || codepoint == 8205 || codepoint == 8232 || codepoint == 8233 || codepoint == 8288 || codepoint == 8289 || codepoint == 8290 || codepoint == 8291 || (8298 <= codepoint && codepoint <= 8303) || codepoint == 65279 || (65529 <= codepoint && codepoint <= 65532) || (119155 <= codepoint && codepoint <= 119162));
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
/*     */   private static boolean asciiControl(char ch) {
/* 305 */     return ((Character.MIN_VALUE <= ch && ch <= '\037') || ch == '');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean nonAsciiSpace(char ch) {
/* 315 */     return (ch == ' ' || ch == ' ' || (' ' <= ch && ch <= '​') || ch == ' ' || ch == ' ' || ch == '　');
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
/*     */   private static boolean mappedToNothing(char ch) {
/* 330 */     return (ch == '­' || ch == '͏' || ch == '᠆' || ch == '᠋' || ch == '᠌' || ch == '᠍' || ch == '​' || ch == '‌' || ch == '‍' || ch == '⁠' || ('︀' <= ch && ch <= '️') || ch == '﻿');
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/SaslPrep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */