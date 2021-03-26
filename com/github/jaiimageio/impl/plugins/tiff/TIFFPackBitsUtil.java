/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class TIFFPackBitsUtil
/*     */ {
/*     */   private static final boolean debug = false;
/*  59 */   byte[] dstData = new byte[8192];
/*  60 */   int dstIndex = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureCapacity(int bytesToAdd) {
/*  66 */     if (this.dstIndex + bytesToAdd > this.dstData.length) {
/*  67 */       byte[] newDstData = new byte[Math.max((int)(this.dstData.length * 1.2F), this.dstIndex + bytesToAdd)];
/*     */       
/*  69 */       System.arraycopy(this.dstData, 0, newDstData, 0, this.dstData.length);
/*  70 */       this.dstData = newDstData;
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] decode(byte[] srcData) throws IOException {
/*  75 */     int inIndex = 0;
/*  76 */     while (inIndex < srcData.length) {
/*  77 */       byte b = srcData[inIndex++];
/*     */       
/*  79 */       if (b >= 0 && b <= Byte.MAX_VALUE) {
/*     */ 
/*     */         
/*  82 */         ensureCapacity(b + 1);
/*  83 */         for (int i = 0; i < b + 1; i++)
/*  84 */           this.dstData[this.dstIndex++] = srcData[inIndex++];  continue;
/*     */       } 
/*  86 */       if (b <= -1 && b >= -127) {
/*     */         
/*  88 */         byte repeat = srcData[inIndex++];
/*  89 */         ensureCapacity(-b + 1);
/*  90 */         for (int i = 0; i < -b + 1; i++) {
/*  91 */           this.dstData[this.dstIndex++] = repeat;
/*     */         }
/*     */         continue;
/*     */       } 
/*  95 */       inIndex++;
/*     */     } 
/*     */ 
/*     */     
/*  99 */     byte[] newDstData = new byte[this.dstIndex];
/* 100 */     System.arraycopy(this.dstData, 0, newDstData, 0, this.dstIndex);
/* 101 */     return newDstData;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFPackBitsUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */