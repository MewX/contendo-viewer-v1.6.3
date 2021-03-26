/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.functions.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HasPositionalPredChecker
/*     */   extends XPathVisitor
/*     */ {
/*     */   private boolean m_hasPositionalPred = false;
/*  39 */   private int m_predDepth = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean check(LocPathIterator path) {
/*  50 */     HasPositionalPredChecker hppc = new HasPositionalPredChecker();
/*  51 */     path.callVisitors(null, hppc);
/*  52 */     return hppc.m_hasPositionalPred;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean visitFunction(ExpressionOwner owner, Function func) {
/*  64 */     if (func instanceof org.apache.xpath.functions.FuncPosition || func instanceof org.apache.xpath.functions.FuncLast)
/*     */     {
/*  66 */       this.m_hasPositionalPred = true; } 
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean visitPredicate(ExpressionOwner owner, Expression pred) {
/*  95 */     this.m_predDepth++;
/*     */     
/*  97 */     if (this.m_predDepth == 1)
/*     */     {
/*  99 */       if (pred instanceof org.apache.xpath.operations.Variable || pred instanceof org.apache.xpath.objects.XNumber || pred instanceof org.apache.xpath.operations.Div || pred instanceof org.apache.xpath.operations.Plus || pred instanceof org.apache.xpath.operations.Minus || pred instanceof org.apache.xpath.operations.Mod || pred instanceof org.apache.xpath.operations.Quo || pred instanceof org.apache.xpath.operations.Mult || pred instanceof org.apache.xpath.operations.Number || pred instanceof Function) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 109 */         this.m_hasPositionalPred = true;
/*     */       } else {
/* 111 */         pred.callVisitors(owner, this);
/*     */       } 
/*     */     }
/* 114 */     this.m_predDepth--;
/*     */ 
/*     */     
/* 117 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/HasPositionalPredChecker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */