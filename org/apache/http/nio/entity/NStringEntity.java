/*     */ package org.apache.http.nio.entity;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.http.entity.AbstractHttpEntity;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.nio.ContentEncoder;
/*     */ import org.apache.http.nio.IOControl;
/*     */ import org.apache.http.protocol.HTTP;
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
/*     */ public class NStringEntity
/*     */   extends AbstractHttpEntity
/*     */   implements HttpAsyncContentProducer, ProducingNHttpEntity
/*     */ {
/*     */   private final byte[] b;
/*     */   private final ByteBuffer buf;
/*     */   @Deprecated
/*     */   protected final byte[] content;
/*     */   @Deprecated
/*     */   protected final ByteBuffer buffer;
/*     */   
/*     */   public NStringEntity(String s, ContentType contentType) {
/*  81 */     Args.notNull(s, "Source string");
/*  82 */     Charset charset = (contentType != null) ? contentType.getCharset() : null;
/*  83 */     if (charset == null) {
/*  84 */       charset = HTTP.DEF_CONTENT_CHARSET;
/*     */     }
/*  86 */     this.b = s.getBytes(charset);
/*  87 */     this.buf = ByteBuffer.wrap(this.b);
/*  88 */     this.content = this.b;
/*  89 */     this.buffer = this.buf;
/*  90 */     if (contentType != null) {
/*  91 */       setContentType(contentType.toString());
/*     */     }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public NStringEntity(String s, String charset) throws UnsupportedEncodingException {
/* 109 */     this(s, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), charset));
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
/*     */ 
/*     */   
/*     */   public NStringEntity(String s, Charset charset) {
/* 125 */     this(s, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), charset));
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
/*     */   public NStringEntity(String s) throws UnsupportedEncodingException {
/* 138 */     this(s, ContentType.DEFAULT_TEXT);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 148 */     return this.b.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 158 */     this.buf.rewind();
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
/* 169 */     close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void produceContent(ContentEncoder encoder, IOControl ioControl) throws IOException {
/* 175 */     encoder.write(this.buf);
/* 176 */     if (!this.buf.hasRemaining()) {
/* 177 */       encoder.complete();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getContent() {
/* 188 */     return new ByteArrayInputStream(this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream outStream) throws IOException {
/* 193 */     Args.notNull(outStream, "Output stream");
/* 194 */     outStream.write(this.b);
/* 195 */     outStream.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/entity/NStringEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */