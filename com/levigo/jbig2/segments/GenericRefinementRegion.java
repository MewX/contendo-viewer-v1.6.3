/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.Region;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.decoder.arithmetic.ArithmeticDecoder;
/*     */ import com.levigo.jbig2.decoder.arithmetic.CX;
/*     */ import com.levigo.jbig2.err.IntegerMaxValueException;
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
/*     */ public class GenericRefinementRegion
/*     */   implements Region
/*     */ {
/*  38 */   private static final Logger log = LoggerFactory.getLogger(GenericRefinementRegion.class);
/*     */   
/*     */   public static abstract class Template {
/*     */     protected abstract short form(short param1Short1, short param1Short2, short param1Short3, short param1Short4, short param1Short5);
/*     */     
/*     */     protected abstract void setIndex(CX param1CX);
/*     */   }
/*     */   
/*     */   private static class Template0 extends Template {
/*     */     private Template0() {}
/*     */     
/*     */     protected short form(short param1Short1, short param1Short2, short param1Short3, short param1Short4, short param1Short5) {
/*  50 */       return (short)(param1Short1 << 10 | param1Short2 << 7 | param1Short3 << 4 | param1Short4 << 1 | param1Short5);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setIndex(CX param1CX) {
/*  56 */       param1CX.setIndex(256);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Template1
/*     */     extends Template {
/*     */     private Template1() {}
/*     */     
/*     */     protected short form(short param1Short1, short param1Short2, short param1Short3, short param1Short4, short param1Short5) {
/*  65 */       return (short)((param1Short1 & 0x2) << 8 | param1Short2 << 6 | (param1Short3 & 0x3) << 4 | param1Short4 << 1 | param1Short5);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setIndex(CX param1CX) {
/*  71 */       param1CX.setIndex(128);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  76 */   private static final Template T0 = new Template0();
/*  77 */   private static final Template T1 = new Template1();
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private SegmentHeader segmentHeader;
/*     */   
/*     */   private RegionSegmentInformation regionInfo;
/*     */   
/*     */   private boolean isTPGROn;
/*     */   
/*     */   private short templateID;
/*     */   
/*     */   private Template template;
/*     */   
/*     */   private short[] grAtX;
/*     */   
/*     */   private short[] grAtY;
/*     */   
/*     */   private Bitmap regionBitmap;
/*     */   
/*     */   private Bitmap referenceBitmap;
/*     */   
/*     */   private int referenceDX;
/*     */   
/*     */   private int referenceDY;
/*     */   
/*     */   private ArithmeticDecoder arithDecoder;
/*     */   
/*     */   private CX cx;
/*     */   
/*     */   private boolean override;
/*     */   
/*     */   private boolean[] grAtOverride;
/*     */ 
/*     */   
/*     */   public GenericRefinementRegion() {}
/*     */   
/*     */   public GenericRefinementRegion(SubInputStream paramSubInputStream) {
/* 115 */     this.subInputStream = paramSubInputStream;
/* 116 */     this.regionInfo = new RegionSegmentInformation(paramSubInputStream);
/*     */   }
/*     */   
/*     */   public GenericRefinementRegion(SubInputStream paramSubInputStream, SegmentHeader paramSegmentHeader) {
/* 120 */     this.subInputStream = paramSubInputStream;
/* 121 */     this.segmentHeader = paramSegmentHeader;
/* 122 */     this.regionInfo = new RegionSegmentInformation(paramSubInputStream);
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
/*     */   private void parseHeader() throws IOException {
/* 135 */     this.regionInfo.parseHeader();
/*     */ 
/*     */     
/* 138 */     this.subInputStream.readBits(6);
/*     */ 
/*     */     
/* 141 */     if (this.subInputStream.readBit() == 1) {
/* 142 */       this.isTPGROn = true;
/*     */     }
/*     */ 
/*     */     
/* 146 */     this.templateID = (short)this.subInputStream.readBit();
/*     */     
/* 148 */     switch (this.templateID) {
/*     */       case 0:
/* 150 */         this.template = T0;
/* 151 */         readAtPixels();
/*     */         break;
/*     */       case 1:
/* 154 */         this.template = T1;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readAtPixels() throws IOException {
/* 160 */     this.grAtX = new short[2];
/* 161 */     this.grAtY = new short[2];
/*     */ 
/*     */     
/* 164 */     this.grAtX[0] = (short)this.subInputStream.readByte();
/*     */     
/* 166 */     this.grAtY[0] = (short)this.subInputStream.readByte();
/*     */     
/* 168 */     this.grAtX[1] = (short)this.subInputStream.readByte();
/*     */     
/* 170 */     this.grAtY[1] = (short)this.subInputStream.readByte();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bitmap getRegionBitmap() throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 181 */     if (null == this.regionBitmap) {
/*     */       
/* 183 */       int i = 0;
/*     */       
/* 185 */       if (this.referenceBitmap == null)
/*     */       {
/* 187 */         this.referenceBitmap = getGrReference();
/*     */       }
/*     */       
/* 190 */       if (this.arithDecoder == null) {
/* 191 */         this.arithDecoder = new ArithmeticDecoder((ImageInputStream)this.subInputStream);
/*     */       }
/*     */       
/* 194 */       if (this.cx == null) {
/* 195 */         this.cx = new CX(8192, 1);
/*     */       }
/*     */ 
/*     */       
/* 199 */       this.regionBitmap = new Bitmap(this.regionInfo.getBitmapWidth(), this.regionInfo.getBitmapHeight());
/*     */       
/* 201 */       if (this.templateID == 0)
/*     */       {
/* 203 */         updateOverride();
/*     */       }
/*     */       
/* 206 */       int j = this.regionBitmap.getWidth() + 7 & 0xFFFFFFF8;
/* 207 */       byte b1 = this.isTPGROn ? (-this.referenceDY * this.referenceBitmap.getRowStride()) : 0;
/* 208 */       int k = b1 + 1;
/*     */ 
/*     */       
/* 211 */       for (byte b2 = 0; b2 < this.regionBitmap.getHeight(); b2++) {
/*     */         
/* 213 */         if (this.isTPGROn) {
/* 214 */           i ^= decodeSLTP();
/*     */         }
/*     */         
/* 217 */         if (i == 0) {
/*     */           
/* 219 */           decodeOptimized(b2, this.regionBitmap.getWidth(), this.regionBitmap.getRowStride(), this.referenceBitmap.getRowStride(), j, b1, k);
/*     */         }
/*     */         else {
/*     */           
/* 223 */           decodeTypicalPredictedLine(b2, this.regionBitmap.getWidth(), this.regionBitmap.getRowStride(), this.referenceBitmap.getRowStride(), j, b1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 229 */     return this.regionBitmap;
/*     */   }
/*     */   
/*     */   private int decodeSLTP() throws IOException {
/* 233 */     this.template.setIndex(this.cx);
/* 234 */     return this.arithDecoder.decode(this.cx);
/*     */   }
/*     */   
/*     */   private Bitmap getGrReference() throws IntegerMaxValueException, InvalidHeaderValueException, IOException {
/* 238 */     SegmentHeader[] arrayOfSegmentHeader = this.segmentHeader.getRtSegments();
/* 239 */     Region region = (Region)arrayOfSegmentHeader[0].getSegmentData();
/*     */     
/* 241 */     return region.getRegionBitmap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeOptimized(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) throws IOException {
/* 249 */     int i = paramInt1 - this.referenceDY;
/* 250 */     int j = this.referenceBitmap.getByteIndex(Math.max(0, -this.referenceDX), i);
/*     */     
/* 252 */     int k = this.regionBitmap.getByteIndex(Math.max(0, this.referenceDX), paramInt1);
/*     */     
/* 254 */     switch (this.templateID) {
/*     */       case 0:
/* 256 */         decodeTemplate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, k, i, j, T0);
/*     */         break;
/*     */       
/*     */       case 1:
/* 260 */         decodeTemplate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, k, i, j, T1);
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
/*     */   private void decodeTemplate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, Template paramTemplate) throws IOException {
/*     */     short s1, s2, s3;
/* 273 */     int m = 0, k = m, j = k, i = j;
/*     */     
/* 275 */     if (paramInt9 >= 1 && paramInt9 - 1 < this.referenceBitmap.getHeight())
/* 276 */       i = this.referenceBitmap.getByteAsInteger(paramInt10 - paramInt4); 
/* 277 */     if (paramInt9 >= 0 && paramInt9 < this.referenceBitmap.getHeight())
/* 278 */       j = this.referenceBitmap.getByteAsInteger(paramInt10); 
/* 279 */     if (paramInt9 >= -1 && paramInt9 + 1 < this.referenceBitmap.getHeight())
/* 280 */       k = this.referenceBitmap.getByteAsInteger(paramInt10 + paramInt4); 
/* 281 */     paramInt10++;
/*     */     
/* 283 */     if (paramInt1 >= 1) {
/* 284 */       m = this.regionBitmap.getByteAsInteger(paramInt8 - paramInt3);
/*     */     }
/* 286 */     paramInt8++;
/*     */     
/* 288 */     int n = this.referenceDX % 8;
/* 289 */     int i1 = 6 + n;
/* 290 */     int i2 = paramInt10 % paramInt4;
/*     */     
/* 292 */     if (i1 >= 0) {
/* 293 */       s1 = (short)(((i1 >= 8) ? 0 : (i >>> i1)) & 0x7);
/* 294 */       s2 = (short)(((i1 >= 8) ? 0 : (j >>> i1)) & 0x7);
/* 295 */       s3 = (short)(((i1 >= 8) ? 0 : (k >>> i1)) & 0x7);
/* 296 */       if (i1 == 6 && i2 > 1) {
/* 297 */         if (paramInt9 >= 1 && paramInt9 - 1 < this.referenceBitmap.getHeight()) {
/* 298 */           s1 = (short)(s1 | this.referenceBitmap.getByteAsInteger(paramInt10 - paramInt4 - 2) << 2 & 0x4);
/*     */         }
/* 300 */         if (paramInt9 >= 0 && paramInt9 < this.referenceBitmap.getHeight()) {
/* 301 */           s2 = (short)(s2 | this.referenceBitmap.getByteAsInteger(paramInt10 - 2) << 2 & 0x4);
/*     */         }
/* 303 */         if (paramInt9 >= -1 && paramInt9 + 1 < this.referenceBitmap.getHeight()) {
/* 304 */           s3 = (short)(s3 | this.referenceBitmap.getByteAsInteger(paramInt10 + paramInt4 - 2) << 2 & 0x4);
/*     */         }
/*     */       } 
/* 307 */       if (i1 == 0) {
/* 308 */         i = j = k = 0;
/* 309 */         if (i2 < paramInt4 - 1) {
/* 310 */           if (paramInt9 >= 1 && paramInt9 - 1 < this.referenceBitmap.getHeight())
/* 311 */             i = this.referenceBitmap.getByteAsInteger(paramInt10 - paramInt4); 
/* 312 */           if (paramInt9 >= 0 && paramInt9 < this.referenceBitmap.getHeight())
/* 313 */             j = this.referenceBitmap.getByteAsInteger(paramInt10); 
/* 314 */           if (paramInt9 >= -1 && paramInt9 + 1 < this.referenceBitmap.getHeight())
/* 315 */             k = this.referenceBitmap.getByteAsInteger(paramInt10 + paramInt4); 
/*     */         } 
/* 317 */         paramInt10++;
/*     */       } 
/*     */     } else {
/* 320 */       s1 = (short)(i << 1 & 0x7);
/* 321 */       s2 = (short)(j << 1 & 0x7);
/* 322 */       s3 = (short)(k << 1 & 0x7);
/* 323 */       i = j = k = 0;
/* 324 */       if (i2 < paramInt4 - 1) {
/* 325 */         if (paramInt9 >= 1 && paramInt9 - 1 < this.referenceBitmap.getHeight())
/* 326 */           i = this.referenceBitmap.getByteAsInteger(paramInt10 - paramInt4); 
/* 327 */         if (paramInt9 >= 0 && paramInt9 < this.referenceBitmap.getHeight())
/* 328 */           j = this.referenceBitmap.getByteAsInteger(paramInt10); 
/* 329 */         if (paramInt9 >= -1 && paramInt9 + 1 < this.referenceBitmap.getHeight())
/* 330 */           k = this.referenceBitmap.getByteAsInteger(paramInt10 + paramInt4); 
/* 331 */         paramInt10++;
/*     */       } 
/* 333 */       s1 = (short)(s1 | (short)(i >>> 7 & 0x7));
/* 334 */       s2 = (short)(s2 | (short)(j >>> 7 & 0x7));
/* 335 */       s3 = (short)(s3 | (short)(k >>> 7 & 0x7));
/*     */     } 
/*     */     
/* 338 */     short s4 = (short)(m >>> 6);
/* 339 */     short s = 0;
/*     */     
/* 341 */     int i3 = (2 - n) % 8;
/* 342 */     i <<= i3;
/* 343 */     j <<= i3;
/* 344 */     k <<= i3;
/*     */     
/* 346 */     m <<= 2;
/*     */     
/* 348 */     for (byte b = 0; b < paramInt2; b++) {
/* 349 */       int i4 = b & 0x7;
/*     */       
/* 351 */       short s5 = paramTemplate.form(s1, s2, s3, s4, s);
/*     */       
/* 353 */       if (this.override) {
/* 354 */         this.cx.setIndex(overrideAtTemplate0(s5, b, paramInt1, this.regionBitmap.getByte(this.regionBitmap.getByteIndex(b, paramInt1)), i4));
/*     */       } else {
/*     */         
/* 357 */         this.cx.setIndex(s5);
/*     */       } 
/* 359 */       int i5 = this.arithDecoder.decode(this.cx);
/* 360 */       this.regionBitmap.setPixel(b, paramInt1, (byte)i5);
/*     */       
/* 362 */       s1 = (short)((s1 << 1 | 0x1 & i >>> 7) & 0x7);
/* 363 */       s2 = (short)((s2 << 1 | 0x1 & j >>> 7) & 0x7);
/* 364 */       s3 = (short)((s3 << 1 | 0x1 & k >>> 7) & 0x7);
/* 365 */       s4 = (short)((s4 << 1 | 0x1 & m >>> 7) & 0x7);
/* 366 */       s = (short)i5;
/*     */       
/* 368 */       if ((b - this.referenceDX) % 8 == 5) {
/* 369 */         if ((b - this.referenceDX) / 8 + 1 >= this.referenceBitmap.getRowStride()) {
/* 370 */           i = j = k = 0;
/*     */         } else {
/* 372 */           if (paramInt9 >= 1 && paramInt9 - 1 < this.referenceBitmap.getHeight()) {
/* 373 */             i = this.referenceBitmap.getByteAsInteger(paramInt10 - paramInt4);
/*     */           } else {
/* 375 */             i = 0;
/*     */           } 
/* 377 */           if (paramInt9 >= 0 && paramInt9 < this.referenceBitmap.getHeight()) {
/* 378 */             j = this.referenceBitmap.getByteAsInteger(paramInt10);
/*     */           } else {
/* 380 */             j = 0;
/*     */           } 
/* 382 */           if (paramInt9 >= -1 && paramInt9 + 1 < this.referenceBitmap.getHeight()) {
/* 383 */             k = this.referenceBitmap.getByteAsInteger(paramInt10 + paramInt4);
/*     */           } else {
/* 385 */             k = 0;
/*     */           } 
/*     */         } 
/* 388 */         paramInt10++;
/*     */       } else {
/* 390 */         i <<= 1;
/* 391 */         j <<= 1;
/* 392 */         k <<= 1;
/*     */       } 
/*     */       
/* 395 */       if (i4 == 5 && paramInt1 >= 1) {
/* 396 */         if ((b >> 3) + 1 >= this.regionBitmap.getRowStride()) {
/* 397 */           m = 0;
/*     */         } else {
/* 399 */           m = this.regionBitmap.getByteAsInteger(paramInt8 - paramInt3);
/*     */         } 
/* 401 */         paramInt8++;
/*     */       } else {
/* 403 */         m <<= 1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateOverride() {
/* 410 */     if (this.grAtX == null || this.grAtY == null) {
/* 411 */       log.info("AT pixels not set");
/*     */       
/*     */       return;
/*     */     } 
/* 415 */     if (this.grAtX.length != this.grAtY.length) {
/* 416 */       log.info("AT pixel inconsistent");
/*     */       
/*     */       return;
/*     */     } 
/* 420 */     this.grAtOverride = new boolean[this.grAtX.length];
/*     */     
/* 422 */     switch (this.templateID) {
/*     */       case 0:
/* 424 */         if (this.grAtX[0] != -1 && this.grAtY[0] != -1) {
/* 425 */           this.grAtOverride[0] = true;
/* 426 */           this.override = true;
/*     */         } 
/*     */         
/* 429 */         if (this.grAtX[1] != -1 && this.grAtY[1] != -1) {
/* 430 */           this.grAtOverride[1] = true;
/* 431 */           this.override = true;
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 435 */         this.override = false;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTypicalPredictedLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) throws IOException {
/* 446 */     int i = paramInt1 - this.referenceDY;
/* 447 */     int j = this.referenceBitmap.getByteIndex(0, i);
/*     */     
/* 449 */     int k = this.regionBitmap.getByteIndex(0, paramInt1);
/*     */     
/* 451 */     switch (this.templateID) {
/*     */       case 0:
/* 453 */         decodeTypicalPredictedLineTemplate0(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, k, i, j);
/*     */         break;
/*     */       
/*     */       case 1:
/* 457 */         decodeTypicalPredictedLineTemplate1(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, k, i, j);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decodeTypicalPredictedLineTemplate0(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9) throws IOException {
/*     */     int j, k, m, n;
/* 474 */     if (paramInt1 > 0) {
/* 475 */       j = this.regionBitmap.getByteAsInteger(paramInt7 - paramInt3);
/*     */     } else {
/* 477 */       j = 0;
/*     */     } 
/*     */     
/* 480 */     if (paramInt8 > 0 && paramInt8 <= this.referenceBitmap.getHeight()) {
/* 481 */       k = this.referenceBitmap.getByteAsInteger(paramInt9 - paramInt4 + paramInt6) << 4;
/*     */     } else {
/* 483 */       k = 0;
/*     */     } 
/*     */     
/* 486 */     if (paramInt8 >= 0 && paramInt8 < this.referenceBitmap.getHeight()) {
/* 487 */       m = this.referenceBitmap.getByteAsInteger(paramInt9 + paramInt6) << 1;
/*     */     } else {
/* 489 */       m = 0;
/*     */     } 
/*     */     
/* 492 */     if (paramInt8 > -2 && paramInt8 < this.referenceBitmap.getHeight() - 1) {
/* 493 */       n = this.referenceBitmap.getByteAsInteger(paramInt9 + paramInt4 + paramInt6);
/*     */     } else {
/* 495 */       n = 0;
/*     */     } 
/*     */     
/* 498 */     int i = j >> 5 & 0x6 | n >> 2 & 0x30 | m & 0x180 | k & 0xC00;
/*     */     
/*     */     int i1;
/*     */     
/* 502 */     for (i1 = 0; i1 < paramInt5; i1 = i2) {
/* 503 */       byte b = 0;
/* 504 */       int i2 = i1 + 8;
/* 505 */       byte b1 = (paramInt2 - i1 > 8) ? 8 : (paramInt2 - i1);
/* 506 */       boolean bool1 = (i2 < paramInt2) ? true : false;
/* 507 */       boolean bool2 = (i2 < this.referenceBitmap.getWidth()) ? true : false;
/*     */       
/* 509 */       int i3 = paramInt6 + 1;
/*     */       
/* 511 */       if (paramInt1 > 0) {
/* 512 */         j = j << 8 | (bool1 ? this.regionBitmap.getByteAsInteger(paramInt7 - paramInt3 + 1) : 0);
/*     */       }
/*     */ 
/*     */       
/* 516 */       if (paramInt8 > 0 && paramInt8 <= this.referenceBitmap.getHeight()) {
/* 517 */         k = k << 8 | (bool2 ? (this.referenceBitmap.getByteAsInteger(paramInt9 - paramInt4 + i3) << 4) : 0);
/*     */       }
/*     */ 
/*     */       
/* 521 */       if (paramInt8 >= 0 && paramInt8 < this.referenceBitmap.getHeight()) {
/* 522 */         m = m << 8 | (bool2 ? (this.referenceBitmap.getByteAsInteger(paramInt9 + i3) << 1) : 0);
/*     */       }
/*     */ 
/*     */       
/* 526 */       if (paramInt8 > -2 && paramInt8 < this.referenceBitmap.getHeight() - 1) {
/* 527 */         n = n << 8 | (bool2 ? this.referenceBitmap.getByteAsInteger(paramInt9 + paramInt4 + i3) : 0);
/*     */       }
/*     */ 
/*     */       
/* 531 */       for (byte b2 = 0; b2 < b1; b2++) {
/* 532 */         boolean bool = false;
/* 533 */         int i4 = 0;
/*     */ 
/*     */         
/* 536 */         int i5 = i >> 4 & 0x1FF;
/*     */         
/* 538 */         if (i5 == 511) {
/* 539 */           bool = true;
/* 540 */           i4 = 1;
/* 541 */         } else if (i5 == 0) {
/* 542 */           bool = true;
/* 543 */           i4 = 0;
/*     */         } 
/*     */         
/* 546 */         if (!bool) {
/*     */ 
/*     */           
/* 549 */           if (this.override) {
/* 550 */             int i7 = overrideAtTemplate0(i, i1 + b2, paramInt1, b, b2);
/* 551 */             this.cx.setIndex(i7);
/*     */           } else {
/* 553 */             this.cx.setIndex(i);
/*     */           } 
/* 555 */           i4 = this.arithDecoder.decode(this.cx);
/*     */         } 
/*     */         
/* 558 */         int i6 = 7 - b2;
/* 559 */         b = (byte)(b | i4 << i6);
/*     */         
/* 561 */         i = (i & 0xDB6) << 1 | i4 | j >> i6 + 5 & 0x2 | n >> i6 + 2 & 0x10 | m >> i6 & 0x80 | k >> i6 & 0x400;
/*     */       } 
/*     */ 
/*     */       
/* 565 */       this.regionBitmap.setByte(paramInt7++, b);
/* 566 */       paramInt9++;
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
/*     */ 
/*     */   
/*     */   private void decodeTypicalPredictedLineTemplate1(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9) throws IOException {
/*     */     int k, m, n, i1;
/* 581 */     if (paramInt1 > 0) {
/* 582 */       k = this.regionBitmap.getByteAsInteger(paramInt7 - paramInt3);
/*     */     } else {
/* 584 */       k = 0;
/*     */     } 
/*     */     
/* 587 */     if (paramInt8 > 0 && paramInt8 <= this.referenceBitmap.getHeight()) {
/* 588 */       m = this.referenceBitmap.getByteAsInteger(paramInt7 - paramInt4 + paramInt6) << 2;
/*     */     } else {
/* 590 */       m = 0;
/*     */     } 
/*     */     
/* 593 */     if (paramInt8 >= 0 && paramInt8 < this.referenceBitmap.getHeight()) {
/* 594 */       n = this.referenceBitmap.getByteAsInteger(paramInt7 + paramInt6);
/*     */     } else {
/* 596 */       n = 0;
/*     */     } 
/*     */     
/* 599 */     if (paramInt8 > -2 && paramInt8 < this.referenceBitmap.getHeight() - 1) {
/* 600 */       i1 = this.referenceBitmap.getByteAsInteger(paramInt7 + paramInt4 + paramInt6);
/*     */     } else {
/* 602 */       i1 = 0;
/*     */     } 
/*     */     
/* 605 */     int i = k >> 5 & 0x6 | i1 >> 2 & 0x30 | n & 0xC0 | m & 0x200;
/*     */ 
/*     */     
/* 608 */     int j = i1 >> 2 & 0x70 | n & 0xC0 | m & 0x700;
/*     */     
/*     */     int i2;
/*     */     
/* 612 */     for (i2 = 0; i2 < paramInt5; i2 = i3) {
/* 613 */       byte b = 0;
/* 614 */       int i3 = i2 + 8;
/* 615 */       byte b1 = (paramInt2 - i2 > 8) ? 8 : (paramInt2 - i2);
/* 616 */       boolean bool1 = (i3 < paramInt2) ? true : false;
/* 617 */       boolean bool2 = (i3 < this.referenceBitmap.getWidth()) ? true : false;
/*     */       
/* 619 */       int i4 = paramInt6 + 1;
/*     */       
/* 621 */       if (paramInt1 > 0) {
/* 622 */         k = k << 8 | (bool1 ? this.regionBitmap.getByteAsInteger(paramInt7 - paramInt3 + 1) : 0);
/*     */       }
/*     */ 
/*     */       
/* 626 */       if (paramInt8 > 0 && paramInt8 <= this.referenceBitmap.getHeight()) {
/* 627 */         m = m << 8 | (bool2 ? (this.referenceBitmap.getByteAsInteger(paramInt9 - paramInt4 + i4) << 2) : 0);
/*     */       }
/*     */ 
/*     */       
/* 631 */       if (paramInt8 >= 0 && paramInt8 < this.referenceBitmap.getHeight()) {
/* 632 */         n = n << 8 | (bool2 ? this.referenceBitmap.getByteAsInteger(paramInt9 + i4) : 0);
/*     */       }
/*     */ 
/*     */       
/* 636 */       if (paramInt8 > -2 && paramInt8 < this.referenceBitmap.getHeight() - 1) {
/* 637 */         i1 = i1 << 8 | (bool2 ? this.referenceBitmap.getByteAsInteger(paramInt9 + paramInt4 + i4) : 0);
/*     */       }
/*     */ 
/*     */       
/* 641 */       for (byte b2 = 0; b2 < b1; b2++) {
/* 642 */         int i5 = 0;
/*     */ 
/*     */         
/* 645 */         int i6 = j >> 4 & 0x1FF;
/*     */         
/* 647 */         if (i6 == 511) {
/* 648 */           i5 = 1;
/* 649 */         } else if (i6 == 0) {
/* 650 */           i5 = 0;
/*     */         } else {
/* 652 */           this.cx.setIndex(i);
/* 653 */           i5 = this.arithDecoder.decode(this.cx);
/*     */         } 
/*     */         
/* 656 */         int i7 = 7 - b2;
/* 657 */         b = (byte)(b | i5 << i7);
/*     */         
/* 659 */         i = (i & 0xD6) << 1 | i5 | k >> i7 + 5 & 0x2 | i1 >> i7 + 2 & 0x10 | n >> i7 & 0x40 | m >> i7 & 0x200;
/*     */ 
/*     */ 
/*     */         
/* 663 */         j = (j & 0xDB) << 1 | i1 >> i7 + 2 & 0x10 | n >> i7 & 0x80 | m >> i7 & 0x400;
/*     */       } 
/*     */       
/* 666 */       this.regionBitmap.setByte(paramInt7++, b);
/* 667 */       paramInt9++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int overrideAtTemplate0(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws IOException {
/* 673 */     if (this.grAtOverride[0]) {
/* 674 */       paramInt1 &= 0xFFF7;
/* 675 */       if (this.grAtY[0] == 0 && this.grAtX[0] >= -paramInt5) {
/* 676 */         paramInt1 |= (paramInt4 >> 7 - paramInt5 + this.grAtX[0] & 0x1) << 3;
/*     */       } else {
/* 678 */         paramInt1 |= getPixel(this.regionBitmap, paramInt2 + this.grAtX[0], paramInt3 + this.grAtY[0]) << 3;
/*     */       } 
/*     */     } 
/*     */     
/* 682 */     if (this.grAtOverride[1]) {
/* 683 */       paramInt1 &= 0xEFFF;
/* 684 */       if (this.grAtY[1] == 0 && this.grAtX[1] >= -paramInt5) {
/* 685 */         paramInt1 |= (paramInt4 >> 7 - paramInt5 + this.grAtX[1] & 0x1) << 12;
/*     */       } else {
/* 687 */         paramInt1 |= getPixel(this.referenceBitmap, paramInt2 + this.grAtX[1] + this.referenceDX, paramInt3 + this.grAtY[1] + this.referenceDY) << 12;
/*     */       } 
/*     */     } 
/* 690 */     return paramInt1;
/*     */   }
/*     */   
/*     */   private byte getPixel(Bitmap paramBitmap, int paramInt1, int paramInt2) throws IOException {
/* 694 */     if (paramInt1 < 0 || paramInt1 >= paramBitmap.getWidth()) {
/* 695 */       return 0;
/*     */     }
/* 697 */     if (paramInt2 < 0 || paramInt2 >= paramBitmap.getHeight()) {
/* 698 */       return 0;
/*     */     }
/*     */     
/* 701 */     return paramBitmap.getPixel(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws IOException {
/* 705 */     this.segmentHeader = paramSegmentHeader;
/* 706 */     this.subInputStream = paramSubInputStream;
/* 707 */     this.regionInfo = new RegionSegmentInformation(this.subInputStream);
/* 708 */     parseHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParameters(CX paramCX, ArithmeticDecoder paramArithmeticDecoder, short paramShort, int paramInt1, int paramInt2, Bitmap paramBitmap, int paramInt3, int paramInt4, boolean paramBoolean, short[] paramArrayOfshort1, short[] paramArrayOfshort2) {
/* 715 */     if (null != paramCX) {
/* 716 */       this.cx = paramCX;
/*     */     }
/*     */     
/* 719 */     if (null != paramArithmeticDecoder) {
/* 720 */       this.arithDecoder = paramArithmeticDecoder;
/*     */     }
/*     */     
/* 723 */     this.templateID = paramShort;
/*     */     
/* 725 */     this.regionInfo.setBitmapWidth(paramInt1);
/* 726 */     this.regionInfo.setBitmapHeight(paramInt2);
/*     */     
/* 728 */     this.referenceBitmap = paramBitmap;
/* 729 */     this.referenceDX = paramInt3;
/* 730 */     this.referenceDY = paramInt4;
/*     */     
/* 732 */     this.isTPGROn = paramBoolean;
/*     */     
/* 734 */     this.grAtX = paramArrayOfshort1;
/* 735 */     this.grAtY = paramArrayOfshort2;
/*     */     
/* 737 */     this.regionBitmap = null;
/*     */   }
/*     */   
/*     */   public RegionSegmentInformation getRegionInfo() {
/* 741 */     return this.regionInfo;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/GenericRefinementRegion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */