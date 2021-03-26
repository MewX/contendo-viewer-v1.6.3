/*     */ package com.github.jaiimageio.impl.plugins.pnm;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.security.AccessController;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import sun.security.action.GetPropertyAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNMImageReader
/*     */   extends ImageReader
/*     */ {
/*     */   private static final int PBM_ASCII = 49;
/*     */   private static final int PGM_ASCII = 50;
/*     */   private static final int PPM_ASCII = 51;
/*     */   private static final int PBM_RAW = 52;
/*     */   private static final int PGM_RAW = 53;
/*     */   private static final int PPM_RAW = 54;
/*     */   private static final int LINE_FEED = 10;
/*     */   private static byte[] lineSeparator;
/*     */   private int variant;
/*     */   private int maxValue;
/*     */   
/*     */   static {
/*  98 */     if (lineSeparator == null) {
/*  99 */       String ls = AccessController.<String>doPrivileged(new GetPropertyAction("line.separator"));
/*     */       
/* 101 */       lineSeparator = ls.getBytes();
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
/* 112 */   private ImageInputStream iis = null;
/*     */ 
/*     */   
/*     */   private boolean gotHeader = false;
/*     */ 
/*     */   
/*     */   private long imageDataOffset;
/*     */ 
/*     */   
/*     */   private int width;
/*     */ 
/*     */   
/*     */   private int height;
/*     */ 
/*     */   
/*     */   private String aLine;
/*     */   
/*     */   private StringTokenizer token;
/*     */   
/*     */   private PNMMetadata metadata;
/*     */ 
/*     */   
/*     */   public PNMImageReader(ImageReaderSpi originator) {
/* 135 */     super(originator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/* 142 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/* 143 */     this.iis = (ImageInputStream)input;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumImages(boolean allowSearch) throws IOException {
/* 148 */     return 1;
/*     */   }
/*     */   
/*     */   public int getWidth(int imageIndex) throws IOException {
/* 152 */     checkIndex(imageIndex);
/* 153 */     readHeader();
/* 154 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight(int imageIndex) throws IOException {
/* 158 */     checkIndex(imageIndex);
/* 159 */     readHeader();
/* 160 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getVariant() {
/* 164 */     return this.variant;
/*     */   }
/*     */   
/*     */   public int getMaxValue() {
/* 168 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   private void checkIndex(int imageIndex) {
/* 172 */     if (imageIndex != 0) {
/* 173 */       throw new IndexOutOfBoundsException(I18N.getString("PNMImageReader1"));
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void readHeader() throws IOException {
/* 178 */     if (this.gotHeader) {
/*     */ 
/*     */       
/* 181 */       this.iis.seek(this.imageDataOffset);
/*     */       
/*     */       return;
/*     */     } 
/* 185 */     if (this.iis != null) {
/* 186 */       if (this.iis.readByte() != 80) {
/* 187 */         throw new RuntimeException(I18N.getString("PNMImageReader0"));
/*     */       }
/*     */       
/* 190 */       this.variant = this.iis.readByte();
/* 191 */       if (this.variant < 49 || this.variant > 54) {
/* 192 */         throw new RuntimeException(I18N.getString("PNMImageReader0"));
/*     */       }
/*     */ 
/*     */       
/* 196 */       this.metadata = new PNMMetadata();
/*     */ 
/*     */       
/* 199 */       this.metadata.setVariant(this.variant);
/*     */ 
/*     */       
/* 202 */       this.iis.readLine();
/*     */       
/* 204 */       readComments(this.iis, this.metadata);
/*     */       
/* 206 */       this.width = readInteger(this.iis);
/* 207 */       this.height = readInteger(this.iis);
/*     */       
/* 209 */       if (this.variant == 49 || this.variant == 52) {
/* 210 */         this.maxValue = 1;
/*     */       } else {
/* 212 */         this.maxValue = readInteger(this.iis);
/*     */       } 
/*     */       
/* 215 */       this.metadata.setWidth(this.width);
/* 216 */       this.metadata.setHeight(this.height);
/* 217 */       this.metadata.setMaxBitDepth(this.maxValue);
/*     */       
/* 219 */       this.gotHeader = true;
/*     */ 
/*     */       
/* 222 */       this.imageDataOffset = this.iis.getStreamPosition();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator getImageTypes(int imageIndex) throws IOException {
/* 228 */     checkIndex(imageIndex);
/*     */     
/* 230 */     readHeader();
/* 231 */     int tmp = (this.variant - 49) % 3;
/*     */     
/* 233 */     ArrayList<ImageTypeSpecifier> list = new ArrayList(1);
/* 234 */     int dataType = 3;
/*     */     
/* 236 */     if (this.maxValue < 256) {
/* 237 */       dataType = 0;
/* 238 */     } else if (this.maxValue < 65536) {
/* 239 */       dataType = 1;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     SampleModel sampleModel = null;
/* 244 */     ColorModel colorModel = null;
/* 245 */     if (this.variant == 49 || this.variant == 52) {
/*     */       
/* 247 */       sampleModel = new MultiPixelPackedSampleModel(0, this.width, this.height, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 252 */       byte[] color = { -1, 0 };
/* 253 */       colorModel = new IndexColorModel(1, 2, color, color, color);
/*     */     } else {
/* 255 */       (new int[1])[0] = 0; (new int[3])[0] = 0; (new int[3])[1] = 1; (new int[3])[2] = 2; sampleModel = new PixelInterleavedSampleModel(dataType, this.width, this.height, (tmp == 1) ? 1 : 3, this.width * ((tmp == 1) ? 1 : 3), (tmp == 1) ? new int[1] : new int[3]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 263 */       colorModel = ImageUtil.createColorModel(null, sampleModel);
/*     */     } 
/*     */     
/* 266 */     list.add(new ImageTypeSpecifier(colorModel, sampleModel));
/*     */     
/* 268 */     return list.iterator();
/*     */   }
/*     */   
/*     */   public ImageReadParam getDefaultReadParam() {
/* 272 */     return new ImageReadParam();
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
/* 277 */     checkIndex(imageIndex);
/* 278 */     readHeader();
/* 279 */     return this.metadata;
/*     */   }
/*     */   
/*     */   public IIOMetadata getStreamMetadata() throws IOException {
/* 283 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isRandomAccessEasy(int imageIndex) throws IOException {
/* 287 */     checkIndex(imageIndex);
/* 288 */     return true; } public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException { DataBuffer dataBuffer; int skipX; byte[] buf; int skipY, originalLS, k, dsx; byte[] data; int m; DataBufferByte bbuf; int destLS, i1; byte[] byteArray; int offset, i; DataBufferUShort sbuf; int i2, n;
/*     */     short[] shortArray;
/*     */     int i3;
/*     */     DataBufferInt ibuf;
/*     */     int j, i4, intArray[], i6, i5, i7;
/* 293 */     checkIndex(imageIndex);
/* 294 */     clearAbortRequest();
/* 295 */     processImageStarted(imageIndex);
/*     */     
/* 297 */     if (param == null) {
/* 298 */       param = getDefaultReadParam();
/*     */     }
/*     */     
/* 301 */     readHeader();
/*     */     
/* 303 */     Rectangle sourceRegion = new Rectangle(0, 0, 0, 0);
/* 304 */     Rectangle destinationRegion = new Rectangle(0, 0, 0, 0);
/*     */     
/* 306 */     computeRegions(param, this.width, this.height, param
/* 307 */         .getDestination(), sourceRegion, destinationRegion);
/*     */ 
/*     */ 
/*     */     
/* 311 */     int scaleX = param.getSourceXSubsampling();
/* 312 */     int scaleY = param.getSourceYSubsampling();
/*     */ 
/*     */     
/* 315 */     int[] sourceBands = param.getSourceBands();
/* 316 */     int[] destBands = param.getDestinationBands();
/*     */     
/* 318 */     boolean seleBand = (sourceBands != null && destBands != null);
/*     */     
/* 320 */     boolean noTransform = (destinationRegion.equals(new Rectangle(0, 0, this.width, this.height)) || seleBand);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     if (isRaw(this.variant) && this.maxValue >= 256) {
/* 327 */       this.maxValue = 255;
/*     */     }
/*     */     
/* 330 */     int numBands = 1;
/*     */ 
/*     */     
/* 333 */     if (this.variant == 51 || this.variant == 54) {
/* 334 */       numBands = 3;
/*     */     }
/*     */     
/* 337 */     if (!seleBand) {
/* 338 */       sourceBands = new int[numBands];
/* 339 */       destBands = new int[numBands];
/* 340 */       for (int i8 = 0; i8 < numBands; i8++) {
/* 341 */         sourceBands[i8] = i8; destBands[i8] = i8;
/*     */       } 
/*     */     } 
/* 344 */     int dataType = 3;
/*     */     
/* 346 */     if (this.maxValue < 256) {
/* 347 */       dataType = 0;
/* 348 */     } else if (this.maxValue < 65536) {
/* 349 */       dataType = 1;
/*     */     } 
/*     */ 
/*     */     
/* 353 */     SampleModel sampleModel = null;
/* 354 */     ColorModel colorModel = null;
/* 355 */     if (this.variant == 49 || this.variant == 52) {
/*     */       
/* 357 */       sampleModel = new MultiPixelPackedSampleModel(0, destinationRegion.width, destinationRegion.height, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 362 */       byte[] color = { -1, 0 };
/* 363 */       colorModel = new IndexColorModel(1, 2, color, color, color);
/*     */     } else {
/* 365 */       sampleModel = new PixelInterleavedSampleModel(dataType, destinationRegion.width, destinationRegion.height, sourceBands.length, destinationRegion.width * sourceBands.length, destBands);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 374 */       colorModel = ImageUtil.createColorModel(null, sampleModel);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 379 */     BufferedImage bi = param.getDestination();
/*     */ 
/*     */     
/* 382 */     WritableRaster raster = null;
/*     */     
/* 384 */     if (bi == null) {
/* 385 */       sampleModel = sampleModel.createCompatibleSampleModel(destinationRegion.x + destinationRegion.width, destinationRegion.y + destinationRegion.height);
/*     */ 
/*     */       
/* 388 */       if (seleBand) {
/* 389 */         sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
/*     */       }
/* 391 */       raster = Raster.createWritableRaster(sampleModel, new Point());
/* 392 */       bi = new BufferedImage(colorModel, raster, false, null);
/*     */     } else {
/* 394 */       raster = bi.getWritableTile(0, 0);
/* 395 */       sampleModel = bi.getSampleModel();
/* 396 */       colorModel = bi.getColorModel();
/* 397 */       noTransform &= destinationRegion.equals(raster.getBounds());
/*     */     } 
/*     */     
/* 400 */     switch (this.variant) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 52:
/* 405 */         dataBuffer = raster.getDataBuffer();
/*     */ 
/*     */         
/* 408 */         buf = ((DataBufferByte)dataBuffer).getData();
/* 409 */         if (noTransform) {
/* 410 */           this.iis.readFully(buf, 0, buf.length);
/* 411 */           processImageUpdate(bi, 0, 0, this.width, this.height, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */           
/* 415 */           processImageProgress(100.0F); break;
/* 416 */         }  if (scaleX == 1 && sourceRegion.x % 8 == 0) {
/* 417 */           int skip = sourceRegion.x >> 3;
/* 418 */           int i8 = this.width + 7 >> 3;
/* 419 */           int i9 = raster.getWidth() + 7 >> 3;
/*     */           
/* 421 */           int readLength = sourceRegion.width + 7 >> 3;
/* 422 */           int i10 = sourceRegion.y * i8;
/* 423 */           this.iis.skipBytes(i10 + skip);
/* 424 */           i10 = i8 * (scaleY - 1) + i8 - readLength;
/* 425 */           byte[] lineData = new byte[readLength];
/*     */           
/* 427 */           int bitoff = destinationRegion.x & 0x7;
/* 428 */           boolean reformat = (bitoff != 0);
/*     */           
/* 430 */           int i11 = 0, i12 = 0;
/* 431 */           int i13 = destinationRegion.y * i9 + (destinationRegion.x >> 3);
/* 432 */           for (; i11 < destinationRegion.height; i11++, i12 += scaleY) {
/* 433 */             if (reformat) {
/* 434 */               this.iis.read(lineData, 0, readLength);
/* 435 */               int mask1 = 255 << bitoff & 0xFF;
/* 436 */               int mask2 = (mask1 ^ 0xFFFFFFFF) & 0xFF;
/* 437 */               int shift = 8 - bitoff;
/*     */               
/* 439 */               int i14 = 0;
/* 440 */               int i15 = i13;
/* 441 */               for (; i14 < readLength - 1; i14++, i15++) {
/* 442 */                 buf[i15] = (byte)((lineData[i14] & mask2) << shift | (lineData[i14 + 1] & mask1) >> bitoff);
/*     */               }
/* 444 */               buf[i15] = (byte)((lineData[i14] & mask2) << shift);
/*     */             } else {
/* 446 */               this.iis.read(buf, i13, readLength);
/*     */             } 
/*     */             
/* 449 */             this.iis.skipBytes(i10);
/* 450 */             i13 += i9;
/*     */             
/* 452 */             processImageUpdate(bi, 0, i11, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */             
/* 456 */             processImageProgress(100.0F * i11 / destinationRegion.height);
/*     */           }  break;
/*     */         } 
/* 459 */         originalLS = this.width + 7 >> 3;
/* 460 */         data = new byte[originalLS];
/* 461 */         this.iis.skipBytes(sourceRegion.y * originalLS);
/* 462 */         destLS = bi.getWidth() + 7 >> 3;
/* 463 */         offset = originalLS * (scaleY - 1);
/* 464 */         i2 = destLS * destinationRegion.y + (destinationRegion.x >> 3);
/*     */         
/* 466 */         i3 = 0; j = 0; i6 = i2;
/* 467 */         for (; i3 < destinationRegion.height; i3++, j += scaleY) {
/* 468 */           this.iis.read(data, 0, originalLS);
/* 469 */           this.iis.skipBytes(offset);
/*     */           
/* 471 */           int b = 0;
/* 472 */           int pos = 7 - (destinationRegion.x & 0x7);
/* 473 */           int i8 = sourceRegion.x;
/* 474 */           for (; i8 < sourceRegion.x + sourceRegion.width; 
/* 475 */             i8 += scaleX) {
/* 476 */             b |= (data[i8 >> 3] >> 7 - (i8 & 0x7) & 0x1) << pos;
/* 477 */             pos--;
/* 478 */             if (pos == -1) {
/* 479 */               buf[i6++] = (byte)b;
/* 480 */               b = 0;
/* 481 */               pos = 7;
/*     */             } 
/*     */           } 
/*     */           
/* 485 */           if (pos != 7) {
/* 486 */             buf[i6++] = (byte)b;
/*     */           }
/* 488 */           i6 += destinationRegion.x >> 3;
/* 489 */           processImageUpdate(bi, 0, i3, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */           
/* 493 */           processImageProgress(100.0F * i3 / destinationRegion.height);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 49:
/* 500 */         dataBuffer = raster.getDataBuffer();
/* 501 */         buf = ((DataBufferByte)dataBuffer).getData();
/* 502 */         if (noTransform) {
/* 503 */           for (int i8 = 0, i9 = 0; i8 < this.height; i8++) {
/* 504 */             int b = 0;
/* 505 */             int pos = 7;
/* 506 */             for (int i10 = 0; i10 < this.width; i10++) {
/* 507 */               b |= (readInteger(this.iis) & 0x1) << pos;
/* 508 */               pos--;
/* 509 */               if (pos == -1) {
/* 510 */                 buf[i9++] = (byte)b;
/* 511 */                 b = 0;
/* 512 */                 pos = 7;
/*     */               } 
/*     */             } 
/* 515 */             if (pos != 7)
/* 516 */               buf[i9++] = (byte)b; 
/* 517 */             processImageUpdate(bi, 0, i8, this.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */             
/* 521 */             processImageProgress(100.0F * i8 / this.height);
/*     */           }  break;
/*     */         } 
/* 524 */         skipInteger(this.iis, sourceRegion.y * this.width + sourceRegion.x);
/* 525 */         k = scaleX - 1;
/* 526 */         m = (scaleY - 1) * this.width + this.width - destinationRegion.width * scaleX;
/*     */         
/* 528 */         i1 = (bi.getWidth() + 7 >> 3) * destinationRegion.y + (destinationRegion.x >> 3);
/*     */         
/* 530 */         for (i = 0, n = i1; i < destinationRegion.height; i++) {
/* 531 */           int b = 0;
/* 532 */           int pos = 7 - (destinationRegion.x & 0x7);
/* 533 */           for (int i8 = 0; i8 < destinationRegion.width; i8++) {
/* 534 */             b |= (readInteger(this.iis) & 0x1) << pos;
/* 535 */             pos--;
/* 536 */             if (pos == -1) {
/* 537 */               buf[n++] = (byte)b;
/* 538 */               b = 0;
/* 539 */               pos = 7;
/*     */             } 
/* 541 */             skipInteger(this.iis, k);
/*     */           } 
/* 543 */           if (pos != 7) {
/* 544 */             buf[n++] = (byte)b;
/*     */           }
/* 546 */           n += destinationRegion.x >> 3;
/* 547 */           skipInteger(this.iis, m);
/* 548 */           processImageUpdate(bi, 0, i, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */           
/* 552 */           processImageProgress(100.0F * i / destinationRegion.height);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 50:
/*     */       case 51:
/*     */       case 53:
/*     */       case 54:
/* 562 */         skipX = (scaleX - 1) * numBands;
/* 563 */         skipY = (scaleY * this.width - destinationRegion.width * scaleX) * numBands;
/*     */         
/* 565 */         dsx = (bi.getWidth() * destinationRegion.y + destinationRegion.x) * numBands;
/*     */         
/* 567 */         switch (dataType) {
/*     */           
/*     */           case 0:
/* 570 */             bbuf = (DataBufferByte)raster.getDataBuffer();
/* 571 */             byteArray = bbuf.getData();
/* 572 */             if (isRaw(this.variant)) {
/* 573 */               if (noTransform) {
/* 574 */                 this.iis.readFully(byteArray);
/* 575 */                 processImageUpdate(bi, 0, 0, this.width, this.height, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */                 
/* 579 */                 processImageProgress(100.0F); break;
/*     */               } 
/* 581 */               this.iis.skipBytes(sourceRegion.y * this.width * numBands);
/* 582 */               int skip = (scaleY - 1) * this.width * numBands;
/* 583 */               byte[] arrayOfByte = new byte[this.width * numBands];
/* 584 */               int pixelStride = scaleX * numBands;
/* 585 */               int sx = sourceRegion.x * numBands;
/* 586 */               int ex = this.width;
/* 587 */               for (int i8 = 0, i9 = dsx; i8 < destinationRegion.height; i8++) {
/* 588 */                 this.iis.read(arrayOfByte);
/* 589 */                 int i10 = sourceRegion.x, i11 = sx;
/* 590 */                 for (; i10 < sourceRegion.x + sourceRegion.width; 
/* 591 */                   i10 += scaleX, i11 += pixelStride) {
/* 592 */                   for (int i12 = 0; i12 < sourceBands.length; i12++)
/* 593 */                     byteArray[i9 + destBands[i12]] = arrayOfByte[i11 + sourceBands[i12]]; 
/* 594 */                   i9 += sourceBands.length;
/*     */                 } 
/* 596 */                 i9 += destinationRegion.x * numBands;
/* 597 */                 this.iis.skipBytes(skip);
/* 598 */                 processImageUpdate(bi, 0, i8, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */                 
/* 602 */                 processImageProgress(100.0F * i8 / destinationRegion.height);
/*     */               } 
/*     */               break;
/*     */             } 
/* 606 */             skipInteger(this.iis, (sourceRegion.y * this.width + sourceRegion.x) * numBands);
/*     */ 
/*     */ 
/*     */             
/* 610 */             if (seleBand) {
/* 611 */               byte[] arrayOfByte = new byte[numBands];
/* 612 */               for (int i8 = 0, i9 = dsx; i8 < destinationRegion.height; i8++) {
/* 613 */                 for (j = 0; j < destinationRegion.width; j++) {
/* 614 */                   int i10; for (i10 = 0; i10 < numBands; i10++)
/* 615 */                     arrayOfByte[i10] = (byte)readInteger(this.iis); 
/* 616 */                   for (i10 = 0; i10 < sourceBands.length; i10++)
/* 617 */                     byteArray[i9 + destBands[i10]] = arrayOfByte[sourceBands[i10]]; 
/* 618 */                   i9 += sourceBands.length;
/* 619 */                   skipInteger(this.iis, skipX);
/*     */                 } 
/* 621 */                 i9 += destinationRegion.x * sourceBands.length;
/* 622 */                 skipInteger(this.iis, skipY);
/* 623 */                 processImageUpdate(bi, 0, i8, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */                 
/* 627 */                 processImageProgress(100.0F * i8 / destinationRegion.height);
/*     */               }  break;
/*     */             } 
/* 630 */             for (i = 0, n = dsx; i < destinationRegion.height; i++) {
/* 631 */               for (int i8 = 0; i8 < destinationRegion.width; i8++) {
/* 632 */                 for (int i9 = 0; i9 < numBands; i9++)
/* 633 */                   byteArray[n++] = (byte)readInteger(this.iis); 
/* 634 */                 skipInteger(this.iis, skipX);
/*     */               } 
/* 636 */               n += destinationRegion.x * sourceBands.length;
/* 637 */               skipInteger(this.iis, skipY);
/* 638 */               processImageUpdate(bi, 0, i, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */               
/* 642 */               processImageProgress(100.0F * i / destinationRegion.height);
/*     */             } 
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 1:
/* 649 */             sbuf = (DataBufferUShort)raster.getDataBuffer();
/* 650 */             shortArray = sbuf.getData();
/* 651 */             skipInteger(this.iis, sourceRegion.y * this.width * numBands + sourceRegion.x);
/*     */             
/* 653 */             if (seleBand) {
/* 654 */               short[] arrayOfShort = new short[numBands];
/* 655 */               for (int i8 = 0; i8 < destinationRegion.height; i8++) {
/* 656 */                 for (int i9 = 0; i9 < destinationRegion.width; i9++) {
/* 657 */                   int i10; for (i10 = 0; i10 < numBands; i10++)
/* 658 */                     arrayOfShort[i10] = (short)readInteger(this.iis); 
/* 659 */                   for (i10 = 0; i10 < sourceBands.length; i10++)
/* 660 */                     shortArray[i6 + destBands[i10]] = arrayOfShort[sourceBands[i10]]; 
/* 661 */                   i6 += sourceBands.length;
/* 662 */                   skipInteger(this.iis, skipX);
/*     */                 } 
/* 664 */                 i6 += destinationRegion.x * sourceBands.length;
/* 665 */                 skipInteger(this.iis, skipY);
/* 666 */                 processImageUpdate(bi, 0, i8, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */                 
/* 670 */                 processImageProgress(100.0F * i8 / destinationRegion.height);
/*     */               }  break;
/*     */             } 
/* 673 */             for (i3 = 0, i4 = dsx; i3 < destinationRegion.height; i3++) {
/* 674 */               for (int i8 = 0; i8 < destinationRegion.width; i8++) {
/* 675 */                 for (int i9 = 0; i9 < numBands; i9++)
/* 676 */                   shortArray[i4++] = (short)readInteger(this.iis); 
/* 677 */                 skipInteger(this.iis, skipX);
/*     */               } 
/* 679 */               i4 += destinationRegion.x * sourceBands.length;
/* 680 */               skipInteger(this.iis, skipY);
/* 681 */               processImageUpdate(bi, 0, i3, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */               
/* 685 */               processImageProgress(100.0F * i3 / destinationRegion.height);
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/* 691 */             ibuf = (DataBufferInt)raster.getDataBuffer();
/* 692 */             intArray = ibuf.getData();
/* 693 */             skipInteger(this.iis, sourceRegion.y * this.width * numBands + sourceRegion.x);
/* 694 */             if (seleBand) {
/* 695 */               int[] arrayOfInt = new int[numBands];
/* 696 */               for (int i8 = 0, i9 = dsx; i8 < destinationRegion.height; i8++) {
/* 697 */                 for (int i10 = 0; i10 < destinationRegion.width; i10++) {
/* 698 */                   int i11; for (i11 = 0; i11 < numBands; i11++)
/* 699 */                     arrayOfInt[i11] = readInteger(this.iis); 
/* 700 */                   for (i11 = 0; i11 < sourceBands.length; i11++)
/* 701 */                     intArray[i9 + destBands[i11]] = arrayOfInt[sourceBands[i11]]; 
/* 702 */                   i9 += sourceBands.length;
/* 703 */                   skipInteger(this.iis, skipX);
/*     */                 } 
/* 705 */                 i9 += destinationRegion.x * sourceBands.length;
/* 706 */                 skipInteger(this.iis, skipY);
/* 707 */                 processImageUpdate(bi, 0, i8, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */                 
/* 711 */                 processImageProgress(100.0F * i8 / destinationRegion.height);
/*     */               }  break;
/*     */             } 
/* 714 */             for (i5 = 0, i7 = dsx; i5 < destinationRegion.height; i5++) {
/* 715 */               for (int i8 = 0; i8 < destinationRegion.width; i8++) {
/* 716 */                 for (int i9 = 0; i9 < numBands; i9++)
/* 717 */                   intArray[i7++] = readInteger(this.iis); 
/* 718 */                 skipInteger(this.iis, skipX);
/*     */               } 
/* 720 */               i7 += destinationRegion.x * sourceBands.length;
/* 721 */               skipInteger(this.iis, skipY);
/* 722 */               processImageUpdate(bi, 0, i5, destinationRegion.width, 1, 1, 1, destBands);
/*     */ 
/*     */ 
/*     */               
/* 726 */               processImageProgress(100.0F * i5 / destinationRegion.height);
/*     */             } 
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */     } 
/* 733 */     if (abortRequested()) {
/* 734 */       processReadAborted();
/*     */     } else {
/* 736 */       processImageComplete();
/* 737 */     }  return bi; }
/*     */ 
/*     */   
/*     */   public boolean canReadRaster() {
/* 741 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
/* 746 */     BufferedImage bi = read(imageIndex, param);
/* 747 */     return bi.getData();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 751 */     super.reset();
/* 752 */     this.iis = null;
/* 753 */     this.gotHeader = false;
/* 754 */     System.gc();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isRaw(int v) {
/* 759 */     return (v >= 52);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readComments(ImageInputStream stream, PNMMetadata metadata) throws IOException {
/* 765 */     String line = null;
/* 766 */     int pos = -1;
/* 767 */     stream.mark();
/* 768 */     while ((line = stream.readLine()) != null && (
/* 769 */       pos = line.indexOf("#")) >= 0) {
/* 770 */       metadata.addComment(line.substring(pos + 1).trim());
/*     */     }
/* 772 */     stream.reset();
/*     */   }
/*     */ 
/*     */   
/*     */   private int readInteger(ImageInputStream stream) throws IOException {
/* 777 */     boolean foundDigit = false;
/*     */     
/* 779 */     while (this.aLine == null) {
/* 780 */       this.aLine = stream.readLine();
/* 781 */       if (this.aLine == null)
/* 782 */         return 0; 
/* 783 */       int pos = this.aLine.indexOf("#");
/* 784 */       if (pos == 0) {
/* 785 */         this.aLine = null;
/* 786 */       } else if (pos > 0) {
/* 787 */         this.aLine = this.aLine.substring(0, pos - 1);
/*     */       } 
/* 789 */       if (this.aLine != null) {
/* 790 */         this.token = new StringTokenizer(this.aLine);
/*     */       }
/*     */     } 
/* 793 */     while (this.token.hasMoreTokens()) {
/* 794 */       String s = this.token.nextToken();
/*     */       
/*     */       try {
/* 797 */         return (new Integer(s)).intValue();
/* 798 */       } catch (NumberFormatException e) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 803 */     if (!foundDigit) {
/* 804 */       this.aLine = null;
/* 805 */       return readInteger(stream);
/*     */     } 
/*     */     
/* 808 */     return 0;
/*     */   }
/*     */   
/*     */   private void skipInteger(ImageInputStream stream, int num) throws IOException {
/* 812 */     for (int i = 0; i < num; i++)
/* 813 */       readInteger(stream); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pnm/PNMImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */