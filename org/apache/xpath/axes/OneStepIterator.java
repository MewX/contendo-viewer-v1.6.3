/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ public class OneStepIterator
/*     */   extends ChildTestIterator
/*     */ {
/*  38 */   protected int m_axis = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DTMAxisIterator m_iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   OneStepIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  55 */     super(compiler, opPos, analysis);
/*  56 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*     */     
/*  58 */     this.m_axis = WalkerFactory.getAxisFromStep(compiler, firstStepPos);
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
/*     */   public OneStepIterator(DTMAxisIterator iterator, int axis) throws TransformerException {
/*  74 */     super(null);
/*     */     
/*  76 */     this.m_iterator = iterator;
/*  77 */     this.m_axis = axis;
/*  78 */     int whatToShow = -1;
/*  79 */     initNodeTest(whatToShow);
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
/*  91 */     super.setRoot(context, environment);
/*  92 */     if (this.m_axis > -1)
/*  93 */       this.m_iterator = this.m_cdtm.getAxisIterator(this.m_axis); 
/*  94 */     this.m_iterator.setStartNode(this.m_context);
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
/* 106 */     if (this.m_allowDetach) {
/*     */       
/* 108 */       if (this.m_axis > -1) {
/* 109 */         this.m_iterator = null;
/*     */       }
/*     */       
/* 112 */       super.detach();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getNextNode() {
/* 121 */     return this.m_lastFetched = this.m_iterator.next();
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
/* 135 */     OneStepIterator clone = (OneStepIterator)super.clone();
/*     */     
/* 137 */     if (this.m_iterator != null)
/*     */     {
/* 139 */       clone.m_iterator = this.m_iterator.cloneIterator();
/*     */     }
/* 141 */     return clone;
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
/*     */   public DTMIterator cloneWithReset() throws CloneNotSupportedException {
/* 155 */     OneStepIterator clone = (OneStepIterator)super.cloneWithReset();
/* 156 */     clone.m_iterator = this.m_iterator;
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
/*     */   public boolean isReverseAxes() {
/* 170 */     return this.m_iterator.isReverse();
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
/*     */   protected int getProximityPosition(int predicateIndex) {
/* 186 */     if (!isReverseAxes()) {
/* 187 */       return super.getProximityPosition(predicateIndex);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 192 */     if (predicateIndex < 0) {
/* 193 */       return -1;
/*     */     }
/* 195 */     if (this.m_proximityPositions[predicateIndex] <= 0) {
/*     */       
/* 197 */       XPathContext xctxt = getXPathContext();
/*     */ 
/*     */       
/* 200 */       try { OneStepIterator clone = (OneStepIterator)clone();
/*     */         
/* 202 */         int root = getRoot();
/* 203 */         xctxt.pushCurrentNode(root);
/* 204 */         clone.setRoot(root, xctxt);
/*     */ 
/*     */         
/* 207 */         clone.m_predCount = predicateIndex;
/*     */ 
/*     */         
/* 210 */         int count = 1;
/*     */         
/*     */         int next;
/* 213 */         while (-1 != (next = clone.nextNode()))
/*     */         {
/* 215 */           count++;
/*     */         }
/*     */         
/* 218 */         this.m_proximityPositions[predicateIndex] = this.m_proximityPositions[predicateIndex] + count; } catch (CloneNotSupportedException cnse)
/*     */       
/*     */       { 
/*     */         
/*     */          }
/*     */       
/*     */       finally
/*     */       
/*     */       { 
/* 227 */         xctxt.popCurrentNode(); }
/*     */     
/*     */     } 
/*     */     
/* 231 */     return this.m_proximityPositions[predicateIndex];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 242 */     if (!isReverseAxes()) {
/* 243 */       return super.getLength();
/*     */     }
/*     */     
/* 246 */     boolean isPredicateTest = (this == this.m_execContext.getSubContextList());
/*     */ 
/*     */     
/* 249 */     int predCount = getPredicateCount();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 254 */     if (-1 != this.m_length && isPredicateTest && this.m_predicateIndex < 1) {
/* 255 */       return this.m_length;
/*     */     }
/* 257 */     int count = 0;
/*     */     
/* 259 */     XPathContext xctxt = getXPathContext();
/*     */     
/*     */     try {
/* 262 */       OneStepIterator clone = (OneStepIterator)cloneWithReset();
/*     */       
/* 264 */       int root = getRoot();
/* 265 */       xctxt.pushCurrentNode(root);
/* 266 */       clone.setRoot(root, xctxt);
/*     */       
/* 268 */       clone.m_predCount = this.m_predicateIndex;
/*     */       
/*     */       int next;
/*     */       
/* 272 */       while (-1 != (next = clone.nextNode()))
/*     */       {
/* 274 */         count++;
/*     */       }
/*     */     }
/*     */     catch (CloneNotSupportedException cnse) {
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 283 */       xctxt.popCurrentNode();
/*     */     } 
/* 285 */     if (isPredicateTest && this.m_predicateIndex < 1) {
/* 286 */       this.m_length = count;
/*     */     }
/* 288 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void countProximityPosition(int i) {
/* 298 */     if (!isReverseAxes()) {
/* 299 */       super.countProximityPosition(i);
/* 300 */     } else if (i < this.m_proximityPositions.length) {
/* 301 */       this.m_proximityPositions[i] = this.m_proximityPositions[i] - 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 310 */     super.reset();
/* 311 */     if (null != this.m_iterator) {
/* 312 */       this.m_iterator.reset();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/* 323 */     return this.m_axis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 331 */     if (!super.deepEquals(expr)) {
/* 332 */       return false;
/*     */     }
/* 334 */     if (this.m_axis != ((OneStepIterator)expr).m_axis) {
/* 335 */       return false;
/*     */     }
/* 337 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/OneStepIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */