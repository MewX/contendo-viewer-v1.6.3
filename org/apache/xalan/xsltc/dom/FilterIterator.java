/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.DTMFilter;
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
/*     */ public final class FilterIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private DTMAxisIterator _source;
/*     */   private final DTMFilter _filter;
/*     */   private final boolean _isReverse;
/*     */   
/*     */   public FilterIterator(DTMAxisIterator source, DTMFilter filter) {
/*  54 */     this._source = source;
/*     */     
/*  56 */     this._filter = filter;
/*  57 */     this._isReverse = source.isReverse();
/*     */   }
/*     */   
/*     */   public boolean isReverse() {
/*  61 */     return this._isReverse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/*  66 */     this._isRestartable = isRestartable;
/*  67 */     this._source.setRestartable(isRestartable);
/*     */   }
/*     */ 
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/*  73 */     try { FilterIterator clone = (FilterIterator)clone();
/*  74 */       clone._source = this._source.cloneIterator();
/*  75 */       clone._isRestartable = false;
/*  76 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*  79 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/*  81 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/*  86 */     this._source.reset();
/*  87 */     return resetPosition();
/*     */   }
/*     */   
/*     */   public int next() {
/*     */     int node;
/*  92 */     while ((node = this._source.next()) != -1) {
/*  93 */       if (this._filter.acceptNode(node, -1) == 1) {
/*  94 */         return returnNode(node);
/*     */       }
/*     */     } 
/*  97 */     return -1;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/* 101 */     if (this._isRestartable) {
/* 102 */       this._source.setStartNode(this._startNode = node);
/* 103 */       return resetPosition();
/*     */     } 
/* 105 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 109 */     this._source.setMark();
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 113 */     this._source.gotoMark();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/FilterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */