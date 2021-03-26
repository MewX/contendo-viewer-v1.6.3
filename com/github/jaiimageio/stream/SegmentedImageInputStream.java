/*     */ package com.github.jaiimageio.stream;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.ImageInputStreamImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SegmentedImageInputStream
/*     */   extends ImageInputStreamImpl
/*     */ {
/*     */   private ImageInputStream stream;
/*     */   private StreamSegmentMapper mapper;
/*     */   private StreamSegment streamSegment;
/*     */   
/*     */   public SegmentedImageInputStream(ImageInputStream stream, StreamSegmentMapper mapper) {
/* 275 */     this.streamSegment = new StreamSegment();
/*     */     this.stream = stream;
/*     */     this.mapper = mapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public SegmentedImageInputStream(ImageInputStream stream, long[] segmentPositions, int[] segmentLengths) {
/*     */     this(stream, new StreamSegmentMapperImpl(segmentPositions, segmentLengths));
/*     */   }
/*     */   
/*     */   public SegmentedImageInputStream(ImageInputStream stream, long[] segmentPositions, int segmentLength, int totalLength) {
/*     */     this(stream, new SectorStreamSegmentMapper(segmentPositions, segmentLength, totalLength));
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 290 */     this.mapper.getStreamSegment(this.streamPos, 1, this.streamSegment);
/* 291 */     int streamSegmentLength = this.streamSegment.getSegmentLength();
/* 292 */     if (streamSegmentLength < 0) {
/* 293 */       return -1;
/*     */     }
/* 295 */     this.stream.seek(this.streamSegment.getStartPos());
/*     */ 
/*     */ 
/*     */     
/* 299 */     int val = this.stream.read();
/* 300 */     this.streamPos++;
/* 301 */     return val;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 353 */     if (b == null) {
/* 354 */       throw new NullPointerException();
/*     */     }
/* 356 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 357 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 359 */     if (len == 0) {
/* 360 */       return 0;
/*     */     }
/*     */     
/* 363 */     this.mapper.getStreamSegment(this.streamPos, len, this.streamSegment);
/* 364 */     int streamSegmentLength = this.streamSegment.getSegmentLength();
/* 365 */     if (streamSegmentLength < 0) {
/* 366 */       return -1;
/*     */     }
/* 368 */     this.stream.seek(this.streamSegment.getStartPos());
/*     */     
/* 370 */     int nbytes = this.stream.read(b, off, streamSegmentLength);
/* 371 */     this.streamPos += nbytes;
/* 372 */     return nbytes;
/*     */   }
/*     */   
/*     */   public long length() {
/*     */     long len;
/* 377 */     if (this.mapper instanceof StreamSegmentMapperImpl) {
/* 378 */       len = ((StreamSegmentMapperImpl)this.mapper).length();
/* 379 */     } else if (this.mapper instanceof SectorStreamSegmentMapper) {
/* 380 */       len = ((SectorStreamSegmentMapper)this.mapper).length();
/* 381 */     } else if (this.mapper != null) {
/* 382 */       long pos = len = 0L;
/* 383 */       StreamSegment seg = this.mapper.getStreamSegment(pos, 2147483647);
/* 384 */       while ((len = seg.getSegmentLength()) > 0L) {
/* 385 */         pos += len;
/* 386 */         seg.setSegmentLength(0);
/* 387 */         this.mapper.getStreamSegment(pos, 2147483647, seg);
/*     */       } 
/* 389 */       len = pos;
/*     */     } else {
/* 391 */       len = super.length();
/*     */     } 
/*     */     
/* 394 */     return len;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/stream/SegmentedImageInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */