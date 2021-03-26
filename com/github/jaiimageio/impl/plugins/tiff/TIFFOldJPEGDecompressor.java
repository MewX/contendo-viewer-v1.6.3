/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFOldJPEGDecompressor
/*     */   extends TIFFJPEGDecompressor
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   private static final int DHT = 196;
/*     */   private static final int DQT = 219;
/*     */   private static final int DRI = 221;
/*     */   private static final int SOF0 = 192;
/*     */   private static final int SOS = 218;
/*     */   private boolean isInitialized = false;
/* 103 */   private Long JPEGStreamOffset = null;
/*     */   
/* 105 */   private int SOFPosition = -1;
/*     */   
/* 107 */   private byte[] SOSMarker = null;
/*     */ 
/*     */   
/* 110 */   private int subsamplingX = 2;
/*     */ 
/*     */   
/* 113 */   private int subsamplingY = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void initialize() throws IOException {
/* 190 */     if (this.isInitialized) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 195 */     TIFFImageMetadata tim = (TIFFImageMetadata)this.metadata;
/*     */ 
/*     */ 
/*     */     
/* 199 */     TIFFField JPEGInterchangeFormatField = tim.getTIFFField(513);
/*     */ 
/*     */ 
/*     */     
/* 203 */     TIFFField segmentOffsetField = tim.getTIFFField(324);
/* 204 */     if (segmentOffsetField == null) {
/*     */       
/* 206 */       segmentOffsetField = tim.getTIFFField(273);
/* 207 */       if (segmentOffsetField == null) {
/* 208 */         segmentOffsetField = JPEGInterchangeFormatField;
/*     */       }
/*     */     } 
/* 211 */     long[] segmentOffsets = segmentOffsetField.getAsLongs();
/*     */ 
/*     */     
/* 214 */     boolean isTiled = (segmentOffsets.length > 1);
/*     */     
/* 216 */     if (!isTiled) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 224 */       this.stream.seek(this.offset);
/* 225 */       this.stream.mark();
/* 226 */       if (this.stream.read() == 255 && this.stream.read() == 216) {
/*     */         
/* 228 */         this.JPEGStreamOffset = new Long(this.offset);
/*     */ 
/*     */ 
/*     */         
/* 232 */         ((TIFFImageReader)this.reader).forwardWarningMessage("SOI marker detected at start of strip or tile.");
/* 233 */         this.isInitialized = true;
/* 234 */         this.stream.reset();
/*     */         return;
/*     */       } 
/* 237 */       this.stream.reset();
/*     */       
/* 239 */       if (JPEGInterchangeFormatField != null) {
/*     */ 
/*     */         
/* 242 */         long jpegInterchangeOffset = JPEGInterchangeFormatField.getAsLong(0);
/*     */ 
/*     */         
/* 245 */         this.stream.mark();
/* 246 */         this.stream.seek(jpegInterchangeOffset);
/* 247 */         if (this.stream.read() == 255 && this.stream.read() == 216) {
/*     */           
/* 249 */           this.JPEGStreamOffset = new Long(jpegInterchangeOffset);
/*     */         } else {
/* 251 */           ((TIFFImageReader)this.reader).forwardWarningMessage("JPEGInterchangeFormat does not point to SOI");
/* 252 */         }  this.stream.reset();
/*     */ 
/*     */ 
/*     */         
/* 256 */         TIFFField JPEGInterchangeFormatLengthField = tim.getTIFFField(514);
/*     */         
/* 258 */         if (JPEGInterchangeFormatLengthField == null) {
/*     */           
/* 260 */           ((TIFFImageReader)this.reader).forwardWarningMessage("JPEGInterchangeFormatLength field is missing");
/*     */         }
/*     */         else {
/*     */           
/* 264 */           long jpegInterchangeLength = JPEGInterchangeFormatLengthField.getAsLong(0);
/*     */           
/* 266 */           if (jpegInterchangeOffset >= segmentOffsets[0] || jpegInterchangeOffset + jpegInterchangeLength <= segmentOffsets[0])
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 272 */             ((TIFFImageReader)this.reader).forwardWarningMessage("JPEGInterchangeFormatLength field value is invalid");
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 277 */         if (this.JPEGStreamOffset != null) {
/* 278 */           this.isInitialized = true;
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 286 */     TIFFField YCbCrSubsamplingField = tim.getTIFFField(530);
/* 287 */     if (YCbCrSubsamplingField != null) {
/* 288 */       this.subsamplingX = YCbCrSubsamplingField.getAsChars()[0];
/* 289 */       this.subsamplingY = YCbCrSubsamplingField.getAsChars()[1];
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     if (JPEGInterchangeFormatField != null) {
/*     */ 
/*     */       
/* 299 */       long jpegInterchangeOffset = JPEGInterchangeFormatField.getAsLong(0);
/*     */ 
/*     */ 
/*     */       
/* 303 */       TIFFField JPEGInterchangeFormatLengthField = tim.getTIFFField(514);
/*     */       
/* 305 */       if (JPEGInterchangeFormatLengthField != null) {
/*     */ 
/*     */         
/* 308 */         long jpegInterchangeLength = JPEGInterchangeFormatLengthField.getAsLong(0);
/*     */         
/* 310 */         if (jpegInterchangeLength >= 2L && jpegInterchangeOffset + jpegInterchangeLength <= segmentOffsets[0]) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 315 */           this.stream.mark();
/* 316 */           this.stream.seek(jpegInterchangeOffset + jpegInterchangeLength - 2L);
/* 317 */           if (this.stream.read() == 255 && this.stream.read() == 217) {
/* 318 */             this.tables = new byte[(int)(jpegInterchangeLength - 2L)];
/*     */           } else {
/* 320 */             this.tables = new byte[(int)jpegInterchangeLength];
/*     */           } 
/* 322 */           this.stream.reset();
/*     */ 
/*     */           
/* 325 */           this.stream.mark();
/* 326 */           this.stream.seek(jpegInterchangeOffset);
/* 327 */           this.stream.readFully(this.tables);
/* 328 */           this.stream.reset();
/*     */ 
/*     */           
/* 331 */           ((TIFFImageReader)this.reader).forwardWarningMessage("Incorrect JPEG interchange format: using JPEGInterchangeFormat offset to derive tables.");
/*     */         } else {
/* 333 */           ((TIFFImageReader)this.reader).forwardWarningMessage("JPEGInterchangeFormat+JPEGInterchangeFormatLength > offset to first strip or tile.");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 338 */     if (this.tables == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 344 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */ 
/*     */ 
/*     */       
/* 348 */       long streamLength = this.stream.length();
/*     */ 
/*     */       
/* 351 */       baos.write(255);
/* 352 */       baos.write(216);
/*     */ 
/*     */ 
/*     */       
/* 356 */       TIFFField f = tim.getTIFFField(519);
/* 357 */       if (f == null) {
/* 358 */         throw new IIOException("JPEGQTables field missing!");
/*     */       }
/* 360 */       long[] off = f.getAsLongs();
/*     */       
/* 362 */       for (int i = 0; i < off.length; i++) {
/* 363 */         baos.write(255);
/* 364 */         baos.write(219);
/*     */         
/* 366 */         char markerLength = 'C';
/* 367 */         baos.write(markerLength >>> 8 & 0xFF);
/* 368 */         baos.write(markerLength & 0xFF);
/*     */         
/* 370 */         baos.write(i);
/*     */         
/* 372 */         byte[] qtable = new byte[64];
/* 373 */         if (streamLength != -1L && off[i] > streamLength) {
/* 374 */           throw new IIOException("JPEGQTables offset for index " + i + " is not in the stream!");
/*     */         }
/*     */         
/* 377 */         this.stream.seek(off[i]);
/* 378 */         this.stream.readFully(qtable);
/*     */         
/* 380 */         baos.write(qtable);
/*     */       } 
/*     */ 
/*     */       
/* 384 */       for (int k = 0; k < 2; k++) {
/* 385 */         int tableTagNumber = (k == 0) ? 520 : 521;
/*     */ 
/*     */         
/* 388 */         f = tim.getTIFFField(tableTagNumber);
/* 389 */         String fieldName = (tableTagNumber == 520) ? "JPEGDCTables" : "JPEGACTables";
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 394 */         if (f == null) {
/* 395 */           throw new IIOException(fieldName + " field missing!");
/*     */         }
/* 397 */         off = f.getAsLongs();
/*     */         
/* 399 */         for (int j = 0; j < off.length; j++) {
/* 400 */           baos.write(255);
/* 401 */           baos.write(196);
/*     */           
/* 403 */           byte[] blengths = new byte[16];
/* 404 */           if (streamLength != -1L && off[j] > streamLength) {
/* 405 */             throw new IIOException(fieldName + " offset for index " + j + " is not in the stream!");
/*     */           }
/*     */           
/* 408 */           this.stream.seek(off[j]);
/* 409 */           this.stream.readFully(blengths);
/* 410 */           int numCodes = 0;
/* 411 */           for (int m = 0; m < 16; m++) {
/* 412 */             numCodes += blengths[m] & 0xFF;
/*     */           }
/*     */           
/* 415 */           char markerLength = (char)(19 + numCodes);
/*     */           
/* 417 */           baos.write(markerLength >>> 8 & 0xFF);
/* 418 */           baos.write(markerLength & 0xFF);
/*     */           
/* 420 */           baos.write(j | k << 4);
/*     */           
/* 422 */           baos.write(blengths);
/*     */           
/* 424 */           byte[] bcodes = new byte[numCodes];
/* 425 */           this.stream.readFully(bcodes);
/* 426 */           baos.write(bcodes);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 431 */       baos.write(-1);
/* 432 */       baos.write(-64);
/* 433 */       short sval = (short)(8 + 3 * this.samplesPerPixel);
/* 434 */       baos.write((byte)(sval >>> 8 & 0xFF));
/* 435 */       baos.write((byte)(sval & 0xFF));
/* 436 */       baos.write(8);
/* 437 */       sval = (short)this.srcHeight;
/* 438 */       baos.write((byte)(sval >>> 8 & 0xFF));
/* 439 */       baos.write((byte)(sval & 0xFF));
/* 440 */       sval = (short)this.srcWidth;
/* 441 */       baos.write((byte)(sval >>> 8 & 0xFF));
/* 442 */       baos.write((byte)(sval & 0xFF));
/* 443 */       baos.write((byte)this.samplesPerPixel);
/* 444 */       if (this.samplesPerPixel == 1) {
/* 445 */         baos.write(1);
/* 446 */         baos.write(17);
/* 447 */         baos.write(0);
/*     */       } else {
/* 449 */         for (int j = 0; j < 3; j++) {
/* 450 */           baos.write((byte)(j + 1));
/* 451 */           baos.write((j != 0) ? 17 : (byte)((this.subsamplingX & 0xF) << 4 | this.subsamplingY & 0xF));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 456 */           baos.write((byte)j);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 462 */       f = tim.getTIFFField(515);
/* 463 */       if (f != null) {
/* 464 */         char restartInterval = f.getAsChars()[0];
/*     */         
/* 466 */         if (restartInterval != '\000') {
/* 467 */           baos.write(-1);
/* 468 */           baos.write(-35);
/*     */           
/* 470 */           sval = 4;
/* 471 */           baos.write((byte)(sval >>> 8 & 0xFF));
/* 472 */           baos.write((byte)(sval & 0xFF));
/*     */ 
/*     */           
/* 475 */           baos.write((byte)(restartInterval >>> 8 & 0xFF));
/* 476 */           baos.write((byte)(restartInterval & 0xFF));
/*     */         } 
/*     */       } 
/*     */       
/* 480 */       this.tables = baos.toByteArray();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 488 */     int idx = 0;
/* 489 */     int idxMax = this.tables.length - 1;
/* 490 */     while (idx < idxMax) {
/* 491 */       if ((this.tables[idx] & 0xFF) == 255 && (this.tables[idx + 1] & 0xFF) == 192) {
/*     */         
/* 493 */         this.SOFPosition = idx;
/*     */         break;
/*     */       } 
/* 496 */       idx++;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 502 */     if (this.SOFPosition == -1) {
/* 503 */       byte[] tmpTables = new byte[this.tables.length + 10 + 3 * this.samplesPerPixel];
/*     */       
/* 505 */       System.arraycopy(this.tables, 0, tmpTables, 0, this.tables.length);
/* 506 */       int tmpOffset = this.tables.length;
/* 507 */       this.SOFPosition = this.tables.length;
/* 508 */       this.tables = tmpTables;
/*     */       
/* 510 */       this.tables[tmpOffset++] = -1;
/* 511 */       this.tables[tmpOffset++] = -64;
/* 512 */       short sval = (short)(8 + 3 * this.samplesPerPixel);
/* 513 */       this.tables[tmpOffset++] = (byte)(sval >>> 8 & 0xFF);
/* 514 */       this.tables[tmpOffset++] = (byte)(sval & 0xFF);
/* 515 */       this.tables[tmpOffset++] = 8;
/* 516 */       sval = (short)this.srcHeight;
/* 517 */       this.tables[tmpOffset++] = (byte)(sval >>> 8 & 0xFF);
/* 518 */       this.tables[tmpOffset++] = (byte)(sval & 0xFF);
/* 519 */       sval = (short)this.srcWidth;
/* 520 */       this.tables[tmpOffset++] = (byte)(sval >>> 8 & 0xFF);
/* 521 */       this.tables[tmpOffset++] = (byte)(sval & 0xFF);
/* 522 */       this.tables[tmpOffset++] = (byte)this.samplesPerPixel;
/* 523 */       if (this.samplesPerPixel == 1) {
/* 524 */         this.tables[tmpOffset++] = 1;
/* 525 */         this.tables[tmpOffset++] = 17;
/* 526 */         this.tables[tmpOffset++] = 0;
/*     */       } else {
/* 528 */         for (int i = 0; i < 3; i++) {
/* 529 */           this.tables[tmpOffset++] = (byte)(i + 1);
/* 530 */           this.tables[tmpOffset++] = (i != 0) ? 17 : (byte)((this.subsamplingX & 0xF) << 4 | this.subsamplingY & 0xF);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 535 */           this.tables[tmpOffset++] = (byte)i;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 543 */     this.stream.mark();
/* 544 */     this.stream.seek(segmentOffsets[0]);
/* 545 */     if (this.stream.read() == 255 && this.stream.read() == 218) {
/*     */ 
/*     */ 
/*     */       
/* 549 */       int SOSLength = this.stream.read() << 8 | this.stream.read();
/* 550 */       this.SOSMarker = new byte[SOSLength + 2];
/* 551 */       this.SOSMarker[0] = -1;
/* 552 */       this.SOSMarker[1] = -38;
/* 553 */       this.SOSMarker[2] = (byte)((SOSLength & 0xFF00) >> 8);
/* 554 */       this.SOSMarker[3] = (byte)(SOSLength & 0xFF);
/* 555 */       this.stream.readFully(this.SOSMarker, 4, SOSLength - 2);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 560 */       this.SOSMarker = new byte[8 + 2 * this.samplesPerPixel];
/* 561 */       int SOSMarkerIndex = 0;
/* 562 */       this.SOSMarker[SOSMarkerIndex++] = -1;
/* 563 */       this.SOSMarker[SOSMarkerIndex++] = -38;
/* 564 */       short sval = (short)(6 + 2 * this.samplesPerPixel);
/* 565 */       this.SOSMarker[SOSMarkerIndex++] = (byte)(sval >>> 8 & 0xFF);
/* 566 */       this.SOSMarker[SOSMarkerIndex++] = (byte)(sval & 0xFF);
/*     */       
/* 568 */       this.SOSMarker[SOSMarkerIndex++] = (byte)this.samplesPerPixel;
/* 569 */       if (this.samplesPerPixel == 1) {
/* 570 */         this.SOSMarker[SOSMarkerIndex++] = 1;
/* 571 */         this.SOSMarker[SOSMarkerIndex++] = 0;
/*     */       } else {
/* 573 */         for (int i = 0; i < 3; i++) {
/* 574 */           this.SOSMarker[SOSMarkerIndex++] = (byte)(i + 1);
/*     */           
/* 576 */           this.SOSMarker[SOSMarkerIndex++] = (byte)(i << 4 | i);
/*     */         } 
/*     */       } 
/*     */       
/* 580 */       this.SOSMarker[SOSMarkerIndex++] = 0;
/* 581 */       this.SOSMarker[SOSMarkerIndex++] = 63;
/* 582 */       this.SOSMarker[SOSMarkerIndex++] = 0;
/*     */     } 
/* 584 */     this.stream.reset();
/*     */ 
/*     */     
/* 587 */     this.isInitialized = true;
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
/*     */   public void decodeRaw(byte[] b, int dstOffset, int bitsPerPixel, int scanlineStride) throws IOException {
/* 602 */     initialize();
/*     */     
/* 604 */     TIFFImageMetadata tim = (TIFFImageMetadata)this.metadata;
/*     */     
/* 606 */     if (this.JPEGStreamOffset != null) {
/* 607 */       this.stream.seek(this.JPEGStreamOffset.longValue());
/* 608 */       this.JPEGReader.setInput(this.stream, false, true);
/*     */     } else {
/*     */       
/* 611 */       int tableLength = this.tables.length;
/* 612 */       int bufLength = tableLength + this.SOSMarker.length + this.byteCount + 2;
/*     */       
/* 614 */       byte[] buf = new byte[bufLength];
/* 615 */       if (this.tables != null) {
/* 616 */         System.arraycopy(this.tables, 0, buf, 0, tableLength);
/*     */       }
/* 618 */       int bufOffset = tableLength;
/*     */ 
/*     */       
/* 621 */       short sval = (short)this.srcHeight;
/* 622 */       buf[this.SOFPosition + 5] = (byte)(sval >>> 8 & 0xFF);
/* 623 */       buf[this.SOFPosition + 6] = (byte)(sval & 0xFF);
/* 624 */       sval = (short)this.srcWidth;
/* 625 */       buf[this.SOFPosition + 7] = (byte)(sval >>> 8 & 0xFF);
/* 626 */       buf[this.SOFPosition + 8] = (byte)(sval & 0xFF);
/*     */ 
/*     */       
/* 629 */       this.stream.seek(this.offset);
/*     */ 
/*     */       
/* 632 */       byte[] twoBytes = new byte[2];
/* 633 */       this.stream.readFully(twoBytes);
/* 634 */       if ((twoBytes[0] & 0xFF) != 255 || (twoBytes[1] & 0xFF) != 218) {
/*     */ 
/*     */         
/* 637 */         System.arraycopy(this.SOSMarker, 0, buf, bufOffset, this.SOSMarker.length);
/*     */         
/* 639 */         bufOffset += this.SOSMarker.length;
/*     */       } 
/*     */ 
/*     */       
/* 643 */       buf[bufOffset++] = twoBytes[0];
/* 644 */       buf[bufOffset++] = twoBytes[1];
/* 645 */       this.stream.readFully(buf, bufOffset, this.byteCount - 2);
/* 646 */       bufOffset += this.byteCount - 2;
/*     */ 
/*     */       
/* 649 */       buf[bufOffset++] = -1;
/* 650 */       buf[bufOffset++] = -39;
/*     */       
/* 652 */       ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, bufOffset);
/*     */       
/* 654 */       ImageInputStream is = new MemoryCacheImageInputStream(bais);
/*     */       
/* 656 */       this.JPEGReader.setInput(is, true, true);
/*     */     } 
/*     */ 
/*     */     
/* 660 */     this.JPEGParam.setDestination(this.rawImage);
/* 661 */     this.JPEGReader.read(0, this.JPEGParam);
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 665 */     super.finalize();
/* 666 */     this.JPEGReader.dispose();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFOldJPEGDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */