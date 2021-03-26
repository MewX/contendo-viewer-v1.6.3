/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class O
/*    */   implements Serializable
/*    */ {
/*    */   private static final long c = 0L;
/*    */   public final byte[] a;
/*    */   
/*    */   public O(byte[] panose) {
/* 33 */     if (!b && panose.length != 10) throw new AssertionError(); 
/* 34 */     this.a = panose;
/*    */   }
/*    */   
/*    */   public byte a() {
/* 38 */     return this.a[0];
/*    */   }
/*    */   
/*    */   public byte b() {
/* 42 */     return this.a[1];
/*    */   }
/*    */   
/*    */   public byte c() {
/* 46 */     return this.a[2];
/*    */   }
/*    */   
/*    */   public byte d() {
/* 50 */     return this.a[3];
/*    */   }
/*    */   
/*    */   public byte e() {
/* 54 */     return this.a[4];
/*    */   }
/*    */   
/*    */   public byte f() {
/* 58 */     return this.a[5];
/*    */   }
/*    */   
/*    */   public byte g() {
/* 62 */     return this.a[6];
/*    */   }
/*    */   
/*    */   public byte h() {
/* 66 */     return this.a[7];
/*    */   }
/*    */   
/*    */   public byte i() {
/* 70 */     return this.a[8];
/*    */   }
/*    */   
/*    */   public byte j() {
/* 74 */     return this.a[9];
/*    */   }
/*    */   
/*    */   public String toString() {
/* 78 */     StringBuffer sb = new StringBuffer();
/* 79 */     sb.append(String.valueOf(this.a[0]));
/* 80 */     for (int i = 1; i < this.a.length; i++) {
/* 81 */       sb.append(' ').append(String.valueOf(this.a[i]));
/*    */     }
/* 83 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/O.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */