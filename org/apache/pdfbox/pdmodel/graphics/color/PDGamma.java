/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*     */ public final class PDGamma
/*     */   implements COSObjectable
/*     */ {
/*  33 */   private COSArray values = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGamma() {
/*  41 */     this.values = new COSArray();
/*  42 */     this.values.add((COSBase)new COSFloat(0.0F));
/*  43 */     this.values.add((COSBase)new COSFloat(0.0F));
/*  44 */     this.values.add((COSBase)new COSFloat(0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGamma(COSArray array) {
/*  53 */     this.values = array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  62 */     return (COSBase)this.values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getCOSArray() {
/*  71 */     return this.values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getR() {
/*  80 */     return ((COSNumber)this.values.get(0)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setR(float r) {
/*  89 */     this.values.set(0, (COSBase)new COSFloat(r));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getG() {
/*  98 */     return ((COSNumber)this.values.get(1)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setG(float g) {
/* 107 */     this.values.set(1, (COSBase)new COSFloat(g));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getB() {
/* 116 */     return ((COSNumber)this.values.get(2)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setB(float b) {
/* 125 */     this.values.set(2, (COSBase)new COSFloat(b));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDGamma.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */