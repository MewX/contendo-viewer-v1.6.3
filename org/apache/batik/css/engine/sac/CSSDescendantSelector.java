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
/*    */ public class CSSDescendantSelector
/*    */   extends AbstractDescendantSelector
/*    */ {
/*    */   public CSSDescendantSelector(Selector ancestor, SimpleSelector simple) {
/* 41 */     super(ancestor, simple);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 49 */     return 10;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 56 */     ExtendedSelector p = (ExtendedSelector)getAncestorSelector();
/* 57 */     if (!((ExtendedSelector)getSimpleSelector()).match(e, pseudoE))
/* 58 */       return false; 
/* 59 */     for (Node n = e.getParentNode(); n != null; n = n.getParentNode()) {
/* 60 */       if (n.getNodeType() == 1 && p.match((Element)n, null))
/*    */       {
/* 62 */         return true;
/*    */       }
/*    */     } 
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fillAttributeSet(Set attrSet) {
/* 72 */     ((ExtendedSelector)getSimpleSelector()).fillAttributeSet(attrSet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     return getAncestorSelector() + " " + getSimpleSelector();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSDescendantSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */