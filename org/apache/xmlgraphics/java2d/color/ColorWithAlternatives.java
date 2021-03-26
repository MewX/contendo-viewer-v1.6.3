/*     */ package org.apache.xmlgraphics.java2d.color;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.color.ColorSpace;
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
/*     */ public class ColorWithAlternatives
/*     */   extends Color
/*     */ {
/*     */   private static final long serialVersionUID = -6125884937776779150L;
/*     */   private Color[] alternativeColors;
/*     */   
/*     */   public ColorWithAlternatives(float r, float g, float b, float a, Color[] alternativeColors) {
/*  56 */     super(r, g, b, a);
/*  57 */     initAlternativeColors(alternativeColors);
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
/*     */   public ColorWithAlternatives(float r, float g, float b, Color[] alternativeColors) {
/*  69 */     super(r, g, b);
/*  70 */     initAlternativeColors(alternativeColors);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorWithAlternatives(int rgba, boolean hasalpha, Color[] alternativeColors) {
/*  81 */     super(rgba, hasalpha);
/*  82 */     initAlternativeColors(alternativeColors);
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
/*     */   public ColorWithAlternatives(int r, int g, int b, int a, Color[] alternativeColors) {
/*  95 */     super(r, g, b, a);
/*  96 */     initAlternativeColors(alternativeColors);
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
/*     */   public ColorWithAlternatives(int r, int g, int b, Color[] alternativeColors) {
/* 108 */     super(r, g, b);
/* 109 */     initAlternativeColors(alternativeColors);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorWithAlternatives(int rgb, Color[] alternativeColors) {
/* 119 */     super(rgb);
/* 120 */     initAlternativeColors(alternativeColors);
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
/*     */   public ColorWithAlternatives(ColorSpace cspace, float[] components, float alpha, Color[] alternativeColors) {
/* 133 */     super(cspace, components, alpha);
/* 134 */     initAlternativeColors(alternativeColors);
/*     */   }
/*     */   
/*     */   private void initAlternativeColors(Color[] colors) {
/* 138 */     if (colors != null) {
/*     */       
/* 140 */       this.alternativeColors = new Color[colors.length];
/* 141 */       System.arraycopy(colors, 0, this.alternativeColors, 0, colors.length);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color[] getAlternativeColors() {
/* 151 */     if (this.alternativeColors != null) {
/* 152 */       Color[] cols = new Color[this.alternativeColors.length];
/* 153 */       System.arraycopy(this.alternativeColors, 0, cols, 0, this.alternativeColors.length);
/* 154 */       return cols;
/*     */     } 
/* 156 */     return new Color[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAlternativeColors() {
/* 165 */     return (this.alternativeColors != null && this.alternativeColors.length > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSameAlternativeColors(ColorWithAlternatives col) {
/* 174 */     if (!hasAlternativeColors()) {
/* 175 */       return !col.hasAlternativeColors();
/*     */     }
/*     */     
/* 178 */     if (!col.hasAlternativeColors()) {
/* 179 */       return false;
/*     */     }
/*     */     
/* 182 */     Color[] alt1 = getAlternativeColors();
/* 183 */     Color[] alt2 = col.getAlternativeColors();
/* 184 */     if (alt1.length != alt2.length) {
/* 185 */       return false;
/*     */     }
/* 187 */     for (int i = 0, c = alt1.length; i < c; i++) {
/* 188 */       Color c1 = alt1[i];
/* 189 */       Color c2 = alt2[i];
/* 190 */       if (!ColorUtil.isSameColor(c1, c2)) {
/* 191 */         return false;
/*     */       }
/*     */     } 
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getFirstAlternativeOfType(int colorSpaceType) {
/* 203 */     if (hasAlternativeColors()) {
/* 204 */       for (int i = 0, c = this.alternativeColors.length; i < c; i++) {
/* 205 */         if (this.alternativeColors[i].getColorSpace().getType() == colorSpaceType) {
/* 206 */           return this.alternativeColors[i];
/*     */         }
/*     */       } 
/*     */     }
/* 210 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 214 */     int hash = super.hashCode();
/* 215 */     if (this.alternativeColors != null) {
/* 216 */       hash = 37 * hash + Arrays.hashCode((Object[])this.alternativeColors);
/*     */     }
/* 218 */     return hash;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/ColorWithAlternatives.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */