/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
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
/*     */ class RC4Cipher
/*     */ {
/*  39 */   private final int[] salt = new int[256];
/*     */ 
/*     */   
/*     */   private int b;
/*     */ 
/*     */   
/*     */   private int c;
/*     */ 
/*     */   
/*     */   public void setKey(byte[] key) {
/*  49 */     this.b = 0;
/*  50 */     this.c = 0;
/*     */     
/*  52 */     if (key.length < 1 || key.length > 32)
/*     */     {
/*  54 */       throw new IllegalArgumentException("number of bytes must be between 1 and 32");
/*     */     }
/*  56 */     for (int i = 0; i < this.salt.length; i++)
/*     */     {
/*  58 */       this.salt[i] = i;
/*     */     }
/*     */     
/*  61 */     int keyIndex = 0;
/*  62 */     int saltIndex = 0;
/*  63 */     for (int j = 0; j < this.salt.length; j++) {
/*     */       
/*  65 */       saltIndex = (fixByte(key[keyIndex]) + this.salt[j] + saltIndex) % 256;
/*  66 */       swap(this.salt, j, saltIndex);
/*  67 */       keyIndex = (keyIndex + 1) % key.length;
/*     */     } 
/*     */   }
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
/*     */   private static int fixByte(byte aByte) {
/*  81 */     return (aByte < 0) ? (256 + aByte) : aByte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void swap(int[] data, int firstIndex, int secondIndex) {
/*  93 */     int tmp = data[firstIndex];
/*  94 */     data[firstIndex] = data[secondIndex];
/*  95 */     data[secondIndex] = tmp;
/*     */   }
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
/*     */   public void write(byte aByte, OutputStream output) throws IOException {
/* 108 */     this.b = (this.b + 1) % 256;
/* 109 */     this.c = (this.salt[this.b] + this.c) % 256;
/* 110 */     swap(this.salt, this.b, this.c);
/* 111 */     int saltIndex = (this.salt[this.b] + this.salt[this.c]) % 256;
/* 112 */     output.write(aByte ^ (byte)this.salt[saltIndex]);
/*     */   }
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
/*     */   public void write(byte[] data, OutputStream output) throws IOException {
/* 125 */     for (byte aData : data)
/*     */     {
/* 127 */       write(aData, output);
/*     */     }
/*     */   }
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
/*     */   public void write(InputStream data, OutputStream output) throws IOException {
/* 141 */     byte[] buffer = new byte[1024];
/*     */     int amountRead;
/* 143 */     while ((amountRead = data.read(buffer)) != -1)
/*     */     {
/* 145 */       write(buffer, 0, amountRead, output);
/*     */     }
/*     */   }
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
/*     */   public void write(byte[] data, int offset, int len, OutputStream output) throws IOException {
/* 161 */     for (int i = offset; i < offset + len; i++)
/*     */     {
/* 163 */       write(data[i], output);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/RC4Cipher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */