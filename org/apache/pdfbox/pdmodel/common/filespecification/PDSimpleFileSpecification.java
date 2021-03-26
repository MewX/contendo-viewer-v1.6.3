/*    */ package org.apache.pdfbox.pdmodel.common.filespecification;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSString;
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
/*    */ public class PDSimpleFileSpecification
/*    */   extends PDFileSpecification
/*    */ {
/*    */   private COSString file;
/*    */   
/*    */   public PDSimpleFileSpecification() {
/* 37 */     this.file = new COSString("");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDSimpleFileSpecification(COSString fileName) {
/* 47 */     this.file = fileName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFile() {
/* 58 */     return this.file.getString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFile(String fileName) {
/* 69 */     this.file = new COSString(fileName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 80 */     return (COSBase)this.file;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/filespecification/PDSimpleFileSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */