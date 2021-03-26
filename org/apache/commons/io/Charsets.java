/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Collections;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Charsets
/*     */ {
/*     */   public static SortedMap<String, Charset> requiredCharsets() {
/*  76 */     TreeMap<String, Charset> m = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
/*  77 */     m.put(StandardCharsets.ISO_8859_1.name(), StandardCharsets.ISO_8859_1);
/*  78 */     m.put(StandardCharsets.US_ASCII.name(), StandardCharsets.US_ASCII);
/*  79 */     m.put(StandardCharsets.UTF_16.name(), StandardCharsets.UTF_16);
/*  80 */     m.put(StandardCharsets.UTF_16BE.name(), StandardCharsets.UTF_16BE);
/*  81 */     m.put(StandardCharsets.UTF_16LE.name(), StandardCharsets.UTF_16LE);
/*  82 */     m.put(StandardCharsets.UTF_8.name(), StandardCharsets.UTF_8);
/*  83 */     return Collections.unmodifiableSortedMap(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Charset toCharset(Charset charset) {
/*  94 */     return (charset == null) ? Charset.defaultCharset() : charset;
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
/*     */   public static Charset toCharset(String charset) {
/* 107 */     return (charset == null) ? Charset.defaultCharset() : Charset.forName(charset);
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
/*     */   @Deprecated
/* 120 */   public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 134 */   public static final Charset US_ASCII = StandardCharsets.US_ASCII;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 149 */   public static final Charset UTF_16 = StandardCharsets.UTF_16;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 163 */   public static final Charset UTF_16BE = StandardCharsets.UTF_16BE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 177 */   public static final Charset UTF_16LE = StandardCharsets.UTF_16LE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 191 */   public static final Charset UTF_8 = StandardCharsets.UTF_8;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/Charsets.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */