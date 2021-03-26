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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnableBackgroundManager
/*     */   extends LengthManager
/*     */ {
/*     */   protected int orientation;
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  74 */     return 23;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  81 */     return "enable-background";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  88 */     return SVGValueConstants.ACCUMULATE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  96 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  98 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       default:
/* 101 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */       case 35:
/*     */         break;
/*     */     } 
/* 105 */     String id = lu.getStringValue().toLowerCase().intern();
/* 106 */     if (id == "accumulate") {
/* 107 */       return SVGValueConstants.ACCUMULATE_VALUE;
/*     */     }
/* 109 */     if (id != "new") {
/* 110 */       throw createInvalidIdentifierDOMException(id);
/*     */     }
/* 112 */     ListValue result = new ListValue(' ');
/* 113 */     result.append(SVGValueConstants.NEW_VALUE);
/* 114 */     lu = lu.getNextLexicalUnit();
/* 115 */     if (lu == null) {
/* 116 */       return (Value)result;
/*     */     }
/* 118 */     result.append(super.createValue(lu, engine));
/* 119 */     for (int i = 1; i < 4; i++) {
/* 120 */       lu = lu.getNextLexicalUnit();
/* 121 */       if (lu == null) {
/* 122 */         throw createMalformedLexicalUnitDOMException();
/*     */       }
/* 124 */       result.append(super.createValue(lu, engine));
/*     */     } 
/* 126 */     return (Value)result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) {
/* 136 */     if (type != 21) {
/* 137 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 139 */     if (!value.equalsIgnoreCase("accumulate")) {
/* 140 */       throw createInvalidIdentifierDOMException(value);
/*     */     }
/* 142 */     return SVGValueConstants.ACCUMULATE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createFloatValue(short unitType, float floatValue) throws DOMException {
/* 150 */     throw createDOMException();
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
/* 163 */     if (value.getCssValueType() == 2) {
/* 164 */       ListValue lv = (ListValue)value;
/* 165 */       if (lv.getLength() == 5) {
/* 166 */         Value lv1 = lv.item(1);
/* 167 */         this.orientation = 0;
/* 168 */         Value v1 = super.computeValue(elt, pseudo, engine, idx, sm, lv1);
/*     */         
/* 170 */         Value lv2 = lv.item(2);
/* 171 */         this.orientation = 1;
/* 172 */         Value v2 = super.computeValue(elt, pseudo, engine, idx, sm, lv2);
/*     */         
/* 174 */         Value lv3 = lv.item(3);
/* 175 */         this.orientation = 0;
/* 176 */         Value v3 = super.computeValue(elt, pseudo, engine, idx, sm, lv3);
/*     */         
/* 178 */         Value lv4 = lv.item(4);
/* 179 */         this.orientation = 1;
/* 180 */         Value v4 = super.computeValue(elt, pseudo, engine, idx, sm, lv4);
/*     */ 
/*     */         
/* 183 */         if (lv1 != v1 || lv2 != v2 || lv3 != v3 || lv4 != v4) {
/*     */           
/* 185 */           ListValue result = new ListValue(' ');
/* 186 */           result.append(lv.item(0));
/* 187 */           result.append(v1);
/* 188 */           result.append(v2);
/* 189 */           result.append(v3);
/* 190 */           result.append(v4);
/* 191 */           return (Value)result;
/*     */         } 
/*     */       } 
/*     */     } 
/* 195 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 203 */     return this.orientation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/EnableBackgroundManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */