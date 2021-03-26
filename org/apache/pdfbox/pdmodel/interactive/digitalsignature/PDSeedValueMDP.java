/*    */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PDSeedValueMDP
/*    */ {
/*    */   private final COSDictionary dictionary;
/*    */   
/*    */   public PDSeedValueMDP() {
/* 37 */     this.dictionary = new COSDictionary();
/* 38 */     this.dictionary.setDirect(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDSeedValueMDP(COSDictionary dict) {
/* 48 */     this.dictionary = dict;
/* 49 */     this.dictionary.setDirect(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSDictionary getCOSObject() {
/* 59 */     return this.dictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getP() {
/* 69 */     return this.dictionary.getInt(COSName.P);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setP(int p) {
/* 79 */     if (p < 0 || p > 3)
/*    */     {
/* 81 */       throw new IllegalArgumentException("Only values between 0 and 3 nare allowed.");
/*    */     }
/* 83 */     this.dictionary.setInt(COSName.P, p);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDSeedValueMDP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */