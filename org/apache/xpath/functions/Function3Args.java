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
/*     */ 
/*     */ public class Function3Args
/*     */   extends Function2Args
/*     */ {
/*     */   Expression m_arg2;
/*     */   
/*     */   public Expression getArg2() {
/*  45 */     return this.m_arg2;
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
/*  60 */     super.fixupVariables(vars, globalsSize);
/*  61 */     if (null != this.m_arg2) {
/*  62 */       this.m_arg2.fixupVariables(vars, globalsSize);
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
/*     */   public void setArg(Expression arg, int argNum) throws WrongNumberArgsException {
/*  78 */     if (argNum < 2) {
/*  79 */       super.setArg(arg, argNum);
/*  80 */     } else if (2 == argNum) {
/*     */       
/*  82 */       this.m_arg2 = arg;
/*  83 */       arg.exprSetParent((ExpressionNode)this);
/*     */     } else {
/*     */       
/*  86 */       reportWrongNumberArgs();
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
/*  99 */     if (argNum != 3) {
/* 100 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 110 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("three", null));
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
/* 121 */     return super.canTraverseOutsideSubtree() ? true : this.m_arg2.canTraverseOutsideSubtree();
/*     */   }
/*     */   
/*     */   class Arg2Owner implements ExpressionOwner { Arg2Owner(Function3Args this$0) {
/* 125 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final Function3Args this$0;
/*     */     
/*     */     public Expression getExpression() {
/* 132 */       return this.this$0.m_arg2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 141 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 142 */       this.this$0.m_arg2 = exp;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callArgVisitors(XPathVisitor visitor) {
/* 152 */     super.callArgVisitors(visitor);
/* 153 */     if (null != this.m_arg2) {
/* 154 */       this.m_arg2.callVisitors(new Arg2Owner(this), visitor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 162 */     if (!super.deepEquals(expr)) {
/* 163 */       return false;
/*     */     }
/* 165 */     if (null != this.m_arg2) {
/*     */       
/* 167 */       if (null == ((Function3Args)expr).m_arg2) {
/* 168 */         return false;
/*     */       }
/* 170 */       if (!this.m_arg2.deepEquals(((Function3Args)expr).m_arg2)) {
/* 171 */         return false;
/*     */       }
/* 173 */     } else if (null != ((Function3Args)expr).m_arg2) {
/* 174 */       return false;
/*     */     } 
/* 176 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/Function3Args.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */