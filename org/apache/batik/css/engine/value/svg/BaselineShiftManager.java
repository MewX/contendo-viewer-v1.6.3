/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.FloatValue;
/*     */ import org.apache.batik.css.engine.value.LengthManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
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
/*     */ public class BaselineShiftManager
/*     */   extends LengthManager
/*     */ {
/*  47 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  49 */     values.put("baseline", SVGValueConstants.BASELINE_VALUE);
/*     */     
/*  51 */     values.put("sub", SVGValueConstants.SUB_VALUE);
/*     */     
/*  53 */     values.put("super", SVGValueConstants.SUPER_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  82 */     return 40;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  89 */     return "baseline-shift";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  96 */     return SVGValueConstants.BASELINE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     Object v;
/* 104 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 106 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/* 109 */         v = values.get(lu.getStringValue().toLowerCase().intern());
/* 110 */         if (v == null) {
/* 111 */           throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */         }
/* 113 */         return (Value)v;
/*     */     } 
/* 115 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 123 */     if (type != 21) {
/* 124 */       throw createInvalidIdentifierDOMException(value);
/*     */     }
/* 126 */     Object v = values.get(value.toLowerCase().intern());
/* 127 */     if (v == null) {
/* 128 */       throw createInvalidIdentifierDOMException(value);
/*     */     }
/* 130 */     return (Value)v;
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
/* 143 */     if (value.getPrimitiveType() == 2) {
/* 144 */       sm.putLineHeightRelative(idx, true);
/*     */       
/* 146 */       int fsi = engine.getLineHeightIndex();
/*     */       
/* 148 */       CSSStylableElement parent = (CSSStylableElement)elt.getParentNode();
/* 149 */       if (parent == null)
/*     */       {
/*     */ 
/*     */         
/* 153 */         parent = elt;
/*     */       }
/* 155 */       Value fs = engine.getComputedStyle(parent, pseudo, fsi);
/* 156 */       float fsv = fs.getFloatValue();
/* 157 */       float v = value.getFloatValue();
/* 158 */       return (Value)new FloatValue((short)1, fsv * v / 100.0F);
/*     */     } 
/*     */     
/* 161 */     return super.computeValue(elt, pseudo, engine, idx, sm, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 170 */     return 2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/BaselineShiftManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */