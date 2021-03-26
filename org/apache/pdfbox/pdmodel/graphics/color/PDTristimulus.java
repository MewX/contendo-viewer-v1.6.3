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
/*     */ public final class PDTristimulus
/*     */   implements COSObjectable
/*     */ {
/*  33 */   private COSArray values = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTristimulus() {
/*  40 */     this.values = new COSArray();
/*  41 */     this.values.add((COSBase)new COSFloat(0.0F));
/*  42 */     this.values.add((COSBase)new COSFloat(0.0F));
/*  43 */     this.values.add((COSBase)new COSFloat(0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTristimulus(COSArray array) {
/*  52 */     this.values = array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTristimulus(float[] array) {
/*  61 */     this.values = new COSArray();
/*  62 */     for (int i = 0; i < array.length && i < 3; i++)
/*     */     {
/*  64 */       this.values.add((COSBase)new COSFloat(array[i]));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  74 */     return (COSBase)this.values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/*  83 */     return ((COSNumber)this.values.get(0)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(float x) {
/*  92 */     this.values.set(0, (COSBase)new COSFloat(x));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/* 101 */     return ((COSNumber)this.values.get(1)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setY(float y) {
/* 110 */     this.values.set(1, (COSBase)new COSFloat(y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getZ() {
/* 119 */     return ((COSNumber)this.values.get(2)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZ(float z) {
/* 128 */     this.values.set(2, (COSBase)new COSFloat(z));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDTristimulus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */