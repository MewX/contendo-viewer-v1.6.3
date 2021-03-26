/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDVariableText
/*     */   extends PDTerminalField
/*     */ {
/*     */   public static final int QUADDING_LEFT = 0;
/*     */   public static final int QUADDING_CENTERED = 1;
/*     */   public static final int QUADDING_RIGHT = 2;
/*     */   
/*     */   PDVariableText(PDAcroForm acroForm) {
/*  48 */     super(acroForm);
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
/*     */   PDVariableText(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/*  60 */     super(acroForm, field, parent);
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
/*     */   public String getDefaultAppearance() {
/*  75 */     COSString defaultAppearance = (COSString)getInheritableAttribute(COSName.DA);
/*  76 */     return defaultAppearance.getString();
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
/*     */   PDDefaultAppearanceString getDefaultAppearanceString() throws IOException {
/*  91 */     COSString da = (COSString)getInheritableAttribute(COSName.DA);
/*  92 */     PDResources dr = getAcroForm().getDefaultResources();
/*  93 */     return new PDDefaultAppearanceString(da, dr);
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
/*     */   public void setDefaultAppearance(String daValue) {
/* 118 */     getCOSObject().setString(COSName.DA, daValue);
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
/*     */   public String getDefaultStyleString() {
/* 131 */     COSString defaultStyleString = (COSString)getCOSObject().getDictionaryObject(COSName.DS);
/* 132 */     return defaultStyleString.getString();
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
/*     */   public void setDefaultStyleString(String defaultStyleString) {
/* 144 */     if (defaultStyleString != null) {
/*     */       
/* 146 */       getCOSObject().setItem(COSName.DS, (COSBase)new COSString(defaultStyleString));
/*     */     }
/*     */     else {
/*     */       
/* 150 */       getCOSObject().removeItem(COSName.DS);
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
/*     */   public int getQ() {
/* 168 */     int retval = 0;
/*     */     
/* 170 */     COSNumber number = (COSNumber)getInheritableAttribute(COSName.Q);
/*     */     
/* 172 */     if (number != null)
/*     */     {
/* 174 */       retval = number.intValue();
/*     */     }
/* 176 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQ(int q) {
/* 186 */     getCOSObject().setInt(COSName.Q, q);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRichTextValue() throws IOException {
/* 197 */     return getStringOrStream(getInheritableAttribute(COSName.RV));
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
/*     */   public void setRichTextValue(String richTextValue) {
/* 217 */     if (richTextValue != null) {
/*     */       
/* 219 */       getCOSObject().setItem(COSName.RV, (COSBase)new COSString(richTextValue));
/*     */     }
/*     */     else {
/*     */       
/* 223 */       getCOSObject().removeItem(COSName.RV);
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
/*     */   protected final String getStringOrStream(COSBase base) {
/* 237 */     if (base == null)
/*     */     {
/* 239 */       return "";
/*     */     }
/* 241 */     if (base instanceof COSString)
/*     */     {
/* 243 */       return ((COSString)base).getString();
/*     */     }
/* 245 */     if (base instanceof COSStream)
/*     */     {
/* 247 */       return ((COSStream)base).toTextString();
/*     */     }
/*     */ 
/*     */     
/* 251 */     return "";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDVariableText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */