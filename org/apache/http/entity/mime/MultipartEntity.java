/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Random;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.entity.mime.content.ContentBody;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class MultipartEntity
/*     */   implements HttpEntity
/*     */ {
/*  53 */   private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final MultipartEntityBuilder builder;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile MultipartFormEntity entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntity(HttpMultipartMode mode, String boundary, Charset charset) {
/*  71 */     this.builder = (new MultipartEntityBuilder()).setMode(mode).setCharset((charset != null) ? charset : MIME.DEFAULT_CHARSET).setBoundary(boundary);
/*     */ 
/*     */ 
/*     */     
/*  75 */     this.entity = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntity(HttpMultipartMode mode) {
/*  84 */     this(mode, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntity() {
/*  91 */     this(HttpMultipartMode.STRICT, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String generateContentType(String boundary, Charset charset) {
/*  97 */     StringBuilder buffer = new StringBuilder();
/*  98 */     buffer.append("multipart/form-data; boundary=");
/*  99 */     buffer.append(boundary);
/* 100 */     if (charset != null) {
/* 101 */       buffer.append("; charset=");
/* 102 */       buffer.append(charset.name());
/*     */     } 
/* 104 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   protected String generateBoundary() {
/* 108 */     StringBuilder buffer = new StringBuilder();
/* 109 */     Random rand = new Random();
/* 110 */     int count = rand.nextInt(11) + 30;
/* 111 */     for (int i = 0; i < count; i++) {
/* 112 */       buffer.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
/*     */     }
/* 114 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   private MultipartFormEntity getEntity() {
/* 118 */     if (this.entity == null) {
/* 119 */       this.entity = this.builder.buildEntity();
/*     */     }
/* 121 */     return this.entity;
/*     */   }
/*     */   
/*     */   public void addPart(FormBodyPart bodyPart) {
/* 125 */     this.builder.addPart(bodyPart);
/* 126 */     this.entity = null;
/*     */   }
/*     */   
/*     */   public void addPart(String name, ContentBody contentBody) {
/* 130 */     addPart(new FormBodyPart(name, contentBody));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/* 135 */     return getEntity().isRepeatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChunked() {
/* 140 */     return getEntity().isChunked();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/* 145 */     return getEntity().isStreaming();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 150 */     return getEntity().getContentLength();
/*     */   }
/*     */ 
/*     */   
/*     */   public Header getContentType() {
/* 155 */     return getEntity().getContentType();
/*     */   }
/*     */ 
/*     */   
/*     */   public Header getContentEncoding() {
/* 160 */     return getEntity().getContentEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeContent() throws IOException, UnsupportedOperationException {
/* 166 */     if (isStreaming()) {
/* 167 */       throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getContent() throws IOException, UnsupportedOperationException {
/* 174 */     throw new UnsupportedOperationException("Multipart form entity does not implement #getContent()");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream outStream) throws IOException {
/* 180 */     getEntity().writeTo(outStream);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/MultipartEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */