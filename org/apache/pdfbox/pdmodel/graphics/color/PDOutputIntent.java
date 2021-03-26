/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ public final class PDOutputIntent
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDOutputIntent(PDDocument doc, InputStream colorProfile) throws IOException {
/*  46 */     this.dictionary = new COSDictionary();
/*  47 */     this.dictionary.setItem(COSName.TYPE, (COSBase)COSName.OUTPUT_INTENT);
/*  48 */     this.dictionary.setItem(COSName.S, (COSBase)COSName.GTS_PDFA1);
/*  49 */     PDStream destOutputIntent = configureOutputProfile(doc, colorProfile);
/*  50 */     this.dictionary.setItem(COSName.DEST_OUTPUT_PROFILE, (COSObjectable)destOutputIntent);
/*     */   }
/*     */ 
/*     */   
/*     */   public PDOutputIntent(COSDictionary dictionary) {
/*  55 */     this.dictionary = dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  61 */     return (COSBase)this.dictionary;
/*     */   }
/*     */ 
/*     */   
/*     */   public COSStream getDestOutputIntent() {
/*  66 */     return (COSStream)this.dictionary.getDictionaryObject(COSName.DEST_OUTPUT_PROFILE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInfo() {
/*  71 */     return this.dictionary.getString(COSName.INFO);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInfo(String value) {
/*  76 */     this.dictionary.setString(COSName.INFO, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOutputCondition() {
/*  81 */     return this.dictionary.getString(COSName.OUTPUT_CONDITION);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOutputCondition(String value) {
/*  86 */     this.dictionary.setString(COSName.OUTPUT_CONDITION, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOutputConditionIdentifier() {
/*  91 */     return this.dictionary.getString(COSName.OUTPUT_CONDITION_IDENTIFIER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOutputConditionIdentifier(String value) {
/*  96 */     this.dictionary.setString(COSName.OUTPUT_CONDITION_IDENTIFIER, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRegistryName() {
/* 101 */     return this.dictionary.getString(COSName.REGISTRY_NAME);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRegistryName(String value) {
/* 106 */     this.dictionary.setString(COSName.REGISTRY_NAME, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private PDStream configureOutputProfile(PDDocument doc, InputStream colorProfile) throws IOException {
/* 112 */     ICC_Profile icc = ICC_Profile.getInstance(colorProfile);
/* 113 */     PDStream stream = new PDStream(doc, new ByteArrayInputStream(icc.getData()), COSName.FLATE_DECODE);
/* 114 */     stream.getCOSObject().setInt(COSName.N, icc.getNumComponents());
/* 115 */     return stream;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDOutputIntent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */