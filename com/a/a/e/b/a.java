/*    */ package com.a.a.e.b;
/*    */ 
/*    */ import com.a.a.e.b;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public class a extends InputStream {
/*    */   b a;
/*    */   
/*    */   public a(b buf) {
/* 12 */     this.a = buf;
/*    */   }
/*    */   
/*    */   public a(ByteBuffer buf) {
/* 16 */     this.a = b.c(buf);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 21 */     if (this.a.g() > 0) return this.a.i() & 0xFF; 
/* 22 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] arrayOfByte, int off, int len) throws IOException {
/* 27 */     int cnt = 0;
/* 28 */     if (len > 0) {
/* 29 */       cnt = len;
/* 30 */       if (cnt > this.a.g()) {
/* 31 */         cnt = this.a.g();
/*    */       }
/* 33 */       if (cnt > 0) {
/* 34 */         this.a.c(arrayOfByte, off, cnt);
/*    */       } else {
/* 36 */         cnt = -1;
/*    */       } 
/*    */     } 
/* 39 */     return cnt;
/*    */   }
/*    */ 
/*    */   
/*    */   public long skip(long n) throws IOException {
/* 44 */     if (n > this.a.g()) {
/* 45 */       n = this.a.g();
/*    */     }
/* 47 */     int pos = (int)(this.a.k() + n);
/* 48 */     this.a.h(pos);
/* 49 */     return n;
/*    */   }
/*    */ 
/*    */   
/*    */   public int available() throws IOException {
/* 54 */     return this.a.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void mark(int readlimit) {
/* 59 */     this.a.h();
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void reset() throws IOException {
/* 64 */     this.a.p();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean markSupported() {
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */