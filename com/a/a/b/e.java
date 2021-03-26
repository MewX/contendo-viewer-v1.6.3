/*    */ package com.a.a.b;
/*    */ 
/*    */ import com.a.a.b.a.c;
/*    */ import com.a.a.b.a.e;
/*    */ import com.a.a.b.a.g;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class e
/*    */   extends b {
/*    */   private e a;
/* 12 */   private byte[] b = new byte[1];
/* 13 */   private byte[] c = new byte[512];
/* 14 */   private long d = 0L;
/* 15 */   private long e = 0L;
/*    */   
/*    */   public e(OutputStream out, c encryptor) throws IOException {
/* 18 */     this(out, (e)new g(encryptor));
/*    */   }
/*    */   
/*    */   public e(OutputStream out, e encryptor) {
/* 22 */     super(out);
/* 23 */     this.a = encryptor;
/*    */   }
/*    */   
/*    */   public long a() {
/* 27 */     return this.d;
/*    */   }
/*    */   
/*    */   public long b() {
/* 31 */     return (this.a == null) ? this.d : this.e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] arrayOfByte, int off, int len) throws IOException {
/* 39 */     if (this.a == null) {
/* 40 */       this.out.write(arrayOfByte, off, len);
/*    */     } else {
/* 42 */       this.a.a(arrayOfByte, off, len);
/* 43 */       c();
/*    */     } 
/* 45 */     this.d += len;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(int i) throws IOException {
/* 54 */     if (this.a == null) {
/* 55 */       super.write(i);
/*    */     } else {
/* 57 */       this.b[0] = (byte)i;
/* 58 */       this.a.a(this.b, 0, 1);
/* 59 */       c();
/*    */     } 
/* 61 */     this.d++;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void c() throws IOException {
/* 68 */     while (this.a.b() > 0) {
/* 69 */       int n = this.a.b(this.c, 0, this.c.length);
/* 70 */       this.out.write(this.c, 0, n);
/* 71 */       this.e += n;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() throws IOException {
/* 80 */     if (this.a != null) c(); 
/* 81 */     super.flush();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/*    */     try {
/* 90 */       if (this.a != null) {
/* 91 */         this.a.c();
/* 92 */         c();
/*    */       } 
/*    */     } finally {
/* 95 */       super.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */