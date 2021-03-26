/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.http.ContentTooLongException;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.message.BasicHeader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MultipartFormEntity
/*     */   implements HttpEntity
/*     */ {
/*     */   private final AbstractMultipartForm multipart;
/*     */   private final Header contentType;
/*     */   private final long contentLength;
/*     */   
/*     */   MultipartFormEntity(AbstractMultipartForm multipart, ContentType contentType, long contentLength) {
/*  55 */     this.multipart = multipart;
/*  56 */     this.contentType = (Header)new BasicHeader("Content-Type", contentType.toString());
/*  57 */     this.contentLength = contentLength;
/*     */   }
/*     */   
/*     */   AbstractMultipartForm getMultipart() {
/*  61 */     return this.multipart;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepeatable() {
/*  66 */     return (this.contentLength != -1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChunked() {
/*  71 */     return !isRepeatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStreaming() {
/*  76 */     return !isRepeatable();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/*  81 */     return this.contentLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public Header getContentType() {
/*  86 */     return this.contentType;
/*     */   }
/*     */ 
/*     */   
/*     */   public Header getContentEncoding() {
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeContent() {}
/*     */ 
/*     */   
/*     */   public InputStream getContent() throws IOException {
/* 100 */     if (this.contentLength < 0L)
/* 101 */       throw new ContentTooLongException("Content length is unknown"); 
/* 102 */     if (this.contentLength > 25600L) {
/* 103 */       throw new ContentTooLongException("Content length is too long: " + this.contentLength);
/*     */     }
/* 105 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 106 */     writeTo(outStream);
/* 107 */     outStream.flush();
/* 108 */     return new ByteArrayInputStream(outStream.toByteArray());
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream outStream) throws IOException {
/* 113 */     this.multipart.writeTo(outStream);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/MultipartFormEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */