/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctionOneArg
/*     */   extends Function
/*     */   implements ExpressionOwner
/*     */ {
/*     */   Expression m_arg0;
/*     */   
/*     */   public Expression getArg0() {
/*  45 */     return this.m_arg0;
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
/*     */   public void setArg(Expression arg, int argNum) throws WrongNumberArgsException {
/*  61 */     if (0 == argNum) {
/*     */       
/*  63 */       this.m_arg0 = arg;
/*  64 */       arg.exprSetParent((ExpressionNode)this);
/*     */     } else {
/*     */       
/*  67 */       reportWrongNumberArgs();
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
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/*  80 */     if (argNum != 1) {
/*  81 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/*  91 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("one", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTraverseOutsideSubtree() {
/* 102 */     return this.m_arg0.canTraverseOutsideSubtree();
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
/* 117 */     if (null != this.m_arg0) {
/* 118 */       this.m_arg0.fixupVariables(vars, globalsSize);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callArgVisitors(XPathVisitor visitor) {
/* 126 */     if (null != this.m_arg0) {
/* 127 */       this.m_arg0.callVisitors(this, visitor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getExpression() {
/* 136 */     return this.m_arg0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(Expression exp) {
/* 144 */     exp.exprSetParent((ExpressionNode)this);
/* 145 */     this.m_arg0 = exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 153 */     if (!super.deepEquals(expr)) {
/* 154 */       return false;
/*     */     }
/* 156 */     if (null != this.m_arg0) {
/*     */       
/* 158 */       if (null == ((FunctionOneArg)expr).m_arg0) {
/* 159 */         return false;
/*     */       }
/* 161 */       if (!this.m_arg0.deepEquals(((FunctionOneArg)expr).m_arg0)) {
/* 162 */         return false;
/*     */       }
/* 164 */     } else if (null != ((FunctionOneArg)expr).m_arg0) {
/* 165 */       return false;
/*     */     } 
/* 167 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FunctionOneArg.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */