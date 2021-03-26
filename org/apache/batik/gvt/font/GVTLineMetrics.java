/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.font.LineMetrics;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVTLineMetrics
/*     */ {
/*     */   protected float ascent;
/*     */   protected int baselineIndex;
/*     */   protected float[] baselineOffsets;
/*     */   protected float descent;
/*     */   protected float height;
/*     */   protected float leading;
/*     */   protected int numChars;
/*     */   protected float strikethroughOffset;
/*     */   protected float strikethroughThickness;
/*     */   protected float underlineOffset;
/*     */   protected float underlineThickness;
/*     */   protected float overlineOffset;
/*     */   protected float overlineThickness;
/*     */   
/*     */   public GVTLineMetrics(LineMetrics lineMetrics) {
/*  52 */     this.ascent = lineMetrics.getAscent();
/*  53 */     this.baselineIndex = lineMetrics.getBaselineIndex();
/*  54 */     this.baselineOffsets = lineMetrics.getBaselineOffsets();
/*  55 */     this.descent = lineMetrics.getDescent();
/*  56 */     this.height = lineMetrics.getHeight();
/*  57 */     this.leading = lineMetrics.getLeading();
/*  58 */     this.numChars = lineMetrics.getNumChars();
/*  59 */     this.strikethroughOffset = lineMetrics.getStrikethroughOffset();
/*  60 */     this.strikethroughThickness = lineMetrics.getStrikethroughThickness();
/*  61 */     this.underlineOffset = lineMetrics.getUnderlineOffset();
/*  62 */     this.underlineThickness = lineMetrics.getUnderlineThickness();
/*  63 */     this.overlineOffset = -this.ascent;
/*  64 */     this.overlineThickness = this.underlineThickness;
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
/*     */   public GVTLineMetrics(LineMetrics lineMetrics, float scaleFactor) {
/*  77 */     this.ascent = lineMetrics.getAscent() * scaleFactor;
/*  78 */     this.baselineIndex = lineMetrics.getBaselineIndex();
/*  79 */     this.baselineOffsets = lineMetrics.getBaselineOffsets();
/*  80 */     for (int i = 0; i < this.baselineOffsets.length; i++) {
/*  81 */       this.baselineOffsets[i] = this.baselineOffsets[i] * scaleFactor;
/*     */     }
/*  83 */     this.descent = lineMetrics.getDescent() * scaleFactor;
/*  84 */     this.height = lineMetrics.getHeight() * scaleFactor;
/*  85 */     this.leading = lineMetrics.getLeading();
/*  86 */     this.numChars = lineMetrics.getNumChars();
/*  87 */     this.strikethroughOffset = lineMetrics.getStrikethroughOffset() * scaleFactor;
/*     */     
/*  89 */     this.strikethroughThickness = lineMetrics.getStrikethroughThickness() * scaleFactor;
/*     */     
/*  91 */     this.underlineOffset = lineMetrics.getUnderlineOffset() * scaleFactor;
/*  92 */     this.underlineThickness = lineMetrics.getUnderlineThickness() * scaleFactor;
/*     */     
/*  94 */     this.overlineOffset = -this.ascent;
/*  95 */     this.overlineThickness = this.underlineThickness;
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
/*     */   public GVTLineMetrics(float ascent, int baselineIndex, float[] baselineOffsets, float descent, float height, float leading, int numChars, float strikethroughOffset, float strikethroughThickness, float underlineOffset, float underlineThickness, float overlineOffset, float overlineThickness) {
/* 115 */     this.ascent = ascent;
/* 116 */     this.baselineIndex = baselineIndex;
/* 117 */     this.baselineOffsets = baselineOffsets;
/* 118 */     this.descent = descent;
/* 119 */     this.height = height;
/* 120 */     this.leading = leading;
/* 121 */     this.numChars = numChars;
/* 122 */     this.strikethroughOffset = strikethroughOffset;
/* 123 */     this.strikethroughThickness = strikethroughThickness;
/* 124 */     this.underlineOffset = underlineOffset;
/* 125 */     this.underlineThickness = underlineThickness;
/* 126 */     this.overlineOffset = overlineOffset;
/* 127 */     this.overlineThickness = overlineThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAscent() {
/* 134 */     return this.ascent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBaselineIndex() {
/* 141 */     return this.baselineIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getBaselineOffsets() {
/* 149 */     return this.baselineOffsets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDescent() {
/* 156 */     return this.descent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 163 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeading() {
/* 170 */     return this.leading;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumChars() {
/* 178 */     return this.numChars;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrikethroughOffset() {
/* 185 */     return this.strikethroughOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrikethroughThickness() {
/* 192 */     return this.strikethroughThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlineOffset() {
/* 199 */     return this.underlineOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlineThickness() {
/* 206 */     return this.underlineThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOverlineOffset() {
/* 213 */     return this.overlineOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOverlineThickness() {
/* 220 */     return this.overlineThickness;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/GVTLineMetrics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */