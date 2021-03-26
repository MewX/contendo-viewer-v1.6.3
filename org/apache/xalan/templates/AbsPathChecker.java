/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import org.apache.xpath.ExpressionOwner;
/*    */ import org.apache.xpath.XPathVisitor;
/*    */ import org.apache.xpath.axes.LocPathIterator;
/*    */ import org.apache.xpath.functions.Function;
/*    */ import org.apache.xpath.operations.Variable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbsPathChecker
/*    */   extends XPathVisitor
/*    */ {
/*    */   private boolean m_isAbs = true;
/*    */   
/*    */   public boolean checkAbsolute(LocPathIterator path) {
/* 46 */     this.m_isAbs = true;
/* 47 */     path.callVisitors(null, this);
/* 48 */     return this.m_isAbs;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean visitFunction(ExpressionOwner owner, Function func) {
/* 60 */     if (func instanceof org.apache.xpath.functions.FuncCurrent || func instanceof org.apache.xpath.functions.FuncExtFunction)
/*    */     {
/* 62 */       this.m_isAbs = false; } 
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean visitVariableRef(ExpressionOwner owner, Variable var) {
/* 75 */     this.m_isAbs = false;
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/AbsPathChecker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */