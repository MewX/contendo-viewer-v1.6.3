/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDChoice
/*     */   extends PDVariableText
/*     */ {
/*     */   static final int FLAG_COMBO = 131072;
/*     */   private static final int FLAG_SORT = 524288;
/*     */   private static final int FLAG_MULTI_SELECT = 2097152;
/*     */   private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
/*     */   private static final int FLAG_COMMIT_ON_SEL_CHANGE = 67108864;
/*     */   
/*     */   public PDChoice(PDAcroForm acroForm) {
/*  55 */     super(acroForm);
/*  56 */     getCOSObject().setItem(COSName.FT, (COSBase)COSName.CH);
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
/*     */   PDChoice(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  68 */     super(acroForm, field, parent);
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
/*     */   public List<String> getOptions() {
/*  90 */     COSBase values = getCOSObject().getDictionaryObject(COSName.OPT);
/*  91 */     return FieldUtils.getPairableItems(values, 0);
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
/*     */   public void setOptions(List<String> displayValues) {
/* 111 */     if (displayValues != null && !displayValues.isEmpty()) {
/*     */       
/* 113 */       if (isSort())
/*     */       {
/* 115 */         Collections.sort(displayValues);
/*     */       }
/* 117 */       getCOSObject().setItem(COSName.OPT, (COSBase)COSArrayList.convertStringListToCOSStringCOSArray(displayValues));
/*     */     }
/*     */     else {
/*     */       
/* 121 */       getCOSObject().removeItem(COSName.OPT);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(List<String> exportValues, List<String> displayValues) {
/* 145 */     if (exportValues != null && displayValues != null && !exportValues.isEmpty() && !displayValues.isEmpty()) {
/*     */       
/* 147 */       if (exportValues.size() != displayValues.size())
/*     */       {
/* 149 */         throw new IllegalArgumentException("The number of entries for exportValue and displayValue shall be the same.");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 154 */       List<FieldUtils.KeyValue> keyValuePairs = FieldUtils.toKeyValueList(exportValues, displayValues);
/*     */       
/* 156 */       if (isSort())
/*     */       {
/* 158 */         FieldUtils.sortByValue(keyValuePairs);
/*     */       }
/*     */       
/* 161 */       COSArray options = new COSArray();
/* 162 */       for (int i = 0; i < exportValues.size(); i++) {
/*     */         
/* 164 */         COSArray entry = new COSArray();
/* 165 */         entry.add((COSBase)new COSString(((FieldUtils.KeyValue)keyValuePairs.get(i)).getKey()));
/* 166 */         entry.add((COSBase)new COSString(((FieldUtils.KeyValue)keyValuePairs.get(i)).getValue()));
/* 167 */         options.add((COSBase)entry);
/*     */       } 
/* 169 */       getCOSObject().setItem(COSName.OPT, (COSBase)options);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 174 */       getCOSObject().removeItem(COSName.OPT);
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
/*     */   
/*     */   public List<String> getOptionsDisplayValues() {
/* 192 */     COSBase values = getCOSObject().getDictionaryObject(COSName.OPT);
/* 193 */     return FieldUtils.getPairableItems(values, 1);
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
/*     */   public List<String> getOptionsExportValues() {
/* 210 */     return getOptions();
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
/*     */   public List<Integer> getSelectedOptionsIndex() {
/* 226 */     COSBase value = getCOSObject().getDictionaryObject(COSName.I);
/* 227 */     if (value != null)
/*     */     {
/* 229 */       return COSArrayList.convertIntegerCOSArrayToList((COSArray)value);
/*     */     }
/* 231 */     return Collections.emptyList();
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
/*     */   public void setSelectedOptionsIndex(List<Integer> values) {
/* 249 */     if (values != null && !values.isEmpty()) {
/*     */       
/* 251 */       if (!isMultiSelect())
/*     */       {
/* 253 */         throw new IllegalArgumentException("Setting the indices is not allowed for choice fields not allowing multiple selections.");
/*     */       }
/*     */       
/* 256 */       getCOSObject().setItem(COSName.I, (COSBase)COSArrayList.converterToCOSArray(values));
/*     */     }
/*     */     else {
/*     */       
/* 260 */       getCOSObject().removeItem(COSName.I);
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
/*     */   public boolean isSort() {
/* 277 */     return getCOSObject().getFlag(COSName.FF, 524288);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSort(boolean sort) {
/* 288 */     getCOSObject().setFlag(COSName.FF, 524288, sort);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMultiSelect() {
/* 298 */     return getCOSObject().getFlag(COSName.FF, 2097152);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMultiSelect(boolean multiSelect) {
/* 308 */     getCOSObject().setFlag(COSName.FF, 2097152, multiSelect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoNotSpellCheck() {
/* 318 */     return getCOSObject().getFlag(COSName.FF, 4194304);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoNotSpellCheck(boolean doNotSpellCheck) {
/* 328 */     getCOSObject().setFlag(COSName.FF, 4194304, doNotSpellCheck);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCommitOnSelChange() {
/* 338 */     return getCOSObject().getFlag(COSName.FF, 67108864);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommitOnSelChange(boolean commitOnSelChange) {
/* 348 */     getCOSObject().setFlag(COSName.FF, 67108864, commitOnSelChange);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCombo() {
/* 358 */     return getCOSObject().getFlag(COSName.FF, 131072);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCombo(boolean combo) {
/* 368 */     getCOSObject().setFlag(COSName.FF, 131072, combo);
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
/*     */   public void setValue(String value) throws IOException {
/* 381 */     getCOSObject().setString(COSName.V, value);
/*     */ 
/*     */     
/* 384 */     setSelectedOptionsIndex((List<Integer>)null);
/*     */     
/* 386 */     applyChange();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultValue(String value) throws IOException {
/* 397 */     getCOSObject().setString(COSName.DV, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(List<String> values) throws IOException {
/* 408 */     if (values != null && !values.isEmpty()) {
/*     */       
/* 410 */       if (!isMultiSelect())
/*     */       {
/* 412 */         throw new IllegalArgumentException("The list box does not allow multiple selections.");
/*     */       }
/* 414 */       if (!getOptions().containsAll(values))
/*     */       {
/* 416 */         throw new IllegalArgumentException("The values are not contained in the selectable options.");
/*     */       }
/* 418 */       getCOSObject().setItem(COSName.V, (COSBase)COSArrayList.convertStringListToCOSStringCOSArray(values));
/* 419 */       updateSelectedOptionsIndex(values);
/*     */     }
/*     */     else {
/*     */       
/* 423 */       getCOSObject().removeItem(COSName.V);
/* 424 */       getCOSObject().removeItem(COSName.I);
/*     */     } 
/* 426 */     applyChange();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getValue() {
/* 437 */     return getValueFor(COSName.V);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getDefaultValue() {
/* 448 */     return getValueFor(COSName.DV);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> getValueFor(COSName name) {
/* 456 */     COSBase value = getCOSObject().getDictionaryObject(name);
/* 457 */     if (value instanceof COSString) {
/*     */       
/* 459 */       List<String> array = new ArrayList<String>();
/* 460 */       array.add(((COSString)value).getString());
/* 461 */       return array;
/*     */     } 
/* 463 */     if (value instanceof COSArray)
/*     */     {
/* 465 */       return COSArrayList.convertCOSStringCOSArrayToList((COSArray)value);
/*     */     }
/* 467 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 473 */     return Arrays.toString(getValue().toArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateSelectedOptionsIndex(List<String> values) {
/* 481 */     List<String> options = getOptions();
/* 482 */     List<Integer> indices = new ArrayList<Integer>();
/*     */     
/* 484 */     for (String value : values)
/*     */     {
/* 486 */       indices.add(Integer.valueOf(options.indexOf(value)));
/*     */     }
/*     */ 
/*     */     
/* 490 */     Collections.sort(indices);
/* 491 */     setSelectedOptionsIndex(indices);
/*     */   }
/*     */   
/*     */   abstract void constructAppearances() throws IOException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDChoice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */