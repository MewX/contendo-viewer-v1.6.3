/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.CombinatorCondition;
/*    */ import org.w3c.css.sac.Condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
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
/* 49 */     this.firstCondition = c1;
/* 50 */     this.secondCondition = c2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getFirstCondition() {
/* 57 */     return this.firstCondition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getSecondCondition() {
/* 64 */     return this.secondCondition;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/AbstractCombinatorCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */