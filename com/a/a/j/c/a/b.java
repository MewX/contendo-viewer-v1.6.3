/*     */ package com.a.a.j.c.a;
/*     */ 
/*     */ import com.a.a.b.a.c;
/*     */ import com.a.a.b.c;
/*     */ import com.a.a.b.e;
/*     */ import com.a.a.b.h;
/*     */ import com.a.a.j.c.m;
/*     */ import com.a.a.j.c.n;
/*     */ import com.a.a.j.c.p;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class b
/*     */   extends n
/*     */   implements p {
/*     */   private final a b;
/*     */   
/*     */   class a implements com.a.a.b.a.b, c {
/*     */     private final byte[] b;
/*     */     
/*     */     a(b this$0) {
/*  26 */       Random rnd = new Random(System.currentTimeMillis());
/*  27 */       int len = rnd.nextInt() & 0xF;
/*  28 */       len += 32;
/*  29 */       this.b = new byte[len];
/*  30 */       rnd.nextBytes(this.b);
/*     */     }
/*     */     
/*     */     public int a() {
/*  34 */       return 0;
/*     */     }
/*     */     
/*     */     public byte[] b() {
/*  38 */       return null;
/*     */     }
/*     */     
/*     */     public void b(byte[] buf, int pos, int off, int len) {
/*  42 */       int keyofs = pos % this.b.length;
/*  43 */       for (int i = 0; i < len; i++, off++) {
/*  44 */         buf[off] = (byte)((buf[off] ^ this.b[keyofs++]) & 0xFF);
/*  45 */         if (keyofs >= this.b.length) {
/*  46 */           keyofs = 0;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public int a(byte[] buf, int off, int len) {
/*  52 */       return 0;
/*     */     }
/*     */     
/*     */     public void a(byte[] buf, int pos, int off, int len) {
/*  56 */       b(buf, pos, off, len);
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
/*     */   public b(m folder, String path, File file) throws FileNotFoundException {
/*  71 */     super(folder, path, file);
/*     */     
/*  73 */     this.b = new a(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream d() throws IOException {
/*  82 */     return (InputStream)new c(super.d(), this.b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public com.a.a.e.b e() throws IOException {
/*  91 */     return com.a.a.e.b.a((h)new com.a.a.b.a.a(super.e(), this.b));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream n() throws FileNotFoundException {
/*     */     try {
/* 100 */       return (OutputStream)new e(com.a.a.b.b.b.a(this.a), this.b);
/* 101 */     } catch (IOException e) {
/* 102 */       throw new FileNotFoundException();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/a/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */