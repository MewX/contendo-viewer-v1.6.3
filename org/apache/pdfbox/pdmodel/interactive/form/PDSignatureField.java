/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*     */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSeedValue;
/*     */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDSignatureField
/*     */   extends PDTerminalField
/*     */ {
/*  41 */   private static final Log LOG = LogFactory.getLog(PDSignatureField.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignatureField(PDAcroForm acroForm) throws IOException {
/*  52 */     super(acroForm);
/*  53 */     getCOSObject().setItem(COSName.FT, (COSBase)COSName.SIG);
/*  54 */     ((PDAnnotationWidget)getWidgets().get(0)).setLocked(true);
/*  55 */     ((PDAnnotationWidget)getWidgets().get(0)).setPrinted(true);
/*  56 */     setPartialName(generatePartialName());
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
/*     */   PDSignatureField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  68 */     super(acroForm, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String generatePartialName() {
/*  78 */     String fieldName = "Signature";
/*  79 */     Set<String> sigNames = new HashSet<String>();
/*  80 */     for (PDField field : getAcroForm().getFieldTree()) {
/*     */       
/*  82 */       if (field instanceof PDSignatureField)
/*     */       {
/*  84 */         sigNames.add(field.getPartialName());
/*     */       }
/*     */     } 
/*  87 */     int i = 1;
/*  88 */     while (sigNames.contains(fieldName + i))
/*     */     {
/*  90 */       i++;
/*     */     }
/*  92 */     return fieldName + i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSignature(PDSignature value) throws IOException {
/* 104 */     setValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature getSignature() {
/* 115 */     return getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(PDSignature value) throws IOException {
/* 125 */     getCOSObject().setItem(COSName.V, (COSObjectable)value);
/* 126 */     applyChange();
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
/*     */   public void setValue(String value) throws UnsupportedOperationException {
/* 140 */     throw new UnsupportedOperationException("Signature fields don't support setting the value as String - use setValue(PDSignature value) instead");
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
/*     */   public void setDefaultValue(PDSignature value) throws IOException {
/* 152 */     getCOSObject().setItem(COSName.DV, (COSObjectable)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature getValue() {
/* 162 */     COSBase value = getCOSObject().getDictionaryObject(COSName.V);
/* 163 */     if (value instanceof COSDictionary)
/*     */     {
/* 165 */       return new PDSignature((COSDictionary)value);
/*     */     }
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature getDefaultValue() {
/* 177 */     COSBase value = getCOSObject().getDictionaryObject(COSName.DV);
/* 178 */     if (value == null)
/*     */     {
/* 180 */       return null;
/*     */     }
/* 182 */     return new PDSignature((COSDictionary)value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 188 */     PDSignature signature = getValue();
/* 189 */     return (signature != null) ? signature.toString() : "";
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
/*     */   public PDSeedValue getSeedValue() {
/* 201 */     COSDictionary dict = (COSDictionary)getCOSObject().getDictionaryObject(COSName.SV);
/* 202 */     PDSeedValue sv = null;
/* 203 */     if (dict != null)
/*     */     {
/* 205 */       sv = new PDSeedValue(dict);
/*     */     }
/* 207 */     return sv;
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
/*     */   public void setSeedValue(PDSeedValue sv) {
/* 219 */     if (sv != null)
/*     */     {
/* 221 */       getCOSObject().setItem(COSName.SV, (COSObjectable)sv);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void constructAppearances() throws IOException {
/* 228 */     PDAnnotationWidget widget = getWidgets().get(0);
/* 229 */     if (widget != null) {
/*     */ 
/*     */       
/* 232 */       if (widget.getRectangle() == null || (widget
/* 233 */         .getRectangle().getHeight() == 0.0F && widget.getRectangle().getWidth() == 0.0F) || widget
/* 234 */         .isNoView() || widget.isHidden()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 240 */       LOG.warn("Appearance generation for signature fields not yet implemented - you need to generate/update that manually");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDSignatureField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */