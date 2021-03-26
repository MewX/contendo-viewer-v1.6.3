/*    */ package jp.cssj.sakae.sac.parser;
/*    */ 
/*    */ import jp.cssj.sakae.sac.css.CombinatorCondition;
/*    */ import jp.cssj.sakae.sac.css.Condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractCombinatorCondition
/*    */   implements CombinatorCondition
/*    */ {
/*    */   protected Condition firstCondition;
/*    */   protected Condition secondCondition;
/*    */   
/*    */   protected AbstractCombinatorCondition(Condition c1, Condition c2) {
/* 81 */     this.firstCondition = c1;
/* 82 */     this.secondCondition = c2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getFirstCondition() {
/* 89 */     return this.firstCondition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getSecondCondition() {
/* 96 */     return this.secondCondition;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/AbstractCombinatorCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */