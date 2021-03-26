/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemNumber;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Counter
/*     */ {
/*     */   static final int MAXCOUNTNODES = 500;
/*  50 */   int m_countNodesStartCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   NodeSetDTM m_countNodes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   int m_fromNode = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ElemNumber m_numberElem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_countResult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Counter(ElemNumber numberElem, NodeSetDTM countNodes) throws TransformerException {
/*  86 */     this.m_countNodes = countNodes;
/*  87 */     this.m_numberElem = numberElem;
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
/*     */   int getPreviouslyCounted(XPathContext support, int node) {
/* 114 */     int n = this.m_countNodes.size();
/*     */     
/* 116 */     this.m_countResult = 0;
/*     */     
/* 118 */     for (int i = n - 1; i >= 0; i--) {
/*     */       
/* 120 */       int countedNode = this.m_countNodes.elementAt(i);
/*     */       
/* 122 */       if (node == countedNode) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         this.m_countResult = i + 1 + this.m_countNodesStartCount;
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 132 */       DTM dtm = support.getDTM(countedNode);
/*     */ 
/*     */ 
/*     */       
/* 136 */       if (dtm.isNodeAfter(countedNode, node)) {
/*     */         break;
/*     */       }
/*     */     } 
/* 140 */     return this.m_countResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getLast() {
/* 151 */     int size = this.m_countNodes.size();
/*     */     
/* 153 */     return (size > 0) ? this.m_countNodes.elementAt(size - 1) : -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/Counter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */