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
/*     */ public abstract class GlyphOrientationManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  66 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     int i;
/*     */     float n;
/*  74 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  76 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 28:
/*  79 */         return (Value)new FloatValue((short)11, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 29:
/*  83 */         return (Value)new FloatValue((short)13, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 30:
/*  87 */         return (Value)new FloatValue((short)12, lu.getFloatValue());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 13:
/*  93 */         i = lu.getIntegerValue();
/*  94 */         return (Value)new FloatValue((short)11, i);
/*     */ 
/*     */       
/*     */       case 14:
/*  98 */         n = lu.getFloatValue();
/*  99 */         return (Value)new FloatValue((short)11, n);
/*     */     } 
/*     */ 
/*     */     
/* 103 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createFloatValue(short type, float floatValue) throws DOMException {
/* 111 */     switch (type) {
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/* 115 */         return (Value)new FloatValue(type, floatValue);
/*     */     } 
/* 117 */     throw createInvalidFloatValueDOMException(floatValue);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/GlyphOrientationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */