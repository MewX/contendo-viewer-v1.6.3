/*     */ package org.apache.http.nio.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleInputBuffer
/*     */   extends ExpandableBuffer
/*     */   implements ContentInputBuffer
/*     */ {
/*     */   private boolean endOfStream = false;
/*     */   
/*     */   public SimpleInputBuffer(int bufferSize, ByteBufferAllocator allocator) {
/*  45 */     super(bufferSize, allocator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleInputBuffer(int bufferSize) {
/*  52 */     this(bufferSize, HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  57 */     this.endOfStream = false;
/*  58 */     clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int consumeContent(ContentDecoder decoder) throws IOException {
/*  63 */     setInputMode();
/*  64 */     int totalRead = 0;
/*     */     int bytesRead;
/*  66 */     while ((bytesRead = decoder.read(this.buffer)) != -1) {
/*  67 */       if (bytesRead == 0) {
/*  68 */         if (!this.buffer.hasRemaining()) {
/*  69 */           expand();
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/*  74 */       totalRead += bytesRead;
/*     */     } 
/*     */     
/*  77 */     if (bytesRead == -1 || decoder.isCompleted()) {
/*  78 */       this.endOfStream = true;
/*     */     }
/*  80 */     return totalRead;
/*     */   }
/*     */   
/*     */   public boolean isEndOfStream() {
/*  84 */     return (!hasData() && this.endOfStream);
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  89 */     if (isEndOfStream()) {
/*  90 */       return -1;
/*     */     }
/*  92 */     setOutputMode();
/*  93 */     return this.buffer.get() & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  98 */     if (isEndOfStream()) {
/*  99 */       return -1;
/*     */     }
/* 101 */     if (b == null) {
/* 102 */       return 0;
/*     */     }
/* 104 */     setOutputMode();
/* 105 */     int chunk = len;
/* 106 */     if (chunk > this.buffer.remaining()) {
/* 107 */       chunk = this.buffer.remaining();
/*     */     }
/* 109 */     this.buffer.get(b, off, chunk);
/* 110 */     return chunk;
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 114 */     if (isEndOfStream()) {
/* 115 */       return -1;
/*     */     }
/* 117 */     if (b == null) {
/* 118 */       return 0;
/*     */     }
/* 120 */     return read(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public void shutdown() {
/* 124 */     this.endOfStream = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/SimpleInputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */