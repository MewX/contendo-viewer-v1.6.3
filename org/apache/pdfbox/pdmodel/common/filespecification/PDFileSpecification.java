/*    */ package org.apache.pdfbox.pdmodel.common.filespecification;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSString;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*    */ public abstract class PDFileSpecification
/*    */   implements COSObjectable
/*    */ {
/*    */   public static PDFileSpecification createFS(COSBase base) throws IOException {
/* 47 */     PDFileSpecification retval = null;
/* 48 */     if (base != null)
/*    */     {
/*    */ 
/*    */       
/* 52 */       if (base instanceof COSString) {
/*    */         
/* 54 */         retval = new PDSimpleFileSpecification((COSString)base);
/*    */       }
/* 56 */       else if (base instanceof COSDictionary) {
/*    */         
/* 58 */         retval = new PDComplexFileSpecification((COSDictionary)base);
/*    */       }
/*    */       else {
/*    */         
/* 62 */         throw new IOException("Error: Unknown file specification " + base);
/*    */       }  } 
/* 64 */     return retval;
/*    */   }
/*    */   
/*    */   public abstract String getFile();
/*    */   
/*    */   public abstract void setFile(String paramString);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/filespecification/PDFileSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */