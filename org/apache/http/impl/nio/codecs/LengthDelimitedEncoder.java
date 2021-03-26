/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.nio.FileContentEncoder;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LengthDelimitedEncoder
/*     */   extends AbstractContentEncoder
/*     */   implements FileContentEncoder
/*     */ {
/*     */   private final long contentLength;
/*     */   private final int fragHint;
/*     */   private long remaining;
/*     */   
/*     */   public LengthDelimitedEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics, long contentLength, int fragementSizeHint) {
/*  78 */     super(channel, buffer, metrics);
/*  79 */     Args.notNegative(contentLength, "Content length");
/*  80 */     this.contentLength = contentLength;
/*  81 */     this.fragHint = (fragementSizeHint > 0) ? fragementSizeHint : 0;
/*  82 */     this.remaining = contentLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LengthDelimitedEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics, long contentLength) {
/*  90 */     this(channel, buffer, metrics, contentLength, 0);
/*     */   }
/*     */   
/*     */   private int nextChunk(ByteBuffer src) {
/*  94 */     return (int)Math.min(Math.min(this.remaining, 2147483647L), src.remaining());
/*     */   }
/*     */ 
/*     */   
/*     */   public int write(ByteBuffer src) throws IOException {
/*  99 */     if (src == null) {
/* 100 */       return 0;
/*     */     }
/* 102 */     assertNotCompleted();
/*     */     
/* 104 */     int total = 0;
/* 105 */     while (src.hasRemaining() && this.remaining > 0L) {
/* 106 */       if (this.buffer.hasData() || this.fragHint > 0) {
/* 107 */         int chunk = nextChunk(src);
/* 108 */         if (chunk <= this.fragHint) {
/* 109 */           int capacity = this.fragHint - this.buffer.length();
/* 110 */           if (capacity > 0) {
/* 111 */             int limit = Math.min(capacity, chunk);
/* 112 */             int bytesWritten = writeToBuffer(src, limit);
/* 113 */             this.remaining -= bytesWritten;
/* 114 */             total += bytesWritten;
/*     */           } 
/*     */         } 
/*     */       } 
/* 118 */       if (this.buffer.hasData()) {
/* 119 */         int chunk = nextChunk(src);
/* 120 */         if (this.buffer.length() >= this.fragHint || chunk > 0) {
/* 121 */           int bytesWritten = flushToChannel();
/* 122 */           if (bytesWritten == 0) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/* 127 */       if (!this.buffer.hasData()) {
/* 128 */         int chunk = nextChunk(src);
/* 129 */         if (chunk > this.fragHint) {
/* 130 */           int bytesWritten = writeToChannel(src, chunk);
/* 131 */           this.remaining -= bytesWritten;
/* 132 */           total += bytesWritten;
/* 133 */           if (bytesWritten == 0) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 139 */     if (this.remaining <= 0L) {
/* 140 */       complete();
/*     */     }
/* 142 */     return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long transfer(FileChannel src, long position, long count) throws IOException {
/* 151 */     if (src == null) {
/* 152 */       return 0L;
/*     */     }
/* 154 */     assertNotCompleted();
/*     */     
/* 156 */     flushToChannel();
/* 157 */     if (this.buffer.hasData()) {
/* 158 */       return 0L;
/*     */     }
/*     */     
/* 161 */     long chunk = Math.min(this.remaining, count);
/* 162 */     long bytesWritten = src.transferTo(position, chunk, this.channel);
/* 163 */     if (bytesWritten > 0L) {
/* 164 */       this.metrics.incrementBytesTransferred(bytesWritten);
/*     */     }
/* 166 */     this.remaining -= bytesWritten;
/* 167 */     if (this.remaining <= 0L) {
/* 168 */       complete();
/*     */     }
/* 170 */     return bytesWritten;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 175 */     StringBuilder sb = new StringBuilder();
/* 176 */     sb.append("[content length: ");
/* 177 */     sb.append(this.contentLength);
/* 178 */     sb.append("; pos: ");
/* 179 */     sb.append(this.contentLength - this.remaining);
/* 180 */     sb.append("; completed: ");
/* 181 */     sb.append(isCompleted());
/* 182 */     sb.append("]");
/* 183 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/LengthDelimitedEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */