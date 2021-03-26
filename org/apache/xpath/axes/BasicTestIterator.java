/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.PrefixResolver;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BasicTestIterator
/*     */   extends LocPathIterator
/*     */ {
/*     */   protected BasicTestIterator() {}
/*     */   
/*     */   protected BasicTestIterator(PrefixResolver nscontext) {
/*  54 */     super(nscontext);
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
/*     */   protected BasicTestIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  72 */     super(compiler, opPos, analysis, false);
/*     */     
/*  74 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*  75 */     int whatToShow = compiler.getWhatToShow(firstStepPos);
/*     */     
/*  77 */     if (0 == (whatToShow & 0x1043) || whatToShow == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  83 */       initNodeTest(whatToShow);
/*     */     } else {
/*     */       
/*  86 */       initNodeTest(whatToShow, compiler.getStepNS(firstStepPos), compiler.getStepLocalName(firstStepPos));
/*     */     } 
/*     */     
/*  89 */     initPredicateInfo(compiler, firstStepPos);
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
/*     */   protected BasicTestIterator(Compiler compiler, int opPos, int analysis, boolean shouldLoadWalkers) throws TransformerException {
/* 111 */     super(compiler, opPos, analysis, shouldLoadWalkers);
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
/*     */   protected abstract int getNextNode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */     //   4: ifeq -> 14
/*     */     //   7: aload_0
/*     */     //   8: iconst_m1
/*     */     //   9: putfield m_lastFetched : I
/*     */     //   12: iconst_m1
/*     */     //   13: ireturn
/*     */     //   14: iconst_m1
/*     */     //   15: aload_0
/*     */     //   16: getfield m_lastFetched : I
/*     */     //   19: if_icmpne -> 26
/*     */     //   22: aload_0
/*     */     //   23: invokevirtual resetProximityPositions : ()V
/*     */     //   26: iconst_m1
/*     */     //   27: aload_0
/*     */     //   28: getfield m_stackFrame : I
/*     */     //   31: if_icmpeq -> 58
/*     */     //   34: aload_0
/*     */     //   35: getfield m_execContext : Lorg/apache/xpath/XPathContext;
/*     */     //   38: invokevirtual getVarStack : ()Lorg/apache/xpath/VariableStack;
/*     */     //   41: astore_2
/*     */     //   42: aload_2
/*     */     //   43: invokevirtual getStackFrame : ()I
/*     */     //   46: istore_3
/*     */     //   47: aload_2
/*     */     //   48: aload_0
/*     */     //   49: getfield m_stackFrame : I
/*     */     //   52: invokevirtual setStackFrame : (I)V
/*     */     //   55: goto -> 62
/*     */     //   58: aconst_null
/*     */     //   59: astore_2
/*     */     //   60: iconst_0
/*     */     //   61: istore_3
/*     */     //   62: aload_0
/*     */     //   63: invokevirtual getNextNode : ()I
/*     */     //   66: istore_1
/*     */     //   67: iconst_m1
/*     */     //   68: iload_1
/*     */     //   69: if_icmpeq -> 89
/*     */     //   72: iconst_1
/*     */     //   73: aload_0
/*     */     //   74: iload_1
/*     */     //   75: invokevirtual acceptNode : (I)S
/*     */     //   78: if_icmpne -> 84
/*     */     //   81: goto -> 89
/*     */     //   84: iload_1
/*     */     //   85: iconst_m1
/*     */     //   86: if_icmpne -> 62
/*     */     //   89: iconst_m1
/*     */     //   90: iload_1
/*     */     //   91: if_icmpeq -> 113
/*     */     //   94: aload_0
/*     */     //   95: dup
/*     */     //   96: getfield m_pos : I
/*     */     //   99: iconst_1
/*     */     //   100: iadd
/*     */     //   101: putfield m_pos : I
/*     */     //   104: iload_1
/*     */     //   105: istore #4
/*     */     //   107: jsr -> 135
/*     */     //   110: iload #4
/*     */     //   112: ireturn
/*     */     //   113: aload_0
/*     */     //   114: iconst_1
/*     */     //   115: putfield m_foundLast : Z
/*     */     //   118: iconst_m1
/*     */     //   119: istore #4
/*     */     //   121: jsr -> 135
/*     */     //   124: iload #4
/*     */     //   126: ireturn
/*     */     //   127: astore #5
/*     */     //   129: jsr -> 135
/*     */     //   132: aload #5
/*     */     //   134: athrow
/*     */     //   135: astore #6
/*     */     //   137: iconst_m1
/*     */     //   138: aload_0
/*     */     //   139: getfield m_stackFrame : I
/*     */     //   142: if_icmpeq -> 150
/*     */     //   145: aload_2
/*     */     //   146: iload_3
/*     */     //   147: invokevirtual setStackFrame : (I)V
/*     */     //   150: ret #6
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #131	-> 0
/*     */     //   #133	-> 7
/*     */     //   #134	-> 12
/*     */     //   #137	-> 14
/*     */     //   #139	-> 22
/*     */     //   #146	-> 26
/*     */     //   #148	-> 34
/*     */     //   #151	-> 42
/*     */     //   #153	-> 47
/*     */     //   #158	-> 58
/*     */     //   #159	-> 60
/*     */     //   #166	-> 62
/*     */     //   #168	-> 67
/*     */     //   #170	-> 72
/*     */     //   #171	-> 81
/*     */     //   #178	-> 84
/*     */     //   #180	-> 89
/*     */     //   #182	-> 94
/*     */     //   #183	-> 104
/*     */     //   #187	-> 113
/*     */     //   #189	-> 118
/*     */     //   #194	-> 127
/*     */     //   #197	-> 145
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	152	0	this	Lorg/apache/xpath/axes/BasicTestIterator;
/*     */     //   42	110	2	vars	Lorg/apache/xpath/VariableStack;
/*     */     //   47	105	3	savedStart	I
/*     */     //   67	85	1	next	I
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   62	127	127	finally
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
/*     */   public DTMIterator cloneWithReset() throws CloneNotSupportedException {
/* 213 */     ChildTestIterator clone = (ChildTestIterator)super.cloneWithReset();
/*     */     
/* 215 */     clone.resetProximityPositions();
/*     */     
/* 217 */     return clone;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/BasicTestIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */