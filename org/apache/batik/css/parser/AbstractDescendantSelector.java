/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.DescendantSelector;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractDescendantSelector
/*    */   implements DescendantSelector
/*    */ {
/*    */   protected Selector ancestorSelector;
/*    */   protected SimpleSelector simpleSelector;
/*    */   
/*    */   protected AbstractDescendantSelector(Selector ancestor, SimpleSelector simple) {
/* 50 */     this.ancestorSelector = ancestor;
/* 51 */     this.simpleSelector = simple;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Selector getAncestorSelector() {
/* 58 */     return this.ancestorSelector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SimpleSelector getSimpleSelector() {
/* 65 */     return this.simpleSelector;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/AbstractDescendantSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */