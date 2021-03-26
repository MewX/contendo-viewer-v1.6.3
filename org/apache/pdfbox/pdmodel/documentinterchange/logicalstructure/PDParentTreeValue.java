/*    */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
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
/*    */ public class PDParentTreeValue
/*    */   implements COSObjectable
/*    */ {
/*    */   COSObjectable obj;
/*    */   
/*    */   public PDParentTreeValue(COSArray obj) {
/* 36 */     this.obj = (COSObjectable)obj;
/*    */   }
/*    */ 
/*    */   
/*    */   public PDParentTreeValue(COSDictionary obj) {
/* 41 */     this.obj = (COSObjectable)obj;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 47 */     return this.obj.getCOSObject();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return this.obj.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDParentTreeValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */