/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.InheritValue;
/*     */ import org.apache.batik.css.engine.value.RectManager;
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
/*     */ public class ClipManager
/*     */   extends RectManager
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
/*  67 */     return 19;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  74 */     return "clip";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/*  81 */     return ValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  89 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  91 */         return (Value)InheritValue.INSTANCE;
/*     */       
/*     */       case 35:
/*  94 */         if (lu.getStringValue().equalsIgnoreCase("auto"))
/*     */         {
/*  96 */           return ValueConstants.AUTO_VALUE; } 
/*     */         break;
/*     */     } 
/*  99 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 108 */     if (type != 21) {
/* 109 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 111 */     if (!value.equalsIgnoreCase("auto")) {
/* 112 */       throw createInvalidIdentifierDOMException(value);
/*     */     }
/* 114 */     return ValueConstants.AUTO_VALUE;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/ClipManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */