/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.Selector;
/*    */ import org.w3c.css.sac.SimpleSelector;
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
/*    */ public class DefaultDescendantSelector
/*    */   extends AbstractDescendantSelector
/*    */ {
/*    */   public DefaultDescendantSelector(Selector ancestor, SimpleSelector simple) {
/* 38 */     super(ancestor, simple);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 46 */     return 10;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return getAncestorSelector() + " " + getSimpleSelector();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultDescendantSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */