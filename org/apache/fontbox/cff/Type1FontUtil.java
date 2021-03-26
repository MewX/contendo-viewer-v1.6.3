/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.util.Locale;
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
/*     */ public final class Type1FontUtil
/*     */ {
/*     */   public static String hexEncode(byte[] bytes) {
/*  40 */     StringBuilder sb = new StringBuilder();
/*  41 */     for (byte aByte : bytes) {
/*     */       
/*  43 */       String string = Integer.toHexString(aByte & 0xFF);
/*  44 */       if (string.length() == 1)
/*     */       {
/*  46 */         sb.append("0");
/*     */       }
/*  48 */       sb.append(string.toUpperCase(Locale.US));
/*     */     } 
/*  50 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] hexDecode(String string) {
/*  60 */     if (string.length() % 2 != 0)
/*     */     {
/*  62 */       throw new IllegalArgumentException();
/*     */     }
/*  64 */     byte[] bytes = new byte[string.length() / 2];
/*  65 */     for (int i = 0; i < string.length(); i += 2)
/*     */     {
/*  67 */       bytes[i / 2] = (byte)Integer.parseInt(string.substring(i, i + 2), 16);
/*     */     }
/*  69 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] eexecEncrypt(byte[] buffer) {
/*  79 */     return encrypt(buffer, 55665, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] charstringEncrypt(byte[] buffer, int n) {
/*  90 */     return encrypt(buffer, 4330, n);
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] encrypt(byte[] plaintextBytes, int r, int n) {
/*  95 */     byte[] buffer = new byte[plaintextBytes.length + n];
/*     */     
/*  97 */     for (int i = 0; i < n; i++)
/*     */     {
/*  99 */       buffer[i] = 0;
/*     */     }
/*     */     
/* 102 */     System.arraycopy(plaintextBytes, 0, buffer, n, buffer.length - n);
/*     */     
/* 104 */     int c1 = 52845;
/* 105 */     int c2 = 22719;
/*     */     
/* 107 */     byte[] ciphertextBytes = new byte[buffer.length];
/*     */     
/* 109 */     for (int j = 0; j < buffer.length; j++) {
/*     */       
/* 111 */       int plain = buffer[j] & 0xFF;
/* 112 */       int cipher = plain ^ r >> 8;
/*     */       
/* 114 */       ciphertextBytes[j] = (byte)cipher;
/*     */       
/* 116 */       r = (cipher + r) * c1 + c2 & 0xFFFF;
/*     */     } 
/*     */     
/* 119 */     return ciphertextBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] eexecDecrypt(byte[] buffer) {
/* 129 */     return decrypt(buffer, 55665, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] charstringDecrypt(byte[] buffer, int n) {
/* 140 */     return decrypt(buffer, 4330, n);
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte[] decrypt(byte[] ciphertextBytes, int r, int n) {
/* 145 */     byte[] buffer = new byte[ciphertextBytes.length];
/*     */     
/* 147 */     int c1 = 52845;
/* 148 */     int c2 = 22719;
/*     */     
/* 150 */     for (int i = 0; i < ciphertextBytes.length; i++) {
/*     */       
/* 152 */       int cipher = ciphertextBytes[i] & 0xFF;
/* 153 */       int plain = cipher ^ r >> 8;
/*     */       
/* 155 */       buffer[i] = (byte)plain;
/*     */       
/* 157 */       r = (cipher + r) * c1 + c2 & 0xFFFF;
/*     */     } 
/*     */     
/* 160 */     byte[] plaintextBytes = new byte[ciphertextBytes.length - n];
/* 161 */     System.arraycopy(buffer, n, plaintextBytes, 0, plaintextBytes.length);
/*     */     
/* 163 */     return plaintextBytes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/Type1FontUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */