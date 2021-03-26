/*    */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*    */ 
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.pdfbox.pdfwriter.COSWriter;
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
/*    */ public class SigningSupport
/*    */   implements Closeable, ExternalSigningSupport
/*    */ {
/*    */   private COSWriter cosWriter;
/*    */   
/*    */   public SigningSupport(COSWriter cosWriter) {
/* 36 */     this.cosWriter = cosWriter;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream getContent() throws IOException {
/* 42 */     return this.cosWriter.getDataToSign();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSignature(byte[] signature) throws IOException {
/* 48 */     this.cosWriter.writeExternalSignature(signature);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 54 */     if (this.cosWriter != null)
/*    */       
/*    */       try {
/*    */         
/* 58 */         this.cosWriter.close();
/*    */       }
/*    */       finally {
/*    */         
/* 62 */         this.cosWriter = null;
/*    */       }  
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/SigningSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */