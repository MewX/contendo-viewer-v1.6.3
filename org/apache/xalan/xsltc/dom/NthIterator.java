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
/*     */ public final class NthIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private DTMAxisIterator _source;
/*     */   private final int _position;
/*     */   private boolean _ready;
/*     */   
/*     */   public NthIterator(DTMAxisIterator source, int n) {
/*  37 */     this._source = source;
/*  38 */     this._position = n;
/*     */   }
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/*  42 */     this._isRestartable = isRestartable;
/*  43 */     this._source.setRestartable(isRestartable);
/*     */   }
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/*  48 */     try { NthIterator clone = (NthIterator)clone();
/*  49 */       clone._source = this._source.cloneIterator();
/*  50 */       clone._isRestartable = false;
/*  51 */       return (DTMAxisIterator)clone; } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*  54 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/*  56 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public int next() {
/*  61 */     if (this._ready) {
/*  62 */       this._ready = false;
/*  63 */       return this._source.getNodeByPosition(this._position);
/*     */     } 
/*  65 */     return -1;
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
/*     */   public DTMAxisIterator setStartNode(int node) {
/*  85 */     if (this._isRestartable) {
/*  86 */       this._source.setStartNode(node);
/*  87 */       this._ready = true;
/*     */     } 
/*  89 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/*  93 */     this._source.reset();
/*  94 */     this._ready = true;
/*  95 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public int getLast() {
/*  99 */     return 1;
/*     */   }
/*     */   
/*     */   public int getPosition() {
/* 103 */     return 1;
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 107 */     this._source.setMark();
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 111 */     this._source.gotoMark();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/NthIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */