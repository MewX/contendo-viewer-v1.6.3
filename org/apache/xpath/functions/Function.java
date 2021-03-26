/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Function
/*     */   extends Expression
/*     */ {
/*     */   public void setArg(Expression arg, int argNum) throws WrongNumberArgsException {
/*  56 */     reportWrongNumberArgs();
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
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/*  71 */     if (argNum != 0) {
/*  72 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/*  83 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("zero", null));
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  98 */     System.out.println("Error! Function.execute should not be called!");
/*     */     
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callArgVisitors(XPathVisitor visitor) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 116 */     if (visitor.visitFunction(owner, this))
/*     */     {
/* 118 */       callArgVisitors(visitor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 127 */     if (!isSameClass(expr)) {
/* 128 */       return false;
/*     */     }
/* 130 */     return true;
/*     */   }
/*     */   
/*     */   public void postCompileStep(Compiler compiler) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/Function.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */