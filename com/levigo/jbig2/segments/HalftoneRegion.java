/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.Region;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.image.Bitmaps;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.CombinationOperator;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HalftoneRegion
/*     */   implements Region
/*     */ {
/*  40 */   private final Logger log = LoggerFactory.getLogger(HalftoneRegion.class);
/*     */ 
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private SegmentHeader segmentHeader;
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
/*     */   private byte hDefaultPixel;
/*     */   
/*     */   private CombinationOperator hCombinationOperator;
/*     */   
/*     */   private boolean hSkipEnabled;
/*     */   
/*     */   private byte hTemplate;
/*     */   
/*     */   private boolean isMMREncoded;
/*     */   
/*     */   private int hGridWidth;
/*     */   
/*     */   private int hGridHeight;
/*     */   
/*     */   private int hGridX;
/*     */   
/*     */   private int hGridY;
/*     */   
/*     */   private int hRegionX;
/*     */   
/*     */   private int hRegionY;
/*     */   
/*     */   private Bitmap halftoneRegionBitmap;
/*     */   
/*     */   private ArrayList<Bitmap> patterns;
/*     */ 
/*     */   
/*     */   public HalftoneRegion() {}
/*     */ 
/*     */   
/*     */   public HalftoneRegion(SubInputStream paramSubInputStream) {
/*  88 */     this.subInputStream = paramSubInputStream;
/*  89 */     this.regionInfo = new RegionSegmentInformation(paramSubInputStream);
/*     */   }
/*     */   
/*     */   public HalftoneRegion(SubInputStream paramSubInputStream, SegmentHeader paramSegmentHeader) {
/*  93 */     this.subInputStream = paramSubInputStream;
/*  94 */     this.segmentHeader = paramSegmentHeader;
/*  95 */     this.regionInfo = new RegionSegmentInformation(paramSubInputStream);
/*     */   }
/*     */   
/*     */   private void parseHeader() throws IOException, InvalidHeaderValueException {
/*  99 */     this.regionInfo.parseHeader();
/*     */ 
/*     */     
/* 102 */     this.hDefaultPixel = (byte)this.subInputStream.readBit();
/*     */ 
/*     */     
/* 105 */     this.hCombinationOperator = CombinationOperator.translateOperatorCodeToEnum((short)(int)(this.subInputStream.readBits(3) & 0xFL));
/*     */ 
/*     */     
/* 108 */     if (this.subInputStream.readBit() == 1) {
/* 109 */       this.hSkipEnabled = true;
/*     */     }
/*     */ 
/*     */     
/* 113 */     this.hTemplate = (byte)(int)(this.subInputStream.readBits(2) & 0xFL);
/*     */ 
/*     */     
/* 116 */     if (this.subInputStream.readBit() == 1) {
/* 117 */       this.isMMREncoded = true;
/*     */     }
/*     */     
/* 120 */     this.hGridWidth = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/* 121 */     this.hGridHeight = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*     */     
/* 123 */     this.hGridX = (int)this.subInputStream.readBits(32);
/* 124 */     this.hGridY = (int)this.subInputStream.readBits(32);
/*     */     
/* 126 */     this.hRegionX = (int)this.subInputStream.readBits(16) & 0xFFFF;
/* 127 */     this.hRegionY = (int)this.subInputStream.readBits(16) & 0xFFFF;
/*     */ 
/*     */     
/* 130 */     computeSegmentDataStructure();
/*     */     
/* 132 */     checkInput();
/*     */   }
/*     */   
/*     */   private void computeSegmentDataStructure() throws IOException {
/* 136 */     this.dataOffset = this.subInputStream.getStreamPosition();
/* 137 */     this.dataHeaderLength = this.dataOffset - this.dataHeaderOffset;
/* 138 */     this.dataLength = this.subInputStream.length() - this.dataHeaderLength;
/*     */   }
/*     */   
/*     */   private void checkInput() throws InvalidHeaderValueException {
/* 142 */     if (this.isMMREncoded) {
/* 143 */       if (this.hTemplate != 0) {
/* 144 */         this.log.info("hTemplate = " + this.hTemplate + " (should contain the value 0)");
/*     */       }
/*     */       
/* 147 */       if (this.hSkipEnabled) {
/* 148 */         this.log.info("hSkipEnabled 0 " + this.hSkipEnabled + " (should contain the value false)");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bitmap getRegionBitmap() throws IOException, InvalidHeaderValueException {
/* 159 */     if (null == this.halftoneRegionBitmap) {
/*     */ 
/*     */ 
/*     */       
/* 163 */       this.halftoneRegionBitmap = new Bitmap(this.regionInfo.getBitmapWidth(), this.regionInfo.getBitmapHeight());
/*     */       
/* 165 */       if (this.patterns == null) {
/* 166 */         this.patterns = getPatterns();
/*     */       }
/*     */       
/* 169 */       if (this.hDefaultPixel == 1) {
/* 170 */         Arrays.fill(this.halftoneRegionBitmap.getByteArray(), (byte)-1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       int i = (int)Math.ceil(Math.log(this.patterns.size()) / Math.log(2.0D));
/*     */ 
/*     */       
/* 190 */       int[][] arrayOfInt = grayScaleDecoding(i);
/*     */ 
/*     */       
/* 193 */       renderPattern(arrayOfInt);
/*     */     } 
/*     */     
/* 196 */     return this.halftoneRegionBitmap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPattern(int[][] paramArrayOfint) {
/* 204 */     int i = 0, j = 0;
/*     */ 
/*     */     
/* 207 */     for (byte b = 0; b < this.hGridHeight; b++) {
/*     */       
/* 209 */       for (byte b1 = 0; b1 < this.hGridWidth; b1++) {
/*     */         
/* 211 */         i = computeX(b, b1);
/* 212 */         j = computeY(b, b1);
/*     */ 
/*     */         
/* 215 */         Bitmap bitmap = this.patterns.get(paramArrayOfint[b][b1]);
/* 216 */         Bitmaps.blit(bitmap, this.halftoneRegionBitmap, i + this.hGridX, j + this.hGridY, this.hCombinationOperator);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<Bitmap> getPatterns() throws InvalidHeaderValueException, IOException {
/* 227 */     ArrayList<Bitmap> arrayList = new ArrayList();
/*     */     
/* 229 */     for (SegmentHeader segmentHeader : this.segmentHeader.getRtSegments()) {
/* 230 */       PatternDictionary patternDictionary = (PatternDictionary)segmentHeader.getSegmentData();
/* 231 */       arrayList.addAll(patternDictionary.getDictionary());
/*     */     } 
/*     */     
/* 234 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[][] grayScaleDecoding(int paramInt) throws IOException {
/* 243 */     short[] arrayOfShort1 = null;
/* 244 */     short[] arrayOfShort2 = null;
/*     */     
/* 246 */     if (!this.isMMREncoded) {
/* 247 */       arrayOfShort1 = new short[4];
/* 248 */       arrayOfShort2 = new short[4];
/*     */       
/* 250 */       if (this.hTemplate <= 1) {
/* 251 */         arrayOfShort1[0] = 3;
/* 252 */       } else if (this.hTemplate >= 2) {
/* 253 */         arrayOfShort1[0] = 2;
/*     */       } 
/* 255 */       arrayOfShort2[0] = -1;
/* 256 */       arrayOfShort1[1] = -3;
/* 257 */       arrayOfShort2[1] = -1;
/* 258 */       arrayOfShort1[2] = 2;
/* 259 */       arrayOfShort2[2] = -2;
/* 260 */       arrayOfShort1[3] = -2;
/* 261 */       arrayOfShort2[3] = -2;
/*     */     } 
/*     */     
/* 264 */     Bitmap[] arrayOfBitmap = new Bitmap[paramInt];
/*     */ 
/*     */     
/* 267 */     GenericRegion genericRegion = new GenericRegion(this.subInputStream);
/* 268 */     genericRegion.setParameters(this.isMMREncoded, this.dataOffset, this.dataLength, this.hGridHeight, this.hGridWidth, this.hTemplate, false, this.hSkipEnabled, arrayOfShort1, arrayOfShort2);
/*     */ 
/*     */ 
/*     */     
/* 272 */     int i = paramInt - 1;
/*     */     
/* 274 */     arrayOfBitmap[i] = genericRegion.getRegionBitmap();
/*     */     
/* 276 */     while (i > 0) {
/* 277 */       i--;
/* 278 */       genericRegion.resetBitmap();
/*     */       
/* 280 */       arrayOfBitmap[i] = genericRegion.getRegionBitmap();
/*     */       
/* 282 */       arrayOfBitmap = combineGrayScalePlanes(arrayOfBitmap, i);
/*     */     } 
/*     */ 
/*     */     
/* 286 */     return computeGrayScaleValues(arrayOfBitmap, paramInt);
/*     */   }
/*     */   
/*     */   private Bitmap[] combineGrayScalePlanes(Bitmap[] paramArrayOfBitmap, int paramInt) {
/* 290 */     byte b1 = 0;
/* 291 */     for (byte b2 = 0; b2 < paramArrayOfBitmap[paramInt].getHeight(); b2++) {
/*     */       
/* 293 */       for (byte b = 0; b < paramArrayOfBitmap[paramInt].getWidth(); b += 8) {
/* 294 */         byte b3 = paramArrayOfBitmap[paramInt + 1].getByte(b1);
/* 295 */         byte b4 = paramArrayOfBitmap[paramInt].getByte(b1);
/*     */         
/* 297 */         paramArrayOfBitmap[paramInt].setByte(b1++, Bitmaps.combineBytes(b4, b3, CombinationOperator.XOR));
/*     */       } 
/*     */     } 
/* 300 */     return paramArrayOfBitmap;
/*     */   }
/*     */ 
/*     */   
/*     */   private int[][] computeGrayScaleValues(Bitmap[] paramArrayOfBitmap, int paramInt) {
/* 305 */     int[][] arrayOfInt = new int[this.hGridHeight][this.hGridWidth];
/*     */ 
/*     */     
/* 308 */     for (byte b = 0; b < this.hGridHeight; b++) {
/* 309 */       for (byte b1 = 0; b1 < this.hGridWidth; b1 += 8) {
/* 310 */         byte b2 = (this.hGridWidth - b1 > 8) ? 8 : (this.hGridWidth - b1);
/* 311 */         int i = paramArrayOfBitmap[0].getByteIndex(b1, b);
/*     */         
/* 313 */         for (byte b3 = 0; b3 < b2; b3++) {
/* 314 */           int j = b3 + b1;
/* 315 */           arrayOfInt[b][j] = 0;
/*     */           
/* 317 */           for (byte b4 = 0; b4 < paramInt; b4++) {
/* 318 */             arrayOfInt[b][j] = arrayOfInt[b][j] + (paramArrayOfBitmap[b4].getByte(i) >> (7 - j & 0x7) & 0x1) * (1 << b4);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 323 */     return arrayOfInt;
/*     */   }
/*     */   
/*     */   private int computeX(int paramInt1, int paramInt2) {
/* 327 */     return shiftAndFill(this.hGridX + paramInt1 * this.hRegionY + paramInt2 * this.hRegionX);
/*     */   }
/*     */   
/*     */   private int computeY(int paramInt1, int paramInt2) {
/* 331 */     return shiftAndFill(this.hGridY + paramInt1 * this.hRegionX - paramInt2 * this.hRegionY);
/*     */   }
/*     */ 
/*     */   
/*     */   private int shiftAndFill(int paramInt) {
/* 336 */     paramInt >>= 8;
/*     */     
/* 338 */     if (paramInt < 0) {
/*     */       
/* 340 */       int i = (int)(Math.log(Integer.highestOneBit(paramInt)) / Math.log(2.0D));
/*     */       
/* 342 */       for (byte b = 1; b < 31 - i; b++)
/*     */       {
/* 344 */         paramInt |= 1 << 31 - b;
/*     */       }
/*     */     } 
/*     */     
/* 348 */     return paramInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IOException {
/* 353 */     this.segmentHeader = paramSegmentHeader;
/* 354 */     this.subInputStream = paramSubInputStream;
/* 355 */     this.regionInfo = new RegionSegmentInformation(this.subInputStream);
/* 356 */     parseHeader();
/*     */   }
/*     */   
/*     */   public CombinationOperator getCombinationOperator() {
/* 360 */     return this.hCombinationOperator;
/*     */   }
/*     */   
/*     */   public RegionSegmentInformation getRegionInfo() {
/* 364 */     return this.regionInfo;
/*     */   }
/*     */   
/*     */   protected byte getHTemplate() {
/* 368 */     return this.hTemplate;
/*     */   }
/*     */   
/*     */   protected boolean isHSkipEnabled() {
/* 372 */     return this.hSkipEnabled;
/*     */   }
/*     */   
/*     */   protected boolean isMMREncoded() {
/* 376 */     return this.isMMREncoded;
/*     */   }
/*     */   
/*     */   protected int getHGridWidth() {
/* 380 */     return this.hGridWidth;
/*     */   }
/*     */   
/*     */   protected int getHGridHeight() {
/* 384 */     return this.hGridHeight;
/*     */   }
/*     */   
/*     */   protected int getHGridX() {
/* 388 */     return this.hGridX;
/*     */   }
/*     */   
/*     */   protected int getHGridY() {
/* 392 */     return this.hGridY;
/*     */   }
/*     */   
/*     */   protected int getHRegionX() {
/* 396 */     return this.hRegionX;
/*     */   }
/*     */   
/*     */   protected int getHRegionY() {
/* 400 */     return this.hRegionY;
/*     */   }
/*     */   
/*     */   protected byte getHDefaultPixel() {
/* 404 */     return this.hDefaultPixel;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/HalftoneRegion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */