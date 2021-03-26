/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
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
/*    */ public class PDAdditionalActions
/*    */   implements COSObjectable
/*    */ {
/*    */   private final COSDictionary actions;
/*    */   
/*    */   public PDAdditionalActions() {
/* 37 */     this.actions = new COSDictionary();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDAdditionalActions(COSDictionary a) {
/* 47 */     this.actions = a;
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
/* 58 */     return this.actions;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDAction getF() {
/* 68 */     return PDActionFactory.createAction((COSDictionary)this.actions.getDictionaryObject(COSName.F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setF(PDAction action) {
/* 78 */     this.actions.setItem(COSName.F, (COSObjectable)action);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDAdditionalActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */