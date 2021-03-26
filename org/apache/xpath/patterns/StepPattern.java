/*      */ package org.apache.xpath.patterns;
/*      */ 
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.dtm.Axis;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xpath.Expression;
/*      */ import org.apache.xpath.ExpressionNode;
/*      */ import org.apache.xpath.ExpressionOwner;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.XPathVisitor;
/*      */ import org.apache.xpath.axes.SubContextList;
/*      */ import org.apache.xpath.objects.XNumber;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StepPattern
/*      */   extends NodeTest
/*      */   implements ExpressionOwner, SubContextList
/*      */ {
/*      */   protected int m_axis;
/*      */   String m_targetString;
/*      */   StepPattern m_relativePathPattern;
/*      */   Expression[] m_predicates;
/*      */   private static final boolean DEBUG_MATCHES = false;
/*      */   
/*      */   public StepPattern(int whatToShow, String namespace, String name, int axis, int axisForPredicate) {
/*   57 */     super(whatToShow, namespace, name);
/*      */     
/*   59 */     this.m_axis = axis;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StepPattern(int whatToShow, int axis, int axisForPredicate) {
/*   73 */     super(whatToShow);
/*      */     
/*   75 */     this.m_axis = axis;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void calcTargetString() {
/*   93 */     int whatToShow = getWhatToShow();
/*      */     
/*   95 */     switch (whatToShow) {
/*      */       
/*      */       case 128:
/*   98 */         this.m_targetString = "#comment";
/*      */         return;
/*      */       case 4:
/*      */       case 8:
/*      */       case 12:
/*  103 */         this.m_targetString = "#text";
/*      */         return;
/*      */       case -1:
/*  106 */         this.m_targetString = "*";
/*      */         return;
/*      */       case 256:
/*      */       case 1280:
/*  110 */         this.m_targetString = "/";
/*      */         return;
/*      */       case 1:
/*  113 */         if ("*" == this.m_name) {
/*  114 */           this.m_targetString = "*";
/*      */         } else {
/*  116 */           this.m_targetString = this.m_name;
/*      */         }  return;
/*      */     } 
/*  119 */     this.m_targetString = "*";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTargetString() {
/*  134 */     return this.m_targetString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fixupVariables(Vector vars, int globalsSize) {
/*  158 */     super.fixupVariables(vars, globalsSize);
/*      */     
/*  160 */     if (null != this.m_predicates)
/*      */     {
/*  162 */       for (int i = 0; i < this.m_predicates.length; i++)
/*      */       {
/*  164 */         this.m_predicates[i].fixupVariables(vars, globalsSize);
/*      */       }
/*      */     }
/*      */     
/*  168 */     if (null != this.m_relativePathPattern)
/*      */     {
/*  170 */       this.m_relativePathPattern.fixupVariables(vars, globalsSize);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRelativePathPattern(StepPattern expr) {
/*  184 */     this.m_relativePathPattern = expr;
/*  185 */     expr.exprSetParent((ExpressionNode)this);
/*      */     
/*  187 */     calcScore();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StepPattern getRelativePathPattern() {
/*  199 */     return this.m_relativePathPattern;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Expression[] getPredicates() {
/*  217 */     return this.m_predicates;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canTraverseOutsideSubtree() {
/*  238 */     int n = getPredicateCount();
/*      */     
/*  240 */     for (int i = 0; i < n; i++) {
/*      */       
/*  242 */       if (getPredicate(i).canTraverseOutsideSubtree()) {
/*  243 */         return true;
/*      */       }
/*      */     } 
/*  246 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Expression getPredicate(int i) {
/*  259 */     return this.m_predicates[i];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getPredicateCount() {
/*  270 */     return (null == this.m_predicates) ? 0 : this.m_predicates.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPredicates(Expression[] predicates) {
/*  283 */     this.m_predicates = predicates;
/*  284 */     if (null != predicates)
/*      */     {
/*  286 */       for (int i = 0; i < predicates.length; i++)
/*      */       {
/*  288 */         predicates[i].exprSetParent((ExpressionNode)this);
/*      */       }
/*      */     }
/*      */     
/*  292 */     calcScore();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void calcScore() {
/*  301 */     if (getPredicateCount() > 0 || null != this.m_relativePathPattern) {
/*      */       
/*  303 */       this.m_score = NodeTest.SCORE_OTHER;
/*      */     } else {
/*      */       
/*  306 */       super.calcScore();
/*      */     } 
/*  308 */     if (null == this.m_targetString) {
/*  309 */       calcTargetString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XObject execute(XPathContext xctxt, int currentNode) throws TransformerException {
/*  331 */     DTM dtm = xctxt.getDTM(currentNode);
/*      */     
/*  333 */     if (dtm != null) {
/*      */       
/*  335 */       int expType = dtm.getExpandedTypeID(currentNode);
/*      */       
/*  337 */       return execute(xctxt, currentNode, dtm, expType);
/*      */     } 
/*      */     
/*  340 */     return (XObject)NodeTest.SCORE_NONE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  360 */     return execute(xctxt, xctxt.getCurrentNode());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XObject execute(XPathContext xctxt, int currentNode, DTM dtm, int expType) throws TransformerException {
/*  383 */     if (this.m_whatToShow == 65536) {
/*      */       
/*  385 */       if (null != this.m_relativePathPattern)
/*      */       {
/*  387 */         return this.m_relativePathPattern.execute(xctxt);
/*      */       }
/*      */       
/*  390 */       return (XObject)NodeTest.SCORE_NONE;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  395 */     XObject score = super.execute(xctxt, currentNode, dtm, expType);
/*      */     
/*  397 */     if (score == NodeTest.SCORE_NONE) {
/*  398 */       return (XObject)NodeTest.SCORE_NONE;
/*      */     }
/*  400 */     if (getPredicateCount() != 0)
/*      */     {
/*  402 */       if (!executePredicates(xctxt, dtm, currentNode)) {
/*  403 */         return (XObject)NodeTest.SCORE_NONE;
/*      */       }
/*      */     }
/*  406 */     if (null != this.m_relativePathPattern) {
/*  407 */       return this.m_relativePathPattern.executeRelativePathPattern(xctxt, dtm, currentNode);
/*      */     }
/*      */     
/*  410 */     return score;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean checkProximityPosition(XPathContext xctxt, int predPos, DTM dtm, int context, int pos) {
/*      */     try {
/*  431 */       DTMAxisTraverser traverser = dtm.getAxisTraverser(12);
/*      */ 
/*      */       
/*  434 */       for (int child = traverser.first(context); -1 != child; 
/*  435 */         child = traverser.next(context, child))
/*      */       {
/*      */         
/*      */         try {
/*  439 */           xctxt.pushCurrentNode(child);
/*      */           
/*  441 */           if (NodeTest.SCORE_NONE != super.execute(xctxt, child)) {
/*      */             
/*  443 */             boolean pass = true;
/*      */ 
/*      */             
/*      */             try {
/*  447 */               xctxt.pushSubContextList(this);
/*      */               
/*  449 */               for (int i = 0; i < predPos; i++) {
/*      */                 
/*  451 */                 xctxt.pushPredicatePos(i);
/*      */                 
/*      */                 try {
/*  454 */                   XObject pred = this.m_predicates[i].execute(xctxt);
/*      */ 
/*      */                   
/*      */                   try {
/*  458 */                     if (2 == pred.getType())
/*      */                     {
/*  460 */                       throw new Error("Why: Should never have been called");
/*      */                     }
/*  462 */                     if (!pred.boolWithSideEffects()) {
/*      */                       
/*  464 */                       pass = false;
/*      */ 
/*      */ 
/*      */                       
/*      */                       break;
/*      */                     } 
/*      */                   } finally {
/*  471 */                     pred.detach();
/*      */                   }
/*      */                 
/*      */                 } finally {
/*      */                   
/*  476 */                   xctxt.popPredicatePos();
/*      */                 }
/*      */               
/*      */               } 
/*      */             } finally {
/*      */               
/*  482 */               xctxt.popSubContextList();
/*      */             } 
/*      */             
/*  485 */             if (pass) {
/*  486 */               pos--;
/*      */             }
/*  488 */             if (pos < 1) {
/*  489 */               return false;
/*      */             }
/*      */           } 
/*      */         } finally {
/*      */           
/*  494 */           xctxt.popCurrentNode();
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     catch (TransformerException se) {
/*      */       
/*  502 */       throw new RuntimeException(se.getMessage());
/*      */     } 
/*      */     
/*  505 */     return (pos == 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int getProximityPosition(XPathContext xctxt, int predPos, boolean findLast) {
/*  524 */     int pos = 0;
/*  525 */     int context = xctxt.getCurrentNode();
/*  526 */     DTM dtm = xctxt.getDTM(context);
/*  527 */     int parent = dtm.getParent(context);
/*      */ 
/*      */     
/*      */     try {
/*  531 */       DTMAxisTraverser traverser = dtm.getAxisTraverser(3);
/*      */       
/*  533 */       for (int child = traverser.first(parent); -1 != child; 
/*  534 */         child = traverser.next(parent, child))
/*      */       {
/*      */         
/*      */         try {
/*  538 */           xctxt.pushCurrentNode(child);
/*      */           
/*  540 */           if (NodeTest.SCORE_NONE != super.execute(xctxt, child))
/*      */           {
/*  542 */             boolean pass = true;
/*      */ 
/*      */             
/*      */             try {
/*  546 */               xctxt.pushSubContextList(this);
/*      */               
/*  548 */               for (int i = 0; i < predPos; i++) {
/*      */                 
/*  550 */                 xctxt.pushPredicatePos(i);
/*      */                 
/*      */                 try {
/*  553 */                   XObject pred = this.m_predicates[i].execute(xctxt);
/*      */ 
/*      */                   
/*      */                   try {
/*  557 */                     if (2 == pred.getType()) {
/*      */                       
/*  559 */                       if (pos + 1 != (int)pred.numWithSideEffects()) {
/*      */                         
/*  561 */                         pass = false;
/*      */ 
/*      */                         
/*      */                         break;
/*      */                       } 
/*  566 */                     } else if (!pred.boolWithSideEffects()) {
/*      */                       
/*  568 */                       pass = false;
/*      */ 
/*      */ 
/*      */                       
/*      */                       break;
/*      */                     } 
/*      */                   } finally {
/*  575 */                     pred.detach();
/*      */                   }
/*      */                 
/*      */                 } finally {
/*      */                   
/*  580 */                   xctxt.popPredicatePos();
/*      */                 }
/*      */               
/*      */               } 
/*      */             } finally {
/*      */               
/*  586 */               xctxt.popSubContextList();
/*      */             } 
/*      */             
/*  589 */             if (pass) {
/*  590 */               pos++;
/*      */             }
/*  592 */             if (!findLast && child == context)
/*      */             {
/*  594 */               return pos;
/*      */             }
/*      */           }
/*      */         
/*      */         } finally {
/*      */           
/*  600 */           xctxt.popCurrentNode();
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     catch (TransformerException se) {
/*      */       
/*  608 */       throw new RuntimeException(se.getMessage());
/*      */     } 
/*      */     
/*  611 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getProximityPosition(XPathContext xctxt) {
/*  626 */     return getProximityPosition(xctxt, xctxt.getPredicatePos(), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLastPos(XPathContext xctxt) {
/*  642 */     return getProximityPosition(xctxt, xctxt.getPredicatePos(), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final XObject executeRelativePathPattern(XPathContext xctxt, DTM dtm, int currentNode) throws TransformerException {
/*      */     XObject xObject;
/*  666 */     XNumber xNumber = NodeTest.SCORE_NONE;
/*  667 */     int context = currentNode;
/*      */ 
/*      */     
/*  670 */     DTMAxisTraverser traverser = dtm.getAxisTraverser(this.m_axis);
/*      */     
/*  672 */     for (int relative = traverser.first(context); -1 != relative; 
/*  673 */       relative = traverser.next(context, relative)) {
/*      */ 
/*      */       
/*      */       try {
/*  677 */         xctxt.pushCurrentNode(relative);
/*      */         
/*  679 */         xObject = execute(xctxt);
/*      */         
/*  681 */         if (xObject != NodeTest.SCORE_NONE) {
/*      */           break;
/*      */         }
/*      */       } finally {
/*      */         
/*  686 */         xctxt.popCurrentNode();
/*      */       } 
/*      */     } 
/*      */     
/*  690 */     return xObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final boolean executePredicates(XPathContext xctxt, DTM dtm, int currentNode) throws TransformerException {
/*  710 */     boolean result = true;
/*  711 */     boolean positionAlreadySeen = false;
/*  712 */     int n = getPredicateCount();
/*      */ 
/*      */     
/*      */     try {
/*  716 */       xctxt.pushSubContextList(this);
/*      */       
/*  718 */       for (int i = 0; i < n; i++) {
/*      */         
/*  720 */         xctxt.pushPredicatePos(i);
/*      */ 
/*      */         
/*      */         try {
/*  724 */           XObject pred = this.m_predicates[i].execute(xctxt);
/*      */ 
/*      */           
/*      */           try {
/*  728 */             if (2 == pred.getType()) {
/*      */               
/*  730 */               int pos = (int)pred.num();
/*      */               
/*  732 */               if (positionAlreadySeen) {
/*      */                 
/*  734 */                 result = (pos == 1);
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*  740 */               positionAlreadySeen = true;
/*      */               
/*  742 */               if (!checkProximityPosition(xctxt, i, dtm, currentNode, pos)) {
/*      */                 
/*  744 */                 result = false;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 break;
/*      */               } 
/*  751 */             } else if (!pred.boolWithSideEffects()) {
/*      */               
/*  753 */               result = false;
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } finally {
/*  760 */             pred.detach();
/*      */           }
/*      */         
/*      */         } finally {
/*      */           
/*  765 */           xctxt.popPredicatePos();
/*      */         }
/*      */       
/*      */       } 
/*      */     } finally {
/*      */       
/*  771 */       xctxt.popSubContextList();
/*      */     } 
/*      */     
/*  774 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  787 */     StringBuffer buf = new StringBuffer();
/*      */     
/*  789 */     for (StepPattern pat = this; pat != null; pat = pat.m_relativePathPattern) {
/*      */       
/*  791 */       if (pat != this) {
/*  792 */         buf.append("/");
/*      */       }
/*  794 */       buf.append(Axis.names[pat.m_axis]);
/*  795 */       buf.append("::");
/*      */       
/*  797 */       if (20480 == pat.m_whatToShow) {
/*      */         
/*  799 */         buf.append("doc()");
/*      */       }
/*  801 */       else if (65536 == pat.m_whatToShow) {
/*      */         
/*  803 */         buf.append("function()");
/*      */       }
/*  805 */       else if (-1 == pat.m_whatToShow) {
/*      */         
/*  807 */         buf.append("node()");
/*      */       }
/*  809 */       else if (4 == pat.m_whatToShow) {
/*      */         
/*  811 */         buf.append("text()");
/*      */       }
/*  813 */       else if (64 == pat.m_whatToShow) {
/*      */         
/*  815 */         buf.append("processing-instruction(");
/*      */         
/*  817 */         if (null != pat.m_name)
/*      */         {
/*  819 */           buf.append(pat.m_name);
/*      */         }
/*      */         
/*  822 */         buf.append(")");
/*      */       }
/*  824 */       else if (128 == pat.m_whatToShow) {
/*      */         
/*  826 */         buf.append("comment()");
/*      */       }
/*  828 */       else if (null != pat.m_name) {
/*      */         
/*  830 */         if (2 == pat.m_whatToShow)
/*      */         {
/*  832 */           buf.append("@");
/*      */         }
/*      */         
/*  835 */         if (null != pat.m_namespace) {
/*      */           
/*  837 */           buf.append("{");
/*  838 */           buf.append(pat.m_namespace);
/*  839 */           buf.append("}");
/*      */         } 
/*      */         
/*  842 */         buf.append(pat.m_name);
/*      */       }
/*  844 */       else if (2 == pat.m_whatToShow) {
/*      */         
/*  846 */         buf.append("@");
/*      */       }
/*  848 */       else if (1280 == pat.m_whatToShow) {
/*      */ 
/*      */         
/*  851 */         buf.append("doc-root()");
/*      */       }
/*      */       else {
/*      */         
/*  855 */         buf.append("?" + Integer.toHexString(pat.m_whatToShow));
/*      */       } 
/*      */       
/*  858 */       if (null != pat.m_predicates)
/*      */       {
/*  860 */         for (int i = 0; i < pat.m_predicates.length; i++) {
/*      */           
/*  862 */           buf.append("[");
/*  863 */           buf.append(pat.m_predicates[i]);
/*  864 */           buf.append("]");
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  869 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMatchScore(XPathContext xctxt, int context) throws TransformerException {
/*  893 */     xctxt.pushCurrentNode(context);
/*  894 */     xctxt.pushCurrentExpressionNode(context);
/*      */ 
/*      */     
/*      */     try {
/*  898 */       XObject score = execute(xctxt);
/*      */       
/*  900 */       return score.num();
/*      */     }
/*      */     finally {
/*      */       
/*  904 */       xctxt.popCurrentNode();
/*  905 */       xctxt.popCurrentExpressionNode();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAxis(int axis) {
/*  919 */     this.m_axis = axis;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAxis() {
/*  930 */     return this.m_axis;
/*      */   }
/*      */   
/*      */   class PredOwner implements ExpressionOwner {
/*      */     int m_index;
/*      */     private final StepPattern this$0;
/*      */     
/*      */     PredOwner(StepPattern this$0, int index) {
/*  938 */       this.this$0 = this$0;
/*  939 */       this.m_index = index;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Expression getExpression() {
/*  947 */       return this.this$0.m_predicates[this.m_index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setExpression(Expression exp) {
/*  956 */       exp.exprSetParent((ExpressionNode)this.this$0);
/*  957 */       this.this$0.m_predicates[this.m_index] = exp;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/*  966 */     if (visitor.visitMatchPattern(owner, this))
/*      */     {
/*  968 */       callSubtreeVisitors(visitor);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void callSubtreeVisitors(XPathVisitor visitor) {
/*  978 */     if (null != this.m_predicates) {
/*      */       
/*  980 */       int n = this.m_predicates.length;
/*  981 */       for (int i = 0; i < n; i++) {
/*      */         
/*  983 */         ExpressionOwner predOwner = new PredOwner(this, i);
/*  984 */         if (visitor.visitPredicate(predOwner, this.m_predicates[i]))
/*      */         {
/*  986 */           this.m_predicates[i].callVisitors(predOwner, visitor);
/*      */         }
/*      */       } 
/*      */     } 
/*  990 */     if (null != this.m_relativePathPattern)
/*      */     {
/*  992 */       this.m_relativePathPattern.callVisitors(this, visitor);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Expression getExpression() {
/* 1002 */     return this.m_relativePathPattern;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExpression(Expression exp) {
/* 1010 */     exp.exprSetParent((ExpressionNode)this);
/* 1011 */     this.m_relativePathPattern = (StepPattern)exp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean deepEquals(Expression expr) {
/* 1019 */     if (!super.deepEquals(expr)) {
/* 1020 */       return false;
/*      */     }
/* 1022 */     StepPattern sp = (StepPattern)expr;
/*      */     
/* 1024 */     if (null != this.m_predicates) {
/*      */       
/* 1026 */       int n = this.m_predicates.length;
/* 1027 */       if (null == sp.m_predicates || sp.m_predicates.length != n)
/* 1028 */         return false; 
/* 1029 */       for (int i = 0; i < n; i++) {
/*      */         
/* 1031 */         if (!this.m_predicates[i].deepEquals(sp.m_predicates[i])) {
/* 1032 */           return false;
/*      */         }
/*      */       } 
/* 1035 */     } else if (null != sp.m_predicates) {
/* 1036 */       return false;
/*      */     } 
/* 1038 */     if (null != this.m_relativePathPattern) {
/*      */       
/* 1040 */       if (!this.m_relativePathPattern.deepEquals(sp.m_relativePathPattern)) {
/* 1041 */         return false;
/*      */       }
/* 1043 */     } else if (sp.m_relativePathPattern != null) {
/* 1044 */       return false;
/*      */     } 
/* 1046 */     return true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/patterns/StepPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */