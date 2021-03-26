/*    */ package org.apache.batik.css.engine.value;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
/*    */ import org.apache.batik.css.engine.CSSStylableElement;
/*    */ import org.apache.batik.css.engine.StyleMap;
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
/*    */ public abstract class AbstractValueManager
/*    */   extends AbstractValueFactory
/*    */   implements ValueManager
/*    */ {
/*    */   public Value createFloatValue(short unitType, float floatValue) throws DOMException {
/* 44 */     throw createDOMException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 53 */     throw createDOMException();
/*    */   }
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
/*    */   public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
/* 67 */     if (value.getCssValueType() == 1 && value.getPrimitiveType() == 20)
/*    */     {
/*    */       
/* 70 */       return new URIValue(value.getStringValue(), value.getStringValue());
/*    */     }
/*    */     
/* 73 */     return value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/AbstractValueManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */