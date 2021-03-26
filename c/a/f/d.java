/*     */ package c.a.f;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class d
/*     */   implements e, f
/*     */ {
/*     */   private String j;
/*     */   private boolean k = true;
/*     */   private RandomAccessFile l;
/*     */   protected byte[] a;
/*     */   protected boolean b;
/*     */   protected int c;
/*     */   protected int d;
/*     */   protected int e;
/*     */   protected boolean f;
/*     */   protected int g;
/*     */   
/*     */   protected d(File file, String mode, int bufferSize) throws IOException {
/* 145 */     this.j = file.getName();
/* 146 */     if (mode.equals("rw") || mode.equals("rw+")) {
/* 147 */       this.k = false;
/* 148 */       if (mode.equals("rw") && 
/* 149 */         file.exists()) {
/* 150 */         file.delete();
/*     */       }
/* 152 */       mode = "rw";
/*     */     } 
/* 154 */     this.l = new RandomAccessFile(file, mode);
/* 155 */     this.a = new byte[bufferSize];
/* 156 */     a(0);
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
/*     */   protected d(File file, String mode) throws IOException {
/* 175 */     this(file, mode, 512);
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
/*     */   protected d(String name, String mode, int bufferSize) throws IOException {
/* 195 */     this(new File(name), mode, bufferSize);
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
/*     */   protected d(String name, String mode) throws IOException {
/* 214 */     this(name, mode, 512);
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
/*     */   protected final void a(int off) throws IOException {
/* 231 */     if (this.b) {
/* 232 */       flush();
/*     */     }
/*     */     
/* 235 */     if (this.k && off >= this.l.length()) {
/* 236 */       throw new EOFException();
/*     */     }
/*     */     
/* 239 */     this.c = off;
/*     */     
/* 241 */     this.l.seek(this.c);
/*     */     
/* 243 */     this.e = this.l.read(this.a, 0, this.a.length);
/* 244 */     this.d = 0;
/*     */     
/* 246 */     if (this.e < this.a.length) {
/* 247 */       this.f = true;
/* 248 */       if (this.e == -1) {
/* 249 */         this.e++;
/*     */       }
/*     */     } else {
/* 252 */       this.f = false;
/*     */     } 
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
/* 265 */     flush();
/* 266 */     this.a = null;
/* 267 */     this.l.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPos() {
/* 274 */     return this.c + this.d;
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
/*     */   public int length() throws IOException {
/* 288 */     int len = (int)this.l.length();
/*     */ 
/*     */ 
/*     */     
/* 292 */     if (this.c + this.e <= len) {
/* 293 */       return len;
/*     */     }
/*     */     
/* 296 */     return this.c + this.e;
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
/*     */   public void seek(int off) throws IOException {
/* 314 */     if (off >= this.c && off < this.c + this.a.length) {
/* 315 */       if (this.k && this.f && off > this.c + this.e)
/*     */       {
/* 317 */         throw new EOFException();
/*     */       }
/* 319 */       this.d = off - this.c;
/*     */     } else {
/*     */       
/* 322 */       a(off);
/*     */     } 
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
/*     */   public final int read() throws IOException, EOFException {
/* 337 */     if (this.d < this.e)
/*     */     {
/* 339 */       return this.a[this.d++] & 0xFF;
/*     */     }
/* 341 */     if (this.f) {
/* 342 */       this.d = this.e + 1;
/* 343 */       throw new EOFException();
/*     */     } 
/*     */     
/* 346 */     a(this.c + this.d);
/* 347 */     return read();
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
/*     */   public final void readFully(byte[] b, int off, int len) throws IOException {
/* 372 */     while (len > 0) {
/*     */       
/* 374 */       if (this.d < this.e) {
/* 375 */         int clen = this.e - this.d;
/* 376 */         if (clen > len) clen = len; 
/* 377 */         System.arraycopy(this.a, this.d, b, off, clen);
/* 378 */         this.d += clen;
/* 379 */         off += clen;
/* 380 */         len -= clen; continue;
/*     */       } 
/* 382 */       if (this.f) {
/* 383 */         this.d = this.e + 1;
/* 384 */         throw new EOFException();
/*     */       } 
/*     */       
/* 387 */       a(this.c + this.d);
/*     */     } 
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
/*     */   public final void write(int b) throws IOException {
/* 405 */     if (this.d < this.a.length) {
/* 406 */       if (this.k)
/* 407 */         throw new IOException("File is read only"); 
/* 408 */       this.a[this.d] = (byte)b;
/* 409 */       if (this.d >= this.e) {
/* 410 */         this.e = this.d + 1;
/*     */       }
/* 412 */       this.d++;
/* 413 */       this.b = true;
/*     */     } else {
/*     */       
/* 416 */       a(this.c + this.d);
/* 417 */       write(b);
/*     */     } 
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
/*     */   public final void a(byte b) throws IOException {
/* 433 */     if (this.d < this.a.length) {
/* 434 */       if (this.k)
/* 435 */         throw new IOException("File is read only"); 
/* 436 */       this.a[this.d] = b;
/* 437 */       if (this.d >= this.e) {
/* 438 */         this.e = this.d + 1;
/*     */       }
/* 440 */       this.d++;
/* 441 */       this.b = true;
/*     */     } else {
/*     */       
/* 444 */       a(this.c + this.d);
/* 445 */       a(b);
/*     */     } 
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
/*     */   public final void a(byte[] b, int offset, int length) throws IOException {
/* 464 */     int stop = offset + length;
/* 465 */     if (stop > b.length)
/* 466 */       throw new ArrayIndexOutOfBoundsException(b.length); 
/* 467 */     for (int i = offset; i < stop; i++) {
/* 468 */       a(b[i]);
/*     */     }
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
/*     */   public final void writeByte(int v) throws IOException {
/* 488 */     write(v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void flush() throws IOException {
/* 499 */     if (this.b) {
/* 500 */       this.l.seek(this.c);
/* 501 */       this.l.write(this.a, 0, this.e);
/* 502 */       this.b = false;
/*     */     } 
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
/*     */   public final byte readByte() throws EOFException, IOException {
/* 519 */     if (this.d < this.e)
/*     */     {
/* 521 */       return this.a[this.d++];
/*     */     }
/* 523 */     if (this.f) {
/* 524 */       this.d = this.e + 1;
/* 525 */       throw new EOFException();
/*     */     } 
/*     */     
/* 528 */     a(this.c + this.d);
/* 529 */     return readByte();
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
/*     */   public final int readUnsignedByte() throws EOFException, IOException {
/* 548 */     return read();
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
/*     */   public int getByteOrdering() {
/* 563 */     return this.g;
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
/*     */   public int skipBytes(int n) throws EOFException, IOException {
/* 578 */     if (n < 0) {
/* 579 */       throw new IllegalArgumentException("Can not skip negative number of bytes");
/*     */     }
/* 581 */     if (n <= this.e - this.d) {
/* 582 */       this.d += n;
/* 583 */       return n;
/*     */     } 
/*     */     
/* 586 */     seek(this.c + this.d + n);
/* 587 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 595 */     return "BufferedRandomAccessFile: " + this.j + " (" + (this.k ? "read only" : "read/write") + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/f/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */