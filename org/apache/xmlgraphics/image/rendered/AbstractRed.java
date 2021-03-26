/*     */ package org.apache.xmlgraphics.image.rendered;
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
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import org.apache.xmlgraphics.image.GraphicsUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  93 */     init((CachableRed)null, bounds, (ColorModel)null, (SampleModel)null, bounds.x, bounds.y, props);
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
/* 105 */     init(src, src.getBounds(), src.getColorModel(), src.getSampleModel(), src.getTileGridXOffset(), src.getTileGridYOffset(), props);
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
/* 120 */     init(src, bounds, src.getColorModel(), src.getSampleModel(), src.getTileGridXOffset(), src.getTileGridYOffset(), props);
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
/* 142 */     init(src, bounds, cm, sm, (src == null) ? 0 : src.getTileGridXOffset(), (src == null) ? 0 : src.getTileGridYOffset(), props);
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
/* 169 */     init(src, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
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
/* 196 */     this.srcs = new Vector(1);
/* 197 */     if (src != null) {
/* 198 */       this.srcs.add(src);
/* 199 */       if (bounds == null) {
/* 200 */         bounds = src.getBounds();
/*     */       }
/* 202 */       if (cm == null) {
/* 203 */         cm = src.getColorModel();
/*     */       }
/* 205 */       if (sm == null) {
/* 206 */         sm = src.getSampleModel();
/*     */       }
/*     */     } 
/*     */     
/* 210 */     this.bounds = bounds;
/* 211 */     this.tileGridXOff = tileGridXOff;
/* 212 */     this.tileGridYOff = tileGridYOff;
/*     */     
/* 214 */     this.props = new HashMap<Object, Object>();
/* 215 */     if (props != null) {
/* 216 */       this.props.putAll(props);
/*     */     }
/*     */     
/* 219 */     if (cm == null) {
/* 220 */       cm = new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     this.cm = cm;
/*     */     
/* 228 */     if (sm == null) {
/* 229 */       sm = cm.createCompatibleSampleModel(bounds.width, bounds.height);
/*     */     }
/* 231 */     this.sm = sm;
/*     */ 
/*     */     
/* 234 */     updateTileGridInfo();
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
/* 247 */     init(srcs, bounds, (ColorModel)null, (SampleModel)null, bounds.x, bounds.y, props);
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
/* 268 */     init(srcs, bounds, cm, sm, bounds.x, bounds.y, props);
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
/* 294 */     init(srcs, bounds, cm, sm, tileGridXOff, tileGridYOff, props);
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
/* 319 */     this.srcs = new Vector();
/* 320 */     if (srcs != null) {
/* 321 */       this.srcs.addAll(srcs);
/* 322 */       if (srcs.size() != 0) {
/* 323 */         CachableRed src = srcs.get(0);
/* 324 */         if (bounds == null) {
/* 325 */           bounds = src.getBounds();
/*     */         }
/* 327 */         if (cm == null) {
/* 328 */           cm = src.getColorModel();
/*     */         }
/* 330 */         if (sm == null) {
/* 331 */           sm = src.getSampleModel();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 336 */     this.bounds = bounds;
/* 337 */     this.tileGridXOff = tileGridXOff;
/* 338 */     this.tileGridYOff = tileGridYOff;
/* 339 */     this.props = new HashMap<Object, Object>();
/* 340 */     if (props != null) {
/* 341 */       this.props.putAll(props);
/*     */     }
/*     */     
/* 344 */     if (cm == null) {
/* 345 */       cm = new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { 8 }, false, false, 1, 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     this.cm = cm;
/*     */     
/* 353 */     if (sm == null) {
/* 354 */       sm = cm.createCompatibleSampleModel(bounds.width, bounds.height);
/*     */     }
/* 356 */     this.sm = sm;
/*     */ 
/*     */     
/* 359 */     updateTileGridInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateTileGridInfo() {
/* 369 */     this.tileWidth = this.sm.getWidth();
/* 370 */     this.tileHeight = this.sm.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 379 */     this.minTileX = getXTile(this.bounds.x);
/* 380 */     this.minTileY = getYTile(this.bounds.y);
/*     */     
/* 382 */     int x1 = this.bounds.x + this.bounds.width - 1;
/* 383 */     int maxTileX = getXTile(x1);
/* 384 */     this.numXTiles = maxTileX - this.minTileX + 1;
/*     */     
/* 386 */     int y1 = this.bounds.y + this.bounds.height - 1;
/* 387 */     int maxTileY = getYTile(y1);
/* 388 */     this.numYTiles = maxTileY - this.minTileY + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle getBounds() {
/* 393 */     return new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getSources() {
/* 400 */     return this.srcs;
/*     */   }
/*     */   
/*     */   public ColorModel getColorModel() {
/* 404 */     return this.cm;
/*     */   }
/*     */   
/*     */   public SampleModel getSampleModel() {
/* 408 */     return this.sm;
/*     */   }
/*     */   
/*     */   public int getMinX() {
/* 412 */     return this.bounds.x;
/*     */   }
/*     */   public int getMinY() {
/* 415 */     return this.bounds.y;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 419 */     return this.bounds.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 423 */     return this.bounds.height;
/*     */   }
/*     */   
/*     */   public int getTileWidth() {
/* 427 */     return this.tileWidth;
/*     */   }
/*     */   
/*     */   public int getTileHeight() {
/* 431 */     return this.tileHeight;
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset() {
/* 435 */     return this.tileGridXOff;
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset() {
/* 439 */     return this.tileGridYOff;
/*     */   }
/*     */   
/*     */   public int getMinTileX() {
/* 443 */     return this.minTileX;
/*     */   }
/*     */   
/*     */   public int getMinTileY() {
/* 447 */     return this.minTileY;
/*     */   }
/*     */   
/*     */   public int getNumXTiles() {
/* 451 */     return this.numXTiles;
/*     */   }
/*     */   
/*     */   public int getNumYTiles() {
/* 455 */     return this.numYTiles;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 459 */     Object ret = this.props.get(name);
/* 460 */     if (ret != null) {
/* 461 */       return ret;
/*     */     }
/* 463 */     Iterator<RenderedImage> i = this.srcs.iterator();
/* 464 */     while (i.hasNext()) {
/* 465 */       RenderedImage ri = i.next();
/* 466 */       ret = ri.getProperty(name);
/* 467 */       if (ret != null) {
/* 468 */         return ret;
/*     */       }
/*     */     } 
/* 471 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 475 */     Set keys = this.props.keySet();
/* 476 */     String[] ret = new String[keys.size()];
/* 477 */     keys.toArray((Object[])ret);
/*     */     
/* 479 */     Iterator<RenderedImage> iter = this.srcs.iterator();
/* 480 */     while (iter.hasNext()) {
/* 481 */       RenderedImage ri = iter.next();
/* 482 */       String[] srcProps = ri.getPropertyNames();
/* 483 */       if (srcProps.length != 0) {
/* 484 */         String[] tmp = new String[ret.length + srcProps.length];
/* 485 */         System.arraycopy(ret, 0, tmp, 0, ret.length);
/* 486 */         System.arraycopy(srcProps, 0, tmp, ret.length, srcProps.length);
/* 487 */         ret = tmp;
/*     */       } 
/*     */     } 
/*     */     
/* 491 */     return ret;
/*     */   }
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle outputRgn) {
/* 495 */     if (srcIndex < 0 || srcIndex > this.srcs.size()) {
/* 496 */       throw new IndexOutOfBoundsException("Nonexistent source requested.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 501 */     if (!outputRgn.intersects(this.bounds)) {
/* 502 */       return new Rectangle();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 507 */     return outputRgn.intersection(this.bounds);
/*     */   }
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle inputRgn) {
/* 511 */     if (srcIndex != 0) {
/* 512 */       throw new IndexOutOfBoundsException("Nonexistent source requested.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 517 */     if (!inputRgn.intersects(this.bounds)) {
/* 518 */       return new Rectangle();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 523 */     return inputRgn.intersection(this.bounds);
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
/* 535 */     WritableRaster wr = makeTile(tileX, tileY);
/* 536 */     return copyData(wr);
/*     */   }
/*     */   
/*     */   public Raster getData() {
/* 540 */     return getData(this.bounds);
/*     */   }
/*     */   
/*     */   public Raster getData(Rectangle rect) {
/* 544 */     SampleModel smRet = this.sm.createCompatibleSampleModel(rect.width, rect.height);
/*     */ 
/*     */     
/* 547 */     Point pt = new Point(rect.x, rect.y);
/* 548 */     WritableRaster wr = Raster.createWritableRaster(smRet, pt);
/*     */ 
/*     */     
/* 551 */     return copyData(wr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getXTile(int xloc) {
/* 560 */     int tgx = xloc - this.tileGridXOff;
/*     */     
/* 562 */     if (tgx >= 0) {
/* 563 */       return tgx / this.tileWidth;
/*     */     }
/* 565 */     return (tgx - this.tileWidth + 1) / this.tileWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getYTile(int yloc) {
/* 575 */     int tgy = yloc - this.tileGridYOff;
/*     */     
/* 577 */     if (tgy >= 0) {
/* 578 */       return tgy / this.tileHeight;
/*     */     }
/* 580 */     return (tgy - this.tileHeight + 1) / this.tileHeight;
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
/* 591 */     int tx0 = getXTile(wr.getMinX());
/* 592 */     int ty0 = getYTile(wr.getMinY());
/* 593 */     int tx1 = getXTile(wr.getMinX() + wr.getWidth() - 1);
/* 594 */     int ty1 = getYTile(wr.getMinY() + wr.getHeight() - 1);
/*     */     
/* 596 */     if (tx0 < this.minTileX) {
/* 597 */       tx0 = this.minTileX;
/*     */     }
/* 599 */     if (ty0 < this.minTileY) {
/* 600 */       ty0 = this.minTileY;
/*     */     }
/*     */     
/* 603 */     if (tx1 >= this.minTileX + this.numXTiles) {
/* 604 */       tx1 = this.minTileX + this.numXTiles - 1;
/*     */     }
/* 606 */     if (ty1 >= this.minTileY + this.numYTiles) {
/* 607 */       ty1 = this.minTileY + this.numYTiles - 1;
/*     */     }
/*     */     
/* 610 */     boolean isIntPack = GraphicsUtil.is_INT_PACK_Data(getSampleModel(), false);
/*     */ 
/*     */     
/* 613 */     for (int y = ty0; y <= ty1; y++) {
/* 614 */       for (int x = tx0; x <= tx1; x++) {
/* 615 */         Raster r = getTile(x, y);
/* 616 */         if (isIntPack) {
/* 617 */           GraphicsUtil.copyData_INT_PACK(r, wr);
/*     */         } else {
/* 619 */           GraphicsUtil.copyData_FALLBACK(r, wr);
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
/*     */ 
/*     */   
/*     */   public WritableRaster makeTile(int tileX, int tileY) {
/* 639 */     if (tileX < this.minTileX || tileX >= this.minTileX + this.numXTiles || tileY < this.minTileY || tileY >= this.minTileY + this.numYTiles)
/*     */     {
/* 641 */       throw new IndexOutOfBoundsException("Requested Tile (" + tileX + ',' + tileY + ") lies outside the bounds of image");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 646 */     Point pt = new Point(this.tileGridXOff + tileX * this.tileWidth, this.tileGridYOff + tileY * this.tileHeight);
/*     */ 
/*     */ 
/*     */     
/* 650 */     WritableRaster wr = Raster.createWritableRaster(this.sm, pt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 669 */     int x0 = wr.getMinX();
/* 670 */     int y0 = wr.getMinY();
/* 671 */     int x1 = x0 + wr.getWidth() - 1;
/* 672 */     int y1 = y0 + wr.getHeight() - 1;
/*     */     
/* 674 */     if (x0 < this.bounds.x || x1 >= this.bounds.x + this.bounds.width || y0 < this.bounds.y || y1 >= this.bounds.y + this.bounds.height) {
/*     */ 
/*     */ 
/*     */       
/* 678 */       if (x0 < this.bounds.x) {
/* 679 */         x0 = this.bounds.x;
/*     */       }
/* 681 */       if (y0 < this.bounds.y) {
/* 682 */         y0 = this.bounds.y;
/*     */       }
/* 684 */       if (x1 >= this.bounds.x + this.bounds.width) {
/* 685 */         x1 = this.bounds.x + this.bounds.width - 1;
/*     */       }
/* 687 */       if (y1 >= this.bounds.y + this.bounds.height) {
/* 688 */         y1 = this.bounds.y + this.bounds.height - 1;
/*     */       }
/*     */       
/* 691 */       wr = wr.createWritableChild(x0, y0, x1 - x0 + 1, y1 - y0 + 1, x0, y0, (int[])null);
/*     */     } 
/*     */     
/* 694 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void copyBand(Raster src, int srcBand, WritableRaster dst, int dstBand) {
/* 699 */     Rectangle srcR = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*     */     
/* 701 */     Rectangle dstR = new Rectangle(dst.getMinX(), dst.getMinY(), dst.getWidth(), dst.getHeight());
/*     */ 
/*     */     
/* 704 */     Rectangle cpR = srcR.intersection(dstR);
/*     */     
/* 706 */     int[] samples = null;
/* 707 */     for (int y = cpR.y; y < cpR.y + cpR.height; y++) {
/* 708 */       samples = src.getSamples(cpR.x, y, cpR.width, 1, srcBand, samples);
/* 709 */       dst.setSamples(cpR.x, y, cpR.width, 1, dstBand, samples);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/AbstractRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */