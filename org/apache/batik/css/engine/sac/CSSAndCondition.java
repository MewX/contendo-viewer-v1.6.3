/*    */ package org.apache.batik.css.engine.sac;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.w3c.css.sac.Condition;
/*    */ import org.w3c.dom.Element;
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
/*    */ public class CSSAndCondition
/*    */   extends AbstractCombinatorCondition
/*    */ {
/*    */   public CSSAndCondition(Condition c1, Condition c2) {
/* 38 */     super(c1, c2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 46 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 53 */     return (((ExtendedCondition)getFirstCondition()).match(e, pseudoE) && ((ExtendedCondition)getSecondCondition()).match(e, pseudoE));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fillAttributeSet(Set attrSet) {
/* 61 */     ((ExtendedCondition)getFirstCondition()).fillAttributeSet(attrSet);
/* 62 */     ((ExtendedCondition)getSecondCondition()).fillAttributeSet(attrSet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     return String.valueOf(getFirstCondition()) + getSecondCondition();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSAndCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */