/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.LengthManager;
/*     */ import org.apache.batik.css.engine.value.ListValue;
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
/*     */ public class StrokeDasharrayManager
/*     */   extends LengthManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  69 */     return 34;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  76 */     return "stroke-dasharray";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  83 */     return SVGValueConstants.NONE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  91 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  93 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/*  96 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/*  98 */           return SVGValueConstants.NONE_VALUE;
/*     */         }
/* 100 */         throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */     } 
/*     */     
/* 103 */     ListValue lv = new ListValue(' ');
/*     */     while (true) {
/* 105 */       Value v = super.createValue(lu, engine);
/* 106 */       lv.append(v);
/* 107 */       lu = lu.getNextLexicalUnit();
/* 108 */       if (lu != null && lu.getLexicalUnitType() == 0)
/*     */       {
/*     */         
/* 111 */         lu = lu.getNextLexicalUnit();
/*     */       }
/* 113 */       if (lu == null) {
/* 114 */         return (Value)lv;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 124 */     if (type != 21) {
/* 125 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 127 */     if (value.equalsIgnoreCase("none")) {
/* 128 */       return SVGValueConstants.NONE_VALUE;
/*     */     }
/* 130 */     throw createInvalidIdentifierDOMException(value);
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
/* 143 */     switch (value.getCssValueType()) {
/*     */       case 1:
/* 145 */         return value;
/*     */     } 
/*     */     
/* 148 */     ListValue lv = (ListValue)value;
/* 149 */     ListValue result = new ListValue(' ');
/* 150 */     for (int i = 0; i < lv.getLength(); i++) {
/* 151 */       result.append(super.computeValue(elt, pseudo, engine, idx, sm, lv.item(i)));
/*     */     }
/*     */     
/* 154 */     return (Value)result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 162 */     return 2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/StrokeDasharrayManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */