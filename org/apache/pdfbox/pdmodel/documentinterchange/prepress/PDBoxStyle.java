/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.prepress;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
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
/*     */ 
/*     */ public class PDBoxStyle
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String GUIDELINE_STYLE_SOLID = "S";
/*     */   public static final String GUIDELINE_STYLE_DASHED = "D";
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDBoxStyle() {
/*  52 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBoxStyle(COSDictionary dic) {
/*  62 */     this.dictionary = dic;
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
/*  73 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getGuidelineColor() {
/*  84 */     COSArray colorValues = (COSArray)this.dictionary.getDictionaryObject(COSName.C);
/*  85 */     if (colorValues == null) {
/*     */       
/*  87 */       colorValues = new COSArray();
/*  88 */       colorValues.add((COSBase)COSInteger.ZERO);
/*  89 */       colorValues.add((COSBase)COSInteger.ZERO);
/*  90 */       colorValues.add((COSBase)COSInteger.ZERO);
/*  91 */       this.dictionary.setItem(COSName.C, (COSBase)colorValues);
/*     */     } 
/*  93 */     PDColor color = new PDColor(colorValues.toFloatArray(), (PDColorSpace)PDDeviceRGB.INSTANCE);
/*  94 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGuideLineColor(PDColor color) {
/* 105 */     COSArray values = null;
/* 106 */     if (color != null)
/*     */     {
/* 108 */       values = color.toCOSArray();
/*     */     }
/* 110 */     this.dictionary.setItem(COSName.C, (COSBase)values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getGuidelineWidth() {
/* 121 */     return this.dictionary.getFloat(COSName.W, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGuidelineWidth(float width) {
/* 131 */     this.dictionary.setFloat(COSName.W, width);
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
/*     */   public String getGuidelineStyle() {
/* 143 */     return this.dictionary.getNameAsString(COSName.S, "S");
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
/*     */   public void setGuidelineStyle(String style) {
/* 155 */     this.dictionary.setName(COSName.S, style);
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
/*     */   public PDLineDashPattern getLineDashPattern() {
/* 167 */     COSArray d = (COSArray)this.dictionary.getDictionaryObject(COSName.D);
/* 168 */     if (d == null) {
/*     */       
/* 170 */       d = new COSArray();
/* 171 */       d.add((COSBase)COSInteger.THREE);
/* 172 */       this.dictionary.setItem(COSName.D, (COSBase)d);
/*     */     } 
/* 174 */     COSArray lineArray = new COSArray();
/* 175 */     lineArray.add((COSBase)d);
/*     */     
/* 177 */     PDLineDashPattern pattern = new PDLineDashPattern(lineArray, 0);
/* 178 */     return pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineDashPattern(COSArray dashArray) {
/* 188 */     COSArray array = null;
/* 189 */     if (dashArray != null)
/*     */     {
/* 191 */       array = dashArray;
/*     */     }
/* 193 */     this.dictionary.setItem(COSName.D, (COSBase)array);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/prepress/PDBoxStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */