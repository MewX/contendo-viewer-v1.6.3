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
/*    */ 
/*    */ 
/*    */ final class IdentityFilter
/*    */   extends Filter
/*    */ {
/*    */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 38 */     IOUtils.copy(encoded, decoded);
/* 39 */     decoded.flush();
/* 40 */     return new DecodeResult(parameters);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 47 */     IOUtils.copy(input, encoded);
/* 48 */     encoded.flush();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/IdentityFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */