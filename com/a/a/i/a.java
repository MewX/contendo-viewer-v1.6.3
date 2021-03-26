/*    */ package com.a.a.i;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */ {
/*    */   public static byte[] a(byte[]... arrays) {
/* 11 */     byte[] buf = null;
/* 12 */     if (arrays != null) {
/* 13 */       int len = 0; byte b1; int i; byte[][] arrayOfByte1;
/* 14 */       for (i = (arrayOfByte1 = arrays).length, b1 = 0; b1 < i; ) { byte[] arrayOfByte = arrayOfByte1[b1];
/* 15 */         if (arrayOfByte != null) len += arrayOfByte.length;  b1++; }
/*    */       
/* 17 */       buf = new byte[len];
/* 18 */       int pos = 0; byte b2; int j;
/*    */       byte[][] arrayOfByte2;
/* 20 */       for (j = (arrayOfByte2 = arrays).length, b2 = 0; b2 < j; ) { byte[] arrayOfByte = arrayOfByte2[b2];
/* 21 */         if (arrayOfByte != null) {
/* 22 */           int n = arrayOfByte.length;
/* 23 */           System.arraycopy(arrayOfByte, 0, buf, pos, n);
/* 24 */           pos += n;
/*    */         }  b2++; }
/*    */     
/*    */     } 
/* 28 */     return buf;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */