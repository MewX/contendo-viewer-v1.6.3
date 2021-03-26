/*    */ package com.a.a.b;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class g extends FilterInputStream {
/*    */   private long a;
/*  9 */   private long b = 0L;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public g(InputStream in) {
/* 15 */     this(in, Long.MAX_VALUE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public g(InputStream in, long limit) {
/* 23 */     super(in);
/* 24 */     this.a = limit;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 32 */     if (this.b >= this.a) return -1;
/*    */     
/* 34 */     int n = super.read();
/* 35 */     if (n >= 0) this.b++;
/*    */     
/* 37 */     return n;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 45 */     if (this.b >= this.a) return -1; 
/* 46 */     if (len == 0) return 0;
/*    */     
/* 48 */     len = Math.min(len, (int)(this.a - this.b));
/* 49 */     if (len <= 0) return -1;
/*    */     
/* 51 */     int n = super.read(b, off, len);
/* 52 */     if (n >= 0) this.b += n;
/*    */     
/* 54 */     return n;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long skip(long n) throws IOException {
/* 62 */     n = Math.min(n, (int)(this.a - this.b));
/* 63 */     if (this.b + n > this.a) {
/* 64 */       n = this.a - this.b;
/*    */     }
/*    */     
/* 67 */     long rtn = super.skip(n);
/* 68 */     if (rtn > 0L) {
/* 69 */       this.b += n;
/*    */     } else {
/* 71 */       n = 0L;
/*    */     } 
/*    */     
/* 74 */     return n;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int available() throws IOException {
/* 82 */     return Math.min((int)(this.a - this.b), super.available());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */