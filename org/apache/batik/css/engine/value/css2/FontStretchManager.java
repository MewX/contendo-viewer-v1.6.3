/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.IdentifierManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontStretchManager
/*     */   extends IdentifierManager
/*     */ {
/*  43 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  45 */     values.put("all", ValueConstants.ALL_VALUE);
/*     */     
/*  47 */     values.put("condensed", ValueConstants.CONDENSED_VALUE);
/*     */     
/*  49 */     values.put("expanded", ValueConstants.EXPANDED_VALUE);
/*     */     
/*  51 */     values.put("extra-condensed", ValueConstants.EXTRA_CONDENSED_VALUE);
/*     */     
/*  53 */     values.put("extra-expanded", ValueConstants.EXTRA_EXPANDED_VALUE);
/*     */     
/*  55 */     values.put("narrower", ValueConstants.NARROWER_VALUE);
/*     */     
/*  57 */     values.put("normal", ValueConstants.NORMAL_VALUE);
/*     */     
/*  59 */     values.put("semi-condensed", ValueConstants.SEMI_CONDENSED_VALUE);
/*     */     
/*  61 */     values.put("semi-expanded", ValueConstants.SEMI_EXPANDED_VALUE);
/*     */     
/*  63 */     values.put("ultra-condensed", ValueConstants.ULTRA_CONDENSED_VALUE);
/*     */     
/*  65 */     values.put("ultra-expanded", ValueConstants.ULTRA_EXPANDED_VALUE);
/*     */     
/*  67 */     values.put("wider", ValueConstants.WIDER_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  97 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 105 */     return "font-stretch";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 113 */     return ValueConstants.NORMAL_VALUE;
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
/*     */   public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
/* 126 */     if (value == ValueConstants.NARROWER_VALUE) {
/* 127 */       sm.putParentRelative(idx, true);
/*     */       
/* 129 */       CSSStylableElement p = CSSEngine.getParentCSSStylableElement((Element)elt);
/* 130 */       if (p == null) {
/* 131 */         return ValueConstants.SEMI_CONDENSED_VALUE;
/*     */       }
/* 133 */       Value v = engine.getComputedStyle(p, pseudo, idx);
/* 134 */       if (v == ValueConstants.NORMAL_VALUE) {
/* 135 */         return ValueConstants.SEMI_CONDENSED_VALUE;
/*     */       }
/* 137 */       if (v == ValueConstants.CONDENSED_VALUE) {
/* 138 */         return ValueConstants.EXTRA_CONDENSED_VALUE;
/*     */       }
/* 140 */       if (v == ValueConstants.EXPANDED_VALUE) {
/* 141 */         return ValueConstants.SEMI_EXPANDED_VALUE;
/*     */       }
/* 143 */       if (v == ValueConstants.SEMI_EXPANDED_VALUE) {
/* 144 */         return ValueConstants.NORMAL_VALUE;
/*     */       }
/* 146 */       if (v == ValueConstants.SEMI_CONDENSED_VALUE) {
/* 147 */         return ValueConstants.CONDENSED_VALUE;
/*     */       }
/* 149 */       if (v == ValueConstants.EXTRA_CONDENSED_VALUE) {
/* 150 */         return ValueConstants.ULTRA_CONDENSED_VALUE;
/*     */       }
/* 152 */       if (v == ValueConstants.EXTRA_EXPANDED_VALUE) {
/* 153 */         return ValueConstants.EXPANDED_VALUE;
/*     */       }
/* 155 */       if (v == ValueConstants.ULTRA_CONDENSED_VALUE) {
/* 156 */         return ValueConstants.ULTRA_CONDENSED_VALUE;
/*     */       }
/* 158 */       return ValueConstants.EXTRA_EXPANDED_VALUE;
/* 159 */     }  if (value == ValueConstants.WIDER_VALUE) {
/* 160 */       sm.putParentRelative(idx, true);
/*     */       
/* 162 */       CSSStylableElement p = CSSEngine.getParentCSSStylableElement((Element)elt);
/* 163 */       if (p == null) {
/* 164 */         return ValueConstants.SEMI_CONDENSED_VALUE;
/*     */       }
/* 166 */       Value v = engine.getComputedStyle(p, pseudo, idx);
/* 167 */       if (v == ValueConstants.NORMAL_VALUE) {
/* 168 */         return ValueConstants.SEMI_EXPANDED_VALUE;
/*     */       }
/* 170 */       if (v == ValueConstants.CONDENSED_VALUE) {
/* 171 */         return ValueConstants.SEMI_CONDENSED_VALUE;
/*     */       }
/* 173 */       if (v == ValueConstants.EXPANDED_VALUE) {
/* 174 */         return ValueConstants.EXTRA_EXPANDED_VALUE;
/*     */       }
/* 176 */       if (v == ValueConstants.SEMI_EXPANDED_VALUE) {
/* 177 */         return ValueConstants.EXPANDED_VALUE;
/*     */       }
/* 179 */       if (v == ValueConstants.SEMI_CONDENSED_VALUE) {
/* 180 */         return ValueConstants.NORMAL_VALUE;
/*     */       }
/* 182 */       if (v == ValueConstants.EXTRA_CONDENSED_VALUE) {
/* 183 */         return ValueConstants.CONDENSED_VALUE;
/*     */       }
/* 185 */       if (v == ValueConstants.EXTRA_EXPANDED_VALUE) {
/* 186 */         return ValueConstants.ULTRA_EXPANDED_VALUE;
/*     */       }
/* 188 */       if (v == ValueConstants.ULTRA_CONDENSED_VALUE) {
/* 189 */         return ValueConstants.EXTRA_CONDENSED_VALUE;
/*     */       }
/* 191 */       return ValueConstants.ULTRA_EXPANDED_VALUE;
/*     */     } 
/* 193 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 200 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontStretchManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */