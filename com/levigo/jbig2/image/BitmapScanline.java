/*     */ package com.levigo.jbig2.image;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import java.awt.image.WritableRaster;
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
/*     */ final class BitmapScanline
/*     */   extends Scanline
/*     */ {
/*     */   private Bitmap bitmap;
/*     */   private WritableRaster raster;
/*     */   private int[] lineBuffer;
/*     */   
/*     */   public BitmapScanline(Bitmap paramBitmap, WritableRaster paramWritableRaster, int paramInt) {
/*  32 */     super(paramInt);
/*  33 */     this.bitmap = paramBitmap;
/*  34 */     this.raster = paramWritableRaster;
/*  35 */     this.lineBuffer = new int[this.length];
/*     */   }
/*     */ 
/*     */   
/*     */   protected void clear() {
/*  40 */     this.lineBuffer = new int[this.length];
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fetch(int paramInt1, int paramInt2) {
/*  45 */     this.lineBuffer = new int[this.length];
/*  46 */     int i = this.bitmap.getByteIndex(paramInt1, paramInt2);
/*  47 */     while (paramInt1 < this.length) {
/*  48 */       byte b = (byte)(this.bitmap.getByte(i++) ^ 0xFFFFFFFF);
/*  49 */       byte b1 = (this.bitmap.getWidth() - paramInt1 > 8) ? 8 : (this.bitmap.getWidth() - paramInt1);
/*  50 */       for (int j = b1 - 1; j >= 0; j--, paramInt1++) {
/*  51 */         if ((b >> j & 0x1) != 0) {
/*  52 */           this.lineBuffer[paramInt1] = 255;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void filter(int[] paramArrayOfint1, int[] paramArrayOfint2, Weighttab[] paramArrayOfWeighttab, Scanline paramScanline) {
/*  59 */     BitmapScanline bitmapScanline = (BitmapScanline)paramScanline;
/*  60 */     int i = paramScanline.length;
/*     */ 
/*     */     
/*  63 */     int j = 1 << paramArrayOfint2[0] - 1;
/*  64 */     int[] arrayOfInt1 = this.lineBuffer;
/*  65 */     int[] arrayOfInt2 = bitmapScanline.lineBuffer;
/*     */ 
/*     */     
/*  68 */     int k = paramArrayOfint1[0];
/*  69 */     int m = paramArrayOfint2[0];
/*  70 */     if (k != 0) {
/*  71 */       for (byte b1 = 0, b2 = 0; b2 < i; b2++) {
/*  72 */         Weighttab weighttab = paramArrayOfWeighttab[b2];
/*  73 */         int n = weighttab.weights.length;
/*     */         
/*  75 */         int i1 = j; int i2, i3;
/*  76 */         for (i2 = 0, i3 = weighttab.i0; i2 < n && i3 < arrayOfInt1.length; i2++) {
/*  77 */           i1 += weighttab.weights[i2] * (arrayOfInt1[i3++] >> k);
/*     */         }
/*     */         
/*  80 */         i2 = i1 >> m;
/*  81 */         arrayOfInt2[b1++] = (i2 < 0) ? 0 : ((i2 > 255) ? 255 : i2);
/*     */       } 
/*     */     } else {
/*  84 */       for (byte b1 = 0, b2 = 0; b2 < i; b2++) {
/*  85 */         Weighttab weighttab = paramArrayOfWeighttab[b2];
/*  86 */         int n = weighttab.weights.length;
/*     */         
/*  88 */         int i1 = j; byte b; int i2;
/*  89 */         for (b = 0, i2 = weighttab.i0; b < n && i2 < arrayOfInt1.length; b++) {
/*  90 */           i1 += weighttab.weights[b] * arrayOfInt1[i2++];
/*     */         }
/*     */         
/*  93 */         arrayOfInt2[b1++] = i1 >> m;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void accumulate(int paramInt, Scanline paramScanline) {
/* 100 */     BitmapScanline bitmapScanline = (BitmapScanline)paramScanline;
/*     */     
/* 102 */     int[] arrayOfInt1 = this.lineBuffer;
/* 103 */     int[] arrayOfInt2 = bitmapScanline.lineBuffer;
/*     */     
/* 105 */     for (byte b = 0; b < arrayOfInt2.length; b++) {
/* 106 */       arrayOfInt2[b] = arrayOfInt2[b] + paramInt * arrayOfInt1[b];
/*     */     }
/*     */   }
/*     */   
/*     */   protected void shift(int[] paramArrayOfint) {
/* 111 */     int i = paramArrayOfint[0];
/* 112 */     int j = 1 << i - 1;
/*     */     
/* 114 */     int[] arrayOfInt = this.lineBuffer;
/*     */     
/* 116 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 117 */       int k = arrayOfInt[b] + j >> i;
/* 118 */       arrayOfInt[b] = (k < 0) ? 0 : ((k > 255) ? 255 : k);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void store(int paramInt1, int paramInt2) {
/* 124 */     this.raster.setSamples(paramInt1, paramInt2, this.length, 1, 0, this.lineBuffer);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/BitmapScanline.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */