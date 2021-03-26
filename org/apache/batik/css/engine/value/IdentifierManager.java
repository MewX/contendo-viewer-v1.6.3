/*    */ package org.apache.batik.css.engine.value;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
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
/*    */ public abstract class IdentifierManager
/*    */   extends AbstractValueManager
/*    */ {
/*    */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*    */     String s;
/*    */     Object v;
/* 40 */     switch (lu.getLexicalUnitType()) {
/*    */       case 12:
/* 42 */         return ValueConstants.INHERIT_VALUE;
/*    */       
/*    */       case 35:
/* 45 */         s = lu.getStringValue().toLowerCase().intern();
/* 46 */         v = getIdentifiers().get(s);
/* 47 */         if (v == null) {
/* 48 */           throw createInvalidIdentifierDOMException(lu.getStringValue());
/*    */         }
/* 50 */         return (Value)v;
/*    */     } 
/*    */     
/* 53 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 64 */     if (type != 21) {
/* 65 */       throw createInvalidStringTypeDOMException(type);
/*    */     }
/* 67 */     Object v = getIdentifiers().get(value.toLowerCase().intern());
/* 68 */     if (v == null) {
/* 69 */       throw createInvalidIdentifierDOMException(value);
/*    */     }
/* 71 */     return (Value)v;
/*    */   }
/*    */   
/*    */   public abstract StringMap getIdentifiers();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/IdentifierManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */