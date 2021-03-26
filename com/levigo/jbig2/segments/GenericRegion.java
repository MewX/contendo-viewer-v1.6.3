/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.Region;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.decoder.arithmetic.ArithmeticDecoder;
/*     */ import com.levigo.jbig2.decoder.arithmetic.CX;
/*     */ import com.levigo.jbig2.decoder.mmr.MMRDecompressor;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.IOException;
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
/*     */ public class GenericRegion
/*     */   implements Region
/*     */ {
/*  40 */   private final Logger log = LoggerFactory.getLogger(GenericRegion.class);
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private long dataHeaderOffset;
/*     */   
/*     */   private long dataHeaderLength;
/*     */   
/*     */   private long dataOffset;
/*     */   
/*     */   private long dataLength;
/*     */   
/*     */   private RegionSegmentInformation regionInfo;
/*     */   
/*     */   private boolean useExtTemplates;
/*     */   
/*     */   private boolean isTPGDon;
/*     */   
/*     */   private byte gbTemplate;
/*     */   
/*     */   private boolean isMMREncoded;
/*     */   
/*     */   private short[] gbAtX;
/*     */   
/*     */   private short[] gbAtY;
/*     */   
/*     */   private boolean[] gbAtOverride;
/*     */   
/*     */   private boolean override;
/*     */   
/*     */   private Bitmap regionBitmap;
/*     */   
/*     */   private ArithmeticDecoder arithDecoder;
/*     */   private CX cx;
/*     */   private MMRDecompressor mmrDecompressor;
/*     */   
/*     */   public GenericRegion() {}
/*     */   
/*     */   public GenericRegion(SubInputStream paramSubInputStream) {
/*  79 */     this.subInputStream = paramSubInputStream;
/*  80 */     this.regionInfo = new RegionSegmentInformation(paramSubInputStream);
/*     */   }
/*     */   
/*     */   private void parseHeader() throws IOException, InvalidHeaderValueException {
/*  84 */     this.regionInfo.parseHeader();
/*     */ 
/*     */     
/*  87 */     this.subInputStream.readBits(3);
/*     */ 
/*     */     
/*  90 */     if (this.subInputStream.readBit() == 1) {
/*  91 */       this.useExtTemplates = true;
/*     */     }
/*     */ 
/*     */     
/*  95 */     if (this.subInputStream.readBit() == 1) {
/*  96 */       this.isTPGDon = true;
/*     */     }
/*     */ 
/*     */     
/* 100 */     this.gbTemplate = (byte)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 103 */     if (this.subInputStream.readBit() == 1) {
/* 104 */       this.isMMREncoded = true;
/*     */     }
/*     */     
/* 107 */     if (!this.isMMREncoded) {
/*     */       boolean bool;
/* 109 */       if (this.gbTemplate == 0) {
/* 110 */         if (this.useExtTemplates) {
/* 111 */           bool = true;
/*     */         } else {
/* 113 */           bool = true;
/*     */         } 
/*     */       } else {
/* 116 */         bool = true;
/*     */       } 
/*     */       
/* 119 */       readGbAtPixels(bool);
/*     */     } 
/*     */ 
/*     */     
/* 123 */     computeSegmentDataStructure();
/*     */     
/* 125 */     checkInput();
/*     */   }
/*     */   
/*     */   private void readGbAtPixels(int paramInt) throws IOException {
/* 129 */     this.gbAtX = new short[paramInt];
/* 130 */     this.gbAtY = new short[paramInt];
/*     */     
/* 132 */     for (byte b = 0; b < paramInt; b++) {
/* 133 */       this.gbAtX[b] = (short)this.subInputStream.readByte();
/* 134 */       this.gbAtY[b] = (short)this.subInputStream.readByte();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeSegmentDataStructure() throws IOException {
/* 139 */     this.dataOffset = this.subInputStream.getStreamPosition();
/* 140 */     this.dataHeaderLength = this.dataOffset - this.dataHeaderOffset;
/* 141 */     this.dataLength = this.subInputStream.length() - this.dataHeaderLength;
/*     */   }
/*     */   
/*     */   private void checkInput() throws InvalidHeaderValueException {
/* 145 */     if (this.isMMREncoded && 
/* 146 */       this.gbTemplate != 0) {
/* 147 */       this.log.info("gbTemplate should contain the value 0");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bitmap getRegionBitmap() throws IOException {
/* 158 */     if (null == this.regionBitmap)
/*     */     {
/* 160 */       if (this.isMMREncoded) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 165 */         if (null == this.mmrDecompressor) {
/* 166 */           this.mmrDecompressor = new MMRDecompressor(this.regionInfo.getBitmapWidth(), this.regionInfo.getBitmapHeight(), (ImageInputStream)new SubInputStream((ImageInputStream)this.subInputStream, this.dataOffset, this.dataLength));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 171 */         this.regionBitmap = this.mmrDecompressor.uncompress();
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 179 */         updateOverrideFlags();
/*     */ 
/*     */         
/* 182 */         int i = 0;
/*     */         
/* 184 */         if (this.arithDecoder == null) {
/* 185 */           this.arithDecoder = new ArithmeticDecoder((ImageInputStream)this.subInputStream);
/*     */         }
/* 187 */         if (this.cx == null) {
/* 188 */           this.cx = new CX(65536, 1);
/*     */         }
/*     */ 
/*     */         
/* 192 */         this.regionBitmap = new Bitmap(this.regionInfo.getBitmapWidth(), this.regionInfo.getBitmapHeight());
/*     */         
/* 194 */         int j = this.regionBitmap.getWidth() + 7 & 0xFFFFFFF8;
/*     */ 
/*     */         
/* 197 */         for (byte b = 0; b < this.regionBitmap.getHeight(); b++) {
/*     */ 
/*     */           
/* 200 */           if (this.isTPGDon) {
/* 201 */             i ^= decodeSLTP();
/*     */           }
/*     */ 
/*     */           
/* 205 */           if (i == 1) {
/* 206 */             if (b > 0) {
/* 207 */               copyLineAbove(b);
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 221 */             decodeLine(b, this.regionBitmap.getWidth(), this.regionBitmap.getRowStride(), j);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     return this.regionBitmap;
/*     */   }
/*     */   
/*     */   private int decodeSLTP() throws IOException {
/* 238 */     switch (this.gbTemplate) {
/*     */       case 0:
/* 240 */         this.cx.setIndex(39717);
/*     */         break;
/*     */       case 1:
/* 243 */         this.cx.setIndex(1941);
/*     */         break;
/*     */       case 2:
/* 246 */         this.cx.setIndex(229);
/*     */         break;
/*     */       case 3:
/* 249 */         this.cx.setIndex(405);
/*     */         break;
/*     */     } 
/* 252 */     return this.arithDecoder.decode(this.cx);
/*     */   }
/*     */ 
/*     */   
/*     */   private void decodeLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IOException {
/* 257 */     int i = this.regionBitmap.getByteIndex(0, paramInt1);
/* 258 */     int j = i - paramInt3;
/*     */     
/* 260 */     switch (this.gbTemplate) {
/*     */       case 0:
/* 262 */         if (!this.useExtTemplates) {
/* 263 */           decodeTemplate0a(paramInt1, paramInt2, paramInt3, paramInt4, i, j); break;
/*     */         } 
/* 265 */         decodeTemplate0b(paramInt1, paramInt2, paramInt3, paramInt4, i, j);
/*     */         break;
/*     */       
/*     */       case 1:
/* 269 */         decodeTemplate1(paramInt1, paramInt2, paramInt3, paramInt4, i, j);
/*     */         break;
/*     */       case 2:
/* 272 */         decodeTemplate2(paramInt1, paramInt2, paramInt3, paramInt4, i, j);
/*     */         break;
/*     */       case 3:
/* 275 */         decodeTemplate3(paramInt1, paramInt2, paramInt3, paramInt4, i, j);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyLineAbove(int paramInt) {
/* 287 */     int i = paramInt * this.regionBitmap.getRowStride();
/* 288 */     int j = i - this.regionBitmap.getRowStride();
/*     */     
/* 290 */     for (byte b = 0; b < this.regionBitmap.getRowStride(); b++)
/*     */     {
/* 292 */       this.regionBitmap.setByte(i++, this.regionBitmap.getByte(j++));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTemplate0a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 299 */     int j = 0;
/*     */     
/* 301 */     int k = 0;
/* 302 */     int m = 0;
/*     */     
/* 304 */     if (paramInt1 >= 1) {
/* 305 */       k = this.regionBitmap.getByteAsInteger(paramInt6);
/*     */     }
/*     */     
/* 308 */     if (paramInt1 >= 2) {
/* 309 */       m = this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3) << 6;
/*     */     }
/*     */     
/* 312 */     int i = k & 0xF0 | m & 0x3800;
/*     */     
/*     */     int n;
/* 315 */     for (n = 0; n < paramInt4; n = i1) {
/*     */       
/* 317 */       byte b = 0;
/* 318 */       int i1 = n + 8;
/* 319 */       byte b1 = (paramInt2 - n > 8) ? 8 : (paramInt2 - n);
/*     */       
/* 321 */       if (paramInt1 > 0) {
/* 322 */         k = k << 8 | ((i1 < paramInt2) ? this.regionBitmap.getByteAsInteger(paramInt6 + 1) : 0);
/*     */       }
/*     */       
/* 325 */       if (paramInt1 > 1) {
/* 326 */         m = m << 8 | ((i1 < paramInt2) ? (this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3 + 1) << 6) : 0);
/*     */       }
/*     */       
/* 329 */       for (byte b2 = 0; b2 < b1; b2++) {
/* 330 */         int i2 = 7 - b2;
/* 331 */         if (this.override) {
/* 332 */           j = overrideAtTemplate0a(i, n + b2, paramInt1, b, b2, i2);
/* 333 */           this.cx.setIndex(j);
/*     */         } else {
/* 335 */           this.cx.setIndex(i);
/*     */         } 
/*     */         
/* 338 */         int i3 = this.arithDecoder.decode(this.cx);
/*     */         
/* 340 */         b = (byte)(b | i3 << i2);
/*     */         
/* 342 */         i = (i & 0x7BF7) << 1 | i3 | k >> i2 & 0x10 | m >> i2 & 0x800;
/*     */       } 
/*     */       
/* 345 */       this.regionBitmap.setByte(paramInt5++, b);
/* 346 */       paramInt6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTemplate0b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 353 */     int j = 0;
/*     */     
/* 355 */     int k = 0;
/* 356 */     int m = 0;
/*     */     
/* 358 */     if (paramInt1 >= 1) {
/* 359 */       k = this.regionBitmap.getByteAsInteger(paramInt6);
/*     */     }
/*     */     
/* 362 */     if (paramInt1 >= 2) {
/* 363 */       m = this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3) << 6;
/*     */     }
/*     */     
/* 366 */     int i = k & 0xF0 | m & 0x3800;
/*     */     
/*     */     int n;
/* 369 */     for (n = 0; n < paramInt4; n = i1) {
/*     */       
/* 371 */       byte b = 0;
/* 372 */       int i1 = n + 8;
/* 373 */       byte b1 = (paramInt2 - n > 8) ? 8 : (paramInt2 - n);
/*     */       
/* 375 */       if (paramInt1 > 0) {
/* 376 */         k = k << 8 | ((i1 < paramInt2) ? this.regionBitmap.getByteAsInteger(paramInt6 + 1) : 0);
/*     */       }
/*     */       
/* 379 */       if (paramInt1 > 1) {
/* 380 */         m = m << 8 | ((i1 < paramInt2) ? (this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3 + 1) << 6) : 0);
/*     */       }
/*     */       
/* 383 */       for (byte b2 = 0; b2 < b1; b2++) {
/* 384 */         int i2 = 7 - b2;
/* 385 */         if (this.override) {
/* 386 */           j = overrideAtTemplate0b(i, n + b2, paramInt1, b, b2, i2);
/* 387 */           this.cx.setIndex(j);
/*     */         } else {
/* 389 */           this.cx.setIndex(i);
/*     */         } 
/*     */         
/* 392 */         int i3 = this.arithDecoder.decode(this.cx);
/*     */         
/* 394 */         b = (byte)(b | i3 << i2);
/*     */         
/* 396 */         i = (i & 0x7BF7) << 1 | i3 | k >> i2 & 0x10 | m >> i2 & 0x800;
/*     */       } 
/*     */       
/* 399 */       this.regionBitmap.setByte(paramInt5++, b);
/* 400 */       paramInt6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTemplate1(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 409 */     int j = 0;
/* 410 */     int k = 0;
/*     */     
/* 412 */     if (paramInt1 >= 1) {
/* 413 */       j = this.regionBitmap.getByteAsInteger(paramInt6);
/*     */     }
/*     */     
/* 416 */     if (paramInt1 >= 2) {
/* 417 */       k = this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3) << 5;
/*     */     }
/*     */     
/* 420 */     int i = j >> 1 & 0x1F8 | k >> 1 & 0x1E00;
/*     */     
/*     */     int m;
/* 423 */     for (m = 0; m < paramInt4; m = n) {
/*     */       
/* 425 */       byte b = 0;
/* 426 */       int n = m + 8;
/* 427 */       byte b1 = (paramInt2 - m > 8) ? 8 : (paramInt2 - m);
/*     */       
/* 429 */       if (paramInt1 >= 1) {
/* 430 */         j = j << 8 | ((n < paramInt2) ? this.regionBitmap.getByteAsInteger(paramInt6 + 1) : 0);
/*     */       }
/*     */       
/* 433 */       if (paramInt1 >= 2) {
/* 434 */         k = k << 8 | ((n < paramInt2) ? (this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3 + 1) << 5) : 0);
/*     */       }
/*     */       
/* 437 */       for (byte b2 = 0; b2 < b1; b2++) {
/* 438 */         if (this.override) {
/* 439 */           int i3 = overrideAtTemplate1(i, m + b2, paramInt1, b, b2);
/* 440 */           this.cx.setIndex(i3);
/*     */         } else {
/* 442 */           this.cx.setIndex(i);
/*     */         } 
/*     */         
/* 445 */         int i1 = this.arithDecoder.decode(this.cx);
/*     */         
/* 447 */         b = (byte)(b | i1 << 7 - b2);
/*     */         
/* 449 */         int i2 = 8 - b2;
/* 450 */         i = (i & 0xEFB) << 1 | i1 | j >> i2 & 0x8 | k >> i2 & 0x200;
/*     */       } 
/*     */       
/* 453 */       this.regionBitmap.setByte(paramInt5++, b);
/* 454 */       paramInt6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTemplate2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 463 */     int j = 0;
/* 464 */     int k = 0;
/*     */     
/* 466 */     if (paramInt1 >= 1) {
/* 467 */       j = this.regionBitmap.getByteAsInteger(paramInt6);
/*     */     }
/*     */     
/* 470 */     if (paramInt1 >= 2) {
/* 471 */       k = this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3) << 4;
/*     */     }
/*     */     
/* 474 */     int i = j >> 3 & 0x7C | k >> 3 & 0x380;
/*     */     
/*     */     int m;
/* 477 */     for (m = 0; m < paramInt4; m = n) {
/*     */       
/* 479 */       byte b = 0;
/* 480 */       int n = m + 8;
/* 481 */       byte b1 = (paramInt2 - m > 8) ? 8 : (paramInt2 - m);
/*     */       
/* 483 */       if (paramInt1 >= 1) {
/* 484 */         j = j << 8 | ((n < paramInt2) ? this.regionBitmap.getByteAsInteger(paramInt6 + 1) : 0);
/*     */       }
/*     */       
/* 487 */       if (paramInt1 >= 2) {
/* 488 */         k = k << 8 | ((n < paramInt2) ? (this.regionBitmap.getByteAsInteger(paramInt6 - paramInt3 + 1) << 4) : 0);
/*     */       }
/*     */       
/* 491 */       for (byte b2 = 0; b2 < b1; b2++) {
/*     */         
/* 493 */         if (this.override) {
/* 494 */           int i3 = overrideAtTemplate2(i, m + b2, paramInt1, b, b2);
/* 495 */           this.cx.setIndex(i3);
/*     */         } else {
/* 497 */           this.cx.setIndex(i);
/*     */         } 
/*     */         
/* 500 */         int i1 = this.arithDecoder.decode(this.cx);
/*     */         
/* 502 */         b = (byte)(b | i1 << 7 - b2);
/*     */         
/* 504 */         int i2 = 10 - b2;
/* 505 */         i = (i & 0x1BD) << 1 | i1 | j >> i2 & 0x4 | k >> i2 & 0x80;
/*     */       } 
/*     */       
/* 508 */       this.regionBitmap.setByte(paramInt5++, b);
/* 509 */       paramInt6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTemplate3(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 518 */     int j = 0;
/*     */     
/* 520 */     if (paramInt1 >= 1) {
/* 521 */       j = this.regionBitmap.getByteAsInteger(paramInt6);
/*     */     }
/*     */     
/* 524 */     int i = j >> 1 & 0x70;
/*     */     
/*     */     int k;
/* 527 */     for (k = 0; k < paramInt4; k = m) {
/*     */       
/* 529 */       byte b = 0;
/* 530 */       int m = k + 8;
/* 531 */       byte b1 = (paramInt2 - k > 8) ? 8 : (paramInt2 - k);
/*     */       
/* 533 */       if (paramInt1 >= 1) {
/* 534 */         j = j << 8 | ((m < paramInt2) ? this.regionBitmap.getByteAsInteger(paramInt6 + 1) : 0);
/*     */       }
/*     */       
/* 537 */       for (byte b2 = 0; b2 < b1; b2++) {
/*     */         
/* 539 */         if (this.override) {
/* 540 */           int i1 = overrideAtTemplate3(i, k + b2, paramInt1, b, b2);
/* 541 */           this.cx.setIndex(i1);
/*     */         } else {
/* 543 */           this.cx.setIndex(i);
/*     */         } 
/*     */         
/* 546 */         int n = this.arithDecoder.decode(this.cx);
/*     */         
/* 548 */         b = (byte)(b | n << 7 - b2);
/* 549 */         i = (i & 0x1F7) << 1 | n | j >> 8 - b2 & 0x10;
/*     */       } 
/*     */       
/* 552 */       this.regionBitmap.setByte(paramInt5++, b);
/* 553 */       paramInt6++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateOverrideFlags() {
/* 558 */     if (this.gbAtX == null || this.gbAtY == null) {
/* 559 */       this.log.info("AT pixels not set");
/*     */       
/*     */       return;
/*     */     } 
/* 563 */     if (this.gbAtX.length != this.gbAtY.length) {
/* 564 */       this.log.info("AT pixel inconsistent, amount of x pixels: " + this.gbAtX.length + ", amount of y pixels:" + this.gbAtY.length);
/*     */       
/*     */       return;
/*     */     } 
/* 568 */     this.gbAtOverride = new boolean[this.gbAtX.length];
/*     */     
/* 570 */     switch (this.gbTemplate) {
/*     */       case 0:
/* 572 */         if (!this.useExtTemplates) {
/* 573 */           if (this.gbAtX[0] != 3 || this.gbAtY[0] != -1) {
/* 574 */             setOverrideFlag(0);
/*     */           }
/* 576 */           if (this.gbAtX[1] != -3 || this.gbAtY[1] != -1) {
/* 577 */             setOverrideFlag(1);
/*     */           }
/* 579 */           if (this.gbAtX[2] != 2 || this.gbAtY[2] != -2) {
/* 580 */             setOverrideFlag(2);
/*     */           }
/* 582 */           if (this.gbAtX[3] != -2 || this.gbAtY[3] != -2)
/* 583 */             setOverrideFlag(3); 
/*     */           break;
/*     */         } 
/* 586 */         if (this.gbAtX[0] != -2 || this.gbAtY[0] != 0) {
/* 587 */           setOverrideFlag(0);
/*     */         }
/* 589 */         if (this.gbAtX[1] != 0 || this.gbAtY[1] != -2) {
/* 590 */           setOverrideFlag(1);
/*     */         }
/* 592 */         if (this.gbAtX[2] != -2 || this.gbAtY[2] != -1) {
/* 593 */           setOverrideFlag(2);
/*     */         }
/* 595 */         if (this.gbAtX[3] != -1 || this.gbAtY[3] != -2) {
/* 596 */           setOverrideFlag(3);
/*     */         }
/* 598 */         if (this.gbAtX[4] != 1 || this.gbAtY[4] != -2) {
/* 599 */           setOverrideFlag(4);
/*     */         }
/* 601 */         if (this.gbAtX[5] != 2 || this.gbAtY[5] != -1) {
/* 602 */           setOverrideFlag(5);
/*     */         }
/* 604 */         if (this.gbAtX[6] != -3 || this.gbAtY[6] != 0) {
/* 605 */           setOverrideFlag(6);
/*     */         }
/* 607 */         if (this.gbAtX[7] != -4 || this.gbAtY[7] != 0) {
/* 608 */           setOverrideFlag(7);
/*     */         }
/* 610 */         if (this.gbAtX[8] != 2 || this.gbAtY[8] != -2) {
/* 611 */           setOverrideFlag(8);
/*     */         }
/* 613 */         if (this.gbAtX[9] != 3 || this.gbAtY[9] != -1) {
/* 614 */           setOverrideFlag(9);
/*     */         }
/* 616 */         if (this.gbAtX[10] != -2 || this.gbAtY[10] != -2) {
/* 617 */           setOverrideFlag(10);
/*     */         }
/* 619 */         if (this.gbAtX[11] != -3 || this.gbAtY[11] != -1) {
/* 620 */           setOverrideFlag(11);
/*     */         }
/*     */         break;
/*     */       case 1:
/* 624 */         if (this.gbAtX[0] != 3 || this.gbAtY[0] != -1)
/* 625 */           setOverrideFlag(0); 
/*     */         break;
/*     */       case 2:
/* 628 */         if (this.gbAtX[0] != 2 || this.gbAtY[0] != -1)
/* 629 */           setOverrideFlag(0); 
/*     */         break;
/*     */       case 3:
/* 632 */         if (this.gbAtX[0] != 2 || this.gbAtY[0] != -1) {
/* 633 */           setOverrideFlag(0);
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setOverrideFlag(int paramInt) {
/* 640 */     this.gbAtOverride[paramInt] = true;
/* 641 */     this.override = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private int overrideAtTemplate0a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 646 */     if (this.gbAtOverride[0]) {
/* 647 */       paramInt1 &= 0xFFEF;
/* 648 */       if (this.gbAtY[0] == 0 && this.gbAtX[0] >= -paramInt5) {
/* 649 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[0] & 0x1) << 4;
/*     */       } else {
/* 651 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[0], paramInt3 + this.gbAtY[0]) << 4;
/*     */       } 
/*     */     } 
/* 654 */     if (this.gbAtOverride[1]) {
/* 655 */       paramInt1 &= 0xFBFF;
/* 656 */       if (this.gbAtY[1] == 0 && this.gbAtX[1] >= -paramInt5) {
/* 657 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[1] & 0x1) << 10;
/*     */       } else {
/* 659 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[1], paramInt3 + this.gbAtY[1]) << 10;
/*     */       } 
/*     */     } 
/* 662 */     if (this.gbAtOverride[2]) {
/* 663 */       paramInt1 &= 0xF7FF;
/* 664 */       if (this.gbAtY[2] == 0 && this.gbAtX[2] >= -paramInt5) {
/* 665 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[2] & 0x1) << 11;
/*     */       } else {
/* 667 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[2], paramInt3 + this.gbAtY[2]) << 11;
/*     */       } 
/*     */     } 
/* 670 */     if (this.gbAtOverride[3]) {
/* 671 */       paramInt1 &= 0x7FFF;
/* 672 */       if (this.gbAtY[3] == 0 && this.gbAtX[3] >= -paramInt5) {
/* 673 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[3] & 0x1) << 15;
/*     */       } else {
/* 675 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[3], paramInt3 + this.gbAtY[3]) << 15;
/*     */       } 
/* 677 */     }  return paramInt1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int overrideAtTemplate0b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 682 */     if (this.gbAtOverride[0]) {
/* 683 */       paramInt1 &= 0xFFFD;
/* 684 */       if (this.gbAtY[0] == 0 && this.gbAtX[0] >= -paramInt5) {
/* 685 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[0] & 0x1) << 1;
/*     */       } else {
/* 687 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[0], paramInt3 + this.gbAtY[0]) << 1;
/*     */       } 
/*     */     } 
/* 690 */     if (this.gbAtOverride[1]) {
/* 691 */       paramInt1 &= 0xDFFF;
/* 692 */       if (this.gbAtY[1] == 0 && this.gbAtX[1] >= -paramInt5) {
/* 693 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[1] & 0x1) << 13;
/*     */       } else {
/* 695 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[1], paramInt3 + this.gbAtY[1]) << 13;
/*     */       } 
/* 697 */     }  if (this.gbAtOverride[2]) {
/* 698 */       paramInt1 &= 0xFDFF;
/* 699 */       if (this.gbAtY[2] == 0 && this.gbAtX[2] >= -paramInt5) {
/* 700 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[2] & 0x1) << 9;
/*     */       } else {
/* 702 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[2], paramInt3 + this.gbAtY[2]) << 9;
/*     */       } 
/* 704 */     }  if (this.gbAtOverride[3]) {
/* 705 */       paramInt1 &= 0xBFFF;
/* 706 */       if (this.gbAtY[3] == 0 && this.gbAtX[3] >= -paramInt5) {
/* 707 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[3] & 0x1) << 14;
/*     */       } else {
/* 709 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[3], paramInt3 + this.gbAtY[3]) << 14;
/*     */       } 
/* 711 */     }  if (this.gbAtOverride[4]) {
/* 712 */       paramInt1 &= 0xEFFF;
/* 713 */       if (this.gbAtY[4] == 0 && this.gbAtX[4] >= -paramInt5) {
/* 714 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[4] & 0x1) << 12;
/*     */       } else {
/* 716 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[4], paramInt3 + this.gbAtY[4]) << 12;
/*     */       } 
/* 718 */     }  if (this.gbAtOverride[5]) {
/* 719 */       paramInt1 &= 0xFFDF;
/* 720 */       if (this.gbAtY[5] == 0 && this.gbAtX[5] >= -paramInt5) {
/* 721 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[5] & 0x1) << 5;
/*     */       } else {
/* 723 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[5], paramInt3 + this.gbAtY[5]) << 5;
/*     */       } 
/* 725 */     }  if (this.gbAtOverride[6]) {
/* 726 */       paramInt1 &= 0xFFFB;
/* 727 */       if (this.gbAtY[6] == 0 && this.gbAtX[6] >= -paramInt5) {
/* 728 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[6] & 0x1) << 2;
/*     */       } else {
/* 730 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[6], paramInt3 + this.gbAtY[6]) << 2;
/*     */       } 
/* 732 */     }  if (this.gbAtOverride[7]) {
/* 733 */       paramInt1 &= 0xFFF7;
/* 734 */       if (this.gbAtY[7] == 0 && this.gbAtX[7] >= -paramInt5) {
/* 735 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[7] & 0x1) << 3;
/*     */       } else {
/* 737 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[7], paramInt3 + this.gbAtY[7]) << 3;
/*     */       } 
/* 739 */     }  if (this.gbAtOverride[8]) {
/* 740 */       paramInt1 &= 0xF7FF;
/* 741 */       if (this.gbAtY[8] == 0 && this.gbAtX[8] >= -paramInt5) {
/* 742 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[8] & 0x1) << 11;
/*     */       } else {
/* 744 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[8], paramInt3 + this.gbAtY[8]) << 11;
/*     */       } 
/* 746 */     }  if (this.gbAtOverride[9]) {
/* 747 */       paramInt1 &= 0xFFEF;
/* 748 */       if (this.gbAtY[9] == 0 && this.gbAtX[9] >= -paramInt5) {
/* 749 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[9] & 0x1) << 4;
/*     */       } else {
/* 751 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[9], paramInt3 + this.gbAtY[9]) << 4;
/*     */       } 
/* 753 */     }  if (this.gbAtOverride[10]) {
/* 754 */       paramInt1 &= 0x7FFF;
/* 755 */       if (this.gbAtY[10] == 0 && this.gbAtX[10] >= -paramInt5) {
/* 756 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[10] & 0x1) << 15;
/*     */       } else {
/* 758 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[10], paramInt3 + this.gbAtY[10]) << 15;
/*     */       } 
/* 760 */     }  if (this.gbAtOverride[11]) {
/* 761 */       paramInt1 &= 0xFDFF;
/* 762 */       if (this.gbAtY[11] == 0 && this.gbAtX[11] >= -paramInt5) {
/* 763 */         paramInt1 |= (paramInt4 >> paramInt6 - this.gbAtX[11] & 0x1) << 10;
/*     */       } else {
/* 765 */         paramInt1 |= getPixel(paramInt2 + this.gbAtX[11], paramInt3 + this.gbAtY[11]) << 10;
/*     */       } 
/*     */     } 
/* 768 */     return paramInt1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int overrideAtTemplate1(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws IOException {
/* 773 */     paramInt1 &= 0x1FF7;
/* 774 */     if (this.gbAtY[0] == 0 && this.gbAtX[0] >= -paramInt5) {
/* 775 */       return paramInt1 | (paramInt4 >> 7 - paramInt5 + this.gbAtX[0] & 0x1) << 3;
/*     */     }
/* 777 */     return paramInt1 | getPixel(paramInt2 + this.gbAtX[0], paramInt3 + this.gbAtY[0]) << 3;
/*     */   }
/*     */ 
/*     */   
/*     */   private int overrideAtTemplate2(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws IOException {
/* 782 */     paramInt1 &= 0x3FB;
/* 783 */     if (this.gbAtY[0] == 0 && this.gbAtX[0] >= -paramInt5) {
/* 784 */       return paramInt1 | (paramInt4 >> 7 - paramInt5 + this.gbAtX[0] & 0x1) << 2;
/*     */     }
/* 786 */     return paramInt1 | getPixel(paramInt2 + this.gbAtX[0], paramInt3 + this.gbAtY[0]) << 2;
/*     */   }
/*     */ 
/*     */   
/*     */   private int overrideAtTemplate3(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws IOException {
/* 791 */     paramInt1 &= 0x3EF;
/* 792 */     if (this.gbAtY[0] == 0 && this.gbAtX[0] >= -paramInt5) {
/* 793 */       return paramInt1 | (paramInt4 >> 7 - paramInt5 + this.gbAtX[0] & 0x1) << 4;
/*     */     }
/* 795 */     return paramInt1 | getPixel(paramInt2 + this.gbAtX[0], paramInt3 + this.gbAtY[0]) << 4;
/*     */   }
/*     */   
/*     */   private byte getPixel(int paramInt1, int paramInt2) throws IOException {
/* 799 */     if (paramInt1 < 0 || paramInt1 >= this.regionBitmap.getWidth()) {
/* 800 */       return 0;
/*     */     }
/* 802 */     if (paramInt2 < 0 || paramInt2 >= this.regionBitmap.getHeight()) {
/* 803 */       return 0;
/*     */     }
/* 805 */     return this.regionBitmap.getPixel(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParameters(boolean paramBoolean, long paramLong1, long paramLong2, int paramInt1, int paramInt2) {
/* 813 */     this.isMMREncoded = paramBoolean;
/* 814 */     this.dataOffset = paramLong1;
/* 815 */     this.dataLength = paramLong2;
/* 816 */     this.regionInfo.setBitmapHeight(paramInt1);
/* 817 */     this.regionInfo.setBitmapWidth(paramInt2);
/*     */     
/* 819 */     this.mmrDecompressor = null;
/* 820 */     resetBitmap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParameters(boolean paramBoolean1, byte paramByte, boolean paramBoolean2, boolean paramBoolean3, short[] paramArrayOfshort1, short[] paramArrayOfshort2, int paramInt1, int paramInt2, CX paramCX, ArithmeticDecoder paramArithmeticDecoder) {
/* 829 */     this.isMMREncoded = paramBoolean1;
/* 830 */     this.gbTemplate = paramByte;
/* 831 */     this.isTPGDon = paramBoolean2;
/* 832 */     this.gbAtX = paramArrayOfshort1;
/* 833 */     this.gbAtY = paramArrayOfshort2;
/* 834 */     this.regionInfo.setBitmapWidth(paramInt1);
/* 835 */     this.regionInfo.setBitmapHeight(paramInt2);
/* 836 */     if (null != paramCX)
/* 837 */       this.cx = paramCX; 
/* 838 */     if (null != paramArithmeticDecoder) {
/* 839 */       this.arithDecoder = paramArithmeticDecoder;
/*     */     }
/* 841 */     this.mmrDecompressor = null;
/* 842 */     resetBitmap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParameters(boolean paramBoolean1, long paramLong1, long paramLong2, int paramInt1, int paramInt2, byte paramByte, boolean paramBoolean2, boolean paramBoolean3, short[] paramArrayOfshort1, short[] paramArrayOfshort2) {
/* 851 */     this.dataOffset = paramLong1;
/* 852 */     this.dataLength = paramLong2;
/*     */     
/* 854 */     this.regionInfo = new RegionSegmentInformation();
/* 855 */     this.regionInfo.setBitmapHeight(paramInt1);
/* 856 */     this.regionInfo.setBitmapWidth(paramInt2);
/* 857 */     this.gbTemplate = paramByte;
/*     */     
/* 859 */     this.isMMREncoded = paramBoolean1;
/* 860 */     this.isTPGDon = paramBoolean2;
/* 861 */     this.gbAtX = paramArrayOfshort1;
/* 862 */     this.gbAtY = paramArrayOfshort2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void resetBitmap() {
/* 869 */     this.regionBitmap = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IOException {
/* 874 */     this.subInputStream = paramSubInputStream;
/* 875 */     this.regionInfo = new RegionSegmentInformation(this.subInputStream);
/* 876 */     parseHeader();
/*     */   }
/*     */   
/*     */   public RegionSegmentInformation getRegionInfo() {
/* 880 */     return this.regionInfo;
/*     */   }
/*     */   
/*     */   protected boolean useExtTemplates() {
/* 884 */     return this.useExtTemplates;
/*     */   }
/*     */   
/*     */   protected boolean isTPGDon() {
/* 888 */     return this.isTPGDon;
/*     */   }
/*     */   
/*     */   protected byte getGbTemplate() {
/* 892 */     return this.gbTemplate;
/*     */   }
/*     */   
/*     */   protected boolean isMMREncoded() {
/* 896 */     return this.isMMREncoded;
/*     */   }
/*     */   
/*     */   protected short[] getGbAtX() {
/* 900 */     return this.gbAtX;
/*     */   }
/*     */   
/*     */   protected short[] getGbAtY() {
/* 904 */     return this.gbAtY;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/GenericRegion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */