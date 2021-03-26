/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDHighlightAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDSquigglyAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDStrikeoutAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDUnderlineAppearanceHandler;
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
/*     */ public class PDAnnotationTextMarkup
/*     */   extends PDAnnotationMarkup
/*     */ {
/*     */   private PDAppearanceHandler customAppearanceHandler;
/*     */   public static final String SUB_TYPE_HIGHLIGHT = "Highlight";
/*     */   public static final String SUB_TYPE_UNDERLINE = "Underline";
/*     */   public static final String SUB_TYPE_SQUIGGLY = "Squiggly";
/*     */   public static final String SUB_TYPE_STRIKEOUT = "StrikeOut";
/*     */   
/*     */   private PDAnnotationTextMarkup() {}
/*     */   
/*     */   public PDAnnotationTextMarkup(String subType) {
/*  68 */     setSubtype(subType);
/*     */ 
/*     */     
/*  71 */     setQuadPoints(new float[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationTextMarkup(COSDictionary field) {
/*  81 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuadPoints(float[] quadPoints) {
/*  91 */     COSArray newQuadPoints = new COSArray();
/*  92 */     newQuadPoints.setFloatArray(quadPoints);
/*  93 */     getCOSObject().setItem(COSName.QUADPOINTS, (COSBase)newQuadPoints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getQuadPoints() {
/* 103 */     COSBase base = getCOSObject().getDictionaryObject(COSName.QUADPOINTS);
/* 104 */     if (base instanceof COSArray)
/*     */     {
/* 106 */       return ((COSArray)base).toFloatArray();
/*     */     }
/*     */     
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtype(String subType) {
/* 120 */     getCOSObject().setName(COSName.SUBTYPE, subType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubtype() {
/* 130 */     return getCOSObject().getNameAsString(COSName.SUBTYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomAppearanceHandler(PDAppearanceHandler appearanceHandler) {
/* 140 */     this.customAppearanceHandler = appearanceHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void constructAppearances() {
/* 146 */     if (this.customAppearanceHandler == null) {
/*     */       PDUnderlineAppearanceHandler pDUnderlineAppearanceHandler;
/* 148 */       PDAppearanceHandler appearanceHandler = null;
/* 149 */       if ("Highlight".equals(getSubtype())) {
/*     */         
/* 151 */         PDHighlightAppearanceHandler pDHighlightAppearanceHandler = new PDHighlightAppearanceHandler(this);
/*     */       }
/* 153 */       else if ("Squiggly".equals(getSubtype())) {
/*     */         
/* 155 */         PDSquigglyAppearanceHandler pDSquigglyAppearanceHandler = new PDSquigglyAppearanceHandler(this);
/*     */       }
/* 157 */       else if ("StrikeOut".equals(getSubtype())) {
/*     */         
/* 159 */         PDStrikeoutAppearanceHandler pDStrikeoutAppearanceHandler = new PDStrikeoutAppearanceHandler(this);
/*     */       }
/* 161 */       else if ("Underline".equals(getSubtype())) {
/*     */         
/* 163 */         pDUnderlineAppearanceHandler = new PDUnderlineAppearanceHandler(this);
/*     */       } 
/*     */       
/* 166 */       if (pDUnderlineAppearanceHandler != null)
/*     */       {
/* 168 */         pDUnderlineAppearanceHandler.generateAppearanceStreams();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 173 */       this.customAppearanceHandler.generateAppearanceStreams();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationTextMarkup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */