/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import java.awt.Rectangle;
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
/*     */ public class Bitmap
/*     */ {
/*     */   private final int height;
/*     */   private final int width;
/*     */   private final int rowStride;
/*     */   private byte[] bitmap;
/*     */   
/*     */   public Bitmap(int paramInt1, int paramInt2) {
/*  51 */     this.height = paramInt2;
/*  52 */     this.width = paramInt1;
/*  53 */     this.rowStride = paramInt1 + 7 >> 3;
/*     */     
/*  55 */     this.bitmap = new byte[this.height * this.rowStride];
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
/*     */   public byte getPixel(int paramInt1, int paramInt2) {
/*  69 */     int i = getByteIndex(paramInt1, paramInt2);
/*  70 */     int j = getBitOffset(paramInt1);
/*     */     
/*  72 */     int k = 7 - j;
/*  73 */     return (byte)(getByte(i) >> k & 0x1);
/*     */   }
/*     */   
/*     */   public void setPixel(int paramInt1, int paramInt2, byte paramByte) {
/*  77 */     int i = getByteIndex(paramInt1, paramInt2);
/*  78 */     int j = getBitOffset(paramInt1);
/*     */     
/*  80 */     int k = 7 - j;
/*     */     
/*  82 */     byte b1 = this.bitmap[i];
/*  83 */     byte b2 = (byte)(b1 | paramByte << k);
/*  84 */     this.bitmap[i] = b2;
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
/*     */   public int getByteIndex(int paramInt1, int paramInt2) {
/*  98 */     return paramInt2 * this.rowStride + (paramInt1 >> 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getByteArray() {
/* 107 */     return this.bitmap;
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
/*     */   public byte getByte(int paramInt) {
/* 120 */     return this.bitmap[paramInt];
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
/*     */   public void setByte(int paramInt, byte paramByte) {
/* 133 */     this.bitmap[paramInt] = paramByte;
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
/*     */   public int getByteAsInteger(int paramInt) {
/* 146 */     return this.bitmap[paramInt] & 0xFF;
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
/*     */   public int getBitOffset(int paramInt) {
/* 160 */     return paramInt & 0x7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 169 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 178 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowStride() {
/* 188 */     return this.rowStride;
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/* 192 */     return new Rectangle(0, 0, this.width, this.height);
/*     */   }
/*     */   
/*     */   public int getMemorySize() {
/* 196 */     return this.bitmap.length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/Bitmap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */