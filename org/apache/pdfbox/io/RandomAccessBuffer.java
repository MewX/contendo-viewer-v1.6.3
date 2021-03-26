/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomAccessBuffer
/*     */   implements Cloneable, RandomAccess
/*     */ {
/*     */   private static final int DEFAULT_CHUNK_SIZE = 1024;
/*  33 */   private int chunkSize = 1024;
/*     */   
/*  35 */   private List<byte[]> bufferList = null;
/*     */ 
/*     */   
/*     */   private byte[] currentBuffer;
/*     */ 
/*     */   
/*     */   private long pointer;
/*     */ 
/*     */   
/*     */   private int currentBufferPointer;
/*     */   
/*     */   private long size;
/*     */   
/*     */   private int bufferListIndex;
/*     */   
/*     */   private int bufferListMaxIndex;
/*     */ 
/*     */   
/*     */   public RandomAccessBuffer() {
/*  54 */     this(1024);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RandomAccessBuffer(int definedChunkSize) {
/*  63 */     this.bufferList = (List)new ArrayList<byte>();
/*  64 */     this.chunkSize = definedChunkSize;
/*  65 */     this.currentBuffer = new byte[this.chunkSize];
/*  66 */     this.bufferList.add(this.currentBuffer);
/*  67 */     this.pointer = 0L;
/*  68 */     this.currentBufferPointer = 0;
/*  69 */     this.size = 0L;
/*  70 */     this.bufferListIndex = 0;
/*  71 */     this.bufferListMaxIndex = 0;
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
/*     */   public RandomAccessBuffer(byte[] input) {
/*  83 */     this.bufferList = (List)new ArrayList<byte>(1);
/*  84 */     this.chunkSize = input.length;
/*  85 */     this.currentBuffer = input;
/*  86 */     this.bufferList.add(this.currentBuffer);
/*  87 */     this.pointer = 0L;
/*  88 */     this.currentBufferPointer = 0;
/*  89 */     this.size = this.chunkSize;
/*  90 */     this.bufferListIndex = 0;
/*  91 */     this.bufferListMaxIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessBuffer(InputStream input) throws IOException {
/* 102 */     this();
/* 103 */     byte[] byteBuffer = new byte[8192];
/* 104 */     int bytesRead = 0;
/* 105 */     while ((bytesRead = input.read(byteBuffer)) > -1)
/*     */     {
/* 107 */       write(byteBuffer, 0, bytesRead);
/*     */     }
/* 109 */     seek(0L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessBuffer clone() {
/* 115 */     RandomAccessBuffer copy = new RandomAccessBuffer(this.chunkSize);
/*     */     
/* 117 */     copy.bufferList = (List)new ArrayList<byte>(this.bufferList.size());
/* 118 */     for (byte[] buffer : this.bufferList) {
/*     */       
/* 120 */       byte[] newBuffer = new byte[buffer.length];
/* 121 */       System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
/* 122 */       copy.bufferList.add(newBuffer);
/*     */     } 
/* 124 */     if (this.currentBuffer != null) {
/*     */       
/* 126 */       copy.currentBuffer = copy.bufferList.get(copy.bufferList.size() - 1);
/*     */     }
/*     */     else {
/*     */       
/* 130 */       copy.currentBuffer = null;
/*     */     } 
/* 132 */     copy.pointer = this.pointer;
/* 133 */     copy.currentBufferPointer = this.currentBufferPointer;
/* 134 */     copy.size = this.size;
/* 135 */     copy.bufferListIndex = this.bufferListIndex;
/* 136 */     copy.bufferListMaxIndex = this.bufferListMaxIndex;
/*     */     
/* 138 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 147 */     this.currentBuffer = null;
/* 148 */     this.bufferList.clear();
/* 149 */     this.pointer = 0L;
/* 150 */     this.currentBufferPointer = 0;
/* 151 */     this.size = 0L;
/* 152 */     this.bufferListIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 161 */     this.bufferList.clear();
/* 162 */     this.currentBuffer = new byte[this.chunkSize];
/* 163 */     this.bufferList.add(this.currentBuffer);
/* 164 */     this.pointer = 0L;
/* 165 */     this.currentBufferPointer = 0;
/* 166 */     this.size = 0L;
/* 167 */     this.bufferListIndex = 0;
/* 168 */     this.bufferListMaxIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long position) throws IOException {
/* 177 */     checkClosed();
/* 178 */     if (position < 0L)
/*     */     {
/* 180 */       throw new IOException("Invalid position " + position);
/*     */     }
/* 182 */     this.pointer = position;
/* 183 */     if (this.pointer < this.size) {
/*     */ 
/*     */       
/* 186 */       this.bufferListIndex = (int)(this.pointer / this.chunkSize);
/* 187 */       this.currentBufferPointer = (int)(this.pointer % this.chunkSize);
/* 188 */       this.currentBuffer = this.bufferList.get(this.bufferListIndex);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 194 */       this.bufferListIndex = this.bufferListMaxIndex;
/* 195 */       this.currentBuffer = this.bufferList.get(this.bufferListIndex);
/* 196 */       this.currentBufferPointer = (int)(this.size % this.chunkSize);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() throws IOException {
/* 206 */     checkClosed();
/* 207 */     return this.pointer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 216 */     checkClosed();
/* 217 */     if (this.pointer >= this.size)
/*     */     {
/* 219 */       return -1;
/*     */     }
/* 221 */     if (this.currentBufferPointer >= this.chunkSize) {
/*     */       
/* 223 */       if (this.bufferListIndex >= this.bufferListMaxIndex)
/*     */       {
/* 225 */         return -1;
/*     */       }
/*     */ 
/*     */       
/* 229 */       this.currentBuffer = this.bufferList.get(++this.bufferListIndex);
/* 230 */       this.currentBufferPointer = 0;
/*     */     } 
/*     */     
/* 233 */     this.pointer++;
/* 234 */     return this.currentBuffer[this.currentBufferPointer++] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int offset, int length) throws IOException {
/* 243 */     checkClosed();
/* 244 */     if (this.pointer >= this.size)
/*     */     {
/* 246 */       return 0;
/*     */     }
/* 248 */     int bytesRead = readRemainingBytes(b, offset, length);
/* 249 */     while (bytesRead < length && available() > 0) {
/*     */       
/* 251 */       bytesRead += readRemainingBytes(b, offset + bytesRead, length - bytesRead);
/* 252 */       if (this.currentBufferPointer == this.chunkSize)
/*     */       {
/* 254 */         nextBuffer();
/*     */       }
/*     */     } 
/* 257 */     return bytesRead;
/*     */   }
/*     */ 
/*     */   
/*     */   private int readRemainingBytes(byte[] b, int offset, int length) throws IOException {
/* 262 */     if (this.pointer >= this.size)
/*     */     {
/* 264 */       return 0;
/*     */     }
/* 266 */     int maxLength = (int)Math.min(length, this.size - this.pointer);
/* 267 */     int remainingBytes = this.chunkSize - this.currentBufferPointer;
/*     */     
/* 269 */     if (remainingBytes == 0)
/*     */     {
/* 271 */       return 0;
/*     */     }
/* 273 */     if (maxLength >= remainingBytes) {
/*     */ 
/*     */       
/* 276 */       System.arraycopy(this.currentBuffer, this.currentBufferPointer, b, offset, remainingBytes);
/*     */       
/* 278 */       this.currentBufferPointer += remainingBytes;
/* 279 */       this.pointer += remainingBytes;
/* 280 */       return remainingBytes;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 285 */     System.arraycopy(this.currentBuffer, this.currentBufferPointer, b, offset, maxLength);
/*     */     
/* 287 */     this.currentBufferPointer += maxLength;
/* 288 */     this.pointer += maxLength;
/* 289 */     return maxLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long length() throws IOException {
/* 299 */     checkClosed();
/* 300 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 309 */     checkClosed();
/*     */     
/* 311 */     if (this.currentBufferPointer >= this.chunkSize) {
/*     */       
/* 313 */       if (this.pointer + this.chunkSize >= 2147483647L)
/*     */       {
/* 315 */         throw new IOException("RandomAccessBuffer overflow");
/*     */       }
/* 317 */       expandBuffer();
/*     */     } 
/* 319 */     this.currentBuffer[this.currentBufferPointer++] = (byte)b;
/* 320 */     this.pointer++;
/* 321 */     if (this.pointer > this.size)
/*     */     {
/* 323 */       this.size = this.pointer;
/*     */     }
/*     */     
/* 326 */     if (this.currentBufferPointer >= this.chunkSize) {
/*     */       
/* 328 */       if (this.pointer + this.chunkSize >= 2147483647L)
/*     */       {
/* 330 */         throw new IOException("RandomAccessBuffer overflow");
/*     */       }
/* 332 */       expandBuffer();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 343 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int offset, int length) throws IOException {
/* 352 */     checkClosed();
/* 353 */     long newSize = this.pointer + length;
/* 354 */     int remainingBytes = this.chunkSize - this.currentBufferPointer;
/* 355 */     if (length >= remainingBytes) {
/*     */       
/* 357 */       if (newSize > 2147483647L)
/*     */       {
/* 359 */         throw new IOException("RandomAccessBuffer overflow");
/*     */       }
/*     */       
/* 362 */       System.arraycopy(b, offset, this.currentBuffer, this.currentBufferPointer, remainingBytes);
/* 363 */       int newOffset = offset + remainingBytes;
/* 364 */       long remainingBytes2Write = (length - remainingBytes);
/*     */       
/* 366 */       int numberOfNewArrays = (int)remainingBytes2Write / this.chunkSize;
/* 367 */       for (int i = 0; i < numberOfNewArrays; i++) {
/*     */         
/* 369 */         expandBuffer();
/* 370 */         System.arraycopy(b, newOffset, this.currentBuffer, this.currentBufferPointer, this.chunkSize);
/* 371 */         newOffset += this.chunkSize;
/*     */       } 
/*     */       
/* 374 */       remainingBytes2Write -= numberOfNewArrays * this.chunkSize;
/* 375 */       if (remainingBytes2Write >= 0L)
/*     */       {
/* 377 */         expandBuffer();
/* 378 */         if (remainingBytes2Write > 0L)
/*     */         {
/* 380 */           System.arraycopy(b, newOffset, this.currentBuffer, this.currentBufferPointer, (int)remainingBytes2Write);
/*     */         }
/* 382 */         this.currentBufferPointer = (int)remainingBytes2Write;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 387 */       System.arraycopy(b, offset, this.currentBuffer, this.currentBufferPointer, length);
/* 388 */       this.currentBufferPointer += length;
/*     */     } 
/* 390 */     this.pointer += length;
/* 391 */     if (this.pointer > this.size)
/*     */     {
/* 393 */       this.size = this.pointer;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void expandBuffer() throws IOException {
/* 402 */     if (this.bufferListMaxIndex > this.bufferListIndex) {
/*     */ 
/*     */       
/* 405 */       nextBuffer();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 410 */       this.currentBuffer = new byte[this.chunkSize];
/* 411 */       this.bufferList.add(this.currentBuffer);
/* 412 */       this.currentBufferPointer = 0;
/* 413 */       this.bufferListMaxIndex++;
/* 414 */       this.bufferListIndex++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nextBuffer() throws IOException {
/* 423 */     if (this.bufferListIndex == this.bufferListMaxIndex)
/*     */     {
/* 425 */       throw new IOException("No more chunks available, end of buffer reached");
/*     */     }
/* 427 */     this.currentBufferPointer = 0;
/* 428 */     this.currentBuffer = this.bufferList.get(++this.bufferListIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkClosed() throws IOException {
/* 437 */     if (this.currentBuffer == null)
/*     */     {
/*     */       
/* 440 */       throw new IOException("RandomAccessBuffer already closed");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 451 */     return (this.currentBuffer == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEOF() throws IOException {
/* 460 */     checkClosed();
/* 461 */     return (this.pointer >= this.size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 470 */     return (int)Math.min(length() - getPosition(), 2147483647L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int peek() throws IOException {
/* 479 */     int result = read();
/* 480 */     if (result != -1)
/*     */     {
/* 482 */       rewind(1);
/*     */     }
/* 484 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rewind(int bytes) throws IOException {
/* 493 */     checkClosed();
/* 494 */     seek(getPosition() - bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readFully(int length) throws IOException {
/* 503 */     byte[] b = new byte[length];
/* 504 */     int bytesRead = read(b);
/* 505 */     while (bytesRead < length)
/*     */     {
/* 507 */       bytesRead += read(b, bytesRead, length - bytesRead);
/*     */     }
/* 509 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 518 */     return read(b, 0, b.length);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */