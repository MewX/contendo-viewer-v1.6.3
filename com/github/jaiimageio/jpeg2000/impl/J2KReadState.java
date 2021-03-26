/*      */ package com.github.jaiimageio.jpeg2000.impl;
/*      */ 
/*      */ import c.a.a.a.a;
/*      */ import c.a.a.a.d;
/*      */ import c.a.a.d;
/*      */ import c.a.b.a;
/*      */ import c.a.c.a.c;
/*      */ import c.a.c.a.e;
/*      */ import c.a.d.a.a;
/*      */ import c.a.e.a;
/*      */ import c.a.e.c;
/*      */ import c.a.e.c.a;
/*      */ import c.a.e.e;
/*      */ import c.a.e.h;
/*      */ import c.a.f.f;
/*      */ import c.a.g.a.a;
/*      */ import c.a.g.a.b;
/*      */ import c.a.h.b;
/*      */ import c.a.j.b.a;
/*      */ import c.a.j.b.f;
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.PixelInterleavedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.util.Hashtable;
/*      */ import javax.imageio.ImageReadParam;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.stream.ImageInputStream;
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
/*      */ public class J2KReadState
/*      */ {
/*   85 */   private ImageInputStream iis = null;
/*      */   private a ff;
/*      */   private d hi;
/*      */   private d hd;
/*      */   private f in;
/*      */   private a breader;
/*      */   private e entdec;
/*      */   private b roids;
/*      */   private b deq;
/*      */   private f invWT;
/*      */   private a ictransf;
/*      */   private h converter;
/*      */   private h converter2;
/*   98 */   private a decSpec = null;
/*   99 */   private J2KImageReadParamJava j2krparam = null;
/*  100 */   private int[] destinationBands = null;
/*  101 */   private int[] sourceBands = null;
/*      */   
/*  103 */   private int[] levelShift = null;
/*  104 */   private int[] minValues = null;
/*  105 */   private int[] maxValues = null;
/*  106 */   private int[] fracBits = null;
/*  107 */   private e[] dataBlocks = null;
/*      */   
/*  109 */   private int[] bandOffsets = null;
/*  110 */   private int maxDepth = 0;
/*      */   
/*      */   private boolean isSigned = false;
/*  113 */   private ColorModel colorModel = null;
/*  114 */   private SampleModel sampleModel = null;
/*  115 */   private int nComp = 0;
/*  116 */   private int tileWidth = 0;
/*  117 */   private int tileHeight = 0; private int scaleX;
/*      */   private int scaleY;
/*      */   private int xOffset;
/*      */   private int yOffset;
/*  121 */   private Rectangle destinationRegion = null;
/*      */   
/*      */   private Point sourceOrigin;
/*      */   
/*      */   private int tileXOffset;
/*      */   private int tileYOffset;
/*      */   private int width;
/*      */   private int height;
/*  129 */   private int[] pixbuf = null;
/*  130 */   private byte[] bytebuf = null;
/*  131 */   private int[] channelMap = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean noTransform = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private int resolution;
/*      */ 
/*      */ 
/*      */   
/*      */   private int stepX;
/*      */ 
/*      */ 
/*      */   
/*      */   private int stepY;
/*      */ 
/*      */   
/*      */   private int tileStepX;
/*      */ 
/*      */   
/*      */   private int tileStepY;
/*      */ 
/*      */   
/*      */   private J2KMetadata metadata;
/*      */ 
/*      */   
/*      */   private BufferedImage destImage;
/*      */ 
/*      */   
/*      */   private J2KImageReader reader;
/*      */ 
/*      */ 
/*      */   
/*      */   public J2KReadState(ImageInputStream iis, J2KImageReadParamJava param, J2KMetadata metadata, J2KImageReader reader) {
/*  167 */     if (iis == null || param == null || metadata == null) {
/*  168 */       throw new IllegalArgumentException(I18N.getString("J2KReadState0"));
/*      */     }
/*  170 */     this.iis = iis;
/*  171 */     this.j2krparam = param;
/*  172 */     this.metadata = metadata;
/*  173 */     this.reader = reader;
/*      */     
/*  175 */     initializeRead(0, param, metadata);
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
/*      */   public J2KReadState(ImageInputStream iis, J2KImageReadParamJava param, J2KImageReader reader) {
/*  189 */     if (iis == null || param == null) {
/*  190 */       throw new IllegalArgumentException(I18N.getString("J2KReadState0"));
/*      */     }
/*  192 */     this.iis = iis;
/*  193 */     this.j2krparam = param;
/*  194 */     this.reader = reader;
/*  195 */     initializeRead(0, param, null);
/*      */   }
/*      */   
/*      */   public int getWidth() throws IOException {
/*  199 */     return this.width;
/*      */   }
/*      */   
/*      */   public int getHeight() throws IOException {
/*  203 */     return this.height;
/*      */   }
/*      */   
/*      */   public d getHeader() {
/*  207 */     return this.hd;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raster getTile(int tileX, int tileY, WritableRaster raster) throws IOException {
/*  212 */     Point nT = this.ictransf.getNumTiles(null);
/*      */     
/*  214 */     if (this.noTransform) {
/*  215 */       int tOffx, tOffy, cTileWidth, cTileHeight; if (tileX >= nT.x || tileY >= nT.y) {
/*  216 */         throw new IllegalArgumentException(I18N.getString("J2KImageReader0"));
/*      */       }
/*  218 */       this.ictransf.setTile(tileX * this.tileStepX, tileY * this.tileStepY);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  226 */       if ((raster != null && this.resolution < 
/*  227 */         (this.hd.m()).g.b()) || this.stepX != 1 || this.stepY != 1) {
/*      */         
/*  229 */         tOffx = raster.getMinX();
/*  230 */         tOffy = raster.getMinY();
/*  231 */         cTileWidth = Math.min(raster.getWidth(), this.ictransf
/*  232 */             .getTileWidth());
/*  233 */         cTileHeight = Math.min(raster.getHeight(), this.ictransf
/*  234 */             .getTileHeight());
/*      */       }
/*      */       else {
/*      */         
/*  238 */         tOffx = this.ictransf.getCompULX(0) - (this.ictransf.getImgULX() + this.ictransf.getCompSubsX(0) - 1) / this.ictransf.getCompSubsX(0) + this.destinationRegion.x;
/*      */ 
/*      */         
/*  241 */         tOffy = this.ictransf.getCompULY(0) - (this.ictransf.getImgULY() + this.ictransf.getCompSubsY(0) - 1) / this.ictransf.getCompSubsY(0) + this.destinationRegion.y;
/*  242 */         cTileWidth = this.ictransf.getTileWidth();
/*  243 */         cTileHeight = this.ictransf.getTileHeight();
/*      */       } 
/*      */       
/*  246 */       if (raster == null) {
/*  247 */         raster = Raster.createWritableRaster(this.sampleModel, new Point(tOffx, tOffy));
/*      */       }
/*      */       
/*  250 */       int numBands = this.sampleModel.getNumBands();
/*      */       
/*  252 */       if (tOffx + cTileWidth >= this.destinationRegion.width + this.destinationRegion.x)
/*      */       {
/*  254 */         cTileWidth = this.destinationRegion.width + this.destinationRegion.x - tOffx;
/*      */       }
/*      */       
/*  257 */       if (tOffy + cTileHeight >= this.destinationRegion.height + this.destinationRegion.y)
/*      */       {
/*  259 */         cTileHeight = this.destinationRegion.height + this.destinationRegion.y - tOffy;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  264 */       if (this.pixbuf == null || this.pixbuf.length < cTileWidth * numBands)
/*  265 */         this.pixbuf = new int[cTileWidth * numBands]; 
/*  266 */       boolean prog = false;
/*      */ 
/*      */       
/*  269 */       for (int l = 0; l < cTileHeight && 
/*  270 */         !this.reader.getAbortRequest(); l++)
/*      */       {
/*      */ 
/*      */         
/*  274 */         for (int i = 0; i < numBands && 
/*  275 */           !this.reader.getAbortRequest(); i++)
/*      */         {
/*  277 */           e db = this.dataBlocks[i];
/*  278 */           db.e = 0;
/*  279 */           db.f = l;
/*  280 */           db.g = cTileWidth;
/*  281 */           db.h = 1;
/*  282 */           this.ictransf.getInternCompData((c)db, this.channelMap[this.sourceBands[i]]);
/*  283 */           prog = (prog || db.k);
/*      */           
/*  285 */           int[] data = db.l;
/*  286 */           int k1 = db.i + cTileWidth - 1;
/*      */           
/*  288 */           int fracBit = this.fracBits[i];
/*  289 */           int lS = this.levelShift[i];
/*  290 */           int min = this.minValues[i];
/*  291 */           int max = this.maxValues[i];
/*      */           
/*  293 */           if (ImageUtil.isBinary(this.sampleModel))
/*      */           {
/*  295 */             min = 0;
/*  296 */             max = 1;
/*  297 */             if (this.bytebuf == null || this.bytebuf.length < cTileWidth * numBands)
/*  298 */               this.bytebuf = new byte[cTileWidth * numBands]; 
/*  299 */             int j = cTileWidth - 1;
/*  300 */             for (; j >= 0; j--) {
/*  301 */               int tmp = (data[k1--] >> fracBit) + lS;
/*  302 */               this.bytebuf[j] = (byte)((tmp < min) ? min : ((tmp > max) ? max : tmp));
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/*  307 */             ImageUtil.setUnpackedBinaryData(this.bytebuf, raster, new Rectangle(tOffx, tOffy + l, cTileWidth, 1));
/*      */ 
/*      */           
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*      */             
/*  315 */             int j = cTileWidth - 1;
/*  316 */             for (; j >= 0; j--) {
/*  317 */               int tmp = (data[k1--] >> fracBit) + lS;
/*  318 */               this.pixbuf[j] = (tmp < min) ? min : ((tmp > max) ? max : tmp);
/*      */             } 
/*      */ 
/*      */             
/*  322 */             raster.setSamples(tOffx, tOffy + l, cTileWidth, 1, this.destinationBands[i], this.pixbuf);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  332 */       readSubsampledRaster(raster);
/*      */     } 
/*      */     
/*  335 */     return raster;
/*      */   }
/*      */   
/*      */   public Rectangle getDestinationRegion() {
/*  339 */     return this.destinationRegion;
/*      */   }
/*      */   
/*      */   public BufferedImage readBufferedImage() throws IOException {
/*  343 */     this.colorModel = getColorModel();
/*  344 */     this.sampleModel = getSampleModel();
/*  345 */     WritableRaster raster = null;
/*  346 */     BufferedImage image = this.j2krparam.getDestination();
/*      */     
/*  348 */     int x = this.destinationRegion.x;
/*  349 */     int y = this.destinationRegion.y;
/*  350 */     this.destinationRegion.setLocation(this.j2krparam.getDestinationOffset());
/*  351 */     if (image == null) {
/*      */       
/*  353 */       ImageTypeSpecifier type = this.j2krparam.getDestinationType();
/*  354 */       if (type != null) {
/*  355 */         this.colorModel = type.getColorModel();
/*      */       }
/*  357 */       raster = Raster.createWritableRaster(this.sampleModel
/*  358 */           .createCompatibleSampleModel(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height), new Point(0, 0));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  364 */       image = new BufferedImage(this.colorModel, raster, this.colorModel.isAlphaPremultiplied(), new Hashtable<Object, Object>());
/*      */     } else {
/*      */       
/*  367 */       raster = image.getWritableTile(0, 0);
/*      */     } 
/*  369 */     this.destImage = image;
/*  370 */     readSubsampledRaster(raster);
/*  371 */     this.destinationRegion.setLocation(x, y);
/*  372 */     this.destImage = null;
/*  373 */     return image;
/*      */   }
/*      */   
/*      */   public Raster readAsRaster() throws IOException {
/*  377 */     BufferedImage image = this.j2krparam.getDestination();
/*  378 */     WritableRaster raster = null;
/*      */     
/*  380 */     if (image == null) {
/*  381 */       raster = Raster.createWritableRaster(this.sampleModel
/*  382 */           .createCompatibleSampleModel(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height), new Point(0, 0));
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  388 */       raster = image.getWritableTile(0, 0);
/*      */     } 
/*  390 */     readSubsampledRaster(raster);
/*  391 */     return raster;
/*      */   }
/*      */ 
/*      */   
/*      */   private void initializeRead(int imageIndex, J2KImageReadParamJava param, J2KMetadata metadata) {
/*      */     try {
/*  397 */       this.iis.mark();
/*  398 */       this.in = new IISRandomAccessIO(this.iis);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  403 */       this.ff = new a(this.in, metadata);
/*  404 */       this.ff.a();
/*  405 */       this.in.seek(this.ff.c());
/*      */       
/*  407 */       this.hi = new d();
/*      */       try {
/*  409 */         this.hd = new d(this.in, this.j2krparam, this.hi);
/*  410 */       } catch (EOFException eOFException) {
/*  411 */         throw new RuntimeException(I18N.getString("J2KReadState2"));
/*  412 */       } catch (IOException ioe) {
/*  413 */         throw new RuntimeException(ioe);
/*      */       } 
/*      */       
/*  416 */       this.width = this.hd.c();
/*  417 */       this.height = this.hd.d();
/*      */       
/*  419 */       Rectangle sourceRegion = param.getSourceRegion();
/*  420 */       this.sourceOrigin = new Point();
/*      */       
/*  422 */       sourceRegion = new Rectangle(this.hd.e(), this.hd.f(), this.width, this.height);
/*      */ 
/*      */ 
/*      */       
/*  426 */       boolean compConsistent = true;
/*  427 */       this.stepX = this.hd.c(0);
/*  428 */       this.stepY = this.hd.d(0);
/*  429 */       for (int i = 1; i < this.nComp; i++) {
/*  430 */         if (this.stepX != this.hd.c(i) || this.stepY != this.hd.d(i)) {
/*  431 */           throw new RuntimeException(I18N.getString("J2KReadState12"));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  436 */       int minResLevels = (this.hd.m()).g.b();
/*      */ 
/*      */       
/*  439 */       this
/*  440 */         .resolution = (param != null) ? param.getResolution() : minResLevels;
/*  441 */       if (this.resolution < 0 || this.resolution > minResLevels) {
/*  442 */         this.resolution = minResLevels;
/*      */       }
/*      */ 
/*      */       
/*  446 */       if (this.resolution != minResLevels || this.stepX != 1 || this.stepY != 1)
/*      */       {
/*  448 */         sourceRegion = J2KImageReader.getReducedRect(sourceRegion, minResLevels, this.resolution, this.stepX, this.stepY);
/*      */       }
/*      */ 
/*      */       
/*  452 */       this.destinationRegion = (Rectangle)sourceRegion.clone();
/*      */       
/*  454 */       J2KImageReader.computeRegionsWrapper((ImageReadParam)param, false, this.width, this.height, param
/*      */ 
/*      */ 
/*      */           
/*  458 */           .getDestination(), sourceRegion, this.destinationRegion);
/*      */ 
/*      */ 
/*      */       
/*  462 */       this.sourceOrigin = new Point(sourceRegion.x, sourceRegion.y);
/*  463 */       this.scaleX = param.getSourceXSubsampling();
/*  464 */       this.scaleY = param.getSourceYSubsampling();
/*  465 */       this.xOffset = param.getSubsamplingXOffset();
/*  466 */       this.yOffset = param.getSubsamplingYOffset();
/*      */       
/*  468 */       this.width = this.destinationRegion.width;
/*  469 */       this.height = this.destinationRegion.height;
/*      */       
/*  471 */       Point tileOffset = this.hd.a(null);
/*      */       
/*  473 */       this.tileWidth = this.hd.g();
/*  474 */       this.tileHeight = this.hd.h();
/*      */ 
/*      */       
/*  477 */       if (this.resolution != minResLevels || this.stepX != 1 || this.stepY != 1) {
/*  478 */         Rectangle tileRect = new Rectangle(tileOffset);
/*  479 */         tileRect.width = this.tileWidth;
/*  480 */         tileRect.height = this.tileHeight;
/*      */         
/*  482 */         tileRect = J2KImageReader.getReducedRect(tileRect, minResLevels, this.resolution, this.stepX, this.stepY);
/*      */         
/*  484 */         tileOffset = tileRect.getLocation();
/*  485 */         this.tileWidth = tileRect.width;
/*  486 */         this.tileHeight = tileRect.height;
/*      */       } 
/*      */       
/*  489 */       this.tileXOffset = tileOffset.x;
/*  490 */       this.tileYOffset = tileOffset.y;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  498 */       if (this.tileWidth * (1 << minResLevels - this.resolution) * this.stepX > this.hd
/*  499 */         .g()) {
/*  500 */         this
/*      */           
/*  502 */           .tileStepX = (this.tileWidth * (1 << minResLevels - this.resolution) * this.stepX + this.hd.g() - 1) / this.hd.g();
/*      */       } else {
/*  504 */         this.tileStepX = 1;
/*      */       } 
/*      */       
/*  507 */       if (this.tileHeight * (1 << minResLevels - this.resolution) * this.stepY > this.hd
/*  508 */         .h()) {
/*  509 */         this
/*      */           
/*  511 */           .tileStepY = (this.tileHeight * (1 << minResLevels - this.resolution) * this.stepY + this.hd.h() - 1) / this.hd.h();
/*      */       } else {
/*  513 */         this.tileStepY = 1;
/*      */       } 
/*      */       
/*  516 */       if (!this.destinationRegion.equals(sourceRegion)) {
/*  517 */         this.noTransform = false;
/*      */       }
/*      */ 
/*      */       
/*  521 */       this.decSpec = this.hd.m();
/*      */ 
/*      */ 
/*      */       
/*  525 */       this.nComp = this.hd.i();
/*      */       
/*  527 */       int[] depth = new int[this.nComp];
/*  528 */       for (int j = 0; j < this.nComp; j++) {
/*  529 */         depth[j] = this.hd.b(j);
/*      */       }
/*      */       
/*  532 */       ChannelDefinitionBox cdb = null;
/*  533 */       if (metadata != null) {
/*  534 */         cdb = (ChannelDefinitionBox)metadata.getElement("JPEG2000ChannelDefinitionBox");
/*      */       }
/*  536 */       this.channelMap = new int[this.nComp];
/*  537 */       if (cdb != null && metadata
/*  538 */         .getElement("JPEG2000PaletteBox") == null)
/*  539 */       { short[] assoc = cdb.getAssociation();
/*  540 */         short[] types = cdb.getTypes();
/*  541 */         short[] channels = cdb.getChannel();
/*      */         
/*  543 */         for (int m = 0; m < types.length; m++) {
/*  544 */           if (types[m] == 0)
/*  545 */           { this.channelMap[channels[m]] = assoc[m] - 1; }
/*  546 */           else if (types[m] == 1 || types[m] == 2)
/*  547 */           { this.channelMap[channels[m]] = channels[m]; } 
/*      */         }  }
/*  549 */       else { for (int m = 0; m < this.nComp; m++) {
/*  550 */           this.channelMap[m] = m;
/*      */         } }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  556 */         boolean logJJ2000Messages = Boolean.getBoolean("jj2000.j2k.decoder.log");
/*  557 */         this
/*  558 */           .breader = a.a(this.in, this.hd, this.j2krparam, this.decSpec, logJJ2000Messages, this.hi);
/*      */       
/*      */       }
/*  561 */       catch (IOException iOException) {
/*  562 */         throw new RuntimeException(I18N.getString("J2KReadState3") + " " + (
/*  563 */             (iOException.getMessage() != null) ? (":\n" + iOException
/*  564 */             .getMessage()) : ""));
/*  565 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  566 */         throw new RuntimeException(I18N.getString("J2KReadState4") + " " + (
/*  567 */             (illegalArgumentException.getMessage() != null) ? (":\n" + illegalArgumentException
/*  568 */             .getMessage()) : ""));
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  573 */         this.entdec = this.hd.a((c)this.breader, this.j2krparam);
/*  574 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  575 */         throw new RuntimeException(I18N.getString("J2KReadState5") + " " + (
/*  576 */             (illegalArgumentException.getMessage() != null) ? (":\n" + illegalArgumentException
/*  577 */             .getMessage()) : ""));
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  582 */         this.roids = this.hd.a((a)this.entdec, this.j2krparam, this.decSpec);
/*  583 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  584 */         throw new RuntimeException(I18N.getString("J2KReadState6") + " " + (
/*  585 */             (illegalArgumentException.getMessage() != null) ? (":\n" + illegalArgumentException
/*  586 */             .getMessage()) : ""));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  592 */         this.deq = this.hd.a((a)this.roids, depth, this.decSpec);
/*  593 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  594 */         throw new RuntimeException(I18N.getString("J2KReadState7") + " " + (
/*  595 */             (illegalArgumentException.getMessage() != null) ? (":\n" + illegalArgumentException
/*  596 */             .getMessage()) : ""));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  602 */         this.invWT = f.a((a)this.deq, this.decSpec);
/*  603 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  604 */         throw new RuntimeException(I18N.getString("J2KReadState8") + " " + (
/*  605 */             (illegalArgumentException.getMessage() != null) ? (":\n" + illegalArgumentException
/*  606 */             .getMessage()) : ""));
/*      */       } 
/*      */       
/*  609 */       int res = this.breader.h();
/*  610 */       int mrl = this.decSpec.g.b();
/*  611 */       this.invWT.b(res);
/*      */ 
/*      */       
/*  614 */       this.converter = new h((a)this.invWT, 0);
/*      */ 
/*      */       
/*  617 */       this.ictransf = new a((a)this.converter, this.decSpec, depth);
/*      */ 
/*      */       
/*  620 */       this.sourceBands = this.j2krparam.getSourceBands();
/*      */       
/*  622 */       if (this.sourceBands == null) {
/*  623 */         this.sourceBands = new int[this.nComp];
/*  624 */         for (int m = 0; m < this.nComp; m++) {
/*  625 */           this.sourceBands[m] = m;
/*      */         }
/*      */       } 
/*  628 */       this.nComp = this.sourceBands.length;
/*      */       
/*  630 */       this.destinationBands = this.j2krparam.getDestinationBands();
/*  631 */       if (this.destinationBands == null) {
/*  632 */         this.destinationBands = new int[this.nComp];
/*  633 */         for (int m = 0; m < this.nComp; m++) {
/*  634 */           this.destinationBands[m] = m;
/*      */         }
/*      */       } 
/*  637 */       J2KImageReader.checkReadParamBandSettingsWrapper((ImageReadParam)param, this.hd
/*  638 */           .i(), this.destinationBands.length);
/*      */ 
/*      */       
/*  641 */       this.levelShift = new int[this.nComp];
/*  642 */       this.minValues = new int[this.nComp];
/*  643 */       this.maxValues = new int[this.nComp];
/*  644 */       this.fracBits = new int[this.nComp];
/*  645 */       this.dataBlocks = new e[this.nComp];
/*      */       
/*  647 */       depth = new int[this.nComp];
/*  648 */       this.bandOffsets = new int[this.nComp];
/*  649 */       this.maxDepth = 0;
/*  650 */       this.isSigned = false;
/*  651 */       for (int k = 0; k < this.nComp; k++) {
/*  652 */         depth[k] = this.hd.b(this.sourceBands[k]);
/*  653 */         if (depth[k] > this.maxDepth)
/*  654 */           this.maxDepth = depth[k]; 
/*  655 */         this.dataBlocks[k] = new e();
/*      */ 
/*      */ 
/*      */         
/*  659 */         this.bandOffsets[k] = k;
/*  660 */         if (this.hd.a(this.sourceBands[k])) {
/*  661 */           this.isSigned = true;
/*      */         } else {
/*  663 */           this.levelShift[k] = 1 << this.ictransf
/*  664 */             .getNomRangeBits(this.sourceBands[k]) - 1;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  669 */         int nomRangeBits = this.ictransf.getNomRangeBits(this.sourceBands[k]);
/*  670 */         this.maxValues[k] = (1 << ((this.isSigned == true) ? (nomRangeBits - 1) : nomRangeBits)) - 1;
/*      */         
/*  672 */         this.minValues[k] = this.isSigned ? -(this.maxValues[k] + 1) : 0;
/*      */         
/*  674 */         this.fracBits[k] = this.ictransf.getFixedPoint(this.sourceBands[k]);
/*      */       } 
/*      */       
/*  677 */       this.iis.reset();
/*  678 */     } catch (IllegalArgumentException illegalArgumentException) {
/*  679 */       throw new RuntimeException(illegalArgumentException.getMessage(), illegalArgumentException);
/*  680 */     } catch (Error error) {
/*  681 */       if (error.getMessage() != null) {
/*  682 */         throw new RuntimeException(error.getMessage(), error);
/*      */       }
/*  684 */       throw new RuntimeException(I18N.getString("J2KReadState9"), error);
/*      */     }
/*  686 */     catch (RuntimeException runtimeException) {
/*  687 */       if (runtimeException.getMessage() != null) {
/*  688 */         throw new RuntimeException(I18N.getString("J2KReadState10") + " " + runtimeException
/*  689 */             .getMessage(), runtimeException);
/*      */       }
/*  691 */       throw new RuntimeException(I18N.getString("J2KReadState10"), runtimeException);
/*      */     }
/*  693 */     catch (Throwable throwable) {
/*  694 */       throw new RuntimeException(I18N.getString("J2KReadState10"), throwable);
/*      */     } 
/*      */   }
/*      */   
/*      */   private Raster readSubsampledRaster(WritableRaster raster) throws IOException {
/*  699 */     if (raster == null) {
/*  700 */       raster = Raster.createWritableRaster(this.sampleModel
/*  701 */           .createCompatibleSampleModel(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height), new Point(this.destinationRegion.x, this.destinationRegion.y));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  707 */     int[] pixbuf = null;
/*  708 */     boolean prog = false;
/*  709 */     Point nT = this.ictransf.getNumTiles(null);
/*  710 */     int numBands = this.sourceBands.length;
/*      */     
/*  712 */     Rectangle destRect = raster.getBounds().intersection(this.destinationRegion);
/*      */     
/*  714 */     int offx = this.destinationRegion.x;
/*  715 */     int offy = this.destinationRegion.y;
/*      */     
/*  717 */     int sourceSX = (destRect.x - offx) * this.scaleX + this.sourceOrigin.x;
/*  718 */     int sourceSY = (destRect.y - offy) * this.scaleY + this.sourceOrigin.y;
/*  719 */     int sourceEX = (destRect.width - 1) * this.scaleX + sourceSX;
/*  720 */     int sourceEY = (destRect.height - 1) * this.scaleY + sourceSY;
/*      */     
/*  722 */     int startXTile = (sourceSX - this.tileXOffset) / this.tileWidth;
/*  723 */     int startYTile = (sourceSY - this.tileYOffset) / this.tileHeight;
/*  724 */     int endXTile = (sourceEX - this.tileXOffset) / this.tileWidth;
/*  725 */     int endYTile = (sourceEY - this.tileYOffset) / this.tileHeight;
/*      */     
/*  727 */     startXTile = clip(startXTile, 0, nT.x - 1);
/*  728 */     startYTile = clip(startYTile, 0, nT.y - 1);
/*  729 */     endXTile = clip(endXTile, 0, nT.x - 1);
/*  730 */     endYTile = clip(endYTile, 0, nT.y - 1);
/*      */     
/*  732 */     int totalXTiles = endXTile - startXTile + 1;
/*  733 */     int totalYTiles = endYTile - startYTile + 1;
/*  734 */     int totalTiles = totalXTiles * totalYTiles;
/*      */ 
/*      */     
/*  737 */     for (int y = startYTile; y <= endYTile && 
/*  738 */       !this.reader.getAbortRequest(); y++) {
/*      */ 
/*      */ 
/*      */       
/*  742 */       for (int x = startXTile; x <= endXTile && 
/*  743 */         !this.reader.getAbortRequest(); x++) {
/*      */ 
/*      */         
/*  746 */         float initialFraction = ((x - startXTile + (y - startYTile) * totalXTiles) / totalTiles);
/*      */ 
/*      */         
/*  749 */         this.ictransf.setTile(x * this.tileStepX, y * this.tileStepY);
/*      */         
/*  751 */         int sx = this.hd.c(0);
/*  752 */         int cTileWidth = (this.ictransf.getTileWidth() + sx - 1) / sx;
/*  753 */         int sy = this.hd.d(0);
/*  754 */         int cTileHeight = (this.ictransf.getTileHeight() + sy - 1) / sy;
/*      */ 
/*      */         
/*  757 */         int tx = 0;
/*  758 */         int ty = 0;
/*      */ 
/*      */         
/*  761 */         int startX = this.tileXOffset + x * this.tileWidth;
/*  762 */         int startY = this.tileYOffset + y * this.tileHeight;
/*      */ 
/*      */         
/*  765 */         if (sourceSX > startX) {
/*  766 */           if (startX >= this.hd.e()) {
/*  767 */             tx = sourceSX - startX;
/*  768 */             cTileWidth -= tx;
/*      */           } 
/*  770 */           startX = sourceSX;
/*      */         } 
/*      */ 
/*      */         
/*  774 */         if (sourceSY > startY) {
/*  775 */           if (startY >= this.hd.f()) {
/*  776 */             ty = sourceSY - startY;
/*  777 */             cTileHeight -= ty;
/*      */           } 
/*  779 */           startY = sourceSY;
/*      */         } 
/*      */ 
/*      */         
/*  783 */         if (sourceEX < startX + cTileWidth - 1) {
/*  784 */           cTileWidth += sourceEX - startX - cTileWidth + 1;
/*      */         }
/*  786 */         if (sourceEY < startY + cTileHeight - 1) {
/*  787 */           cTileHeight += sourceEY - startY - cTileHeight + 1;
/*      */         }
/*      */ 
/*      */         
/*  791 */         int x1 = (startX + this.scaleX - 1 - this.sourceOrigin.x) / this.scaleX;
/*  792 */         int x2 = (startX + this.scaleX - 1 + cTileWidth - this.sourceOrigin.x) / this.scaleX;
/*      */         
/*  794 */         int lineLength = x2 - x1;
/*  795 */         if (pixbuf == null || pixbuf.length < lineLength)
/*  796 */           pixbuf = new int[lineLength]; 
/*  797 */         x2 = (x2 - 1) * this.scaleX + this.sourceOrigin.x - startX;
/*      */         
/*  799 */         int y1 = (startY + this.scaleY - 1 - this.sourceOrigin.y) / this.scaleY;
/*      */         
/*  801 */         x1 += offx;
/*  802 */         y1 += offy;
/*      */ 
/*      */         
/*  805 */         int l = ty, m = y1;
/*  806 */         for (; l < ty + cTileHeight; 
/*  807 */           l += this.scaleY, m++) {
/*  808 */           if (this.reader.getAbortRequest()) {
/*      */             break;
/*      */           }
/*  811 */           for (int i = 0; i < numBands; i++) {
/*  812 */             e db = this.dataBlocks[i];
/*  813 */             db.e = tx;
/*  814 */             db.f = l;
/*  815 */             db.g = cTileWidth;
/*  816 */             db.h = 1;
/*  817 */             this.ictransf.getInternCompData((c)db, this.channelMap[this.sourceBands[i]]);
/*  818 */             prog = (prog || db.k);
/*      */             
/*  820 */             int[] data = db.l;
/*  821 */             int k1 = db.i + x2;
/*      */             
/*  823 */             int fracBit = this.fracBits[i];
/*  824 */             int lS = this.levelShift[i];
/*  825 */             int min = this.minValues[i];
/*  826 */             int max = this.maxValues[i];
/*      */             
/*  828 */             if (ImageUtil.isBinary(this.sampleModel)) {
/*      */               
/*  830 */               min = 0;
/*  831 */               max = 1;
/*  832 */               if (this.bytebuf == null || this.bytebuf.length < cTileWidth * numBands)
/*  833 */                 this.bytebuf = new byte[cTileWidth * numBands]; 
/*  834 */               for (int j = lineLength - 1; j >= 0; j--, k1 -= this.scaleX) {
/*  835 */                 int tmp = (data[k1] >> fracBit) + lS;
/*  836 */                 this.bytebuf[j] = (byte)((tmp < min) ? min : ((tmp > max) ? max : tmp));
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  841 */               ImageUtil.setUnpackedBinaryData(this.bytebuf, raster, new Rectangle(x1, m, lineLength, 1));
/*      */ 
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */               
/*  848 */               for (int j = lineLength - 1; j >= 0; j--, k1 -= this.scaleX) {
/*  849 */                 int tmp = (data[k1] >> fracBit) + lS;
/*  850 */                 pixbuf[j] = (tmp < min) ? min : ((tmp > max) ? max : tmp);
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  855 */               raster.setSamples(x1, m, lineLength, 1, this.destinationBands[i], pixbuf);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  864 */           if (this.destImage != null) {
/*  865 */             this.reader.processImageUpdateWrapper(this.destImage, x1, m, cTileWidth, 1, 1, 1, this.destinationBands);
/*      */           }
/*      */ 
/*      */           
/*  869 */           float fraction = initialFraction + ((l - ty) + 1.0F) / cTileHeight / totalTiles;
/*      */           
/*  871 */           this.reader.processImageProgressWrapper(100.0F * fraction);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  876 */     return raster;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ImageTypeSpecifier getImageType() throws IOException {
/*  882 */     getSampleModel();
/*  883 */     getColorModel();
/*      */     
/*  885 */     return new ImageTypeSpecifier(this.colorModel, this.sampleModel);
/*      */   }
/*      */   
/*      */   public SampleModel getSampleModel() {
/*  889 */     if (this.sampleModel != null) {
/*  890 */       return this.sampleModel;
/*      */     }
/*  892 */     if (this.nComp == 1 && (this.maxDepth == 1 || this.maxDepth == 2 || this.maxDepth == 4)) {
/*  893 */       this.sampleModel = new MultiPixelPackedSampleModel(0, this.tileWidth, this.tileHeight, this.maxDepth);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  898 */     else if (this.maxDepth <= 8) {
/*  899 */       this.sampleModel = new PixelInterleavedSampleModel(0, this.tileWidth, this.tileHeight, this.nComp, this.tileWidth * this.nComp, this.bandOffsets);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  906 */     else if (this.maxDepth <= 16) {
/*  907 */       this.sampleModel = new PixelInterleavedSampleModel(this.isSigned ? 2 : 1, this.tileWidth, this.tileHeight, this.nComp, this.tileWidth * this.nComp, this.bandOffsets);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  915 */     else if (this.maxDepth <= 32) {
/*  916 */       this.sampleModel = new PixelInterleavedSampleModel(3, this.tileWidth, this.tileHeight, this.nComp, this.tileWidth * this.nComp, this.bandOffsets);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  924 */       throw new IllegalArgumentException(I18N.getString("J2KReadState11") + " " + this.maxDepth);
/*      */     } 
/*  926 */     return this.sampleModel;
/*      */   }
/*      */ 
/*      */   
/*      */   public ColorModel getColorModel() {
/*  931 */     if (this.colorModel != null) {
/*  932 */       return this.colorModel;
/*      */     }
/*      */     
/*  935 */     this.colorModel = this.ff.e();
/*  936 */     if (this.colorModel != null) {
/*  937 */       return this.colorModel;
/*      */     }
/*  939 */     if (this.hi.a.k <= 4) {
/*      */       ColorSpace cs;
/*      */ 
/*      */       
/*  943 */       if (this.hi.a.k > 2) {
/*  944 */         cs = ColorSpace.getInstance(1000);
/*      */       } else {
/*  946 */         cs = ColorSpace.getInstance(1003);
/*      */       } 
/*      */       
/*  949 */       int[] bitsPerComponent = new int[this.hi.a.k];
/*  950 */       boolean isSigned = false;
/*  951 */       int maxBitDepth = -1;
/*  952 */       for (int i = 0; i < this.hi.a.k; i++) {
/*  953 */         bitsPerComponent[i] = this.hi.a.d(i);
/*  954 */         if (maxBitDepth < bitsPerComponent[i]) {
/*  955 */           maxBitDepth = bitsPerComponent[i];
/*      */         }
/*  957 */         isSigned |= this.hi.a.c(i);
/*      */       } 
/*      */       
/*  960 */       boolean hasAlpha = (this.hi.a.k % 2 == 0);
/*      */       
/*  962 */       int type = -1;
/*      */       
/*  964 */       if (maxBitDepth <= 8) {
/*  965 */         type = 0;
/*  966 */       } else if (maxBitDepth <= 16) {
/*  967 */         type = isSigned ? 2 : 1;
/*  968 */       } else if (maxBitDepth <= 32) {
/*  969 */         type = 3;
/*      */       } 
/*      */       
/*  972 */       if (type != -1) {
/*  973 */         if (this.hi.a.k == 1 && (maxBitDepth == 1 || maxBitDepth == 2 || maxBitDepth == 4)) {
/*      */           
/*  975 */           this.colorModel = ImageUtil.createColorModel(getSampleModel());
/*      */         } else {
/*  977 */           this.colorModel = new ComponentColorModel(cs, bitsPerComponent, hasAlpha, false, hasAlpha ? 3 : 1, type);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  987 */         return this.colorModel;
/*      */       } 
/*      */     } 
/*      */     
/*  991 */     if (this.sampleModel == null) {
/*  992 */       this.sampleModel = getSampleModel();
/*      */     }
/*      */     
/*  995 */     if (this.sampleModel == null) {
/*  996 */       return null;
/*      */     }
/*  998 */     return ImageUtil.createColorModel(null, this.sampleModel);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Rectangle getTile0Rect() {
/* 1006 */     return new Rectangle(this.tileXOffset, this.tileYOffset, this.tileWidth, this.tileHeight);
/*      */   }
/*      */   
/*      */   private int clip(int value, int min, int max) {
/* 1010 */     if (value < min)
/* 1011 */       value = min; 
/* 1012 */     if (value > max)
/* 1013 */       value = max; 
/* 1014 */     return value;
/*      */   }
/*      */   
/*      */   private void clipDestination(Rectangle dest) {
/* 1018 */     Point offset = this.j2krparam.getDestinationOffset();
/* 1019 */     if (dest.x < offset.x) {
/* 1020 */       dest.width += dest.x - offset.x;
/* 1021 */       dest.x = offset.x;
/*      */     } 
/* 1023 */     if (dest.y < offset.y) {
/* 1024 */       dest.height += dest.y - offset.y;
/* 1025 */       dest.y = offset.y;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KReadState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */