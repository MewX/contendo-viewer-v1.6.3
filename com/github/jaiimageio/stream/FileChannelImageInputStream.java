/*     */ package com.github.jaiimageio.stream;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.nio.channels.FileChannel;
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
/*     */ public class FileChannelImageInputStream
/*     */   extends ImageInputStreamImpl
/*     */ {
/*     */   private FileChannel channel;
/*     */   private MappedByteBuffer mappedBuffer;
/*     */   private long mappedPos;
/*     */   private long mappedUpperBound;
/*     */   
/*     */   public FileChannelImageInputStream(FileChannel channel) throws IOException {
/* 113 */     if (channel == null)
/* 114 */       throw new IllegalArgumentException("channel == null"); 
/* 115 */     if (!channel.isOpen()) {
/* 116 */       throw new IllegalArgumentException("channel.isOpen() == false");
/*     */     }
/*     */ 
/*     */     
/* 120 */     this.channel = channel;
/*     */ 
/*     */     
/* 123 */     long channelPosition = channel.position();
/*     */ 
/*     */     
/* 126 */     this.streamPos = this.flushedPos = channelPosition;
/*     */ 
/*     */     
/* 129 */     long fullSize = channel.size() - channelPosition;
/* 130 */     long mappedSize = Math.min(fullSize, 2147483647L);
/*     */ 
/*     */     
/* 133 */     this.mappedPos = 0L;
/* 134 */     this.mappedUpperBound = this.mappedPos + mappedSize;
/*     */ 
/*     */     
/* 137 */     this.mappedBuffer = channel.map(FileChannel.MapMode.READ_ONLY, channelPosition, mappedSize);
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
/*     */   private MappedByteBuffer getMappedBuffer(int len) throws IOException {
/* 153 */     if (this.streamPos < this.mappedPos || this.streamPos + len >= this.mappedUpperBound) {
/*     */ 
/*     */       
/* 156 */       this.mappedPos = this.streamPos;
/*     */ 
/*     */       
/* 159 */       long mappedSize = Math.min(this.channel.size() - this.mappedPos, 2147483647L);
/*     */ 
/*     */ 
/*     */       
/* 163 */       this.mappedUpperBound = this.mappedPos + mappedSize;
/*     */ 
/*     */       
/* 166 */       this.mappedBuffer = this.channel.map(FileChannel.MapMode.READ_ONLY, this.mappedPos, mappedSize);
/*     */ 
/*     */ 
/*     */       
/* 170 */       this.mappedBuffer.order(getByteOrder());
/*     */     } 
/*     */ 
/*     */     
/* 174 */     return this.mappedBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 180 */     checkClosed();
/* 181 */     this.bitOffset = 0;
/*     */ 
/*     */     
/* 184 */     ByteBuffer byteBuffer = getMappedBuffer(1);
/*     */ 
/*     */     
/* 187 */     if (byteBuffer.remaining() < 1)
/*     */     {
/* 189 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 193 */     int value = byteBuffer.get() & 0xFF;
/*     */ 
/*     */     
/* 196 */     this.streamPos++;
/*     */ 
/*     */ 
/*     */     
/* 200 */     return value;
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 204 */     if (off < 0 || len < 0 || off + len > b.length)
/*     */     {
/* 206 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > b.length");
/*     */     }
/* 208 */     if (len == 0) {
/* 209 */       return 0;
/*     */     }
/*     */     
/* 212 */     checkClosed();
/* 213 */     this.bitOffset = 0;
/*     */ 
/*     */     
/* 216 */     ByteBuffer byteBuffer = getMappedBuffer(len);
/*     */ 
/*     */     
/* 219 */     int numBytesRemaining = byteBuffer.remaining();
/*     */ 
/*     */     
/* 222 */     if (numBytesRemaining < 1)
/*     */     {
/* 224 */       return -1; } 
/* 225 */     if (len > numBytesRemaining)
/*     */     {
/*     */       
/* 228 */       len = numBytesRemaining;
/*     */     }
/*     */ 
/*     */     
/* 232 */     byteBuffer.get(b, off, len);
/*     */ 
/*     */     
/* 235 */     this.streamPos += len;
/*     */     
/* 237 */     return len;
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
/*     */   public void close() throws IOException {
/* 250 */     super.close();
/* 251 */     this.channel = null;
/*     */   }
/*     */   
/*     */   public void readFully(char[] c, int off, int len) throws IOException {
/* 255 */     if (off < 0 || len < 0 || off + len > c.length)
/*     */     {
/* 257 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > c.length");
/*     */     }
/* 259 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 264 */     int byteLen = 2 * len;
/*     */ 
/*     */     
/* 267 */     ByteBuffer byteBuffer = getMappedBuffer(byteLen);
/*     */ 
/*     */     
/* 270 */     if (byteBuffer.remaining() < byteLen) {
/* 271 */       throw new EOFException();
/*     */     }
/*     */ 
/*     */     
/* 275 */     CharBuffer viewBuffer = byteBuffer.asCharBuffer();
/*     */ 
/*     */     
/* 278 */     viewBuffer.get(c, off, len);
/*     */ 
/*     */     
/* 281 */     seek(this.streamPos + byteLen);
/*     */   }
/*     */   
/*     */   public void readFully(short[] s, int off, int len) throws IOException {
/* 285 */     if (off < 0 || len < 0 || off + len > s.length)
/*     */     {
/* 287 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > s.length");
/*     */     }
/* 289 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 294 */     int byteLen = 2 * len;
/*     */ 
/*     */     
/* 297 */     ByteBuffer byteBuffer = getMappedBuffer(byteLen);
/*     */ 
/*     */     
/* 300 */     if (byteBuffer.remaining() < byteLen) {
/* 301 */       throw new EOFException();
/*     */     }
/*     */ 
/*     */     
/* 305 */     ShortBuffer viewBuffer = byteBuffer.asShortBuffer();
/*     */ 
/*     */     
/* 308 */     viewBuffer.get(s, off, len);
/*     */ 
/*     */     
/* 311 */     seek(this.streamPos + byteLen);
/*     */   }
/*     */   
/*     */   public void readFully(int[] i, int off, int len) throws IOException {
/* 315 */     if (off < 0 || len < 0 || off + len > i.length)
/*     */     {
/* 317 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > i.length");
/*     */     }
/* 319 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 324 */     int byteLen = 4 * len;
/*     */ 
/*     */     
/* 327 */     ByteBuffer byteBuffer = getMappedBuffer(byteLen);
/*     */ 
/*     */     
/* 330 */     if (byteBuffer.remaining() < byteLen) {
/* 331 */       throw new EOFException();
/*     */     }
/*     */ 
/*     */     
/* 335 */     IntBuffer viewBuffer = byteBuffer.asIntBuffer();
/*     */ 
/*     */     
/* 338 */     viewBuffer.get(i, off, len);
/*     */ 
/*     */     
/* 341 */     seek(this.streamPos + byteLen);
/*     */   }
/*     */   
/*     */   public void readFully(long[] l, int off, int len) throws IOException {
/* 345 */     if (off < 0 || len < 0 || off + len > l.length)
/*     */     {
/* 347 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > l.length");
/*     */     }
/* 349 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 354 */     int byteLen = 8 * len;
/*     */ 
/*     */     
/* 357 */     ByteBuffer byteBuffer = getMappedBuffer(byteLen);
/*     */ 
/*     */     
/* 360 */     if (byteBuffer.remaining() < byteLen) {
/* 361 */       throw new EOFException();
/*     */     }
/*     */ 
/*     */     
/* 365 */     LongBuffer viewBuffer = byteBuffer.asLongBuffer();
/*     */ 
/*     */     
/* 368 */     viewBuffer.get(l, off, len);
/*     */ 
/*     */     
/* 371 */     seek(this.streamPos + byteLen);
/*     */   }
/*     */   
/*     */   public void readFully(float[] f, int off, int len) throws IOException {
/* 375 */     if (off < 0 || len < 0 || off + len > f.length)
/*     */     {
/* 377 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > f.length");
/*     */     }
/* 379 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 384 */     int byteLen = 4 * len;
/*     */ 
/*     */     
/* 387 */     ByteBuffer byteBuffer = getMappedBuffer(byteLen);
/*     */ 
/*     */     
/* 390 */     if (byteBuffer.remaining() < byteLen) {
/* 391 */       throw new EOFException();
/*     */     }
/*     */ 
/*     */     
/* 395 */     FloatBuffer viewBuffer = byteBuffer.asFloatBuffer();
/*     */ 
/*     */     
/* 398 */     viewBuffer.get(f, off, len);
/*     */ 
/*     */     
/* 401 */     seek(this.streamPos + byteLen);
/*     */   }
/*     */   
/*     */   public void readFully(double[] d, int off, int len) throws IOException {
/* 405 */     if (off < 0 || len < 0 || off + len > d.length)
/*     */     {
/* 407 */       throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > d.length");
/*     */     }
/* 409 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 414 */     int byteLen = 8 * len;
/*     */ 
/*     */     
/* 417 */     ByteBuffer byteBuffer = getMappedBuffer(byteLen);
/*     */ 
/*     */     
/* 420 */     if (byteBuffer.remaining() < byteLen) {
/* 421 */       throw new EOFException();
/*     */     }
/*     */ 
/*     */     
/* 425 */     DoubleBuffer viewBuffer = byteBuffer.asDoubleBuffer();
/*     */ 
/*     */     
/* 428 */     viewBuffer.get(d, off, len);
/*     */ 
/*     */     
/* 431 */     seek(this.streamPos + byteLen);
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
/*     */   public long length() {
/* 444 */     long length = -1L;
/*     */ 
/*     */     
/*     */     try {
/* 448 */       length = this.channel.size();
/* 449 */     } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */     
/* 453 */     return length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long pos) throws IOException {
/* 463 */     super.seek(pos);
/*     */     
/* 465 */     if (pos >= this.mappedPos && pos < this.mappedUpperBound) {
/*     */       
/* 467 */       this.mappedBuffer.position((int)(pos - this.mappedPos));
/*     */     }
/*     */     else {
/*     */       
/* 471 */       int len = (int)Math.min(this.channel.size() - pos, 2147483647L);
/* 472 */       this.mappedBuffer = getMappedBuffer(len);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setByteOrder(ByteOrder networkByteOrder) {
/* 477 */     super.setByteOrder(networkByteOrder);
/* 478 */     this.mappedBuffer.order(networkByteOrder);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/stream/FileChannelImageInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */