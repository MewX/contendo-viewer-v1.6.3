/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.image.Raster;
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
/*     */ public class TileGrid
/*     */   implements TileStore
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   private static final boolean COUNT = false;
/*     */   private int xSz;
/*     */   private int ySz;
/*     */   private int minTileX;
/*     */   private int minTileY;
/*  38 */   private TileLRUMember[][] rasters = (TileLRUMember[][])null;
/*  39 */   private TileGenerator source = null;
/*  40 */   private LRUCache cache = null;
/*     */   
/*     */   static int requests;
/*     */   static int misses;
/*     */   
/*     */   public TileGrid(int minTileX, int minTileY, int xSz, int ySz, TileGenerator source, LRUCache cache) {
/*  46 */     this.cache = cache;
/*  47 */     this.source = source;
/*  48 */     this.minTileX = minTileX;
/*  49 */     this.minTileY = minTileY;
/*  50 */     this.xSz = xSz;
/*  51 */     this.ySz = ySz;
/*     */     
/*  53 */     this.rasters = new TileLRUMember[ySz][];
/*     */   }
/*     */   public void setTile(int x, int y, Raster ras) {
/*     */     TileLRUMember item;
/*  57 */     x -= this.minTileX;
/*  58 */     y -= this.minTileY;
/*  59 */     if (x < 0 || x >= this.xSz)
/*  60 */       return;  if (y < 0 || y >= this.ySz)
/*     */       return; 
/*  62 */     TileLRUMember[] row = this.rasters[y];
/*     */     
/*  64 */     if (ras == null) {
/*     */       
/*  66 */       if (row == null)
/*  67 */         return;  item = row[x];
/*  68 */       if (item == null)
/*     */         return; 
/*  70 */       row[x] = null;
/*  71 */       this.cache.remove(item);
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     if (row != null) {
/*  76 */       item = row[x];
/*  77 */       if (item == null) {
/*  78 */         item = new TileLRUMember();
/*  79 */         row[x] = item;
/*     */       } 
/*     */     } else {
/*  82 */       row = new TileLRUMember[this.xSz];
/*  83 */       item = new TileLRUMember();
/*  84 */       row[x] = item;
/*  85 */       this.rasters[y] = row;
/*     */     } 
/*  87 */     item.setRaster(ras);
/*     */     
/*  89 */     this.cache.add(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getTileNoCompute(int x, int y) {
/*  98 */     x -= this.minTileX;
/*  99 */     y -= this.minTileY;
/* 100 */     if (x < 0 || x >= this.xSz) return null; 
/* 101 */     if (y < 0 || y >= this.ySz) return null;
/*     */     
/* 103 */     TileLRUMember[] row = this.rasters[y];
/* 104 */     if (row == null)
/* 105 */       return null; 
/* 106 */     TileLRUMember item = row[x];
/* 107 */     if (item == null)
/* 108 */       return null; 
/* 109 */     Raster ret = item.retrieveRaster();
/* 110 */     if (ret != null)
/* 111 */       this.cache.add(item); 
/* 112 */     return ret;
/*     */   }
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 116 */     x -= this.minTileX;
/* 117 */     y -= this.minTileY;
/* 118 */     if (x < 0 || x >= this.xSz) return null; 
/* 119 */     if (y < 0 || y >= this.ySz) return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     Raster ras = null;
/* 126 */     TileLRUMember[] row = this.rasters[y];
/* 127 */     TileLRUMember item = null;
/* 128 */     if (row != null) {
/* 129 */       item = row[x];
/* 130 */       if (item != null) {
/* 131 */         ras = item.retrieveRaster();
/*     */       } else {
/* 133 */         item = new TileLRUMember();
/* 134 */         row[x] = item;
/*     */       } 
/*     */     } else {
/* 137 */       row = new TileLRUMember[this.xSz];
/* 138 */       this.rasters[y] = row;
/* 139 */       item = new TileLRUMember();
/* 140 */       row[x] = item;
/*     */     } 
/*     */     
/* 143 */     if (ras == null) {
/*     */ 
/*     */ 
/*     */       
/* 147 */       ras = this.source.genTile(x + this.minTileX, y + this.minTileY);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       if (HaltingThread.hasBeenHalted()) {
/* 153 */         return ras;
/*     */       }
/* 155 */       item.setRaster(ras);
/*     */     } 
/*     */ 
/*     */     
/* 159 */     this.cache.add(item);
/*     */     
/* 161 */     return ras;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/TileGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */