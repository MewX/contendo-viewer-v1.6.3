/*    */ package org.apache.pdfbox.pdmodel.font;
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
/*    */ public final class PDCIDSystemInfo
/*    */   implements COSObjectable
/*    */ {
/*    */   private final COSDictionary dictionary;
/*    */   
/*    */   PDCIDSystemInfo(String registry, String ordering, int supplement) {
/* 36 */     this.dictionary = new COSDictionary();
/* 37 */     this.dictionary.setString(COSName.REGISTRY, registry);
/* 38 */     this.dictionary.setString(COSName.ORDERING, ordering);
/* 39 */     this.dictionary.setInt(COSName.SUPPLEMENT, supplement);
/*    */   }
/*    */ 
/*    */   
/*    */   PDCIDSystemInfo(COSDictionary dictionary) {
/* 44 */     this.dictionary = dictionary;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRegistry() {
/* 49 */     return this.dictionary.getNameAsString(COSName.REGISTRY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOrdering() {
/* 54 */     return this.dictionary.getNameAsString(COSName.ORDERING);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSupplement() {
/* 59 */     return this.dictionary.getInt(COSName.SUPPLEMENT);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 65 */     return (COSBase)this.dictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return getRegistry() + "-" + getOrdering() + "-" + getSupplement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDCIDSystemInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */