/*     */ package org.apache.pdfbox.pdmodel.graphics.image;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageOutputStream;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.filter.FilterFactory;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDICCBased;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LosslessFactory
/*     */ {
/*     */   static boolean usePredictorEncoder = true;
/*     */   
/*     */   public static PDImageXObject createFromImage(PDDocument document, BufferedImage image) throws IOException {
/*  82 */     if ((image.getType() == 10 && image.getColorModel().getPixelSize() <= 8) || (image
/*  83 */       .getType() == 12 && image.getColorModel().getPixelSize() == 1))
/*     */     {
/*  85 */       return createFromGrayImage(image, document);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  90 */     if (usePredictorEncoder) {
/*     */       
/*  92 */       PDImageXObject pdImageXObject = (new PredictorEncoder(document, image)).encode();
/*  93 */       if (pdImageXObject != null) {
/*     */         
/*  95 */         if (pdImageXObject.getColorSpace() == PDDeviceRGB.INSTANCE && pdImageXObject
/*  96 */           .getBitsPerComponent() < 16 && image
/*  97 */           .getWidth() * image.getHeight() <= 2500) {
/*     */ 
/*     */           
/* 100 */           PDImageXObject pdImageXObjectClassic = createFromRGBImage(image, document);
/* 101 */           if (pdImageXObjectClassic.getCOSObject().getLength() < pdImageXObject
/* 102 */             .getCOSObject().getLength()) {
/*     */             
/* 104 */             pdImageXObject.getCOSObject().close();
/* 105 */             return pdImageXObjectClassic;
/*     */           } 
/*     */ 
/*     */           
/* 109 */           pdImageXObjectClassic.getCOSObject().close();
/*     */         } 
/*     */         
/* 112 */         return pdImageXObject;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 117 */     return createFromRGBImage(image, document);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDImageXObject createFromGrayImage(BufferedImage image, PDDocument document) throws IOException {
/* 125 */     int height = image.getHeight();
/* 126 */     int width = image.getWidth();
/* 127 */     int[] rgbLineBuffer = new int[width];
/* 128 */     int bpc = image.getColorModel().getPixelSize();
/* 129 */     ByteArrayOutputStream baos = new ByteArrayOutputStream((width * bpc / 8 + ((width * bpc % 8 != 0) ? 1 : 0)) * height);
/* 130 */     MemoryCacheImageOutputStream mcios = new MemoryCacheImageOutputStream(baos);
/* 131 */     for (int y = 0; y < height; y++) {
/*     */       
/* 133 */       for (int pixel : image.getRGB(0, y, width, 1, rgbLineBuffer, 0, width))
/*     */       {
/* 135 */         mcios.writeBits((pixel & 0xFF), bpc);
/*     */       }
/*     */       
/* 138 */       int bitOffset = mcios.getBitOffset();
/* 139 */       if (bitOffset != 0)
/*     */       {
/* 141 */         mcios.writeBits(0L, 8 - bitOffset);
/*     */       }
/*     */     } 
/* 144 */     mcios.flush();
/* 145 */     mcios.close();
/* 146 */     return prepareImageXObject(document, baos.toByteArray(), image
/* 147 */         .getWidth(), image.getHeight(), bpc, (PDColorSpace)PDDeviceGray.INSTANCE);
/*     */   }
/*     */   
/*     */   private static PDImageXObject createFromRGBImage(BufferedImage image, PDDocument document) throws IOException {
/*     */     byte[] alphaImageData;
/* 152 */     int height = image.getHeight();
/* 153 */     int width = image.getWidth();
/* 154 */     int[] rgbLineBuffer = new int[width];
/* 155 */     int bpc = 8;
/* 156 */     PDDeviceRGB pDDeviceRGB = PDDeviceRGB.INSTANCE;
/* 157 */     byte[] imageData = new byte[width * height * 3];
/* 158 */     int byteIdx = 0;
/* 159 */     int alphaByteIdx = 0;
/* 160 */     int alphaBitPos = 7;
/* 161 */     int transparency = image.getTransparency();
/* 162 */     int apbc = (transparency == 2) ? 1 : 8;
/*     */     
/* 164 */     if (transparency != 1) {
/*     */       
/* 166 */       alphaImageData = new byte[(width * apbc / 8 + ((width * apbc % 8 != 0) ? 1 : 0)) * height];
/*     */     }
/*     */     else {
/*     */       
/* 170 */       alphaImageData = new byte[0];
/*     */     } 
/* 172 */     for (int y = 0; y < height; y++) {
/*     */       
/* 174 */       for (int pixel : image.getRGB(0, y, width, 1, rgbLineBuffer, 0, width)) {
/*     */         
/* 176 */         imageData[byteIdx++] = (byte)(pixel >> 16 & 0xFF);
/* 177 */         imageData[byteIdx++] = (byte)(pixel >> 8 & 0xFF);
/* 178 */         imageData[byteIdx++] = (byte)(pixel & 0xFF);
/* 179 */         if (transparency != 1)
/*     */         {
/*     */ 
/*     */           
/* 183 */           if (transparency == 2) {
/*     */ 
/*     */             
/* 186 */             alphaImageData[alphaByteIdx] = (byte)(alphaImageData[alphaByteIdx] | (pixel >> 24 & 0x1) << alphaBitPos);
/* 187 */             if (--alphaBitPos < 0)
/*     */             {
/* 189 */               alphaBitPos = 7;
/* 190 */               alphaByteIdx++;
/*     */             }
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 196 */             alphaImageData[alphaByteIdx++] = (byte)(pixel >> 24 & 0xFF);
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 202 */       if (transparency == 2 && alphaBitPos != 7) {
/*     */         
/* 204 */         alphaBitPos = 7;
/* 205 */         alphaByteIdx++;
/*     */       } 
/*     */     } 
/* 208 */     PDImageXObject pdImage = prepareImageXObject(document, imageData, image
/* 209 */         .getWidth(), image.getHeight(), bpc, (PDColorSpace)pDDeviceRGB);
/* 210 */     if (transparency != 1) {
/*     */       
/* 212 */       PDImageXObject pdMask = prepareImageXObject(document, alphaImageData, image
/* 213 */           .getWidth(), image.getHeight(), apbc, (PDColorSpace)PDDeviceGray.INSTANCE);
/* 214 */       pdImage.getCOSObject().setItem(COSName.SMASK, pdMask);
/*     */     } 
/* 216 */     return pdImage;
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
/*     */   private static PDImageXObject prepareImageXObject(PDDocument document, byte[] byteArray, int width, int height, int bitsPerComponent, PDColorSpace initColorSpace) throws IOException {
/* 236 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(byteArray.length / 2);
/*     */     
/* 238 */     Filter filter = FilterFactory.INSTANCE.getFilter(COSName.FLATE_DECODE);
/* 239 */     filter.encode(new ByteArrayInputStream(byteArray), baos, new COSDictionary(), 0);
/*     */     
/* 241 */     ByteArrayInputStream encodedByteStream = new ByteArrayInputStream(baos.toByteArray());
/* 242 */     return new PDImageXObject(document, encodedByteStream, (COSBase)COSName.FLATE_DECODE, width, height, bitsPerComponent, initColorSpace);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class PredictorEncoder
/*     */   {
/*     */     private final PDDocument document;
/*     */     
/*     */     private final BufferedImage image;
/*     */     
/*     */     private final int componentsPerPixel;
/*     */     
/*     */     private final int transferType;
/*     */     
/*     */     private final int bytesPerComponent;
/*     */     
/*     */     private final int bytesPerPixel;
/*     */     
/*     */     private final int height;
/*     */     
/*     */     private final int width;
/*     */     
/*     */     private final byte[] dataRawRowNone;
/*     */     private final byte[] dataRawRowSub;
/*     */     private final byte[] dataRawRowUp;
/*     */     private final byte[] dataRawRowAverage;
/*     */     private final byte[] dataRawRowPaeth;
/*     */     final int imageType;
/*     */     final boolean hasAlpha;
/*     */     final byte[] alphaImageData;
/*     */     final byte[] aValues;
/*     */     final byte[] cValues;
/*     */     final byte[] bValues;
/*     */     final byte[] xValues;
/*     */     final byte[] tmpResultValues;
/*     */     
/*     */     PredictorEncoder(PDDocument document, BufferedImage image) {
/* 279 */       this.document = document;
/* 280 */       this.image = image;
/*     */ 
/*     */       
/* 283 */       this.componentsPerPixel = image.getColorModel().getNumComponents();
/* 284 */       this.transferType = image.getRaster().getTransferType();
/* 285 */       this.bytesPerComponent = (this.transferType == 2 || this.transferType == 1) ? 2 : 1;
/*     */ 
/*     */ 
/*     */       
/* 289 */       this.bytesPerPixel = image.getColorModel().getNumColorComponents() * this.bytesPerComponent;
/*     */       
/* 291 */       this.height = image.getHeight();
/* 292 */       this.width = image.getWidth();
/* 293 */       this.imageType = image.getType();
/* 294 */       this
/* 295 */         .hasAlpha = (image.getColorModel().getNumComponents() != image.getColorModel().getNumColorComponents());
/* 296 */       this.alphaImageData = this.hasAlpha ? new byte[this.width * this.height * this.bytesPerComponent] : null;
/*     */ 
/*     */       
/* 299 */       int dataRowByteCount = this.width * this.bytesPerPixel + 1;
/* 300 */       this.dataRawRowNone = new byte[dataRowByteCount];
/* 301 */       this.dataRawRowSub = new byte[dataRowByteCount];
/* 302 */       this.dataRawRowUp = new byte[dataRowByteCount];
/* 303 */       this.dataRawRowAverage = new byte[dataRowByteCount];
/* 304 */       this.dataRawRowPaeth = new byte[dataRowByteCount];
/*     */ 
/*     */       
/* 307 */       this.dataRawRowNone[0] = 0;
/* 308 */       this.dataRawRowSub[0] = 1;
/* 309 */       this.dataRawRowUp[0] = 2;
/* 310 */       this.dataRawRowAverage[0] = 3;
/* 311 */       this.dataRawRowPaeth[0] = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 318 */       this.aValues = new byte[this.bytesPerPixel];
/* 319 */       this.cValues = new byte[this.bytesPerPixel];
/* 320 */       this.bValues = new byte[this.bytesPerPixel];
/* 321 */       this.xValues = new byte[this.bytesPerPixel];
/* 322 */       this.tmpResultValues = new byte[this.bytesPerPixel];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     PDImageXObject encode() throws IOException {
/*     */       int elementsInRowPerPixel;
/*     */       Object prevRow, transferRow;
/* 333 */       Raster imageRaster = this.image.getRaster();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 341 */       switch (this.imageType) {
/*     */ 
/*     */         
/*     */         case 0:
/* 345 */           switch (imageRaster.getTransferType()) {
/*     */             
/*     */             case 1:
/* 348 */               elementsInRowPerPixel = this.componentsPerPixel;
/* 349 */               prevRow = new short[this.width * elementsInRowPerPixel];
/* 350 */               transferRow = new short[this.width * elementsInRowPerPixel];
/*     */               break;
/*     */             case 0:
/* 353 */               elementsInRowPerPixel = this.componentsPerPixel;
/* 354 */               prevRow = new byte[this.width * elementsInRowPerPixel];
/* 355 */               transferRow = new byte[this.width * elementsInRowPerPixel];
/*     */               break;
/*     */           } 
/* 358 */           return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 5:
/*     */         case 6:
/* 366 */           elementsInRowPerPixel = this.componentsPerPixel;
/* 367 */           prevRow = new byte[this.width * elementsInRowPerPixel];
/* 368 */           transferRow = new byte[this.width * elementsInRowPerPixel];
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/*     */         case 2:
/*     */         case 4:
/* 376 */           elementsInRowPerPixel = 1;
/* 377 */           prevRow = new int[this.width * elementsInRowPerPixel];
/* 378 */           transferRow = new int[this.width * elementsInRowPerPixel];
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 384 */           return null;
/*     */       } 
/*     */       
/* 387 */       int elementsInTransferRow = this.width * elementsInRowPerPixel;
/*     */ 
/*     */       
/* 390 */       ByteArrayOutputStream stream = new ByteArrayOutputStream(this.height * this.width * this.bytesPerPixel / 2);
/*     */       
/* 392 */       Deflater deflater = new Deflater(Filter.getCompressionLevel());
/* 393 */       DeflaterOutputStream zip = new DeflaterOutputStream(stream, deflater);
/*     */       
/* 395 */       int alphaPtr = 0;
/*     */       
/* 397 */       for (int rowNum = 0; rowNum < this.height; rowNum++) {
/*     */         byte[] transferRowByte, prevRowByte; int[] transferRowInt, prevRowInt; short[] transferRowShort, prevRowShort;
/* 399 */         imageRaster.getDataElements(0, rowNum, this.width, 1, transferRow);
/*     */ 
/*     */         
/* 402 */         int writerPtr = 1;
/* 403 */         Arrays.fill(this.aValues, (byte)0);
/* 404 */         Arrays.fill(this.cValues, (byte)0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 413 */         if (transferRow instanceof byte[]) {
/*     */           
/* 415 */           transferRowByte = (byte[])transferRow;
/* 416 */           prevRowByte = (byte[])prevRow;
/* 417 */           transferRowInt = prevRowInt = null;
/* 418 */           transferRowShort = prevRowShort = null;
/*     */         }
/* 420 */         else if (transferRow instanceof int[]) {
/*     */           
/* 422 */           transferRowInt = (int[])transferRow;
/* 423 */           prevRowInt = (int[])prevRow;
/* 424 */           transferRowShort = prevRowShort = null;
/* 425 */           transferRowByte = prevRowByte = null;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 430 */           transferRowShort = (short[])transferRow;
/* 431 */           prevRowShort = (short[])prevRow;
/* 432 */           transferRowInt = prevRowInt = null;
/* 433 */           transferRowByte = prevRowByte = null;
/*     */         } 
/*     */         
/* 436 */         for (int indexInTransferRow = 0; indexInTransferRow < elementsInTransferRow; 
/* 437 */           indexInTransferRow += elementsInRowPerPixel, alphaPtr += this.bytesPerComponent) {
/*     */ 
/*     */           
/* 440 */           if (transferRowByte != null) {
/*     */             
/* 442 */             copyImageBytes(transferRowByte, indexInTransferRow, this.xValues, this.alphaImageData, alphaPtr);
/*     */             
/* 444 */             copyImageBytes(prevRowByte, indexInTransferRow, this.bValues, null, 0);
/*     */           }
/* 446 */           else if (transferRowInt != null) {
/*     */             
/* 448 */             copyIntToBytes(transferRowInt, indexInTransferRow, this.xValues, this.alphaImageData, alphaPtr);
/*     */             
/* 450 */             copyIntToBytes(prevRowInt, indexInTransferRow, this.bValues, null, 0);
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 455 */             copyShortsToBytes(transferRowShort, indexInTransferRow, this.xValues, this.alphaImageData, alphaPtr);
/* 456 */             copyShortsToBytes(prevRowShort, indexInTransferRow, this.bValues, null, 0);
/*     */           } 
/*     */ 
/*     */           
/* 460 */           int length = this.xValues.length;
/* 461 */           for (int bytePtr = 0; bytePtr < length; bytePtr++) {
/*     */             
/* 463 */             int x = this.xValues[bytePtr] & 0xFF;
/* 464 */             int a = this.aValues[bytePtr] & 0xFF;
/* 465 */             int b = this.bValues[bytePtr] & 0xFF;
/* 466 */             int c = this.cValues[bytePtr] & 0xFF;
/* 467 */             this.dataRawRowNone[writerPtr] = (byte)x;
/* 468 */             this.dataRawRowSub[writerPtr] = pngFilterSub(x, a);
/* 469 */             this.dataRawRowUp[writerPtr] = pngFilterUp(x, b);
/* 470 */             this.dataRawRowAverage[writerPtr] = pngFilterAverage(x, a, b);
/* 471 */             this.dataRawRowPaeth[writerPtr] = pngFilterPaeth(x, a, b, c);
/* 472 */             writerPtr++;
/*     */           } 
/*     */ 
/*     */           
/* 476 */           System.arraycopy(this.xValues, 0, this.aValues, 0, this.bytesPerPixel);
/* 477 */           System.arraycopy(this.bValues, 0, this.cValues, 0, this.bytesPerPixel);
/*     */         } 
/*     */         
/* 480 */         byte[] rowToWrite = chooseDataRowToWrite();
/*     */ 
/*     */         
/* 483 */         zip.write(rowToWrite, 0, rowToWrite.length);
/*     */ 
/*     */         
/* 486 */         Object temp = prevRow;
/* 487 */         prevRow = transferRow;
/* 488 */         transferRow = temp;
/*     */       } 
/* 490 */       zip.close();
/* 491 */       deflater.end();
/*     */       
/* 493 */       return preparePredictorPDImage(stream, this.bytesPerComponent * 8);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void copyIntToBytes(int[] transferRow, int indexInTranferRow, byte[] targetValues, byte[] alphaImageData, int alphaPtr) {
/* 499 */       int val = transferRow[indexInTranferRow];
/* 500 */       byte b0 = (byte)(val & 0xFF);
/* 501 */       byte b1 = (byte)(val >> 8 & 0xFF);
/* 502 */       byte b2 = (byte)(val >> 16 & 0xFF);
/*     */       
/* 504 */       switch (this.imageType) {
/*     */         
/*     */         case 4:
/* 507 */           targetValues[0] = b0;
/* 508 */           targetValues[1] = b1;
/* 509 */           targetValues[2] = b2;
/*     */           break;
/*     */         case 2:
/* 512 */           targetValues[0] = b2;
/* 513 */           targetValues[1] = b1;
/* 514 */           targetValues[2] = b0;
/* 515 */           if (alphaImageData != null) {
/*     */             
/* 517 */             byte b3 = (byte)(val >> 24 & 0xFF);
/* 518 */             alphaImageData[alphaPtr] = b3;
/*     */           } 
/*     */           break;
/*     */         case 1:
/* 522 */           targetValues[0] = b2;
/* 523 */           targetValues[1] = b1;
/* 524 */           targetValues[2] = b0;
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void copyImageBytes(byte[] transferRow, int indexInTranferRow, byte[] targetValues, byte[] alphaImageData, int alphaPtr) {
/* 532 */       System.arraycopy(transferRow, indexInTranferRow, targetValues, 0, targetValues.length);
/* 533 */       if (alphaImageData != null)
/*     */       {
/* 535 */         alphaImageData[alphaPtr] = transferRow[indexInTranferRow + targetValues.length];
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private static void copyShortsToBytes(short[] transferRow, int indexInTranferRow, byte[] targetValues, byte[] alphaImageData, int alphaPtr) {
/* 542 */       int itr = indexInTranferRow;
/* 543 */       for (int i = 0; i < targetValues.length; i += 2) {
/*     */         
/* 545 */         short val = transferRow[itr++];
/* 546 */         targetValues[i] = (byte)(val >> 8 & 0xFF);
/* 547 */         targetValues[i + 1] = (byte)(val & 0xFF);
/*     */       } 
/* 549 */       if (alphaImageData != null) {
/*     */         
/* 551 */         short alpha = transferRow[itr];
/* 552 */         alphaImageData[alphaPtr] = (byte)(alpha >> 8 & 0xFF);
/* 553 */         alphaImageData[alphaPtr + 1] = (byte)(alpha & 0xFF);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private PDImageXObject preparePredictorPDImage(ByteArrayOutputStream stream, int bitsPerComponent) throws IOException {
/*     */       PDICCBased pDICCBased;
/* 560 */       int h = this.image.getHeight();
/* 561 */       int w = this.image.getWidth();
/*     */       
/* 563 */       ColorSpace srcCspace = this.image.getColorModel().getColorSpace();
/* 564 */       PDColorSpace pdColorSpace = (srcCspace.getType() != 9) ? (PDColorSpace)PDDeviceRGB.INSTANCE : (PDColorSpace)PDDeviceCMYK.INSTANCE;
/*     */ 
/*     */ 
/*     */       
/* 568 */       if (srcCspace instanceof ICC_ColorSpace) {
/*     */         
/* 570 */         ICC_Profile profile = ((ICC_ColorSpace)srcCspace).getProfile();
/*     */         
/* 572 */         if (profile != ICC_Profile.getInstance(1000)) {
/*     */           
/* 574 */           PDICCBased pdProfile = new PDICCBased(this.document);
/*     */           
/* 576 */           OutputStream outputStream = pdProfile.getPDStream().createOutputStream(COSName.FLATE_DECODE);
/* 577 */           outputStream.write(profile.getData());
/* 578 */           outputStream.close();
/* 579 */           pdProfile.getPDStream().getCOSObject().setInt(COSName.N, srcCspace
/* 580 */               .getNumComponents());
/* 581 */           pdProfile.getPDStream().getCOSObject().setItem(COSName.ALTERNATE, 
/* 582 */               (srcCspace.getType() == 9) ? (COSBase)COSName.DEVICECMYK : (COSBase)COSName.DEVICERGB);
/*     */ 
/*     */           
/* 585 */           pDICCBased = pdProfile;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 590 */       PDImageXObject imageXObject = new PDImageXObject(this.document, new ByteArrayInputStream(stream.toByteArray()), (COSBase)COSName.FLATE_DECODE, w, h, bitsPerComponent, (PDColorSpace)pDICCBased);
/*     */ 
/*     */       
/* 593 */       COSDictionary decodeParms = new COSDictionary();
/* 594 */       decodeParms.setItem(COSName.BITS_PER_COMPONENT, (COSBase)COSInteger.get(bitsPerComponent));
/* 595 */       decodeParms.setItem(COSName.PREDICTOR, (COSBase)COSInteger.get(15L));
/* 596 */       decodeParms.setItem(COSName.COLUMNS, (COSBase)COSInteger.get(w));
/* 597 */       decodeParms.setItem(COSName.COLORS, (COSBase)COSInteger.get(srcCspace.getNumComponents()));
/* 598 */       imageXObject.getCOSObject().setItem(COSName.DECODE_PARMS, (COSBase)decodeParms);
/*     */       
/* 600 */       if (this.image.getTransparency() != 1) {
/*     */         
/* 602 */         PDImageXObject pdMask = LosslessFactory.prepareImageXObject(this.document, this.alphaImageData, this.image
/* 603 */             .getWidth(), this.image.getHeight(), 8 * this.bytesPerComponent, (PDColorSpace)PDDeviceGray.INSTANCE);
/* 604 */         imageXObject.getCOSObject().setItem(COSName.SMASK, pdMask);
/*     */       } 
/* 606 */       return imageXObject;
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
/*     */     
/*     */     private byte[] chooseDataRowToWrite() {
/* 620 */       byte[] rowToWrite = this.dataRawRowNone;
/* 621 */       long estCompressSum = estCompressSum(this.dataRawRowNone);
/* 622 */       long estCompressSumSub = estCompressSum(this.dataRawRowSub);
/* 623 */       long estCompressSumUp = estCompressSum(this.dataRawRowUp);
/* 624 */       long estCompressSumAvg = estCompressSum(this.dataRawRowAverage);
/* 625 */       long estCompressSumPaeth = estCompressSum(this.dataRawRowPaeth);
/* 626 */       if (estCompressSum > estCompressSumSub) {
/*     */         
/* 628 */         rowToWrite = this.dataRawRowSub;
/* 629 */         estCompressSum = estCompressSumSub;
/*     */       } 
/* 631 */       if (estCompressSum > estCompressSumUp) {
/*     */         
/* 633 */         rowToWrite = this.dataRawRowUp;
/* 634 */         estCompressSum = estCompressSumUp;
/*     */       } 
/* 636 */       if (estCompressSum > estCompressSumAvg) {
/*     */         
/* 638 */         rowToWrite = this.dataRawRowAverage;
/* 639 */         estCompressSum = estCompressSumAvg;
/*     */       } 
/* 641 */       if (estCompressSum > estCompressSumPaeth)
/*     */       {
/* 643 */         rowToWrite = this.dataRawRowPaeth;
/*     */       }
/* 645 */       return rowToWrite;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static byte pngFilterSub(int x, int a) {
/* 653 */       return (byte)((x & 0xFF) - (a & 0xFF));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private static byte pngFilterUp(int x, int b) {
/* 659 */       return pngFilterSub(x, b);
/*     */     }
/*     */ 
/*     */     
/*     */     private static byte pngFilterAverage(int x, int a, int b) {
/* 664 */       return (byte)(x - (b + a) / 2);
/*     */     }
/*     */ 
/*     */     
/*     */     private static byte pngFilterPaeth(int x, int a, int b, int c) {
/* 669 */       int pr, p = a + b - c;
/* 670 */       int pa = Math.abs(p - a);
/* 671 */       int pb = Math.abs(p - b);
/* 672 */       int pc = Math.abs(p - c);
/*     */       
/* 674 */       if (pa <= pb && pa <= pc) {
/*     */         
/* 676 */         pr = a;
/*     */       }
/* 678 */       else if (pb <= pc) {
/*     */         
/* 680 */         pr = b;
/*     */       }
/*     */       else {
/*     */         
/* 684 */         pr = c;
/*     */       } 
/*     */       
/* 687 */       int r = x - pr;
/* 688 */       return (byte)r;
/*     */     }
/*     */ 
/*     */     
/*     */     private static long estCompressSum(byte[] dataRawRowSub) {
/* 693 */       long sum = 0L;
/* 694 */       for (byte aDataRawRowSub : dataRawRowSub)
/*     */       {
/*     */         
/* 697 */         sum += Math.abs(aDataRawRowSub);
/*     */       }
/* 699 */       return sum;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/LosslessFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */