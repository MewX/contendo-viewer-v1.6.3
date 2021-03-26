/*     */ package com.a.a.e;
/*     */ 
/*     */ import com.a.a.a.a;
/*     */ import com.a.a.a.b;
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.InvalidMarkException;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ 
/*     */ abstract class c
/*     */   extends b implements Cloneable {
/*     */   private int d;
/*     */   private int e;
/*     */   private int f;
/*     */   private int g;
/*  18 */   protected a b = null;
/*  19 */   protected b c = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c() {
/*  25 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(ByteOrder bo) {
/*  33 */     this.b = new a(bo);
/*  34 */     this.c = new b();
/*  35 */     this.d = 0;
/*  36 */     this.e = 0;
/*  37 */     this.f = -1;
/*  38 */     this.g = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOrder w() {
/*  45 */     return this.b.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(ByteOrder bo) {
/*  52 */     synchronized (this.b) {
/*  53 */       if (w() != bo) {
/*  54 */         this.b = new a(bo);
/*     */       }
/*     */     } 
/*  57 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int k(int len) {
/*  67 */     int pos = k();
/*  68 */     int next = pos + len;
/*  69 */     if (!b(pos, next)) throw new BufferUnderflowException(); 
/*  70 */     h(next);
/*  71 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int l(int len) {
/*  80 */     int pos = k();
/*  81 */     if (!b(pos, pos + len)) throw new BufferUnderflowException(); 
/*  82 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m(int pos) {
/*  90 */     if (pos < 0 || pos >= f()) return false; 
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(int pos1, int pos2) {
/* 100 */     if (pos1 < 0 || pos2 > f()) return false; 
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char c(int position) throws IndexOutOfBoundsException {
/* 109 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 111 */       a(position, arrayOfByte, 0, 2);
/* 112 */       return this.b.a(arrayOfByte, 0);
/*     */     } finally {
/* 114 */       this.c.a(arrayOfByte);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short d(int position) throws IndexOutOfBoundsException {
/* 123 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 125 */       a(position, arrayOfByte, 0, 2);
/* 126 */       return this.b.b(arrayOfByte, 0);
/*     */     } finally {
/* 128 */       this.c.a(arrayOfByte);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int e(int position) throws IndexOutOfBoundsException {
/* 137 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 139 */       a(position, arrayOfByte, 0, 4);
/* 140 */       return this.b.c(arrayOfByte, 0);
/*     */     } finally {
/* 142 */       this.c.a(arrayOfByte);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long f(int position) throws IndexOutOfBoundsException {
/* 151 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 153 */       a(position, arrayOfByte, 0, 8);
/* 154 */       return this.b.d(arrayOfByte, 0);
/*     */     } finally {
/* 156 */       this.c.a(arrayOfByte);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char c() throws BufferUnderflowException {
/* 165 */     return c(k(2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short t() throws BufferUnderflowException {
/* 173 */     return d(k(2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int n() throws BufferUnderflowException {
/* 181 */     return e(k(4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long d() throws BufferUnderflowException {
/* 189 */     return f(k(8));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte[] buf, int offset, int length) throws IndexOutOfBoundsException {
/* 197 */     if (!b(position, position + length)) throw new IndexOutOfBoundsException(); 
/* 198 */     for (int i = 0; i < length; i++) {
/* 199 */       buf[offset++] = b(position++);
/*     */     }
/* 201 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(byte[] buf, int offset, int length) throws BufferUnderflowException {
/* 209 */     return a(k(length), buf, offset, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(char[] buf, int offset, int length) throws BufferUnderflowException {
/* 217 */     int size = length * 2;
/* 218 */     int tmpsize = Math.min(512, size);
/* 219 */     byte[] tmp = new byte[tmpsize];
/* 220 */     while (size > 0) {
/* 221 */       int len = Math.min(size, tmpsize);
/* 222 */       a(tmp, 0, len);
/* 223 */       size -= len;
/* 224 */       for (int pos = 0; pos < len; pos += 2) {
/* 225 */         buf[offset++] = this.b.a(tmp, pos);
/*     */       }
/*     */     } 
/* 228 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(short[] buf, int offset, int length) throws BufferUnderflowException {
/* 236 */     int size = length * 2;
/* 237 */     int tmpsize = Math.min(512, size);
/* 238 */     byte[] tmp = new byte[tmpsize];
/* 239 */     while (size > 0) {
/* 240 */       int len = Math.min(size, tmpsize);
/* 241 */       a(tmp, 0, len);
/* 242 */       size -= len;
/* 243 */       for (int pos = 0; pos < len; pos += 2) {
/* 244 */         buf[offset++] = this.b.b(tmp, pos);
/*     */       }
/*     */     } 
/* 247 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int[] buf, int offset, int length) throws BufferUnderflowException {
/* 255 */     int size = length * 4;
/* 256 */     int tmpsize = Math.min(512, size);
/* 257 */     byte[] tmp = new byte[tmpsize];
/* 258 */     while (size > 0) {
/* 259 */       int len = Math.min(size, tmpsize);
/* 260 */       a(tmp, 0, len);
/* 261 */       size -= len;
/* 262 */       for (int pos = 0; pos < len; pos += 4) {
/* 263 */         buf[offset++] = this.b.c(tmp, pos);
/*     */       }
/*     */     } 
/* 266 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte b1) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 275 */     throw new ReadOnlyBufferException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, char c1) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 284 */     if (!b(position, position + 2))
/* 285 */       throw new IndexOutOfBoundsException(); 
/* 286 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 288 */       this.b.a(arrayOfByte, c1, 0);
/* 289 */       b(position, arrayOfByte, 0, 2);
/*     */     } finally {
/* 291 */       this.c.a(arrayOfByte);
/*     */     } 
/* 293 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, short s) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 302 */     if (!b(position, position + 2))
/* 303 */       throw new IndexOutOfBoundsException(); 
/* 304 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 306 */       this.b.a(arrayOfByte, s, 0);
/* 307 */       b(position, arrayOfByte, 0, 2);
/*     */     } finally {
/* 309 */       this.c.a(arrayOfByte);
/*     */     } 
/* 311 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, int i) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 320 */     if (!b(position, position + 4))
/* 321 */       throw new IndexOutOfBoundsException(); 
/* 322 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 324 */       this.b.a(arrayOfByte, i, 0);
/* 325 */       b(position, arrayOfByte, 0, 4);
/*     */     } finally {
/* 327 */       this.c.a(arrayOfByte);
/*     */     } 
/* 329 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, long i) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 338 */     if (!b(position, position + 8))
/* 339 */       throw new IndexOutOfBoundsException(); 
/* 340 */     byte[] arrayOfByte = this.c.a();
/*     */     try {
/* 342 */       this.b.a(arrayOfByte, i, 0);
/* 343 */       b(position, arrayOfByte, 0, 8);
/*     */     } finally {
/* 345 */       this.c.a(arrayOfByte);
/*     */     } 
/* 347 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(byte b1) throws BufferUnderflowException, ReadOnlyBufferException {
/* 356 */     return a(k(1), b1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(char c1) throws BufferUnderflowException, ReadOnlyBufferException {
/* 365 */     return a(k(2), c1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(short s) throws BufferUnderflowException, ReadOnlyBufferException {
/* 374 */     return a(k(2), s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b i(int i) throws BufferUnderflowException, ReadOnlyBufferException {
/* 383 */     return a(k(4), i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(long value) throws BufferUnderflowException, ReadOnlyBufferException {
/* 392 */     return a(k(8), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(int position, byte[] data) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 401 */     return b(position, data, 0, data.length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(int position, byte[] data, int offset, int length) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 407 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b c(byte[] data) throws BufferUnderflowException, ReadOnlyBufferException {
/* 416 */     if (data != null) {
/* 417 */       b(data, 0, data.length);
/*     */     }
/* 419 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(byte[] data, int offset, int length) throws BufferUnderflowException, ReadOnlyBufferException, IndexOutOfBoundsException {
/* 429 */     for (int i = 0; i < length; i++) {
/* 430 */       a(data[offset++]);
/*     */     }
/* 432 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(e<?> data) throws ReadOnlyBufferException {
/* 440 */     int remain = Integer.MAX_VALUE;
/* 441 */     if (data instanceof b) {
/* 442 */       remain = ((b)data).g();
/*     */     }
/*     */     try {
/* 445 */       for (int i = 0; i < remain; i++) {
/* 446 */         a(data.i());
/*     */       }
/* 448 */     } catch (BufferUnderflowException bufferUnderflowException) {}
/*     */     
/* 450 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(e<?> data, int position, int length) throws ReadOnlyBufferException, IndexOutOfBoundsException {
/* 459 */     for (int i = 0; i < length; i++) {
/* 460 */       a(data.b(position++));
/*     */     }
/* 462 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] e() throws UnsupportedOperationException {
/* 470 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int v() throws UnsupportedOperationException {
/* 478 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean j() {
/* 486 */     return false;
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
/*     */   public b r() {
/* 500 */     this.g = -1;
/* 501 */     this.f = -1;
/* 502 */     s();
/* 503 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b m() {
/* 511 */     g(k());
/* 512 */     h(0);
/* 513 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int g() {
/* 521 */     return f() - k();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean q() {
/* 529 */     return (g() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int k() {
/* 537 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b h(int position) throws IllegalArgumentException {
/* 545 */     if (f() < position) throw new IllegalArgumentException(); 
/* 546 */     this.e = position;
/* 547 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized b x() {
/*     */     try {
/* 556 */       return (b)clone();
/* 557 */     } catch (CloneNotSupportedException e) {
/* 558 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized b u() {
/* 567 */     c dup = (c)x();
/* 568 */     dup.d = o() + k();
/* 569 */     dup.s();
/* 570 */     dup.g(f() - k());
/* 571 */     dup.g = -1;
/* 572 */     return dup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte i() throws BufferUnderflowException {
/* 580 */     return b(k(1));
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
/*     */   public int f() throws IllegalArgumentException {
/* 594 */     if (this.f < 0) {
/* 595 */       this.f = a() - o();
/*     */     }
/* 597 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b g(int length) {
/* 605 */     if (length < 0 || o() + length > a()) {
/* 606 */       throw new IllegalArgumentException();
/*     */     }
/* 608 */     this.f = length;
/* 609 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int o() {
/* 617 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(byte[] buf) {
/* 625 */     return a(buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b h() {
/* 633 */     this.g = k();
/* 634 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b p() throws InvalidMarkException {
/* 642 */     if (this.g < 0) throw new InvalidMarkException(); 
/* 643 */     h(this.g);
/* 644 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b s() {
/* 652 */     h(0);
/* 653 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte[] buf) throws IndexOutOfBoundsException {
/* 662 */     return a(position, buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer l() {
/* 670 */     int size = f();
/* 671 */     ByteBuffer buf = ByteBuffer.allocate(size);
/*     */     
/* 673 */     u().a(buf);
/* 674 */     buf.flip();
/* 675 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(ByteBuffer buf) {
/* 683 */     int size = buf.remaining();
/* 684 */     int buflen = Math.min(512, size);
/* 685 */     byte[] arrayOfByte = new byte[buflen];
/*     */     
/* 687 */     while (size > 0) {
/* 688 */       int len = Math.min(size, buflen);
/* 689 */       a(arrayOfByte, 0, len);
/* 690 */       buf.put(arrayOfByte, 0, len);
/* 691 */       size -= len;
/*     */     } 
/*     */     
/* 694 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(ByteBuffer buf, int pos, int len) throws BufferOverflowException, IllegalArgumentException, ReadOnlyBufferException {
/* 704 */     ByteBuffer tmp = buf.duplicate();
/* 705 */     tmp.position(pos);
/* 706 */     tmp.limit(len);
/* 707 */     return a(tmp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 715 */     return true;
/*     */   }
/*     */   
/*     */   public abstract int a();
/*     */   
/*     */   public abstract byte b(int paramInt);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */