/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDFIconFit
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSDictionary fit;
/*     */   public static final String SCALE_OPTION_ALWAYS = "A";
/*     */   public static final String SCALE_OPTION_ONLY_WHEN_ICON_IS_BIGGER = "B";
/*     */   public static final String SCALE_OPTION_ONLY_WHEN_ICON_IS_SMALLER = "S";
/*     */   public static final String SCALE_OPTION_NEVER = "N";
/*     */   public static final String SCALE_TYPE_ANAMORPHIC = "A";
/*     */   public static final String SCALE_TYPE_PROPORTIONAL = "P";
/*     */   
/*     */   public FDFIconFit() {
/*  65 */     this.fit = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFIconFit(COSDictionary f) {
/*  75 */     this.fit = f;
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
/*  86 */     return this.fit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScaleOption() {
/*  97 */     String retval = this.fit.getNameAsString(COSName.SW);
/*  98 */     if (retval == null)
/*     */     {
/* 100 */       retval = "A";
/*     */     }
/* 102 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleOption(String option) {
/* 112 */     this.fit.setName(COSName.SW, option);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScaleType() {
/* 123 */     String retval = this.fit.getNameAsString(COSName.S);
/* 124 */     if (retval == null)
/*     */     {
/* 126 */       retval = "P";
/*     */     }
/* 128 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleType(String scale) {
/* 138 */     this.fit.setName(COSName.S, scale);
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
/*     */   public PDRange getFractionalSpaceToAllocate() {
/* 153 */     PDRange retval = null;
/* 154 */     COSArray array = (COSArray)this.fit.getDictionaryObject(COSName.A);
/* 155 */     if (array == null) {
/*     */       
/* 157 */       retval = new PDRange();
/* 158 */       retval.setMin(0.5F);
/* 159 */       retval.setMax(0.5F);
/* 160 */       setFractionalSpaceToAllocate(retval);
/*     */     }
/*     */     else {
/*     */       
/* 164 */       retval = new PDRange(array);
/*     */     } 
/* 166 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFractionalSpaceToAllocate(PDRange space) {
/* 176 */     this.fit.setItem(COSName.A, (COSObjectable)space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldScaleToFitAnnotation() {
/* 186 */     return this.fit.getBoolean(COSName.FB, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleToFitAnnotation(boolean value) {
/* 196 */     this.fit.setBoolean(COSName.FB, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFIconFit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */