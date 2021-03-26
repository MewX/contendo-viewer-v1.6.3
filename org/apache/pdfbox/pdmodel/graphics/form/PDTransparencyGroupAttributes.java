/*     */ package org.apache.pdfbox.pdmodel.graphics.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
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
/*     */ public final class PDTransparencyGroupAttributes
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   private PDColorSpace colorSpace;
/*     */   
/*     */   public PDTransparencyGroupAttributes() {
/*  42 */     this.dictionary = new COSDictionary();
/*  43 */     this.dictionary.setItem(COSName.S, (COSBase)COSName.TRANSPARENCY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTransparencyGroupAttributes(COSDictionary dic) {
/*  52 */     this.dictionary = dic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  58 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace() throws IOException {
/*  69 */     return getColorSpace(null);
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
/*     */   public PDColorSpace getColorSpace(PDResources resources) throws IOException {
/*  81 */     if (this.colorSpace == null && getCOSObject().containsKey(COSName.CS))
/*     */     {
/*  83 */       this.colorSpace = PDColorSpace.create(getCOSObject().getDictionaryObject(COSName.CS), resources);
/*     */     }
/*  85 */     return this.colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsolated() {
/*  94 */     return getCOSObject().getBoolean(COSName.I, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKnockout() {
/* 103 */     return getCOSObject().getBoolean(COSName.K, false);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/form/PDTransparencyGroupAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */