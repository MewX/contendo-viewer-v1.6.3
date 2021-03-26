/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
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
/*     */ public class PDFourColours
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSArray array;
/*     */   
/*     */   public PDFourColours() {
/*  37 */     this.array = new COSArray();
/*  38 */     this.array.add((COSBase)COSNull.NULL);
/*  39 */     this.array.add((COSBase)COSNull.NULL);
/*  40 */     this.array.add((COSBase)COSNull.NULL);
/*  41 */     this.array.add((COSBase)COSNull.NULL);
/*     */   }
/*     */ 
/*     */   
/*     */   public PDFourColours(COSArray array) {
/*  46 */     this.array = array;
/*     */     
/*  48 */     if (this.array.size() < 4)
/*     */     {
/*  50 */       for (int i = this.array.size() - 1; i < 4; i++)
/*     */       {
/*  52 */         this.array.add((COSBase)COSNull.NULL);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGamma getBeforeColour() {
/*  65 */     return getColourByIndex(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBeforeColour(PDGamma colour) {
/*  75 */     setColourByIndex(0, colour);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGamma getAfterColour() {
/*  85 */     return getColourByIndex(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAfterColour(PDGamma colour) {
/*  95 */     setColourByIndex(1, colour);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGamma getStartColour() {
/* 105 */     return getColourByIndex(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartColour(PDGamma colour) {
/* 115 */     setColourByIndex(2, colour);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGamma getEndColour() {
/* 125 */     return getColourByIndex(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndColour(PDGamma colour) {
/* 135 */     setColourByIndex(3, colour);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 145 */     return (COSBase)this.array;
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
/*     */   private PDGamma getColourByIndex(int index) {
/* 157 */     PDGamma retval = null;
/* 158 */     COSBase item = this.array.getObject(index);
/* 159 */     if (item instanceof COSArray)
/*     */     {
/* 161 */       retval = new PDGamma((COSArray)item);
/*     */     }
/* 163 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setColourByIndex(int index, PDGamma colour) {
/*     */     COSArray cOSArray;
/* 175 */     if (colour == null) {
/*     */       
/* 177 */       COSNull cOSNull = COSNull.NULL;
/*     */     }
/*     */     else {
/*     */       
/* 181 */       cOSArray = colour.getCOSArray();
/*     */     } 
/* 183 */     this.array.set(index, (COSBase)cOSArray);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDFourColours.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */