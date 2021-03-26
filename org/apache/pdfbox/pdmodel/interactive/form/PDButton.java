/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDButton
/*     */   extends PDTerminalField
/*     */ {
/*     */   static final int FLAG_RADIO = 32768;
/*     */   static final int FLAG_PUSHBUTTON = 65536;
/*     */   static final int FLAG_RADIOS_IN_UNISON = 33554432;
/*     */   
/*     */   public PDButton(PDAcroForm acroForm) {
/*  68 */     super(acroForm);
/*  69 */     getCOSObject().setItem(COSName.FT, (COSBase)COSName.BTN);
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
/*     */   PDButton(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  81 */     super(acroForm, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPushButton() {
/*  91 */     return getCOSObject().getFlag(COSName.FF, 65536);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPushButton(boolean pushbutton) {
/* 101 */     getCOSObject().setFlag(COSName.FF, 65536, pushbutton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRadioButton() {
/* 111 */     return getCOSObject().getFlag(COSName.FF, 32768);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRadioButton(boolean radiobutton) {
/* 121 */     getCOSObject().setFlag(COSName.FF, 32768, radiobutton);
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
/*     */   public String getValue() {
/* 134 */     COSBase value = getInheritableAttribute(COSName.V);
/* 135 */     if (value instanceof COSName)
/*     */     {
/* 137 */       return ((COSName)value).getName();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     return "Off";
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
/*     */   public void setValue(String value) throws IOException {
/* 158 */     checkValue(value);
/*     */ 
/*     */ 
/*     */     
/* 162 */     boolean hasExportValues = (getExportValues().size() > 0);
/*     */     
/* 164 */     if (hasExportValues) {
/* 165 */       updateByOption(value);
/*     */     }
/*     */     else {
/*     */       
/* 169 */       updateByValue(value);
/*     */     } 
/*     */     
/* 172 */     applyChange();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultValue() {
/* 183 */     COSBase value = getInheritableAttribute(COSName.DV);
/* 184 */     if (value instanceof COSName)
/*     */     {
/* 186 */       return ((COSName)value).getName();
/*     */     }
/*     */ 
/*     */     
/* 190 */     return "";
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
/*     */   public void setDefaultValue(String value) {
/* 202 */     checkValue(value);
/* 203 */     getCOSObject().setName(COSName.DV, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 209 */     return getValue();
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
/*     */   public List<String> getExportValues() {
/* 234 */     COSBase value = getInheritableAttribute(COSName.OPT);
/*     */     
/* 236 */     if (value instanceof COSString) {
/*     */       
/* 238 */       List<String> array = new ArrayList<String>();
/* 239 */       array.add(((COSString)value).getString());
/* 240 */       return array;
/*     */     } 
/* 242 */     if (value instanceof COSArray)
/*     */     {
/* 244 */       return COSArrayList.convertCOSStringCOSArrayToList((COSArray)value);
/*     */     }
/* 246 */     return Collections.emptyList();
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
/*     */   public void setExportValues(List<String> values) {
/* 258 */     if (values != null && !values.isEmpty()) {
/*     */       
/* 260 */       COSArray cosValues = COSArrayList.convertStringListToCOSStringCOSArray(values);
/* 261 */       getCOSObject().setItem(COSName.OPT, (COSBase)cosValues);
/*     */     }
/*     */     else {
/*     */       
/* 265 */       getCOSObject().removeItem(COSName.OPT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void constructAppearances() throws IOException {
/* 272 */     List<String> exportValues = getExportValues();
/* 273 */     if (exportValues.size() > 0) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 279 */         int optionsIndex = Integer.parseInt(getValue());
/* 280 */         if (optionsIndex < exportValues.size())
/*     */         {
/* 282 */           updateByOption(exportValues.get(optionsIndex));
/*     */         }
/* 284 */       } catch (NumberFormatException numberFormatException) {}
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 292 */       updateByValue(getValue());
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
/*     */   
/*     */   public Set<String> getOnValues() {
/* 309 */     Set<String> onValues = new LinkedHashSet<String>();
/*     */     
/* 311 */     if (getExportValues().size() > 0) {
/*     */       
/* 313 */       onValues.addAll(getExportValues());
/* 314 */       return onValues;
/*     */     } 
/*     */     
/* 317 */     List<PDAnnotationWidget> widgets = getWidgets();
/* 318 */     for (PDAnnotationWidget widget : widgets)
/*     */     {
/* 320 */       onValues.add(getOnValueForWidget(widget));
/*     */     }
/* 322 */     return onValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getOnValue(int index) {
/* 330 */     List<PDAnnotationWidget> widgets = getWidgets();
/* 331 */     if (index < widgets.size())
/*     */     {
/* 333 */       return getOnValueForWidget(widgets.get(index));
/*     */     }
/* 335 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getOnValueForWidget(PDAnnotationWidget widget) {
/* 343 */     PDAppearanceDictionary apDictionary = widget.getAppearance();
/* 344 */     if (apDictionary != null) {
/*     */       
/* 346 */       PDAppearanceEntry normalAppearance = apDictionary.getNormalAppearance();
/* 347 */       if (normalAppearance != null) {
/*     */         
/* 349 */         Set<COSName> entries = normalAppearance.getSubDictionary().keySet();
/* 350 */         for (COSName entry : entries) {
/*     */           
/* 352 */           if (COSName.Off.compareTo(entry) != 0)
/*     */           {
/* 354 */             return entry.getName();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 359 */     return "";
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
/*     */   void checkValue(String value) throws IllegalArgumentException {
/* 371 */     Set<String> onValues = getOnValues();
/* 372 */     if (COSName.Off.getName().compareTo(value) != 0 && !onValues.contains(value))
/*     */     {
/* 374 */       throw new IllegalArgumentException("value '" + value + "' is not a valid option for the field " + 
/* 375 */           getFullyQualifiedName() + ", valid values are: " + onValues + " and " + COSName.Off
/* 376 */           .getName());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateByValue(String value) throws IOException {
/* 382 */     getCOSObject().setName(COSName.V, value);
/*     */     
/* 384 */     for (PDAnnotationWidget widget : getWidgets()) {
/*     */       
/* 386 */       if (widget.getAppearance() == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 390 */       PDAppearanceEntry appearanceEntry = widget.getAppearance().getNormalAppearance();
/* 391 */       if (((COSDictionary)appearanceEntry.getCOSObject()).containsKey(value)) {
/*     */         
/* 393 */         widget.setAppearanceState(value);
/*     */         
/*     */         continue;
/*     */       } 
/* 397 */       widget.setAppearanceState(COSName.Off.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateByOption(String value) throws IOException {
/* 404 */     List<PDAnnotationWidget> widgets = getWidgets();
/* 405 */     List<String> options = getExportValues();
/*     */     
/* 407 */     if (widgets.size() != options.size())
/*     */     {
/* 409 */       throw new IllegalArgumentException("The number of options doesn't match the number of widgets");
/*     */     }
/*     */     
/* 412 */     if (value.equals(COSName.Off.getName())) {
/*     */       
/* 414 */       updateByValue(value);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 419 */       int optionsIndex = options.indexOf(value);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 424 */       if (optionsIndex != -1)
/*     */       {
/* 426 */         updateByValue(getOnValue(optionsIndex));
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */