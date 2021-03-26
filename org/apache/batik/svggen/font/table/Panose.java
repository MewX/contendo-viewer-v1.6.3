/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Panose
/*     */ {
/*  27 */   byte bFamilyType = 0;
/*  28 */   byte bSerifStyle = 0;
/*  29 */   byte bWeight = 0;
/*  30 */   byte bProportion = 0;
/*  31 */   byte bContrast = 0;
/*  32 */   byte bStrokeVariation = 0;
/*  33 */   byte bArmStyle = 0;
/*  34 */   byte bLetterform = 0;
/*  35 */   byte bMidline = 0;
/*  36 */   byte bXHeight = 0;
/*     */ 
/*     */   
/*     */   public Panose(byte[] panose) {
/*  40 */     this.bFamilyType = panose[0];
/*  41 */     this.bSerifStyle = panose[1];
/*  42 */     this.bWeight = panose[2];
/*  43 */     this.bProportion = panose[3];
/*  44 */     this.bContrast = panose[4];
/*  45 */     this.bStrokeVariation = panose[5];
/*  46 */     this.bArmStyle = panose[6];
/*  47 */     this.bLetterform = panose[7];
/*  48 */     this.bMidline = panose[8];
/*  49 */     this.bXHeight = panose[9];
/*     */   }
/*     */   
/*     */   public byte getFamilyType() {
/*  53 */     return this.bFamilyType;
/*     */   }
/*     */   
/*     */   public byte getSerifStyle() {
/*  57 */     return this.bSerifStyle;
/*     */   }
/*     */   
/*     */   public byte getWeight() {
/*  61 */     return this.bWeight;
/*     */   }
/*     */   
/*     */   public byte getProportion() {
/*  65 */     return this.bProportion;
/*     */   }
/*     */   
/*     */   public byte getContrast() {
/*  69 */     return this.bContrast;
/*     */   }
/*     */   
/*     */   public byte getStrokeVariation() {
/*  73 */     return this.bStrokeVariation;
/*     */   }
/*     */   
/*     */   public byte getArmStyle() {
/*  77 */     return this.bArmStyle;
/*     */   }
/*     */   
/*     */   public byte getLetterForm() {
/*  81 */     return this.bLetterform;
/*     */   }
/*     */   
/*     */   public byte getMidline() {
/*  85 */     return this.bMidline;
/*     */   }
/*     */   
/*     */   public byte getXHeight() {
/*  89 */     return this.bXHeight;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  93 */     StringBuffer sb = new StringBuffer();
/*  94 */     sb.append(String.valueOf(this.bFamilyType)).append(" ").append(String.valueOf(this.bSerifStyle)).append(" ").append(String.valueOf(this.bWeight)).append(" ").append(String.valueOf(this.bProportion)).append(" ").append(String.valueOf(this.bContrast)).append(" ").append(String.valueOf(this.bStrokeVariation)).append(" ").append(String.valueOf(this.bArmStyle)).append(" ").append(String.valueOf(this.bLetterform)).append(" ").append(String.valueOf(this.bMidline)).append(" ").append(String.valueOf(this.bXHeight));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Panose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */