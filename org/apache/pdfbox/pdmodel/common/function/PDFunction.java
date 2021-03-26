/*     */ package org.apache.pdfbox.pdmodel.common.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ public abstract class PDFunction
/*     */   implements COSObjectable
/*     */ {
/*  40 */   private PDStream functionStream = null;
/*  41 */   private COSDictionary functionDictionary = null;
/*  42 */   private COSArray domain = null;
/*  43 */   private COSArray range = null;
/*  44 */   private int numberOfInputValues = -1;
/*  45 */   private int numberOfOutputValues = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunction(COSBase function) {
/*  55 */     if (function instanceof COSStream) {
/*     */       
/*  57 */       this.functionStream = new PDStream((COSStream)function);
/*  58 */       this.functionStream.getCOSObject().setItem(COSName.TYPE, (COSBase)COSName.FUNCTION);
/*     */     }
/*  60 */     else if (function instanceof COSDictionary) {
/*     */       
/*  62 */       this.functionDictionary = (COSDictionary)function;
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
/*     */   public COSDictionary getCOSObject() {
/*  87 */     if (this.functionStream != null)
/*     */     {
/*  89 */       return (COSDictionary)this.functionStream.getCOSObject();
/*     */     }
/*     */ 
/*     */     
/*  93 */     return this.functionDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDStream getPDStream() {
/* 103 */     return this.functionStream;
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
/*     */   public static PDFunction create(COSBase function) throws IOException {
/* 116 */     if (function == COSName.IDENTITY)
/*     */     {
/* 118 */       return new PDFunctionTypeIdentity(null);
/*     */     }
/*     */     
/* 121 */     COSBase base = function;
/* 122 */     if (function instanceof COSObject)
/*     */     {
/* 124 */       base = ((COSObject)function).getObject();
/*     */     }
/* 126 */     if (!(base instanceof COSDictionary))
/*     */     {
/* 128 */       throw new IOException("Error: Function must be a Dictionary, but is " + base
/* 129 */           .getClass().getSimpleName());
/*     */     }
/* 131 */     COSDictionary functionDictionary = (COSDictionary)base;
/* 132 */     int functionType = functionDictionary.getInt(COSName.FUNCTION_TYPE);
/* 133 */     switch (functionType) {
/*     */       
/*     */       case 0:
/* 136 */         return new PDFunctionType0((COSBase)functionDictionary);
/*     */       case 2:
/* 138 */         return new PDFunctionType2((COSBase)functionDictionary);
/*     */       case 3:
/* 140 */         return new PDFunctionType3((COSBase)functionDictionary);
/*     */       case 4:
/* 142 */         return new PDFunctionType4((COSBase)functionDictionary);
/*     */     } 
/* 144 */     throw new IOException("Error: Unknown function type " + functionType);
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
/*     */   public int getNumberOfOutputParameters() {
/* 160 */     if (this.numberOfOutputValues == -1) {
/*     */       
/* 162 */       COSArray rangeValues = getRangeValues();
/* 163 */       this.numberOfOutputValues = rangeValues.size() / 2;
/*     */     } 
/* 165 */     return this.numberOfOutputValues;
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
/*     */   public PDRange getRangeForOutput(int n) {
/* 179 */     COSArray rangeValues = getRangeValues();
/* 180 */     return new PDRange(rangeValues, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRangeValues(COSArray rangeValues) {
/* 190 */     this.range = rangeValues;
/* 191 */     getCOSObject().setItem(COSName.RANGE, (COSBase)rangeValues);
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
/*     */   public int getNumberOfInputParameters() {
/* 203 */     if (this.numberOfInputValues == -1) {
/*     */       
/* 205 */       COSArray array = getDomainValues();
/* 206 */       this.numberOfInputValues = array.size() / 2;
/*     */     } 
/* 208 */     return this.numberOfInputValues;
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
/*     */   public PDRange getDomainForInput(int n) {
/* 222 */     COSArray domainValues = getDomainValues();
/* 223 */     return new PDRange(domainValues, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDomainValues(COSArray domainValues) {
/* 233 */     this.domain = domainValues;
/* 234 */     getCOSObject().setItem(COSName.DOMAIN, (COSBase)domainValues);
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
/*     */   @Deprecated
/*     */   public COSArray eval(COSArray input) throws IOException {
/* 248 */     float[] outputValues = eval(input.toFloatArray());
/* 249 */     COSArray array = new COSArray();
/* 250 */     array.setFloatArray(outputValues);
/* 251 */     return array;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected COSArray getRangeValues() {
/* 275 */     if (this.range == null)
/*     */     {
/* 277 */       this.range = (COSArray)getCOSObject().getDictionaryObject(COSName.RANGE);
/*     */     }
/* 279 */     return this.range;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSArray getDomainValues() {
/* 289 */     if (this.domain == null)
/*     */     {
/* 291 */       this.domain = (COSArray)getCOSObject().getDictionaryObject(COSName.DOMAIN);
/*     */     }
/* 293 */     return this.domain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float[] clipToRange(float[] inputValues) {
/*     */     float[] result;
/* 304 */     COSArray rangesArray = getRangeValues();
/*     */     
/* 306 */     if (rangesArray != null) {
/*     */       
/* 308 */       float[] rangeValues = rangesArray.toFloatArray();
/* 309 */       int numberOfRanges = rangeValues.length / 2;
/* 310 */       result = new float[numberOfRanges];
/* 311 */       for (int i = 0; i < numberOfRanges; i++)
/*     */       {
/* 313 */         int index = i << 1;
/* 314 */         result[i] = clipToRange(inputValues[i], rangeValues[index], rangeValues[index + 1]);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 319 */       result = inputValues;
/*     */     } 
/* 321 */     return result;
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
/*     */   protected float clipToRange(float x, float rangeMin, float rangeMax) {
/* 335 */     if (x < rangeMin)
/*     */     {
/* 337 */       return rangeMin;
/*     */     }
/* 339 */     if (x > rangeMax)
/*     */     {
/* 341 */       return rangeMax;
/*     */     }
/* 343 */     return x;
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
/*     */   protected float interpolate(float x, float xRangeMin, float xRangeMax, float yRangeMin, float yRangeMax) {
/* 360 */     return yRangeMin + (x - xRangeMin) * (yRangeMax - yRangeMin) / (xRangeMax - xRangeMin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 369 */     return "FunctionType" + getFunctionType();
/*     */   }
/*     */   
/*     */   public abstract int getFunctionType();
/*     */   
/*     */   public abstract float[] eval(float[] paramArrayOffloat) throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/PDFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */