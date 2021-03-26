/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSBase;
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
/*    */ 
/*    */ public class PDActionResetForm
/*    */   extends PDAction
/*    */ {
/*    */   public static final String SUB_TYPE = "ResetForm";
/*    */   
/*    */   public PDActionResetForm() {
/* 41 */     setSubType("ResetForm");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionResetForm(COSDictionary a) {
/* 51 */     super(a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSArray getFields() {
/* 62 */     COSBase retval = this.action.getDictionaryObject(COSName.FIELDS);
/* 63 */     return (retval instanceof COSArray) ? (COSArray)retval : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFields(COSArray array) {
/* 71 */     this.action.setItem(COSName.FIELDS, (COSBase)array);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFlags() {
/* 81 */     return this.action.getInt(COSName.FLAGS, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFlags(int flags) {
/* 89 */     this.action.setInt(COSName.FLAGS, flags);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionResetForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */