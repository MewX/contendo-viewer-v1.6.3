/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
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
/*     */ public class PDBorderStyleDictionary
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String STYLE_SOLID = "S";
/*     */   public static final String STYLE_DASHED = "D";
/*     */   public static final String STYLE_BEVELED = "B";
/*     */   public static final String STYLE_INSET = "I";
/*     */   public static final String STYLE_UNDERLINE = "U";
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDBorderStyleDictionary() {
/*  71 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderStyleDictionary(COSDictionary dict) {
/*  81 */     this.dictionary = dict;
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
/*  92 */     return this.dictionary;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidth(float w) {
/* 109 */     if (w == (int)w) {
/*     */       
/* 111 */       getCOSObject().setInt(COSName.W, (int)w);
/*     */     }
/*     */     else {
/*     */       
/* 115 */       getCOSObject().setFloat(COSName.W, w);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth() {
/* 126 */     if (getCOSObject().getDictionaryObject(COSName.W) instanceof COSName)
/*     */     {
/*     */ 
/*     */       
/* 130 */       return 0.0F;
/*     */     }
/* 132 */     return getCOSObject().getFloat(COSName.W, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStyle(String s) {
/* 142 */     getCOSObject().setName(COSName.S, s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStyle() {
/* 152 */     return getCOSObject().getNameAsString(COSName.S, "S");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDashStyle(COSArray dashArray) {
/* 162 */     COSArray array = null;
/* 163 */     if (dashArray != null)
/*     */     {
/* 165 */       array = dashArray;
/*     */     }
/* 167 */     getCOSObject().setItem(COSName.D, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDLineDashPattern getDashStyle() {
/* 177 */     COSArray d = (COSArray)getCOSObject().getDictionaryObject(COSName.D);
/* 178 */     if (d == null) {
/*     */       
/* 180 */       d = new COSArray();
/* 181 */       d.add((COSBase)COSInteger.THREE);
/* 182 */       getCOSObject().setItem(COSName.D, (COSBase)d);
/*     */     } 
/* 184 */     return new PDLineDashPattern(d, 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDBorderStyleDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */