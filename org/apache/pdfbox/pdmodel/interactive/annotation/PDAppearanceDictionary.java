/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
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
/*     */ public class PDAppearanceDictionary
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDAppearanceDictionary() {
/*  39 */     this.dictionary = new COSDictionary();
/*     */     
/*  41 */     this.dictionary.setItem(COSName.N, (COSBase)new COSDictionary());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceDictionary(COSDictionary dictionary) {
/*  51 */     this.dictionary = dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  57 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceEntry getNormalAppearance() {
/*  68 */     COSBase entry = this.dictionary.getDictionaryObject(COSName.N);
/*  69 */     if (entry instanceof COSDictionary)
/*     */     {
/*  71 */       return new PDAppearanceEntry(entry);
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNormalAppearance(PDAppearanceEntry entry) {
/*  84 */     this.dictionary.setItem(COSName.N, entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNormalAppearance(PDAppearanceStream ap) {
/*  94 */     this.dictionary.setItem(COSName.N, (COSObjectable)ap);
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
/*     */   public PDAppearanceEntry getRolloverAppearance() {
/* 106 */     COSBase entry = this.dictionary.getDictionaryObject(COSName.R);
/* 107 */     if (entry instanceof COSDictionary)
/*     */     {
/* 109 */       return new PDAppearanceEntry(entry);
/*     */     }
/*     */ 
/*     */     
/* 113 */     return getNormalAppearance();
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
/*     */   public void setRolloverAppearance(PDAppearanceEntry entry) {
/* 125 */     this.dictionary.setItem(COSName.R, entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRolloverAppearance(PDAppearanceStream ap) {
/* 135 */     this.dictionary.setItem(COSName.R, (COSObjectable)ap);
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
/*     */   public PDAppearanceEntry getDownAppearance() {
/* 147 */     COSBase entry = this.dictionary.getDictionaryObject(COSName.D);
/* 148 */     if (entry instanceof COSDictionary)
/*     */     {
/* 150 */       return new PDAppearanceEntry(entry);
/*     */     }
/*     */ 
/*     */     
/* 154 */     return getNormalAppearance();
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
/*     */   public void setDownAppearance(PDAppearanceEntry entry) {
/* 166 */     this.dictionary.setItem(COSName.D, entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDownAppearance(PDAppearanceStream ap) {
/* 176 */     this.dictionary.setItem(COSName.D, (COSObjectable)ap);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAppearanceDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */