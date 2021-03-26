/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSContext;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.IdentifierManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
/*     */ import org.w3c.css.sac.LexicalUnit;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ 
/*     */ 
/*     */ public class FontWeightManager
/*     */   extends IdentifierManager
/*     */ {
/*  48 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  50 */     values.put("all", ValueConstants.ALL_VALUE);
/*     */     
/*  52 */     values.put("bold", ValueConstants.BOLD_VALUE);
/*     */     
/*  54 */     values.put("bolder", ValueConstants.BOLDER_VALUE);
/*     */     
/*  56 */     values.put("lighter", ValueConstants.LIGHTER_VALUE);
/*     */     
/*  58 */     values.put("normal", ValueConstants.NORMAL_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  87 */     return 28;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  94 */     return "font-weight";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 101 */     return ValueConstants.NORMAL_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 109 */     if (lu.getLexicalUnitType() == 13) {
/* 110 */       int i = lu.getIntegerValue();
/* 111 */       switch (i) {
/*     */         case 100:
/* 113 */           return ValueConstants.NUMBER_100;
/*     */         case 200:
/* 115 */           return ValueConstants.NUMBER_200;
/*     */         case 300:
/* 117 */           return ValueConstants.NUMBER_300;
/*     */         case 400:
/* 119 */           return ValueConstants.NUMBER_400;
/*     */         case 500:
/* 121 */           return ValueConstants.NUMBER_500;
/*     */         case 600:
/* 123 */           return ValueConstants.NUMBER_600;
/*     */         case 700:
/* 125 */           return ValueConstants.NUMBER_700;
/*     */         case 800:
/* 127 */           return ValueConstants.NUMBER_800;
/*     */         case 900:
/* 129 */           return ValueConstants.NUMBER_900;
/*     */       } 
/* 131 */       throw createInvalidFloatValueDOMException(i);
/*     */     } 
/* 133 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createFloatValue(short type, float floatValue) throws DOMException {
/* 141 */     if (type == 1) {
/* 142 */       int i = (int)floatValue;
/* 143 */       if (floatValue == i) {
/* 144 */         switch (i) {
/*     */           case 100:
/* 146 */             return ValueConstants.NUMBER_100;
/*     */           case 200:
/* 148 */             return ValueConstants.NUMBER_200;
/*     */           case 300:
/* 150 */             return ValueConstants.NUMBER_300;
/*     */           case 400:
/* 152 */             return ValueConstants.NUMBER_400;
/*     */           case 500:
/* 154 */             return ValueConstants.NUMBER_500;
/*     */           case 600:
/* 156 */             return ValueConstants.NUMBER_600;
/*     */           case 700:
/* 158 */             return ValueConstants.NUMBER_700;
/*     */           case 800:
/* 160 */             return ValueConstants.NUMBER_800;
/*     */           case 900:
/* 162 */             return ValueConstants.NUMBER_900;
/*     */         } 
/*     */       }
/*     */     } 
/* 166 */     throw createInvalidFloatValueDOMException(floatValue);
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
/* 179 */     if (value == ValueConstants.BOLDER_VALUE) {
/* 180 */       float fw; sm.putParentRelative(idx, true);
/*     */       
/* 182 */       CSSContext ctx = engine.getCSSContext();
/* 183 */       CSSStylableElement p = CSSEngine.getParentCSSStylableElement((Element)elt);
/*     */       
/* 185 */       if (p == null) {
/* 186 */         fw = 400.0F;
/*     */       } else {
/* 188 */         Value v = engine.getComputedStyle(p, pseudo, idx);
/* 189 */         fw = v.getFloatValue();
/*     */       } 
/* 191 */       return createFontWeight(ctx.getBolderFontWeight(fw));
/* 192 */     }  if (value == ValueConstants.LIGHTER_VALUE) {
/* 193 */       float fw; sm.putParentRelative(idx, true);
/*     */       
/* 195 */       CSSContext ctx = engine.getCSSContext();
/* 196 */       CSSStylableElement p = CSSEngine.getParentCSSStylableElement((Element)elt);
/*     */       
/* 198 */       if (p == null) {
/* 199 */         fw = 400.0F;
/*     */       } else {
/* 201 */         Value v = engine.getComputedStyle(p, pseudo, idx);
/* 202 */         fw = v.getFloatValue();
/*     */       } 
/* 204 */       return createFontWeight(ctx.getLighterFontWeight(fw));
/* 205 */     }  if (value == ValueConstants.NORMAL_VALUE)
/* 206 */       return ValueConstants.NUMBER_400; 
/* 207 */     if (value == ValueConstants.BOLD_VALUE) {
/* 208 */       return ValueConstants.NUMBER_700;
/*     */     }
/* 210 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Value createFontWeight(float f) {
/* 217 */     switch ((int)f) {
/*     */       case 100:
/* 219 */         return ValueConstants.NUMBER_100;
/*     */       case 200:
/* 221 */         return ValueConstants.NUMBER_200;
/*     */       case 300:
/* 223 */         return ValueConstants.NUMBER_300;
/*     */       case 400:
/* 225 */         return ValueConstants.NUMBER_400;
/*     */       case 500:
/* 227 */         return ValueConstants.NUMBER_500;
/*     */       case 600:
/* 229 */         return ValueConstants.NUMBER_600;
/*     */       case 700:
/* 231 */         return ValueConstants.NUMBER_700;
/*     */       case 800:
/* 233 */         return ValueConstants.NUMBER_800;
/*     */     } 
/* 235 */     return ValueConstants.NUMBER_900;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 243 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontWeightManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */