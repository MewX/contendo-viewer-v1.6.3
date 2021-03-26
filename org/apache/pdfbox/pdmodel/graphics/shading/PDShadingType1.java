/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public class PDShadingType1
/*     */   extends PDShading
/*     */ {
/*  33 */   private COSArray domain = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShadingType1(COSDictionary shadingDictionary) {
/*  42 */     super(shadingDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShadingType() {
/*  48 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getMatrix() {
/*  58 */     return Matrix.createMatrix(getCOSObject().getDictionaryObject(COSName.MATRIX));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMatrix(AffineTransform transform) {
/*  68 */     COSArray matrix = new COSArray();
/*  69 */     double[] values = new double[6];
/*  70 */     transform.getMatrix(values);
/*  71 */     for (double v : values)
/*     */     {
/*  73 */       matrix.add((COSBase)new COSFloat((float)v));
/*     */     }
/*  75 */     getCOSObject().setItem(COSName.MATRIX, (COSBase)matrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getDomain() {
/*  85 */     if (this.domain == null)
/*     */     {
/*  87 */       this.domain = (COSArray)getCOSObject().getDictionaryObject(COSName.DOMAIN);
/*     */     }
/*  89 */     return this.domain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDomain(COSArray newDomain) {
/*  99 */     this.domain = newDomain;
/* 100 */     getCOSObject().setItem(COSName.DOMAIN, (COSBase)newDomain);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint toPaint(Matrix matrix) {
/* 106 */     return new Type1ShadingPaint(this, matrix);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShadingType1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */