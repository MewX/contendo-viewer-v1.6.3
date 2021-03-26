/*     */ package org.apache.xmlgraphics.util.io;
/*     */ 
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubInputStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   private long bytesToRead;
/*     */   private boolean closeUnderlying;
/*     */   
/*     */   public SubInputStream(InputStream in, long maxLen, boolean closeUnderlying) {
/*  54 */     super(in);
/*  55 */     this.bytesToRead = maxLen;
/*  56 */     this.closeUnderlying = closeUnderlying;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubInputStream(InputStream in, long maxLen) {
/*  66 */     this(in, maxLen, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  71 */     if (this.bytesToRead > 0L) {
/*  72 */       int result = super.read();
/*  73 */       if (result >= 0) {
/*  74 */         this.bytesToRead--;
/*  75 */         return result;
/*     */       } 
/*  77 */       return -1;
/*     */     } 
/*     */     
/*  80 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  86 */     if (this.bytesToRead == 0L) {
/*  87 */       return -1;
/*     */     }
/*  89 */     int effRead = (int)Math.min(this.bytesToRead, len);
/*     */ 
/*     */     
/*  92 */     int result = super.read(b, off, effRead);
/*  93 */     if (result >= 0) {
/*  94 */       this.bytesToRead -= result;
/*     */     }
/*  96 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 101 */     long effRead = Math.min(this.bytesToRead, n);
/* 102 */     long result = super.skip(effRead);
/* 103 */     this.bytesToRead -= result;
/* 104 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 109 */     this.bytesToRead = 0L;
/* 110 */     if (this.closeUnderlying)
/* 111 */       super.close(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/SubInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */