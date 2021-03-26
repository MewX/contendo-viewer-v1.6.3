/*     */ package org.apache.http.nio.entity;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.entity.HttpEntityWrapper;
/*     */ import org.apache.http.nio.ContentDecoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.nio.util.ByteBufferAllocator;
/*     */ import org.apache.http.nio.util.ContentInputBuffer;
/*     */ import org.apache.http.nio.util.SimpleInputBuffer;
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
/*     */ @Deprecated
/*     */ public class BufferingNHttpEntity
/*     */   extends HttpEntityWrapper
/*     */   implements ConsumingNHttpEntity
/*     */ {
/*     */   private static final int BUFFER_SIZE = 2048;
/*     */   private final SimpleInputBuffer buffer;
/*     */   private boolean finished;
/*     */   private boolean consumed;
/*     */   
/*     */   public BufferingNHttpEntity(HttpEntity httpEntity, ByteBufferAllocator allocator) {
/*  68 */     super(httpEntity);
/*  69 */     this.buffer = new SimpleInputBuffer(2048, allocator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeContent(ContentDecoder decoder, IOControl ioControl) throws IOException {
/*  76 */     this.buffer.consumeContent(decoder);
/*  77 */     if (decoder.isCompleted()) {
/*  78 */       this.finished = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void finish() {
/*  84 */     this.finished = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getContent() throws IOException {
/*  95 */     Asserts.check(this.finished, "Entity content has not been fully received");
/*  96 */     Asserts.check(!this.consumed, "Entity content has been consumed");
/*  97 */     this.consumed = true;
/*  98 */     return new ContentInputStream((ContentInputBuffer)this.buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream outStream) throws IOException {
/* 113 */     Args.notNull(outStream, "Output stream");
/* 114 */     InputStream inStream = getContent();
/* 115 */     byte[] buff = new byte[2048];
/*     */     
/*     */     int l;
/* 118 */     while ((l = inStream.read(buff)) != -1)
/* 119 */       outStream.write(buff, 0, l); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/BufferingNHttpEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */