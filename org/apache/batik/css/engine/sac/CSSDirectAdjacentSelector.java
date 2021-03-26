/*    */ package org.apache.batik.css.engine.sac;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.w3c.css.sac.Selector;
/*    */ import org.w3c.css.sac.SimpleSelector;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
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
/*    */ public class CSSDirectAdjacentSelector
/*    */   extends AbstractSiblingSelector
/*    */ {
/*    */   public CSSDirectAdjacentSelector(short type, Selector parent, SimpleSelector simple) {
/* 44 */     super(type, parent, simple);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 52 */     return 12;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 59 */     Node n = e;
/* 60 */     if (!((ExtendedSelector)getSiblingSelector()).match(e, pseudoE)) {
/* 61 */       return false;
/*    */     }
/* 63 */     while ((n = n.getPreviousSibling()) != null && n.getNodeType() != 1);
/*    */ 
/*    */     
/* 66 */     if (n == null) {
/* 67 */       return false;
/*    */     }
/* 69 */     return ((ExtendedSelector)getSelector()).match((Element)n, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fillAttributeSet(Set attrSet) {
/* 76 */     ((ExtendedSelector)getSelector()).fillAttributeSet(attrSet);
/* 77 */     ((ExtendedSelector)getSiblingSelector()).fillAttributeSet(attrSet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     return getSelector() + " + " + getSiblingSelector();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSDirectAdjacentSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */