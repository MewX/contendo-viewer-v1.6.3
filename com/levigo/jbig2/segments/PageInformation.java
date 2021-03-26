/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.SegmentData;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.CombinationOperator;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageInformation
/*     */   implements SegmentData
/*     */ {
/*  35 */   private final Logger log = LoggerFactory.getLogger(PageInformation.class);
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private int bitmapWidth;
/*     */   
/*     */   private int bitmapHeight;
/*     */   
/*     */   private int resolutionX;
/*     */   
/*     */   private int resolutionY;
/*     */   
/*     */   private boolean combinationOperatorOverrideAllowed;
/*     */   
/*     */   private CombinationOperator combinationOperator;
/*     */   
/*     */   private boolean requiresAuxiliaryBuffer;
/*     */   
/*     */   private short defaultPixelValue;
/*     */   
/*     */   private boolean mightContainRefinements;
/*     */   
/*     */   private boolean isLossless;
/*     */   
/*     */   private boolean isStriped;
/*     */   
/*     */   private short maxStripeSize;
/*     */ 
/*     */   
/*     */   private void parseHeader() throws IOException, InvalidHeaderValueException {
/*  65 */     readWidthAndHeight();
/*  66 */     readResolution();
/*     */ 
/*     */     
/*  69 */     this.subInputStream.readBit();
/*     */ 
/*     */     
/*  72 */     readCombinationOperatorOverrideAllowed();
/*     */ 
/*     */     
/*  75 */     readRequiresAuxiliaryBuffer();
/*     */ 
/*     */     
/*  78 */     readCombinationOperator();
/*     */ 
/*     */     
/*  81 */     readDefaultPixelvalue();
/*     */ 
/*     */     
/*  84 */     readContainsRefinement();
/*     */ 
/*     */     
/*  87 */     readIsLossless();
/*     */ 
/*     */     
/*  90 */     readIsStriped();
/*     */ 
/*     */     
/*  93 */     readMaxStripeSize();
/*     */     
/*  95 */     checkInput();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readResolution() throws IOException {
/* 100 */     this.resolutionX = (int)this.subInputStream.readBits(32) & 0xFFFFFFFF;
/* 101 */     this.resolutionY = (int)this.subInputStream.readBits(32) & 0xFFFFFFFF;
/*     */   }
/*     */   
/*     */   private void checkInput() throws InvalidHeaderValueException {
/* 105 */     if (this.bitmapHeight == 4294967295L && 
/* 106 */       !this.isStriped) {
/* 107 */       this.log.info("isStriped should contaion the value true");
/*     */     }
/*     */   }
/*     */   
/*     */   private void readCombinationOperatorOverrideAllowed() throws IOException {
/* 112 */     if (this.subInputStream.readBit() == 1) {
/* 113 */       this.combinationOperatorOverrideAllowed = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void readRequiresAuxiliaryBuffer() throws IOException {
/* 119 */     if (this.subInputStream.readBit() == 1) {
/* 120 */       this.requiresAuxiliaryBuffer = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void readCombinationOperator() throws IOException {
/* 126 */     this.combinationOperator = CombinationOperator.translateOperatorCodeToEnum((short)(int)(this.subInputStream.readBits(2) & 0xFL));
/*     */   }
/*     */ 
/*     */   
/*     */   private void readDefaultPixelvalue() throws IOException {
/* 131 */     this.defaultPixelValue = (short)this.subInputStream.readBit();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readContainsRefinement() throws IOException {
/* 136 */     if (this.subInputStream.readBit() == 1) {
/* 137 */       this.mightContainRefinements = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void readIsLossless() throws IOException {
/* 143 */     if (this.subInputStream.readBit() == 1) {
/* 144 */       this.isLossless = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void readIsStriped() throws IOException {
/* 150 */     if (this.subInputStream.readBit() == 1) {
/* 151 */       this.isStriped = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void readMaxStripeSize() throws IOException {
/* 157 */     this.maxStripeSize = (short)(int)(this.subInputStream.readBits(15) & 0xFFFFL);
/*     */   }
/*     */   
/*     */   private void readWidthAndHeight() throws IOException {
/* 161 */     this.bitmapWidth = (int)this.subInputStream.readBits(32);
/* 162 */     this.bitmapHeight = (int)this.subInputStream.readBits(32);
/*     */   }
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IOException {
/* 166 */     this.subInputStream = paramSubInputStream;
/*     */     
/* 168 */     parseHeader();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 172 */     return this.bitmapWidth;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 176 */     return this.bitmapHeight;
/*     */   }
/*     */   
/*     */   public int getResolutionX() {
/* 180 */     return this.resolutionX;
/*     */   }
/*     */   
/*     */   public int getResolutionY() {
/* 184 */     return this.resolutionY;
/*     */   }
/*     */   
/*     */   public short getDefaultPixelValue() {
/* 188 */     return this.defaultPixelValue;
/*     */   }
/*     */   
/*     */   public boolean isCombinationOperatorOverrideAllowed() {
/* 192 */     return this.combinationOperatorOverrideAllowed;
/*     */   }
/*     */   
/*     */   public CombinationOperator getCombinationOperator() {
/* 196 */     return this.combinationOperator;
/*     */   }
/*     */   
/*     */   public boolean isStriped() {
/* 200 */     return this.isStriped;
/*     */   }
/*     */   
/*     */   public short getMaxStripeSize() {
/* 204 */     return this.maxStripeSize;
/*     */   }
/*     */   
/*     */   public boolean isAuxiliaryBufferRequired() {
/* 208 */     return this.requiresAuxiliaryBuffer;
/*     */   }
/*     */   
/*     */   public boolean mightContainRefinements() {
/* 212 */     return this.mightContainRefinements;
/*     */   }
/*     */   
/*     */   public boolean isLossless() {
/* 216 */     return this.isLossless;
/*     */   }
/*     */   
/*     */   protected int getBitmapWidth() {
/* 220 */     return this.bitmapWidth;
/*     */   }
/*     */   
/*     */   protected int getBitmapHeight() {
/* 224 */     return this.bitmapHeight;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/PageInformation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */