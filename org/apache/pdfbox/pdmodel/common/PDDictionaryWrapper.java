/*    */ package org.apache.pdfbox.pdmodel.common;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
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
/*    */ public class PDDictionaryWrapper
/*    */   implements COSObjectable
/*    */ {
/*    */   private final COSDictionary dictionary;
/*    */   
/*    */   public PDDictionaryWrapper() {
/* 37 */     this.dictionary = new COSDictionary();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDDictionaryWrapper(COSDictionary dictionary) {
/* 47 */     this.dictionary = dictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSDictionary getCOSObject() {
/* 56 */     return this.dictionary;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 62 */     if (this == obj)
/*    */     {
/* 64 */       return true;
/*    */     }
/* 66 */     if (obj instanceof PDDictionaryWrapper)
/*    */     {
/* 68 */       return this.dictionary.equals(((PDDictionaryWrapper)obj).dictionary);
/*    */     }
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 76 */     return this.dictionary.hashCode();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDDictionaryWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */