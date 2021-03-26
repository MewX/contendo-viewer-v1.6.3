/*    */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ public class PDExternalDataDictionary
/*    */   implements COSObjectable
/*    */ {
/*    */   private final COSDictionary dataDictionary;
/*    */   
/*    */   public PDExternalDataDictionary() {
/* 37 */     this.dataDictionary = new COSDictionary();
/* 38 */     this.dataDictionary.setName(COSName.TYPE, "ExData");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDExternalDataDictionary(COSDictionary dictionary) {
/* 48 */     this.dataDictionary = dictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSDictionary getCOSObject() {
/* 59 */     return this.dataDictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getType() {
/* 69 */     return getCOSObject().getNameAsString(COSName.TYPE, "ExData");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSubtype() {
/* 79 */     return getCOSObject().getNameAsString(COSName.SUBTYPE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSubtype(String subtype) {
/* 89 */     getCOSObject().setName(COSName.SUBTYPE, subtype);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDExternalDataDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */