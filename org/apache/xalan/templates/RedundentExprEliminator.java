/*      */ package org.apache.xalan.templates;
/*      */ 
/*      */ import java.util.Vector;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.apache.xpath.Expression;
/*      */ import org.apache.xpath.ExpressionNode;
/*      */ import org.apache.xpath.ExpressionOwner;
/*      */ import org.apache.xpath.XPath;
/*      */ import org.apache.xpath.axes.AxesWalker;
/*      */ import org.apache.xpath.axes.FilterExprIteratorSimple;
/*      */ import org.apache.xpath.axes.FilterExprWalker;
/*      */ import org.apache.xpath.axes.LocPathIterator;
/*      */ import org.apache.xpath.axes.WalkerFactory;
/*      */ import org.apache.xpath.axes.WalkingIterator;
/*      */ import org.apache.xpath.operations.Variable;
/*      */ import org.apache.xpath.operations.VariableSafeAbsRef;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Node;
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
/*      */ public class RedundentExprEliminator
/*      */   extends XSLTVisitor
/*      */ {
/*      */   Vector m_paths;
/*      */   Vector m_absPaths;
/*      */   boolean m_isSameContext;
/*   52 */   AbsPathChecker m_absPathChecker = new AbsPathChecker();
/*      */   
/*   54 */   static int m_uniquePsuedoVarID = 1;
/*      */   
/*      */   static final String PSUEDOVARNAMESPACE = "http://xml.apache.org/xalan/psuedovar";
/*      */   
/*      */   public static boolean DEBUG = false;
/*      */   
/*      */   public static boolean DIAGNOSE_NUM_PATHS_REDUCED = false;
/*      */   
/*      */   public static boolean DIAGNOSE_MULTISTEPLIST = false;
/*      */   
/*   64 */   VarNameCollector m_varNameCollector = new VarNameCollector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RedundentExprEliminator() {
/*   75 */     this.m_isSameContext = true;
/*   76 */     this.m_absPaths = new Vector();
/*   77 */     this.m_paths = null;
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
/*      */   public void eleminateRedundentLocals(ElemTemplateElement psuedoVarRecipient) {
/*   94 */     eleminateRedundent(psuedoVarRecipient, this.m_paths);
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
/*      */   public void eleminateRedundentGlobals(StylesheetRoot stylesheet) {
/*  110 */     eleminateRedundent(stylesheet, this.m_absPaths);
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
/*      */   protected void eleminateRedundent(ElemTemplateElement psuedoVarRecipient, Vector paths) {
/*  128 */     int n = paths.size();
/*  129 */     int numPathsEliminated = 0;
/*  130 */     int numUniquePathsEliminated = 0;
/*  131 */     for (int i = 0; i < n; i++) {
/*      */       
/*  133 */       ExpressionOwner owner = paths.elementAt(i);
/*  134 */       if (null != owner) {
/*      */         
/*  136 */         int found = findAndEliminateRedundant(i + 1, i, owner, psuedoVarRecipient, paths);
/*  137 */         if (found > 0)
/*  138 */           numUniquePathsEliminated++; 
/*  139 */         numPathsEliminated += found;
/*      */       } 
/*      */     } 
/*      */     
/*  143 */     eleminateSharedPartialPaths(psuedoVarRecipient, paths);
/*      */     
/*  145 */     if (DIAGNOSE_NUM_PATHS_REDUCED) {
/*  146 */       diagnoseNumPaths(paths, numPathsEliminated, numUniquePathsEliminated);
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
/*      */   protected void eleminateSharedPartialPaths(ElemTemplateElement psuedoVarRecipient, Vector paths) {
/*  159 */     MultistepExprHolder list = createMultistepExprList(paths);
/*  160 */     if (null != list) {
/*      */       
/*  162 */       if (DIAGNOSE_MULTISTEPLIST) {
/*  163 */         list.diagnose();
/*      */       }
/*  165 */       boolean isGlobal = (paths == this.m_absPaths);
/*      */ 
/*      */ 
/*      */       
/*  169 */       int longestStepsCount = list.m_stepCount;
/*  170 */       for (int i = longestStepsCount - 1; i >= 1; i--) {
/*      */         
/*  172 */         MultistepExprHolder next = list;
/*  173 */         while (null != next) {
/*      */           
/*  175 */           if (next.m_stepCount < i)
/*      */             break; 
/*  177 */           list = matchAndEliminatePartialPaths(next, list, isGlobal, i, psuedoVarRecipient);
/*  178 */           next = next.m_next;
/*      */         } 
/*      */       } 
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
/*      */   protected MultistepExprHolder matchAndEliminatePartialPaths(MultistepExprHolder testee, MultistepExprHolder head, boolean isGlobal, int lengthToTest, ElemTemplateElement varScope) {
/*  197 */     if (null == testee.m_exprOwner) {
/*  198 */       return head;
/*      */     }
/*      */     
/*  201 */     WalkingIterator iter1 = (WalkingIterator)testee.m_exprOwner.getExpression();
/*  202 */     if (partialIsVariable(testee, lengthToTest))
/*  203 */       return head; 
/*  204 */     MultistepExprHolder matchedPaths = null;
/*  205 */     MultistepExprHolder matchedPathsTail = null;
/*  206 */     MultistepExprHolder meh = head;
/*  207 */     while (null != meh) {
/*      */       
/*  209 */       if (meh != testee && null != meh.m_exprOwner) {
/*      */         
/*  211 */         WalkingIterator iter2 = (WalkingIterator)meh.m_exprOwner.getExpression();
/*  212 */         if (stepsEqual(iter1, iter2, lengthToTest)) {
/*      */           
/*  214 */           if (null == matchedPaths) {
/*      */ 
/*      */ 
/*      */             
/*  218 */             try { matchedPaths = (MultistepExprHolder)testee.clone();
/*  219 */               testee.m_exprOwner = null; } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*      */ 
/*      */             
/*  222 */             matchedPathsTail = matchedPaths;
/*  223 */             matchedPathsTail.m_next = null;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  228 */           try { matchedPathsTail.m_next = (MultistepExprHolder)meh.clone();
/*  229 */             meh.m_exprOwner = null; } catch (CloneNotSupportedException cloneNotSupportedException) {}
/*      */ 
/*      */           
/*  232 */           matchedPathsTail = matchedPathsTail.m_next;
/*  233 */           matchedPathsTail.m_next = null;
/*      */         } 
/*      */       } 
/*  236 */       meh = meh.m_next;
/*      */     } 
/*      */     
/*  239 */     int matchCount = 0;
/*  240 */     if (null != matchedPaths) {
/*      */       
/*  242 */       ElemTemplateElement root = isGlobal ? varScope : findCommonAncestor(matchedPaths);
/*  243 */       WalkingIterator sharedIter = (WalkingIterator)matchedPaths.m_exprOwner.getExpression();
/*  244 */       WalkingIterator newIter = createIteratorFromSteps(sharedIter, lengthToTest);
/*  245 */       ElemVariable var = createPsuedoVarDecl(root, (LocPathIterator)newIter, isGlobal);
/*  246 */       if (DIAGNOSE_MULTISTEPLIST)
/*  247 */         System.err.println("Created var: " + var.getName() + (isGlobal ? "(Global)" : "")); 
/*  248 */       while (null != matchedPaths) {
/*      */         
/*  250 */         ExpressionOwner owner = matchedPaths.m_exprOwner;
/*  251 */         WalkingIterator iter = (WalkingIterator)owner.getExpression();
/*      */         
/*  253 */         if (DIAGNOSE_MULTISTEPLIST) {
/*  254 */           diagnoseLineNumber((Expression)iter);
/*      */         }
/*  256 */         LocPathIterator newIter2 = changePartToRef(var.getName(), iter, lengthToTest, isGlobal);
/*      */         
/*  258 */         owner.setExpression((Expression)newIter2);
/*      */         
/*  260 */         matchedPaths = matchedPaths.m_next;
/*      */       } 
/*      */     } 
/*      */     
/*  264 */     if (DIAGNOSE_MULTISTEPLIST)
/*  265 */       diagnoseMultistepList(matchCount, lengthToTest, isGlobal); 
/*  266 */     return head;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean partialIsVariable(MultistepExprHolder testee, int lengthToTest) {
/*  275 */     if (1 == lengthToTest) {
/*      */       
/*  277 */       WalkingIterator wi = (WalkingIterator)testee.m_exprOwner.getExpression();
/*  278 */       if (wi.getFirstWalker() instanceof FilterExprWalker)
/*  279 */         return true; 
/*      */     } 
/*  281 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void diagnoseLineNumber(Expression expr) {
/*  289 */     ElemTemplateElement e = getElemFromExpression(expr);
/*  290 */     System.err.println("   " + e.getSystemId() + " Line " + e.getLineNumber());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ElemTemplateElement findCommonAncestor(MultistepExprHolder head) {
/*  300 */     int numExprs = head.getLength();
/*      */ 
/*      */ 
/*      */     
/*  304 */     ElemTemplateElement[] elems = new ElemTemplateElement[numExprs];
/*  305 */     int[] ancestorCounts = new int[numExprs];
/*      */ 
/*      */ 
/*      */     
/*  309 */     MultistepExprHolder next = head;
/*  310 */     int shortestAncestorCount = 10000;
/*  311 */     for (int i = 0; i < numExprs; i++) {
/*      */       
/*  313 */       ElemTemplateElement elem = getElemFromExpression(next.m_exprOwner.getExpression());
/*      */       
/*  315 */       elems[i] = elem;
/*  316 */       int numAncestors = countAncestors(elem);
/*  317 */       ancestorCounts[i] = numAncestors;
/*  318 */       if (numAncestors < shortestAncestorCount)
/*      */       {
/*  320 */         shortestAncestorCount = numAncestors;
/*      */       }
/*  322 */       next = next.m_next;
/*      */     } 
/*      */ 
/*      */     
/*  326 */     for (int j = 0; j < numExprs; j++) {
/*      */       
/*  328 */       if (ancestorCounts[j] > shortestAncestorCount) {
/*      */         
/*  330 */         int numStepCorrection = ancestorCounts[j] - shortestAncestorCount;
/*  331 */         for (int k = 0; k < numStepCorrection; k++)
/*      */         {
/*  333 */           elems[j] = elems[j].getParentElem();
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  340 */     ElemTemplateElement first = null;
/*  341 */     while (shortestAncestorCount-- >= 0) {
/*      */       
/*  343 */       boolean areEqual = true;
/*  344 */       first = elems[0];
/*  345 */       for (int k = 1; k < numExprs; k++) {
/*      */         
/*  347 */         if (first != elems[k]) {
/*      */           
/*  349 */           areEqual = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  355 */       if (areEqual && isNotSameAsOwner(head, first) && first.canAcceptVariables()) {
/*      */         
/*  357 */         if (DIAGNOSE_MULTISTEPLIST) {
/*      */           
/*  359 */           System.err.print(first.getClass().getName());
/*  360 */           System.err.println(" at   " + first.getSystemId() + " Line " + first.getLineNumber());
/*      */         } 
/*  362 */         return first;
/*      */       } 
/*      */       
/*  365 */       for (int m = 0; m < numExprs; m++)
/*      */       {
/*  367 */         elems[m] = elems[m].getParentElem();
/*      */       }
/*      */     } 
/*      */     
/*  371 */     assertion(false, "Could not find common ancestor!!!");
/*  372 */     return null;
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
/*      */   protected boolean isNotSameAsOwner(MultistepExprHolder head, ElemTemplateElement ete) {
/*  389 */     MultistepExprHolder next = head;
/*  390 */     while (null != next) {
/*      */       
/*  392 */       ElemTemplateElement elemOwner = getElemFromExpression(next.m_exprOwner.getExpression());
/*  393 */       if (elemOwner == ete)
/*  394 */         return false; 
/*  395 */       next = next.m_next;
/*      */     } 
/*  397 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int countAncestors(ElemTemplateElement elem) {
/*  408 */     int count = 0;
/*  409 */     while (null != elem) {
/*      */       
/*  411 */       count++;
/*  412 */       elem = elem.getParentElem();
/*      */     } 
/*  414 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void diagnoseMultistepList(int matchCount, int lengthToTest, boolean isGlobal) {
/*  425 */     if (matchCount > 0) {
/*      */       
/*  427 */       System.err.print("Found multistep matches: " + matchCount + ", " + lengthToTest + " length");
/*      */       
/*  429 */       if (isGlobal) {
/*  430 */         System.err.println(" (global)");
/*      */       } else {
/*  432 */         System.err.println();
/*      */       } 
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
/*      */   protected LocPathIterator changePartToRef(QName uniquePsuedoVarName, WalkingIterator wi, int numSteps, boolean isGlobal) {
/*  447 */     Variable var = new Variable();
/*  448 */     var.setQName(uniquePsuedoVarName);
/*  449 */     var.setIsGlobal(isGlobal);
/*  450 */     if (isGlobal) {
/*  451 */       ElemTemplateElement elem = getElemFromExpression((Expression)wi);
/*  452 */       StylesheetRoot root = elem.getStylesheetRoot();
/*  453 */       Vector vars = root.getVariablesAndParamsComposed();
/*  454 */       var.setIndex(vars.size() - 1);
/*      */     } 
/*      */ 
/*      */     
/*  458 */     AxesWalker walker = wi.getFirstWalker();
/*  459 */     for (int i = 0; i < numSteps; i++) {
/*      */       
/*  461 */       assertion((null != walker), "Walker should not be null!");
/*  462 */       walker = walker.getNextWalker();
/*      */     } 
/*      */     
/*  465 */     if (null != walker) {
/*      */ 
/*      */       
/*  468 */       FilterExprWalker few = new FilterExprWalker(wi);
/*  469 */       few.setInnerExpression((Expression)var);
/*  470 */       few.exprSetParent((ExpressionNode)wi);
/*  471 */       few.setNextWalker(walker);
/*  472 */       walker.setPrevWalker((AxesWalker)few);
/*  473 */       wi.setFirstWalker((AxesWalker)few);
/*  474 */       return (LocPathIterator)wi;
/*      */     } 
/*      */ 
/*      */     
/*  478 */     FilterExprIteratorSimple feis = new FilterExprIteratorSimple((Expression)var);
/*  479 */     feis.exprSetParent(wi.exprGetParent());
/*  480 */     return (LocPathIterator)feis;
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
/*      */   protected WalkingIterator createIteratorFromSteps(WalkingIterator wi, int numSteps) {
/*  494 */     WalkingIterator newIter = new WalkingIterator(wi.getPrefixResolver());
/*      */ 
/*      */     
/*  497 */     try { AxesWalker walker = (AxesWalker)wi.getFirstWalker().clone();
/*  498 */       newIter.setFirstWalker(walker);
/*  499 */       walker.setLocPathIterator((LocPathIterator)newIter);
/*  500 */       for (int i = 1; i < numSteps; i++) {
/*      */         
/*  502 */         AxesWalker next = (AxesWalker)walker.getNextWalker().clone();
/*  503 */         walker.setNextWalker(next);
/*  504 */         next.setLocPathIterator((LocPathIterator)newIter);
/*  505 */         walker = next;
/*      */       } 
/*  507 */       walker.setNextWalker(null); } catch (CloneNotSupportedException cnse)
/*      */     
/*      */     { 
/*      */       
/*  511 */       throw new WrappedRuntimeException(cnse); }
/*      */     
/*  513 */     return newIter;
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
/*      */   protected boolean stepsEqual(WalkingIterator iter1, WalkingIterator iter2, int numSteps) {
/*  528 */     AxesWalker aw1 = iter1.getFirstWalker();
/*  529 */     AxesWalker aw2 = iter2.getFirstWalker();
/*      */     
/*  531 */     for (int i = 0; i < numSteps; i++) {
/*      */       
/*  533 */       if (null == aw1 || null == aw2) {
/*  534 */         return false;
/*      */       }
/*  536 */       if (!aw1.deepEquals((Expression)aw2)) {
/*  537 */         return false;
/*      */       }
/*  539 */       aw1 = aw1.getNextWalker();
/*  540 */       aw2 = aw2.getNextWalker();
/*      */     } 
/*      */     
/*  543 */     assertion((null != aw1 || null != aw2), "Total match is incorrect!");
/*      */     
/*  545 */     return true;
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
/*      */   protected MultistepExprHolder createMultistepExprList(Vector paths) {
/*  561 */     MultistepExprHolder first = null;
/*  562 */     int n = paths.size();
/*  563 */     for (int i = 0; i < n; i++) {
/*      */       
/*  565 */       ExpressionOwner eo = paths.elementAt(i);
/*  566 */       if (null != eo) {
/*      */ 
/*      */ 
/*      */         
/*  570 */         LocPathIterator lpi = (LocPathIterator)eo.getExpression();
/*  571 */         int numPaths = countSteps(lpi);
/*  572 */         if (numPaths > 1)
/*      */         {
/*  574 */           if (null == first) {
/*  575 */             first = new MultistepExprHolder(this, eo, numPaths, null);
/*      */           } else {
/*  577 */             first = first.addInSortedOrder(eo, numPaths);
/*      */           }  } 
/*      */       } 
/*      */     } 
/*  581 */     if (null == first || first.getLength() <= 1) {
/*  582 */       return null;
/*      */     }
/*  584 */     return first;
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
/*      */   protected int findAndEliminateRedundant(int start, int firstOccuranceIndex, ExpressionOwner firstOccuranceOwner, ElemTemplateElement psuedoVarRecipient, Vector paths) throws DOMException {
/*  607 */     MultistepExprHolder head = null;
/*  608 */     MultistepExprHolder tail = null;
/*  609 */     int numPathsFound = 0;
/*  610 */     int n = paths.size();
/*      */     
/*  612 */     Expression expr1 = firstOccuranceOwner.getExpression();
/*  613 */     if (DEBUG)
/*  614 */       assertIsLocPathIterator(expr1, firstOccuranceOwner); 
/*  615 */     boolean isGlobal = (paths == this.m_absPaths);
/*  616 */     LocPathIterator lpi = (LocPathIterator)expr1;
/*  617 */     int stepCount = countSteps(lpi);
/*  618 */     for (int j = start; j < n; j++) {
/*      */       
/*  620 */       ExpressionOwner owner2 = paths.elementAt(j);
/*  621 */       if (null != owner2) {
/*      */         
/*  623 */         Expression expr2 = owner2.getExpression();
/*  624 */         boolean isEqual = expr2.deepEquals((Expression)lpi);
/*  625 */         if (isEqual) {
/*      */           
/*  627 */           LocPathIterator lpi2 = (LocPathIterator)expr2;
/*  628 */           if (null == head) {
/*      */             
/*  630 */             head = new MultistepExprHolder(this, firstOccuranceOwner, stepCount, null);
/*  631 */             tail = head;
/*  632 */             numPathsFound++;
/*      */           } 
/*  634 */           tail.m_next = new MultistepExprHolder(this, owner2, stepCount, null);
/*  635 */           tail = tail.m_next;
/*      */ 
/*      */           
/*  638 */           paths.setElementAt(null, j);
/*      */ 
/*      */           
/*  641 */           numPathsFound++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  647 */     if (0 == numPathsFound && isGlobal) {
/*      */       
/*  649 */       head = new MultistepExprHolder(this, firstOccuranceOwner, stepCount, null);
/*  650 */       numPathsFound++;
/*      */     } 
/*      */     
/*  653 */     if (null != head) {
/*      */       
/*  655 */       ElemTemplateElement root = isGlobal ? psuedoVarRecipient : findCommonAncestor(head);
/*  656 */       LocPathIterator sharedIter = (LocPathIterator)head.m_exprOwner.getExpression();
/*  657 */       ElemVariable var = createPsuedoVarDecl(root, sharedIter, isGlobal);
/*  658 */       if (DIAGNOSE_MULTISTEPLIST)
/*  659 */         System.err.println("Created var: " + var.getName() + (isGlobal ? "(Global)" : "")); 
/*  660 */       QName uniquePsuedoVarName = var.getName();
/*  661 */       while (null != head) {
/*      */         
/*  663 */         ExpressionOwner owner = head.m_exprOwner;
/*  664 */         if (DIAGNOSE_MULTISTEPLIST)
/*  665 */           diagnoseLineNumber(owner.getExpression()); 
/*  666 */         changeToVarRef(uniquePsuedoVarName, owner, paths, root);
/*  667 */         head = head.m_next;
/*      */       } 
/*      */ 
/*      */       
/*  671 */       paths.setElementAt(var.getSelect(), firstOccuranceIndex);
/*      */     } 
/*      */     
/*  674 */     return numPathsFound;
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
/*      */   protected int oldFindAndEliminateRedundant(int start, int firstOccuranceIndex, ExpressionOwner firstOccuranceOwner, ElemTemplateElement psuedoVarRecipient, Vector paths) throws DOMException {
/*  686 */     QName uniquePsuedoVarName = null;
/*  687 */     boolean foundFirst = false;
/*  688 */     int numPathsFound = 0;
/*  689 */     int n = paths.size();
/*  690 */     Expression expr1 = firstOccuranceOwner.getExpression();
/*  691 */     if (DEBUG)
/*  692 */       assertIsLocPathIterator(expr1, firstOccuranceOwner); 
/*  693 */     boolean isGlobal = (paths == this.m_absPaths);
/*  694 */     LocPathIterator lpi = (LocPathIterator)expr1;
/*  695 */     for (int j = start; j < n; j++) {
/*      */       
/*  697 */       ExpressionOwner owner2 = paths.elementAt(j);
/*  698 */       if (null != owner2) {
/*      */         
/*  700 */         Expression expr2 = owner2.getExpression();
/*  701 */         boolean isEqual = expr2.deepEquals((Expression)lpi);
/*  702 */         if (isEqual) {
/*      */           
/*  704 */           LocPathIterator lpi2 = (LocPathIterator)expr2;
/*  705 */           if (!foundFirst) {
/*      */             
/*  707 */             foundFirst = true;
/*      */ 
/*      */ 
/*      */             
/*  711 */             ElemVariable var = createPsuedoVarDecl(psuedoVarRecipient, lpi, isGlobal);
/*  712 */             if (null == var)
/*  713 */               return 0; 
/*  714 */             uniquePsuedoVarName = var.getName();
/*      */             
/*  716 */             changeToVarRef(uniquePsuedoVarName, firstOccuranceOwner, paths, psuedoVarRecipient);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  721 */             paths.setElementAt(var.getSelect(), firstOccuranceIndex);
/*  722 */             numPathsFound++;
/*      */           } 
/*      */           
/*  725 */           changeToVarRef(uniquePsuedoVarName, owner2, paths, psuedoVarRecipient);
/*      */ 
/*      */           
/*  728 */           paths.setElementAt(null, j);
/*      */ 
/*      */           
/*  731 */           numPathsFound++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  737 */     if (0 == numPathsFound && paths == this.m_absPaths) {
/*      */       
/*  739 */       ElemVariable var = createPsuedoVarDecl(psuedoVarRecipient, lpi, true);
/*  740 */       if (null == var)
/*  741 */         return 0; 
/*  742 */       uniquePsuedoVarName = var.getName();
/*  743 */       changeToVarRef(uniquePsuedoVarName, firstOccuranceOwner, paths, psuedoVarRecipient);
/*  744 */       paths.setElementAt(var.getSelect(), firstOccuranceIndex);
/*  745 */       numPathsFound++;
/*      */     } 
/*  747 */     return numPathsFound;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int countSteps(LocPathIterator lpi) {
/*  758 */     if (lpi instanceof WalkingIterator) {
/*      */       
/*  760 */       WalkingIterator wi = (WalkingIterator)lpi;
/*  761 */       AxesWalker aw = wi.getFirstWalker();
/*  762 */       int count = 0;
/*  763 */       while (null != aw) {
/*      */         
/*  765 */         count++;
/*  766 */         aw = aw.getNextWalker();
/*      */       } 
/*  768 */       return count;
/*      */     } 
/*      */     
/*  771 */     return 1;
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
/*      */   protected void changeToVarRef(QName varName, ExpressionOwner owner, Vector paths, ElemTemplateElement psuedoVarRecipient) {
/*  793 */     Variable varRef = (paths == this.m_absPaths) ? (Variable)new VariableSafeAbsRef() : new Variable();
/*  794 */     varRef.setQName(varName);
/*  795 */     if (paths == this.m_absPaths) {
/*      */       
/*  797 */       StylesheetRoot root = (StylesheetRoot)psuedoVarRecipient;
/*  798 */       Vector globalVars = root.getVariablesAndParamsComposed();
/*      */ 
/*      */       
/*  801 */       varRef.setIndex(globalVars.size() - 1);
/*  802 */       varRef.setIsGlobal(true);
/*      */     } 
/*  804 */     owner.setExpression((Expression)varRef);
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
/*      */   protected ElemVariable createPsuedoVarDecl(ElemTemplateElement psuedoVarRecipient, LocPathIterator lpi, boolean isGlobal) throws DOMException {
/*  822 */     QName uniquePsuedoVarName = new QName("http://xml.apache.org/xalan/psuedovar", "#" + m_uniquePsuedoVarID);
/*  823 */     m_uniquePsuedoVarID++;
/*      */     
/*  825 */     if (isGlobal)
/*      */     {
/*  827 */       return createGlobalPsuedoVarDecl(uniquePsuedoVarName, (StylesheetRoot)psuedoVarRecipient, lpi);
/*      */     }
/*      */ 
/*      */     
/*  831 */     return createLocalPsuedoVarDecl(uniquePsuedoVarName, psuedoVarRecipient, lpi);
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
/*      */   protected ElemVariable createGlobalPsuedoVarDecl(QName uniquePsuedoVarName, StylesheetRoot stylesheetRoot, LocPathIterator lpi) throws DOMException {
/*  850 */     ElemVariable psuedoVar = new ElemVariable();
/*  851 */     psuedoVar.setIsTopLevel(true);
/*  852 */     XPath xpath = new XPath((Expression)lpi);
/*  853 */     psuedoVar.setSelect(xpath);
/*  854 */     psuedoVar.setName(uniquePsuedoVarName);
/*      */     
/*  856 */     Vector globalVars = stylesheetRoot.getVariablesAndParamsComposed();
/*  857 */     psuedoVar.setIndex(globalVars.size());
/*  858 */     globalVars.addElement(psuedoVar);
/*  859 */     return psuedoVar;
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
/*      */   protected ElemVariable createLocalPsuedoVarDecl(QName uniquePsuedoVarName, ElemTemplateElement psuedoVarRecipient, LocPathIterator lpi) throws DOMException {
/*  882 */     ElemVariable psuedoVar = new ElemVariablePsuedo();
/*      */     
/*  884 */     XPath xpath = new XPath((Expression)lpi);
/*  885 */     psuedoVar.setSelect(xpath);
/*  886 */     psuedoVar.setName(uniquePsuedoVarName);
/*      */     
/*  888 */     ElemVariable var = addVarDeclToElem(psuedoVarRecipient, lpi, psuedoVar);
/*      */     
/*  890 */     lpi.exprSetParent(var);
/*      */     
/*  892 */     return var;
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
/*      */   protected ElemVariable addVarDeclToElem(ElemTemplateElement psuedoVarRecipient, LocPathIterator lpi, ElemVariable psuedoVar) throws DOMException {
/*  905 */     ElemTemplateElement ete = psuedoVarRecipient.getFirstChildElem();
/*      */     
/*  907 */     lpi.callVisitors(null, this.m_varNameCollector);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  913 */     if (this.m_varNameCollector.getVarCount() > 0) {
/*      */       
/*  915 */       ElemTemplateElement baseElem = getElemFromExpression((Expression)lpi);
/*  916 */       ElemVariable varElem = getPrevVariableElem(baseElem);
/*  917 */       while (null != varElem) {
/*      */         
/*  919 */         if (this.m_varNameCollector.doesOccur(varElem.getName())) {
/*      */           
/*  921 */           psuedoVarRecipient = varElem.getParentElem();
/*  922 */           ete = varElem.getNextSiblingElem();
/*      */           break;
/*      */         } 
/*  925 */         varElem = getPrevVariableElem(varElem);
/*      */       } 
/*      */     } 
/*      */     
/*  929 */     if (null != ete && 41 == ete.getXSLToken()) {
/*      */ 
/*      */       
/*  932 */       if (isParam((ExpressionNode)lpi)) {
/*  933 */         return null;
/*      */       }
/*  935 */       while (null != ete) {
/*      */         
/*  937 */         ete = ete.getNextSiblingElem();
/*  938 */         if (null != ete && 41 != ete.getXSLToken())
/*      */           break; 
/*      */       } 
/*      */     } 
/*  942 */     psuedoVarRecipient.insertBefore((Node)psuedoVar, (Node)ete);
/*  943 */     this.m_varNameCollector.reset();
/*  944 */     return psuedoVar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isParam(ExpressionNode expr) {
/*  952 */     while (null != expr) {
/*      */       
/*  954 */       if (expr instanceof ElemTemplateElement)
/*      */         break; 
/*  956 */       expr = expr.exprGetParent();
/*      */     } 
/*  958 */     if (null != expr) {
/*      */       
/*  960 */       ElemTemplateElement ete = (ElemTemplateElement)expr;
/*  961 */       while (null != ete) {
/*      */         
/*  963 */         int type = ete.getXSLToken();
/*  964 */         switch (type) {
/*      */           
/*      */           case 41:
/*  967 */             return true;
/*      */           case 19:
/*      */           case 25:
/*  970 */             return false;
/*      */         } 
/*  972 */         ete = ete.getParentElem();
/*      */       } 
/*      */     } 
/*  975 */     return false;
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
/*      */   protected ElemVariable getPrevVariableElem(ElemTemplateElement elem) {
/*  992 */     while (null != (elem = getPrevElementWithinContext(elem))) {
/*      */       
/*  994 */       int type = elem.getXSLToken();
/*      */       
/*  996 */       if (73 == type || 41 == type)
/*      */       {
/*      */         
/*  999 */         return (ElemVariable)elem;
/*      */       }
/*      */     } 
/* 1002 */     return null;
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
/*      */   protected ElemTemplateElement getPrevElementWithinContext(ElemTemplateElement elem) {
/* 1015 */     ElemTemplateElement prev = elem.getPreviousSiblingElem();
/* 1016 */     if (null == prev)
/* 1017 */       prev = elem.getParentElem(); 
/* 1018 */     if (null != prev) {
/*      */       
/* 1020 */       int type = prev.getXSLToken();
/* 1021 */       if (28 == type || 19 == type || 25 == type)
/*      */       {
/*      */ 
/*      */         
/* 1025 */         prev = null;
/*      */       }
/*      */     } 
/* 1028 */     return prev;
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
/*      */   protected ElemTemplateElement getElemFromExpression(Expression expr) {
/* 1041 */     ExpressionNode parent = expr.exprGetParent();
/* 1042 */     while (null != parent) {
/*      */       
/* 1044 */       if (parent instanceof ElemTemplateElement)
/* 1045 */         return (ElemTemplateElement)parent; 
/* 1046 */       parent = parent.exprGetParent();
/*      */     } 
/* 1048 */     throw new RuntimeException(XSLMessages.createMessage("ER_ASSERT_NO_TEMPLATE_PARENT", null));
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
/*      */   public boolean isAbsolute(LocPathIterator path) {
/* 1060 */     int analysis = path.getAnalysisBits();
/* 1061 */     boolean isAbs = (WalkerFactory.isSet(analysis, 134217728) || WalkerFactory.isSet(analysis, 536870912));
/*      */     
/* 1063 */     if (isAbs)
/*      */     {
/* 1065 */       isAbs = this.m_absPathChecker.checkAbsolute(path);
/*      */     }
/* 1067 */     return isAbs;
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
/*      */   public boolean visitLocationPath(ExpressionOwner owner, LocPathIterator path) {
/* 1082 */     if (path instanceof org.apache.xpath.axes.SelfIteratorNoPredicate)
/*      */     {
/* 1084 */       return true;
/*      */     }
/* 1086 */     if (path instanceof WalkingIterator) {
/*      */       
/* 1088 */       WalkingIterator wi = (WalkingIterator)path;
/* 1089 */       AxesWalker aw = wi.getFirstWalker();
/* 1090 */       if (aw instanceof FilterExprWalker && null == aw.getNextWalker()) {
/*      */         
/* 1092 */         FilterExprWalker few = (FilterExprWalker)aw;
/* 1093 */         Expression exp = few.getInnerExpression();
/* 1094 */         if (exp instanceof Variable) {
/* 1095 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/* 1099 */     if (isAbsolute(path) && null != this.m_absPaths) {
/*      */       
/* 1101 */       if (DEBUG)
/* 1102 */         validateNewAddition(this.m_absPaths, owner, path); 
/* 1103 */       this.m_absPaths.addElement(owner);
/*      */     }
/* 1105 */     else if (this.m_isSameContext && null != this.m_paths) {
/*      */       
/* 1107 */       if (DEBUG)
/* 1108 */         validateNewAddition(this.m_paths, owner, path); 
/* 1109 */       this.m_paths.addElement(owner);
/*      */     } 
/*      */     
/* 1112 */     return true;
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
/*      */   public boolean visitPredicate(ExpressionOwner owner, Expression pred) {
/* 1127 */     boolean savedIsSame = this.m_isSameContext;
/* 1128 */     this.m_isSameContext = false;
/*      */ 
/*      */     
/* 1131 */     pred.callVisitors(owner, this);
/*      */     
/* 1133 */     this.m_isSameContext = savedIsSame;
/*      */ 
/*      */ 
/*      */     
/* 1137 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean visitTopLevelInstruction(ElemTemplateElement elem) {
/* 1148 */     int type = elem.getXSLToken();
/* 1149 */     switch (type) {
/*      */       
/*      */       case 19:
/* 1152 */         return visitInstruction(elem);
/*      */     } 
/* 1154 */     return true;
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
/*      */   public boolean visitInstruction(ElemTemplateElement elem) {
/*      */     Vector savedPaths;
/*      */     boolean savedIsSame;
/* 1168 */     int type = elem.getXSLToken();
/* 1169 */     switch (type) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 17:
/*      */       case 19:
/*      */       case 28:
/* 1177 */         if (type == 28) {
/*      */           
/* 1179 */           ElemForEach efe = (ElemForEach)elem;
/*      */           
/* 1181 */           Expression select = efe.getSelect();
/* 1182 */           select.callVisitors(efe, this);
/*      */         } 
/*      */         
/* 1185 */         savedPaths = this.m_paths;
/* 1186 */         this.m_paths = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1191 */         elem.callChildVisitors(this, false);
/* 1192 */         eleminateRedundentLocals(elem);
/*      */         
/* 1194 */         this.m_paths = savedPaths;
/*      */ 
/*      */         
/* 1197 */         return false;
/*      */ 
/*      */ 
/*      */       
/*      */       case 35:
/*      */       case 64:
/* 1203 */         savedIsSame = this.m_isSameContext;
/* 1204 */         this.m_isSameContext = false;
/* 1205 */         elem.callChildVisitors(this);
/* 1206 */         this.m_isSameContext = savedIsSame;
/* 1207 */         return false;
/*      */     } 
/*      */     
/* 1210 */     return true;
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
/*      */   protected void diagnoseNumPaths(Vector paths, int numPathsEliminated, int numUniquePathsEliminated) {
/* 1222 */     if (numPathsEliminated > 0)
/*      */     {
/* 1224 */       if (paths == this.m_paths) {
/*      */         
/* 1226 */         System.err.println("Eliminated " + numPathsEliminated + " total paths!");
/* 1227 */         System.err.println("Consolodated " + numUniquePathsEliminated + " redundent paths!");
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1232 */         System.err.println("Eliminated " + numPathsEliminated + " total global paths!");
/* 1233 */         System.err.println("Consolodated " + numUniquePathsEliminated + " redundent global paths!");
/*      */       } 
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
/*      */   private final void assertIsLocPathIterator(Expression expr1, ExpressionOwner eo) throws RuntimeException {
/* 1247 */     if (!(expr1 instanceof LocPathIterator)) {
/*      */       String str;
/*      */       
/* 1250 */       if (expr1 instanceof Variable) {
/*      */         
/* 1252 */         str = "Programmer's assertion: expr1 not an iterator: " + ((Variable)expr1).getQName();
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1257 */         str = "Programmer's assertion: expr1 not an iterator: " + expr1.getClass().getName();
/*      */       } 
/*      */       
/* 1260 */       throw new RuntimeException(str + ", " + eo.getClass().getName() + " " + expr1.exprGetParent());
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
/*      */   private static void validateNewAddition(Vector paths, ExpressionOwner owner, LocPathIterator path) throws RuntimeException {
/* 1275 */     assertion((owner.getExpression() == path), "owner.getExpression() != path!!!");
/* 1276 */     int n = paths.size();
/*      */     
/* 1278 */     for (int i = 0; i < n; i++) {
/*      */       
/* 1280 */       ExpressionOwner ew = paths.elementAt(i);
/* 1281 */       assertion((ew != owner), "duplicate owner on the list!!!");
/* 1282 */       assertion((ew.getExpression() != path), "duplicate expression on the list!!!");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void assertion(boolean b, String msg) {
/* 1291 */     if (!b)
/*      */     {
/* 1293 */       throw new RuntimeException(XSLMessages.createMessage("ER_ASSERT_REDUNDENT_EXPR_ELIMINATOR", new Object[] { msg }));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class MultistepExprHolder
/*      */     implements Cloneable
/*      */   {
/*      */     ExpressionOwner m_exprOwner;
/*      */ 
/*      */     
/*      */     final int m_stepCount;
/*      */ 
/*      */     
/*      */     MultistepExprHolder m_next;
/*      */     
/*      */     private final RedundentExprEliminator this$0;
/*      */ 
/*      */     
/*      */     public Object clone() throws CloneNotSupportedException {
/* 1314 */       return super.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     MultistepExprHolder(RedundentExprEliminator this$0, ExpressionOwner exprOwner, int stepCount, MultistepExprHolder next) {
/* 1325 */       this.this$0 = this$0;
/* 1326 */       this.m_exprOwner = exprOwner;
/* 1327 */       RedundentExprEliminator.assertion((null != this.m_exprOwner), "exprOwner can not be null!");
/* 1328 */       this.m_stepCount = stepCount;
/* 1329 */       this.m_next = next;
/*      */     }
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
/*      */     MultistepExprHolder addInSortedOrder(ExpressionOwner exprOwner, int stepCount) {
/* 1342 */       MultistepExprHolder first = this;
/* 1343 */       MultistepExprHolder next = this;
/* 1344 */       MultistepExprHolder prev = null;
/* 1345 */       while (null != next) {
/*      */         
/* 1347 */         if (stepCount >= next.m_stepCount) {
/*      */           
/* 1349 */           MultistepExprHolder newholder = new MultistepExprHolder(this.this$0, exprOwner, stepCount, next);
/* 1350 */           if (null == prev) {
/* 1351 */             first = newholder;
/*      */           } else {
/* 1353 */             prev.m_next = newholder;
/*      */           } 
/* 1355 */           return first;
/*      */         } 
/* 1357 */         prev = next;
/* 1358 */         next = next.m_next;
/*      */       } 
/*      */       
/* 1361 */       prev.m_next = new MultistepExprHolder(this.this$0, exprOwner, stepCount, null);
/* 1362 */       return first;
/*      */     }
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
/*      */     MultistepExprHolder unlink(MultistepExprHolder itemToRemove) {
/* 1377 */       MultistepExprHolder first = this;
/* 1378 */       MultistepExprHolder next = this;
/* 1379 */       MultistepExprHolder prev = null;
/* 1380 */       while (null != next) {
/*      */         
/* 1382 */         if (next == itemToRemove) {
/*      */           
/* 1384 */           if (null == prev) {
/* 1385 */             first = next.m_next;
/*      */           } else {
/* 1387 */             prev.m_next = next.m_next;
/*      */           } 
/* 1389 */           next.m_next = null;
/*      */           
/* 1391 */           return first;
/*      */         } 
/* 1393 */         prev = next;
/* 1394 */         next = next.m_next;
/*      */       } 
/*      */       
/* 1397 */       RedundentExprEliminator.assertion(false, "unlink failed!!!");
/* 1398 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getLength() {
/* 1406 */       int count = 0;
/* 1407 */       MultistepExprHolder next = this;
/* 1408 */       while (null != next) {
/*      */         
/* 1410 */         count++;
/* 1411 */         next = next.m_next;
/*      */       } 
/* 1413 */       return count;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void diagnose() {
/* 1421 */       System.err.print("Found multistep iterators: " + getLength() + "  ");
/* 1422 */       MultistepExprHolder next = this;
/* 1423 */       while (null != next) {
/*      */         
/* 1425 */         System.err.print("" + next.m_stepCount);
/* 1426 */         next = next.m_next;
/* 1427 */         if (null != next)
/* 1428 */           System.err.print(", "); 
/*      */       } 
/* 1430 */       System.err.println();
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/RedundentExprEliminator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */