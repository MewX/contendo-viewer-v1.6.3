/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDRadioButton
/*     */   extends PDButton
/*     */ {
/*     */   private static final int FLAG_NO_TOGGLE_TO_OFF = 16384;
/*     */   
/*     */   public PDRadioButton(PDAcroForm acroForm) {
/*  46 */     super(acroForm);
/*  47 */     setRadioButton(true);
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
/*     */   PDRadioButton(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  59 */     super(acroForm, field, parent);
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
/*     */   public void setRadiosInUnison(boolean radiosInUnison) {
/*  72 */     getCOSObject().setFlag(COSName.FF, 33554432, radiosInUnison);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRadiosInUnison() {
/*  81 */     return getCOSObject().getFlag(COSName.FF, 33554432);
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
/*     */   public List<String> getSelectedExportValues() throws IOException {
/* 101 */     Set<String> onValues = getOnValues();
/* 102 */     List<String> exportValues = getExportValues();
/* 103 */     List<String> selectedExportValues = new ArrayList<String>();
/* 104 */     if (exportValues.isEmpty()) {
/*     */       
/* 106 */       selectedExportValues.add(getValue());
/* 107 */       return selectedExportValues;
/*     */     } 
/*     */ 
/*     */     
/* 111 */     String fieldValue = getValue();
/* 112 */     int idx = 0;
/* 113 */     for (String onValue : onValues) {
/*     */       
/* 115 */       if (onValue.compareTo(fieldValue) == 0)
/*     */       {
/* 117 */         selectedExportValues.add(exportValues.get(idx));
/*     */       }
/*     */     } 
/* 120 */     return selectedExportValues;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDRadioButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */