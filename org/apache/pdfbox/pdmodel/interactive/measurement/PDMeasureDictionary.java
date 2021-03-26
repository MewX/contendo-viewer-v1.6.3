/*    */ package org.apache.pdfbox.pdmodel.interactive.measurement;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PDMeasureDictionary
/*    */   implements COSObjectable
/*    */ {
/*    */   public static final String TYPE = "Measure";
/*    */   private final COSDictionary measureDictionary;
/*    */   
/*    */   protected PDMeasureDictionary() {
/* 41 */     this.measureDictionary = new COSDictionary();
/* 42 */     getCOSObject().setName(COSName.TYPE, "Measure");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDMeasureDictionary(COSDictionary dictionary) {
/* 52 */     this.measureDictionary = dictionary;
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
/* 63 */     return this.measureDictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getType() {
/* 74 */     return "Measure";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSubtype() {
/* 84 */     return getCOSObject().getNameAsString(COSName.SUBTYPE, "RL");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setSubtype(String subtype) {
/* 94 */     getCOSObject().setName(COSName.SUBTYPE, subtype);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/measurement/PDMeasureDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */