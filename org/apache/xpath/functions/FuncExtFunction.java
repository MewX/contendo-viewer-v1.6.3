/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.ExtensionsProvider;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.objects.XNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FuncExtFunction
/*     */   extends Function
/*     */ {
/*     */   String m_namespace;
/*     */   String m_extensionName;
/*     */   Object m_methodKey;
/*  68 */   Vector m_argVec = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  84 */     if (null != this.m_argVec) {
/*     */       
/*  86 */       int nArgs = this.m_argVec.size();
/*     */       
/*  88 */       for (int i = 0; i < nArgs; i++) {
/*     */         
/*  90 */         Expression arg = this.m_argVec.elementAt(i);
/*     */         
/*  92 */         arg.fixupVariables(vars, globalsSize);
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
/*     */   public String getNamespace() {
/* 104 */     return this.m_namespace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFunctionName() {
/* 114 */     return this.m_extensionName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getMethodKey() {
/* 124 */     return this.m_methodKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getArg(int n) {
/* 134 */     if (n >= 0 && n < this.m_argVec.size()) {
/* 135 */       return this.m_argVec.elementAt(n);
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArgCount() {
/* 147 */     return this.m_argVec.size();
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
/*     */   public FuncExtFunction(String namespace, String extensionName, Object methodKey) {
/* 165 */     this.m_namespace = namespace;
/* 166 */     this.m_extensionName = extensionName;
/* 167 */     this.m_methodKey = methodKey;
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
/*     */     XNull xNull;
/* 183 */     Vector argVec = new Vector();
/* 184 */     int nArgs = this.m_argVec.size();
/*     */     
/* 186 */     for (int i = 0; i < nArgs; i++) {
/*     */       
/* 188 */       Expression arg = this.m_argVec.elementAt(i);
/*     */       
/* 190 */       XObject xobj = arg.execute(xctxt);
/*     */ 
/*     */ 
/*     */       
/* 194 */       xobj.allowDetachToRelease(false);
/* 195 */       argVec.addElement(xobj);
/*     */     } 
/*     */     
/* 198 */     ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
/* 199 */     Object val = extProvider.extFunction(this, argVec);
/*     */     
/* 201 */     if (null != val) {
/*     */       
/* 203 */       XObject result = XObject.create(val, xctxt);
/*     */     }
/*     */     else {
/*     */       
/* 207 */       xNull = new XNull();
/*     */     } 
/*     */     
/* 210 */     return (XObject)xNull;
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
/* 226 */     this.m_argVec.addElement(arg);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {}
/*     */ 
/*     */ 
/*     */   
/*     */   class ArgExtOwner
/*     */     implements ExpressionOwner
/*     */   {
/*     */     Expression m_exp;
/*     */ 
/*     */     
/*     */     private final FuncExtFunction this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     ArgExtOwner(FuncExtFunction this$0, Expression exp) {
/* 246 */       this.this$0 = this$0;
/* 247 */       this.m_exp = exp;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Expression getExpression() {
/* 255 */       return this.m_exp;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 264 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 265 */       this.m_exp = exp;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callArgVisitors(XPathVisitor visitor) {
/* 275 */     for (int i = 0; i < this.m_argVec.size(); i++) {
/*     */       
/* 277 */       Expression exp = this.m_argVec.elementAt(i);
/* 278 */       exp.callVisitors(new ArgExtOwner(this, exp), visitor);
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
/*     */   public void exprSetParent(ExpressionNode n) {
/* 293 */     super.exprSetParent(n);
/*     */     
/* 295 */     int nArgs = this.m_argVec.size();
/*     */     
/* 297 */     for (int i = 0; i < nArgs; i++) {
/*     */       
/* 299 */       Expression arg = this.m_argVec.elementAt(i);
/*     */       
/* 301 */       arg.exprSetParent(n);
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
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 313 */     String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { "Programmer's assertion:  the method FunctionMultiArgs.reportWrongNumberArgs() should never be called." });
/*     */ 
/*     */ 
/*     */     
/* 317 */     throw new RuntimeException(fMsg);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncExtFunction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */