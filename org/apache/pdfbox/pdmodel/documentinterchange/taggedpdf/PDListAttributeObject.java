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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDListAttributeObject
/*     */   extends PDStandardAttributeObject
/*     */ {
/*     */   public static final String OWNER_LIST = "List";
/*     */   protected static final String LIST_NUMBERING = "ListNumbering";
/*     */   public static final String LIST_NUMBERING_CIRCLE = "Circle";
/*     */   public static final String LIST_NUMBERING_DECIMAL = "Decimal";
/*     */   public static final String LIST_NUMBERING_DISC = "Disc";
/*     */   public static final String LIST_NUMBERING_LOWER_ALPHA = "LowerAlpha";
/*     */   public static final String LIST_NUMBERING_LOWER_ROMAN = "LowerRoman";
/*     */   public static final String LIST_NUMBERING_NONE = "None";
/*     */   public static final String LIST_NUMBERING_SQUARE = "Square";
/*     */   public static final String LIST_NUMBERING_UPPER_ALPHA = "UpperAlpha";
/*     */   public static final String LIST_NUMBERING_UPPER_ROMAN = "UpperRoman";
/*     */   
/*     */   public PDListAttributeObject() {
/*  80 */     setOwner("List");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDListAttributeObject(COSDictionary dictionary) {
/*  90 */     super(dictionary);
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
/* 102 */     return getName("ListNumbering", "None");
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
/*     */   public void setListNumbering(String listNumbering) {
/* 124 */     setName("ListNumbering", listNumbering);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     StringBuilder sb = (new StringBuilder()).append(super.toString());
/* 131 */     if (isSpecified("ListNumbering"))
/*     */     {
/* 133 */       sb.append(", ListNumbering=").append(getListNumbering());
/*     */     }
/* 135 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDListAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */