/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.function.PDFunction;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
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
/*     */ public abstract class PDShading
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*  38 */   private COSArray background = null;
/*  39 */   private PDRectangle bBox = null;
/*  40 */   private PDColorSpace colorSpace = null;
/*  41 */   private PDFunction function = null;
/*  42 */   private PDFunction[] functionArray = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE1 = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE2 = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE3 = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE4 = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE5 = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE6 = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADING_TYPE7 = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShading() {
/*  84 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDShading(COSDictionary shadingDictionary) {
/*  94 */     this.dictionary = shadingDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 105 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 115 */     return COSName.SHADING.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShadingType(int shadingType) {
/* 125 */     this.dictionary.setInt(COSName.SHADING_TYPE, shadingType);
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
/*     */   public void setBackground(COSArray newBackground) {
/* 142 */     this.background = newBackground;
/* 143 */     this.dictionary.setItem(COSName.BACKGROUND, (COSBase)newBackground);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getBackground() {
/* 153 */     if (this.background == null)
/*     */     {
/* 155 */       this.background = (COSArray)this.dictionary.getDictionaryObject(COSName.BACKGROUND);
/*     */     }
/* 157 */     return this.background;
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
/*     */   public PDRectangle getBBox() {
/* 169 */     if (this.bBox == null) {
/*     */       
/* 171 */       COSArray array = (COSArray)this.dictionary.getDictionaryObject(COSName.BBOX);
/* 172 */       if (array != null)
/*     */       {
/* 174 */         this.bBox = new PDRectangle(array);
/*     */       }
/*     */     } 
/* 177 */     return this.bBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBBox(PDRectangle newBBox) {
/* 187 */     this.bBox = newBBox;
/* 188 */     if (this.bBox == null) {
/*     */       
/* 190 */       this.dictionary.removeItem(COSName.BBOX);
/*     */     }
/*     */     else {
/*     */       
/* 194 */       this.dictionary.setItem(COSName.BBOX, (COSBase)this.bBox.getCOSArray());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAntiAlias(boolean antiAlias) {
/* 205 */     this.dictionary.setBoolean(COSName.ANTI_ALIAS, antiAlias);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAntiAlias() {
/* 215 */     return this.dictionary.getBoolean(COSName.ANTI_ALIAS, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace() throws IOException {
/* 226 */     if (this.colorSpace == null) {
/*     */       
/* 228 */       COSBase colorSpaceDictionary = this.dictionary.getDictionaryObject(COSName.CS, COSName.COLORSPACE);
/* 229 */       this.colorSpace = PDColorSpace.create(colorSpaceDictionary);
/*     */     } 
/* 231 */     return this.colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorSpace(PDColorSpace colorSpace) {
/* 241 */     this.colorSpace = colorSpace;
/* 242 */     if (colorSpace != null) {
/*     */       
/* 244 */       this.dictionary.setItem(COSName.COLORSPACE, colorSpace.getCOSObject());
/*     */     }
/*     */     else {
/*     */       
/* 248 */       this.dictionary.removeItem(COSName.COLORSPACE);
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
/*     */   public static PDShading create(COSDictionary shadingDictionary) throws IOException {
/* 261 */     PDShading shading = null;
/* 262 */     int shadingType = shadingDictionary.getInt(COSName.SHADING_TYPE, 0);
/* 263 */     switch (shadingType) {
/*     */       
/*     */       case 1:
/* 266 */         shading = new PDShadingType1(shadingDictionary);
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
/* 289 */         return shading;case 2: shading = new PDShadingType2(shadingDictionary); return shading;case 3: shading = new PDShadingType3(shadingDictionary); return shading;case 4: shading = new PDShadingType4(shadingDictionary); return shading;case 5: shading = new PDShadingType5(shadingDictionary); return shading;case 6: shading = new PDShadingType6(shadingDictionary); return shading;case 7: shading = new PDShadingType7(shadingDictionary); return shading;
/*     */     } 
/*     */     throw new IOException("Error: Unknown shading type " + shadingType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunction(PDFunction newFunction) {
/* 299 */     this.functionArray = null;
/* 300 */     this.function = newFunction;
/* 301 */     getCOSObject().setItem(COSName.FUNCTION, (COSObjectable)newFunction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunction(COSArray newFunctions) {
/* 311 */     this.functionArray = null;
/* 312 */     this.function = null;
/* 313 */     getCOSObject().setItem(COSName.FUNCTION, (COSBase)newFunctions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunction getFunction() throws IOException {
/* 324 */     if (this.function == null) {
/*     */       
/* 326 */       COSBase dictionaryFunctionObject = getCOSObject().getDictionaryObject(COSName.FUNCTION);
/* 327 */       if (dictionaryFunctionObject != null)
/*     */       {
/* 329 */         this.function = PDFunction.create(dictionaryFunctionObject);
/*     */       }
/*     */     } 
/* 332 */     return this.function;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDFunction[] getFunctionsArray() throws IOException {
/* 343 */     if (this.functionArray == null) {
/*     */       
/* 345 */       COSBase functionObject = getCOSObject().getDictionaryObject(COSName.FUNCTION);
/* 346 */       if (functionObject instanceof COSDictionary) {
/*     */         
/* 348 */         this.functionArray = new PDFunction[1];
/* 349 */         this.functionArray[0] = PDFunction.create(functionObject);
/*     */       }
/* 351 */       else if (functionObject instanceof COSArray) {
/*     */         
/* 353 */         COSArray functionCOSArray = (COSArray)functionObject;
/* 354 */         int numberOfFunctions = functionCOSArray.size();
/* 355 */         this.functionArray = new PDFunction[numberOfFunctions];
/* 356 */         for (int i = 0; i < numberOfFunctions; i++)
/*     */         {
/* 358 */           this.functionArray[i] = PDFunction.create(functionCOSArray.get(i));
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 363 */         throw new IOException("mandatory /Function element must be a dictionary or an array");
/*     */       } 
/*     */     } 
/* 366 */     return this.functionArray;
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
/*     */   public float[] evalFunction(float inputValue) throws IOException {
/* 378 */     return evalFunction(new float[] { inputValue });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] evalFunction(float[] input) throws IOException {
/*     */     float[] returnValues;
/* 390 */     PDFunction[] functions = getFunctionsArray();
/* 391 */     int numberOfFunctions = functions.length;
/*     */     
/* 393 */     if (numberOfFunctions == 1) {
/*     */       
/* 395 */       returnValues = functions[0].eval(input);
/*     */     }
/*     */     else {
/*     */       
/* 399 */       returnValues = new float[numberOfFunctions];
/* 400 */       for (int j = 0; j < numberOfFunctions; j++) {
/*     */         
/* 402 */         float[] newValue = functions[j].eval(input);
/* 403 */         returnValues[j] = newValue[0];
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 409 */     for (int i = 0; i < returnValues.length; i++) {
/*     */       
/* 411 */       if (returnValues[i] < 0.0F) {
/*     */         
/* 413 */         returnValues[i] = 0.0F;
/*     */       }
/* 415 */       else if (returnValues[i] > 1.0F) {
/*     */         
/* 417 */         returnValues[i] = 1.0F;
/*     */       } 
/*     */     } 
/* 420 */     return returnValues;
/*     */   }
/*     */   
/*     */   public abstract int getShadingType();
/*     */   
/*     */   public abstract Paint toPaint(Matrix paramMatrix);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShading.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */