/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ public class UnionChildIterator
/*     */   extends ChildTestIterator
/*     */ {
/*  39 */   private PredicatedNodeTest[] m_nodeTests = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UnionChildIterator() {
/*  46 */     super(null);
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
/*     */   public void addNodeTest(PredicatedNodeTest test) {
/*  62 */     if (null == this.m_nodeTests) {
/*     */       
/*  64 */       this.m_nodeTests = new PredicatedNodeTest[1];
/*  65 */       this.m_nodeTests[0] = test;
/*     */     }
/*     */     else {
/*     */       
/*  69 */       PredicatedNodeTest[] tests = this.m_nodeTests;
/*  70 */       int len = this.m_nodeTests.length;
/*     */       
/*  72 */       this.m_nodeTests = new PredicatedNodeTest[len + 1];
/*     */       
/*  74 */       System.arraycopy(tests, 0, this.m_nodeTests, 0, len);
/*     */       
/*  76 */       this.m_nodeTests[len] = test;
/*     */     } 
/*  78 */     test.exprSetParent((ExpressionNode)this);
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
/*  93 */     super.fixupVariables(vars, globalsSize);
/*  94 */     if (this.m_nodeTests != null) {
/*  95 */       for (int i = 0; i < this.m_nodeTests.length; i++) {
/*  96 */         this.m_nodeTests[i].fixupVariables(vars, globalsSize);
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
/*     */   public short acceptNode(int n) {
/* 112 */     XPathContext xctxt = getXPathContext();
/*     */     
/*     */     try {
/* 115 */       xctxt.pushCurrentNode(n);
/* 116 */       for (int i = 0; i < this.m_nodeTests.length; i++)
/*     */       {
/* 118 */         PredicatedNodeTest pnt = this.m_nodeTests[i];
/* 119 */         XObject score = pnt.execute(xctxt, n);
/* 120 */         if (score != NodeTest.SCORE_NONE)
/*     */         {
/*     */           
/* 123 */           if (pnt.getPredicateCount() > 0) {
/*     */             
/* 125 */             if (pnt.executePredicates(n, xctxt)) {
/* 126 */               return 1;
/*     */             }
/*     */           } else {
/* 129 */             return 1;
/*     */           }
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/*     */     catch (TransformerException se) {
/*     */       
/* 138 */       throw new RuntimeException(se.getMessage());
/*     */     }
/*     */     finally {
/*     */       
/* 142 */       xctxt.popCurrentNode();
/*     */     } 
/* 144 */     return 3;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/UnionChildIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */