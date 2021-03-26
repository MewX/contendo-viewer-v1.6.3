/*    */ package org.apache.batik.css.engine.value.svg12;
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
/*    */ 
/*    */ 
/*    */ public class MarginShorthandManager
/*    */   extends AbstractValueFactory
/*    */   implements ShorthandManager
/*    */ {
/*    */   public String getPropertyName() {
/* 46 */     return "margin";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAnimatableProperty() {
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isAdditiveProperty() {
/* 60 */     return false;
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
/* 71 */     if (lu.getLexicalUnitType() == 12) {
/*    */       return;
/*    */     }
/* 74 */     LexicalUnit[] lus = new LexicalUnit[4];
/* 75 */     int cnt = 0;
/* 76 */     while (lu != null) {
/* 77 */       if (cnt == 4) {
/* 78 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*    */       }
/* 80 */       lus[cnt++] = lu;
/* 81 */       lu = lu.getNextLexicalUnit();
/*    */     } 
/* 83 */     switch (cnt) { case 1:
/* 84 */         lus[1] = lus[0]; lus[2] = lus[0]; lus[3] = lus[0]; break;
/* 85 */       case 2: lus[2] = lus[0]; lus[3] = lus[1]; break;
/* 86 */       case 3: lus[3] = lus[1];
/*    */         break; }
/*    */ 
/*    */     
/* 90 */     ph.property("margin-top", lus[0], imp);
/* 91 */     ph.property("margin-right", lus[1], imp);
/* 92 */     ph.property("margin-bottom", lus[2], imp);
/* 93 */     ph.property("margin-left", lus[3], imp);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/MarginShorthandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */