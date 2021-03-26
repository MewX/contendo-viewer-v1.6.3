/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractRed
/*     */   implements CachableRed
/*     */ {
/*     */   protected Rectangle bounds;
/*     */   protected Vector srcs;
/*     */   protected Map props;
/*     */   protected SampleModel sm;
/*     */   protected ColorModel cm;
/*     */   protected int tileGridXOff;
/*     */   protected int tileGridYOff;
/*     */   protected int tileWidth;
/*     */   protected int tileHeight;
/*     */   protected int minTileX;
/*     */   protected int minTileY;
/*     */   protected int numXTiles;
/*     */   protected int numYTiles;
/*     */   
/*     */   protected AbstractRed() {}
/*     */   
/*     */   protected AbstractRed(Rectangle bounds, Map props) {
/*  86 */     init((CachableRed)null, bounds, (ColorModel)null, (SampleModel)null, bounds.x, bounds.y, props);
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
/*     */   protected AbstractRed(CachableRed src, Map props) {
/*  98 */     init(src, src.getBounds(), src.getColorModel(), src.getSampleModel(), src.getTileGridXOffset(), src.getTileGridYOffset(), props);
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
/*     */   protected AbstractRed(CachableRed src, Rectangle bounds, Map props) {
/* 113 */     init(src, bounds, src.getColorModel(), src.getSampleModel(), src.getTileGridXOffset(), src.getTileGridYOffset(), props);
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
/*     */   protected AbstractRed(CachableRed src, Rectangle bounds, ColorModel cm, SampleModel sm, Map props) {
/* 135 */     init(src, bounds, cm, sm, (src == null) ? 0 : src.getTileGridXOffset(), (src == null) ? 0 : src.getTileGridYOffset(), props);
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
/*     */   protected AbstractRed(CachableRed src, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 162 */     init(src, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
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
/* 189 */     this.srcs = new Vector(1);
/* 190 */     if (src != null) {
/* 191 */       this.srcs.add(src);
/* 192 */       if (bounds == null) bounds = src.getBounds(); 
/* 193 */       if (cm == null) cm = src.getColorModel(); 
/* 194 */       if (sm == null) sm = src.getSampleModel();
/*     */     
/*     */     } 
/* 197 */     this.bounds = bounds;
/* 198 */     this.tileGridXOff = tileGridXOff;
/* 199 */     this.tileGridYOff = tileGridYOff;
/*     */     
/* 201 */     this.props = new HashMap<Object, Object>();
/* 202 */     if (props != null) {
/* 203 */       this.props.putAll(props);
/*     */     }
/*     */     
/* 206 */     if (cm == null) {
/* 207 */       cm = new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 212 */     this.cm = cm;
/*     */     
/* 214 */     if (sm == null)
/* 215 */       sm = cm.createCompatibleSampleModel(bounds.width, bounds.height); 
/* 216 */     this.sm = sm;
/*     */ 
/*     */     
/* 219 */     updateTileGridInfo();
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
/*     */   protected AbstractRed(List srcs, Rectangle bounds, Map props) {
/* 232 */     init(srcs, bounds, (ColorModel)null, (SampleModel)null, bounds.x, bounds.y, props);
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
/*     */   protected AbstractRed(List srcs, Rectangle bounds, ColorModel cm, SampleModel sm, Map props) {
/* 253 */     init(srcs, bounds, cm, sm, bounds.x, bounds.y, props);
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
/*     */   protected AbstractRed(List srcs, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 279 */     init(srcs, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
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
/*     */   protected void init(List<CachableRed> srcs, Rectangle bounds, ColorModel cm, SampleModel sm, int tileGridXOff, int tileGridYOff, Map props) {
/* 304 */     this.srcs = new Vector();
/* 305 */     if (srcs != null) {
/* 306 */       this.srcs.addAll(srcs);
/*     */     }
/*     */     
/* 309 */     if (srcs.size() != 0) {
/* 310 */       CachableRed src = srcs.get(0);
/* 311 */       if (bounds == null) bounds = src.getBounds(); 
/* 312 */       if (cm == null) cm = src.getColorModel(); 
/* 313 */       if (sm == null) sm = src.getSampleModel();
/*     */     
/*     */     } 
/* 316 */     this.bounds = bounds;
/* 317 */     this.tileGridXOff = tileGridXOff;
/* 318 */     this.tileGridYOff = tileGridYOff;
/* 319 */     this.props = new HashMap<Object, Object>();
/* 320 */     if (props != null) {
/* 321 */       this.props.putAll(props);
/*     */     }
/*     */     
/* 324 */     if (cm == null) {
/* 325 */       cm = new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 330 */     this.cm = cm;
/*     */     
/* 332 */     if (sm == null)
/* 333 */       sm = cm.createCompatibleSampleModel(bounds.width, bounds.height); 
/* 334 */     this.sm = sm;
/*     */ 
/*     */     
/* 337 */     updateTileGridInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateTileGridInfo() {
/* 347 */     this.tileWidth = this.sm.getWidth();
/* 348 */     this.tileHeight = this.sm.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     this.minTileX = getXTile(this.bounds.x);
/* 355 */     this.minTileY = getYTile(this.bounds.y);
/*     */     
/* 357 */     int x1 = this.bounds.x + this.bounds.width - 1;
/* 358 */     int maxTileX = getXTile(x1);
/* 359 */     this.numXTiles = maxTileX - this.minTileX + 1;
/*     */     
/* 361 */     int y1 = this.bounds.y + this.bounds.height - 1;
/* 362 */     int maxTileY = getYTile(y1);
/* 363 */     this.numYTiles = maxTileY - this.minTileY + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 368 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getSources() {
/* 375 */     return this.srcs;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 379 */     return this.cm;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 383 */     return this.sm;
/*     */   }
/*     */   
/*     */   public int getMinX() {
/* 387 */     return this.bounds.x;
/*     */   }
/*     */   public int getMinY() {
/* 390 */     return this.bounds.y;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 394 */     return this.bounds.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 398 */     return this.bounds.height;
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 402 */     return this.tileWidth;
/*     */   }
/*     */   
/*     */   public int getTileHeight() {
/* 406 */     return this.tileHeight;
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 410 */     return this.tileGridXOff;
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 414 */     return this.tileGridYOff;
/*     */   }
/*     */   
/*     */   public int getMinTileX() {
/* 418 */     return this.minTileX;
/*     */   }
/*     */   
/*     */   public int getMinTileY() {
/* 422 */     return this.minTileY;
/*     */   }
/*     */   
/*     */   public int getNumXTiles() {
/* 426 */     return this.numXTiles;
/*     */   }
/*     */   
/*     */   public int getNumYTiles() {
/* 430 */     return this.numYTiles;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 434 */     Object ret = this.props.get(name);
/* 435 */     if (ret != null) return ret; 
/* 436 */     for (Object src : this.srcs) {
/* 437 */       RenderedImage ri = (RenderedImage)src;
/* 438 */       ret = ri.getProperty(name);
/* 439 */       if (ret != null) return ret; 
/*     */     } 
/* 441 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 445 */     Set keys = this.props.keySet();
/* 446 */     String[] ret = new String[keys.size()];
/* 447 */     keys.toArray((Object[])ret);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 455 */     for (Object src : this.srcs) {
/* 456 */       RenderedImage ri = (RenderedImage)src;
/* 457 */       String[] srcProps = ri.getPropertyNames();
/* 458 */       if (srcProps.length != 0) {
/* 459 */         String[] tmp = new String[ret.length + srcProps.length];
/* 460 */         System.arraycopy(ret, 0, tmp, 0, ret.length);
/*     */         
/* 462 */         System.arraycopy(srcProps, 0, tmp, ret.length, srcProps.length);
/* 463 */         ret = tmp;
/*     */       } 
/*     */     } 
/*     */     
/* 467 */     return ret;
/*     */   }
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle outputRgn) {
/* 471 */     if (srcIndex < 0 || srcIndex > this.srcs.size()) {
/* 472 */       throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */     }
/*     */ 
/*     */     
/* 476 */     if (!outputRgn.intersects(this.bounds)) {
/* 477 */       return new Rectangle();
/*     */     }
/*     */ 
/*     */     
/* 481 */     return outputRgn.intersection(this.bounds);
/*     */   }
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle inputRgn) {
/* 485 */     if (srcIndex != 0) {
/* 486 */       throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */     }
/*     */ 
/*     */     
/* 490 */     if (!inputRgn.intersects(this.bounds)) {
/* 491 */       return new Rectangle();
/*     */     }
/*     */ 
/*     */     
/* 495 */     return inputRgn.intersection(this.bounds);
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
/*     */   public Raster getTile(int tileX, int tileY) {
/* 507 */     WritableRaster wr = makeTile(tileX, tileY);
/* 508 */     return copyData(wr);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 512 */     return getData(this.bounds);
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 516 */     SampleModel smRet = this.sm.createCompatibleSampleModel(rect.width, rect.height);
/*     */ 
/*     */     
/* 519 */     Point pt = new Point(rect.x, rect.y);
/* 520 */     WritableRaster wr = Raster.createWritableRaster(smRet, pt);
/*     */ 
/*     */     
/* 523 */     return copyData(wr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getXTile(int xloc) {
/* 532 */     int tgx = xloc - this.tileGridXOff;
/*     */     
/* 534 */     if (tgx >= 0) {
/* 535 */       return tgx / this.tileWidth;
/*     */     }
/* 537 */     return (tgx - this.tileWidth + 1) / this.tileWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getYTile(int yloc) {
/* 546 */     int tgy = yloc - this.tileGridYOff;
/*     */     
/* 548 */     if (tgy >= 0) {
/* 549 */       return tgy / this.tileHeight;
/*     */     }
/* 551 */     return (tgy - this.tileHeight + 1) / this.tileHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyToRaster(WritableRaster wr) {
/* 561 */     int tx0 = getXTile(wr.getMinX());
/* 562 */     int ty0 = getYTile(wr.getMinY());
/* 563 */     int tx1 = getXTile(wr.getMinX() + wr.getWidth() - 1);
/* 564 */     int ty1 = getYTile(wr.getMinY() + wr.getHeight() - 1);
/*     */     
/* 566 */     if (tx0 < this.minTileX) tx0 = this.minTileX; 
/* 567 */     if (ty0 < this.minTileY) ty0 = this.minTileY;
/*     */     
/* 569 */     if (tx1 >= this.minTileX + this.numXTiles) tx1 = this.minTileX + this.numXTiles - 1; 
/* 570 */     if (ty1 >= this.minTileY + this.numYTiles) ty1 = this.minTileY + this.numYTiles - 1;
/*     */     
/* 572 */     boolean is_INT_PACK = GraphicsUtil.is_INT_PACK_Data(getSampleModel(), false);
/*     */ 
/*     */     
/* 575 */     for (int y = ty0; y <= ty1; y++) {
/* 576 */       for (int x = tx0; x <= tx1; x++) {
/* 577 */         Raster r = getTile(x, y);
/* 578 */         if (is_INT_PACK) {
/* 579 */           GraphicsUtil.copyData_INT_PACK(r, wr);
/*     */         } else {
/* 581 */           GraphicsUtil.copyData_FALLBACK(r, wr);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster makeTile(int tileX, int tileY) {
/* 599 */     if (tileX < this.minTileX || tileX >= this.minTileX + this.numXTiles || tileY < this.minTileY || tileY >= this.minTileY + this.numYTiles)
/*     */     {
/* 601 */       throw new IndexOutOfBoundsException("Requested Tile (" + tileX + ',' + tileY + ") lies outside the bounds of image");
/*     */     }
/*     */ 
/*     */     
/* 605 */     Point pt = new Point(this.tileGridXOff + tileX * this.tileWidth, this.tileGridYOff + tileY * this.tileHeight);
/*     */ 
/*     */ 
/*     */     
/* 609 */     WritableRaster wr = Raster.createWritableRaster(this.sm, pt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 628 */     int x0 = wr.getMinX();
/* 629 */     int y0 = wr.getMinY();
/* 630 */     int x1 = x0 + wr.getWidth() - 1;
/* 631 */     int y1 = y0 + wr.getHeight() - 1;
/*     */     
/* 633 */     if (x0 < this.bounds.x || x1 >= this.bounds.x + this.bounds.width || y0 < this.bounds.y || y1 >= this.bounds.y + this.bounds.height) {
/*     */ 
/*     */ 
/*     */       
/* 637 */       if (x0 < this.bounds.x) x0 = this.bounds.x; 
/* 638 */       if (y0 < this.bounds.y) y0 = this.bounds.y; 
/* 639 */       if (x1 >= this.bounds.x + this.bounds.width) x1 = this.bounds.x + this.bounds.width - 1; 
/* 640 */       if (y1 >= this.bounds.y + this.bounds.height) y1 = this.bounds.y + this.bounds.height - 1;
/*     */       
/* 642 */       wr = wr.createWritableChild(x0, y0, x1 - x0 + 1, y1 - y0 + 1, x0, y0, (int[])null);
/*     */     } 
/*     */     
/* 645 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void copyBand(Raster src, int srcBand, WritableRaster dst, int dstBand) {
/* 650 */     Rectangle srcR = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*     */     
/* 652 */     Rectangle dstR = new Rectangle(dst.getMinX(), dst.getMinY(), dst.getWidth(), dst.getHeight());
/*     */ 
/*     */     
/* 655 */     Rectangle cpR = srcR.intersection(dstR);
/*     */     
/* 657 */     int[] samples = null;
/* 658 */     for (int y = cpR.y; y < cpR.y + cpR.height; y++) {
/* 659 */       samples = src.getSamples(cpR.x, y, cpR.width, 1, srcBand, samples);
/* 660 */       dst.setSamples(cpR.x, y, cpR.width, 1, dstBand, samples);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/AbstractRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */