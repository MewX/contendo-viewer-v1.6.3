/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.Region;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.decoder.arithmetic.ArithmeticDecoder;
/*     */ import com.levigo.jbig2.decoder.arithmetic.ArithmeticIntegerDecoder;
/*     */ import com.levigo.jbig2.decoder.arithmetic.CX;
/*     */ import com.levigo.jbig2.decoder.huffman.EncodedTable;
/*     */ import com.levigo.jbig2.decoder.huffman.FixedSizeTable;
/*     */ import com.levigo.jbig2.decoder.huffman.HuffmanTable;
/*     */ import com.levigo.jbig2.decoder.huffman.StandardTables;
/*     */ import com.levigo.jbig2.err.IntegerMaxValueException;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.image.Bitmaps;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.CombinationOperator;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ public class TextRegion
/*     */   implements Region
/*     */ {
/*  50 */   private final Logger log = LoggerFactory.getLogger(TextRegion.class);
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private RegionSegmentInformation regionInfo;
/*     */   
/*     */   private short sbrTemplate;
/*     */   
/*     */   private short sbdsOffset;
/*     */   
/*     */   private short defaultPixel;
/*     */   
/*     */   private CombinationOperator combinationOperator;
/*     */   
/*     */   private short isTransposed;
/*     */   
/*     */   private short referenceCorner;
/*     */   
/*     */   private short logSBStrips;
/*     */   
/*     */   private boolean useRefinement;
/*     */   
/*     */   private boolean isHuffmanEncoded;
/*     */   
/*     */   private short sbHuffRSize;
/*     */   
/*     */   private short sbHuffRDY;
/*     */   
/*     */   private short sbHuffRDX;
/*     */   private short sbHuffRDHeight;
/*     */   private short sbHuffRDWidth;
/*     */   private short sbHuffDT;
/*     */   private short sbHuffDS;
/*     */   private short sbHuffFS;
/*     */   private short[] sbrATX;
/*     */   private short[] sbrATY;
/*     */   private long amountOfSymbolInstances;
/*     */   private long currentS;
/*     */   private int sbStrips;
/*     */   private int amountOfSymbols;
/*     */   private Bitmap regionBitmap;
/*  91 */   private ArrayList<Bitmap> symbols = new ArrayList<>();
/*     */   
/*     */   private ArithmeticDecoder arithmeticDecoder;
/*     */   
/*     */   private ArithmeticIntegerDecoder integerDecoder;
/*     */   
/*     */   private GenericRefinementRegion genericRefinementRegion;
/*     */   
/*     */   private CX cxIADT;
/*     */   
/*     */   private CX cxIAFS;
/*     */   
/*     */   private CX cxIADS;
/*     */   
/*     */   private CX cxIAIT;
/*     */   private CX cxIARI;
/*     */   private CX cxIARDW;
/*     */   private CX cxIARDH;
/*     */   private CX cxIAID;
/*     */   private CX cxIARDX;
/*     */   private CX cxIARDY;
/*     */   private CX cx;
/*     */   private int symbolCodeLength;
/*     */   private FixedSizeTable symbolCodeTable;
/*     */   private SegmentHeader segmentHeader;
/*     */   private HuffmanTable fsTable;
/*     */   private HuffmanTable dsTable;
/*     */   private HuffmanTable table;
/*     */   private HuffmanTable rdwTable;
/*     */   private HuffmanTable rdhTable;
/*     */   private HuffmanTable rdxTable;
/*     */   private HuffmanTable rdyTable;
/*     */   private HuffmanTable rSizeTable;
/*     */   
/*     */   public TextRegion() {}
/*     */   
/*     */   public TextRegion(SubInputStream paramSubInputStream, SegmentHeader paramSegmentHeader) {
/* 128 */     this.subInputStream = paramSubInputStream;
/* 129 */     this.regionInfo = new RegionSegmentInformation(paramSubInputStream);
/* 130 */     this.segmentHeader = paramSegmentHeader;
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseHeader() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 135 */     this.regionInfo.parseHeader();
/*     */     
/* 137 */     readRegionFlags();
/*     */     
/* 139 */     if (this.isHuffmanEncoded) {
/* 140 */       readHuffmanFlags();
/*     */     }
/*     */     
/* 143 */     readUseRefinement();
/*     */     
/* 145 */     readAmountOfSymbolInstances();
/*     */ 
/*     */     
/* 148 */     getSymbols();
/*     */     
/* 150 */     computeSymbolCodeLength();
/*     */     
/* 152 */     checkInput();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readRegionFlags() throws IOException {
/* 157 */     this.sbrTemplate = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 160 */     this.sbdsOffset = (short)(int)this.subInputStream.readBits(5);
/* 161 */     if (this.sbdsOffset > 15) {
/* 162 */       this.sbdsOffset = (short)(this.sbdsOffset - 32);
/*     */     }
/*     */ 
/*     */     
/* 166 */     this.defaultPixel = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 169 */     this.combinationOperator = CombinationOperator.translateOperatorCodeToEnum((short)(int)(this.subInputStream.readBits(2) & 0x3L));
/*     */ 
/*     */     
/* 172 */     this.isTransposed = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 175 */     this.referenceCorner = (short)(int)(this.subInputStream.readBits(2) & 0x3L);
/*     */ 
/*     */     
/* 178 */     this.logSBStrips = (short)(int)(this.subInputStream.readBits(2) & 0x3L);
/* 179 */     this.sbStrips = 1 << this.logSBStrips;
/*     */ 
/*     */     
/* 182 */     if (this.subInputStream.readBit() == 1) {
/* 183 */       this.useRefinement = true;
/*     */     }
/*     */ 
/*     */     
/* 187 */     if (this.subInputStream.readBit() == 1) {
/* 188 */       this.isHuffmanEncoded = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void readHuffmanFlags() throws IOException {
/* 194 */     this.subInputStream.readBit();
/*     */ 
/*     */     
/* 197 */     this.sbHuffRSize = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 200 */     this.sbHuffRDY = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 203 */     this.sbHuffRDX = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 206 */     this.sbHuffRDHeight = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 209 */     this.sbHuffRDWidth = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 212 */     this.sbHuffDT = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 215 */     this.sbHuffDS = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 218 */     this.sbHuffFS = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */   }
/*     */   
/*     */   private void readUseRefinement() throws IOException {
/* 222 */     if (this.useRefinement && this.sbrTemplate == 0) {
/* 223 */       this.sbrATX = new short[2];
/* 224 */       this.sbrATY = new short[2];
/*     */ 
/*     */       
/* 227 */       this.sbrATX[0] = (short)this.subInputStream.readByte();
/*     */ 
/*     */       
/* 230 */       this.sbrATY[0] = (short)this.subInputStream.readByte();
/*     */ 
/*     */       
/* 233 */       this.sbrATX[1] = (short)this.subInputStream.readByte();
/*     */ 
/*     */       
/* 236 */       this.sbrATY[1] = (short)this.subInputStream.readByte();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readAmountOfSymbolInstances() throws IOException {
/* 241 */     this.amountOfSymbolInstances = this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private void getSymbols() throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 245 */     if (this.segmentHeader.getRtSegments() != null) {
/* 246 */       initSymbols();
/*     */     }
/*     */   }
/*     */   
/*     */   private void computeSymbolCodeLength() throws IOException {
/* 251 */     if (this.isHuffmanEncoded) {
/* 252 */       symbolIDCodeLengths();
/*     */     } else {
/* 254 */       this.symbolCodeLength = (int)Math.ceil(Math.log(this.amountOfSymbols) / Math.log(2.0D));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkInput() throws InvalidHeaderValueException {
/* 260 */     if (!this.useRefinement && 
/* 261 */       this.sbrTemplate != 0) {
/* 262 */       this.log.info("sbrTemplate should be 0");
/* 263 */       this.sbrTemplate = 0;
/*     */     } 
/*     */ 
/*     */     
/* 267 */     if (this.sbHuffFS == 2 || this.sbHuffRDWidth == 2 || this.sbHuffRDHeight == 2 || this.sbHuffRDX == 2 || this.sbHuffRDY == 2) {
/* 268 */       throw new InvalidHeaderValueException("Huffman flag value of text region segment is not permitted");
/*     */     }
/*     */     
/* 271 */     if (!this.useRefinement) {
/* 272 */       if (this.sbHuffRSize != 0) {
/* 273 */         this.log.info("sbHuffRSize should be 0");
/* 274 */         this.sbHuffRSize = 0;
/*     */       } 
/* 276 */       if (this.sbHuffRDY != 0) {
/* 277 */         this.log.info("sbHuffRDY should be 0");
/* 278 */         this.sbHuffRDY = 0;
/*     */       } 
/* 280 */       if (this.sbHuffRDX != 0) {
/* 281 */         this.log.info("sbHuffRDX should be 0");
/* 282 */         this.sbHuffRDX = 0;
/*     */       } 
/* 284 */       if (this.sbHuffRDWidth != 0) {
/* 285 */         this.log.info("sbHuffRDWidth should be 0");
/* 286 */         this.sbHuffRDWidth = 0;
/*     */       } 
/* 288 */       if (this.sbHuffRDHeight != 0) {
/* 289 */         this.log.info("sbHuffRDHeight should be 0");
/* 290 */         this.sbHuffRDHeight = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Bitmap getRegionBitmap() throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 297 */     if (!this.isHuffmanEncoded) {
/* 298 */       setCodingStatistics();
/*     */     }
/*     */     
/* 301 */     createRegionBitmap();
/* 302 */     decodeSymbolInstances();
/*     */ 
/*     */     
/* 305 */     return this.regionBitmap;
/*     */   }
/*     */   
/*     */   private void setCodingStatistics() throws IOException {
/* 309 */     if (this.cxIADT == null) {
/* 310 */       this.cxIADT = new CX(512, 1);
/*     */     }
/* 312 */     if (this.cxIAFS == null) {
/* 313 */       this.cxIAFS = new CX(512, 1);
/*     */     }
/* 315 */     if (this.cxIADS == null) {
/* 316 */       this.cxIADS = new CX(512, 1);
/*     */     }
/* 318 */     if (this.cxIAIT == null) {
/* 319 */       this.cxIAIT = new CX(512, 1);
/*     */     }
/* 321 */     if (this.cxIARI == null) {
/* 322 */       this.cxIARI = new CX(512, 1);
/*     */     }
/* 324 */     if (this.cxIARDW == null) {
/* 325 */       this.cxIARDW = new CX(512, 1);
/*     */     }
/* 327 */     if (this.cxIARDH == null) {
/* 328 */       this.cxIARDH = new CX(512, 1);
/*     */     }
/* 330 */     if (this.cxIAID == null) {
/* 331 */       this.cxIAID = new CX(1 << this.symbolCodeLength, 1);
/*     */     }
/* 333 */     if (this.cxIARDX == null) {
/* 334 */       this.cxIARDX = new CX(512, 1);
/*     */     }
/* 336 */     if (this.cxIARDY == null) {
/* 337 */       this.cxIARDY = new CX(512, 1);
/*     */     }
/* 339 */     if (this.arithmeticDecoder == null) {
/* 340 */       this.arithmeticDecoder = new ArithmeticDecoder((ImageInputStream)this.subInputStream);
/*     */     }
/* 342 */     if (this.integerDecoder == null) {
/* 343 */       this.integerDecoder = new ArithmeticIntegerDecoder(this.arithmeticDecoder);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void createRegionBitmap() {
/* 349 */     int i = this.regionInfo.getBitmapWidth();
/* 350 */     int j = this.regionInfo.getBitmapHeight();
/* 351 */     this.regionBitmap = new Bitmap(i, j);
/*     */ 
/*     */     
/* 354 */     if (this.defaultPixel != 0) {
/* 355 */       Arrays.fill(this.regionBitmap.getByteArray(), (byte)-1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeStripT() throws IOException, InvalidHeaderValueException {
/* 361 */     long l = 0L;
/*     */     
/* 363 */     if (this.isHuffmanEncoded) {
/*     */       
/* 365 */       if (this.sbHuffDT == 3) {
/*     */         
/* 367 */         if (this.table == null) {
/* 368 */           byte b = 0;
/*     */           
/* 370 */           if (this.sbHuffFS == 3) {
/* 371 */             b++;
/*     */           }
/*     */           
/* 374 */           if (this.sbHuffDS == 3) {
/* 375 */             b++;
/*     */           }
/*     */           
/* 378 */           this.table = getUserTable(b);
/*     */         } 
/* 380 */         l = this.table.decode((ImageInputStream)this.subInputStream);
/*     */       } else {
/* 382 */         l = StandardTables.getTable(11 + this.sbHuffDT).decode((ImageInputStream)this.subInputStream);
/*     */       } 
/*     */     } else {
/* 385 */       l = this.integerDecoder.decode(this.cxIADT);
/*     */     } 
/*     */     
/* 388 */     return l * -this.sbStrips;
/*     */   }
/*     */ 
/*     */   
/*     */   private void decodeSymbolInstances() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 393 */     long l1 = decodeStripT();
/*     */ 
/*     */     
/* 396 */     long l2 = 0L;
/* 397 */     byte b = 0;
/*     */ 
/*     */     
/* 400 */     label17: while (b < this.amountOfSymbolInstances) {
/*     */       
/* 402 */       long l3 = decodeDT();
/* 403 */       l1 += l3;
/* 404 */       long l4 = 0L;
/*     */ 
/*     */       
/* 407 */       boolean bool = true;
/* 408 */       this.currentS = 0L;
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 413 */         if (bool) {
/*     */           
/* 415 */           l4 = decodeDfS();
/* 416 */           l2 += l4;
/* 417 */           this.currentS = l2;
/* 418 */           bool = false;
/*     */         }
/*     */         else {
/*     */           
/* 422 */           long l = decodeIdS();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 427 */           if (l == Long.MAX_VALUE) {
/*     */             continue label17;
/*     */           }
/* 430 */           this.currentS += l + this.sbdsOffset;
/*     */         } 
/*     */ 
/*     */         
/* 434 */         long l5 = decodeCurrentT();
/* 435 */         long l6 = l1 + l5;
/*     */ 
/*     */         
/* 438 */         long l7 = decodeID();
/*     */ 
/*     */         
/* 441 */         long l8 = decodeRI();
/*     */         
/* 443 */         Bitmap bitmap = decodeIb(l8, l7);
/*     */ 
/*     */         
/* 446 */         blit(bitmap, l6);
/*     */         
/* 448 */         b++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final long decodeDT() throws IOException {
/*     */     long l;
/* 457 */     if (this.isHuffmanEncoded) {
/* 458 */       if (this.sbHuffDT == 3) {
/* 459 */         l = this.table.decode((ImageInputStream)this.subInputStream);
/*     */       } else {
/* 461 */         l = StandardTables.getTable(11 + this.sbHuffDT).decode((ImageInputStream)this.subInputStream);
/*     */       } 
/*     */     } else {
/* 464 */       l = this.integerDecoder.decode(this.cxIADT);
/*     */     } 
/*     */     
/* 467 */     return l * this.sbStrips;
/*     */   }
/*     */   
/*     */   private final long decodeDfS() throws IOException, InvalidHeaderValueException {
/* 471 */     if (this.isHuffmanEncoded) {
/* 472 */       if (this.sbHuffFS == 3) {
/* 473 */         if (this.fsTable == null) {
/* 474 */           this.fsTable = getUserTable(0);
/*     */         }
/* 476 */         return this.fsTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/* 478 */       return StandardTables.getTable(6 + this.sbHuffFS).decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 481 */     return this.integerDecoder.decode(this.cxIAFS);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeIdS() throws IOException, InvalidHeaderValueException {
/* 486 */     if (this.isHuffmanEncoded) {
/* 487 */       if (this.sbHuffDS == 3) {
/*     */         
/* 489 */         if (this.dsTable == null) {
/* 490 */           byte b = 0;
/* 491 */           if (this.sbHuffFS == 3) {
/* 492 */             b++;
/*     */           }
/*     */           
/* 495 */           this.dsTable = getUserTable(b);
/*     */         } 
/* 497 */         return this.dsTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/*     */       
/* 500 */       return StandardTables.getTable(8 + this.sbHuffDS).decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 503 */     return this.integerDecoder.decode(this.cxIADS);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeCurrentT() throws IOException {
/* 508 */     if (this.sbStrips != 1) {
/* 509 */       if (this.isHuffmanEncoded) {
/* 510 */         return this.subInputStream.readBits(this.logSBStrips);
/*     */       }
/* 512 */       return this.integerDecoder.decode(this.cxIAIT);
/*     */     } 
/*     */ 
/*     */     
/* 516 */     return 0L;
/*     */   }
/*     */   
/*     */   private final long decodeID() throws IOException {
/* 520 */     if (this.isHuffmanEncoded) {
/* 521 */       if (this.symbolCodeTable == null) {
/* 522 */         return this.subInputStream.readBits(this.symbolCodeLength);
/*     */       }
/*     */       
/* 525 */       return this.symbolCodeTable.decode((ImageInputStream)this.subInputStream);
/*     */     } 
/* 527 */     return this.integerDecoder.decodeIAID(this.cxIAID, this.symbolCodeLength);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeRI() throws IOException {
/* 532 */     if (this.useRefinement) {
/* 533 */       if (this.isHuffmanEncoded) {
/* 534 */         return this.subInputStream.readBit();
/*     */       }
/* 536 */       return this.integerDecoder.decode(this.cxIARI);
/*     */     } 
/*     */     
/* 539 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final Bitmap decodeIb(long paramLong1, long paramLong2) throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/*     */     Bitmap bitmap;
/* 546 */     if (paramLong1 == 0L) {
/* 547 */       bitmap = this.symbols.get((int)paramLong2);
/*     */     } else {
/*     */       
/* 550 */       long l1 = decodeRdw();
/* 551 */       long l2 = decodeRdh();
/* 552 */       long l3 = decodeRdx();
/* 553 */       long l4 = decodeRdy();
/*     */ 
/*     */ 
/*     */       
/* 557 */       if (this.isHuffmanEncoded) {
/* 558 */         decodeSymInRefSize();
/* 559 */         this.subInputStream.skipBits();
/*     */       } 
/*     */ 
/*     */       
/* 563 */       Bitmap bitmap1 = this.symbols.get((int)paramLong2);
/* 564 */       int i = bitmap1.getWidth();
/* 565 */       int j = bitmap1.getHeight();
/*     */       
/* 567 */       int k = (int)((l1 >> 1L) + l3);
/* 568 */       int m = (int)((l2 >> 1L) + l4);
/*     */       
/* 570 */       if (this.genericRefinementRegion == null) {
/* 571 */         this.genericRefinementRegion = new GenericRefinementRegion(this.subInputStream);
/*     */       }
/*     */       
/* 574 */       this.genericRefinementRegion.setParameters(this.cx, this.arithmeticDecoder, this.sbrTemplate, (int)(i + l1), (int)(j + l2), bitmap1, k, m, false, this.sbrATX, this.sbrATY);
/*     */ 
/*     */       
/* 577 */       bitmap = this.genericRefinementRegion.getRegionBitmap();
/*     */ 
/*     */       
/* 580 */       if (this.isHuffmanEncoded) {
/* 581 */         this.subInputStream.skipBits();
/*     */       }
/*     */     } 
/* 584 */     return bitmap;
/*     */   }
/*     */   
/*     */   private final long decodeRdw() throws IOException, InvalidHeaderValueException {
/* 588 */     if (this.isHuffmanEncoded) {
/* 589 */       if (this.sbHuffRDWidth == 3) {
/*     */         
/* 591 */         if (this.rdwTable == null) {
/* 592 */           byte b = 0;
/* 593 */           if (this.sbHuffFS == 3) {
/* 594 */             b++;
/*     */           }
/*     */           
/* 597 */           if (this.sbHuffDS == 3) {
/* 598 */             b++;
/*     */           }
/*     */           
/* 601 */           if (this.sbHuffDT == 3) {
/* 602 */             b++;
/*     */           }
/*     */           
/* 605 */           this.rdwTable = getUserTable(b);
/*     */         } 
/* 607 */         return this.rdwTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/*     */       
/* 610 */       return StandardTables.getTable(14 + this.sbHuffRDWidth).decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 613 */     return this.integerDecoder.decode(this.cxIARDW);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeRdh() throws IOException, InvalidHeaderValueException {
/* 618 */     if (this.isHuffmanEncoded) {
/* 619 */       if (this.sbHuffRDHeight == 3) {
/* 620 */         if (this.rdhTable == null) {
/* 621 */           byte b = 0;
/*     */           
/* 623 */           if (this.sbHuffFS == 3) {
/* 624 */             b++;
/*     */           }
/*     */           
/* 627 */           if (this.sbHuffDS == 3) {
/* 628 */             b++;
/*     */           }
/*     */           
/* 631 */           if (this.sbHuffDT == 3) {
/* 632 */             b++;
/*     */           }
/*     */           
/* 635 */           if (this.sbHuffRDWidth == 3) {
/* 636 */             b++;
/*     */           }
/*     */           
/* 639 */           this.rdhTable = getUserTable(b);
/*     */         } 
/* 641 */         return this.rdhTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/* 643 */       return StandardTables.getTable(14 + this.sbHuffRDHeight).decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 646 */     return this.integerDecoder.decode(this.cxIARDH);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeRdx() throws IOException, InvalidHeaderValueException {
/* 651 */     if (this.isHuffmanEncoded) {
/* 652 */       if (this.sbHuffRDX == 3) {
/* 653 */         if (this.rdxTable == null) {
/* 654 */           byte b = 0;
/* 655 */           if (this.sbHuffFS == 3) {
/* 656 */             b++;
/*     */           }
/*     */           
/* 659 */           if (this.sbHuffDS == 3) {
/* 660 */             b++;
/*     */           }
/*     */           
/* 663 */           if (this.sbHuffDT == 3) {
/* 664 */             b++;
/*     */           }
/*     */           
/* 667 */           if (this.sbHuffRDWidth == 3) {
/* 668 */             b++;
/*     */           }
/*     */           
/* 671 */           if (this.sbHuffRDHeight == 3) {
/* 672 */             b++;
/*     */           }
/*     */           
/* 675 */           this.rdxTable = getUserTable(b);
/*     */         } 
/* 677 */         return this.rdxTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/* 679 */       return StandardTables.getTable(14 + this.sbHuffRDX).decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 682 */     return this.integerDecoder.decode(this.cxIARDX);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeRdy() throws IOException, InvalidHeaderValueException {
/* 687 */     if (this.isHuffmanEncoded) {
/* 688 */       if (this.sbHuffRDY == 3) {
/* 689 */         if (this.rdyTable == null) {
/* 690 */           byte b = 0;
/* 691 */           if (this.sbHuffFS == 3) {
/* 692 */             b++;
/*     */           }
/*     */           
/* 695 */           if (this.sbHuffDS == 3) {
/* 696 */             b++;
/*     */           }
/*     */           
/* 699 */           if (this.sbHuffDT == 3) {
/* 700 */             b++;
/*     */           }
/*     */           
/* 703 */           if (this.sbHuffRDWidth == 3) {
/* 704 */             b++;
/*     */           }
/*     */           
/* 707 */           if (this.sbHuffRDHeight == 3) {
/* 708 */             b++;
/*     */           }
/*     */           
/* 711 */           if (this.sbHuffRDX == 3) {
/* 712 */             b++;
/*     */           }
/*     */           
/* 715 */           this.rdyTable = getUserTable(b);
/*     */         } 
/* 717 */         return this.rdyTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/* 719 */       return StandardTables.getTable(14 + this.sbHuffRDY).decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 722 */     return this.integerDecoder.decode(this.cxIARDY);
/*     */   }
/*     */ 
/*     */   
/*     */   private final long decodeSymInRefSize() throws IOException, InvalidHeaderValueException {
/* 727 */     if (this.sbHuffRSize == 0) {
/* 728 */       return StandardTables.getTable(1).decode((ImageInputStream)this.subInputStream);
/*     */     }
/* 730 */     if (this.rSizeTable == null) {
/* 731 */       byte b = 0;
/*     */       
/* 733 */       if (this.sbHuffFS == 3) {
/* 734 */         b++;
/*     */       }
/*     */       
/* 737 */       if (this.sbHuffDS == 3) {
/* 738 */         b++;
/*     */       }
/*     */       
/* 741 */       if (this.sbHuffDT == 3) {
/* 742 */         b++;
/*     */       }
/*     */       
/* 745 */       if (this.sbHuffRDWidth == 3) {
/* 746 */         b++;
/*     */       }
/*     */       
/* 749 */       if (this.sbHuffRDHeight == 3) {
/* 750 */         b++;
/*     */       }
/*     */       
/* 753 */       if (this.sbHuffRDX == 3) {
/* 754 */         b++;
/*     */       }
/*     */       
/* 757 */       if (this.sbHuffRDY == 3) {
/* 758 */         b++;
/*     */       }
/*     */       
/* 761 */       this.rSizeTable = getUserTable(b);
/*     */     } 
/* 763 */     return this.rSizeTable.decode((ImageInputStream)this.subInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void blit(Bitmap paramBitmap, long paramLong) {
/* 769 */     if (this.isTransposed == 0 && (this.referenceCorner == 2 || this.referenceCorner == 3)) {
/* 770 */       this.currentS += (paramBitmap.getWidth() - 1);
/* 771 */     } else if (this.isTransposed == 1 && (this.referenceCorner == 0 || this.referenceCorner == 2)) {
/* 772 */       this.currentS += (paramBitmap.getHeight() - 1);
/*     */     } 
/*     */ 
/*     */     
/* 776 */     long l = this.currentS;
/*     */ 
/*     */     
/* 779 */     if (this.isTransposed == 1) {
/* 780 */       long l1 = paramLong;
/* 781 */       paramLong = l;
/* 782 */       l = l1;
/*     */     } 
/*     */     
/* 785 */     if (this.referenceCorner != 1) {
/* 786 */       if (this.referenceCorner == 0) {
/*     */         
/* 788 */         paramLong -= (paramBitmap.getHeight() - 1);
/* 789 */       } else if (this.referenceCorner == 2) {
/*     */         
/* 791 */         paramLong -= (paramBitmap.getHeight() - 1);
/* 792 */         l -= (paramBitmap.getWidth() - 1);
/* 793 */       } else if (this.referenceCorner == 3) {
/*     */         
/* 795 */         l -= (paramBitmap.getWidth() - 1);
/*     */       } 
/*     */     }
/*     */     
/* 799 */     Bitmaps.blit(paramBitmap, this.regionBitmap, (int)l, (int)paramLong, this.combinationOperator);
/*     */ 
/*     */     
/* 802 */     if (this.isTransposed == 0 && (this.referenceCorner == 0 || this.referenceCorner == 1)) {
/* 803 */       this.currentS += (paramBitmap.getWidth() - 1);
/*     */     }
/*     */     
/* 806 */     if (this.isTransposed == 1 && (this.referenceCorner == 1 || this.referenceCorner == 3)) {
/* 807 */       this.currentS += (paramBitmap.getHeight() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void initSymbols() throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 813 */     for (SegmentHeader segmentHeader : this.segmentHeader.getRtSegments()) {
/* 814 */       if (segmentHeader.getSegmentType() == 0) {
/* 815 */         SymbolDictionary symbolDictionary = (SymbolDictionary)segmentHeader.getSegmentData();
/*     */         
/* 817 */         symbolDictionary.cxIAID = this.cxIAID;
/* 818 */         this.symbols.addAll(symbolDictionary.getDictionary());
/*     */       } 
/*     */     } 
/* 821 */     this.amountOfSymbols = this.symbols.size();
/*     */   }
/*     */   
/*     */   private HuffmanTable getUserTable(int paramInt) throws InvalidHeaderValueException, IOException {
/* 825 */     SegmentHeader segmentHeader = this.segmentHeader.getRtSegments()[paramInt];
/* 826 */     Table table = (Table)segmentHeader.getSegmentData();
/* 827 */     return (HuffmanTable)new EncodedTable(table);
/*     */   }
/*     */ 
/*     */   
/*     */   private void symbolIDCodeLengths() throws IOException {
/* 832 */     ArrayList<HuffmanTable.Code> arrayList1 = new ArrayList();
/*     */     
/* 834 */     for (byte b1 = 0; b1 < 35; b1++) {
/* 835 */       int i = (int)(this.subInputStream.readBits(4) & 0xFL);
/* 836 */       if (i > 0) {
/* 837 */         arrayList1.add(new HuffmanTable.Code(i, 0, b1, false));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 844 */     FixedSizeTable fixedSizeTable = new FixedSizeTable(arrayList1);
/*     */ 
/*     */     
/* 847 */     long l = 0L;
/*     */     
/* 849 */     byte b2 = 0;
/* 850 */     ArrayList<HuffmanTable.Code> arrayList2 = new ArrayList();
/* 851 */     while (b2 < this.amountOfSymbols) {
/* 852 */       long l1 = fixedSizeTable.decode((ImageInputStream)this.subInputStream);
/* 853 */       if (l1 < 32L) {
/* 854 */         if (l1 > 0L) {
/* 855 */           arrayList2.add(new HuffmanTable.Code((int)l1, 0, b2, false));
/*     */         }
/*     */         
/* 858 */         l = l1;
/* 859 */         b2++;
/*     */         continue;
/*     */       } 
/* 862 */       long l2 = 0L;
/* 863 */       long l3 = 0L;
/* 864 */       if (l1 == 32L) {
/* 865 */         l2 = 3L + this.subInputStream.readBits(2);
/* 866 */         if (b2 > 0) {
/* 867 */           l3 = l;
/*     */         }
/* 869 */       } else if (l1 == 33L) {
/* 870 */         l2 = 3L + this.subInputStream.readBits(3);
/* 871 */       } else if (l1 == 34L) {
/* 872 */         l2 = 11L + this.subInputStream.readBits(7);
/*     */       } 
/*     */       
/* 875 */       for (byte b = 0; b < l2; b++) {
/* 876 */         if (l3 > 0L) {
/* 877 */           arrayList2.add(new HuffmanTable.Code((int)l3, 0, b2, false));
/*     */         }
/* 879 */         b2++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 885 */     this.subInputStream.skipBits();
/*     */ 
/*     */     
/* 888 */     this.symbolCodeTable = new FixedSizeTable(arrayList2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IntegerMaxValueException, IOException {
/* 894 */     this.segmentHeader = paramSegmentHeader;
/* 895 */     this.subInputStream = paramSubInputStream;
/* 896 */     this.regionInfo = new RegionSegmentInformation(this.subInputStream);
/* 897 */     parseHeader();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setContexts(CX paramCX1, CX paramCX2, CX paramCX3, CX paramCX4, CX paramCX5, CX paramCX6, CX paramCX7, CX paramCX8, CX paramCX9, CX paramCX10) {
/* 902 */     this.cx = paramCX1;
/*     */     
/* 904 */     this.cxIADT = paramCX2;
/* 905 */     this.cxIAFS = paramCX3;
/* 906 */     this.cxIADS = paramCX4;
/* 907 */     this.cxIAIT = paramCX5;
/*     */     
/* 909 */     this.cxIAID = paramCX6;
/*     */     
/* 911 */     this.cxIARDW = paramCX7;
/* 912 */     this.cxIARDH = paramCX8;
/* 913 */     this.cxIARDX = paramCX9;
/* 914 */     this.cxIARDY = paramCX10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setParameters(ArithmeticDecoder paramArithmeticDecoder, ArithmeticIntegerDecoder paramArithmeticIntegerDecoder, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4, short paramShort1, short paramShort2, short paramShort3, short paramShort4, short paramShort5, short paramShort6, short paramShort7, short paramShort8, short paramShort9, short paramShort10, short paramShort11, short paramShort12, short paramShort13, short paramShort14, short[] paramArrayOfshort1, short[] paramArrayOfshort2, ArrayList<Bitmap> paramArrayList, int paramInt5) {
/* 924 */     this.arithmeticDecoder = paramArithmeticDecoder;
/*     */     
/* 926 */     this.integerDecoder = paramArithmeticIntegerDecoder;
/*     */     
/* 928 */     this.isHuffmanEncoded = paramBoolean1;
/* 929 */     this.useRefinement = paramBoolean2;
/*     */     
/* 931 */     this.regionInfo.setBitmapWidth(paramInt1);
/* 932 */     this.regionInfo.setBitmapHeight(paramInt2);
/*     */     
/* 934 */     this.amountOfSymbolInstances = paramLong;
/* 935 */     this.sbStrips = paramInt3;
/* 936 */     this.amountOfSymbols = paramInt4;
/* 937 */     this.defaultPixel = paramShort1;
/* 938 */     this.combinationOperator = CombinationOperator.translateOperatorCodeToEnum(paramShort2);
/* 939 */     this.isTransposed = paramShort3;
/* 940 */     this.referenceCorner = paramShort4;
/* 941 */     this.sbdsOffset = paramShort5;
/*     */     
/* 943 */     this.sbHuffFS = paramShort6;
/* 944 */     this.sbHuffDS = paramShort7;
/* 945 */     this.sbHuffDT = paramShort8;
/* 946 */     this.sbHuffRDWidth = paramShort9;
/* 947 */     this.sbHuffRDHeight = paramShort10;
/* 948 */     this.sbHuffRDX = paramShort11;
/* 949 */     this.sbHuffRDY = paramShort12;
/* 950 */     this.sbHuffRSize = paramShort13;
/*     */     
/* 952 */     this.sbrTemplate = paramShort14;
/* 953 */     this.sbrATX = paramArrayOfshort1;
/* 954 */     this.sbrATY = paramArrayOfshort2;
/*     */     
/* 956 */     this.symbols = paramArrayList;
/* 957 */     this.symbolCodeLength = paramInt5;
/*     */   }
/*     */   
/*     */   public RegionSegmentInformation getRegionInfo() {
/* 961 */     return this.regionInfo;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/TextRegion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */