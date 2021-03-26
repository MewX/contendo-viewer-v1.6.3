/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
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
/*     */ public class PDShadingType2
/*     */   extends PDShading
/*     */ {
/*  31 */   private COSArray coords = null;
/*  32 */   private COSArray domain = null;
/*  33 */   private COSArray extend = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShadingType2(COSDictionary shadingDictionary) {
/*  42 */     super(shadingDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShadingType() {
/*  48 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getExtend() {
/*  58 */     if (this.extend == null)
/*     */     {
/*  60 */       this.extend = (COSArray)getCOSObject().getDictionaryObject(COSName.EXTEND);
/*     */     }
/*  62 */     return this.extend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExtend(COSArray newExtend) {
/*  72 */     this.extend = newExtend;
/*  73 */     getCOSObject().setItem(COSName.EXTEND, (COSBase)newExtend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getDomain() {
/*  83 */     if (this.domain == null)
/*     */     {
/*  85 */       this.domain = (COSArray)getCOSObject().getDictionaryObject(COSName.DOMAIN);
/*     */     }
/*  87 */     return this.domain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDomain(COSArray newDomain) {
/*  97 */     this.domain = newDomain;
/*  98 */     getCOSObject().setItem(COSName.DOMAIN, (COSBase)newDomain);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getCoords() {
/* 108 */     if (this.coords == null)
/*     */     {
/* 110 */       this.coords = (COSArray)getCOSObject().getDictionaryObject(COSName.COORDS);
/*     */     }
/* 112 */     return this.coords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoords(COSArray newCoords) {
/* 122 */     this.coords = newCoords;
/* 123 */     getCOSObject().setItem(COSName.COORDS, (COSBase)newCoords);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint toPaint(Matrix matrix) {
/* 129 */     return new AxialShadingPaint(this, matrix);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShadingType2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */