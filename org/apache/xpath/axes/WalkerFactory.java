/*      */ package org.apache.xpath.axes;
/*      */ 
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.dtm.DTMIterator;
/*      */ import org.apache.xpath.Expression;
/*      */ import org.apache.xpath.ExpressionNode;
/*      */ import org.apache.xpath.compiler.Compiler;
/*      */ import org.apache.xpath.compiler.OpMap;
/*      */ import org.apache.xpath.objects.XNumber;
/*      */ import org.apache.xpath.patterns.ContextMatchStepPattern;
/*      */ import org.apache.xpath.patterns.FunctionPattern;
/*      */ import org.apache.xpath.patterns.StepPattern;
/*      */ import org.apache.xpath.res.XPATHMessages;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WalkerFactory
/*      */ {
/*      */   static final boolean DEBUG_PATTERN_CREATION = false;
/*      */   static final boolean DEBUG_WALKER_CREATION = false;
/*      */   static final boolean DEBUG_ITERATOR_CREATION = false;
/*      */   public static final int BITS_COUNT = 255;
/*      */   public static final int BITS_RESERVED = 3840;
/*      */   public static final int BIT_PREDICATE = 4096;
/*      */   public static final int BIT_ANCESTOR = 8192;
/*      */   public static final int BIT_ANCESTOR_OR_SELF = 16384;
/*      */   public static final int BIT_ATTRIBUTE = 32768;
/*      */   public static final int BIT_CHILD = 65536;
/*      */   public static final int BIT_DESCENDANT = 131072;
/*      */   public static final int BIT_DESCENDANT_OR_SELF = 262144;
/*      */   public static final int BIT_FOLLOWING = 524288;
/*      */   public static final int BIT_FOLLOWING_SIBLING = 1048576;
/*      */   public static final int BIT_NAMESPACE = 2097152;
/*      */   public static final int BIT_PARENT = 4194304;
/*      */   public static final int BIT_PRECEDING = 8388608;
/*      */   public static final int BIT_PRECEDING_SIBLING = 16777216;
/*      */   public static final int BIT_SELF = 33554432;
/*      */   public static final int BIT_FILTER = 67108864;
/*      */   public static final int BIT_ROOT = 134217728;
/*      */   public static final int BITMASK_TRAVERSES_OUTSIDE_SUBTREE = 234381312;
/*      */   public static final int BIT_BACKWARDS_SELF = 268435456;
/*      */   public static final int BIT_ANY_DESCENDANT_FROM_ROOT = 536870912;
/*      */   public static final int BIT_NODETEST_ANY = 1073741824;
/*      */   public static final int BIT_MATCH_PATTERN = -2147483648;
/*      */   
/*      */   static AxesWalker loadOneWalker(WalkingIterator lpi, Compiler compiler, int stepOpCodePos) throws TransformerException {
/*   65 */     AxesWalker firstWalker = null;
/*   66 */     int stepType = compiler.getOp(stepOpCodePos);
/*      */     
/*   68 */     if (stepType != -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*   73 */       firstWalker = createDefaultWalker(compiler, stepType, lpi, 0);
/*      */       
/*   75 */       firstWalker.init(compiler, stepOpCodePos, stepType);
/*      */     } 
/*      */     
/*   78 */     return firstWalker;
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
/*      */ 
/*      */   
/*      */   static AxesWalker loadWalkers(WalkingIterator lpi, Compiler compiler, int stepOpCodePos, int stepIndex) throws TransformerException {
/*  104 */     AxesWalker firstWalker = null;
/*  105 */     AxesWalker prevWalker = null;
/*      */     
/*  107 */     int analysis = analyze(compiler, stepOpCodePos, stepIndex);
/*      */     int stepType;
/*  109 */     while (-1 != (stepType = compiler.getOp(stepOpCodePos))) {
/*      */       
/*  111 */       AxesWalker walker = createDefaultWalker(compiler, stepOpCodePos, lpi, analysis);
/*      */       
/*  113 */       walker.init(compiler, stepOpCodePos, stepType);
/*  114 */       walker.exprSetParent((ExpressionNode)lpi);
/*      */ 
/*      */       
/*  117 */       if (null == firstWalker) {
/*      */         
/*  119 */         firstWalker = walker;
/*      */       }
/*      */       else {
/*      */         
/*  123 */         prevWalker.setNextWalker(walker);
/*  124 */         walker.setPrevWalker(prevWalker);
/*      */       } 
/*      */       
/*  127 */       prevWalker = walker;
/*  128 */       stepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
/*      */       
/*  130 */       if (stepOpCodePos < 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*  134 */     return firstWalker;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSet(int analysis, int bits) {
/*  139 */     return (0 != (analysis & bits));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void diagnoseIterator(String name, int analysis, Compiler compiler) {
/*  144 */     System.out.println(compiler.toString() + ", " + name + ", " + Integer.toBinaryString(analysis) + ", " + getAnalysisString(analysis));
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
/*      */   public static DTMIterator newDTMIterator(Compiler compiler, int opPos, boolean isTopLevel) throws TransformerException {
/*      */     DTMIterator dTMIterator;
/*  167 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*  168 */     int analysis = analyze(compiler, firstStepPos, 0);
/*  169 */     boolean isOneStep = isOneStep(analysis);
/*      */ 
/*      */ 
/*      */     
/*  173 */     if (isOneStep && walksSelfOnly(analysis) && isWild(analysis) && !hasPredicate(analysis)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  181 */       dTMIterator = new SelfIteratorNoPredicate(compiler, opPos, analysis);
/*      */     
/*      */     }
/*  184 */     else if (walksChildrenOnly(analysis) && isOneStep) {
/*      */ 
/*      */ 
/*      */       
/*  188 */       if (isWild(analysis) && !hasPredicate(analysis))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  194 */         dTMIterator = new ChildIterator(compiler, opPos, analysis);
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         
/*  202 */         dTMIterator = new ChildTestIterator(compiler, opPos, analysis);
/*      */       }
/*      */     
/*      */     }
/*  206 */     else if (isOneStep && walksAttributes(analysis)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  213 */       dTMIterator = new AttributeIterator(compiler, opPos, analysis);
/*      */     }
/*  215 */     else if (isOneStep && !walksFilteredList(analysis)) {
/*      */       
/*  217 */       if (!walksNamespaces(analysis) && (walksInDocOrder(analysis) || isSet(analysis, 4194304)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  225 */         dTMIterator = new OneStepIteratorForward(compiler, opPos, analysis);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         
/*  234 */         dTMIterator = new OneStepIterator(compiler, opPos, analysis);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  250 */     else if (isOptimizableForDescendantIterator(compiler, firstStepPos, 0)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  259 */       dTMIterator = new DescendantIterator(compiler, opPos, analysis);
/*      */ 
/*      */     
/*      */     }
/*  263 */     else if (isNaturalDocOrder(compiler, firstStepPos, 0, analysis)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  270 */       dTMIterator = new WalkingIterator(compiler, opPos, analysis, true);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  281 */       dTMIterator = new WalkingIteratorSorted(compiler, opPos, analysis, true);
/*      */     } 
/*      */     
/*  284 */     if (dTMIterator instanceof LocPathIterator) {
/*  285 */       ((LocPathIterator)dTMIterator).setIsTopLevel(isTopLevel);
/*      */     }
/*  287 */     return dTMIterator;
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
/*      */   public static int getAxisFromStep(Compiler compiler, int stepOpCodePos) throws TransformerException {
/*  309 */     int stepType = compiler.getOp(stepOpCodePos);
/*      */     
/*  311 */     switch (stepType) {
/*      */       
/*      */       case 43:
/*  314 */         return 6;
/*      */       case 44:
/*  316 */         return 7;
/*      */       case 46:
/*  318 */         return 11;
/*      */       case 47:
/*  320 */         return 12;
/*      */       case 45:
/*  322 */         return 10;
/*      */       case 49:
/*  324 */         return 9;
/*      */       case 37:
/*  326 */         return 0;
/*      */       case 38:
/*  328 */         return 1;
/*      */       case 39:
/*  330 */         return 2;
/*      */       case 50:
/*  332 */         return 19;
/*      */       case 40:
/*  334 */         return 3;
/*      */       case 42:
/*  336 */         return 5;
/*      */       case 41:
/*  338 */         return 4;
/*      */       case 48:
/*  340 */         return 13;
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/*  345 */         return 20;
/*      */     } 
/*      */     
/*  348 */     throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getAnalysisBitFromAxes(int axis) {
/*  359 */     switch (axis) {
/*      */       
/*      */       case 0:
/*  362 */         return 8192;
/*      */       case 1:
/*  364 */         return 16384;
/*      */       case 2:
/*  366 */         return 32768;
/*      */       case 3:
/*  368 */         return 65536;
/*      */       case 4:
/*  370 */         return 131072;
/*      */       case 5:
/*  372 */         return 262144;
/*      */       case 6:
/*  374 */         return 524288;
/*      */       case 7:
/*  376 */         return 1048576;
/*      */       case 8:
/*      */       case 9:
/*  379 */         return 2097152;
/*      */       case 10:
/*  381 */         return 4194304;
/*      */       case 11:
/*  383 */         return 8388608;
/*      */       case 12:
/*  385 */         return 16777216;
/*      */       case 13:
/*  387 */         return 33554432;
/*      */       case 14:
/*  389 */         return 262144;
/*      */       
/*      */       case 16:
/*      */       case 17:
/*      */       case 18:
/*  394 */         return 536870912;
/*      */       case 19:
/*  396 */         return 134217728;
/*      */       case 20:
/*  398 */         return 67108864;
/*      */     } 
/*  400 */     return 67108864;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean functionProximateOrContainsProximate(Compiler compiler, int opPos) {
/*  407 */     int endFunc = opPos + compiler.getOp(opPos + 1) - 1;
/*  408 */     opPos = OpMap.getFirstChildPos(opPos);
/*  409 */     int funcID = compiler.getOp(opPos);
/*      */ 
/*      */ 
/*      */     
/*  413 */     switch (funcID) {
/*      */       
/*      */       case 1:
/*      */       case 2:
/*  417 */         return true;
/*      */     } 
/*  419 */     opPos++;
/*  420 */     int i = 0;
/*  421 */     for (int p = opPos; p < endFunc; p = compiler.getNextOpPos(p), i++) {
/*      */       
/*  423 */       int innerExprOpPos = p + 2;
/*  424 */       int argOp = compiler.getOp(innerExprOpPos);
/*  425 */       boolean prox = isProximateInnerExpr(compiler, innerExprOpPos);
/*  426 */       if (prox) {
/*  427 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  431 */     return false;
/*      */   }
/*      */   
/*      */   static boolean isProximateInnerExpr(Compiler compiler, int opPos) {
/*      */     boolean isProx;
/*  436 */     int leftPos, rightPos, op = compiler.getOp(opPos);
/*  437 */     int innerExprOpPos = opPos + 2;
/*  438 */     switch (op)
/*      */     
/*      */     { case 26:
/*  441 */         if (isProximateInnerExpr(compiler, innerExprOpPos)) {
/*  442 */           return true;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 21:
/*      */       case 22:
/*      */       case 27:
/*      */       case 28:
/*  471 */         return false;
/*      */       case 25: isProx = functionProximateOrContainsProximate(compiler, opPos); if (isProx)
/*      */           return true; 
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*      */         leftPos = OpMap.getFirstChildPos(op); rightPos = compiler.getNextOpPos(leftPos); isProx = isProximateInnerExpr(compiler, leftPos); if (isProx)
/*      */           return true;  isProx = isProximateInnerExpr(compiler, rightPos); if (isProx)
/*  481 */           return true;  }  return true; } public static boolean mightBeProximate(Compiler compiler, int opPos, int stepType) throws TransformerException { int argLen; boolean mightBeProximate = false;
/*      */ 
/*      */     
/*  484 */     switch (stepType) {
/*      */       
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/*  490 */         argLen = compiler.getArgLength(opPos);
/*      */         break;
/*      */       default:
/*  493 */         argLen = compiler.getArgLengthOfStep(opPos);
/*      */         break;
/*      */     } 
/*  496 */     int predPos = compiler.getFirstPredicateOpPos(opPos);
/*  497 */     int count = 0;
/*      */     
/*  499 */     while (29 == compiler.getOp(predPos)) {
/*      */       boolean isProx; int leftPos, rightPos;
/*  501 */       count++;
/*      */       
/*  503 */       int innerExprOpPos = predPos + 2;
/*  504 */       int predOp = compiler.getOp(innerExprOpPos);
/*      */       
/*  506 */       switch (predOp) {
/*      */         
/*      */         case 22:
/*  509 */           return true;
/*      */         
/*      */         case 28:
/*      */           break;
/*      */         case 19:
/*      */         case 27:
/*  515 */           return true;
/*      */         case 25:
/*  517 */           isProx = functionProximateOrContainsProximate(compiler, innerExprOpPos);
/*      */           
/*  519 */           if (isProx)
/*  520 */             return true; 
/*      */           break;
/*      */         case 5:
/*      */         case 6:
/*      */         case 7:
/*      */         case 8:
/*      */         case 9:
/*  527 */           leftPos = OpMap.getFirstChildPos(innerExprOpPos);
/*  528 */           rightPos = compiler.getNextOpPos(leftPos);
/*  529 */           isProx = isProximateInnerExpr(compiler, leftPos);
/*  530 */           if (isProx)
/*  531 */             return true; 
/*  532 */           isProx = isProximateInnerExpr(compiler, rightPos);
/*  533 */           if (isProx)
/*  534 */             return true; 
/*      */           break;
/*      */         default:
/*  537 */           return true;
/*      */       } 
/*      */       
/*  540 */       predPos = compiler.getNextOpPos(predPos);
/*      */     } 
/*      */     
/*  543 */     return mightBeProximate; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isOptimizableForDescendantIterator(Compiler compiler, int stepOpCodePos, int stepIndex) throws TransformerException {
/*  566 */     int stepCount = 0;
/*  567 */     boolean foundDorDS = false;
/*  568 */     boolean foundSelf = false;
/*  569 */     boolean foundDS = false;
/*      */     
/*  571 */     int nodeTestType = 1033;
/*      */     int stepType;
/*  573 */     while (-1 != (stepType = compiler.getOp(stepOpCodePos))) {
/*      */ 
/*      */ 
/*      */       
/*  577 */       if (nodeTestType != 1033 && nodeTestType != 35) {
/*  578 */         return false;
/*      */       }
/*  580 */       stepCount++;
/*  581 */       if (stepCount > 3) {
/*  582 */         return false;
/*      */       }
/*  584 */       boolean mightBeProximate = mightBeProximate(compiler, stepOpCodePos, stepType);
/*  585 */       if (mightBeProximate) {
/*  586 */         return false;
/*      */       }
/*  588 */       switch (stepType) {
/*      */         
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 49:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*  606 */           return false;
/*      */         case 50:
/*  608 */           if (1 != stepCount)
/*  609 */             return false; 
/*      */           break;
/*      */         case 40:
/*  612 */           if (!foundDS && (!foundDorDS || !foundSelf))
/*  613 */             return false; 
/*      */           break;
/*      */         case 42:
/*  616 */           foundDS = true;
/*      */         case 41:
/*  618 */           if (3 == stepCount)
/*  619 */             return false; 
/*  620 */           foundDorDS = true;
/*      */           break;
/*      */         case 48:
/*  623 */           if (1 != stepCount)
/*  624 */             return false; 
/*  625 */           foundSelf = true;
/*      */           break;
/*      */         default:
/*  628 */           throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
/*      */       } 
/*      */ 
/*      */       
/*  632 */       nodeTestType = compiler.getStepTestType(stepOpCodePos);
/*      */       
/*  634 */       int nextStepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
/*      */       
/*  636 */       if (nextStepOpCodePos < 0) {
/*      */         break;
/*      */       }
/*  639 */       if (-1 != compiler.getOp(nextStepOpCodePos))
/*      */       {
/*  641 */         if (compiler.countPredicates(stepOpCodePos) > 0)
/*      */         {
/*  643 */           return false;
/*      */         }
/*      */       }
/*      */       
/*  647 */       stepOpCodePos = nextStepOpCodePos;
/*      */     } 
/*      */     
/*  650 */     return true;
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
/*      */   private static int analyze(Compiler compiler, int stepOpCodePos, int stepIndex) throws TransformerException {
/*  674 */     int stepCount = 0;
/*  675 */     int analysisResult = 0;
/*      */     int stepType;
/*  677 */     while (-1 != (stepType = compiler.getOp(stepOpCodePos))) {
/*      */       
/*  679 */       stepCount++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  686 */       boolean predAnalysis = analyzePredicate(compiler, stepOpCodePos, stepType);
/*      */ 
/*      */       
/*  689 */       if (predAnalysis) {
/*  690 */         analysisResult |= 0x1000;
/*      */       }
/*  692 */       switch (stepType) {
/*      */         
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*  698 */           analysisResult |= 0x4000000;
/*      */           break;
/*      */         case 50:
/*  701 */           analysisResult |= 0x8000000;
/*      */           break;
/*      */         case 37:
/*  704 */           analysisResult |= 0x2000;
/*      */           break;
/*      */         case 38:
/*  707 */           analysisResult |= 0x4000;
/*      */           break;
/*      */         case 39:
/*  710 */           analysisResult |= 0x8000;
/*      */           break;
/*      */         case 49:
/*  713 */           analysisResult |= 0x200000;
/*      */           break;
/*      */         case 40:
/*  716 */           analysisResult |= 0x10000;
/*      */           break;
/*      */         case 41:
/*  719 */           analysisResult |= 0x20000;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 42:
/*  724 */           if (2 == stepCount && 134217728 == analysisResult)
/*      */           {
/*  726 */             analysisResult |= 0x20000000;
/*      */           }
/*      */           
/*  729 */           analysisResult |= 0x40000;
/*      */           break;
/*      */         case 43:
/*  732 */           analysisResult |= 0x80000;
/*      */           break;
/*      */         case 44:
/*  735 */           analysisResult |= 0x100000;
/*      */           break;
/*      */         case 46:
/*  738 */           analysisResult |= 0x800000;
/*      */           break;
/*      */         case 47:
/*  741 */           analysisResult |= 0x1000000;
/*      */           break;
/*      */         case 45:
/*  744 */           analysisResult |= 0x400000;
/*      */           break;
/*      */         case 48:
/*  747 */           analysisResult |= 0x2000000;
/*      */           break;
/*      */         case 51:
/*  750 */           analysisResult |= 0x80008000;
/*      */           break;
/*      */         case 52:
/*  753 */           analysisResult |= 0x80002000;
/*      */           break;
/*      */         case 53:
/*  756 */           analysisResult |= 0x80400000;
/*      */           break;
/*      */         default:
/*  759 */           throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
/*      */       } 
/*      */ 
/*      */       
/*  763 */       if (1033 == compiler.getOp(stepOpCodePos + 3))
/*      */       {
/*  765 */         analysisResult |= 0x40000000;
/*      */       }
/*      */       
/*  768 */       stepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
/*      */       
/*  770 */       if (stepOpCodePos < 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*  774 */     analysisResult |= stepCount & 0xFF;
/*      */     
/*  776 */     return analysisResult;
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
/*      */   public static boolean isDownwardAxisOfMany(int axis) {
/*  789 */     return (5 == axis || 4 == axis || 6 == axis || 11 == axis);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static StepPattern loadSteps(MatchPatternIterator mpi, Compiler compiler, int stepOpCodePos, int stepIndex) throws TransformerException {
/*  834 */     StepPattern step = null;
/*  835 */     StepPattern firstStep = null, prevStep = null;
/*  836 */     int analysis = analyze(compiler, stepOpCodePos, stepIndex);
/*      */     int stepType;
/*  838 */     while (-1 != (stepType = compiler.getOp(stepOpCodePos))) {
/*      */       
/*  840 */       step = createDefaultStepPattern(compiler, stepOpCodePos, mpi, analysis, firstStep, prevStep);
/*      */ 
/*      */       
/*  843 */       if (null == firstStep) {
/*      */         
/*  845 */         firstStep = step;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  851 */         step.setRelativePathPattern(prevStep);
/*      */       } 
/*      */       
/*  854 */       prevStep = step;
/*  855 */       stepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
/*      */       
/*  857 */       if (stepOpCodePos < 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*  861 */     int axis = 13;
/*  862 */     int paxis = 13;
/*  863 */     StepPattern tail = step;
/*  864 */     for (StepPattern pat = step; null != pat; 
/*  865 */       pat = pat.getRelativePathPattern()) {
/*      */       
/*  867 */       int nextAxis = pat.getAxis();
/*      */       
/*  869 */       pat.setAxis(axis);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  894 */       int whatToShow = pat.getWhatToShow();
/*  895 */       if (whatToShow == 2 || whatToShow == 4096) {
/*      */ 
/*      */         
/*  898 */         int newAxis = (whatToShow == 2) ? 2 : 9;
/*      */         
/*  900 */         if (isDownwardAxisOfMany(axis)) {
/*      */           
/*  902 */           StepPattern attrPat = new StepPattern(whatToShow, pat.getNamespace(), pat.getLocalName(), newAxis, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  907 */           XNumber score = pat.getStaticScore();
/*  908 */           pat.setNamespace(null);
/*  909 */           pat.setLocalName("*");
/*  910 */           attrPat.setPredicates(pat.getPredicates());
/*  911 */           pat.setPredicates(null);
/*  912 */           pat.setWhatToShow(1);
/*  913 */           StepPattern rel = pat.getRelativePathPattern();
/*  914 */           pat.setRelativePathPattern(attrPat);
/*  915 */           attrPat.setRelativePathPattern(rel);
/*  916 */           attrPat.setStaticScore(score);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  922 */           if (11 == pat.getAxis()) {
/*  923 */             pat.setAxis(15);
/*      */           }
/*  925 */           else if (4 == pat.getAxis()) {
/*  926 */             pat.setAxis(5);
/*      */           } 
/*  928 */           pat = attrPat;
/*      */         }
/*  930 */         else if (3 == pat.getAxis()) {
/*      */ 
/*      */ 
/*      */           
/*  934 */           pat.setAxis(2);
/*      */         } 
/*      */       } 
/*  937 */       axis = nextAxis;
/*      */       
/*  939 */       tail = pat;
/*      */     } 
/*      */     
/*  942 */     if (axis < 16) {
/*      */       
/*  944 */       ContextMatchStepPattern contextMatchStepPattern = new ContextMatchStepPattern(axis, paxis);
/*      */       
/*  946 */       XNumber score = tail.getStaticScore();
/*  947 */       tail.setRelativePathPattern((StepPattern)contextMatchStepPattern);
/*  948 */       tail.setStaticScore(score);
/*  949 */       contextMatchStepPattern.setStaticScore(score);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  958 */     return step;
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
/*      */ 
/*      */   
/*      */   private static StepPattern createDefaultStepPattern(Compiler compiler, int opPos, MatchPatternIterator mpi, int analysis, StepPattern tail, StepPattern head) throws TransformerException {
/*      */     FunctionPattern functionPattern;
/*      */     StepPattern stepPattern1;
/*      */     int axis, predicateAxis;
/*      */     Expression expr;
/*  988 */     int stepType = compiler.getOp(opPos);
/*  989 */     boolean simpleInit = false;
/*  990 */     int totalNumberWalkers = analysis & 0xFF;
/*  991 */     boolean prevIsOneStepDown = true;
/*  992 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*      */     
/*  994 */     int whatToShow = compiler.getWhatToShow(opPos);
/*  995 */     StepPattern ai = null;
/*      */ 
/*      */     
/*  998 */     switch (stepType) {
/*      */       
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/* 1004 */         prevIsOneStepDown = false;
/*      */ 
/*      */ 
/*      */         
/* 1008 */         switch (stepType) {
/*      */           
/*      */           case 22:
/*      */           case 23:
/*      */           case 24:
/*      */           case 25:
/* 1014 */             expr = compiler.compile(opPos);
/*      */             break;
/*      */           default:
/* 1017 */             expr = compiler.compile(opPos + 2);
/*      */             break;
/*      */         } 
/* 1020 */         axis = 20;
/* 1021 */         predicateAxis = 20;
/* 1022 */         functionPattern = new FunctionPattern(expr, axis, predicateAxis);
/* 1023 */         simpleInit = true;
/*      */         break;
/*      */       case 50:
/* 1026 */         whatToShow = 1280;
/*      */ 
/*      */         
/* 1029 */         axis = 19;
/* 1030 */         predicateAxis = 19;
/* 1031 */         stepPattern1 = new StepPattern(1280, axis, predicateAxis);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 39:
/* 1036 */         whatToShow = 2;
/* 1037 */         axis = 10;
/* 1038 */         predicateAxis = 2;
/*      */         break;
/*      */       
/*      */       case 49:
/* 1042 */         whatToShow = 4096;
/* 1043 */         axis = 10;
/* 1044 */         predicateAxis = 9;
/*      */         break;
/*      */       
/*      */       case 37:
/* 1048 */         axis = 4;
/* 1049 */         predicateAxis = 0;
/*      */         break;
/*      */       case 40:
/* 1052 */         axis = 10;
/* 1053 */         predicateAxis = 3;
/*      */         break;
/*      */       case 38:
/* 1056 */         axis = 5;
/* 1057 */         predicateAxis = 1;
/*      */         break;
/*      */       case 48:
/* 1060 */         axis = 13;
/* 1061 */         predicateAxis = 13;
/*      */         break;
/*      */       case 45:
/* 1064 */         axis = 3;
/* 1065 */         predicateAxis = 10;
/*      */         break;
/*      */       case 47:
/* 1068 */         axis = 7;
/* 1069 */         predicateAxis = 12;
/*      */         break;
/*      */       case 46:
/* 1072 */         axis = 6;
/* 1073 */         predicateAxis = 11;
/*      */         break;
/*      */       case 44:
/* 1076 */         axis = 12;
/* 1077 */         predicateAxis = 7;
/*      */         break;
/*      */       case 43:
/* 1080 */         axis = 11;
/* 1081 */         predicateAxis = 6;
/*      */         break;
/*      */       case 42:
/* 1084 */         axis = 1;
/* 1085 */         predicateAxis = 5;
/*      */         break;
/*      */       case 41:
/* 1088 */         axis = 0;
/* 1089 */         predicateAxis = 4;
/*      */         break;
/*      */       default:
/* 1092 */         throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
/*      */     } 
/*      */     
/* 1095 */     if (null == stepPattern1) {
/*      */       
/* 1097 */       whatToShow = compiler.getWhatToShow(opPos);
/* 1098 */       stepPattern1 = new StepPattern(whatToShow, compiler.getStepNS(opPos), compiler.getStepLocalName(opPos), axis, predicateAxis);
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
/*      */     
/* 1113 */     int argLen = compiler.getFirstPredicateOpPos(opPos);
/*      */     
/* 1115 */     stepPattern1.setPredicates(compiler.getCompiledPredicates(argLen));
/*      */     
/* 1117 */     return stepPattern1;
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
/*      */   static boolean analyzePredicate(Compiler compiler, int opPos, int stepType) throws TransformerException {
/*      */     int argLen;
/* 1139 */     switch (stepType) {
/*      */       
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/* 1145 */         argLen = compiler.getArgLength(opPos);
/*      */         break;
/*      */       default:
/* 1148 */         argLen = compiler.getArgLengthOfStep(opPos);
/*      */         break;
/*      */     } 
/* 1151 */     int pos = compiler.getFirstPredicateOpPos(opPos);
/* 1152 */     int nPredicates = compiler.countPredicates(pos);
/*      */     
/* 1154 */     return (nPredicates > 0);
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
/*      */   private static AxesWalker createDefaultWalker(Compiler compiler, int opPos, WalkingIterator lpi, int analysis) {
/* 1174 */     AxesWalker ai = null;
/* 1175 */     int stepType = compiler.getOp(opPos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1185 */     boolean simpleInit = false;
/* 1186 */     int totalNumberWalkers = analysis & 0xFF;
/* 1187 */     boolean prevIsOneStepDown = true;
/*      */     
/* 1189 */     switch (stepType) {
/*      */       
/*      */       case 22:
/*      */       case 23:
/*      */       case 24:
/*      */       case 25:
/* 1195 */         prevIsOneStepDown = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1201 */         ai = new FilterExprWalker(lpi);
/* 1202 */         simpleInit = true;
/*      */         break;
/*      */       case 50:
/* 1205 */         ai = new AxesWalker(lpi, 19);
/*      */         break;
/*      */       case 37:
/* 1208 */         prevIsOneStepDown = false;
/* 1209 */         ai = new ReverseAxesWalker(lpi, 0);
/*      */         break;
/*      */       case 38:
/* 1212 */         prevIsOneStepDown = false;
/* 1213 */         ai = new ReverseAxesWalker(lpi, 1);
/*      */         break;
/*      */       case 39:
/* 1216 */         ai = new AxesWalker(lpi, 2);
/*      */         break;
/*      */       case 49:
/* 1219 */         ai = new AxesWalker(lpi, 9);
/*      */         break;
/*      */       case 40:
/* 1222 */         ai = new AxesWalker(lpi, 3);
/*      */         break;
/*      */       case 41:
/* 1225 */         prevIsOneStepDown = false;
/* 1226 */         ai = new AxesWalker(lpi, 4);
/*      */         break;
/*      */       case 42:
/* 1229 */         prevIsOneStepDown = false;
/* 1230 */         ai = new AxesWalker(lpi, 5);
/*      */         break;
/*      */       case 43:
/* 1233 */         prevIsOneStepDown = false;
/* 1234 */         ai = new AxesWalker(lpi, 6);
/*      */         break;
/*      */       case 44:
/* 1237 */         prevIsOneStepDown = false;
/* 1238 */         ai = new AxesWalker(lpi, 7);
/*      */         break;
/*      */       case 46:
/* 1241 */         prevIsOneStepDown = false;
/* 1242 */         ai = new ReverseAxesWalker(lpi, 11);
/*      */         break;
/*      */       case 47:
/* 1245 */         prevIsOneStepDown = false;
/* 1246 */         ai = new ReverseAxesWalker(lpi, 12);
/*      */         break;
/*      */       case 45:
/* 1249 */         prevIsOneStepDown = false;
/* 1250 */         ai = new ReverseAxesWalker(lpi, 10);
/*      */         break;
/*      */       case 48:
/* 1253 */         ai = new AxesWalker(lpi, 13);
/*      */         break;
/*      */       default:
/* 1256 */         throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
/*      */     } 
/*      */ 
/*      */     
/* 1260 */     if (simpleInit) {
/*      */       
/* 1262 */       ai.initNodeTest(-1);
/*      */     }
/*      */     else {
/*      */       
/* 1266 */       int whatToShow = compiler.getWhatToShow(opPos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1275 */       if (0 == (whatToShow & 0x1043) || whatToShow == -1) {
/*      */ 
/*      */         
/* 1278 */         ai.initNodeTest(whatToShow);
/*      */       } else {
/*      */         
/* 1281 */         ai.initNodeTest(whatToShow, compiler.getStepNS(opPos), compiler.getStepLocalName(opPos));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1286 */     return ai;
/*      */   }
/*      */ 
/*      */   
/*      */   public static String getAnalysisString(int analysis) {
/* 1291 */     StringBuffer buf = new StringBuffer();
/* 1292 */     buf.append("count: " + getStepCount(analysis) + " ");
/* 1293 */     if ((analysis & 0x40000000) != 0)
/*      */     {
/* 1295 */       buf.append("NTANY|");
/*      */     }
/* 1297 */     if ((analysis & 0x1000) != 0)
/*      */     {
/* 1299 */       buf.append("PRED|");
/*      */     }
/* 1301 */     if ((analysis & 0x2000) != 0)
/*      */     {
/* 1303 */       buf.append("ANC|");
/*      */     }
/* 1305 */     if ((analysis & 0x4000) != 0)
/*      */     {
/* 1307 */       buf.append("ANCOS|");
/*      */     }
/* 1309 */     if ((analysis & 0x8000) != 0)
/*      */     {
/* 1311 */       buf.append("ATTR|");
/*      */     }
/* 1313 */     if ((analysis & 0x10000) != 0)
/*      */     {
/* 1315 */       buf.append("CH|");
/*      */     }
/* 1317 */     if ((analysis & 0x20000) != 0)
/*      */     {
/* 1319 */       buf.append("DESC|");
/*      */     }
/* 1321 */     if ((analysis & 0x40000) != 0)
/*      */     {
/* 1323 */       buf.append("DESCOS|");
/*      */     }
/* 1325 */     if ((analysis & 0x80000) != 0)
/*      */     {
/* 1327 */       buf.append("FOL|");
/*      */     }
/* 1329 */     if ((analysis & 0x100000) != 0)
/*      */     {
/* 1331 */       buf.append("FOLS|");
/*      */     }
/* 1333 */     if ((analysis & 0x200000) != 0)
/*      */     {
/* 1335 */       buf.append("NS|");
/*      */     }
/* 1337 */     if ((analysis & 0x400000) != 0)
/*      */     {
/* 1339 */       buf.append("P|");
/*      */     }
/* 1341 */     if ((analysis & 0x800000) != 0)
/*      */     {
/* 1343 */       buf.append("PREC|");
/*      */     }
/* 1345 */     if ((analysis & 0x1000000) != 0)
/*      */     {
/* 1347 */       buf.append("PRECS|");
/*      */     }
/* 1349 */     if ((analysis & 0x2000000) != 0)
/*      */     {
/* 1351 */       buf.append(".|");
/*      */     }
/* 1353 */     if ((analysis & 0x4000000) != 0)
/*      */     {
/* 1355 */       buf.append("FLT|");
/*      */     }
/* 1357 */     if ((analysis & 0x8000000) != 0)
/*      */     {
/* 1359 */       buf.append("R|");
/*      */     }
/* 1361 */     return buf.toString();
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
/*      */   public static boolean hasPredicate(int analysis) {
/* 1375 */     return (0 != (analysis & 0x1000));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWild(int analysis) {
/* 1380 */     return (0 != (analysis & 0x40000000));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksAncestors(int analysis) {
/* 1385 */     return isSet(analysis, 24576);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksAttributes(int analysis) {
/* 1390 */     return (0 != (analysis & 0x8000));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksNamespaces(int analysis) {
/* 1395 */     return (0 != (analysis & 0x200000));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksChildren(int analysis) {
/* 1400 */     return (0 != (analysis & 0x10000));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksDescendants(int analysis) {
/* 1405 */     return isSet(analysis, 393216);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksSubtree(int analysis) {
/* 1410 */     return isSet(analysis, 458752);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksSubtreeOnlyMaybeAbsolute(int analysis) {
/* 1415 */     return (walksSubtree(analysis) && !walksExtraNodes(analysis) && !walksUp(analysis) && !walksSideways(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksSubtreeOnly(int analysis) {
/* 1424 */     return (walksSubtreeOnlyMaybeAbsolute(analysis) && !isAbsolute(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksFilteredList(int analysis) {
/* 1431 */     return isSet(analysis, 67108864);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksSubtreeOnlyFromRootOrContext(int analysis) {
/* 1436 */     return (walksSubtree(analysis) && !walksExtraNodes(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isSet(analysis, 67108864));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksInDocOrder(int analysis) {
/* 1446 */     return ((walksSubtreeOnlyMaybeAbsolute(analysis) || walksExtraNodesOnly(analysis) || walksFollowingOnlyMaybeAbsolute(analysis)) && !isSet(analysis, 67108864));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksFollowingOnlyMaybeAbsolute(int analysis) {
/* 1455 */     return (isSet(analysis, 35127296) && !walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksUp(int analysis) {
/* 1464 */     return isSet(analysis, 4218880);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksSideways(int analysis) {
/* 1469 */     return isSet(analysis, 26738688);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksExtraNodes(int analysis) {
/* 1475 */     return isSet(analysis, 2129920);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksExtraNodesOnly(int analysis) {
/* 1480 */     return (walksExtraNodes(analysis) && !isSet(analysis, 33554432) && !walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAbsolute(int analysis) {
/* 1491 */     return isSet(analysis, 201326592);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean walksChildrenOnly(int analysis) {
/* 1496 */     return (walksChildren(analysis) && !isSet(analysis, 33554432) && !walksExtraNodes(analysis) && !walksDescendants(analysis) && !walksUp(analysis) && !walksSideways(analysis) && (!isAbsolute(analysis) || isSet(analysis, 134217728)));
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
/*      */   public static boolean walksChildrenAndExtraAndSelfOnly(int analysis) {
/* 1508 */     return (walksChildren(analysis) && !walksDescendants(analysis) && !walksUp(analysis) && !walksSideways(analysis) && (!isAbsolute(analysis) || isSet(analysis, 134217728)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksDescendantsAndExtraAndSelfOnly(int analysis) {
/* 1518 */     return (!walksChildren(analysis) && walksDescendants(analysis) && !walksUp(analysis) && !walksSideways(analysis) && (!isAbsolute(analysis) || isSet(analysis, 134217728)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksSelfOnly(int analysis) {
/* 1528 */     return (isSet(analysis, 33554432) && !walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksUpOnly(int analysis) {
/* 1539 */     return (!walksSubtree(analysis) && walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksDownOnly(int analysis) {
/* 1548 */     return (walksSubtree(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean walksDownExtraOnly(int analysis) {
/* 1557 */     return (walksSubtree(analysis) && walksExtraNodes(analysis) && !walksUp(analysis) && !walksSideways(analysis) && !isAbsolute(analysis));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean canSkipSubtrees(int analysis) {
/* 1566 */     return isSet(analysis, 65536) | walksSideways(analysis);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean canCrissCross(int analysis) {
/* 1572 */     if (walksSelfOnly(analysis))
/* 1573 */       return false; 
/* 1574 */     if (walksDownOnly(analysis) && !canSkipSubtrees(analysis))
/* 1575 */       return false; 
/* 1576 */     if (walksChildrenAndExtraAndSelfOnly(analysis))
/* 1577 */       return false; 
/* 1578 */     if (walksDescendantsAndExtraAndSelfOnly(analysis))
/* 1579 */       return false; 
/* 1580 */     if (walksUpOnly(analysis))
/* 1581 */       return false; 
/* 1582 */     if (walksExtraNodesOnly(analysis))
/* 1583 */       return false; 
/* 1584 */     if (walksSubtree(analysis) && (walksSideways(analysis) || walksUp(analysis) || canSkipSubtrees(analysis)))
/*      */     {
/*      */ 
/*      */       
/* 1588 */       return true;
/*      */     }
/* 1590 */     return false;
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
/*      */   public static boolean isNaturalDocOrder(int analysis) {
/* 1605 */     if (canCrissCross(analysis) || isSet(analysis, 2097152) || walksFilteredList(analysis))
/*      */     {
/* 1607 */       return false;
/*      */     }
/* 1609 */     if (walksInDocOrder(analysis)) {
/* 1610 */       return true;
/*      */     }
/* 1612 */     return false;
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
/*      */   private static boolean isNaturalDocOrder(Compiler compiler, int stepOpCodePos, int stepIndex, int analysis) throws TransformerException {
/* 1633 */     if (canCrissCross(analysis)) {
/* 1634 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1638 */     if (isSet(analysis, 2097152)) {
/* 1639 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1647 */     if (isSet(analysis, 1572864) && isSet(analysis, 25165824))
/*      */     {
/* 1649 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1657 */     int stepCount = 0;
/* 1658 */     boolean foundWildAttribute = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1663 */     int potentialDuplicateMakingStepCount = 0;
/*      */     int stepType;
/* 1665 */     while (-1 != (stepType = compiler.getOp(stepOpCodePos))) {
/*      */       String localName;
/* 1667 */       stepCount++;
/*      */       
/* 1669 */       switch (stepType) {
/*      */         
/*      */         case 39:
/*      */         case 51:
/* 1673 */           if (foundWildAttribute) {
/* 1674 */             return false;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1679 */           localName = compiler.getStepLocalName(stepOpCodePos);
/*      */           
/* 1681 */           if (localName.equals("*"))
/*      */           {
/* 1683 */             foundWildAttribute = true;
/*      */           }
/*      */           break;
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 37:
/*      */         case 38:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 49:
/*      */         case 52:
/*      */         case 53:
/* 1702 */           if (potentialDuplicateMakingStepCount > 0)
/* 1703 */             return false; 
/* 1704 */           potentialDuplicateMakingStepCount++;
/*      */         case 40:
/*      */         case 48:
/*      */         case 50:
/* 1708 */           if (foundWildAttribute)
/* 1709 */             return false; 
/*      */           break;
/*      */         default:
/* 1712 */           throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[] { Integer.toString(stepType) }));
/*      */       } 
/*      */ 
/*      */       
/* 1716 */       int nextStepOpCodePos = compiler.getNextStepPos(stepOpCodePos);
/*      */       
/* 1718 */       if (nextStepOpCodePos < 0) {
/*      */         break;
/*      */       }
/* 1721 */       stepOpCodePos = nextStepOpCodePos;
/*      */     } 
/*      */     
/* 1724 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOneStep(int analysis) {
/* 1729 */     return ((analysis & 0xFF) == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getStepCount(int analysis) {
/* 1734 */     return analysis & 0xFF;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/WalkerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */