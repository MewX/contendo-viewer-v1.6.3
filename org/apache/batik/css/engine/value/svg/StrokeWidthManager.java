/*    */ package org.apache.batik.css.engine.value.svg;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
/*    */ import org.apache.batik.css.engine.value.LengthManager;
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
/*    */ public class StrokeWidthManager
/*    */   extends LengthManager
/*    */ {
/*    */   public boolean isInheritedProperty() {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimatableProperty() {
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAdditiveProperty() {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPropertyType() {
/* 64 */     return 17;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPropertyName() {
/* 71 */     return "stroke-width";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getDefaultValue() {
/* 78 */     return SVGValueConstants.NUMBER_1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 86 */     if (lu.getLexicalUnitType() == 12) {
/* 87 */       return SVGValueConstants.INHERIT_VALUE;
/*    */     }
/* 89 */     return super.createValue(lu, engine);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getOrientation() {
/* 97 */     return 2;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/StrokeWidthManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */