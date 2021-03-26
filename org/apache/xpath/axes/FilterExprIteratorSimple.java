/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterExprIteratorSimple
/*     */   extends LocPathIterator
/*     */ {
/*     */   private Expression m_expr;
/*     */   private transient XNodeSet m_exprObj;
/*     */   private boolean m_mustHardReset = false;
/*     */   private boolean m_canDetachNodeset = true;
/*     */   
/*     */   public FilterExprIteratorSimple() {
/*  56 */     super(null);
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
/*     */   public FilterExprIteratorSimple(Expression expr) {
/*  68 */     super(null);
/*  69 */     this.m_expr = expr;
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
/*  81 */     super.setRoot(context, environment);
/*  82 */     this.m_exprObj = executeFilterExpr(context, this.m_execContext, getPrefixResolver(), getIsTopLevel(), this.m_stackFrame, this.m_expr);
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
/*     */   public static XNodeSet executeFilterExpr(int context, XPathContext xctxt, PrefixResolver prefixResolver, boolean isTopLevel, int stackFrame, Expression expr) throws WrappedRuntimeException {
/*  97 */     PrefixResolver savedResolver = xctxt.getNamespaceContext();
/*  98 */     XNodeSet result = null;
/*     */ 
/*     */ 
/*     */     
/* 102 */     try { xctxt.pushCurrentNode(context);
/* 103 */       xctxt.setNamespaceContext(prefixResolver);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       if (isTopLevel)
/*     */       
/*     */       { 
/* 113 */         VariableStack vars = xctxt.getVarStack();
/*     */ 
/*     */         
/* 116 */         int savedStart = vars.getStackFrame();
/* 117 */         vars.setStackFrame(stackFrame);
/*     */         
/* 119 */         result = (XNodeSet)expr.execute(xctxt);
/* 120 */         result.setShouldCacheNodes(true);
/*     */ 
/*     */         
/* 123 */         vars.setStackFrame(savedStart); }
/*     */       else
/*     */       
/* 126 */       { result = (XNodeSet)expr.execute(xctxt); }  } catch (TransformerException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       throw new WrappedRuntimeException(se); }
/*     */     
/*     */     finally
/*     */     
/* 137 */     { xctxt.popCurrentNode();
/* 138 */       xctxt.setNamespaceContext(savedResolver); }
/*     */     
/* 140 */     return result;
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
/*     */   public int nextNode() {
/*     */     byte b;
/* 153 */     if (this.m_foundLast) {
/* 154 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 158 */     if (null != this.m_exprObj) {
/*     */       
/* 160 */       this.m_lastFetched = b = this.m_exprObj.nextNode();
/*     */     } else {
/*     */       
/* 163 */       this.m_lastFetched = b = -1;
/*     */     } 
/*     */     
/* 166 */     if (-1 != b) {
/*     */       
/* 168 */       this.m_pos++;
/* 169 */       return b;
/*     */     } 
/*     */ 
/*     */     
/* 173 */     this.m_foundLast = true;
/*     */     
/* 175 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/* 186 */     if (this.m_allowDetach) {
/*     */       
/* 188 */       super.detach();
/* 189 */       this.m_exprObj.detach();
/* 190 */       this.m_exprObj = null;
/*     */     } 
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
/* 206 */     super.fixupVariables(vars, globalsSize);
/* 207 */     this.m_expr.fixupVariables(vars, globalsSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getInnerExpression() {
/* 215 */     return this.m_expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInnerExpression(Expression expr) {
/* 223 */     expr.exprSetParent((ExpressionNode)this);
/* 224 */     this.m_expr = expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/* 233 */     if (null != this.m_expr && this.m_expr instanceof PathComponent)
/*     */     {
/* 235 */       return ((PathComponent)this.m_expr).getAnalysisBits();
/*     */     }
/* 237 */     return 67108864;
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
/* 249 */     return this.m_exprObj.isDocOrdered();
/*     */   }
/*     */   class filterExprOwner implements ExpressionOwner { filterExprOwner(FilterExprIteratorSimple this$0) {
/* 252 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final FilterExprIteratorSimple this$0;
/*     */     
/*     */     public Expression getExpression() {
/* 259 */       return this.this$0.m_expr;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 267 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 268 */       this.this$0.m_expr = exp;
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
/* 284 */     this.m_expr.callVisitors(new filterExprOwner(this), visitor);
/*     */     
/* 286 */     super.callPredicateVisitors(visitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 294 */     if (!super.deepEquals(expr)) {
/* 295 */       return false;
/*     */     }
/* 297 */     FilterExprIteratorSimple fet = (FilterExprIteratorSimple)expr;
/* 298 */     if (!this.m_expr.deepEquals(fet.m_expr)) {
/* 299 */       return false;
/*     */     }
/* 301 */     return true;
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
/* 312 */     if (null != this.m_exprObj) {
/* 313 */       return this.m_exprObj.getAxis();
/*     */     }
/* 315 */     return 20;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/FilterExprIteratorSimple.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */