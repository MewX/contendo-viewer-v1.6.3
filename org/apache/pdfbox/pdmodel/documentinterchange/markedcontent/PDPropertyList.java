/*    */ package org.apache.pdfbox.pdmodel.documentinterchange.markedcontent;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
/*    */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentMembershipDictionary;
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
/*    */ public class PDPropertyList
/*    */   implements COSObjectable
/*    */ {
/*    */   protected final COSDictionary dict;
/*    */   
/*    */   public static PDPropertyList create(COSDictionary dict) {
/* 42 */     if (COSName.OCG.equals(dict.getItem(COSName.TYPE)))
/*    */     {
/* 44 */       return (PDPropertyList)new PDOptionalContentGroup(dict);
/*    */     }
/* 46 */     if (COSName.OCMD.equals(dict.getItem(COSName.TYPE)))
/*    */     {
/* 48 */       return (PDPropertyList)new PDOptionalContentMembershipDictionary(dict);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 53 */     return new PDPropertyList(dict);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDPropertyList() {
/* 62 */     this.dict = new COSDictionary();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDPropertyList(COSDictionary dict) {
/* 72 */     this.dict = dict;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSDictionary getCOSObject() {
/* 78 */     return this.dict;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/markedcontent/PDPropertyList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */