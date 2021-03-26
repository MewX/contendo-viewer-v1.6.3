/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueManager;
/*     */ import org.apache.batik.css.engine.value.StringValue;
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
/*     */ public class ColorProfileManager
/*     */   extends AbstractValueManager
/*     */ {
/*     */   public boolean isInheritedProperty() {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  52 */     return "color-profile";
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
/*  80 */     return SVGValueConstants.AUTO_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     String s;
/*  88 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/*  90 */         return SVGValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/*  93 */         s = lu.getStringValue().toLowerCase();
/*  94 */         if (s.equals("auto")) {
/*  95 */           return SVGValueConstants.AUTO_VALUE;
/*     */         }
/*  97 */         if (s.equals("srgb")) {
/*  98 */           return SVGValueConstants.SRGB_VALUE;
/*     */         }
/* 100 */         return (Value)new StringValue((short)21, s);
/*     */       
/*     */       case 24:
/* 103 */         return (Value)new URIValue(lu.getStringValue(), resolveURI(engine.getCSSBaseURI(), lu.getStringValue()));
/*     */     } 
/*     */ 
/*     */     
/* 107 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/*     */     String s;
/* 116 */     switch (type) {
/*     */       case 21:
/* 118 */         s = value.toLowerCase();
/* 119 */         if (s.equals("auto")) {
/* 120 */           return SVGValueConstants.AUTO_VALUE;
/*     */         }
/* 122 */         if (s.equals("srgb")) {
/* 123 */           return SVGValueConstants.SRGB_VALUE;
/*     */         }
/* 125 */         return (Value)new StringValue((short)21, s);
/*     */       
/*     */       case 20:
/* 128 */         return (Value)new URIValue(value, resolveURI(engine.getCSSBaseURI(), value));
/*     */     } 
/*     */     
/* 131 */     throw createInvalidStringTypeDOMException(type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/ColorProfileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */