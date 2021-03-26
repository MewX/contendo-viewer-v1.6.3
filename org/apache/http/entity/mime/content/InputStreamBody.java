/*     */ package org.apache.http.entity.mime.content;
/*     */ 
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
/*     */ public class InputStreamBody
/*     */   extends AbstractContentBody
/*     */ {
/*     */   private final InputStream in;
/*     */   private final String filename;
/*     */   
/*     */   @Deprecated
/*     */   public InputStreamBody(InputStream in, String mimeType, String filename) {
/*  58 */     this(in, ContentType.create(mimeType), filename);
/*     */   }
/*     */   
/*     */   public InputStreamBody(InputStream in, String filename) {
/*  62 */     this(in, ContentType.DEFAULT_BINARY, filename);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStreamBody(InputStream in, ContentType contentType, String filename) {
/*  69 */     super(contentType);
/*  70 */     Args.notNull(in, "Input stream");
/*  71 */     this.in = in;
/*  72 */     this.filename = filename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStreamBody(InputStream in, ContentType contentType) {
/*  79 */     this(in, contentType, (String)null);
/*     */   }
/*     */   
/*     */   public InputStream getInputStream() {
/*  83 */     return this.in;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/*  88 */     Args.notNull(out, "Output stream");
/*     */     try {
/*  90 */       byte[] tmp = new byte[4096];
/*     */       int l;
/*  92 */       while ((l = this.in.read(tmp)) != -1) {
/*  93 */         out.write(tmp, 0, l);
/*     */       }
/*  95 */       out.flush();
/*     */     } finally {
/*  97 */       this.in.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTransferEncoding() {
/* 103 */     return "binary";
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 108 */     return -1L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilename() {
/* 113 */     return this.filename;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/content/InputStreamBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */