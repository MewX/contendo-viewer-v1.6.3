/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.Selector;
/*    */ import org.w3c.css.sac.SiblingSelector;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSiblingSelector
/*    */   implements SiblingSelector
/*    */ {
/*    */   protected short nodeType;
/*    */   protected Selector selector;
/*    */   protected SimpleSelector simpleSelector;
/*    */   
/*    */   protected AbstractSiblingSelector(short type, Selector sel, SimpleSelector simple) {
/* 56 */     this.nodeType = type;
/* 57 */     this.selector = sel;
/* 58 */     this.simpleSelector = simple;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getNodeType() {
/* 65 */     return this.nodeType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Selector getSelector() {
/* 73 */     return this.selector;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SimpleSelector getSiblingSelector() {
/* 81 */     return this.simpleSelector;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/AbstractSiblingSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */