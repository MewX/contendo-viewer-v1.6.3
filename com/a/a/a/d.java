/*    */ package com.a.a.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class d
/*    */   implements c
/*    */ {
/*    */   public int a(byte[] b, byte x, int pos) {
/* 14 */     b[pos] = x;
/* 15 */     return pos + 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(byte[] b, byte[] x, int pos) {
/* 23 */     System.arraycopy(x, 0, b, pos, x.length);
/* 24 */     return pos + x.length;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public char a(char n) {
/* 32 */     return (char)((n << 8 | n >>> 8) & 0xFFFF);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short a(short n) {
/* 40 */     return (short)((n << 8 | n >>> 8) & 0xFFFF);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(int n) {
/* 48 */     int h = n << 8 & 0xFF00FF00;
/* 49 */     int l = n >>> 8 & 0xFF00FF;
/* 50 */     n = h | l;
/* 51 */     h = n << 16;
/* 52 */     l = n >>> 16;
/* 53 */     return h | l;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/a/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */