/*     */ package jp.cssj.sakae.b.b.a;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public abstract class d
/*     */   implements RenderedImage
/*     */ {
/*     */   protected int k;
/*     */   protected int l;
/*     */   protected int m;
/*     */   protected int n;
/*     */   protected int o;
/*     */   protected int p;
/*  62 */   protected int q = 0;
/*     */ 
/*     */   
/*  65 */   protected int r = 0;
/*     */ 
/*     */   
/*  68 */   protected SampleModel s = null;
/*     */ 
/*     */   
/*  71 */   protected ColorModel t = null;
/*     */ 
/*     */   
/*  74 */   protected Vector<RenderedImage> u = new Vector<>();
/*     */ 
/*     */   
/*  77 */   protected Map<String, Object> v = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinX() {
/*  85 */     return this.k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int a() {
/*  94 */     return getMinX() + getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinY() {
/*  99 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int b() {
/* 108 */     return getMinY() + getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 113 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 118 */     return this.n;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle c() {
/* 123 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTileWidth() {
/* 128 */     return this.o;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTileHeight() {
/* 133 */     return this.p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileGridXOffset() {
/* 140 */     return this.q;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileGridYOffset() {
/* 147 */     return this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinTileX() {
/* 156 */     return a(getMinX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int d() {
/* 165 */     return a(a() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumXTiles() {
/* 174 */     return d() - getMinTileX() + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinTileY() {
/* 183 */     return b(getMinY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int e() {
/* 192 */     return b(b() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumYTiles() {
/* 201 */     return e() - getMinTileY() + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 206 */     return this.s;
/*     */   }
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/* 211 */     return this.t;
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
/*     */   public Object getProperty(String name) {
/* 225 */     name = name.toLowerCase();
/* 226 */     return this.v.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getPropertyNames() {
/* 236 */     String[] names = new String[this.v.size()];
/* 237 */     int index = 0;
/*     */     
/* 239 */     Iterator<String> e = this.v.keySet().iterator();
/* 240 */     while (e.hasNext()) {
/* 241 */       String name = e.next();
/* 242 */       names[index++] = name;
/*     */     } 
/*     */     
/* 245 */     return names;
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
/*     */   public String[] a(String prefix) {
/* 261 */     String[] propertyNames = getPropertyNames();
/* 262 */     if (propertyNames == null) {
/* 263 */       return null;
/*     */     }
/*     */     
/* 266 */     prefix = prefix.toLowerCase();
/*     */     
/* 268 */     Vector<String> names = new Vector<>();
/* 269 */     for (int i = 0; i < propertyNames.length; i++) {
/* 270 */       if (propertyNames[i].startsWith(prefix)) {
/* 271 */         names.addElement(propertyNames[i]);
/*     */       }
/*     */     } 
/*     */     
/* 275 */     if (names.size() == 0) {
/* 276 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 280 */     String[] prefixNames = new String[names.size()];
/* 281 */     int count = 0;
/* 282 */     for (Iterator<String> it = names.iterator(); it.hasNext();) {
/* 283 */       prefixNames[count++] = it.next();
/*     */     }
/*     */     
/* 286 */     return prefixNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(int x, int tileGridXOffset, int tileWidth) {
/* 296 */     x -= tileGridXOffset;
/* 297 */     if (x < 0) {
/* 298 */       x += 1 - tileWidth;
/*     */     }
/* 300 */     return x / tileWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int b(int y, int tileGridYOffset, int tileHeight) {
/* 308 */     y -= tileGridYOffset;
/* 309 */     if (y < 0) {
/* 310 */       y += 1 - tileHeight;
/*     */     }
/* 312 */     return y / tileHeight;
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
/*     */   public int a(int x) {
/* 324 */     return a(x, getTileGridXOffset(), getTileWidth());
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
/*     */   public int b(int y) {
/* 336 */     return b(y, getTileGridYOffset(), getTileHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int c(int tx, int tileGridXOffset, int tileWidth) {
/* 345 */     return tx * tileWidth + tileGridXOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int d(int ty, int tileGridYOffset, int tileHeight) {
/* 354 */     return ty * tileHeight + tileGridYOffset;
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
/*     */   public int c(int tx) {
/* 367 */     return tx * this.o + this.q;
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
/*     */   public int d(int ty) {
/* 380 */     return ty * this.p + this.r;
/*     */   }
/*     */   
/*     */   public Vector<RenderedImage> getSources() {
/* 384 */     return null;
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
/*     */   public Raster getData() {
/* 403 */     Rectangle rect = new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/* 404 */     return getData(rect);
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
/*     */   public Raster getData(Rectangle bounds) {
/* 424 */     int startX = a(bounds.x);
/* 425 */     int startY = b(bounds.y);
/* 426 */     int endX = a(bounds.x + bounds.width - 1);
/* 427 */     int endY = b(bounds.y + bounds.height - 1);
/*     */ 
/*     */     
/* 430 */     if (startX == endX && startY == endY) {
/* 431 */       Raster tile = getTile(startX, startY);
/* 432 */       return tile.createChild(bounds.x, bounds.y, bounds.width, bounds.height, bounds.x, bounds.y, null);
/*     */     } 
/*     */     
/* 435 */     SampleModel sm = this.s.createCompatibleSampleModel(bounds.width, bounds.height);
/*     */ 
/*     */     
/* 438 */     WritableRaster dest = Raster.createWritableRaster(sm, bounds.getLocation());
/*     */     
/* 440 */     for (int j = startY; j <= endY; j++) {
/* 441 */       for (int i = startX; i <= endX; i++) {
/* 442 */         Raster tile = getTile(i, j);
/* 443 */         Rectangle intersectRect = bounds.intersection(tile.getBounds());
/* 444 */         Raster liveRaster = tile.createChild(intersectRect.x, intersectRect.y, intersectRect.width, intersectRect.height, intersectRect.x, intersectRect.y, null);
/*     */         
/* 446 */         dest.setDataElements(0, 0, liveRaster);
/*     */       } 
/*     */     } 
/* 449 */     return dest;
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
/*     */   public WritableRaster copyData(WritableRaster dest) {
/*     */     Rectangle bounds;
/* 473 */     if (dest == null) {
/* 474 */       bounds = c();
/* 475 */       Point p = new Point(this.k, this.l);
/*     */       
/* 477 */       SampleModel sm = this.s.createCompatibleSampleModel(this.m, this.n);
/* 478 */       dest = Raster.createWritableRaster(sm, p);
/*     */     } else {
/* 480 */       bounds = dest.getBounds();
/*     */     } 
/*     */     
/* 483 */     int startX = a(bounds.x);
/* 484 */     int startY = b(bounds.y);
/* 485 */     int endX = a(bounds.x + bounds.width - 1);
/* 486 */     int endY = b(bounds.y + bounds.height - 1);
/*     */     
/* 488 */     for (int j = startY; j <= endY; j++) {
/* 489 */       for (int i = startX; i <= endX; i++) {
/* 490 */         Raster tile = getTile(i, j);
/* 491 */         Rectangle intersectRect = bounds.intersection(tile.getBounds());
/* 492 */         Raster liveRaster = tile.createChild(intersectRect.x, intersectRect.y, intersectRect.width, intersectRect.height, intersectRect.x, intersectRect.y, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 501 */         dest.setDataElements(0, 0, liveRaster);
/*     */       } 
/*     */     } 
/* 504 */     return dest;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/b/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */