/*     */ package org.apache.http.nio.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
/*     */ public class SharedInputBuffer
/*     */   extends ExpandableBuffer
/*     */   implements ContentInputBuffer
/*     */ {
/*     */   private final ReentrantLock lock;
/*     */   private final Condition condition;
/*     */   private volatile IOControl ioControl;
/*     */   private volatile boolean shutdown = false;
/*     */   private volatile boolean endOfStream = false;
/*     */   
/*     */   @Deprecated
/*     */   public SharedInputBuffer(int bufferSize, IOControl ioControl, ByteBufferAllocator allocator) {
/*  70 */     super(bufferSize, allocator);
/*  71 */     this.ioControl = ioControl;
/*  72 */     this.lock = new ReentrantLock();
/*  73 */     this.condition = this.lock.newCondition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SharedInputBuffer(int bufferSize, ByteBufferAllocator allocator) {
/*  80 */     super(bufferSize, allocator);
/*  81 */     this.lock = new ReentrantLock();
/*  82 */     this.condition = this.lock.newCondition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SharedInputBuffer(int bufferSize) {
/*  89 */     this(bufferSize, HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  94 */     if (this.shutdown) {
/*     */       return;
/*     */     }
/*  97 */     this.lock.lock();
/*     */     try {
/*  99 */       clear();
/* 100 */       this.endOfStream = false;
/*     */     } finally {
/* 102 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int consumeContent(ContentDecoder decoder) throws IOException {
/* 112 */     return consumeContent(decoder, (IOControl)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/* 119 */     if (this.shutdown) {
/* 120 */       return -1;
/*     */     }
/* 122 */     this.lock.lock();
/*     */     try {
/* 124 */       if (ioControl != null) {
/* 125 */         this.ioControl = ioControl;
/*     */       }
/* 127 */       setInputMode();
/* 128 */       int totalRead = 0;
/*     */       int bytesRead;
/* 130 */       while ((bytesRead = decoder.read(this.buffer)) > 0) {
/* 131 */         totalRead += bytesRead;
/*     */       }
/* 133 */       if (bytesRead == -1 || decoder.isCompleted()) {
/* 134 */         this.endOfStream = true;
/*     */       }
/* 136 */       if (!this.buffer.hasRemaining() && 
/* 137 */         this.ioControl != null) {
/* 138 */         this.ioControl.suspendInput();
/*     */       }
/*     */       
/* 141 */       this.condition.signalAll();
/*     */       
/* 143 */       if (totalRead > 0) {
/* 144 */         return totalRead;
/*     */       }
/* 146 */       return this.endOfStream ? -1 : 0;
/*     */     } finally {
/* 148 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 154 */     this.lock.lock();
/*     */     try {
/* 156 */       return super.hasData();
/*     */     } finally {
/* 158 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() {
/* 164 */     this.lock.lock();
/*     */     try {
/* 166 */       return super.available();
/*     */     } finally {
/* 168 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int capacity() {
/* 174 */     this.lock.lock();
/*     */     try {
/* 176 */       return super.capacity();
/*     */     } finally {
/* 178 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int length() {
/* 184 */     this.lock.lock();
/*     */     try {
/* 186 */       return super.length();
/*     */     } finally {
/* 188 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void waitForData() throws IOException {
/* 193 */     this.lock.lock(); 
/*     */     try { while (true) {
/*     */         try {
/* 196 */           if (!super.hasData() && !this.endOfStream) {
/* 197 */             if (this.shutdown) {
/* 198 */               throw new InterruptedIOException("Input operation aborted");
/*     */             }
/* 200 */             if (this.ioControl != null) {
/* 201 */               this.ioControl.requestInput();
/*     */             }
/* 203 */             this.condition.await(); continue;
/*     */           } 
/* 205 */         } catch (InterruptedException ex) {
/* 206 */           throw new IOException("Interrupted while waiting for more data");
/*     */         }  break;
/*     */       }  }
/* 209 */     finally { this.lock.unlock(); }
/*     */   
/*     */   }
/*     */   
/*     */   public void close() {
/* 214 */     if (this.shutdown) {
/*     */       return;
/*     */     }
/* 217 */     this.endOfStream = true;
/* 218 */     this.lock.lock();
/*     */     try {
/* 220 */       this.condition.signalAll();
/*     */     } finally {
/* 222 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void shutdown() {
/* 227 */     if (this.shutdown) {
/*     */       return;
/*     */     }
/* 230 */     this.shutdown = true;
/* 231 */     this.lock.lock();
/*     */     try {
/* 233 */       this.condition.signalAll();
/*     */     } finally {
/* 235 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isShutdown() {
/* 240 */     return this.shutdown;
/*     */   }
/*     */   
/*     */   protected boolean isEndOfStream() {
/* 244 */     return (this.shutdown || (!hasData() && this.endOfStream));
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 249 */     if (this.shutdown) {
/* 250 */       return -1;
/*     */     }
/* 252 */     this.lock.lock();
/*     */     try {
/* 254 */       if (!hasData()) {
/* 255 */         waitForData();
/*     */       }
/* 257 */       if (isEndOfStream()) {
/* 258 */         return -1;
/*     */       }
/* 260 */       return this.buffer.get() & 0xFF;
/*     */     } finally {
/* 262 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 268 */     if (this.shutdown) {
/* 269 */       return -1;
/*     */     }
/* 271 */     if (b == null) {
/* 272 */       return 0;
/*     */     }
/* 274 */     this.lock.lock();
/*     */     try {
/* 276 */       if (!hasData()) {
/* 277 */         waitForData();
/*     */       }
/* 279 */       if (isEndOfStream()) {
/* 280 */         return -1;
/*     */       }
/* 282 */       setOutputMode();
/* 283 */       int chunk = len;
/* 284 */       if (chunk > this.buffer.remaining()) {
/* 285 */         chunk = this.buffer.remaining();
/*     */       }
/* 287 */       this.buffer.get(b, off, chunk);
/* 288 */       return chunk;
/*     */     } finally {
/* 290 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 295 */     if (this.shutdown) {
/* 296 */       return -1;
/*     */     }
/* 298 */     if (b == null) {
/* 299 */       return 0;
/*     */     }
/* 301 */     return read(b, 0, b.length);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/SharedInputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */