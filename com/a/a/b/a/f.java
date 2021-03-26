/*     */ package com.a.a.b.a;
/*     */ 
/*     */ import com.a.a.b.a;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ 
/*     */ public class f
/*     */   implements a.b, d
/*     */ {
/*     */   protected final b a;
/*  12 */   a b = new a();
/*  13 */   private int d = 0;
/*  14 */   int c = 0;
/*     */   
/*     */   private boolean e = false;
/*     */   private boolean f = true;
/*  18 */   private int g = 0;
/*  19 */   private long h = 0L;
/*     */   
/*  21 */   private final LinkedList<byte[]> i = (LinkedList)new LinkedList<byte>();
/*  22 */   private int j = 0;
/*     */ 
/*     */   
/*     */   private static final int k = 8192;
/*     */ 
/*     */   
/*     */   private static final int l = 2;
/*     */ 
/*     */   
/*     */   public f(b decryptor) throws IOException {
/*  32 */     this.a = decryptor;
/*     */     
/*  34 */     this.b.a(this);
/*     */     
/*  36 */     this.f = (this.a != null);
/*  37 */     if (this.a != null) {
/*  38 */       this.g = this.a.a();
/*  39 */       this.e = (this.g != 0);
/*  40 */       if (this.g < 0) {
/*  41 */         this.g = -this.g;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Object parent, byte[] array, int offset, int length) {
/*  51 */     synchronized (this.i) {
/*  52 */       this.j--;
/*  53 */       if (array.length == 8192 && this.i.size() < 2) {
/*  54 */         this.i.add(array);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] a(int len) {
/*  64 */     byte[] buf = null;
/*  65 */     synchronized (this.i) {
/*  66 */       if (len <= 8192) {
/*  67 */         if (this.i.isEmpty()) {
/*  68 */           if (this.j < 2) {
/*  69 */             buf = new byte[8192];
/*     */           } else {
/*  71 */             buf = new byte[len];
/*     */           } 
/*     */         } else {
/*  74 */           buf = this.i.getFirst();
/*     */         } 
/*     */       } else {
/*  77 */         buf = new byte[len];
/*     */       } 
/*  79 */       this.j++;
/*     */     } 
/*  81 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/*  89 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*     */     try {
/*  98 */       return this.b.available();
/*  99 */     } catch (IOException iOException) {
/*     */       
/* 101 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(byte[] arrayOfByte, int off, int len) {
/* 109 */     byte[] t = a(len);
/* 110 */     System.arraycopy(arrayOfByte, off, t, 0, len);
/* 111 */     this.b.a(t, 0, len);
/* 112 */     if (this.d > 0) {
/* 113 */       this.d -= len;
/* 114 */       if (this.d < 0) this.d = 0;
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(byte[] arrayOfByte, int off, int len) {
/* 123 */     int read = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 128 */       if (this.e) {
/* 129 */         this.d = this.g - this.b.available();
/* 130 */         if (this.d <= 0) {
/* 131 */           byte[] buf = new byte[this.g];
/* 132 */           this.b.mark(this.g);
/* 133 */           this.b.read(buf);
/* 134 */           int n = this.a.a(buf, 0, buf.length);
/* 135 */           this.b.reset();
/* 136 */           if (n == 0) {
/*     */             
/* 138 */             this.f = false;
/* 139 */             this.e = false;
/* 140 */             this.d = 0;
/* 141 */             return b(arrayOfByte, off, len);
/* 142 */           }  if (n > 0) {
/*     */             
/* 144 */             this.e = false;
/* 145 */             this.d = 0;
/* 146 */             this.b.skip(n);
/* 147 */             return b(arrayOfByte, off, len);
/*     */           } 
/*     */           
/* 150 */           this.d = -n;
/* 151 */           this.g -= n;
/*     */         } 
/*     */       } else {
/*     */         
/* 155 */         read = this.b.read(arrayOfByte, off, len);
/*     */         
/* 157 */         if (this.f) {
/* 158 */           this.a.a(arrayOfByte, (int)this.h, off, read);
/*     */         }
/* 160 */         this.h += read;
/*     */       } 
/* 162 */     } catch (IOException e) {
/* 163 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 166 */     return read;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(long n) {
/* 174 */     this.b.b();
/* 175 */     this.h += n;
/* 176 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/a/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */