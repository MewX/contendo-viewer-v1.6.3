/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
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
/*     */ public class IdentityDecoder
/*     */   extends AbstractContentDecoder
/*     */   implements FileContentDecoder
/*     */ {
/*     */   public IdentityDecoder(ReadableByteChannel channel, SessionInputBuffer buffer, HttpTransportMetricsImpl metrics) {
/*  59 */     super(channel, buffer, metrics);
/*     */   }
/*     */   
/*     */   public int read(ByteBuffer dst) throws IOException {
/*     */     int bytesRead;
/*  64 */     Args.notNull(dst, "Byte buffer");
/*  65 */     if (isCompleted()) {
/*  66 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*  70 */     if (this.buffer.hasData()) {
/*  71 */       bytesRead = this.buffer.read(dst);
/*     */     } else {
/*  73 */       bytesRead = readFromChannel(dst);
/*     */     } 
/*  75 */     if (bytesRead == -1) {
/*  76 */       setCompleted();
/*     */     }
/*  78 */     return bytesRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long transfer(FileChannel dst, long position, long count) throws IOException {
/*     */     long bytesRead;
/*  87 */     if (dst == null) {
/*  88 */       return 0L;
/*     */     }
/*  90 */     if (isCompleted()) {
/*  91 */       return 0L;
/*     */     }
/*     */ 
/*     */     
/*  95 */     if (this.buffer.hasData()) {
/*  96 */       dst.position(position);
/*  97 */       bytesRead = this.buffer.read(dst);
/*     */     } else {
/*  99 */       if (this.channel.isOpen()) {
/* 100 */         if (position > dst.size()) {
/* 101 */           throw new IOException(String.format("Position past end of file [%,d > %,d]", new Object[] { Long.valueOf(position), Long.valueOf(dst.size()) }));
/*     */         }
/*     */         
/* 104 */         bytesRead = dst.transferFrom(this.channel, position, count);
/* 105 */         if (count > 0L && bytesRead == 0L) {
/* 106 */           bytesRead = this.buffer.fill(this.channel);
/*     */         }
/*     */       } else {
/* 109 */         bytesRead = -1L;
/*     */       } 
/* 111 */       if (bytesRead > 0L) {
/* 112 */         this.metrics.incrementBytesTransferred(bytesRead);
/*     */       }
/*     */     } 
/* 115 */     if (bytesRead == -1L) {
/* 116 */       setCompleted();
/*     */     }
/* 118 */     return bytesRead;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 123 */     StringBuilder sb = new StringBuilder();
/* 124 */     sb.append("[identity; completed: ");
/* 125 */     sb.append(this.completed);
/* 126 */     sb.append("]");
/* 127 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/IdentityDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */