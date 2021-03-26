/*     */ package org.apache.http.entity.mime.content;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.http.entity.ContentType;
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
/*     */ public class FileBody
/*     */   extends AbstractContentBody
/*     */ {
/*     */   private final File file;
/*     */   private final String filename;
/*     */   
/*     */   @Deprecated
/*     */   public FileBody(File file, String filename, String mimeType, String charset) {
/*  63 */     this(file, ContentType.create(mimeType, charset), filename);
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
/*     */   @Deprecated
/*     */   public FileBody(File file, String mimeType, String charset) {
/*  76 */     this(file, null, mimeType, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public FileBody(File file, String mimeType) {
/*  85 */     this(file, ContentType.create(mimeType), (String)null);
/*     */   }
/*     */   
/*     */   public FileBody(File file) {
/*  89 */     this(file, ContentType.DEFAULT_BINARY, (file != null) ? file.getName() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileBody(File file, ContentType contentType, String filename) {
/*  96 */     super(contentType);
/*  97 */     Args.notNull(file, "File");
/*  98 */     this.file = file;
/*  99 */     this.filename = (filename == null) ? file.getName() : filename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileBody(File file, ContentType contentType) {
/* 106 */     this(file, contentType, (file != null) ? file.getName() : null);
/*     */   }
/*     */   
/*     */   public InputStream getInputStream() throws IOException {
/* 110 */     return new FileInputStream(this.file);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/* 115 */     Args.notNull(out, "Output stream");
/* 116 */     InputStream in = new FileInputStream(this.file);
/*     */     try {
/* 118 */       byte[] tmp = new byte[4096];
/*     */       int l;
/* 120 */       while ((l = in.read(tmp)) != -1) {
/* 121 */         out.write(tmp, 0, l);
/*     */       }
/* 123 */       out.flush();
/*     */     } finally {
/* 125 */       in.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTransferEncoding() {
/* 131 */     return "binary";
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 136 */     return this.file.length();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilename() {
/* 141 */     return this.filename;
/*     */   }
/*     */   
/*     */   public File getFile() {
/* 145 */     return this.file;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/content/FileBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */