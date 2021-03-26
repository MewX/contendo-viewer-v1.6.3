/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.FontFaceRule;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.gvt.font.GVTFontFamily;
/*     */ import org.apache.batik.util.ParsedURL;
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
/*     */ public class CSSFontFace
/*     */   extends FontFace
/*     */   implements SVGConstants
/*     */ {
/*  44 */   GVTFontFamily fontFamily = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSFontFace(List srcs, String familyName, float unitsPerEm, String fontWeight, String fontStyle, String fontVariant, String fontStretch, float slope, String panose1, float ascent, float descent, float strikethroughPosition, float strikethroughThickness, float underlinePosition, float underlineThickness, float overlinePosition, float overlineThickness) {
/*  57 */     super(srcs, familyName, unitsPerEm, fontWeight, fontStyle, fontVariant, fontStretch, slope, panose1, ascent, descent, strikethroughPosition, strikethroughThickness, underlinePosition, underlineThickness, overlinePosition, overlineThickness);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CSSFontFace(String familyName) {
/*  66 */     super(familyName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CSSFontFace createCSSFontFace(CSSEngine eng, FontFaceRule ffr) {
/*  71 */     StyleMap sm = ffr.getStyleMap();
/*  72 */     String familyName = getStringProp(sm, eng, 21);
/*     */ 
/*     */     
/*  75 */     CSSFontFace ret = new CSSFontFace(familyName);
/*     */ 
/*     */     
/*  78 */     Value v = sm.getValue(27);
/*  79 */     if (v != null)
/*  80 */       ret.fontWeight = v.getCssText(); 
/*  81 */     v = sm.getValue(25);
/*  82 */     if (v != null)
/*  83 */       ret.fontStyle = v.getCssText(); 
/*  84 */     v = sm.getValue(26);
/*  85 */     if (v != null)
/*  86 */       ret.fontVariant = v.getCssText(); 
/*  87 */     v = sm.getValue(24);
/*  88 */     if (v != null)
/*  89 */       ret.fontStretch = v.getCssText(); 
/*  90 */     v = sm.getValue(41);
/*     */     
/*  92 */     ParsedURL base = ffr.getURL();
/*  93 */     if (v != null && v != ValueConstants.NONE_VALUE) {
/*  94 */       if (v.getCssValueType() == 1) {
/*  95 */         ret.srcs = new LinkedList();
/*  96 */         ret.srcs.add(getSrcValue(v, base));
/*  97 */       } else if (v.getCssValueType() == 2) {
/*  98 */         ret.srcs = new LinkedList();
/*  99 */         for (int i = 0; i < v.getLength(); i++) {
/* 100 */           ret.srcs.add(getSrcValue(v.item(i), base));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     return ret;
/*     */   }
/*     */   
/*     */   public static Object getSrcValue(Value v, ParsedURL base) {
/* 132 */     if (v.getCssValueType() != 1)
/* 133 */       return null; 
/* 134 */     if (v.getPrimitiveType() == 20) {
/* 135 */       if (base != null)
/* 136 */         return new ParsedURL(base, v.getStringValue()); 
/* 137 */       return new ParsedURL(v.getStringValue());
/*     */     } 
/* 139 */     if (v.getPrimitiveType() == 19)
/* 140 */       return v.getStringValue(); 
/* 141 */     return null;
/*     */   }
/*     */   public static String getStringProp(StyleMap sm, CSSEngine eng, int pidx) {
/* 144 */     Value v = sm.getValue(pidx);
/* 145 */     ValueManager[] vms = eng.getValueManagers();
/* 146 */     if (v == null) {
/* 147 */       ValueManager vm = vms[pidx];
/* 148 */       v = vm.getDefaultValue();
/*     */     } 
/* 150 */     while (v.getCssValueType() == 2) {
/* 151 */       v = v.item(0);
/*     */     }
/* 153 */     return v.getStringValue();
/*     */   }
/*     */   
/*     */   public static float getFloatProp(StyleMap sm, CSSEngine eng, int pidx) {
/* 157 */     Value v = sm.getValue(pidx);
/* 158 */     ValueManager[] vms = eng.getValueManagers();
/* 159 */     if (v == null) {
/* 160 */       ValueManager vm = vms[pidx];
/* 161 */       v = vm.getDefaultValue();
/*     */     } 
/* 163 */     while (v.getCssValueType() == 2) {
/* 164 */       v = v.item(0);
/*     */     }
/* 166 */     return v.getFloatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFontFamily getFontFamily(BridgeContext ctx) {
/* 173 */     if (this.fontFamily != null) {
/* 174 */       return this.fontFamily;
/*     */     }
/* 176 */     this.fontFamily = super.getFontFamily(ctx);
/* 177 */     return this.fontFamily;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/CSSFontFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */