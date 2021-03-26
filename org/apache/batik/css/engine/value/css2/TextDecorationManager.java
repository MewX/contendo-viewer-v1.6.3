/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.StringMap;
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
/*     */ public class TextDecorationManager
/*     */   extends AbstractValueManager
/*     */ {
/*  45 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  47 */     values.put("blink", ValueConstants.BLINK_VALUE);
/*     */     
/*  49 */     values.put("line-through", ValueConstants.LINE_THROUGH_VALUE);
/*     */     
/*  51 */     values.put("overline", ValueConstants.OVERLINE_VALUE);
/*     */     
/*  53 */     values.put("underline", ValueConstants.UNDERLINE_VALUE);
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
/*  82 */     return 18;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  89 */     return "text-decoration";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  96 */     return ValueConstants.NONE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     ListValue lv;
/* 104 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 106 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/* 109 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/* 111 */           return ValueConstants.NONE_VALUE;
/*     */         }
/* 113 */         lv = new ListValue(' ');
/*     */         while (true) {
/* 115 */           if (lu.getLexicalUnitType() == 35) {
/* 116 */             String s = lu.getStringValue().toLowerCase().intern();
/* 117 */             Object obj = values.get(s);
/* 118 */             if (obj == null) {
/* 119 */               throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */             }
/*     */             
/* 122 */             lv.append((Value)obj);
/* 123 */             lu = lu.getNextLexicalUnit();
/*     */           } else {
/* 125 */             throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */           } 
/*     */ 
/*     */           
/* 129 */           if (lu == null)
/* 130 */             return (Value)lv; 
/*     */         } 
/* 132 */     }  throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 142 */     if (type != 21) {
/* 143 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 145 */     if (!value.equalsIgnoreCase("none")) {
/* 146 */       throw createInvalidIdentifierDOMException(value);
/*     */     }
/* 148 */     return ValueConstants.NONE_VALUE;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/TextDecorationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */