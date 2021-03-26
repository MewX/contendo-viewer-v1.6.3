/*     */ package org.apache.pdfbox.pdmodel.interactive.measurement;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDNumberFormatDictionary
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String TYPE = "NumberFormat";
/*     */   public static final String LABEL_SUFFIX_TO_VALUE = "S";
/*     */   public static final String LABEL_PREFIX_TO_VALUE = "P";
/*     */   public static final String FRACTIONAL_DISPLAY_DECIMAL = "D";
/*     */   public static final String FRACTIONAL_DISPLAY_FRACTION = "F";
/*     */   public static final String FRACTIONAL_DISPLAY_ROUND = "R";
/*     */   public static final String FRACTIONAL_DISPLAY_TRUNCATE = "T";
/*     */   private COSDictionary numberFormatDictionary;
/*     */   
/*     */   public PDNumberFormatDictionary() {
/*  68 */     this.numberFormatDictionary = new COSDictionary();
/*  69 */     this.numberFormatDictionary.setName(COSName.TYPE, "NumberFormat");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberFormatDictionary(COSDictionary dictionary) {
/*  79 */     this.numberFormatDictionary = dictionary;
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
/*  90 */     return this.numberFormatDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 101 */     return "NumberFormat";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnits() {
/* 111 */     return getCOSObject().getString("U");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnits(String units) {
/* 121 */     getCOSObject().setString("U", units);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getConversionFactor() {
/* 131 */     return getCOSObject().getFloat("C");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConversionFactor(float conversionFactor) {
/* 141 */     getCOSObject().setFloat("C", conversionFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFractionalDisplay() {
/* 151 */     return getCOSObject().getString("F", "D");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFractionalDisplay(String fractionalDisplay) {
/* 161 */     if (fractionalDisplay == null || "D"
/* 162 */       .equals(fractionalDisplay) || "F"
/* 163 */       .equals(fractionalDisplay) || "R"
/* 164 */       .equals(fractionalDisplay) || "T"
/* 165 */       .equals(fractionalDisplay)) {
/*     */       
/* 167 */       getCOSObject().setString("F", fractionalDisplay);
/*     */     }
/*     */     else {
/*     */       
/* 171 */       throw new IllegalArgumentException("Value must be \"D\", \"F\", \"R\", or \"T\", (or null).");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDenominator() {
/* 182 */     return getCOSObject().getInt("D");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDenominator(int denominator) {
/* 192 */     getCOSObject().setInt("D", denominator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFD() {
/* 202 */     return getCOSObject().getBoolean("FD", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFD(boolean fd) {
/* 212 */     getCOSObject().setBoolean("FD", fd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getThousandsSeparator() {
/* 222 */     return getCOSObject().getString("RT", ",");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThousandsSeparator(String thousandsSeparator) {
/* 232 */     getCOSObject().setString("RT", thousandsSeparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDecimalSeparator() {
/* 242 */     return getCOSObject().getString("RD", ".");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecimalSeparator(String decimalSeparator) {
/* 252 */     getCOSObject().setString("RD", decimalSeparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabelPrefixString() {
/* 261 */     return getCOSObject().getString("PS", " ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabelPrefixString(String labelPrefixString) {
/* 270 */     getCOSObject().setString("PS", labelPrefixString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabelSuffixString() {
/* 280 */     return getCOSObject().getString("SS", " ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabelSuffixString(String labelSuffixString) {
/* 290 */     getCOSObject().setString("SS", labelSuffixString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabelPositionToValue() {
/* 300 */     return getCOSObject().getString("O", "S");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabelPositionToValue(String labelPositionToValue) {
/* 311 */     if (labelPositionToValue == null || "P"
/* 312 */       .equals(labelPositionToValue) || "S"
/* 313 */       .equals(labelPositionToValue)) {
/*     */       
/* 315 */       getCOSObject().setString("O", labelPositionToValue);
/*     */     }
/*     */     else {
/*     */       
/* 319 */       throw new IllegalArgumentException("Value must be \"S\", or \"P\" (or null).");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/measurement/PDNumberFormatDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */