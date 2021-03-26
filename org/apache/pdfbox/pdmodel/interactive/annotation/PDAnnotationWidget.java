/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAnnotationAdditionalActions;
/*     */ import org.apache.pdfbox.pdmodel.interactive.form.PDTerminalField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAnnotationWidget
/*     */   extends PDAnnotation
/*     */ {
/*     */   public static final String SUB_TYPE = "Widget";
/*     */   
/*     */   public PDAnnotationWidget() {
/*  46 */     getCOSObject().setName(COSName.SUBTYPE, "Widget");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationWidget(COSDictionary field) {
/*  56 */     super(field);
/*  57 */     getCOSObject().setName(COSName.SUBTYPE, "Widget");
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
/*     */   public String getHighlightingMode() {
/*  80 */     return getCOSObject().getNameAsString(COSName.H, "I");
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
/*     */   public void setHighlightingMode(String highlightingMode) {
/* 103 */     if (highlightingMode == null || "N".equals(highlightingMode) || "I"
/* 104 */       .equals(highlightingMode) || "O".equals(highlightingMode) || "P"
/* 105 */       .equals(highlightingMode) || "T".equals(highlightingMode)) {
/*     */       
/* 107 */       getCOSObject().setName(COSName.H, highlightingMode);
/*     */     }
/*     */     else {
/*     */       
/* 111 */       throw new IllegalArgumentException("Valid values for highlighting mode are 'N', 'N', 'O', 'P' or 'T'");
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
/*     */   public PDAppearanceCharacteristicsDictionary getAppearanceCharacteristics() {
/* 123 */     COSBase mk = getCOSObject().getDictionaryObject(COSName.MK);
/* 124 */     if (mk instanceof COSDictionary)
/*     */     {
/* 126 */       return new PDAppearanceCharacteristicsDictionary((COSDictionary)mk);
/*     */     }
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearanceCharacteristics(PDAppearanceCharacteristicsDictionary appearanceCharacteristics) {
/* 139 */     getCOSObject().setItem(COSName.MK, appearanceCharacteristics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getAction() {
/* 149 */     COSBase base = getCOSObject().getDictionaryObject(COSName.A);
/* 150 */     if (base instanceof COSDictionary)
/*     */     {
/* 152 */       return PDActionFactory.createAction((COSDictionary)base);
/*     */     }
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(PDAction action) {
/* 164 */     getCOSObject().setItem(COSName.A, (COSObjectable)action);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationAdditionalActions getActions() {
/* 175 */     COSBase base = getCOSObject().getDictionaryObject(COSName.AA);
/* 176 */     if (base instanceof COSDictionary)
/*     */     {
/* 178 */       return new PDAnnotationAdditionalActions((COSDictionary)base);
/*     */     }
/* 180 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActions(PDAnnotationAdditionalActions actions) {
/* 190 */     getCOSObject().setItem(COSName.AA, (COSObjectable)actions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorderStyle(PDBorderStyleDictionary bs) {
/* 201 */     getCOSObject().setItem(COSName.BS, bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderStyleDictionary getBorderStyle() {
/* 211 */     COSBase bs = getCOSObject().getDictionaryObject(COSName.BS);
/* 212 */     if (bs instanceof COSDictionary)
/*     */     {
/* 214 */       return new PDBorderStyleDictionary((COSDictionary)bs);
/*     */     }
/* 216 */     return null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(PDTerminalField field) {
/* 243 */     if (getCOSObject().equals(field.getCOSObject()))
/*     */     {
/* 245 */       throw new IllegalArgumentException("setParent() is not to be called for a field that shares a dictionary with its only widget");
/*     */     }
/* 247 */     getCOSObject().setItem(COSName.PARENT, (COSObjectable)field);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationWidget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */