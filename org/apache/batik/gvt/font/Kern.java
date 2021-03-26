/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Kern
/*     */ {
/*     */   private int[] firstGlyphCodes;
/*     */   private int[] secondGlyphCodes;
/*     */   private UnicodeRange[] firstUnicodeRanges;
/*     */   private UnicodeRange[] secondUnicodeRanges;
/*     */   private float kerningAdjust;
/*     */   
/*     */   public Kern(int[] firstGlyphCodes, int[] secondGlyphCodes, UnicodeRange[] firstUnicodeRanges, UnicodeRange[] secondUnicodeRanges, float adjustValue) {
/*  61 */     this.firstGlyphCodes = firstGlyphCodes;
/*  62 */     this.secondGlyphCodes = secondGlyphCodes;
/*  63 */     this.firstUnicodeRanges = firstUnicodeRanges;
/*  64 */     this.secondUnicodeRanges = secondUnicodeRanges;
/*  65 */     this.kerningAdjust = adjustValue;
/*     */     
/*  67 */     if (firstGlyphCodes != null)
/*  68 */       Arrays.sort(this.firstGlyphCodes); 
/*  69 */     if (secondGlyphCodes != null) {
/*  70 */       Arrays.sort(this.secondGlyphCodes);
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
/*     */   public boolean matchesFirstGlyph(int glyphCode, String glyphUnicode) {
/*  83 */     if (this.firstGlyphCodes != null) {
/*  84 */       int pt = Arrays.binarySearch(this.firstGlyphCodes, glyphCode);
/*  85 */       if (pt >= 0) return true; 
/*     */     } 
/*  87 */     if (glyphUnicode.length() < 1) return false; 
/*  88 */     char glyphChar = glyphUnicode.charAt(0);
/*  89 */     for (UnicodeRange firstUnicodeRange : this.firstUnicodeRanges) {
/*  90 */       if (firstUnicodeRange.contains(glyphChar))
/*  91 */         return true; 
/*     */     } 
/*  93 */     return false;
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
/*     */   public boolean matchesFirstGlyph(int glyphCode, char glyphUnicode) {
/* 106 */     if (this.firstGlyphCodes != null) {
/* 107 */       int pt = Arrays.binarySearch(this.firstGlyphCodes, glyphCode);
/* 108 */       if (pt >= 0) return true; 
/*     */     } 
/* 110 */     for (UnicodeRange firstUnicodeRange : this.firstUnicodeRanges) {
/* 111 */       if (firstUnicodeRange.contains(glyphUnicode))
/* 112 */         return true; 
/*     */     } 
/* 114 */     return false;
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
/*     */   public boolean matchesSecondGlyph(int glyphCode, String glyphUnicode) {
/* 128 */     if (this.secondGlyphCodes != null) {
/* 129 */       int pt = Arrays.binarySearch(this.secondGlyphCodes, glyphCode);
/* 130 */       if (pt >= 0) return true; 
/*     */     } 
/* 132 */     if (glyphUnicode.length() < 1) return false; 
/* 133 */     char glyphChar = glyphUnicode.charAt(0);
/* 134 */     for (UnicodeRange secondUnicodeRange : this.secondUnicodeRanges) {
/* 135 */       if (secondUnicodeRange.contains(glyphChar))
/* 136 */         return true; 
/*     */     } 
/* 138 */     return false;
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
/*     */   public boolean matchesSecondGlyph(int glyphCode, char glyphUnicode) {
/* 152 */     if (this.secondGlyphCodes != null) {
/* 153 */       int pt = Arrays.binarySearch(this.secondGlyphCodes, glyphCode);
/* 154 */       if (pt >= 0) return true; 
/*     */     } 
/* 156 */     for (UnicodeRange secondUnicodeRange : this.secondUnicodeRanges) {
/* 157 */       if (secondUnicodeRange.contains(glyphUnicode))
/* 158 */         return true; 
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAdjustValue() {
/* 170 */     return this.kerningAdjust;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/Kern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */