/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharSequenceUtils
/*     */ {
/*     */   private static final int NOT_FOUND = -1;
/*     */   
/*     */   public static CharSequence subSequence(CharSequence cs, int start) {
/*  57 */     return (cs == null) ? null : cs.subSequence(start, cs.length());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int indexOf(CharSequence cs, int searchChar, int start) {
/* 100 */     if (cs instanceof String) {
/* 101 */       return ((String)cs).indexOf(searchChar, start);
/*     */     }
/* 103 */     int sz = cs.length();
/* 104 */     if (start < 0) {
/* 105 */       start = 0;
/*     */     }
/* 107 */     if (searchChar < 65536) {
/* 108 */       for (int i = start; i < sz; i++) {
/* 109 */         if (cs.charAt(i) == searchChar) {
/* 110 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 115 */     if (searchChar <= 1114111) {
/* 116 */       char[] chars = Character.toChars(searchChar);
/* 117 */       for (int i = start; i < sz - 1; i++) {
/* 118 */         char high = cs.charAt(i);
/* 119 */         char low = cs.charAt(i + 1);
/* 120 */         if (high == chars[0] && low == chars[1]) {
/* 121 */           return i;
/*     */         }
/*     */       } 
/*     */     } 
/* 125 */     return -1;
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
/*     */   static int indexOf(CharSequence cs, CharSequence searchChar, int start) {
/* 137 */     return cs.toString().indexOf(searchChar.toString(), start);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int lastIndexOf(CharSequence cs, int searchChar, int start) {
/* 176 */     if (cs instanceof String) {
/* 177 */       return ((String)cs).lastIndexOf(searchChar, start);
/*     */     }
/* 179 */     int sz = cs.length();
/* 180 */     if (start < 0) {
/* 181 */       return -1;
/*     */     }
/* 183 */     if (start >= sz) {
/* 184 */       start = sz - 1;
/*     */     }
/* 186 */     if (searchChar < 65536) {
/* 187 */       for (int i = start; i >= 0; i--) {
/* 188 */         if (cs.charAt(i) == searchChar) {
/* 189 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 195 */     if (searchChar <= 1114111) {
/* 196 */       char[] chars = Character.toChars(searchChar);
/*     */       
/* 198 */       if (start == sz - 1) {
/* 199 */         return -1;
/*     */       }
/* 201 */       for (int i = start; i >= 0; i--) {
/* 202 */         char high = cs.charAt(i);
/* 203 */         char low = cs.charAt(i + 1);
/* 204 */         if (chars[0] == high && chars[1] == low) {
/* 205 */           return i;
/*     */         }
/*     */       } 
/*     */     } 
/* 209 */     return -1;
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
/*     */   static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start) {
/* 221 */     return cs.toString().lastIndexOf(searchChar.toString(), start);
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
/*     */   static char[] toCharArray(CharSequence cs) {
/* 240 */     if (cs instanceof String) {
/* 241 */       return ((String)cs).toCharArray();
/*     */     }
/* 243 */     int sz = cs.length();
/* 244 */     char[] array = new char[cs.length()];
/* 245 */     for (int i = 0; i < sz; i++) {
/* 246 */       array[i] = cs.charAt(i);
/*     */     }
/* 248 */     return array;
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
/*     */   static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
/* 264 */     if (cs instanceof String && substring instanceof String) {
/* 265 */       return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
/*     */     }
/* 267 */     int index1 = thisStart;
/* 268 */     int index2 = start;
/* 269 */     int tmpLen = length;
/*     */ 
/*     */     
/* 272 */     int srcLen = cs.length() - thisStart;
/* 273 */     int otherLen = substring.length() - start;
/*     */ 
/*     */     
/* 276 */     if (thisStart < 0 || start < 0 || length < 0) {
/* 277 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 281 */     if (srcLen < length || otherLen < length) {
/* 282 */       return false;
/*     */     }
/*     */     
/* 285 */     while (tmpLen-- > 0) {
/* 286 */       char c1 = cs.charAt(index1++);
/* 287 */       char c2 = substring.charAt(index2++);
/*     */       
/* 289 */       if (c1 == c2) {
/*     */         continue;
/*     */       }
/*     */       
/* 293 */       if (!ignoreCase) {
/* 294 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 298 */       if (Character.toUpperCase(c1) != Character.toUpperCase(c2) && 
/* 299 */         Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
/* 300 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 304 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/CharSequenceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */