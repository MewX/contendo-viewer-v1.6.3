/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public final class PDCalGray
/*     */   extends PDCIEDictionaryBasedColorSpace
/*     */ {
/*  35 */   private final PDColor initialColor = new PDColor(new float[] { 0.0F }, this);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private final Map<Float, float[]> map1 = (Map)new HashMap<Float, float>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCalGray() {
/*  47 */     super(COSName.CALGRAY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCalGray(COSArray array) {
/*  57 */     super(array);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  63 */     return COSName.CALGRAY.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/*  69 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/*  75 */     return new float[] { 0.0F, 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/*  81 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) {
/*  88 */     if (this.wpX == 1.0F && this.wpY == 1.0F && this.wpZ == 1.0F) {
/*     */       
/*  90 */       float a = value[0];
/*  91 */       float[] result = this.map1.get(Float.valueOf(a));
/*  92 */       if (result != null)
/*     */       {
/*  94 */         return (float[])result.clone();
/*     */       }
/*  96 */       float gamma = getGamma();
/*  97 */       float powAG = (float)Math.pow(a, gamma);
/*  98 */       result = convXYZtoRGB(powAG, powAG, powAG);
/*  99 */       this.map1.put(Float.valueOf(a), result.clone());
/* 100 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return new float[] { value[0], value[0], value[0] };
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
/*     */   public float getGamma() {
/* 116 */     float retval = 1.0F;
/* 117 */     COSNumber gamma = (COSNumber)this.dictionary.getDictionaryObject(COSName.GAMMA);
/* 118 */     if (gamma != null)
/*     */     {
/* 120 */       retval = gamma.floatValue();
/*     */     }
/* 122 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGamma(float value) {
/* 132 */     this.dictionary.setItem(COSName.GAMMA, (COSBase)new COSFloat(value));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDCalGray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */