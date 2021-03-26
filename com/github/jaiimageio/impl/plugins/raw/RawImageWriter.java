/*     */ package com.github.jaiimageio.impl.plugins.raw;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferDouble;
/*     */ import java.awt.image.DataBufferFloat;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferShort;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageWriterSpi;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RawImageWriter
/*     */   extends ImageWriter
/*     */ {
/* 123 */   private ImageOutputStream stream = null;
/*     */   
/*     */   private int imageIndex;
/*     */   
/*     */   private int tileWidth;
/*     */   
/*     */   private int tileHeight;
/*     */   
/*     */   private int tileXOffset;
/*     */   
/*     */   private int tileYOffset;
/*     */   
/*     */   private int scaleX;
/*     */   
/*     */   private int scaleY;
/*     */   
/*     */   private int xOffset;
/*     */   private int yOffset;
/* 141 */   private int[] sourceBands = null;
/*     */ 
/*     */   
/*     */   private int numBands;
/*     */ 
/*     */   
/*     */   private RenderedImage input;
/*     */ 
/*     */   
/*     */   private Raster inputRaster;
/*     */   
/* 152 */   private Rectangle destinationRegion = null;
/*     */ 
/*     */   
/*     */   private SampleModel sampleModel;
/*     */ 
/*     */   
/*     */   private boolean noTransform = true;
/*     */ 
/*     */   
/*     */   private boolean noSubband = true;
/*     */   
/*     */   private boolean writeRaster = false;
/*     */   
/*     */   private boolean optimal = false;
/*     */   
/*     */   private int pxlStride;
/*     */   
/*     */   private int lineStride;
/*     */   
/*     */   private int bandStride;
/*     */ 
/*     */   
/*     */   public RawImageWriter(ImageWriterSpi originator) {
/* 175 */     super(originator);
/*     */   }
/*     */   
/*     */   public void setOutput(Object output) {
/* 179 */     super.setOutput(output);
/* 180 */     if (output != null) {
/* 181 */       if (!(output instanceof ImageOutputStream))
/* 182 */         throw new IllegalArgumentException(I18N.getString("RawImageWriter0")); 
/* 183 */       this.stream = (ImageOutputStream)output;
/*     */     } else {
/* 185 */       this.stream = null;
/*     */     } 
/*     */   }
/*     */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata convertImageMetadata(IIOMetadata metadata, ImageTypeSpecifier type, ImageWriteParam param) {
/* 205 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canWriteRasters() {
/* 209 */     return true;
/*     */   }
/*     */   
/*     */   public ImageWriteParam getDefaultWriteParam() {
/* 213 */     return new RawImageWriteParam(getLocale());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
/* 219 */     clearAbortRequest();
/* 220 */     processImageStarted(this.imageIndex++);
/*     */     
/* 222 */     if (param == null) {
/* 223 */       param = getDefaultWriteParam();
/*     */     }
/* 225 */     this.writeRaster = image.hasRaster();
/* 226 */     Rectangle sourceRegion = param.getSourceRegion();
/* 227 */     ColorModel colorModel = null;
/* 228 */     Rectangle originalRegion = null;
/*     */     
/* 230 */     if (this.writeRaster) {
/* 231 */       this.inputRaster = image.getRaster();
/* 232 */       this.sampleModel = this.inputRaster.getSampleModel();
/* 233 */       originalRegion = this.inputRaster.getBounds();
/*     */     } else {
/* 235 */       this.input = image.getRenderedImage();
/* 236 */       this.sampleModel = this.input.getSampleModel();
/*     */ 
/*     */       
/* 239 */       originalRegion = new Rectangle(this.input.getMinX(), this.input.getMinY(), this.input.getWidth(), this.input.getHeight());
/*     */       
/* 241 */       colorModel = this.input.getColorModel();
/*     */     } 
/*     */     
/* 244 */     if (sourceRegion == null) {
/* 245 */       sourceRegion = (Rectangle)originalRegion.clone();
/*     */     } else {
/* 247 */       sourceRegion = sourceRegion.intersection(originalRegion);
/*     */     } 
/* 249 */     if (sourceRegion.isEmpty()) {
/* 250 */       throw new RuntimeException(I18N.getString("RawImageWriter1"));
/*     */     }
/* 252 */     this.scaleX = param.getSourceXSubsampling();
/* 253 */     this.scaleY = param.getSourceYSubsampling();
/* 254 */     this.xOffset = param.getSubsamplingXOffset();
/* 255 */     this.yOffset = param.getSubsamplingYOffset();
/*     */     
/* 257 */     sourceRegion.translate(this.xOffset, this.yOffset);
/* 258 */     sourceRegion.width -= this.xOffset;
/* 259 */     sourceRegion.height -= this.yOffset;
/*     */     
/* 261 */     this.xOffset = sourceRegion.x % this.scaleX;
/* 262 */     this.yOffset = sourceRegion.y % this.scaleY;
/*     */     
/* 264 */     int minX = sourceRegion.x / this.scaleX;
/* 265 */     int minY = sourceRegion.y / this.scaleY;
/* 266 */     int w = (sourceRegion.width + this.scaleX - 1) / this.scaleX;
/* 267 */     int h = (sourceRegion.height + this.scaleY - 1) / this.scaleY;
/*     */     
/* 269 */     this.destinationRegion = new Rectangle(minX, minY, w, h);
/* 270 */     this.noTransform = this.destinationRegion.equals(originalRegion);
/*     */     
/* 272 */     this.tileHeight = this.sampleModel.getHeight();
/* 273 */     this.tileWidth = this.sampleModel.getWidth();
/* 274 */     if (this.noTransform) {
/* 275 */       if (this.writeRaster) {
/* 276 */         this.tileXOffset = this.inputRaster.getMinX();
/* 277 */         this.tileYOffset = this.inputRaster.getMinY();
/*     */       } else {
/* 279 */         this.tileXOffset = this.input.getTileGridXOffset();
/* 280 */         this.tileYOffset = this.input.getTileGridYOffset();
/*     */       } 
/*     */     } else {
/* 283 */       this.tileXOffset = this.destinationRegion.x;
/* 284 */       this.tileYOffset = this.destinationRegion.y;
/*     */     } 
/*     */     
/* 287 */     this.sourceBands = param.getSourceBands();
/* 288 */     boolean noSubband = true;
/* 289 */     this.numBands = this.sampleModel.getNumBands();
/*     */     
/* 291 */     if (this.sourceBands != null) {
/* 292 */       this.sampleModel = this.sampleModel.createSubsetSampleModel(this.sourceBands);
/* 293 */       colorModel = null;
/* 294 */       noSubband = false;
/* 295 */       this.numBands = this.sampleModel.getNumBands();
/*     */     } else {
/* 297 */       this.sourceBands = new int[this.numBands];
/* 298 */       for (int i = 0; i < this.numBands; i++) {
/* 299 */         this.sourceBands[i] = i;
/*     */       }
/*     */     } 
/* 302 */     if (this.sampleModel instanceof ComponentSampleModel) {
/* 303 */       ComponentSampleModel csm = (ComponentSampleModel)this.sampleModel;
/* 304 */       int[] bandOffsets = csm.getBandOffsets();
/*     */       
/* 306 */       this.bandStride = bandOffsets[0];
/*     */       
/* 308 */       for (int i = 1; i < bandOffsets.length; i++) {
/* 309 */         if (this.bandStride > bandOffsets[i])
/* 310 */           this.bandStride = bandOffsets[i]; 
/*     */       } 
/* 312 */       int[] bankIndices = csm.getBankIndices();
/* 313 */       int numBank = bankIndices[0];
/* 314 */       for (int j = 1; j < bankIndices.length; j++) {
/* 315 */         if (numBank > bankIndices[j])
/* 316 */           numBank = bankIndices[j]; 
/*     */       } 
/* 318 */       this.pxlStride = csm.getPixelStride();
/* 319 */       this.lineStride = csm.getScanlineStride();
/*     */       
/* 321 */       this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 327 */         .optimal = (this.bandStride == 0 || (this.pxlStride < this.lineStride && this.pxlStride == this.numBands) || (this.lineStride < this.pxlStride && this.lineStride == this.numBands) || (this.pxlStride < this.lineStride && this.lineStride == this.numBands * csm.getWidth()) || (this.lineStride < this.pxlStride && this.pxlStride == this.numBands * csm.getHeight()) || csm instanceof java.awt.image.BandedSampleModel);
/*     */     }
/* 329 */     else if (this.sampleModel instanceof java.awt.image.SinglePixelPackedSampleModel || this.sampleModel instanceof java.awt.image.MultiPixelPackedSampleModel) {
/*     */       
/* 331 */       this.optimal = true;
/*     */     } 
/*     */     
/* 334 */     int numXTiles = getMaxTileX() - getMinTileX() + 1;
/* 335 */     int totalTiles = numXTiles * (getMaxTileY() - getMinTileY() + 1);
/*     */     
/* 337 */     for (int y = getMinTileY(); y <= getMaxTileY(); y++) {
/* 338 */       for (int x = getMinTileX(); x <= getMaxTileX(); x++) {
/* 339 */         writeRaster(getTile(x, y));
/*     */         
/* 341 */         float percentage = ((x + y * numXTiles) + 1.0F) / totalTiles;
/* 342 */         processImageProgress(percentage * 100.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 346 */     this.stream.flush();
/* 347 */     if (abortRequested()) {
/* 348 */       processWriteAborted();
/*     */     } else {
/* 350 */       processImageComplete();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 355 */     return this.destinationRegion.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 359 */     return this.destinationRegion.height;
/*     */   }
/*     */   
/*     */   private void writeRaster(Raster raster) throws IOException {
/* 363 */     int numBank = 0;
/* 364 */     int bandStride = 0;
/* 365 */     int[] bankIndices = null;
/* 366 */     int[] bandOffsets = null;
/* 367 */     int bandSize = 0;
/* 368 */     int numBand = this.sampleModel.getNumBands();
/* 369 */     int type = this.sampleModel.getDataType();
/*     */     
/* 371 */     if (this.sampleModel instanceof ComponentSampleModel) {
/* 372 */       ComponentSampleModel csm = (ComponentSampleModel)this.sampleModel;
/*     */       
/* 374 */       bandOffsets = csm.getBandOffsets(); int i;
/* 375 */       for (i = 0; i < numBand; i++) {
/* 376 */         if (bandStride < bandOffsets[i])
/* 377 */           bandStride = bandOffsets[i]; 
/*     */       } 
/* 379 */       bankIndices = csm.getBankIndices();
/* 380 */       for (i = 0; i < numBand; i++) {
/* 381 */         if (numBank < bankIndices[i])
/* 382 */           numBank = bankIndices[i]; 
/*     */       } 
/* 384 */       bandSize = (int)ImageUtil.getBandSize(this.sampleModel);
/*     */     } 
/*     */     
/* 387 */     byte[] bdata = null;
/* 388 */     short[] sdata = null;
/* 389 */     int[] idata = null;
/* 390 */     float[] fdata = null;
/* 391 */     double[] ddata = null;
/*     */     
/* 393 */     if (raster.getParent() != null && 
/* 394 */       !this.sampleModel.equals(raster.getParent().getSampleModel())) {
/*     */       
/* 396 */       WritableRaster ras = Raster.createWritableRaster(this.sampleModel, new Point(raster
/* 397 */             .getMinX(), raster
/* 398 */             .getMinY()));
/* 399 */       ras.setRect(raster);
/* 400 */       raster = ras;
/*     */     } 
/*     */     
/* 403 */     DataBuffer data = raster.getDataBuffer();
/*     */     
/* 405 */     if (this.optimal) {
/* 406 */       if (numBank > 0) {
/* 407 */         for (int i = 0; i < this.numBands; i++) {
/* 408 */           int bank = bankIndices[this.sourceBands[i]];
/* 409 */           switch (type) {
/*     */             case 0:
/* 411 */               bdata = ((DataBufferByte)data).getData(bank);
/* 412 */               this.stream.write(bdata, 0, bdata.length);
/*     */               break;
/*     */             case 2:
/* 415 */               sdata = ((DataBufferShort)data).getData(bank);
/* 416 */               this.stream.writeShorts(sdata, 0, sdata.length);
/*     */               break;
/*     */             case 1:
/* 419 */               sdata = ((DataBufferUShort)data).getData(bank);
/* 420 */               this.stream.writeShorts(sdata, 0, sdata.length);
/*     */               break;
/*     */             case 3:
/* 423 */               idata = ((DataBufferInt)data).getData(bank);
/* 424 */               this.stream.writeInts(idata, 0, idata.length);
/*     */               break;
/*     */             case 4:
/* 427 */               fdata = ((DataBufferFloat)data).getData(bank);
/* 428 */               this.stream.writeFloats(fdata, 0, fdata.length);
/*     */               break;
/*     */             case 5:
/* 431 */               ddata = ((DataBufferDouble)data).getData(bank);
/* 432 */               this.stream.writeDoubles(ddata, 0, ddata.length);
/*     */               break;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 437 */         switch (type) {
/*     */           case 0:
/* 439 */             bdata = ((DataBufferByte)data).getData();
/*     */             break;
/*     */           case 2:
/* 442 */             sdata = ((DataBufferShort)data).getData();
/*     */             break;
/*     */           case 1:
/* 445 */             sdata = ((DataBufferUShort)data).getData();
/*     */             break;
/*     */           case 3:
/* 448 */             idata = ((DataBufferInt)data).getData();
/*     */             break;
/*     */           case 4:
/* 451 */             fdata = ((DataBufferFloat)data).getData();
/*     */             break;
/*     */           case 5:
/* 454 */             ddata = ((DataBufferDouble)data).getData();
/*     */             break;
/*     */         } 
/*     */         
/* 458 */         if (!this.noSubband && bandStride >= raster
/* 459 */           .getWidth() * raster
/* 460 */           .getHeight() * (this.numBands - 1)) {
/*     */           
/* 462 */           for (int i = 0; i < this.numBands; i++) {
/* 463 */             int offset = bandOffsets[this.sourceBands[i]];
/* 464 */             switch (type) {
/*     */               case 0:
/* 466 */                 this.stream.write(bdata, offset, bandSize);
/*     */                 break;
/*     */               case 1:
/*     */               case 2:
/* 470 */                 this.stream.writeShorts(sdata, offset, bandSize);
/*     */                 break;
/*     */               case 3:
/* 473 */                 this.stream.writeInts(idata, offset, bandSize);
/*     */                 break;
/*     */               case 4:
/* 476 */                 this.stream.writeFloats(fdata, offset, bandSize);
/*     */                 break;
/*     */               case 5:
/* 479 */                 this.stream.writeDoubles(ddata, offset, bandSize);
/*     */                 break;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 484 */           switch (type) {
/*     */             case 0:
/* 486 */               this.stream.write(bdata, 0, bdata.length);
/*     */               break;
/*     */             case 1:
/*     */             case 2:
/* 490 */               this.stream.writeShorts(sdata, 0, sdata.length);
/*     */               break;
/*     */             case 3:
/* 493 */               this.stream.writeInts(idata, 0, idata.length);
/*     */               break;
/*     */             case 4:
/* 496 */               this.stream.writeFloats(fdata, 0, fdata.length);
/*     */               break;
/*     */             case 5:
/* 499 */               this.stream.writeDoubles(ddata, 0, ddata.length);
/*     */               break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 504 */     } else if (this.sampleModel instanceof ComponentSampleModel) {
/*     */       
/* 506 */       switch (type) {
/*     */         case 0:
/* 508 */           bdata = ((DataBufferByte)data).getData();
/*     */           break;
/*     */         case 2:
/* 511 */           sdata = ((DataBufferShort)data).getData();
/*     */           break;
/*     */         case 1:
/* 514 */           sdata = ((DataBufferUShort)data).getData();
/*     */           break;
/*     */         case 3:
/* 517 */           idata = ((DataBufferInt)data).getData();
/*     */           break;
/*     */         case 4:
/* 520 */           fdata = ((DataBufferFloat)data).getData();
/*     */           break;
/*     */         case 5:
/* 523 */           ddata = ((DataBufferDouble)data).getData();
/*     */           break;
/*     */       } 
/*     */       
/* 527 */       ComponentSampleModel csm = (ComponentSampleModel)this.sampleModel;
/*     */       
/* 529 */       int offset = csm.getOffset(raster.getMinX() - raster.getSampleModelTranslateX(), raster
/* 530 */           .getMinY() - raster.getSampleModelTranslateY()) - bandOffsets[0];
/*     */ 
/*     */       
/* 533 */       int srcSkip = this.pxlStride;
/* 534 */       int copyLength = 1;
/* 535 */       int innerStep = this.pxlStride;
/*     */       
/* 537 */       int width = raster.getWidth();
/* 538 */       int height = raster.getHeight();
/*     */       
/* 540 */       int innerBound = width;
/* 541 */       int outerBound = height;
/*     */       
/* 543 */       if (srcSkip < this.lineStride) {
/* 544 */         if (bandStride > this.pxlStride)
/* 545 */           copyLength = width; 
/* 546 */         srcSkip = this.lineStride;
/*     */       } else {
/* 548 */         if (bandStride > this.lineStride)
/* 549 */           copyLength = height; 
/* 550 */         innerStep = this.lineStride;
/* 551 */         innerBound = height;
/* 552 */         outerBound = width;
/*     */       } 
/*     */       
/* 555 */       int writeLength = innerBound * this.numBands;
/* 556 */       byte[] destBBuf = null;
/* 557 */       short[] destSBuf = null;
/* 558 */       int[] destIBuf = null;
/* 559 */       float[] destFBuf = null;
/* 560 */       double[] destDBuf = null;
/* 561 */       Object srcBuf = null;
/* 562 */       Object dstBuf = null;
/*     */       
/* 564 */       switch (type) {
/*     */         case 0:
/* 566 */           srcBuf = bdata;
/* 567 */           dstBuf = destBBuf = new byte[writeLength];
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/* 571 */           srcBuf = sdata;
/* 572 */           dstBuf = destSBuf = new short[writeLength];
/*     */           break;
/*     */         case 3:
/* 575 */           srcBuf = idata;
/* 576 */           dstBuf = destIBuf = new int[writeLength];
/*     */           break;
/*     */         case 4:
/* 579 */           srcBuf = fdata;
/* 580 */           dstBuf = destFBuf = new float[writeLength];
/*     */           break;
/*     */         case 5:
/* 583 */           srcBuf = ddata;
/* 584 */           dstBuf = destDBuf = new double[writeLength];
/*     */           break;
/*     */       } 
/*     */       
/* 588 */       if (copyLength > 1) {
/* 589 */         for (int i = 0; i < outerBound; i++) {
/* 590 */           for (int b = 0; b < this.numBands; b++) {
/* 591 */             int bandOffset = bandOffsets[b];
/*     */             
/* 593 */             System.arraycopy(srcBuf, offset + bandOffset, dstBuf, b * innerBound, innerBound);
/*     */           } 
/*     */ 
/*     */           
/* 597 */           switch (type) {
/*     */             case 0:
/* 599 */               this.stream.write((byte[])dstBuf, 0, writeLength);
/*     */               break;
/*     */             case 1:
/*     */             case 2:
/* 603 */               this.stream.writeShorts((short[])dstBuf, 0, writeLength);
/*     */               break;
/*     */             case 3:
/* 606 */               this.stream.writeInts((int[])dstBuf, 0, writeLength);
/*     */               break;
/*     */             case 4:
/* 609 */               this.stream.writeFloats((float[])dstBuf, 0, writeLength);
/*     */               break;
/*     */             case 5:
/* 612 */               this.stream.writeDoubles((double[])dstBuf, 0, writeLength);
/*     */               break;
/*     */           } 
/* 615 */           offset += srcSkip;
/*     */         } 
/*     */       } else {
/* 618 */         int i; switch (type) {
/*     */           case 0:
/* 620 */             for (i = 0; i < outerBound; i++) {
/* 621 */               for (int b = 0, k = 0; b < this.numBands; b++) {
/* 622 */                 int bandOffset = bandOffsets[b];
/*     */                 int m;
/* 624 */                 for (int j = 0; j < innerBound; 
/* 625 */                   j++, m += innerStep)
/*     */                 {
/* 627 */                   destBBuf[k++] = bdata[m + bandOffset];
/*     */                 }
/*     */               } 
/* 630 */               this.stream.write(destBBuf, 0, writeLength);
/* 631 */               offset += srcSkip;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 1:
/*     */           case 2:
/* 637 */             for (i = 0; i < outerBound; i++) {
/* 638 */               for (int b = 0, k = 0; b < this.numBands; b++) {
/* 639 */                 int bandOffset = bandOffsets[b];
/*     */                 int m;
/* 641 */                 for (int j = 0; j < innerBound; 
/* 642 */                   j++, m += innerStep)
/*     */                 {
/* 644 */                   destSBuf[k++] = sdata[m + bandOffset];
/*     */                 }
/*     */               } 
/* 647 */               this.stream.writeShorts(destSBuf, 0, writeLength);
/* 648 */               offset += srcSkip;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 3:
/* 653 */             for (i = 0; i < outerBound; i++) {
/* 654 */               for (int b = 0, k = 0; b < this.numBands; b++) {
/* 655 */                 int bandOffset = bandOffsets[b];
/*     */                 int m;
/* 657 */                 for (int j = 0; j < innerBound; 
/* 658 */                   j++, m += innerStep)
/*     */                 {
/* 660 */                   destIBuf[k++] = idata[m + bandOffset];
/*     */                 }
/*     */               } 
/* 663 */               this.stream.writeInts(destIBuf, 0, writeLength);
/* 664 */               offset += srcSkip;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 4:
/* 669 */             for (i = 0; i < outerBound; i++) {
/* 670 */               for (int b = 0, k = 0; b < this.numBands; b++) {
/* 671 */                 int bandOffset = bandOffsets[b];
/*     */                 int m;
/* 673 */                 for (int j = 0; j < innerBound; 
/* 674 */                   j++, m += innerStep)
/*     */                 {
/* 676 */                   destFBuf[k++] = fdata[m + bandOffset];
/*     */                 }
/*     */               } 
/* 679 */               this.stream.writeFloats(destFBuf, 0, writeLength);
/* 680 */               offset += srcSkip;
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 5:
/* 685 */             for (i = 0; i < outerBound; i++) {
/* 686 */               for (int b = 0, k = 0; b < this.numBands; b++) {
/* 687 */                 int bandOffset = bandOffsets[b];
/*     */                 int m;
/* 689 */                 for (int j = 0; j < innerBound; 
/* 690 */                   j++, m += innerStep)
/*     */                 {
/* 692 */                   destDBuf[k++] = ddata[m + bandOffset];
/*     */                 }
/*     */               } 
/* 695 */               this.stream.writeDoubles(destDBuf, 0, writeLength);
/* 696 */               offset += srcSkip;
/*     */             } 
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Raster getTile(int tileX, int tileY) {
/* 706 */     int sx = this.tileXOffset + tileX * this.tileWidth;
/* 707 */     int sy = this.tileYOffset + tileY * this.tileHeight;
/* 708 */     Rectangle bounds = new Rectangle(sx, sy, this.tileWidth, this.tileHeight);
/*     */     
/* 710 */     if (this.writeRaster) {
/* 711 */       bounds = bounds.intersection(this.destinationRegion);
/* 712 */       if (this.noTransform) {
/* 713 */         return this.inputRaster.createChild(bounds.x, bounds.y, bounds.width, bounds.height, bounds.x, bounds.y, this.sourceBands);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 718 */       sx = bounds.x;
/* 719 */       sy = bounds.y;
/*     */ 
/*     */       
/* 722 */       WritableRaster writableRaster = Raster.createWritableRaster(this.sampleModel, new Point(sx, sy));
/*     */       
/* 724 */       int i = mapToSourceX(sx);
/* 725 */       int k = mapToSourceY(sy);
/*     */       
/* 727 */       int m = this.inputRaster.getMinY();
/* 728 */       int n = this.inputRaster.getMinY() + this.inputRaster.getHeight();
/*     */       
/* 730 */       int i1 = bounds.width;
/*     */       
/* 732 */       int i2 = (i1 - 1) * this.scaleX + 1;
/*     */       
/* 734 */       for (int i3 = 0; i3 < bounds.height; i3++, sy++, k += this.scaleY) {
/* 735 */         if (k >= m && k < n) {
/*     */ 
/*     */           
/* 738 */           Raster source = this.inputRaster.createChild(i, k, i2, 1, i, k, null);
/*     */           
/* 740 */           int tempX = sx; int offset;
/* 741 */           for (int i4 = 0; i4 < i1; 
/* 742 */             i4++, tempX++, offset += this.scaleX) {
/* 743 */             for (int i5 = 0; i5 < this.numBands; i5++) {
/* 744 */               int p = source.getSample(offset, k, this.sourceBands[i5]);
/* 745 */               writableRaster.setSample(tempX, sy, i5, p);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 750 */       return writableRaster;
/*     */     } 
/*     */     
/* 753 */     if (this.noTransform) {
/* 754 */       Raster raster = this.input.getTile(tileX, tileY);
/* 755 */       if (this.destinationRegion.contains(bounds) && this.noSubband) {
/* 756 */         return raster;
/*     */       }
/* 758 */       bounds = bounds.intersection(this.destinationRegion);
/* 759 */       return raster.createChild(bounds.x, bounds.y, bounds.width, bounds.height, bounds.x, bounds.y, this.sourceBands);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 765 */     bounds = bounds.intersection(this.destinationRegion);
/* 766 */     sx = bounds.x;
/* 767 */     sy = bounds.y;
/*     */ 
/*     */     
/* 770 */     WritableRaster ras = Raster.createWritableRaster(this.sampleModel, new Point(sx, sy));
/*     */     
/* 772 */     int x = mapToSourceX(sx);
/* 773 */     int y = mapToSourceY(sy);
/*     */     
/* 775 */     int minY = this.input.getMinY();
/* 776 */     int maxY = this.input.getMinY() + this.input.getHeight();
/*     */     
/* 778 */     int cTileWidth = bounds.width;
/* 779 */     int length = (cTileWidth - 1) * this.scaleX + 1;
/*     */     
/* 781 */     for (int j = 0; j < bounds.height; j++, sy++, y += this.scaleY) {
/* 782 */       if (y >= minY && y < maxY) {
/*     */ 
/*     */ 
/*     */         
/* 786 */         Raster source = this.input.getData(new Rectangle(x, y, length, 1));
/*     */         
/* 788 */         int tempX = sx; int offset;
/* 789 */         for (int i = 0; i < cTileWidth; 
/* 790 */           i++, tempX++, offset += this.scaleX) {
/* 791 */           for (int k = 0; k < this.numBands; k++) {
/* 792 */             int p = source.getSample(offset, y, this.sourceBands[k]);
/* 793 */             ras.setSample(tempX, sy, k, p);
/*     */           } 
/*     */         } 
/*     */       } 
/* 797 */     }  return ras;
/*     */   }
/*     */ 
/*     */   
/*     */   private int mapToSourceX(int x) {
/* 802 */     return x * this.scaleX + this.xOffset;
/*     */   }
/*     */   
/*     */   private int mapToSourceY(int y) {
/* 806 */     return y * this.scaleY + this.yOffset;
/*     */   }
/*     */   
/*     */   private int getMinTileX() {
/* 810 */     return ToTile(this.destinationRegion.x, this.tileXOffset, this.tileWidth);
/*     */   }
/*     */   
/*     */   private int getMaxTileX() {
/* 814 */     return ToTile(this.destinationRegion.x + this.destinationRegion.width - 1, this.tileXOffset, this.tileWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMinTileY() {
/* 819 */     return ToTile(this.destinationRegion.y, this.tileYOffset, this.tileHeight);
/*     */   }
/*     */   
/*     */   private int getMaxTileY() {
/* 823 */     return ToTile(this.destinationRegion.y + this.destinationRegion.height - 1, this.tileYOffset, this.tileHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int ToTile(int pos, int tileOffset, int tileSize) {
/* 828 */     pos -= tileOffset;
/* 829 */     if (pos < 0) {
/* 830 */       pos += 1 - tileSize;
/*     */     }
/* 832 */     return pos / tileSize;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 836 */     super.reset();
/* 837 */     this.stream = null;
/* 838 */     this.optimal = false;
/* 839 */     this.sourceBands = null;
/* 840 */     this.destinationRegion = null;
/* 841 */     this.noTransform = true;
/* 842 */     this.noSubband = true;
/* 843 */     this.writeRaster = false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/raw/RawImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */