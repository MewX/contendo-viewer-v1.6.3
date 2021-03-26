/*     */ package org.apache.http.nio.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.Asserts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class SharedOutputBuffer
/*     */   extends ExpandableBuffer
/*     */   implements ContentOutputBuffer
/*     */ {
/*     */   private final ReentrantLock lock;
/*     */   private final Condition condition;
/*     */   private volatile IOControl ioControl;
/*     */   private volatile boolean shutdown = false;
/*     */   private volatile boolean endOfStream = false;
/*     */   
/*     */   @Deprecated
/*     */   public SharedOutputBuffer(int bufferSize, IOControl ioControl, ByteBufferAllocator allocator) {
/*  72 */     super(bufferSize, allocator);
/*  73 */     Args.notNull(ioControl, "I/O content control");
/*  74 */     this.ioControl = ioControl;
/*  75 */     this.lock = new ReentrantLock();
/*  76 */     this.condition = this.lock.newCondition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SharedOutputBuffer(int bufferSize, ByteBufferAllocator allocator) {
/*  83 */     super(bufferSize, allocator);
/*  84 */     this.lock = new ReentrantLock();
/*  85 */     this.condition = this.lock.newCondition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SharedOutputBuffer(int bufferSize) {
/*  92 */     this(bufferSize, HeapByteBufferAllocator.INSTANCE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  97 */     if (this.shutdown) {
/*     */       return;
/*     */     }
/* 100 */     this.lock.lock();
/*     */     try {
/* 102 */       clear();
/* 103 */       this.endOfStream = false;
/*     */     } finally {
/* 105 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 111 */     this.lock.lock();
/*     */     try {
/* 113 */       return super.hasData();
/*     */     } finally {
/* 115 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() {
/* 121 */     this.lock.lock();
/*     */     try {
/* 123 */       return super.available();
/*     */     } finally {
/* 125 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int capacity() {
/* 131 */     this.lock.lock();
/*     */     try {
/* 133 */       return super.capacity();
/*     */     } finally {
/* 135 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int length() {
/* 141 */     this.lock.lock();
/*     */     try {
/* 143 */       return super.length();
/*     */     } finally {
/* 145 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int produceContent(ContentEncoder encoder) throws IOException {
/* 155 */     return produceContent(encoder, (IOControl)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 162 */     if (this.shutdown) {
/* 163 */       return -1;
/*     */     }
/* 165 */     this.lock.lock();
/*     */     try {
/* 167 */       if (ioControl != null) {
/* 168 */         this.ioControl = ioControl;
/*     */       }
/* 170 */       setOutputMode();
/* 171 */       int bytesWritten = 0;
/* 172 */       if (super.hasData()) {
/* 173 */         bytesWritten = encoder.write(this.buffer);
/* 174 */         if (encoder.isCompleted()) {
/* 175 */           this.endOfStream = true;
/*     */         }
/*     */       } 
/* 178 */       if (!super.hasData()) {
/*     */ 
/*     */         
/* 181 */         if (this.endOfStream && !encoder.isCompleted()) {
/* 182 */           encoder.complete();
/*     */         }
/* 184 */         if (!this.endOfStream)
/*     */         {
/* 186 */           if (this.ioControl != null) {
/* 187 */             this.ioControl.suspendOutput();
/*     */           }
/*     */         }
/*     */       } 
/* 191 */       this.condition.signalAll();
/* 192 */       return bytesWritten;
/*     */     } finally {
/* 194 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 199 */     shutdown();
/*     */   }
/*     */   
/*     */   public void shutdown() {
/* 203 */     if (this.shutdown) {
/*     */       return;
/*     */     }
/* 206 */     this.shutdown = true;
/* 207 */     this.lock.lock();
/*     */     try {
/* 209 */       this.condition.signalAll();
/*     */     } finally {
/* 211 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 217 */     if (b == null) {
/*     */       return;
/*     */     }
/* 220 */     int pos = off;
/* 221 */     this.lock.lock();
/*     */     try {
/* 223 */       Asserts.check((!this.shutdown && !this.endOfStream), "Buffer already closed for writing");
/* 224 */       setInputMode();
/* 225 */       int remaining = len;
/* 226 */       while (remaining > 0) {
/* 227 */         if (!this.buffer.hasRemaining()) {
/* 228 */           flushContent();
/* 229 */           setInputMode();
/*     */         } 
/* 231 */         int chunk = Math.min(remaining, this.buffer.remaining());
/* 232 */         this.buffer.put(b, pos, chunk);
/* 233 */         remaining -= chunk;
/* 234 */         pos += chunk;
/*     */       } 
/*     */     } finally {
/* 237 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 242 */     if (b == null) {
/*     */       return;
/*     */     }
/* 245 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 250 */     this.lock.lock();
/*     */     try {
/* 252 */       Asserts.check((!this.shutdown && !this.endOfStream), "Buffer already closed for writing");
/* 253 */       setInputMode();
/* 254 */       if (!this.buffer.hasRemaining()) {
/* 255 */         flushContent();
/* 256 */         setInputMode();
/*     */       } 
/* 258 */       this.buffer.put((byte)b);
/*     */     } finally {
/* 260 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {}
/*     */ 
/*     */   
/*     */   private void flushContent() throws IOException {
/* 270 */     this.lock.lock(); 
/*     */     try { while (true) {
/*     */         try {
/* 273 */           if (super.hasData()) {
/* 274 */             if (this.shutdown) {
/* 275 */               throw new InterruptedIOException("Output operation aborted");
/*     */             }
/* 277 */             if (this.ioControl != null) {
/* 278 */               this.ioControl.requestOutput();
/*     */             }
/* 280 */             this.condition.await(); continue;
/*     */           } 
/* 282 */         } catch (InterruptedException ex) {
/* 283 */           throw new IOException("Interrupted while flushing the content buffer");
/*     */         }  break;
/*     */       }  }
/* 286 */     finally { this.lock.unlock(); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeCompleted() throws IOException {
/* 292 */     this.lock.lock();
/*     */     try {
/* 294 */       if (this.endOfStream) {
/*     */         return;
/*     */       }
/* 297 */       this.endOfStream = true;
/* 298 */       if (this.ioControl != null) {
/* 299 */         this.ioControl.requestOutput();
/*     */       }
/*     */     } finally {
/* 302 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/SharedOutputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */