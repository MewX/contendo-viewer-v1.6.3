/*     */ package org.apache.http.impl.nio.codecs;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ import org.apache.http.impl.io.HttpTransportMetricsImpl;
/*     */ import org.apache.http.io.BufferInfo;
/*     */ import org.apache.http.nio.reactor.SessionOutputBuffer;
/*     */ import org.apache.http.util.CharArrayBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkEncoder
/*     */   extends AbstractContentEncoder
/*     */ {
/*     */   private final int fragHint;
/*     */   private final CharArrayBuffer lineBuffer;
/*     */   private final BufferInfo bufferinfo;
/*     */   
/*     */   public ChunkEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics, int fragementSizeHint) {
/*  67 */     super(channel, buffer, metrics);
/*  68 */     this.fragHint = (fragementSizeHint > 0) ? fragementSizeHint : 0;
/*  69 */     this.lineBuffer = new CharArrayBuffer(16);
/*  70 */     if (buffer instanceof BufferInfo) {
/*  71 */       this.bufferinfo = (BufferInfo)buffer;
/*     */     } else {
/*  73 */       this.bufferinfo = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkEncoder(WritableByteChannel channel, SessionOutputBuffer buffer, HttpTransportMetricsImpl metrics) {
/*  81 */     this(channel, buffer, metrics, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int write(ByteBuffer src) throws IOException {
/*  86 */     if (src == null) {
/*  87 */       return 0;
/*     */     }
/*  89 */     assertNotCompleted();
/*     */     
/*  91 */     int total = 0;
/*  92 */     while (src.hasRemaining()) {
/*  93 */       int avail, chunk = src.remaining();
/*     */       
/*  95 */       if (this.bufferinfo != null) {
/*  96 */         avail = this.bufferinfo.available();
/*     */       } else {
/*  98 */         avail = 4096;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 104 */       avail -= 12;
/* 105 */       if (avail > 0) {
/* 106 */         if (avail < chunk) {
/*     */           
/* 108 */           chunk = avail;
/* 109 */           this.lineBuffer.clear();
/* 110 */           this.lineBuffer.append(Integer.toHexString(chunk));
/* 111 */           this.buffer.writeLine(this.lineBuffer);
/* 112 */           int oldlimit = src.limit();
/* 113 */           src.limit(src.position() + chunk);
/* 114 */           this.buffer.write(src);
/* 115 */           src.limit(oldlimit);
/*     */         } else {
/*     */           
/* 118 */           this.lineBuffer.clear();
/* 119 */           this.lineBuffer.append(Integer.toHexString(chunk));
/* 120 */           this.buffer.writeLine(this.lineBuffer);
/* 121 */           this.buffer.write(src);
/*     */         } 
/* 123 */         this.lineBuffer.clear();
/* 124 */         this.buffer.writeLine(this.lineBuffer);
/* 125 */         total += chunk;
/*     */       } 
/* 127 */       if (this.buffer.length() >= this.fragHint || src.hasRemaining()) {
/* 128 */         int bytesWritten = flushToChannel();
/* 129 */         if (bytesWritten == 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/* 134 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   public void complete() throws IOException {
/* 139 */     assertNotCompleted();
/* 140 */     this.lineBuffer.clear();
/* 141 */     this.lineBuffer.append("0");
/* 142 */     this.buffer.writeLine(this.lineBuffer);
/* 143 */     this.lineBuffer.clear();
/* 144 */     this.buffer.writeLine(this.lineBuffer);
/* 145 */     super.complete();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     StringBuilder sb = new StringBuilder();
/* 151 */     sb.append("[chunk-coded; completed: ");
/* 152 */     sb.append(isCompleted());
/* 153 */     sb.append("]");
/* 154 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/codecs/ChunkEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */