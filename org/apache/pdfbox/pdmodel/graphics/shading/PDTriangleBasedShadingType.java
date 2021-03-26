/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
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
/*     */ abstract class PDTriangleBasedShadingType
/*     */   extends PDShading
/*     */ {
/*  31 */   private COSArray decode = null;
/*     */ 
/*     */   
/*     */   PDTriangleBasedShadingType(COSDictionary shadingDictionary) {
/*  35 */     super(shadingDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBitsPerComponent() {
/*  46 */     return getCOSObject().getInt(COSName.BITS_PER_COMPONENT, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBitsPerComponent(int bitsPerComponent) {
/*  56 */     getCOSObject().setInt(COSName.BITS_PER_COMPONENT, bitsPerComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBitsPerCoordinate() {
/*  67 */     return getCOSObject().getInt(COSName.BITS_PER_COORDINATE, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBitsPerCoordinate(int bitsPerComponent) {
/*  77 */     getCOSObject().setInt(COSName.BITS_PER_COORDINATE, bitsPerComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSArray getDecodeValues() {
/*  87 */     if (this.decode == null)
/*     */     {
/*  89 */       this.decode = (COSArray)getCOSObject().getDictionaryObject(COSName.DECODE);
/*     */     }
/*  91 */     return this.decode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecodeValues(COSArray decodeValues) {
/* 101 */     this.decode = decodeValues;
/* 102 */     getCOSObject().setItem(COSName.DECODE, (COSBase)decodeValues);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRange getDecodeForParameter(int paramNum) {
/* 113 */     PDRange retval = null;
/* 114 */     COSArray decodeValues = getDecodeValues();
/* 115 */     if (decodeValues != null && decodeValues.size() >= paramNum * 2 + 1)
/*     */     {
/* 117 */       retval = new PDRange(decodeValues, paramNum);
/*     */     }
/* 119 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDTriangleBasedShadingType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */