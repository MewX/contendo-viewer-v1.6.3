/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.VariableStack;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WalkingIterator
/*     */   extends LocPathIterator
/*     */   implements ExpressionOwner
/*     */ {
/*     */   protected AxesWalker m_lastUsedWalker;
/*     */   protected AxesWalker m_firstWalker;
/*     */   
/*     */   WalkingIterator(Compiler compiler, int opPos, int analysis, boolean shouldLoadWalkers) throws TransformerException {
/*  54 */     super(compiler, opPos, analysis, shouldLoadWalkers);
/*     */     
/*  56 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*     */     
/*  58 */     if (shouldLoadWalkers) {
/*     */       
/*  60 */       this.m_firstWalker = WalkerFactory.loadWalkers(this, compiler, firstStepPos, 0);
/*  61 */       this.m_lastUsedWalker = this.m_firstWalker;
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
/*     */   public WalkingIterator(PrefixResolver nscontext) {
/*  74 */     super(nscontext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/*  84 */     int bits = 0;
/*  85 */     if (null != this.m_firstWalker) {
/*     */       
/*  87 */       AxesWalker walker = this.m_firstWalker;
/*     */       
/*  89 */       while (null != walker) {
/*     */         
/*  91 */         int bit = walker.getAnalysisBits();
/*  92 */         bits |= bit;
/*  93 */         walker = walker.getNextWalker();
/*     */       } 
/*     */     } 
/*  96 */     return bits;
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
/* 110 */     WalkingIterator clone = (WalkingIterator)super.clone();
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (null != this.m_firstWalker)
/*     */     {
/* 116 */       clone.m_firstWalker = this.m_firstWalker.cloneDeep(clone, null);
/*     */     }
/*     */     
/* 119 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 128 */     super.reset();
/*     */     
/* 130 */     if (null != this.m_firstWalker) {
/*     */       
/* 132 */       this.m_lastUsedWalker = this.m_firstWalker;
/*     */       
/* 134 */       this.m_firstWalker.setRoot(this.m_context);
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
/*     */   public void setRoot(int context, Object environment) {
/* 149 */     super.setRoot(context, environment);
/*     */     
/* 151 */     if (null != this.m_firstWalker) {
/*     */       
/* 153 */       this.m_firstWalker.setRoot(context);
/* 154 */       this.m_lastUsedWalker = this.m_firstWalker;
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
/*     */   public int nextNode() {
/* 167 */     if (this.m_foundLast) {
/* 168 */       return -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (-1 == this.m_stackFrame)
/*     */     {
/* 180 */       return returnNextNode(this.m_firstWalker.nextNode());
/*     */     }
/*     */ 
/*     */     
/* 184 */     VariableStack vars = this.m_execContext.getVarStack();
/*     */ 
/*     */     
/* 187 */     int savedStart = vars.getStackFrame();
/*     */     
/* 189 */     vars.setStackFrame(this.m_stackFrame);
/*     */     
/* 191 */     int n = returnNextNode(this.m_firstWalker.nextNode());
/*     */ 
/*     */     
/* 194 */     vars.setStackFrame(savedStart);
/*     */     
/* 196 */     return n;
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
/*     */   public final AxesWalker getFirstWalker() {
/* 210 */     return this.m_firstWalker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setFirstWalker(AxesWalker walker) {
/* 221 */     this.m_firstWalker = walker;
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
/*     */   public final void setLastUsedWalker(AxesWalker walker) {
/* 233 */     this.m_lastUsedWalker = walker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final AxesWalker getLastUsedWalker() {
/* 244 */     return this.m_lastUsedWalker;
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
/* 256 */     if (this.m_allowDetach) {
/*     */       
/* 258 */       AxesWalker walker = this.m_firstWalker;
/* 259 */       while (null != walker) {
/*     */         
/* 261 */         walker.detach();
/* 262 */         walker = walker.getNextWalker();
/*     */       } 
/*     */       
/* 265 */       this.m_lastUsedWalker = null;
/*     */ 
/*     */       
/* 268 */       super.detach();
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
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/* 284 */     this.m_predicateIndex = -1;
/*     */     
/* 286 */     AxesWalker walker = this.m_firstWalker;
/*     */     
/* 288 */     while (null != walker) {
/*     */       
/* 290 */       walker.fixupVariables(vars, globalsSize);
/* 291 */       walker = walker.getNextWalker();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 300 */     if (visitor.visitLocationPath(owner, this))
/*     */     {
/* 302 */       if (null != this.m_firstWalker)
/*     */       {
/* 304 */         this.m_firstWalker.callVisitors(this, visitor);
/*     */       }
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
/*     */   public Expression getExpression() {
/* 323 */     return (Expression)this.m_firstWalker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(Expression exp) {
/* 331 */     exp.exprSetParent((ExpressionNode)this);
/* 332 */     this.m_firstWalker = (AxesWalker)exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 340 */     if (!super.deepEquals(expr)) {
/* 341 */       return false;
/*     */     }
/* 343 */     AxesWalker walker1 = this.m_firstWalker;
/* 344 */     AxesWalker walker2 = ((WalkingIterator)expr).m_firstWalker;
/* 345 */     while (null != walker1 && null != walker2) {
/*     */       
/* 347 */       if (!walker1.deepEquals((Expression)walker2))
/* 348 */         return false; 
/* 349 */       walker1 = walker1.getNextWalker();
/* 350 */       walker2 = walker2.getNextWalker();
/*     */     } 
/*     */     
/* 353 */     if (null != walker1 || null != walker2) {
/* 354 */       return false;
/*     */     }
/* 356 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/WalkingIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */