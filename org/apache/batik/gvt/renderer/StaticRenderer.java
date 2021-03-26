/*     */ package org.apache.batik.gvt.renderer;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.Collection;
/*     */ import org.apache.batik.ext.awt.geom.RectListManager;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.PadRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.TileCacheRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.TranslateRed;
/*     */ import org.apache.batik.gvt.GraphicsNode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StaticRenderer
/*     */   implements ImageRenderer
/*     */ {
/*     */   protected GraphicsNode rootGN;
/*     */   protected Filter rootFilter;
/*     */   protected CachableRed rootCR;
/*     */   protected SoftReference lastCR;
/*     */   protected SoftReference lastCache;
/*     */   protected boolean isDoubleBuffered = false;
/*     */   protected WritableRaster currentBaseRaster;
/*     */   protected WritableRaster currentRaster;
/*     */   protected BufferedImage currentOffScreen;
/*     */   protected WritableRaster workingBaseRaster;
/*     */   protected WritableRaster workingRaster;
/*     */   protected BufferedImage workingOffScreen;
/*     */   protected int offScreenWidth;
/*     */   protected int offScreenHeight;
/*     */   protected RenderingHints renderingHints;
/*     */   protected AffineTransform usr2dev;
/*  94 */   protected static RenderingHints defaultRenderingHints = new RenderingHints(null); static {
/*  95 */     defaultRenderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */     
/*  98 */     defaultRenderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StaticRenderer(RenderingHints rh, AffineTransform at) {
/* 108 */     this.renderingHints = new RenderingHints(null);
/* 109 */     this.renderingHints.add(rh);
/* 110 */     this.usr2dev = new AffineTransform(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StaticRenderer() {
/* 117 */     this.renderingHints = new RenderingHints(null);
/* 118 */     this.renderingHints.add(defaultRenderingHints);
/* 119 */     this.usr2dev = new AffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 127 */     this.rootGN = null;
/* 128 */     this.rootFilter = null;
/* 129 */     this.rootCR = null;
/*     */     
/* 131 */     this.workingOffScreen = null;
/* 132 */     this.workingBaseRaster = null;
/* 133 */     this.workingRaster = null;
/*     */     
/* 135 */     this.currentOffScreen = null;
/* 136 */     this.currentBaseRaster = null;
/* 137 */     this.currentRaster = null;
/*     */     
/* 139 */     this.renderingHints = null;
/* 140 */     this.lastCache = null;
/* 141 */     this.lastCR = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTree(GraphicsNode rootGN) {
/* 150 */     this.rootGN = rootGN;
/* 151 */     this.rootFilter = null;
/* 152 */     this.rootCR = null;
/*     */     
/* 154 */     this.workingOffScreen = null;
/* 155 */     this.workingRaster = null;
/*     */     
/* 157 */     this.currentOffScreen = null;
/* 158 */     this.currentRaster = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getTree() {
/* 167 */     return this.rootGN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingHints(RenderingHints rh) {
/* 174 */     this.renderingHints = new RenderingHints(null);
/* 175 */     this.renderingHints.add(rh);
/*     */     
/* 177 */     this.rootFilter = null;
/* 178 */     this.rootCR = null;
/*     */     
/* 180 */     this.workingOffScreen = null;
/* 181 */     this.workingRaster = null;
/*     */     
/* 183 */     this.currentOffScreen = null;
/* 184 */     this.currentRaster = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 192 */     return this.renderingHints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransform(AffineTransform usr2dev) {
/* 203 */     if (this.usr2dev.equals(usr2dev)) {
/*     */       return;
/*     */     }
/* 206 */     if (usr2dev == null) {
/* 207 */       this.usr2dev = new AffineTransform();
/*     */     } else {
/* 209 */       this.usr2dev = new AffineTransform(usr2dev);
/*     */     } 
/* 211 */     this.rootCR = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 219 */     return this.usr2dev;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoubleBuffered() {
/* 228 */     return this.isDoubleBuffered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoubleBuffered(boolean isDoubleBuffered) {
/* 239 */     if (this.isDoubleBuffered == isDoubleBuffered) {
/*     */       return;
/*     */     }
/* 242 */     this.isDoubleBuffered = isDoubleBuffered;
/* 243 */     if (isDoubleBuffered) {
/*     */       
/* 245 */       this.currentOffScreen = null;
/* 246 */       this.currentBaseRaster = null;
/* 247 */       this.currentRaster = null;
/*     */     } else {
/*     */       
/* 250 */       this.currentOffScreen = this.workingOffScreen;
/* 251 */       this.currentBaseRaster = this.workingBaseRaster;
/* 252 */       this.currentRaster = this.workingRaster;
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
/*     */   public void updateOffScreen(int width, int height) {
/* 266 */     this.offScreenWidth = width;
/* 267 */     this.offScreenHeight = height;
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
/*     */   public BufferedImage getOffScreen() {
/* 282 */     if (this.rootGN == null) {
/* 283 */       return null;
/*     */     }
/* 285 */     return this.currentOffScreen;
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
/*     */   public void clearOffScreen() {
/* 303 */     if (this.isDoubleBuffered) {
/*     */       return;
/*     */     }
/* 306 */     updateWorkingBuffers();
/* 307 */     if (this.rootCR == null || this.workingBaseRaster == null) {
/*     */       return;
/*     */     }
/*     */     
/* 311 */     ColorModel cm = this.rootCR.getColorModel();
/* 312 */     WritableRaster syncRaster = this.workingBaseRaster;
/*     */ 
/*     */     
/* 315 */     synchronized (syncRaster) {
/* 316 */       BufferedImage bi = new BufferedImage(cm, this.workingBaseRaster, cm.isAlphaPremultiplied(), null);
/*     */       
/* 318 */       Graphics2D g2d = bi.createGraphics();
/* 319 */       g2d.setComposite(AlphaComposite.Clear);
/* 320 */       g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());
/* 321 */       g2d.dispose();
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
/*     */   public void repaint(Shape area) {
/* 340 */     if (area == null)
/* 341 */       return;  RectListManager rlm = new RectListManager();
/* 342 */     rlm.add(this.usr2dev.createTransformedShape(area).getBounds());
/* 343 */     repaint(rlm);
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
/*     */   public void repaint(RectListManager areas) {
/*     */     PadRed padRed;
/* 361 */     if (areas == null) {
/*     */       return;
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
/* 374 */     updateWorkingBuffers();
/* 375 */     if (this.rootCR == null || this.workingBaseRaster == null) {
/*     */       return;
/*     */     }
/*     */     
/* 379 */     CachableRed cr = this.rootCR;
/* 380 */     WritableRaster syncRaster = this.workingBaseRaster;
/* 381 */     WritableRaster copyRaster = this.workingRaster;
/*     */     
/* 383 */     Rectangle srcR = this.rootCR.getBounds();
/* 384 */     Rectangle dstR = this.workingRaster.getBounds();
/* 385 */     if (dstR.x < srcR.x || dstR.y < srcR.y || dstR.x + dstR.width > srcR.x + srcR.width || dstR.y + dstR.height > srcR.y + srcR.height)
/*     */     {
/*     */ 
/*     */       
/* 389 */       padRed = new PadRed(cr, dstR, PadMode.ZERO_PAD, null);
/*     */     }
/*     */     
/* 392 */     synchronized (syncRaster) {
/* 393 */       padRed.copyData(copyRaster);
/*     */     } 
/*     */     
/* 396 */     if (!HaltingThread.hasBeenHalted()) {
/*     */       
/* 398 */       BufferedImage tmpBI = this.workingOffScreen;
/*     */       
/* 400 */       this.workingBaseRaster = this.currentBaseRaster;
/* 401 */       this.workingRaster = this.currentRaster;
/* 402 */       this.workingOffScreen = this.currentOffScreen;
/*     */       
/* 404 */       this.currentRaster = copyRaster;
/* 405 */       this.currentBaseRaster = syncRaster;
/* 406 */       this.currentOffScreen = tmpBI;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/* 416 */     if (this.lastCache == null)
/* 417 */       return;  Object o = this.lastCache.get();
/* 418 */     if (o == null)
/*     */       return; 
/* 420 */     TileCacheRed tcr = (TileCacheRed)o;
/* 421 */     tcr.flushCache(tcr.getBounds());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(Collection areas) {
/* 428 */     AffineTransform at = getTransform();
/* 429 */     for (Object area : areas) {
/* 430 */       Shape s = (Shape)area;
/* 431 */       Rectangle r = at.createTransformedShape(s).getBounds();
/* 432 */       flush(r);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(Rectangle r) {
/* 440 */     if (this.lastCache == null)
/* 441 */       return;  Object o = this.lastCache.get();
/* 442 */     if (o == null)
/*     */       return; 
/* 444 */     TileCacheRed tcr = (TileCacheRed)o;
/* 445 */     r = (Rectangle)r.clone();
/* 446 */     r.x -= Math.round((float)this.usr2dev.getTranslateX());
/* 447 */     r.y -= Math.round((float)this.usr2dev.getTranslateY());
/*     */     
/* 449 */     tcr.flushCache(r);
/*     */   }
/*     */   
/*     */   protected CachableRed setupCache(CachableRed img) {
/* 453 */     if (this.lastCR == null || img != this.lastCR.get()) {
/*     */       
/* 455 */       this.lastCR = new SoftReference<CachableRed>(img);
/* 456 */       this.lastCache = null;
/*     */     } 
/*     */     
/* 459 */     Object o = null;
/* 460 */     if (this.lastCache != null)
/* 461 */       o = this.lastCache.get(); 
/* 462 */     if (o != null) {
/* 463 */       return (CachableRed)o;
/*     */     }
/* 465 */     TileCacheRed tileCacheRed = new TileCacheRed(img);
/* 466 */     this.lastCache = new SoftReference<TileCacheRed>(tileCacheRed);
/* 467 */     return (CachableRed)tileCacheRed;
/*     */   }
/*     */ 
/*     */   
/*     */   protected CachableRed renderGNR() {
/* 472 */     AffineTransform at = this.usr2dev;
/* 473 */     AffineTransform rcAT = new AffineTransform(at.getScaleX(), at.getShearY(), at.getShearX(), at.getScaleY(), 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */     
/* 477 */     RenderContext rc = new RenderContext(rcAT, null, this.renderingHints);
/*     */     
/* 479 */     RenderedImage ri = this.rootFilter.createRendering(rc);
/* 480 */     if (ri == null) {
/* 481 */       return null;
/*     */     }
/*     */     
/* 484 */     CachableRed ret = GraphicsUtil.wrap(ri);
/* 485 */     ret = setupCache(ret);
/*     */     
/* 487 */     int dx = Math.round((float)at.getTranslateX());
/* 488 */     int dy = Math.round((float)at.getTranslateY());
/* 489 */     TranslateRed translateRed = new TranslateRed(ret, ret.getMinX() + dx, ret.getMinY() + dy);
/* 490 */     return GraphicsUtil.convertTosRGB((CachableRed)translateRed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateWorkingBuffers() {
/*     */     int xt, yt;
/* 501 */     if (this.rootFilter == null) {
/* 502 */       this.rootFilter = this.rootGN.getGraphicsNodeRable(true);
/* 503 */       this.rootCR = null;
/*     */     } 
/*     */     
/* 506 */     this.rootCR = renderGNR();
/* 507 */     if (this.rootCR == null) {
/*     */       
/* 509 */       this.workingRaster = null;
/* 510 */       this.workingOffScreen = null;
/* 511 */       this.workingBaseRaster = null;
/*     */       
/* 513 */       this.currentOffScreen = null;
/* 514 */       this.currentBaseRaster = null;
/* 515 */       this.currentRaster = null;
/*     */       
/*     */       return;
/*     */     } 
/* 519 */     SampleModel sm = this.rootCR.getSampleModel();
/* 520 */     int w = this.offScreenWidth;
/* 521 */     int h = this.offScreenHeight;
/*     */     
/* 523 */     int tw = sm.getWidth();
/* 524 */     int th = sm.getHeight();
/* 525 */     w = ((w + tw - 1) / tw + 1) * tw;
/* 526 */     h = ((h + th - 1) / th + 1) * th;
/*     */     
/* 528 */     if (this.workingBaseRaster == null || this.workingBaseRaster.getWidth() < w || this.workingBaseRaster.getHeight() < h) {
/*     */ 
/*     */ 
/*     */       
/* 532 */       sm = sm.createCompatibleSampleModel(w, h);
/*     */       
/* 534 */       this.workingBaseRaster = Raster.createWritableRaster(sm, new Point(0, 0));
/*     */     } 
/*     */ 
/*     */     
/* 538 */     int tgx = -this.rootCR.getTileGridXOffset();
/* 539 */     int tgy = -this.rootCR.getTileGridYOffset();
/*     */     
/* 541 */     if (tgx >= 0) { xt = tgx / tw; }
/* 542 */     else { xt = (tgx - tw + 1) / tw; }
/* 543 */      if (tgy >= 0) { yt = tgy / th; }
/* 544 */     else { yt = (tgy - th + 1) / th; }
/*     */     
/* 546 */     int xloc = xt * tw - tgx;
/* 547 */     int yloc = yt * th - tgy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     this.workingRaster = this.workingBaseRaster.createWritableChild(0, 0, w, h, xloc, yloc, (int[])null);
/*     */ 
/*     */     
/* 558 */     this.workingOffScreen = new BufferedImage(this.rootCR.getColorModel(), this.workingRaster.createWritableChild(0, 0, this.offScreenWidth, this.offScreenHeight, 0, 0, (int[])null), this.rootCR.getColorModel().isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 565 */     if (!this.isDoubleBuffered) {
/* 566 */       this.currentOffScreen = this.workingOffScreen;
/* 567 */       this.currentBaseRaster = this.workingBaseRaster;
/* 568 */       this.currentRaster = this.workingRaster;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/renderer/StaticRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */