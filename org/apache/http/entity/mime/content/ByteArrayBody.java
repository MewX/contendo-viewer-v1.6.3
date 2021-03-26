/*     */ package org.apache.http.entity.mime.content;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ByteArrayBody
/*     */   extends AbstractContentBody
/*     */ {
/*     */   private final byte[] data;
/*     */   private final String filename;
/*     */   
/*     */   @Deprecated
/*     */   public ByteArrayBody(byte[] data, String mimeType, String filename) {
/*  67 */     this(data, ContentType.create(mimeType), filename);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteArrayBody(byte[] data, ContentType contentType, String filename) {
/*  74 */     super(contentType);
/*  75 */     Args.notNull(data, "byte[]");
/*  76 */     this.data = data;
/*  77 */     this.filename = filename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteArrayBody(byte[] data, String filename) {
/*  87 */     this(data, "application/octet-stream", filename);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilename() {
/*  92 */     return this.filename;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/*  97 */     out.write(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCharset() {
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTransferEncoding() {
/* 107 */     return "binary";
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 112 */     return this.data.length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/content/ByteArrayBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */