/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTiledRed
/*     */   extends AbstractRed
/*     */   implements TileGenerator
/*     */ {
/*     */   private TileStore tiles;
/*  49 */   private static int defaultTileSize = 128; public static int getDefaultTileSize() {
/*  50 */     return defaultTileSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractTiledRed() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractTiledRed(Rectangle bounds, Map props) {
/*  69 */     super(bounds, props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractTiledRed(CachableRed src, Map props) {
/*  80 */     super(src, props);
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
/*     */   protected AbstractTiledRed(CachableRed src, Rectangle bounds, Map props) {
/*  92 */     super(src, bounds, props);
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
/*     */   protected AbstractTiledRed(CachableRed src, Rectangle bounds, ColorModel cm, SampleModel sm, Map props) {
/* 111 */     super(src, bounds, cm, sm, props);
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
/*     */ 
/*     */   
/*     */   protected AbstractTiledRed(CachableRed src, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 135 */     super(src, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(CachableRed src, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 162 */     init(src, bounds, cm, sm, tileGridXOff, tileGridYOff, (TileStore)null, props);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(CachableRed src, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, TileStore tiles, Map props) {
/* 192 */     super.init(src, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
/* 193 */     this.tiles = tiles;
/* 194 */     if (this.tiles == null) {
/* 195 */       this.tiles = createTileStore();
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
/*     */   protected AbstractTiledRed(List srcs, Rectangle bounds, Map props) {
/* 208 */     super(srcs, bounds, props);
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
/*     */   protected AbstractTiledRed(List srcs, Rectangle bounds, ColorModel cm, SampleModel sm, Map props) {
/* 229 */     super(srcs, bounds, cm, sm, props);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractTiledRed(List srcs, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 255 */     super(srcs, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(List srcs, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 280 */     super.init(srcs, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
/* 281 */     this.tiles = createTileStore();
/*     */   }
/*     */   
/*     */   public TileStore getTileStore() {
/* 285 */     return this.tiles;
/*     */   }
/*     */   
/*     */   protected void setTileStore(TileStore tiles) {
/* 289 */     this.tiles = tiles;
/*     */   }
/*     */   
/*     */   protected TileStore createTileStore() {
/* 293 */     return TileCache.getTileMap(this);
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 297 */     copyToRasterByBlocks(wr);
/* 298 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 303 */     int xt0 = getXTile(rect.x);
/* 304 */     int xt1 = getXTile(rect.x + rect.width - 1);
/* 305 */     int yt0 = getYTile(rect.y);
/* 306 */     int yt1 = getYTile(rect.y + rect.height - 1);
/*     */     
/* 308 */     if (xt0 == xt1 && yt0 == yt1) {
/* 309 */       Raster r = getTile(xt0, yt0);
/* 310 */       return r.createChild(rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, null);
/*     */     } 
/*     */ 
/*     */     
/* 314 */     return super.getData(rect);
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 319 */     return this.tiles.getTile(x, y);
/*     */   }
/*     */   
/*     */   public Raster genTile(int x, int y) {
/* 323 */     WritableRaster wr = makeTile(x, y);
/* 324 */     genRect(wr);
/* 325 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void genRect(WritableRaster paramWritableRaster);
/*     */ 
/*     */   
/*     */   public void setTile(int x, int y, Raster ras) {
/* 333 */     this.tiles.setTile(x, y, ras);
/*     */   }
/*     */   
/*     */   public void copyToRasterByBlocks(WritableRaster wr) {
/* 337 */     boolean is_INT_PACK = GraphicsUtil.is_INT_PACK_Data(getSampleModel(), false);
/*     */ 
/*     */     
/* 340 */     Rectangle bounds = getBounds();
/* 341 */     Rectangle wrR = wr.getBounds();
/*     */     
/* 343 */     int tx0 = getXTile(wrR.x);
/* 344 */     int ty0 = getYTile(wrR.y);
/* 345 */     int tx1 = getXTile(wrR.x + wrR.width - 1);
/* 346 */     int ty1 = getYTile(wrR.y + wrR.height - 1);
/*     */     
/* 348 */     if (tx0 < this.minTileX) tx0 = this.minTileX; 
/* 349 */     if (ty0 < this.minTileY) ty0 = this.minTileY;
/*     */     
/* 351 */     if (tx1 >= this.minTileX + this.numXTiles) tx1 = this.minTileX + this.numXTiles - 1; 
/* 352 */     if (ty1 >= this.minTileY + this.numYTiles) ty1 = this.minTileY + this.numYTiles - 1;
/*     */     
/* 354 */     if (tx1 < tx0 || ty1 < ty0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 360 */     int insideTx0 = tx0;
/* 361 */     int insideTx1 = tx1;
/*     */     
/* 363 */     int insideTy0 = ty0;
/* 364 */     int insideTy1 = ty1;
/*     */ 
/*     */ 
/*     */     
/* 368 */     int tx = tx0 * this.tileWidth + this.tileGridXOff;
/* 369 */     if (tx < wrR.x && bounds.x != wrR.x)
/*     */     {
/* 371 */       insideTx0++;
/*     */     }
/* 373 */     int ty = ty0 * this.tileHeight + this.tileGridYOff;
/* 374 */     if (ty < wrR.y && bounds.y != wrR.y)
/*     */     {
/* 376 */       insideTy0++;
/*     */     }
/* 378 */     tx = (tx1 + 1) * this.tileWidth + this.tileGridXOff - 1;
/* 379 */     if (tx >= wrR.x + wrR.width && bounds.x + bounds.width != wrR.x + wrR.width)
/*     */     {
/*     */       
/* 382 */       insideTx1--;
/*     */     }
/* 384 */     ty = (ty1 + 1) * this.tileHeight + this.tileGridYOff - 1;
/* 385 */     if (ty >= wrR.y + wrR.height && bounds.y + bounds.height != wrR.y + wrR.height)
/*     */     {
/*     */       
/* 388 */       insideTy1--;
/*     */     }
/* 390 */     int xtiles = insideTx1 - insideTx0 + 1;
/* 391 */     int ytiles = insideTy1 - insideTy0 + 1;
/* 392 */     boolean[] occupied = null;
/* 393 */     if (xtiles > 0 && ytiles > 0) {
/* 394 */       occupied = new boolean[xtiles * ytiles];
/*     */     }
/* 396 */     boolean[] got = new boolean[2 * (tx1 - tx0 + 1) + 2 * (ty1 - ty0 + 1)];
/* 397 */     int idx = 0;
/* 398 */     int numFound = 0;
/*     */     
/* 400 */     for (int y = ty0; y <= ty1; y++) {
/* 401 */       for (int x = tx0; x <= tx1; x++) {
/* 402 */         Raster ras = this.tiles.getTileNoCompute(x, y);
/* 403 */         boolean found = (ras != null);
/* 404 */         if (y >= insideTy0 && y <= insideTy1 && x >= insideTx0 && x <= insideTx1) {
/*     */           
/* 406 */           occupied[x - insideTx0 + (y - insideTy0) * xtiles] = found;
/*     */         } else {
/* 408 */           got[idx++] = found;
/*     */         } 
/* 410 */         if (found) {
/*     */           
/* 412 */           numFound++;
/*     */           
/* 414 */           if (is_INT_PACK) {
/* 415 */             GraphicsUtil.copyData_INT_PACK(ras, wr);
/*     */           } else {
/* 417 */             GraphicsUtil.copyData_FALLBACK(ras, wr);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 425 */     if (xtiles > 0 && ytiles > 0) {
/* 426 */       TileBlock block = new TileBlock(insideTx0, insideTy0, xtiles, ytiles, occupied, 0, 0, xtiles, ytiles);
/*     */ 
/*     */ 
/*     */       
/* 430 */       drawBlock(block, wr);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 436 */     Thread currentThread = Thread.currentThread();
/* 437 */     if (HaltingThread.hasBeenHalted()) {
/*     */       return;
/*     */     }
/* 440 */     idx = 0;
/*     */     
/* 442 */     for (ty = ty0; ty <= ty1; ty++) {
/*     */       
/* 444 */       for (tx = tx0; tx <= tx1; tx++) {
/*     */         
/* 446 */         Raster ras = this.tiles.getTileNoCompute(tx, ty);
/*     */         
/* 448 */         if (ty >= insideTy0 && ty <= insideTy1 && tx >= insideTx0 && tx <= insideTx1) {
/*     */ 
/*     */           
/* 451 */           if (ras == null)
/*     */           {
/*     */ 
/*     */             
/* 455 */             WritableRaster tile = makeTile(tx, ty);
/* 456 */             if (is_INT_PACK) {
/* 457 */               GraphicsUtil.copyData_INT_PACK(wr, tile);
/*     */             } else {
/* 459 */               GraphicsUtil.copyData_FALLBACK(wr, tile);
/*     */             } 
/* 461 */             this.tiles.setTile(tx, ty, tile);
/*     */           }
/*     */         
/* 464 */         } else if (!got[idx++]) {
/*     */ 
/*     */ 
/*     */           
/* 468 */           ras = getTile(tx, ty);
/*     */           
/* 470 */           if (HaltingThread.hasBeenHalted(currentThread)) {
/*     */             return;
/*     */           }
/* 473 */           if (is_INT_PACK) {
/* 474 */             GraphicsUtil.copyData_INT_PACK(ras, wr);
/*     */           } else {
/* 476 */             GraphicsUtil.copyData_FALLBACK(ras, wr);
/*     */           } 
/*     */         } 
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
/*     */   public void copyToRaster(WritableRaster wr) {
/* 491 */     Rectangle wrR = wr.getBounds();
/*     */     
/* 493 */     int tx0 = getXTile(wrR.x);
/* 494 */     int ty0 = getYTile(wrR.y);
/* 495 */     int tx1 = getXTile(wrR.x + wrR.width - 1);
/* 496 */     int ty1 = getYTile(wrR.y + wrR.height - 1);
/*     */     
/* 498 */     if (tx0 < this.minTileX) tx0 = this.minTileX; 
/* 499 */     if (ty0 < this.minTileY) ty0 = this.minTileY;
/*     */     
/* 501 */     if (tx1 >= this.minTileX + this.numXTiles) tx1 = this.minTileX + this.numXTiles - 1; 
/* 502 */     if (ty1 >= this.minTileY + this.numYTiles) ty1 = this.minTileY + this.numYTiles - 1;
/*     */     
/* 504 */     boolean is_INT_PACK = GraphicsUtil.is_INT_PACK_Data(getSampleModel(), false);
/*     */ 
/*     */     
/* 507 */     int xtiles = tx1 - tx0 + 1;
/* 508 */     boolean[] got = new boolean[xtiles * (ty1 - ty0 + 1)];
/*     */     
/*     */     int y;
/*     */     
/* 512 */     for (y = ty0; y <= ty1; y++) {
/* 513 */       for (int x = tx0; x <= tx1; x++) {
/* 514 */         Raster r = this.tiles.getTileNoCompute(x, y);
/* 515 */         if (r != null) {
/*     */           
/* 517 */           got[x - tx0 + (y - ty0) * xtiles] = true;
/*     */           
/* 519 */           if (is_INT_PACK) {
/* 520 */             GraphicsUtil.copyData_INT_PACK(r, wr);
/*     */           } else {
/* 522 */             GraphicsUtil.copyData_FALLBACK(r, wr);
/*     */           } 
/*     */         } 
/*     */       } 
/* 526 */     }  for (y = ty0; y <= ty1; y++) {
/* 527 */       for (int x = tx0; x <= tx1; x++) {
/* 528 */         if (!got[x - tx0 + (y - ty0) * xtiles]) {
/*     */           
/* 530 */           Raster r = getTile(x, y);
/* 531 */           if (is_INT_PACK) {
/* 532 */             GraphicsUtil.copyData_INT_PACK(r, wr);
/*     */           } else {
/* 534 */             GraphicsUtil.copyData_FALLBACK(r, wr);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }  } protected void drawBlock(TileBlock block, WritableRaster wr) {
/* 539 */     TileBlock[] blocks = block.getBestSplit();
/* 540 */     if (blocks == null) {
/*     */       return;
/*     */     }
/*     */     
/* 544 */     drawBlockInPlace(blocks, wr);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBlockAndCopy(TileBlock[] blocks, WritableRaster wr) {
/* 549 */     if (blocks.length == 1) {
/* 550 */       TileBlock curr = blocks[0];
/* 551 */       int xloc = curr.getXLoc() * this.tileWidth + this.tileGridXOff;
/* 552 */       int yloc = curr.getYLoc() * this.tileHeight + this.tileGridYOff;
/* 553 */       if (xloc == wr.getMinX() && yloc == wr.getMinY()) {
/*     */ 
/*     */         
/* 556 */         drawBlockInPlace(blocks, wr);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 561 */     int workTileWidth = this.tileWidth;
/* 562 */     int workTileHeight = this.tileHeight;
/* 563 */     int maxTileSize = 0;
/* 564 */     for (TileBlock curr : blocks) {
/* 565 */       int sz = curr.getWidth() * workTileWidth * curr.getHeight() * workTileHeight;
/*     */       
/* 567 */       if (sz > maxTileSize) {
/* 568 */         maxTileSize = sz;
/*     */       }
/*     */     } 
/* 571 */     DataBufferInt dbi = new DataBufferInt(maxTileSize);
/* 572 */     int[] masks = { 16711680, 65280, 255, -16777216 };
/* 573 */     boolean use_INT_PACK = GraphicsUtil.is_INT_PACK_Data(wr.getSampleModel(), false);
/*     */ 
/*     */     
/* 576 */     Thread currentThread = Thread.currentThread();
/*     */     
/* 578 */     for (TileBlock curr : blocks) {
/* 579 */       int xloc = curr.getXLoc() * workTileWidth + this.tileGridXOff;
/* 580 */       int yloc = curr.getYLoc() * workTileHeight + this.tileGridYOff;
/* 581 */       Rectangle tb = new Rectangle(xloc, yloc, curr.getWidth() * workTileWidth, curr.getHeight() * workTileHeight);
/*     */ 
/*     */       
/* 584 */       tb = tb.intersection(this.bounds);
/* 585 */       Point loc = new Point(tb.x, tb.y);
/* 586 */       WritableRaster child = Raster.createPackedRaster(dbi, tb.width, tb.height, tb.width, masks, loc);
/* 587 */       genRect(child);
/* 588 */       if (use_INT_PACK) {
/* 589 */         GraphicsUtil.copyData_INT_PACK(child, wr);
/*     */       } else {
/* 591 */         GraphicsUtil.copyData_FALLBACK(child, wr);
/*     */       } 
/*     */ 
/*     */       
/* 595 */       if (HaltingThread.hasBeenHalted(currentThread)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawBlockInPlace(TileBlock[] blocks, WritableRaster wr) {
/* 606 */     Thread currentThread = Thread.currentThread();
/*     */     
/* 608 */     int workTileWidth = this.tileWidth;
/* 609 */     int workTileHeight = this.tileHeight;
/*     */     
/* 611 */     for (TileBlock curr : blocks) {
/*     */ 
/*     */       
/* 614 */       int xloc = curr.getXLoc() * workTileWidth + this.tileGridXOff;
/* 615 */       int yloc = curr.getYLoc() * workTileHeight + this.tileGridYOff;
/* 616 */       Rectangle tb = new Rectangle(xloc, yloc, curr.getWidth() * workTileWidth, curr.getHeight() * workTileHeight);
/*     */ 
/*     */       
/* 619 */       tb = tb.intersection(this.bounds);
/*     */       
/* 621 */       WritableRaster child = wr.createWritableChild(tb.x, tb.y, tb.width, tb.height, tb.x, tb.y, (int[])null);
/*     */ 
/*     */ 
/*     */       
/* 625 */       genRect(child);
/*     */ 
/*     */       
/* 628 */       if (HaltingThread.hasBeenHalted(currentThread))
/*     */         return; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/AbstractTiledRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */