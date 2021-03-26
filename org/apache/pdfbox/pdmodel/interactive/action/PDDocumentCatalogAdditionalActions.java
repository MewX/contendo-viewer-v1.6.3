/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
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
/*     */ public class PDDocumentCatalogAdditionalActions
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary actions;
/*     */   
/*     */   public PDDocumentCatalogAdditionalActions() {
/*  38 */     this.actions = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentCatalogAdditionalActions(COSDictionary a) {
/*  48 */     this.actions = a;
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
/*  59 */     return this.actions;
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
/*     */   public PDAction getWC() {
/*  71 */     COSDictionary wc = (COSDictionary)this.actions.getDictionaryObject("WC");
/*  72 */     PDAction retval = null;
/*  73 */     if (wc != null)
/*     */     {
/*  75 */       retval = PDActionFactory.createAction(wc);
/*     */     }
/*  77 */     return retval;
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
/*     */   public void setWC(PDAction wc) {
/*  89 */     this.actions.setItem("WC", (COSObjectable)wc);
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
/*     */   public PDAction getWS() {
/* 101 */     COSDictionary ws = (COSDictionary)this.actions.getDictionaryObject("WS");
/* 102 */     PDAction retval = null;
/* 103 */     if (ws != null)
/*     */     {
/* 105 */       retval = PDActionFactory.createAction(ws);
/*     */     }
/* 107 */     return retval;
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
/*     */   public void setWS(PDAction ws) {
/* 119 */     this.actions.setItem("WS", (COSObjectable)ws);
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
/*     */   public PDAction getDS() {
/* 131 */     COSDictionary ds = (COSDictionary)this.actions.getDictionaryObject("DS");
/* 132 */     PDAction retval = null;
/* 133 */     if (ds != null)
/*     */     {
/* 135 */       retval = PDActionFactory.createAction(ds);
/*     */     }
/* 137 */     return retval;
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
/*     */   public void setDS(PDAction ds) {
/* 149 */     this.actions.setItem("DS", (COSObjectable)ds);
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
/*     */   public PDAction getWP() {
/* 161 */     COSDictionary wp = (COSDictionary)this.actions.getDictionaryObject("WP");
/* 162 */     PDAction retval = null;
/* 163 */     if (wp != null)
/*     */     {
/* 165 */       retval = PDActionFactory.createAction(wp);
/*     */     }
/* 167 */     return retval;
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
/*     */   public void setWP(PDAction wp) {
/* 179 */     this.actions.setItem("WP", (COSObjectable)wp);
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
/*     */   public PDAction getDP() {
/* 191 */     COSDictionary dp = (COSDictionary)this.actions.getDictionaryObject("DP");
/* 192 */     PDAction retval = null;
/* 193 */     if (dp != null)
/*     */     {
/* 195 */       retval = PDActionFactory.createAction(dp);
/*     */     }
/* 197 */     return retval;
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
/*     */   public void setDP(PDAction dp) {
/* 209 */     this.actions.setItem("DP", (COSObjectable)dp);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDDocumentCatalogAdditionalActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */