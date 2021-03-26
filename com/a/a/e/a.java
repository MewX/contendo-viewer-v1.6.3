/*     */ package com.a.a.e;
/*     */ 
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.InvalidMarkException;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public class a
/*     */   extends c
/*     */   implements RandomAccess {
/*     */   private byte[] d;
/*     */   
/*     */   public static b a(byte[] b) {
/*  17 */     return new a(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b a(int size) {
/*  25 */     return a(new byte[size]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(byte[] b) {
/*  32 */     this.d = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(byte[] b, ByteOrder bo) {
/*  40 */     super(bo);
/*  41 */     this.d = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/*  49 */     return this.d.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int pos, byte[] buf, int off, int len) throws IndexOutOfBoundsException {
/*  58 */     synchronized (this.d) {
/*  59 */       if (!b(pos, pos + len)) throw new IndexOutOfBoundsException(); 
/*  60 */       int abspos = pos + o();
/*  61 */       System.arraycopy(this.d, abspos, buf, off, len);
/*     */     } 
/*  63 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte b(int position) {
/*  71 */     synchronized (this.d) {
/*  72 */       return this.d[position + o()];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char c(int position) throws IndexOutOfBoundsException {
/*  81 */     synchronized (this.d) {
/*  82 */       return this.b.a(this.d, position + o());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short d(int position) throws IndexOutOfBoundsException {
/*  91 */     synchronized (this.d) {
/*  92 */       return this.b.b(this.d, position + o());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int e(int position) throws IndexOutOfBoundsException {
/* 101 */     synchronized (this.d) {
/* 102 */       return this.b.c(this.d, position + o());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long f(int position) throws IndexOutOfBoundsException {
/* 111 */     synchronized (this.d) {
/* 112 */       return this.b.d(this.d, position + o());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(byte[] buf, int offset, int length) throws BufferUnderflowException {
/* 122 */     int pos = k(length) + o();
/* 123 */     synchronized (this.d) {
/* 124 */       System.arraycopy(this.d, pos, buf, offset, length);
/*     */     } 
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(char[] buf, int offset, int length) throws BufferUnderflowException {
/* 135 */     int size = length * 2;
/* 136 */     int pos = k(size) + o();
/*     */     
/* 138 */     synchronized (this.d) {
/* 139 */       for (int i = 0; i < length; i++) {
/* 140 */         buf[i] = this.b.a(this.d, pos);
/* 141 */         pos += 2;
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(short[] buf, int offset, int length) throws BufferUnderflowException {
/* 154 */     int size = length * 2;
/* 155 */     int pos = k(size) + o();
/*     */     
/* 157 */     synchronized (this.d) {
/* 158 */       for (int i = 0; i < length; i++) {
/* 159 */         buf[i] = this.b.b(this.d, pos);
/* 160 */         pos += 2;
/*     */       } 
/*     */     } 
/*     */     
/* 164 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int[] buf, int offset, int length) throws BufferUnderflowException {
/* 173 */     int size = length * 4;
/* 174 */     int pos = k(size) + o();
/*     */     
/* 176 */     synchronized (this.d) {
/* 177 */       for (int i = 0; i < length; i++) {
/* 178 */         buf[i] = this.b.c(this.d, pos);
/* 179 */         pos += 4;
/*     */       } 
/*     */     } 
/*     */     
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int position, byte b) {
/* 190 */     synchronized (this.d) {
/* 191 */       if (!m(position)) throw new IndexOutOfBoundsException(); 
/* 192 */       this.d[position + o()] = b;
/*     */     } 
/* 194 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 202 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */