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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SectorStreamSegmentMapper
/*     */   implements StreamSegmentMapper
/*     */ {
/*     */   long[] segmentPositions;
/*     */   int segmentLength;
/*     */   int totalLength;
/*     */   int lastSegmentLength;
/*     */   
/*     */   public SectorStreamSegmentMapper(long[] segmentPositions, int segmentLength, int totalLength) {
/* 127 */     this.segmentPositions = (long[])segmentPositions.clone();
/* 128 */     this.segmentLength = segmentLength;
/* 129 */     this.totalLength = totalLength;
/* 130 */     this.lastSegmentLength = totalLength - (segmentPositions.length - 1) * segmentLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public StreamSegment getStreamSegment(long position, int length) {
/* 135 */     int index = (int)(position / this.segmentLength);
/*     */ 
/*     */     
/* 138 */     int len = (index == this.segmentPositions.length - 1) ? this.lastSegmentLength : this.segmentLength;
/*     */ 
/*     */ 
/*     */     
/* 142 */     position -= (index * this.segmentLength);
/*     */ 
/*     */     
/* 145 */     len = (int)(len - position);
/* 146 */     if (len > length) {
/* 147 */       len = length;
/*     */     }
/* 149 */     return new StreamSegment(this.segmentPositions[index] + position, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void getStreamSegment(long position, int length, StreamSegment seg) {
/* 154 */     int index = (int)(position / this.segmentLength);
/*     */ 
/*     */     
/* 157 */     int len = (index == this.segmentPositions.length - 1) ? this.lastSegmentLength : this.segmentLength;
/*     */ 
/*     */ 
/*     */     
/* 161 */     position -= (index * this.segmentLength);
/*     */ 
/*     */     
/* 164 */     len = (int)(len - position);
/* 165 */     if (len > length) {
/* 166 */       len = length;
/*     */     }
/*     */     
/* 169 */     seg.setStartPos(this.segmentPositions[index] + position);
/* 170 */     seg.setSegmentLength(len);
/*     */   }
/*     */   
/*     */   long length() {
/* 174 */     return this.totalLength;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/stream/SectorStreamSegmentMapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */