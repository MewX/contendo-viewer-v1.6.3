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
/*     */ 
/*     */ public class NamedColorSpace
/*     */   extends ColorSpace
/*     */   implements ColorSpaceOrigin
/*     */ {
/*     */   private static final long serialVersionUID = -8957543225908514658L;
/*     */   private final String name;
/*     */   private final float[] xyz;
/*     */   private final String profileName;
/*     */   private final String profileURI;
/*     */   
/*     */   public NamedColorSpace(String name, float[] xyz) {
/*  47 */     this(name, xyz, (String)null, (String)null);
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
/*     */   public NamedColorSpace(String name, float[] xyz, String profileName, String profileURI) {
/*  59 */     super(6, 1);
/*  60 */     checkNumComponents(xyz, 3);
/*  61 */     if (name == null || name.trim().length() == 0) {
/*  62 */       throw new IllegalArgumentException("No name provided for named color space");
/*     */     }
/*  64 */     this.name = name.trim();
/*  65 */     this.xyz = new float[3];
/*  66 */     System.arraycopy(xyz, 0, this.xyz, 0, 3);
/*  67 */     this.profileName = profileName;
/*  68 */     this.profileURI = profileURI;
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
/*     */   public NamedColorSpace(String name, Color color, String profileName, String profileURI) {
/*  80 */     this(name, color.getColorSpace().toCIEXYZ(color.getColorComponents(null)), profileName, profileURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedColorSpace(String name, Color color) {
/*  91 */     this(name, color.getColorSpace().toCIEXYZ(color.getColorComponents(null)), (String)null, (String)null);
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
/*     */   public String getColorName() {
/* 109 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProfileName() {
/* 114 */     return this.profileName;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProfileURI() {
/* 119 */     return this.profileURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getXYZ() {
/* 127 */     float[] result = new float[this.xyz.length];
/* 128 */     System.arraycopy(this.xyz, 0, result, 0, this.xyz.length);
/* 129 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getRGBColor() {
/* 138 */     float[] comps = toRGB(this.xyz);
/* 139 */     return new Color(comps[0], comps[1], comps[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMinValue(int component) {
/* 144 */     return getMaxValue(component);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxValue(int component) {
/* 149 */     switch (component) {
/*     */       case 0:
/* 151 */         return 1.0F;
/*     */     } 
/* 153 */     throw new IllegalArgumentException("A named color space only has 1 component!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName(int component) {
/* 159 */     switch (component) {
/*     */       case 0:
/* 161 */         return "Tint";
/*     */     } 
/* 163 */     throw new IllegalArgumentException("A named color space only has 1 component!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] fromCIEXYZ(float[] colorvalue) {
/* 170 */     return new float[] { 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] fromRGB(float[] rgbvalue) {
/* 176 */     return new float[] { 1.0F };
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] toCIEXYZ(float[] colorvalue) {
/* 181 */     float[] ret = new float[3];
/* 182 */     System.arraycopy(this.xyz, 0, ret, 0, this.xyz.length);
/* 183 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] colorvalue) {
/* 188 */     ColorSpace sRGB = ColorSpace.getInstance(1000);
/* 189 */     return sRGB.fromCIEXYZ(this.xyz);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 194 */     if (!(obj instanceof NamedColorSpace)) {
/* 195 */       return false;
/*     */     }
/* 197 */     NamedColorSpace other = (NamedColorSpace)obj;
/* 198 */     if (!this.name.equals(other.name)) {
/* 199 */       return false;
/*     */     }
/* 201 */     for (int i = 0, c = this.xyz.length; i < c; i++) {
/* 202 */       if (this.xyz[i] != other.xyz[i]) {
/* 203 */         return false;
/*     */       }
/*     */     } 
/* 206 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 211 */     return (this.profileName + this.name).hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     return "Named Color Space: " + getColorName();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/NamedColorSpace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */