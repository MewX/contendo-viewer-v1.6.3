/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoundedReader
/*     */   extends Reader
/*     */ {
/*     */   private static final int INVALID = -1;
/*     */   private final Reader target;
/*  45 */   private int charsRead = 0;
/*     */   
/*  47 */   private int markedAt = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   private int readAheadLimit;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int maxCharsFromTargetReader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundedReader(Reader target, int maxCharsFromTargetReader) throws IOException {
/*  61 */     this.target = target;
/*  62 */     this.maxCharsFromTargetReader = maxCharsFromTargetReader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  72 */     this.target.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() throws IOException {
/*  83 */     this.charsRead = this.markedAt;
/*  84 */     this.target.reset();
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
/*     */   public void mark(int readAheadLimit) throws IOException {
/* 102 */     this.readAheadLimit = readAheadLimit - this.charsRead;
/*     */     
/* 104 */     this.markedAt = this.charsRead;
/*     */     
/* 106 */     this.target.mark(readAheadLimit);
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
/*     */   public int read() throws IOException {
/* 119 */     if (this.charsRead >= this.maxCharsFromTargetReader) {
/* 120 */       return -1;
/*     */     }
/*     */     
/* 123 */     if (this.markedAt >= 0 && this.charsRead - this.markedAt >= this.readAheadLimit) {
/* 124 */       return -1;
/*     */     }
/* 126 */     this.charsRead++;
/* 127 */     return this.target.read();
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
/*     */   public int read(char[] cbuf, int off, int len) throws IOException {
/* 143 */     for (int i = 0; i < len; i++) {
/* 144 */       int c = read();
/* 145 */       if (c == -1) {
/* 146 */         return (i == 0) ? -1 : i;
/*     */       }
/* 148 */       cbuf[off + i] = (char)c;
/*     */     } 
/* 150 */     return len;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/BoundedReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */