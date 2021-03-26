/*    */ package org.apache.batik.css.engine.value.svg;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
/*    */ import org.apache.batik.css.engine.value.AbstractValueFactory;
/*    */ import org.apache.batik.css.engine.value.ShorthandManager;
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
/*    */ public class MarkerShorthandManager
/*    */   extends AbstractValueFactory
/*    */   implements ShorthandManager
/*    */ {
/*    */   public String getPropertyName() {
/* 44 */     return "marker";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimatableProperty() {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAdditiveProperty() {
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValues(CSSEngine eng, ShorthandManager.PropertyHandler ph, LexicalUnit lu, boolean imp) throws DOMException {
/* 69 */     ph.property("marker-end", lu, imp);
/* 70 */     ph.property("marker-mid", lu, imp);
/* 71 */     ph.property("marker-start", lu, imp);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/MarkerShorthandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */