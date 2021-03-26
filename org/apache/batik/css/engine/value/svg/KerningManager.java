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
/*     */ public class KerningManager
/*     */   extends LengthManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  51 */     return "kerning";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  72 */     return 41;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  79 */     return SVGValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  87 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  89 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/*  92 */         if (lu.getStringValue().equalsIgnoreCase("auto"))
/*     */         {
/*  94 */           return SVGValueConstants.AUTO_VALUE;
/*     */         }
/*  96 */         throw createInvalidIdentifierDOMException(lu.getStringValue());
/*     */     } 
/*  98 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 107 */     if (type != 21) {
/* 108 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 110 */     if (value.equalsIgnoreCase("auto")) {
/* 111 */       return SVGValueConstants.AUTO_VALUE;
/*     */     }
/* 113 */     throw createInvalidIdentifierDOMException(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 121 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/KerningManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */