/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
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
/*     */ public abstract class PDField
/*     */   implements COSObjectable
/*     */ {
/*     */   private static final int FLAG_READ_ONLY = 1;
/*     */   private static final int FLAG_REQUIRED = 2;
/*     */   private static final int FLAG_NO_EXPORT = 4;
/*     */   private final PDAcroForm acroForm;
/*     */   private final PDNonTerminalField parent;
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   PDField(PDAcroForm acroForm) {
/*  54 */     this(acroForm, new COSDictionary(), null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  65 */     this.acroForm = acroForm;
/*  66 */     this.dictionary = field;
/*  67 */     this.parent = parent;
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
/*     */   static PDField fromDictionary(PDAcroForm form, COSDictionary field, PDNonTerminalField parent) {
/*  80 */     return PDFieldFactory.createField(form, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected COSBase getInheritableAttribute(COSName key) {
/*  91 */     if (this.dictionary.containsKey(key))
/*     */     {
/*  93 */       return this.dictionary.getDictionaryObject(key);
/*     */     }
/*  95 */     if (this.parent != null)
/*     */     {
/*  97 */       return this.parent.getInheritableAttribute(key);
/*     */     }
/*     */ 
/*     */     
/* 101 */     return this.acroForm.getCOSObject().getDictionaryObject(key);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadOnly(boolean readonly) {
/* 151 */     this.dictionary.setFlag(COSName.FF, 1, readonly);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 160 */     return this.dictionary.getFlag(COSName.FF, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequired(boolean required) {
/* 171 */     this.dictionary.setFlag(COSName.FF, 2, required);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRequired() {
/* 180 */     return this.dictionary.getFlag(COSName.FF, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoExport(boolean noExport) {
/* 190 */     this.dictionary.setFlag(COSName.FF, 4, noExport);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNoExport() {
/* 199 */     return this.dictionary.getFlag(COSName.FF, 4);
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
/*     */   public void setFieldFlags(int flags) {
/* 216 */     this.dictionary.setInt(COSName.FF, flags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormFieldAdditionalActions getActions() {
/* 227 */     COSDictionary aa = (COSDictionary)this.dictionary.getDictionaryObject(COSName.AA);
/* 228 */     if (aa != null)
/*     */     {
/* 230 */       return new PDFormFieldAdditionalActions(aa);
/*     */     }
/* 232 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void importFDF(FDFField fdfField) throws IOException {
/* 243 */     COSBase fieldValue = fdfField.getCOSValue();
/*     */     
/* 245 */     if (fieldValue != null && this instanceof PDTerminalField) {
/*     */       
/* 247 */       PDTerminalField currentField = (PDTerminalField)this;
/*     */       
/* 249 */       if (fieldValue instanceof COSName)
/*     */       {
/* 251 */         currentField.setValue(((COSName)fieldValue).getName());
/*     */       }
/* 253 */       else if (fieldValue instanceof COSString)
/*     */       {
/* 255 */         currentField.setValue(((COSString)fieldValue).getString());
/*     */       }
/* 257 */       else if (fieldValue instanceof COSStream)
/*     */       {
/* 259 */         currentField.setValue(((COSStream)fieldValue).toTextString());
/*     */       }
/* 261 */       else if (fieldValue instanceof COSArray && this instanceof PDChoice)
/*     */       {
/* 263 */         ((PDChoice)this).setValue(COSArrayList.convertCOSStringCOSArrayToList((COSArray)fieldValue));
/*     */       }
/*     */       else
/*     */       {
/* 267 */         throw new IOException("Error:Unknown type for field import" + fieldValue);
/*     */       }
/*     */     
/* 270 */     } else if (fieldValue != null) {
/*     */       
/* 272 */       this.dictionary.setItem(COSName.V, fieldValue);
/*     */     } 
/*     */     
/* 275 */     Integer ff = fdfField.getFieldFlags();
/* 276 */     if (ff != null) {
/*     */       
/* 278 */       setFieldFlags(ff.intValue());
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 283 */       Integer setFf = fdfField.getSetFieldFlags();
/* 284 */       int fieldFlags = getFieldFlags();
/*     */       
/* 286 */       if (setFf != null) {
/*     */         
/* 288 */         int setFfInt = setFf.intValue();
/* 289 */         fieldFlags |= setFfInt;
/* 290 */         setFieldFlags(fieldFlags);
/*     */       } 
/*     */       
/* 293 */       Integer clrFf = fdfField.getClearFieldFlags();
/* 294 */       if (clrFf != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 304 */         int clrFfValue = clrFf.intValue();
/* 305 */         clrFfValue ^= 0xFFFFFFFF;
/* 306 */         fieldFlags &= clrFfValue;
/* 307 */         setFieldFlags(fieldFlags);
/*     */       } 
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
/*     */   
/*     */   public PDNonTerminalField getParent() {
/* 324 */     return this.parent;
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
/*     */   PDField findKid(String[] name, int nameIndex) {
/* 338 */     PDField retval = null;
/* 339 */     COSArray kids = (COSArray)this.dictionary.getDictionaryObject(COSName.KIDS);
/* 340 */     if (kids != null)
/*     */     {
/* 342 */       for (int i = 0; retval == null && i < kids.size(); i++) {
/*     */         
/* 344 */         COSDictionary kidDictionary = (COSDictionary)kids.getObject(i);
/* 345 */         if (name[nameIndex].equals(kidDictionary.getString(COSName.T))) {
/*     */           
/* 347 */           retval = fromDictionary(this.acroForm, kidDictionary, (PDNonTerminalField)this);
/*     */           
/* 349 */           if (retval != null && name.length > nameIndex + 1)
/*     */           {
/* 351 */             retval = retval.findKid(name, nameIndex + 1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 356 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAcroForm getAcroForm() {
/* 366 */     return this.acroForm;
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
/* 377 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPartialName() {
/* 387 */     return this.dictionary.getString(COSName.T);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartialName(String name) {
/* 396 */     this.dictionary.setString(COSName.T, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullyQualifiedName() {
/* 406 */     String finalName = getPartialName();
/* 407 */     String parentName = (this.parent != null) ? this.parent.getFullyQualifiedName() : null;
/* 408 */     if (parentName != null)
/*     */     {
/* 410 */       if (finalName != null) {
/*     */         
/* 412 */         finalName = parentName + "." + finalName;
/*     */       }
/*     */       else {
/*     */         
/* 416 */         finalName = parentName;
/*     */       } 
/*     */     }
/* 419 */     return finalName;
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
/*     */   public String getAlternateFieldName() {
/* 431 */     return this.dictionary.getString(COSName.TU);
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
/*     */   public void setAlternateFieldName(String alternateFieldName) {
/* 444 */     this.dictionary.setString(COSName.TU, alternateFieldName);
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
/*     */   public String getMappingName() {
/* 457 */     return this.dictionary.getString(COSName.TM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMappingName(String mappingName) {
/* 467 */     this.dictionary.setString(COSName.TM, mappingName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 473 */     return getFullyQualifiedName() + "{type: " + getClass().getSimpleName() + " value: " + 
/* 474 */       getInheritableAttribute(COSName.V) + "}";
/*     */   }
/*     */   
/*     */   public abstract String getFieldType();
/*     */   
/*     */   public abstract String getValueAsString();
/*     */   
/*     */   public abstract void setValue(String paramString) throws IOException;
/*     */   
/*     */   public abstract List<PDAnnotationWidget> getWidgets();
/*     */   
/*     */   public abstract int getFieldFlags();
/*     */   
/*     */   abstract FDFField exportFDF() throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */