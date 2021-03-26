/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.compiler.Compiler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChildIterator
/*     */   extends LocPathIterator
/*     */ {
/*     */   ChildIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  48 */     super(compiler, opPos, analysis, false);
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
/*     */   public int asNode(XPathContext xctxt) throws TransformerException {
/*  62 */     int current = xctxt.getCurrentNode();
/*     */     
/*  64 */     DTM dtm = xctxt.getDTM(current);
/*     */     
/*  66 */     return dtm.getFirstChild(current);
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
/*     */   public int nextNode() {
/*  79 */     if (this.m_foundLast) {
/*  80 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*  84 */     int next = (-1 == this.m_lastFetched) ? this.m_cdtm.getFirstChild(this.m_context) : this.m_cdtm.getNextSibling(this.m_lastFetched);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     if (-1 != next) {
/*     */       
/*  91 */       this.m_pos++;
/*  92 */       return next;
/*     */     } 
/*     */ 
/*     */     
/*  96 */     this.m_foundLast = true;
/*     */     
/*  98 */     return -1;
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
/*     */   public int getAxis() {
/* 110 */     return 3;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/ChildIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */