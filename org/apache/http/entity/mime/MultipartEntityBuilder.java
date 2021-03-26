/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.entity.mime.content.ByteArrayBody;
/*     */ import org.apache.http.entity.mime.content.ContentBody;
/*     */ import org.apache.http.entity.mime.content.FileBody;
/*     */ import org.apache.http.entity.mime.content.InputStreamBody;
/*     */ import org.apache.http.entity.mime.content.StringBody;
/*     */ import org.apache.http.message.BasicNameValuePair;
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
/*     */ public class MultipartEntityBuilder
/*     */ {
/*  59 */   private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
/*     */ 
/*     */   
/*     */   private static final String DEFAULT_SUBTYPE = "form-data";
/*     */   
/*     */   private ContentType contentType;
/*     */   
/*  66 */   private HttpMultipartMode mode = HttpMultipartMode.STRICT;
/*  67 */   private String boundary = null;
/*  68 */   private Charset charset = null;
/*  69 */   private List<FormBodyPart> bodyParts = null;
/*     */   
/*     */   public static MultipartEntityBuilder create() {
/*  72 */     return new MultipartEntityBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder setMode(HttpMultipartMode mode) {
/*  79 */     this.mode = mode;
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public MultipartEntityBuilder setLaxMode() {
/*  84 */     this.mode = HttpMultipartMode.BROWSER_COMPATIBLE;
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public MultipartEntityBuilder setStrictMode() {
/*  89 */     this.mode = HttpMultipartMode.STRICT;
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public MultipartEntityBuilder setBoundary(String boundary) {
/*  94 */     this.boundary = boundary;
/*  95 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder setMimeSubtype(String subType) {
/* 102 */     Args.notBlank(subType, "MIME subtype");
/* 103 */     this.contentType = ContentType.create("multipart/" + subType);
/* 104 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public MultipartEntityBuilder seContentType(ContentType contentType) {
/* 114 */     return setContentType(contentType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder setContentType(ContentType contentType) {
/* 121 */     Args.notNull(contentType, "Content type");
/* 122 */     this.contentType = contentType;
/* 123 */     return this;
/*     */   }
/*     */   
/*     */   public MultipartEntityBuilder setCharset(Charset charset) {
/* 127 */     this.charset = charset;
/* 128 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addPart(FormBodyPart bodyPart) {
/* 135 */     if (bodyPart == null) {
/* 136 */       return this;
/*     */     }
/* 138 */     if (this.bodyParts == null) {
/* 139 */       this.bodyParts = new ArrayList<FormBodyPart>();
/*     */     }
/* 141 */     this.bodyParts.add(bodyPart);
/* 142 */     return this;
/*     */   }
/*     */   
/*     */   public MultipartEntityBuilder addPart(String name, ContentBody contentBody) {
/* 146 */     Args.notNull(name, "Name");
/* 147 */     Args.notNull(contentBody, "Content body");
/* 148 */     return addPart(FormBodyPartBuilder.create(name, contentBody).build());
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addTextBody(String name, String text, ContentType contentType) {
/* 153 */     return addPart(name, (ContentBody)new StringBody(text, contentType));
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addTextBody(String name, String text) {
/* 158 */     return addTextBody(name, text, ContentType.DEFAULT_TEXT);
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addBinaryBody(String name, byte[] b, ContentType contentType, String filename) {
/* 163 */     return addPart(name, (ContentBody)new ByteArrayBody(b, contentType, filename));
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addBinaryBody(String name, byte[] b) {
/* 168 */     return addBinaryBody(name, b, ContentType.DEFAULT_BINARY, (String)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addBinaryBody(String name, File file, ContentType contentType, String filename) {
/* 173 */     return addPart(name, (ContentBody)new FileBody(file, contentType, filename));
/*     */   }
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addBinaryBody(String name, File file) {
/* 178 */     return addBinaryBody(name, file, ContentType.DEFAULT_BINARY, (file != null) ? file.getName() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipartEntityBuilder addBinaryBody(String name, InputStream stream, ContentType contentType, String filename) {
/* 184 */     return addPart(name, (ContentBody)new InputStreamBody(stream, contentType, filename));
/*     */   }
/*     */   
/*     */   public MultipartEntityBuilder addBinaryBody(String name, InputStream stream) {
/* 188 */     return addBinaryBody(name, stream, ContentType.DEFAULT_BINARY, (String)null);
/*     */   }
/*     */   
/*     */   private String generateBoundary() {
/* 192 */     StringBuilder buffer = new StringBuilder();
/* 193 */     Random rand = new Random();
/* 194 */     int count = rand.nextInt(11) + 30;
/* 195 */     for (int i = 0; i < count; i++) {
/* 196 */       buffer.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
/*     */     }
/* 198 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   MultipartFormEntity buildEntity() {
/* 202 */     String boundaryCopy = this.boundary;
/* 203 */     if (boundaryCopy == null && this.contentType != null) {
/* 204 */       boundaryCopy = this.contentType.getParameter("boundary");
/*     */     }
/* 206 */     if (boundaryCopy == null) {
/* 207 */       boundaryCopy = generateBoundary();
/*     */     }
/* 209 */     Charset charsetCopy = this.charset;
/* 210 */     if (charsetCopy == null && this.contentType != null) {
/* 211 */       charsetCopy = this.contentType.getCharset();
/*     */     }
/* 213 */     List<NameValuePair> paramsList = new ArrayList<NameValuePair>(2);
/* 214 */     paramsList.add(new BasicNameValuePair("boundary", boundaryCopy));
/* 215 */     if (charsetCopy != null) {
/* 216 */       paramsList.add(new BasicNameValuePair("charset", charsetCopy.name()));
/*     */     }
/* 218 */     NameValuePair[] params = paramsList.<NameValuePair>toArray(new NameValuePair[paramsList.size()]);
/* 219 */     ContentType contentTypeCopy = (this.contentType != null) ? this.contentType.withParameters(params) : ContentType.create("multipart/form-data", params);
/*     */ 
/*     */     
/* 222 */     List<FormBodyPart> bodyPartsCopy = (this.bodyParts != null) ? new ArrayList<FormBodyPart>(this.bodyParts) : Collections.<FormBodyPart>emptyList();
/*     */     
/* 224 */     HttpMultipartMode modeCopy = (this.mode != null) ? this.mode : HttpMultipartMode.STRICT;
/*     */     
/* 226 */     switch (modeCopy)
/*     */     { case BROWSER_COMPATIBLE:
/* 228 */         form = new HttpBrowserCompatibleMultipart(charsetCopy, boundaryCopy, bodyPartsCopy);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 236 */         return new MultipartFormEntity(form, contentTypeCopy, form.getTotalLength());case RFC6532: form = new HttpRFC6532Multipart(charsetCopy, boundaryCopy, bodyPartsCopy); return new MultipartFormEntity(form, contentTypeCopy, form.getTotalLength()); }  AbstractMultipartForm form = new HttpStrictMultipart(charsetCopy, boundaryCopy, bodyPartsCopy); return new MultipartFormEntity(form, contentTypeCopy, form.getTotalLength());
/*     */   }
/*     */   
/*     */   public HttpEntity build() {
/* 240 */     return buildEntity();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/MultipartEntityBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */