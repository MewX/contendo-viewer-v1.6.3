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
/*     */ public class PDFormFieldAdditionalActions
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary actions;
/*     */   
/*     */   public PDFormFieldAdditionalActions() {
/*  39 */     this.actions = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormFieldAdditionalActions(COSDictionary a) {
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
/*     */   public PDAction getK() {
/*  73 */     COSDictionary k = (COSDictionary)this.actions.getDictionaryObject(COSName.K);
/*  74 */     PDAction retval = null;
/*  75 */     if (k != null)
/*     */     {
/*  77 */       retval = PDActionFactory.createAction(k);
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
/*     */   public void setK(PDAction k) {
/*  92 */     this.actions.setItem(COSName.K, (COSObjectable)k);
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
/*     */   public PDAction getF() {
/* 104 */     COSDictionary f = (COSDictionary)this.actions.getDictionaryObject(COSName.F);
/* 105 */     PDAction retval = null;
/* 106 */     if (f != null)
/*     */     {
/* 108 */       retval = PDActionFactory.createAction(f);
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
/*     */   public void setF(PDAction f) {
/* 122 */     this.actions.setItem(COSName.F, (COSObjectable)f);
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
/*     */   public PDAction getV() {
/* 135 */     COSDictionary v = (COSDictionary)this.actions.getDictionaryObject(COSName.V);
/* 136 */     PDAction retval = null;
/* 137 */     if (v != null)
/*     */     {
/* 139 */       retval = PDActionFactory.createAction(v);
/*     */     }
/* 141 */     return retval;
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
/*     */   public void setV(PDAction v) {
/* 154 */     this.actions.setItem(COSName.V, (COSObjectable)v);
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
/*     */   
/*     */   public PDAction getC() {
/* 168 */     COSDictionary c = (COSDictionary)this.actions.getDictionaryObject(COSName.C);
/* 169 */     PDAction retval = null;
/* 170 */     if (c != null)
/*     */     {
/* 172 */       retval = PDActionFactory.createAction(c);
/*     */     }
/* 174 */     return retval;
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
/*     */   
/*     */   public void setC(PDAction c) {
/* 188 */     this.actions.setItem(COSName.C, (COSObjectable)c);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDFormFieldAdditionalActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */