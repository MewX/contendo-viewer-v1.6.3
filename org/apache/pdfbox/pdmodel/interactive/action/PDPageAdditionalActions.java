/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDPageAdditionalActions
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary actions;
/*     */   
/*     */   public PDPageAdditionalActions() {
/*  39 */     this.actions = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageAdditionalActions(COSDictionary a) {
/*  49 */     this.actions = a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  60 */     return this.actions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getO() {
/*  73 */     COSDictionary o = (COSDictionary)this.actions.getDictionaryObject(COSName.O);
/*  74 */     PDAction retval = null;
/*  75 */     if (o != null)
/*     */     {
/*  77 */       retval = PDActionFactory.createAction(o);
/*     */     }
/*  79 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setO(PDAction o) {
/*  92 */     this.actions.setItem(COSName.O, (COSObjectable)o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getC() {
/* 104 */     COSDictionary c = (COSDictionary)this.actions.getDictionaryObject("C");
/* 105 */     PDAction retval = null;
/* 106 */     if (c != null)
/*     */     {
/* 108 */       retval = PDActionFactory.createAction(c);
/*     */     }
/* 110 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setC(PDAction c) {
/* 122 */     this.actions.setItem("C", (COSObjectable)c);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDPageAdditionalActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */