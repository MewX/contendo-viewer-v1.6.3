/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMAxisTraverser;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ import org.apache.xpath.compiler.OpMap;
/*     */ import org.apache.xpath.patterns.NodeTest;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DescendantIterator
/*     */   extends LocPathIterator
/*     */ {
/*     */   protected transient DTMAxisTraverser m_traverser;
/*     */   protected int m_axis;
/*     */   protected int m_extendedTypeID;
/*     */   
/*     */   DescendantIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  53 */     super(compiler, opPos, analysis, false);
/*     */     
/*  55 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*  56 */     int stepType = compiler.getOp(firstStepPos);
/*     */     
/*  58 */     boolean orSelf = (42 == stepType);
/*  59 */     boolean fromRoot = false;
/*  60 */     if (48 == stepType) {
/*     */       
/*  62 */       orSelf = true;
/*     */     
/*     */     }
/*  65 */     else if (50 == stepType) {
/*     */       
/*  67 */       fromRoot = true;
/*     */       
/*  69 */       int i = compiler.getNextStepPos(firstStepPos);
/*  70 */       if (compiler.getOp(i) == 42) {
/*  71 */         orSelf = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  76 */     int nextStepPos = firstStepPos;
/*     */     
/*     */     while (true) {
/*  79 */       nextStepPos = compiler.getNextStepPos(nextStepPos);
/*  80 */       if (nextStepPos > 0) {
/*     */         
/*  82 */         int stepOp = compiler.getOp(nextStepPos);
/*  83 */         if (-1 != stepOp) {
/*  84 */           firstStepPos = nextStepPos;
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       
/*     */       break;
/*     */     } 
/*     */     
/*  94 */     if ((analysis & 0x10000) != 0) {
/*  95 */       orSelf = false;
/*     */     }
/*  97 */     if (fromRoot) {
/*     */       
/*  99 */       if (orSelf) {
/* 100 */         this.m_axis = 18;
/*     */       } else {
/* 102 */         this.m_axis = 17;
/*     */       } 
/* 104 */     } else if (orSelf) {
/* 105 */       this.m_axis = 5;
/*     */     } else {
/* 107 */       this.m_axis = 4;
/*     */     } 
/* 109 */     int whatToShow = compiler.getWhatToShow(firstStepPos);
/*     */     
/* 111 */     if (0 == (whatToShow & 0x43) || whatToShow == -1) {
/*     */ 
/*     */ 
/*     */       
/* 115 */       initNodeTest(whatToShow);
/*     */     } else {
/*     */       
/* 118 */       initNodeTest(whatToShow, compiler.getStepNS(firstStepPos), compiler.getStepLocalName(firstStepPos));
/*     */     } 
/*     */     
/* 121 */     initPredicateInfo(compiler, firstStepPos);
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
/*     */   public DescendantIterator() {
/* 135 */     super(null);
/* 136 */     this.m_axis = 18;
/* 137 */     int whatToShow = -1;
/* 138 */     initNodeTest(whatToShow);
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
/*     */   public DTMIterator cloneWithReset() throws CloneNotSupportedException {
/* 153 */     DescendantIterator clone = (DescendantIterator)super.cloneWithReset();
/* 154 */     clone.m_traverser = this.m_traverser;
/*     */     
/* 156 */     clone.resetProximityPositions();
/*     */     
/* 158 */     return clone;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield m_foundLast : Z
/*     */     //   4: ifeq -> 9
/*     */     //   7: iconst_m1
/*     */     //   8: ireturn
/*     */     //   9: iconst_m1
/*     */     //   10: aload_0
/*     */     //   11: getfield m_lastFetched : I
/*     */     //   14: if_icmpne -> 21
/*     */     //   17: aload_0
/*     */     //   18: invokevirtual resetProximityPositions : ()V
/*     */     //   21: iconst_m1
/*     */     //   22: aload_0
/*     */     //   23: getfield m_stackFrame : I
/*     */     //   26: if_icmpeq -> 53
/*     */     //   29: aload_0
/*     */     //   30: getfield m_execContext : Lorg/apache/xpath/XPathContext;
/*     */     //   33: invokevirtual getVarStack : ()Lorg/apache/xpath/VariableStack;
/*     */     //   36: astore_2
/*     */     //   37: aload_2
/*     */     //   38: invokevirtual getStackFrame : ()I
/*     */     //   41: istore_3
/*     */     //   42: aload_2
/*     */     //   43: aload_0
/*     */     //   44: getfield m_stackFrame : I
/*     */     //   47: invokevirtual setStackFrame : (I)V
/*     */     //   50: goto -> 57
/*     */     //   53: aconst_null
/*     */     //   54: astore_2
/*     */     //   55: iconst_0
/*     */     //   56: istore_3
/*     */     //   57: iconst_0
/*     */     //   58: aload_0
/*     */     //   59: getfield m_extendedTypeID : I
/*     */     //   62: if_icmpne -> 111
/*     */     //   65: aload_0
/*     */     //   66: iconst_m1
/*     */     //   67: aload_0
/*     */     //   68: getfield m_lastFetched : I
/*     */     //   71: if_icmpne -> 88
/*     */     //   74: aload_0
/*     */     //   75: getfield m_traverser : Lorg/apache/xml/dtm/DTMAxisTraverser;
/*     */     //   78: aload_0
/*     */     //   79: getfield m_context : I
/*     */     //   82: invokevirtual first : (I)I
/*     */     //   85: goto -> 103
/*     */     //   88: aload_0
/*     */     //   89: getfield m_traverser : Lorg/apache/xml/dtm/DTMAxisTraverser;
/*     */     //   92: aload_0
/*     */     //   93: getfield m_context : I
/*     */     //   96: aload_0
/*     */     //   97: getfield m_lastFetched : I
/*     */     //   100: invokevirtual next : (II)I
/*     */     //   103: dup_x1
/*     */     //   104: putfield m_lastFetched : I
/*     */     //   107: istore_1
/*     */     //   108: goto -> 162
/*     */     //   111: aload_0
/*     */     //   112: iconst_m1
/*     */     //   113: aload_0
/*     */     //   114: getfield m_lastFetched : I
/*     */     //   117: if_icmpne -> 138
/*     */     //   120: aload_0
/*     */     //   121: getfield m_traverser : Lorg/apache/xml/dtm/DTMAxisTraverser;
/*     */     //   124: aload_0
/*     */     //   125: getfield m_context : I
/*     */     //   128: aload_0
/*     */     //   129: getfield m_extendedTypeID : I
/*     */     //   132: invokevirtual first : (II)I
/*     */     //   135: goto -> 157
/*     */     //   138: aload_0
/*     */     //   139: getfield m_traverser : Lorg/apache/xml/dtm/DTMAxisTraverser;
/*     */     //   142: aload_0
/*     */     //   143: getfield m_context : I
/*     */     //   146: aload_0
/*     */     //   147: getfield m_lastFetched : I
/*     */     //   150: aload_0
/*     */     //   151: getfield m_extendedTypeID : I
/*     */     //   154: invokevirtual next : (III)I
/*     */     //   157: dup_x1
/*     */     //   158: putfield m_lastFetched : I
/*     */     //   161: istore_1
/*     */     //   162: iconst_m1
/*     */     //   163: iload_1
/*     */     //   164: if_icmpeq -> 184
/*     */     //   167: iconst_1
/*     */     //   168: aload_0
/*     */     //   169: iload_1
/*     */     //   170: invokevirtual acceptNode : (I)S
/*     */     //   173: if_icmpne -> 179
/*     */     //   176: goto -> 184
/*     */     //   179: iload_1
/*     */     //   180: iconst_m1
/*     */     //   181: if_icmpne -> 57
/*     */     //   184: iconst_m1
/*     */     //   185: iload_1
/*     */     //   186: if_icmpeq -> 208
/*     */     //   189: aload_0
/*     */     //   190: dup
/*     */     //   191: getfield m_pos : I
/*     */     //   194: iconst_1
/*     */     //   195: iadd
/*     */     //   196: putfield m_pos : I
/*     */     //   199: iload_1
/*     */     //   200: istore #4
/*     */     //   202: jsr -> 230
/*     */     //   205: iload #4
/*     */     //   207: ireturn
/*     */     //   208: aload_0
/*     */     //   209: iconst_1
/*     */     //   210: putfield m_foundLast : Z
/*     */     //   213: iconst_m1
/*     */     //   214: istore #4
/*     */     //   216: jsr -> 230
/*     */     //   219: iload #4
/*     */     //   221: ireturn
/*     */     //   222: astore #5
/*     */     //   224: jsr -> 230
/*     */     //   227: aload #5
/*     */     //   229: athrow
/*     */     //   230: astore #6
/*     */     //   232: iconst_m1
/*     */     //   233: aload_0
/*     */     //   234: getfield m_stackFrame : I
/*     */     //   237: if_icmpeq -> 245
/*     */     //   240: aload_2
/*     */     //   241: iload_3
/*     */     //   242: invokevirtual setStackFrame : (I)V
/*     */     //   245: ret #6
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #175	-> 0
/*     */     //   #176	-> 7
/*     */     //   #178	-> 9
/*     */     //   #180	-> 17
/*     */     //   #187	-> 21
/*     */     //   #189	-> 29
/*     */     //   #192	-> 37
/*     */     //   #194	-> 42
/*     */     //   #199	-> 53
/*     */     //   #200	-> 55
/*     */     //   #207	-> 57
/*     */     //   #209	-> 65
/*     */     //   #215	-> 111
/*     */     //   #221	-> 162
/*     */     //   #223	-> 167
/*     */     //   #224	-> 176
/*     */     //   #231	-> 179
/*     */     //   #233	-> 184
/*     */     //   #235	-> 189
/*     */     //   #236	-> 199
/*     */     //   #240	-> 208
/*     */     //   #242	-> 213
/*     */     //   #247	-> 222
/*     */     //   #250	-> 240
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	247	0	this	Lorg/apache/xpath/axes/DescendantIterator;
/*     */     //   37	210	2	vars	Lorg/apache/xpath/VariableStack;
/*     */     //   42	205	3	savedStart	I
/*     */     //   108	139	1	next	I
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   57	222	222	finally
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 264 */     super.setRoot(context, environment);
/* 265 */     this.m_traverser = this.m_cdtm.getAxisTraverser(this.m_axis);
/*     */     
/* 267 */     String localName = getLocalName();
/* 268 */     String namespace = getNamespace();
/* 269 */     int what = this.m_whatToShow;
/*     */ 
/*     */     
/* 272 */     if (-1 == what || localName == "*" || namespace == "*") {
/*     */ 
/*     */ 
/*     */       
/* 276 */       this.m_extendedTypeID = 0;
/*     */     }
/*     */     else {
/*     */       
/* 280 */       int type = NodeTest.getNodeTypeTest(what);
/* 281 */       this.m_extendedTypeID = this.m_cdtm.getExpandedTypeID(namespace, localName, type);
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
/*     */   public int asNode(XPathContext xctxt) throws TransformerException {
/* 297 */     if (getPredicateCount() > 0) {
/* 298 */       return super.asNode(xctxt);
/*     */     }
/* 300 */     int current = xctxt.getCurrentNode();
/*     */     
/* 302 */     DTM dtm = xctxt.getDTM(current);
/* 303 */     DTMAxisTraverser traverser = dtm.getAxisTraverser(this.m_axis);
/*     */     
/* 305 */     String localName = getLocalName();
/* 306 */     String namespace = getNamespace();
/* 307 */     int what = this.m_whatToShow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 313 */     if (-1 == what || localName == "*" || namespace == "*")
/*     */     {
/*     */ 
/*     */       
/* 317 */       return traverser.first(current);
/*     */     }
/*     */ 
/*     */     
/* 321 */     int type = NodeTest.getNodeTypeTest(what);
/* 322 */     int extendedType = dtm.getExpandedTypeID(namespace, localName, type);
/* 323 */     return traverser.first(current, extendedType);
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
/*     */   public void detach() {
/* 336 */     if (this.m_allowDetach) {
/* 337 */       this.m_traverser = null;
/* 338 */       this.m_extendedTypeID = 0;
/*     */ 
/*     */       
/* 341 */       super.detach();
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
/*     */   public int getAxis() {
/* 353 */     return this.m_axis;
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
/*     */   public boolean deepEquals(Expression expr) {
/* 371 */     if (!super.deepEquals(expr)) {
/* 372 */       return false;
/*     */     }
/* 374 */     if (this.m_axis != ((DescendantIterator)expr).m_axis) {
/* 375 */       return false;
/*     */     }
/* 377 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/DescendantIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */