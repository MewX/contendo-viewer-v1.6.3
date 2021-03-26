/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.EOFException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NullInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private final long size;
/*     */   private long position;
/*  69 */   private long mark = -1L;
/*     */ 
/*     */   
/*     */   private long readlimit;
/*     */   
/*     */   private boolean eof;
/*     */   
/*     */   private final boolean throwEofException;
/*     */   
/*     */   private final boolean markSupported;
/*     */ 
/*     */   
/*     */   public NullInputStream(long size) {
/*  82 */     this(size, true, false);
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
/*     */   public NullInputStream(long size, boolean markSupported, boolean throwEofException) {
/*  97 */     this.size = size;
/*  98 */     this.markSupported = markSupported;
/*  99 */     this.throwEofException = throwEofException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() {
/* 108 */     return this.position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSize() {
/* 117 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() {
/* 127 */     long avail = this.size - this.position;
/* 128 */     if (avail <= 0L)
/* 129 */       return 0; 
/* 130 */     if (avail > 2147483647L) {
/* 131 */       return Integer.MAX_VALUE;
/*     */     }
/* 133 */     return (int)avail;
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
/*     */   public void close() throws IOException {
/* 145 */     this.eof = false;
/* 146 */     this.position = 0L;
/* 147 */     this.mark = -1L;
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
/*     */   public synchronized void mark(int readlimit) {
/* 159 */     if (!this.markSupported) {
/* 160 */       throw new UnsupportedOperationException("Mark not supported");
/*     */     }
/* 162 */     this.mark = this.position;
/* 163 */     this.readlimit = readlimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 173 */     return this.markSupported;
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
/*     */   public int read() throws IOException {
/* 188 */     if (this.eof) {
/* 189 */       throw new IOException("Read after end of file");
/*     */     }
/* 191 */     if (this.position == this.size) {
/* 192 */       return doEndOfFile();
/*     */     }
/* 194 */     this.position++;
/* 195 */     return processByte();
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
/*     */   public int read(byte[] bytes) throws IOException {
/* 211 */     return read(bytes, 0, bytes.length);
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
/*     */   public int read(byte[] bytes, int offset, int length) throws IOException {
/* 229 */     if (this.eof) {
/* 230 */       throw new IOException("Read after end of file");
/*     */     }
/* 232 */     if (this.position == this.size) {
/* 233 */       return doEndOfFile();
/*     */     }
/* 235 */     this.position += length;
/* 236 */     int returnLength = length;
/* 237 */     if (this.position > this.size) {
/* 238 */       returnLength = length - (int)(this.position - this.size);
/* 239 */       this.position = this.size;
/*     */     } 
/* 241 */     processBytes(bytes, offset, returnLength);
/* 242 */     return returnLength;
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
/*     */   public synchronized void reset() throws IOException {
/* 255 */     if (!this.markSupported) {
/* 256 */       throw new UnsupportedOperationException("Mark not supported");
/*     */     }
/* 258 */     if (this.mark < 0L) {
/* 259 */       throw new IOException("No position has been marked");
/*     */     }
/* 261 */     if (this.position > this.mark + this.readlimit) {
/* 262 */       throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readlimit + "]");
/*     */     }
/*     */ 
/*     */     
/* 266 */     this.position = this.mark;
/* 267 */     this.eof = false;
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
/*     */   public long skip(long numberOfBytes) throws IOException {
/* 283 */     if (this.eof) {
/* 284 */       throw new IOException("Skip after end of file");
/*     */     }
/* 286 */     if (this.position == this.size) {
/* 287 */       return doEndOfFile();
/*     */     }
/* 289 */     this.position += numberOfBytes;
/* 290 */     long returnLength = numberOfBytes;
/* 291 */     if (this.position > this.size) {
/* 292 */       returnLength = numberOfBytes - this.position - this.size;
/* 293 */       this.position = this.size;
/*     */     } 
/* 295 */     return returnLength;
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
/*     */   protected int processByte() {
/* 307 */     return 0;
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
/*     */   protected void processBytes(byte[] bytes, int offset, int length) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int doEndOfFile() throws EOFException {
/* 333 */     this.eof = true;
/* 334 */     if (this.throwEofException) {
/* 335 */       throw new EOFException();
/*     */     }
/* 337 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/NullInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */