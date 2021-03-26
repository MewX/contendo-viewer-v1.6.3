/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import c.a.a.a.d;
/*     */ import c.a.i.c;
/*     */ import c.a.i.f;
/*     */ import com.github.jaiimageio.jpeg2000.J2KImageReadParam;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class J2KImageReader
/*     */   extends ImageReader
/*     */   implements f
/*     */ {
/*  78 */   private ImageInputStream iis = null;
/*     */ 
/*     */   
/*     */   private long streamPosition0;
/*     */ 
/*     */   
/*     */   private boolean gotHeader = false;
/*     */ 
/*     */   
/*     */   private int width;
/*     */ 
/*     */   
/*     */   private int height;
/*     */ 
/*     */   
/*  93 */   private J2KMetadata imageMetadata = null;
/*     */ 
/*     */   
/*  96 */   private int imageMetadataIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private d hd;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private J2KReadState readState = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean logJJ2000Msg = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void computeRegionsWrapper(ImageReadParam param, boolean allowZeroDestOffset, int srcWidth, int srcHeight, BufferedImage image, Rectangle srcRegion, Rectangle destRegion) {
/* 124 */     if (srcRegion == null) {
/* 125 */       throw new IllegalArgumentException(I18N.getString("J2KImageReader0"));
/*     */     }
/* 127 */     if (destRegion == null) {
/* 128 */       throw new IllegalArgumentException(I18N.getString("J2KImageReader1"));
/*     */     }
/*     */ 
/*     */     
/* 132 */     int periodX = 1;
/* 133 */     int periodY = 1;
/* 134 */     int gridX = 0;
/* 135 */     int gridY = 0;
/* 136 */     if (param != null) {
/* 137 */       Rectangle paramSrcRegion = param.getSourceRegion();
/* 138 */       if (paramSrcRegion != null) {
/* 139 */         srcRegion.setBounds(srcRegion.intersection(paramSrcRegion));
/*     */       }
/* 141 */       periodX = param.getSourceXSubsampling();
/* 142 */       periodY = param.getSourceYSubsampling();
/* 143 */       gridX = param.getSubsamplingXOffset();
/* 144 */       gridY = param.getSubsamplingYOffset();
/* 145 */       srcRegion.translate(gridX, gridY);
/* 146 */       srcRegion.width -= gridX;
/* 147 */       srcRegion.height -= gridY;
/* 148 */       if (allowZeroDestOffset) {
/* 149 */         destRegion.setLocation(param.getDestinationOffset());
/*     */       } else {
/* 151 */         Point destOffset = param.getDestinationOffset();
/* 152 */         if (destOffset.x != 0 || destOffset.y != 0) {
/* 153 */           destRegion.setLocation(param.getDestinationOffset());
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (destRegion.x < 0) {
/* 161 */       int delta = -destRegion.x * periodX;
/* 162 */       srcRegion.x += delta;
/* 163 */       srcRegion.width -= delta;
/* 164 */       destRegion.x = 0;
/*     */     } 
/* 166 */     if (destRegion.y < 0) {
/* 167 */       int delta = -destRegion.y * periodY;
/* 168 */       srcRegion.y += delta;
/* 169 */       srcRegion.height -= delta;
/* 170 */       destRegion.y = 0;
/*     */     } 
/*     */ 
/*     */     
/* 174 */     int subsampledWidth = (srcRegion.width + periodX - 1) / periodX;
/* 175 */     int subsampledHeight = (srcRegion.height + periodY - 1) / periodY;
/* 176 */     destRegion.width = subsampledWidth;
/* 177 */     destRegion.height = subsampledHeight;
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (image != null) {
/*     */ 
/*     */       
/* 184 */       Rectangle destImageRect = new Rectangle(0, 0, image.getWidth(), image.getHeight());
/* 185 */       destRegion.setBounds(destRegion.intersection(destImageRect));
/* 186 */       if (destRegion.isEmpty()) {
/* 187 */         throw new IllegalArgumentException(
/* 188 */             I18N.getString("J2KImageReader2"));
/*     */       }
/*     */       
/* 191 */       int deltaX = destRegion.x + subsampledWidth - image.getWidth();
/* 192 */       if (deltaX > 0) {
/* 193 */         srcRegion.width -= deltaX * periodX;
/*     */       }
/* 195 */       int deltaY = destRegion.y + subsampledHeight - image.getHeight();
/* 196 */       if (deltaY > 0) {
/* 197 */         srcRegion.height -= deltaY * periodY;
/*     */       }
/*     */     } 
/* 200 */     if (srcRegion.isEmpty() || destRegion.isEmpty()) {
/* 201 */       throw new IllegalArgumentException(I18N.getString("J2KImageReader3"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkReadParamBandSettingsWrapper(ImageReadParam param, int numSrcBands, int numDstBands) {
/* 212 */     checkReadParamBandSettings(param, numSrcBands, numDstBands);
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
/*     */   static Rectangle getReducedRect(Rectangle r, int maxLevel, int level, int subX, int subY) {
/* 232 */     if (r == null)
/* 233 */       throw new IllegalArgumentException("r == null!"); 
/* 234 */     if (maxLevel < 0 || level < 0)
/* 235 */       throw new IllegalArgumentException("maxLevel < 0 || level < 0!"); 
/* 236 */     if (level > maxLevel) {
/* 237 */       throw new IllegalArgumentException("level > maxLevel");
/*     */     }
/*     */ 
/*     */     
/* 241 */     if (level == maxLevel && subX == 1 && subY == 1) {
/* 242 */       return r;
/*     */     }
/*     */ 
/*     */     
/* 246 */     int divisor = 1 << maxLevel - level;
/* 247 */     int divX = divisor * subX;
/* 248 */     int divY = divisor * subY;
/*     */ 
/*     */     
/* 251 */     int x1 = (r.x + divX - 1) / divX;
/* 252 */     int x2 = (r.x + r.width + divX - 1) / divX;
/* 253 */     int y1 = (r.y + divY - 1) / divY;
/* 254 */     int y2 = (r.y + r.height + divY - 1) / divY;
/*     */ 
/*     */     
/* 257 */     return new Rectangle(x1, y1, x2 - x1, y2 - y1);
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
/*     */   public void processImageUpdateWrapper(BufferedImage theImage, int minX, int minY, int width, int height, int periodX, int periodY, int[] bands) {
/* 269 */     processImageUpdate(theImage, minX, minY, width, height, periodX, periodY, bands);
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
/*     */   public void processImageProgressWrapper(float percentageDone) {
/* 281 */     processImageProgress(percentageDone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public J2KImageReader(ImageReaderSpi originator) {
/* 288 */     super(originator);
/*     */     
/* 290 */     this.logJJ2000Msg = Boolean.getBoolean("jj2000.j2k.decoder.log");
/*     */     
/* 292 */     c.a(null, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/* 299 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/* 300 */     this.ignoreMetadata = ignoreMetadata;
/* 301 */     this.iis = (ImageInputStream)input;
/* 302 */     this.imageMetadata = null;
/*     */     try {
/* 304 */       this.streamPosition0 = this.iis.getStreamPosition();
/* 305 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumImages(boolean allowSearch) throws IOException {
/* 312 */     return 1;
/*     */   }
/*     */   
/*     */   public int getWidth(int imageIndex) throws IOException {
/* 316 */     checkIndex(imageIndex);
/* 317 */     readHeader();
/* 318 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight(int imageIndex) throws IOException {
/* 322 */     checkIndex(imageIndex);
/* 323 */     readHeader();
/* 324 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getTileGridXOffset(int imageIndex) throws IOException {
/* 328 */     checkIndex(imageIndex);
/* 329 */     readHeader();
/* 330 */     return (this.hd.a(null)).x;
/*     */   }
/*     */   
/*     */   public int getTileGridYOffset(int imageIndex) throws IOException {
/* 334 */     checkIndex(imageIndex);
/* 335 */     readHeader();
/* 336 */     return (this.hd.a(null)).y;
/*     */   }
/*     */   
/*     */   public int getTileWidth(int imageIndex) throws IOException {
/* 340 */     checkIndex(imageIndex);
/* 341 */     readHeader();
/* 342 */     return this.hd.g();
/*     */   }
/*     */   
/*     */   public int getTileHeight(int imageIndex) throws IOException {
/* 346 */     checkIndex(imageIndex);
/* 347 */     readHeader();
/* 348 */     return this.hd.h();
/*     */   }
/*     */   
/*     */   private void checkIndex(int imageIndex) {
/* 352 */     if (imageIndex != 0) {
/* 353 */       throw new IndexOutOfBoundsException(I18N.getString("J2KImageReader4"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void readHeader() {
/* 358 */     if (this.gotHeader) {
/*     */       return;
/*     */     }
/* 361 */     if (this.readState == null) {
/*     */       try {
/* 363 */         this.iis.seek(this.streamPosition0);
/* 364 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/* 368 */       this
/*     */         
/* 370 */         .readState = new J2KReadState(this.iis, new J2KImageReadParamJava(getDefaultReadParam()), this);
/*     */     } 
/*     */ 
/*     */     
/* 374 */     this.hd = this.readState.getHeader();
/* 375 */     this.gotHeader = true;
/*     */     
/* 377 */     this.width = this.hd.c();
/* 378 */     this.height = this.hd.d();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator getImageTypes(int imageIndex) throws IOException {
/* 383 */     checkIndex(imageIndex);
/* 384 */     readHeader();
/* 385 */     if (this.readState != null) {
/* 386 */       ArrayList<ImageTypeSpecifier> list = new ArrayList();
/* 387 */       list.add(new ImageTypeSpecifier(this.readState.getColorModel(), this.readState
/* 388 */             .getSampleModel()));
/* 389 */       return list.iterator();
/*     */     } 
/* 391 */     return null;
/*     */   }
/*     */   
/*     */   public ImageReadParam getDefaultReadParam() {
/* 395 */     return (ImageReadParam)new J2KImageReadParam();
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
/* 400 */     checkIndex(imageIndex);
/* 401 */     if (this.ignoreMetadata) {
/* 402 */       return null;
/*     */     }
/* 404 */     if (this.imageMetadata == null) {
/* 405 */       this.iis.mark();
/* 406 */       this.imageMetadata = new J2KMetadata(this.iis, this);
/* 407 */       this.iis.reset();
/*     */     } 
/* 409 */     return this.imageMetadata;
/*     */   }
/*     */   
/*     */   public IIOMetadata getStreamMetadata() throws IOException {
/* 413 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
/* 418 */     checkIndex(imageIndex);
/* 419 */     clearAbortRequest();
/* 420 */     processImageStarted(imageIndex);
/*     */     
/* 422 */     if (param == null) {
/* 423 */       param = getDefaultReadParam();
/*     */     }
/* 425 */     J2KImageReadParamJava j2KImageReadParamJava = new J2KImageReadParamJava(param);
/*     */     
/* 427 */     if (!this.ignoreMetadata) {
/* 428 */       this.imageMetadata = new J2KMetadata();
/* 429 */       this.iis.seek(this.streamPosition0);
/* 430 */       this.readState = new J2KReadState(this.iis, j2KImageReadParamJava, this.imageMetadata, this);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 435 */       this.iis.seek(this.streamPosition0);
/* 436 */       this.readState = new J2KReadState(this.iis, j2KImageReadParamJava, this);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 441 */     BufferedImage bi = this.readState.readBufferedImage();
/* 442 */     if (abortRequested()) {
/* 443 */       processReadAborted();
/*     */     } else {
/* 445 */       processImageComplete();
/* 446 */     }  return bi;
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderedImage readAsRenderedImage(int imageIndex, ImageReadParam param) throws IOException {
/*     */     J2KRenderedImage j2KRenderedImage;
/* 452 */     checkIndex(imageIndex);
/* 453 */     RenderedImage ri = null;
/* 454 */     clearAbortRequest();
/* 455 */     processImageStarted(imageIndex);
/*     */     
/* 457 */     if (param == null) {
/* 458 */       param = getDefaultReadParam();
/*     */     }
/* 460 */     J2KImageReadParamJava j2KImageReadParamJava = new J2KImageReadParamJava(param);
/* 461 */     if (!this.ignoreMetadata) {
/* 462 */       if (this.imageMetadata == null)
/* 463 */         this.imageMetadata = new J2KMetadata(); 
/* 464 */       j2KRenderedImage = new J2KRenderedImage(this.iis, j2KImageReadParamJava, this.imageMetadata, this);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 470 */       j2KRenderedImage = new J2KRenderedImage(this.iis, j2KImageReadParamJava, this);
/* 471 */     }  if (abortRequested()) {
/* 472 */       processReadAborted();
/*     */     } else {
/* 474 */       processImageComplete();
/* 475 */     }  return (RenderedImage)j2KRenderedImage;
/*     */   }
/*     */   
/*     */   public boolean canReadRaster() {
/* 479 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isRandomAccessEasy(int imageIndex) throws IOException {
/* 483 */     checkIndex(imageIndex);
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
/* 489 */     checkIndex(imageIndex);
/* 490 */     processImageStarted(imageIndex);
/* 491 */     J2KImageReadParamJava j2KImageReadParamJava = new J2KImageReadParamJava(param);
/*     */     
/* 493 */     if (!this.ignoreMetadata) {
/* 494 */       this.imageMetadata = new J2KMetadata();
/* 495 */       this.iis.seek(this.streamPosition0);
/* 496 */       this.readState = new J2KReadState(this.iis, j2KImageReadParamJava, this.imageMetadata, this);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 501 */       this.iis.seek(this.streamPosition0);
/* 502 */       this.readState = new J2KReadState(this.iis, j2KImageReadParamJava, this);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 507 */     Raster ras = this.readState.readAsRaster();
/* 508 */     if (abortRequested()) {
/* 509 */       processReadAborted();
/*     */     } else {
/* 511 */       processImageComplete();
/* 512 */     }  return ras;
/*     */   }
/*     */   
/*     */   public boolean isImageTiled(int imageIndex) {
/* 516 */     checkIndex(imageIndex);
/* 517 */     readHeader();
/* 518 */     if (this.readState != null) {
/* 519 */       J2KRenderedImage j2KRenderedImage = new J2KRenderedImage(this.readState);
/* 520 */       if (j2KRenderedImage.getNumXTiles() * j2KRenderedImage.getNumYTiles() > 0)
/* 521 */         return true; 
/* 522 */       return false;
/*     */     } 
/* 524 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 529 */     super.reset();
/*     */     
/* 531 */     this.iis = null;
/* 532 */     this.gotHeader = false;
/* 533 */     this.imageMetadata = null;
/* 534 */     this.readState = null;
/* 535 */     System.gc();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAbortRequest() {
/* 542 */     return abortRequested();
/*     */   }
/*     */ 
/*     */   
/*     */   private ImageTypeSpecifier getImageType(int imageIndex) throws IOException {
/* 547 */     checkIndex(imageIndex);
/* 548 */     readHeader();
/* 549 */     if (this.readState != null) {
/* 550 */       return new ImageTypeSpecifier(this.readState.getColorModel(), this.readState
/* 551 */           .getSampleModel());
/*     */     }
/* 553 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */   
/*     */   public void println(String str, int flind, int ind) {
/* 562 */     printmsg(1, str);
/*     */   }
/*     */   
/*     */   public void printmsg(int sev, String msg) {
/* 566 */     if (this.logJJ2000Msg) {
/*     */       String msgSev;
/* 568 */       switch (sev) {
/*     */         case 3:
/* 570 */           msgSev = "ERROR";
/*     */           break;
/*     */         case 1:
/* 573 */           msgSev = "INFO";
/*     */           break;
/*     */         case 0:
/* 576 */           msgSev = "LOG";
/*     */           break;
/*     */         
/*     */         default:
/* 580 */           msgSev = "WARNING";
/*     */           break;
/*     */       } 
/*     */       
/* 584 */       processWarningOccurred("[JJ2000 " + msgSev + "] " + msg);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */