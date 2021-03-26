/*    */ package org.apache.batik.css.engine.sac;
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
/*    */ 
/*    */ public abstract class AbstractDescendantSelector
/*    */   implements ExtendedSelector, DescendantSelector
/*    */ {
/*    */   protected Selector ancestorSelector;
/*    */   protected SimpleSelector simpleSelector;
/*    */   
/*    */   protected AbstractDescendantSelector(Selector ancestor, SimpleSelector simple) {
/* 51 */     this.ancestorSelector = ancestor;
/* 52 */     this.simpleSelector = simple;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 60 */     if (obj == null || obj.getClass() != getClass()) {
/* 61 */       return false;
/*    */     }
/* 63 */     AbstractDescendantSelector s = (AbstractDescendantSelector)obj;
/* 64 */     return s.simpleSelector.equals(this.simpleSelector);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSpecificity() {
/* 71 */     return ((ExtendedSelector)this.ancestorSelector).getSpecificity() + ((ExtendedSelector)this.simpleSelector).getSpecificity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Selector getAncestorSelector() {
/* 80 */     return this.ancestorSelector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SimpleSelector getSimpleSelector() {
/* 88 */     return this.simpleSelector;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/AbstractDescendantSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */