/*     */ package org.apache.xmlgraphics.image.codec.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FileCacheSeekableStream
/*     */   extends SeekableStream
/*     */ {
/*     */   private InputStream stream;
/*     */   private File cacheFile;
/*     */   private RandomAccessFile cache;
/*  53 */   private int bufLen = 1024;
/*     */ 
/*     */   
/*  56 */   private byte[] buf = new byte[this.bufLen];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long length;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long pointer;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean foundEOF;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileCacheSeekableStream(InputStream stream) throws IOException {
/*  77 */     this.stream = stream;
/*  78 */     this.cacheFile = File.createTempFile("jai-FCSS-", ".tmp");
/*  79 */     this.cacheFile.deleteOnExit();
/*  80 */     this.cache = new RandomAccessFile(this.cacheFile, "rw");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long readUntil(long pos) throws IOException {
/*  91 */     if (pos < this.length) {
/*  92 */       return pos;
/*     */     }
/*     */     
/*  95 */     if (this.foundEOF) {
/*  96 */       return this.length;
/*     */     }
/*     */     
/*  99 */     long len = pos - this.length;
/* 100 */     this.cache.seek(this.length);
/* 101 */     while (len > 0L) {
/*     */ 
/*     */       
/* 104 */       int nbytes = this.stream.read(this.buf, 0, (int)Math.min(len, this.bufLen));
/* 105 */       if (nbytes == -1) {
/* 106 */         this.foundEOF = true;
/* 107 */         return this.length;
/*     */       } 
/*     */       
/* 110 */       this.cache.setLength(this.cache.length() + nbytes);
/* 111 */       this.cache.write(this.buf, 0, nbytes);
/* 112 */       len -= nbytes;
/* 113 */       this.length += nbytes;
/*     */     } 
/*     */     
/* 116 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeekBackwards() {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFilePointer() {
/* 135 */     return this.pointer;
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
/*     */   public void seek(long pos) throws IOException {
/* 149 */     if (pos < 0L) {
/* 150 */       throw new IOException(PropertyUtil.getString("FileCacheSeekableStream0"));
/*     */     }
/* 152 */     this.pointer = pos;
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
/*     */   public int read() throws IOException {
/* 168 */     long next = this.pointer + 1L;
/* 169 */     long pos = readUntil(next);
/* 170 */     if (pos >= next) {
/* 171 */       this.cache.seek(this.pointer++);
/* 172 */       return this.cache.read();
/*     */     } 
/* 174 */     return -1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 227 */     if (b == null) {
/* 228 */       throw new NullPointerException();
/*     */     }
/* 230 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 231 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 233 */     if (len == 0) {
/* 234 */       return 0;
/*     */     }
/*     */     
/* 237 */     long pos = readUntil(this.pointer + len);
/*     */ 
/*     */     
/* 240 */     len = (int)Math.min(len, pos - this.pointer);
/* 241 */     if (len > 0) {
/* 242 */       this.cache.seek(this.pointer);
/* 243 */       this.cache.readFully(b, off, len);
/* 244 */       this.pointer += len;
/* 245 */       return len;
/*     */     } 
/* 247 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 258 */     super.close();
/* 259 */     this.cache.close();
/* 260 */     this.cacheFile.delete();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/FileCacheSeekableStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */