/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.segments.EndOfStripe;
/*     */ import com.levigo.jbig2.segments.GenericRefinementRegion;
/*     */ import com.levigo.jbig2.segments.GenericRegion;
/*     */ import com.levigo.jbig2.segments.HalftoneRegion;
/*     */ import com.levigo.jbig2.segments.PageInformation;
/*     */ import com.levigo.jbig2.segments.PatternDictionary;
/*     */ import com.levigo.jbig2.segments.Profiles;
/*     */ import com.levigo.jbig2.segments.SymbolDictionary;
/*     */ import com.levigo.jbig2.segments.Table;
/*     */ import com.levigo.jbig2.segments.TextRegion;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class SegmentHeader
/*     */ {
/*  47 */   private static final Logger log = LoggerFactory.getLogger(SegmentHeader.class);
/*     */   
/*  49 */   private static final Map<Integer, Class<? extends SegmentData>> SEGMENT_TYPE_MAP = new HashMap<>();
/*     */   
/*     */   static {
/*  52 */     Object[][] arrayOfObject = { { Integer.valueOf(0), SymbolDictionary.class }, { Integer.valueOf(4), TextRegion.class }, { Integer.valueOf(6), TextRegion.class }, { Integer.valueOf(7), TextRegion.class }, { Integer.valueOf(16), PatternDictionary.class }, { Integer.valueOf(20), HalftoneRegion.class }, { Integer.valueOf(22), HalftoneRegion.class }, { Integer.valueOf(23), HalftoneRegion.class }, { Integer.valueOf(36), GenericRegion.class }, { Integer.valueOf(38), GenericRegion.class }, { Integer.valueOf(39), GenericRegion.class }, { Integer.valueOf(40), GenericRefinementRegion.class }, { Integer.valueOf(42), GenericRefinementRegion.class }, { Integer.valueOf(43), GenericRefinementRegion.class }, { Integer.valueOf(48), PageInformation.class }, { Integer.valueOf(50), EndOfStripe.class }, { Integer.valueOf(52), Profiles.class }, { Integer.valueOf(53), Table.class } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     for (byte b = 0; b < arrayOfObject.length; b++) {
/*  93 */       Object[] arrayOfObject1 = arrayOfObject[b];
/*  94 */       SEGMENT_TYPE_MAP.put((Integer)arrayOfObject1[0], (Class<? extends SegmentData>)arrayOfObject1[1]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int segmentNr;
/*     */   
/*     */   private int segmentType;
/*     */   
/*     */   private byte retainFlag;
/*     */   private int pageAssociation;
/*     */   private byte pageAssociationFieldSize;
/*     */   private SegmentHeader[] rtSegments;
/*     */   private long segmentHeaderLength;
/*     */   private long segmentDataLength;
/*     */   private long segmentDataStartOffset;
/*     */   private final SubInputStream subInputStream;
/*     */   private Reference<SegmentData> segmentData;
/*     */   
/*     */   public SegmentHeader(JBIG2Document paramJBIG2Document, SubInputStream paramSubInputStream, long paramLong, int paramInt) throws IOException {
/* 114 */     this.subInputStream = paramSubInputStream;
/* 115 */     parse(paramJBIG2Document, (ImageInputStream)paramSubInputStream, paramLong, paramInt);
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
/*     */   private void parse(JBIG2Document paramJBIG2Document, ImageInputStream paramImageInputStream, long paramLong, int paramInt) throws IOException {
/* 130 */     printDebugMessage("\n########################");
/* 131 */     printDebugMessage("Segment parsing started.");
/*     */     
/* 133 */     paramImageInputStream.seek(paramLong);
/* 134 */     printDebugMessage("|-Seeked to offset: " + paramLong);
/*     */ 
/*     */     
/* 137 */     readSegmentNumber(paramImageInputStream);
/*     */ 
/*     */     
/* 140 */     readSegmentHeaderFlag(paramImageInputStream);
/*     */ 
/*     */     
/* 143 */     int i = readAmountOfReferredToSegments(paramImageInputStream);
/*     */ 
/*     */     
/* 146 */     int[] arrayOfInt = readReferredToSegmentsNumbers(paramImageInputStream, i);
/*     */ 
/*     */     
/* 149 */     readSegmentPageAssociation(paramJBIG2Document, paramImageInputStream, i, arrayOfInt);
/*     */ 
/*     */     
/* 152 */     readSegmentDataLength(paramImageInputStream);
/*     */     
/* 154 */     readDataStartOffset(paramImageInputStream, paramInt);
/* 155 */     readSegmentHeaderLength(paramImageInputStream, paramLong);
/* 156 */     printDebugMessage("########################\n");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readSegmentNumber(ImageInputStream paramImageInputStream) throws IOException {
/* 166 */     this.segmentNr = (int)(paramImageInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/* 167 */     printDebugMessage("|-Segment Nr: " + this.segmentNr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readSegmentHeaderFlag(ImageInputStream paramImageInputStream) throws IOException {
/* 178 */     this.retainFlag = (byte)paramImageInputStream.readBit();
/* 179 */     printDebugMessage("|-Retain flag: " + this.retainFlag);
/*     */ 
/*     */     
/* 182 */     this.pageAssociationFieldSize = (byte)paramImageInputStream.readBit();
/* 183 */     printDebugMessage("|-Page association field size=" + this.pageAssociationFieldSize);
/*     */ 
/*     */     
/* 186 */     this.segmentType = (int)(paramImageInputStream.readBits(6) & 0xFFL);
/* 187 */     printDebugMessage("|-Segment type=" + this.segmentType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int readAmountOfReferredToSegments(ImageInputStream paramImageInputStream) throws IOException {
/* 198 */     int i = (int)(paramImageInputStream.readBits(3) & 0xFL);
/* 199 */     printDebugMessage("|-RTS count: " + i);
/*     */ 
/*     */ 
/*     */     
/* 203 */     printDebugMessage("  |-Stream position before RTS: " + paramImageInputStream.getStreamPosition());
/*     */     
/* 205 */     if (i <= 4) {
/*     */       
/* 207 */       byte[] arrayOfByte = new byte[5];
/* 208 */       for (byte b = 0; b <= 4; b++) {
/* 209 */         arrayOfByte[b] = (byte)paramImageInputStream.readBit();
/*     */       }
/*     */     } else {
/*     */       
/* 213 */       i = (int)(paramImageInputStream.readBits(29) & 0xFFFFFFFFFFFFFFFFL);
/*     */       
/* 215 */       int j = i + 8 >> 3;
/* 216 */       byte[] arrayOfByte = new byte[j <<= 3];
/*     */       
/* 218 */       for (byte b = 0; b < j; b++) {
/* 219 */         arrayOfByte[b] = (byte)paramImageInputStream.readBit();
/*     */       }
/*     */     } 
/*     */     
/* 223 */     printDebugMessage("  |-Stream position after RTS: " + paramImageInputStream.getStreamPosition());
/*     */     
/* 225 */     return i;
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
/*     */ 
/*     */   
/*     */   private int[] readReferredToSegmentsNumbers(ImageInputStream paramImageInputStream, int paramInt) throws IOException {
/* 242 */     int[] arrayOfInt = new int[paramInt];
/*     */     
/* 244 */     if (paramInt > 0) {
/* 245 */       byte b1 = 1;
/* 246 */       if (this.segmentNr > 256) {
/* 247 */         b1 = 2;
/* 248 */         if (this.segmentNr > 65536) {
/* 249 */           b1 = 4;
/*     */         }
/*     */       } 
/*     */       
/* 253 */       this.rtSegments = new SegmentHeader[paramInt];
/*     */       
/* 255 */       printDebugMessage("|-Length of RT segments list: " + this.rtSegments.length);
/*     */       
/* 257 */       for (byte b2 = 0; b2 < paramInt; b2++) {
/* 258 */         arrayOfInt[b2] = (int)(paramImageInputStream.readBits(b1 << 3) & 0xFFFFFFFFFFFFFFFFL);
/*     */       }
/*     */     } 
/*     */     
/* 262 */     return arrayOfInt;
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
/*     */   private void readSegmentPageAssociation(JBIG2Document paramJBIG2Document, ImageInputStream paramImageInputStream, int paramInt, int[] paramArrayOfint) throws IOException {
/* 276 */     if (this.pageAssociationFieldSize == 0) {
/*     */       
/* 278 */       this.pageAssociation = (short)(int)(paramImageInputStream.readBits(8) & 0xFFL);
/*     */     } else {
/*     */       
/* 281 */       this.pageAssociation = (int)(paramImageInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL);
/*     */     } 
/*     */     
/* 284 */     if (paramInt > 0) {
/* 285 */       JBIG2Page jBIG2Page = paramJBIG2Document.getPage(this.pageAssociation);
/* 286 */       for (byte b = 0; b < paramInt; b++) {
/* 287 */         this.rtSegments[b] = (null != jBIG2Page) ? jBIG2Page.getSegment(paramArrayOfint[b]) : paramJBIG2Document.getGlobalSegment(paramArrayOfint[b]);
/*     */       }
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
/*     */   private void readSegmentDataLength(ImageInputStream paramImageInputStream) throws IOException {
/* 301 */     this.segmentDataLength = paramImageInputStream.readBits(32) & 0xFFFFFFFFFFFFFFFFL;
/* 302 */     printDebugMessage("|-Data length: " + this.segmentDataLength);
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
/*     */   private void readDataStartOffset(ImageInputStream paramImageInputStream, int paramInt) throws IOException {
/* 314 */     if (paramInt == 1) {
/* 315 */       printDebugMessage("|-Organization is sequential.");
/* 316 */       this.segmentDataStartOffset = paramImageInputStream.getStreamPosition();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readSegmentHeaderLength(ImageInputStream paramImageInputStream, long paramLong) throws IOException {
/* 321 */     this.segmentHeaderLength = paramImageInputStream.getStreamPosition() - paramLong;
/* 322 */     printDebugMessage("|-Segment header length: " + this.segmentHeaderLength);
/*     */   }
/*     */   
/*     */   private void printDebugMessage(String paramString) {
/* 326 */     log.debug(paramString);
/*     */   }
/*     */   
/*     */   public int getSegmentNr() {
/* 330 */     return this.segmentNr;
/*     */   }
/*     */   
/*     */   public int getSegmentType() {
/* 334 */     return this.segmentType;
/*     */   }
/*     */   
/*     */   public long getSegmentHeaderLength() {
/* 338 */     return this.segmentHeaderLength;
/*     */   }
/*     */   
/*     */   public long getSegmentDataLength() {
/* 342 */     return this.segmentDataLength;
/*     */   }
/*     */   
/*     */   public long getSegmentDataStartOffset() {
/* 346 */     return this.segmentDataStartOffset;
/*     */   }
/*     */   
/*     */   public void setSegmentDataStartOffset(long paramLong) {
/* 350 */     this.segmentDataStartOffset = paramLong;
/*     */   }
/*     */   
/*     */   public SegmentHeader[] getRtSegments() {
/* 354 */     return this.rtSegments;
/*     */   }
/*     */   
/*     */   public int getPageAssociation() {
/* 358 */     return this.pageAssociation;
/*     */   }
/*     */   
/*     */   public short getRetainFlag() {
/* 362 */     return (short)this.retainFlag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubInputStream getDataInputStream() {
/* 372 */     return new SubInputStream((ImageInputStream)this.subInputStream, this.segmentDataStartOffset, this.segmentDataLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SegmentData getSegmentData() {
/* 381 */     SegmentData segmentData = null;
/*     */     
/* 383 */     if (null != this.segmentData) {
/* 384 */       segmentData = this.segmentData.get();
/*     */     }
/*     */     
/* 387 */     if (null == segmentData) {
/*     */       
/*     */       try {
/* 390 */         Class<SegmentData> clazz = (Class)SEGMENT_TYPE_MAP.get(Integer.valueOf(this.segmentType));
/*     */         
/* 392 */         if (null == clazz) {
/* 393 */           throw new IllegalArgumentException("No segment class for type " + this.segmentType);
/*     */         }
/*     */         
/* 396 */         segmentData = clazz.newInstance();
/* 397 */         segmentData.init(this, getDataInputStream());
/*     */         
/* 399 */         this.segmentData = new SoftReference<>(segmentData);
/*     */       }
/* 401 */       catch (Exception exception) {
/* 402 */         throw new RuntimeException("Can't instantiate segment class", exception);
/*     */       } 
/*     */     }
/*     */     
/* 406 */     return segmentData;
/*     */   }
/*     */   
/*     */   public void cleanSegmentData() {
/* 410 */     if (this.segmentData != null) {
/* 411 */       this.segmentData = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 416 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/* 418 */     if (this.rtSegments != null) {
/* 419 */       for (SegmentHeader segmentHeader : this.rtSegments) {
/* 420 */         stringBuilder.append(segmentHeader.segmentNr + " ");
/*     */       }
/*     */     } else {
/* 423 */       stringBuilder.append("none");
/*     */     } 
/*     */     
/* 426 */     return "\n#SegmentNr: " + this.segmentNr + "\n SegmentType: " + this.segmentType + "\n PageAssociation: " + this.pageAssociation + "\n Referred-to segments: " + stringBuilder.toString() + "\n";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/SegmentHeader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */