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
/*     */ public class StepIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   protected DTMAxisIterator _source;
/*     */   protected DTMAxisIterator _iterator;
/*  55 */   private int _pos = -1;
/*     */   
/*     */   public StepIterator(DTMAxisIterator source, DTMAxisIterator iterator) {
/*  58 */     this._source = source;
/*  59 */     this._iterator = iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/*  66 */     this._isRestartable = isRestartable;
/*  67 */     this._source.setRestartable(isRestartable);
/*  68 */     this._iterator.setRestartable(true);
/*     */   }
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*  72 */     this._isRestartable = false;
/*     */     
/*  74 */     try { StepIterator clone = (StepIterator)clone();
/*  75 */       clone._source = this._source.cloneIterator();
/*  76 */       clone._iterator = this._iterator.cloneIterator();
/*  77 */       clone._iterator.setRestartable(true);
/*  78 */       clone._isRestartable = false;
/*  79 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*  82 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/*  84 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/*  89 */     if (this._isRestartable) {
/*     */       
/*  91 */       this._source.setStartNode(this._startNode = node);
/*     */ 
/*     */ 
/*     */       
/*  95 */       this._iterator.setStartNode(this._includeSelf ? this._startNode : this._source.next());
/*  96 */       return resetPosition();
/*     */     } 
/*  98 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/* 102 */     this._source.reset();
/*     */     
/* 104 */     this._iterator.setStartNode(this._includeSelf ? this._startNode : this._source.next());
/* 105 */     return resetPosition();
/*     */   }
/*     */   
/*     */   public int next() {
/*     */     while (true) {
/*     */       int node;
/* 111 */       if ((node = this._iterator.next()) != -1) {
/* 112 */         return returnNode(node);
/*     */       }
/*     */       
/* 115 */       if ((node = this._source.next()) == -1) {
/* 116 */         return -1;
/*     */       }
/*     */ 
/*     */       
/* 120 */       this._iterator.setStartNode(node);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMark() {
/* 126 */     this._source.setMark();
/* 127 */     this._iterator.setMark();
/*     */   }
/*     */ 
/*     */   
/*     */   public void gotoMark() {
/* 132 */     this._source.gotoMark();
/* 133 */     this._iterator.gotoMark();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/StepIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */