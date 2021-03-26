/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.reactor.SessionInputBuffer;
/*     */ import org.apache.http.util.Args;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractContentDecoder
/*     */   implements ContentDecoder
/*     */ {
/*     */   protected final ReadableByteChannel channel;
/*     */   protected final SessionInputBuffer buffer;
/*     */   protected final HttpTransportMetricsImpl metrics;
/*     */   protected boolean completed;
/*     */   
/*     */   public AbstractContentDecoder(ReadableByteChannel channel, SessionInputBuffer buffer, HttpTransportMetricsImpl metrics) {
/*  66 */     Args.notNull(channel, "Channel");
/*  67 */     Args.notNull(buffer, "Session input buffer");
/*  68 */     Args.notNull(metrics, "Transport metrics");
/*  69 */     this.buffer = buffer;
/*  70 */     this.channel = channel;
/*  71 */     this.metrics = metrics;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCompleted() {
/*  76 */     return this.completed;
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
/*     */   public void setCompleted(boolean completed) {
/*  90 */     this.completed = completed;
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
/*     */   protected void setCompleted() {
/* 103 */     this.completed = true;
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
/*     */   protected int readFromChannel(ByteBuffer dst) throws IOException {
/* 115 */     int bytesRead = this.channel.read(dst);
/* 116 */     if (bytesRead > 0) {
/* 117 */       this.metrics.incrementBytesTransferred(bytesRead);
/*     */     }
/* 119 */     return bytesRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int fillBufferFromChannel() throws IOException {
/* 129 */     int bytesRead = this.buffer.fill(this.channel);
/* 130 */     if (bytesRead > 0) {
/* 131 */       this.metrics.incrementBytesTransferred(bytesRead);
/*     */     }
/* 133 */     return bytesRead;
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
/*     */   protected int readFromChannel(ByteBuffer dst, int limit) throws IOException {
/*     */     int bytesRead;
/* 147 */     if (dst.remaining() > limit) {
/* 148 */       int oldLimit = dst.limit();
/* 149 */       int newLimit = oldLimit - dst.remaining() - limit;
/* 150 */       dst.limit(newLimit);
/* 151 */       bytesRead = this.channel.read(dst);
/* 152 */       dst.limit(oldLimit);
/*     */     } else {
/* 154 */       bytesRead = this.channel.read(dst);
/*     */     } 
/* 156 */     if (bytesRead > 0) {
/* 157 */       this.metrics.incrementBytesTransferred(bytesRead);
/*     */     }
/* 159 */     return bytesRead;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/AbstractContentDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */