/*     */ package org.apache.xpath;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Expression
/*     */   implements Serializable, ExpressionNode, XPathVisitable
/*     */ {
/*     */   private ExpressionNode m_parent;
/*     */   
/*     */   public boolean canTraverseOutsideSubtree() {
/*  61 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject execute(XPathContext xctxt, int currentNode) throws TransformerException {
/*  94 */     return execute(xctxt);
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
/*     */   public XObject execute(XPathContext xctxt, int currentNode, DTM dtm, int expType) throws TransformerException {
/* 118 */     return execute(xctxt);
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
/*     */   public abstract XObject execute(XPathContext paramXPathContext) throws TransformerException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject execute(XPathContext xctxt, boolean destructiveOK) throws TransformerException {
/* 153 */     return execute(xctxt);
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
/*     */   public double num(XPathContext xctxt) throws TransformerException {
/* 169 */     return execute(xctxt).num();
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
/*     */   public boolean bool(XPathContext xctxt) throws TransformerException {
/* 184 */     return execute(xctxt).bool();
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
/*     */   public XMLString xstr(XPathContext xctxt) throws TransformerException {
/* 199 */     return execute(xctxt).xstr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNodesetExpr() {
/* 209 */     return false;
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
/*     */   public int asNode(XPathContext xctxt) throws TransformerException {
/* 223 */     DTMIterator iter = execute(xctxt).iter();
/* 224 */     return iter.nextNode();
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
/*     */   public DTMIterator asIterator(XPathContext xctxt, int contextNode) throws TransformerException {
/*     */     try {
/* 248 */       xctxt.pushCurrentNodeAndExpression(contextNode, contextNode);
/*     */       
/* 250 */       return execute(xctxt).iter();
/*     */     }
/*     */     finally {
/*     */       
/* 254 */       xctxt.popCurrentNodeAndExpression();
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
/*     */   public DTMIterator asIteratorRaw(XPathContext xctxt, int contextNode) throws TransformerException {
/*     */     try {
/* 279 */       xctxt.pushCurrentNodeAndExpression(contextNode, contextNode);
/*     */       
/* 281 */       XNodeSet nodeset = (XNodeSet)execute(xctxt);
/* 282 */       return nodeset.iterRaw();
/*     */     }
/*     */     finally {
/*     */       
/* 286 */       xctxt.popCurrentNodeAndExpression();
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
/*     */   
/*     */   public void executeCharsToContentHandler(XPathContext xctxt, ContentHandler handler) throws TransformerException, SAXException {
/* 311 */     XObject obj = execute(xctxt);
/*     */     
/* 313 */     obj.dispatchCharactersEvents(handler);
/* 314 */     obj.detach();
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
/*     */   public boolean isStableNumber() {
/* 327 */     return false;
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
/*     */   public abstract void fixupVariables(Vector paramVector, int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean deepEquals(Expression paramExpression);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean isSameClass(Expression expr) {
/* 365 */     if (null == expr) {
/* 366 */       return false;
/*     */     }
/* 368 */     return (getClass() == expr.getClass());
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
/*     */   public void warn(XPathContext xctxt, String msg, Object[] args) throws TransformerException {
/* 390 */     String fmsg = XPATHMessages.createXPATHWarning(msg, args);
/*     */     
/* 392 */     if (null != xctxt) {
/*     */       
/* 394 */       ErrorListener eh = xctxt.getErrorListener();
/*     */ 
/*     */       
/* 397 */       eh.warning(new TransformerException(fmsg, xctxt.getSAXLocator()));
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
/*     */   public void assertion(boolean b, String msg) {
/* 415 */     if (!b) {
/*     */       
/* 417 */       String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg });
/*     */ 
/*     */ 
/*     */       
/* 421 */       throw new RuntimeException(fMsg);
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
/*     */   public void error(XPathContext xctxt, String msg, Object[] args) throws TransformerException {
/* 445 */     String fmsg = XPATHMessages.createXPATHMessage(msg, args);
/*     */     
/* 447 */     if (null != xctxt) {
/*     */       
/* 449 */       ErrorListener eh = xctxt.getErrorListener();
/* 450 */       TransformerException te = new TransformerException(fmsg, this);
/*     */       
/* 452 */       eh.fatalError(te);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpressionNode getExpressionOwner() {
/* 462 */     ExpressionNode parent = exprGetParent();
/* 463 */     while (null != parent && parent instanceof Expression)
/* 464 */       parent = parent.exprGetParent(); 
/* 465 */     return parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exprSetParent(ExpressionNode n) {
/* 474 */     assertion((n != this), "Can not parent an expression to itself!");
/* 475 */     this.m_parent = n;
/*     */   }
/*     */ 
/*     */   
/*     */   public ExpressionNode exprGetParent() {
/* 480 */     return this.m_parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exprAddChild(ExpressionNode n, int i) {
/* 487 */     assertion(false, "exprAddChild method not implemented!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpressionNode exprGetChild(int i) {
/* 494 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int exprGetNumChildren() {
/* 500 */     return 0;
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
/*     */   public String getPublicId() {
/* 518 */     if (null == this.m_parent)
/* 519 */       return null; 
/* 520 */     return this.m_parent.getPublicId();
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
/*     */   public String getSystemId() {
/* 539 */     if (null == this.m_parent)
/* 540 */       return null; 
/* 541 */     return this.m_parent.getSystemId();
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
/*     */   public int getLineNumber() {
/* 561 */     if (null == this.m_parent)
/* 562 */       return 0; 
/* 563 */     return this.m_parent.getLineNumber();
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
/*     */   public int getColumnNumber() {
/* 583 */     if (null == this.m_parent)
/* 584 */       return 0; 
/* 585 */     return this.m_parent.getColumnNumber();
/*     */   }
/*     */   
/*     */   public abstract void callVisitors(ExpressionOwner paramExpressionOwner, XPathVisitor paramXPathVisitor);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/Expression.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */