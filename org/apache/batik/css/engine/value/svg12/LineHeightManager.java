/*     */ package org.apache.batik.css.engine.value.svg12;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.FloatValue;
/*     */ import org.apache.batik.css.engine.value.LengthManager;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.w3c.css.sac.LexicalUnit;
/*     */ import org.w3c.dom.DOMException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineHeightManager
/*     */   extends LengthManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  72 */     return 43;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  79 */     return "line-height";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  86 */     return SVG12ValueConstants.NORMAL_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     String s;
/*  95 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  97 */         return SVG12ValueConstants.INHERIT_VALUE;
/*     */       case 35:
/*  99 */         s = lu.getStringValue().toLowerCase();
/* 100 */         if ("normal".equals(s))
/* 101 */           return SVG12ValueConstants.NORMAL_VALUE; 
/* 102 */         throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */     } 
/*     */     
/* 105 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 116 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
/*     */     float v;
/*     */     int fsidx;
/*     */     float fs;
/* 130 */     if (value.getCssValueType() != 1) {
/* 131 */       return value;
/*     */     }
/* 133 */     switch (value.getPrimitiveType()) {
/*     */       case 1:
/* 135 */         return (Value)new LineHeightValue((short)1, value.getFloatValue(), true);
/*     */ 
/*     */       
/*     */       case 2:
/* 139 */         v = value.getFloatValue();
/* 140 */         fsidx = engine.getFontSizeIndex();
/* 141 */         fs = engine.getComputedStyle(elt, pseudo, fsidx).getFloatValue();
/*     */         
/* 143 */         return (Value)new FloatValue((short)1, v * fs * 0.01F);
/*     */     } 
/*     */ 
/*     */     
/* 147 */     return super.computeValue(elt, pseudo, engine, idx, sm, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/LineHeightManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */