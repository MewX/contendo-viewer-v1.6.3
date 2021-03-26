/*    */ package org.apache.pdfbox.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.io.IOUtils;
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
/*    */ final class ASCII85Filter
/*    */   extends Filter
/*    */ {
/*    */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 36 */     ASCII85InputStream is = null;
/*    */     
/*    */     try {
/* 39 */       is = new ASCII85InputStream(encoded);
/* 40 */       IOUtils.copy(is, decoded);
/* 41 */       decoded.flush();
/*    */     }
/*    */     finally {
/*    */       
/* 45 */       IOUtils.closeQuietly(is);
/*    */     } 
/* 47 */     return new DecodeResult(parameters);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 54 */     ASCII85OutputStream os = new ASCII85OutputStream(encoded);
/* 55 */     IOUtils.copy(input, os);
/* 56 */     os.close();
/* 57 */     encoded.flush();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/ASCII85Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */