/*    */ package jp.cssj.sakae.sac.parser;
/*    */ 
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
/*    */ public class DefaultDirectAdjacentSelector
/*    */   extends AbstractSiblingSelector
/*    */ {
/*    */   public DefaultDirectAdjacentSelector(short type, Selector parent, SimpleSelector simple) {
/* 71 */     super(type, parent, simple);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 79 */     return 12;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     return getSelector() + " + " + getSiblingSelector();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultDirectAdjacentSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */