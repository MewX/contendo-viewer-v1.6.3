/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import org.apache.http.ConnectionClosedException;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.nio.FileContentDecoder;
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
/*     */ public class LengthDelimitedDecoder
/*     */   extends AbstractContentDecoder
/*     */   implements FileContentDecoder
/*     */ {
/*     */   private final long contentLength;
/*     */   private long len;
/*     */   
/*     */   public LengthDelimitedDecoder(ReadableByteChannel channel, SessionInputBuffer buffer, HttpTransportMetricsImpl metrics, long contentLength) {
/*  66 */     super(channel, buffer, metrics);
/*  67 */     Args.notNegative(contentLength, "Content length");
/*  68 */     this.contentLength = contentLength;
/*     */   }
/*     */   
/*     */   public int read(ByteBuffer dst) throws IOException {
/*     */     int bytesRead;
/*  73 */     Args.notNull(dst, "Byte buffer");
/*  74 */     if (isCompleted()) {
/*  75 */       return -1;
/*     */     }
/*  77 */     int chunk = (int)Math.min(this.contentLength - this.len, 2147483647L);
/*     */ 
/*     */     
/*  80 */     if (this.buffer.hasData()) {
/*  81 */       int maxLen = Math.min(chunk, this.buffer.length());
/*  82 */       bytesRead = this.buffer.read(dst, maxLen);
/*     */     } else {
/*  84 */       bytesRead = readFromChannel(dst, chunk);
/*     */     } 
/*  86 */     if (bytesRead == -1) {
/*  87 */       setCompleted();
/*  88 */       if (this.len < this.contentLength) {
/*  89 */         throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: %,d; received: %,d)", new Object[] { Long.valueOf(this.contentLength), Long.valueOf(this.len) });
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  94 */     this.len += bytesRead;
/*  95 */     if (this.len >= this.contentLength) {
/*  96 */       setCompleted();
/*     */     }
/*  98 */     return (isCompleted() && bytesRead == 0) ? -1 : bytesRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long transfer(FileChannel dst, long position, long count) throws IOException {
/*     */     long bytesRead;
/* 107 */     if (dst == null) {
/* 108 */       return 0L;
/*     */     }
/* 110 */     if (isCompleted()) {
/* 111 */       return -1L;
/*     */     }
/*     */     
/* 114 */     int chunk = (int)Math.min(this.contentLength - this.len, 2147483647L);
/*     */ 
/*     */     
/* 117 */     if (this.buffer.hasData()) {
/* 118 */       int maxLen = Math.min(chunk, this.buffer.length());
/* 119 */       dst.position(position);
/* 120 */       bytesRead = this.buffer.read(dst, maxLen);
/*     */     } else {
/* 122 */       if (this.channel.isOpen()) {
/* 123 */         if (position > dst.size()) {
/* 124 */           throw new IOException(String.format("Position past end of file [%,d > %,d]", new Object[] { Long.valueOf(position), Long.valueOf(dst.size()) }));
/*     */         }
/*     */         
/* 127 */         bytesRead = dst.transferFrom(this.channel, position, (count < chunk) ? count : chunk);
/*     */       } else {
/* 129 */         bytesRead = -1L;
/*     */       } 
/* 131 */       if (bytesRead > 0L) {
/* 132 */         this.metrics.incrementBytesTransferred(bytesRead);
/*     */       }
/*     */     } 
/* 135 */     if (bytesRead == -1L) {
/* 136 */       setCompleted();
/* 137 */       if (this.len < this.contentLength) {
/* 138 */         throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: %,d; received: %,d)", new Object[] { Long.valueOf(this.contentLength), Long.valueOf(this.len) });
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 143 */     this.len += bytesRead;
/* 144 */     if (this.len >= this.contentLength) {
/* 145 */       setCompleted();
/*     */     }
/* 147 */     return bytesRead;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 152 */     StringBuilder sb = new StringBuilder();
/* 153 */     sb.append("[content length: ");
/* 154 */     sb.append(this.contentLength);
/* 155 */     sb.append("; pos: ");
/* 156 */     sb.append(this.len);
/* 157 */     sb.append("; completed: ");
/* 158 */     sb.append(this.completed);
/* 159 */     sb.append("]");
/* 160 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/LengthDelimitedDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */