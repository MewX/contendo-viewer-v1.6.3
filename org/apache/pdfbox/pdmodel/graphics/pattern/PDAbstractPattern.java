/*     */ package org.apache.pdfbox.pdmodel.graphics.pattern;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.util.Matrix;
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
/*     */ public abstract class PDAbstractPattern
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final int TYPE_TILING_PATTERN = 1;
/*     */   public static final int TYPE_SHADING_PATTERN = 2;
/*     */   private final COSDictionary patternDictionary;
/*     */   
/*     */   public static PDAbstractPattern create(COSDictionary dictionary) throws IOException {
/*     */     PDAbstractPattern pattern;
/*  49 */     int patternType = dictionary.getInt(COSName.PATTERN_TYPE, 0);
/*  50 */     switch (patternType) {
/*     */       
/*     */       case 1:
/*  53 */         pattern = new PDTilingPattern(dictionary);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  61 */         return pattern;case 2: pattern = new PDShadingPattern(dictionary); return pattern;
/*     */     } 
/*     */     throw new IOException("Error: Unknown pattern type " + patternType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAbstractPattern() {
/*  71 */     this.patternDictionary = new COSDictionary();
/*  72 */     this.patternDictionary.setName(COSName.TYPE, COSName.PATTERN.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAbstractPattern(COSDictionary dictionary) {
/*  81 */     this.patternDictionary = dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  91 */     return this.patternDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaintType(int paintType) {
/* 100 */     this.patternDictionary.setInt(COSName.PAINT_TYPE, paintType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 109 */     return COSName.PATTERN.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPatternType(int patternType) {
/* 118 */     this.patternDictionary.setInt(COSName.PATTERN_TYPE, patternType);
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
/*     */   public Matrix getMatrix() {
/* 132 */     return Matrix.createMatrix(getCOSObject().getDictionaryObject(COSName.MATRIX));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMatrix(AffineTransform transform) {
/* 141 */     COSArray matrix = new COSArray();
/* 142 */     double[] values = new double[6];
/* 143 */     transform.getMatrix(values);
/* 144 */     for (double v : values)
/*     */     {
/* 146 */       matrix.add((COSBase)new COSFloat((float)v));
/*     */     }
/* 148 */     getCOSObject().setItem(COSName.MATRIX, (COSBase)matrix);
/*     */   }
/*     */   
/*     */   public abstract int getPatternType();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/pattern/PDAbstractPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */