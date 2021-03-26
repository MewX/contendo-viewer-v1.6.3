/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public class PDVisibleSigProperties
/*     */ {
/*     */   private String signerName;
/*     */   private String signerLocation;
/*     */   private String signatureReason;
/*     */   private boolean visualSignEnabled;
/*     */   private int page;
/*     */   private int preferredSize;
/*     */   private InputStream visibleSignature;
/*     */   private PDVisibleSignDesigner pdVisibleSignature;
/*     */   
/*     */   public void buildSignature() throws IOException {
/*  46 */     PDFTemplateBuilder builder = new PDVisibleSigBuilder();
/*  47 */     PDFTemplateCreator creator = new PDFTemplateCreator(builder);
/*  48 */     setVisibleSignature(creator.buildPDF(getPdVisibleSignature()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignerName() {
/*  57 */     return this.signerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties signerName(String signerName) {
/*  67 */     this.signerName = signerName;
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignerLocation() {
/*  77 */     return this.signerLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties signerLocation(String signerLocation) {
/*  87 */     this.signerLocation = signerLocation;
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignatureReason() {
/*  97 */     return this.signatureReason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties signatureReason(String signatureReason) {
/* 107 */     this.signatureReason = signatureReason;
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPage() {
/* 117 */     return this.page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties page(int page) {
/* 127 */     this.page = page;
/* 128 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredSize() {
/* 138 */     return this.preferredSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties preferredSize(int preferredSize) {
/* 149 */     this.preferredSize = preferredSize;
/* 150 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVisualSignEnabled() {
/* 159 */     return this.visualSignEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties visualSignEnabled(boolean visualSignEnabled) {
/* 169 */     this.visualSignEnabled = visualSignEnabled;
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner getPdVisibleSignature() {
/* 179 */     return this.pdVisibleSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSigProperties setPdVisibleSignature(PDVisibleSignDesigner pdVisibleSignature) {
/* 189 */     this.pdVisibleSignature = pdVisibleSignature;
/* 190 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getVisibleSignature() {
/* 199 */     return this.visibleSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibleSignature(InputStream visibleSignature) {
/* 208 */     this.visibleSignature = visibleSignature;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/visible/PDVisibleSigProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */