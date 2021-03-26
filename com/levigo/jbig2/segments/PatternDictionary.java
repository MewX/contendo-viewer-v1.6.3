/*     */ package com.levigo.jbig2.segments;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import com.levigo.jbig2.Dictionary;
/*     */ import com.levigo.jbig2.SegmentHeader;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.image.Bitmaps;
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PatternDictionary
/*     */   implements Dictionary
/*     */ {
/*  38 */   private final Logger log = LoggerFactory.getLogger(PatternDictionary.class);
/*     */   
/*     */   private SubInputStream subInputStream;
/*     */   
/*     */   private long dataHeaderOffset;
/*     */   
/*     */   private long dataHeaderLength;
/*     */   
/*     */   private long dataOffset;
/*     */   private long dataLength;
/*  48 */   private short[] gbAtX = null;
/*  49 */   private short[] gbAtY = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMMREncoded;
/*     */ 
/*     */   
/*     */   private byte hdTemplate;
/*     */ 
/*     */   
/*     */   private short hdpWidth;
/*     */ 
/*     */   
/*     */   private short hdpHeight;
/*     */ 
/*     */   
/*     */   private ArrayList<Bitmap> patterns;
/*     */ 
/*     */   
/*     */   private int grayMax;
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseHeader() throws IOException, InvalidHeaderValueException {
/*  73 */     this.subInputStream.readBits(5);
/*     */ 
/*     */     
/*  76 */     readTemplate();
/*     */ 
/*     */     
/*  79 */     readIsMMREncoded();
/*     */     
/*  81 */     readPatternWidthAndHeight();
/*     */     
/*  83 */     readGrayMax();
/*     */ 
/*     */     
/*  86 */     computeSegmentDataStructure();
/*     */     
/*  88 */     checkInput();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readTemplate() throws IOException {
/*  93 */     this.hdTemplate = (byte)(int)this.subInputStream.readBits(2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readIsMMREncoded() throws IOException {
/*  98 */     if (this.subInputStream.readBit() == 1) {
/*  99 */       this.isMMREncoded = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void readPatternWidthAndHeight() throws IOException {
/* 104 */     this.hdpWidth = (short)this.subInputStream.readByte();
/* 105 */     this.hdpHeight = (short)this.subInputStream.readByte();
/*     */   }
/*     */   
/*     */   private void readGrayMax() throws IOException {
/* 109 */     this.grayMax = (int)(this.subInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*     */   }
/*     */   
/*     */   private void computeSegmentDataStructure() throws IOException {
/* 113 */     this.dataOffset = this.subInputStream.getStreamPosition();
/* 114 */     this.dataHeaderLength = this.dataOffset - this.dataHeaderOffset;
/* 115 */     this.dataLength = this.subInputStream.length() - this.dataHeaderLength;
/*     */   }
/*     */   
/*     */   private void checkInput() throws InvalidHeaderValueException {
/* 119 */     if (this.hdpHeight < 1 || this.hdpWidth < 1) {
/* 120 */       throw new InvalidHeaderValueException("Width/Heigth must be greater than zero.");
/*     */     }
/*     */     
/* 123 */     if (this.isMMREncoded && 
/* 124 */       this.hdTemplate != 0) {
/* 125 */       this.log.info("hdTemplate should contain the value 0");
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
/*     */   public ArrayList<Bitmap> getDictionary() throws IOException, InvalidHeaderValueException {
/* 138 */     if (null == this.patterns) {
/*     */       
/* 140 */       if (!this.isMMREncoded) {
/* 141 */         setGbAtPixels();
/*     */       }
/*     */ 
/*     */       
/* 145 */       GenericRegion genericRegion = new GenericRegion(this.subInputStream);
/* 146 */       genericRegion.setParameters(this.isMMREncoded, this.dataOffset, this.dataLength, this.hdpHeight, (this.grayMax + 1) * this.hdpWidth, this.hdTemplate, false, false, this.gbAtX, this.gbAtY);
/*     */ 
/*     */       
/* 149 */       Bitmap bitmap = genericRegion.getRegionBitmap();
/*     */ 
/*     */       
/* 152 */       extractPatterns(bitmap);
/*     */     } 
/*     */     
/* 155 */     return this.patterns;
/*     */   }
/*     */ 
/*     */   
/*     */   private void extractPatterns(Bitmap paramBitmap) {
/* 160 */     byte b = 0;
/* 161 */     this.patterns = new ArrayList<>(this.grayMax + 1);
/*     */ 
/*     */     
/* 164 */     while (b <= this.grayMax) {
/*     */       
/* 166 */       Rectangle rectangle = new Rectangle(this.hdpWidth * b, 0, this.hdpWidth, this.hdpHeight);
/* 167 */       Bitmap bitmap = Bitmaps.extract(rectangle, paramBitmap);
/* 168 */       this.patterns.add(bitmap);
/*     */ 
/*     */       
/* 171 */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setGbAtPixels() {
/* 176 */     if (this.hdTemplate == 0) {
/* 177 */       this.gbAtX = new short[4];
/* 178 */       this.gbAtY = new short[4];
/* 179 */       this.gbAtX[0] = (short)-this.hdpWidth;
/* 180 */       this.gbAtY[0] = 0;
/* 181 */       this.gbAtX[1] = -3;
/* 182 */       this.gbAtY[1] = -1;
/* 183 */       this.gbAtX[2] = 2;
/* 184 */       this.gbAtY[2] = -2;
/* 185 */       this.gbAtX[3] = -2;
/* 186 */       this.gbAtY[3] = -2;
/*     */     } else {
/*     */       
/* 189 */       this.gbAtX = new short[1];
/* 190 */       this.gbAtY = new short[1];
/* 191 */       this.gbAtX[0] = (short)-this.hdpWidth;
/* 192 */       this.gbAtY[0] = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void init(SegmentHeader paramSegmentHeader, SubInputStream paramSubInputStream) throws InvalidHeaderValueException, IOException {
/* 197 */     this.subInputStream = paramSubInputStream;
/* 198 */     parseHeader();
/*     */   }
/*     */   
/*     */   protected boolean isMMREncoded() {
/* 202 */     return this.isMMREncoded;
/*     */   }
/*     */   
/*     */   protected byte getHdTemplate() {
/* 206 */     return this.hdTemplate;
/*     */   }
/*     */   
/*     */   protected short getHdpWidth() {
/* 210 */     return this.hdpWidth;
/*     */   }
/*     */   
/*     */   protected short getHdpHeight() {
/* 214 */     return this.hdpHeight;
/*     */   }
/*     */   
/*     */   protected int getGrayMax() {
/* 218 */     return this.grayMax;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/segments/PatternDictionary.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */