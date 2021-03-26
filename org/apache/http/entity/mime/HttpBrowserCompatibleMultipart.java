/*    */ package org.apache.http.entity.mime;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.nio.charset.Charset;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class HttpBrowserCompatibleMultipart
/*    */   extends AbstractMultipartForm
/*    */ {
/*    */   private final List<FormBodyPart> parts;
/*    */   
/*    */   public HttpBrowserCompatibleMultipart(Charset charset, String boundary, List<FormBodyPart> parts) {
/* 49 */     super(charset, boundary);
/* 50 */     this.parts = parts;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<FormBodyPart> getBodyParts() {
/* 55 */     return this.parts;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void formatMultipartHeader(FormBodyPart part, OutputStream out) throws IOException {
/* 67 */     Header header = part.getHeader();
/* 68 */     MinimalField cd = header.getField("Content-Disposition");
/* 69 */     writeField(cd, this.charset, out);
/* 70 */     String filename = part.getBody().getFilename();
/* 71 */     if (filename != null) {
/* 72 */       MinimalField ct = header.getField("Content-Type");
/* 73 */       writeField(ct, this.charset, out);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/HttpBrowserCompatibleMultipart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */