/*     */ package org.apache.xmlgraphics.java2d.color;
/*     */ 
/*     */ import java.awt.Color;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ColorUtil
/*     */ {
/*     */   public static Color lightenColor(Color col, float factor) {
/*  49 */     float[] cols = new float[4];
/*  50 */     cols = col.getRGBComponents(cols);
/*  51 */     if (factor > 0.0F) {
/*  52 */       cols[0] = (float)(cols[0] + (1.0D - cols[0]) * factor);
/*  53 */       cols[1] = (float)(cols[1] + (1.0D - cols[1]) * factor);
/*  54 */       cols[2] = (float)(cols[2] + (1.0D - cols[2]) * factor);
/*     */     } else {
/*  56 */       cols[0] = cols[0] - cols[0] * -factor;
/*  57 */       cols[1] = cols[1] - cols[1] * -factor;
/*  58 */       cols[2] = cols[2] - cols[2] * -factor;
/*     */     } 
/*  60 */     return new Color(cols[0], cols[1], cols[2], cols[3]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isGray(Color col) {
/*  71 */     return (col.getRed() == col.getBlue() && col.getRed() == col.getGreen());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color toCMYKGrayColor(float black) {
/*  81 */     float[] cmyk = { 0.0F, 0.0F, 0.0F, 1.0F - black };
/*     */     
/*  83 */     return DeviceCMYKColorSpace.createCMYKColor(cmyk);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color toSRGBColor(Color col) {
/*  93 */     if (col.getColorSpace().isCS_sRGB()) {
/*  94 */       return col;
/*     */     }
/*  96 */     float[] comps = col.getColorComponents(null);
/*  97 */     float[] srgb = col.getColorSpace().toRGB(comps);
/*  98 */     comps = col.getComponents(null);
/*  99 */     float alpha = comps[comps.length - 1];
/* 100 */     return new Color(srgb[0], srgb[1], srgb[2], alpha);
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
/*     */   public static boolean isSameColor(Color col1, Color col2) {
/* 118 */     if (!col1.equals(col2)) {
/* 119 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 124 */     Class<?> cl1 = col1.getClass();
/* 125 */     if (col1 instanceof ColorWithAlternatives && !((ColorWithAlternatives)col1).hasAlternativeColors())
/*     */     {
/* 127 */       cl1 = Color.class;
/*     */     }
/* 129 */     Class<?> cl2 = col2.getClass();
/* 130 */     if (col2 instanceof ColorWithAlternatives && !((ColorWithAlternatives)col2).hasAlternativeColors())
/*     */     {
/* 132 */       cl2 = Color.class;
/*     */     }
/* 134 */     if (cl1 != cl2) {
/* 135 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 139 */     if (!col1.getColorSpace().equals(col2.getColorSpace())) {
/* 140 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 144 */     float[] comps1 = col1.getComponents(null);
/* 145 */     float[] comps2 = col2.getComponents(null);
/* 146 */     if (comps1.length != comps2.length) {
/* 147 */       return false;
/*     */     }
/* 149 */     for (int i = 0, c = comps1.length; i < c; i++) {
/* 150 */       if (comps1[i] != comps2[i]) {
/* 151 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 156 */     if (col1 instanceof ColorWithAlternatives && col2 instanceof ColorWithAlternatives) {
/* 157 */       ColorWithAlternatives ca1 = (ColorWithAlternatives)col1;
/* 158 */       ColorWithAlternatives ca2 = (ColorWithAlternatives)col2;
/* 159 */       return ca1.hasSameAlternativeColors(ca2);
/*     */     } 
/*     */     
/* 162 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/ColorUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */