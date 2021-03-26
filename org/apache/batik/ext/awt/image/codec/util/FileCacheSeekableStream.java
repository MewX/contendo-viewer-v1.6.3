/*     */ package org.apache.batik.ext.awt.image.codec.util;
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
/*     */ 
/*     */ public final class FileCacheSeekableStream
/*     */   extends SeekableStream
/*     */ {
/*     */   private InputStream stream;
/*     */   private File cacheFile;
/*     */   private RandomAccessFile cache;
/*  54 */   private int bufLen = 1024;
/*     */ 
/*     */   
/*  57 */   private byte[] buf = new byte[this.bufLen];
/*     */ 
/*     */   
/*  60 */   private long length = 0L;
/*     */ 
/*     */   
/*  63 */   private long pointer = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean foundEOF = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileCacheSeekableStream(InputStream stream) throws IOException {
/*  78 */     this.stream = stream;
/*  79 */     this.cacheFile = File.createTempFile("jai-FCSS-", ".tmp");
/*  80 */     this.cacheFile.deleteOnExit();
/*  81 */     this.cache = new RandomAccessFile(this.cacheFile, "rw");
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
/*  92 */     if (pos < this.length) {
/*  93 */       return pos;
/*     */     }
/*     */     
/*  96 */     if (this.foundEOF) {
/*  97 */       return this.length;
/*     */     }
/*     */     
/* 100 */     long len = pos - this.length;
/* 101 */     this.cache.seek(this.length);
/* 102 */     while (len > 0L) {
/*     */ 
/*     */       
/* 105 */       int nbytes = this.stream.read(this.buf, 0, (int)Math.min(len, this.bufLen));
/* 106 */       if (nbytes == -1) {
/* 107 */         this.foundEOF = true;
/* 108 */         return this.length;
/*     */       } 
/*     */       
/* 111 */       this.cache.setLength(this.cache.length() + nbytes);
/* 112 */       this.cache.write(this.buf, 0, nbytes);
/* 113 */       len -= nbytes;
/* 114 */       this.length += nbytes;
/*     */     } 
/*     */     
/* 117 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeekBackwards() {
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFilePointer() {
/* 136 */     return this.pointer;
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
/* 150 */     if (pos < 0L) {
/* 151 */       throw new IOException(PropertyUtil.getString("FileCacheSeekableStream0"));
/*     */     }
/* 153 */     this.pointer = pos;
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
/* 169 */     long next = this.pointer + 1L;
/* 170 */     long pos = readUntil(next);
/* 171 */     if (pos >= next) {
/* 172 */       this.cache.seek(this.pointer++);
/* 173 */       return this.cache.read();
/*     */     } 
/* 175 */     return -1;
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
/* 228 */     if (b == null) {
/* 229 */       throw new NullPointerException();
/*     */     }
/* 231 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 232 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 234 */     if (len == 0) {
/* 235 */       return 0;
/*     */     }
/*     */     
/* 238 */     long pos = readUntil(this.pointer + len);
/*     */ 
/*     */     
/* 241 */     len = (int)Math.min(len, pos - this.pointer);
/* 242 */     if (len > 0) {
/* 243 */       this.cache.seek(this.pointer);
/* 244 */       this.cache.readFully(b, off, len);
/* 245 */       this.pointer += len;
/* 246 */       return len;
/*     */     } 
/* 248 */     return -1;
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
/* 259 */     super.close();
/* 260 */     this.cache.close();
/* 261 */     this.cacheFile.delete();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/FileCacheSeekableStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */