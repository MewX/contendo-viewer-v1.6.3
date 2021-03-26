/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import com.levigo.jbig2.err.IntegerMaxValueException;
/*     */ import com.levigo.jbig2.err.InvalidHeaderValueException;
/*     */ import com.levigo.jbig2.err.JBIG2Exception;
/*     */ import com.levigo.jbig2.image.Bitmaps;
/*     */ import com.levigo.jbig2.segments.EndOfStripe;
/*     */ import com.levigo.jbig2.segments.PageInformation;
/*     */ import com.levigo.jbig2.segments.RegionSegmentInformation;
/*     */ import com.levigo.jbig2.util.CombinationOperator;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
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
/*     */ class JBIG2Page
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(JBIG2Page.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private final Map<Integer, SegmentHeader> segments = new TreeMap<>();
/*     */   
/*     */   private final int pageNumber;
/*     */   
/*     */   private Bitmap pageBitmap;
/*     */   
/*     */   private int finalHeight;
/*     */   
/*     */   private int finalWidth;
/*     */   
/*     */   private int resolutionX;
/*     */   
/*     */   private int resolutionY;
/*     */   private final JBIG2Document document;
/*     */   
/*     */   protected JBIG2Page(JBIG2Document paramJBIG2Document, int paramInt) {
/*  64 */     this.document = paramJBIG2Document;
/*  65 */     this.pageNumber = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SegmentHeader getSegment(int paramInt) {
/*  76 */     SegmentHeader segmentHeader = this.segments.get(Integer.valueOf(paramInt));
/*     */     
/*  78 */     if (null != segmentHeader) {
/*  79 */       return segmentHeader;
/*     */     }
/*     */     
/*  82 */     if (null != this.document) {
/*  83 */       return this.document.getGlobalSegment(paramInt);
/*     */     }
/*     */     
/*  86 */     log.info("Segment not found, returning null.");
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SegmentHeader getPageInformationSegment() {
/*  96 */     for (SegmentHeader segmentHeader : this.segments.values()) {
/*  97 */       if (segmentHeader.getSegmentType() == 48) {
/*  98 */         return segmentHeader;
/*     */       }
/*     */     } 
/*     */     
/* 102 */     log.info("Page information segment not found.");
/* 103 */     return null;
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
/*     */   
/*     */   protected Bitmap getBitmap() throws JBIG2Exception, IOException {
/* 121 */     if (null == this.pageBitmap) {
/* 122 */       composePageBitmap();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     return this.pageBitmap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void composePageBitmap() throws IOException, JBIG2Exception {
/* 139 */     if (this.pageNumber > 0) {
/*     */       
/* 141 */       PageInformation pageInformation = (PageInformation)getPageInformationSegment().getSegmentData();
/* 142 */       createPage(pageInformation);
/* 143 */       clearSegmentData();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createPage(PageInformation paramPageInformation) throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 149 */     if (!paramPageInformation.isStriped() || paramPageInformation.getHeight() != -1) {
/*     */       
/* 151 */       createNormalPage(paramPageInformation);
/*     */     } else {
/* 153 */       createStripedPage(paramPageInformation);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createNormalPage(PageInformation paramPageInformation) throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 160 */     this.pageBitmap = new Bitmap(paramPageInformation.getWidth(), paramPageInformation.getHeight());
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (paramPageInformation.getDefaultPixelValue() != 0) {
/* 165 */       Arrays.fill(this.pageBitmap.getByteArray(), (byte)-1);
/*     */     }
/*     */     
/* 168 */     for (SegmentHeader segmentHeader : this.segments.values()) {
/*     */       Region region; Bitmap bitmap; RegionSegmentInformation regionSegmentInformation; CombinationOperator combinationOperator;
/* 170 */       switch (segmentHeader.getSegmentType()) {
/*     */         case 6:
/*     */         case 7:
/*     */         case 22:
/*     */         case 23:
/*     */         case 38:
/*     */         case 39:
/*     */         case 42:
/*     */         case 43:
/* 179 */           region = (Region)segmentHeader.getSegmentData();
/*     */           
/* 181 */           bitmap = region.getRegionBitmap();
/*     */           
/* 183 */           if (fitsPage(paramPageInformation, bitmap)) {
/* 184 */             this.pageBitmap = bitmap; continue;
/*     */           } 
/* 186 */           regionSegmentInformation = region.getRegionInfo();
/* 187 */           combinationOperator = getCombinationOperator(paramPageInformation, regionSegmentInformation.getCombinationOperator());
/*     */           
/* 189 */           Bitmaps.blit(bitmap, this.pageBitmap, regionSegmentInformation.getXLocation(), regionSegmentInformation.getYLocation(), combinationOperator);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean fitsPage(PageInformation paramPageInformation, Bitmap paramBitmap) {
/* 207 */     return (countRegions() == 1 && paramPageInformation.getDefaultPixelValue() == 0 && paramPageInformation.getWidth() == paramBitmap.getWidth() && paramPageInformation.getHeight() == paramBitmap.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createStripedPage(PageInformation paramPageInformation) throws IOException, IntegerMaxValueException, InvalidHeaderValueException {
/* 214 */     ArrayList<SegmentData> arrayList = collectPageStripes();
/*     */     
/* 216 */     this.pageBitmap = new Bitmap(paramPageInformation.getWidth(), this.finalHeight);
/*     */     
/* 218 */     int i = 0;
/* 219 */     for (SegmentData segmentData : arrayList) {
/* 220 */       if (segmentData instanceof EndOfStripe) {
/* 221 */         i = ((EndOfStripe)segmentData).getLineNumber() + 1; continue;
/*     */       } 
/* 223 */       Region region = (Region)segmentData;
/* 224 */       RegionSegmentInformation regionSegmentInformation = region.getRegionInfo();
/* 225 */       CombinationOperator combinationOperator = getCombinationOperator(paramPageInformation, regionSegmentInformation.getCombinationOperator());
/* 226 */       Bitmaps.blit(region.getRegionBitmap(), this.pageBitmap, regionSegmentInformation.getXLocation(), i, combinationOperator);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ArrayList<SegmentData> collectPageStripes() {
/* 232 */     ArrayList<Region> arrayList = new ArrayList();
/* 233 */     for (SegmentHeader segmentHeader : this.segments.values()) {
/*     */       Region region; EndOfStripe endOfStripe;
/* 235 */       switch (segmentHeader.getSegmentType()) {
/*     */         case 6:
/*     */         case 7:
/*     */         case 22:
/*     */         case 23:
/*     */         case 38:
/*     */         case 39:
/*     */         case 42:
/*     */         case 43:
/* 244 */           region = (Region)segmentHeader.getSegmentData();
/* 245 */           arrayList.add(region);
/*     */ 
/*     */         
/*     */         case 50:
/* 249 */           endOfStripe = (EndOfStripe)segmentHeader.getSegmentData();
/* 250 */           arrayList.add(endOfStripe);
/* 251 */           this.finalHeight = endOfStripe.getLineNumber() + 1;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 256 */     return (ArrayList)arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int countRegions() {
/* 266 */     byte b = 0;
/*     */     
/* 268 */     for (SegmentHeader segmentHeader : this.segments.values()) {
/* 269 */       switch (segmentHeader.getSegmentType()) {
/*     */         case 6:
/*     */         case 7:
/*     */         case 22:
/*     */         case 23:
/*     */         case 38:
/*     */         case 39:
/*     */         case 42:
/*     */         case 43:
/* 278 */           b++;
/*     */       } 
/*     */     
/*     */     } 
/* 282 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CombinationOperator getCombinationOperator(PageInformation paramPageInformation, CombinationOperator paramCombinationOperator) {
/* 293 */     if (paramPageInformation.isCombinationOperatorOverrideAllowed()) {
/* 294 */       return paramCombinationOperator;
/*     */     }
/* 296 */     return paramPageInformation.getCombinationOperator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void add(SegmentHeader paramSegmentHeader) {
/* 307 */     this.segments.put(Integer.valueOf(paramSegmentHeader.getSegmentNr()), paramSegmentHeader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearSegmentData() {
/* 315 */     Set<Integer> set = this.segments.keySet();
/*     */     
/* 317 */     for (Integer integer : set) {
/* 318 */       ((SegmentHeader)this.segments.get(integer)).cleanSegmentData();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clearPageData() {
/* 326 */     this.pageBitmap = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getHeight() throws IOException, JBIG2Exception {
/* 337 */     if (this.finalHeight == 0) {
/* 338 */       PageInformation pageInformation = (PageInformation)getPageInformationSegment().getSegmentData();
/* 339 */       if (pageInformation.getHeight() == -1) {
/* 340 */         getBitmap();
/*     */       } else {
/* 342 */         this.finalHeight = pageInformation.getHeight();
/*     */       } 
/*     */     } 
/* 345 */     return this.finalHeight;
/*     */   }
/*     */   
/*     */   protected int getWidth() {
/* 349 */     if (this.finalWidth == 0) {
/* 350 */       PageInformation pageInformation = (PageInformation)getPageInformationSegment().getSegmentData();
/* 351 */       this.finalWidth = pageInformation.getWidth();
/*     */     } 
/* 353 */     return this.finalWidth;
/*     */   }
/*     */   
/*     */   protected int getResolutionX() {
/* 357 */     if (this.resolutionX == 0) {
/* 358 */       PageInformation pageInformation = (PageInformation)getPageInformationSegment().getSegmentData();
/* 359 */       this.resolutionX = pageInformation.getResolutionX();
/*     */     } 
/* 361 */     return this.resolutionX;
/*     */   }
/*     */   
/*     */   protected int getResolutionY() {
/* 365 */     if (this.resolutionY == 0) {
/* 366 */       PageInformation pageInformation = (PageInformation)getPageInformationSegment().getSegmentData();
/* 367 */       this.resolutionY = pageInformation.getResolutionY();
/*     */     } 
/* 369 */     return this.resolutionY;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 374 */     return getClass().getSimpleName() + " (Page number: " + this.pageNumber + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2Page.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */