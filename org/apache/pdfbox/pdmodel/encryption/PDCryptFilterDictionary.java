/*     */ package org.apache.pdfbox.pdmodel.encryption;
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
/*     */ public class PDCryptFilterDictionary
/*     */   implements COSObjectable
/*     */ {
/*  36 */   protected COSDictionary cryptFilterDictionary = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCryptFilterDictionary() {
/*  43 */     this.cryptFilterDictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCryptFilterDictionary(COSDictionary d) {
/*  52 */     this.cryptFilterDictionary = d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public COSDictionary getCOSDictionary() {
/*  64 */     return this.cryptFilterDictionary;
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
/*  75 */     return this.cryptFilterDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(int length) {
/*  85 */     this.cryptFilterDictionary.setInt(COSName.LENGTH, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/*  96 */     return this.cryptFilterDictionary.getInt(COSName.LENGTH, 40);
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
/*     */   public void setCryptFilterMethod(COSName cfm) {
/* 108 */     this.cryptFilterDictionary.setItem(COSName.CFM, (COSBase)cfm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getCryptFilterMethod() {
/* 119 */     return (COSName)this.cryptFilterDictionary.getDictionaryObject(COSName.CFM);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/PDCryptFilterDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */