/*     */ package org.apache.batik.ext.awt.image.codec.util;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SimpleRenderedImage
/*     */   implements RenderedImage
/*     */ {
/*     */   protected int minX;
/*     */   protected int minY;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected int tileWidth;
/*     */   protected int tileHeight;
/*  68 */   protected int tileGridXOffset = 0;
/*     */ 
/*     */   
/*  71 */   protected int tileGridYOffset = 0;
/*     */ 
/*     */   
/*  74 */   protected SampleModel sampleModel = null;
/*     */ 
/*     */   
/*  77 */   protected ColorModel colorModel = null;
/*     */ 
/*     */   
/*  80 */   protected List sources = new ArrayList();
/*     */ 
/*     */   
/*  83 */   protected Map properties = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinX() {
/*  89 */     return this.minX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMaxX() {
/*  99 */     return getMinX() + getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinY() {
/* 104 */     return this.minY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMaxY() {
/* 114 */     return getMinY() + getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 119 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 124 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 129 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileWidth() {
/* 135 */     return this.tileWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTileHeight() {
/* 140 */     return this.tileHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileGridXOffset() {
/* 147 */     return this.tileGridXOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileGridYOffset() {
/* 154 */     return this.tileGridYOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinTileX() {
/* 163 */     return XToTileX(getMinX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTileX() {
/* 172 */     return XToTileX(getMaxX() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumXTiles() {
/* 182 */     return getMaxTileX() - getMinTileX() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinTileY() {
/* 191 */     return YToTileY(getMinY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTileY() {
/* 200 */     return YToTileY(getMaxY() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumYTiles() {
/* 210 */     return getMaxTileY() - getMinTileY() + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 215 */     return this.sampleModel;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/* 220 */     return this.colorModel;
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
/*     */   public Object getProperty(String name) {
/* 233 */     name = name.toLowerCase();
/* 234 */     return this.properties.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getPropertyNames() {
/* 245 */     String[] names = new String[this.properties.size()];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     this.properties.keySet().toArray((Object[])names);
/* 254 */     return names;
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
/*     */   public String[] getPropertyNames(String prefix) {
/* 271 */     String[] propertyNames = getPropertyNames();
/* 272 */     if (propertyNames == null) {
/* 273 */       return null;
/*     */     }
/*     */     
/* 276 */     prefix = prefix.toLowerCase();
/*     */     
/* 278 */     List<String> names = new ArrayList();
/* 279 */     for (String propertyName : propertyNames) {
/* 280 */       if (propertyName.startsWith(prefix)) {
/* 281 */         names.add(propertyName);
/*     */       }
/*     */     } 
/*     */     
/* 285 */     if (names.size() == 0) {
/* 286 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 290 */     String[] prefixNames = new String[names.size()];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     names.toArray(prefixNames);
/*     */     
/* 298 */     return prefixNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int XToTileX(int x, int tileGridXOffset, int tileWidth) {
/* 309 */     x -= tileGridXOffset;
/* 310 */     if (x < 0) {
/* 311 */       x += 1 - tileWidth;
/*     */     }
/* 313 */     return x / tileWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int YToTileY(int y, int tileGridYOffset, int tileHeight) {
/* 322 */     y -= tileGridYOffset;
/* 323 */     if (y < 0) {
/* 324 */       y += 1 - tileHeight;
/*     */     }
/* 326 */     return y / tileHeight;
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
/*     */   public int XToTileX(int x) {
/* 338 */     return XToTileX(x, getTileGridXOffset(), getTileWidth());
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
/*     */   public int YToTileY(int y) {
/* 350 */     return YToTileY(y, getTileGridYOffset(), getTileHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int tileXToX(int tx, int tileGridXOffset, int tileWidth) {
/* 359 */     return tx * tileWidth + tileGridXOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int tileYToY(int ty, int tileGridYOffset, int tileHeight) {
/* 368 */     return ty * tileHeight + tileGridYOffset;
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
/*     */   public int tileXToX(int tx) {
/* 380 */     return tx * this.tileWidth + this.tileGridXOffset;
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
/*     */   public int tileYToY(int ty) {
/* 392 */     return ty * this.tileHeight + this.tileGridYOffset;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/* 396 */     return null;
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
/*     */   public Raster getData() {
/* 416 */     Rectangle rect = new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */     
/* 418 */     return getData(rect);
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
/*     */   public Raster getData(Rectangle bounds) {
/* 439 */     int startX = XToTileX(bounds.x);
/* 440 */     int startY = YToTileY(bounds.y);
/* 441 */     int endX = XToTileX(bounds.x + bounds.width - 1);
/* 442 */     int endY = YToTileY(bounds.y + bounds.height - 1);
/*     */ 
/*     */     
/* 445 */     if (startX == endX && startY == endY) {
/* 446 */       Raster tile = getTile(startX, startY);
/* 447 */       return tile.createChild(bounds.x, bounds.y, bounds.width, bounds.height, bounds.x, bounds.y, null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 452 */     SampleModel sm = this.sampleModel.createCompatibleSampleModel(bounds.width, bounds.height);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 457 */     WritableRaster dest = Raster.createWritableRaster(sm, bounds.getLocation());
/*     */ 
/*     */     
/* 460 */     for (int j = startY; j <= endY; j++) {
/* 461 */       for (int i = startX; i <= endX; i++) {
/* 462 */         Raster tile = getTile(i, j);
/* 463 */         Rectangle intersectRect = bounds.intersection(tile.getBounds());
/*     */         
/* 465 */         Raster liveRaster = tile.createChild(intersectRect.x, intersectRect.y, intersectRect.width, intersectRect.height, intersectRect.x, intersectRect.y, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 472 */         dest.setDataElements(0, 0, liveRaster);
/*     */       } 
/*     */     } 
/* 475 */     return dest;
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
/*     */   public WritableRaster copyData(WritableRaster dest) {
/*     */     Rectangle bounds;
/* 500 */     if (dest == null) {
/* 501 */       bounds = getBounds();
/* 502 */       Point p = new Point(this.minX, this.minY);
/*     */       
/* 504 */       SampleModel sm = this.sampleModel.createCompatibleSampleModel(this.width, this.height);
/*     */       
/* 506 */       dest = Raster.createWritableRaster(sm, p);
/*     */     } else {
/* 508 */       bounds = dest.getBounds();
/*     */     } 
/*     */     
/* 511 */     int startX = XToTileX(bounds.x);
/* 512 */     int startY = YToTileY(bounds.y);
/* 513 */     int endX = XToTileX(bounds.x + bounds.width - 1);
/* 514 */     int endY = YToTileY(bounds.y + bounds.height - 1);
/*     */     
/* 516 */     for (int j = startY; j <= endY; j++) {
/* 517 */       for (int i = startX; i <= endX; i++) {
/* 518 */         Raster tile = getTile(i, j);
/* 519 */         Rectangle intersectRect = bounds.intersection(tile.getBounds());
/*     */         
/* 521 */         Raster liveRaster = tile.createChild(intersectRect.x, intersectRect.y, intersectRect.width, intersectRect.height, intersectRect.x, intersectRect.y, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 536 */         dest.setDataElements(0, 0, liveRaster);
/*     */       } 
/*     */     } 
/* 539 */     return dest;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/SimpleRenderedImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */