/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  64 */   protected static final int[] IDENTIFIER_START = new int[] { 0, 0, -2013265922, 134217726 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   protected static final int[] NAME = new int[] { 0, 67052544, -2013265922, 134217726 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   protected static final int[] HEXADECIMAL = new int[] { 0, 67043328, 126, 126 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   protected static final int[] STRING = new int[] { 512, -133, -1, Integer.MAX_VALUE };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   protected static final int[] URI = new int[] { 0, -902, -1, Integer.MAX_VALUE };
/*     */ 
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
/*  96 */     return ((c <= ' ' && (4294981120L >> c & 0x1L) != 0L) || c == '　' || c == '﻿');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSIdentifierStartCharacter(char c) {
/* 105 */     return (c >= '' || (IDENTIFIER_START[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSNameCharacter(char c) {
/* 112 */     return (c >= '' || (NAME[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSHexadecimalCharacter(char c) {
/* 119 */     return (c < '' && (HEXADECIMAL[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSStringCharacter(char c) {
/* 126 */     return (c >= '' || (STRING[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCSSURICharacter(char c) {
/* 133 */     return (c >= '' || (URI[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/ScannerUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */