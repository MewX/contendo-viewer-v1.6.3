/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ForwardPositionIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private DTMAxisIterator _source;
/*     */   
/*     */   public ForwardPositionIterator(DTMAxisIterator source) {
/*  66 */     this._source = source;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/*  71 */     try { ForwardPositionIterator clone = (ForwardPositionIterator)clone();
/*     */       
/*  73 */       clone._source = this._source.cloneIterator();
/*  74 */       clone._isRestartable = false;
/*  75 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*  78 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/*  80 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public int next() {
/*  85 */     return returnNode(this._source.next());
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/*  89 */     this._source.setStartNode(node);
/*  90 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/*  94 */     this._source.reset();
/*  95 */     return resetPosition();
/*     */   }
/*     */   
/*     */   public void setMark() {
/*  99 */     this._source.setMark();
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 103 */     this._source.gotoMark();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/ForwardPositionIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */