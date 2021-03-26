/*     */ package org.apache.http.nio.entity;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.apache.http.entity.AbstractHttpEntity;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
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
/*     */ public class NByteArrayEntity
/*     */   extends AbstractHttpEntity
/*     */   implements HttpAsyncContentProducer, ProducingNHttpEntity
/*     */ {
/*     */   private final byte[] b;
/*     */   private final int off;
/*     */   private final int len;
/*     */   private final ByteBuffer buf;
/*     */   @Deprecated
/*     */   protected final byte[] content;
/*     */   @Deprecated
/*     */   protected final ByteBuffer buffer;
/*     */   
/*     */   public NByteArrayEntity(byte[] b, ContentType contentType) {
/*  71 */     Args.notNull(b, "Source byte array");
/*  72 */     this.b = b;
/*  73 */     this.off = 0;
/*  74 */     this.len = b.length;
/*  75 */     this.buf = ByteBuffer.wrap(b);
/*  76 */     this.content = b;
/*  77 */     this.buffer = this.buf;
/*  78 */     if (contentType != null) {
/*  79 */       setContentType(contentType.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NByteArrayEntity(byte[] b, int off, int len, ContentType contentType) {
/*  88 */     Args.notNull(b, "Source byte array");
/*  89 */     if (off < 0 || off > b.length || len < 0 || off + len < 0 || off + len > b.length)
/*     */     {
/*  91 */       throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
/*     */     }
/*  93 */     this.b = b;
/*  94 */     this.off = off;
/*  95 */     this.len = len;
/*  96 */     this.buf = ByteBuffer.wrap(b, off, len);
/*  97 */     this.content = b;
/*  98 */     this.buffer = this.buf;
/*  99 */     if (contentType != null) {
/* 100 */       setContentType(contentType.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public NByteArrayEntity(byte[] b) {
/* 105 */     this(b, (ContentType)null);
/*     */   }
/*     */   
/*     */   public NByteArrayEntity(byte[] b, int off, int len) {
/* 109 */     this(b, off, len, (ContentType)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 119 */     this.buf.rewind();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void finish() {
/* 130 */     close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 136 */     encoder.write(this.buf);
/* 137 */     if (!this.buf.hasRemaining()) {
/* 138 */       encoder.complete();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 144 */     return this.len;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getContent() {
/* 159 */     return new ByteArrayInputStream(this.b, this.off, this.len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream outStream) throws IOException {
/* 164 */     Args.notNull(outStream, "Output stream");
/* 165 */     outStream.write(this.b, this.off, this.len);
/* 166 */     outStream.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/NByteArrayEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */