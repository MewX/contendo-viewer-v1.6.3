/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import org.apache.xml.utils.QName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DecimalFormatProperties
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   DecimalFormatSymbols m_dfs;
/*     */   private QName m_qname;
/*     */   
/*     */   public DecimalFormatProperties(int docOrderNumber) {
/*  98 */     this.m_qname = null;
/*     */     this.m_dfs = new DecimalFormatSymbols();
/*     */     this.m_dfs.setInfinity("Infinity");
/*     */     this.m_dfs.setNaN("NaN");
/*     */     this.m_docOrderNumber = docOrderNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName qname) {
/* 109 */     this.m_qname = qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecimalFormatSymbols getDecimalFormatSymbols() {
/*     */     return this.m_dfs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getName() {
/* 122 */     if (this.m_qname == null) {
/* 123 */       return new QName("");
/*     */     }
/* 125 */     return this.m_qname;
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
/*     */   public void setDecimalSeparator(char ds) {
/* 137 */     this.m_dfs.setDecimalSeparator(ds);
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
/*     */   public char getDecimalSeparator() {
/* 149 */     return this.m_dfs.getDecimalSeparator();
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
/*     */   public void setGroupingSeparator(char gs) {
/* 161 */     this.m_dfs.setGroupingSeparator(gs);
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
/*     */   public char getGroupingSeparator() {
/* 173 */     return this.m_dfs.getGroupingSeparator();
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
/*     */   public void setInfinity(String inf) {
/* 185 */     this.m_dfs.setInfinity(inf);
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
/*     */   public String getInfinity() {
/* 197 */     return this.m_dfs.getInfinity();
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
/*     */   public void setMinusSign(char v) {
/* 209 */     this.m_dfs.setMinusSign(v);
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
/*     */   public char getMinusSign() {
/* 221 */     return this.m_dfs.getMinusSign();
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
/*     */   public void setNaN(String v) {
/* 233 */     this.m_dfs.setNaN(v);
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
/*     */   public String getNaN() {
/* 245 */     return this.m_dfs.getNaN();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 255 */     return "decimal-format";
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
/*     */   public void setPercent(char v) {
/* 267 */     this.m_dfs.setPercent(v);
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
/*     */   public char getPercent() {
/* 279 */     return this.m_dfs.getPercent();
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
/*     */   public void setPerMille(char v) {
/* 291 */     this.m_dfs.setPerMill(v);
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
/*     */   public char getPerMille() {
/* 303 */     return this.m_dfs.getPerMill();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 314 */     return 83;
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
/*     */   public void setZeroDigit(char v) {
/* 326 */     this.m_dfs.setZeroDigit(v);
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
/*     */   public char getZeroDigit() {
/* 338 */     return this.m_dfs.getZeroDigit();
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
/*     */   public void setDigit(char v) {
/* 350 */     this.m_dfs.setDigit(v);
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
/*     */   public char getDigit() {
/* 362 */     return this.m_dfs.getDigit();
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
/*     */   public void setPatternSeparator(char v) {
/* 375 */     this.m_dfs.setPatternSeparator(v);
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
/*     */   public char getPatternSeparator() {
/* 388 */     return this.m_dfs.getPatternSeparator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recompose(StylesheetRoot root) {
/* 398 */     root.recomposeDecimalFormats(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/DecimalFormatProperties.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */