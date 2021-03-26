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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarkerManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   protected String property;
/*     */   
/*     */   public MarkerManager(String prop) {
/*  51 */     this.property = prop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  79 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  86 */     return this.property;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  93 */     return ValueConstants.NONE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 101 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 103 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 24:
/* 106 */         return (Value)new URIValue(lu.getStringValue(), resolveURI(engine.getCSSBaseURI(), lu.getStringValue()));
/*     */ 
/*     */ 
/*     */       
/*     */       case 35:
/* 111 */         if (lu.getStringValue().equalsIgnoreCase("none"))
/*     */         {
/* 113 */           return ValueConstants.NONE_VALUE; } 
/*     */         break;
/*     */     } 
/* 116 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 125 */     switch (type) {
/*     */       case 21:
/* 127 */         if (value.equalsIgnoreCase("none")) {
/* 128 */           return ValueConstants.NONE_VALUE;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 20:
/* 133 */         return (Value)new URIValue(value, resolveURI(engine.getCSSBaseURI(), value));
/*     */     } 
/*     */     
/* 136 */     throw createInvalidStringTypeDOMException(type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/MarkerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */