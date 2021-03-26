/*    */ package org.apache.pdfbox.pdmodel;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
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
/*    */ public class PDDocumentNameDestinationDictionary
/*    */   implements COSObjectable
/*    */ {
/*    */   private final COSDictionary nameDictionary;
/*    */   
/*    */   public PDDocumentNameDestinationDictionary(COSDictionary dict) {
/* 43 */     this.nameDictionary = dict;
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
/* 54 */     return this.nameDictionary;
/*    */   }
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
/*    */   public PDDestination getDestination(String name) throws IOException {
/* 67 */     COSBase item = this.nameDictionary.getDictionaryObject(name);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 72 */     if (item instanceof org.apache.pdfbox.cos.COSArray)
/*    */     {
/* 74 */       return PDDestination.create(item);
/*    */     }
/* 76 */     if (item instanceof COSDictionary) {
/*    */       
/* 78 */       COSDictionary dict = (COSDictionary)item;
/* 79 */       if (dict.containsKey(COSName.D))
/*    */       {
/* 81 */         return PDDestination.create(dict.getDictionaryObject(COSName.D));
/*    */       }
/*    */     } 
/* 84 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDDocumentNameDestinationDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */