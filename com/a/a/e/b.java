/*     */ package com.a.a.e;
/*     */ 
/*     */ import com.a.a.b.h;
/*     */ import com.a.a.c.a;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.BufferOverflowException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.InvalidMarkException;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ 
/*     */ public abstract class b
/*     */   implements d {
/*  17 */   protected static a a = com.a.a.c.b.a(b.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int o() {
/*  68 */     return 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b j(int size) {
/* 141 */     return a.a(size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b b(ByteBuffer byteBuffer) {
/* 149 */     return new g(byteBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b d(byte[] arrayOfByte) {
/* 157 */     return a.a(arrayOfByte);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b a(RandomAccessFile raf) throws IOException {
/* 166 */     return new i(raf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b a(h raf) throws IOException {
/* 175 */     return new i(raf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b e(byte[] arrayOfByte) {
/* 183 */     return a.a(arrayOfByte);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static b c(ByteBuffer byteBuffer) {
/* 191 */     return new g(byteBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(char[] buf) throws BufferUnderflowException {
/* 199 */     return b(buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(short[] buf) throws BufferUnderflowException {
/* 207 */     return b(buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int[] buf) throws BufferUnderflowException {
/* 215 */     return b(buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(OutputStream out) throws IOException {
/* 220 */     byte[] buf = new byte[512];
/*     */     
/* 222 */     int total = 0;
/* 223 */     int len = 0;
/* 224 */     while ((len = Math.max(g(), buf.length)) > 0) {
/* 225 */       c(buf, 0, len);
/* 226 */       out.write(buf, 0, len);
/* 227 */       total += len;
/*     */     } 
/* 229 */     return total;
/*     */   }
/*     */   
/*     */   public abstract byte[] e() throws UnsupportedOperationException;
/*     */   
/*     */   public abstract int v() throws UnsupportedOperationException;
/*     */   
/*     */   public abstract boolean j();
/*     */   
/*     */   public abstract int a();
/*     */   
/*     */   public abstract b r();
/*     */   
/*     */   public abstract b m();
/*     */   
/*     */   public abstract int g();
/*     */   
/*     */   public abstract boolean q();
/*     */   
/*     */   public abstract boolean b();
/*     */   
/*     */   public abstract int k();
/*     */   
/*     */   public abstract b h(int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   public abstract b x();
/*     */   
/*     */   public abstract b u();
/*     */   
/*     */   public abstract int f() throws IllegalArgumentException;
/*     */   
/*     */   public abstract b g(int paramInt);
/*     */   
/*     */   public abstract b h();
/*     */   
/*     */   public abstract b p() throws InvalidMarkException;
/*     */   
/*     */   public abstract b s();
/*     */   
/*     */   public abstract ByteBuffer l();
/*     */   
/*     */   public abstract b a(ByteBuffer paramByteBuffer) throws BufferOverflowException, IllegalArgumentException, ReadOnlyBufferException;
/*     */   
/*     */   public abstract ByteOrder w();
/*     */   
/*     */   public abstract b a(ByteOrder paramByteOrder);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */