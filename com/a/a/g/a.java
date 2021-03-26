/*     */ package com.a.a.g;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*     */   public static final String a = "iso-2022-jp";
/*     */   public static final String b = "ASCII";
/*     */   public static final String c = "Windows-31J";
/*     */   public static final String d = "euc-jp";
/*     */   public static final String e = "utf-8";
/*     */   public static final String f = "utf-16";
/*     */   public static final String g = "utf-32";
/*  15 */   static final char[] h = new char[] {
/*  16 */       '\001', ''
/*     */     };
/*     */   
/*  19 */   static final char[] i = new char[] { '\004', '\033', '$', '(', 'D', '\003', '\033', '$', '@', '\003', '\033', '$', 'B', '\006', '\033', '&', '@', '\033', '$', 'B', '\003', '\033', '(', 'B', '\003', '\033', '(', 'I', '\003', '\033', '(', 'J' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   static final char[] j = new char[] { 
/*  30 */       '\001', '', 
/*  31 */       '\001', '¡', 'ß', 
/*  32 */       '\002', '', '', '@', '~', 
/*  33 */       '\002', '', '', '', 'ü', 
/*  34 */       '\002', 'à', 'ü', '@', '~', 
/*  35 */       '\002', 'à', 'ü', '', 'ü' };
/*     */ 
/*     */   
/*  38 */   static final char[] k = new char[] { 
/*  39 */       '\001', '', 
/*  40 */       '\002', '¡', 'þ', '¡', 'þ', 
/*  41 */       '\002', '', '', '¡', 'ß', 
/*  42 */       '\003', '', '', '¡', 'þ', '¡', 'þ' };
/*     */ 
/*     */   
/*  45 */   static final char[] l = new char[] { 
/*  46 */       '\001', '', 
/*  47 */       '\002', 'À', 'ß', '', '¿', 
/*  48 */       '\003', 'à', 'ï', '', '¿', '', '¿', 
/*  49 */       '\004', 'ð', '÷', '', '¿', '', '¿', '', '¿' };
/*     */ 
/*     */   
/*  52 */   static final char[] m = new char[] { '\003', 'ï', '»', '¿' };
/*     */ 
/*     */ 
/*     */   
/*  56 */   static final char[] n = new char[] { '\002', 'ÿ', 'þ', '\002', 'þ', 'ÿ' };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   static final char[] o = new char[] {
/*  62 */       '\004', 'þ', 'ÿ', 
/*  63 */       '\004', 'ÿ', 'þ'
/*     */     };
/*     */   
/*     */   static class a
/*     */   {
/*     */     String a;
/*     */     char[] b;
/*     */     char[] c;
/*     */     char[] d;
/*     */     boolean e;
/*     */     boolean f = false;
/*  74 */     int g = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     a(String charsets, char[] range, char[] literals, char[] specials) {
/*  83 */       this.a = charsets;
/*  84 */       this.b = range;
/*  85 */       this.c = literals;
/*  86 */       this.d = specials;
/*  87 */       this.e = (range != null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  94 */       return String.valueOf(this.a) + "=" + this.e;
/*     */     }
/*     */   }
/*     */   
/*     */   public static String a(byte[] b) throws UnsupportedEncodingException {
/*  99 */     return new String(b, b(b));
/*     */   }
/*     */   
/*     */   public static String a(byte[] b, int off, int len) throws UnsupportedEncodingException {
/* 103 */     return new String(b, off, len, b(b, off, len));
/*     */   }
/*     */   
/*     */   public static String b(byte[] b) {
/* 107 */     return b(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public static String b(byte[] b, int off, int len) {
/* 111 */     a[] work = {
/* 112 */         new a("utf-8", l, null, m), 
/* 113 */         new a("euc-jp", k, null, null), 
/* 114 */         new a("Windows-31J", j, null, null), 
/* 115 */         new a("iso-2022-jp", h, i, null), 
/* 116 */         new a("ASCII", h, null, null), 
/* 117 */         new a("utf-32", null, null, o), 
/* 118 */         new a("utf-16", null, null, n)
/*     */       };
/*     */     
/* 121 */     int save_off = off;
/* 122 */     int save_len = len;
/* 123 */     while (len > 0) {
/* 124 */       int cnt = 0; byte b2; int j; a[] arrayOfA;
/* 125 */       for (j = (arrayOfA = work).length, b2 = 0; b2 < j; ) { a f = arrayOfA[b2];
/* 126 */         if (f.e) {
/* 127 */           if (f.g == 0 && (f.g = a(b, off, len, f.c)) < 0) {
/* 128 */             f.e = false;
/* 129 */           } else if (f.g == 0 && (f.g = b(b, off, len, f.b)) < 0) {
/* 130 */             f.e = false;
/*     */           } 
/*     */ 
/*     */           
/* 134 */           if (f.g > 0) f.g--;
/*     */ 
/*     */           
/* 137 */           if (f.e) cnt++;
/*     */         
/*     */         } 
/*     */         b2++; }
/*     */       
/* 142 */       off++;
/* 143 */       len--;
/*     */     }  byte b1;
/*     */     int i;
/*     */     a[] arrayOfA1;
/* 147 */     for (i = (arrayOfA1 = work).length, b1 = 0; b1 < i; ) { a f = arrayOfA1[b1];
/* 148 */       if (f.d != null && (f.e || f.b == null) && 
/* 149 */         c(b, save_off, save_len, f.d)) {
/* 150 */         return f.a;
/*     */       }
/*     */       
/*     */       b1++; }
/*     */     
/* 155 */     for (i = (arrayOfA1 = work).length, b1 = 0; b1 < i; ) { a f = arrayOfA1[b1];
/* 156 */       if (f.e)
/* 157 */         return f.a; 
/*     */       b1++; }
/*     */     
/* 160 */     return "ISO-8859-1";
/*     */   }
/*     */   
/*     */   private static int a(byte[] b, int off, int len, char[] literals) {
/* 164 */     if (literals == null) return 0; 
/* 165 */     int pos = 0;
/* 166 */     boolean flg = false;
/* 167 */     while (pos < literals.length) {
/* 168 */       int l = literals[pos++];
/* 169 */       if (len >= l) {
/* 170 */         boolean same = true;
/* 171 */         for (int i = 0, p = off; i < l; i++, p++) {
/* 172 */           char c = (char)(b[p] & 0xFF);
/* 173 */           if (c != literals[pos + i]) {
/* 174 */             if (i > 0) flg = true; 
/* 175 */             same = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 179 */         if (same) {
/* 180 */           return l;
/*     */         }
/*     */       } 
/* 183 */       pos += l;
/*     */     } 
/* 185 */     if (flg) {
/* 186 */       return -1;
/*     */     }
/* 188 */     return 0;
/*     */   }
/*     */   
/*     */   private static int b(byte[] b, int off, int len, char[] ranges) {
/* 192 */     if (ranges == null) return 0; 
/* 193 */     int pos = 0;
/* 194 */     while (pos < ranges.length) {
/* 195 */       int l = ranges[pos++];
/* 196 */       if (len >= l) {
/* 197 */         boolean ok = true;
/* 198 */         for (int i = 0, p = off, n = pos; i < l; i++) {
/* 199 */           char s = ranges[n++];
/* 200 */           char e = ranges[n++];
/* 201 */           char c = (char)(b[p++] & 0xFF);
/* 202 */           if (c < s || c > e) {
/* 203 */             ok = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 207 */         if (ok) {
/* 208 */           return l;
/*     */         }
/*     */       } 
/* 211 */       pos += l * 2;
/*     */     } 
/* 213 */     return -1;
/*     */   }
/*     */   
/*     */   private static boolean c(byte[] b, int off, int len, char[] specials) {
/* 217 */     if (specials == null) return false; 
/* 218 */     int pos = 0;
/*     */     
/* 220 */     while (pos < specials.length) {
/* 221 */       int l = specials[pos++];
/* 222 */       if (len >= l) {
/* 223 */         boolean ok = true;
/* 224 */         for (int i = 0; i < l; i++) {
/* 225 */           char c = (char)(b[off + i] & 0xFF);
/* 226 */           if (c != specials[pos + i]) {
/* 227 */             ok = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 231 */         if (ok) {
/* 232 */           return true;
/*     */         }
/*     */       } 
/* 235 */       pos += l;
/*     */     } 
/*     */     
/* 238 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/g/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */