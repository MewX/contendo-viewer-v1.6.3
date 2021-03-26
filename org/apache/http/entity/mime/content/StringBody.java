/*     */ package org.apache.http.entity.mime.content;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import org.apache.http.Consts;
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
/*     */ public class StringBody
/*     */   extends AbstractContentBody
/*     */ {
/*     */   private final byte[] content;
/*     */   
/*     */   @Deprecated
/*     */   public static StringBody create(String text, String mimeType, Charset charset) throws IllegalArgumentException {
/*     */     try {
/*  67 */       return new StringBody(text, mimeType, charset);
/*  68 */     } catch (UnsupportedEncodingException ex) {
/*  69 */       throw new IllegalArgumentException("Charset " + charset + " is not supported", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static StringBody create(String text, Charset charset) throws IllegalArgumentException {
/*  82 */     return create(text, null, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static StringBody create(String text) throws IllegalArgumentException {
/*  93 */     return create(text, null, null);
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
/*     */   
/*     */   @Deprecated
/*     */   public StringBody(String text, String mimeType, Charset charset) throws UnsupportedEncodingException {
/* 113 */     this(text, ContentType.create(mimeType, (charset != null) ? charset : Consts.ASCII));
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
/*     */   @Deprecated
/*     */   public StringBody(String text, Charset charset) throws UnsupportedEncodingException {
/* 130 */     this(text, "text/plain", charset);
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
/*     */   @Deprecated
/*     */   public StringBody(String text) throws UnsupportedEncodingException {
/* 147 */     this(text, "text/plain", Consts.ASCII);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBody(String text, ContentType contentType) {
/* 154 */     super(contentType);
/* 155 */     Args.notNull(text, "Text");
/* 156 */     Charset charset = contentType.getCharset();
/* 157 */     this.content = text.getBytes((charset != null) ? charset : Consts.ASCII);
/*     */   }
/*     */   
/*     */   public Reader getReader() {
/* 161 */     Charset charset = getContentType().getCharset();
/* 162 */     return new InputStreamReader(new ByteArrayInputStream(this.content), (charset != null) ? charset : Consts.ASCII);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/* 169 */     Args.notNull(out, "Output stream");
/* 170 */     InputStream in = new ByteArrayInputStream(this.content);
/* 171 */     byte[] tmp = new byte[4096];
/*     */     int l;
/* 173 */     while ((l = in.read(tmp)) != -1) {
/* 174 */       out.write(tmp, 0, l);
/*     */     }
/* 176 */     out.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTransferEncoding() {
/* 181 */     return "8bit";
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLength() {
/* 186 */     return this.content.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFilename() {
/* 191 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/content/StringBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */