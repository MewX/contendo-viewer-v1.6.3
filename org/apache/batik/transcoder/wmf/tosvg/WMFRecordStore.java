/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMFRecordStore
/*     */   extends AbstractWMFReader
/*     */ {
/*     */   private URL url;
/*     */   protected int numRecords;
/*     */   protected float vpX;
/*     */   protected float vpY;
/*     */   protected List records;
/*     */   private boolean _bext = true;
/*     */   
/*     */   public WMFRecordStore() {
/*  48 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  55 */     this.numRecords = 0;
/*  56 */     this.vpX = 0.0F;
/*  57 */     this.vpY = 0.0F;
/*  58 */     this.vpW = 1000;
/*  59 */     this.vpH = 1000;
/*  60 */     this.scaleX = 1.0F;
/*  61 */     this.scaleY = 1.0F;
/*  62 */     this.scaleXY = 1.0F;
/*  63 */     this.inch = 84;
/*  64 */     this.records = new ArrayList(20);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean readRecords(DataInputStream is) throws IOException {
/*  72 */     short functionId = 1;
/*  73 */     int recSize = 0;
/*     */ 
/*     */     
/*  76 */     this.numRecords = 0;
/*     */     
/*  78 */     while (functionId > 0) {
/*  79 */       int mapmode, i, yVal, len, lfHeight, height, i2, ydenom, i1, width, align, colorref, y, n, count, bottom, left, el_height, yend, m, k, rop, mode, type, j, xVal, read, lfWidth, i9, i8, ynum, i7, i6, red, x, pts[], i5, right, top, el_width, xend, i4, heightSrc, usage, sy, i3, lenText; byte[] bstr; int lfEscapement, xdenom, i16, i15, green, ptCount, i14, i13, i12, ystart, i11, widthSrc, i10, sx; byte[] bitmap; int flag, i28, i27, lfOrientation, xnum, i26, i25, blue, i24, offset, i23, i22, i21, xstart, i20, i19, i18, hdc, i17, i39, i38, lfWeight, i37, i36, flags, i35, i34, i33, i32, i31, i30, i29; boolean clipped; int lfItalic, i44, i43, i42, heightDst, i41, i40, x1, lfUnderline, i46, widthDst, i45, dy, y1, lfStrikeOut, i49, i48, i47, dx, x2, lfCharSet, i52, i51, i50, y2, lfOutPrecision, i54, i53, lfClipPrecision; byte[] arrayOfByte1; int i55; byte[] arrayOfByte3; int lfQuality, i56; byte[] arrayOfByte2; int i58, lfPitchAndFamily, i57, i59; byte[] lfFaceName; int i60; String str; recSize = readInt(is);
/*     */       
/*  81 */       recSize -= 3;
/*  82 */       functionId = readShort(is);
/*  83 */       if (functionId <= 0) {
/*     */         break;
/*     */       }
/*  86 */       MetaRecord mr = new MetaRecord();
/*  87 */       switch (functionId) {
/*     */         case 259:
/*  89 */           mr.numPoints = recSize;
/*  90 */           mr.functionId = functionId;
/*     */           
/*  92 */           mapmode = readShort(is);
/*  93 */           if (mapmode == 8) this.isotropic = false; 
/*  94 */           mr.addElement(mapmode);
/*  95 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1583:
/* 100 */           for (i = 0; i < recSize; i++)
/* 101 */             short recData = readShort(is); 
/* 102 */           this.numRecords--;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2610:
/* 108 */           yVal = readShort(is) * this.ySign;
/* 109 */           xVal = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 110 */           lenText = readShort(is);
/* 111 */           flag = readShort(is);
/* 112 */           i39 = 4;
/* 113 */           clipped = false;
/* 114 */           x1 = 0; y1 = 0; x2 = 0; y2 = 0;
/*     */ 
/*     */           
/* 117 */           if ((flag & 0x4) != 0) {
/* 118 */             x1 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 119 */             y1 = readShort(is) * this.ySign;
/* 120 */             x2 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 121 */             y2 = readShort(is) * this.ySign;
/* 122 */             i39 += 4;
/* 123 */             clipped = true;
/*     */           } 
/* 125 */           arrayOfByte3 = new byte[lenText];
/* 126 */           i58 = 0;
/* 127 */           for (; i58 < lenText; i58++) {
/* 128 */             arrayOfByte3[i58] = is.readByte();
/*     */           }
/* 130 */           i39 += (lenText + 1) / 2;
/*     */ 
/*     */ 
/*     */           
/* 134 */           if (lenText % 2 != 0) is.readByte();
/*     */           
/* 136 */           if (i39 < recSize) for (int i61 = i39; i61 < recSize; ) { readShort(is); i61++; }
/*     */           
/*     */ 
/*     */ 
/*     */           
/* 141 */           mr = new MetaRecord.ByteRecord(arrayOfByte3);
/* 142 */           mr.numPoints = recSize;
/* 143 */           mr.functionId = functionId;
/*     */           
/* 145 */           mr.addElement(xVal);
/* 146 */           mr.addElement(yVal);
/* 147 */           mr.addElement(flag);
/* 148 */           if (clipped) {
/* 149 */             mr.addElement(x1);
/* 150 */             mr.addElement(y1);
/* 151 */             mr.addElement(x2);
/* 152 */             mr.addElement(y2);
/*     */           } 
/* 154 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1313:
/* 160 */           len = readShort(is);
/* 161 */           read = 1;
/* 162 */           bstr = new byte[len];
/* 163 */           for (i28 = 0; i28 < len; i28++) {
/* 164 */             bstr[i28] = is.readByte();
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 169 */           if (len % 2 != 0) is.readByte(); 
/* 170 */           read += (len + 1) / 2;
/*     */           
/* 172 */           i27 = readShort(is) * this.ySign;
/* 173 */           i38 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 174 */           read += 2;
/*     */           
/* 176 */           if (read < recSize) for (int i61 = read; i61 < recSize; ) { readShort(is); i61++; }
/*     */           
/*     */ 
/*     */ 
/*     */           
/* 181 */           mr = new MetaRecord.ByteRecord(bstr);
/* 182 */           mr.numPoints = recSize;
/* 183 */           mr.functionId = functionId;
/*     */           
/* 185 */           mr.addElement(i38);
/* 186 */           mr.addElement(i27);
/* 187 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 763:
/* 194 */           lfHeight = readShort(is);
/* 195 */           lfWidth = readShort(is);
/* 196 */           lfEscapement = readShort(is);
/* 197 */           lfOrientation = readShort(is);
/* 198 */           lfWeight = readShort(is);
/*     */           
/* 200 */           lfItalic = is.readByte();
/* 201 */           lfUnderline = is.readByte();
/* 202 */           lfStrikeOut = is.readByte();
/* 203 */           lfCharSet = is.readByte() & 0xFF;
/*     */           
/* 205 */           lfOutPrecision = is.readByte();
/* 206 */           lfClipPrecision = is.readByte();
/* 207 */           lfQuality = is.readByte();
/* 208 */           lfPitchAndFamily = is.readByte();
/*     */ 
/*     */ 
/*     */           
/* 212 */           i59 = 2 * (recSize - 9);
/* 213 */           lfFaceName = new byte[i59];
/*     */           
/* 215 */           for (i60 = 0; i60 < i59; ) { lfFaceName[i60] = is.readByte(); i60++; }
/*     */           
/* 217 */           str = new String(lfFaceName);
/*     */           
/* 219 */           mr = new MetaRecord.StringRecord(str);
/* 220 */           mr.numPoints = recSize;
/* 221 */           mr.functionId = functionId;
/*     */           
/* 223 */           mr.addElement(lfHeight);
/* 224 */           mr.addElement(lfItalic);
/* 225 */           mr.addElement(lfWeight);
/* 226 */           mr.addElement(lfCharSet);
/* 227 */           mr.addElement(lfUnderline);
/* 228 */           mr.addElement(lfStrikeOut);
/* 229 */           mr.addElement(lfOrientation);
/*     */           
/* 231 */           mr.addElement(lfEscapement);
/* 232 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 523:
/*     */         case 524:
/*     */         case 525:
/*     */         case 526:
/* 240 */           mr.numPoints = recSize;
/* 241 */           mr.functionId = functionId;
/*     */           
/* 243 */           height = readShort(is);
/* 244 */           i9 = readShort(is);
/*     */           
/* 246 */           if (i9 < 0) {
/* 247 */             i9 = -i9;
/* 248 */             this.xSign = -1;
/*     */           } 
/* 250 */           if (height < 0) {
/* 251 */             height = -height;
/* 252 */             this.ySign = -1;
/*     */           } 
/*     */           
/* 255 */           if (this._bext && functionId == 524) {
/* 256 */             this.vpW = i9;
/* 257 */             this.vpH = height;
/*     */ 
/*     */ 
/*     */             
/* 261 */             this._bext = false;
/*     */           } 
/*     */ 
/*     */           
/* 265 */           if (!this.isAldus) {
/* 266 */             this.width = this.vpW;
/* 267 */             this.height = this.vpH;
/*     */           } 
/*     */           
/* 270 */           mr.addElement((int)(i9 * this.scaleXY));
/* 271 */           mr.addElement(height);
/* 272 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 527:
/*     */         case 529:
/* 278 */           mr.numPoints = recSize;
/* 279 */           mr.functionId = functionId;
/*     */           
/* 281 */           i2 = readShort(is) * this.ySign;
/* 282 */           i8 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 283 */           mr.addElement(i8);
/* 284 */           mr.addElement(i2);
/* 285 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1040:
/*     */         case 1042:
/* 291 */           mr.numPoints = recSize;
/* 292 */           mr.functionId = functionId;
/*     */           
/* 294 */           ydenom = readShort(is);
/* 295 */           ynum = readShort(is);
/* 296 */           xdenom = readShort(is);
/* 297 */           xnum = readShort(is);
/* 298 */           mr.addElement(xdenom);
/* 299 */           mr.addElement(ydenom);
/* 300 */           mr.addElement(xnum);
/* 301 */           mr.addElement(ynum);
/* 302 */           this.records.add(mr);
/* 303 */           this.scaleX = this.scaleX * xdenom / xnum;
/* 304 */           this.scaleY = this.scaleY * ydenom / ynum;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 764:
/* 310 */           mr.numPoints = recSize;
/* 311 */           mr.functionId = functionId;
/*     */ 
/*     */           
/* 314 */           mr.addElement(readShort(is));
/*     */           
/* 316 */           i1 = readInt(is);
/* 317 */           i7 = i1 & 0xFF;
/* 318 */           i16 = (i1 & 0xFF00) >> 8;
/* 319 */           i26 = (i1 & 0xFF0000) >> 16;
/* 320 */           i37 = (i1 & 0x3000000) >> 24;
/* 321 */           mr.addElement(i7);
/* 322 */           mr.addElement(i16);
/* 323 */           mr.addElement(i26);
/*     */ 
/*     */           
/* 326 */           mr.addElement(readShort(is));
/*     */           
/* 328 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 762:
/* 334 */           mr.numPoints = recSize;
/* 335 */           mr.functionId = functionId;
/*     */ 
/*     */           
/* 338 */           mr.addElement(readShort(is));
/*     */           
/* 340 */           width = readInt(is);
/* 341 */           i6 = readInt(is);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 349 */           if (recSize == 6) readShort(is);
/*     */           
/* 351 */           i15 = i6 & 0xFF;
/* 352 */           i25 = (i6 & 0xFF00) >> 8;
/* 353 */           i36 = (i6 & 0xFF0000) >> 16;
/* 354 */           i44 = (i6 & 0x3000000) >> 24;
/*     */           
/* 356 */           mr.addElement(i15);
/* 357 */           mr.addElement(i25);
/* 358 */           mr.addElement(i36);
/*     */ 
/*     */           
/* 361 */           mr.addElement(width);
/*     */           
/* 363 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 302:
/* 369 */           mr.numPoints = recSize;
/* 370 */           mr.functionId = functionId;
/* 371 */           align = readShort(is);
/*     */           
/* 373 */           if (recSize > 1) for (int i61 = 1; i61 < recSize; ) { readShort(is); i61++; }
/* 374 */               mr.addElement(align);
/* 375 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 513:
/*     */         case 521:
/* 382 */           mr.numPoints = recSize;
/* 383 */           mr.functionId = functionId;
/*     */           
/* 385 */           colorref = readInt(is);
/* 386 */           red = colorref & 0xFF;
/* 387 */           green = (colorref & 0xFF00) >> 8;
/* 388 */           blue = (colorref & 0xFF0000) >> 16;
/* 389 */           flags = (colorref & 0x3000000) >> 24;
/* 390 */           mr.addElement(red);
/* 391 */           mr.addElement(green);
/* 392 */           mr.addElement(blue);
/* 393 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 531:
/*     */         case 532:
/* 400 */           mr.numPoints = recSize;
/* 401 */           mr.functionId = functionId;
/*     */           
/* 403 */           y = readShort(is) * this.ySign;
/* 404 */           x = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 405 */           mr.addElement(x);
/* 406 */           mr.addElement(y);
/* 407 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 262:
/* 413 */           mr.numPoints = recSize;
/* 414 */           mr.functionId = functionId;
/*     */           
/* 416 */           n = readShort(is);
/*     */           
/* 418 */           if (recSize > 1) for (int i61 = 1; i61 < recSize; ) { readShort(is); i61++; }
/* 419 */               mr.addElement(n);
/* 420 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1336:
/* 426 */           mr.numPoints = recSize;
/* 427 */           mr.functionId = functionId;
/*     */           
/* 429 */           count = readShort(is);
/* 430 */           pts = new int[count];
/* 431 */           ptCount = 0;
/* 432 */           for (i24 = 0; i24 < count; i24++) {
/* 433 */             pts[i24] = readShort(is);
/* 434 */             ptCount += pts[i24];
/*     */           } 
/* 436 */           mr.addElement(count);
/*     */           
/* 438 */           for (i24 = 0; i24 < count; i24++) {
/* 439 */             mr.addElement(pts[i24]);
/*     */           }
/* 441 */           offset = count + 1;
/* 442 */           for (i35 = 0; i35 < count; i35++) {
/* 443 */             int nPoints = pts[i35];
/* 444 */             for (int i61 = 0; i61 < nPoints; i61++) {
/* 445 */               mr.addElement((int)((readShort(is) * this.xSign) * this.scaleXY));
/* 446 */               mr.addElement(readShort(is) * this.ySign);
/*     */             } 
/*     */           } 
/* 449 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 804:
/*     */         case 805:
/* 456 */           mr.numPoints = recSize;
/* 457 */           mr.functionId = functionId;
/*     */           
/* 459 */           count = readShort(is);
/* 460 */           mr.addElement(count);
/* 461 */           for (i5 = 0; i5 < count; i5++) {
/* 462 */             mr.addElement((int)((readShort(is) * this.xSign) * this.scaleXY));
/* 463 */             mr.addElement(readShort(is) * this.ySign);
/*     */           } 
/* 465 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1046:
/*     */         case 1048:
/*     */         case 1051:
/* 473 */           mr.numPoints = recSize;
/* 474 */           mr.functionId = functionId;
/*     */           
/* 476 */           bottom = readShort(is) * this.ySign;
/* 477 */           right = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 478 */           i14 = readShort(is) * this.ySign;
/* 479 */           i23 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 480 */           mr.addElement(i23);
/* 481 */           mr.addElement(i14);
/* 482 */           mr.addElement(right);
/* 483 */           mr.addElement(bottom);
/* 484 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1791:
/* 489 */           mr.numPoints = recSize;
/* 490 */           mr.functionId = functionId;
/* 491 */           left = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 492 */           top = readShort(is) * this.ySign;
/* 493 */           i13 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 494 */           i22 = readShort(is) * this.ySign;
/* 495 */           mr.addElement(left);
/* 496 */           mr.addElement(top);
/* 497 */           mr.addElement(i13);
/* 498 */           mr.addElement(i22);
/* 499 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1564:
/* 504 */           mr.numPoints = recSize;
/* 505 */           mr.functionId = functionId;
/*     */           
/* 507 */           el_height = readShort(is) * this.ySign;
/* 508 */           el_width = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 509 */           i12 = readShort(is) * this.ySign;
/* 510 */           i21 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 511 */           i34 = readShort(is) * this.ySign;
/* 512 */           i43 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 513 */           mr.addElement(i43);
/* 514 */           mr.addElement(i34);
/* 515 */           mr.addElement(i21);
/* 516 */           mr.addElement(i12);
/* 517 */           mr.addElement(el_width);
/* 518 */           mr.addElement(el_height);
/* 519 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2071:
/*     */         case 2074:
/* 526 */           mr.numPoints = recSize;
/* 527 */           mr.functionId = functionId;
/*     */           
/* 529 */           yend = readShort(is) * this.ySign;
/* 530 */           xend = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 531 */           ystart = readShort(is) * this.ySign;
/* 532 */           xstart = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 533 */           i33 = readShort(is) * this.ySign;
/* 534 */           i42 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 535 */           i46 = readShort(is) * this.ySign;
/* 536 */           i49 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 537 */           mr.addElement(i49);
/* 538 */           mr.addElement(i46);
/* 539 */           mr.addElement(i42);
/* 540 */           mr.addElement(i33);
/* 541 */           mr.addElement(xstart);
/* 542 */           mr.addElement(ystart);
/* 543 */           mr.addElement(xend);
/* 544 */           mr.addElement(yend);
/* 545 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1565:
/* 552 */           mr.numPoints = recSize;
/* 553 */           mr.functionId = functionId;
/*     */           
/* 555 */           m = readInt(is);
/* 556 */           i4 = readShort(is) * this.ySign;
/* 557 */           i11 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 558 */           i20 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 559 */           i32 = readShort(is) * this.ySign;
/*     */           
/* 561 */           mr.addElement(m);
/* 562 */           mr.addElement(i4);
/* 563 */           mr.addElement(i11);
/* 564 */           mr.addElement(i32);
/* 565 */           mr.addElement(i20);
/*     */           
/* 567 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 258:
/* 573 */           mr.numPoints = recSize;
/* 574 */           mr.functionId = functionId;
/*     */           
/* 576 */           k = readShort(is);
/* 577 */           mr.addElement(k);
/*     */           
/* 579 */           if (recSize > 1) for (int i61 = 1; i61 < recSize; ) { readShort(is); i61++; }
/* 580 */               this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 260:
/* 587 */           mr.numPoints = recSize;
/* 588 */           mr.functionId = functionId;
/*     */ 
/*     */ 
/*     */           
/* 592 */           if (recSize == 1) { rop = readShort(is); }
/* 593 */           else { rop = readInt(is); }
/*     */           
/* 595 */           mr.addElement(rop);
/* 596 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2881:
/* 602 */           mode = is.readInt() & 0xFF;
/* 603 */           heightSrc = readShort(is) * this.ySign;
/* 604 */           widthSrc = readShort(is) * this.xSign;
/* 605 */           i19 = readShort(is) * this.ySign;
/* 606 */           i31 = readShort(is) * this.xSign;
/* 607 */           heightDst = readShort(is) * this.ySign;
/* 608 */           widthDst = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 609 */           i48 = readShort(is) * this.ySign;
/* 610 */           i52 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/*     */           
/* 612 */           i54 = 2 * recSize - 20;
/* 613 */           arrayOfByte1 = new byte[i54];
/* 614 */           for (i56 = 0; i56 < i54; ) { arrayOfByte1[i56] = is.readByte(); i56++; }
/*     */           
/* 616 */           mr = new MetaRecord.ByteRecord(arrayOfByte1);
/* 617 */           mr.numPoints = recSize;
/* 618 */           mr.functionId = functionId;
/* 619 */           mr.addElement(mode);
/* 620 */           mr.addElement(heightSrc);
/* 621 */           mr.addElement(widthSrc);
/* 622 */           mr.addElement(i19);
/* 623 */           mr.addElement(i31);
/* 624 */           mr.addElement(heightDst);
/* 625 */           mr.addElement(widthDst);
/* 626 */           mr.addElement(i48);
/* 627 */           mr.addElement(i52);
/* 628 */           this.records.add(mr);
/*     */           break;
/*     */         
/*     */         case 3907:
/* 632 */           mode = is.readInt() & 0xFF;
/* 633 */           usage = readShort(is);
/* 634 */           i10 = readShort(is) * this.ySign;
/* 635 */           i18 = readShort(is) * this.xSign;
/* 636 */           i30 = readShort(is) * this.ySign;
/* 637 */           i41 = readShort(is) * this.xSign;
/* 638 */           i45 = readShort(is) * this.ySign;
/* 639 */           i47 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 640 */           i51 = readShort(is) * this.ySign;
/* 641 */           i53 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/*     */           
/* 643 */           i55 = 2 * recSize - 22;
/* 644 */           arrayOfByte2 = new byte[i55];
/* 645 */           for (i57 = 0; i57 < i55; ) { arrayOfByte2[i57] = is.readByte(); i57++; }
/*     */           
/* 647 */           mr = new MetaRecord.ByteRecord(arrayOfByte2);
/* 648 */           mr.numPoints = recSize;
/* 649 */           mr.functionId = functionId;
/* 650 */           mr.addElement(mode);
/* 651 */           mr.addElement(i10);
/* 652 */           mr.addElement(i18);
/* 653 */           mr.addElement(i30);
/* 654 */           mr.addElement(i41);
/* 655 */           mr.addElement(i45);
/* 656 */           mr.addElement(i47);
/* 657 */           mr.addElement(i51);
/* 658 */           mr.addElement(i53);
/* 659 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2368:
/* 665 */           mode = is.readInt() & 0xFF;
/* 666 */           sy = readShort(is);
/* 667 */           sx = readShort(is);
/* 668 */           hdc = readShort(is);
/* 669 */           i29 = readShort(is);
/* 670 */           i40 = (int)((readShort(is) * this.xSign) * this.scaleXY);
/* 671 */           dy = readShort(is);
/* 672 */           dx = (int)((readShort(is) * this.xSign) * this.scaleXY);
/*     */           
/* 674 */           i50 = 2 * recSize - 18;
/* 675 */           if (i50 > 0) {
/* 676 */             byte[] arrayOfByte = new byte[i50];
/* 677 */             for (int i61 = 0; i61 < i50; i61++)
/* 678 */               arrayOfByte[i61] = is.readByte(); 
/* 679 */             mr = new MetaRecord.ByteRecord(arrayOfByte);
/* 680 */             mr.numPoints = recSize;
/* 681 */             mr.functionId = functionId;
/*     */           } else {
/*     */             
/* 684 */             mr.numPoints = recSize;
/* 685 */             mr.functionId = functionId;
/* 686 */             for (int i61 = 0; i61 < i50; ) { is.readByte(); i61++; }
/*     */           
/*     */           } 
/* 689 */           mr.addElement(mode);
/* 690 */           mr.addElement(i29);
/* 691 */           mr.addElement(i40);
/* 692 */           mr.addElement(sy);
/* 693 */           mr.addElement(sx);
/* 694 */           mr.addElement(dy);
/* 695 */           mr.addElement(dx);
/* 696 */           this.records.add(mr);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 322:
/* 702 */           type = is.readInt() & 0xFF;
/* 703 */           i3 = 2 * recSize - 4;
/* 704 */           bitmap = new byte[i3];
/* 705 */           for (i17 = 0; i17 < i3; ) { bitmap[i17] = is.readByte(); i17++; }
/*     */           
/* 707 */           mr = new MetaRecord.ByteRecord(bitmap);
/* 708 */           mr.numPoints = recSize;
/* 709 */           mr.functionId = functionId;
/* 710 */           mr.addElement(type);
/* 711 */           this.records.add(mr);
/*     */           break;
/*     */         
/*     */         default:
/* 715 */           mr.numPoints = recSize;
/* 716 */           mr.functionId = functionId;
/*     */           
/* 718 */           for (j = 0; j < recSize; j++) {
/* 719 */             mr.addElement(readShort(is));
/*     */           }
/* 721 */           this.records.add(mr);
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 726 */       this.numRecords++;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 732 */     if (!this.isAldus) {
/* 733 */       this.right = (int)this.vpX;
/* 734 */       this.left = (int)(this.vpX + this.vpW);
/* 735 */       this.top = (int)this.vpY;
/* 736 */       this.bottom = (int)(this.vpY + this.vpH);
/*     */     } 
/* 738 */     setReading(false);
/* 739 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getUrl() {
/* 746 */     return this.url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUrl(URL newUrl) {
/* 753 */     this.url = newUrl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetaRecord getRecord(int idx) {
/* 760 */     return this.records.get(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumRecords() {
/* 767 */     return this.numRecords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVpX() {
/* 774 */     return this.vpX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVpY() {
/* 781 */     return this.vpY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVpX(float newValue) {
/* 788 */     this.vpX = newValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVpY(float newValue) {
/* 795 */     this.vpY = newValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/WMFRecordStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */