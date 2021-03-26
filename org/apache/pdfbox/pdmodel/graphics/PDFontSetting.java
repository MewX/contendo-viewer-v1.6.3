/*     */ package org.apache.pdfbox.pdmodel.graphics;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFontFactory;
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
/*     */ public class PDFontSetting
/*     */   implements COSObjectable
/*     */ {
/*  40 */   private COSArray fontSetting = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontSetting() {
/*  47 */     this.fontSetting = new COSArray();
/*  48 */     this.fontSetting.add(null);
/*  49 */     this.fontSetting.add((COSBase)new COSFloat(1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFontSetting(COSArray fs) {
/*  59 */     this.fontSetting = fs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  68 */     return (COSBase)this.fontSetting;
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
/*     */   public PDFont getFont() throws IOException {
/*  80 */     PDFont retval = null;
/*  81 */     COSBase font = this.fontSetting.getObject(0);
/*  82 */     if (font instanceof COSDictionary)
/*     */     {
/*  84 */       retval = PDFontFactory.createFont((COSDictionary)font);
/*     */     }
/*  86 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(PDFont font) {
/*  96 */     this.fontSetting.set(0, (COSObjectable)font);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFontSize() {
/* 106 */     COSNumber size = (COSNumber)this.fontSetting.get(1);
/* 107 */     return size.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontSize(float size) {
/* 117 */     this.fontSetting.set(1, (COSBase)new COSFloat(size));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/PDFontSetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */