/*    */ package org.apache.batik.css.engine.sac;
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
/*    */   implements ExtendedCondition, CombinatorCondition
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
/*    */   
/*    */   public boolean equals(Object obj) {
/* 58 */     if (obj == null || obj.getClass() != getClass()) {
/* 59 */       return false;
/*    */     }
/* 61 */     AbstractCombinatorCondition c = (AbstractCombinatorCondition)obj;
/* 62 */     return (c.firstCondition.equals(this.firstCondition) && c.secondCondition.equals(this.secondCondition));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSpecificity() {
/* 70 */     return ((ExtendedCondition)getFirstCondition()).getSpecificity() + ((ExtendedCondition)getSecondCondition()).getSpecificity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getFirstCondition() {
/* 79 */     return this.firstCondition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getSecondCondition() {
/* 87 */     return this.secondCondition;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/AbstractCombinatorCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */