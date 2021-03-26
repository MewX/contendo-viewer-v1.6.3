/*     */ package org.apache.batik.css.parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScannerUtilities
/*     */ {
/*  33 */   protected static final int[] IDENTIFIER_START = new int[] { 0, 0, -2013265922, 134217726 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   protected static final int[] NAME = new int[] { 0, 67051520, -2013265922, 134217726 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected static final int[] HEXADECIMAL = new int[] { 0, 67043328, 126, 126 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected static final int[] STRING = new int[] { 512, -133, -1, Integer.MAX_VALUE };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected static final int[] URI = new int[] { 0, -902, -1, Integer.MAX_VALUE };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSSpace(char c) {
/*  70 */     return (c <= ' ' && (4294981120L >> c & 0x1L) != 0L);
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
/*     */   public static boolean isCSSIdentifierStartCharacter(char c) {
/*  82 */     return (c >= '' || (IDENTIFIER_START[c >> 5] & 1 << (c & 0x1F)) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSNameCharacter(char c) {
/*  89 */     return (c >= '' || (NAME[c >> 5] & 1 << (c & 0x1F)) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSHexadecimalCharacter(char c) {
/*  96 */     return (c < '' && (HEXADECIMAL[c >> 5] & 1 << (c & 0x1F)) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSStringCharacter(char c) {
/* 103 */     return (c >= '' || (STRING[c >> 5] & 1 << (c & 0x1F)) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSURICharacter(char c) {
/* 110 */     return (c >= '' || (URI[c >> 5] & 1 << (c & 0x1F)) != 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/ScannerUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */