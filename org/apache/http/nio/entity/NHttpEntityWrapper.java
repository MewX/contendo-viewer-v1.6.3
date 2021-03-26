/*     */ package org.apache.http.nio.entity;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.entity.HttpEntityWrapper;
/*     */ import org.apache.http.nio.ContentEncoder;
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
/*     */ @Deprecated
/*     */ public class NHttpEntityWrapper
/*     */   extends HttpEntityWrapper
/*     */   implements ProducingNHttpEntity
/*     */ {
/*     */   private final ReadableByteChannel channel;
/*     */   private final ByteBuffer buffer;
/*     */   
/*     */   public NHttpEntityWrapper(HttpEntity httpEntity) throws IOException {
/*  58 */     super(httpEntity);
/*  59 */     this.channel = Channels.newChannel(httpEntity.getContent());
/*  60 */     this.buffer = ByteBuffer.allocate(4096);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getContent() throws IOException, UnsupportedOperationException {
/*  68 */     throw new UnsupportedOperationException("Does not support blocking methods");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException, UnsupportedOperationException {
/*  81 */     throw new UnsupportedOperationException("Does not support blocking methods");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/*  88 */     int i = this.channel.read(this.buffer);
/*  89 */     this.buffer.flip();
/*  90 */     encoder.write(this.buffer);
/*  91 */     boolean buffering = this.buffer.hasRemaining();
/*  92 */     this.buffer.compact();
/*  93 */     if (i == -1 && !buffering) {
/*  94 */       encoder.complete();
/*  95 */       this.channel.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish() {
/*     */     try {
/* 102 */       this.channel.close();
/* 103 */     } catch (IOException iOException) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/NHttpEntityWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */