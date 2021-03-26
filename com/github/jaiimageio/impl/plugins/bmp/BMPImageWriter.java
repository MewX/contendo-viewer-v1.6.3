/*      */ package com.github.jaiimageio.impl.plugins.bmp;
/*      */ 
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import com.github.jaiimageio.plugins.bmp.BMPImageWriteParam;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.Iterator;
/*      */ import javax.imageio.IIOException;
/*      */ import javax.imageio.IIOImage;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.ImageWriteParam;
/*      */ import javax.imageio.ImageWriter;
/*      */ import javax.imageio.event.IIOWriteProgressListener;
/*      */ import javax.imageio.event.IIOWriteWarningListener;
/*      */ import javax.imageio.metadata.IIOInvalidTreeException;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.spi.ImageWriterSpi;
/*      */ import javax.imageio.stream.ImageOutputStream;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BMPImageWriter
/*      */   extends ImageWriter
/*      */   implements BMPConstants
/*      */ {
/*  103 */   private ImageOutputStream stream = null;
/*  104 */   private ByteArrayOutputStream embedded_stream = null; private int compressionType;
/*      */   private boolean isTopDown;
/*      */   private int w;
/*      */   private int h;
/*  108 */   private int compImageSize = 0;
/*      */   
/*      */   private int[] bitMasks;
/*      */   
/*      */   private int[] bitPos;
/*      */   
/*      */   private byte[] bpixels;
/*      */   private short[] spixels;
/*      */   private int[] ipixels;
/*      */   
/*      */   public BMPImageWriter(ImageWriterSpi originator) {
/*  119 */     super(originator);
/*      */   }
/*      */   
/*      */   public void setOutput(Object output) {
/*  123 */     super.setOutput(output);
/*  124 */     if (output != null) {
/*  125 */       if (!(output instanceof ImageOutputStream))
/*  126 */         throw new IllegalArgumentException(I18N.getString("BMPImageWriter0")); 
/*  127 */       this.stream = (ImageOutputStream)output;
/*  128 */       this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*      */     } else {
/*  130 */       this.stream = null;
/*      */     } 
/*      */   }
/*      */   public ImageWriteParam getDefaultWriteParam() {
/*  134 */     return (ImageWriteParam)new BMPImageWriteParam();
/*      */   }
/*      */   
/*      */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/*  138 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/*  143 */     BMPMetadata meta = new BMPMetadata();
/*  144 */     meta.initialize(imageType.getColorModel(), imageType
/*  145 */         .getSampleModel(), param);
/*      */     
/*  147 */     return meta;
/*      */   }
/*      */ 
/*      */   
/*      */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/*  152 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
/*  160 */     if (inData == null) {
/*  161 */       throw new IllegalArgumentException("inData == null!");
/*      */     }
/*  163 */     if (imageType == null) {
/*  164 */       throw new IllegalArgumentException("imageType == null!");
/*      */     }
/*      */     
/*  167 */     BMPMetadata outData = null;
/*      */ 
/*      */     
/*  170 */     if (inData instanceof BMPMetadata) {
/*      */       
/*  172 */       outData = (BMPMetadata)((BMPMetadata)inData).clone();
/*      */     } else {
/*      */       try {
/*  175 */         outData = new BMPMetadata(inData);
/*  176 */       } catch (IIOInvalidTreeException e) {
/*      */         
/*  178 */         outData = new BMPMetadata();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  183 */     outData.initialize(imageType.getColorModel(), imageType
/*  184 */         .getSampleModel(), param);
/*      */ 
/*      */     
/*  187 */     return outData;
/*      */   }
/*      */   
/*      */   public boolean canWriteRasters() {
/*  191 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
/*      */     int k;
/*  198 */     if (this.stream == null) {
/*  199 */       throw new IllegalStateException(I18N.getString("BMPImageWriter7"));
/*      */     }
/*      */     
/*  202 */     if (image == null) {
/*  203 */       throw new IllegalArgumentException(I18N.getString("BMPImageWriter8"));
/*      */     }
/*      */     
/*  206 */     clearAbortRequest();
/*  207 */     processImageStarted(0);
/*  208 */     if (param == null) {
/*  209 */       param = getDefaultWriteParam();
/*      */     }
/*  211 */     BMPImageWriteParam bmpParam = (BMPImageWriteParam)param;
/*      */ 
/*      */     
/*  214 */     int bitsPerPixel = 24;
/*  215 */     boolean isPalette = false;
/*  216 */     int paletteEntries = 0;
/*  217 */     IndexColorModel icm = null;
/*      */     
/*  219 */     RenderedImage input = null;
/*  220 */     Raster inputRaster = null;
/*  221 */     boolean writeRaster = image.hasRaster();
/*  222 */     Rectangle sourceRegion = param.getSourceRegion();
/*  223 */     SampleModel sampleModel = null;
/*  224 */     ColorModel colorModel = null;
/*      */     
/*  226 */     this.compImageSize = 0;
/*      */     
/*  228 */     if (writeRaster)
/*  229 */     { inputRaster = image.getRaster();
/*  230 */       sampleModel = inputRaster.getSampleModel();
/*  231 */       colorModel = ImageUtil.createColorModel(null, sampleModel);
/*  232 */       if (sourceRegion == null) {
/*  233 */         sourceRegion = inputRaster.getBounds();
/*      */       } else {
/*  235 */         sourceRegion = sourceRegion.intersection(inputRaster.getBounds());
/*      */       }  }
/*  237 */     else { input = image.getRenderedImage();
/*  238 */       sampleModel = input.getSampleModel();
/*  239 */       colorModel = input.getColorModel();
/*      */       
/*  241 */       Rectangle rect = new Rectangle(input.getMinX(), input.getMinY(), input.getWidth(), input.getHeight());
/*  242 */       if (sourceRegion == null) {
/*  243 */         sourceRegion = rect;
/*      */       } else {
/*  245 */         sourceRegion = sourceRegion.intersection(rect);
/*      */       }  }
/*      */     
/*  248 */     IIOMetadata imageMetadata = image.getMetadata();
/*  249 */     BMPMetadata bmpImageMetadata = null;
/*  250 */     ImageTypeSpecifier imageType = new ImageTypeSpecifier(colorModel, sampleModel);
/*      */     
/*  252 */     if (imageMetadata != null) {
/*      */ 
/*      */       
/*  255 */       bmpImageMetadata = (BMPMetadata)convertImageMetadata(imageMetadata, imageType, param);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  260 */       bmpImageMetadata = (BMPMetadata)getDefaultImageMetadata(imageType, param);
/*      */     } 
/*      */     
/*  263 */     if (sourceRegion.isEmpty()) {
/*  264 */       throw new RuntimeException(I18N.getString("BMPImageWrite0"));
/*      */     }
/*  266 */     int scaleX = param.getSourceXSubsampling();
/*  267 */     int scaleY = param.getSourceYSubsampling();
/*  268 */     int xOffset = param.getSubsamplingXOffset();
/*  269 */     int yOffset = param.getSubsamplingYOffset();
/*      */ 
/*      */     
/*  272 */     int dataType = sampleModel.getDataType();
/*      */     
/*  274 */     sourceRegion.translate(xOffset, yOffset);
/*  275 */     sourceRegion.width -= xOffset;
/*  276 */     sourceRegion.height -= yOffset;
/*      */     
/*  278 */     int minX = sourceRegion.x / scaleX;
/*  279 */     int minY = sourceRegion.y / scaleY;
/*  280 */     this.w = (sourceRegion.width + scaleX - 1) / scaleX;
/*  281 */     this.h = (sourceRegion.height + scaleY - 1) / scaleY;
/*  282 */     xOffset = sourceRegion.x % scaleX;
/*  283 */     yOffset = sourceRegion.y % scaleY;
/*      */     
/*  285 */     Rectangle destinationRegion = new Rectangle(minX, minY, this.w, this.h);
/*  286 */     int noTransform = destinationRegion.equals(sourceRegion);
/*      */ 
/*      */     
/*  289 */     int[] sourceBands = param.getSourceBands();
/*  290 */     boolean noSubband = true;
/*  291 */     int numBands = sampleModel.getNumBands();
/*      */     
/*  293 */     if (sourceBands != null) {
/*  294 */       sampleModel = sampleModel.createSubsetSampleModel(sourceBands);
/*  295 */       colorModel = null;
/*  296 */       noSubband = false;
/*  297 */       numBands = sampleModel.getNumBands();
/*      */     } else {
/*  299 */       sourceBands = new int[numBands];
/*  300 */       for (int n = 0; n < numBands; n++) {
/*  301 */         sourceBands[n] = n;
/*      */       }
/*      */     } 
/*  304 */     int[] bandOffsets = null;
/*  305 */     boolean bgrOrder = true;
/*      */     
/*  307 */     if (sampleModel instanceof ComponentSampleModel) {
/*  308 */       bandOffsets = ((ComponentSampleModel)sampleModel).getBandOffsets();
/*  309 */       if (sampleModel instanceof java.awt.image.BandedSampleModel)
/*      */       {
/*      */         
/*  312 */         bgrOrder = false;
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  317 */         for (int n = 0; n < bandOffsets.length; n++) {
/*  318 */           k = bgrOrder & ((bandOffsets[n] == bandOffsets.length - n - 1) ? 1 : 0);
/*      */         }
/*      */       }
/*      */     
/*  322 */     } else if (sampleModel instanceof SinglePixelPackedSampleModel) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  327 */       int[] bitOffsets = ((SinglePixelPackedSampleModel)sampleModel).getBitOffsets();
/*  328 */       for (int n = 0; n < bitOffsets.length - 1; n++) {
/*  329 */         k &= (bitOffsets[n] > bitOffsets[n + 1]) ? 1 : 0;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  334 */     if (bandOffsets == null) {
/*      */ 
/*      */       
/*  337 */       bandOffsets = new int[numBands];
/*  338 */       for (int n = 0; n < numBands; n++) {
/*  339 */         bandOffsets[n] = n;
/*      */       }
/*      */     } 
/*  342 */     int j = noTransform & k;
/*      */     
/*  344 */     int[] sampleSize = sampleModel.getSampleSize();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  349 */     int destScanlineBytes = this.w * numBands;
/*      */     
/*  351 */     switch (bmpParam.getCompressionMode()) {
/*      */       case 2:
/*  353 */         this.compressionType = getCompressionType(bmpParam.getCompressionType());
/*      */         break;
/*      */       case 3:
/*  356 */         this.compressionType = bmpImageMetadata.compression;
/*      */         break;
/*      */       case 1:
/*  359 */         this.compressionType = getPreferredCompressionType(colorModel, sampleModel);
/*      */         break;
/*      */       
/*      */       default:
/*  363 */         this.compressionType = 0;
/*      */         break;
/*      */     } 
/*  366 */     if (!canEncodeImage(this.compressionType, colorModel, sampleModel)) {
/*  367 */       if (param.getCompressionMode() == 2) {
/*  368 */         throw new IIOException("Image can not be encoded with compression type " + compressionTypeNames[this.compressionType]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  373 */       this.compressionType = getPreferredCompressionType(colorModel, sampleModel);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  378 */     byte[] r = null, g = null, b = null, a = null;
/*      */     
/*  380 */     if (this.compressionType == 3) {
/*      */       
/*  382 */       bitsPerPixel = DataBuffer.getDataTypeSize(sampleModel.getDataType());
/*      */       
/*  384 */       if (bitsPerPixel != 16 && bitsPerPixel != 32) {
/*      */ 
/*      */         
/*  387 */         bitsPerPixel = 32;
/*      */ 
/*      */ 
/*      */         
/*  391 */         j = 0;
/*      */       } 
/*      */       
/*  394 */       destScanlineBytes = this.w * bitsPerPixel + 7 >> 3;
/*      */       
/*  396 */       isPalette = true;
/*  397 */       paletteEntries = 3;
/*  398 */       r = new byte[paletteEntries];
/*  399 */       g = new byte[paletteEntries];
/*  400 */       b = new byte[paletteEntries];
/*  401 */       a = new byte[paletteEntries];
/*      */       
/*  403 */       int rmask = 16711680;
/*  404 */       int gmask = 65280;
/*  405 */       int bmask = 255;
/*      */       
/*  407 */       if (bitsPerPixel == 16)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  414 */         if (colorModel instanceof DirectColorModel) {
/*  415 */           DirectColorModel dcm = (DirectColorModel)colorModel;
/*  416 */           rmask = dcm.getRedMask();
/*  417 */           gmask = dcm.getGreenMask();
/*  418 */           bmask = dcm.getBlueMask();
/*      */         }
/*      */         else {
/*      */           
/*  422 */           throw new IOException("Image can not be encoded with compression type " + compressionTypeNames[this.compressionType]);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  427 */       writeMaskToPalette(rmask, 0, r, g, b, a);
/*  428 */       writeMaskToPalette(gmask, 1, r, g, b, a);
/*  429 */       writeMaskToPalette(bmask, 2, r, g, b, a);
/*      */       
/*  431 */       if (j == 0) {
/*      */         
/*  433 */         this.bitMasks = new int[3];
/*  434 */         this.bitMasks[0] = rmask;
/*  435 */         this.bitMasks[1] = gmask;
/*  436 */         this.bitMasks[2] = bmask;
/*      */         
/*  438 */         this.bitPos = new int[3];
/*  439 */         this.bitPos[0] = firstLowBit(rmask);
/*  440 */         this.bitPos[1] = firstLowBit(gmask);
/*  441 */         this.bitPos[2] = firstLowBit(bmask);
/*      */       } 
/*      */       
/*  444 */       if (colorModel instanceof IndexColorModel) {
/*  445 */         icm = (IndexColorModel)colorModel;
/*      */       }
/*      */     }
/*  448 */     else if (colorModel instanceof IndexColorModel) {
/*  449 */       isPalette = true;
/*  450 */       icm = (IndexColorModel)colorModel;
/*  451 */       paletteEntries = icm.getMapSize();
/*      */       
/*  453 */       if (paletteEntries <= 2) {
/*  454 */         bitsPerPixel = 1;
/*  455 */         destScanlineBytes = this.w + 7 >> 3;
/*  456 */       } else if (paletteEntries <= 16) {
/*  457 */         bitsPerPixel = 4;
/*  458 */         destScanlineBytes = this.w + 1 >> 1;
/*  459 */       } else if (paletteEntries <= 256) {
/*  460 */         bitsPerPixel = 8;
/*      */       }
/*      */       else {
/*      */         
/*  464 */         bitsPerPixel = 24;
/*  465 */         isPalette = false;
/*  466 */         paletteEntries = 0;
/*  467 */         destScanlineBytes = this.w * 3;
/*      */       } 
/*      */       
/*  470 */       if (isPalette == true) {
/*  471 */         r = new byte[paletteEntries];
/*  472 */         g = new byte[paletteEntries];
/*  473 */         b = new byte[paletteEntries];
/*      */         
/*  475 */         icm.getReds(r);
/*  476 */         icm.getGreens(g);
/*  477 */         icm.getBlues(b);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  482 */     else if (numBands == 1) {
/*      */       
/*  484 */       isPalette = true;
/*  485 */       paletteEntries = 256;
/*  486 */       bitsPerPixel = sampleSize[0];
/*      */       
/*  488 */       destScanlineBytes = this.w * bitsPerPixel + 7 >> 3;
/*      */       
/*  490 */       r = new byte[256];
/*  491 */       g = new byte[256];
/*  492 */       b = new byte[256];
/*      */       
/*  494 */       for (int n = 0; n < 256; n++) {
/*  495 */         r[n] = (byte)n;
/*  496 */         g[n] = (byte)n;
/*  497 */         b[n] = (byte)n;
/*      */       }
/*      */     
/*      */     }
/*  501 */     else if (sampleModel instanceof SinglePixelPackedSampleModel && noSubband) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  510 */       int[] sample_sizes = sampleModel.getSampleSize();
/*  511 */       bitsPerPixel = 0;
/*  512 */       for (int n = 0; n < sample_sizes.length; n++) {
/*  513 */         bitsPerPixel += sample_sizes[n];
/*      */       }
/*  515 */       bitsPerPixel = roundBpp(bitsPerPixel);
/*  516 */       if (bitsPerPixel != DataBuffer.getDataTypeSize(sampleModel.getDataType())) {
/*  517 */         j = 0;
/*      */       }
/*  519 */       destScanlineBytes = this.w * bitsPerPixel + 7 >> 3;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  526 */     int fileSize = 0;
/*  527 */     int offset = 0;
/*  528 */     int headerSize = 0;
/*  529 */     int imageSize = 0;
/*  530 */     int xPelsPerMeter = bmpImageMetadata.xPixelsPerMeter;
/*  531 */     int yPelsPerMeter = bmpImageMetadata.yPixelsPerMeter;
/*  532 */     int colorsUsed = (bmpImageMetadata.colorsUsed > 0) ? bmpImageMetadata.colorsUsed : paletteEntries;
/*      */     
/*  534 */     int colorsImportant = paletteEntries;
/*      */ 
/*      */     
/*  537 */     int padding = destScanlineBytes % 4;
/*  538 */     if (padding != 0) {
/*  539 */       padding = 4 - padding;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  545 */     offset = 54 + paletteEntries * 4;
/*      */     
/*  547 */     imageSize = (destScanlineBytes + padding) * this.h;
/*  548 */     fileSize = imageSize + offset;
/*  549 */     headerSize = 40;
/*      */     
/*  551 */     long headPos = this.stream.getStreamPosition();
/*      */     
/*  553 */     if (param instanceof BMPImageWriteParam) {
/*  554 */       this.isTopDown = ((BMPImageWriteParam)param).isTopDown();
/*      */ 
/*      */       
/*  557 */       if (this.compressionType != 0 && this.compressionType != 3)
/*  558 */         this.isTopDown = false; 
/*      */     } else {
/*  560 */       this.isTopDown = false;
/*      */     } 
/*      */     
/*  563 */     writeFileHeader(fileSize, offset);
/*      */     
/*  565 */     writeInfoHeader(headerSize, bitsPerPixel);
/*      */ 
/*      */     
/*  568 */     this.stream.writeInt(this.compressionType);
/*      */ 
/*      */     
/*  571 */     this.stream.writeInt(imageSize);
/*      */ 
/*      */     
/*  574 */     this.stream.writeInt(xPelsPerMeter);
/*      */ 
/*      */     
/*  577 */     this.stream.writeInt(yPelsPerMeter);
/*      */ 
/*      */     
/*  580 */     this.stream.writeInt(colorsUsed);
/*      */ 
/*      */     
/*  583 */     this.stream.writeInt(colorsImportant);
/*      */ 
/*      */     
/*  586 */     if (isPalette == true)
/*      */     {
/*      */       
/*  589 */       if (this.compressionType == 3) {
/*      */         
/*  591 */         for (int n = 0; n < 3; n++) {
/*  592 */           int mask = (a[n] & 0xFF) + (r[n] & 0xFF) * 256 + (g[n] & 0xFF) * 65536 + (b[n] & 0xFF) * 16777216;
/*  593 */           this.stream.writeInt(mask);
/*      */         } 
/*      */       } else {
/*  596 */         for (int n = 0; n < paletteEntries; n++) {
/*  597 */           this.stream.writeByte(b[n]);
/*  598 */           this.stream.writeByte(g[n]);
/*  599 */           this.stream.writeByte(r[n]);
/*  600 */           this.stream.writeByte(0);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  606 */     int scanlineBytes = this.w * numBands;
/*      */ 
/*      */     
/*  609 */     int[] pixels = new int[scanlineBytes * scaleX];
/*      */ 
/*      */ 
/*      */     
/*  613 */     this.bpixels = new byte[destScanlineBytes];
/*      */ 
/*      */ 
/*      */     
/*  617 */     if (this.compressionType == 4 || this.compressionType == 5) {
/*      */ 
/*      */       
/*  620 */       this.embedded_stream = new ByteArrayOutputStream();
/*  621 */       writeEmbedded(image, (ImageWriteParam)bmpParam);
/*      */       
/*  623 */       this.embedded_stream.flush();
/*  624 */       imageSize = this.embedded_stream.size();
/*      */       
/*  626 */       long endPos = this.stream.getStreamPosition();
/*  627 */       fileSize = offset + imageSize;
/*  628 */       this.stream.seek(headPos);
/*  629 */       writeSize(fileSize, 2);
/*  630 */       this.stream.seek(headPos);
/*  631 */       writeSize(imageSize, 34);
/*  632 */       this.stream.seek(endPos);
/*  633 */       this.stream.write(this.embedded_stream.toByteArray());
/*  634 */       this.embedded_stream = null;
/*      */       
/*  636 */       if (abortRequested()) {
/*  637 */         processWriteAborted();
/*      */       } else {
/*  639 */         processImageComplete();
/*  640 */         this.stream.flushBefore(this.stream.getStreamPosition());
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  646 */     int maxBandOffset = bandOffsets[0];
/*  647 */     for (int i = 1; i < bandOffsets.length; i++) {
/*  648 */       if (bandOffsets[i] > maxBandOffset)
/*  649 */         maxBandOffset = bandOffsets[i]; 
/*      */     } 
/*  651 */     int[] pixel = new int[maxBandOffset + 1];
/*      */     
/*  653 */     int destScanlineLength = destScanlineBytes;
/*      */     
/*  655 */     if (j != 0 && noSubband) {
/*  656 */       destScanlineLength = destScanlineBytes / (DataBuffer.getDataTypeSize(dataType) >> 3);
/*      */     }
/*  658 */     for (int m = 0; m < this.h && 
/*  659 */       !abortRequested(); m++) {
/*      */ 
/*      */ 
/*      */       
/*  663 */       int row = minY + m;
/*      */       
/*  665 */       if (!this.isTopDown) {
/*  666 */         row = minY + this.h - m - 1;
/*      */       }
/*      */       
/*  669 */       Raster src = inputRaster;
/*      */       
/*  671 */       Rectangle srcRect = new Rectangle(minX * scaleX + xOffset, row * scaleY + yOffset, (this.w - 1) * scaleX + 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  676 */       if (!writeRaster) {
/*  677 */         src = input.getData(srcRect);
/*      */       }
/*  679 */       if (j != 0 && noSubband) {
/*  680 */         SampleModel sm = src.getSampleModel();
/*  681 */         int pos = 0;
/*  682 */         int startX = srcRect.x - src.getSampleModelTranslateX();
/*  683 */         int startY = srcRect.y - src.getSampleModelTranslateY();
/*  684 */         if (sm instanceof ComponentSampleModel) {
/*  685 */           ComponentSampleModel csm = (ComponentSampleModel)sm;
/*  686 */           pos = csm.getOffset(startX, startY, 0);
/*  687 */           for (int nb = 1; nb < csm.getNumBands(); nb++) {
/*  688 */             if (pos > csm.getOffset(startX, startY, nb)) {
/*  689 */               pos = csm.getOffset(startX, startY, nb);
/*      */             }
/*      */           } 
/*  692 */         } else if (sm instanceof MultiPixelPackedSampleModel) {
/*  693 */           MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)sm;
/*      */           
/*  695 */           pos = mppsm.getOffset(startX, startY);
/*  696 */         } else if (sm instanceof SinglePixelPackedSampleModel) {
/*  697 */           SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/*      */           
/*  699 */           pos = sppsm.getOffset(startX, startY);
/*      */         } 
/*      */         
/*  702 */         if (this.compressionType == 0 || this.compressionType == 3) {
/*  703 */           byte[] bdata; short[] sdata; short[] usdata; int[] idata; switch (dataType) {
/*      */             
/*      */             case 0:
/*  706 */               bdata = ((DataBufferByte)src.getDataBuffer()).getData();
/*  707 */               this.stream.write(bdata, pos, destScanlineLength);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 2:
/*  712 */               sdata = ((DataBufferShort)src.getDataBuffer()).getData();
/*  713 */               this.stream.writeShorts(sdata, pos, destScanlineLength);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 1:
/*  718 */               usdata = ((DataBufferUShort)src.getDataBuffer()).getData();
/*  719 */               this.stream.writeShorts(usdata, pos, destScanlineLength);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 3:
/*  724 */               idata = ((DataBufferInt)src.getDataBuffer()).getData();
/*  725 */               this.stream.writeInts(idata, pos, destScanlineLength);
/*      */               break;
/*      */           } 
/*      */           
/*  729 */           for (int n = 0; n < padding; n++) {
/*  730 */             this.stream.writeByte(0);
/*      */           }
/*  732 */         } else if (this.compressionType == 2) {
/*  733 */           if (this.bpixels == null || this.bpixels.length < scanlineBytes)
/*  734 */             this.bpixels = new byte[scanlineBytes]; 
/*  735 */           src.getPixels(srcRect.x, srcRect.y, srcRect.width, srcRect.height, pixels);
/*      */           
/*  737 */           for (int h = 0; h < scanlineBytes; h++) {
/*  738 */             this.bpixels[h] = (byte)pixels[h];
/*      */           }
/*  740 */           encodeRLE4(this.bpixels, scanlineBytes);
/*  741 */         } else if (this.compressionType == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  746 */           if (this.bpixels == null || this.bpixels.length < scanlineBytes)
/*  747 */             this.bpixels = new byte[scanlineBytes]; 
/*  748 */           src.getPixels(srcRect.x, srcRect.y, srcRect.width, srcRect.height, pixels);
/*      */           
/*  750 */           for (int h = 0; h < scanlineBytes; h++) {
/*  751 */             this.bpixels[h] = (byte)pixels[h];
/*      */           }
/*      */           
/*  754 */           encodeRLE8(this.bpixels, scanlineBytes);
/*      */         } 
/*      */       } else {
/*  757 */         src.getPixels(srcRect.x, srcRect.y, srcRect.width, srcRect.height, pixels);
/*      */ 
/*      */         
/*  760 */         if (scaleX != 1 || maxBandOffset != numBands - 1) {
/*  761 */           int n; for (int i1 = 0, i2 = 0; i1 < this.w; 
/*  762 */             i1++, i2 += scaleX * numBands, n += numBands) {
/*      */             
/*  764 */             System.arraycopy(pixels, i2, pixel, 0, pixel.length);
/*      */             
/*  766 */             for (int i3 = 0; i3 < numBands; i3++)
/*      */             {
/*  768 */               pixels[n + i3] = pixel[sourceBands[i3]];
/*      */             }
/*      */           } 
/*      */         } 
/*  772 */         writePixels(0, scanlineBytes, bitsPerPixel, pixels, padding, numBands, icm);
/*      */       } 
/*      */ 
/*      */       
/*  776 */       processImageProgress(100.0F * m / this.h);
/*      */     } 
/*      */     
/*  779 */     if (this.compressionType == 2 || this.compressionType == 1) {
/*      */ 
/*      */       
/*  782 */       this.stream.writeByte(0);
/*  783 */       this.stream.writeByte(1);
/*  784 */       incCompImageSize(2);
/*      */       
/*  786 */       imageSize = this.compImageSize;
/*  787 */       fileSize = this.compImageSize + offset;
/*  788 */       long endPos = this.stream.getStreamPosition();
/*  789 */       this.stream.seek(headPos);
/*  790 */       writeSize(fileSize, 2);
/*  791 */       this.stream.seek(headPos);
/*  792 */       writeSize(imageSize, 34);
/*  793 */       this.stream.seek(endPos);
/*      */     } 
/*      */     
/*  796 */     if (abortRequested()) {
/*  797 */       processWriteAborted();
/*      */     } else {
/*  799 */       processImageComplete();
/*  800 */       this.stream.flushBefore(this.stream.getStreamPosition());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writePixels(int l, int scanlineBytes, int bitsPerPixel, int[] pixels, int padding, int numBands, IndexColorModel icm) throws IOException {
/*      */     int j, entries, m;
/*      */     byte[] r, g, b;
/*  808 */     int i, pixel = 0;
/*  809 */     int k = 0;
/*  810 */     switch (bitsPerPixel) {
/*      */ 
/*      */       
/*      */       case 1:
/*  814 */         for (j = 0; j < scanlineBytes / 8; j++) {
/*  815 */           this.bpixels[k++] = (byte)(pixels[l++] << 7 | pixels[l++] << 6 | pixels[l++] << 5 | pixels[l++] << 4 | pixels[l++] << 3 | pixels[l++] << 2 | pixels[l++] << 1 | pixels[l++]);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  826 */         if (scanlineBytes % 8 > 0) {
/*  827 */           pixel = 0;
/*  828 */           for (j = 0; j < scanlineBytes % 8; j++) {
/*  829 */             pixel |= pixels[l++] << 7 - j;
/*      */           }
/*  831 */           this.bpixels[k++] = (byte)pixel;
/*      */         } 
/*  833 */         this.stream.write(this.bpixels, 0, (scanlineBytes + 7) / 8);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  838 */         if (this.compressionType == 2) {
/*  839 */           byte[] bipixels = new byte[scanlineBytes];
/*  840 */           for (int h = 0; h < scanlineBytes; h++) {
/*  841 */             bipixels[h] = (byte)pixels[l++];
/*      */           }
/*  843 */           encodeRLE4(bipixels, scanlineBytes); break;
/*      */         } 
/*  845 */         for (j = 0; j < scanlineBytes / 2; j++) {
/*  846 */           pixel = pixels[l++] << 4 | pixels[l++];
/*  847 */           this.bpixels[k++] = (byte)pixel;
/*      */         } 
/*      */         
/*  850 */         if (scanlineBytes % 2 == 1) {
/*  851 */           pixel = pixels[l] << 4;
/*  852 */           this.bpixels[k++] = (byte)pixel;
/*      */         } 
/*  854 */         this.stream.write(this.bpixels, 0, (scanlineBytes + 1) / 2);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 8:
/*  859 */         if (this.compressionType == 1) {
/*  860 */           for (int h = 0; h < scanlineBytes; h++) {
/*  861 */             this.bpixels[h] = (byte)pixels[l++];
/*      */           }
/*  863 */           encodeRLE8(this.bpixels, scanlineBytes); break;
/*      */         } 
/*  865 */         for (j = 0; j < scanlineBytes; j++) {
/*  866 */           this.bpixels[j] = (byte)pixels[l++];
/*      */         }
/*  868 */         this.stream.write(this.bpixels, 0, scanlineBytes);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 16:
/*  873 */         if (this.spixels == null) {
/*  874 */           this.spixels = new short[scanlineBytes / numBands];
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  884 */         for (j = 0, m = 0; j < scanlineBytes; m++) {
/*  885 */           this.spixels[m] = 0;
/*  886 */           if (this.compressionType == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  891 */             this.spixels[m] = (short)((0x1F & pixels[j]) << 10 | (0x1F & pixels[j + 1]) << 5 | 0x1F & pixels[j + 2]);
/*      */ 
/*      */ 
/*      */             
/*  895 */             j += 3;
/*      */           } else {
/*  897 */             for (int n = 0; n < numBands; n++, j++) {
/*  898 */               this.spixels[m] = (short)(this.spixels[m] | pixels[j] << this.bitPos[n] & this.bitMasks[n]);
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  903 */         this.stream.writeShorts(this.spixels, 0, this.spixels.length);
/*      */         break;
/*      */       
/*      */       case 24:
/*  907 */         if (numBands == 3) {
/*  908 */           for (j = 0; j < scanlineBytes; j += 3) {
/*      */             
/*  910 */             this.bpixels[k++] = (byte)pixels[l + 2];
/*  911 */             this.bpixels[k++] = (byte)pixels[l + 1];
/*  912 */             this.bpixels[k++] = (byte)pixels[l];
/*  913 */             l += 3;
/*      */           } 
/*  915 */           this.stream.write(this.bpixels, 0, scanlineBytes);
/*      */           break;
/*      */         } 
/*  918 */         entries = icm.getMapSize();
/*      */         
/*  920 */         r = new byte[entries];
/*  921 */         g = new byte[entries];
/*  922 */         b = new byte[entries];
/*      */         
/*  924 */         icm.getReds(r);
/*  925 */         icm.getGreens(g);
/*  926 */         icm.getBlues(b);
/*      */ 
/*      */         
/*  929 */         for (i = 0; i < scanlineBytes; i++) {
/*  930 */           int index = pixels[l];
/*  931 */           this.bpixels[k++] = b[index];
/*  932 */           this.bpixels[k++] = g[index];
/*  933 */           this.bpixels[k++] = b[index];
/*  934 */           l++;
/*      */         } 
/*  936 */         this.stream.write(this.bpixels, 0, scanlineBytes * 3);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 32:
/*  941 */         if (this.ipixels == null)
/*  942 */           this.ipixels = new int[scanlineBytes / numBands]; 
/*  943 */         if (numBands == 3) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  953 */           for (int n = 0, i1 = 0; n < scanlineBytes; i1++) {
/*  954 */             this.ipixels[i1] = 0;
/*  955 */             if (this.compressionType == 0) {
/*  956 */               this.ipixels[i1] = (0xFF & pixels[n + 2]) << 16 | (0xFF & pixels[n + 1]) << 8 | 0xFF & pixels[n];
/*      */ 
/*      */ 
/*      */               
/*  960 */               n += 3;
/*      */             } else {
/*  962 */               for (int i2 = 0; i2 < numBands; i2++, n++) {
/*  963 */                 this.ipixels[i1] = this.ipixels[i1] | pixels[n] << this.bitPos[i2] & this.bitMasks[i2];
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  976 */           for (int n = 0; n < scanlineBytes; n++) {
/*  977 */             if (icm != null) {
/*  978 */               this.ipixels[n] = icm.getRGB(pixels[n]);
/*      */             } else {
/*  980 */               this.ipixels[n] = pixels[n] << 16 | pixels[n] << 8 | pixels[n];
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  985 */         this.stream.writeInts(this.ipixels, 0, this.ipixels.length);
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  990 */     if (this.compressionType == 0 || this.compressionType == 3)
/*      */     {
/*  992 */       for (k = 0; k < padding; k++) {
/*  993 */         this.stream.writeByte(0);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void encodeRLE8(byte[] bpixels, int scanlineBytes) throws IOException {
/* 1001 */     int runCount = 1, absVal = -1, j = -1;
/* 1002 */     byte runVal = 0, nextVal = 0;
/*      */     
/* 1004 */     runVal = bpixels[++j];
/* 1005 */     byte[] absBuf = new byte[256];
/*      */     
/* 1007 */     while (j < scanlineBytes - 1) {
/* 1008 */       nextVal = bpixels[++j];
/* 1009 */       if (nextVal == runVal) {
/* 1010 */         if (absVal >= 3) {
/*      */           
/* 1012 */           this.stream.writeByte(0);
/* 1013 */           this.stream.writeByte(absVal);
/* 1014 */           incCompImageSize(2);
/* 1015 */           for (int a = 0; a < absVal; a++) {
/* 1016 */             this.stream.writeByte(absBuf[a]);
/* 1017 */             incCompImageSize(1);
/*      */           } 
/* 1019 */           if (!isEven(absVal))
/*      */           {
/* 1021 */             this.stream.writeByte(0);
/* 1022 */             incCompImageSize(1);
/*      */           }
/*      */         
/* 1025 */         } else if (absVal > -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1030 */           for (int b = 0; b < absVal; b++) {
/* 1031 */             this.stream.writeByte(1);
/* 1032 */             this.stream.writeByte(absBuf[b]);
/* 1033 */             incCompImageSize(2);
/*      */           } 
/*      */         } 
/* 1036 */         absVal = -1;
/* 1037 */         runCount++;
/* 1038 */         if (runCount == 256) {
/*      */           
/* 1040 */           this.stream.writeByte(runCount - 1);
/* 1041 */           this.stream.writeByte(runVal);
/* 1042 */           incCompImageSize(2);
/* 1043 */           runCount = 1;
/*      */         } 
/*      */       } else {
/*      */         
/* 1047 */         if (runCount > 1) {
/*      */           
/* 1049 */           this.stream.writeByte(runCount);
/* 1050 */           this.stream.writeByte(runVal);
/* 1051 */           incCompImageSize(2);
/* 1052 */         } else if (absVal < 0) {
/*      */           
/* 1054 */           absBuf[++absVal] = runVal;
/* 1055 */           absBuf[++absVal] = nextVal;
/* 1056 */         } else if (absVal < 254) {
/*      */           
/* 1058 */           absBuf[++absVal] = nextVal;
/*      */         } else {
/* 1060 */           this.stream.writeByte(0);
/* 1061 */           this.stream.writeByte(absVal + 1);
/* 1062 */           incCompImageSize(2);
/* 1063 */           for (int a = 0; a <= absVal; a++) {
/* 1064 */             this.stream.writeByte(absBuf[a]);
/* 1065 */             incCompImageSize(1);
/*      */           } 
/*      */           
/* 1068 */           this.stream.writeByte(0);
/* 1069 */           incCompImageSize(1);
/* 1070 */           absVal = -1;
/*      */         } 
/* 1072 */         runVal = nextVal;
/* 1073 */         runCount = 1;
/*      */       } 
/*      */       
/* 1076 */       if (j == scanlineBytes - 1) {
/*      */         
/* 1078 */         if (absVal == -1) {
/* 1079 */           this.stream.writeByte(runCount);
/* 1080 */           this.stream.writeByte(runVal);
/* 1081 */           incCompImageSize(2);
/* 1082 */           runCount = 1;
/*      */ 
/*      */         
/*      */         }
/* 1086 */         else if (absVal >= 2) {
/* 1087 */           this.stream.writeByte(0);
/* 1088 */           this.stream.writeByte(absVal + 1);
/* 1089 */           incCompImageSize(2);
/* 1090 */           for (int a = 0; a <= absVal; a++) {
/* 1091 */             this.stream.writeByte(absBuf[a]);
/* 1092 */             incCompImageSize(1);
/*      */           } 
/* 1094 */           if (!isEven(absVal + 1))
/*      */           {
/* 1096 */             this.stream.writeByte(0);
/* 1097 */             incCompImageSize(1);
/*      */           }
/*      */         
/*      */         }
/* 1101 */         else if (absVal > -1) {
/* 1102 */           for (int b = 0; b <= absVal; b++) {
/* 1103 */             this.stream.writeByte(1);
/* 1104 */             this.stream.writeByte(absBuf[b]);
/* 1105 */             incCompImageSize(2);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1111 */         this.stream.writeByte(0);
/* 1112 */         this.stream.writeByte(0);
/* 1113 */         incCompImageSize(2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void encodeRLE4(byte[] bipixels, int scanlineBytes) throws IOException {
/* 1121 */     int runCount = 2, absVal = -1, j = -1, pixel = 0, q = 0;
/* 1122 */     byte runVal1 = 0, runVal2 = 0, nextVal1 = 0, nextVal2 = 0;
/* 1123 */     byte[] absBuf = new byte[256];
/*      */ 
/*      */     
/* 1126 */     runVal1 = bipixels[++j];
/* 1127 */     runVal2 = bipixels[++j];
/*      */     
/* 1129 */     while (j < scanlineBytes - 2) {
/* 1130 */       nextVal1 = bipixels[++j];
/* 1131 */       nextVal2 = bipixels[++j];
/*      */       
/* 1133 */       if (nextVal1 == runVal1) {
/*      */ 
/*      */         
/* 1136 */         if (absVal >= 4) {
/* 1137 */           this.stream.writeByte(0);
/* 1138 */           this.stream.writeByte(absVal - 1);
/* 1139 */           incCompImageSize(2);
/*      */ 
/*      */           
/* 1142 */           for (int a = 0; a < absVal - 2; a += 2) {
/* 1143 */             pixel = absBuf[a] << 4 | absBuf[a + 1];
/* 1144 */             this.stream.writeByte((byte)pixel);
/* 1145 */             incCompImageSize(1);
/*      */           } 
/*      */           
/* 1148 */           if (!isEven(absVal - 1)) {
/* 1149 */             q = absBuf[absVal - 2] << 4 | 0x0;
/* 1150 */             this.stream.writeByte(q);
/* 1151 */             incCompImageSize(1);
/*      */           } 
/*      */           
/* 1154 */           if (!isEven((int)Math.ceil(((absVal - 1) / 2)))) {
/* 1155 */             this.stream.writeByte(0);
/* 1156 */             incCompImageSize(1);
/*      */           } 
/* 1158 */         } else if (absVal > -1) {
/* 1159 */           this.stream.writeByte(2);
/* 1160 */           pixel = absBuf[0] << 4 | absBuf[1];
/* 1161 */           this.stream.writeByte(pixel);
/* 1162 */           incCompImageSize(2);
/*      */         } 
/* 1164 */         absVal = -1;
/*      */         
/* 1166 */         if (nextVal2 == runVal2) {
/*      */           
/* 1168 */           runCount += 2;
/* 1169 */           if (runCount == 256) {
/* 1170 */             this.stream.writeByte(runCount - 1);
/* 1171 */             pixel = runVal1 << 4 | runVal2;
/* 1172 */             this.stream.writeByte(pixel);
/* 1173 */             incCompImageSize(2);
/* 1174 */             runCount = 2;
/* 1175 */             if (j < scanlineBytes - 1) {
/* 1176 */               runVal1 = runVal2;
/* 1177 */               runVal2 = bipixels[++j];
/*      */             } else {
/* 1179 */               this.stream.writeByte(1);
/* 1180 */               int r = runVal2 << 4 | 0x0;
/* 1181 */               this.stream.writeByte(r);
/* 1182 */               incCompImageSize(2);
/* 1183 */               runCount = -1;
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1190 */           runCount++;
/* 1191 */           pixel = runVal1 << 4 | runVal2;
/* 1192 */           this.stream.writeByte(runCount);
/* 1193 */           this.stream.writeByte(pixel);
/* 1194 */           incCompImageSize(2);
/* 1195 */           runCount = 2;
/* 1196 */           runVal1 = nextVal2;
/*      */           
/* 1198 */           if (j < scanlineBytes - 1) {
/* 1199 */             runVal2 = bipixels[++j];
/*      */           } else {
/* 1201 */             this.stream.writeByte(1);
/* 1202 */             int r = nextVal2 << 4 | 0x0;
/* 1203 */             this.stream.writeByte(r);
/* 1204 */             incCompImageSize(2);
/* 1205 */             runCount = -1;
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 1211 */         if (runCount > 2) {
/* 1212 */           pixel = runVal1 << 4 | runVal2;
/* 1213 */           this.stream.writeByte(runCount);
/* 1214 */           this.stream.writeByte(pixel);
/* 1215 */           incCompImageSize(2);
/* 1216 */         } else if (absVal < 0) {
/* 1217 */           absBuf[++absVal] = runVal1;
/* 1218 */           absBuf[++absVal] = runVal2;
/* 1219 */           absBuf[++absVal] = nextVal1;
/* 1220 */           absBuf[++absVal] = nextVal2;
/* 1221 */         } else if (absVal < 253) {
/* 1222 */           absBuf[++absVal] = nextVal1;
/* 1223 */           absBuf[++absVal] = nextVal2;
/*      */         } else {
/* 1225 */           this.stream.writeByte(0);
/* 1226 */           this.stream.writeByte(absVal + 1);
/* 1227 */           incCompImageSize(2);
/* 1228 */           for (int a = 0; a < absVal; a += 2) {
/* 1229 */             pixel = absBuf[a] << 4 | absBuf[a + 1];
/* 1230 */             this.stream.writeByte((byte)pixel);
/* 1231 */             incCompImageSize(1);
/*      */           } 
/*      */ 
/*      */           
/* 1235 */           this.stream.writeByte(0);
/* 1236 */           incCompImageSize(1);
/* 1237 */           absVal = -1;
/*      */         } 
/*      */         
/* 1240 */         runVal1 = nextVal1;
/* 1241 */         runVal2 = nextVal2;
/* 1242 */         runCount = 2;
/*      */       } 
/*      */       
/* 1245 */       if (j >= scanlineBytes - 2) {
/* 1246 */         if (absVal == -1 && runCount >= 2) {
/* 1247 */           if (j == scanlineBytes - 2) {
/* 1248 */             if (bipixels[++j] == runVal1) {
/* 1249 */               runCount++;
/* 1250 */               pixel = runVal1 << 4 | runVal2;
/* 1251 */               this.stream.writeByte(runCount);
/* 1252 */               this.stream.writeByte(pixel);
/* 1253 */               incCompImageSize(2);
/*      */             } else {
/* 1255 */               pixel = runVal1 << 4 | runVal2;
/* 1256 */               this.stream.writeByte(runCount);
/* 1257 */               this.stream.writeByte(pixel);
/* 1258 */               this.stream.writeByte(1);
/* 1259 */               pixel = bipixels[j] << 4 | 0x0;
/* 1260 */               this.stream.writeByte(pixel);
/* 1261 */               int n = bipixels[j] << 4 | 0x0;
/* 1262 */               incCompImageSize(4);
/*      */             } 
/*      */           } else {
/* 1265 */             this.stream.writeByte(runCount);
/* 1266 */             pixel = runVal1 << 4 | runVal2;
/* 1267 */             this.stream.writeByte(pixel);
/* 1268 */             incCompImageSize(2);
/*      */           } 
/* 1270 */         } else if (absVal > -1) {
/* 1271 */           if (j == scanlineBytes - 2) {
/* 1272 */             absBuf[++absVal] = bipixels[++j];
/*      */           }
/* 1274 */           if (absVal >= 2) {
/* 1275 */             this.stream.writeByte(0);
/* 1276 */             this.stream.writeByte(absVal + 1);
/* 1277 */             incCompImageSize(2);
/* 1278 */             for (int a = 0; a < absVal; a += 2) {
/* 1279 */               pixel = absBuf[a] << 4 | absBuf[a + 1];
/* 1280 */               this.stream.writeByte((byte)pixel);
/* 1281 */               incCompImageSize(1);
/*      */             } 
/* 1283 */             if (!isEven(absVal + 1)) {
/* 1284 */               q = absBuf[absVal] << 4 | 0x0;
/* 1285 */               this.stream.writeByte(q);
/* 1286 */               incCompImageSize(1);
/*      */             } 
/*      */ 
/*      */             
/* 1290 */             if (!isEven((int)Math.ceil(((absVal + 1) / 2)))) {
/* 1291 */               this.stream.writeByte(0);
/* 1292 */               incCompImageSize(1);
/*      */             } 
/*      */           } else {
/*      */             int n;
/* 1296 */             switch (absVal) {
/*      */               case 0:
/* 1298 */                 this.stream.writeByte(1);
/* 1299 */                 n = absBuf[0] << 4 | 0x0;
/* 1300 */                 this.stream.writeByte(n);
/* 1301 */                 incCompImageSize(2);
/*      */                 break;
/*      */               case 1:
/* 1304 */                 this.stream.writeByte(2);
/* 1305 */                 pixel = absBuf[0] << 4 | absBuf[1];
/* 1306 */                 this.stream.writeByte(pixel);
/* 1307 */                 incCompImageSize(2);
/*      */                 break;
/*      */             } 
/*      */           
/*      */           } 
/*      */         } 
/* 1313 */         this.stream.writeByte(0);
/* 1314 */         this.stream.writeByte(0);
/* 1315 */         incCompImageSize(2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private synchronized void incCompImageSize(int value) {
/* 1322 */     this.compImageSize += value;
/*      */   }
/*      */   
/*      */   private boolean isEven(int number) {
/* 1326 */     return (number % 2 == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeFileHeader(int fileSize, int offset) throws IOException {
/* 1331 */     this.stream.writeByte(66);
/* 1332 */     this.stream.writeByte(77);
/*      */ 
/*      */     
/* 1335 */     this.stream.writeInt(fileSize);
/*      */ 
/*      */     
/* 1338 */     this.stream.writeInt(0);
/*      */ 
/*      */     
/* 1341 */     this.stream.writeInt(offset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeInfoHeader(int headerSize, int bitsPerPixel) throws IOException {
/* 1348 */     this.stream.writeInt(headerSize);
/*      */ 
/*      */     
/* 1351 */     this.stream.writeInt(this.w);
/*      */ 
/*      */     
/* 1354 */     if (this.isTopDown == true) {
/* 1355 */       this.stream.writeInt(-this.h);
/*      */     } else {
/* 1357 */       this.stream.writeInt(this.h);
/*      */     } 
/*      */     
/* 1360 */     this.stream.writeShort(1);
/*      */ 
/*      */     
/* 1363 */     this.stream.writeShort(bitsPerPixel);
/*      */   }
/*      */   
/*      */   private void writeSize(int dword, int offset) throws IOException {
/* 1367 */     this.stream.skipBytes(offset);
/* 1368 */     this.stream.writeInt(dword);
/*      */   }
/*      */   
/*      */   public void reset() {
/* 1372 */     super.reset();
/* 1373 */     this.stream = null;
/*      */   }
/*      */   
/*      */   static int getCompressionType(String typeString) {
/* 1377 */     for (int i = 0; i < BMPConstants.compressionTypeNames.length; i++) {
/* 1378 */       if (BMPConstants.compressionTypeNames[i].equals(typeString))
/* 1379 */         return i; 
/* 1380 */     }  return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeEmbedded(IIOImage image, ImageWriteParam bmpParam) throws IOException {
/* 1385 */     String format = (this.compressionType == 4) ? "jpeg" : "png";
/*      */     
/* 1387 */     Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(format);
/* 1388 */     ImageWriter writer = null;
/* 1389 */     if (iterator.hasNext())
/* 1390 */       writer = iterator.next(); 
/* 1391 */     if (writer != null) {
/* 1392 */       if (this.embedded_stream == null) {
/* 1393 */         throw new RuntimeException("No stream for writing embedded image!");
/*      */       }
/*      */       
/* 1396 */       writer.addIIOWriteProgressListener(new IIOWriteProgressAdapter() {
/*      */             public void imageProgress(ImageWriter source, float percentageDone) {
/* 1398 */               BMPImageWriter.this.processImageProgress(percentageDone);
/*      */             }
/*      */           });
/*      */       
/* 1402 */       writer.addIIOWriteWarningListener(new IIOWriteWarningListener() {
/*      */             public void warningOccurred(ImageWriter source, int imageIndex, String warning) {
/* 1404 */               BMPImageWriter.this.processWarningOccurred(imageIndex, warning);
/*      */             }
/*      */           });
/*      */ 
/*      */       
/* 1409 */       ImageOutputStream emb_ios = ImageIO.createImageOutputStream(this.embedded_stream);
/* 1410 */       writer.setOutput(emb_ios);
/* 1411 */       ImageWriteParam param = writer.getDefaultWriteParam();
/*      */       
/* 1413 */       param.setDestinationOffset(bmpParam.getDestinationOffset());
/* 1414 */       param.setSourceBands(bmpParam.getSourceBands());
/* 1415 */       param.setSourceRegion(bmpParam.getSourceRegion());
/* 1416 */       param.setSourceSubsampling(bmpParam.getSourceXSubsampling(), bmpParam
/* 1417 */           .getSourceYSubsampling(), bmpParam
/* 1418 */           .getSubsamplingXOffset(), bmpParam
/* 1419 */           .getSubsamplingYOffset());
/* 1420 */       writer.write((IIOMetadata)null, image, param);
/* 1421 */       emb_ios.flush();
/*      */     } else {
/* 1423 */       throw new RuntimeException(I18N.getString("BMPImageWrite5") + " " + format);
/*      */     } 
/*      */   }
/*      */   
/*      */   private int firstLowBit(int num) {
/* 1428 */     int count = 0;
/* 1429 */     while ((num & 0x1) == 0) {
/* 1430 */       count++;
/* 1431 */       num >>>= 1;
/*      */     } 
/* 1433 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class IIOWriteProgressAdapter
/*      */     implements IIOWriteProgressListener
/*      */   {
/*      */     private IIOWriteProgressAdapter() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void imageComplete(ImageWriter source) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void imageProgress(ImageWriter source, float percentageDone) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void imageStarted(ImageWriter source, int imageIndex) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void thumbnailComplete(ImageWriter source) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void thumbnailProgress(ImageWriter source, float percentageDone) {}
/*      */ 
/*      */     
/*      */     public void thumbnailStarted(ImageWriter source, int imageIndex, int thumbnailIndex) {}
/*      */ 
/*      */     
/*      */     public void writeAborted(ImageWriter source) {}
/*      */   }
/*      */ 
/*      */   
/*      */   static int getPreferredCompressionType(ColorModel cm, SampleModel sm) {
/* 1472 */     ImageTypeSpecifier imageType = new ImageTypeSpecifier(cm, sm);
/* 1473 */     return getPreferredCompressionType(imageType);
/*      */   }
/*      */   
/*      */   static int getPreferredCompressionType(ImageTypeSpecifier imageType) {
/* 1477 */     int biType = imageType.getBufferedImageType();
/* 1478 */     if (biType == 8 || biType == 9)
/*      */     {
/* 1480 */       return 3;
/*      */     }
/* 1482 */     return 0;
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
/*      */   protected boolean canEncodeImage(int compression, ColorModel cm, SampleModel sm) {
/* 1494 */     ImageTypeSpecifier imgType = new ImageTypeSpecifier(cm, sm);
/* 1495 */     return canEncodeImage(compression, imgType);
/*      */   }
/*      */   
/*      */   protected boolean canEncodeImage(int compression, ImageTypeSpecifier imgType) {
/* 1499 */     ImageWriterSpi spi = getOriginatingProvider();
/* 1500 */     if (!spi.canEncodeImage(imgType)) {
/* 1501 */       return false;
/*      */     }
/* 1503 */     int bpp = imgType.getColorModel().getPixelSize();
/* 1504 */     if (this.compressionType == 2 && bpp != 4)
/*      */     {
/* 1506 */       return false;
/*      */     }
/* 1508 */     if (this.compressionType == 1 && bpp != 8)
/*      */     {
/* 1510 */       return false;
/*      */     }
/* 1512 */     if (bpp == 16) {
/*      */       int i, j;
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
/* 1539 */       boolean canUseRGB = false;
/* 1540 */       boolean canUseBITFIELDS = false;
/*      */       
/* 1542 */       SampleModel sm = imgType.getSampleModel();
/* 1543 */       if (sm instanceof SinglePixelPackedSampleModel) {
/*      */         
/* 1545 */         int[] sizes = ((SinglePixelPackedSampleModel)sm).getSampleSize();
/*      */         
/* 1547 */         canUseRGB = true;
/* 1548 */         canUseBITFIELDS = true;
/* 1549 */         for (int k = 0; k < sizes.length; k++) {
/* 1550 */           i = canUseRGB & ((sizes[k] == 5) ? 1 : 0);
/* 1551 */           j = canUseBITFIELDS & ((sizes[k] == 5 || (k == 1 && sizes[k] == 6)) ? 1 : 0);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1556 */       return ((this.compressionType == 0 && i != 0) || (this.compressionType == 3 && j != 0));
/*      */     } 
/*      */     
/* 1559 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void writeMaskToPalette(int mask, int i, byte[] r, byte[] g, byte[] b, byte[] a) {
/* 1564 */     b[i] = (byte)(0xFF & mask >> 24);
/* 1565 */     g[i] = (byte)(0xFF & mask >> 16);
/* 1566 */     r[i] = (byte)(0xFF & mask >> 8);
/* 1567 */     a[i] = (byte)(0xFF & mask);
/*      */   }
/*      */   
/*      */   private int roundBpp(int x) {
/* 1571 */     if (x <= 8)
/* 1572 */       return 8; 
/* 1573 */     if (x <= 16)
/* 1574 */       return 16; 
/* 1575 */     if (x <= 24) {
/* 1576 */       return 24;
/*      */     }
/* 1578 */     return 32;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/bmp/BMPImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */