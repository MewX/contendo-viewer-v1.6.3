/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDCheckBox
/*     */   extends PDButton
/*     */ {
/*     */   public PDCheckBox(PDAcroForm acroForm) {
/*  43 */     super(acroForm);
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
/*     */   PDCheckBox(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  55 */     super(acroForm, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChecked() {
/*  66 */     return (getValue().compareTo(getOnValue()) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() throws IOException {
/*  76 */     setValue(getOnValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unCheck() throws IOException {
/*  86 */     setValue(COSName.Off.getName());
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
/*     */   public String getOnValue() {
/* 102 */     PDAnnotationWidget widget = getWidgets().get(0);
/* 103 */     PDAppearanceDictionary apDictionary = widget.getAppearance();
/*     */     
/* 105 */     String onValue = "";
/* 106 */     if (apDictionary != null) {
/*     */       
/* 108 */       PDAppearanceEntry normalAppearance = apDictionary.getNormalAppearance();
/* 109 */       if (normalAppearance != null) {
/*     */         
/* 111 */         Set<COSName> entries = normalAppearance.getSubDictionary().keySet();
/* 112 */         for (COSName entry : entries) {
/*     */           
/* 114 */           if (COSName.Off.compareTo(entry) != 0)
/*     */           {
/* 116 */             onValue = entry.getName();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 121 */     return onValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDCheckBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */