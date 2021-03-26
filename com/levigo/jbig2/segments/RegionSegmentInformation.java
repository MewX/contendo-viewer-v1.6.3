/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.SegmentData;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.err.IntegerMaxValueException;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.CombinationOperator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegionSegmentInformation
/*     */   implements SegmentData
/*     */ {
/*     */   private SubInputStream subInputStream;
/*     */   private int bitmapWidth;
/*     */   private int bitmapHeight;
/*     */   private int xLocation;
/*     */   private int yLocation;
/*     */   private CombinationOperator combinationOperator;
/*     */   
/*     */   public RegionSegmentInformation(SubInputStream paramSubInputStream) {
/*  53 */     this.subInputStream = paramSubInputStream;
/*     */   }
/*     */ 
/*     */   
/*     */   public RegionSegmentInformation() {}
/*     */   
/*     */   public void parseHeader() throws IOException {
/*  60 */     this.bitmapWidth = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*  61 */     this.bitmapHeight = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*  62 */     this.xLocation = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*  63 */     this.yLocation = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*     */ 
/*     */     
/*  66 */     this.subInputStream.readBits(5);
/*     */ 
/*     */     
/*  69 */     readCombinationOperator();
/*     */   }
/*     */   
/*     */   private void readCombinationOperator() throws IOException {
/*  73 */     this.combinationOperator = CombinationOperator.translateOperatorCodeToEnum((short)(int)(this.subInputStream.readBits(3) & 0xFL));
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IntegerMaxValueException, IOException {}
/*     */ 
/*     */   
/*     */   public void setBitmapWidth(int paramInt) {
/*  81 */     this.bitmapWidth = paramInt;
/*     */   }
/*     */   
/*     */   public int getBitmapWidth() {
/*  85 */     return this.bitmapWidth;
/*     */   }
/*     */   
/*     */   public void setBitmapHeight(int paramInt) {
/*  89 */     this.bitmapHeight = paramInt;
/*     */   }
/*     */   
/*     */   public int getBitmapHeight() {
/*  93 */     return this.bitmapHeight;
/*     */   }
/*     */   
/*     */   public int getXLocation() {
/*  97 */     return this.xLocation;
/*     */   }
/*     */   
/*     */   public int getYLocation() {
/* 101 */     return this.yLocation;
/*     */   }
/*     */   
/*     */   public CombinationOperator getCombinationOperator() {
/* 105 */     return this.combinationOperator;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/RegionSegmentInformation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */