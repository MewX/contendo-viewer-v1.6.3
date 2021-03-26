/*     */ package com.github.jaiimageio.impl.plugins.pnm;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import com.github.jaiimageio.plugins.pnm.PNMImageWriteParam;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.IOException;
/*     */ import java.security.AccessController;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageWriterSpi;
/*     */ import javax.imageio.stream.ImageOutputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNMImageWriter
/*     */   extends ImageWriter
/*     */ {
/*     */   private static final int PBM_ASCII = 49;
/*     */   private static final int PGM_ASCII = 50;
/*     */   private static final int PPM_ASCII = 51;
/*     */   private static final int PBM_RAW = 52;
/*     */   private static final int PGM_RAW = 53;
/*     */   private static final int PPM_RAW = 54;
/*     */   private static final int SPACE = 32;
/*     */   private static final String COMMENT = "# written by com.github.jaiimageio.impl.PNMImageWriter";
/*     */   private static byte[] lineSeparator;
/*     */   private int variant;
/*     */   private int maxValue;
/*     */   
/*     */   static {
/* 108 */     if (lineSeparator == null) {
/* 109 */       String ls = AccessController.<String>doPrivileged(new GetPropertyAction("line.separator"));
/*     */       
/* 111 */       lineSeparator = ls.getBytes();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 116 */   private ImageOutputStream stream = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PNMImageWriter(ImageWriterSpi originator) {
/* 122 */     super(originator);
/*     */   }
/*     */   
/*     */   public void setOutput(Object output) {
/* 126 */     super.setOutput(output);
/* 127 */     if (output != null) {
/* 128 */       if (!(output instanceof ImageOutputStream))
/* 129 */         throw new IllegalArgumentException(I18N.getString("PNMImageWriter0")); 
/* 130 */       this.stream = (ImageOutputStream)output;
/*     */     } else {
/* 132 */       this.stream = null;
/*     */     } 
/*     */   }
/*     */   public ImageWriteParam getDefaultWriteParam() {
/* 136 */     return (ImageWriteParam)new PNMImageWriteParam();
/*     */   }
/*     */   
/*     */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 145 */     return new PNMMetadata(imageType, param);
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 157 */     if (inData == null) {
/* 158 */       throw new IllegalArgumentException("inData == null!");
/*     */     }
/* 160 */     if (imageType == null) {
/* 161 */       throw new IllegalArgumentException("imageType == null!");
/*     */     }
/*     */     
/* 164 */     PNMMetadata outData = null;
/*     */ 
/*     */     
/* 167 */     if (inData instanceof PNMMetadata) {
/*     */       
/* 169 */       outData = (PNMMetadata)((PNMMetadata)inData).clone();
/*     */     } else {
/*     */       try {
/* 172 */         outData = new PNMMetadata(inData);
/* 173 */       } catch (IIOInvalidTreeException e) {
/*     */         
/* 175 */         outData = new PNMMetadata();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 180 */     outData.initialize(imageType, param);
/*     */     
/* 182 */     return outData;
/*     */   }
/*     */   
/*     */   public boolean canWriteRasters() {
/* 186 */     return true;
/*     */   }
/*     */   public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
/*     */     ImageTypeSpecifier imageType;
/*     */     PNMMetadata metadata;
/*     */     boolean isRawPNM;
/* 192 */     clearAbortRequest();
/* 193 */     processImageStarted(0);
/* 194 */     if (param == null) {
/* 195 */       param = getDefaultWriteParam();
/*     */     }
/* 197 */     RenderedImage input = null;
/* 198 */     Raster inputRaster = null;
/* 199 */     boolean writeRaster = image.hasRaster();
/* 200 */     Rectangle sourceRegion = param.getSourceRegion();
/* 201 */     SampleModel sampleModel = null;
/* 202 */     ColorModel colorModel = null;
/*     */     
/* 204 */     if (writeRaster)
/* 205 */     { inputRaster = image.getRaster();
/* 206 */       sampleModel = inputRaster.getSampleModel();
/* 207 */       if (sourceRegion == null) {
/* 208 */         sourceRegion = inputRaster.getBounds();
/*     */       } else {
/* 210 */         sourceRegion = sourceRegion.intersection(inputRaster.getBounds());
/*     */       }  }
/* 212 */     else { input = image.getRenderedImage();
/* 213 */       sampleModel = input.getSampleModel();
/* 214 */       colorModel = input.getColorModel();
/*     */       
/* 216 */       Rectangle rect = new Rectangle(input.getMinX(), input.getMinY(), input.getWidth(), input.getHeight());
/* 217 */       if (sourceRegion == null) {
/* 218 */         sourceRegion = rect;
/*     */       } else {
/* 220 */         sourceRegion = sourceRegion.intersection(rect);
/*     */       }  }
/*     */     
/* 223 */     if (sourceRegion.isEmpty()) {
/* 224 */       throw new RuntimeException(I18N.getString("PNMImageWrite1"));
/*     */     }
/* 226 */     ImageUtil.canEncodeImage(this, colorModel, sampleModel);
/*     */     
/* 228 */     int scaleX = param.getSourceXSubsampling();
/* 229 */     int scaleY = param.getSourceYSubsampling();
/* 230 */     int xOffset = param.getSubsamplingXOffset();
/* 231 */     int yOffset = param.getSubsamplingYOffset();
/*     */     
/* 233 */     sourceRegion.translate(xOffset, yOffset);
/* 234 */     sourceRegion.width -= xOffset;
/* 235 */     sourceRegion.height -= yOffset;
/*     */     
/* 237 */     int minX = sourceRegion.x / scaleX;
/* 238 */     int minY = sourceRegion.y / scaleY;
/* 239 */     int w = (sourceRegion.width + scaleX - 1) / scaleX;
/* 240 */     int h = (sourceRegion.height + scaleY - 1) / scaleY;
/*     */     
/* 242 */     Rectangle destinationRegion = new Rectangle(minX, minY, w, h);
/*     */     
/* 244 */     int tileHeight = sampleModel.getHeight();
/* 245 */     int tileWidth = sampleModel.getWidth();
/*     */ 
/*     */     
/* 248 */     int[] sampleSize = sampleModel.getSampleSize();
/* 249 */     int[] sourceBands = param.getSourceBands();
/* 250 */     boolean noSubband = true;
/* 251 */     int numBands = sampleModel.getNumBands();
/*     */     
/* 253 */     if (sourceBands != null) {
/* 254 */       sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
/* 255 */       colorModel = null;
/* 256 */       noSubband = false;
/* 257 */       numBands = sampleModel.getNumBands();
/*     */     } else {
/* 259 */       sourceBands = new int[numBands];
/* 260 */       for (int j = 0; j < numBands; j++) {
/* 261 */         sourceBands[j] = j;
/*     */       }
/*     */     } 
/*     */     
/* 265 */     byte[] reds = null;
/* 266 */     byte[] greens = null;
/* 267 */     byte[] blues = null;
/*     */ 
/*     */     
/* 270 */     boolean isPBMInverted = false;
/*     */     
/* 272 */     if (numBands == 1) {
/* 273 */       if (colorModel instanceof IndexColorModel) {
/* 274 */         IndexColorModel icm = (IndexColorModel)colorModel;
/*     */         
/* 276 */         int mapSize = icm.getMapSize();
/* 277 */         if (mapSize < 1 << sampleSize[0]) {
/* 278 */           throw new RuntimeException(I18N.getString("PNMImageWrite2"));
/*     */         }
/* 280 */         if (sampleSize[0] == 1) {
/* 281 */           this.variant = 52;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 286 */           isPBMInverted = (icm.getRed(1) > icm.getRed(0));
/*     */         } else {
/* 288 */           this.variant = 54;
/*     */           
/* 290 */           reds = new byte[mapSize];
/* 291 */           greens = new byte[mapSize];
/* 292 */           blues = new byte[mapSize];
/*     */           
/* 294 */           icm.getReds(reds);
/* 295 */           icm.getGreens(greens);
/* 296 */           icm.getBlues(blues);
/*     */         } 
/* 298 */       } else if (sampleSize[0] == 1) {
/* 299 */         this.variant = 52;
/* 300 */       } else if (sampleSize[0] <= 8) {
/* 301 */         this.variant = 53;
/*     */       } else {
/* 303 */         this.variant = 50;
/*     */       } 
/* 305 */     } else if (numBands == 3) {
/* 306 */       if (sampleSize[0] <= 8 && sampleSize[1] <= 8 && sampleSize[2] <= 8) {
/*     */         
/* 308 */         this.variant = 54;
/*     */       } else {
/* 310 */         this.variant = 51;
/*     */       } 
/*     */     } else {
/* 313 */       throw new RuntimeException(I18N.getString("PNMImageWrite3"));
/*     */     } 
/*     */     
/* 316 */     IIOMetadata inputMetadata = image.getMetadata();
/*     */     
/* 318 */     if (colorModel != null) {
/* 319 */       imageType = new ImageTypeSpecifier(colorModel, sampleModel);
/*     */     } else {
/* 321 */       ColorSpace cs; int dataType = sampleModel.getDataType();
/* 322 */       switch (numBands) {
/*     */         
/*     */         case 1:
/* 325 */           imageType = ImageTypeSpecifier.createGrayscale(sampleSize[0], dataType, false);
/*     */           break;
/*     */         
/*     */         case 3:
/* 329 */           cs = ColorSpace.getInstance(1000);
/*     */           
/* 331 */           imageType = ImageTypeSpecifier.createInterleaved(cs, new int[] { 0, 1, 2 }, dataType, false, false);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 337 */           throw new IIOException("Cannot encode image with " + numBands + " bands!");
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 343 */     if (inputMetadata != null) {
/*     */       
/* 345 */       metadata = (PNMMetadata)convertImageMetadata(inputMetadata, imageType, param);
/*     */     }
/*     */     else {
/*     */       
/* 349 */       metadata = (PNMMetadata)getDefaultImageMetadata(imageType, param);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 354 */     if (param instanceof PNMImageWriteParam) {
/* 355 */       isRawPNM = ((PNMImageWriteParam)param).getRaw();
/*     */     } else {
/* 357 */       isRawPNM = metadata.isRaw();
/*     */     } 
/*     */     
/* 360 */     this.maxValue = metadata.getMaxValue();
/* 361 */     for (int i = 0; i < sampleSize.length; i++) {
/* 362 */       int v = (1 << sampleSize[i]) - 1;
/* 363 */       if (v > this.maxValue) {
/* 364 */         this.maxValue = v;
/*     */       }
/*     */     } 
/*     */     
/* 368 */     if (isRawPNM) {
/*     */       
/* 370 */       int maxBitDepth = metadata.getMaxBitDepth();
/* 371 */       if (!isRaw(this.variant) && maxBitDepth <= 8) {
/*     */ 
/*     */         
/* 374 */         this.variant += 3;
/* 375 */       } else if (isRaw(this.variant) && maxBitDepth > 8) {
/*     */ 
/*     */         
/* 378 */         this.variant -= 3;
/*     */       }
/*     */     
/*     */     }
/* 382 */     else if (isRaw(this.variant)) {
/*     */       
/* 384 */       this.variant -= 3;
/*     */     } 
/*     */ 
/*     */     
/* 388 */     this.stream.writeByte(80);
/* 389 */     this.stream.writeByte(this.variant);
/*     */     
/* 391 */     this.stream.write(lineSeparator);
/* 392 */     this.stream.write("# written by com.github.jaiimageio.impl.PNMImageWriter".getBytes());
/*     */ 
/*     */     
/* 395 */     Iterator<String> comments = metadata.getComments();
/* 396 */     if (comments != null) {
/* 397 */       while (comments.hasNext()) {
/* 398 */         this.stream.write(lineSeparator);
/* 399 */         String comment = "# " + (String)comments.next();
/* 400 */         this.stream.write(comment.getBytes());
/*     */       } 
/*     */     }
/*     */     
/* 404 */     this.stream.write(lineSeparator);
/* 405 */     writeInteger(this.stream, w);
/* 406 */     this.stream.write(32);
/* 407 */     writeInteger(this.stream, h);
/*     */ 
/*     */     
/* 410 */     if (this.variant != 52 && this.variant != 49) {
/* 411 */       this.stream.write(lineSeparator);
/* 412 */       writeInteger(this.stream, this.maxValue);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 417 */     if (this.variant == 52 || this.variant == 53 || this.variant == 54)
/*     */     {
/*     */       
/* 420 */       this.stream.write(10);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 425 */     boolean writeOptimal = false;
/* 426 */     if (this.variant == 52 && sampleModel
/* 427 */       .getTransferType() == 0 && sampleModel instanceof MultiPixelPackedSampleModel) {
/*     */ 
/*     */       
/* 430 */       MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)sampleModel;
/*     */ 
/*     */       
/* 433 */       int originX = 0;
/* 434 */       if (writeRaster) {
/* 435 */         originX = inputRaster.getMinX();
/*     */       } else {
/* 437 */         originX = input.getMinX();
/*     */       } 
/*     */       
/* 440 */       if (mppsm.getBitOffset((sourceRegion.x - originX) % tileWidth) == 0 && mppsm
/* 441 */         .getPixelBitStride() == 1 && scaleX == 1)
/* 442 */         writeOptimal = true; 
/* 443 */     } else if ((this.variant == 53 || this.variant == 54) && sampleModel instanceof ComponentSampleModel && !(colorModel instanceof IndexColorModel)) {
/*     */ 
/*     */ 
/*     */       
/* 447 */       ComponentSampleModel csm = (ComponentSampleModel)sampleModel;
/*     */ 
/*     */ 
/*     */       
/* 451 */       if (csm.getPixelStride() == numBands && scaleX == 1) {
/* 452 */         writeOptimal = true;
/*     */ 
/*     */         
/* 455 */         if (this.variant == 54) {
/* 456 */           int[] bandOffsets = csm.getBandOffsets();
/* 457 */           for (int b = 0; b < numBands; b++) {
/* 458 */             if (bandOffsets[b] != b) {
/* 459 */               writeOptimal = false;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 468 */     if (writeOptimal) {
/*     */       
/* 470 */       int bytesPerRow = (this.variant == 52) ? ((w + 7) / 8) : (w * sampleModel.getNumBands());
/* 471 */       byte[] bdata = null;
/* 472 */       byte[] invertedData = new byte[bytesPerRow];
/*     */ 
/*     */       
/* 475 */       for (int j = 0; j < sourceRegion.height && 
/* 476 */         !abortRequested(); j++) {
/*     */         
/* 478 */         Raster lineRaster = null;
/* 479 */         if (writeRaster) {
/* 480 */           lineRaster = inputRaster.createChild(sourceRegion.x, j, sourceRegion.width, 1, 0, 0, null);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 486 */           lineRaster = input.getData(new Rectangle(sourceRegion.x, sourceRegion.y + j, w, 1));
/*     */ 
/*     */           
/* 489 */           lineRaster = lineRaster.createTranslatedChild(0, 0);
/*     */         } 
/*     */         
/* 492 */         bdata = ((DataBufferByte)lineRaster.getDataBuffer()).getData();
/*     */         
/* 494 */         sampleModel = lineRaster.getSampleModel();
/* 495 */         int offset = 0;
/* 496 */         if (sampleModel instanceof ComponentSampleModel) {
/*     */           
/* 498 */           offset = ((ComponentSampleModel)sampleModel).getOffset(lineRaster.getMinX() - lineRaster.getSampleModelTranslateX(), lineRaster
/* 499 */               .getMinY() - lineRaster.getSampleModelTranslateY());
/* 500 */         } else if (sampleModel instanceof MultiPixelPackedSampleModel) {
/* 501 */           offset = ((MultiPixelPackedSampleModel)sampleModel).getOffset(lineRaster.getMinX() - lineRaster
/* 502 */               .getSampleModelTranslateX(), lineRaster
/* 503 */               .getMinX() - lineRaster.getSampleModelTranslateY());
/*     */         } 
/*     */         
/* 506 */         if (isPBMInverted) {
/* 507 */           for (int k = offset, m = 0; m < bytesPerRow; k++, m++)
/* 508 */             invertedData[m] = (byte)(bdata[k] ^ 0xFFFFFFFF); 
/* 509 */           bdata = invertedData;
/* 510 */           offset = 0;
/*     */         } 
/*     */         
/* 513 */         this.stream.write(bdata, offset, bytesPerRow);
/* 514 */         processImageProgress(100.0F * j / sourceRegion.height);
/*     */       } 
/*     */ 
/*     */       
/* 518 */       this.stream.flush();
/* 519 */       if (abortRequested()) {
/* 520 */         processWriteAborted();
/*     */       } else {
/* 522 */         processImageComplete();
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 527 */     int size = sourceRegion.width * numBands;
/*     */     
/* 529 */     int[] pixels = new int[size];
/*     */ 
/*     */ 
/*     */     
/* 533 */     byte[] bpixels = (reds == null) ? new byte[w * numBands] : new byte[w * 3];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 539 */     int count = 0;
/*     */ 
/*     */     
/* 542 */     int lastRow = sourceRegion.y + sourceRegion.height;
/*     */     int row;
/* 544 */     for (row = sourceRegion.y; row < lastRow && 
/* 545 */       !abortRequested(); row += scaleY) {
/*     */       int k, kdst, ksrc, b, pos, m, j;
/*     */       
/* 548 */       Raster src = null;
/*     */       
/* 550 */       if (writeRaster) {
/* 551 */         src = inputRaster.createChild(sourceRegion.x, row, sourceRegion.width, 1, sourceRegion.x, row, sourceBands);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 556 */         src = input.getData(new Rectangle(sourceRegion.x, row, sourceRegion.width, 1));
/*     */       } 
/* 558 */       src.getPixels(sourceRegion.x, row, sourceRegion.width, 1, pixels);
/*     */       
/* 560 */       if (isPBMInverted) {
/* 561 */         int n; for (n = 0; n < size; n += scaleX)
/* 562 */           bpixels[n] = (byte)(bpixels[n] ^ 0x1); 
/*     */       } 
/* 564 */       switch (this.variant) {
/*     */         case 49:
/*     */         case 50:
/* 567 */           for (k = 0; k < size; k += scaleX) {
/* 568 */             if (count++ % 16 == 0) {
/* 569 */               this.stream.write(lineSeparator);
/*     */             } else {
/* 571 */               this.stream.write(32);
/*     */             } 
/* 573 */             writeInteger(this.stream, pixels[k]);
/*     */           } 
/* 575 */           this.stream.write(lineSeparator);
/*     */           break;
/*     */         
/*     */         case 51:
/* 579 */           if (reds == null) {
/* 580 */             for (k = 0; k < size; k += scaleX * numBands) {
/* 581 */               for (int n = 0; n < numBands; n++) {
/* 582 */                 if (count++ % 16 == 0) {
/* 583 */                   this.stream.write(lineSeparator);
/*     */                 } else {
/* 585 */                   this.stream.write(32);
/*     */                 } 
/* 587 */                 writeInteger(this.stream, pixels[k + n]);
/*     */               } 
/*     */             } 
/*     */           } else {
/* 591 */             for (k = 0; k < size; k += scaleX) {
/* 592 */               if (count++ % 5 == 0) {
/* 593 */                 this.stream.write(lineSeparator);
/*     */               } else {
/* 595 */                 this.stream.write(32);
/*     */               } 
/* 597 */               writeInteger(this.stream, reds[pixels[k]] & 0xFF);
/* 598 */               this.stream.write(32);
/* 599 */               writeInteger(this.stream, greens[pixels[k]] & 0xFF);
/* 600 */               this.stream.write(32);
/* 601 */               writeInteger(this.stream, blues[pixels[k]] & 0xFF);
/*     */             } 
/*     */           } 
/* 604 */           this.stream.write(lineSeparator);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 52:
/* 609 */           kdst = 0;
/* 610 */           ksrc = 0;
/* 611 */           b = 0;
/* 612 */           pos = 7;
/* 613 */           for (m = 0; m < size; m += scaleX) {
/* 614 */             b |= pixels[m] << pos;
/* 615 */             pos--;
/* 616 */             if (pos == -1) {
/* 617 */               bpixels[kdst++] = (byte)b;
/* 618 */               b = 0;
/* 619 */               pos = 7;
/*     */             } 
/*     */           } 
/*     */           
/* 623 */           if (pos != 7) {
/* 624 */             bpixels[kdst++] = (byte)b;
/*     */           }
/* 626 */           this.stream.write(bpixels, 0, kdst);
/*     */           break;
/*     */         
/*     */         case 53:
/* 630 */           for (m = 0, j = 0; m < size; m += scaleX) {
/* 631 */             bpixels[j++] = (byte)pixels[m];
/*     */           }
/* 633 */           this.stream.write(bpixels, 0, w);
/*     */           break;
/*     */         
/*     */         case 54:
/* 637 */           if (reds == null) {
/* 638 */             int n; for (m = 0, n = 0; m < size; m += scaleX * numBands) {
/* 639 */               for (int i1 = 0; i1 < numBands; i1++)
/* 640 */                 bpixels[n++] = (byte)(pixels[m + i1] & 0xFF); 
/*     */             } 
/*     */           } else {
/* 643 */             for (m = 0, j = 0; m < size; m += scaleX) {
/* 644 */               bpixels[j++] = reds[pixels[m]];
/* 645 */               bpixels[j++] = greens[pixels[m]];
/* 646 */               bpixels[j++] = blues[pixels[m]];
/*     */             } 
/*     */           } 
/* 649 */           this.stream.write(bpixels, 0, bpixels.length);
/*     */           break;
/*     */       } 
/*     */       
/* 653 */       processImageProgress(100.0F * (row - sourceRegion.y) / sourceRegion.height);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 658 */     this.stream.flush();
/*     */     
/* 660 */     if (abortRequested()) {
/* 661 */       processWriteAborted();
/*     */     } else {
/* 663 */       processImageComplete();
/*     */     } 
/*     */   }
/*     */   public void reset() {
/* 667 */     super.reset();
/* 668 */     this.stream = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeInteger(ImageOutputStream output, int i) throws IOException {
/* 673 */     output.write(Integer.toString(i).getBytes());
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeByte(ImageOutputStream output, byte b) throws IOException {
/* 678 */     output.write(Byte.toString(b).getBytes());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isRaw(int v) {
/* 683 */     return (v >= 52);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pnm/PNMImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */