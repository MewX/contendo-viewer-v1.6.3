/*     */ package com.github.jaiimageio.stream;
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
/*     */ class StreamSegmentMapperImpl
/*     */   implements StreamSegmentMapper
/*     */ {
/*     */   private long[] segmentPositions;
/*     */   private int[] segmentLengths;
/*     */   
/*     */   public StreamSegmentMapperImpl(long[] segmentPositions, int[] segmentLengths) {
/*  64 */     this.segmentPositions = (long[])segmentPositions.clone();
/*  65 */     this.segmentLengths = (int[])segmentLengths.clone();
/*     */   }
/*     */   
/*     */   public StreamSegment getStreamSegment(long position, int length) {
/*  69 */     int numSegments = this.segmentLengths.length;
/*  70 */     for (int i = 0; i < numSegments; i++) {
/*  71 */       int len = this.segmentLengths[i];
/*  72 */       if (position < len) {
/*  73 */         return new StreamSegment(this.segmentPositions[i] + position, 
/*  74 */             Math.min(len - (int)position, length));
/*     */       }
/*     */       
/*  77 */       position -= len;
/*     */     } 
/*     */     
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void getStreamSegment(long position, int length, StreamSegment seg) {
/*  85 */     int numSegments = this.segmentLengths.length;
/*  86 */     for (int i = 0; i < numSegments; i++) {
/*  87 */       int len = this.segmentLengths[i];
/*  88 */       if (position < len) {
/*  89 */         seg.setStartPos(this.segmentPositions[i] + position);
/*  90 */         seg.setSegmentLength(Math.min(len - (int)position, length));
/*     */         return;
/*     */       } 
/*  93 */       position -= len;
/*     */     } 
/*     */     
/*  96 */     seg.setStartPos(-1L);
/*  97 */     seg.setSegmentLength(-1);
/*     */   }
/*     */ 
/*     */   
/*     */   long length() {
/* 102 */     int numSegments = this.segmentLengths.length;
/* 103 */     long len = 0L;
/*     */     
/* 105 */     for (int i = 0; i < numSegments; i++) {
/* 106 */       len += this.segmentLengths[i];
/*     */     }
/*     */     
/* 109 */     return len;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/stream/StreamSegmentMapperImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */