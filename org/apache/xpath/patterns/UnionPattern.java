/*     */ package org.apache.xpath.patterns;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.objects.XNumber;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UnionPattern
/*     */   extends Expression
/*     */ {
/*     */   private StepPattern[] m_patterns;
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/*  44 */     for (int i = 0; i < this.m_patterns.length; i++)
/*     */     {
/*  46 */       this.m_patterns[i].fixupVariables(vars, globalsSize);
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
/*     */   public boolean canTraverseOutsideSubtree() {
/*  59 */     if (null != this.m_patterns) {
/*     */       
/*  61 */       int n = this.m_patterns.length;
/*  62 */       for (int i = 0; i < n; i++) {
/*     */         
/*  64 */         if (this.m_patterns[i].canTraverseOutsideSubtree())
/*  65 */           return true; 
/*     */       } 
/*     */     } 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPatterns(StepPattern[] patterns) {
/*  79 */     this.m_patterns = patterns;
/*  80 */     if (null != patterns)
/*     */     {
/*  82 */       for (int i = 0; i < patterns.length; i++)
/*     */       {
/*  84 */         patterns[i].exprSetParent((ExpressionNode)this);
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
/*     */   public StepPattern[] getPatterns() {
/*  98 */     return this.m_patterns;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*     */     XNumber xNumber;
/* 117 */     XObject bestScore = null;
/* 118 */     int n = this.m_patterns.length;
/*     */     
/* 120 */     for (int i = 0; i < n; i++) {
/*     */       
/* 122 */       XObject score = this.m_patterns[i].execute(xctxt);
/*     */       
/* 124 */       if (score != NodeTest.SCORE_NONE)
/*     */       {
/* 126 */         if (null == bestScore) {
/* 127 */           bestScore = score;
/* 128 */         } else if (score.num() > bestScore.num()) {
/* 129 */           bestScore = score;
/*     */         } 
/*     */       }
/*     */     } 
/* 133 */     if (null == bestScore)
/*     */     {
/* 135 */       xNumber = NodeTest.SCORE_NONE;
/*     */     }
/*     */     
/* 138 */     return (XObject)xNumber;
/*     */   }
/*     */   
/*     */   class UnionPathPartOwner implements ExpressionOwner {
/*     */     int m_index;
/*     */     private final UnionPattern this$0;
/*     */     
/*     */     UnionPathPartOwner(UnionPattern this$0, int index) {
/* 146 */       this.this$0 = this$0;
/* 147 */       this.m_index = index;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Expression getExpression() {
/* 155 */       return this.this$0.m_patterns[this.m_index];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setExpression(Expression exp) {
/* 164 */       exp.exprSetParent((ExpressionNode)this.this$0);
/* 165 */       this.this$0.m_patterns[this.m_index] = (StepPattern)exp;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 174 */     visitor.visitUnionPattern(owner, this);
/* 175 */     if (null != this.m_patterns) {
/*     */       
/* 177 */       int n = this.m_patterns.length;
/* 178 */       for (int i = 0; i < n; i++)
/*     */       {
/* 180 */         this.m_patterns[i].callVisitors(new UnionPathPartOwner(this, i), visitor);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 190 */     if (!isSameClass(expr)) {
/* 191 */       return false;
/*     */     }
/* 193 */     UnionPattern up = (UnionPattern)expr;
/*     */     
/* 195 */     if (null != this.m_patterns) {
/*     */       
/* 197 */       int n = this.m_patterns.length;
/* 198 */       if (null == up.m_patterns || up.m_patterns.length != n) {
/* 199 */         return false;
/*     */       }
/* 201 */       for (int i = 0; i < n; i++) {
/*     */         
/* 203 */         if (!this.m_patterns[i].deepEquals(up.m_patterns[i])) {
/* 204 */           return false;
/*     */         }
/*     */       } 
/* 207 */     } else if (up.m_patterns != null) {
/* 208 */       return false;
/*     */     } 
/* 210 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/patterns/UnionPattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */