/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.URIValue;
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
/*     */ public class SVGPaintManager
/*     */   extends SVGColorManager
/*     */ {
/*     */   public SVGPaintManager(String prop) {
/*  48 */     super(prop);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPaintManager(String prop, Value v) {
/*  57 */     super(prop, v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  85 */     return 7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  93 */     switch (lu.getLexicalUnitType()) {
/*     */       case 35:
/*  95 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/*  97 */           return SVGValueConstants.NONE_VALUE;
/*     */         }
/*     */       
/*     */       default:
/* 101 */         return super.createValue(lu, engine);
/*     */       case 24:
/*     */         break;
/* 104 */     }  String value = lu.getStringValue();
/* 105 */     String uri = resolveURI(engine.getCSSBaseURI(), value);
/* 106 */     lu = lu.getNextLexicalUnit();
/* 107 */     if (lu == null) {
/* 108 */       return (Value)new URIValue(value, uri);
/*     */     }
/*     */     
/* 111 */     ListValue result = new ListValue(' ');
/* 112 */     result.append((Value)new URIValue(value, uri));
/*     */     
/* 114 */     if (lu.getLexicalUnitType() == 35 && 
/* 115 */       lu.getStringValue().equalsIgnoreCase("none")) {
/*     */       
/* 117 */       result.append(SVGValueConstants.NONE_VALUE);
/* 118 */       return (Value)result;
/*     */     } 
/*     */     
/* 121 */     Value v = super.createValue(lu, engine);
/* 122 */     if (v.getCssValueType() == 3) {
/* 123 */       ListValue lv = (ListValue)v;
/* 124 */       for (int i = 0; i < lv.getLength(); i++) {
/* 125 */         result.append(lv.item(i));
/*     */       }
/*     */     } else {
/* 128 */       result.append(v);
/*     */     } 
/* 130 */     return (Value)result;
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
/* 143 */     if (value == SVGValueConstants.NONE_VALUE) {
/* 144 */       return value;
/*     */     }
/* 146 */     if (value.getCssValueType() == 2) {
/* 147 */       ListValue lv = (ListValue)value;
/* 148 */       Value v = lv.item(0);
/* 149 */       if (v.getPrimitiveType() == 20) {
/* 150 */         v = lv.item(1);
/* 151 */         if (v == SVGValueConstants.NONE_VALUE) {
/* 152 */           return value;
/*     */         }
/* 154 */         Value t = super.computeValue(elt, pseudo, engine, idx, sm, v);
/* 155 */         if (t != v) {
/* 156 */           ListValue result = new ListValue(' ');
/* 157 */           result.append(lv.item(0));
/* 158 */           result.append(t);
/* 159 */           if (lv.getLength() == 3) {
/* 160 */             result.append(lv.item(1));
/*     */           }
/* 162 */           return (Value)result;
/*     */         } 
/* 164 */         return value;
/*     */       } 
/*     */     } 
/* 167 */     return super.computeValue(elt, pseudo, engine, idx, sm, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/SVGPaintManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */