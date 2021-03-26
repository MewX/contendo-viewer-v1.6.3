/*     */ package com.a.a.b.a;
/*     */ 
/*     */ import com.a.a.b.a;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */   implements a.b, e
/*     */ {
/*     */   protected final c a;
/*  14 */   a b = new a();
/*  15 */   int c = 0;
/*  16 */   int d = 0;
/*  17 */   private final LinkedList<byte[]> f = (LinkedList)new LinkedList<byte>();
/*  18 */   private int g = 0;
/*     */   
/*     */   private static final int h = 8192;
/*     */   
/*     */   private static final int i = 2;
/*  23 */   long e = 0L;
/*     */   
/*     */   public g(c encryptor) throws IOException {
/*  26 */     this.a = encryptor;
/*     */     
/*  28 */     this.b.a(this);
/*     */     
/*  30 */     if (this.a.a() != 0) {
/*  31 */       byte[] arrayOfByte = this.a.b();
/*  32 */       if (arrayOfByte != null) {
/*  33 */         this.b.a(arrayOfByte);
/*  34 */         this.d = arrayOfByte.length;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Object parent, byte[] array, int offset, int length) {
/*  44 */     synchronized (this.f) {
/*  45 */       this.g--;
/*  46 */       if (array.length == 8192 && this.f.size() < 2) {
/*  47 */         this.f.add(array);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] a(int len) {
/*  57 */     byte[] buf = null;
/*  58 */     synchronized (this.f) {
/*  59 */       if (len <= 8192) {
/*  60 */         if (this.f.isEmpty()) {
/*  61 */           if (this.g < 2) {
/*  62 */             buf = new byte[8192];
/*     */           } else {
/*  64 */             buf = new byte[len];
/*     */           } 
/*     */         } else {
/*  67 */           buf = this.f.getFirst();
/*     */         } 
/*     */       } else {
/*  70 */         buf = new byte[len];
/*     */       } 
/*  72 */       this.g++;
/*     */     } 
/*  74 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*     */     try {
/*  83 */       return this.b.available();
/*  84 */     } catch (IOException iOException) {
/*     */       
/*  86 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/*  94 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte[] arrayOfByte) {
/* 102 */     a(arrayOfByte, 0, arrayOfByte.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte[] arrayOfByte, int off, int len) {
/* 110 */     byte[] t = a(len);
/* 111 */     System.arraycopy(arrayOfByte, off, t, 0, len);
/* 112 */     this.b.a(t, 0, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(byte[] arrayOfByte, int off, int len) {
/* 120 */     int read = 0;
/*     */     
/*     */     try {
/* 123 */       read = this.b.read(arrayOfByte, off, len);
/*     */       
/* 125 */       int i = 0;
/* 126 */       if (this.d > 0) {
/* 127 */         int n = Math.min(read, this.d);
/* 128 */         i += n;
/* 129 */         off += n;
/* 130 */         this.d -= n;
/*     */       } 
/* 132 */       if (i < read) {
/* 133 */         this.a.b(arrayOfByte, (int)this.e, off, read);
/* 134 */         this.e += read;
/*     */       } 
/* 136 */     } catch (IOException iOException) {
/* 137 */       iOException.printStackTrace();
/*     */     } 
/*     */     
/* 140 */     return read;
/*     */   }
/*     */   
/*     */   public void c() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/a/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */