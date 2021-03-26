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
/*     */ public class SelfIteratorNoPredicate
/*     */   extends LocPathIterator
/*     */ {
/*     */   SelfIteratorNoPredicate(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*  47 */     super(compiler, opPos, analysis, false);
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
/*     */   public SelfIteratorNoPredicate() throws TransformerException {
/*  63 */     super(null);
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
/*     */   public int nextNode() {
/*  77 */     if (this.m_foundLast) {
/*  78 */       return -1;
/*     */     }
/*     */     
/*  81 */     DTM dtm = this.m_cdtm;
/*     */     
/*  83 */     int next = (-1 == this.m_lastFetched) ? this.m_context : -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (-1 != next) {
/*     */       
/*  90 */       this.m_pos++;
/*     */       
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int asNode(XPathContext xctxt) throws TransformerException {
/* 113 */     return xctxt.getCurrentNode();
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
/*     */   public int getLastPos(XPathContext xctxt) {
/* 126 */     return 1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/SelfIteratorNoPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */