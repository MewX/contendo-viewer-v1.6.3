/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVTFontFace
/*     */   implements SVGConstants
/*     */ {
/*     */   protected String familyName;
/*     */   protected float unitsPerEm;
/*     */   protected String fontWeight;
/*     */   protected String fontStyle;
/*     */   protected String fontVariant;
/*     */   protected String fontStretch;
/*     */   protected float slope;
/*     */   protected String panose1;
/*     */   protected float ascent;
/*     */   protected float descent;
/*     */   protected float strikethroughPosition;
/*     */   protected float strikethroughThickness;
/*     */   protected float underlinePosition;
/*     */   protected float underlineThickness;
/*     */   protected float overlinePosition;
/*     */   protected float overlineThickness;
/*     */   
/*     */   public GVTFontFace(String familyName, float unitsPerEm, String fontWeight, String fontStyle, String fontVariant, String fontStretch, float slope, String panose1, float ascent, float descent, float strikethroughPosition, float strikethroughThickness, float underlinePosition, float underlineThickness, float overlinePosition, float overlineThickness) {
/*  59 */     this.familyName = familyName;
/*  60 */     this.unitsPerEm = unitsPerEm;
/*  61 */     this.fontWeight = fontWeight;
/*  62 */     this.fontStyle = fontStyle;
/*  63 */     this.fontVariant = fontVariant;
/*  64 */     this.fontStretch = fontStretch;
/*  65 */     this.slope = slope;
/*  66 */     this.panose1 = panose1;
/*  67 */     this.ascent = ascent;
/*  68 */     this.descent = descent;
/*  69 */     this.strikethroughPosition = strikethroughPosition;
/*  70 */     this.strikethroughThickness = strikethroughThickness;
/*  71 */     this.underlinePosition = underlinePosition;
/*  72 */     this.underlineThickness = underlineThickness;
/*  73 */     this.overlinePosition = overlinePosition;
/*  74 */     this.overlineThickness = overlineThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFontFace(String familyName) {
/*  82 */     this(familyName, 1000.0F, "all", "all", "normal", "normal", 0.0F, "0 0 0 0 0 0 0 0 0 0", 800.0F, 200.0F, 300.0F, 50.0F, -75.0F, 50.0F, 800.0F, 50.0F);
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
/*     */   public String getFamilyName() {
/*  95 */     return this.familyName;
/*     */   }
/*     */   
/*     */   public boolean hasFamilyName(String family) {
/*  99 */     String ffname = this.familyName;
/* 100 */     if (ffname.length() < family.length()) {
/* 101 */       return false;
/*     */     }
/*     */     
/* 104 */     ffname = ffname.toLowerCase();
/*     */     
/* 106 */     int idx = ffname.indexOf(family.toLowerCase());
/*     */     
/* 108 */     if (idx == -1) {
/* 109 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 113 */     if (ffname.length() > family.length()) {
/* 114 */       boolean quote = false;
/* 115 */       if (idx > 0) {
/* 116 */         int i; char c = ffname.charAt(idx - 1);
/* 117 */         switch (c) {
/*     */           default:
/* 119 */             return false;
/*     */           case ' ':
/* 121 */             for (i = idx - 2; i >= 0; i--) {
/* 122 */               switch (ffname.charAt(i)) {
/*     */                 default:
/* 124 */                   return false;
/*     */                 case ' ':
/*     */                   break;
/*     */                 case '"':
/*     */                 case '\'':
/* 129 */                   quote = true;
/*     */                   break;
/*     */               } 
/*     */             } 
/*     */             break;
/*     */           case '"':
/*     */           case '\'':
/* 136 */             quote = true; break;
/*     */           case ',':
/*     */             break;
/*     */         } 
/* 140 */       }  if (idx + family.length() < ffname.length()) {
/* 141 */         int i; char c = ffname.charAt(idx + family.length());
/* 142 */         switch (c) {
/*     */           default:
/* 144 */             return false;
/*     */           case ' ':
/* 146 */             i = idx + family.length() + 1;
/* 147 */             for (; i < ffname.length(); i++) {
/* 148 */               switch (ffname.charAt(i)) {
/*     */                 default:
/* 150 */                   return false;
/*     */                 case ' ':
/*     */                   break;
/*     */                 case '"':
/*     */                 case '\'':
/* 155 */                   if (!quote) {
/* 156 */                     return false;
/*     */                   }
/*     */                   break;
/*     */               } 
/*     */             } 
/*     */             break;
/*     */           case '"':
/*     */           case '\'':
/* 164 */             if (!quote)
/* 165 */               return false;  break;
/*     */           case ',':
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontWeight() {
/* 178 */     return this.fontWeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontStyle() {
/* 185 */     return this.fontStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnitsPerEm() {
/* 192 */     return this.unitsPerEm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAscent() {
/* 200 */     return this.ascent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDescent() {
/* 208 */     return this.descent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrikethroughPosition() {
/* 215 */     return this.strikethroughPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrikethroughThickness() {
/* 222 */     return this.strikethroughThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlinePosition() {
/* 229 */     return this.underlinePosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getUnderlineThickness() {
/* 236 */     return this.underlineThickness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOverlinePosition() {
/* 243 */     return this.overlinePosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getOverlineThickness() {
/* 250 */     return this.overlineThickness;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/GVTFontFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */