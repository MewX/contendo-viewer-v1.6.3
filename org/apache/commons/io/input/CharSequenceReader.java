/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.Reader;
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
/*     */ public class CharSequenceReader
/*     */   extends Reader
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3724187752191401220L;
/*     */   private final CharSequence charSequence;
/*     */   private int idx;
/*     */   private int mark;
/*     */   
/*     */   public CharSequenceReader(CharSequence charSequence) {
/*  45 */     this.charSequence = (charSequence != null) ? charSequence : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  53 */     this.idx = 0;
/*  54 */     this.mark = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mark(int readAheadLimit) {
/*  64 */     this.mark = this.idx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() {
/*  85 */     if (this.idx >= this.charSequence.length()) {
/*  86 */       return -1;
/*     */     }
/*  88 */     return this.charSequence.charAt(this.idx++);
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
/*     */   public int read(char[] array, int offset, int length) {
/* 103 */     if (this.idx >= this.charSequence.length()) {
/* 104 */       return -1;
/*     */     }
/* 106 */     if (array == null) {
/* 107 */       throw new NullPointerException("Character array is missing");
/*     */     }
/* 109 */     if (length < 0 || offset < 0 || offset + length > array.length) {
/* 110 */       throw new IndexOutOfBoundsException("Array Size=" + array.length + ", offset=" + offset + ", length=" + length);
/*     */     }
/*     */     
/* 113 */     int count = 0;
/* 114 */     for (int i = 0; i < length; i++) {
/* 115 */       int c = read();
/* 116 */       if (c == -1) {
/* 117 */         return count;
/*     */       }
/* 119 */       array[offset + i] = (char)c;
/* 120 */       count++;
/*     */     } 
/* 122 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 131 */     this.idx = this.mark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long n) {
/* 142 */     if (n < 0L) {
/* 143 */       throw new IllegalArgumentException("Number of characters to skip is less than zero: " + n);
/*     */     }
/*     */     
/* 146 */     if (this.idx >= this.charSequence.length()) {
/* 147 */       return -1L;
/*     */     }
/* 149 */     int dest = (int)Math.min(this.charSequence.length(), this.idx + n);
/* 150 */     int count = dest - this.idx;
/* 151 */     this.idx = dest;
/* 152 */     return count;
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
/* 163 */     return this.charSequence.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/CharSequenceReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */