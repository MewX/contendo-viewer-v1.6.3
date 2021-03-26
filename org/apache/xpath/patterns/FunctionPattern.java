/*     */ package org.apache.xpath.patterns;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.objects.XNumber;
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
/*     */ public class FunctionPattern
/*     */   extends StepPattern
/*     */ {
/*     */   Expression m_functionExpr;
/*     */   
/*     */   public FunctionPattern(Expression expr, int axis, int predaxis) {
/*  49 */     super(0, null, null, axis, predaxis);
/*     */     
/*  51 */     this.m_functionExpr = expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void calcScore() {
/*  60 */     this.m_score = NodeTest.SCORE_OTHER;
/*     */     
/*  62 */     if (null == this.m_targetString) {
/*  63 */       calcTargetString();
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
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/*  84 */     super.fixupVariables(vars, globalsSize);
/*  85 */     this.m_functionExpr.fixupVariables(vars, globalsSize);
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
/*     */   public XObject execute(XPathContext xctxt, int context) throws TransformerException {
/* 106 */     DTMIterator nl = this.m_functionExpr.asIterator(xctxt, context);
/* 107 */     XNumber score = NodeTest.SCORE_NONE;
/*     */     
/* 109 */     if (null != nl) {
/*     */       int n;
/*     */ 
/*     */       
/* 113 */       while (-1 != (n = nl.nextNode())) {
/*     */         
/* 115 */         score = (n == context) ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE;
/*     */         
/* 117 */         if (score == NodeTest.SCORE_OTHER) {
/*     */           
/* 119 */           context = n;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     nl.detach();
/*     */     
/* 129 */     return (XObject)score;
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
/*     */   public XObject execute(XPathContext xctxt, int context, DTM dtm, int expType) throws TransformerException {
/* 150 */     DTMIterator nl = this.m_functionExpr.asIterator(xctxt, context);
/* 151 */     XNumber score = NodeTest.SCORE_NONE;
/*     */     
/* 153 */     if (null != nl) {
/*     */       int n;
/*     */ 
/*     */       
/* 157 */       while (-1 != (n = nl.nextNode())) {
/*     */         
/* 159 */         score = (n == context) ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE;
/*     */         
/* 161 */         if (score == NodeTest.SCORE_OTHER) {
/*     */           
/* 163 */           context = n;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 169 */       nl.detach();
/*     */     } 
/*     */     
/* 172 */     return (XObject)score;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 192 */     int context = xctxt.getCurrentNode();
/* 193 */     DTMIterator nl = this.m_functionExpr.asIterator(xctxt, context);
/* 194 */     XNumber score = NodeTest.SCORE_NONE;
/*     */     
/* 196 */     if (null != nl) {
/*     */       int n;
/*     */ 
/*     */       
/* 200 */       while (-1 != (n = nl.nextNode())) {
/*     */         
/* 202 */         score = (n == context) ? NodeTest.SCORE_OTHER : NodeTest.SCORE_NONE;
/*     */         
/* 204 */         if (score == NodeTest.SCORE_OTHER) {
/*     */           
/* 206 */           context = n;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 212 */       nl.detach();
/*     */     } 
/*     */     
/* 215 */     return (XObject)score;
/*     */   }
/*     */   class FunctionOwner implements ExpressionOwner { FunctionOwner(FunctionPattern this$0) {
/* 218 */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */     
/*     */     private final FunctionPattern this$0;
/*     */     
/*     */     public Expression getExpression() {
/* 225 */       return this.this$0.m_functionExpr;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 234 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 235 */       this.this$0.m_functionExpr = exp;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callSubtreeVisitors(XPathVisitor visitor) {
/* 244 */     this.m_functionExpr.callVisitors(new FunctionOwner(this), visitor);
/* 245 */     super.callSubtreeVisitors(visitor);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/patterns/FunctionPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */