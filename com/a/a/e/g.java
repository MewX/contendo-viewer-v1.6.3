/*     */ package com.a.a.e;
/*     */ 
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.InvalidMarkException;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ 
/*     */ public class g
/*     */   extends b
/*     */ {
/*     */   ByteBuffer b;
/*     */   
/*     */   public g(ByteBuffer byteBuffer) {
/*  16 */     this.b = byteBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte b(int position) throws IndexOutOfBoundsException {
/*  24 */     return this.b.get(position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char c(int position) throws IndexOutOfBoundsException {
/*  32 */     return this.b.getChar(position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short d(int position) throws IndexOutOfBoundsException {
/*  40 */     return this.b.getShort(position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int e(int position) throws IndexOutOfBoundsException {
/*  48 */     return this.b.getInt(position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long f(int position) throws IndexOutOfBoundsException {
/*  56 */     return this.b.getLong(position);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte i() throws BufferUnderflowException {
/*  64 */     return this.b.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char c() throws BufferUnderflowException {
/*  72 */     return this.b.getChar();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short t() throws BufferUnderflowException {
/*  80 */     return this.b.getShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int n() throws BufferUnderflowException {
/*  88 */     return this.b.getInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long d() throws BufferUnderflowException {
/*  96 */     return this.b.getLong();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte[] buf) throws IndexOutOfBoundsException {
/* 105 */     return a(position, buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte[] buf, int offset, int length) throws IndexOutOfBoundsException {
/* 114 */     ByteBuffer tmp = this.b.duplicate();
/* 115 */     tmp.position(position);
/* 116 */     tmp.get(buf, offset, length);
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(byte[] buf) throws BufferUnderflowException {
/* 125 */     this.b.get(buf);
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(byte[] buf, int offset, int length) throws BufferUnderflowException {
/* 135 */     this.b.get(buf, offset, length);
/* 136 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte b1) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 145 */     this.b.put(position, b1);
/* 146 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, char c) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 155 */     this.b.putChar(position, c);
/* 156 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, short s) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 165 */     this.b.putShort(position, s);
/* 166 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, int i) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 175 */     this.b.putInt(position, i);
/* 176 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, long value) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 185 */     this.b.putLong(position, value);
/* 186 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(byte b1) throws BufferUnderflowException, ReadOnlyBufferException {
/* 195 */     this.b.put(b1);
/* 196 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(char c) throws BufferUnderflowException, ReadOnlyBufferException {
/* 205 */     this.b.putChar(c);
/* 206 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(short s) throws BufferUnderflowException, ReadOnlyBufferException {
/* 215 */     this.b.putShort(s);
/* 216 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int i) throws BufferUnderflowException, ReadOnlyBufferException {
/* 225 */     this.b.putInt(i);
/* 226 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(long value) throws BufferUnderflowException, ReadOnlyBufferException {
/* 235 */     this.b.putLong(value);
/* 236 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(int position, byte[] data) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 245 */     return b(position, data, 0, data.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(int position, byte[] data, int offset, int length) throws IndexOutOfBoundsException, ReadOnlyBufferException {
/* 254 */     ByteBuffer tmp = this.b.duplicate();
/* 255 */     tmp.position(position);
/* 256 */     tmp.put(data, offset, length);
/* 257 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(byte[] data) throws BufferUnderflowException, ReadOnlyBufferException {
/* 266 */     this.b.put(data);
/* 267 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(byte[] data, int offset, int length) throws BufferUnderflowException, ReadOnlyBufferException, IndexOutOfBoundsException {
/* 277 */     this.b.put(data, offset, length);
/* 278 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(e<?> data) throws ReadOnlyBufferException {
/* 286 */     f.a(this, data);
/* 287 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(e<?> data, int position, int length) throws ReadOnlyBufferException, IndexOutOfBoundsException {
/* 296 */     f.a(this, data, position, length);
/* 297 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] e() throws UnsupportedOperationException {
/* 305 */     return this.b.array();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int v() throws UnsupportedOperationException {
/* 313 */     return this.b.arrayOffset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean j() {
/* 321 */     return this.b.hasArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 329 */     return this.b.capacity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b r() {
/* 337 */     this.b.clear();
/* 338 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b m() {
/* 346 */     this.b.flip();
/* 347 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int g() {
/* 355 */     return this.b.remaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean q() {
/* 363 */     return this.b.hasRemaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int k() {
/* 371 */     return this.b.position();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b h(int position) throws IllegalArgumentException {
/* 379 */     this.b.position(position);
/* 380 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b x() {
/* 388 */     return new g(this.b.duplicate());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b u() {
/* 396 */     return new g(this.b.slice());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int f() throws IllegalArgumentException {
/* 404 */     return this.b.limit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b g(int length) {
/* 412 */     this.b.limit(length);
/* 413 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b h() {
/* 421 */     this.b.mark();
/* 422 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b p() throws InvalidMarkException {
/* 430 */     this.b.reset();
/* 431 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b s() {
/* 439 */     this.b.rewind();
/* 440 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer l() {
/* 448 */     return this.b.slice();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(ByteBuffer buf) {
/* 456 */     this.b.put(buf);
/* 457 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(ByteBuffer buf, int pos, int len) throws BufferOverflowException, IllegalArgumentException, ReadOnlyBufferException {
/* 467 */     ByteBuffer tmp = buf.duplicate();
/* 468 */     tmp.position(pos);
/* 469 */     tmp.limit(len);
/* 470 */     return a(tmp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOrder w() {
/* 478 */     return this.b.order();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(ByteOrder bo) {
/* 486 */     this.b.order(bo);
/* 487 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 495 */     return this.b.isReadOnly();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(char[] buf, int offset, int length) throws BufferUnderflowException {
/* 504 */     for (int i = 0; i < length; i++) {
/* 505 */       buf[offset++] = this.b.getChar();
/*     */     }
/* 507 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(short[] buf, int offset, int length) throws BufferUnderflowException {
/* 516 */     for (int i = 0; i < length; i++) {
/* 517 */       buf[offset++] = this.b.getShort();
/*     */     }
/* 519 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int[] buf, int offset, int length) throws BufferUnderflowException {
/* 529 */     for (int i = 0; i < length; i++) {
/* 530 */       buf[offset++] = this.b.getInt();
/*     */     }
/*     */     
/* 533 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */