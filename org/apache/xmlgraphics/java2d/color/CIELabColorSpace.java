/*     */ package org.apache.xmlgraphics.java2d.color;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.color.ColorSpace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CIELabColorSpace
/*     */   extends ColorSpace
/*     */ {
/*     */   private static final long serialVersionUID = -1821569090707520704L;
/*     */   private static final float REF_X_D65 = 95.047F;
/*     */   private static final float REF_Y_D65 = 100.0F;
/*     */   private static final float REF_Z_D65 = 108.883F;
/*     */   private static final float REF_X_D50 = 96.42F;
/*     */   private static final float REF_Y_D50 = 100.0F;
/*     */   private static final float REF_Z_D50 = 82.49F;
/*     */   private static final double D = 0.20689655172413793D;
/*  46 */   private static final double REF_A = 1.0D / 3.0D * Math.pow(0.20689655172413793D, 2.0D);
/*     */   private static final double REF_B = 0.13793103448275862D;
/*  48 */   private static final double T0 = Math.pow(0.20689655172413793D, 3.0D);
/*     */   
/*     */   private float wpX;
/*     */   
/*     */   private float wpY;
/*     */   
/*     */   private float wpZ;
/*     */   private static final String CIE_LAB_ONLY_HAS_3_COMPONENTS = "CIE Lab only has 3 components!";
/*     */   
/*     */   public CIELabColorSpace() {
/*  58 */     this(getD65WhitePoint());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CIELabColorSpace(float[] whitePoint) {
/*  67 */     super(1, 3);
/*  68 */     checkNumComponents(whitePoint, 3);
/*  69 */     this.wpX = whitePoint[0];
/*  70 */     this.wpY = whitePoint[1];
/*  71 */     this.wpZ = whitePoint[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] getD65WhitePoint() {
/*  79 */     return new float[] { 95.047F, 100.0F, 108.883F };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] getD50WhitePoint() {
/*  87 */     return new float[] { 96.42F, 100.0F, 82.49F };
/*     */   }
/*     */   
/*     */   private void checkNumComponents(float[] colorvalue) {
/*  91 */     checkNumComponents(colorvalue, getNumComponents());
/*     */   }
/*     */   
/*     */   private void checkNumComponents(float[] colorvalue, int expected) {
/*  95 */     if (colorvalue == null) {
/*  96 */       throw new NullPointerException("color value may not be null");
/*     */     }
/*  98 */     if (colorvalue.length != expected) {
/*  99 */       throw new IllegalArgumentException("Expected " + expected + " components, but got " + colorvalue.length);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getWhitePoint() {
/* 109 */     return new float[] { this.wpX, this.wpY, this.wpZ };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMinValue(int component) {
/* 117 */     switch (component) {
/*     */       case 0:
/* 119 */         return 0.0F;
/*     */       case 1:
/*     */       case 2:
/* 122 */         return -128.0F;
/*     */     } 
/* 124 */     throw new IllegalArgumentException("CIE Lab only has 3 components!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxValue(int component) {
/* 131 */     switch (component) {
/*     */       case 0:
/* 133 */         return 100.0F;
/*     */       case 1:
/*     */       case 2:
/* 136 */         return 128.0F;
/*     */     } 
/* 138 */     throw new IllegalArgumentException("CIE Lab only has 3 components!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int component) {
/* 145 */     switch (component) {
/*     */       case 0:
/* 147 */         return "L*";
/*     */       case 1:
/* 149 */         return "a*";
/*     */       case 2:
/* 151 */         return "b*";
/*     */     } 
/* 153 */     throw new IllegalArgumentException("CIE Lab only has 3 components!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] fromCIEXYZ(float[] colorvalue) {
/* 163 */     checkNumComponents(colorvalue, 3);
/* 164 */     float x = colorvalue[0];
/* 165 */     float y = colorvalue[1];
/* 166 */     float z = colorvalue[2];
/*     */     
/* 168 */     double varX = (x / this.wpX);
/* 169 */     double varY = (y / this.wpY);
/* 170 */     double varZ = (z / this.wpZ);
/*     */     
/* 172 */     if (varX > T0) {
/* 173 */       varX = Math.pow(varX, 0.3333333333333333D);
/*     */     } else {
/* 175 */       varX = REF_A * varX + 0.13793103448275862D;
/*     */     } 
/* 177 */     if (varY > T0) {
/* 178 */       varY = Math.pow(varY, 0.3333333333333333D);
/*     */     } else {
/* 180 */       varY = REF_A * varY + 0.13793103448275862D;
/*     */     } 
/* 182 */     if (varZ > T0) {
/* 183 */       varZ = Math.pow(varZ, 0.3333333333333333D);
/*     */     } else {
/* 185 */       varZ = REF_A * varZ + 0.13793103448275862D;
/*     */     } 
/*     */     
/* 188 */     float l = (float)(116.0D * varY - 16.0D);
/* 189 */     float a = (float)(500.0D * (varX - varY));
/* 190 */     float b = (float)(200.0D * (varY - varZ));
/*     */ 
/*     */     
/* 193 */     l = normalize(l, 0);
/* 194 */     a = normalize(a, 1);
/* 195 */     b = normalize(b, 2);
/* 196 */     return new float[] { l, a, b };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] fromRGB(float[] rgbvalue) {
/* 202 */     ColorSpace sRGB = ColorSpace.getInstance(1000);
/* 203 */     float[] xyz = sRGB.toCIEXYZ(rgbvalue);
/* 204 */     return fromCIEXYZ(xyz);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toCIEXYZ(float[] colorvalue) {
/* 210 */     checkNumComponents(colorvalue);
/*     */     
/* 212 */     float l = denormalize(colorvalue[0], 0);
/* 213 */     float a = denormalize(colorvalue[1], 1);
/* 214 */     float b = denormalize(colorvalue[2], 2);
/*     */     
/* 216 */     return toCIEXYZNative(l, a, b);
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
/*     */   public float[] toCIEXYZNative(float l, float a, float b) {
/* 230 */     double varY = (l + 16.0F) / 116.0D;
/* 231 */     double varX = (a / 500.0F) + varY;
/* 232 */     double varZ = varY - b / 200.0D;
/*     */     
/* 234 */     if (Math.pow(varY, 3.0D) > T0) {
/* 235 */       varY = Math.pow(varY, 3.0D);
/*     */     } else {
/* 237 */       varY = (varY - 0.13793103448275862D) / REF_A;
/*     */     } 
/* 239 */     if (Math.pow(varX, 3.0D) > T0) {
/* 240 */       varX = Math.pow(varX, 3.0D);
/*     */     } else {
/* 242 */       varX = (varX - 0.13793103448275862D) / REF_A;
/*     */     } 
/* 244 */     if (Math.pow(varZ, 3.0D) > T0) {
/* 245 */       varZ = Math.pow(varZ, 3.0D);
/*     */     } else {
/* 247 */       varZ = (varZ - 0.13793103448275862D) / REF_A;
/*     */     } 
/*     */     
/* 250 */     float x = (float)(this.wpX * varX / 100.0D);
/* 251 */     float y = (float)(this.wpY * varY / 100.0D);
/* 252 */     float z = (float)(this.wpZ * varZ / 100.0D);
/*     */     
/* 254 */     return new float[] { x, y, z };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] colorvalue) {
/* 260 */     ColorSpace sRGB = ColorSpace.getInstance(1000);
/* 261 */     float[] xyz = toCIEXYZ(colorvalue);
/* 262 */     return sRGB.fromCIEXYZ(xyz);
/*     */   }
/*     */   
/*     */   private float getNativeValueRange(int component) {
/* 266 */     return getMaxValue(component) - getMinValue(component);
/*     */   }
/*     */   
/*     */   private float normalize(float value, int component) {
/* 270 */     return (value - getMinValue(component)) / getNativeValueRange(component);
/*     */   }
/*     */   
/*     */   private float denormalize(float value, int component) {
/* 274 */     return value * getNativeValueRange(component) + getMinValue(component);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toNativeComponents(float[] comps) {
/* 283 */     checkNumComponents(comps);
/* 284 */     float[] nativeComps = new float[comps.length];
/* 285 */     for (int i = 0, c = comps.length; i < c; i++) {
/* 286 */       nativeComps[i] = denormalize(comps[i], i);
/*     */     }
/* 288 */     return nativeComps;
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
/*     */   public Color toColor(float[] colorvalue, float alpha) {
/* 300 */     int c = colorvalue.length;
/* 301 */     float[] normalized = new float[c];
/* 302 */     for (int i = 0; i < c; i++) {
/* 303 */       normalized[i] = normalize(colorvalue[i], i);
/*     */     }
/*     */     
/* 306 */     return new ColorWithAlternatives(this, normalized, alpha, null);
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
/*     */   public Color toColor(float l, float a, float b, float alpha) {
/* 319 */     return toColor(new float[] { l, a, b }, alpha);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/CIELabColorSpace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */