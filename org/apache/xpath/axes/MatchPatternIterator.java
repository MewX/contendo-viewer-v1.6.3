/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMAxisTraverser;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ import org.apache.xpath.compiler.OpMap;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.patterns.NodeTest;
/*     */ import org.apache.xpath.patterns.StepPattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MatchPatternIterator
/*     */   extends LocPathIterator
/*     */ {
/*     */   protected StepPattern m_pattern;
/*  46 */   protected int m_superAxis = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DTMAxisTraverser m_traverser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean DEBUG = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MatchPatternIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  74 */     super(compiler, opPos, analysis, false);
/*     */     
/*  76 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*     */     
/*  78 */     this.m_pattern = WalkerFactory.loadSteps(this, compiler, firstStepPos, 0);
/*     */     
/*  80 */     boolean fromRoot = false;
/*  81 */     boolean walkBack = false;
/*  82 */     boolean walkDescendants = false;
/*  83 */     boolean walkAttributes = false;
/*     */     
/*  85 */     if (0 != (analysis & 0x28000000))
/*     */     {
/*  87 */       fromRoot = true;
/*     */     }
/*  89 */     if (0 != (analysis & 0x5D86000))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  97 */       walkBack = true;
/*     */     }
/*  99 */     if (0 != (analysis & 0x70000))
/*     */     {
/*     */ 
/*     */       
/* 103 */       walkDescendants = true;
/*     */     }
/* 105 */     if (0 != (analysis & 0x208000))
/*     */     {
/* 107 */       walkAttributes = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (fromRoot || walkBack) {
/*     */       
/* 117 */       if (walkAttributes)
/*     */       {
/* 119 */         this.m_superAxis = 16;
/*     */       }
/*     */       else
/*     */       {
/* 123 */         this.m_superAxis = 17;
/*     */       }
/*     */     
/* 126 */     } else if (walkDescendants) {
/*     */       
/* 128 */       if (walkAttributes)
/*     */       {
/* 130 */         this.m_superAxis = 14;
/*     */       }
/*     */       else
/*     */       {
/* 134 */         this.m_superAxis = 5;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 139 */       this.m_superAxis = 16;
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
/*     */   public void setRoot(int context, Object environment) {
/* 158 */     super.setRoot(context, environment);
/* 159 */     this.m_traverser = this.m_cdtm.getAxisTraverser(this.m_superAxis);
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
/*     */   public void detach() {
/* 171 */     if (this.m_allowDetach) {
/*     */       
/* 173 */       this.m_traverser = null;
/*     */ 
/*     */       
/* 176 */       super.detach();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getNextNode() {
/* 186 */     this.m_lastFetched = (-1 == this.m_lastFetched) ? this.m_traverser.first(this.m_context) : this.m_traverser.next(this.m_context, this.m_lastFetched);
/*     */ 
/*     */     
/* 189 */     return this.m_lastFetched;
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
/*     */   public int nextNode() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield m_foundLast : Z
/*     */     //   4: ifeq -> 9
/*     */     //   7: iconst_m1
/*     */     //   8: ireturn
/*     */     //   9: iconst_m1
/*     */     //   10: aload_0
/*     */     //   11: getfield m_stackFrame : I
/*     */     //   14: if_icmpeq -> 41
/*     */     //   17: aload_0
/*     */     //   18: getfield m_execContext : Lorg/apache/xpath/XPathContext;
/*     */     //   21: invokevirtual getVarStack : ()Lorg/apache/xpath/VariableStack;
/*     */     //   24: astore_2
/*     */     //   25: aload_2
/*     */     //   26: invokevirtual getStackFrame : ()I
/*     */     //   29: istore_3
/*     */     //   30: aload_2
/*     */     //   31: aload_0
/*     */     //   32: getfield m_stackFrame : I
/*     */     //   35: invokevirtual setStackFrame : (I)V
/*     */     //   38: goto -> 45
/*     */     //   41: aconst_null
/*     */     //   42: astore_2
/*     */     //   43: iconst_0
/*     */     //   44: istore_3
/*     */     //   45: aload_0
/*     */     //   46: invokevirtual getNextNode : ()I
/*     */     //   49: istore_1
/*     */     //   50: iconst_m1
/*     */     //   51: iload_1
/*     */     //   52: if_icmpeq -> 76
/*     */     //   55: iconst_1
/*     */     //   56: aload_0
/*     */     //   57: iload_1
/*     */     //   58: aload_0
/*     */     //   59: getfield m_execContext : Lorg/apache/xpath/XPathContext;
/*     */     //   62: invokevirtual acceptNode : (ILorg/apache/xpath/XPathContext;)S
/*     */     //   65: if_icmpne -> 71
/*     */     //   68: goto -> 76
/*     */     //   71: iload_1
/*     */     //   72: iconst_m1
/*     */     //   73: if_icmpne -> 45
/*     */     //   76: iconst_m1
/*     */     //   77: iload_1
/*     */     //   78: if_icmpeq -> 94
/*     */     //   81: aload_0
/*     */     //   82: invokevirtual incrementCurrentPos : ()V
/*     */     //   85: iload_1
/*     */     //   86: istore #4
/*     */     //   88: jsr -> 116
/*     */     //   91: iload #4
/*     */     //   93: ireturn
/*     */     //   94: aload_0
/*     */     //   95: iconst_1
/*     */     //   96: putfield m_foundLast : Z
/*     */     //   99: iconst_m1
/*     */     //   100: istore #4
/*     */     //   102: jsr -> 116
/*     */     //   105: iload #4
/*     */     //   107: ireturn
/*     */     //   108: astore #5
/*     */     //   110: jsr -> 116
/*     */     //   113: aload #5
/*     */     //   115: athrow
/*     */     //   116: astore #6
/*     */     //   118: iconst_m1
/*     */     //   119: aload_0
/*     */     //   120: getfield m_stackFrame : I
/*     */     //   123: if_icmpeq -> 131
/*     */     //   126: aload_2
/*     */     //   127: iload_3
/*     */     //   128: invokevirtual setStackFrame : (I)V
/*     */     //   131: ret #6
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #201	-> 0
/*     */     //   #202	-> 7
/*     */     //   #208	-> 9
/*     */     //   #210	-> 17
/*     */     //   #213	-> 25
/*     */     //   #215	-> 30
/*     */     //   #220	-> 41
/*     */     //   #221	-> 43
/*     */     //   #231	-> 45
/*     */     //   #233	-> 50
/*     */     //   #235	-> 55
/*     */     //   #236	-> 68
/*     */     //   #243	-> 71
/*     */     //   #245	-> 76
/*     */     //   #252	-> 81
/*     */     //   #254	-> 85
/*     */     //   #258	-> 94
/*     */     //   #260	-> 99
/*     */     //   #265	-> 108
/*     */     //   #268	-> 126
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	133	0	this	Lorg/apache/xpath/axes/MatchPatternIterator;
/*     */     //   25	108	2	vars	Lorg/apache/xpath/VariableStack;
/*     */     //   30	103	3	savedStart	I
/*     */     //   50	83	1	next	I
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   45	108	108	finally
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
/*     */   public short acceptNode(int n, XPathContext xctxt) {
/*     */     
/* 288 */     try { xctxt.pushCurrentNode(n);
/* 289 */       xctxt.pushIteratorRoot(this.m_context);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 300 */       XObject score = this.m_pattern.execute(xctxt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 310 */       return (score == NodeTest.SCORE_NONE) ? 3 : 1; } catch (TransformerException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 317 */       throw new RuntimeException(se.getMessage()); }
/*     */     
/*     */     finally
/*     */     
/* 321 */     { xctxt.popCurrentNode();
/* 322 */       xctxt.popIteratorRoot(); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/MatchPatternIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */