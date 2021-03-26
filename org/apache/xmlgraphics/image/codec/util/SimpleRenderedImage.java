/*     */ package org.apache.xmlgraphics.image.codec.util;
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
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   protected int tileGridXOffset;
/*     */   protected int tileGridYOffset;
/*     */   protected SampleModel sampleModel;
/*     */   protected ColorModel colorModel;
/*  87 */   protected Map properties = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinX() {
/*  93 */     return this.minX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMaxX() {
/* 103 */     return getMinX() + getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinY() {
/* 108 */     return this.minY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getMaxY() {
/* 118 */     return getMinY() + getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 123 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 128 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 133 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileWidth() {
/* 139 */     return this.tileWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTileHeight() {
/* 144 */     return this.tileHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileGridXOffset() {
/* 151 */     return this.tileGridXOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileGridYOffset() {
/* 158 */     return this.tileGridYOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinTileX() {
/* 167 */     return convertXToTileX(getMinX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTileX() {
/* 176 */     return convertXToTileX(getMaxX() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumXTiles() {
/* 186 */     return getMaxTileX() - getMinTileX() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinTileY() {
/* 195 */     return convertYToTileY(getMinY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTileY() {
/* 204 */     return convertYToTileY(getMaxY() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumYTiles() {
/* 214 */     return getMaxTileY() - getMinTileY() + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 219 */     return this.sampleModel;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/* 224 */     return this.colorModel;
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
/* 237 */     name = name.toLowerCase(Locale.getDefault());
/* 238 */     return this.properties.get(name);
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
/* 249 */     String[] names = new String[this.properties.size()];
/* 250 */     this.properties.keySet().toArray((Object[])names);
/* 251 */     return names;
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
/* 268 */     String[] propertyNames = getPropertyNames();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     prefix = prefix.toLowerCase(Locale.getDefault());
/*     */     
/* 275 */     List<String> names = new ArrayList();
/* 276 */     for (int i = 0; i < propertyNames.length; i++) {
/* 277 */       if (propertyNames[i].startsWith(prefix)) {
/* 278 */         names.add(propertyNames[i]);
/*     */       }
/*     */     } 
/*     */     
/* 282 */     if (names.size() == 0) {
/* 283 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 287 */     String[] prefixNames = new String[names.size()];
/* 288 */     names.toArray(prefixNames);
/* 289 */     return prefixNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertXToTileX(int x, int tileGridXOffset, int tileWidth) {
/* 300 */     x -= tileGridXOffset;
/* 301 */     if (x < 0) {
/* 302 */       x += 1 - tileWidth;
/*     */     }
/* 304 */     return x / tileWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertYToTileY(int y, int tileGridYOffset, int tileHeight) {
/* 313 */     y -= tileGridYOffset;
/* 314 */     if (y < 0) {
/* 315 */       y += 1 - tileHeight;
/*     */     }
/* 317 */     return y / tileHeight;
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
/*     */   public int convertXToTileX(int x) {
/* 329 */     return convertXToTileX(x, getTileGridXOffset(), getTileWidth());
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
/*     */   public int convertYToTileY(int y) {
/* 341 */     return convertYToTileY(y, getTileGridYOffset(), getTileHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int tileXToX(int tx, int tileGridXOffset, int tileWidth) {
/* 350 */     return tx * tileWidth + tileGridXOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int tileYToY(int ty, int tileGridYOffset, int tileHeight) {
/* 359 */     return ty * tileHeight + tileGridYOffset;
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
/* 371 */     return tx * this.tileWidth + this.tileGridXOffset;
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
/* 383 */     return ty * this.tileHeight + this.tileGridYOffset;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/* 387 */     return null;
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
/* 407 */     Rectangle rect = new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */     
/* 409 */     return getData(rect);
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
/* 430 */     int startX = convertXToTileX(bounds.x);
/* 431 */     int startY = convertYToTileY(bounds.y);
/* 432 */     int endX = convertXToTileX(bounds.x + bounds.width - 1);
/* 433 */     int endY = convertYToTileY(bounds.y + bounds.height - 1);
/*     */ 
/*     */     
/* 436 */     if (startX == endX && startY == endY) {
/* 437 */       Raster tile = getTile(startX, startY);
/* 438 */       return tile.createChild(bounds.x, bounds.y, bounds.width, bounds.height, bounds.x, bounds.y, null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 443 */     SampleModel sm = this.sampleModel.createCompatibleSampleModel(bounds.width, bounds.height);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 448 */     WritableRaster dest = Raster.createWritableRaster(sm, bounds.getLocation());
/*     */ 
/*     */     
/* 451 */     for (int j = startY; j <= endY; j++) {
/* 452 */       for (int i = startX; i <= endX; i++) {
/* 453 */         Raster tile = getTile(i, j);
/* 454 */         Rectangle intersectRect = bounds.intersection(tile.getBounds());
/*     */         
/* 456 */         Raster liveRaster = tile.createChild(intersectRect.x, intersectRect.y, intersectRect.width, intersectRect.height, intersectRect.x, intersectRect.y, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 463 */         dest.setDataElements(0, 0, liveRaster);
/*     */       } 
/*     */     } 
/* 466 */     return dest;
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
/* 491 */     if (dest == null) {
/* 492 */       bounds = getBounds();
/* 493 */       Point p = new Point(this.minX, this.minY);
/*     */       
/* 495 */       SampleModel sm = this.sampleModel.createCompatibleSampleModel(this.width, this.height);
/*     */       
/* 497 */       dest = Raster.createWritableRaster(sm, p);
/*     */     } else {
/* 499 */       bounds = dest.getBounds();
/*     */     } 
/*     */     
/* 502 */     int startX = convertXToTileX(bounds.x);
/* 503 */     int startY = convertYToTileY(bounds.y);
/* 504 */     int endX = convertXToTileX(bounds.x + bounds.width - 1);
/* 505 */     int endY = convertYToTileY(bounds.y + bounds.height - 1);
/*     */     
/* 507 */     for (int j = startY; j <= endY; j++) {
/* 508 */       for (int i = startX; i <= endX; i++) {
/* 509 */         Raster tile = getTile(i, j);
/* 510 */         Rectangle intersectRect = bounds.intersection(tile.getBounds());
/*     */         
/* 512 */         Raster liveRaster = tile.createChild(intersectRect.x, intersectRect.y, intersectRect.width, intersectRect.height, intersectRect.x, intersectRect.y, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 527 */         dest.setDataElements(0, 0, liveRaster);
/*     */       } 
/*     */     } 
/* 530 */     return dest;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/SimpleRenderedImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */