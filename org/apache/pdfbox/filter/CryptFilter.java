/*    */ package org.apache.pdfbox.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ final class CryptFilter
/*    */   extends Filter
/*    */ {
/*    */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 36 */     COSName encryptionName = (COSName)parameters.getDictionaryObject(COSName.NAME);
/* 37 */     if (encryptionName == null || encryptionName.equals(COSName.IDENTITY)) {
/*    */ 
/*    */       
/* 40 */       Filter identityFilter = new IdentityFilter();
/* 41 */       identityFilter.decode(encoded, decoded, parameters, index);
/* 42 */       return new DecodeResult(parameters);
/*    */     } 
/* 44 */     throw new IOException("Unsupported crypt filter " + encryptionName.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 51 */     COSName encryptionName = (COSName)parameters.getDictionaryObject(COSName.NAME);
/* 52 */     if (encryptionName == null || encryptionName.equals(COSName.IDENTITY)) {
/*    */ 
/*    */       
/* 55 */       Filter identityFilter = new IdentityFilter();
/* 56 */       identityFilter.encode(input, encoded, parameters);
/*    */     }
/*    */     else {
/*    */       
/* 60 */       throw new IOException("Unsupported crypt filter " + encryptionName.getName());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/CryptFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */