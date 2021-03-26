/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PDFieldFactory
/*     */ {
/*     */   private static final String FIELD_TYPE_TEXT = "Tx";
/*     */   private static final String FIELD_TYPE_BUTTON = "Btn";
/*     */   private static final String FIELD_TYPE_CHOICE = "Ch";
/*     */   private static final String FIELD_TYPE_SIGNATURE = "Sig";
/*     */   
/*     */   static PDField createField(PDAcroForm form, COSDictionary field, PDNonTerminalField parent) {
/*  50 */     String fieldType = findFieldType(field);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     if (field.containsKey(COSName.KIDS)) {
/*     */       
/*  58 */       COSArray kids = (COSArray)field.getDictionaryObject(COSName.KIDS);
/*  59 */       if (kids != null && kids.size() > 0)
/*     */       {
/*  61 */         for (int i = 0; i < kids.size(); i++) {
/*     */           
/*  63 */           COSBase kid = kids.getObject(i);
/*  64 */           if (kid instanceof COSDictionary && ((COSDictionary)kid).getString(COSName.T) != null)
/*     */           {
/*  66 */             return new PDNonTerminalField(form, field, parent);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  72 */     if ("Ch".equals(fieldType))
/*     */     {
/*  74 */       return createChoiceSubType(form, field, parent);
/*     */     }
/*  76 */     if ("Tx".equals(fieldType))
/*     */     {
/*  78 */       return new PDTextField(form, field, parent);
/*     */     }
/*  80 */     if ("Sig".equals(fieldType))
/*     */     {
/*  82 */       return new PDSignatureField(form, field, parent);
/*     */     }
/*  84 */     if ("Btn".equals(fieldType))
/*     */     {
/*  86 */       return createButtonSubType(form, field, parent);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDField createChoiceSubType(PDAcroForm form, COSDictionary field, PDNonTerminalField parent) {
/*  98 */     int flags = field.getInt(COSName.FF, 0);
/*  99 */     if ((flags & 0x20000) != 0)
/*     */     {
/* 101 */       return new PDComboBox(form, field, parent);
/*     */     }
/*     */ 
/*     */     
/* 105 */     return new PDListBox(form, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDField createButtonSubType(PDAcroForm form, COSDictionary field, PDNonTerminalField parent) {
/* 112 */     int flags = field.getInt(COSName.FF, 0);
/*     */ 
/*     */ 
/*     */     
/* 116 */     if ((flags & 0x8000) != 0)
/*     */     {
/* 118 */       return new PDRadioButton(form, field, parent);
/*     */     }
/* 120 */     if ((flags & 0x10000) != 0)
/*     */     {
/* 122 */       return new PDPushButton(form, field, parent);
/*     */     }
/*     */ 
/*     */     
/* 126 */     return new PDCheckBox(form, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String findFieldType(COSDictionary dic) {
/* 132 */     String retval = dic.getNameAsString(COSName.FT);
/* 133 */     if (retval == null) {
/*     */       
/* 135 */       COSBase base = dic.getDictionaryObject(COSName.PARENT, COSName.P);
/* 136 */       if (base instanceof COSDictionary)
/*     */       {
/* 138 */         retval = findFieldType((COSDictionary)base);
/*     */       }
/*     */     } 
/* 141 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDFieldFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */