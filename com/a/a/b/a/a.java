/*    */ package com.a.a.b.a;
/*    */ 
/*    */ import com.a.a.b.h;
/*    */ import com.a.a.e.b;
/*    */ import java.io.EOFException;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class a
/*    */   extends h {
/*    */   final b a;
/*    */   final b b;
/*    */   private boolean c = true;
/*    */   
/*    */   public a(b buf, b decryptor) throws IOException {
/* 15 */     this.b = decryptor;
/* 16 */     b tmp = buf.u();
/* 17 */     if (this.b.a() != 0) {
/* 18 */       int headderSize = this.b.a();
/* 19 */       byte[] arrayOfByte = new byte[(headderSize < 0) ? 1024 : headderSize];
/* 20 */       headderSize = Math.abs(headderSize);
/*    */       
/*    */       while (true) {
/* 23 */         tmp.f(arrayOfByte);
/* 24 */         int n = this.b.a(arrayOfByte, 0, headderSize);
/* 25 */         tmp.s();
/* 26 */         if (n == 0) {
/* 27 */           this.c = false; break;
/*    */         } 
/* 29 */         if (n > 0) {
/* 30 */           tmp = tmp.h(n).u();
/*    */           break;
/*    */         } 
/* 33 */         headderSize -= n;
/*    */       } 
/*    */     } 
/*    */     
/* 37 */     this.a = tmp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long a() throws IOException {
/* 52 */     return this.a.k();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long b() throws IOException {
/* 60 */     return this.a.f();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(byte[] arrayOfByte, int off, int len) throws IOException {
/* 68 */     int n = Math.min(this.a.g(), len);
/* 69 */     int pos = this.a.k();
/* 70 */     this.a.c(arrayOfByte, off, n);
/* 71 */     if (this.c) {
/* 72 */       this.b.a(arrayOfByte, pos, off, len);
/*    */     }
/* 74 */     return n;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(byte[] arrayOfByte, int off, int len) throws EOFException, IOException {
/* 83 */     if (this.a.g() < len) throw new EOFException(); 
/* 84 */     int pos = this.a.k();
/* 85 */     this.a.c(arrayOfByte, off, len);
/* 86 */     if (this.c) {
/* 87 */       this.b.a(arrayOfByte, pos, off, len);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(long pos) throws IOException {
/* 97 */     this.a.h((int)pos);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */