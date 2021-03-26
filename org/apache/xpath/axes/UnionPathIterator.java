/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ import org.apache.xpath.compiler.OpMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnionPathIterator
/*     */   extends LocPathIterator
/*     */   implements Serializable, Cloneable, DTMIterator, PathComponent
/*     */ {
/*     */   protected LocPathIterator[] m_exprs;
/*     */   protected DTMIterator[] m_iterators;
/*     */   
/*     */   public UnionPathIterator() {
/*  52 */     this.m_iterators = null;
/*  53 */     this.m_exprs = null;
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
/*     */   public void setRoot(int context, Object environment) {
/*  65 */     super.setRoot(context, environment);
/*     */ 
/*     */ 
/*     */     
/*  69 */     try { if (null != this.m_exprs)
/*     */       
/*  71 */       { int n = this.m_exprs.length;
/*  72 */         DTMIterator[] newIters = new DTMIterator[n];
/*     */         
/*  74 */         for (int i = 0; i < n; i++) {
/*     */           
/*  76 */           DTMIterator iter = this.m_exprs[i].asIterator(this.m_execContext, context);
/*  77 */           newIters[i] = iter;
/*  78 */           iter.nextNode();
/*     */         } 
/*  80 */         this.m_iterators = newIters; }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/*  85 */       throw new WrappedRuntimeException(e); }
/*     */   
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
/*     */   public void addIterator(DTMIterator expr) {
/*  99 */     if (null == this.m_iterators) {
/*     */       
/* 101 */       this.m_iterators = new DTMIterator[1];
/* 102 */       this.m_iterators[0] = expr;
/*     */     }
/*     */     else {
/*     */       
/* 106 */       DTMIterator[] exprs = this.m_iterators;
/* 107 */       int len = this.m_iterators.length;
/*     */       
/* 109 */       this.m_iterators = new DTMIterator[len + 1];
/*     */       
/* 111 */       System.arraycopy(exprs, 0, this.m_iterators, 0, len);
/*     */       
/* 113 */       this.m_iterators[len] = expr;
/*     */     } 
/* 115 */     expr.nextNode();
/* 116 */     if (expr instanceof Expression) {
/* 117 */       ((Expression)expr).exprSetParent((ExpressionNode)this);
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
/*     */   public void detach() {
/* 129 */     if (this.m_allowDetach && null != this.m_iterators) {
/* 130 */       int n = this.m_iterators.length;
/* 131 */       for (int i = 0; i < n; i++)
/*     */       {
/* 133 */         this.m_iterators[i].detach();
/*     */       }
/* 135 */       this.m_iterators = null;
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
/*     */   public UnionPathIterator(Compiler compiler, int opPos) throws TransformerException {
/* 158 */     opPos = OpMap.getFirstChildPos(opPos);
/*     */     
/* 160 */     loadLocationPaths(compiler, opPos, 0);
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
/*     */   public static LocPathIterator createUnionIterator(Compiler compiler, int opPos) throws TransformerException {
/* 182 */     UnionPathIterator upi = new UnionPathIterator(compiler, opPos);
/* 183 */     int nPaths = upi.m_exprs.length;
/* 184 */     boolean isAllChildIterators = true;
/* 185 */     for (int i = 0; i < nPaths; i++) {
/*     */       
/* 187 */       LocPathIterator lpi = upi.m_exprs[i];
/*     */       
/* 189 */       if (lpi.getAxis() != 3) {
/*     */         
/* 191 */         isAllChildIterators = false;
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 197 */       if (HasPositionalPredChecker.check(lpi)) {
/*     */         
/* 199 */         isAllChildIterators = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 204 */     if (isAllChildIterators) {
/*     */       
/* 206 */       UnionChildIterator uci = new UnionChildIterator();
/*     */       
/* 208 */       for (int j = 0; j < nPaths; j++) {
/*     */         
/* 210 */         PredicatedNodeTest lpi = upi.m_exprs[j];
/*     */ 
/*     */ 
/*     */         
/* 214 */         uci.addNodeTest(lpi);
/*     */       } 
/* 216 */       return uci;
/*     */     } 
/*     */ 
/*     */     
/* 220 */     return upi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/* 229 */     int bits = 0;
/*     */     
/* 231 */     if (this.m_exprs != null) {
/*     */       
/* 233 */       int n = this.m_exprs.length;
/*     */       
/* 235 */       for (int i = 0; i < n; i++) {
/*     */         
/* 237 */         int bit = this.m_exprs[i].getAnalysisBits();
/* 238 */         bits |= bit;
/*     */       } 
/*     */     } 
/*     */     
/* 242 */     return bits;
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
/*     */   private void readObject(ObjectInputStream stream) throws IOException, TransformerException {
/*     */     
/* 258 */     try { stream.defaultReadObject();
/* 259 */       this.m_clones = new IteratorPool(this); } catch (ClassNotFoundException cnfe)
/*     */     
/*     */     { 
/*     */       
/* 263 */       throw new TransformerException(cnfe); }
/*     */   
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
/*     */   public Object clone() throws CloneNotSupportedException {
/* 278 */     UnionPathIterator clone = (UnionPathIterator)super.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     return clone;
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
/*     */   protected LocPathIterator createDTMIterator(Compiler compiler, int opPos) throws TransformerException {
/* 309 */     LocPathIterator lpi = (LocPathIterator)WalkerFactory.newDTMIterator(compiler, opPos, (compiler.getLocationPathDepth() <= 0));
/*     */     
/* 311 */     return lpi;
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
/*     */   protected void loadLocationPaths(Compiler compiler, int opPos, int count) throws TransformerException {
/* 330 */     int steptype = compiler.getOp(opPos);
/*     */     
/* 332 */     if (steptype == 28) {
/*     */       
/* 334 */       loadLocationPaths(compiler, compiler.getNextOpPos(opPos), count + 1);
/*     */       
/* 336 */       this.m_exprs[count] = createDTMIterator(compiler, opPos);
/* 337 */       this.m_exprs[count].exprSetParent((ExpressionNode)this);
/*     */     } else {
/*     */       WalkingIterator iter;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 344 */       switch (steptype) {
/*     */         
/*     */         case 22:
/*     */         case 23:
/*     */         case 24:
/*     */         case 25:
/* 350 */           loadLocationPaths(compiler, compiler.getNextOpPos(opPos), count + 1);
/*     */           
/* 352 */           iter = new WalkingIterator(compiler.getNamespaceContext());
/*     */           
/* 354 */           iter.exprSetParent((ExpressionNode)this);
/*     */           
/* 356 */           if (compiler.getLocationPathDepth() <= 0) {
/* 357 */             iter.setIsTopLevel(true);
/*     */           }
/* 359 */           iter.m_firstWalker = new FilterExprWalker(iter);
/*     */           
/* 361 */           iter.m_firstWalker.init(compiler, opPos, steptype);
/*     */           
/* 363 */           this.m_exprs[count] = iter;
/*     */           return;
/*     */       } 
/* 366 */       this.m_exprs = new LocPathIterator[count];
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
/*     */   public int nextNode() {
/* 380 */     if (this.m_foundLast) {
/* 381 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 385 */     int earliestNode = -1;
/*     */     
/* 387 */     if (null != this.m_iterators) {
/*     */       
/* 389 */       int n = this.m_iterators.length;
/* 390 */       int iteratorUsed = -1;
/*     */       
/* 392 */       for (int i = 0; i < n; i++) {
/*     */         
/* 394 */         int node = this.m_iterators[i].getCurrentNode();
/*     */         
/* 396 */         if (-1 != node)
/*     */         {
/* 398 */           if (-1 == earliestNode) {
/*     */             
/* 400 */             iteratorUsed = i;
/* 401 */             earliestNode = node;
/*     */ 
/*     */           
/*     */           }
/* 405 */           else if (node == earliestNode) {
/*     */ 
/*     */ 
/*     */             
/* 409 */             this.m_iterators[i].nextNode();
/*     */           }
/*     */           else {
/*     */             
/* 413 */             DTM dtm = getDTM(node);
/*     */             
/* 415 */             if (dtm.isNodeAfter(node, earliestNode)) {
/*     */               
/* 417 */               iteratorUsed = i;
/* 418 */               earliestNode = node;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 424 */       if (-1 != earliestNode) {
/*     */         
/* 426 */         this.m_iterators[iteratorUsed].nextNode();
/*     */         
/* 428 */         incrementCurrentPos();
/*     */       } else {
/*     */         
/* 431 */         this.m_foundLast = true;
/*     */       } 
/*     */     } 
/* 434 */     this.m_lastFetched = earliestNode;
/*     */     
/* 436 */     return earliestNode;
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
/* 451 */     for (int i = 0; i < this.m_exprs.length; i++)
/*     */     {
/* 453 */       this.m_exprs[i].fixupVariables(vars, globalsSize);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/* 484 */     return -1;
/*     */   }
/*     */   
/*     */   class iterOwner implements ExpressionOwner {
/*     */     int m_index;
/*     */     private final UnionPathIterator this$0;
/*     */     
/*     */     iterOwner(UnionPathIterator this$0, int index) {
/* 492 */       this.this$0 = this$0;
/* 493 */       this.m_index = index;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Expression getExpression() {
/* 501 */       return (Expression)this.this$0.m_exprs[this.m_index];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/*     */       WalkingIterator walkingIterator;
/* 510 */       if (!(exp instanceof LocPathIterator)) {
/*     */ 
/*     */ 
/*     */         
/* 514 */         WalkingIterator wi = new WalkingIterator(this.this$0.getPrefixResolver());
/* 515 */         FilterExprWalker few = new FilterExprWalker(wi);
/* 516 */         wi.setFirstWalker(few);
/* 517 */         few.setInnerExpression(exp);
/* 518 */         wi.exprSetParent((ExpressionNode)this.this$0);
/* 519 */         few.exprSetParent((ExpressionNode)wi);
/* 520 */         exp.exprSetParent((ExpressionNode)few);
/* 521 */         walkingIterator = wi;
/*     */       } else {
/*     */         
/* 524 */         walkingIterator.exprSetParent((ExpressionNode)this.this$0);
/* 525 */       }  this.this$0.m_exprs[this.m_index] = walkingIterator;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 535 */     if (visitor.visitUnionPath(owner, this))
/*     */     {
/* 537 */       if (null != this.m_exprs) {
/*     */         
/* 539 */         int n = this.m_exprs.length;
/* 540 */         for (int i = 0; i < n; i++)
/*     */         {
/* 542 */           this.m_exprs[i].callVisitors(new iterOwner(this, i), visitor);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 553 */     if (!super.deepEquals(expr)) {
/* 554 */       return false;
/*     */     }
/* 556 */     UnionPathIterator upi = (UnionPathIterator)expr;
/*     */     
/* 558 */     if (null != this.m_exprs) {
/*     */       
/* 560 */       int n = this.m_exprs.length;
/*     */       
/* 562 */       if (null == upi.m_exprs || upi.m_exprs.length != n) {
/* 563 */         return false;
/*     */       }
/* 565 */       for (int i = 0; i < n; i++) {
/*     */         
/* 567 */         if (!this.m_exprs[i].deepEquals((Expression)upi.m_exprs[i])) {
/* 568 */           return false;
/*     */         }
/*     */       } 
/* 571 */     } else if (null != upi.m_exprs) {
/*     */       
/* 573 */       return false;
/*     */     } 
/*     */     
/* 576 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/UnionPathIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */