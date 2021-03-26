/*     */ package org.apache.pdfbox.pdmodel.common.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public class PDFunctionType2
/*     */   extends PDFunction
/*     */ {
/*     */   private final COSArray c0;
/*     */   private final COSArray c1;
/*     */   private final float exponent;
/*     */   
/*     */   public PDFunctionType2(COSBase function) {
/*  55 */     super(function);
/*     */     
/*  57 */     if (getCOSObject().getDictionaryObject(COSName.C0) instanceof COSArray) {
/*     */       
/*  59 */       this.c0 = (COSArray)getCOSObject().getDictionaryObject(COSName.C0);
/*     */     }
/*     */     else {
/*     */       
/*  63 */       this.c0 = new COSArray();
/*     */     } 
/*  65 */     if (this.c0.size() == 0)
/*     */     {
/*  67 */       this.c0.add((COSBase)new COSFloat(0.0F));
/*     */     }
/*     */     
/*  70 */     if (getCOSObject().getDictionaryObject(COSName.C1) instanceof COSArray) {
/*     */       
/*  72 */       this.c1 = (COSArray)getCOSObject().getDictionaryObject(COSName.C1);
/*     */     }
/*     */     else {
/*     */       
/*  76 */       this.c1 = new COSArray();
/*     */     } 
/*  78 */     if (this.c1.size() == 0)
/*     */     {
/*  80 */       this.c1.add((COSBase)new COSFloat(1.0F));
/*     */     }
/*     */     
/*  83 */     this.exponent = getCOSObject().getFloat(COSName.N);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFunctionType() {
/*  92 */     return 2;
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
/*     */   public float[] eval(float[] input) throws IOException {
/* 104 */     float xToN = (float)Math.pow(input[0], this.exponent);
/*     */     
/* 106 */     float[] result = new float[Math.min(this.c0.size(), this.c1.size())];
/* 107 */     for (int j = 0; j < result.length; j++) {
/*     */       
/* 109 */       float c0j = ((COSNumber)this.c0.get(j)).floatValue();
/* 110 */       float c1j = ((COSNumber)this.c1.get(j)).floatValue();
/* 111 */       result[j] = c0j + xToN * (c1j - c0j);
/*     */     } 
/*     */     
/* 114 */     return clipToRange(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getC0() {
/* 124 */     return this.c0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getC1() {
/* 134 */     return this.c1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getN() {
/* 144 */     return this.exponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 153 */     return "FunctionType2{C0: " + 
/* 154 */       getC0() + " C1: " + 
/* 155 */       getC1() + " N: " + 
/* 156 */       getN() + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/PDFunctionType2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */