/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDExportFormatAttributeObject
/*     */   extends PDLayoutAttributeObject
/*     */ {
/*     */   public static final String OWNER_XML_1_00 = "XML-1.00";
/*     */   public static final String OWNER_HTML_3_20 = "HTML-3.2";
/*     */   public static final String OWNER_HTML_4_01 = "HTML-4.01";
/*     */   public static final String OWNER_OEB_1_00 = "OEB-1.00";
/*     */   public static final String OWNER_RTF_1_05 = "RTF-1.05";
/*     */   public static final String OWNER_CSS_1_00 = "CSS-1.00";
/*     */   public static final String OWNER_CSS_2_00 = "CSS-2.00";
/*     */   
/*     */   public PDExportFormatAttributeObject(String owner) {
/*  66 */     setOwner(owner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDExportFormatAttributeObject(COSDictionary dictionary) {
/*  76 */     super(dictionary);
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
/*     */   public String getListNumbering() {
/*  88 */     return getName("ListNumbering", "None");
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
/*     */   public void setListNumbering(String listNumbering) {
/* 111 */     setName("ListNumbering", listNumbering);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowSpan() {
/* 122 */     return getInteger("RowSpan", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRowSpan(int rowSpan) {
/* 133 */     setInteger("RowSpan", rowSpan);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColSpan() {
/* 144 */     return getInteger("ColSpan", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColSpan(int colSpan) {
/* 155 */     setInteger("ColSpan", colSpan);
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
/*     */   public String[] getHeaders() {
/* 168 */     return getArrayOfString("Headers");
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
/*     */   public void setHeaders(String[] headers) {
/* 181 */     setArrayOfString("Headers", headers);
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
/*     */   public String getScope() {
/* 193 */     return getName("Scope");
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
/*     */   public void setScope(String scope) {
/* 211 */     setName("Scope", scope);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSummary() {
/* 221 */     return getString("Summary");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSummary(String summary) {
/* 231 */     setString("Summary", summary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 238 */     StringBuilder sb = (new StringBuilder()).append(super.toString());
/* 239 */     if (isSpecified("ListNumbering"))
/*     */     {
/* 241 */       sb.append(", ListNumbering=").append(getListNumbering());
/*     */     }
/* 243 */     if (isSpecified("RowSpan"))
/*     */     {
/* 245 */       sb.append(", RowSpan=").append(String.valueOf(getRowSpan()));
/*     */     }
/* 247 */     if (isSpecified("ColSpan"))
/*     */     {
/* 249 */       sb.append(", ColSpan=").append(String.valueOf(getColSpan()));
/*     */     }
/* 251 */     if (isSpecified("Headers"))
/*     */     {
/* 253 */       sb.append(", Headers=").append(arrayToString((Object[])getHeaders()));
/*     */     }
/* 255 */     if (isSpecified("Scope"))
/*     */     {
/* 257 */       sb.append(", Scope=").append(getScope());
/*     */     }
/* 259 */     if (isSpecified("Summary"))
/*     */     {
/* 261 */       sb.append(", Summary=").append(getSummary());
/*     */     }
/* 263 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDExportFormatAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */