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
/*     */ 
/*     */ public class FunctionMultiArgs
/*     */   extends Function3Args
/*     */ {
/*     */   Expression[] m_args;
/*     */   
/*     */   public Expression[] getArgs() {
/*  46 */     return this.m_args;
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
/*  63 */     if (argNum < 3) {
/*  64 */       super.setArg(arg, argNum);
/*     */     } else {
/*     */       
/*  67 */       if (null == this.m_args) {
/*     */         
/*  69 */         this.m_args = new Expression[1];
/*  70 */         this.m_args[0] = arg;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  76 */         Expression[] args = new Expression[this.m_args.length + 1];
/*     */         
/*  78 */         System.arraycopy(this.m_args, 0, args, 0, this.m_args.length);
/*     */         
/*  80 */         args[this.m_args.length] = arg;
/*  81 */         this.m_args = args;
/*     */       } 
/*  83 */       arg.exprSetParent((ExpressionNode)this);
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
/*  99 */     super.fixupVariables(vars, globalsSize);
/* 100 */     if (null != this.m_args)
/*     */     {
/* 102 */       for (int i = 0; i < this.m_args.length; i++)
/*     */       {
/* 104 */         this.m_args[i].fixupVariables(vars, globalsSize);
/*     */       }
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
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 127 */     String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { "Programmer's assertion:  the method FunctionMultiArgs.reportWrongNumberArgs() should never be called." });
/*     */ 
/*     */ 
/*     */     
/* 131 */     throw new RuntimeException(fMsg);
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
/*     */   public boolean canTraverseOutsideSubtree() {
/* 143 */     if (super.canTraverseOutsideSubtree()) {
/* 144 */       return true;
/*     */     }
/*     */     
/* 147 */     int n = this.m_args.length;
/*     */     
/* 149 */     for (int i = 0; i < n; i++) {
/*     */       
/* 151 */       if (this.m_args[i].canTraverseOutsideSubtree()) {
/* 152 */         return true;
/*     */       }
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   class ArgMultiOwner
/*     */     implements ExpressionOwner {
/*     */     int m_argIndex;
/*     */     private final FunctionMultiArgs this$0;
/*     */     
/*     */     ArgMultiOwner(FunctionMultiArgs this$0, int index) {
/* 164 */       this.this$0 = this$0;
/* 165 */       this.m_argIndex = index;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Expression getExpression() {
/* 173 */       return this.this$0.m_args[this.m_argIndex];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 182 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 183 */       this.this$0.m_args[this.m_argIndex] = exp;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callArgVisitors(XPathVisitor visitor) {
/* 193 */     super.callArgVisitors(visitor);
/* 194 */     if (null != this.m_args) {
/*     */       
/* 196 */       int n = this.m_args.length;
/* 197 */       for (int i = 0; i < n; i++)
/*     */       {
/* 199 */         this.m_args[i].callVisitors(new ArgMultiOwner(this, i), visitor);
/*     */       }
/*     */     } 
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
/* 212 */     FunctionMultiArgs fma = (FunctionMultiArgs)expr;
/* 213 */     if (null != this.m_args) {
/*     */       
/* 215 */       int n = this.m_args.length;
/* 216 */       if (null == fma || fma.m_args.length != n) {
/* 217 */         return false;
/*     */       }
/* 219 */       for (int i = 0; i < n; i++)
/*     */       {
/* 221 */         if (!this.m_args[i].deepEquals(fma.m_args[i])) {
/* 222 */           return false;
/*     */         }
/*     */       }
/*     */     
/* 226 */     } else if (null != fma.m_args) {
/*     */       
/* 228 */       return false;
/*     */     } 
/*     */     
/* 231 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FunctionMultiArgs.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */