/*     */ package org.apache.batik.ext.awt;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MultipleGradientPaint
/*     */   implements Paint
/*     */ {
/*     */   protected int transparency;
/*     */   protected float[] fractions;
/*     */   protected Color[] colors;
/*     */   protected AffineTransform gradientTransform;
/*     */   protected CycleMethodEnum cycleMethod;
/*     */   protected ColorSpaceEnum colorSpace;
/*     */   
/*     */   public static class ColorSpaceEnum {}
/*     */   
/*     */   public static class CycleMethodEnum {}
/*     */   
/*  65 */   public static final CycleMethodEnum NO_CYCLE = new CycleMethodEnum();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static final CycleMethodEnum REFLECT = new CycleMethodEnum();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final CycleMethodEnum REPEAT = new CycleMethodEnum();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static final ColorSpaceEnum SRGB = new ColorSpaceEnum();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final ColorSpaceEnum LINEAR_RGB = new ColorSpaceEnum();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MultipleGradientPaint(float[] fractions, Color[] colors, CycleMethodEnum cycleMethod, ColorSpaceEnum colorSpace, AffineTransform gradientTransform) {
/* 117 */     if (fractions == null) {
/* 118 */       throw new IllegalArgumentException("Fractions array cannot be null");
/*     */     }
/*     */ 
/*     */     
/* 122 */     if (colors == null) {
/* 123 */       throw new IllegalArgumentException("Colors array cannot be null");
/*     */     }
/*     */     
/* 126 */     if (fractions.length != colors.length) {
/* 127 */       throw new IllegalArgumentException("Colors and fractions must have equal size");
/*     */     }
/*     */ 
/*     */     
/* 131 */     if (colors.length < 2) {
/* 132 */       throw new IllegalArgumentException("User must specify at least 2 colors");
/*     */     }
/*     */ 
/*     */     
/* 136 */     if (colorSpace != LINEAR_RGB && colorSpace != SRGB)
/*     */     {
/* 138 */       throw new IllegalArgumentException("Invalid colorspace for interpolation.");
/*     */     }
/*     */ 
/*     */     
/* 142 */     if (cycleMethod != NO_CYCLE && cycleMethod != REFLECT && cycleMethod != REPEAT)
/*     */     {
/*     */       
/* 145 */       throw new IllegalArgumentException("Invalid cycle method.");
/*     */     }
/*     */     
/* 148 */     if (gradientTransform == null) {
/* 149 */       throw new IllegalArgumentException("Gradient transform cannot be null.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.fractions = new float[fractions.length];
/* 155 */     System.arraycopy(fractions, 0, this.fractions, 0, fractions.length);
/*     */ 
/*     */     
/* 158 */     this.colors = new Color[colors.length];
/* 159 */     System.arraycopy(colors, 0, this.colors, 0, colors.length);
/*     */ 
/*     */     
/* 162 */     this.colorSpace = colorSpace;
/* 163 */     this.cycleMethod = cycleMethod;
/*     */ 
/*     */     
/* 166 */     this.gradientTransform = (AffineTransform)gradientTransform.clone();
/*     */ 
/*     */     
/* 169 */     boolean opaque = true;
/* 170 */     for (Color color : colors) {
/* 171 */       opaque = (opaque && color.getAlpha() == 255);
/*     */     }
/*     */     
/* 174 */     if (opaque) {
/* 175 */       this.transparency = 1;
/*     */     }
/*     */     else {
/*     */       
/* 179 */       this.transparency = 3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color[] getColors() {
/* 189 */     Color[] colors = new Color[this.colors.length];
/* 190 */     System.arraycopy(this.colors, 0, colors, 0, this.colors.length);
/* 191 */     return colors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getFractions() {
/* 202 */     float[] fractions = new float[this.fractions.length];
/* 203 */     System.arraycopy(this.fractions, 0, fractions, 0, this.fractions.length);
/* 204 */     return fractions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTransparency() {
/* 213 */     return this.transparency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CycleMethodEnum getCycleMethod() {
/* 221 */     return this.cycleMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorSpaceEnum getColorSpace() {
/* 231 */     return this.colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 239 */     return (AffineTransform)this.gradientTransform.clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/MultipleGradientPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */