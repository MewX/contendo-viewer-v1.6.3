/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
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
/*     */ public class FilterManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  52 */     return "filter";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  73 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  80 */     return SVGValueConstants.NONE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  88 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  90 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 24:
/*  93 */         return (Value)new URIValue(lu.getStringValue(), resolveURI(engine.getCSSBaseURI(), lu.getStringValue()));
/*     */ 
/*     */ 
/*     */       
/*     */       case 35:
/*  98 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/* 100 */           return SVGValueConstants.NONE_VALUE;
/*     */         }
/* 102 */         throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */     } 
/* 104 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 113 */     if (type == 21) {
/* 114 */       if (value.equalsIgnoreCase("none")) {
/* 115 */         return SVGValueConstants.NONE_VALUE;
/*     */       }
/* 117 */       throw createInvalidIdentifierDOMException(value);
/*     */     } 
/* 119 */     if (type == 20) {
/* 120 */       return (Value)new URIValue(value, resolveURI(engine.getCSSBaseURI(), value));
/*     */     }
/*     */     
/* 123 */     throw createInvalidStringTypeDOMException(type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/FilterManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */