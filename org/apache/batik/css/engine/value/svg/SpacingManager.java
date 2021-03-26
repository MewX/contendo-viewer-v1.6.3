/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.LengthManager;
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
/*     */ public class SpacingManager
/*     */   extends LengthManager
/*     */ {
/*     */   protected String property;
/*     */   
/*     */   public SpacingManager(String prop) {
/*  49 */     this.property = prop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  78 */     return 42;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  85 */     return this.property;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  92 */     return SVGValueConstants.NORMAL_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 100 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 102 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/* 105 */         if (lu.getStringValue().equalsIgnoreCase("normal"))
/*     */         {
/* 107 */           return SVGValueConstants.NORMAL_VALUE;
/*     */         }
/* 109 */         throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */     } 
/* 111 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 120 */     if (type != 21) {
/* 121 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 123 */     if (value.equalsIgnoreCase("normal")) {
/* 124 */       return SVGValueConstants.NORMAL_VALUE;
/*     */     }
/* 126 */     throw createInvalidIdentifierDOMException(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 134 */     return 2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/SpacingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */