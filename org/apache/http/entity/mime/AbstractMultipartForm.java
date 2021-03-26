/*     */ package org.apache.http.entity.mime;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.List;
/*     */ import org.apache.http.entity.mime.content.ContentBody;
/*     */ import org.apache.http.util.Args;
/*     */ import org.apache.http.util.ByteArrayBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractMultipartForm
/*     */ {
/*     */   private static ByteArrayBuffer encode(Charset charset, String string) {
/*  53 */     ByteBuffer encoded = charset.encode(CharBuffer.wrap(string));
/*  54 */     ByteArrayBuffer bab = new ByteArrayBuffer(encoded.remaining());
/*  55 */     bab.append(encoded.array(), encoded.position(), encoded.remaining());
/*  56 */     return bab;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeBytes(ByteArrayBuffer b, OutputStream out) throws IOException {
/*  61 */     out.write(b.buffer(), 0, b.length());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeBytes(String s, Charset charset, OutputStream out) throws IOException {
/*  66 */     ByteArrayBuffer b = encode(charset, s);
/*  67 */     writeBytes(b, out);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeBytes(String s, OutputStream out) throws IOException {
/*  72 */     ByteArrayBuffer b = encode(MIME.DEFAULT_CHARSET, s);
/*  73 */     writeBytes(b, out);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void writeField(MinimalField field, OutputStream out) throws IOException {
/*  78 */     writeBytes(field.getName(), out);
/*  79 */     writeBytes(FIELD_SEP, out);
/*  80 */     writeBytes(field.getBody(), out);
/*  81 */     writeBytes(CR_LF, out);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void writeField(MinimalField field, Charset charset, OutputStream out) throws IOException {
/*  86 */     writeBytes(field.getName(), charset, out);
/*  87 */     writeBytes(FIELD_SEP, out);
/*  88 */     writeBytes(field.getBody(), charset, out);
/*  89 */     writeBytes(CR_LF, out);
/*     */   }
/*     */   
/*  92 */   private static final ByteArrayBuffer FIELD_SEP = encode(MIME.DEFAULT_CHARSET, ": ");
/*  93 */   private static final ByteArrayBuffer CR_LF = encode(MIME.DEFAULT_CHARSET, "\r\n");
/*  94 */   private static final ByteArrayBuffer TWO_DASHES = encode(MIME.DEFAULT_CHARSET, "--");
/*     */ 
/*     */ 
/*     */   
/*     */   final Charset charset;
/*     */ 
/*     */ 
/*     */   
/*     */   final String boundary;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractMultipartForm(Charset charset, String boundary) {
/* 108 */     Args.notNull(boundary, "Multipart boundary");
/* 109 */     this.charset = (charset != null) ? charset : MIME.DEFAULT_CHARSET;
/* 110 */     this.boundary = boundary;
/*     */   }
/*     */   
/*     */   public AbstractMultipartForm(String boundary) {
/* 114 */     this(null, boundary);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract List<FormBodyPart> getBodyParts();
/*     */ 
/*     */   
/*     */   void doWriteTo(OutputStream out, boolean writeContent) throws IOException {
/* 123 */     ByteArrayBuffer boundaryEncoded = encode(this.charset, this.boundary);
/* 124 */     for (FormBodyPart part : getBodyParts()) {
/* 125 */       writeBytes(TWO_DASHES, out);
/* 126 */       writeBytes(boundaryEncoded, out);
/* 127 */       writeBytes(CR_LF, out);
/*     */       
/* 129 */       formatMultipartHeader(part, out);
/*     */       
/* 131 */       writeBytes(CR_LF, out);
/*     */       
/* 133 */       if (writeContent) {
/* 134 */         part.getBody().writeTo(out);
/*     */       }
/* 136 */       writeBytes(CR_LF, out);
/*     */     } 
/* 138 */     writeBytes(TWO_DASHES, out);
/* 139 */     writeBytes(boundaryEncoded, out);
/* 140 */     writeBytes(TWO_DASHES, out);
/* 141 */     writeBytes(CR_LF, out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void formatMultipartHeader(FormBodyPart paramFormBodyPart, OutputStream paramOutputStream) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/* 157 */     doWriteTo(out, true);
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
/*     */   
/*     */   public long getTotalLength() {
/* 175 */     long contentLen = 0L;
/* 176 */     for (FormBodyPart part : getBodyParts()) {
/* 177 */       ContentBody body = part.getBody();
/* 178 */       long len = body.getContentLength();
/* 179 */       if (len >= 0L) {
/* 180 */         contentLen += len; continue;
/*     */       } 
/* 182 */       return -1L;
/*     */     } 
/*     */     
/* 185 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*     */     try {
/* 187 */       doWriteTo(out, false);
/* 188 */       byte[] extra = out.toByteArray();
/* 189 */       return contentLen + extra.length;
/* 190 */     } catch (IOException ex) {
/*     */       
/* 192 */       return -1L;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/AbstractMultipartForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */