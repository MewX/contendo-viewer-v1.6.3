/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class HttpMultipart
/*     */   extends AbstractMultipartForm
/*     */ {
/*     */   private final HttpMultipartMode mode;
/*     */   private final List<FormBodyPart> parts;
/*     */   private final String subType;
/*     */   
/*     */   public HttpMultipart(String subType, Charset charset, String boundary, HttpMultipartMode mode) {
/*  66 */     super(charset, boundary);
/*  67 */     this.subType = subType;
/*  68 */     this.mode = mode;
/*  69 */     this.parts = new ArrayList<FormBodyPart>();
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
/*     */   public HttpMultipart(String subType, Charset charset, String boundary) {
/*  83 */     this(subType, charset, boundary, HttpMultipartMode.STRICT);
/*     */   }
/*     */   
/*     */   public HttpMultipart(String subType, String boundary) {
/*  87 */     this(subType, (Charset)null, boundary);
/*     */   }
/*     */   
/*     */   public HttpMultipartMode getMode() {
/*  91 */     return this.mode;
/*     */   }
/*     */   
/*     */   protected void formatMultipartHeader(FormBodyPart part, OutputStream out) throws IOException {
/*     */     MinimalField cd;
/*     */     String filename;
/*  97 */     Header header = part.getHeader();
/*  98 */     switch (this.mode) {
/*     */ 
/*     */       
/*     */       case BROWSER_COMPATIBLE:
/* 102 */         cd = header.getField("Content-Disposition");
/* 103 */         writeField(cd, this.charset, out);
/* 104 */         filename = part.getBody().getFilename();
/* 105 */         if (filename != null) {
/* 106 */           MinimalField ct = header.getField("Content-Type");
/* 107 */           writeField(ct, this.charset, out);
/*     */         } 
/*     */         return;
/*     */     } 
/* 111 */     for (MinimalField field : header) {
/* 112 */       writeField(field, out);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FormBodyPart> getBodyParts() {
/* 119 */     return this.parts;
/*     */   }
/*     */   
/*     */   public void addBodyPart(FormBodyPart part) {
/* 123 */     if (part == null) {
/*     */       return;
/*     */     }
/* 126 */     this.parts.add(part);
/*     */   }
/*     */   
/*     */   public String getSubType() {
/* 130 */     return this.subType;
/*     */   }
/*     */   
/*     */   public Charset getCharset() {
/* 134 */     return this.charset;
/*     */   }
/*     */   
/*     */   public String getBoundary() {
/* 138 */     return this.boundary;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/HttpMultipart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */