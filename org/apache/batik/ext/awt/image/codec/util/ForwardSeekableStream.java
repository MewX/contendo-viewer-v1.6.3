/*     */ package org.apache.batik.ext.awt.image.codec.util;
/*     */ 
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
/*     */ public class ForwardSeekableStream
/*     */   extends SeekableStream
/*     */ {
/*     */   private InputStream src;
/*  37 */   long pointer = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ForwardSeekableStream(InputStream src) {
/*  44 */     this.src = src;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int read() throws IOException {
/*  49 */     int result = this.src.read();
/*  50 */     if (result != -1) {
/*  51 */       this.pointer++;
/*     */     }
/*  53 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int read(byte[] b, int off, int len) throws IOException {
/*  58 */     int result = this.src.read(b, off, len);
/*  59 */     if (result != -1) {
/*  60 */       this.pointer += result;
/*     */     }
/*  62 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public final long skip(long n) throws IOException {
/*  67 */     long skipped = this.src.skip(n);
/*  68 */     this.pointer += skipped;
/*  69 */     return skipped;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int available() throws IOException {
/*  74 */     return this.src.available();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void close() throws IOException {
/*  79 */     this.src.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void mark(int readLimit) {
/*  87 */     this.markPos = this.pointer;
/*  88 */     this.src.mark(readLimit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final synchronized void reset() throws IOException {
/*  96 */     if (this.markPos != -1L) {
/*  97 */       this.pointer = this.markPos;
/*     */     }
/*  99 */     this.src.reset();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 104 */     return this.src.markSupported();
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canSeekBackwards() {
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final long getFilePointer() {
/* 114 */     return this.pointer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void seek(long pos) throws IOException {
/* 124 */     while (pos - this.pointer > 0L)
/* 125 */       this.pointer += this.src.skip(pos - this.pointer); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/ForwardSeekableStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */