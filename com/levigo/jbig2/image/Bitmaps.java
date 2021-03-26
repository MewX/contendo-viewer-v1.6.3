/*     */ package com.levigo.jbig2.image;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.JBIG2ReadParam;
/*     */ import com.levigo.jbig2.util.CombinationOperator;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import javax.imageio.ImageReadParam;
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
/*     */ public class Bitmaps
/*     */ {
/*     */   public static WritableRaster asRaster(Bitmap paramBitmap) {
/*  38 */     return asRaster(paramBitmap, FilterType.Gaussian);
/*     */   }
/*     */   
/*     */   public static WritableRaster asRaster(Bitmap paramBitmap, FilterType paramFilterType) {
/*  42 */     if (paramBitmap == null) {
/*  43 */       throw new IllegalArgumentException("bitmap must not be null");
/*     */     }
/*  45 */     JBIG2ReadParam jBIG2ReadParam = new JBIG2ReadParam(1, 1, 0, 0, new Rectangle(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Dimension(paramBitmap.getWidth(), paramBitmap.getHeight()));
/*     */ 
/*     */     
/*  48 */     return asRaster(paramBitmap, (ImageReadParam)jBIG2ReadParam, paramFilterType);
/*     */   }
/*     */   public static WritableRaster asRaster(Bitmap paramBitmap, ImageReadParam paramImageReadParam, FilterType paramFilterType) {
/*     */     double d1, d2;
/*  52 */     if (paramBitmap == null) {
/*  53 */       throw new IllegalArgumentException("bitmap must not be null");
/*     */     }
/*  55 */     if (paramImageReadParam == null) {
/*  56 */       throw new IllegalArgumentException("param must not be null");
/*     */     }
/*  58 */     Dimension dimension = paramImageReadParam.getSourceRenderSize();
/*     */ 
/*     */ 
/*     */     
/*  62 */     if (dimension != null) {
/*  63 */       d1 = dimension.getWidth() / paramBitmap.getWidth();
/*  64 */       d2 = dimension.getHeight() / paramBitmap.getHeight();
/*     */     } else {
/*  66 */       d1 = d2 = 1.0D;
/*     */     } 
/*     */     
/*  69 */     Rectangle rectangle = paramImageReadParam.getSourceRegion();
/*  70 */     if (rectangle != null && !paramBitmap.getBounds().equals(rectangle)) {
/*     */       
/*  72 */       rectangle = paramBitmap.getBounds().intersection(rectangle);
/*     */ 
/*     */       
/*  75 */       paramBitmap = extract(rectangle, paramBitmap);
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
/*     */ 
/*     */     
/*  88 */     boolean bool1 = (d1 != 1.0D || d2 != 1.0D) ? true : false;
/*     */     
/*  90 */     boolean bool2 = (paramImageReadParam.getSourceXSubsampling() != 1) ? true : false;
/*  91 */     boolean bool3 = (paramImageReadParam.getSourceYSubsampling() != 1) ? true : false;
/*     */     
/*  93 */     if (bool2 && bool3) {
/*     */       
/*  95 */       if (bool1) {
/*  96 */         d1 /= paramImageReadParam.getSourceXSubsampling();
/*  97 */         d2 /= paramImageReadParam.getSourceYSubsampling();
/*     */       } else {
/*  99 */         paramBitmap = subsample(paramBitmap, paramImageReadParam);
/*     */       } 
/*     */     } else {
/* 102 */       if (bool2)
/*     */       {
/* 104 */         if (bool1) {
/* 105 */           d1 /= paramImageReadParam.getSourceXSubsampling();
/*     */         } else {
/* 107 */           paramBitmap = subsampleX(paramBitmap, paramImageReadParam.getSourceXSubsampling(), paramImageReadParam.getSubsamplingXOffset());
/*     */         } 
/*     */       }
/*     */       
/* 111 */       if (bool3)
/*     */       {
/* 113 */         if (bool1) {
/* 114 */           d2 /= paramImageReadParam.getSourceYSubsampling();
/*     */         } else {
/* 116 */           paramBitmap = subsampleY(paramBitmap, paramImageReadParam.getSourceYSubsampling(), paramImageReadParam.getSubsamplingYOffset());
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 121 */     return buildRaster(paramBitmap, paramFilterType, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   private static WritableRaster buildRaster(Bitmap paramBitmap, FilterType paramFilterType, double paramDouble1, double paramDouble2) {
/* 126 */     Rectangle rectangle = new Rectangle(0, 0, (int)Math.round(paramBitmap.getWidth() * paramDouble1), (int)Math.round(paramBitmap.getHeight() * paramDouble2));
/*     */ 
/*     */ 
/*     */     
/* 130 */     WritableRaster writableRaster = WritableRaster.createInterleavedRaster(0, rectangle.width, rectangle.height, 1, new Point());
/*     */ 
/*     */     
/* 133 */     if (paramDouble1 != 1.0D || paramDouble2 != 1.0D) {
/*     */       
/* 135 */       Resizer resizer = new Resizer(paramDouble1, paramDouble2);
/* 136 */       Filter filter = Filter.byType(paramFilterType);
/* 137 */       resizer.resize(paramBitmap, paramBitmap.getBounds(), writableRaster, rectangle, filter, filter);
/*     */     } else {
/*     */       
/* 140 */       byte b1 = 0;
/* 141 */       for (byte b2 = 0; b2 < paramBitmap.getHeight(); b2++) {
/* 142 */         for (byte b = 0; b < paramBitmap.getWidth(); b1++) {
/* 143 */           int i = (paramBitmap.getByte(b1) ^ 0xFFFFFFFF) & 0xFF;
/* 144 */           byte b3 = (paramBitmap.getWidth() - b > 8) ? 8 : (paramBitmap.getWidth() - b);
/* 145 */           int j = 7 - b3;
/* 146 */           for (byte b4 = 7; b4 > j; b4--, b++) {
/* 147 */             writableRaster.setSample(b, b2, 0, i >> b4 & 0x1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     return writableRaster;
/*     */   }
/*     */   
/*     */   public static BufferedImage asBufferedImage(Bitmap paramBitmap) {
/* 157 */     return asBufferedImage(paramBitmap, FilterType.Gaussian);
/*     */   }
/*     */   
/*     */   public static BufferedImage asBufferedImage(Bitmap paramBitmap, FilterType paramFilterType) {
/* 161 */     if (paramBitmap == null) {
/* 162 */       throw new IllegalArgumentException("bitmap must not be null");
/*     */     }
/* 164 */     JBIG2ReadParam jBIG2ReadParam = new JBIG2ReadParam(1, 1, 0, 0, new Rectangle(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), new Dimension(paramBitmap.getWidth(), paramBitmap.getHeight()));
/*     */ 
/*     */     
/* 167 */     return asBufferedImage(paramBitmap, (ImageReadParam)jBIG2ReadParam, paramFilterType);
/*     */   }
/*     */   public static BufferedImage asBufferedImage(Bitmap paramBitmap, ImageReadParam paramImageReadParam, FilterType paramFilterType) {
/*     */     double d1, d2;
/* 171 */     if (paramBitmap == null) {
/* 172 */       throw new IllegalArgumentException("bitmap must not be null");
/*     */     }
/* 174 */     if (paramImageReadParam == null) {
/* 175 */       throw new IllegalArgumentException("param must not be null");
/*     */     }
/* 177 */     WritableRaster writableRaster = asRaster(paramBitmap, paramImageReadParam, paramFilterType);
/*     */     
/* 179 */     Dimension dimension = paramImageReadParam.getSourceRenderSize();
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (dimension != null) {
/* 184 */       d1 = dimension.getWidth() / paramBitmap.getWidth();
/* 185 */       d2 = dimension.getHeight() / paramBitmap.getHeight();
/*     */     } else {
/* 187 */       d1 = d2 = 1.0D;
/*     */     } 
/*     */     
/* 190 */     IndexColorModel indexColorModel = null;
/* 191 */     boolean bool = (d1 != 1.0D || d2 != 1.0D) ? true : false;
/* 192 */     if (bool) {
/*     */ 
/*     */ 
/*     */       
/* 196 */       byte[] arrayOfByte = new byte[256]; char c; byte b;
/* 197 */       for (c = 'ÿ', b = 0; c >= '\000'; c--, b++) {
/* 198 */         arrayOfByte[c] = (byte)(255 - b * 255 / 255);
/*     */       }
/* 200 */       indexColorModel = new IndexColorModel(8, 256, arrayOfByte, arrayOfByte, arrayOfByte);
/*     */     } else {
/*     */       
/* 203 */       indexColorModel = new IndexColorModel(8, 2, new byte[] { 0, -1 }, new byte[] { 0, -1 }, new byte[] { 0, -1 });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     return new BufferedImage(indexColorModel, writableRaster, false, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bitmap extract(Rectangle paramRectangle, Bitmap paramBitmap) {
/* 223 */     Bitmap bitmap = new Bitmap(paramRectangle.width, paramRectangle.height);
/*     */     
/* 225 */     int i = paramRectangle.x & 0x7;
/* 226 */     int j = 8 - i;
/* 227 */     int k = 0;
/*     */     
/* 229 */     int m = 8 - bitmap.getWidth() & 0x7;
/* 230 */     int n = paramBitmap.getByteIndex(paramRectangle.x, paramRectangle.y);
/* 231 */     int i1 = paramBitmap.getByteIndex(paramRectangle.x + paramRectangle.width - 1, paramRectangle.y);
/* 232 */     boolean bool = (bitmap.getRowStride() == i1 + 1 - n) ? true : false;
/*     */     
/* 234 */     for (int i2 = paramRectangle.y; i2 < paramRectangle.getMaxY(); i2++) {
/* 235 */       int i3 = n;
/* 236 */       int i4 = k;
/*     */       
/* 238 */       if (n == i1) {
/* 239 */         byte b = (byte)(paramBitmap.getByte(i3) << i);
/* 240 */         bitmap.setByte(i4, unpad(m, b));
/* 241 */       } else if (i == 0) {
/* 242 */         for (int i5 = n; i5 <= i1; i5++) {
/* 243 */           byte b = paramBitmap.getByte(i3++);
/*     */           
/* 245 */           if (i5 == i1 && bool) {
/* 246 */             b = unpad(m, b);
/*     */           }
/*     */           
/* 249 */           bitmap.setByte(i4++, b);
/*     */         } 
/*     */       } else {
/* 252 */         copyLine(paramBitmap, bitmap, i, j, m, n, i1, bool, i3, i4);
/*     */       } 
/*     */       
/* 255 */       n += paramBitmap.getRowStride();
/* 256 */       i1 += paramBitmap.getRowStride();
/* 257 */       k += bitmap.getRowStride();
/*     */     } 
/*     */     
/* 260 */     return bitmap;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void copyLine(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, int paramInt6, int paramInt7) {
/* 265 */     for (int i = paramInt4; i < paramInt5; i++) {
/*     */       
/* 267 */       if (paramInt6 + 1 < (paramBitmap1.getByteArray()).length) {
/* 268 */         boolean bool = (i + 1 == paramInt5) ? true : false;
/* 269 */         byte b = (byte)(paramBitmap1.getByte(paramInt6++) << paramInt1 | (paramBitmap1.getByte(paramInt6) & 0xFF) >>> paramInt2);
/*     */         
/* 271 */         if (bool && !paramBoolean) {
/* 272 */           b = unpad(paramInt3, b);
/*     */         }
/*     */         
/* 275 */         paramBitmap2.setByte(paramInt7++, b);
/*     */         
/* 277 */         if (bool && paramBoolean) {
/* 278 */           b = unpad(paramInt3, (byte)((paramBitmap1.getByte(paramInt6) & 0xFF) << paramInt1));
/* 279 */           paramBitmap2.setByte(paramInt7, b);
/*     */         } 
/*     */       } else {
/*     */         
/* 283 */         byte b = (byte)(paramBitmap1.getByte(paramInt6++) << paramInt1 & 0xFF);
/* 284 */         paramBitmap2.setByte(paramInt7++, b);
/*     */       } 
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
/*     */   private static byte unpad(int paramInt, byte paramByte) {
/* 297 */     return (byte)(paramByte >> paramInt << paramInt);
/*     */   }
/*     */   
/*     */   public static Bitmap subsample(Bitmap paramBitmap, ImageReadParam paramImageReadParam) {
/* 301 */     if (paramBitmap == null) {
/* 302 */       throw new IllegalArgumentException("src must not be null");
/*     */     }
/* 304 */     if (paramImageReadParam == null) {
/* 305 */       throw new IllegalArgumentException("param must not be null");
/*     */     }
/* 307 */     int i = paramImageReadParam.getSourceXSubsampling();
/* 308 */     int j = paramImageReadParam.getSourceYSubsampling();
/* 309 */     int k = paramImageReadParam.getSubsamplingXOffset();
/* 310 */     int m = paramImageReadParam.getSubsamplingYOffset();
/*     */     
/* 312 */     int n = (paramBitmap.getWidth() - k) / i;
/* 313 */     int i1 = (paramBitmap.getHeight() - m) / j;
/*     */     
/* 315 */     Bitmap bitmap = new Bitmap(n, i1);
/*     */     int i2;
/* 317 */     for (byte b = 0; b < bitmap.getHeight(); b++, i2 += j) {
/* 318 */       int i3; for (byte b1 = 0; b1 < bitmap.getWidth(); b1++, i3 += i) {
/* 319 */         byte b2 = paramBitmap.getPixel(i3, i2);
/* 320 */         if (b2 != 0) {
/* 321 */           bitmap.setPixel(b1, b, b2);
/*     */         }
/*     */       } 
/*     */     } 
/* 325 */     return bitmap;
/*     */   }
/*     */   
/*     */   public static Bitmap subsampleX(Bitmap paramBitmap, int paramInt1, int paramInt2) {
/* 329 */     if (paramBitmap == null) {
/* 330 */       throw new IllegalArgumentException("src must not be null");
/*     */     }
/* 332 */     int i = (paramBitmap.getWidth() - paramInt2) / paramInt1;
/* 333 */     Bitmap bitmap = new Bitmap(paramBitmap.getWidth(), i);
/*     */     
/* 335 */     for (byte b = 0; b < bitmap.getHeight(); b++) {
/* 336 */       int j; for (byte b1 = 0; b1 < bitmap.getWidth(); b1++, j += paramInt1) {
/* 337 */         byte b2 = paramBitmap.getPixel(j, b);
/* 338 */         if (b2 != 0) {
/* 339 */           bitmap.setPixel(b1, b, b2);
/*     */         }
/*     */       } 
/*     */     } 
/* 343 */     return bitmap;
/*     */   }
/*     */   
/*     */   public static Bitmap subsampleY(Bitmap paramBitmap, int paramInt1, int paramInt2) {
/* 347 */     if (paramBitmap == null) {
/* 348 */       throw new IllegalArgumentException("src must not be null");
/*     */     }
/* 350 */     int i = (paramBitmap.getWidth() - paramInt2) / paramInt1;
/* 351 */     Bitmap bitmap = new Bitmap(i, paramBitmap.getHeight());
/*     */     int j;
/* 353 */     for (byte b = 0; b < bitmap.getHeight(); b++, j += paramInt1) {
/* 354 */       for (byte b1 = 0; b1 < bitmap.getWidth(); b1++) {
/* 355 */         byte b2 = paramBitmap.getPixel(b1, j);
/* 356 */         if (b2 != 0) {
/* 357 */           bitmap.setPixel(b1, b, b2);
/*     */         }
/*     */       } 
/*     */     } 
/* 361 */     return bitmap;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte combineBytes(byte paramByte1, byte paramByte2, CombinationOperator paramCombinationOperator) {
/* 380 */     switch (paramCombinationOperator) {
/*     */       case OR:
/* 382 */         return (byte)(paramByte2 | paramByte1);
/*     */       case AND:
/* 384 */         return (byte)(paramByte2 & paramByte1);
/*     */       case XOR:
/* 386 */         return (byte)(paramByte2 ^ paramByte1);
/*     */       case XNOR:
/* 388 */         return (byte)(paramByte1 ^ paramByte2 ^ 0xFFFFFFFF);
/*     */     } 
/*     */ 
/*     */     
/* 392 */     return paramByte2;
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
/*     */ 
/*     */   
/*     */   public static void blit(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2, CombinationOperator paramCombinationOperator) {
/* 410 */     int i = 0;
/* 411 */     int j = 0;
/* 412 */     int k = paramBitmap1.getRowStride() - 1;
/*     */ 
/*     */     
/* 415 */     if (paramInt1 < 0) {
/* 416 */       j = -paramInt1;
/* 417 */       paramInt1 = 0;
/* 418 */     } else if (paramInt1 + paramBitmap1.getWidth() > paramBitmap2.getWidth()) {
/* 419 */       k -= paramBitmap1.getWidth() + paramInt1 - paramBitmap2.getWidth();
/*     */     } 
/*     */     
/* 422 */     if (paramInt2 < 0) {
/* 423 */       i = -paramInt2;
/* 424 */       paramInt2 = 0;
/* 425 */       j += paramBitmap1.getRowStride();
/* 426 */       k += paramBitmap1.getRowStride();
/* 427 */     } else if (paramInt2 + paramBitmap1.getHeight() > paramBitmap2.getHeight()) {
/* 428 */       i = paramBitmap1.getHeight() + paramInt2 - paramBitmap2.getHeight();
/*     */     } 
/*     */     
/* 431 */     int m = paramInt1 & 0x7;
/* 432 */     int n = 8 - m;
/*     */     
/* 434 */     int i1 = paramBitmap1.getWidth() & 0x7;
/* 435 */     int i2 = n - i1;
/*     */     
/* 437 */     boolean bool1 = ((n & 0x7) != 0) ? true : false;
/* 438 */     boolean bool2 = (paramBitmap1.getWidth() <= (k - j << 3) + n) ? true : false;
/*     */     
/* 440 */     int i3 = paramBitmap2.getByteIndex(paramInt1, paramInt2);
/*     */     
/* 442 */     int i4 = Math.min(paramBitmap1.getHeight(), i + paramBitmap2.getHeight());
/*     */     
/* 444 */     if (!bool1) {
/* 445 */       blitUnshifted(paramBitmap1, paramBitmap2, i, i4, i3, j, k, paramCombinationOperator);
/* 446 */     } else if (bool2) {
/* 447 */       blitSpecialShifted(paramBitmap1, paramBitmap2, i, i4, i3, j, k, i2, m, n, paramCombinationOperator);
/*     */     } else {
/*     */       
/* 450 */       blitShifted(paramBitmap1, paramBitmap2, i, i4, i3, j, k, i2, m, n, paramCombinationOperator, i1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void blitUnshifted(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, CombinationOperator paramCombinationOperator) {
/* 458 */     for (int i = paramInt1; i < paramInt2; i++, paramInt3 += paramBitmap2.getRowStride(), paramInt4 += paramBitmap1.getRowStride(), paramInt5 += paramBitmap1.getRowStride()) {
/* 459 */       int j = paramInt3;
/*     */ 
/*     */       
/* 462 */       for (int k = paramInt4; k <= paramInt5; k++) {
/* 463 */         byte b1 = paramBitmap2.getByte(j);
/* 464 */         byte b2 = paramBitmap1.getByte(k);
/* 465 */         paramBitmap2.setByte(j++, combineBytes(b1, b2, paramCombinationOperator));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void blitSpecialShifted(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, CombinationOperator paramCombinationOperator) {
/* 473 */     for (int i = paramInt1; i < paramInt2; i++, paramInt3 += paramBitmap2.getRowStride(), paramInt4 += paramBitmap1.getRowStride(), paramInt5 += paramBitmap1.getRowStride()) {
/* 474 */       short s = 0;
/* 475 */       int j = paramInt3;
/*     */ 
/*     */       
/* 478 */       for (int k = paramInt4; k <= paramInt5; k++) {
/* 479 */         byte b1 = paramBitmap2.getByte(j);
/* 480 */         s = (short)((s | paramBitmap1.getByte(k) & 0xFF) << paramInt8);
/* 481 */         byte b2 = (byte)(s >> 8);
/*     */         
/* 483 */         if (k == paramInt5) {
/* 484 */           b2 = unpad(paramInt6, b2);
/*     */         }
/*     */         
/* 487 */         paramBitmap2.setByte(j++, combineBytes(b1, b2, paramCombinationOperator));
/* 488 */         s = (short)(s << paramInt7);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void blitShifted(Bitmap paramBitmap1, Bitmap paramBitmap2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, CombinationOperator paramCombinationOperator, int paramInt9) {
/* 496 */     for (int i = paramInt1; i < paramInt2; i++, paramInt3 += paramBitmap2.getRowStride(), paramInt4 += paramBitmap1.getRowStride(), paramInt5 += paramBitmap1.getRowStride()) {
/* 497 */       short s = 0;
/* 498 */       int j = paramInt3;
/*     */ 
/*     */       
/* 501 */       for (int k = paramInt4; k <= paramInt5; k++) {
/* 502 */         byte b1 = paramBitmap2.getByte(j);
/* 503 */         s = (short)((s | paramBitmap1.getByte(k) & 0xFF) << paramInt8);
/*     */         
/* 505 */         byte b2 = (byte)(s >> 8);
/* 506 */         paramBitmap2.setByte(j++, combineBytes(b1, b2, paramCombinationOperator));
/*     */         
/* 508 */         s = (short)(s << paramInt7);
/*     */         
/* 510 */         if (k == paramInt5) {
/* 511 */           b2 = (byte)(s >> 8 - paramInt8);
/*     */           
/* 513 */           if (paramInt9 != 0) {
/* 514 */             b2 = unpad(8 + paramInt6, b2);
/*     */           }
/*     */           
/* 517 */           b1 = paramBitmap2.getByte(j);
/* 518 */           paramBitmap2.setByte(j, combineBytes(b1, b2, paramCombinationOperator));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/Bitmaps.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */