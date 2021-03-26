/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileRed
/*     */   extends AbstractRed
/*     */   implements TileGenerator
/*     */ {
/*  48 */   static final AffineTransform IDENTITY = new AffineTransform();
/*     */ 
/*     */   
/*     */   Rectangle tiledRegion;
/*     */ 
/*     */   
/*     */   int xStep;
/*     */ 
/*     */   
/*     */   int yStep;
/*     */ 
/*     */   
/*     */   TileStore tiles;
/*     */ 
/*     */   
/*     */   private RenderingHints hints;
/*     */   
/*     */   final boolean is_INT_PACK;
/*     */   
/*  67 */   RenderedImage tile = null;
/*  68 */   WritableRaster raster = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public TileRed(RenderedImage tile, Rectangle tiledRegion) {
/*  73 */     this(tile, tiledRegion, tile.getWidth(), tile.getHeight(), (RenderingHints)null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileRed(RenderedImage tile, Rectangle tiledRegion, RenderingHints hints) {
/*  79 */     this(tile, tiledRegion, tile.getWidth(), tile.getHeight(), hints);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileRed(RenderedImage tile, Rectangle tiledRegion, int xStep, int yStep) {
/*  85 */     this(tile, tiledRegion, xStep, yStep, (RenderingHints)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileRed(RenderedImage tile, Rectangle tiledRegion, int xStep, int yStep, RenderingHints hints) {
/*  92 */     if (tiledRegion == null) {
/*  93 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  96 */     if (tile == null) {
/*  97 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/* 101 */     this.tiledRegion = tiledRegion;
/* 102 */     this.xStep = xStep;
/* 103 */     this.yStep = yStep;
/* 104 */     this.hints = hints;
/*     */     
/* 106 */     SampleModel sm = fixSampleModel(tile, xStep, yStep, tiledRegion.width, tiledRegion.height);
/*     */ 
/*     */     
/* 109 */     ColorModel cm = tile.getColorModel();
/*     */     
/* 111 */     double smSz = AbstractTiledRed.getDefaultTileSize();
/* 112 */     smSz *= smSz;
/*     */     
/* 114 */     double stepSz = xStep * yStep;
/*     */ 
/*     */     
/* 117 */     if (16.1D * smSz > stepSz) {
/* 118 */       int xSz = xStep;
/* 119 */       int ySz = yStep;
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (4.0D * stepSz <= smSz) {
/* 124 */         int mult = (int)Math.ceil(Math.sqrt(smSz / stepSz));
/* 125 */         xSz *= mult;
/* 126 */         ySz *= mult;
/*     */       } 
/*     */       
/* 129 */       sm = sm.createCompatibleSampleModel(xSz, ySz);
/* 130 */       this.raster = Raster.createWritableRaster(sm, new Point(tile.getMinX(), tile.getMinY()));
/*     */     } 
/*     */ 
/*     */     
/* 134 */     this.is_INT_PACK = GraphicsUtil.is_INT_PACK_Data(sm, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     init((CachableRed)null, tiledRegion, cm, sm, tile.getMinX(), tile.getMinY(), (Map)null);
/*     */ 
/*     */     
/* 146 */     if (this.raster != null) {
/* 147 */       WritableRaster fromRaster = this.raster.createWritableChild(tile.getMinX(), tile.getMinY(), xStep, yStep, tile.getMinX(), tile.getMinY(), (int[])null);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       fillRasterFrom(fromRaster, tile);
/* 153 */       fillOutRaster(this.raster);
/*     */     } else {
/*     */       
/* 156 */       this.tile = new TileCacheRed(GraphicsUtil.wrap(tile));
/*     */     } 
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 161 */     int xOff = (int)Math.floor((wr.getMinX() / this.xStep)) * this.xStep;
/* 162 */     int yOff = (int)Math.floor((wr.getMinY() / this.yStep)) * this.yStep;
/* 163 */     int x0 = wr.getMinX() - xOff;
/* 164 */     int y0 = wr.getMinY() - yOff;
/* 165 */     int tx0 = getXTile(x0);
/* 166 */     int ty0 = getYTile(y0);
/* 167 */     int tx1 = getXTile(x0 + wr.getWidth() - 1);
/* 168 */     int ty1 = getYTile(y0 + wr.getHeight() - 1);
/*     */     
/* 170 */     for (int y = ty0; y <= ty1; y++) {
/* 171 */       for (int x = tx0; x <= tx1; x++) {
/* 172 */         Raster r = getTile(x, y);
/* 173 */         r = r.createChild(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight(), r.getMinX() + xOff, r.getMinY() + yOff, null);
/*     */ 
/*     */         
/* 176 */         if (this.is_INT_PACK)
/* 177 */         { GraphicsUtil.copyData_INT_PACK(r, wr); }
/*     */         else
/* 179 */         { GraphicsUtil.copyData_FALLBACK(r, wr); } 
/*     */       } 
/* 181 */     }  return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 187 */     if (this.raster != null) {
/*     */ 
/*     */       
/* 190 */       int tx = this.tileGridXOff + x * this.tileWidth;
/* 191 */       int ty = this.tileGridYOff + y * this.tileHeight;
/* 192 */       return this.raster.createTranslatedChild(tx, ty);
/*     */     } 
/*     */ 
/*     */     
/* 196 */     return genTile(x, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster genTile(int x, int y) {
/* 201 */     int tx = this.tileGridXOff + x * this.tileWidth;
/* 202 */     int ty = this.tileGridYOff + y * this.tileHeight;
/*     */     
/* 204 */     if (this.raster != null)
/*     */     {
/*     */       
/* 207 */       return this.raster.createTranslatedChild(tx, ty);
/*     */     }
/*     */     
/* 210 */     Point pt = new Point(tx, ty);
/* 211 */     WritableRaster wr = Raster.createWritableRaster(this.sm, pt);
/* 212 */     fillRasterFrom(wr, this.tile);
/* 213 */     return wr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster fillRasterFrom(WritableRaster wr, RenderedImage src) {
/* 220 */     ColorModel cm = getColorModel();
/* 221 */     BufferedImage bi = new BufferedImage(cm, wr.createWritableTranslatedChild(0, 0), cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     Graphics2D g = GraphicsUtil.createGraphics(bi, this.hints);
/*     */     
/* 228 */     int minX = wr.getMinX();
/* 229 */     int minY = wr.getMinY();
/* 230 */     int maxX = wr.getWidth();
/* 231 */     int maxY = wr.getHeight();
/*     */ 
/*     */     
/* 234 */     g.setComposite(AlphaComposite.Clear);
/* 235 */     g.setColor(new Color(0, 0, 0, 0));
/* 236 */     g.fillRect(0, 0, maxX, maxY);
/* 237 */     g.setComposite(AlphaComposite.SrcOver);
/*     */     
/* 239 */     g.translate(-minX, -minY);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     int x1 = src.getMinX() + src.getWidth() - 1;
/* 245 */     int y1 = src.getMinY() + src.getHeight() - 1;
/*     */     
/* 247 */     int tileTx = (int)Math.ceil(((minX - x1) / this.xStep)) * this.xStep;
/* 248 */     int tileTy = (int)Math.ceil(((minY - y1) / this.yStep)) * this.yStep;
/*     */     
/* 250 */     g.translate(tileTx, tileTy);
/*     */     
/* 252 */     int curX = tileTx - wr.getMinX() + src.getMinX();
/* 253 */     int curY = tileTy - wr.getMinY() + src.getMinY();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     minX = curX;
/* 262 */     while (curY < maxY) {
/* 263 */       if (HaltingThread.hasBeenHalted()) {
/* 264 */         return wr;
/*     */       }
/* 266 */       while (curX < maxX) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 272 */         GraphicsUtil.drawImage(g, src);
/* 273 */         curX += this.xStep;
/* 274 */         g.translate(this.xStep, 0);
/*     */       } 
/* 276 */       curY += this.yStep;
/* 277 */       g.translate(minX - curX, this.yStep);
/* 278 */       curX = minX;
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
/* 290 */     return wr;
/*     */   }
/*     */   
/*     */   protected void fillOutRaster(WritableRaster wr) {
/* 294 */     if (this.is_INT_PACK) {
/* 295 */       fillOutRaster_INT_PACK(wr);
/*     */     } else {
/* 297 */       fillOutRaster_FALLBACK(wr);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fillOutRaster_INT_PACK(WritableRaster wr) {
/* 303 */     int x0 = wr.getMinX();
/* 304 */     int y0 = wr.getMinY();
/* 305 */     int width = wr.getWidth();
/* 306 */     int height = wr.getHeight();
/*     */ 
/*     */     
/* 309 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 311 */     int scanStride = sppsm.getScanlineStride();
/* 312 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 313 */     int[] pixels = db.getBankData()[0];
/* 314 */     int base = db.getOffset() + sppsm.getOffset(x0 - wr.getSampleModelTranslateX(), y0 - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */     
/* 318 */     int step = this.xStep;
/* 319 */     for (int x = this.xStep; x < width; x += step, step *= 2) {
/* 320 */       int w = step;
/* 321 */       if (x + w > width) w = width - x; 
/* 322 */       if (w >= 128) {
/* 323 */         int srcSP = base;
/* 324 */         int dstSP = base + x;
/* 325 */         for (int i = 0; i < this.yStep; i++) {
/* 326 */           System.arraycopy(pixels, srcSP, pixels, dstSP, w);
/* 327 */           srcSP += scanStride;
/* 328 */           dstSP += scanStride;
/*     */         } 
/*     */       } else {
/* 331 */         int srcSP = base;
/* 332 */         int dstSP = base + x;
/* 333 */         for (int i = 0; i < this.yStep; i++) {
/* 334 */           int end = srcSP;
/* 335 */           srcSP += w - 1;
/* 336 */           dstSP += w - 1;
/* 337 */           while (srcSP >= end)
/* 338 */             pixels[dstSP--] = pixels[srcSP--]; 
/* 339 */           srcSP += scanStride + 1;
/* 340 */           dstSP += scanStride + 1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 345 */     step = this.yStep;
/* 346 */     for (int y = this.yStep; y < height; y += step, step *= 2) {
/* 347 */       int h = step;
/* 348 */       if (y + h > height) h = height - y; 
/* 349 */       int dstSP = base + y * scanStride;
/* 350 */       System.arraycopy(pixels, base, pixels, dstSP, h * scanStride);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fillOutRaster_FALLBACK(WritableRaster wr) {
/* 356 */     int width = wr.getWidth();
/* 357 */     int height = wr.getHeight();
/*     */     
/* 359 */     Object data = null;
/*     */     
/* 361 */     int step = this.xStep;
/* 362 */     for (int x = this.xStep; x < width; x += step, step *= 4) {
/* 363 */       int w = step;
/* 364 */       if (x + w > width) w = width - x; 
/* 365 */       data = wr.getDataElements(0, 0, w, this.yStep, data);
/* 366 */       wr.setDataElements(x, 0, w, this.yStep, data);
/* 367 */       x += w;
/*     */       
/* 369 */       if (x >= width)
/* 370 */         break;  if (x + w > width) w = width - x; 
/* 371 */       wr.setDataElements(x, 0, w, this.yStep, data);
/* 372 */       x += w;
/*     */       
/* 374 */       if (x >= width)
/* 375 */         break;  if (x + w > width) w = width - x; 
/* 376 */       wr.setDataElements(x, 0, w, this.yStep, data);
/*     */     } 
/*     */     
/* 379 */     step = this.yStep;
/* 380 */     for (int y = this.yStep; y < height; y += step, step *= 4) {
/* 381 */       int h = step;
/* 382 */       if (y + h > height) h = height - y; 
/* 383 */       data = wr.getDataElements(0, 0, width, h, data);
/* 384 */       wr.setDataElements(0, y, width, h, data);
/* 385 */       y += h;
/*     */       
/* 387 */       if (h >= height)
/* 388 */         break;  if (y + h > height) h = height - y; 
/* 389 */       wr.setDataElements(0, y, width, h, data);
/* 390 */       y += h;
/*     */       
/* 392 */       if (h >= height)
/* 393 */         break;  if (y + h > height) h = height - y; 
/* 394 */       wr.setDataElements(0, y, width, h, data);
/* 395 */       y += h;
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
/*     */   protected static SampleModel fixSampleModel(RenderedImage src, int stepX, int stepY, int width, int height) {
/* 407 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/* 408 */     SampleModel sm = src.getSampleModel();
/* 409 */     int w = sm.getWidth();
/* 410 */     if (w < defSz) w = defSz; 
/* 411 */     if (w > stepX) w = stepX;
/*     */     
/* 413 */     int h = sm.getHeight();
/* 414 */     if (h < defSz) h = defSz; 
/* 415 */     if (h > stepY) h = stepY;
/*     */     
/* 417 */     return sm.createCompatibleSampleModel(w, h);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */