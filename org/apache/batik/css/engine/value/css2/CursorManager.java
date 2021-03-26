/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.URIValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
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
/*     */ public class CursorManager
/*     */   extends AbstractValueManager
/*     */ {
/*  50 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  52 */     values.put("auto", ValueConstants.AUTO_VALUE);
/*     */     
/*  54 */     values.put("crosshair", ValueConstants.CROSSHAIR_VALUE);
/*     */     
/*  56 */     values.put("default", ValueConstants.DEFAULT_VALUE);
/*     */     
/*  58 */     values.put("e-resize", ValueConstants.E_RESIZE_VALUE);
/*     */     
/*  60 */     values.put("help", ValueConstants.HELP_VALUE);
/*     */     
/*  62 */     values.put("move", ValueConstants.MOVE_VALUE);
/*     */     
/*  64 */     values.put("n-resize", ValueConstants.N_RESIZE_VALUE);
/*     */     
/*  66 */     values.put("ne-resize", ValueConstants.NE_RESIZE_VALUE);
/*     */     
/*  68 */     values.put("nw-resize", ValueConstants.NW_RESIZE_VALUE);
/*     */     
/*  70 */     values.put("pointer", ValueConstants.POINTER_VALUE);
/*     */     
/*  72 */     values.put("s-resize", ValueConstants.S_RESIZE_VALUE);
/*     */     
/*  74 */     values.put("se-resize", ValueConstants.SE_RESIZE_VALUE);
/*     */     
/*  76 */     values.put("sw-resize", ValueConstants.SW_RESIZE_VALUE);
/*     */     
/*  78 */     values.put("text", ValueConstants.TEXT_VALUE);
/*     */     
/*  80 */     values.put("w-resize", ValueConstants.W_RESIZE_VALUE);
/*     */     
/*  82 */     values.put("wait", ValueConstants.WAIT_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/* 111 */     return 21;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 118 */     return "cursor";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 125 */     return ValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     String s;
/*     */     Object v;
/* 133 */     ListValue result = new ListValue();
/* 134 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 136 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 24:
/*     */         while (true) {
/* 140 */           result.append((Value)new URIValue(lu.getStringValue(), resolveURI(engine.getCSSBaseURI(), lu.getStringValue())));
/*     */ 
/*     */           
/* 143 */           lu = lu.getNextLexicalUnit();
/* 144 */           if (lu == null) {
/* 145 */             throw createMalformedLexicalUnitDOMException();
/*     */           }
/* 147 */           if (lu.getLexicalUnitType() != 0)
/*     */           {
/* 149 */             throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */           }
/*     */           
/* 152 */           lu = lu.getNextLexicalUnit();
/* 153 */           if (lu == null) {
/* 154 */             throw createMalformedLexicalUnitDOMException();
/*     */           }
/* 156 */           if (lu.getLexicalUnitType() != 24) {
/* 157 */             if (lu.getLexicalUnitType() != 35) {
/* 158 */               throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */             }
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       case 35:
/* 164 */         s = lu.getStringValue().toLowerCase().intern();
/* 165 */         v = values.get(s);
/* 166 */         if (v == null) {
/* 167 */           throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */         }
/* 169 */         result.append((Value)v);
/* 170 */         lu = lu.getNextLexicalUnit(); break;
/*     */     } 
/* 172 */     if (lu != null) {
/* 173 */       throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */     }
/*     */     
/* 176 */     return (Value)result;
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
/* 189 */     if (value.getCssValueType() == 2) {
/* 190 */       ListValue lv = (ListValue)value;
/* 191 */       int len = lv.getLength();
/* 192 */       ListValue result = new ListValue(' ');
/* 193 */       for (int i = 0; i < len; i++) {
/* 194 */         Value v = lv.item(0);
/* 195 */         if (v.getPrimitiveType() == 20) {
/*     */           
/* 197 */           result.append((Value)new URIValue(v.getStringValue(), v.getStringValue()));
/*     */         } else {
/*     */           
/* 200 */           result.append(v);
/*     */         } 
/*     */       } 
/* 203 */       return (Value)result;
/*     */     } 
/* 205 */     return super.computeValue(elt, pseudo, engine, idx, sm, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/CursorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */