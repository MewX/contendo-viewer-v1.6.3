/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.NodeIterator;
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NodeIteratorBase
/*     */   implements NodeIterator
/*     */ {
/*  35 */   protected int _last = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   protected int _position = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int _markedNode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected int _startNode = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _includeSelf = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _isRestartable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/*  67 */     this._isRestartable = isRestartable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract NodeIterator setStartNode(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeIterator reset() {
/*  82 */     boolean temp = this._isRestartable;
/*  83 */     this._isRestartable = true;
/*     */     
/*  85 */     setStartNode(this._includeSelf ? (this._startNode + 1) : this._startNode);
/*  86 */     this._isRestartable = temp;
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeIterator includeSelf() {
/*  94 */     this._includeSelf = true;
/*  95 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLast() {
/* 104 */     if (this._last == -1) {
/* 105 */       int temp = this._position;
/* 106 */       setMark();
/* 107 */       reset();
/*     */       while (true) {
/* 109 */         this._last++;
/* 110 */         if (next() == -1)
/* 111 */         { gotoMark();
/* 112 */           this._position = temp; break; } 
/*     */       } 
/* 114 */     }  return this._last;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 121 */     return (this._position == 0) ? 1 : this._position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReverse() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeIterator cloneIterator() {
/*     */     
/* 141 */     try { NodeIteratorBase clone = (NodeIteratorBase)clone();
/* 142 */       clone._isRestartable = false;
/* 143 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/* 146 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/* 148 */       return null; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int returnNode(int node) {
/* 157 */     this._position++;
/* 158 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final NodeIterator resetPosition() {
/* 165 */     this._position = 0;
/* 166 */     return this;
/*     */   }
/*     */   
/*     */   public abstract void gotoMark();
/*     */   
/*     */   public abstract void setMark();
/*     */   
/*     */   public abstract int next();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/NodeIteratorBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */