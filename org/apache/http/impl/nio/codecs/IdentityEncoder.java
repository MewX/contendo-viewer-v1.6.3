/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.nio.FileContentEncoder;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IdentityEncoder
/*     */   extends AbstractContentEncoder
/*     */   implements FileContentEncoder
/*     */ {
/*     */   private final int fragHint;
/*     */   
/*     */   public IdentityEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics, int fragementSizeHint) {
/*  71 */     super(channel, buffer, metrics);
/*  72 */     this.fragHint = (fragementSizeHint > 0) ? fragementSizeHint : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IdentityEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics) {
/*  79 */     this(channel, buffer, metrics, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int write(ByteBuffer src) throws IOException {
/*  84 */     if (src == null) {
/*  85 */       return 0;
/*     */     }
/*  87 */     assertNotCompleted();
/*     */     
/*  89 */     int total = 0;
/*  90 */     while (src.hasRemaining()) {
/*  91 */       if ((this.buffer.hasData() || this.fragHint > 0) && 
/*  92 */         src.remaining() <= this.fragHint) {
/*  93 */         int capacity = this.fragHint - this.buffer.length();
/*  94 */         if (capacity > 0) {
/*  95 */           int limit = Math.min(capacity, src.remaining());
/*  96 */           int bytesWritten = writeToBuffer(src, limit);
/*  97 */           total += bytesWritten;
/*     */         } 
/*     */       } 
/*     */       
/* 101 */       if (this.buffer.hasData() && (
/* 102 */         this.buffer.length() >= this.fragHint || src.hasRemaining())) {
/* 103 */         int bytesWritten = flushToChannel();
/* 104 */         if (bytesWritten == 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 109 */       if (!this.buffer.hasData() && src.remaining() > this.fragHint) {
/* 110 */         int bytesWritten = writeToChannel(src);
/* 111 */         total += bytesWritten;
/* 112 */         if (bytesWritten == 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/* 117 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long transfer(FileChannel src, long position, long count) throws IOException {
/* 126 */     if (src == null) {
/* 127 */       return 0L;
/*     */     }
/* 129 */     assertNotCompleted();
/*     */     
/* 131 */     flushToChannel();
/* 132 */     if (this.buffer.hasData()) {
/* 133 */       return 0L;
/*     */     }
/*     */     
/* 136 */     long bytesWritten = src.transferTo(position, count, this.channel);
/* 137 */     if (bytesWritten > 0L) {
/* 138 */       this.metrics.incrementBytesTransferred(bytesWritten);
/*     */     }
/* 140 */     return bytesWritten;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 145 */     StringBuilder sb = new StringBuilder();
/* 146 */     sb.append("[identity; completed: ");
/* 147 */     sb.append(isCompleted());
/* 148 */     sb.append("]");
/* 149 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/IdentityEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */