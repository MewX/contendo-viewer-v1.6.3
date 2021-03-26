/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
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
/*     */ public class PDPageLabelRange
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSDictionary root;
/*  36 */   private static final COSName KEY_START = COSName.ST;
/*  37 */   private static final COSName KEY_PREFIX = COSName.P;
/*  38 */   private static final COSName KEY_STYLE = COSName.S;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String STYLE_DECIMAL = "D";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String STYLE_ROMAN_UPPER = "R";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String STYLE_ROMAN_LOWER = "r";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String STYLE_LETTERS_UPPER = "A";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String STYLE_LETTERS_LOWER = "a";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageLabelRange() {
/*  74 */     this(new COSDictionary());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageLabelRange(COSDictionary dict) {
/*  85 */     this.root = dict;
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
/*  96 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStyle() {
/* 106 */     return this.root.getNameAsString(KEY_STYLE);
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
/*     */   public void setStyle(String style) {
/* 118 */     if (style != null) {
/*     */       
/* 120 */       this.root.setName(KEY_STYLE, style);
/*     */     }
/*     */     else {
/*     */       
/* 124 */       this.root.removeItem(KEY_STYLE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStart() {
/* 135 */     return this.root.getInt(KEY_START, 1);
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
/*     */   public void setStart(int start) {
/* 148 */     if (start <= 0)
/*     */     {
/* 150 */       throw new IllegalArgumentException("The page numbering start value must be a positive integer");
/*     */     }
/*     */     
/* 153 */     this.root.setInt(KEY_START, start);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 164 */     return this.root.getString(KEY_PREFIX);
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
/*     */   public void setPrefix(String prefix) {
/* 176 */     if (prefix != null) {
/*     */       
/* 178 */       this.root.setString(KEY_PREFIX, prefix);
/*     */     }
/*     */     else {
/*     */       
/* 182 */       this.root.removeItem(KEY_PREFIX);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDPageLabelRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */