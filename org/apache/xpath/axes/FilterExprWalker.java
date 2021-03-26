/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ 
/*     */ public class FilterExprWalker
/*     */   extends AxesWalker
/*     */ {
/*     */   private Expression m_expr;
/*     */   private transient XNodeSet m_exprObj;
/*     */   private boolean m_mustHardReset;
/*     */   private boolean m_canDetachNodeset;
/*     */   
/*     */   public void init(Compiler compiler, int opPos, int stepType) throws TransformerException {
/*     */     super.init(compiler, opPos, stepType);
/*     */     switch (stepType) {
/*     */       case 24:
/*     */       case 25:
/*     */         this.m_mustHardReset = true;
/*     */       case 22:
/*     */       case 23:
/*     */         this.m_expr = compiler.compile(opPos);
/*     */         this.m_expr.exprSetParent((ExpressionNode)this);
/*     */         if (this.m_expr instanceof org.apache.xpath.operations.Variable)
/*     */           this.m_canDetachNodeset = false; 
/*     */         return;
/*     */     } 
/*     */     this.m_expr = compiler.compile(opPos + 2);
/*     */     this.m_expr.exprSetParent((ExpressionNode)this);
/*     */   }
/*     */   
/*     */   public void detach() {
/*     */     super.detach();
/*     */     if (this.m_canDetachNodeset)
/*     */       this.m_exprObj.detach(); 
/*     */     this.m_exprObj = null;
/*     */   }
/*     */   
/*     */   public FilterExprWalker(WalkingIterator locPathIterator) {
/*  47 */     super(locPathIterator, 20);
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
/* 222 */     this.m_mustHardReset = false;
/* 223 */     this.m_canDetachNodeset = true;
/*     */   }
/*     */   public void setRoot(int root) { super.setRoot(root); this.m_exprObj = FilterExprIteratorSimple.executeFilterExpr(root, this.m_lpi.getXPathContext(), this.m_lpi.getPrefixResolver(), this.m_lpi.getIsTopLevel(), this.m_lpi.m_stackFrame, this.m_expr); }
/*     */   public Object clone() throws CloneNotSupportedException { FilterExprWalker clone = (FilterExprWalker)super.clone(); if (null != this.m_exprObj)
/*     */       clone.m_exprObj = (XNodeSet)this.m_exprObj.clone(); 
/*     */     return clone; } public short acceptNode(int n) { try {
/*     */       if (getPredicateCount() > 0) {
/*     */         countProximityPosition(0);
/*     */         if (!executePredicates(n, this.m_lpi.getXPathContext()))
/*     */           return 3; 
/*     */       } 
/*     */       return 1;
/*     */     } catch (TransformerException se) {
/*     */       throw new RuntimeException(se.getMessage());
/* 237 */     }  } public void fixupVariables(Vector vars, int globalsSize) { super.fixupVariables(vars, globalsSize);
/* 238 */     this.m_expr.fixupVariables(vars, globalsSize); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getInnerExpression() {
/* 246 */     return this.m_expr; } public int getNextNode() { if (null != this.m_exprObj) {
/*     */       int next = this.m_exprObj.nextNode();
/*     */       return next;
/*     */     } 
/*     */     return -1; } public int getLastPos(XPathContext xctxt) {
/*     */     return this.m_exprObj.getLength();
/*     */   }
/*     */   public void setInnerExpression(Expression expr) {
/* 254 */     expr.exprSetParent((ExpressionNode)this);
/* 255 */     this.m_expr = expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/* 265 */     if (null != this.m_expr && this.m_expr instanceof PathComponent)
/*     */     {
/* 267 */       return ((PathComponent)this.m_expr).getAnalysisBits();
/*     */     }
/* 269 */     return 67108864;
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
/* 281 */     return this.m_exprObj.isDocOrdered();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/* 292 */     return this.m_exprObj.getAxis();
/*     */   } class filterExprOwner implements ExpressionOwner { private final FilterExprWalker this$0;
/*     */     filterExprOwner(FilterExprWalker this$0) {
/* 295 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Expression getExpression() {
/* 302 */       return this.this$0.m_expr;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 310 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 311 */       this.this$0.m_expr = exp;
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
/*     */   public void callPredicateVisitors(XPathVisitor visitor) {
/* 326 */     this.m_expr.callVisitors(new filterExprOwner(this), visitor);
/*     */     
/* 328 */     super.callPredicateVisitors(visitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 337 */     if (!super.deepEquals(expr)) {
/* 338 */       return false;
/*     */     }
/* 340 */     FilterExprWalker walker = (FilterExprWalker)expr;
/* 341 */     if (!this.m_expr.deepEquals(walker.m_expr)) {
/* 342 */       return false;
/*     */     }
/* 344 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/FilterExprWalker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */