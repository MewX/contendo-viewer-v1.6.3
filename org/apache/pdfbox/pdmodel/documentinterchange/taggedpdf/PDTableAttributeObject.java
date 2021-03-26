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
/*     */ public class PDTableAttributeObject
/*     */   extends PDStandardAttributeObject
/*     */ {
/*     */   public static final String OWNER_TABLE = "Table";
/*     */   protected static final String ROW_SPAN = "RowSpan";
/*     */   protected static final String COL_SPAN = "ColSpan";
/*     */   protected static final String HEADERS = "Headers";
/*     */   protected static final String SCOPE = "Scope";
/*     */   protected static final String SUMMARY = "Summary";
/*     */   public static final String SCOPE_BOTH = "Both";
/*     */   public static final String SCOPE_COLUMN = "Column";
/*     */   public static final String SCOPE_ROW = "Row";
/*     */   
/*     */   public PDTableAttributeObject() {
/*  59 */     setOwner("Table");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTableAttributeObject(COSDictionary dictionary) {
/*  69 */     super(dictionary);
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
/*     */   public int getRowSpan() {
/*  81 */     return getInteger("RowSpan", 1);
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
/*  92 */     setInteger("RowSpan", rowSpan);
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
/* 103 */     return getInteger("ColSpan", 1);
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
/* 114 */     setInteger("ColSpan", colSpan);
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
/* 127 */     return getArrayOfString("Headers");
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
/* 140 */     setArrayOfString("Headers", headers);
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
/* 152 */     return getName("Scope");
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
/* 170 */     setName("Scope", scope);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSummary() {
/* 180 */     return getString("Summary");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSummary(String summary) {
/* 190 */     setString("Summary", summary);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 196 */     StringBuilder sb = (new StringBuilder()).append(super.toString());
/* 197 */     if (isSpecified("RowSpan"))
/*     */     {
/* 199 */       sb.append(", RowSpan=").append(String.valueOf(getRowSpan()));
/*     */     }
/* 201 */     if (isSpecified("ColSpan"))
/*     */     {
/* 203 */       sb.append(", ColSpan=").append(String.valueOf(getColSpan()));
/*     */     }
/* 205 */     if (isSpecified("Headers"))
/*     */     {
/* 207 */       sb.append(", Headers=").append(arrayToString((Object[])getHeaders()));
/*     */     }
/* 209 */     if (isSpecified("Scope"))
/*     */     {
/* 211 */       sb.append(", Scope=").append(getScope());
/*     */     }
/* 213 */     if (isSpecified("Summary"))
/*     */     {
/* 215 */       sb.append(", Summary=").append(getSummary());
/*     */     }
/* 217 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDTableAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */