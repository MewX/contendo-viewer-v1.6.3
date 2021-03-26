/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDPropBuild
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSDictionary dictionary;
/*     */   
/*     */   public PDPropBuild() {
/*  43 */     this.dictionary = new COSDictionary();
/*  44 */     this.dictionary.setDirect(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPropBuild(COSDictionary dict) {
/*  54 */     this.dictionary = dict;
/*  55 */     this.dictionary.setDirect(true);
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
/*  66 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPropBuildDataDict getFilter() {
/*  77 */     PDPropBuildDataDict filter = null;
/*  78 */     COSDictionary filterDic = (COSDictionary)this.dictionary.getDictionaryObject(COSName.FILTER);
/*  79 */     if (filterDic != null)
/*     */     {
/*  81 */       filter = new PDPropBuildDataDict(filterDic);
/*     */     }
/*  83 */     return filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPDPropBuildFilter(PDPropBuildDataDict filter) {
/*  94 */     this.dictionary.setItem(COSName.FILTER, filter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPropBuildDataDict getPubSec() {
/* 105 */     PDPropBuildDataDict pubSec = null;
/* 106 */     COSDictionary pubSecDic = (COSDictionary)this.dictionary.getDictionaryObject(COSName.PUB_SEC);
/* 107 */     if (pubSecDic != null)
/*     */     {
/* 109 */       pubSec = new PDPropBuildDataDict(pubSecDic);
/*     */     }
/* 111 */     return pubSec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPDPropBuildPubSec(PDPropBuildDataDict pubSec) {
/* 121 */     this.dictionary.setItem(COSName.PUB_SEC, pubSec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPropBuildDataDict getApp() {
/* 132 */     PDPropBuildDataDict app = null;
/* 133 */     COSDictionary appDic = (COSDictionary)this.dictionary.getDictionaryObject(COSName.APP);
/* 134 */     if (appDic != null)
/*     */     {
/* 136 */       app = new PDPropBuildDataDict(appDic);
/*     */     }
/* 138 */     return app;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPDPropBuildApp(PDPropBuildDataDict app) {
/* 149 */     this.dictionary.setItem(COSName.APP, app);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDPropBuild.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */