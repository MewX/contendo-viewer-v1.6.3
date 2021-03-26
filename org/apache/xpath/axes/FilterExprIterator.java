/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.objects.XNodeSet;
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
/*     */ 
/*     */ 
/*     */ public class FilterExprIterator
/*     */   extends BasicTestIterator
/*     */ {
/*     */   private Expression m_expr;
/*     */   private transient XNodeSet m_exprObj;
/*     */   private boolean m_mustHardReset = false;
/*     */   private boolean m_canDetachNodeset = true;
/*     */   
/*     */   public FilterExprIterator() {
/*  48 */     super(null);
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
/*     */   public FilterExprIterator(Expression expr) {
/*  60 */     super(null);
/*  61 */     this.m_expr = expr;
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
/*     */   public void setRoot(int context, Object environment) {
/*  73 */     super.setRoot(context, environment);
/*     */     
/*  75 */     this.m_exprObj = FilterExprIteratorSimple.executeFilterExpr(context, this.m_execContext, getPrefixResolver(), getIsTopLevel(), this.m_stackFrame, this.m_expr);
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
/*     */   protected int getNextNode() {
/*  87 */     if (null != this.m_exprObj) {
/*     */       
/*  89 */       this.m_lastFetched = this.m_exprObj.nextNode();
/*     */     } else {
/*     */       
/*  92 */       this.m_lastFetched = -1;
/*     */     } 
/*  94 */     return this.m_lastFetched;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/* 104 */     super.detach();
/* 105 */     this.m_exprObj.detach();
/* 106 */     this.m_exprObj = null;
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
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/* 121 */     super.fixupVariables(vars, globalsSize);
/* 122 */     this.m_expr.fixupVariables(vars, globalsSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getInnerExpression() {
/* 130 */     return this.m_expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInnerExpression(Expression expr) {
/* 138 */     expr.exprSetParent((ExpressionNode)this);
/* 139 */     this.m_expr = expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/* 148 */     if (null != this.m_expr && this.m_expr instanceof PathComponent)
/*     */     {
/* 150 */       return ((PathComponent)this.m_expr).getAnalysisBits();
/*     */     }
/* 152 */     return 67108864;
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
/*     */   public boolean isDocOrdered() {
/* 164 */     return this.m_exprObj.isDocOrdered();
/*     */   }
/*     */   class filterExprOwner implements ExpressionOwner { filterExprOwner(FilterExprIterator this$0) {
/* 167 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final FilterExprIterator this$0;
/*     */     
/*     */     public Expression getExpression() {
/* 174 */       return this.this$0.m_expr;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 182 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 183 */       this.this$0.m_expr = exp;
/*     */     } }
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
/*     */   public void callPredicateVisitors(XPathVisitor visitor) {
/* 199 */     this.m_expr.callVisitors(new filterExprOwner(this), visitor);
/*     */     
/* 201 */     super.callPredicateVisitors(visitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 209 */     if (!super.deepEquals(expr)) {
/* 210 */       return false;
/*     */     }
/* 212 */     FilterExprIterator fet = (FilterExprIterator)expr;
/* 213 */     if (!this.m_expr.deepEquals(fet.m_expr)) {
/* 214 */       return false;
/*     */     }
/* 216 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/FilterExprIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */