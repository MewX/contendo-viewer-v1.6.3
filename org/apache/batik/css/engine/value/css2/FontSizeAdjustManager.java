/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
/*     */ import org.apache.batik.css.engine.value.FloatValue;
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
/*     */ public class FontSizeAdjustManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  67 */     return 44;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  74 */     return "font-size-adjust";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  81 */     return ValueConstants.NONE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  89 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  91 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 13:
/*  94 */         return (Value)new FloatValue((short)1, lu.getIntegerValue());
/*     */ 
/*     */       
/*     */       case 14:
/*  98 */         return (Value)new FloatValue((short)1, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 35:
/* 102 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/* 104 */           return ValueConstants.NONE_VALUE;
/*     */         }
/* 106 */         throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */     } 
/* 108 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 117 */     if (type != 21) {
/* 118 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 120 */     if (value.equalsIgnoreCase("none")) {
/* 121 */       return ValueConstants.NONE_VALUE;
/*     */     }
/* 123 */     throw createInvalidIdentifierDOMException(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createFloatValue(short type, float floatValue) throws DOMException {
/* 131 */     if (type == 1) {
/* 132 */       return (Value)new FloatValue(type, floatValue);
/*     */     }
/* 134 */     throw createInvalidFloatTypeDOMException(type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontSizeAdjustManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */