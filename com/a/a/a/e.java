/*     */ package com.a.a.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   extends d
/*     */ {
/*     */   public char a(byte[] b, int pos) {
/*  14 */     int n = b[pos] & 0xFF;
/*  15 */     n = n << 8 | b[pos + 1] & 0xFF;
/*  16 */     return (char)n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short b(byte[] b, int pos) {
/*  24 */     int n = b[pos] & 0xFF;
/*  25 */     n = n << 8 | b[pos + 1] & 0xFF;
/*  26 */     return (short)n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int c(byte[] b, int pos) {
/*  34 */     int n = b[pos] & 0xFF;
/*  35 */     n = n << 8 | b[pos + 1] & 0xFF;
/*  36 */     n = n << 8 | b[pos + 2] & 0xFF;
/*  37 */     n = n << 8 | b[pos + 3] & 0xFF;
/*  38 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long d(byte[] b, int pos) {
/*  46 */     long n = b[pos] & 0xFFL;
/*  47 */     n = n << 8L | b[pos + 1] & 0xFFL;
/*  48 */     n = n << 8L | b[pos + 2] & 0xFFL;
/*  49 */     n = n << 8L | b[pos + 3] & 0xFFL;
/*  50 */     n = n << 8L | b[pos + 4] & 0xFFL;
/*  51 */     n = n << 8L | b[pos + 5] & 0xFFL;
/*  52 */     n = n << 8L | b[pos + 6] & 0xFFL;
/*  53 */     n = n << 8L | b[pos + 7] & 0xFFL;
/*  54 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, int n, int pos) {
/*  62 */     b[pos + 3] = (byte)(n & 0xFF); n >>>= 8;
/*  63 */     b[pos + 2] = (byte)(n & 0xFF); n >>>= 8;
/*  64 */     b[pos + 1] = (byte)(n & 0xFF); n >>>= 8;
/*  65 */     b[pos] = (byte)(n & 0xFF);
/*  66 */     return pos + 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, short s, int pos) {
/*  74 */     int n = s;
/*  75 */     b[pos + 1] = (byte)(n & 0xFF); n >>>= 8;
/*  76 */     b[pos] = (byte)(n & 0xFF);
/*  77 */     return pos + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, char c, int pos) {
/*  85 */     int n = c;
/*  86 */     b[pos + 1] = (byte)(n & 0xFF); n >>>= 8;
/*  87 */     b[pos] = (byte)(n & 0xFF);
/*  88 */     return pos + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] b, long l, int pos) {
/*  96 */     b[pos + 7] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/*  97 */     b[pos + 6] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/*  98 */     b[pos + 5] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/*  99 */     b[pos + 4] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/* 100 */     b[pos + 3] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/* 101 */     b[pos + 2] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/* 102 */     b[pos + 1] = (byte)(int)(l & 0xFFL); l >>>= 8L;
/* 103 */     b[pos] = (byte)(int)(l & 0xFFL);
/* 104 */     return pos + 8;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/a/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */