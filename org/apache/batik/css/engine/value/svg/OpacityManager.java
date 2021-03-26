/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
/*     */ import org.apache.batik.css.engine.value.FloatValue;
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
/*     */ 
/*     */ 
/*     */ public class OpacityManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   protected boolean inherited;
/*     */   protected String property;
/*     */   
/*     */   public OpacityManager(String prop, boolean inherit) {
/*  54 */     this.property = prop;
/*  55 */     this.inherited = inherit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  62 */     return this.inherited;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  83 */     return 25;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  90 */     return this.property;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  97 */     return SVGValueConstants.NUMBER_1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 105 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 107 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 13:
/* 110 */         return (Value)new FloatValue((short)1, lu.getIntegerValue());
/*     */ 
/*     */       
/*     */       case 14:
/* 114 */         return (Value)new FloatValue((short)1, lu.getFloatValue());
/*     */     } 
/*     */     
/* 117 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createFloatValue(short type, float floatValue) throws DOMException {
/* 125 */     if (type == 1) {
/* 126 */       return (Value)new FloatValue(type, floatValue);
/*     */     }
/* 128 */     throw createInvalidFloatTypeDOMException(type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/OpacityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */