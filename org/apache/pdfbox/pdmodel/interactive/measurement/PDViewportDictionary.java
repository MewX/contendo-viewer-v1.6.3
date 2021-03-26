/*     */ package org.apache.pdfbox.pdmodel.interactive.measurement;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
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
/*     */ public class PDViewportDictionary
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String TYPE = "Viewport";
/*     */   private final COSDictionary viewportDictionary;
/*     */   
/*     */   public PDViewportDictionary() {
/*  45 */     this.viewportDictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDViewportDictionary(COSDictionary dictionary) {
/*  55 */     this.viewportDictionary = dictionary;
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
/*  66 */     return this.viewportDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  77 */     return "Viewport";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getBBox() {
/*  87 */     COSBase bbox = getCOSObject().getDictionaryObject(COSName.BBOX);
/*  88 */     if (bbox instanceof COSArray)
/*     */     {
/*  90 */       return new PDRectangle((COSArray)bbox);
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBBox(PDRectangle rectangle) {
/* 102 */     getCOSObject().setItem(COSName.BBOX, (COSObjectable)rectangle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 112 */     return getCOSObject().getNameAsString(COSName.NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 122 */     getCOSObject().setName(COSName.NAME, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDMeasureDictionary getMeasure() {
/* 132 */     COSBase base = getCOSObject().getDictionaryObject(COSName.MEASURE);
/* 133 */     if (base instanceof COSDictionary)
/*     */     {
/* 135 */       return new PDMeasureDictionary((COSDictionary)base);
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMeasure(PDMeasureDictionary measure) {
/* 147 */     getCOSObject().setItem(COSName.MEASURE, measure);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/measurement/PDViewportDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */