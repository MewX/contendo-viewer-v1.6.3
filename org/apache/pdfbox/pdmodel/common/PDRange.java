/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSNumber;
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
/*     */ public class PDRange
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSArray rangeArray;
/*     */   private int startingIndex;
/*     */   
/*     */   public PDRange() {
/*  39 */     this.rangeArray = new COSArray();
/*  40 */     this.rangeArray.add((COSBase)new COSFloat(0.0F));
/*  41 */     this.rangeArray.add((COSBase)new COSFloat(1.0F));
/*  42 */     this.startingIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRange(COSArray range) {
/*  52 */     this.rangeArray = range;
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
/*     */   public PDRange(COSArray range, int index) {
/*  66 */     this.rangeArray = range;
/*  67 */     this.startingIndex = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  78 */     return (COSBase)this.rangeArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getCOSArray() {
/*  88 */     return this.rangeArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMin() {
/*  98 */     COSNumber min = (COSNumber)this.rangeArray.getObject(this.startingIndex * 2);
/*  99 */     return min.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMin(float min) {
/* 109 */     this.rangeArray.set(this.startingIndex * 2, (COSBase)new COSFloat(min));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMax() {
/* 119 */     COSNumber max = (COSNumber)this.rangeArray.getObject(this.startingIndex * 2 + 1);
/* 120 */     return max.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMax(float max) {
/* 130 */     this.rangeArray.set(this.startingIndex * 2 + 1, (COSBase)new COSFloat(max));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 139 */     return "PDRange{" + getMin() + ", " + getMax() + '}';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */