/*    */ package com.a.a.h.a;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class b
/*    */ {
/*  8 */   public static final char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
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
/*    */   public static String a(Random rnd, int minlen, int maxlen, char[] chartab) {
/* 23 */     int len = minlen;
/*    */     
/* 25 */     if (minlen < 0 || maxlen < 0) throw new IllegalArgumentException("Length is not must negative."); 
/* 26 */     if (minlen > maxlen) throw new IllegalArgumentException("Minlen larger than Maxlen.");
/*    */     
/* 28 */     if (maxlen == 0) return ""; 
/* 29 */     if (minlen != maxlen) {
/* 30 */       len += rnd.nextInt(maxlen - minlen);
/*    */     }
/*    */     
/* 33 */     if (chartab == null || chartab.length == 0) {
/* 34 */       chartab = a;
/*    */     }
/*    */     
/* 37 */     StringBuilder sb = new StringBuilder();
/* 38 */     for (int i = 0; i < len; i++) {
/* 39 */       sb.append(chartab[rnd.nextInt(chartab.length)]);
/*    */     }
/*    */     
/* 42 */     return sb.toString().intern();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/a/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */