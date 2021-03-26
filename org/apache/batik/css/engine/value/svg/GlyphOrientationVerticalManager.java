/*    */ package org.apache.batik.css.engine.value.svg;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
/*    */ import org.apache.batik.css.engine.value.Value;
/*    */ import org.w3c.css.sac.LexicalUnit;
/*    */ import org.w3c.dom.DOMException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlyphOrientationVerticalManager
/*    */   extends GlyphOrientationManager
/*    */ {
/*    */   public String getPropertyName() {
/* 43 */     return "glyph-orientation-vertical";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getDefaultValue() {
/* 50 */     return SVGValueConstants.AUTO_VALUE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 58 */     if (lu.getLexicalUnitType() == 35) {
/* 59 */       if (lu.getStringValue().equalsIgnoreCase("auto"))
/*    */       {
/* 61 */         return SVGValueConstants.AUTO_VALUE;
/*    */       }
/* 63 */       throw createInvalidIdentifierDOMException(lu.getStringValue());
/*    */     } 
/* 65 */     return super.createValue(lu, engine);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 74 */     if (type != 21) {
/* 75 */       throw createInvalidStringTypeDOMException(type);
/*    */     }
/* 77 */     if (value.equalsIgnoreCase("auto")) {
/* 78 */       return SVGValueConstants.AUTO_VALUE;
/*    */     }
/* 80 */     throw createInvalidIdentifierDOMException(value);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/GlyphOrientationVerticalManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */