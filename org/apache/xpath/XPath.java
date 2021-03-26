/*     */ package org.apache.xpath;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.DefaultErrorHandler;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ import org.apache.xpath.compiler.FunctionTable;
/*     */ import org.apache.xpath.compiler.XPathParser;
/*     */ import org.apache.xpath.functions.Function;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XPath
/*     */   implements Serializable, ExpressionOwner
/*     */ {
/*     */   private Expression m_mainExp;
/*     */   String m_patternString;
/*     */   public static final int SELECT = 0;
/*     */   public static final int MATCH = 1;
/*     */   private static final boolean DEBUG_MATCHES = false;
/*     */   public static final double MATCH_SCORE_NONE = -InfinityD;
/*     */   public static final double MATCH_SCORE_QNAME = 0.0D;
/*     */   public static final double MATCH_SCORE_NSWILD = -0.25D;
/*     */   public static final double MATCH_SCORE_NODETEST = -0.5D;
/*     */   public static final double MATCH_SCORE_OTHER = 0.5D;
/*     */   
/*     */   public Expression getExpression() {
/*  58 */     return this.m_mainExp;
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
/*  73 */     this.m_mainExp.fixupVariables(vars, globalsSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(Expression exp) {
/*  84 */     if (null != this.m_mainExp)
/*  85 */       exp.exprSetParent(this.m_mainExp.exprGetParent()); 
/*  86 */     this.m_mainExp = exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceLocator getLocator() {
/*  97 */     return this.m_mainExp;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPatternString() {
/* 125 */     return this.m_patternString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPath(String exprString, SourceLocator locator, PrefixResolver prefixResolver, int type, ErrorListener errorListener) throws TransformerException {
/*     */     DefaultErrorHandler defaultErrorHandler;
/* 153 */     if (null == errorListener) {
/* 154 */       defaultErrorHandler = new DefaultErrorHandler();
/*     */     }
/* 156 */     this.m_patternString = exprString;
/*     */     
/* 158 */     XPathParser parser = new XPathParser((ErrorListener)defaultErrorHandler, locator);
/* 159 */     Compiler compiler = new Compiler((ErrorListener)defaultErrorHandler, locator);
/*     */     
/* 161 */     if (0 == type) {
/* 162 */       parser.initXPath(compiler, exprString, prefixResolver);
/* 163 */     } else if (1 == type) {
/* 164 */       parser.initMatchPattern(compiler, exprString, prefixResolver);
/*     */     } else {
/* 166 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_CANNOT_DEAL_XPATH_TYPE", new Object[] { Integer.toString(type) }));
/*     */     } 
/*     */     
/* 169 */     Expression expr = compiler.compile(0);
/*     */ 
/*     */     
/* 172 */     setExpression(expr);
/*     */     
/* 174 */     if (null != locator && locator instanceof ExpressionNode)
/*     */     {
/* 176 */       expr.exprSetParent((ExpressionNode)locator);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPath(String exprString, SourceLocator locator, PrefixResolver prefixResolver, int type) throws TransformerException {
/* 198 */     this(exprString, locator, prefixResolver, type, null);
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
/*     */   public XPath(Expression expr) {
/* 210 */     setExpression(expr);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject execute(XPathContext xctxt, Node contextNode, PrefixResolver namespaceContext) throws TransformerException {
/* 234 */     return execute(xctxt, xctxt.getDTMHandleFromNode(contextNode), namespaceContext);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject execute(XPathContext xctxt, int contextNode, PrefixResolver namespaceContext) throws TransformerException {
/* 260 */     xctxt.pushNamespaceContext(namespaceContext);
/*     */     
/* 262 */     xctxt.pushCurrentNodeAndExpression(contextNode, contextNode);
/*     */     
/* 264 */     XObject xobj = null;
/*     */ 
/*     */ 
/*     */     
/* 268 */     try { xobj = this.m_mainExp.execute(xctxt); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 272 */       te.setLocator(getLocator());
/* 273 */       ErrorListener el = xctxt.getErrorListener();
/* 274 */       if (null != el)
/*     */       
/* 276 */       { el.error(te); }
/*     */       else
/*     */       
/* 279 */       { throw te; }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 283 */       while (e instanceof WrappedRuntimeException)
/*     */       {
/* 285 */         e = ((WrappedRuntimeException)e).getException();
/*     */       }
/*     */ 
/*     */       
/* 289 */       String msg = e.getMessage();
/*     */       
/* 291 */       if (msg == null || msg.length() == 0) {
/* 292 */         msg = XPATHMessages.createXPATHMessage("ER_XPATH_ERROR", null);
/*     */       }
/*     */ 
/*     */       
/* 296 */       TransformerException te = new TransformerException(msg, getLocator(), e);
/*     */       
/* 298 */       ErrorListener el = xctxt.getErrorListener();
/*     */       
/* 300 */       if (null != el) {
/*     */         
/* 302 */         el.fatalError(te);
/*     */       } else {
/*     */         
/* 305 */         throw te;
/*     */       }  }
/*     */     finally
/*     */     
/* 309 */     { xctxt.popNamespaceContext();
/*     */       
/* 311 */       xctxt.popCurrentNodeAndExpression(); }
/*     */ 
/*     */     
/* 314 */     return xobj;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean bool(XPathContext xctxt, int contextNode, PrefixResolver namespaceContext) throws TransformerException {
/* 337 */     xctxt.pushNamespaceContext(namespaceContext);
/*     */     
/* 339 */     xctxt.pushCurrentNodeAndExpression(contextNode, contextNode);
/*     */ 
/*     */ 
/*     */     
/* 343 */     try { return this.m_mainExp.bool(xctxt); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 347 */       te.setLocator(getLocator());
/* 348 */       ErrorListener el = xctxt.getErrorListener();
/* 349 */       if (null != el)
/*     */       
/* 351 */       { el.error(te); }
/*     */       else
/*     */       
/* 354 */       { throw te; }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 358 */       while (e instanceof WrappedRuntimeException)
/*     */       {
/* 360 */         e = ((WrappedRuntimeException)e).getException();
/*     */       }
/*     */ 
/*     */       
/* 364 */       String msg = e.getMessage();
/*     */       
/* 366 */       if (msg == null || msg.length() == 0) {
/* 367 */         msg = XPATHMessages.createXPATHMessage("ER_XPATH_ERROR", null);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 372 */       TransformerException te = new TransformerException(msg, getLocator(), e);
/*     */       
/* 374 */       ErrorListener el = xctxt.getErrorListener();
/*     */       
/* 376 */       if (null != el) {
/*     */         
/* 378 */         el.fatalError(te);
/*     */       } else {
/*     */         
/* 381 */         throw te;
/*     */       }  }
/*     */     finally
/*     */     
/* 385 */     { xctxt.popNamespaceContext();
/*     */       
/* 387 */       xctxt.popCurrentNodeAndExpression(); }
/*     */ 
/*     */     
/* 390 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMatchScore(XPathContext xctxt, int context) throws TransformerException {
/* 413 */     xctxt.pushCurrentNode(context);
/* 414 */     xctxt.pushCurrentExpressionNode(context);
/*     */ 
/*     */     
/*     */     try {
/* 418 */       XObject score = this.m_mainExp.execute(xctxt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 428 */       return score.num();
/*     */     }
/*     */     finally {
/*     */       
/* 432 */       xctxt.popCurrentNode();
/* 433 */       xctxt.popCurrentExpressionNode();
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
/*     */   public void installFunction(String name, int funcIndex, Function func) {
/* 448 */     FunctionTable.installFunction((Expression)func, funcIndex);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warn(XPathContext xctxt, int sourceNode, String msg, Object[] args) throws TransformerException {
/* 470 */     String fmsg = XPATHMessages.createXPATHWarning(msg, args);
/* 471 */     ErrorListener ehandler = xctxt.getErrorListener();
/*     */     
/* 473 */     if (null != ehandler)
/*     */     {
/*     */ 
/*     */       
/* 477 */       ehandler.warning(new TransformerException(fmsg, xctxt.getSAXLocator()));
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
/*     */   public void assertion(boolean b, String msg) {
/* 493 */     if (!b) {
/*     */       
/* 495 */       String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg });
/*     */ 
/*     */ 
/*     */       
/* 499 */       throw new RuntimeException(fMsg);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(XPathContext xctxt, int sourceNode, String msg, Object[] args) throws TransformerException {
/* 523 */     String fmsg = XPATHMessages.createXPATHMessage(msg, args);
/* 524 */     ErrorListener ehandler = xctxt.getErrorListener();
/*     */     
/* 526 */     if (null != ehandler) {
/*     */       
/* 528 */       ehandler.fatalError(new TransformerException(fmsg, xctxt.getSAXLocator()));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 533 */       SourceLocator slocator = xctxt.getSAXLocator();
/* 534 */       System.out.println(fmsg + "; file " + slocator.getSystemId() + "; line " + slocator.getLineNumber() + "; column " + slocator.getColumnNumber());
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
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 551 */     this.m_mainExp.callVisitors(this, visitor);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/XPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */