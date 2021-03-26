/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.Condition;
/*    */ import org.w3c.css.sac.ConditionalSelector;
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
/*    */ public class DefaultConditionalSelector
/*    */   implements ConditionalSelector
/*    */ {
/*    */   protected SimpleSelector simpleSelector;
/*    */   protected Condition condition;
/*    */   
/*    */   public DefaultConditionalSelector(SimpleSelector s, Condition c) {
/* 48 */     this.simpleSelector = s;
/* 49 */     this.condition = c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 57 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SimpleSelector getSimpleSelector() {
/* 65 */     return this.simpleSelector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Condition getCondition() {
/* 73 */     return this.condition;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     return String.valueOf(this.simpleSelector) + this.condition;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultConditionalSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */