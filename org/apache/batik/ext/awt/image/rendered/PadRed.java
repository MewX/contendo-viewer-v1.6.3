/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
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
/*     */ public class PadRed
/*     */   extends AbstractRed
/*     */ {
/*     */   static final boolean DEBUG = false;
/*     */   PadMode padMode;
/*     */   RenderingHints hints;
/*     */   
/*     */   public PadRed(CachableRed src, Rectangle bounds, PadMode padMode, RenderingHints hints) {
/*  57 */     super(src, bounds, src.getColorModel(), fixSampleModel(src, bounds), bounds.x, bounds.y, (Map)null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     this.padMode = padMode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.hints = hints;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/*  76 */     CachableRed src = getSources().get(0);
/*     */     
/*  78 */     Rectangle srcR = src.getBounds();
/*  79 */     Rectangle wrR = wr.getBounds();
/*     */     
/*  81 */     if (wrR.intersects(srcR)) {
/*  82 */       Rectangle r = wrR.intersection(srcR);
/*     */ 
/*     */ 
/*     */       
/*  86 */       WritableRaster srcWR = wr.createWritableChild(r.x, r.y, r.width, r.height, r.x, r.y, (int[])null);
/*     */       
/*  88 */       src.copyData(srcWR);
/*     */     } 
/*     */     
/*  91 */     if (this.padMode == PadMode.ZERO_PAD) {
/*  92 */       handleZero(wr);
/*  93 */     } else if (this.padMode == PadMode.REPLICATE) {
/*  94 */       handleReplicate(wr);
/*  95 */     } else if (this.padMode == PadMode.WRAP) {
/*  96 */       handleWrap(wr);
/*     */     } 
/*     */     
/*  99 */     return wr;
/*     */   }
/*     */   
/*     */   protected static class ZeroRecter {
/*     */     WritableRaster wr;
/*     */     int bands;
/* 105 */     static int[] zeros = null;
/*     */     public ZeroRecter(WritableRaster wr) {
/* 107 */       this.wr = wr;
/* 108 */       this.bands = wr.getSampleModel().getNumBands();
/*     */     }
/*     */     public void zeroRect(Rectangle r) {
/* 111 */       synchronized (this) {
/* 112 */         if (zeros == null || zeros.length < r.width * this.bands) {
/* 113 */           zeros = new int[r.width * this.bands];
/*     */         }
/*     */       } 
/* 116 */       for (int y = 0; y < r.height; y++) {
/* 117 */         this.wr.setPixels(r.x, r.y + y, r.width, 1, zeros);
/*     */       }
/*     */     }
/*     */     
/*     */     public static ZeroRecter getZeroRecter(WritableRaster wr) {
/* 122 */       if (GraphicsUtil.is_INT_PACK_Data(wr.getSampleModel(), false)) {
/* 123 */         return new PadRed.ZeroRecter_INT_PACK(wr);
/*     */       }
/* 125 */       return new ZeroRecter(wr);
/*     */     }
/*     */     
/*     */     public static void zeroRect(WritableRaster wr) {
/* 129 */       ZeroRecter zr = getZeroRecter(wr);
/* 130 */       zr.zeroRect(wr.getBounds());
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class ZeroRecter_INT_PACK extends ZeroRecter {
/*     */     final int base;
/*     */     final int scanStride;
/*     */     final int[] pixels;
/*     */     final int[] zeros;
/*     */     final int x0;
/*     */     final int y0;
/*     */     
/*     */     public ZeroRecter_INT_PACK(WritableRaster wr) {
/* 143 */       super(wr);
/*     */ 
/*     */       
/* 146 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */       
/* 148 */       this.scanStride = sppsm.getScanlineStride();
/* 149 */       DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 150 */       this.x0 = wr.getMinY();
/* 151 */       this.y0 = wr.getMinX();
/* 152 */       this.base = db.getOffset() + sppsm.getOffset(this.x0 - wr.getSampleModelTranslateX(), this.y0 - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */       
/* 156 */       this.pixels = db.getBankData()[0];
/* 157 */       if (wr.getWidth() > 10) {
/* 158 */         this.zeros = new int[wr.getWidth()];
/*     */       } else {
/* 160 */         this.zeros = null;
/*     */       } 
/*     */     }
/*     */     public void zeroRect(Rectangle r) {
/* 164 */       int rbase = this.base + r.x - this.x0 + (r.y - this.y0) * this.scanStride;
/*     */       
/* 166 */       if (r.width > 10) {
/*     */         
/* 168 */         for (int y = 0; y < r.height; y++) {
/* 169 */           int sp = rbase + y * this.scanStride;
/* 170 */           System.arraycopy(this.zeros, 0, this.pixels, sp, r.width);
/*     */         } 
/*     */       } else {
/*     */         
/* 174 */         int sp = rbase;
/* 175 */         int end = sp + r.width;
/* 176 */         int adj = this.scanStride - r.width;
/* 177 */         for (int y = 0; y < r.height; y++) {
/* 178 */           while (sp < end)
/* 179 */             this.pixels[sp++] = 0; 
/* 180 */           sp += adj;
/* 181 */           end += this.scanStride;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleZero(WritableRaster wr) {
/* 189 */     CachableRed src = getSources().get(0);
/* 190 */     Rectangle srcR = src.getBounds();
/* 191 */     Rectangle wrR = wr.getBounds();
/*     */     
/* 193 */     ZeroRecter zr = ZeroRecter.getZeroRecter(wr);
/*     */ 
/*     */     
/* 196 */     Rectangle ar = new Rectangle(wrR.x, wrR.y, wrR.width, wrR.height);
/*     */     
/* 198 */     Rectangle dr = new Rectangle(wrR.x, wrR.y, wrR.width, wrR.height);
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
/* 221 */     if (ar.x < srcR.x) {
/* 222 */       int w = srcR.x - ar.x;
/* 223 */       if (w > ar.width) w = ar.width;
/*     */       
/* 225 */       dr.width = w;
/* 226 */       zr.zeroRect(dr);
/*     */       
/* 228 */       ar.x += w;
/* 229 */       ar.width -= w;
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
/* 240 */     if (ar.y < srcR.y) {
/* 241 */       int h = srcR.y - ar.y;
/* 242 */       if (h > ar.height) h = ar.height;
/*     */       
/* 244 */       dr.x = ar.x;
/* 245 */       dr.y = ar.y;
/* 246 */       dr.width = ar.width;
/* 247 */       dr.height = h;
/* 248 */       zr.zeroRect(dr);
/*     */       
/* 250 */       ar.y += h;
/* 251 */       ar.height -= h;
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
/* 262 */     if (ar.y + ar.height > srcR.y + srcR.height) {
/* 263 */       int h = ar.y + ar.height - srcR.y + srcR.height;
/* 264 */       if (h > ar.height) h = ar.height;
/*     */       
/* 266 */       int y0 = ar.y + ar.height - h;
/*     */ 
/*     */       
/* 269 */       dr.x = ar.x;
/* 270 */       dr.y = y0;
/* 271 */       dr.width = ar.width;
/* 272 */       dr.height = h;
/* 273 */       zr.zeroRect(dr);
/*     */       
/* 275 */       ar.height -= h;
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
/* 286 */     if (ar.x + ar.width > srcR.x + srcR.width) {
/* 287 */       int w = ar.x + ar.width - srcR.x + srcR.width;
/* 288 */       if (w > ar.width) w = ar.width; 
/* 289 */       int x0 = ar.x + ar.width - w;
/*     */ 
/*     */       
/* 292 */       dr.x = x0;
/* 293 */       dr.y = ar.y;
/* 294 */       dr.width = w;
/* 295 */       dr.height = ar.height;
/* 296 */       zr.zeroRect(dr);
/*     */       
/* 298 */       ar.width -= w;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleReplicate(WritableRaster wr) {
/* 305 */     CachableRed src = getSources().get(0);
/* 306 */     Rectangle srcR = src.getBounds();
/* 307 */     Rectangle wrR = wr.getBounds();
/*     */     
/* 309 */     int x = wrR.x;
/* 310 */     int y = wrR.y;
/* 311 */     int width = wrR.width;
/* 312 */     int height = wrR.height;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 321 */     int minX = (srcR.x > x) ? srcR.x : x;
/* 322 */     int maxX = (srcR.x + srcR.width - 1 < x + width - 1) ? (srcR.x + srcR.width - 1) : (x + width - 1);
/*     */     
/* 324 */     int minY = (srcR.y > y) ? srcR.y : y;
/* 325 */     int maxY = (srcR.y + srcR.height - 1 < y + height - 1) ? (srcR.y + srcR.height - 1) : (y + height - 1);
/*     */ 
/*     */     
/* 328 */     int x0 = minX;
/* 329 */     int w = maxX - minX + 1;
/* 330 */     int y0 = minY;
/* 331 */     int h = maxY - minY + 1;
/* 332 */     if (w < 0) { x0 = 0; w = 0; }
/* 333 */      if (h < 0) { y0 = 0; h = 0; }
/* 334 */      Rectangle r = new Rectangle(x0, y0, w, h);
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
/* 352 */     if (y < srcR.y) {
/* 353 */       int repW = r.width;
/* 354 */       int repX = r.x;
/* 355 */       int wrX = r.x;
/* 356 */       int wrY = y;
/* 357 */       if (x + width - 1 <= srcR.x) {
/*     */ 
/*     */         
/* 360 */         repW = 1;
/* 361 */         repX = srcR.x;
/* 362 */         wrX = x + width - 1;
/* 363 */       } else if (x >= srcR.x + srcR.width) {
/*     */ 
/*     */         
/* 366 */         repW = 1;
/* 367 */         repX = srcR.x + srcR.width - 1;
/* 368 */         wrX = x;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 374 */       WritableRaster wr1 = wr.createWritableChild(wrX, wrY, repW, 1, repX, srcR.y, (int[])null);
/*     */ 
/*     */       
/* 377 */       src.copyData(wr1);
/* 378 */       wrY++;
/*     */       
/* 380 */       int endY = srcR.y;
/* 381 */       if (y + height < endY) endY = y + height;
/*     */       
/* 383 */       if (wrY < endY) {
/* 384 */         int[] pixels = wr.getPixels(wrX, wrY - 1, repW, 1, (int[])null);
/*     */         
/* 386 */         while (wrY < srcR.y) {
/* 387 */           wr.setPixels(wrX, wrY, repW, 1, pixels);
/* 388 */           wrY++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 394 */     if (y + height > srcR.y + srcR.height) {
/* 395 */       int repW = r.width;
/* 396 */       int repX = r.x;
/* 397 */       int repY = srcR.y + srcR.height - 1;
/*     */       
/* 399 */       int wrX = r.x;
/* 400 */       int wrY = srcR.y + srcR.height;
/* 401 */       if (wrY < y) wrY = y;
/*     */       
/* 403 */       if (x + width <= srcR.x) {
/*     */ 
/*     */         
/* 406 */         repW = 1;
/* 407 */         repX = srcR.x;
/* 408 */         wrX = x + width - 1;
/* 409 */       } else if (x >= srcR.x + srcR.width) {
/*     */ 
/*     */         
/* 412 */         repW = 1;
/* 413 */         repX = srcR.x + srcR.width - 1;
/* 414 */         wrX = x;
/*     */       } 
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
/* 426 */       WritableRaster wr1 = wr.createWritableChild(wrX, wrY, repW, 1, repX, repY, (int[])null);
/*     */ 
/*     */ 
/*     */       
/* 430 */       src.copyData(wr1);
/* 431 */       wrY++;
/*     */       
/* 433 */       int endY = y + height;
/* 434 */       if (wrY < endY) {
/*     */         
/* 436 */         int[] pixels = wr.getPixels(wrX, wrY - 1, repW, 1, (int[])null);
/*     */         
/* 438 */         while (wrY < endY) {
/* 439 */           wr.setPixels(wrX, wrY, repW, 1, pixels);
/* 440 */           wrY++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 446 */     if (x < srcR.x) {
/*     */ 
/*     */ 
/*     */       
/* 450 */       int wrX = srcR.x;
/* 451 */       if (x + width <= srcR.x) {
/* 452 */         wrX = x + width - 1;
/*     */       }
/*     */       
/* 455 */       int xLoc = x;
/* 456 */       int[] pixels = wr.getPixels(wrX, y, 1, height, (int[])null);
/* 457 */       while (xLoc < wrX) {
/* 458 */         wr.setPixels(xLoc, y, 1, height, pixels);
/* 459 */         xLoc++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 464 */     if (x + width > srcR.x + srcR.width) {
/*     */ 
/*     */ 
/*     */       
/* 468 */       int wrX = srcR.x + srcR.width - 1;
/* 469 */       if (x >= srcR.x + srcR.width) {
/* 470 */         wrX = x;
/*     */       }
/*     */       
/* 473 */       int xLoc = wrX + 1;
/* 474 */       int endX = x + width - 1;
/* 475 */       int[] pixels = wr.getPixels(wrX, y, 1, height, (int[])null);
/* 476 */       while (xLoc < endX) {
/* 477 */         wr.setPixels(xLoc, y, 1, height, pixels);
/* 478 */         xLoc++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void handleWrap(WritableRaster wr) {
/* 485 */     handleZero(wr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static SampleModel fixSampleModel(CachableRed src, Rectangle bounds) {
/* 495 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/*     */     
/* 497 */     SampleModel sm = src.getSampleModel();
/* 498 */     int w = sm.getWidth();
/* 499 */     if (w < defSz) w = defSz; 
/* 500 */     if (w > bounds.width) w = bounds.width; 
/* 501 */     int h = sm.getHeight();
/* 502 */     if (h < defSz) h = defSz; 
/* 503 */     if (h > bounds.height) h = bounds.height;
/*     */ 
/*     */ 
/*     */     
/* 507 */     return sm.createCompatibleSampleModel(w, h);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/PadRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */