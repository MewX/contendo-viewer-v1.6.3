/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ import org.apache.xalan.xsltc.util.IntegerArray;
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
/*     */ public final class DupFilterIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private DTMAxisIterator _source;
/*  44 */   private IntegerArray _nodes = new IntegerArray();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private int _current = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private int _nodesSize = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private int _lastNext = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private int _markedLastNext = -1;
/*     */   
/*     */   public DupFilterIterator(DTMAxisIterator source) {
/*  67 */     this._source = source;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (source instanceof KeyIndex) {
/*  74 */       setStartNode(0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/*  84 */     if (this._isRestartable) {
/*     */ 
/*     */       
/*  87 */       if (this._source instanceof KeyIndex && this._startNode == 0)
/*     */       {
/*  89 */         return (DTMAxisIterator)this;
/*     */       }
/*     */       
/*  92 */       if (node != this._startNode) {
/*  93 */         this._source.setStartNode(this._startNode = node);
/*     */         
/*  95 */         this._nodes.clear();
/*  96 */         while ((node = this._source.next()) != -1) {
/*  97 */           this._nodes.add(node);
/*     */         }
/*  99 */         this._nodes.sort();
/* 100 */         this._nodesSize = this._nodes.cardinality();
/* 101 */         this._current = 0;
/* 102 */         this._lastNext = -1;
/* 103 */         resetPosition();
/*     */       } 
/*     */     } 
/* 106 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public int next() {
/* 110 */     while (this._current < this._nodesSize) {
/* 111 */       int next = this._nodes.at(this._current++);
/* 112 */       if (next != this._lastNext) {
/* 113 */         return returnNode(this._lastNext = next);
/*     */       }
/*     */     } 
/* 116 */     return -1;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/* 121 */     try { DupFilterIterator clone = (DupFilterIterator)clone();
/*     */       
/* 123 */       clone._nodes = (IntegerArray)this._nodes.clone();
/* 124 */       clone._source = this._source.cloneIterator();
/* 125 */       clone._isRestartable = false;
/* 126 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/* 129 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/* 131 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/* 136 */     this._isRestartable = isRestartable;
/* 137 */     this._source.setRestartable(isRestartable);
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 141 */     this._markedNode = this._current;
/* 142 */     this._markedLastNext = this._lastNext;
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 146 */     this._current = this._markedNode;
/* 147 */     this._lastNext = this._markedLastNext;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/* 151 */     this._current = 0;
/* 152 */     this._lastNext = -1;
/* 153 */     return resetPosition();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/DupFilterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */