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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharSetUtils
/*     */ {
/*     */   public static String squeeze(String str, String... set) {
/*  64 */     if (StringUtils.isEmpty(str) || deepEmpty(set)) {
/*  65 */       return str;
/*     */     }
/*  67 */     CharSet chars = CharSet.getInstance(set);
/*  68 */     StringBuilder buffer = new StringBuilder(str.length());
/*  69 */     char[] chrs = str.toCharArray();
/*  70 */     int sz = chrs.length;
/*  71 */     char lastChar = chrs[0];
/*  72 */     char ch = ' ';
/*  73 */     Character inChars = null;
/*  74 */     Character notInChars = null;
/*  75 */     buffer.append(lastChar);
/*  76 */     int i = 1; while (true) { if (i < sz)
/*  77 */       { ch = chrs[i];
/*  78 */         if (ch == lastChar)
/*  79 */         { if (inChars != null && ch == inChars.charValue()) {
/*     */             continue;
/*     */           }
/*  82 */           if (notInChars == null || ch != notInChars.charValue())
/*  83 */           { if (chars.contains(ch))
/*  84 */             { inChars = Character.valueOf(ch); }
/*     */             else
/*     */             
/*  87 */             { notInChars = Character.valueOf(ch);
/*     */ 
/*     */               
/*  90 */               buffer.append(ch);
/*  91 */               lastChar = ch; }  continue; }  }  } else { break; }  buffer.append(ch); lastChar = ch; i++; }
/*     */     
/*  93 */     return buffer.toString();
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
/*     */   public static boolean containsAny(String str, String... set) {
/* 118 */     if (StringUtils.isEmpty(str) || deepEmpty(set)) {
/* 119 */       return false;
/*     */     }
/* 121 */     CharSet chars = CharSet.getInstance(set);
/* 122 */     for (char c : str.toCharArray()) {
/* 123 */       if (chars.contains(c)) {
/* 124 */         return true;
/*     */       }
/*     */     } 
/* 127 */     return false;
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
/*     */   public static int count(String str, String... set) {
/* 151 */     if (StringUtils.isEmpty(str) || deepEmpty(set)) {
/* 152 */       return 0;
/*     */     }
/* 154 */     CharSet chars = CharSet.getInstance(set);
/* 155 */     int count = 0;
/* 156 */     for (char c : str.toCharArray()) {
/* 157 */       if (chars.contains(c)) {
/* 158 */         count++;
/*     */       }
/*     */     } 
/* 161 */     return count;
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
/*     */   public static String keep(String str, String... set) {
/* 186 */     if (str == null) {
/* 187 */       return null;
/*     */     }
/* 189 */     if (str.isEmpty() || deepEmpty(set)) {
/* 190 */       return "";
/*     */     }
/* 192 */     return modify(str, set, true);
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
/*     */   public static String delete(String str, String... set) {
/* 216 */     if (StringUtils.isEmpty(str) || deepEmpty(set)) {
/* 217 */       return str;
/*     */     }
/* 219 */     return modify(str, set, false);
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
/*     */   private static String modify(String str, String[] set, boolean expect) {
/* 232 */     CharSet chars = CharSet.getInstance(set);
/* 233 */     StringBuilder buffer = new StringBuilder(str.length());
/* 234 */     char[] chrs = str.toCharArray();
/* 235 */     for (char chr : chrs) {
/* 236 */       if (chars.contains(chr) == expect) {
/* 237 */         buffer.append(chr);
/*     */       }
/*     */     } 
/* 240 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean deepEmpty(String[] strings) {
/* 251 */     if (strings != null) {
/* 252 */       for (String s : strings) {
/* 253 */         if (StringUtils.isNotEmpty(s)) {
/* 254 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/* 258 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/CharSetUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */