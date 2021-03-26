/*      */ package com.github.jaiimageio.jpeg2000.impl;
/*      */ 
/*      */ import c.a.e.a;
/*      */ import c.a.e.c;
/*      */ import c.a.e.e;
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RenderedImageSrc
/*      */   implements a
/*      */ {
/*      */   private int w;
/*      */   private int h;
/*      */   int tileWidth;
/*      */   int tileHeight;
/*      */   int tileXOffset;
/*      */   int tileYOffset;
/*      */   int scaleX;
/*      */   int scaleY;
/*      */   int xOffset;
/*      */   int yOffset;
/*   84 */   int[] sourceBands = null;
/*      */ 
/*      */   
/*      */   int minX;
/*      */ 
/*      */   
/*      */   int minY;
/*      */ 
/*      */   
/*      */   private int nc;
/*      */   
/*      */   private int rb;
/*      */   
/*   97 */   private int[][] barr = (int[][])null;
/*      */ 
/*      */   
/*  100 */   private e dbi = new e();
/*      */ 
/*      */   
/*      */   private byte[] buf;
/*      */ 
/*      */   
/*      */   private e intBlk;
/*      */ 
/*      */   
/*      */   private RenderedImage src;
/*      */ 
/*      */   
/*      */   private J2KImageWriteParamJava param;
/*      */   
/*      */   private Raster raster;
/*      */   
/*      */   private Raster aTile;
/*      */   
/*  118 */   private Point co = new Point();
/*      */   
/*  120 */   private int dcOffset = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isBinary = false;
/*      */ 
/*      */   
/*      */   private Rectangle destinationRegion;
/*      */ 
/*      */   
/*      */   private Rectangle sourceRegion;
/*      */ 
/*      */   
/*      */   private ColorModel cm;
/*      */ 
/*      */   
/*      */   private SampleModel sm;
/*      */ 
/*      */   
/*      */   private boolean noTransform = true;
/*      */ 
/*      */   
/*      */   private boolean noSubband = true;
/*      */ 
/*      */   
/*      */   private J2KImageWriter writer;
/*      */ 
/*      */   
/*      */   private boolean inputIsRaster = false;
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderedImageSrc(Raster raster, J2KImageWriteParamJava param, J2KImageWriter writer) {
/*  153 */     this.raster = raster;
/*  154 */     this.param = param;
/*  155 */     this.writer = writer;
/*  156 */     this.inputIsRaster = true;
/*      */     
/*  158 */     this.sourceRegion = param.getSourceRegion();
/*      */     
/*  160 */     if (this.sourceRegion == null) {
/*  161 */       this
/*  162 */         .sourceRegion = new Rectangle(raster.getMinX(), raster.getMinY(), raster.getWidth(), raster.getHeight());
/*      */     } else {
/*  164 */       this.sourceRegion = this.sourceRegion.intersection(raster.getBounds());
/*      */     } 
/*  166 */     if (this.sourceRegion.isEmpty()) {
/*  167 */       throw new RuntimeException(I18N.getString("J2KImageWriterCodecLib0"));
/*      */     }
/*  169 */     this.sm = raster.getSampleModel();
/*  170 */     getFromParam();
/*  171 */     setSampleModelAndMore();
/*  172 */     setTile(0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderedImageSrc(RenderedImage src, J2KImageWriteParamJava param, J2KImageWriter writer) {
/*  188 */     this.src = src;
/*  189 */     this.param = param;
/*  190 */     this.writer = writer;
/*      */     
/*  192 */     this.sourceRegion = param.getSourceRegion();
/*      */     
/*  194 */     if (this.sourceRegion == null) {
/*  195 */       this
/*  196 */         .sourceRegion = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*      */     } else {
/*  198 */       this.sourceRegion = this.sourceRegion.intersection(new Rectangle(src.getMinX(), src
/*  199 */             .getMinY(), src
/*  200 */             .getWidth(), src
/*  201 */             .getHeight()));
/*  202 */     }  if (this.sourceRegion.isEmpty()) {
/*  203 */       throw new RuntimeException(I18N.getString("J2KImageWriterCodecLib0"));
/*      */     }
/*  205 */     this.sm = src.getSampleModel();
/*  206 */     this.cm = src.getColorModel();
/*  207 */     getFromParam();
/*  208 */     setSampleModelAndMore();
/*      */   }
/*      */   
/*      */   private void getFromParam() {
/*      */     try {
/*  213 */       this.tileWidth = this.param.getTileWidth();
/*  214 */       this.tileHeight = this.param.getTileHeight();
/*  215 */       this.tileXOffset = this.param.getTileGridXOffset();
/*  216 */       this.tileYOffset = this.param.getTileGridYOffset();
/*  217 */     } catch (IllegalStateException illegalStateException) {
/*  218 */       this.param.setTilingMode(2);
/*  219 */       if (this.inputIsRaster) {
/*  220 */         this.param.setTiling(this.raster.getWidth(), this.raster.getHeight(), this.raster
/*  221 */             .getMinX(), this.raster.getMinY());
/*      */       } else {
/*  223 */         this.param.setTiling(this.src.getWidth(), this.src.getHeight(), this.src
/*  224 */             .getMinX(), this.src.getMinY());
/*      */       } 
/*  226 */       this.tileWidth = this.param.getTileWidth();
/*  227 */       this.tileHeight = this.param.getTileHeight();
/*  228 */       this.tileXOffset = this.param.getTileGridXOffset();
/*  229 */       this.tileYOffset = this.param.getTileGridYOffset();
/*      */     } 
/*      */     
/*  232 */     this.scaleX = this.param.getSourceXSubsampling();
/*  233 */     this.scaleY = this.param.getSourceYSubsampling();
/*  234 */     this.xOffset = this.param.getSubsamplingXOffset();
/*  235 */     this.yOffset = this.param.getSubsamplingYOffset();
/*      */     
/*  237 */     this.sourceRegion.translate(this.xOffset, this.yOffset);
/*  238 */     this.sourceRegion.width -= this.xOffset;
/*  239 */     this.sourceRegion.height -= this.yOffset;
/*      */     
/*  241 */     this.xOffset = this.sourceRegion.x % this.scaleX;
/*  242 */     this.yOffset = this.sourceRegion.y % this.scaleY;
/*      */     
/*  244 */     this.minX = this.sourceRegion.x / this.scaleX;
/*  245 */     this.minY = this.sourceRegion.y / this.scaleY;
/*      */     
/*  247 */     this.w = (this.sourceRegion.width + this.scaleX - 1) / this.scaleX;
/*  248 */     this.h = (this.sourceRegion.height + this.scaleY - 1) / this.scaleY;
/*      */     
/*  250 */     this.tileXOffset += (this.minX - this.tileXOffset) / this.tileWidth * this.tileWidth;
/*  251 */     this.tileYOffset += (this.minY - this.tileYOffset) / this.tileHeight * this.tileHeight;
/*      */     
/*  253 */     this.destinationRegion = new Rectangle(this.minX, this.minY, this.w, this.h);
/*      */     
/*  255 */     if (!this.destinationRegion.equals(this.sourceRegion) || this.tileWidth != this.sm
/*  256 */       .getWidth() || this.tileHeight != this.sm
/*  257 */       .getHeight() || (!this.inputIsRaster && (this.tileXOffset != this.src
/*      */       
/*  259 */       .getTileGridXOffset() || this.tileYOffset != this.src
/*  260 */       .getTileGridYOffset())) || (this.inputIsRaster && (this.tileXOffset != this.raster
/*      */       
/*  262 */       .getMinX() || this.tileYOffset != this.raster
/*  263 */       .getMinY()))) {
/*  264 */       this.noTransform = false;
/*      */     }
/*      */   }
/*      */   
/*      */   private void setSampleModelAndMore() {
/*  269 */     this.nc = this.sm.getNumBands();
/*  270 */     this.sourceBands = this.param.getSourceBands();
/*  271 */     if (this.sourceBands != null) {
/*  272 */       this.sm = this.sm.createSubsetSampleModel(this.sourceBands);
/*  273 */       this.noSubband = false;
/*      */     } else {
/*  275 */       this.sourceBands = new int[this.nc];
/*  276 */       for (int i = 0; i < this.nc; i++) {
/*  277 */         this.sourceBands[i] = i;
/*      */       }
/*      */     } 
/*  280 */     this.sm = this.sm.createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*  281 */     this.nc = this.sm.getNumBands();
/*  282 */     this.isBinary = ImageUtil.isBinary(this.sm);
/*      */     
/*  284 */     if (this.cm != null) {
/*      */       
/*  286 */       this.rb = this.cm.getComponentSize(0);
/*  287 */       for (int i = 1; i < this.cm.getNumComponents(); i++) {
/*  288 */         if (this.rb < this.cm.getComponentSize(i))
/*  289 */           this.rb = this.cm.getComponentSize(i); 
/*      */       } 
/*      */     } else {
/*  292 */       this.rb = this.sm.getSampleSize(0);
/*  293 */       for (int i = 1; i < this.sm.getNumBands(); i++) {
/*  294 */         if (this.rb < this.sm.getSampleSize(i))
/*  295 */           this.rb = this.sm.getSampleSize(i); 
/*      */       } 
/*      */     } 
/*  298 */     if (!isOrigSigned(0) && this.rb > 1)
/*      */     {
/*  300 */       this.dcOffset = 1 << this.rb - 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getTilePartULX() {
/*  305 */     return this.tileXOffset;
/*      */   }
/*      */   
/*      */   public int getTilePartULY() {
/*  309 */     return this.tileYOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTileWidth() {
/*  318 */     int width = this.tileWidth;
/*  319 */     int maxX = getImgULX() + getImgWidth();
/*  320 */     int x = this.co.x * this.tileWidth + this.tileXOffset;
/*  321 */     if (x + this.tileWidth >= maxX)
/*  322 */       width = maxX - x; 
/*  323 */     return width;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTileHeight() {
/*  331 */     int height = this.tileHeight;
/*  332 */     int maxY = getImgULY() + getImgHeight();
/*  333 */     int y = this.co.y * this.tileHeight + this.tileYOffset;
/*  334 */     if (y + this.tileHeight >= maxY) {
/*  335 */       height = maxY - y;
/*      */     }
/*  337 */     return height;
/*      */   }
/*      */   
/*      */   public int getNomTileWidth() {
/*  341 */     return this.tileWidth;
/*      */   }
/*      */   
/*      */   public int getNomTileHeight() {
/*  345 */     return this.tileHeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getImgWidth() {
/*  356 */     return this.w;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getImgHeight() {
/*  367 */     return this.h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumComps() {
/*  377 */     return this.nc;
/*      */   }
/*      */   
/*      */   public int getTileGridXOffset() {
/*  381 */     return this.param.getTileGridXOffset();
/*      */   }
/*      */   
/*      */   public int getTileGridYOffset() {
/*  385 */     return this.param.getTileGridYOffset();
/*      */   }
/*      */   
/*      */   public int getTileCompHeight(int t, int c) {
/*  389 */     return this.tileHeight;
/*      */   }
/*      */   
/*      */   public int getTileCompWidth(int t, int c) {
/*  393 */     return this.tileWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompSubsX(int c) {
/*  409 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompSubsY(int c) {
/*  425 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompWidth(int n) {
/*  440 */     return this.w;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompHeight(int c) {
/*  455 */     return this.h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompImgWidth(int c) {
/*  469 */     return this.w;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompImgHeight(int c) {
/*  483 */     return this.h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTile(int x, int y) {
/*  494 */     if (x >= getNumXTiles()) {
/*  495 */       y += x / getNumXTiles();
/*  496 */       x %= getNumXTiles();
/*      */     } 
/*  498 */     this.co.x = x;
/*  499 */     this.co.y = y;
/*  500 */     this.aTile = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextTile() {
/*  508 */     this.co.x++;
/*  509 */     if (this.co.x >= getNumXTiles()) {
/*  510 */       this.co.x = 0;
/*  511 */       this.co.y++;
/*      */     } 
/*  513 */     setTile(this.co.x, this.co.y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point getTile(Point co) {
/*  526 */     if (co != null) {
/*  527 */       return co;
/*      */     }
/*  529 */     return new Point(0, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTileIdx() {
/*  539 */     return getNumXTiles() * this.co.y + this.co.x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point getTileOff(Point p, int c) {
/*  559 */     if (p != null) {
/*  560 */       p.x = this.co.x * this.tileWidth + this.tileXOffset;
/*  561 */       p.y = this.co.y * this.tileHeight + this.tileYOffset;
/*  562 */       return this.co;
/*      */     } 
/*  564 */     return new Point(this.co.x * this.tileWidth + this.tileXOffset, this.co.y * this.tileHeight + this.tileYOffset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompULX(int c) {
/*  580 */     return this.raster.getMinX();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompULY(int c) {
/*  595 */     return this.raster.getMinY();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getImgULX() {
/*  606 */     return this.destinationRegion.x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getImgULY() {
/*  617 */     return this.destinationRegion.y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Point getNumTiles(Point co) {
/*  631 */     if (co != null) {
/*  632 */       co.x = getNumXTiles();
/*  633 */       co.y = getNumYTiles();
/*  634 */       return co;
/*      */     } 
/*      */     
/*  637 */     return new Point(getNumXTiles(), getNumYTiles());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumTiles() {
/*  648 */     return getNumXTiles() * getNumYTiles();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNomRangeBits(int c) {
/*  670 */     return this.rb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFixedPoint(int c) {
/*  685 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final c getInternCompData(c blk, int i) {
/*      */     e e1;
/*  736 */     if (this.writer != null && this.writer.getAbortRequest()) {
/*  737 */       throw new RuntimeException(J2KImageWriter.WRITE_ABORTED);
/*      */     }
/*  739 */     if (this.barr == null) {
/*  740 */       this.barr = new int[this.nc][];
/*      */     }
/*      */     
/*  743 */     if (blk.a() != 3) {
/*  744 */       if (this.intBlk == null) {
/*  745 */         this.intBlk = new e(blk.e, blk.f, blk.g, blk.h);
/*      */       } else {
/*  747 */         this.intBlk.e = blk.e;
/*  748 */         this.intBlk.f = blk.f;
/*  749 */         this.intBlk.g = blk.g;
/*  750 */         this.intBlk.h = blk.h;
/*      */       } 
/*  752 */       e1 = this.intBlk;
/*      */     } 
/*      */ 
/*      */     
/*  756 */     float percentage = (getTileIdx() + (((c)e1).f + 1.0F) / ((c)e1).h) / getNumTiles();
/*  757 */     this.writer.processImageProgressWrapper(percentage * 100.0F);
/*      */ 
/*      */ 
/*      */     
/*  761 */     if (this.barr[i] == null || this.dbi.e > ((c)e1).e || this.dbi.f > ((c)e1).f || this.dbi.e + this.dbi.g < ((c)e1).e + ((c)e1).g || this.dbi.f + this.dbi.h < ((c)e1).f + ((c)e1).h) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  768 */       if (this.barr[i] == null || (this.barr[i]).length < ((c)e1).g * ((c)e1).h) {
/*  769 */         this.barr[i] = new int[((c)e1).g * ((c)e1).h];
/*      */       }
/*  771 */       e1.a(this.barr[i]);
/*      */       int j;
/*  773 */       for (j = (i + 1) % this.nc; j != i; j = (j + 1) % this.nc) {
/*  774 */         if (this.barr[j] == null || (this.barr[j]).length < ((c)e1).g * ((c)e1).h) {
/*  775 */           this.barr[j] = new int[((c)e1).g * ((c)e1).h];
/*      */         }
/*      */       } 
/*      */       
/*  779 */       this.dbi.e = ((c)e1).e;
/*  780 */       this.dbi.f = ((c)e1).f;
/*  781 */       this.dbi.g = ((c)e1).g;
/*  782 */       this.dbi.h = ((c)e1).h;
/*      */ 
/*      */       
/*  785 */       if (this.aTile == null) {
/*  786 */         this.aTile = getTile(this.co.x, this.co.y);
/*  787 */         Rectangle temp = this.aTile.getBounds();
/*  788 */         this.aTile = this.aTile.createTranslatedChild(temp.x - this.minX, temp.y - this.minY);
/*      */       } 
/*      */ 
/*      */       
/*  792 */       for (j = 0; j < this.nc; j++) {
/*  793 */         this.aTile.getSamples(((c)e1).e, ((c)e1).f, ((c)e1).g, ((c)e1).h, j, this.barr[j]);
/*  794 */         for (int k = 0; k < (this.barr[j]).length; k++) {
/*  795 */           this.barr[j][k] = this.barr[j][k] - this.dcOffset;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  800 */       e1.a(this.barr[i]);
/*  801 */       ((c)e1).i = 0;
/*  802 */       ((c)e1).j = ((c)e1).g;
/*      */     } else {
/*  804 */       e1.a(this.barr[i]);
/*  805 */       ((c)e1).i = (((c)e1).e - this.dbi.e) * this.dbi.g + ((c)e1).e - this.dbi.e;
/*  806 */       ((c)e1).j = this.dbi.j;
/*      */     } 
/*      */ 
/*      */     
/*  810 */     ((c)e1).k = false;
/*  811 */     return (c)e1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final c getCompData(c blk, int i) {
/*      */     e e1;
/*  862 */     if (blk.a() != 3) {
/*  863 */       e tmp = new e(blk.e, blk.f, blk.g, blk.h);
/*  864 */       e1 = tmp;
/*      */     } 
/*      */     
/*  867 */     int[] bakarr = (int[])e1.b();
/*      */     
/*  869 */     int ulx = ((c)e1).e;
/*  870 */     int uly = ((c)e1).f;
/*  871 */     int w = ((c)e1).g;
/*  872 */     int h = ((c)e1).h;
/*      */     
/*  874 */     e1.a(null);
/*  875 */     getInternCompData((c)e1, i);
/*      */     
/*  877 */     if (bakarr == null) {
/*  878 */       bakarr = new int[w * h];
/*      */     }
/*  880 */     if (((c)e1).i == 0 && ((c)e1).j == w) {
/*      */       
/*  882 */       System.arraycopy(e1.b(), 0, bakarr, 0, w * h);
/*      */     } else {
/*      */       
/*  885 */       for (int j = h - 1; j >= 0; j--) {
/*  886 */         System.arraycopy(e1.b(), ((c)e1).i + j * ((c)e1).j, bakarr, j * w, w);
/*      */       }
/*      */     } 
/*      */     
/*  890 */     e1.a(bakarr);
/*  891 */     ((c)e1).i = 0;
/*  892 */     ((c)e1).j = ((c)e1).g;
/*  893 */     return (c)e1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOrigSigned(int c) {
/*  906 */     if (this.isBinary) return true;
/*      */ 
/*      */     
/*  909 */     SampleModel sm = null;
/*  910 */     if (this.inputIsRaster) {
/*  911 */       sm = this.raster.getSampleModel();
/*      */     } else {
/*  913 */       sm = this.src.getSampleModel();
/*      */     } 
/*  915 */     if (sm.getDataType() == 1 || sm
/*  916 */       .getDataType() == 0)
/*  917 */       return false; 
/*  918 */     return true;
/*      */   }
/*      */   
/*      */   private int getNumXTiles() {
/*  922 */     int x = this.destinationRegion.x;
/*  923 */     int tx = this.tileXOffset;
/*  924 */     int tw = this.tileWidth;
/*  925 */     return ToTile(x + this.destinationRegion.width - 1, tx, tw) - ToTile(x, tx, tw) + 1;
/*      */   }
/*      */   
/*      */   private int getNumYTiles() {
/*  929 */     int y = this.destinationRegion.y;
/*  930 */     int ty = this.tileYOffset;
/*  931 */     int th = this.tileHeight;
/*  932 */     return ToTile(y + this.destinationRegion.height - 1, ty, th) - ToTile(y, ty, th) + 1;
/*      */   }
/*      */   
/*      */   private static int ToTile(int pos, int tileOffset, int tileSize) {
/*  936 */     pos -= tileOffset;
/*  937 */     if (pos < 0) {
/*  938 */       pos += 1 - tileSize;
/*      */     }
/*  940 */     return pos / tileSize;
/*      */   }
/*      */   
/*      */   private Raster getTile(int tileX, int tileY) {
/*  944 */     int sx = this.tileXOffset + tileX * this.tileWidth;
/*  945 */     int sy = this.tileYOffset + tileY * this.tileHeight;
/*  946 */     tileX += this.tileXOffset / this.tileWidth;
/*  947 */     tileY += this.tileYOffset / this.tileHeight;
/*      */     
/*  949 */     if (this.inputIsRaster) {
/*  950 */       if (this.noTransform) {
/*  951 */         return this.raster.createChild(sx, sy, getTileWidth(), getTileHeight(), sx, sy, this.sourceBands);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  956 */       WritableRaster writableRaster = Raster.createWritableRaster(this.sm, new Point(sx, sy));
/*      */       
/*  958 */       int i = mapToSourceX(sx);
/*  959 */       int k = mapToSourceY(sy);
/*      */       
/*  961 */       int m = this.raster.getMinY();
/*  962 */       int n = this.raster.getMinY() + this.raster.getHeight();
/*      */       
/*  964 */       int cTileWidth = getTileWidth();
/*  965 */       for (int i1 = 0; i1 < getTileHeight(); i1++, sy++, k += this.scaleY) {
/*  966 */         if (k >= m && k < n) {
/*      */           
/*  968 */           Raster source = this.raster.createChild(i, k, (cTileWidth - 1) * this.scaleX + 1, 1, i, k, null);
/*      */           
/*  970 */           int tempX = sx; int offset;
/*  971 */           for (int i2 = 0; i2 < cTileWidth; i2++, tempX++, offset += this.scaleX) {
/*  972 */             for (int i3 = 0; i3 < this.nc; i3++) {
/*  973 */               int p = source.getSample(offset, k, this.sourceBands[i3]);
/*  974 */               writableRaster.setSample(tempX, sy, i3, p);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  979 */       return writableRaster;
/*      */     } 
/*      */     
/*  982 */     if (this.noTransform) {
/*  983 */       Raster raster = this.src.getTile(tileX, tileY);
/*  984 */       if (this.noSubband) {
/*  985 */         return raster;
/*      */       }
/*  987 */       return raster.createChild(sx, sy, this.tileWidth, this.tileHeight, sx, sy, this.sourceBands);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  992 */     WritableRaster ras = Raster.createWritableRaster(this.sm, new Point(sx, sy));
/*      */     
/*  994 */     int x = mapToSourceX(sx);
/*  995 */     int y = mapToSourceY(sy);
/*      */     
/*  997 */     int minY = this.src.getMinY();
/*  998 */     int maxY = this.src.getMinY() + this.src.getHeight();
/*  999 */     int length = this.tileWidth * this.scaleX;
/*      */     
/* 1001 */     if (x + length >= this.src.getWidth())
/* 1002 */       length = this.src.getWidth() - x; 
/* 1003 */     int dLength = (length + this.scaleX - 1) / this.scaleX;
/*      */     
/* 1005 */     for (int j = 0; j < this.tileHeight; j++, sy++, y += this.scaleY) {
/* 1006 */       if (y >= minY && y < maxY) {
/*      */ 
/*      */         
/* 1009 */         Raster source = this.src.getData(new Rectangle(x, y, length, 1));
/*      */         
/* 1011 */         int tempX = sx; int offset;
/* 1012 */         for (int i = 0; i < dLength; i++, tempX++, offset += this.scaleX) {
/*      */           
/* 1014 */           for (int k = 0; k < this.nc; k++) {
/* 1015 */             int p = source.getSample(offset, y, this.sourceBands[k]);
/*      */             
/* 1017 */             ras.setSample(tempX, sy, k, p);
/*      */           } 
/*      */         } 
/*      */       } 
/* 1021 */     }  return ras;
/*      */   }
/*      */ 
/*      */   
/*      */   private int mapToSourceX(int x) {
/* 1026 */     return x * this.scaleX + this.xOffset;
/*      */   }
/*      */   
/*      */   private int mapToSourceY(int y) {
/* 1030 */     return y * this.scaleY + this.yOffset;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/RenderedImageSrc.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */