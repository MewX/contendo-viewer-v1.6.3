/*     */ package org.apache.xmlgraphics.util;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnitConv
/*     */ {
/*     */   public static final float IN2MM = 25.4F;
/*     */   public static final float IN2CM = 2.54F;
/*     */   public static final int IN2PT = 72;
/*     */   public static final String PICA = "pc";
/*     */   public static final String POINT = "pt";
/*     */   public static final String MM = "mm";
/*     */   public static final String CM = "cm";
/*     */   public static final String INCH = "in";
/*     */   public static final String MPT = "mpt";
/*     */   public static final String PX = "px";
/*     */   
/*     */   public static double mm2pt(double mm) {
/*  69 */     return mm * 72.0D / 25.399999618530273D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double mm2mpt(double mm) {
/*  78 */     return mm * 1000.0D * 72.0D / 25.399999618530273D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double pt2mm(double pt) {
/*  87 */     return pt * 25.399999618530273D / 72.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double mm2in(double mm) {
/*  96 */     return mm / 25.399999618530273D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double in2mm(double in) {
/* 105 */     return in * 25.399999618530273D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double in2mpt(double in) {
/* 114 */     return in * 72.0D * 1000.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double in2pt(double in) {
/* 123 */     return in * 72.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double mpt2in(double mpt) {
/* 132 */     return mpt / 72.0D / 1000.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double mm2px(double mm, int resolution) {
/* 142 */     return mm2in(mm) * resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double mpt2px(double mpt, int resolution) {
/* 152 */     return mpt2in(mpt) * resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AffineTransform mptToPt(AffineTransform at) {
/* 161 */     double[] matrix = new double[6];
/* 162 */     at.getMatrix(matrix);
/*     */     
/* 164 */     matrix[4] = matrix[4] / 1000.0D;
/* 165 */     matrix[5] = matrix[5] / 1000.0D;
/* 166 */     return new AffineTransform(matrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AffineTransform ptToMpt(AffineTransform at) {
/* 175 */     double[] matrix = new double[6];
/* 176 */     at.getMatrix(matrix);
/*     */     
/* 178 */     matrix[4] = matrix[4] * 1000.0D;
/* 179 */     matrix[5] = matrix[5] * 1000.0D;
/* 180 */     return new AffineTransform(matrix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convert(String value) {
/* 191 */     double retValue = 0.0D;
/* 192 */     if (value != null) {
/* 193 */       if (value.toLowerCase(Locale.getDefault()).indexOf("px") >= 0) {
/* 194 */         retValue = Double.parseDouble(value.substring(0, value.length() - 2));
/* 195 */         retValue *= 1000.0D;
/* 196 */       } else if (value.toLowerCase(Locale.getDefault()).indexOf("in") >= 0) {
/* 197 */         retValue = Double.parseDouble(value.substring(0, value.length() - 2));
/* 198 */         retValue *= 72000.0D;
/* 199 */       } else if (value.toLowerCase(Locale.getDefault()).indexOf("cm") >= 0) {
/* 200 */         retValue = Double.parseDouble(value.substring(0, value.length() - 2));
/* 201 */         retValue *= 28346.4567D;
/* 202 */       } else if (value.toLowerCase(Locale.getDefault()).indexOf("mm") >= 0) {
/* 203 */         retValue = Double.parseDouble(value.substring(0, value.length() - 2));
/* 204 */         retValue *= 2834.64567D;
/* 205 */       } else if (value.toLowerCase(Locale.getDefault()).indexOf("mpt") >= 0) {
/* 206 */         retValue = Double.parseDouble(value.substring(0, value.length() - 3));
/* 207 */       } else if (value.toLowerCase(Locale.getDefault()).indexOf("pt") >= 0) {
/* 208 */         retValue = Double.parseDouble(value.substring(0, value.length() - 2));
/* 209 */         retValue *= 1000.0D;
/* 210 */       } else if (value.toLowerCase(Locale.getDefault()).indexOf("pc") >= 0) {
/* 211 */         retValue = Double.parseDouble(value.substring(0, value.length() - 2));
/* 212 */         retValue *= 12000.0D;
/*     */       } 
/*     */     }
/* 215 */     return (int)retValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/UnitConv.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */