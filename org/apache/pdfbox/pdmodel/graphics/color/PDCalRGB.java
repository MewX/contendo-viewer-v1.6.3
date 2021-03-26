/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDCalRGB
/*     */   extends PDCIEDictionaryBasedColorSpace
/*     */ {
/*  34 */   private final PDColor initialColor = new PDColor(new float[] { 0.0F, 0.0F, 0.0F }, this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCalRGB() {
/*  41 */     super(COSName.CALRGB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCalRGB(COSArray rgb) {
/*  50 */     super(rgb);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  56 */     return COSName.CALRGB.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/*  62 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/*  68 */     return new float[] { 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/*  74 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) {
/*  80 */     if (this.wpX == 1.0F && this.wpY == 1.0F && this.wpZ == 1.0F) {
/*     */       
/*  82 */       float a = value[0];
/*  83 */       float b = value[1];
/*  84 */       float c = value[2];
/*     */       
/*  86 */       PDGamma gamma = getGamma();
/*  87 */       float powAR = (float)Math.pow(a, gamma.getR());
/*  88 */       float powBG = (float)Math.pow(b, gamma.getG());
/*  89 */       float powCB = (float)Math.pow(c, gamma.getB());
/*     */       
/*  91 */       float[] matrix = getMatrix();
/*  92 */       float mXA = matrix[0];
/*  93 */       float mYA = matrix[1];
/*  94 */       float mZA = matrix[2];
/*  95 */       float mXB = matrix[3];
/*  96 */       float mYB = matrix[4];
/*  97 */       float mZB = matrix[5];
/*  98 */       float mXC = matrix[6];
/*  99 */       float mYC = matrix[7];
/* 100 */       float mZC = matrix[8];
/*     */       
/* 102 */       float x = mXA * powAR + mXB * powBG + mXC * powCB;
/* 103 */       float y = mYA * powAR + mYB * powBG + mYC * powCB;
/* 104 */       float z = mZA * powAR + mZB * powBG + mZC * powCB;
/* 105 */       return convXYZtoRGB(x, y, z);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     return new float[] { value[0], value[1], value[2] };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final PDGamma getGamma() {
/* 123 */     COSArray gammaArray = (COSArray)this.dictionary.getDictionaryObject(COSName.GAMMA);
/* 124 */     if (gammaArray == null) {
/*     */       
/* 126 */       gammaArray = new COSArray();
/* 127 */       gammaArray.add((COSBase)new COSFloat(1.0F));
/* 128 */       gammaArray.add((COSBase)new COSFloat(1.0F));
/* 129 */       gammaArray.add((COSBase)new COSFloat(1.0F));
/* 130 */       this.dictionary.setItem(COSName.GAMMA, (COSBase)gammaArray);
/*     */     } 
/* 132 */     return new PDGamma(gammaArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float[] getMatrix() {
/* 142 */     COSArray matrix = (COSArray)this.dictionary.getDictionaryObject(COSName.MATRIX);
/* 143 */     if (matrix == null)
/*     */     {
/* 145 */       return new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*     */     }
/*     */ 
/*     */     
/* 149 */     return matrix.toFloatArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setGamma(PDGamma gamma) {
/* 159 */     COSArray gammaArray = null;
/* 160 */     if (gamma != null)
/*     */     {
/* 162 */       gammaArray = gamma.getCOSArray();
/*     */     }
/* 164 */     this.dictionary.setItem(COSName.GAMMA, (COSBase)gammaArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setMatrix(Matrix matrix) {
/* 174 */     COSArray matrixArray = null;
/* 175 */     if (matrix != null)
/*     */     {
/* 177 */       matrixArray = matrix.toCOSArray();
/*     */     }
/* 179 */     this.dictionary.setItem(COSName.MATRIX, (COSBase)matrixArray);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDCalRGB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */