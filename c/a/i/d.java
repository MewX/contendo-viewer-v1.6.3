/*     */ package c.a.i;
/*     */ 
/*     */ import c.a.f.f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   implements f
/*     */ {
/*     */   private InputStream a;
/*     */   private int b;
/*     */   private int c;
/*     */   private byte[] d;
/*     */   private int e;
/*     */   private int f;
/*     */   private boolean g;
/*     */   
/*     */   public d(InputStream is, int size, int inc, int maxsize) {
/* 128 */     if (size < 0 || inc <= 0 || maxsize <= 0 || is == null) {
/* 129 */       throw new IllegalArgumentException();
/*     */     }
/* 131 */     this.a = is;
/*     */     
/* 133 */     if (size < Integer.MAX_VALUE) size++; 
/* 134 */     this.d = new byte[size];
/* 135 */     this.c = inc;
/*     */     
/* 137 */     if (maxsize < Integer.MAX_VALUE) maxsize++; 
/* 138 */     this.b = maxsize;
/* 139 */     this.f = 0;
/* 140 */     this.e = 0;
/* 141 */     this.g = false;
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
/*     */   public d(InputStream is) {
/* 153 */     this(is, 262144, 262144, 2147483647);
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
/*     */   private void a() throws IOException {
/*     */     byte[] newbuf;
/* 168 */     int effinc = this.c;
/* 169 */     if (this.d.length + effinc > this.b) effinc = this.b - this.d.length; 
/* 170 */     if (effinc <= 0) {
/* 171 */       throw new IOException("Reached maximum cache size (" + this.b + ")");
/*     */     }
/*     */     try {
/* 174 */       newbuf = new byte[this.d.length + this.c];
/* 175 */     } catch (OutOfMemoryError e) {
/* 176 */       throw new IOException("Out of memory to cache input data");
/*     */     } 
/* 178 */     System.arraycopy(this.d, 0, newbuf, 0, this.e);
/* 179 */     this.d = newbuf;
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
/*     */   private void b() throws IOException {
/*     */     int k;
/* 197 */     if (this.g) {
/* 198 */       throw new IllegalArgumentException("Already reached EOF");
/*     */     }
/*     */ 
/*     */     
/* 202 */     int n = 0;
/* 203 */     if (n == 0) n = 1; 
/* 204 */     while (this.e + n > this.d.length) {
/* 205 */       a();
/*     */     }
/*     */     
/*     */     do {
/* 209 */       k = this.a.read(this.d, this.e, n);
/* 210 */       if (k <= 0)
/* 211 */         continue;  this.e += k;
/* 212 */       n -= k;
/*     */     }
/* 214 */     while (n > 0 && k > 0);
/* 215 */     if (k <= 0) {
/* 216 */       this.g = true;
/* 217 */       this.a = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 228 */     this.d = null;
/* 229 */     if (!this.g) {
/* 230 */       this.a.close();
/* 231 */       this.a = null;
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
/*     */   public int getPos() throws IOException {
/* 243 */     return this.f;
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
/*     */   public void seek(int off) throws IOException {
/* 263 */     if (this.g && 
/* 264 */       off > this.e) {
/* 265 */       throw new EOFException();
/*     */     }
/*     */     
/* 268 */     this.f = off;
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
/*     */   public int length() throws IOException {
/* 281 */     if (Integer.MAX_VALUE != this.b)
/* 282 */       return this.b - 1; 
/* 283 */     while (!this.g) {
/* 284 */       b();
/*     */     }
/* 286 */     return this.e;
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
/*     */   public int read() throws IOException {
/* 300 */     if (this.f < this.e) {
/* 301 */       return 0xFF & this.d[this.f++];
/*     */     }
/*     */     
/* 304 */     while (!this.g && this.f >= this.e) {
/* 305 */       b();
/*     */     }
/* 307 */     if (this.f == this.e)
/* 308 */       throw new EOFException(); 
/* 309 */     if (this.f > this.e) {
/* 310 */       throw new IOException("Position beyond EOF");
/*     */     }
/* 312 */     return 0xFF & this.d[this.f++];
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
/*     */   public void readFully(byte[] b, int off, int n) throws IOException {
/* 335 */     if (this.f + n <= this.e) {
/* 336 */       System.arraycopy(this.d, this.f, b, off, n);
/* 337 */       this.f += n;
/*     */       
/*     */       return;
/*     */     } 
/* 341 */     while (!this.g && this.f + n > this.e) {
/* 342 */       b();
/*     */     }
/* 344 */     if (this.f + n > this.e) {
/* 345 */       throw new EOFException();
/*     */     }
/* 347 */     System.arraycopy(this.d, this.f, b, off, n);
/* 348 */     this.f += n;
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
/*     */   public int getByteOrdering() {
/* 362 */     return 0;
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
/*     */   public byte readByte() throws IOException {
/* 377 */     if (this.f < this.e) {
/* 378 */       return this.d[this.f++];
/*     */     }
/*     */     
/* 381 */     return (byte)read();
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
/*     */   public int readUnsignedByte() throws IOException {
/* 396 */     if (this.f < this.e) {
/* 397 */       return 0xFF & this.d[this.f++];
/*     */     }
/*     */     
/* 400 */     return read();
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
/*     */   public short readShort() throws IOException {
/* 415 */     if (this.f + 1 < this.e) {
/* 416 */       return (short)(this.d[this.f++] << 8 | 0xFF & this.d[this.f++]);
/*     */     }
/*     */     
/* 419 */     return (short)(read() << 8 | read());
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
/*     */   public int readUnsignedShort() throws IOException {
/* 434 */     if (this.f + 1 < this.e) {
/* 435 */       return (0xFF & this.d[this.f++]) << 8 | 0xFF & this.d[this.f++];
/*     */     }
/*     */     
/* 438 */     return read() << 8 | read();
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
/*     */   public int readInt() throws IOException {
/* 453 */     if (this.f + 3 < this.e) {
/* 454 */       return this.d[this.f++] << 24 | (0xFF & this.d[this.f++]) << 16 | (0xFF & this.d[this.f++]) << 8 | 0xFF & this.d[this.f++];
/*     */     }
/*     */ 
/*     */     
/* 458 */     return read() << 24 | read() << 16 | read() << 8 | read();
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
/*     */   public long readUnsignedInt() throws IOException {
/* 473 */     if (this.f + 3 < this.e) {
/* 474 */       return 0xFFFFFFFFL & (this.d[this.f++] << 24 | (0xFF & this.d[this.f++]) << 16 | (0xFF & this.d[this.f++]) << 8 | 0xFF & this.d[this.f++]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 480 */     return 0xFFFFFFFFL & (read() << 24 | read() << 16 | read() << 8 | read());
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
/*     */   public long readLong() throws IOException {
/* 495 */     if (this.f + 7 < this.e) {
/* 496 */       return this.d[this.f++] << 56L | (0xFF & this.d[this.f++]) << 48L | (0xFF & this.d[this.f++]) << 40L | (0xFF & this.d[this.f++]) << 32L | (0xFF & this.d[this.f++]) << 24L | (0xFF & this.d[this.f++]) << 16L | (0xFF & this.d[this.f++]) << 8L | (0xFF & this.d[this.f++]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 513 */     return read() << 56L | read() << 48L | read() << 40L | read() << 32L | read() << 24L | read() << 16L | read() << 8L | read();
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
/*     */   public float readFloat() throws IOException {
/* 528 */     if (this.f + 3 < this.e) {
/* 529 */       return Float.intBitsToFloat(this.d[this.f++] << 24 | (0xFF & this.d[this.f++]) << 16 | (0xFF & this.d[this.f++]) << 8 | 0xFF & this.d[this.f++]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 535 */     return Float.intBitsToFloat(read() << 24 | read() << 16 | 
/* 536 */         read() << 8 | read());
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
/*     */   public double readDouble() throws IOException {
/* 551 */     if (this.f + 7 < this.e) {
/* 552 */       return Double.longBitsToDouble(this.d[this.f++] << 56L | (0xFF & this.d[this.f++]) << 48L | (0xFF & this.d[this.f++]) << 40L | (0xFF & this.d[this.f++]) << 32L | (0xFF & this.d[this.f++]) << 24L | (0xFF & this.d[this.f++]) << 16L | (0xFF & this.d[this.f++]) << 8L | (0xFF & this.d[this.f++]));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 562 */     return Double.longBitsToDouble(read() << 56L | 
/* 563 */         read() << 48L | 
/* 564 */         read() << 40L | 
/* 565 */         read() << 32L | 
/* 566 */         read() << 24L | 
/* 567 */         read() << 16L | 
/* 568 */         read() << 8L | 
/* 569 */         read());
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
/*     */   public int skipBytes(int n) throws IOException {
/* 585 */     if (this.g && 
/* 586 */       this.f + n > this.e) {
/* 587 */       throw new EOFException();
/*     */     }
/*     */     
/* 590 */     this.f += n;
/* 591 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 604 */     throw new IOException("read-only");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByte(int v) throws IOException {
/* 611 */     throw new IOException("read-only");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShort(int v) throws IOException {
/* 618 */     throw new IOException("read-only");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int v) throws IOException {
/* 625 */     throw new IOException("read-only");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLong(long v) throws IOException {
/* 632 */     throw new IOException("read-only");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeFloat(float v) throws IOException {
/* 639 */     throw new IOException("read-only");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDouble(double v) throws IOException {
/* 646 */     throw new IOException("read-only");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */