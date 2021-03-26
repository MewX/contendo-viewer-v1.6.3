/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.fdf.FDFField;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDFormFieldAdditionalActions;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDTerminalField
/*     */   extends PDField
/*     */ {
/*     */   protected PDTerminalField(PDAcroForm acroForm) {
/*  47 */     super(acroForm);
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
/*     */   PDTerminalField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  59 */     super(acroForm, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActions(PDFormFieldAdditionalActions actions) {
/*  69 */     getCOSObject().setItem(COSName.AA, (COSObjectable)actions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFieldFlags() {
/*  75 */     int retval = 0;
/*  76 */     COSInteger ff = (COSInteger)getCOSObject().getDictionaryObject(COSName.FF);
/*  77 */     if (ff != null) {
/*     */       
/*  79 */       retval = ff.intValue();
/*     */     }
/*  81 */     else if (getParent() != null) {
/*     */       
/*  83 */       retval = getParent().getFieldFlags();
/*     */     } 
/*  85 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFieldType() {
/*  91 */     String fieldType = getCOSObject().getNameAsString(COSName.FT);
/*  92 */     if (fieldType == null && getParent() != null)
/*     */     {
/*  94 */       fieldType = getParent().getFieldType();
/*     */     }
/*  96 */     return fieldType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void importFDF(FDFField fdfField) throws IOException {
/* 102 */     super.importFDF(fdfField);
/*     */     
/* 104 */     PDAnnotationWidget widget = getWidgets().get(0);
/* 105 */     if (widget != null) {
/*     */       
/* 107 */       int annotFlags = widget.getAnnotationFlags();
/* 108 */       Integer f = fdfField.getWidgetFieldFlags();
/* 109 */       if (f != null) {
/*     */         
/* 111 */         widget.setAnnotationFlags(f.intValue());
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 116 */         Integer setF = fdfField.getSetWidgetFieldFlags();
/* 117 */         if (setF != null) {
/*     */           
/* 119 */           annotFlags |= setF.intValue();
/* 120 */           widget.setAnnotationFlags(annotFlags);
/*     */         } 
/*     */         
/* 123 */         Integer clrF = fdfField.getClearWidgetFieldFlags();
/* 124 */         if (clrF != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 134 */           int clrFValue = clrF.intValue();
/* 135 */           clrFValue = (int)(clrFValue ^ 0xFFFFFFFFL);
/* 136 */           annotFlags &= clrFValue;
/* 137 */           widget.setAnnotationFlags(annotFlags);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   FDFField exportFDF() throws IOException {
/* 146 */     FDFField fdfField = new FDFField();
/* 147 */     fdfField.setPartialFieldName(getPartialName());
/* 148 */     fdfField.setValue(getCOSObject().getDictionaryObject(COSName.V));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     return fdfField;
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
/*     */   public List<PDAnnotationWidget> getWidgets() {
/* 167 */     List<PDAnnotationWidget> widgets = new ArrayList<PDAnnotationWidget>();
/* 168 */     COSArray kids = (COSArray)getCOSObject().getDictionaryObject(COSName.KIDS);
/* 169 */     if (kids == null) {
/*     */ 
/*     */       
/* 172 */       widgets.add(new PDAnnotationWidget(getCOSObject()));
/*     */     }
/* 174 */     else if (kids.size() > 0) {
/*     */ 
/*     */       
/* 177 */       for (int i = 0; i < kids.size(); i++) {
/*     */         
/* 179 */         COSBase kid = kids.getObject(i);
/* 180 */         if (kid instanceof COSDictionary)
/*     */         {
/* 182 */           widgets.add(new PDAnnotationWidget((COSDictionary)kid));
/*     */         }
/*     */       } 
/*     */     } 
/* 186 */     return widgets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidgets(List<PDAnnotationWidget> children) {
/* 196 */     COSArray kidsArray = COSArrayList.converterToCOSArray(children);
/* 197 */     getCOSObject().setItem(COSName.KIDS, (COSBase)kidsArray);
/* 198 */     for (PDAnnotationWidget widget : children)
/*     */     {
/* 200 */       widget.getCOSObject().setItem(COSName.PARENT, this);
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
/*     */   @Deprecated
/*     */   public PDAnnotationWidget getWidget() {
/* 216 */     return getWidgets().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void applyChange() throws IOException {
/* 226 */     if (!getAcroForm().getNeedAppearances())
/*     */     {
/* 228 */       constructAppearances();
/*     */     }
/*     */   }
/*     */   
/*     */   abstract void constructAppearances() throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDTerminalField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */