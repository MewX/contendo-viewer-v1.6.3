/*     */ package org.apache.xpath.operations;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Operation
/*     */   extends Expression
/*     */   implements ExpressionOwner
/*     */ {
/*     */   protected Expression m_left;
/*     */   protected Expression m_right;
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/*  53 */     this.m_left.fixupVariables(vars, globalsSize);
/*  54 */     this.m_right.fixupVariables(vars, globalsSize);
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
/*     */   public boolean canTraverseOutsideSubtree() {
/*  67 */     if (null != this.m_left && this.m_left.canTraverseOutsideSubtree()) {
/*  68 */       return true;
/*     */     }
/*  70 */     if (null != this.m_right && this.m_right.canTraverseOutsideSubtree()) {
/*  71 */       return true;
/*     */     }
/*  73 */     return false;
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
/*     */   public void setLeftRight(Expression l, Expression r) {
/*  85 */     this.m_left = l;
/*  86 */     this.m_right = r;
/*  87 */     l.exprSetParent((ExpressionNode)this);
/*  88 */     r.exprSetParent((ExpressionNode)this);
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 106 */     XObject left = this.m_left.execute(xctxt, true);
/* 107 */     XObject right = this.m_right.execute(xctxt, true);
/*     */     
/* 109 */     XObject result = operate(left, right);
/* 110 */     left.detach();
/* 111 */     right.detach();
/* 112 */     return result;
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
/*     */   public XObject operate(XObject left, XObject right) throws TransformerException {
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getLeftOperand() {
/* 135 */     return this.m_left;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getRightOperand() {
/* 141 */     return this.m_right;
/*     */   }
/*     */   class LeftExprOwner implements ExpressionOwner { LeftExprOwner(Operation this$0) {
/* 144 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final Operation this$0;
/*     */     
/*     */     public Expression getExpression() {
/* 151 */       return this.this$0.m_left;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 159 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 160 */       this.this$0.m_left = exp;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 169 */     if (visitor.visitBinaryOperation(owner, this)) {
/*     */       
/* 171 */       this.m_left.callVisitors(new LeftExprOwner(this), visitor);
/* 172 */       this.m_right.callVisitors(this, visitor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getExpression() {
/* 181 */     return this.m_right;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(Expression exp) {
/* 189 */     exp.exprSetParent((ExpressionNode)this);
/* 190 */     this.m_right = exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 198 */     if (!isSameClass(expr)) {
/* 199 */       return false;
/*     */     }
/* 201 */     if (!this.m_left.deepEquals(((Operation)expr).m_left)) {
/* 202 */       return false;
/*     */     }
/* 204 */     if (!this.m_right.deepEquals(((Operation)expr).m_right)) {
/* 205 */       return false;
/*     */     }
/* 207 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/Operation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */