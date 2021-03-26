/*      */ package com.github.jaiimageio.impl.common;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
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
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import javax.imageio.IIOException;
/*      */ import javax.imageio.ImageReadParam;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.ImageWriter;
/*      */ import javax.imageio.spi.IIORegistry;
/*      */ import javax.imageio.spi.ImageReaderSpi;
/*      */ import javax.imageio.spi.ImageReaderWriterSpi;
/*      */ import javax.imageio.spi.ImageWriterSpi;
/*      */ import javax.imageio.spi.ServiceRegistry;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ImageUtil
/*      */ {
/*      */   public static final ColorModel createColorModel(SampleModel sampleModel) {
/*  172 */     if (sampleModel == null) {
/*  173 */       throw new IllegalArgumentException("sampleModel == null!");
/*      */     }
/*      */ 
/*      */     
/*  177 */     int dataType = sampleModel.getDataType();
/*      */ 
/*      */     
/*  180 */     switch (dataType) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */         break;
/*      */       
/*      */       default:
/*  190 */         return null;
/*      */     } 
/*      */ 
/*      */     
/*  194 */     ColorModel colorModel = null;
/*      */ 
/*      */     
/*  197 */     int[] sampleSize = sampleModel.getSampleSize();
/*      */ 
/*      */     
/*  200 */     if (sampleModel instanceof ComponentSampleModel) {
/*      */       
/*  202 */       int numBands = sampleModel.getNumBands();
/*      */ 
/*      */       
/*  205 */       ColorSpace colorSpace = null;
/*  206 */       if (numBands <= 2) {
/*  207 */         colorSpace = ColorSpace.getInstance(1003);
/*  208 */       } else if (numBands <= 4) {
/*  209 */         colorSpace = ColorSpace.getInstance(1000);
/*      */       } else {
/*  211 */         colorSpace = new BogusColorSpace(numBands);
/*      */       } 
/*      */       
/*  214 */       boolean hasAlpha = (numBands == 2 || numBands == 4);
/*  215 */       boolean isAlphaPremultiplied = false;
/*  216 */       int transparency = hasAlpha ? 3 : 1;
/*      */ 
/*      */       
/*  219 */       colorModel = new ComponentColorModel(colorSpace, sampleSize, hasAlpha, isAlphaPremultiplied, transparency, dataType);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  225 */       if (sampleModel.getNumBands() <= 4 && sampleModel instanceof SinglePixelPackedSampleModel) {
/*      */         
/*  227 */         SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sampleModel;
/*      */ 
/*      */         
/*  230 */         int[] bitMasks = sppsm.getBitMasks();
/*  231 */         int rmask = 0;
/*  232 */         int gmask = 0;
/*  233 */         int bmask = 0;
/*  234 */         int amask = 0;
/*      */         
/*  236 */         int numBands = bitMasks.length;
/*  237 */         if (numBands <= 2) {
/*  238 */           rmask = gmask = bmask = bitMasks[0];
/*  239 */           if (numBands == 2) {
/*  240 */             amask = bitMasks[1];
/*      */           }
/*      */         } else {
/*  243 */           rmask = bitMasks[0];
/*  244 */           gmask = bitMasks[1];
/*  245 */           bmask = bitMasks[2];
/*  246 */           if (numBands == 4) {
/*  247 */             amask = bitMasks[3];
/*      */           }
/*      */         } 
/*      */         
/*  251 */         int bits = 0;
/*  252 */         for (int i = 0; i < sampleSize.length; i++) {
/*  253 */           bits += sampleSize[i];
/*      */         }
/*      */         
/*  256 */         return new DirectColorModel(bits, rmask, gmask, bmask, amask);
/*      */       } 
/*  258 */       if (sampleModel instanceof MultiPixelPackedSampleModel) {
/*      */         
/*  260 */         int bitsPerSample = sampleSize[0];
/*  261 */         int numEntries = 1 << bitsPerSample;
/*  262 */         byte[] map = new byte[numEntries];
/*  263 */         for (int i = 0; i < numEntries; i++) {
/*  264 */           map[i] = (byte)(i * 255 / (numEntries - 1));
/*      */         }
/*      */         
/*  267 */         colorModel = new IndexColorModel(bitsPerSample, numEntries, map, map, map);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  272 */     return colorModel;
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
/*      */   public static byte[] getPackedBinaryData(Raster raster, Rectangle rect) {
/*  292 */     SampleModel sm = raster.getSampleModel();
/*  293 */     if (!isBinary(sm)) {
/*  294 */       throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
/*      */     }
/*      */     
/*  297 */     int rectX = rect.x;
/*  298 */     int rectY = rect.y;
/*  299 */     int rectWidth = rect.width;
/*  300 */     int rectHeight = rect.height;
/*      */     
/*  302 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*      */     
/*  304 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  305 */     int dy = rectY - raster.getSampleModelTranslateY();
/*      */     
/*  307 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  308 */     int lineStride = mpp.getScanlineStride();
/*  309 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  310 */     int bitOffset = mpp.getBitOffset(dx);
/*      */     
/*  312 */     int numBytesPerRow = (rectWidth + 7) / 8;
/*  313 */     if (dataBuffer instanceof DataBufferByte && eltOffset == 0 && bitOffset == 0 && numBytesPerRow == lineStride && (((DataBufferByte)dataBuffer)
/*      */ 
/*      */       
/*  316 */       .getData()).length == numBytesPerRow * rectHeight)
/*      */     {
/*  318 */       return ((DataBufferByte)dataBuffer).getData();
/*      */     }
/*      */     
/*  321 */     byte[] binaryDataArray = new byte[numBytesPerRow * rectHeight];
/*      */     
/*  323 */     int b = 0;
/*      */     
/*  325 */     if (bitOffset == 0) {
/*  326 */       if (dataBuffer instanceof DataBufferByte) {
/*  327 */         byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  328 */         int stride = numBytesPerRow;
/*  329 */         int offset = 0;
/*  330 */         for (int y = 0; y < rectHeight; y++) {
/*  331 */           System.arraycopy(data, eltOffset, binaryDataArray, offset, stride);
/*      */ 
/*      */           
/*  334 */           offset += stride;
/*  335 */           eltOffset += lineStride;
/*      */         } 
/*  337 */       } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*      */ 
/*      */ 
/*      */         
/*  341 */         short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*      */         
/*  343 */         for (int y = 0; y < rectHeight; y++) {
/*  344 */           int xRemaining = rectWidth;
/*  345 */           int i = eltOffset;
/*  346 */           while (xRemaining > 8) {
/*  347 */             short datum = data[i++];
/*  348 */             binaryDataArray[b++] = (byte)(datum >>> 8 & 0xFF);
/*  349 */             binaryDataArray[b++] = (byte)(datum & 0xFF);
/*  350 */             xRemaining -= 16;
/*      */           } 
/*  352 */           if (xRemaining > 0) {
/*  353 */             binaryDataArray[b++] = (byte)(data[i] >>> 8 & 0xFF);
/*      */           }
/*  355 */           eltOffset += lineStride;
/*      */         } 
/*  357 */       } else if (dataBuffer instanceof DataBufferInt) {
/*  358 */         int[] data = ((DataBufferInt)dataBuffer).getData();
/*      */         
/*  360 */         for (int y = 0; y < rectHeight; y++) {
/*  361 */           int xRemaining = rectWidth;
/*  362 */           int i = eltOffset;
/*  363 */           while (xRemaining > 24) {
/*  364 */             int datum = data[i++];
/*  365 */             binaryDataArray[b++] = (byte)(datum >>> 24 & 0xFF);
/*  366 */             binaryDataArray[b++] = (byte)(datum >>> 16 & 0xFF);
/*  367 */             binaryDataArray[b++] = (byte)(datum >>> 8 & 0xFF);
/*  368 */             binaryDataArray[b++] = (byte)(datum & 0xFF);
/*  369 */             xRemaining -= 32;
/*      */           } 
/*  371 */           int shift = 24;
/*  372 */           while (xRemaining > 0) {
/*  373 */             binaryDataArray[b++] = (byte)(data[i] >>> shift & 0xFF);
/*      */             
/*  375 */             shift -= 8;
/*  376 */             xRemaining -= 8;
/*      */           } 
/*  378 */           eltOffset += lineStride;
/*      */         }
/*      */       
/*      */       } 
/*  382 */     } else if (dataBuffer instanceof DataBufferByte) {
/*  383 */       byte[] data = ((DataBufferByte)dataBuffer).getData();
/*      */       
/*  385 */       if ((bitOffset & 0x7) == 0) {
/*  386 */         int stride = numBytesPerRow;
/*  387 */         int offset = 0;
/*  388 */         for (int y = 0; y < rectHeight; y++) {
/*  389 */           System.arraycopy(data, eltOffset, binaryDataArray, offset, stride);
/*      */ 
/*      */           
/*  392 */           offset += stride;
/*  393 */           eltOffset += lineStride;
/*      */         } 
/*      */       } else {
/*  396 */         int leftShift = bitOffset & 0x7;
/*  397 */         int rightShift = 8 - leftShift;
/*  398 */         for (int y = 0; y < rectHeight; y++) {
/*  399 */           int i = eltOffset;
/*  400 */           int xRemaining = rectWidth;
/*  401 */           while (xRemaining > 0) {
/*  402 */             if (xRemaining > rightShift) {
/*  403 */               binaryDataArray[b++] = (byte)((data[i++] & 0xFF) << leftShift | (data[i] & 0xFF) >>> rightShift);
/*      */             }
/*      */             else {
/*      */               
/*  407 */               binaryDataArray[b++] = (byte)((data[i] & 0xFF) << leftShift);
/*      */             } 
/*      */             
/*  410 */             xRemaining -= 8;
/*      */           } 
/*  412 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*  415 */     } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*      */ 
/*      */ 
/*      */       
/*  419 */       short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*      */       
/*  421 */       for (int y = 0; y < rectHeight; y++) {
/*  422 */         int bOffset = bitOffset;
/*  423 */         for (int x = 0; x < rectWidth; x += 8, bOffset += 8) {
/*  424 */           int i = eltOffset + bOffset / 16;
/*  425 */           int mod = bOffset % 16;
/*  426 */           int left = data[i] & 0xFFFF;
/*  427 */           if (mod <= 8) {
/*  428 */             binaryDataArray[b++] = (byte)(left >>> 8 - mod);
/*      */           } else {
/*  430 */             int delta = mod - 8;
/*  431 */             int right = data[i + 1] & 0xFFFF;
/*  432 */             binaryDataArray[b++] = (byte)(left << delta | right >>> 16 - delta);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  437 */         eltOffset += lineStride;
/*      */       } 
/*  439 */     } else if (dataBuffer instanceof DataBufferInt) {
/*  440 */       int[] data = ((DataBufferInt)dataBuffer).getData();
/*      */       
/*  442 */       for (int y = 0; y < rectHeight; y++) {
/*  443 */         int bOffset = bitOffset;
/*  444 */         for (int x = 0; x < rectWidth; x += 8, bOffset += 8) {
/*  445 */           int i = eltOffset + bOffset / 32;
/*  446 */           int mod = bOffset % 32;
/*  447 */           int left = data[i];
/*  448 */           if (mod <= 24) {
/*  449 */             binaryDataArray[b++] = (byte)(left >>> 24 - mod);
/*      */           } else {
/*      */             
/*  452 */             int delta = mod - 24;
/*  453 */             int right = data[i + 1];
/*  454 */             binaryDataArray[b++] = (byte)(left << delta | right >>> 32 - delta);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  459 */         eltOffset += lineStride;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  464 */     return binaryDataArray;
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
/*      */   public static byte[] getUnpackedBinaryData(Raster raster, Rectangle rect) {
/*  477 */     SampleModel sm = raster.getSampleModel();
/*  478 */     if (!isBinary(sm)) {
/*  479 */       throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
/*      */     }
/*      */     
/*  482 */     int rectX = rect.x;
/*  483 */     int rectY = rect.y;
/*  484 */     int rectWidth = rect.width;
/*  485 */     int rectHeight = rect.height;
/*      */     
/*  487 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*      */     
/*  489 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  490 */     int dy = rectY - raster.getSampleModelTranslateY();
/*      */     
/*  492 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  493 */     int lineStride = mpp.getScanlineStride();
/*  494 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  495 */     int bitOffset = mpp.getBitOffset(dx);
/*      */     
/*  497 */     byte[] bdata = new byte[rectWidth * rectHeight];
/*  498 */     int maxY = rectY + rectHeight;
/*  499 */     int maxX = rectX + rectWidth;
/*  500 */     int k = 0;
/*      */     
/*  502 */     if (dataBuffer instanceof DataBufferByte) {
/*  503 */       byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  504 */       for (int y = rectY; y < maxY; y++) {
/*  505 */         int bOffset = eltOffset * 8 + bitOffset;
/*  506 */         for (int x = rectX; x < maxX; x++) {
/*  507 */           byte b = data[bOffset / 8];
/*  508 */           bdata[k++] = (byte)(b >>> (7 - bOffset & 0x7) & 0x1);
/*      */           
/*  510 */           bOffset++;
/*      */         } 
/*  512 */         eltOffset += lineStride;
/*      */       } 
/*  514 */     } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*      */ 
/*      */ 
/*      */       
/*  518 */       short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  519 */       for (int y = rectY; y < maxY; y++) {
/*  520 */         int bOffset = eltOffset * 16 + bitOffset;
/*  521 */         for (int x = rectX; x < maxX; x++) {
/*  522 */           short s = data[bOffset / 16];
/*  523 */           bdata[k++] = (byte)(s >>> 15 - bOffset % 16 & 0x1);
/*      */ 
/*      */           
/*  526 */           bOffset++;
/*      */         } 
/*  528 */         eltOffset += lineStride;
/*      */       } 
/*  530 */     } else if (dataBuffer instanceof DataBufferInt) {
/*  531 */       int[] data = ((DataBufferInt)dataBuffer).getData();
/*  532 */       for (int y = rectY; y < maxY; y++) {
/*  533 */         int bOffset = eltOffset * 32 + bitOffset;
/*  534 */         for (int x = rectX; x < maxX; x++) {
/*  535 */           int i = data[bOffset / 32];
/*  536 */           bdata[k++] = (byte)(i >>> 31 - bOffset % 32 & 0x1);
/*      */ 
/*      */           
/*  539 */           bOffset++;
/*      */         } 
/*  541 */         eltOffset += lineStride;
/*      */       } 
/*      */     } 
/*      */     
/*  545 */     return bdata;
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
/*      */   public static void setPackedBinaryData(byte[] binaryDataArray, WritableRaster raster, Rectangle rect) {
/*  560 */     SampleModel sm = raster.getSampleModel();
/*  561 */     if (!isBinary(sm)) {
/*  562 */       throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
/*      */     }
/*      */     
/*  565 */     int rectX = rect.x;
/*  566 */     int rectY = rect.y;
/*  567 */     int rectWidth = rect.width;
/*  568 */     int rectHeight = rect.height;
/*      */     
/*  570 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*      */     
/*  572 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  573 */     int dy = rectY - raster.getSampleModelTranslateY();
/*      */     
/*  575 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  576 */     int lineStride = mpp.getScanlineStride();
/*  577 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  578 */     int bitOffset = mpp.getBitOffset(dx);
/*      */     
/*  580 */     int b = 0;
/*      */     
/*  582 */     if (bitOffset == 0) {
/*  583 */       if (dataBuffer instanceof DataBufferByte) {
/*  584 */         byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  585 */         if (data == binaryDataArray) {
/*      */           return;
/*      */         }
/*      */         
/*  589 */         int stride = (rectWidth + 7) / 8;
/*  590 */         int offset = 0;
/*  591 */         for (int y = 0; y < rectHeight; y++) {
/*  592 */           System.arraycopy(binaryDataArray, offset, data, eltOffset, stride);
/*      */ 
/*      */           
/*  595 */           offset += stride;
/*  596 */           eltOffset += lineStride;
/*      */         } 
/*  598 */       } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*      */ 
/*      */ 
/*      */         
/*  602 */         short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*      */         
/*  604 */         for (int y = 0; y < rectHeight; y++) {
/*  605 */           int xRemaining = rectWidth;
/*  606 */           int i = eltOffset;
/*  607 */           while (xRemaining > 8) {
/*  608 */             data[i++] = (short)((binaryDataArray[b++] & 0xFF) << 8 | binaryDataArray[b++] & 0xFF);
/*      */ 
/*      */             
/*  611 */             xRemaining -= 16;
/*      */           } 
/*  613 */           if (xRemaining > 0) {
/*  614 */             data[i++] = (short)((binaryDataArray[b++] & 0xFF) << 8);
/*      */           }
/*      */           
/*  617 */           eltOffset += lineStride;
/*      */         } 
/*  619 */       } else if (dataBuffer instanceof DataBufferInt) {
/*  620 */         int[] data = ((DataBufferInt)dataBuffer).getData();
/*      */         
/*  622 */         for (int y = 0; y < rectHeight; y++) {
/*  623 */           int xRemaining = rectWidth;
/*  624 */           int i = eltOffset;
/*  625 */           while (xRemaining > 24) {
/*  626 */             data[i++] = (binaryDataArray[b++] & 0xFF) << 24 | (binaryDataArray[b++] & 0xFF) << 16 | (binaryDataArray[b++] & 0xFF) << 8 | binaryDataArray[b++] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  631 */             xRemaining -= 32;
/*      */           } 
/*  633 */           int shift = 24;
/*  634 */           while (xRemaining > 0) {
/*  635 */             data[i] = data[i] | (binaryDataArray[b++] & 0xFF) << shift;
/*      */             
/*  637 */             shift -= 8;
/*  638 */             xRemaining -= 8;
/*      */           } 
/*  640 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  644 */       int stride = (rectWidth + 7) / 8;
/*  645 */       int offset = 0;
/*  646 */       if (dataBuffer instanceof DataBufferByte) {
/*  647 */         byte[] data = ((DataBufferByte)dataBuffer).getData();
/*      */         
/*  649 */         if ((bitOffset & 0x7) == 0) {
/*  650 */           for (int y = 0; y < rectHeight; y++) {
/*  651 */             System.arraycopy(binaryDataArray, offset, data, eltOffset, stride);
/*      */ 
/*      */             
/*  654 */             offset += stride;
/*  655 */             eltOffset += lineStride;
/*      */           } 
/*      */         } else {
/*  658 */           int rightShift = bitOffset & 0x7;
/*  659 */           int leftShift = 8 - rightShift;
/*  660 */           int leftShift8 = 8 + leftShift;
/*  661 */           int mask = (byte)(255 << leftShift);
/*  662 */           int mask1 = (byte)(mask ^ 0xFFFFFFFF);
/*      */           
/*  664 */           for (int y = 0; y < rectHeight; y++) {
/*  665 */             int i = eltOffset;
/*  666 */             int xRemaining = rectWidth;
/*  667 */             while (xRemaining > 0) {
/*  668 */               byte datum = binaryDataArray[b++];
/*      */               
/*  670 */               if (xRemaining > leftShift8) {
/*      */ 
/*      */                 
/*  673 */                 data[i] = (byte)(data[i] & mask | (datum & 0xFF) >>> rightShift);
/*      */                 
/*  675 */                 data[++i] = (byte)((datum & 0xFF) << leftShift);
/*  676 */               } else if (xRemaining > leftShift) {
/*      */ 
/*      */ 
/*      */                 
/*  680 */                 data[i] = (byte)(data[i] & mask | (datum & 0xFF) >>> rightShift);
/*      */                 
/*  682 */                 i++;
/*  683 */                 data[i] = (byte)(data[i] & mask1 | (datum & 0xFF) << leftShift);
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/*  688 */                 int remainMask = (1 << leftShift - xRemaining) - 1;
/*  689 */                 data[i] = (byte)(data[i] & (mask | remainMask) | (datum & 0xFF) >>> rightShift & (remainMask ^ 0xFFFFFFFF));
/*      */               } 
/*      */ 
/*      */               
/*  693 */               xRemaining -= 8;
/*      */             } 
/*  695 */             eltOffset += lineStride;
/*      */           } 
/*      */         } 
/*  698 */       } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*      */ 
/*      */ 
/*      */         
/*  702 */         short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*      */         
/*  704 */         int rightShift = bitOffset & 0x7;
/*  705 */         int leftShift = 8 - rightShift;
/*  706 */         int leftShift16 = 16 + leftShift;
/*  707 */         int mask = (short)(255 << leftShift ^ 0xFFFFFFFF);
/*  708 */         int mask1 = (short)(65535 << leftShift);
/*  709 */         int mask2 = (short)(mask1 ^ 0xFFFFFFFF);
/*      */         
/*  711 */         for (int y = 0; y < rectHeight; y++) {
/*  712 */           int bOffset = bitOffset;
/*  713 */           int xRemaining = rectWidth;
/*  714 */           for (int x = 0; x < rectWidth; 
/*  715 */             x += 8, bOffset += 8, xRemaining -= 8) {
/*  716 */             int i = eltOffset + (bOffset >> 4);
/*  717 */             int mod = bOffset & 0xF;
/*  718 */             int datum = binaryDataArray[b++] & 0xFF;
/*  719 */             if (mod <= 8) {
/*      */               
/*  721 */               if (xRemaining < 8)
/*      */               {
/*  723 */                 datum &= 255 << 8 - xRemaining;
/*      */               }
/*  725 */               data[i] = (short)(data[i] & mask | datum << leftShift);
/*  726 */             } else if (xRemaining > leftShift16) {
/*      */               
/*  728 */               data[i] = (short)(data[i] & mask1 | datum >>> rightShift & 0xFFFF);
/*  729 */               data[++i] = (short)(datum << leftShift & 0xFFFF);
/*      */             }
/*  731 */             else if (xRemaining > leftShift) {
/*      */ 
/*      */               
/*  734 */               data[i] = (short)(data[i] & mask1 | datum >>> rightShift & 0xFFFF);
/*  735 */               i++;
/*  736 */               data[i] = (short)(data[i] & mask2 | datum << leftShift & 0xFFFF);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  741 */               int remainMask = (1 << leftShift - xRemaining) - 1;
/*  742 */               data[i] = (short)(data[i] & (mask1 | remainMask) | datum >>> rightShift & 0xFFFF & (remainMask ^ 0xFFFFFFFF));
/*      */             } 
/*      */           } 
/*      */           
/*  746 */           eltOffset += lineStride;
/*      */         } 
/*  748 */       } else if (dataBuffer instanceof DataBufferInt) {
/*  749 */         int[] data = ((DataBufferInt)dataBuffer).getData();
/*  750 */         int rightShift = bitOffset & 0x7;
/*  751 */         int leftShift = 8 - rightShift;
/*  752 */         int leftShift32 = 32 + leftShift;
/*  753 */         int mask = -1 << leftShift;
/*  754 */         int mask1 = mask ^ 0xFFFFFFFF;
/*      */         
/*  756 */         for (int y = 0; y < rectHeight; y++) {
/*  757 */           int bOffset = bitOffset;
/*  758 */           int xRemaining = rectWidth;
/*  759 */           for (int x = 0; x < rectWidth; 
/*  760 */             x += 8, bOffset += 8, xRemaining -= 8) {
/*  761 */             int i = eltOffset + (bOffset >> 5);
/*  762 */             int mod = bOffset & 0x1F;
/*  763 */             int datum = binaryDataArray[b++] & 0xFF;
/*  764 */             if (mod <= 24) {
/*      */               
/*  766 */               int shift = 24 - mod;
/*  767 */               if (xRemaining < 8)
/*      */               {
/*  769 */                 datum &= 255 << 8 - xRemaining;
/*      */               }
/*  771 */               data[i] = data[i] & (255 << shift ^ 0xFFFFFFFF) | datum << shift;
/*  772 */             } else if (xRemaining > leftShift32) {
/*      */               
/*  774 */               data[i] = data[i] & mask | datum >>> rightShift;
/*  775 */               data[++i] = datum << leftShift;
/*  776 */             } else if (xRemaining > leftShift) {
/*      */ 
/*      */               
/*  779 */               data[i] = data[i] & mask | datum >>> rightShift;
/*  780 */               i++;
/*  781 */               data[i] = data[i] & mask1 | datum << leftShift;
/*      */             } else {
/*      */               
/*  784 */               int remainMask = (1 << leftShift - xRemaining) - 1;
/*  785 */               data[i] = data[i] & (mask | remainMask) | datum >>> rightShift & (remainMask ^ 0xFFFFFFFF);
/*      */             } 
/*      */           } 
/*      */           
/*  789 */           eltOffset += lineStride;
/*      */         } 
/*      */       } 
/*      */     } 
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
/*      */   public static void setUnpackedBinaryData(byte[] bdata, WritableRaster raster, Rectangle rect) {
/*  810 */     SampleModel sm = raster.getSampleModel();
/*  811 */     if (!isBinary(sm)) {
/*  812 */       throw new IllegalArgumentException(I18N.getString("ImageUtil0"));
/*      */     }
/*      */     
/*  815 */     int rectX = rect.x;
/*  816 */     int rectY = rect.y;
/*  817 */     int rectWidth = rect.width;
/*  818 */     int rectHeight = rect.height;
/*      */     
/*  820 */     DataBuffer dataBuffer = raster.getDataBuffer();
/*      */     
/*  822 */     int dx = rectX - raster.getSampleModelTranslateX();
/*  823 */     int dy = rectY - raster.getSampleModelTranslateY();
/*      */     
/*  825 */     MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)sm;
/*  826 */     int lineStride = mpp.getScanlineStride();
/*  827 */     int eltOffset = dataBuffer.getOffset() + mpp.getOffset(dx, dy);
/*  828 */     int bitOffset = mpp.getBitOffset(dx);
/*      */     
/*  830 */     int k = 0;
/*      */     
/*  832 */     if (dataBuffer instanceof DataBufferByte) {
/*  833 */       byte[] data = ((DataBufferByte)dataBuffer).getData();
/*  834 */       for (int y = 0; y < rectHeight; y++) {
/*  835 */         int bOffset = eltOffset * 8 + bitOffset;
/*  836 */         for (int x = 0; x < rectWidth; x++) {
/*  837 */           if (bdata[k++] != 0) {
/*  838 */             data[bOffset / 8] = (byte)(data[bOffset / 8] | (byte)(1 << (7 - bOffset & 0x7)));
/*      */           }
/*      */           
/*  841 */           bOffset++;
/*      */         } 
/*  843 */         eltOffset += lineStride;
/*      */       } 
/*  845 */     } else if (dataBuffer instanceof DataBufferShort || dataBuffer instanceof DataBufferUShort) {
/*      */ 
/*      */ 
/*      */       
/*  849 */       short[] data = (dataBuffer instanceof DataBufferShort) ? ((DataBufferShort)dataBuffer).getData() : ((DataBufferUShort)dataBuffer).getData();
/*  850 */       for (int y = 0; y < rectHeight; y++) {
/*  851 */         int bOffset = eltOffset * 16 + bitOffset;
/*  852 */         for (int x = 0; x < rectWidth; x++) {
/*  853 */           if (bdata[k++] != 0) {
/*  854 */             data[bOffset / 16] = (short)(data[bOffset / 16] | (short)(1 << 15 - bOffset % 16));
/*      */           }
/*      */ 
/*      */           
/*  858 */           bOffset++;
/*      */         } 
/*  860 */         eltOffset += lineStride;
/*      */       } 
/*  862 */     } else if (dataBuffer instanceof DataBufferInt) {
/*  863 */       int[] data = ((DataBufferInt)dataBuffer).getData();
/*  864 */       for (int y = 0; y < rectHeight; y++) {
/*  865 */         int bOffset = eltOffset * 32 + bitOffset;
/*  866 */         for (int x = 0; x < rectWidth; x++) {
/*  867 */           if (bdata[k++] != 0) {
/*  868 */             data[bOffset / 32] = data[bOffset / 32] | 1 << 31 - bOffset % 32;
/*      */           }
/*      */ 
/*      */           
/*  872 */           bOffset++;
/*      */         } 
/*  874 */         eltOffset += lineStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static boolean isBinary(SampleModel sm) {
/*  880 */     return (sm instanceof MultiPixelPackedSampleModel && ((MultiPixelPackedSampleModel)sm)
/*  881 */       .getPixelBitStride() == 1 && sm
/*  882 */       .getNumBands() == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ColorModel createColorModel(ColorSpace colorSpace, SampleModel sampleModel) {
/*  887 */     ColorModel colorModel = null;
/*      */     
/*  889 */     if (sampleModel == null) {
/*  890 */       throw new IllegalArgumentException(I18N.getString("ImageUtil1"));
/*      */     }
/*      */     
/*  893 */     int numBands = sampleModel.getNumBands();
/*  894 */     if (numBands < 1 || numBands > 4) {
/*  895 */       return null;
/*      */     }
/*      */     
/*  898 */     int dataType = sampleModel.getDataType();
/*  899 */     if (sampleModel instanceof ComponentSampleModel) {
/*  900 */       if (dataType < 0 || dataType > 5)
/*      */       {
/*      */         
/*  903 */         return null;
/*      */       }
/*      */       
/*  906 */       if (colorSpace == null)
/*      */       {
/*      */ 
/*      */         
/*  910 */         colorSpace = (numBands <= 2) ? ColorSpace.getInstance(1003) : ColorSpace.getInstance(1000);
/*      */       }
/*  912 */       boolean useAlpha = (numBands == 2 || numBands == 4);
/*  913 */       int transparency = useAlpha ? 3 : 1;
/*      */ 
/*      */       
/*  916 */       boolean premultiplied = false;
/*      */       
/*  918 */       int dataTypeSize = DataBuffer.getDataTypeSize(dataType);
/*  919 */       int[] bits = new int[numBands];
/*  920 */       for (int i = 0; i < numBands; i++) {
/*  921 */         bits[i] = dataTypeSize;
/*      */       }
/*      */       
/*  924 */       colorModel = new ComponentColorModel(colorSpace, bits, useAlpha, premultiplied, transparency, dataType);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  930 */     else if (sampleModel instanceof SinglePixelPackedSampleModel) {
/*  931 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sampleModel;
/*      */ 
/*      */       
/*  934 */       int[] bitMasks = sppsm.getBitMasks();
/*  935 */       int rmask = 0;
/*  936 */       int gmask = 0;
/*  937 */       int bmask = 0;
/*  938 */       int amask = 0;
/*      */       
/*  940 */       numBands = bitMasks.length;
/*  941 */       if (numBands <= 2) {
/*  942 */         rmask = gmask = bmask = bitMasks[0];
/*  943 */         if (numBands == 2) {
/*  944 */           amask = bitMasks[1];
/*      */         }
/*      */       } else {
/*  947 */         rmask = bitMasks[0];
/*  948 */         gmask = bitMasks[1];
/*  949 */         bmask = bitMasks[2];
/*  950 */         if (numBands == 4) {
/*  951 */           amask = bitMasks[3];
/*      */         }
/*      */       } 
/*      */       
/*  955 */       int[] sampleSize = sppsm.getSampleSize();
/*  956 */       int bits = 0;
/*  957 */       for (int i = 0; i < sampleSize.length; i++) {
/*  958 */         bits += sampleSize[i];
/*      */       }
/*      */       
/*  961 */       if (colorSpace == null) {
/*  962 */         colorSpace = ColorSpace.getInstance(1000);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  968 */       colorModel = new DirectColorModel(colorSpace, bits, rmask, gmask, bmask, amask, false, sampleModel.getDataType());
/*  969 */     } else if (sampleModel instanceof MultiPixelPackedSampleModel) {
/*      */       
/*  971 */       int bits = ((MultiPixelPackedSampleModel)sampleModel).getPixelBitStride();
/*  972 */       int size = 1 << bits;
/*  973 */       byte[] comp = new byte[size];
/*      */       
/*  975 */       for (int i = 0; i < size; i++) {
/*  976 */         comp[i] = (byte)(255 * i / (size - 1));
/*      */       }
/*  978 */       colorModel = new IndexColorModel(bits, size, comp, comp, comp);
/*      */     } 
/*      */     
/*  981 */     return colorModel;
/*      */   }
/*      */   
/*      */   public static int getElementSize(SampleModel sm) {
/*  985 */     int elementSize = DataBuffer.getDataTypeSize(sm.getDataType());
/*      */     
/*  987 */     if (sm instanceof MultiPixelPackedSampleModel) {
/*  988 */       MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)sm;
/*      */       
/*  990 */       return mppsm.getSampleSize(0) * mppsm.getNumBands();
/*  991 */     }  if (sm instanceof ComponentSampleModel)
/*  992 */       return sm.getNumBands() * elementSize; 
/*  993 */     if (sm instanceof SinglePixelPackedSampleModel) {
/*  994 */       return elementSize;
/*      */     }
/*      */     
/*  997 */     return elementSize * sm.getNumBands();
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getTileSize(SampleModel sm) {
/* 1002 */     int elementSize = DataBuffer.getDataTypeSize(sm.getDataType());
/*      */     
/* 1004 */     if (sm instanceof MultiPixelPackedSampleModel) {
/* 1005 */       MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)sm;
/*      */ 
/*      */       
/* 1008 */       return ((mppsm.getScanlineStride() * mppsm.getHeight() + (mppsm.getDataBitOffset() + elementSize - 1) / elementSize) * (elementSize + 7) / 8);
/*      */     } 
/* 1010 */     if (sm instanceof ComponentSampleModel) {
/* 1011 */       ComponentSampleModel csm = (ComponentSampleModel)sm;
/* 1012 */       int[] bandOffsets = csm.getBandOffsets();
/* 1013 */       int maxBandOff = bandOffsets[0];
/* 1014 */       for (int i = 1; i < bandOffsets.length; i++) {
/* 1015 */         maxBandOff = Math.max(maxBandOff, bandOffsets[i]);
/*      */       }
/* 1017 */       long size = 0L;
/* 1018 */       int pixelStride = csm.getPixelStride();
/* 1019 */       int scanlineStride = csm.getScanlineStride();
/* 1020 */       if (maxBandOff >= 0)
/* 1021 */         size += (maxBandOff + 1); 
/* 1022 */       if (pixelStride > 0)
/* 1023 */         size += (pixelStride * (sm.getWidth() - 1)); 
/* 1024 */       if (scanlineStride > 0) {
/* 1025 */         size += (scanlineStride * (sm.getHeight() - 1));
/*      */       }
/* 1027 */       int[] bankIndices = csm.getBankIndices();
/* 1028 */       maxBandOff = bankIndices[0];
/* 1029 */       for (int j = 1; j < bankIndices.length; j++)
/* 1030 */         maxBandOff = Math.max(maxBandOff, bankIndices[j]); 
/* 1031 */       return size * (maxBandOff + 1) * ((elementSize + 7) / 8);
/* 1032 */     }  if (sm instanceof SinglePixelPackedSampleModel) {
/* 1033 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/*      */ 
/*      */       
/* 1036 */       long size = (sppsm.getScanlineStride() * (sppsm.getHeight() - 1) + sppsm.getWidth());
/* 1037 */       return size * ((elementSize + 7) / 8);
/*      */     } 
/*      */     
/* 1040 */     return 0L;
/*      */   }
/*      */   
/*      */   public static long getBandSize(SampleModel sm) {
/* 1044 */     int elementSize = DataBuffer.getDataTypeSize(sm.getDataType());
/*      */     
/* 1046 */     if (sm instanceof ComponentSampleModel) {
/* 1047 */       ComponentSampleModel csm = (ComponentSampleModel)sm;
/* 1048 */       int pixelStride = csm.getPixelStride();
/* 1049 */       int scanlineStride = csm.getScanlineStride();
/* 1050 */       long size = Math.min(pixelStride, scanlineStride);
/*      */       
/* 1052 */       if (pixelStride > 0)
/* 1053 */         size += (pixelStride * (sm.getWidth() - 1)); 
/* 1054 */       if (scanlineStride > 0)
/* 1055 */         size += (scanlineStride * (sm.getHeight() - 1)); 
/* 1056 */       return size * ((elementSize + 7) / 8);
/*      */     } 
/* 1058 */     return getTileSize(sm);
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
/*      */   public static boolean isGrayscaleMapping(IndexColorModel icm) {
/* 1074 */     if (icm == null) {
/* 1075 */       throw new IllegalArgumentException("icm == null!");
/*      */     }
/*      */ 
/*      */     
/* 1079 */     int mapSize = icm.getMapSize();
/*      */     
/* 1081 */     byte[] r = new byte[mapSize];
/* 1082 */     byte[] g = new byte[mapSize];
/* 1083 */     byte[] b = new byte[mapSize];
/*      */     
/* 1085 */     icm.getReds(r);
/* 1086 */     icm.getGreens(g);
/* 1087 */     icm.getBlues(b);
/*      */     
/* 1089 */     boolean isGrayToColor = true;
/*      */     
/*      */     int i;
/* 1092 */     for (i = 0; i < mapSize; i++) {
/* 1093 */       byte temp = (byte)(i * 255 / (mapSize - 1));
/*      */       
/* 1095 */       if (r[i] != temp || g[i] != temp || b[i] != temp) {
/* 1096 */         isGrayToColor = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1101 */     if (!isGrayToColor) {
/* 1102 */       isGrayToColor = true;
/*      */       
/*      */       int j;
/* 1105 */       for (i = 0, j = mapSize - 1; i < mapSize; i++, j--) {
/* 1106 */         byte temp = (byte)(j * 255 / (mapSize - 1));
/*      */         
/* 1108 */         if (r[i] != temp || g[i] != temp || b[i] != temp) {
/* 1109 */           isGrayToColor = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1115 */     return isGrayToColor;
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
/*      */   public static boolean isIndicesForGrayscale(byte[] r, byte[] g, byte[] b) {
/* 1128 */     if (r.length != g.length || r.length != b.length) {
/* 1129 */       return false;
/*      */     }
/* 1131 */     int size = r.length;
/*      */     
/* 1133 */     if (size != 256) {
/* 1134 */       return false;
/*      */     }
/* 1136 */     for (int i = 0; i < size; i++) {
/* 1137 */       byte temp = (byte)i;
/*      */       
/* 1139 */       if (r[i] != temp || g[i] != temp || b[i] != temp) {
/* 1140 */         return false;
/*      */       }
/*      */     } 
/* 1143 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String convertObjectToString(Object obj) {
/* 1148 */     if (obj == null) {
/* 1149 */       return "";
/*      */     }
/* 1151 */     String s = "";
/* 1152 */     if (obj instanceof byte[]) {
/* 1153 */       byte[] bArray = (byte[])obj;
/* 1154 */       for (int i = 0; i < bArray.length; i++)
/* 1155 */         s = s + bArray[i] + " "; 
/* 1156 */       return s;
/*      */     } 
/*      */     
/* 1159 */     if (obj instanceof int[]) {
/* 1160 */       int[] iArray = (int[])obj;
/* 1161 */       for (int i = 0; i < iArray.length; i++)
/* 1162 */         s = s + iArray[i] + " "; 
/* 1163 */       return s;
/*      */     } 
/*      */     
/* 1166 */     if (obj instanceof short[]) {
/* 1167 */       short[] sArray = (short[])obj;
/* 1168 */       for (int i = 0; i < sArray.length; i++)
/* 1169 */         s = s + sArray[i] + " "; 
/* 1170 */       return s;
/*      */     } 
/*      */     
/* 1173 */     return obj.toString();
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
/*      */   public static final void canEncodeImage(ImageWriter writer, ImageTypeSpecifier type) throws IIOException {
/* 1187 */     ImageWriterSpi spi = writer.getOriginatingProvider();
/*      */     
/* 1189 */     if (type != null && spi != null && !spi.canEncodeImage(type)) {
/* 1190 */       throw new IIOException(I18N.getString("ImageUtil2") + " " + writer
/* 1191 */           .getClass().getName());
/*      */     }
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
/*      */   public static final void canEncodeImage(ImageWriter writer, ColorModel colorModel, SampleModel sampleModel) throws IIOException {
/* 1207 */     ImageTypeSpecifier type = null;
/* 1208 */     if (colorModel != null && sampleModel != null)
/* 1209 */       type = new ImageTypeSpecifier(colorModel, sampleModel); 
/* 1210 */     canEncodeImage(writer, type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean imageIsContiguous(RenderedImage image) {
/*      */     SampleModel sm;
/* 1218 */     if (image instanceof BufferedImage) {
/* 1219 */       WritableRaster ras = ((BufferedImage)image).getRaster();
/* 1220 */       sm = ras.getSampleModel();
/*      */     } else {
/* 1222 */       sm = image.getSampleModel();
/*      */     } 
/*      */     
/* 1225 */     if (sm instanceof ComponentSampleModel) {
/*      */ 
/*      */       
/* 1228 */       ComponentSampleModel csm = (ComponentSampleModel)sm;
/*      */       
/* 1230 */       if (csm.getPixelStride() != csm.getNumBands()) {
/* 1231 */         return false;
/*      */       }
/*      */       
/* 1234 */       int[] bandOffsets = csm.getBandOffsets();
/* 1235 */       for (int i = 0; i < bandOffsets.length; i++) {
/* 1236 */         if (bandOffsets[i] != i) {
/* 1237 */           return false;
/*      */         }
/*      */       } 
/*      */       
/* 1241 */       int[] bankIndices = csm.getBankIndices();
/* 1242 */       for (int j = 0; j < bandOffsets.length; j++) {
/* 1243 */         if (bankIndices[j] != 0) {
/* 1244 */           return false;
/*      */         }
/*      */       } 
/*      */       
/* 1248 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1254 */     return isBinary(sm);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final ImageTypeSpecifier getDestinationType(ImageReadParam param, Iterator<ImageTypeSpecifier> imageTypes) throws IIOException {
/* 1265 */     if (imageTypes == null || !imageTypes.hasNext()) {
/* 1266 */       throw new IllegalArgumentException("imageTypes null or empty!");
/*      */     }
/*      */     
/* 1269 */     ImageTypeSpecifier imageType = null;
/*      */ 
/*      */     
/* 1272 */     if (param != null) {
/* 1273 */       imageType = param.getDestinationType();
/*      */     }
/*      */ 
/*      */     
/* 1277 */     if (imageType == null) {
/* 1278 */       Object o = imageTypes.next();
/* 1279 */       if (!(o instanceof ImageTypeSpecifier)) {
/* 1280 */         throw new IllegalArgumentException("Non-ImageTypeSpecifier retrieved from imageTypes!");
/*      */       }
/*      */       
/* 1283 */       imageType = (ImageTypeSpecifier)o;
/*      */     } else {
/* 1285 */       boolean foundIt = false;
/* 1286 */       while (imageTypes.hasNext()) {
/*      */         
/* 1288 */         ImageTypeSpecifier type = imageTypes.next();
/* 1289 */         if (type.equals(imageType)) {
/* 1290 */           foundIt = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1295 */       if (!foundIt) {
/* 1296 */         throw new IIOException("Destination type from ImageReadParam does not match!");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1301 */     return imageType;
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
/*      */   public static boolean isNonStandardICCColorSpace(ColorSpace cs) {
/* 1313 */     boolean retval = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1325 */       retval = (cs instanceof java.awt.color.ICC_ColorSpace && !cs.isCS_sRGB() && !cs.equals(ColorSpace.getInstance(1004)) && !cs.equals(ColorSpace.getInstance(1003)) && !cs.equals(ColorSpace.getInstance(1001)) && !cs.equals(ColorSpace.getInstance(1002)));
/* 1326 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*      */ 
/*      */ 
/*      */     
/* 1330 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List getJDKImageReaderWriterSPI(ServiceRegistry registry, String formatName, boolean isReader) {
/*      */     Class<ImageWriterSpi> spiClass;
/*      */     String descPart;
/* 1339 */     IIORegistry iioRegistry = (IIORegistry)registry;
/*      */ 
/*      */ 
/*      */     
/* 1343 */     if (isReader) {
/* 1344 */       Class<ImageReaderSpi> clazz = ImageReaderSpi.class;
/* 1345 */       descPart = " image reader";
/*      */     } else {
/* 1347 */       spiClass = ImageWriterSpi.class;
/* 1348 */       descPart = " image writer";
/*      */     } 
/*      */     
/* 1351 */     Iterator<ImageWriterSpi> iter = iioRegistry.getServiceProviders(spiClass, true);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1356 */     String desc = "standard " + formatName + descPart;
/* 1357 */     String jiioPath = "com.github.jaiimageio.impl";
/* 1358 */     Locale locale = Locale.getDefault();
/* 1359 */     ArrayList<ImageReaderWriterSpi> list = new ArrayList();
/* 1360 */     while (iter.hasNext()) {
/* 1361 */       ImageReaderWriterSpi provider = iter.next();
/*      */ 
/*      */       
/* 1364 */       if (provider.getVendorName().startsWith("Sun Microsystems") && desc
/* 1365 */         .equalsIgnoreCase(provider.getDescription(locale)) && 
/*      */         
/* 1367 */         !provider.getPluginClassName().startsWith(jiioPath)) {
/*      */ 
/*      */         
/* 1370 */         String[] formatNames = provider.getFormatNames();
/* 1371 */         for (int i = 0; i < formatNames.length; i++) {
/* 1372 */           if (formatNames[i].equalsIgnoreCase(formatName)) {
/*      */             
/* 1374 */             list.add(provider);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1381 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void processOnRegistration(ServiceRegistry registry, Class<ImageReaderWriterSpi> category, String formatName, ImageReaderWriterSpi spi, int deregisterJvmVersion, int priorityJvmVersion) {
/* 1392 */     String jvmVendor = System.getProperty("java.vendor");
/*      */     
/* 1394 */     String jvmVersionString = System.getProperty("java.specification.version");
/* 1395 */     int verIndex = jvmVersionString.indexOf("1.");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1404 */     jvmVersionString = jvmVersionString.substring(verIndex + 2);
/*      */     
/* 1406 */     int jvmVersion = Integer.parseInt(jvmVersionString);
/*      */     
/* 1408 */     if (jvmVendor.equals("Sun Microsystems Inc.")) {
/*      */       List<ImageReaderWriterSpi> list;
/*      */       
/* 1411 */       if (spi instanceof ImageReaderSpi) {
/* 1412 */         list = getJDKImageReaderWriterSPI(registry, formatName, true);
/*      */       } else {
/* 1414 */         list = getJDKImageReaderWriterSPI(registry, formatName, false);
/*      */       } 
/* 1416 */       if (jvmVersion >= deregisterJvmVersion && list.size() != 0) {
/*      */         
/* 1418 */         registry.deregisterServiceProvider(spi, category);
/*      */       } else {
/* 1420 */         for (int i = 0; i < list.size(); i++) {
/* 1421 */           if (jvmVersion >= priorityJvmVersion) {
/*      */             
/* 1423 */             registry.setOrdering(category, list
/* 1424 */                 .get(i), spi);
/*      */           }
/*      */           else {
/*      */             
/* 1428 */             registry.setOrdering(category, spi, list
/*      */                 
/* 1430 */                 .get(i));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int readMultiByteInteger(ImageInputStream iis) throws IOException {
/* 1438 */     int value = iis.readByte();
/* 1439 */     int result = value & 0x7F;
/* 1440 */     while ((value & 0x80) == 128) {
/* 1441 */       result <<= 7;
/* 1442 */       value = iis.readByte();
/* 1443 */       result |= value & 0x7F;
/*      */     } 
/* 1445 */     return result;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/common/ImageUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */