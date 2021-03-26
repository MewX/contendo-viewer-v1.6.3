/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
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
/*     */ public final class PDColor
/*     */ {
/*     */   private final float[] components;
/*     */   private final COSName patternName;
/*     */   private final PDColorSpace colorSpace;
/*     */   
/*     */   public PDColor(COSArray array, PDColorSpace colorSpace) {
/*  48 */     if (array.size() > 0 && array.get(array.size() - 1) instanceof COSName) {
/*     */ 
/*     */       
/*  51 */       this.components = new float[array.size() - 1];
/*  52 */       for (int i = 0; i < array.size() - 1; i++)
/*     */       {
/*  54 */         this.components[i] = ((COSNumber)array.get(i)).floatValue();
/*     */       }
/*     */ 
/*     */       
/*  58 */       this.patternName = (COSName)array.get(array.size() - 1);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  63 */       this.components = new float[array.size()];
/*  64 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/*  66 */         this.components[i] = ((COSNumber)array.get(i)).floatValue();
/*     */       }
/*  68 */       this.patternName = null;
/*     */     } 
/*  70 */     this.colorSpace = colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor(float[] components, PDColorSpace colorSpace) {
/*  80 */     this.components = (float[])components.clone();
/*  81 */     this.patternName = null;
/*  82 */     this.colorSpace = colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor(COSName patternName, PDColorSpace colorSpace) {
/*  92 */     this.components = new float[0];
/*  93 */     this.patternName = patternName;
/*  94 */     this.colorSpace = colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor(float[] components, COSName patternName, PDColorSpace colorSpace) {
/* 105 */     this.components = (float[])components.clone();
/* 106 */     this.patternName = patternName;
/* 107 */     this.colorSpace = colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getComponents() {
/* 116 */     if (this.colorSpace instanceof PDPattern || this.colorSpace == null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 121 */       return (float[])this.components.clone();
/*     */     }
/*     */     
/* 124 */     return Arrays.copyOf(this.components, this.colorSpace.getNumberOfComponents());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getPatternName() {
/* 133 */     return this.patternName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPattern() {
/* 142 */     return (this.patternName != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toRGB() throws IOException {
/* 153 */     float[] floats = this.colorSpace.toRGB(this.components);
/* 154 */     int r = Math.round(floats[0] * 255.0F);
/* 155 */     int g = Math.round(floats[1] * 255.0F);
/* 156 */     int b = Math.round(floats[2] * 255.0F);
/* 157 */     int rgb = r;
/* 158 */     rgb = (rgb << 8) + g;
/* 159 */     rgb = (rgb << 8) + b;
/* 160 */     return rgb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray toCOSArray() {
/* 169 */     COSArray array = new COSArray();
/* 170 */     array.setFloatArray(this.components);
/* 171 */     if (this.patternName != null)
/*     */     {
/* 173 */       array.add((COSBase)this.patternName);
/*     */     }
/* 175 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace() {
/* 183 */     return this.colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 189 */     return "PDColor{components=" + Arrays.toString(this.components) + ", patternName=" + this.patternName + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */