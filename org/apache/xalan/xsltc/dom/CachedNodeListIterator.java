/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
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
/*     */ public final class CachedNodeListIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private DTMAxisIterator _source;
/*  38 */   private IntegerArray _nodes = new IntegerArray();
/*  39 */   private int _numCachedNodes = 0;
/*  40 */   private int _index = 0;
/*     */   private boolean _isEnded = false;
/*     */   
/*     */   public CachedNodeListIterator(DTMAxisIterator source) {
/*  44 */     this._source = source;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {}
/*     */ 
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/*  53 */     if (this._isRestartable) {
/*  54 */       this._startNode = node;
/*  55 */       this._source.setStartNode(node);
/*  56 */       resetPosition();
/*     */       
/*  58 */       this._isRestartable = false;
/*     */     } 
/*  60 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public int next() {
/*  64 */     return getNode(this._index++);
/*     */   }
/*     */   
/*     */   public int getPosition() {
/*  68 */     return (this._index == 0) ? 1 : this._index;
/*     */   }
/*     */   
/*     */   public int getNodeByPosition(int pos) {
/*  72 */     return getNode(pos);
/*     */   }
/*     */   
/*     */   public int getNode(int index) {
/*  76 */     if (index < this._numCachedNodes) {
/*  77 */       return this._nodes.at(index);
/*     */     }
/*  79 */     if (!this._isEnded) {
/*  80 */       int node = this._source.next();
/*  81 */       if (node != -1) {
/*  82 */         this._nodes.add(node);
/*  83 */         this._numCachedNodes++;
/*     */       } else {
/*     */         
/*  86 */         this._isEnded = true;
/*     */       } 
/*  88 */       return node;
/*     */     } 
/*     */     
/*  91 */     return -1;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*  95 */     ClonedNodeListIterator clone = new ClonedNodeListIterator(this);
/*  96 */     return (DTMAxisIterator)clone;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/* 100 */     this._index = 0;
/* 101 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 105 */     this._source.setMark();
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 109 */     this._source.gotoMark();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/CachedNodeListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */