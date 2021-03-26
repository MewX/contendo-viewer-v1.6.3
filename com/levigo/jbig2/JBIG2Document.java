/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import com.levigo.jbig2.io.SubInputStream;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
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
/*     */ class JBIG2Document
/*     */ {
/*  37 */   private static final Logger log = LoggerFactory.getLogger(JBIG2Document.class);
/*     */ 
/*     */   
/*  40 */   private int[] FILE_HEADER_ID = new int[] { 151, 74, 66, 50, 13, 10, 26, 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private final Map<Integer, JBIG2Page> pages = new TreeMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private short fileHeaderLength = 9;
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
/*  65 */   private short organisationType = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int RANDOM = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SEQUENTIAL = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean amountOfPagesUnknown = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int amountOfPages;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean gbUseExtTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final SubInputStream subInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JBIG2Globals globalSegments;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JBIG2Document(ImageInputStream paramImageInputStream) throws IOException {
/* 108 */     this(paramImageInputStream, null);
/*     */   }
/*     */   
/*     */   protected JBIG2Document(ImageInputStream paramImageInputStream, JBIG2Globals paramJBIG2Globals) throws IOException {
/* 112 */     if (paramImageInputStream == null) {
/* 113 */       throw new IllegalArgumentException("imageInputStream must not be null");
/*     */     }
/* 115 */     this.subInputStream = new SubInputStream(paramImageInputStream, 0L, Long.MAX_VALUE);
/* 116 */     this.globalSegments = paramJBIG2Globals;
/*     */     
/* 118 */     mapStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SegmentHeader getGlobalSegment(int paramInt) {
/* 129 */     if (null != this.globalSegments) {
/* 130 */       return this.globalSegments.getSegment(paramInt);
/*     */     }
/*     */     
/* 133 */     if (log.isErrorEnabled()) {
/* 134 */       log.error("Segment not found. Returning null.");
/*     */     }
/*     */     
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JBIG2Page getPage(int paramInt) {
/* 148 */     return this.pages.get(Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getAmountOfPages() throws IOException {
/* 159 */     if (this.amountOfPagesUnknown || this.amountOfPages == 0) {
/* 160 */       if (this.pages.size() == 0) {
/* 161 */         mapStream();
/*     */       }
/*     */       
/* 164 */       return this.pages.size();
/*     */     } 
/* 166 */     return this.amountOfPages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mapStream() throws IOException {
/* 174 */     LinkedList<SegmentHeader> linkedList = new LinkedList();
/*     */     
/* 176 */     long l = 0L;
/* 177 */     int i = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     if (isFileHeaderPresent()) {
/* 183 */       parseFileHeader();
/* 184 */       l += this.fileHeaderLength;
/*     */     } 
/*     */     
/* 187 */     if (this.globalSegments == null) {
/* 188 */       this.globalSegments = new JBIG2Globals();
/*     */     }
/*     */     
/* 191 */     JBIG2Page jBIG2Page = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     while (i != 51 && !reachedEndOfStream(l)) {
/* 198 */       SegmentHeader segmentHeader = new SegmentHeader(this, this.subInputStream, l, this.organisationType);
/*     */       
/* 200 */       int j = segmentHeader.getPageAssociation();
/* 201 */       i = segmentHeader.getSegmentType();
/*     */       
/* 203 */       if (j != 0) {
/* 204 */         jBIG2Page = getPage(j);
/* 205 */         if (jBIG2Page == null) {
/* 206 */           jBIG2Page = new JBIG2Page(this, j);
/* 207 */           this.pages.put(Integer.valueOf(j), jBIG2Page);
/*     */         } 
/* 209 */         jBIG2Page.add(segmentHeader);
/*     */       } else {
/* 211 */         this.globalSegments.addSegment(Integer.valueOf(segmentHeader.getSegmentNr()), segmentHeader);
/*     */       } 
/* 213 */       linkedList.add(segmentHeader);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       l = this.subInputStream.getStreamPosition();
/*     */ 
/*     */       
/* 224 */       if (this.organisationType == 1) {
/* 225 */         l += segmentHeader.getSegmentDataLength();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     determineRandomDataOffsets(linkedList, l);
/*     */   }
/*     */   
/*     */   private boolean isFileHeaderPresent() throws IOException {
/* 237 */     SubInputStream subInputStream = this.subInputStream;
/* 238 */     subInputStream.mark();
/*     */     
/* 240 */     for (int i : this.FILE_HEADER_ID) {
/* 241 */       if (i != subInputStream.read()) {
/* 242 */         subInputStream.reset();
/* 243 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     subInputStream.reset();
/* 248 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void determineRandomDataOffsets(List<SegmentHeader> paramList, long paramLong) {
/* 258 */     if (this.organisationType == 0) {
/* 259 */       for (SegmentHeader segmentHeader : paramList) {
/* 260 */         segmentHeader.setSegmentDataStartOffset(paramLong);
/* 261 */         paramLong += segmentHeader.getSegmentDataLength();
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
/*     */   private void parseFileHeader() throws IOException {
/* 274 */     this.subInputStream.seek(0L);
/*     */ 
/*     */     
/* 277 */     this.subInputStream.skipBytes(8);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     this.subInputStream.readBits(5);
/*     */ 
/*     */     
/* 287 */     if (this.subInputStream.readBit() == 1) {
/* 288 */       this.gbUseExtTemplate = true;
/*     */     }
/*     */ 
/*     */     
/* 292 */     if (this.subInputStream.readBit() != 1) {
/* 293 */       this.amountOfPagesUnknown = false;
/*     */     }
/*     */ 
/*     */     
/* 297 */     this.organisationType = (short)this.subInputStream.readBit();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 304 */     if (!this.amountOfPagesUnknown) {
/* 305 */       this.amountOfPages = (int)this.subInputStream.readUnsignedInt();
/* 306 */       this.fileHeaderLength = 13;
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
/*     */   private boolean reachedEndOfStream(long paramLong) throws IOException {
/*     */     try {
/* 322 */       this.subInputStream.seek(paramLong);
/* 323 */       this.subInputStream.readBits(32);
/* 324 */       return false;
/* 325 */     } catch (EOFException eOFException) {
/* 326 */       return true;
/* 327 */     } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
/* 328 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected JBIG2Globals getGlobalSegments() {
/* 333 */     return this.globalSegments;
/*     */   }
/*     */   
/*     */   protected boolean isAmountOfPagesUnknown() {
/* 337 */     return this.amountOfPagesUnknown;
/*     */   }
/*     */   
/*     */   boolean isGbUseExtTemplate() {
/* 341 */     return this.gbUseExtTemplate;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2Document.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */