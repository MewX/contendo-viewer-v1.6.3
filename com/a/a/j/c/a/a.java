/*    */ package com.a.a.j.c.a;
/*    */ 
/*    */ import com.a.a.b.a.b;
/*    */ import com.a.a.b.a.c;
/*    */ import java.util.Random;
/*    */ 
/*    */ final class a
/*    */   implements b, c {
/*    */   private final byte[] a;
/*    */   
/*    */   a() {
/* 12 */     Random rnd = new Random(System.currentTimeMillis());
/* 13 */     this.a = new byte[256];
/* 14 */     rnd.nextBytes(this.a);
/*    */   }
/*    */   
/*    */   public int a() {
/* 18 */     return 0;
/*    */   }
/*    */   
/*    */   public byte[] b() {
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public void b(byte[] buf, int pos, int off, int len) {
/* 26 */     int keyofs = pos & 0xFF;
/* 27 */     for (int i = off, m = off + len; i < m; i++, keyofs++) {
/* 28 */       if (keyofs >= this.a.length) keyofs = 0; 
/* 29 */       buf[i] = (byte)((buf[i] ^ this.a[keyofs]) & 0xFF);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a(byte[] buf, int off, int len) {
/* 34 */     return 0;
/*    */   }
/*    */   
/*    */   public void a(byte[] buf, int pos, int off, int len) {
/* 38 */     b(buf, pos, off, len);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */