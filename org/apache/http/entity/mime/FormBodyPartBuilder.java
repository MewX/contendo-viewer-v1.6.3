/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.http.entity.ContentType;
/*     */ import org.apache.http.entity.mime.content.AbstractContentBody;
/*     */ import org.apache.http.entity.mime.content.ContentBody;
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
/*     */ public class FormBodyPartBuilder
/*     */ {
/*     */   private String name;
/*     */   private ContentBody body;
/*     */   private final Header header;
/*     */   
/*     */   public static FormBodyPartBuilder create(String name, ContentBody body) {
/*  50 */     return new FormBodyPartBuilder(name, body);
/*     */   }
/*     */   
/*     */   public static FormBodyPartBuilder create() {
/*  54 */     return new FormBodyPartBuilder();
/*     */   }
/*     */   
/*     */   FormBodyPartBuilder(String name, ContentBody body) {
/*  58 */     this();
/*  59 */     this.name = name;
/*  60 */     this.body = body;
/*     */   }
/*     */   
/*     */   FormBodyPartBuilder() {
/*  64 */     this.header = new Header();
/*     */   }
/*     */   
/*     */   public FormBodyPartBuilder setName(String name) {
/*  68 */     this.name = name;
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public FormBodyPartBuilder setBody(ContentBody body) {
/*  73 */     this.body = body;
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   public FormBodyPartBuilder addField(String name, String value) {
/*  78 */     Args.notNull(name, "Field name");
/*  79 */     this.header.addField(new MinimalField(name, value));
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public FormBodyPartBuilder setField(String name, String value) {
/*  84 */     Args.notNull(name, "Field name");
/*  85 */     this.header.setField(new MinimalField(name, value));
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public FormBodyPartBuilder removeFields(String name) {
/*  90 */     Args.notNull(name, "Field name");
/*  91 */     this.header.removeFields(name);
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   public FormBodyPart build() {
/*  96 */     Asserts.notBlank(this.name, "Name");
/*  97 */     Asserts.notNull(this.body, "Content body");
/*  98 */     Header headerCopy = new Header();
/*  99 */     List<MinimalField> fields = this.header.getFields();
/* 100 */     for (MinimalField field : fields) {
/* 101 */       headerCopy.addField(field);
/*     */     }
/* 103 */     if (headerCopy.getField("Content-Disposition") == null) {
/* 104 */       StringBuilder buffer = new StringBuilder();
/* 105 */       buffer.append("form-data; name=\"");
/* 106 */       buffer.append(encodeForHeader(this.name));
/* 107 */       buffer.append("\"");
/* 108 */       if (this.body.getFilename() != null) {
/* 109 */         buffer.append("; filename=\"");
/* 110 */         buffer.append(encodeForHeader(this.body.getFilename()));
/* 111 */         buffer.append("\"");
/*     */       } 
/* 113 */       headerCopy.addField(new MinimalField("Content-Disposition", buffer.toString()));
/*     */     } 
/* 115 */     if (headerCopy.getField("Content-Type") == null) {
/*     */       ContentType contentType;
/* 117 */       if (this.body instanceof AbstractContentBody) {
/* 118 */         contentType = ((AbstractContentBody)this.body).getContentType();
/*     */       } else {
/* 120 */         contentType = null;
/*     */       } 
/* 122 */       if (contentType != null) {
/* 123 */         headerCopy.addField(new MinimalField("Content-Type", contentType.toString()));
/*     */       } else {
/* 125 */         StringBuilder buffer = new StringBuilder();
/* 126 */         buffer.append(this.body.getMimeType());
/* 127 */         if (this.body.getCharset() != null) {
/* 128 */           buffer.append("; charset=");
/* 129 */           buffer.append(this.body.getCharset());
/*     */         } 
/* 131 */         headerCopy.addField(new MinimalField("Content-Type", buffer.toString()));
/*     */       } 
/*     */     } 
/* 134 */     if (headerCopy.getField("Content-Transfer-Encoding") == null)
/*     */     {
/* 136 */       headerCopy.addField(new MinimalField("Content-Transfer-Encoding", this.body.getTransferEncoding()));
/*     */     }
/* 138 */     return new FormBodyPart(this.name, this.body, headerCopy);
/*     */   }
/*     */   
/*     */   private static String encodeForHeader(String headerName) {
/* 142 */     if (headerName == null) {
/* 143 */       return null;
/*     */     }
/* 145 */     StringBuilder sb = new StringBuilder();
/* 146 */     for (int i = 0; i < headerName.length(); i++) {
/* 147 */       char x = headerName.charAt(i);
/* 148 */       if (x == '"' || x == '\\' || x == '\r') {
/* 149 */         sb.append("\\");
/*     */       }
/* 151 */       sb.append(x);
/*     */     } 
/* 153 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/FormBodyPartBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */