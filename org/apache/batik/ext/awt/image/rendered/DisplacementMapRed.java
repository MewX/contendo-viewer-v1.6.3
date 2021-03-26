/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.ARGBChannel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DisplacementMapRed
/*     */   extends AbstractRed
/*     */ {
/*     */   private static final boolean TIME = false;
/*     */   private static final boolean USE_NN = false;
/*     */   private float scaleX;
/*     */   private float scaleY;
/*     */   private ARGBChannel xChannel;
/*     */   private ARGBChannel yChannel;
/*     */   CachableRed image;
/*     */   CachableRed offsets;
/*     */   int maxOffX;
/*     */   int maxOffY;
/*     */   RenderingHints hints;
/*     */   TileOffsets[] xOffsets;
/*     */   TileOffsets[] yOffsets;
/*     */   
/*     */   static class TileOffsets
/*     */   {
/*     */     int[] tile;
/*     */     int[] off;
/*     */     
/*     */     TileOffsets(int len, int base, int stride, int loc, int endLoc, int slop, int tile, int endTile) {
/*  97 */       this.tile = new int[len + 1];
/*  98 */       this.off = new int[len + 1];
/*     */       
/* 100 */       if (tile == endTile) endLoc -= slop;
/*     */       
/* 102 */       for (int i = 0; i < len; i++) {
/* 103 */         this.tile[i] = tile;
/* 104 */         this.off[i] = base + loc * stride;
/* 105 */         loc++;
/* 106 */         if (loc == endLoc) {
/* 107 */           loc = 0;
/* 108 */           tile++;
/* 109 */           if (tile == endTile) endLoc -= slop; 
/*     */         } 
/*     */       } 
/* 112 */       this.tile[len] = this.tile[len - 1];
/* 113 */       this.off[len] = this.off[len - 1];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DisplacementMapRed(CachableRed image, CachableRed offsets, ARGBChannel xChannel, ARGBChannel yChannel, float scaleX, float scaleY, RenderingHints rh) {
/* 136 */     if (xChannel == null) {
/* 137 */       throw new IllegalArgumentException("Must provide xChannel");
/*     */     }
/*     */     
/* 140 */     if (yChannel == null) {
/* 141 */       throw new IllegalArgumentException("Must provide yChannel");
/*     */     }
/*     */     
/* 144 */     this.offsets = offsets;
/* 145 */     this.scaleX = scaleX;
/* 146 */     this.scaleY = scaleY;
/* 147 */     this.xChannel = xChannel;
/* 148 */     this.yChannel = yChannel;
/* 149 */     this.hints = rh;
/*     */     
/* 151 */     this.maxOffX = (int)Math.ceil((scaleX / 2.0F));
/* 152 */     this.maxOffY = (int)Math.ceil((scaleY / 2.0F));
/*     */     
/* 154 */     Rectangle rect = image.getBounds();
/*     */     
/* 156 */     Rectangle r = image.getBounds();
/* 157 */     r.x -= this.maxOffX; r.width += 2 * this.maxOffX;
/* 158 */     r.y -= this.maxOffY; r.height += 2 * this.maxOffY;
/* 159 */     image = new PadRed(image, r, PadMode.ZERO_PAD, null);
/* 160 */     image = new TileCacheRed(image);
/* 161 */     this.image = image;
/* 162 */     ColorModel cm = image.getColorModel();
/*     */ 
/*     */     
/* 165 */     cm = GraphicsUtil.coerceColorModel(cm, true);
/*     */     
/* 167 */     init(image, rect, cm, image.getSampleModel(), rect.x, rect.y, (Map)null);
/*     */ 
/*     */     
/* 170 */     this.xOffsets = new TileOffsets[getNumXTiles()];
/* 171 */     this.yOffsets = new TileOffsets[getNumYTiles()];
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 175 */     copyToRaster(wr);
/* 176 */     return wr;
/*     */   }
/*     */   
/*     */   public Raster getTile(int tileX, int tileY) {
/* 180 */     WritableRaster dest = makeTile(tileX, tileY);
/* 181 */     Rectangle srcR = dest.getBounds();
/*     */ 
/*     */     
/* 184 */     Raster mapRas = this.offsets.getData(srcR);
/* 185 */     ColorModel mapCM = this.offsets.getColorModel();
/*     */     
/* 187 */     GraphicsUtil.coerceData((WritableRaster)mapRas, mapCM, false);
/*     */     
/* 189 */     TileOffsets xinfo = getXOffsets(tileX);
/* 190 */     TileOffsets yinfo = getYOffsets(tileY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (this.image.getColorModel().isAlphaPremultiplied()) {
/* 197 */       filterBL(mapRas, dest, xinfo.tile, xinfo.off, yinfo.tile, yinfo.off);
/*     */     }
/*     */     else {
/*     */       
/* 201 */       filterBLPre(mapRas, dest, xinfo.tile, xinfo.off, yinfo.tile, yinfo.off);
/*     */     } 
/*     */ 
/*     */     
/* 205 */     return dest;
/*     */   }
/*     */   
/*     */   public TileOffsets getXOffsets(int xTile) {
/* 209 */     TileOffsets ret = this.xOffsets[xTile - getMinTileX()];
/* 210 */     if (ret != null) {
/* 211 */       return ret;
/*     */     }
/*     */     
/* 214 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)getSampleModel();
/* 215 */     int base = sppsm.getOffset(0, 0);
/* 216 */     int tw = sppsm.getWidth();
/*     */ 
/*     */     
/* 219 */     int width = tw + 2 * this.maxOffX;
/*     */ 
/*     */     
/* 222 */     int x0 = getTileGridXOffset() + xTile * tw - this.maxOffX - this.image.getTileGridXOffset();
/*     */     
/* 224 */     int x1 = x0 + width - 1;
/*     */     
/* 226 */     int tile = (int)Math.floor(x0 / tw);
/* 227 */     int endTile = (int)Math.floor(x1 / tw);
/* 228 */     int loc = x0 - tile * tw;
/* 229 */     int endLoc = tw;
/*     */ 
/*     */     
/* 232 */     int slop = (endTile + 1) * tw - 1 - x1;
/*     */     
/* 234 */     ret = new TileOffsets(width, base, 1, loc, endLoc, slop, tile, endTile);
/*     */ 
/*     */     
/* 237 */     this.xOffsets[xTile - getMinTileX()] = ret;
/* 238 */     return ret;
/*     */   }
/*     */   
/*     */   public TileOffsets getYOffsets(int yTile) {
/* 242 */     TileOffsets ret = this.yOffsets[yTile - getMinTileY()];
/* 243 */     if (ret != null) {
/* 244 */       return ret;
/*     */     }
/*     */     
/* 247 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)getSampleModel();
/* 248 */     int stride = sppsm.getScanlineStride();
/* 249 */     int th = sppsm.getHeight();
/*     */ 
/*     */     
/* 252 */     int height = th + 2 * this.maxOffY;
/*     */ 
/*     */     
/* 255 */     int y0 = getTileGridYOffset() + yTile * th - this.maxOffY - this.image.getTileGridYOffset();
/*     */     
/* 257 */     int y1 = y0 + height - 1;
/*     */     
/* 259 */     int tile = (int)Math.floor(y0 / th);
/* 260 */     int endTile = (int)Math.floor(y1 / th);
/* 261 */     int loc = y0 - tile * th;
/* 262 */     int endLoc = th;
/*     */ 
/*     */     
/* 265 */     int slop = (endTile + 1) * th - 1 - y1;
/*     */     
/* 267 */     ret = new TileOffsets(height, 0, stride, loc, endLoc, slop, tile, endTile);
/*     */ 
/*     */     
/* 270 */     this.yOffsets[yTile - getMinTileY()] = ret;
/* 271 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void filterBL(Raster off, WritableRaster dst, int[] xTile, int[] xOff, int[] yTile, int[] yOff) {
/* 277 */     int w = dst.getWidth();
/* 278 */     int h = dst.getHeight();
/* 279 */     int xStart = this.maxOffX;
/* 280 */     int yStart = this.maxOffY;
/* 281 */     int xEnd = xStart + w;
/* 282 */     int yEnd = yStart + h;
/*     */ 
/*     */     
/* 285 */     DataBufferInt dstDB = (DataBufferInt)dst.getDataBuffer();
/* 286 */     DataBufferInt offDB = (DataBufferInt)off.getDataBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dst.getSampleModel();
/* 292 */     int dstOff = dstDB.getOffset() + dstSPPSM.getOffset(dst.getMinX() - dst.getSampleModelTranslateX(), dst.getMinY() - dst.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */     
/* 296 */     SinglePixelPackedSampleModel offSPPSM = (SinglePixelPackedSampleModel)off.getSampleModel();
/* 297 */     int offOff = offDB.getOffset() + offSPPSM.getOffset(dst.getMinX() - off.getSampleModelTranslateX(), dst.getMinY() - off.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 303 */     int dstScanStride = dstSPPSM.getScanlineStride();
/* 304 */     int offScanStride = offSPPSM.getScanlineStride();
/*     */     
/* 306 */     int dstAdjust = dstScanStride - w;
/* 307 */     int offAdjust = offScanStride - w;
/*     */ 
/*     */     
/* 310 */     int[] dstPixels = dstDB.getBankData()[0];
/* 311 */     int[] offPixels = offDB.getBankData()[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     int xShift = this.xChannel.toInt() * 8;
/* 317 */     int yShift = this.yChannel.toInt() * 8;
/*     */ 
/*     */     
/* 320 */     int dp = dstOff, ip = offOff;
/*     */ 
/*     */     
/* 323 */     int fpScaleX = (int)(this.scaleX / 255.0D * 32768.0D + 0.5D);
/* 324 */     int fpAdjX = (int)(-127.5D * fpScaleX - 0.5D);
/* 325 */     int fpScaleY = (int)(this.scaleY / 255.0D * 32768.0D + 0.5D);
/* 326 */     int fpAdjY = (int)(-127.5D * fpScaleY - 0.5D);
/*     */     
/* 328 */     long start = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 335 */     int xt = xTile[0] - 1, yt = yTile[0] - 1;
/* 336 */     int[] imgPix = null;
/*     */     
/* 338 */     for (int y = yStart; y < yEnd; y++) {
/* 339 */       for (int x = xStart; x < xEnd; x++, dp++, ip++) {
/* 340 */         int pel01, pel10, pel11, dPel = offPixels[ip];
/*     */         
/* 342 */         int xDisplace = fpScaleX * (dPel >> xShift & 0xFF) + fpAdjX;
/* 343 */         int yDisplace = fpScaleY * (dPel >> yShift & 0xFF) + fpAdjY;
/*     */         
/* 345 */         int x0 = x + (xDisplace >> 15);
/* 346 */         int y0 = y + (yDisplace >> 15);
/*     */         
/* 348 */         if (xt != xTile[x0] || yt != yTile[y0]) {
/*     */           
/* 350 */           xt = xTile[x0]; yt = yTile[y0];
/* 351 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt).getDataBuffer()).getBankData()[0];
/*     */         } 
/*     */         
/* 354 */         int pel00 = imgPix[xOff[x0] + yOff[y0]];
/*     */         
/* 356 */         int xt1 = xTile[x0 + 1];
/* 357 */         int yt1 = yTile[y0 + 1];
/* 358 */         if (yt == yt1) {
/*     */           
/* 360 */           if (xt == xt1) {
/*     */             
/* 362 */             pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/* 363 */             pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/* 364 */             pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/*     */           } else {
/*     */             
/* 367 */             pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/*     */             
/* 369 */             imgPix = ((DataBufferInt)this.image.getTile(xt1, yt).getDataBuffer()).getBankData()[0];
/*     */             
/* 371 */             pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/* 372 */             pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/* 373 */             xt = xt1;
/*     */           }
/*     */         
/*     */         }
/* 377 */         else if (xt == xt1) {
/*     */           
/* 379 */           pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/*     */           
/* 381 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt1).getDataBuffer()).getBankData()[0];
/*     */           
/* 383 */           pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/* 384 */           pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/* 385 */           yt = yt1;
/*     */         } else {
/*     */           
/* 388 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt1).getDataBuffer()).getBankData()[0];
/*     */           
/* 390 */           pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/*     */           
/* 392 */           imgPix = ((DataBufferInt)this.image.getTile(xt1, yt1).getDataBuffer()).getBankData()[0];
/*     */           
/* 394 */           pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/*     */           
/* 396 */           imgPix = ((DataBufferInt)this.image.getTile(xt1, yt).getDataBuffer()).getBankData()[0];
/*     */           
/* 398 */           pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/* 399 */           xt = xt1;
/*     */         } 
/*     */ 
/*     */         
/* 403 */         int xFrac = xDisplace & 0x7FFF;
/* 404 */         int yFrac = yDisplace & 0x7FFF;
/*     */ 
/*     */         
/* 407 */         int sp0 = pel00 >>> 16 & 0xFF00;
/* 408 */         int sp1 = pel10 >>> 16 & 0xFF00;
/* 409 */         int pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 410 */         sp0 = pel01 >>> 16 & 0xFF00;
/* 411 */         sp1 = pel11 >>> 16 & 0xFF00;
/* 412 */         int pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 413 */         int newPel = ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) << 1;
/*     */ 
/*     */ 
/*     */         
/* 417 */         sp0 = pel00 >> 8 & 0xFF00;
/* 418 */         sp1 = pel10 >> 8 & 0xFF00;
/* 419 */         pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 420 */         sp0 = pel01 >> 8 & 0xFF00;
/* 421 */         sp1 = pel11 >> 8 & 0xFF00;
/* 422 */         pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 423 */         newPel |= ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) >>> 7;
/*     */ 
/*     */ 
/*     */         
/* 427 */         sp0 = pel00 & 0xFF00;
/* 428 */         sp1 = pel10 & 0xFF00;
/* 429 */         pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 430 */         sp0 = pel01 & 0xFF00;
/* 431 */         sp1 = pel11 & 0xFF00;
/* 432 */         pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 433 */         newPel |= ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) >>> 15;
/*     */ 
/*     */ 
/*     */         
/* 437 */         sp0 = pel00 << 8 & 0xFF00;
/* 438 */         sp1 = pel10 << 8 & 0xFF00;
/* 439 */         pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 440 */         sp0 = pel01 << 8 & 0xFF00;
/* 441 */         sp1 = pel11 << 8 & 0xFF00;
/* 442 */         pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 443 */         newPel |= ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) >>> 23;
/*     */ 
/*     */         
/* 446 */         dstPixels[dp] = newPel;
/*     */       } 
/*     */       
/* 449 */       dp += dstAdjust;
/* 450 */       ip += offAdjust;
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
/*     */   public void filterBLPre(Raster off, WritableRaster dst, int[] xTile, int[] xOff, int[] yTile, int[] yOff) {
/* 462 */     int w = dst.getWidth();
/* 463 */     int h = dst.getHeight();
/* 464 */     int xStart = this.maxOffX;
/* 465 */     int yStart = this.maxOffY;
/* 466 */     int xEnd = xStart + w;
/* 467 */     int yEnd = yStart + h;
/*     */ 
/*     */     
/* 470 */     DataBufferInt dstDB = (DataBufferInt)dst.getDataBuffer();
/* 471 */     DataBufferInt offDB = (DataBufferInt)off.getDataBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 476 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dst.getSampleModel();
/* 477 */     int dstOff = dstDB.getOffset() + dstSPPSM.getOffset(dst.getMinX() - dst.getSampleModelTranslateX(), dst.getMinY() - dst.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */     
/* 481 */     SinglePixelPackedSampleModel offSPPSM = (SinglePixelPackedSampleModel)off.getSampleModel();
/* 482 */     int offOff = offDB.getOffset() + offSPPSM.getOffset(dst.getMinX() - off.getSampleModelTranslateX(), dst.getMinY() - off.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 488 */     int dstScanStride = dstSPPSM.getScanlineStride();
/* 489 */     int offScanStride = offSPPSM.getScanlineStride();
/*     */     
/* 491 */     int dstAdjust = dstScanStride - w;
/* 492 */     int offAdjust = offScanStride - w;
/*     */ 
/*     */     
/* 495 */     int[] dstPixels = dstDB.getBankData()[0];
/* 496 */     int[] offPixels = offDB.getBankData()[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 501 */     int xShift = this.xChannel.toInt() * 8;
/* 502 */     int yShift = this.yChannel.toInt() * 8;
/*     */ 
/*     */     
/* 505 */     int dp = dstOff, ip = offOff;
/*     */ 
/*     */ 
/*     */     
/* 509 */     int fpScaleX = (int)(this.scaleX / 255.0D * 32768.0D + 0.5D);
/* 510 */     int fpAdjX = (int)(-127.5D * fpScaleX - 0.5D);
/* 511 */     int fpScaleY = (int)(this.scaleY / 255.0D * 32768.0D + 0.5D);
/* 512 */     int fpAdjY = (int)(-127.5D * fpScaleY - 0.5D);
/*     */     
/* 514 */     long start = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 520 */     int norm = 65793;
/*     */     
/* 522 */     int xt = xTile[0] - 1, yt = yTile[0] - 1;
/* 523 */     int[] imgPix = null;
/*     */     
/* 525 */     for (int y = yStart; y < yEnd; y++) {
/* 526 */       for (int x = xStart; x < xEnd; x++, dp++, ip++) {
/* 527 */         int pel01, pel10, pel11, dPel = offPixels[ip];
/*     */         
/* 529 */         int xDisplace = fpScaleX * (dPel >> xShift & 0xFF) + fpAdjX;
/* 530 */         int yDisplace = fpScaleY * (dPel >> yShift & 0xFF) + fpAdjY;
/*     */         
/* 532 */         int x0 = x + (xDisplace >> 15);
/* 533 */         int y0 = y + (yDisplace >> 15);
/*     */         
/* 535 */         if (xt != xTile[x0] || yt != yTile[y0]) {
/* 536 */           xt = xTile[x0];
/* 537 */           yt = yTile[y0];
/* 538 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt).getDataBuffer()).getBankData()[0];
/*     */         } 
/*     */         
/* 541 */         int pel00 = imgPix[xOff[x0] + yOff[y0]];
/*     */         
/* 543 */         int xt1 = xTile[x0 + 1];
/* 544 */         int yt1 = yTile[y0 + 1];
/* 545 */         if (yt == yt1) {
/*     */           
/* 547 */           if (xt == xt1) {
/*     */             
/* 549 */             pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/* 550 */             pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/* 551 */             pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/*     */           } else {
/*     */             
/* 554 */             pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/*     */             
/* 556 */             imgPix = ((DataBufferInt)this.image.getTile(xt1, yt).getDataBuffer()).getBankData()[0];
/*     */             
/* 558 */             pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/* 559 */             pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/* 560 */             xt = xt1;
/*     */           }
/*     */         
/*     */         }
/* 564 */         else if (xt == xt1) {
/*     */           
/* 566 */           pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/*     */           
/* 568 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt1).getDataBuffer()).getBankData()[0];
/*     */           
/* 570 */           pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/* 571 */           pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/* 572 */           yt = yt1;
/*     */         } else {
/*     */           
/* 575 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt1).getDataBuffer()).getBankData()[0];
/*     */           
/* 577 */           pel01 = imgPix[xOff[x0] + yOff[y0 + 1]];
/*     */           
/* 579 */           imgPix = ((DataBufferInt)this.image.getTile(xt1, yt1).getDataBuffer()).getBankData()[0];
/*     */           
/* 581 */           pel11 = imgPix[xOff[x0 + 1] + yOff[y0 + 1]];
/*     */           
/* 583 */           imgPix = ((DataBufferInt)this.image.getTile(xt1, yt).getDataBuffer()).getBankData()[0];
/*     */           
/* 585 */           pel10 = imgPix[xOff[x0 + 1] + yOff[y0]];
/* 586 */           xt = xt1;
/*     */         } 
/*     */ 
/*     */         
/* 590 */         int xFrac = xDisplace & 0x7FFF;
/* 591 */         int yFrac = yDisplace & 0x7FFF;
/*     */ 
/*     */         
/* 594 */         int sp0 = pel00 >>> 16 & 0xFF00;
/* 595 */         int sp1 = pel10 >>> 16 & 0xFF00;
/* 596 */         int pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 597 */         int a00 = (sp0 >> 8) * 65793 + 128 >> 8;
/* 598 */         int a10 = (sp1 >> 8) * 65793 + 128 >> 8;
/*     */         
/* 600 */         sp0 = pel01 >>> 16 & 0xFF00;
/* 601 */         sp1 = pel11 >>> 16 & 0xFF00;
/* 602 */         int pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 603 */         int a01 = (sp0 >> 8) * 65793 + 128 >> 8;
/* 604 */         int a11 = (sp1 >> 8) * 65793 + 128 >> 8;
/* 605 */         int newPel = ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) << 1;
/*     */ 
/*     */ 
/*     */         
/* 609 */         sp0 = (pel00 >> 16 & 0xFF) * a00 + 128 >> 8;
/* 610 */         sp1 = (pel10 >> 16 & 0xFF) * a10 + 128 >> 8;
/* 611 */         pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 612 */         sp0 = (pel01 >> 16 & 0xFF) * a01 + 128 >> 8;
/* 613 */         sp1 = (pel11 >> 16 & 0xFF) * a11 + 128 >> 8;
/* 614 */         pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 615 */         newPel |= ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) >>> 7;
/*     */ 
/*     */ 
/*     */         
/* 619 */         sp0 = (pel00 >> 8 & 0xFF) * a00 + 128 >> 8;
/* 620 */         sp1 = (pel10 >> 8 & 0xFF) * a10 + 128 >> 8;
/* 621 */         pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 622 */         sp0 = (pel01 >> 8 & 0xFF) * a01 + 128 >> 8;
/* 623 */         sp1 = (pel11 >> 8 & 0xFF) * a11 + 128 >> 8;
/* 624 */         pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 625 */         newPel |= ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) >>> 15;
/*     */ 
/*     */ 
/*     */         
/* 629 */         sp0 = (pel00 & 0xFF) * a00 + 128 >> 8;
/* 630 */         sp1 = (pel10 & 0xFF) * a10 + 128 >> 8;
/* 631 */         pel0 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 632 */         sp0 = (pel01 & 0xFF) * a01 + 128 >> 8;
/* 633 */         sp1 = (pel11 & 0xFF) * a11 + 128 >> 8;
/* 634 */         pel1 = sp0 + ((sp1 - sp0) * xFrac + 16384 >> 15) & 0xFFFF;
/* 635 */         newPel |= ((pel0 << 15) + (pel1 - pel0) * yFrac + 4194304 & 0x7F800000) >>> 23;
/*     */ 
/*     */         
/* 638 */         dstPixels[dp] = newPel;
/*     */       } 
/*     */       
/* 641 */       dp += dstAdjust;
/* 642 */       ip += offAdjust;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void filterNN(Raster off, WritableRaster dst, int[] xTile, int[] xOff, int[] yTile, int[] yOff) {
/* 662 */     int w = dst.getWidth();
/* 663 */     int h = dst.getHeight();
/* 664 */     int xStart = this.maxOffX;
/* 665 */     int yStart = this.maxOffY;
/* 666 */     int xEnd = xStart + w;
/* 667 */     int yEnd = yStart + h;
/*     */ 
/*     */     
/* 670 */     DataBufferInt dstDB = (DataBufferInt)dst.getDataBuffer();
/* 671 */     DataBufferInt offDB = (DataBufferInt)off.getDataBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 676 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dst.getSampleModel();
/* 677 */     int dstOff = dstDB.getOffset() + dstSPPSM.getOffset(dst.getMinX() - dst.getSampleModelTranslateX(), dst.getMinY() - dst.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */     
/* 681 */     SinglePixelPackedSampleModel offSPPSM = (SinglePixelPackedSampleModel)off.getSampleModel();
/* 682 */     int offOff = offDB.getOffset() + offSPPSM.getOffset(off.getMinX() - off.getSampleModelTranslateX(), off.getMinY() - off.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 688 */     int dstScanStride = dstSPPSM.getScanlineStride();
/* 689 */     int offScanStride = offSPPSM.getScanlineStride();
/*     */     
/* 691 */     int dstAdjust = dstScanStride - w;
/* 692 */     int offAdjust = offScanStride - w;
/*     */ 
/*     */     
/* 695 */     int[] dstPixels = dstDB.getBankData()[0];
/* 696 */     int[] offPixels = offDB.getBankData()[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 701 */     int xShift = this.xChannel.toInt() * 8;
/* 702 */     int yShift = this.yChannel.toInt() * 8;
/*     */     
/* 704 */     int fpScaleX = (int)(this.scaleX / 255.0D * 32768.0D + 0.5D);
/* 705 */     int fpScaleY = (int)(this.scaleY / 255.0D * 32768.0D + 0.5D);
/*     */ 
/*     */ 
/*     */     
/* 709 */     int fpAdjX = (int)(-127.5D * fpScaleX - 0.5D) + 16384;
/* 710 */     int fpAdjY = (int)(-127.5D * fpScaleY - 0.5D) + 16384;
/*     */ 
/*     */     
/* 713 */     int dp = dstOff, ip = offOff;
/*     */     
/* 715 */     long start = System.currentTimeMillis();
/* 716 */     int y = yStart, xt = xTile[0] - 1, yt = yTile[0] - 1;
/* 717 */     int[] imgPix = null;
/*     */ 
/*     */     
/* 720 */     while (y < yEnd) {
/* 721 */       int x = xStart;
/* 722 */       while (x < xEnd) {
/* 723 */         int dPel = offPixels[ip];
/*     */         
/* 725 */         int xDisplace = fpScaleX * (dPel >> xShift & 0xFF) + fpAdjX;
/* 726 */         int yDisplace = fpScaleY * (dPel >> yShift & 0xFF) + fpAdjY;
/*     */         
/* 728 */         int x0 = x + (xDisplace >> 15);
/* 729 */         int y0 = y + (yDisplace >> 15);
/*     */         
/* 731 */         if (xt != xTile[x0] || yt != yTile[y0]) {
/*     */           
/* 733 */           xt = xTile[x0]; yt = yTile[y0];
/* 734 */           imgPix = ((DataBufferInt)this.image.getTile(xt, yt).getDataBuffer()).getBankData()[0];
/*     */         } 
/*     */         
/* 737 */         dstPixels[dp] = imgPix[xOff[x0] + yOff[y0]];
/*     */         
/* 739 */         dp++;
/* 740 */         ip++;
/* 741 */         x++;
/*     */       } 
/*     */       
/* 744 */       dp += dstAdjust;
/* 745 */       ip += offAdjust;
/* 746 */       y++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/DisplacementMapRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */