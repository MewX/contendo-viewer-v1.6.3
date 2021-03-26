/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSContext;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.StringValue;
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
/*     */ public class FontFamilyManager
/*     */   extends AbstractValueManager
/*     */ {
/*  50 */   protected static final ListValue DEFAULT_VALUE = new ListValue();
/*     */   static {
/*  52 */     DEFAULT_VALUE.append((Value)new StringValue((short)19, "Arial"));
/*     */ 
/*     */     
/*  55 */     DEFAULT_VALUE.append((Value)new StringValue((short)19, "Helvetica"));
/*     */ 
/*     */     
/*  58 */     DEFAULT_VALUE.append((Value)new StringValue((short)21, "sans-serif"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  68 */     values.put("cursive", ValueConstants.CURSIVE_VALUE);
/*     */     
/*  70 */     values.put("fantasy", ValueConstants.FANTASY_VALUE);
/*     */     
/*  72 */     values.put("monospace", ValueConstants.MONOSPACE_VALUE);
/*     */     
/*  74 */     values.put("serif", ValueConstants.SERIF_VALUE);
/*     */     
/*  76 */     values.put("sans-serif", ValueConstants.SANS_SERIF_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/* 105 */     return 26;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 112 */     return "font-family";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 119 */     return (Value)DEFAULT_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 127 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 129 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       default:
/* 132 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */       
/*     */       case 35:
/*     */       case 36:
/*     */         break;
/*     */     } 
/* 138 */     ListValue result = new ListValue(); while (true) {
/*     */       StringBuffer sb; String id, s; Value v;
/* 140 */       switch (lu.getLexicalUnitType()) {
/*     */         case 36:
/* 142 */           result.append((Value)new StringValue((short)19, lu.getStringValue()));
/*     */           
/* 144 */           lu = lu.getNextLexicalUnit();
/*     */           break;
/*     */         
/*     */         case 35:
/* 148 */           sb = new StringBuffer(lu.getStringValue());
/* 149 */           lu = lu.getNextLexicalUnit();
/* 150 */           if (lu != null && isIdentOrNumber(lu)) {
/*     */             do {
/* 152 */               sb.append(' ');
/* 153 */               switch (lu.getLexicalUnitType()) {
/*     */                 case 35:
/* 155 */                   sb.append(lu.getStringValue());
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case 13:
/* 160 */                   sb.append(Integer.toString(lu.getIntegerValue())); break;
/*     */               } 
/* 162 */               lu = lu.getNextLexicalUnit();
/* 163 */             } while (lu != null && isIdentOrNumber(lu));
/* 164 */             result.append((Value)new StringValue((short)19, sb.toString()));
/*     */             break;
/*     */           } 
/* 167 */           id = sb.toString();
/* 168 */           s = id.toLowerCase().intern();
/* 169 */           v = (Value)values.get(s);
/* 170 */           result.append((v != null) ? v : (Value)new StringValue((short)19, id));
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 176 */       if (lu == null)
/* 177 */         return (Value)result; 
/* 178 */       if (lu.getLexicalUnitType() != 0) {
/* 179 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */       }
/* 181 */       lu = lu.getNextLexicalUnit();
/* 182 */       if (lu == null)
/* 183 */         throw createMalformedLexicalUnitDOMException(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isIdentOrNumber(LexicalUnit lu) {
/* 188 */     short type = lu.getLexicalUnitType();
/* 189 */     switch (type) {
/*     */       case 13:
/*     */       case 35:
/* 192 */         return true;
/*     */     } 
/* 194 */     return false;
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
/*     */   
/*     */   public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
/* 208 */     if (value == DEFAULT_VALUE) {
/* 209 */       CSSContext ctx = engine.getCSSContext();
/* 210 */       value = ctx.getDefaultFontFamily();
/*     */     } 
/* 212 */     return value;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontFamilyManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */