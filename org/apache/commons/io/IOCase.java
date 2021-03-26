/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum IOCase
/*     */   implements Serializable
/*     */ {
/*  41 */   SENSITIVE("Sensitive", true),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   INSENSITIVE("Insensitive", false),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   SYSTEM("System", !FilenameUtils.isSystemWindows());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long serialVersionUID = -6343169151696340687L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String name;
/*     */ 
/*     */ 
/*     */   
/*     */   private final transient boolean sensitive;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IOCase forName(String name) {
/*  80 */     for (IOCase ioCase : values()) {
/*     */       
/*  82 */       if (ioCase.getName().equals(name))
/*     */       {
/*  84 */         return ioCase;
/*     */       }
/*     */     } 
/*  87 */     throw new IllegalArgumentException("Invalid IOCase name: " + name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   IOCase(String name, boolean sensitive) {
/*  98 */     this.name = name;
/*  99 */     this.sensitive = sensitive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object readResolve() {
/* 109 */     return forName(this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 119 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCaseSensitive() {
/* 128 */     return this.sensitive;
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
/*     */   public int checkCompareTo(String str1, String str2) {
/* 144 */     if (str1 == null || str2 == null) {
/* 145 */       throw new NullPointerException("The strings must not be null");
/*     */     }
/* 147 */     return this.sensitive ? str1.compareTo(str2) : str1.compareToIgnoreCase(str2);
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
/*     */   public boolean checkEquals(String str1, String str2) {
/* 162 */     if (str1 == null || str2 == null) {
/* 163 */       throw new NullPointerException("The strings must not be null");
/*     */     }
/* 165 */     return this.sensitive ? str1.equals(str2) : str1.equalsIgnoreCase(str2);
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
/*     */   public boolean checkStartsWith(String str, String start) {
/* 180 */     return str.regionMatches(!this.sensitive, 0, start, 0, start.length());
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
/*     */   public boolean checkEndsWith(String str, String end) {
/* 195 */     int endLen = end.length();
/* 196 */     return str.regionMatches(!this.sensitive, str.length() - endLen, end, 0, endLen);
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
/*     */   public int checkIndexOf(String str, int strStartIndex, String search) {
/* 215 */     int endIndex = str.length() - search.length();
/* 216 */     if (endIndex >= strStartIndex) {
/* 217 */       for (int i = strStartIndex; i <= endIndex; i++) {
/* 218 */         if (checkRegionMatches(str, i, search)) {
/* 219 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/* 223 */     return -1;
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
/*     */   public boolean checkRegionMatches(String str, int strStartIndex, String search) {
/* 239 */     return str.regionMatches(!this.sensitive, strStartIndex, search, 0, search.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 250 */     return this.name;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/IOCase.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */