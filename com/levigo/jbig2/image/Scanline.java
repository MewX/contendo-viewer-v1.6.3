/*     */ package com.levigo.jbig2.image;
/*     */ 
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
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
/*     */ 
/*     */ abstract class Scanline
/*     */ {
/*     */   int y;
/*     */   protected final int length;
/*     */   
/*     */   public static interface ScanlineFilter
/*     */   {
/*     */     void filter(int param1Int1, int param1Int2, int param1Int3, Object param1Object, int param1Int4);
/*     */   }
/*     */   
/*     */   protected static final class ByteBGRScanline
/*     */     extends Scanline
/*     */   {
/*     */     private final Raster srcRaster;
/*     */     private final WritableRaster dstRaster;
/*     */     private final int[] data;
/*     */     
/*     */     protected ByteBGRScanline(Raster param1Raster, WritableRaster param1WritableRaster, int param1Int) {
/*  45 */       super(param1Int);
/*  46 */       this.srcRaster = param1Raster;
/*  47 */       this.dstRaster = param1WritableRaster;
/*     */       
/*  49 */       this.data = new int[3 * param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     protected void accumulate(int param1Int, Scanline param1Scanline) {
/*  54 */       ByteBGRScanline byteBGRScanline = (ByteBGRScanline)param1Scanline;
/*     */       
/*  56 */       int[] arrayOfInt1 = this.data;
/*  57 */       int[] arrayOfInt2 = byteBGRScanline.data;
/*     */       
/*  59 */       for (byte b = 0; b < arrayOfInt2.length; b++) {
/*  60 */         arrayOfInt2[b] = arrayOfInt2[b] + param1Int * arrayOfInt1[b];
/*     */       }
/*     */     }
/*     */     
/*     */     protected void clear() {
/*  65 */       int[] arrayOfInt = this.data;
/*  66 */       for (byte b = 0; b < arrayOfInt.length; b++) {
/*  67 */         arrayOfInt[b] = 0;
/*     */       }
/*     */     }
/*     */     
/*     */     protected void fetch(int param1Int1, int param1Int2) {
/*  72 */       this.srcRaster.getPixels(param1Int1, param1Int2, this.length, 1, this.data);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void filter(int[] param1ArrayOfint1, int[] param1ArrayOfint2, Weighttab[] param1ArrayOfWeighttab, Scanline param1Scanline) {
/*  77 */       ByteBGRScanline byteBGRScanline = (ByteBGRScanline)param1Scanline;
/*  78 */       int i = param1Scanline.length;
/*     */ 
/*     */       
/*  81 */       int[] arrayOfInt1 = { 1 << param1ArrayOfint2[0] - 1, 1 << param1ArrayOfint2[1] - 1, 1 << param1ArrayOfint2[2] - 1 };
/*     */ 
/*     */       
/*  84 */       int[] arrayOfInt2 = this.data;
/*  85 */       int[] arrayOfInt3 = byteBGRScanline.data;
/*     */ 
/*     */ 
/*     */       
/*  89 */       if (param1ArrayOfint1[0] != 0 || param1ArrayOfint1[1] != 0 || param1ArrayOfint1[2] != 0) {
/*  90 */         for (byte b1 = 0, b2 = 0; b2 < i; b2++) {
/*  91 */           Weighttab weighttab = param1ArrayOfWeighttab[b2];
/*  92 */           int j = weighttab.weights.length;
/*     */           
/*  94 */           int k = arrayOfInt1[0];
/*  95 */           int m = arrayOfInt1[1];
/*  96 */           int n = arrayOfInt1[2]; int i1, i2;
/*  97 */           for (i1 = 0, i2 = weighttab.i0 * 3; i1 < j && i2 < arrayOfInt2.length; i1++) {
/*  98 */             int i3 = weighttab.weights[i1];
/*  99 */             k += i3 * (arrayOfInt2[i2++] >> param1ArrayOfint1[0]);
/* 100 */             m += i3 * (arrayOfInt2[i2++] >> param1ArrayOfint1[1]);
/* 101 */             n += i3 * (arrayOfInt2[i2++] >> param1ArrayOfint1[2]);
/*     */           } 
/*     */           
/* 104 */           i1 = k >> param1ArrayOfint2[0];
/* 105 */           arrayOfInt3[b1++] = (i1 < 0) ? 0 : ((i1 > 255) ? 255 : i1);
/* 106 */           i1 = m >> param1ArrayOfint2[1];
/* 107 */           arrayOfInt3[b1++] = (i1 < 0) ? 0 : ((i1 > 255) ? 255 : i1);
/* 108 */           i1 = n >> param1ArrayOfint2[2];
/* 109 */           arrayOfInt3[b1++] = (i1 < 0) ? 0 : ((i1 > 255) ? 255 : i1);
/*     */         } 
/*     */       } else {
/* 112 */         for (byte b1 = 0, b2 = 0; b2 < i; b2++) {
/* 113 */           Weighttab weighttab = param1ArrayOfWeighttab[b2];
/* 114 */           int j = weighttab.weights.length;
/*     */           
/* 116 */           int k = arrayOfInt1[0];
/* 117 */           int m = arrayOfInt1[1];
/* 118 */           int n = arrayOfInt1[2]; byte b; int i1;
/* 119 */           for (b = 0, i1 = weighttab.i0 * 3; b < j && i1 < arrayOfInt2.length; b++) {
/* 120 */             int i2 = weighttab.weights[b];
/* 121 */             k += i2 * arrayOfInt2[i1++];
/* 122 */             m += i2 * arrayOfInt2[i1++];
/* 123 */             n += i2 * arrayOfInt2[i1++];
/*     */           } 
/*     */           
/* 126 */           arrayOfInt3[b1++] = k >> param1ArrayOfint2[0];
/* 127 */           arrayOfInt3[b1++] = m >> param1ArrayOfint2[1];
/* 128 */           arrayOfInt3[b1++] = n >> param1ArrayOfint2[2];
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void shift(int[] param1ArrayOfint) {
/* 134 */       int[] arrayOfInt1 = { 1 << param1ArrayOfint[0] - 1, 1 << param1ArrayOfint[1] - 1, 1 << param1ArrayOfint[2] - 1 };
/*     */ 
/*     */ 
/*     */       
/* 138 */       int[] arrayOfInt2 = this.data;
/*     */       
/* 140 */       for (byte b = 0; b < arrayOfInt2.length;) {
/* 141 */         for (byte b1 = 0; b1 < 3; b1++, b++) {
/* 142 */           int i = arrayOfInt2[b] + arrayOfInt1[b1] >> param1ArrayOfint[b1];
/* 143 */           arrayOfInt2[b] = (i < 0) ? 0 : ((i > 255) ? 255 : i);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void store(int param1Int1, int param1Int2) {
/* 150 */       this.dstRaster.setPixels(param1Int1, param1Int2, this.length, 1, this.data);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static final class IntegerSinglePixelPackedScanline
/*     */     extends Scanline
/*     */   {
/*     */     private final Raster srcRaster;
/*     */     
/*     */     private final WritableRaster dstRaster;
/*     */     private final int[] data;
/*     */     private final int[] bitMasks;
/*     */     private final int[] bitOffsets;
/*     */     private final int componentCount;
/*     */     private final SinglePixelPackedSampleModel srcSM;
/*     */     private final int[] tmp;
/*     */     
/*     */     protected IntegerSinglePixelPackedScanline(Raster param1Raster, WritableRaster param1WritableRaster, int param1Int) {
/* 169 */       super(param1Int);
/* 170 */       this.srcRaster = param1Raster;
/* 171 */       this.dstRaster = param1WritableRaster;
/*     */       
/* 173 */       this.srcSM = (SinglePixelPackedSampleModel)this.srcRaster.getSampleModel();
/*     */       
/* 175 */       this.bitMasks = this.srcSM.getBitMasks();
/* 176 */       this.bitOffsets = this.srcSM.getBitOffsets();
/* 177 */       this.componentCount = this.bitMasks.length;
/*     */       
/* 179 */       if (this.componentCount != this.bitOffsets.length || this.bitOffsets.length != this.srcSM.getNumBands()) {
/* 180 */         throw new IllegalArgumentException("weird: getBitMasks().length != getBitOffsets().length");
/*     */       }
/* 182 */       this.tmp = new int[this.componentCount];
/*     */       
/* 184 */       this.data = new int[this.componentCount * param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     protected void accumulate(int param1Int, Scanline param1Scanline) {
/* 189 */       IntegerSinglePixelPackedScanline integerSinglePixelPackedScanline = (IntegerSinglePixelPackedScanline)param1Scanline;
/*     */       
/* 191 */       int[] arrayOfInt1 = this.data;
/* 192 */       int[] arrayOfInt2 = integerSinglePixelPackedScanline.data;
/*     */       
/* 194 */       for (byte b = 0; b < arrayOfInt2.length; b++) {
/* 195 */         arrayOfInt2[b] = arrayOfInt2[b] + param1Int * arrayOfInt1[b];
/*     */       }
/*     */     }
/*     */     
/*     */     protected void clear() {
/* 200 */       int[] arrayOfInt = this.data;
/* 201 */       for (byte b = 0; b < arrayOfInt.length; b++) {
/* 202 */         arrayOfInt[b] = 0;
/*     */       }
/*     */     }
/*     */     
/*     */     protected void fetch(int param1Int1, int param1Int2) {
/* 207 */       this.srcRaster.getPixels(param1Int1, param1Int2, this.length, 1, this.data);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void filter(int[] param1ArrayOfint1, int[] param1ArrayOfint2, Weighttab[] param1ArrayOfWeighttab, Scanline param1Scanline) {
/* 212 */       IntegerSinglePixelPackedScanline integerSinglePixelPackedScanline = (IntegerSinglePixelPackedScanline)param1Scanline;
/* 213 */       int i = param1Scanline.length;
/*     */ 
/*     */       
/* 216 */       int[] arrayOfInt1 = this.tmp;
/* 217 */       for (byte b1 = 0; b1 < this.componentCount; b1++) {
/* 218 */         arrayOfInt1[b1] = 1 << param1ArrayOfint2[b1] - 1;
/*     */       }
/* 220 */       int[] arrayOfInt2 = this.data;
/* 221 */       int[] arrayOfInt3 = integerSinglePixelPackedScanline.data;
/*     */ 
/*     */ 
/*     */       
/* 225 */       int j = 0; byte b2;
/* 226 */       for (b2 = 0; b2 < this.componentCount && !j; b2++)
/* 227 */         j |= (param1ArrayOfint1[b2] != 0) ? 1 : 0; 
/* 228 */       if (j != 0) {
/* 229 */         byte b; for (b2 = 0, b = 0; b < i; b++) {
/* 230 */           Weighttab weighttab = param1ArrayOfWeighttab[b];
/* 231 */           int k = weighttab.weights.length;
/*     */           
/* 233 */           for (byte b3 = 0; b3 < this.componentCount; b3++) {
/* 234 */             int m = arrayOfInt1[b3]; int n, i1;
/* 235 */             for (n = 0, i1 = weighttab.i0 * this.componentCount + b3; n < k && i1 < arrayOfInt2.length; n++, i1 += this.componentCount) {
/* 236 */               m += weighttab.weights[n] * (arrayOfInt2[i1] >> param1ArrayOfint1[b3]);
/*     */             }
/* 238 */             n = m >> param1ArrayOfint2[b3];
/* 239 */             arrayOfInt3[b2++] = (n < 0) ? 0 : ((n > 255) ? 255 : n);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 243 */         byte b; for (b2 = 0, b = 0; b < i; b++) {
/* 244 */           Weighttab weighttab = param1ArrayOfWeighttab[b];
/* 245 */           int k = weighttab.weights.length;
/*     */           
/* 247 */           for (byte b3 = 0; b3 < this.componentCount; b3++) {
/* 248 */             int m = arrayOfInt1[b3]; int n;
/* 249 */             for (byte b4 = 0; b4 < k && n < arrayOfInt2.length; b4++, n += this.componentCount) {
/* 250 */               m += weighttab.weights[b4] * arrayOfInt2[n];
/*     */             }
/* 252 */             arrayOfInt3[b2++] = m >> param1ArrayOfint2[b3];
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void shift(int[] param1ArrayOfint) {
/* 259 */       int[] arrayOfInt1 = this.tmp;
/* 260 */       for (byte b1 = 0; b1 < this.componentCount; b1++) {
/* 261 */         arrayOfInt1[b1] = 1 << param1ArrayOfint[b1] - 1;
/*     */       }
/* 263 */       int[] arrayOfInt2 = this.data;
/*     */       
/* 265 */       for (byte b2 = 0; b2 < arrayOfInt2.length;) {
/* 266 */         for (byte b = 0; b < this.componentCount; b++, b2++) {
/* 267 */           int i = arrayOfInt2[b2] + arrayOfInt1[b] >> param1ArrayOfint[b];
/* 268 */           arrayOfInt2[b2] = (i < 0) ? 0 : ((i > 255) ? 255 : i);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void store(int param1Int1, int param1Int2) {
/* 275 */       this.dstRaster.setPixels(param1Int1, param1Int2, this.length, 1, this.data);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static final class GenericRasterScanline
/*     */     extends Scanline
/*     */   {
/*     */     private final Raster srcRaster;
/*     */     
/*     */     private final WritableRaster dstRaster;
/*     */     
/*     */     private final int componentCount;
/*     */     private final int[][] data;
/*     */     private final SampleModel srcSM;
/*     */     private final SampleModel dstSM;
/*     */     private final int[] channelMask;
/*     */     private final int[] tmp;
/*     */     private final Scanline.ScanlineFilter inputFilter;
/*     */     
/*     */     protected GenericRasterScanline(Raster param1Raster, WritableRaster param1WritableRaster, int param1Int, int[] param1ArrayOfint, Scanline.ScanlineFilter param1ScanlineFilter) {
/* 296 */       super(param1Int);
/* 297 */       this.srcRaster = param1Raster;
/* 298 */       this.dstRaster = param1WritableRaster;
/* 299 */       this.inputFilter = param1ScanlineFilter;
/*     */       
/* 301 */       this.srcSM = this.srcRaster.getSampleModel();
/* 302 */       this.dstSM = this.dstRaster.getSampleModel();
/*     */       
/* 304 */       this.componentCount = this.srcSM.getNumBands();
/*     */       
/* 306 */       if (this.componentCount != this.dstSM.getNumBands()) {
/* 307 */         throw new IllegalArgumentException("weird: src raster num bands != dst raster num bands");
/*     */       }
/* 309 */       this.tmp = new int[this.componentCount];
/*     */       
/* 311 */       this.data = new int[this.componentCount][]; byte b;
/* 312 */       for (b = 0; b < this.data.length; b++) {
/* 313 */         this.data[b] = new int[param1Int];
/*     */       }
/* 315 */       this.channelMask = new int[this.componentCount];
/* 316 */       for (b = 0; b < this.componentCount; b++) {
/* 317 */         this.channelMask[b] = (1 << param1ArrayOfint[b]) - 1;
/*     */       }
/*     */     }
/*     */     
/*     */     protected void accumulate(int param1Int, Scanline param1Scanline) {
/* 322 */       GenericRasterScanline genericRasterScanline = (GenericRasterScanline)param1Scanline;
/*     */       
/* 324 */       int i = (genericRasterScanline.data[0]).length;
/* 325 */       for (byte b = 0; b < this.componentCount; b++) {
/* 326 */         int[] arrayOfInt1 = this.data[b];
/* 327 */         int[] arrayOfInt2 = genericRasterScanline.data[b];
/*     */         
/* 329 */         for (byte b1 = 0; b1 < i; b1++) {
/* 330 */           arrayOfInt2[b1] = arrayOfInt2[b1] + param1Int * arrayOfInt1[b1];
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void clear() {
/* 336 */       for (byte b = 0; b < this.componentCount; b++) {
/* 337 */         int[] arrayOfInt = this.data[b];
/* 338 */         for (byte b1 = 0; b1 < arrayOfInt.length; b1++) {
/* 339 */           arrayOfInt[b1] = 0;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void fetch(int param1Int1, int param1Int2) {
/* 345 */       for (byte b = 0; b < this.componentCount; b++) {
/* 346 */         this.srcRaster.getSamples(param1Int1, param1Int2, this.length, 1, b, this.data[b]);
/* 347 */         if (null != this.inputFilter) {
/* 348 */           this.inputFilter.filter(param1Int1, param1Int2, b, this.data[b], this.length);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void filter(int[] param1ArrayOfint1, int[] param1ArrayOfint2, Weighttab[] param1ArrayOfWeighttab, Scanline param1Scanline) {
/* 354 */       GenericRasterScanline genericRasterScanline = (GenericRasterScanline)param1Scanline;
/* 355 */       int i = param1Scanline.length;
/*     */ 
/*     */       
/* 358 */       int[] arrayOfInt = this.tmp; int j;
/* 359 */       for (j = 0; j < this.componentCount; j++) {
/* 360 */         arrayOfInt[j] = 1 << param1ArrayOfint2[j] - 1;
/*     */       }
/* 362 */       j = (this.data[0]).length;
/*     */ 
/*     */ 
/*     */       
/* 366 */       int k = 0; byte b;
/* 367 */       for (b = 0; b < this.componentCount && !k; b++)
/* 368 */         k |= (param1ArrayOfint1[b] != 0) ? 1 : 0; 
/* 369 */       if (k != 0) {
/* 370 */         for (b = 0; b < this.componentCount; b++) {
/* 371 */           int[] arrayOfInt1 = this.data[b];
/* 372 */           int[] arrayOfInt2 = genericRasterScanline.data[b];
/* 373 */           int m = this.channelMask[b];
/* 374 */           for (byte b1 = 0; b1 < i; b1++) {
/* 375 */             Weighttab weighttab = param1ArrayOfWeighttab[b1];
/* 376 */             int n = weighttab.weights.length;
/*     */             
/* 378 */             int i1 = arrayOfInt[b]; int i2, i3;
/* 379 */             for (i2 = 0, i3 = weighttab.i0; i2 < n && i3 < j; i2++, i3++) {
/* 380 */               i1 += weighttab.weights[i2] * (arrayOfInt1[i3] >> param1ArrayOfint1[b]);
/*     */             }
/* 382 */             i2 = i1 >> param1ArrayOfint2[b];
/* 383 */             arrayOfInt2[b1] = (i2 < 0) ? 0 : ((i2 > m) ? m : i2);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 387 */         for (b = 0; b < this.componentCount; b++) {
/* 388 */           int[] arrayOfInt1 = this.data[b];
/* 389 */           int[] arrayOfInt2 = genericRasterScanline.data[b];
/*     */           
/* 391 */           for (byte b1 = 0; b1 < i; b1++) {
/* 392 */             Weighttab weighttab = param1ArrayOfWeighttab[b1];
/* 393 */             int m = weighttab.weights.length;
/*     */             
/* 395 */             int n = arrayOfInt[b]; byte b2; int i1;
/* 396 */             for (b2 = 0, i1 = weighttab.i0; b2 < m && i1 < j; b2++, i1++) {
/* 397 */               n += weighttab.weights[b2] * arrayOfInt1[i1];
/*     */             }
/* 399 */             arrayOfInt2[b1] = n >> param1ArrayOfint2[b];
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void shift(int[] param1ArrayOfint) {
/* 406 */       int[] arrayOfInt = this.tmp;
/* 407 */       for (byte b1 = 0; b1 < this.componentCount; b1++) {
/* 408 */         arrayOfInt[b1] = 1 << param1ArrayOfint[b1] - 1;
/*     */       }
/* 410 */       int[][] arrayOfInt1 = this.data;
/*     */       
/* 412 */       int i = (arrayOfInt1[0]).length;
/* 413 */       for (byte b2 = 0; b2 < this.componentCount; b2++) {
/* 414 */         int[] arrayOfInt2 = this.data[b2];
/* 415 */         int j = this.channelMask[b2];
/*     */         
/* 417 */         for (byte b = 0; b < i; b++) {
/* 418 */           int k = arrayOfInt2[b] + arrayOfInt[b2] >> param1ArrayOfint[b2];
/* 419 */           arrayOfInt2[b] = (k < 0) ? 0 : ((k > j) ? j : k);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void store(int param1Int1, int param1Int2) {
/* 426 */       int i = this.length;
/* 427 */       for (byte b = 0; b < this.componentCount; b++) {
/* 428 */         this.dstRaster.setSamples(param1Int1, param1Int2, i, 1, b, this.data[b]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static final class ByteBiLevelPackedScanline
/*     */     extends Scanline
/*     */   {
/*     */     private final Raster srcRaster;
/*     */     
/*     */     private final WritableRaster dstRaster;
/*     */     private final int[] data;
/*     */     
/*     */     protected ByteBiLevelPackedScanline(Raster param1Raster, WritableRaster param1WritableRaster, int param1Int) {
/* 443 */       super(param1Int);
/* 444 */       this.srcRaster = param1Raster;
/* 445 */       this.dstRaster = param1WritableRaster;
/*     */       
/* 447 */       this.data = new int[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     protected void accumulate(int param1Int, Scanline param1Scanline) {
/* 452 */       ByteBiLevelPackedScanline byteBiLevelPackedScanline = (ByteBiLevelPackedScanline)param1Scanline;
/*     */       
/* 454 */       int[] arrayOfInt1 = this.data;
/* 455 */       int[] arrayOfInt2 = byteBiLevelPackedScanline.data;
/*     */       
/* 457 */       for (byte b = 0; b < arrayOfInt2.length; b++) {
/* 458 */         arrayOfInt2[b] = arrayOfInt2[b] + param1Int * arrayOfInt1[b];
/*     */       }
/*     */     }
/*     */     
/*     */     protected void clear() {
/* 463 */       int[] arrayOfInt = this.data;
/* 464 */       for (byte b = 0; b < arrayOfInt.length; b++) {
/* 465 */         arrayOfInt[b] = 0;
/*     */       }
/*     */     }
/*     */     
/*     */     protected void fetch(int param1Int1, int param1Int2) {
/* 470 */       this.srcRaster.getPixels(param1Int1, param1Int2, this.length, 1, this.data);
/* 471 */       for (byte b = 0; b < this.length; b++) {
/* 472 */         if (this.data[b] != 0)
/* 473 */           this.data[b] = 255; 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void filter(int[] param1ArrayOfint1, int[] param1ArrayOfint2, Weighttab[] param1ArrayOfWeighttab, Scanline param1Scanline) {
/* 478 */       ByteBiLevelPackedScanline byteBiLevelPackedScanline = (ByteBiLevelPackedScanline)param1Scanline;
/* 479 */       int i = param1Scanline.length;
/*     */ 
/*     */       
/* 482 */       int j = 1 << param1ArrayOfint2[0] - 1;
/* 483 */       int[] arrayOfInt1 = this.data;
/* 484 */       int[] arrayOfInt2 = byteBiLevelPackedScanline.data;
/*     */ 
/*     */ 
/*     */       
/* 488 */       int k = param1ArrayOfint1[0];
/* 489 */       int m = param1ArrayOfint2[0];
/* 490 */       if (k != 0) {
/* 491 */         for (byte b1 = 0, b2 = 0; b2 < i; b2++) {
/* 492 */           Weighttab weighttab = param1ArrayOfWeighttab[b2];
/* 493 */           int n = weighttab.weights.length;
/*     */           
/* 495 */           int i1 = j; int i2, i3;
/* 496 */           for (i2 = 0, i3 = weighttab.i0; i2 < n && i3 < arrayOfInt1.length; i2++) {
/* 497 */             i1 += weighttab.weights[i2] * (arrayOfInt1[i3++] >> k);
/*     */           }
/*     */           
/* 500 */           i2 = i1 >> m;
/* 501 */           arrayOfInt2[b1++] = (i2 < 0) ? 0 : ((i2 > 255) ? 255 : i2);
/*     */         } 
/*     */       } else {
/* 504 */         for (byte b1 = 0, b2 = 0; b2 < i; b2++) {
/* 505 */           Weighttab weighttab = param1ArrayOfWeighttab[b2];
/* 506 */           int n = weighttab.weights.length;
/*     */           
/* 508 */           int i1 = j; byte b; int i2;
/* 509 */           for (b = 0, i2 = weighttab.i0; b < n && i2 < arrayOfInt1.length; b++) {
/* 510 */             i1 += weighttab.weights[b] * arrayOfInt1[i2++];
/*     */           }
/*     */           
/* 513 */           arrayOfInt2[b1++] = i1 >> m;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void shift(int[] param1ArrayOfint) {
/* 519 */       int i = param1ArrayOfint[0];
/* 520 */       int j = 1 << i - 1;
/*     */       
/* 522 */       int[] arrayOfInt = this.data;
/*     */       
/* 524 */       for (byte b = 0; b < arrayOfInt.length; b++) {
/* 525 */         int k = arrayOfInt[b] + j >> i;
/* 526 */         arrayOfInt[b] = (k < 0) ? 0 : ((k > 255) ? 255 : k);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void store(int param1Int1, int param1Int2) {
/* 532 */       this.dstRaster.setPixels(param1Int1, param1Int2, this.length, 1, this.data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Scanline(int paramInt) {
/* 540 */     this.length = paramInt;
/*     */   }
/*     */   
/*     */   protected final int getWidth() {
/* 544 */     return this.length;
/*     */   }
/*     */   
/*     */   protected abstract void clear();
/*     */   
/*     */   protected abstract void fetch(int paramInt1, int paramInt2);
/*     */   
/*     */   protected abstract void filter(int[] paramArrayOfint1, int[] paramArrayOfint2, Weighttab[] paramArrayOfWeighttab, Scanline paramScanline);
/*     */   
/*     */   protected abstract void accumulate(int paramInt, Scanline paramScanline);
/*     */   
/*     */   protected abstract void shift(int[] paramArrayOfint);
/*     */   
/*     */   protected abstract void store(int paramInt1, int paramInt2);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/Scanline.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */