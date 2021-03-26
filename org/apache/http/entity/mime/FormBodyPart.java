/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.entity.mime.content.AbstractContentBody;
/*     */ import org.apache.http.entity.mime.content.ContentBody;
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
/*     */ public class FormBodyPart
/*     */ {
/*     */   private final String name;
/*     */   private final Header header;
/*     */   private final ContentBody body;
/*     */   
/*     */   FormBodyPart(String name, ContentBody body, Header header) {
/*  50 */     Args.notNull(name, "Name");
/*  51 */     Args.notNull(body, "Body");
/*  52 */     this.name = name;
/*  53 */     this.body = body;
/*  54 */     this.header = (header != null) ? header : new Header();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public FormBodyPart(String name, ContentBody body) {
/*  63 */     Args.notNull(name, "Name");
/*  64 */     Args.notNull(body, "Body");
/*  65 */     this.name = name;
/*  66 */     this.body = body;
/*  67 */     this.header = new Header();
/*     */     
/*  69 */     generateContentDisp(body);
/*  70 */     generateContentType(body);
/*  71 */     generateTransferEncoding(body);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  75 */     return this.name;
/*     */   }
/*     */   
/*     */   public ContentBody getBody() {
/*  79 */     return this.body;
/*     */   }
/*     */   
/*     */   public Header getHeader() {
/*  83 */     return this.header;
/*     */   }
/*     */   
/*     */   public void addField(String name, String value) {
/*  87 */     Args.notNull(name, "Field name");
/*  88 */     this.header.addField(new MinimalField(name, value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected void generateContentDisp(ContentBody body) {
/*  96 */     StringBuilder buffer = new StringBuilder();
/*  97 */     buffer.append("form-data; name=\"");
/*  98 */     buffer.append(getName());
/*  99 */     buffer.append("\"");
/* 100 */     if (body.getFilename() != null) {
/* 101 */       buffer.append("; filename=\"");
/* 102 */       buffer.append(body.getFilename());
/* 103 */       buffer.append("\"");
/*     */     } 
/* 105 */     addField("Content-Disposition", buffer.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected void generateContentType(ContentBody body) {
/*     */     ContentType contentType;
/* 114 */     if (body instanceof AbstractContentBody) {
/* 115 */       contentType = ((AbstractContentBody)body).getContentType();
/*     */     } else {
/* 117 */       contentType = null;
/*     */     } 
/* 119 */     if (contentType != null) {
/* 120 */       addField("Content-Type", contentType.toString());
/*     */     } else {
/* 122 */       StringBuilder buffer = new StringBuilder();
/* 123 */       buffer.append(body.getMimeType());
/* 124 */       if (body.getCharset() != null) {
/* 125 */         buffer.append("; charset=");
/* 126 */         buffer.append(body.getCharset());
/*     */       } 
/* 128 */       addField("Content-Type", buffer.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected void generateTransferEncoding(ContentBody body) {
/* 137 */     addField("Content-Transfer-Encoding", body.getTransferEncoding());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/FormBodyPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */