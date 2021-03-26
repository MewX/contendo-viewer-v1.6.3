/*     */ package org.apache.http.nio.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleOutputBuffer
/*     */   extends ExpandableBuffer
/*     */   implements ContentOutputBuffer
/*     */ {
/*     */   private boolean endOfStream;
/*     */   
/*     */   public SimpleOutputBuffer(int bufferSize, ByteBufferAllocator allocator) {
/*  45 */     super(bufferSize, allocator);
/*  46 */     this.endOfStream = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleOutputBuffer(int bufferSize) {
/*  53 */     this(bufferSize, HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int produceContent(ContentEncoder encoder) throws IOException {
/*  58 */     setOutputMode();
/*  59 */     int bytesWritten = encoder.write(this.buffer);
/*  60 */     if (!hasData() && this.endOfStream) {
/*  61 */       encoder.complete();
/*     */     }
/*  63 */     return bytesWritten;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  68 */     if (b == null) {
/*     */       return;
/*     */     }
/*  71 */     if (this.endOfStream) {
/*     */       return;
/*     */     }
/*  74 */     setInputMode();
/*  75 */     ensureCapacity(this.buffer.position() + len);
/*  76 */     this.buffer.put(b, off, len);
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  80 */     if (b == null) {
/*     */       return;
/*     */     }
/*  83 */     if (this.endOfStream) {
/*     */       return;
/*     */     }
/*  86 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/*  91 */     if (this.endOfStream) {
/*     */       return;
/*     */     }
/*  94 */     setInputMode();
/*  95 */     ensureCapacity(capacity() + 1);
/*  96 */     this.buffer.put((byte)b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 101 */     clear();
/* 102 */     this.endOfStream = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCompleted() {
/* 112 */     this.endOfStream = true;
/*     */   }
/*     */   
/*     */   public void shutdown() {
/* 116 */     this.endOfStream = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/SimpleOutputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */