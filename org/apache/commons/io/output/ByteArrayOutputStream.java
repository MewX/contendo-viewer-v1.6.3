/*     */ package org.apache.commons.io.output;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.input.ClosedInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ByteArrayOutputStream
/*     */   extends OutputStream
/*     */ {
/*     */   static final int DEFAULT_SIZE = 1024;
/*  61 */   private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*     */ 
/*     */   
/*  64 */   private final List<byte[]> buffers = (List)new ArrayList<>();
/*     */ 
/*     */   
/*     */   private int currentBufferIndex;
/*     */ 
/*     */   
/*     */   private int filledBufferSum;
/*     */ 
/*     */   
/*     */   private byte[] currentBuffer;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private boolean reuseBuffers = true;
/*     */ 
/*     */   
/*     */   public ByteArrayOutputStream() {
/*  81 */     this(1024);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteArrayOutputStream(int size) {
/*  92 */     if (size < 0) {
/*  93 */       throw new IllegalArgumentException("Negative initial size: " + size);
/*     */     }
/*     */     
/*  96 */     synchronized (this) {
/*  97 */       needNewBuffer(size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void needNewBuffer(int newcount) {
/* 108 */     if (this.currentBufferIndex < this.buffers.size() - 1) {
/*     */       
/* 110 */       this.filledBufferSum += this.currentBuffer.length;
/*     */       
/* 112 */       this.currentBufferIndex++;
/* 113 */       this.currentBuffer = this.buffers.get(this.currentBufferIndex);
/*     */     } else {
/*     */       int newBufferSize;
/*     */       
/* 117 */       if (this.currentBuffer == null) {
/* 118 */         newBufferSize = newcount;
/* 119 */         this.filledBufferSum = 0;
/*     */       } else {
/* 121 */         newBufferSize = Math.max(this.currentBuffer.length << 1, newcount - this.filledBufferSum);
/*     */ 
/*     */         
/* 124 */         this.filledBufferSum += this.currentBuffer.length;
/*     */       } 
/*     */       
/* 127 */       this.currentBufferIndex++;
/* 128 */       this.currentBuffer = new byte[newBufferSize];
/* 129 */       this.buffers.add(this.currentBuffer);
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
/*     */   public void write(byte[] b, int off, int len) {
/* 141 */     if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 146 */       throw new IndexOutOfBoundsException(); } 
/* 147 */     if (len == 0) {
/*     */       return;
/*     */     }
/* 150 */     synchronized (this) {
/* 151 */       int newcount = this.count + len;
/* 152 */       int remaining = len;
/* 153 */       int inBufferPos = this.count - this.filledBufferSum;
/* 154 */       while (remaining > 0) {
/* 155 */         int part = Math.min(remaining, this.currentBuffer.length - inBufferPos);
/* 156 */         System.arraycopy(b, off + len - remaining, this.currentBuffer, inBufferPos, part);
/* 157 */         remaining -= part;
/* 158 */         if (remaining > 0) {
/* 159 */           needNewBuffer(newcount);
/* 160 */           inBufferPos = 0;
/*     */         } 
/*     */       } 
/* 163 */       this.count = newcount;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void write(int b) {
/* 173 */     int inBufferPos = this.count - this.filledBufferSum;
/* 174 */     if (inBufferPos == this.currentBuffer.length) {
/* 175 */       needNewBuffer(this.count + 1);
/* 176 */       inBufferPos = 0;
/*     */     } 
/* 178 */     this.currentBuffer[inBufferPos] = (byte)b;
/* 179 */     this.count++;
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
/*     */   public synchronized int write(InputStream in) throws IOException {
/* 194 */     int readCount = 0;
/* 195 */     int inBufferPos = this.count - this.filledBufferSum;
/* 196 */     int n = in.read(this.currentBuffer, inBufferPos, this.currentBuffer.length - inBufferPos);
/* 197 */     while (n != -1) {
/* 198 */       readCount += n;
/* 199 */       inBufferPos += n;
/* 200 */       this.count += n;
/* 201 */       if (inBufferPos == this.currentBuffer.length) {
/* 202 */         needNewBuffer(this.currentBuffer.length);
/* 203 */         inBufferPos = 0;
/*     */       } 
/* 205 */       n = in.read(this.currentBuffer, inBufferPos, this.currentBuffer.length - inBufferPos);
/*     */     } 
/* 207 */     return readCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int size() {
/* 215 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() {
/* 235 */     this.count = 0;
/* 236 */     this.filledBufferSum = 0;
/* 237 */     this.currentBufferIndex = 0;
/* 238 */     if (this.reuseBuffers) {
/* 239 */       this.currentBuffer = this.buffers.get(this.currentBufferIndex);
/*     */     } else {
/*     */       
/* 242 */       this.currentBuffer = null;
/* 243 */       int size = ((byte[])this.buffers.get(0)).length;
/* 244 */       this.buffers.clear();
/* 245 */       needNewBuffer(size);
/* 246 */       this.reuseBuffers = true;
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
/*     */   public synchronized void writeTo(OutputStream out) throws IOException {
/* 259 */     int remaining = this.count;
/* 260 */     for (byte[] buf : this.buffers) {
/* 261 */       int c = Math.min(buf.length, remaining);
/* 262 */       out.write(buf, 0, c);
/* 263 */       remaining -= c;
/* 264 */       if (remaining == 0) {
/*     */         break;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputStream toBufferedInputStream(InputStream input) throws IOException {
/* 293 */     return toBufferedInputStream(input, 1024);
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
/*     */   public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
/* 322 */     ByteArrayOutputStream output = new ByteArrayOutputStream(size);
/* 323 */     output.write(input);
/* 324 */     return output.toInputStream();
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
/*     */   public synchronized InputStream toInputStream() {
/* 338 */     int remaining = this.count;
/* 339 */     if (remaining == 0) {
/* 340 */       return (InputStream)new ClosedInputStream();
/*     */     }
/* 342 */     List<ByteArrayInputStream> list = new ArrayList<>(this.buffers.size());
/* 343 */     for (byte[] buf : this.buffers) {
/* 344 */       int c = Math.min(buf.length, remaining);
/* 345 */       list.add(new ByteArrayInputStream(buf, 0, c));
/* 346 */       remaining -= c;
/* 347 */       if (remaining == 0) {
/*     */         break;
/*     */       }
/*     */     } 
/* 351 */     this.reuseBuffers = false;
/* 352 */     return new SequenceInputStream(Collections.enumeration((Collection)list));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized byte[] toByteArray() {
/* 363 */     int remaining = this.count;
/* 364 */     if (remaining == 0) {
/* 365 */       return EMPTY_BYTE_ARRAY;
/*     */     }
/* 367 */     byte[] newbuf = new byte[remaining];
/* 368 */     int pos = 0;
/* 369 */     for (byte[] buf : this.buffers) {
/* 370 */       int c = Math.min(buf.length, remaining);
/* 371 */       System.arraycopy(buf, 0, newbuf, pos, c);
/* 372 */       pos += c;
/* 373 */       remaining -= c;
/* 374 */       if (remaining == 0) {
/*     */         break;
/*     */       }
/*     */     } 
/* 378 */     return newbuf;
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
/*     */   @Deprecated
/*     */   public String toString() {
/* 392 */     return new String(toByteArray(), Charset.defaultCharset());
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
/*     */   public String toString(String enc) throws UnsupportedEncodingException {
/* 405 */     return new String(toByteArray(), enc);
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
/*     */   public String toString(Charset charset) {
/* 418 */     return new String(toByteArray(), charset);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/output/ByteArrayOutputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */