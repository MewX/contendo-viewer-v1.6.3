/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
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
/*     */ public class OneStepIteratorForward
/*     */   extends ChildTestIterator
/*     */ {
/*  37 */   protected int m_axis = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   OneStepIteratorForward(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  51 */     super(compiler, opPos, analysis);
/*  52 */     int firstStepPos = OpMap.getFirstChildPos(opPos);
/*     */     
/*  54 */     this.m_axis = WalkerFactory.getAxisFromStep(compiler, firstStepPos);
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
/*     */   public OneStepIteratorForward(int axis) {
/*  67 */     super(null);
/*     */     
/*  69 */     this.m_axis = axis;
/*  70 */     int whatToShow = -1;
/*  71 */     initNodeTest(whatToShow);
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
/*     */   public void setRoot(int context, Object environment) {
/*  86 */     super.setRoot(context, environment);
/*  87 */     this.m_traverser = this.m_cdtm.getAxisTraverser(this.m_axis);
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
/*     */   protected int getNextNode() {
/* 136 */     this.m_lastFetched = (-1 == this.m_lastFetched) ? this.m_traverser.first(this.m_context) : this.m_traverser.next(this.m_context, this.m_lastFetched);
/*     */ 
/*     */     
/* 139 */     return this.m_lastFetched;
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
/* 150 */     return this.m_axis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 158 */     if (!super.deepEquals(expr)) {
/* 159 */       return false;
/*     */     }
/* 161 */     if (this.m_axis != ((OneStepIteratorForward)expr).m_axis) {
/* 162 */       return false;
/*     */     }
/* 164 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/OneStepIteratorForward.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */