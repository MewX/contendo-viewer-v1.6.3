/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
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
/*    */ public class PDActionImportData
/*    */   extends PDAction
/*    */ {
/*    */   public static final String SUB_TYPE = "ImportData";
/*    */   
/*    */   public PDActionImportData() {
/* 42 */     setSubType("ImportData");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionImportData(COSDictionary a) {
/* 52 */     super(a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDFileSpecification getFile() throws IOException {
/* 63 */     return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFile(PDFileSpecification fs) {
/* 73 */     this.action.setItem(COSName.F, (COSObjectable)fs);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionImportData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */