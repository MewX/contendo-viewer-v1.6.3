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
/*     */ public class DeviceCMYKColorSpace
/*     */   extends AbstractDeviceSpecificColorSpace
/*     */   implements ColorSpaceOrigin
/*     */ {
/*     */   private static final long serialVersionUID = 2925508946083542974L;
/*     */   public static final String PSEUDO_PROFILE_NAME = "#CMYK";
/*     */   
/*     */   public DeviceCMYKColorSpace() {
/*  41 */     super(9, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static DeviceCMYKColorSpace getInstance() {
/*  51 */     return ColorSpaces.getDeviceCMYKColorSpace();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] colorvalue) {
/*  57 */     return new float[] { (1.0F - colorvalue[0]) * (1.0F - colorvalue[3]), (1.0F - colorvalue[1]) * (1.0F - colorvalue[3]), (1.0F - colorvalue[2]) * (1.0F - colorvalue[3]) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] fromRGB(float[] rgbvalue) {
/*  66 */     assert rgbvalue.length == 3;
/*     */     
/*  68 */     float r = rgbvalue[0];
/*  69 */     float g = rgbvalue[1];
/*  70 */     float b = rgbvalue[2];
/*  71 */     if (r == g && r == b) {
/*  72 */       return new float[] { 0.0F, 0.0F, 0.0F, 1.0F - r };
/*     */     }
/*  74 */     float c = 1.0F - r;
/*  75 */     float m = 1.0F - g;
/*  76 */     float y = 1.0F - b;
/*  77 */     float k = Math.min(c, Math.min(m, y));
/*  78 */     return new float[] { c, m, y, k };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toCIEXYZ(float[] colorvalue) {
/*  85 */     throw new UnsupportedOperationException("NYI");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] fromCIEXYZ(float[] colorvalue) {
/*  91 */     throw new UnsupportedOperationException("NYI");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color createCMYKColor(float[] cmykComponents) {
/* 102 */     DeviceCMYKColorSpace cmykCs = ColorSpaces.getDeviceCMYKColorSpace();
/* 103 */     Color cmykColor = new ColorWithAlternatives(cmykCs, cmykComponents, 1.0F, null);
/* 104 */     return cmykColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProfileName() {
/* 109 */     return "#CMYK";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProfileURI() {
/* 114 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/DeviceCMYKColorSpace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */