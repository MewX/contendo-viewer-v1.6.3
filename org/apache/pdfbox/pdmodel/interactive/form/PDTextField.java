/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public final class PDTextField
/*     */   extends PDVariableText
/*     */ {
/*     */   private static final int FLAG_MULTILINE = 4096;
/*     */   private static final int FLAG_PASSWORD = 8192;
/*     */   private static final int FLAG_FILE_SELECT = 1048576;
/*     */   private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
/*     */   private static final int FLAG_DO_NOT_SCROLL = 8388608;
/*     */   private static final int FLAG_COMB = 16777216;
/*     */   private static final int FLAG_RICH_TEXT = 33554432;
/*     */   
/*     */   public PDTextField(PDAcroForm acroForm) {
/*  46 */     super(acroForm);
/*  47 */     getCOSObject().setItem(COSName.FT, (COSBase)COSName.TX);
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
/*     */   PDTextField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  59 */     super(acroForm, field, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMultiline() {
/*  67 */     return getCOSObject().getFlag(COSName.FF, 4096);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMultiline(boolean multiline) {
/*  77 */     getCOSObject().setFlag(COSName.FF, 4096, multiline);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPassword() {
/*  85 */     return getCOSObject().getFlag(COSName.FF, 8192);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(boolean password) {
/*  95 */     getCOSObject().setFlag(COSName.FF, 8192, password);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFileSelect() {
/* 103 */     return getCOSObject().getFlag(COSName.FF, 1048576);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileSelect(boolean fileSelect) {
/* 113 */     getCOSObject().setFlag(COSName.FF, 1048576, fileSelect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doNotSpellCheck() {
/* 121 */     return getCOSObject().getFlag(COSName.FF, 4194304);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoNotSpellCheck(boolean doNotSpellCheck) {
/* 131 */     getCOSObject().setFlag(COSName.FF, 4194304, doNotSpellCheck);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doNotScroll() {
/* 139 */     return getCOSObject().getFlag(COSName.FF, 8388608);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoNotScroll(boolean doNotScroll) {
/* 149 */     getCOSObject().setFlag(COSName.FF, 8388608, doNotScroll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComb() {
/* 157 */     return getCOSObject().getFlag(COSName.FF, 16777216);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComb(boolean comb) {
/* 167 */     getCOSObject().setFlag(COSName.FF, 16777216, comb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRichText() {
/* 175 */     return getCOSObject().getFlag(COSName.FF, 33554432);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRichText(boolean richText) {
/* 185 */     getCOSObject().setFlag(COSName.FF, 33554432, richText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxLen() {
/* 195 */     return getCOSObject().getInt(COSName.MAX_LEN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxLen(int maxLen) {
/* 205 */     getCOSObject().setInt(COSName.MAX_LEN, maxLen);
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
/* 218 */     getCOSObject().setString(COSName.V, value);
/* 219 */     applyChange();
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
/* 230 */     getCOSObject().setString(COSName.DV, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 240 */     return getStringOrStream(getInheritableAttribute(COSName.V));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultValue() {
/* 250 */     return getStringOrStream(getInheritableAttribute(COSName.DV));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 256 */     return getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void constructAppearances() throws IOException {
/* 263 */     AppearanceGeneratorHelper apHelper = new AppearanceGeneratorHelper(this);
/* 264 */     apHelper.setAppearanceValue(getValue());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDTextField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */