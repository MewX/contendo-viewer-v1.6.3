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
/*    */ public class CSSChildSelector
/*    */   extends AbstractDescendantSelector
/*    */ {
/*    */   public CSSChildSelector(Selector ancestor, SimpleSelector simple) {
/* 41 */     super(ancestor, simple);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 49 */     return 11;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 56 */     Node n = e.getParentNode();
/* 57 */     if (n != null && n.getNodeType() == 1) {
/* 58 */       return (((ExtendedSelector)getAncestorSelector()).match((Element)n, null) && ((ExtendedSelector)getSimpleSelector()).match(e, pseudoE));
/*    */     }
/*    */ 
/*    */     
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fillAttributeSet(Set attrSet) {
/* 69 */     ((ExtendedSelector)getAncestorSelector()).fillAttributeSet(attrSet);
/* 70 */     ((ExtendedSelector)getSimpleSelector()).fillAttributeSet(attrSet);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     SimpleSelector s = getSimpleSelector();
/* 78 */     if (s.getSelectorType() == 9) {
/* 79 */       return String.valueOf(getAncestorSelector()) + s;
/*    */     }
/* 81 */     return getAncestorSelector() + " > " + s;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSChildSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */