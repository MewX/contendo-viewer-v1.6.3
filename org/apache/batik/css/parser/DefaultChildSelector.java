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
/*    */ public class DefaultChildSelector
/*    */   extends AbstractDescendantSelector
/*    */ {
/*    */   public DefaultChildSelector(Selector ancestor, SimpleSelector simple) {
/* 37 */     super(ancestor, simple);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 45 */     return 11;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     SimpleSelector s = getSimpleSelector();
/* 53 */     if (s.getSelectorType() == 9) {
/* 54 */       return String.valueOf(getAncestorSelector()) + s;
/*    */     }
/* 56 */     return getAncestorSelector() + " > " + s;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultChildSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */