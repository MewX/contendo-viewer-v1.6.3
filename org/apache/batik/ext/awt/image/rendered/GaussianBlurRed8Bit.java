/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ConvolveOp;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.Kernel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
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
/*     */ public class GaussianBlurRed8Bit
/*     */   extends AbstractRed
/*     */ {
/*     */   int xinset;
/*     */   int yinset;
/*     */   double stdDevX;
/*     */   double stdDevY;
/*     */   RenderingHints hints;
/*  49 */   ConvolveOp[] convOp = new ConvolveOp[2];
/*     */ 
/*     */ 
/*     */   
/*     */   int dX;
/*     */ 
/*     */ 
/*     */   
/*     */   int dY;
/*     */ 
/*     */ 
/*     */   
/*     */   public GaussianBlurRed8Bit(CachableRed src, double stdDev, RenderingHints rh) {
/*  62 */     this(src, stdDev, stdDev, rh);
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
/*     */   
/*     */   public GaussianBlurRed8Bit(CachableRed src, double stdDevX, double stdDevY, RenderingHints rh) {
/*  78 */     this.stdDevX = stdDevX;
/*  79 */     this.stdDevY = stdDevY;
/*  80 */     this.hints = rh;
/*     */     
/*  82 */     this.xinset = surroundPixels(stdDevX, rh);
/*  83 */     this.yinset = surroundPixels(stdDevY, rh);
/*     */     
/*  85 */     Rectangle myBounds = src.getBounds();
/*  86 */     myBounds.x += this.xinset;
/*  87 */     myBounds.y += this.yinset;
/*  88 */     myBounds.width -= 2 * this.xinset;
/*  89 */     myBounds.height -= 2 * this.yinset;
/*  90 */     if (myBounds.width <= 0 || myBounds.height <= 0) {
/*     */       
/*  92 */       myBounds.width = 0;
/*  93 */       myBounds.height = 0;
/*     */     } 
/*     */     
/*  96 */     ColorModel cm = fixColorModel(src);
/*  97 */     SampleModel sm = src.getSampleModel();
/*  98 */     int tw = sm.getWidth();
/*  99 */     int th = sm.getHeight();
/* 100 */     if (tw > myBounds.width) tw = myBounds.width; 
/* 101 */     if (th > myBounds.height) th = myBounds.height; 
/* 102 */     sm = cm.createCompatibleSampleModel(tw, th);
/*     */     
/* 104 */     init(src, myBounds, cm, sm, src.getTileGridXOffset() + this.xinset, src.getTileGridYOffset() + this.yinset, (Map)null);
/*     */ 
/*     */ 
/*     */     
/* 108 */     boolean highQuality = (this.hints != null && RenderingHints.VALUE_RENDER_QUALITY.equals(this.hints.get(RenderingHints.KEY_RENDERING)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (this.xinset != 0 && (stdDevX < 2.0D || highQuality)) {
/* 114 */       this.convOp[0] = new ConvolveOp(makeQualityKernelX(this.xinset * 2 + 1));
/*     */     } else {
/* 116 */       this.dX = (int)Math.floor(DSQRT2PI * stdDevX + 0.5D);
/*     */     } 
/* 118 */     if (this.yinset != 0 && (stdDevY < 2.0D || highQuality)) {
/* 119 */       this.convOp[1] = new ConvolveOp(makeQualityKernelY(this.yinset * 2 + 1));
/*     */     } else {
/* 121 */       this.dY = (int)Math.floor(DSQRT2PI * stdDevY + 0.5D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 127 */   static final float SQRT2PI = (float)Math.sqrt(6.283185307179586D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   static final float DSQRT2PI = SQRT2PI * 3.0F / 4.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final float precision = 0.499F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int surroundPixels(double stdDev) {
/* 144 */     return surroundPixels(stdDev, (RenderingHints)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int surroundPixels(double stdDev, RenderingHints hints) {
/* 153 */     boolean highQuality = (hints != null && RenderingHints.VALUE_RENDER_QUALITY.equals(hints.get(RenderingHints.KEY_RENDERING)));
/*     */ 
/*     */ 
/*     */     
/* 157 */     if (stdDev < 2.0D || highQuality) {
/*     */       
/* 159 */       float areaSum = (float)(0.5D / stdDev * SQRT2PI);
/* 160 */       int i = 0;
/* 161 */       while (areaSum < 0.499F) {
/* 162 */         areaSum += (float)(Math.pow(Math.E, (-i * i) / 2.0D * stdDev * stdDev) / stdDev * SQRT2PI);
/*     */         
/* 164 */         i++;
/*     */       } 
/*     */       
/* 167 */       return i;
/*     */     } 
/*     */ 
/*     */     
/* 171 */     int diam = (int)Math.floor(DSQRT2PI * stdDev + 0.5D);
/* 172 */     if (diam % 2 == 0) {
/* 173 */       return diam - 1 + diam / 2;
/*     */     }
/* 175 */     return diam - 2 + diam / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] computeQualityKernelData(int len, double stdDev) {
/* 185 */     float[] kernelData = new float[len];
/*     */     
/* 187 */     int mid = len / 2;
/* 188 */     float sum = 0.0F; int i;
/* 189 */     for (i = 0; i < len; i++) {
/* 190 */       kernelData[i] = (float)(Math.pow(Math.E, (-(i - mid) * (i - mid)) / 2.0D * stdDev * stdDev) / SQRT2PI * stdDev);
/*     */ 
/*     */       
/* 193 */       sum += kernelData[i];
/*     */     } 
/*     */ 
/*     */     
/* 197 */     for (i = 0; i < len; i++) {
/* 198 */       kernelData[i] = kernelData[i] / sum;
/*     */     }
/* 200 */     return kernelData;
/*     */   }
/*     */   
/*     */   private Kernel makeQualityKernelX(int len) {
/* 204 */     return new Kernel(len, 1, computeQualityKernelData(len, this.stdDevX));
/*     */   }
/*     */   
/*     */   private Kernel makeQualityKernelY(int len) {
/* 208 */     return new Kernel(1, len, computeQualityKernelData(len, this.stdDevY));
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/*     */     int skipX;
/* 213 */     CachableRed src = getSources().get(0);
/*     */     
/* 215 */     Rectangle r = wr.getBounds();
/* 216 */     r.x -= this.xinset;
/* 217 */     r.y -= this.yinset;
/* 218 */     r.width += 2 * this.xinset;
/* 219 */     r.height += 2 * this.yinset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     ColorModel srcCM = src.getColorModel();
/*     */     
/* 226 */     WritableRaster tmpR1 = null, tmpR2 = null;
/*     */     
/* 228 */     tmpR1 = srcCM.createCompatibleWritableRaster(r.width, r.height);
/*     */ 
/*     */     
/* 231 */     WritableRaster fill = tmpR1.createWritableTranslatedChild(r.x, r.y);
/* 232 */     src.copyData(fill);
/*     */     
/* 234 */     if (srcCM.hasAlpha() && !srcCM.isAlphaPremultiplied()) {
/* 235 */       GraphicsUtil.coerceData(tmpR1, srcCM, true);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     if (this.xinset == 0) {
/* 246 */       skipX = 0;
/* 247 */     } else if (this.convOp[0] != null) {
/* 248 */       tmpR2 = getColorModel().createCompatibleWritableRaster(r.width, r.height);
/*     */       
/* 250 */       tmpR2 = this.convOp[0].filter(tmpR1, tmpR2);
/* 251 */       skipX = this.convOp[0].getKernel().getXOrigin();
/*     */ 
/*     */       
/* 254 */       WritableRaster tmp = tmpR1;
/* 255 */       tmpR1 = tmpR2;
/* 256 */       tmpR2 = tmp;
/*     */     }
/* 258 */     else if ((this.dX & 0x1) == 0) {
/* 259 */       tmpR1 = boxFilterH(tmpR1, tmpR1, 0, 0, this.dX, this.dX / 2);
/* 260 */       tmpR1 = boxFilterH(tmpR1, tmpR1, this.dX / 2, 0, this.dX, this.dX / 2 - 1);
/* 261 */       tmpR1 = boxFilterH(tmpR1, tmpR1, this.dX - 1, 0, this.dX + 1, this.dX / 2);
/* 262 */       skipX = this.dX - 1 + this.dX / 2;
/*     */     } else {
/* 264 */       tmpR1 = boxFilterH(tmpR1, tmpR1, 0, 0, this.dX, this.dX / 2);
/* 265 */       tmpR1 = boxFilterH(tmpR1, tmpR1, this.dX / 2, 0, this.dX, this.dX / 2);
/* 266 */       tmpR1 = boxFilterH(tmpR1, tmpR1, this.dX - 2, 0, this.dX, this.dX / 2);
/* 267 */       skipX = this.dX - 2 + this.dX / 2;
/*     */     } 
/*     */ 
/*     */     
/* 271 */     if (this.yinset == 0) {
/* 272 */       tmpR2 = tmpR1;
/* 273 */     } else if (this.convOp[1] != null) {
/* 274 */       if (tmpR2 == null) {
/* 275 */         tmpR2 = getColorModel().createCompatibleWritableRaster(r.width, r.height);
/*     */       }
/*     */       
/* 278 */       tmpR2 = this.convOp[1].filter(tmpR1, tmpR2);
/*     */     } else {
/* 280 */       if ((this.dY & 0x1) == 0) {
/* 281 */         tmpR1 = boxFilterV(tmpR1, tmpR1, skipX, 0, this.dY, this.dY / 2);
/* 282 */         tmpR1 = boxFilterV(tmpR1, tmpR1, skipX, this.dY / 2, this.dY, this.dY / 2 - 1);
/* 283 */         tmpR1 = boxFilterV(tmpR1, tmpR1, skipX, this.dY - 1, this.dY + 1, this.dY / 2);
/*     */       } else {
/*     */         
/* 286 */         tmpR1 = boxFilterV(tmpR1, tmpR1, skipX, 0, this.dY, this.dY / 2);
/* 287 */         tmpR1 = boxFilterV(tmpR1, tmpR1, skipX, this.dY / 2, this.dY, this.dY / 2);
/* 288 */         tmpR1 = boxFilterV(tmpR1, tmpR1, skipX, this.dY - 2, this.dY, this.dY / 2);
/*     */       } 
/* 290 */       tmpR2 = tmpR1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     tmpR2 = tmpR2.createWritableTranslatedChild(r.x, r.y);
/* 302 */     GraphicsUtil.copyData(tmpR2, wr);
/*     */     
/* 304 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WritableRaster boxFilterH(Raster src, WritableRaster dest, int skipX, int skipY, int boxSz, int loc) {
/* 311 */     int w = src.getWidth();
/* 312 */     int h = src.getHeight();
/*     */ 
/*     */     
/* 315 */     if (w < 2 * skipX + boxSz) return dest; 
/* 316 */     if (h < 2 * skipY) return dest;
/*     */     
/* 318 */     SinglePixelPackedSampleModel srcSPPSM = (SinglePixelPackedSampleModel)src.getSampleModel();
/*     */ 
/*     */     
/* 321 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dest.getSampleModel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     int srcScanStride = srcSPPSM.getScanlineStride();
/* 327 */     int dstScanStride = dstSPPSM.getScanlineStride();
/*     */ 
/*     */     
/* 330 */     DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/* 331 */     DataBufferInt dstDB = (DataBufferInt)dest.getDataBuffer();
/*     */ 
/*     */     
/* 334 */     int srcOff = srcDB.getOffset() + srcSPPSM.getOffset(src.getMinX() - src.getSampleModelTranslateX(), src.getMinY() - src.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 339 */     int dstOff = dstDB.getOffset() + dstSPPSM.getOffset(dest.getMinX() - dest.getSampleModelTranslateX(), dest.getMinY() - dest.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 346 */     int[] srcPixels = srcDB.getBankData()[0];
/* 347 */     int[] destPixels = dstDB.getBankData()[0];
/*     */     
/* 349 */     int[] buffer = new int[boxSz];
/*     */ 
/*     */ 
/*     */     
/* 353 */     int scale = 16777216 / boxSz;
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
/* 365 */     for (int y = skipY; y < h - skipY; y++) {
/* 366 */       int sp = srcOff + y * srcScanStride;
/* 367 */       int dp = dstOff + y * dstScanStride;
/* 368 */       int rowEnd = sp + w - skipX;
/*     */       
/* 370 */       int k = 0;
/* 371 */       int sumA = 0;
/* 372 */       int sumR = 0;
/* 373 */       int sumG = 0;
/* 374 */       int sumB = 0;
/*     */       
/* 376 */       sp += skipX;
/* 377 */       int end = sp + boxSz;
/*     */       
/* 379 */       while (sp < end) {
/* 380 */         int curr = buffer[k] = srcPixels[sp];
/* 381 */         sumA += curr >>> 24;
/* 382 */         sumR += curr >> 16 & 0xFF;
/* 383 */         sumG += curr >> 8 & 0xFF;
/* 384 */         sumB += curr & 0xFF;
/* 385 */         k++;
/* 386 */         sp++;
/*     */       } 
/*     */       
/* 389 */       dp += skipX + loc;
/* 390 */       int prev = destPixels[dp] = sumA * scale & 0xFF000000 | (sumR * scale & 0xFF000000) >>> 8 | (sumG * scale & 0xFF000000) >>> 16 | (sumB * scale & 0xFF000000) >>> 24;
/*     */ 
/*     */ 
/*     */       
/* 394 */       dp++;
/* 395 */       k = 0;
/* 396 */       while (sp < rowEnd) {
/* 397 */         int curr = buffer[k];
/* 398 */         if (curr == srcPixels[sp]) {
/* 399 */           destPixels[dp] = prev;
/*     */         } else {
/* 401 */           sumA -= curr >>> 24;
/* 402 */           sumR -= curr >> 16 & 0xFF;
/* 403 */           sumG -= curr >> 8 & 0xFF;
/* 404 */           sumB -= curr & 0xFF;
/*     */           
/* 406 */           curr = buffer[k] = srcPixels[sp];
/*     */           
/* 408 */           sumA += curr >>> 24;
/* 409 */           sumR += curr >> 16 & 0xFF;
/* 410 */           sumG += curr >> 8 & 0xFF;
/* 411 */           sumB += curr & 0xFF;
/* 412 */           prev = destPixels[dp] = sumA * scale & 0xFF000000 | (sumR * scale & 0xFF000000) >>> 8 | (sumG * scale & 0xFF000000) >>> 16 | (sumB * scale & 0xFF000000) >>> 24;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 417 */         k = (k + 1) % boxSz;
/* 418 */         sp++;
/* 419 */         dp++;
/*     */       } 
/*     */     } 
/* 422 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WritableRaster boxFilterV(Raster src, WritableRaster dest, int skipX, int skipY, int boxSz, int loc) {
/* 429 */     int w = src.getWidth();
/* 430 */     int h = src.getHeight();
/*     */ 
/*     */     
/* 433 */     if (w < 2 * skipX) return dest; 
/* 434 */     if (h < 2 * skipY + boxSz) return dest;
/*     */     
/* 436 */     SinglePixelPackedSampleModel srcSPPSM = (SinglePixelPackedSampleModel)src.getSampleModel();
/*     */ 
/*     */     
/* 439 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dest.getSampleModel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 444 */     int srcScanStride = srcSPPSM.getScanlineStride();
/* 445 */     int dstScanStride = dstSPPSM.getScanlineStride();
/*     */ 
/*     */     
/* 448 */     DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/* 449 */     DataBufferInt dstDB = (DataBufferInt)dest.getDataBuffer();
/*     */ 
/*     */     
/* 452 */     int srcOff = srcDB.getOffset() + srcSPPSM.getOffset(src.getMinX() - src.getSampleModelTranslateX(), src.getMinY() - src.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 457 */     int dstOff = dstDB.getOffset() + dstSPPSM.getOffset(dest.getMinX() - dest.getSampleModelTranslateX(), dest.getMinY() - dest.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 465 */     int[] srcPixels = srcDB.getBankData()[0];
/* 466 */     int[] destPixels = dstDB.getBankData()[0];
/*     */     
/* 468 */     int[] buffer = new int[boxSz];
/*     */ 
/*     */ 
/*     */     
/* 472 */     int scale = 16777216 / boxSz;
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
/* 484 */     for (int x = skipX; x < w - skipX; x++) {
/* 485 */       int sp = srcOff + x;
/* 486 */       int dp = dstOff + x;
/* 487 */       int colEnd = sp + (h - skipY) * srcScanStride;
/*     */       
/* 489 */       int k = 0;
/* 490 */       int sumA = 0;
/* 491 */       int sumR = 0;
/* 492 */       int sumG = 0;
/* 493 */       int sumB = 0;
/*     */       
/* 495 */       sp += skipY * srcScanStride;
/* 496 */       int end = sp + boxSz * srcScanStride;
/*     */       
/* 498 */       while (sp < end) {
/* 499 */         int curr = buffer[k] = srcPixels[sp];
/* 500 */         sumA += curr >>> 24;
/* 501 */         sumR += curr >> 16 & 0xFF;
/* 502 */         sumG += curr >> 8 & 0xFF;
/* 503 */         sumB += curr & 0xFF;
/* 504 */         k++;
/* 505 */         sp += srcScanStride;
/*     */       } 
/*     */ 
/*     */       
/* 509 */       dp += (skipY + loc) * dstScanStride;
/* 510 */       int prev = destPixels[dp] = sumA * scale & 0xFF000000 | (sumR * scale & 0xFF000000) >>> 8 | (sumG * scale & 0xFF000000) >>> 16 | (sumB * scale & 0xFF000000) >>> 24;
/*     */ 
/*     */ 
/*     */       
/* 514 */       dp += dstScanStride;
/* 515 */       k = 0;
/* 516 */       while (sp < colEnd) {
/* 517 */         int curr = buffer[k];
/* 518 */         if (curr == srcPixels[sp]) {
/* 519 */           destPixels[dp] = prev;
/*     */         } else {
/* 521 */           sumA -= curr >>> 24;
/* 522 */           sumR -= curr >> 16 & 0xFF;
/* 523 */           sumG -= curr >> 8 & 0xFF;
/* 524 */           sumB -= curr & 0xFF;
/*     */           
/* 526 */           curr = buffer[k] = srcPixels[sp];
/*     */           
/* 528 */           sumA += curr >>> 24;
/* 529 */           sumR += curr >> 16 & 0xFF;
/* 530 */           sumG += curr >> 8 & 0xFF;
/* 531 */           sumB += curr & 0xFF;
/* 532 */           prev = destPixels[dp] = sumA * scale & 0xFF000000 | (sumR * scale & 0xFF000000) >>> 8 | (sumG * scale & 0xFF000000) >>> 16 | (sumB * scale & 0xFF000000) >>> 24;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 537 */         k = (k + 1) % boxSz;
/* 538 */         sp += srcScanStride;
/* 539 */         dp += dstScanStride;
/*     */       } 
/*     */     } 
/* 542 */     return dest;
/*     */   }
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/*     */     ColorSpace cs;
/* 546 */     ColorModel cm = src.getColorModel();
/*     */     
/* 548 */     int b = src.getSampleModel().getNumBands();
/* 549 */     int[] masks = new int[4];
/* 550 */     switch (b) {
/*     */       case 1:
/* 552 */         masks[0] = 255;
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
/* 573 */         cs = cm.getColorSpace();
/* 574 */         return new DirectColorModel(cs, 8 * b, masks[0], masks[1], masks[2], masks[3], true, 3);case 2: masks[0] = 255; masks[3] = 65280; cs = cm.getColorSpace(); return new DirectColorModel(cs, 8 * b, masks[0], masks[1], masks[2], masks[3], true, 3);case 3: masks[0] = 16711680; masks[1] = 65280; masks[2] = 255; cs = cm.getColorSpace(); return new DirectColorModel(cs, 8 * b, masks[0], masks[1], masks[2], masks[3], true, 3);case 4: masks[0] = 16711680; masks[1] = 65280; masks[2] = 255; masks[3] = -16777216; cs = cm.getColorSpace(); return new DirectColorModel(cs, 8 * b, masks[0], masks[1], masks[2], masks[3], true, 3);
/*     */     } 
/*     */     throw new IllegalArgumentException("GaussianBlurRed8Bit only supports one to four band images");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/GaussianBlurRed8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */