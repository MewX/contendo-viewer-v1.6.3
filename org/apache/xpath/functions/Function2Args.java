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
/*     */ public class Function2Args
/*     */   extends FunctionOneArg
/*     */ {
/*     */   Expression m_arg1;
/*     */   
/*     */   public Expression getArg1() {
/*  45 */     return this.m_arg1;
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
/*  61 */     if (null != this.m_arg1) {
/*  62 */       this.m_arg1.fixupVariables(vars, globalsSize);
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
/*     */ 
/*     */   
/*     */   public void setArg(Expression arg, int argNum) throws WrongNumberArgsException {
/*  80 */     if (argNum == 0) {
/*  81 */       super.setArg(arg, argNum);
/*  82 */     } else if (1 == argNum) {
/*     */       
/*  84 */       this.m_arg1 = arg;
/*  85 */       arg.exprSetParent((ExpressionNode)this);
/*     */     } else {
/*     */       
/*  88 */       reportWrongNumberArgs();
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
/* 101 */     if (argNum != 2) {
/* 102 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 112 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("two", null));
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
/* 123 */     return super.canTraverseOutsideSubtree() ? true : this.m_arg1.canTraverseOutsideSubtree();
/*     */   }
/*     */   
/*     */   class Arg1Owner implements ExpressionOwner { Arg1Owner(Function2Args this$0) {
/* 127 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final Function2Args this$0;
/*     */     
/*     */     public Expression getExpression() {
/* 134 */       return this.this$0.m_arg1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 143 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 144 */       this.this$0.m_arg1 = exp;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callArgVisitors(XPathVisitor visitor) {
/* 154 */     super.callArgVisitors(visitor);
/* 155 */     if (null != this.m_arg1) {
/* 156 */       this.m_arg1.callVisitors(new Arg1Owner(this), visitor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 164 */     if (!super.deepEquals(expr)) {
/* 165 */       return false;
/*     */     }
/* 167 */     if (null != this.m_arg1) {
/*     */       
/* 169 */       if (null == ((Function2Args)expr).m_arg1) {
/* 170 */         return false;
/*     */       }
/* 172 */       if (!this.m_arg1.deepEquals(((Function2Args)expr).m_arg1)) {
/* 173 */         return false;
/*     */       }
/* 175 */     } else if (null != ((Function2Args)expr).m_arg1) {
/* 176 */       return false;
/*     */     } 
/* 178 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/Function2Args.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */