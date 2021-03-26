/*    */ package jp.cssj.sakae.sac.parser;
/*    */ 
/*    */ import jp.cssj.sakae.sac.css.DescendantSelector;
/*    */ import jp.cssj.sakae.sac.css.Selector;
/*    */ import jp.cssj.sakae.sac.css.SimpleSelector;
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
/* 81 */     this.ancestorSelector = ancestor;
/* 82 */     this.simpleSelector = simple;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Selector getAncestorSelector() {
/* 89 */     return this.ancestorSelector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SimpleSelector getSimpleSelector() {
/* 96 */     return this.simpleSelector;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/AbstractDescendantSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */