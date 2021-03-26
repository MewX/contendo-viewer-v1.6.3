/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractContentEncoder
/*     */   implements ContentEncoder
/*     */ {
/*     */   protected final WritableByteChannel channel;
/*     */   protected final SessionOutputBuffer buffer;
/*     */   protected final HttpTransportMetricsImpl metrics;
/*     */   protected boolean completed;
/*     */   
/*     */   public AbstractContentEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics) {
/*  70 */     Args.notNull(channel, "Channel");
/*  71 */     Args.notNull(buffer, "Session input buffer");
/*  72 */     Args.notNull(metrics, "Transport metrics");
/*  73 */     this.buffer = buffer;
/*  74 */     this.channel = channel;
/*  75 */     this.metrics = metrics;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCompleted() {
/*  80 */     return this.completed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void complete() throws IOException {
/*  85 */     this.completed = true;
/*     */   }
/*     */   
/*     */   protected void assertNotCompleted() {
/*  89 */     Asserts.check(!this.completed, "Encoding process already completed");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int flushToChannel() throws IOException {
/* 100 */     if (!this.buffer.hasData()) {
/* 101 */       return 0;
/*     */     }
/* 103 */     int bytesWritten = this.buffer.flush(this.channel);
/* 104 */     if (bytesWritten > 0) {
/* 105 */       this.metrics.incrementBytesTransferred(bytesWritten);
/*     */     }
/* 107 */     return bytesWritten;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int writeToChannel(ByteBuffer src) throws IOException {
/* 118 */     if (!src.hasRemaining()) {
/* 119 */       return 0;
/*     */     }
/* 121 */     int bytesWritten = this.channel.write(src);
/* 122 */     if (bytesWritten > 0) {
/* 123 */       this.metrics.incrementBytesTransferred(bytesWritten);
/*     */     }
/* 125 */     return bytesWritten;
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
/*     */   protected int writeToChannel(ByteBuffer src, int limit) throws IOException {
/* 138 */     return doWriteChunk(src, limit, true);
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
/*     */   protected int writeToBuffer(ByteBuffer src, int limit) throws IOException {
/* 151 */     return doWriteChunk(src, limit, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private int doWriteChunk(ByteBuffer src, int chunk, boolean direct) throws IOException {
/*     */     int bytesWritten;
/* 157 */     if (src.remaining() > chunk) {
/* 158 */       int oldLimit = src.limit();
/* 159 */       int newLimit = oldLimit - src.remaining() - chunk;
/* 160 */       src.limit(newLimit);
/* 161 */       bytesWritten = doWriteChunk(src, direct);
/* 162 */       src.limit(oldLimit);
/*     */     } else {
/* 164 */       bytesWritten = doWriteChunk(src, direct);
/*     */     } 
/* 166 */     return bytesWritten;
/*     */   }
/*     */   
/*     */   private int doWriteChunk(ByteBuffer src, boolean direct) throws IOException {
/* 170 */     if (direct) {
/* 171 */       int bytesWritten = this.channel.write(src);
/* 172 */       if (bytesWritten > 0) {
/* 173 */         this.metrics.incrementBytesTransferred(bytesWritten);
/*     */       }
/* 175 */       return bytesWritten;
/*     */     } 
/* 177 */     int chunk = src.remaining();
/* 178 */     this.buffer.write(src);
/* 179 */     return chunk;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/AbstractContentEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */