/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDCircleAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDSquareAppearanceHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAnnotationSquareCircle
/*     */   extends PDAnnotationMarkup
/*     */ {
/*     */   public static final String SUB_TYPE_SQUARE = "Square";
/*     */   public static final String SUB_TYPE_CIRCLE = "Circle";
/*     */   private PDAppearanceHandler customAppearanceHandler;
/*     */   
/*     */   public PDAnnotationSquareCircle(String subType) {
/*  56 */     setSubtype(subType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationSquareCircle(COSDictionary field) {
/*  66 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteriorColor(PDColor ic) {
/*  77 */     getCOSObject().setItem(COSName.IC, (COSBase)ic.toCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInteriorColor() {
/*  87 */     return getColor(COSName.IC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorderEffect(PDBorderEffectDictionary be) {
/*  98 */     getCOSObject().setItem(COSName.BE, be);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderEffectDictionary getBorderEffect() {
/* 108 */     COSDictionary be = (COSDictionary)getCOSObject().getDictionaryObject(COSName.BE);
/* 109 */     if (be != null)
/*     */     {
/* 111 */       return new PDBorderEffectDictionary(be);
/*     */     }
/*     */ 
/*     */     
/* 115 */     return null;
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
/*     */   public void setRectDifference(PDRectangle rd) {
/* 128 */     getCOSObject().setItem(COSName.RD, (COSObjectable)rd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getRectDifference() {
/* 139 */     COSArray rd = (COSArray)getCOSObject().getDictionaryObject(COSName.RD);
/* 140 */     if (rd != null)
/*     */     {
/* 142 */       return new PDRectangle(rd);
/*     */     }
/*     */ 
/*     */     
/* 146 */     return null;
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
/*     */   public void setSubtype(String subType) {
/* 158 */     getCOSObject().setName(COSName.SUBTYPE, subType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubtype() {
/* 169 */     return getCOSObject().getNameAsString(COSName.SUBTYPE);
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
/*     */   public void setBorderStyle(PDBorderStyleDictionary bs) {
/* 181 */     getCOSObject().setItem(COSName.BS, bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderStyleDictionary getBorderStyle() {
/* 192 */     COSBase bs = getCOSObject().getDictionaryObject(COSName.BS);
/* 193 */     if (bs instanceof COSDictionary)
/*     */     {
/* 195 */       return new PDBorderStyleDictionary((COSDictionary)bs);
/*     */     }
/* 197 */     return null;
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
/*     */   public void setRectDifferences(float difference) {
/* 211 */     setRectDifferences(difference, difference, difference, difference);
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
/*     */   public void setRectDifferences(float differenceLeft, float differenceTop, float differenceRight, float differenceBottom) {
/* 226 */     COSArray margins = new COSArray();
/* 227 */     margins.add((COSBase)new COSFloat(differenceLeft));
/* 228 */     margins.add((COSBase)new COSFloat(differenceTop));
/* 229 */     margins.add((COSBase)new COSFloat(differenceRight));
/* 230 */     margins.add((COSBase)new COSFloat(differenceBottom));
/* 231 */     getCOSObject().setItem(COSName.RD, (COSBase)margins);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getRectDifferences() {
/* 242 */     COSBase margin = getCOSObject().getItem(COSName.RD);
/* 243 */     if (margin instanceof COSArray)
/*     */     {
/* 245 */       return ((COSArray)margin).toFloatArray();
/*     */     }
/* 247 */     return new float[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomAppearanceHandler(PDAppearanceHandler appearanceHandler) {
/* 257 */     this.customAppearanceHandler = appearanceHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void constructAppearances() {
/* 263 */     if (this.customAppearanceHandler == null) {
/*     */       
/* 265 */       if ("Circle".equals(getSubtype()))
/*     */       {
/* 267 */         PDCircleAppearanceHandler appearanceHandler = new PDCircleAppearanceHandler(this);
/* 268 */         appearanceHandler.generateAppearanceStreams();
/*     */       }
/* 270 */       else if ("Square".equals(getSubtype()))
/*     */       {
/* 272 */         PDSquareAppearanceHandler appearanceHandler = new PDSquareAppearanceHandler(this);
/* 273 */         appearanceHandler.generateAppearanceStreams();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 278 */       this.customAppearanceHandler.generateAppearanceStreams();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationSquareCircle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */