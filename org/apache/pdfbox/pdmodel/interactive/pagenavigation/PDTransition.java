/*     */ package org.apache.pdfbox.pdmodel.interactive.pagenavigation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;
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
/*     */ public final class PDTransition
/*     */   extends PDDictionaryWrapper
/*     */ {
/*     */   public PDTransition() {
/*  41 */     this(PDTransitionStyle.R);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTransition(PDTransitionStyle style) {
/*  52 */     getCOSObject().setName(COSName.TYPE, COSName.TRANS.getName());
/*  53 */     getCOSObject().setName(COSName.S, style.name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTransition(COSDictionary dictionary) {
/*  63 */     super(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStyle() {
/*  72 */     return getCOSObject().getNameAsString(COSName.S, PDTransitionStyle.R.name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDimension() {
/*  82 */     return getCOSObject().getNameAsString(COSName.DM, PDTransitionDimension.H.name());
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
/*     */   public void setDimension(PDTransitionDimension dimension) {
/*  94 */     getCOSObject().setName(COSName.DM, dimension.name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMotion() {
/* 104 */     return getCOSObject().getNameAsString(COSName.M, PDTransitionMotion.I.name());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMotion(PDTransitionMotion motion) {
/* 115 */     getCOSObject().setName(COSName.M, motion.name());
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
/*     */   public COSBase getDirection() {
/* 127 */     COSBase item = getCOSObject().getItem(COSName.DI);
/* 128 */     if (item == null)
/*     */     {
/* 130 */       return (COSBase)COSInteger.ZERO;
/*     */     }
/* 132 */     return item;
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
/*     */   public void setDirection(PDTransitionDirection direction) {
/* 144 */     getCOSObject().setItem(COSName.DI, direction.getCOSBase());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDuration() {
/* 152 */     return getCOSObject().getFloat(COSName.D, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDuration(float duration) {
/* 160 */     getCOSObject().setItem(COSName.D, (COSBase)new COSFloat(duration));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFlyScale() {
/* 169 */     return getCOSObject().getFloat(COSName.SS, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlyScale(float scale) {
/* 178 */     getCOSObject().setItem(COSName.SS, (COSBase)new COSFloat(scale));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFlyAreaOpaque() {
/* 187 */     return getCOSObject().getBoolean(COSName.B, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlyAreaOpaque(boolean opaque) {
/* 196 */     getCOSObject().setItem(COSName.B, (COSBase)COSBoolean.getBoolean(opaque));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/pagenavigation/PDTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */