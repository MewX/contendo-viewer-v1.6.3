/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.EOFException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NullReader
/*     */   extends Reader
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
/*     */   public NullReader(long size) {
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
/*     */   public NullReader(long size, boolean markSupported, boolean throwEofException) {
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
/*     */   
/*     */   public void close() throws IOException {
/* 128 */     this.eof = false;
/* 129 */     this.position = 0L;
/* 130 */     this.mark = -1L;
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
/* 142 */     if (!this.markSupported) {
/* 143 */       throw new UnsupportedOperationException("Mark not supported");
/*     */     }
/* 145 */     this.mark = this.position;
/* 146 */     this.readlimit = readlimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 156 */     return this.markSupported;
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
/* 171 */     if (this.eof) {
/* 172 */       throw new IOException("Read after end of file");
/*     */     }
/* 174 */     if (this.position == this.size) {
/* 175 */       return doEndOfFile();
/*     */     }
/* 177 */     this.position++;
/* 178 */     return processChar();
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
/*     */   public int read(char[] chars) throws IOException {
/* 194 */     return read(chars, 0, chars.length);
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
/*     */   public int read(char[] chars, int offset, int length) throws IOException {
/* 212 */     if (this.eof) {
/* 213 */       throw new IOException("Read after end of file");
/*     */     }
/* 215 */     if (this.position == this.size) {
/* 216 */       return doEndOfFile();
/*     */     }
/* 218 */     this.position += length;
/* 219 */     int returnLength = length;
/* 220 */     if (this.position > this.size) {
/* 221 */       returnLength = length - (int)(this.position - this.size);
/* 222 */       this.position = this.size;
/*     */     } 
/* 224 */     processChars(chars, offset, returnLength);
/* 225 */     return returnLength;
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
/* 238 */     if (!this.markSupported) {
/* 239 */       throw new UnsupportedOperationException("Mark not supported");
/*     */     }
/* 241 */     if (this.mark < 0L) {
/* 242 */       throw new IOException("No position has been marked");
/*     */     }
/* 244 */     if (this.position > this.mark + this.readlimit) {
/* 245 */       throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readlimit + "]");
/*     */     }
/*     */ 
/*     */     
/* 249 */     this.position = this.mark;
/* 250 */     this.eof = false;
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
/*     */   public long skip(long numberOfChars) throws IOException {
/* 266 */     if (this.eof) {
/* 267 */       throw new IOException("Skip after end of file");
/*     */     }
/* 269 */     if (this.position == this.size) {
/* 270 */       return doEndOfFile();
/*     */     }
/* 272 */     this.position += numberOfChars;
/* 273 */     long returnLength = numberOfChars;
/* 274 */     if (this.position > this.size) {
/* 275 */       returnLength = numberOfChars - this.position - this.size;
/* 276 */       this.position = this.size;
/*     */     } 
/* 278 */     return returnLength;
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
/*     */   protected int processChar() {
/* 290 */     return 0;
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
/*     */   protected void processChars(char[] chars, int offset, int length) {}
/*     */ 
/*     */ 
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
/* 316 */     this.eof = true;
/* 317 */     if (this.throwEofException) {
/* 318 */       throw new EOFException();
/*     */     }
/* 320 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/NullReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */