/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CurrentNodeListIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private boolean _docOrder;
/*     */   private DTMAxisIterator _source;
/*     */   private final CurrentNodeListFilter _filter;
/*  61 */   private IntegerArray _nodes = new IntegerArray();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int _currentIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int _currentNode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractTranslet _translet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CurrentNodeListIterator(DTMAxisIterator source, CurrentNodeListFilter filter, int currentNode, AbstractTranslet translet) {
/*  83 */     this(source, !source.isReverse(), filter, currentNode, translet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CurrentNodeListIterator(DTMAxisIterator source, boolean docOrder, CurrentNodeListFilter filter, int currentNode, AbstractTranslet translet) {
/*  91 */     this._source = source;
/*  92 */     this._filter = filter;
/*  93 */     this._translet = translet;
/*  94 */     this._docOrder = docOrder;
/*  95 */     this._currentNode = currentNode;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator forceNaturalOrder() {
/*  99 */     this._docOrder = true;
/* 100 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/* 104 */     this._isRestartable = isRestartable;
/* 105 */     this._source.setRestartable(isRestartable);
/*     */   }
/*     */   
/*     */   public boolean isReverse() {
/* 109 */     return !this._docOrder;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/* 114 */     try { CurrentNodeListIterator clone = (CurrentNodeListIterator)clone();
/*     */       
/* 116 */       clone._nodes = (IntegerArray)this._nodes.clone();
/* 117 */       clone._source = this._source.cloneIterator();
/* 118 */       clone._isRestartable = false;
/* 119 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/* 122 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/* 124 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/* 129 */     this._currentIndex = 0;
/* 130 */     return resetPosition();
/*     */   }
/*     */   
/*     */   public int next() {
/* 134 */     int last = this._nodes.cardinality();
/* 135 */     int currentNode = this._currentNode;
/* 136 */     AbstractTranslet translet = this._translet;
/*     */     
/* 138 */     for (int index = this._currentIndex; index < last; ) {
/* 139 */       int position = this._docOrder ? (index + 1) : (last - index);
/* 140 */       int node = this._nodes.at(index++);
/*     */       
/* 142 */       if (this._filter.test(node, position, last, currentNode, translet, (DTMAxisIterator)this)) {
/*     */         
/* 144 */         this._currentIndex = index;
/* 145 */         return returnNode(node);
/*     */       } 
/*     */     } 
/* 148 */     return -1;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/* 152 */     if (this._isRestartable) {
/* 153 */       this._source.setStartNode(this._startNode = node);
/*     */       
/* 155 */       this._nodes.clear();
/* 156 */       while ((node = this._source.next()) != -1) {
/* 157 */         this._nodes.add(node);
/*     */       }
/* 159 */       this._currentIndex = 0;
/* 160 */       resetPosition();
/*     */     } 
/* 162 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public int getLast() {
/* 166 */     if (this._last == -1) {
/* 167 */       this._last = computePositionOfLast();
/*     */     }
/* 169 */     return this._last;
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 173 */     this._markedNode = this._currentIndex;
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 177 */     this._currentIndex = this._markedNode;
/*     */   }
/*     */   
/*     */   private int computePositionOfLast() {
/* 181 */     int last = this._nodes.cardinality();
/* 182 */     int currNode = this._currentNode;
/* 183 */     AbstractTranslet translet = this._translet;
/*     */     
/* 185 */     int lastPosition = this._position;
/* 186 */     for (int index = this._currentIndex; index < last; ) {
/* 187 */       int position = this._docOrder ? (index + 1) : (last - index);
/* 188 */       int nodeIndex = this._nodes.at(index++);
/*     */       
/* 190 */       if (this._filter.test(nodeIndex, position, last, currNode, translet, (DTMAxisIterator)this))
/*     */       {
/* 192 */         lastPosition++;
/*     */       }
/*     */     } 
/* 195 */     return lastPosition;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/CurrentNodeListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */