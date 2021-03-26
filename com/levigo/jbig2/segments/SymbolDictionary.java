/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.Dictionary;
/*     */ import com.levigo.jbig2.Region;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.decoder.arithmetic.ArithmeticDecoder;
/*     */ import com.levigo.jbig2.decoder.arithmetic.ArithmeticIntegerDecoder;
/*     */ import com.levigo.jbig2.decoder.arithmetic.CX;
/*     */ import com.levigo.jbig2.decoder.huffman.EncodedTable;
/*     */ import com.levigo.jbig2.decoder.huffman.HuffmanTable;
/*     */ import com.levigo.jbig2.decoder.huffman.StandardTables;
/*     */ import com.levigo.jbig2.err.IntegerMaxValueException;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.image.Bitmaps;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ public class SymbolDictionary
/*     */   implements Dictionary
/*     */ {
/*  48 */   private final Logger log = LoggerFactory.getLogger(SymbolDictionary.class);
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private short sdrTemplate;
/*     */   
/*     */   private byte sdTemplate;
/*     */   
/*     */   private boolean isCodingContextRetained;
/*     */   
/*     */   private boolean isCodingContextUsed;
/*     */   
/*     */   private short sdHuffAggInstanceSelection;
/*     */   
/*     */   private short sdHuffBMSizeSelection;
/*     */   
/*     */   private short sdHuffDecodeWidthSelection;
/*     */   
/*     */   private short sdHuffDecodeHeightSelection;
/*     */   
/*     */   private boolean useRefinementAggregation;
/*     */   
/*     */   private boolean isHuffmanEncoded;
/*     */   
/*     */   private short[] sdATX;
/*     */   
/*     */   private short[] sdATY;
/*     */   
/*     */   private short[] sdrATX;
/*     */   
/*     */   private short[] sdrATY;
/*     */   
/*     */   private int amountOfExportSymbolss;
/*     */   
/*     */   private int amountOfNewSymbolss;
/*     */   
/*     */   private SegmentHeader segmentHeader;
/*     */   
/*     */   private int amountOfImportedSymbolss;
/*     */   
/*     */   private ArrayList<Bitmap> importSymbols;
/*     */   
/*     */   private int amountOfDecodedSymbols;
/*     */   
/*     */   private Bitmap[] newSymbols;
/*     */   private HuffmanTable dhTable;
/*     */   private HuffmanTable dwTable;
/*     */   private HuffmanTable bmSizeTable;
/*     */   private HuffmanTable aggInstTable;
/*     */   private ArrayList<Bitmap> exportSymbols;
/*     */   private ArrayList<Bitmap> sbSymbols;
/*     */   private ArithmeticDecoder arithmeticDecoder;
/*     */   private ArithmeticIntegerDecoder iDecoder;
/*     */   private TextRegion textRegion;
/*     */   private GenericRegion genericRegion;
/*     */   private GenericRefinementRegion genericRefinementRegion;
/*     */   private CX cx;
/*     */   private CX cxIADH;
/*     */   private CX cxIADW;
/*     */   private CX cxIAAI;
/*     */   private CX cxIAEX;
/*     */   private CX cxIARDX;
/*     */   private CX cxIARDY;
/*     */   private CX cxIADT;
/*     */   protected CX cxIAID;
/*     */   private int sbSymCodeLen;
/*     */   
/*     */   public SymbolDictionary() {}
/*     */   
/*     */   public SymbolDictionary(SubInputStream paramSubInputStream, SegmentHeader paramSegmentHeader) throws IOException {
/* 118 */     this.subInputStream = paramSubInputStream;
/* 119 */     this.segmentHeader = paramSegmentHeader;
/*     */   }
/*     */   
/*     */   private void parseHeader() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 123 */     readRegionFlags();
/* 124 */     setAtPixels();
/* 125 */     setRefinementAtPixels();
/* 126 */     readAmountOfExportedSymbols();
/* 127 */     readAmountOfNewSymbols();
/* 128 */     setInSyms();
/*     */     
/* 130 */     if (this.isCodingContextUsed) {
/* 131 */       SegmentHeader[] arrayOfSegmentHeader = this.segmentHeader.getRtSegments();
/*     */       
/* 133 */       for (int i = arrayOfSegmentHeader.length - 1; i >= 0; i--) {
/*     */         
/* 135 */         if (arrayOfSegmentHeader[i].getSegmentType() == 0) {
/* 136 */           SymbolDictionary symbolDictionary = (SymbolDictionary)arrayOfSegmentHeader[i].getSegmentData();
/*     */           
/* 138 */           if (symbolDictionary.isCodingContextRetained)
/*     */           {
/* 140 */             setRetainedCodingContexts(symbolDictionary);
/*     */           }
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 147 */     checkInput();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readRegionFlags() throws IOException {
/* 152 */     this.subInputStream.readBits(3);
/*     */ 
/*     */     
/* 155 */     this.sdrTemplate = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 158 */     this.sdTemplate = (byte)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 161 */     if (this.subInputStream.readBit() == 1) {
/* 162 */       this.isCodingContextRetained = true;
/*     */     }
/*     */ 
/*     */     
/* 166 */     if (this.subInputStream.readBit() == 1) {
/* 167 */       this.isCodingContextUsed = true;
/*     */     }
/*     */ 
/*     */     
/* 171 */     this.sdHuffAggInstanceSelection = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 174 */     this.sdHuffBMSizeSelection = (short)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 177 */     this.sdHuffDecodeWidthSelection = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 180 */     this.sdHuffDecodeHeightSelection = (short)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 183 */     if (this.subInputStream.readBit() == 1) {
/* 184 */       this.useRefinementAggregation = true;
/*     */     }
/*     */ 
/*     */     
/* 188 */     if (this.subInputStream.readBit() == 1) {
/* 189 */       this.isHuffmanEncoded = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void setAtPixels() throws IOException {
/* 194 */     if (!this.isHuffmanEncoded) {
/* 195 */       if (this.sdTemplate == 0) {
/* 196 */         readAtPixels(4);
/*     */       } else {
/* 198 */         readAtPixels(1);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRefinementAtPixels() throws IOException {
/* 204 */     if (this.useRefinementAggregation && this.sdrTemplate == 0) {
/* 205 */       readRefinementAtPixels(2);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readAtPixels(int paramInt) throws IOException {
/* 210 */     this.sdATX = new short[paramInt];
/* 211 */     this.sdATY = new short[paramInt];
/*     */     
/* 213 */     for (byte b = 0; b < paramInt; b++) {
/* 214 */       this.sdATX[b] = (short)this.subInputStream.readByte();
/* 215 */       this.sdATY[b] = (short)this.subInputStream.readByte();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readRefinementAtPixels(int paramInt) throws IOException {
/* 220 */     this.sdrATX = new short[paramInt];
/* 221 */     this.sdrATY = new short[paramInt];
/*     */     
/* 223 */     for (byte b = 0; b < paramInt; b++) {
/* 224 */       this.sdrATX[b] = (short)this.subInputStream.readByte();
/* 225 */       this.sdrATY[b] = (short)this.subInputStream.readByte();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readAmountOfExportedSymbols() throws IOException {
/* 230 */     this.amountOfExportSymbolss = (int)this.subInputStream.readBits(32);
/*     */   }
/*     */   
/*     */   private void readAmountOfNewSymbols() throws IOException {
/* 234 */     this.amountOfNewSymbolss = (int)this.subInputStream.readBits(32);
/*     */   }
/*     */   
/*     */   private void setInSyms() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 238 */     if (this.segmentHeader.getRtSegments() != null) {
/* 239 */       retrieveImportSymbols();
/*     */     } else {
/* 241 */       this.importSymbols = new ArrayList<>();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRetainedCodingContexts(SymbolDictionary paramSymbolDictionary) {
/* 246 */     this.arithmeticDecoder = paramSymbolDictionary.arithmeticDecoder;
/* 247 */     this.isHuffmanEncoded = paramSymbolDictionary.isHuffmanEncoded;
/* 248 */     this.useRefinementAggregation = paramSymbolDictionary.useRefinementAggregation;
/* 249 */     this.sdTemplate = paramSymbolDictionary.sdTemplate;
/* 250 */     this.sdrTemplate = paramSymbolDictionary.sdrTemplate;
/* 251 */     this.sdATX = paramSymbolDictionary.sdATX;
/* 252 */     this.sdATY = paramSymbolDictionary.sdATY;
/* 253 */     this.sdrATX = paramSymbolDictionary.sdrATX;
/* 254 */     this.sdrATY = paramSymbolDictionary.sdrATY;
/* 255 */     this.cx = paramSymbolDictionary.cx;
/*     */   }
/*     */   
/*     */   private void checkInput() throws InvalidHeaderValueException {
/* 259 */     if (this.sdHuffDecodeHeightSelection == 2) {
/* 260 */       this.log.info("sdHuffDecodeHeightSelection = " + this.sdHuffDecodeHeightSelection + " (value not permitted)");
/*     */     }
/*     */     
/* 263 */     if (this.sdHuffDecodeWidthSelection == 2) {
/* 264 */       this.log.info("sdHuffDecodeWidthSelection = " + this.sdHuffDecodeWidthSelection + " (value not permitted)");
/*     */     }
/*     */     
/* 267 */     if (this.isHuffmanEncoded) {
/* 268 */       if (this.sdTemplate != 0) {
/* 269 */         this.log.info("sdTemplate = " + this.sdTemplate + " (should be 0)");
/* 270 */         this.sdTemplate = 0;
/*     */       } 
/* 272 */       if (!this.useRefinementAggregation) {
/* 273 */         if (this.isCodingContextRetained) {
/* 274 */           this.log.info("isCodingContextRetained = " + this.isCodingContextRetained + " (should be 0)");
/* 275 */           this.isCodingContextRetained = false;
/*     */         } 
/*     */         
/* 278 */         if (this.isCodingContextUsed) {
/* 279 */           this.log.info("isCodingContextUsed = " + this.isCodingContextUsed + " (should be 0)");
/* 280 */           this.isCodingContextUsed = false;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 285 */       if (this.sdHuffBMSizeSelection != 0) {
/* 286 */         this.log.info("sdHuffBMSizeSelection should be 0");
/* 287 */         this.sdHuffBMSizeSelection = 0;
/*     */       } 
/* 289 */       if (this.sdHuffDecodeWidthSelection != 0) {
/* 290 */         this.log.info("sdHuffDecodeWidthSelection should be 0");
/* 291 */         this.sdHuffDecodeWidthSelection = 0;
/*     */       } 
/* 293 */       if (this.sdHuffDecodeHeightSelection != 0) {
/* 294 */         this.log.info("sdHuffDecodeHeightSelection should be 0");
/* 295 */         this.sdHuffDecodeHeightSelection = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 299 */     if (!this.useRefinementAggregation && 
/* 300 */       this.sdrTemplate != 0) {
/* 301 */       this.log.info("sdrTemplate = " + this.sdrTemplate + " (should be 0)");
/* 302 */       this.sdrTemplate = 0;
/*     */     } 
/*     */ 
/*     */     
/* 306 */     if ((!this.isHuffmanEncoded || !this.useRefinementAggregation) && 
/* 307 */       this.sdHuffAggInstanceSelection != 0) {
/* 308 */       this.log.info("sdHuffAggInstanceSelection = " + this.sdHuffAggInstanceSelection + " (should be 0)");
/* 309 */       this.sdHuffAggInstanceSelection = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Bitmap> getDictionary() throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 320 */     long l = System.currentTimeMillis();
/* 321 */     if (null == this.exportSymbols) {
/*     */       
/* 323 */       if (this.useRefinementAggregation) {
/* 324 */         this.sbSymCodeLen = getSbSymCodeLen();
/*     */       }
/* 326 */       if (!this.isHuffmanEncoded) {
/* 327 */         setCodingStatistics();
/*     */       }
/*     */ 
/*     */       
/* 331 */       this.newSymbols = new Bitmap[this.amountOfNewSymbolss];
/*     */ 
/*     */       
/* 334 */       int[] arrayOfInt1 = null;
/* 335 */       if (this.isHuffmanEncoded && !this.useRefinementAggregation) {
/* 336 */         arrayOfInt1 = new int[this.amountOfNewSymbolss];
/*     */       }
/*     */       
/* 339 */       setSymbolsArray();
/*     */ 
/*     */       
/* 342 */       int i = 0;
/* 343 */       this.amountOfDecodedSymbols = 0;
/*     */ 
/*     */       
/* 346 */       while (this.amountOfDecodedSymbols != this.amountOfNewSymbolss) {
/*     */ 
/*     */         
/* 349 */         i = (int)(i + decodeHeightClassDeltaHeight());
/* 350 */         int j = 0;
/* 351 */         int k = 0;
/* 352 */         int m = this.amountOfDecodedSymbols;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         while (true) {
/* 359 */           long l1 = decodeDifferenceWidth();
/*     */ 
/*     */ 
/*     */           
/* 363 */           if (l1 == Long.MAX_VALUE) {
/*     */             break;
/*     */           }
/*     */           
/* 367 */           j = (int)(j + l1);
/* 368 */           k += j;
/*     */ 
/*     */           
/* 371 */           if (!this.isHuffmanEncoded || this.useRefinementAggregation) {
/* 372 */             if (!this.useRefinementAggregation) {
/*     */               
/* 374 */               decodeDirectlyThroughGenericRegion(j, i);
/*     */             } else {
/*     */               
/* 377 */               decodeAggregate(j, i);
/*     */             } 
/* 379 */           } else if (this.isHuffmanEncoded && !this.useRefinementAggregation) {
/*     */             
/* 381 */             arrayOfInt1[this.amountOfDecodedSymbols] = j;
/*     */           } 
/* 383 */           this.amountOfDecodedSymbols++;
/*     */         } 
/*     */ 
/*     */         
/* 387 */         if (this.isHuffmanEncoded && !this.useRefinementAggregation) {
/*     */           long l1;
/*     */           
/* 390 */           if (this.sdHuffBMSizeSelection == 0) {
/* 391 */             l1 = StandardTables.getTable(1).decode((ImageInputStream)this.subInputStream);
/*     */           } else {
/* 393 */             l1 = huffDecodeBmSize();
/*     */           } 
/*     */           
/* 396 */           this.subInputStream.skipBits();
/*     */           
/* 398 */           Bitmap bitmap = decodeHeightClassCollectiveBitmap(l1, i, k);
/*     */ 
/*     */           
/* 401 */           this.subInputStream.skipBits();
/* 402 */           decodeHeightClassBitmap(bitmap, m, i, arrayOfInt1);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 410 */       int[] arrayOfInt2 = getToExportFlags();
/*     */ 
/*     */       
/* 413 */       setExportedSymbols(arrayOfInt2);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 421 */     return this.exportSymbols;
/*     */   }
/*     */   
/*     */   private void setCodingStatistics() throws IOException {
/* 425 */     if (this.cxIADT == null) {
/* 426 */       this.cxIADT = new CX(512, 1);
/*     */     }
/*     */     
/* 429 */     if (this.cxIADH == null) {
/* 430 */       this.cxIADH = new CX(512, 1);
/*     */     }
/*     */     
/* 433 */     if (this.cxIADW == null) {
/* 434 */       this.cxIADW = new CX(512, 1);
/*     */     }
/*     */     
/* 437 */     if (this.cxIAAI == null) {
/* 438 */       this.cxIAAI = new CX(512, 1);
/*     */     }
/*     */     
/* 441 */     if (this.cxIAEX == null) {
/* 442 */       this.cxIAEX = new CX(512, 1);
/*     */     }
/*     */     
/* 445 */     if (this.useRefinementAggregation && this.cxIAID == null) {
/* 446 */       this.cxIAID = new CX(1 << this.sbSymCodeLen, 1);
/* 447 */       this.cxIARDX = new CX(512, 1);
/* 448 */       this.cxIARDY = new CX(512, 1);
/*     */     } 
/*     */     
/* 451 */     if (this.cx == null) {
/* 452 */       this.cx = new CX(65536, 1);
/*     */     }
/*     */     
/* 455 */     if (this.arithmeticDecoder == null) {
/* 456 */       this.arithmeticDecoder = new ArithmeticDecoder((ImageInputStream)this.subInputStream);
/*     */     }
/*     */     
/* 459 */     if (this.iDecoder == null) {
/* 460 */       this.iDecoder = new ArithmeticIntegerDecoder(this.arithmeticDecoder);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void decodeHeightClassBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int[] paramArrayOfint) throws IntegerMaxValueException, InvalidHeaderValueException, IOException {
/* 469 */     for (int i = paramInt1; i < this.amountOfDecodedSymbols; i++) {
/* 470 */       int j = 0;
/*     */       
/* 472 */       for (int k = paramInt1; k <= i - 1; k++) {
/* 473 */         j += paramArrayOfint[k];
/*     */       }
/*     */       
/* 476 */       Rectangle rectangle = new Rectangle(j, 0, paramArrayOfint[i], paramInt2);
/* 477 */       Bitmap bitmap = Bitmaps.extract(rectangle, paramBitmap);
/* 478 */       this.newSymbols[i] = bitmap;
/* 479 */       this.sbSymbols.add(bitmap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void decodeAggregate(int paramInt1, int paramInt2) throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/*     */     long l;
/* 488 */     if (this.isHuffmanEncoded) {
/* 489 */       this.log.info("Refinement or aggregate-coded symbols may couse problems with huffman decoding!");
/* 490 */       l = huffDecodeRefAggNInst();
/*     */     } else {
/* 492 */       l = this.iDecoder.decode(this.cxIAAI);
/*     */     } 
/*     */     
/* 495 */     if (l > 1L) {
/*     */       
/* 497 */       decodeThroughTextRegion(paramInt1, paramInt2, l);
/* 498 */     } else if (l == 1L) {
/*     */       
/* 500 */       decodeRefinedSymbol(paramInt1, paramInt2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final long huffDecodeRefAggNInst() throws IOException, InvalidHeaderValueException {
/* 505 */     if (this.sdHuffAggInstanceSelection == 0)
/* 506 */       return StandardTables.getTable(1).decode((ImageInputStream)this.subInputStream); 
/* 507 */     if (this.sdHuffAggInstanceSelection == 1) {
/* 508 */       if (this.aggInstTable == null) {
/* 509 */         byte b = 0;
/*     */         
/* 511 */         if (this.sdHuffDecodeHeightSelection == 3) {
/* 512 */           b++;
/*     */         }
/* 514 */         if (this.sdHuffDecodeWidthSelection == 3) {
/* 515 */           b++;
/*     */         }
/* 517 */         if (this.sdHuffBMSizeSelection == 3) {
/* 518 */           b++;
/*     */         }
/*     */         
/* 521 */         this.aggInstTable = getUserTable(b);
/*     */       } 
/* 523 */       return this.aggInstTable.decode((ImageInputStream)this.subInputStream);
/*     */     } 
/* 525 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void decodeThroughTextRegion(int paramInt1, int paramInt2, long paramLong) throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 531 */     if (this.textRegion == null) {
/* 532 */       this.textRegion = new TextRegion(this.subInputStream, null);
/*     */       
/* 534 */       this.textRegion.setContexts(this.cx, new CX(512, 1), new CX(512, 1), new CX(512, 1), new CX(512, 1), this.cxIAID, new CX(512, 1), new CX(512, 1), new CX(512, 1), new CX(512, 1));
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
/*     */     
/* 548 */     setSymbolsArray();
/*     */ 
/*     */     
/* 551 */     this.textRegion.setParameters(this.arithmeticDecoder, this.iDecoder, this.isHuffmanEncoded, true, paramInt1, paramInt2, paramLong, 1, this.amountOfImportedSymbolss + this.amountOfDecodedSymbols, (short)0, (short)0, (short)0, (short)1, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, (short)0, this.sdrTemplate, this.sdrATX, this.sdrATY, this.sbSymbols, this.sbSymCodeLen);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 556 */     addSymbol(this.textRegion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void decodeRefinedSymbol(int paramInt1, int paramInt2) throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/*     */     int i, j, k;
/* 566 */     if (this.isHuffmanEncoded) {
/*     */       
/* 568 */       i = (int)this.subInputStream.readBits(this.sbSymCodeLen);
/* 569 */       j = (int)StandardTables.getTable(15).decode((ImageInputStream)this.subInputStream);
/* 570 */       k = (int)StandardTables.getTable(15).decode((ImageInputStream)this.subInputStream);
/*     */ 
/*     */       
/* 573 */       StandardTables.getTable(1).decode((ImageInputStream)this.subInputStream);
/*     */ 
/*     */       
/* 576 */       this.subInputStream.skipBits();
/*     */     } else {
/*     */       
/* 579 */       i = this.iDecoder.decodeIAID(this.cxIAID, this.sbSymCodeLen);
/* 580 */       j = (int)this.iDecoder.decode(this.cxIARDX);
/* 581 */       k = (int)this.iDecoder.decode(this.cxIARDY);
/*     */     } 
/*     */ 
/*     */     
/* 585 */     setSymbolsArray();
/* 586 */     Bitmap bitmap = this.sbSymbols.get(i);
/* 587 */     decodeNewSymbols(paramInt1, paramInt2, bitmap, j, k);
/*     */ 
/*     */     
/* 590 */     if (this.isHuffmanEncoded) {
/* 591 */       this.subInputStream.skipBits();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void decodeNewSymbols(int paramInt1, int paramInt2, Bitmap paramBitmap, int paramInt3, int paramInt4) throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 598 */     if (this.genericRefinementRegion == null) {
/* 599 */       this.genericRefinementRegion = new GenericRefinementRegion(this.subInputStream);
/*     */       
/* 601 */       if (this.arithmeticDecoder == null) {
/* 602 */         this.arithmeticDecoder = new ArithmeticDecoder((ImageInputStream)this.subInputStream);
/*     */       }
/*     */       
/* 605 */       if (this.cx == null) {
/* 606 */         this.cx = new CX(65536, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 611 */     this.genericRefinementRegion.setParameters(this.cx, this.arithmeticDecoder, this.sdrTemplate, paramInt1, paramInt2, paramBitmap, paramInt3, paramInt4, false, this.sdrATX, this.sdrATY);
/*     */ 
/*     */     
/* 614 */     addSymbol(this.genericRefinementRegion);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void decodeDirectlyThroughGenericRegion(int paramInt1, int paramInt2) throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 619 */     if (this.genericRegion == null) {
/* 620 */       this.genericRegion = new GenericRegion(this.subInputStream);
/*     */     }
/*     */ 
/*     */     
/* 624 */     this.genericRegion.setParameters(false, this.sdTemplate, false, false, this.sdATX, this.sdATY, paramInt1, paramInt2, this.cx, this.arithmeticDecoder);
/*     */ 
/*     */     
/* 627 */     addSymbol(this.genericRegion);
/*     */   }
/*     */ 
/*     */   
/*     */   private final void addSymbol(Region paramRegion) throws IntegerMaxValueException, InvalidHeaderValueException, IOException {
/* 632 */     Bitmap bitmap = paramRegion.getRegionBitmap();
/* 633 */     this.newSymbols[this.amountOfDecodedSymbols] = bitmap;
/* 634 */     this.sbSymbols.add(bitmap);
/*     */   }
/*     */   
/*     */   private final long decodeDifferenceWidth() throws IOException, InvalidHeaderValueException {
/* 638 */     if (this.isHuffmanEncoded) {
/* 639 */       switch (this.sdHuffDecodeWidthSelection) {
/*     */         case 0:
/* 641 */           return StandardTables.getTable(2).decode((ImageInputStream)this.subInputStream);
/*     */         case 1:
/* 643 */           return StandardTables.getTable(3).decode((ImageInputStream)this.subInputStream);
/*     */         case 3:
/* 645 */           if (this.dwTable == null) {
/* 646 */             byte b = 0;
/*     */             
/* 648 */             if (this.sdHuffDecodeHeightSelection == 3) {
/* 649 */               b++;
/*     */             }
/* 651 */             this.dwTable = getUserTable(b);
/*     */           } 
/*     */           
/* 654 */           return this.dwTable.decode((ImageInputStream)this.subInputStream);
/*     */       } 
/*     */     } else {
/* 657 */       return this.iDecoder.decode(this.cxIADW);
/*     */     } 
/* 659 */     return 0L;
/*     */   }
/*     */   
/*     */   private final long decodeHeightClassDeltaHeight() throws IOException, InvalidHeaderValueException {
/* 663 */     if (this.isHuffmanEncoded) {
/* 664 */       return decodeHeightClassDeltaHeightWithHuffman();
/*     */     }
/* 666 */     return this.iDecoder.decode(this.cxIADH);
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
/*     */   private final long decodeHeightClassDeltaHeightWithHuffman() throws IOException, InvalidHeaderValueException {
/* 678 */     switch (this.sdHuffDecodeHeightSelection) {
/*     */       case 0:
/* 680 */         return StandardTables.getTable(4).decode((ImageInputStream)this.subInputStream);
/*     */       case 1:
/* 682 */         return StandardTables.getTable(5).decode((ImageInputStream)this.subInputStream);
/*     */       case 3:
/* 684 */         if (this.dhTable == null) {
/* 685 */           this.dhTable = getUserTable(0);
/*     */         }
/* 687 */         return this.dhTable.decode((ImageInputStream)this.subInputStream);
/*     */     } 
/*     */     
/* 690 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   private final Bitmap decodeHeightClassCollectiveBitmap(long paramLong, int paramInt1, int paramInt2) throws IOException {
/* 695 */     if (paramLong == 0L) {
/* 696 */       Bitmap bitmap = new Bitmap(paramInt2, paramInt1);
/*     */       
/* 698 */       for (byte b = 0; b < (bitmap.getByteArray()).length; b++) {
/* 699 */         bitmap.setByte(b, this.subInputStream.readByte());
/*     */       }
/*     */       
/* 702 */       return bitmap;
/*     */     } 
/* 704 */     if (this.genericRegion == null) {
/* 705 */       this.genericRegion = new GenericRegion(this.subInputStream);
/*     */     }
/*     */     
/* 708 */     this.genericRegion.setParameters(true, this.subInputStream.getStreamPosition(), paramLong, paramInt1, paramInt2);
/*     */     
/* 710 */     return this.genericRegion.getRegionBitmap();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setExportedSymbols(int[] paramArrayOfint) {
/* 715 */     this.exportSymbols = new ArrayList<>(this.amountOfExportSymbolss);
/*     */     
/* 717 */     for (byte b = 0; b < this.amountOfImportedSymbolss + this.amountOfNewSymbolss; b++) {
/*     */       
/* 719 */       if (paramArrayOfint[b] == 1) {
/* 720 */         if (b < this.amountOfImportedSymbolss) {
/* 721 */           this.exportSymbols.add(this.importSymbols.get(b));
/*     */         } else {
/* 723 */           this.exportSymbols.add(this.newSymbols[b - this.amountOfImportedSymbolss]);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private int[] getToExportFlags() throws IOException, InvalidHeaderValueException {
/* 730 */     boolean bool = false;
/* 731 */     long l = 0L;
/* 732 */     int[] arrayOfInt = new int[this.amountOfImportedSymbolss + this.amountOfNewSymbolss];
/*     */     int i;
/* 734 */     for (i = 0; i < this.amountOfImportedSymbolss + this.amountOfNewSymbolss; i = (int)(i + l)) {
/*     */       
/* 736 */       if (this.isHuffmanEncoded) {
/* 737 */         l = StandardTables.getTable(1).decode((ImageInputStream)this.subInputStream);
/*     */       } else {
/* 739 */         l = this.iDecoder.decode(this.cxIAEX);
/*     */       } 
/*     */       
/* 742 */       if (l != 0L) {
/* 743 */         for (int j = i; j < i + l; j++) {
/* 744 */           arrayOfInt[j] = bool;
/*     */         }
/*     */       }
/*     */       
/* 748 */       bool = !bool ? true : false;
/*     */     } 
/*     */     
/* 751 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */   private final long huffDecodeBmSize() throws IOException, InvalidHeaderValueException {
/* 755 */     if (this.bmSizeTable == null) {
/* 756 */       byte b = 0;
/*     */       
/* 758 */       if (this.sdHuffDecodeHeightSelection == 3) {
/* 759 */         b++;
/*     */       }
/*     */       
/* 762 */       if (this.sdHuffDecodeWidthSelection == 3) {
/* 763 */         b++;
/*     */       }
/*     */       
/* 766 */       this.bmSizeTable = getUserTable(b);
/*     */     } 
/* 768 */     return this.bmSizeTable.decode((ImageInputStream)this.subInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getSbSymCodeLen() throws IOException {
/* 778 */     if (this.isHuffmanEncoded) {
/* 779 */       return Math.max((int)Math.ceil(Math.log((this.amountOfImportedSymbolss + this.amountOfNewSymbolss)) / Math.log(2.0D)), 1);
/*     */     }
/* 781 */     return (int)Math.ceil(Math.log((this.amountOfImportedSymbolss + this.amountOfNewSymbolss)) / Math.log(2.0D));
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
/*     */   private final void setSymbolsArray() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 793 */     if (this.importSymbols == null) {
/* 794 */       retrieveImportSymbols();
/*     */     }
/*     */     
/* 797 */     if (this.sbSymbols == null) {
/* 798 */       this.sbSymbols = new ArrayList<>();
/* 799 */       this.sbSymbols.addAll(this.importSymbols);
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
/*     */   private void retrieveImportSymbols() throws IOException, InvalidHeaderValueException, IntegerMaxValueException {
/* 811 */     this.importSymbols = new ArrayList<>();
/* 812 */     for (SegmentHeader segmentHeader : this.segmentHeader.getRtSegments()) {
/* 813 */       if (segmentHeader.getSegmentType() == 0) {
/* 814 */         SymbolDictionary symbolDictionary = (SymbolDictionary)segmentHeader.getSegmentData();
/* 815 */         this.importSymbols.addAll(symbolDictionary.getDictionary());
/* 816 */         this.amountOfImportedSymbolss += symbolDictionary.amountOfExportSymbolss;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private HuffmanTable getUserTable(int paramInt) throws InvalidHeaderValueException, IOException {
/* 822 */     int i = 0;
/*     */     
/* 824 */     for (SegmentHeader segmentHeader : this.segmentHeader.getRtSegments()) {
/* 825 */       if (segmentHeader.getSegmentType() == 53) {
/* 826 */         if (i == paramInt) {
/* 827 */           Table table = (Table)segmentHeader.getSegmentData();
/* 828 */           return (HuffmanTable)new EncodedTable(table);
/*     */         } 
/* 830 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 834 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IntegerMaxValueException, IOException {
/* 839 */     this.subInputStream = paramSubInputStream;
/* 840 */     this.segmentHeader = paramSegmentHeader;
/* 841 */     parseHeader();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/SymbolDictionary.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */