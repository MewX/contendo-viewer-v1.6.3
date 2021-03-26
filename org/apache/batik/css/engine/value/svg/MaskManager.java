/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
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
/*     */ public class MaskManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  46 */     return false;
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
/*  67 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  74 */     return "mask";
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
/*     */       case 24:
/*  94 */         return (Value)new URIValue(lu.getStringValue(), resolveURI(engine.getCSSBaseURI(), lu.getStringValue()));
/*     */ 
/*     */ 
/*     */       
/*     */       case 35:
/*  99 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/* 101 */           return ValueConstants.NONE_VALUE; } 
/*     */         break;
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
/* 113 */     switch (type) {
/*     */       case 21:
/* 115 */         if (value.equalsIgnoreCase("none")) {
/* 116 */           return ValueConstants.NONE_VALUE;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 20:
/* 121 */         return (Value)new URIValue(value, resolveURI(engine.getCSSBaseURI(), value));
/*     */     } 
/*     */     
/* 124 */     throw createInvalidStringTypeDOMException(type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/MaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */