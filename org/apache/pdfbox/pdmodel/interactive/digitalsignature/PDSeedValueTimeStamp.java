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
/*    */ public class PDSeedValueTimeStamp
/*    */ {
/*    */   private final COSDictionary dictionary;
/*    */   
/*    */   public PDSeedValueTimeStamp() {
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
/*    */   public PDSeedValueTimeStamp(COSDictionary dict) {
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
/*    */   public String getURL() {
/* 69 */     return this.dictionary.getString(COSName.URL);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setURL(String url) {
/* 78 */     this.dictionary.setString(COSName.URL, url);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTimestampRequired() {
/* 88 */     return (this.dictionary.getInt(COSName.FT, 0) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTimestampRequired(boolean flag) {
/* 98 */     this.dictionary.setInt(COSName.FT, flag ? 1 : 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDSeedValueTimeStamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */